package com.xuegao.educloud.user.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuegao.educloud.user.client.entities.Campus;
import com.xuegao.educloud.user.client.params.dto.CampusDTO;
import com.xuegao.educloud.user.client.params.dto.CampusDeptDTO;
import com.xuegao.educloud.user.server.dao.CampusDao;
import com.xuegao.educloud.user.server.service.ICampusDepartmentService;
import com.xuegao.educloud.user.server.service.ICampusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: LIM
 * @Date: 2019/11/5 14:25
 * @Description:
 */
@Service
public class CampusService extends ServiceImpl<CampusDao, Campus> implements ICampusService {

    @Autowired
    private ICampusDepartmentService campusDepartmentService;

    @Override
    public Integer saveCampus(CampusDTO campusDTO) {
        Campus campus = new Campus().setCampusName(campusDTO.getCampusName())
                .setProvince(campusDTO.getProvince()).setCity(campusDTO.getCity())
                .setCounty(campusDTO.getCounty()).setPerson(campusDTO.getPerson())
                .setAddrDetail(campusDTO.getAddrDetail()).setTel(campusDTO.getTel());
        return baseMapper.insert(campus);
    }

    @Override
    public Integer updateCampus(CampusDTO campusDTO) {
        LambdaUpdateWrapper<Campus> updateWrapper = Wrappers.<Campus>lambdaUpdate()
                .eq(Campus::getCampusId, campusDTO.getCampusId())
                .set(Campus::getCampusName, campusDTO.getCampusName())
                .set(Campus::getPerson, campusDTO.getPerson())
                .set(Campus::getProvince, campusDTO.getProvince())
                .set(Campus::getCity, campusDTO.getCity())
                .set(Campus::getCounty, campusDTO.getCounty())
                .set(Campus::getAddrDetail, campusDTO.getAddrDetail())
                .set(Campus::getTel, campusDTO.getTel());
        return baseMapper.update(null, updateWrapper);
    }

    @Override
    public IPage<CampusDTO> getCampusDeptTreePage(Page<CampusDTO> page, CampusDTO campusDTO) {
        IPage<CampusDTO> sourcePage = baseMapper.getCampusPage(page, campusDTO);
        List<CampusDTO> campusDTOS = sourcePage.getRecords();
        if (campusDTOS != null && campusDTOS.size() > 0) {
            for (CampusDTO dto : campusDTOS) {
                List<String> nameList = new ArrayList<>();
                List<CampusDeptDTO> departmentList = campusDepartmentService.getDeptTreeByCampusId(dto.getCampusId());
                if(departmentList != null && departmentList.size() > 0){
                    for (CampusDeptDTO campusDepartment : departmentList) {
                        List<String> stringList = getDepartmentName(campusDepartment);
                        nameList.addAll(stringList);
                    }
                    String departmentStr = listToString(nameList);
                    dto.setDepartmentNameStr(departmentStr);
                }
            }
        }
        return sourcePage;
    }

    @Override
    public List<Campus> getCampusList() {
        LambdaQueryWrapper<Campus> queryWrapper = Wrappers.<Campus>lambdaQuery()
                .eq(Campus::getIsDel, 0)
                .select(Campus::getCampusId, Campus::getCampusName);
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<CampusDTO> getCampusPage(Page<CampusDTO> page, CampusDTO campusDTO) {
        IPage<CampusDTO> sourcePage = baseMapper.getCampusPage(page, campusDTO);
        return sourcePage;
    }

    private String listToString(List<String> list) {
        char separator = '、';
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i < list.size() - 1) {
                sb.append(separator);
            }
        }
        return sb.toString();
    }

    //将list集合转成树形结构的list集合
    private List<String> getDepartmentName(CampusDeptDTO departmentDto) {
        //用递归找子。
        List<String> nameList = new ArrayList<>();
        nameList.add(departmentDto.getDepartmentName());
        if (departmentDto.getParentId() == 0) {
            nameList = getNodeName(nameList, departmentDto);
        }
        return nameList;
    }

    //寻找子节点
    private List<String> getNodeName(List<String> nameList, CampusDeptDTO departmentDTO) {
        if (departmentDTO.getChildren() != null && departmentDTO.getChildren().size() > 0) {
            for (CampusDeptDTO node : departmentDTO.getChildren()) {
                if (node.getParentId() == departmentDTO.getDepartmentId()) {
                    nameList.add(node.getDepartmentName());
                    nameList = getNodeName(nameList, node);
                }
            }
        }
        return nameList;
    }
}
