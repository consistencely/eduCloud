package com.xuegao.educloud.user.server.controller;

import cn.hutool.core.collection.IterUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuegao.educloud.common.error.ErrorCode;
import com.xuegao.educloud.common.params.R;
import com.xuegao.educloud.system.client.entities.Grade;
import com.xuegao.educloud.system.client.feign.SystemClient;
import com.xuegao.educloud.user.client.entities.Campus;
import com.xuegao.educloud.user.client.entities.Role;
import com.xuegao.educloud.user.client.entities.User;
import com.xuegao.educloud.user.client.params.dto.UserInfoDTO;
import com.xuegao.educloud.user.client.params.dto.UserQuery;
import com.xuegao.educloud.user.client.params.vo.UserVO;
import com.xuegao.educloud.user.server.constants.UserConstants;
import com.xuegao.educloud.user.server.service.ICampusService;
import com.xuegao.educloud.user.server.service.IUserService;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @Auther: LIM
 * @Date: 2019/11/8 13:53
 * @Description:
 */
@RestController
@RequestMapping
@Slf4j
public class UserController {


    @Autowired
    private IUserService userService;
    @Autowired
    private ICampusService campusService;
    @Autowired
    private SystemClient systemClient;

    /**
     * 新增用户
     * @param userInfoDTO
     * @return
     */
    @PostMapping("/user")
    public R saveUser(@RequestBody UserInfoDTO userInfoDTO){

        R result = this.verifyUserParam(userInfoDTO);
        if(!result.isSuccess()){
            return result;
        }
        boolean exist = userService.isPhoneExist(userInfoDTO.getPhone());
        if(exist){
            return R.fail(ErrorCode.Error_40001,"手机号码已存在");
        }

        userService.saveUser(userInfoDTO);
        return R.ok();
    }

    /**
     * 修改用户
     * @param userInfoDTO
     * @return
     */
    @PutMapping("/user")
    public R updateUser(@RequestBody UserInfoDTO userInfoDTO){

        //参数校验
        Long userId = userInfoDTO.getUserId();
        if(userId == null){
            return R.fail("用户ID为空");
        }
        R result = this.verifyUserParam(userInfoDTO);
        if(!result.isSuccess()){
            return result;
        }

        User user = userService.getById(userId);
        if(user == null){
            return R.fail("用户不存在");
        }
        if(user.getStatus() == UserConstants.USER_STATUS_DEL){
            log.error("该用户[{}]已注销，不可修改",userId);
            return R.fail("该用户已注销");
        }
        User phone_user = userService.getUserByPhone(userInfoDTO.getPhone());
        if(phone_user != null && !phone_user.getUserId().equals(userId)){
             return R.fail("手机号码已存在");
        }
        userService.updateUser(userInfoDTO);
        return R.ok();
    }

    /**
     * 校验用户参数
     * @param userInfoDTO
     * @return
     */
    private R verifyUserParam(UserInfoDTO userInfoDTO){
        if(!Validator.isMobile(userInfoDTO.getPhone())){
            return R.fail(ErrorCode.Error_40001,"手机格式错误");
        }
        //校验密码
        if(StrUtil.isEmpty(userInfoDTO.getPassword())
                || !Pattern.matches(UserConstants.PATTERN_PWD, userInfoDTO.getPassword())){
            return R.fail(ErrorCode.Error_40002,"密码格式不正确");
        }

        if(ArrayUtil.isEmpty(userInfoDTO.getRoleIds())){
            return R.fail("请选择角色");
        }
        if(ArrayUtil.isEmpty(userInfoDTO.getGradeIds())){
            return R.fail("请选择年级");
        }
        if(userInfoDTO.getCampusId() == null){
            return R.fail("请选择所属校区");
        }

        if(userInfoDTO.getValidType() == UserConstants.VALID_TYPE_PERPETUAL){
            userInfoDTO.setValidStart(null).setValidEnd(null);
        }else if(userInfoDTO.getValidType() == UserConstants.VALID_TYPE_OVERDUE){
            userInfoDTO.setValidStart(null);
            if(userInfoDTO.getValidEnd() == null){
                return R.fail("请选择过期时间");
            }
        }else if(userInfoDTO.getValidType() == UserConstants.VALID_TYPE_SCOPE){
            if(userInfoDTO.getValidStart() == null){
                return R.fail("请选择有效期开始时间");
            }
            if(userInfoDTO.getValidEnd() == null){
                return R.fail("请选择有效期过期时间");
            }
            if(!userInfoDTO.getValidStart().before(userInfoDTO.getValidEnd())){
                return R.fail("请选择正确有效时间");
            }
        }else{
            return R.fail("请选择正确的有效类型");
        }

        return R.ok();
    }

