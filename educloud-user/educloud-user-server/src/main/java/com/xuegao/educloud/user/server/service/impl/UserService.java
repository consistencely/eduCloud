package com.xuegao.educloud.user.server.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuegao.educloud.user.client.entities.User;
import com.xuegao.educloud.user.client.entities.UserAddress;
import com.xuegao.educloud.user.client.params.dto.UserInfoDTO;
import com.xuegao.educloud.user.client.params.dto.UserQuery;
import com.xuegao.educloud.user.client.params.vo.UserVO;
import com.xuegao.educloud.user.server.constants.UserConstants;
import com.xuegao.educloud.user.server.dao.UserDao;
import com.xuegao.educloud.user.server.service.IUserAddressService;
import com.xuegao.educloud.user.server.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


/**
 * @Auther: LIM
 * @Date: 2019/11/5 14:25
 * @Description:
 */
@Service
public class UserService extends ServiceImpl<UserDao, User> implements IUserService {

    @Autowired
    private IUserAddressService userAddressService;


    /**
     * 新增用户
     * TODO 分部式事务？
     *
     * @param userInfoDTO
     */
    @Override
    public void saveUser(UserInfoDTO userInfoDTO) {


        //记录用户
        String phone = userInfoDTO.getPhone();
        String phoneSub = StrUtil.sub(phone, phone.length() - 4, phone.length());//手机后四位
        String avatar = StrUtil.isNotEmpty(userInfoDTO.getAvatar()) ? userInfoDTO.getAvatar() : UserConstants.DEFAULT_AVATAR;
        String nickname = StrUtil.isNotEmpty(userInfoDTO.getNickname()) ? userInfoDTO.getNickname() : phoneSub;

        String uuid = UUID.randomUUID().toString();

        User user = new User()
                .setAvatar(avatar)
                .setUsername(this.getRandomUsername(phoneSub))
                .setNickname(nickname)
                .setPhone(phone)
                .setPassword(this.encryptPwd(userInfoDTO.getPassword() + uuid))
                .setSourceId(userInfoDTO.getSourceId())
                .setOrganizationId(userInfoDTO.getOrganizationId())
                .setBirthday(userInfoDTO.getBirthday())
                .setSchool(userInfoDTO.getSchool())
                .setRegisterFrom(UserConstants.REGISTER_FROM_ADMIN)
                .setUuid(uuid)
                .setExpireTime(userInfoDTO.getExpireTime())
                .setRegisterTime(new Date());
        baseMapper.insert(user);


        //记录用户角色
        baseMapper.saveUserRole(user.getUserId(), userInfoDTO.getRoleIds());

        //记录用户年级
        baseMapper.saveUserGrade(user.getUserId(), userInfoDTO.getGradeIds());

        //记录用户地址
        UserAddress address = new UserAddress()
                .setUserId(user.getUserId())
                .setProvince(userInfoDTO.getProvince())
                .setCity(userInfoDTO.getCity())
                .setCounty(userInfoDTO.getCounty())
                .setAddrDetail(userInfoDTO.getAddrDetail())
                .setTel(userInfoDTO.getTel());
        userAddressService.save(address);

    }

    /**
     * 修改用户
     * TODO 分部式事务？
     *
     * @param userInfoDTO
     */
    @Override
    public void updateUser(UserInfoDTO userInfoDTO) {

        String phone = userInfoDTO.getPhone();
        String phoneSub = StrUtil.sub(phone, phone.length() - 4, phone.length());//手机后四位
        String avatar = StrUtil.isNotEmpty(userInfoDTO.getAvatar()) ? userInfoDTO.getAvatar() : UserConstants.DEFAULT_AVATAR;
        String nickname = StrUtil.isNotEmpty(userInfoDTO.getNickname()) ? userInfoDTO.getNickname() : phoneSub;
        long userId = userInfoDTO.getUserId();

        //修改用户
        User user = new User()
                .setUserId(userId)
                .setAvatar(avatar)
                .setPhone(phone)
                .setNickname(nickname)
                .setSourceId(userInfoDTO.getSourceId())
                .setOrganizationId(userInfoDTO.getOrganizationId())
                .setBirthday(userInfoDTO.getBirthday())
                .setExpireTime(userInfoDTO.getExpireTime())
                .setSchool(userInfoDTO.getSchool());

        baseMapper.updateById(user);

        //记录用户角色
        baseMapper.clearUserRole(userId);
        baseMapper.saveUserRole(userId, userInfoDTO.getRoleIds());

        //记录用户年级
        baseMapper.clearUserGrade(userId);
        baseMapper.saveUserGrade(userId, userInfoDTO.getGradeIds());

        //记录用户地址
        UserAddress userAddress = userAddressService.getOneByUserId(userId);
        if (userAddress == null) {
            userAddress = new UserAddress().setUserId(userId);

        }
        userAddress.setProvince(userInfoDTO.getProvince())
                .setCity(userInfoDTO.getCity())
                .setCounty(userInfoDTO.getCounty())
                .setAddrDetail(userInfoDTO.getAddrDetail())
                .setTel(userInfoDTO.getTel());
        userAddressService.updateById(userAddress);


    }

