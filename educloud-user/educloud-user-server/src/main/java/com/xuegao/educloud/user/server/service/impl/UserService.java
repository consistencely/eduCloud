package com.xuegao.educloud.user.server.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuegao.educloud.user.client.entities.Role;
import com.xuegao.educloud.user.client.entities.User;
import com.xuegao.educloud.user.client.params.dto.UserInfoDTO;
import com.xuegao.educloud.user.server.dao.RoleDao;
import com.xuegao.educloud.user.server.dao.UserDao;
import com.xuegao.educloud.user.server.service.IRoleService;
import com.xuegao.educloud.user.server.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Auther: LIM
 * @Date: 2019/11/5 14:25
 * @Description:
 */
@Service
public class UserService extends ServiceImpl<UserDao, User> implements IUserService {


    /**
     * 新增用户
     *
     * @param userInfoDTO
     */
    @Override
    public void saveUser(UserInfoDTO userInfoDTO) {


        //记录用户
        String phone = userInfoDTO.getPhone();
        String phoneSub = StrUtil.sub(phone,phone.length() - 4 , phone.length());//手机后四位
        String avatar = StrUtil.isNotEmpty(userInfoDTO.getAvatar()) ? userInfoDTO.getAvatar() : "";
        String nickname = StrUtil.isNotEmpty(userInfoDTO.getNickname()) ? userInfoDTO.getNickname() : phoneSub;

        User user = new User()
                .setAvatar(avatar)
                .setUsername(this.getRandomUsername(phoneSub))
                .setNickname(nickname)
                .setPhone(phone)
                .setPassword(this.encryptPwd(userInfoDTO.getPassword()))
                .setSourceId(userInfoDTO.getSourceId())
                .setOrganizationId(userInfoDTO.getOrganizationId())
                .setBirthday(userInfoDTO.getBirthday())
                .setSchool(userInfoDTO.getSchool())
                .setRegisterFrom((byte)3)
                .setRegisterTime(new Date())
                ;
        baseMapper.insert(user);


        //记录用户角色
        baseMapper.saveUserRole(user.getUserId(),userInfoDTO.getRoleIds());
        //记录用户年级
        baseMapper.saveUserGrade(user.getUserId(),userInfoDTO.getGradeIds());
        //记录用户地址
    }

    /**
     * 密码加密
     *
     * @param password 未加密密码
     * @return 加密后的密码
     */
    private String encryptPwd(String password) {
        return password;
    }

    /**
     * 生成系统账号
     * @return
     */
    private String getRandomUsername(String sub){
        String format = "XG_{}{}";
        String random = RandomUtil.randomString(8);
        return StrUtil.format(format, random, sub);
    }
}