    /**
     * 用户信息
     * @param userId
     * @return
     */
    @GetMapping("/user/wholeinfo/{userId}")
    public R<UserInfoDTO> getUserInfo(@PathVariable("userId") long userId){
        UserInfoDTO userInfo = userService.getUserInfo(userId);
        if(userInfo == null){
            return R.fail("用户不存在");
        }
        return R.ok(userInfo);
    }

    /**
     * 修改密码
     * @return
     */
    @PutMapping("/user/password")
    public R updatePwd(@RequestBody User user){

        Long userId = user.getUserId();
        String newPwd = user.getPassword();

        //校验密码
        if(StrUtil.isEmpty(newPwd)
                || !Pattern.matches(UserConstants.PATTERN_PWD, newPwd)){
            return R.fail(ErrorCode.Error_40002,"密码格式不正确");
        }
        User db_user = userService.getById(userId);
        if(db_user == null){
            return R.fail("用户不存在");
        }
        if(db_user.getStatus() == UserConstants.USER_STATUS_DEL){
            return R.fail("用户已注销");
        }

        boolean success = userService.updatePwd(userId,db_user.getUuid(),newPwd);
        return success ? R.ok() : R.fail("修改密码失败");
    }

    /**
     * 批量修改用户状态
     * @param statusCode 状态码
     * @return
     */
    @PutMapping("/users/status/{statusCode}")
    public R batchUpdateStatus(@PathVariable("statusCode") byte statusCode,@RequestBody Map<String,Object> userMap){
        List<Long> userIds = (List<Long>) userMap.get("userIds");
        if(IterUtil.isEmpty(userIds)){
            return R.fail("请选择用户");
        }
        if(statusCode != UserConstants.USER_STATUS_DEL
                && statusCode != UserConstants.USER_STATUS_NORMAL
                && statusCode != UserConstants.USER_STATUS_LOCK){
            return R.fail("请选择正确的状态");
        }

        List<User> users = (List<User>) userService.listByIds(userIds);
        boolean hasDel = false;
        for (User user : users) {
            if(user.getStatus() == UserConstants.USER_STATUS_DEL){
                hasDel = true;
                break;
            }
        }
        if(hasDel){
            return R.fail("包含已注销用户，操作失败");
        }
        userService.batchUpdateStatus(statusCode, userIds);
        return R.ok();
    }

    /**
     * 批量修改年级、从属机构
     * @param userInfo
     * @return
     */
    @PutMapping("/users")
    public R batchUpdate(@RequestBody UserInfoDTO userInfo){
        if(ArrayUtil.isEmpty(userInfo.getUserIds())){
            return R.fail("请选择用户");
        }

        List<User> users = (List<User>) userService.listByIds(Arrays.asList(userInfo.getUserIds()));
        boolean hasDel = false;
        for (User user : users) {
            if(user.getStatus() == UserConstants.USER_STATUS_DEL){
                hasDel = true;
                break;
            }
        }
        if(hasDel){
            return R.fail("包含已注销用户，操作失败");
        }

        if(ArrayUtil.isNotEmpty(userInfo.getGradeIds())){
            List<Grade> grades = systemClient.getGradeByIds(userInfo.getGradeIds()).getData();
            if(IterUtil.isEmpty(grades) || grades.size() != userInfo.getGradeIds().length){
                return R.fail("内含不合法年级");
            }
        }
        if(userInfo.getCampusId() != null){
            Campus campus = campusService.getById(userInfo.getCampusId());
            if(campus == null){
                return R.fail("校区不存在");
            }
        }

        userService.batchUpdate(userInfo);

        return R.ok();

    }


    /**
     * 分页查询用户列表
     * @param curr
     * @param userQuery
     * @return
     */
    @GetMapping("/users/page/{curr}")
    public R<IPage> userInfoPage(@PathVariable("curr") int curr, @ModelAttribute UserQuery userQuery ){
        Page<UserVO> page = new Page<UserVO>().setCurrent(curr);
        IPage<UserVO> userPage = userService.getUserPage(page,userQuery);
        return R.ok(userPage);
    }

}