    /**
     * 密码加密
     *
     * @param password 未加密密码
     * @return 加密后的密码
     */
    private String encryptPwd(String password) {
        return SecureUtil.md5(password);
    }

    /**
     * 生成系统账号
     *
     * @return
     */
    private String getRandomUsername(String sub) {
        String format = "XG_{}{}";
        String random = RandomUtil.randomString(8);
        return StrUtil.format(format, random, sub);
    }

    /**
     * 手机号是否存在
     *
     * @param phone
     * @return
     */
    @Override
    public boolean isPhoneExist(String phone) {
        if (StrUtil.isEmpty(phone)) {
            return false;
        }
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery().eq(User::getPhone, phone);

        return count(wrapper) > 0;
    }

    /**
     * 手机号查用户
     *
     * @param phone
     * @return
     */
    @Override
    public User getUserByPhone(String phone) {
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery()
                .eq(User::getPhone, phone)
                .eq(User::getStatus, UserConstants.USER_STATUS_NORMAL);

        return getOne(wrapper);
    }

    /**
     * 用户所属角色
     *
     * @param userId
     * @return
     */
    @Override
    public List<Integer> getUserRoles(long userId) {
        return baseMapper.getUserRoles(userId);
    }

    /**
     * 用户所属年级
     *
     * @param userId
     * @return
     */
    @Override
    public List<Integer> getUserGrades(long userId) {
        return baseMapper.getUserGrades(userId);
    }

    /**
     * 用户信息
     *
     * @param userId
     * @return
     */
    @Override
    public UserInfoDTO getUserInfo(long userId) {

        User user = this.getById(userId);
        if (user == null) {
            return null;
        }

        UserInfoDTO userInfo = new UserInfoDTO();
        BeanUtils.copyProperties(user, userInfo);
        //封装角色
        List<Integer> roles = this.getUserRoles(userId);
        userInfo.setRoleIds(Convert.toIntArray(roles));
        //封装年级
        List<Integer> grades = this.getUserGrades(userId);
        userInfo.setGradeIds(Convert.toIntArray(grades));
        //封装地址
        UserAddress userAddress = userAddressService.getOneByUserId(userId);
        //封装数据
        userInfo.setProvince(userAddress.getProvince())
                .setCity(userAddress.getCity())
                .setCounty(userAddress.getCounty())
                .setAddrDetail(userAddress.getAddrDetail())
                .setTel(userAddress.getTel());

        return userInfo;
    }

    /**
     * 批量修改用户状态
     * TODO 分部式事务？
     *
     * @param statusCode
     * @param userIds
     */
    @Override
    public void batchUpdateStatus(byte statusCode, List<Long> userIds) {
        List<User> userList = userIds.stream()
                .map(userId -> new User().setUserId(userId).setStatus(statusCode))
                .collect(Collectors.toList());
        this.updateBatchById(userList);
    }

    /**
     * 批量修改用户
     * TODO 分部式事务？
     *
     * @param userInfo
     */
    @Override
    public void batchUpdate(UserInfoDTO userInfo) {
        Long[] userIds = userInfo.getUserIds();
        if(ArrayUtil.isNotEmpty(userIds)){
            if(ArrayUtil.isNotEmpty(userInfo.getGradeIds())){

                baseMapper.batchClearUserGrade(userIds);

                Arrays.stream(userIds).forEach(userId->{
                    baseMapper.saveUserGrade(userId, userInfo.getGradeIds());
                });
            }
            if(userInfo.getOrganizationId() != null){
                baseMapper.batchUpdateOrgan(userInfo.getOrganizationId(), userIds);
            }
        }

    }

    /**
     * 分页查询用户信息
     *
     * @param page
     * @param userQuery
     * @return
     */
    @Override
    public IPage<UserVO> getUserPage(Page<UserVO> page, UserQuery userQuery) {
        IPage<UserVO> userPage = baseMapper.getUserPage(page, userQuery);
        if(userPage != null && ArrayUtil.isNotEmpty(userPage.getRecords())){
            List<UserVO> userList = userPage.getRecords();
            List<Long> userIds = userList.stream().map(UserVO::getUserId).collect(Collectors.toList());

            /**
             * TODO
             SELECT `user`.user_id,tmp_grade.gradeName, tmp_role.roleName  FROM educloud_user user
             LEFT JOIN (
             SELECT userGrade.user_id,GROUP_CONCAT(grade.`name` SEPARATOR '、') gradeName
             FROM educloud_user_grade userGrade,educloud_system.educloud_grade grade
             WHERE userGrade.grade_id = grade.id
             GROUP BY userGrade.user_id
             ) tmp_grade ON `user`.user_id = tmp_grade.user_id
             LEFT JOIN (
             SELECT userRole.user_id, GROUP_CONCAT(role.role_name SEPARATOR '、') roleName
             FROM educloud_user_role userRole,educloud_role role
             WHERE userRole.role_id = role.role_id
             GROUP BY userRole.user_id
             ) tmp_role ON `user`.user_id = tmp_role.user_id
             WHERE `user`.user_id IN (1,2)
             */

        }
        return userPage;
    }
}
