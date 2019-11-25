package com.xuegao.educloud.user.server.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuegao.educloud.user.client.entities.CampusDepartment;
import com.xuegao.educloud.user.client.params.dto.CampusDeptDTO;
import com.xuegao.educloud.user.server.dao.CampusDepartmentDao;
import com.xuegao.educloud.user.server.service.ICampusDepartmentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * @Auther: ZWei
 * @Date: 2019/11/18 16:43
 * @Description:
 */
@Service
public class CampusDepartmentService extends ServiceImpl<CampusDepartmentDao, CampusDepartment> implements ICampusDepartmentService {

    @Override
    public Integer saveDepartment(CampusDeptDTO departmentDTO) {
        CampusDepartment campusDepartment = new CampusDepartment()
                .setCampusId(departmentDTO.getCampusId()).setDepartmentName(departmentDTO.getDepartmentName())
                .setLevel(departmentDTO.getLevel()).setPath(departmentDTO.getPath())
                .setParentId(departmentDTO.getParentId());
        return baseMapper.insert(campusDepartment);
    }

    @Override
    public Integer updateCampus(CampusDeptDTO departmentDTO) {
        LambdaUpdateWrapper<CampusDepartment> updateWrapper = Wrappers.<CampusDepartment>lambdaUpdate()
                .eq(CampusDepartment::getDepartmentId, departmentDTO.getDepartmentId())
                .set(CampusDepartment::getDepartmentName, departmentDTO.getDepartmentName());
        return baseMapper.update(null, updateWrapper);
    }

    @Override
    public List<CampusDeptDTO> getDeptTreeByCampusId(Integer campusId) {
        LambdaQueryWrapper<CampusDepartment> queryWrapper = Wrappers.<CampusDepartment>lambdaQuery()
                .eq(CampusDepartment::getCampusId, campusId)
                .select(CampusDepartment::getCampusId, CampusDepartment::getDepartmentId
                        , CampusDepartment::getDepartmentName, CampusDepartment::getLevel
                        , CampusDepartment::getPath, CampusDepartment::getParentId);
        List<CampusDepartment> departments = baseMapper.selectList(queryWrapper);
        List<CampusDeptDTO> list = new ArrayList<>();
        for (CampusDepartment department : departments) {
            CampusDeptDTO campusDepartment = new CampusDeptDTO();
            BeanUtil.copyProperties(department,campusDepartment);
            list.add(campusDepartment);
        }
        List<CampusDeptDTO> dtoList = listToTree(list);
        return dtoList;
    }

    @Override
    public List<CampusDepartment> getDeptByCampusId(Integer campusId) {
        LambdaQueryWrapper<CampusDepartment> queryWrapper = Wrappers.<CampusDepartment>lambdaQuery()
                .eq(CampusDepartment::getCampusId, campusId)
                .select(CampusDepartment::getCampusId, CampusDepartment::getDepartmentId
                        , CampusDepartment::getDepartmentName, CampusDepartment::getLevel
                        , CampusDepartment::getPath, CampusDepartment::getParentId);
        return  baseMapper.selectList(queryWrapper);
    }

    //将list集合转成树形结构的list集合
    private  List<CampusDeptDTO> listToTree(List<CampusDeptDTO> list) {
        //用递归找子。
        List<CampusDeptDTO> treeList = new ArrayList<>();
        for (CampusDeptDTO tree : list) {
            if (tree.getParentId() == 0) {
                treeList.add(findChildren(tree, list));
            }
        }
        return treeList;
    }

    //寻找子节点
    private CampusDeptDTO findChildren(CampusDeptDTO tree, List<CampusDeptDTO> list) {
        for (CampusDeptDTO node : list) {
            if (node.getParentId() == tree.getDepartmentId()) {
                if (tree.getChildren() == null) {
                    tree.setChildren(new ArrayList<>());
                }
                tree.getChildren().add(findChildren(node, list));
            }
        }
        return tree;
    }
}
