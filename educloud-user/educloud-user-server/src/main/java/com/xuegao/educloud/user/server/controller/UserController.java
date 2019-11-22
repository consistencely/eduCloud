package com.xuegao.educloud.user.server.controller;

import cn.hutool.core.collection.IterUtil;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuegao.educloud.common.constants.CommonConstants;
import com.xuegao.educloud.common.exception.InvalidRequestException;
import com.xuegao.educloud.common.exception.ServiceException;
import com.xuegao.educloud.common.response.R;
import com.xuegao.educloud.system.client.entities.Grade;
import com.xuegao.educloud.system.client.feign.RemoteGradeService;
import com.xuegao.educloud.user.client.entities.Campus;
import com.xuegao.educloud.user.client.entities.User;
import com.xuegao.educloud.user.client.params.dto.UserInfoDTO;
import com.xuegao.educloud.user.client.params.dto.UserQuery;
import com.xuegao.educloud.user.client.params.vo.UserVO;
import com.xuegao.educloud.user.server.constants.UserConstants;
import com.xuegao.educloud.user.server.error.ECUserExceptionEnum;
import com.xuegao.educloud.user.server.service.ICampusService;
import com.xuegao.educloud.user.server.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @Auther: LIM
 * @Date: 2019/11/8 13:53
 * @Description:
 */
@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {


    @Autowired
    private IUserService userService;
    @Autowired
    private ICampusService campusService;
    @Autowired
    private RemoteGradeService remoteGradeService;

    @GetMapping("/demo")
    public R<List<Grade>> getGrades(){
        Integer[] ids = new Integer[]{1,2,3,4};
        R<List<Grade>> result = remoteGradeService.getGradeByIds(ids);
        log.info(JSONUtil.toJsonStr(result));
        return result;
    }

    /**
     * 新增用户
     * @param userInfoDTO
     * @return
     */
    @PostMapping
    public void saveUser(@RequestBody UserInfoDTO userInfoDTO){

        //校验参数
        this.verifyUserParam(userInfoDTO);

        boolean exist = userService.isPhoneExist(userInfoDTO.getPhone());
        if(exist){
            throw new ServiceException(ECUserExceptionEnum.PHONE_EXIST);
        }

        userService.saveUser(userInfoDTO);
    }

    /**
     * 修改用户
     * @param userInfoDTO
     * @return
     */
    @PutMapping("/{userId}")
    public void updateUser(@PathVariable("userId") long userId,@RequestBody UserInfoDTO userInfoDTO){

        //校验参数
        this.verifyUserParam(userInfoDTO);

        User user = userService.getById(userId);
        if(user == null){
            throw new ServiceException(ECUserExceptionEnum.USER_NOT_FOUND);
        }
        if(user.getStatus() == UserConstants.USER_STATUS_DEL){
            log.error("该用户[{}]已注销，不可修改",userId);
            throw new ServiceException(ECUserExceptionEnum.USER_DEL);
        }
        User phone_user = userService.getUserByPhone(userInfoDTO.getPhone());
        if(phone_user != null && !phone_user.getUserId().equals(userId)){
            throw new ServiceException(ECUserExceptionEnum.PHONE_EXIST);
        }
        userInfoDTO.setUserId(userId);
        userService.updateUser(userInfoDTO);
    }

    /**
     * 校验用户参数
     * @param userInfoDTO
     * @return
     */
    private void verifyUserParam(UserInfoDTO userInfoDTO){
        if(!Validator.isMobile(userInfoDTO.getPhone())){
            throw new InvalidRequestException("手机格式不正确");
        }
        //校验密码
        if(StrUtil.isEmpty(userInfoDTO.getPassword())
                || !Pattern.matches(UserConstants.PATTERN_PWD, userInfoDTO.getPassword())){
            throw new InvalidRequestException("密码格式不正确");
        }

        if(ArrayUtil.isEmpty(userInfoDTO.getRoleIds())){
            throw new InvalidRequestException("角色参数不正确");
        }
        if(ArrayUtil.isEmpty(userInfoDTO.getGradeIds())){
            throw new InvalidRequestException("年级参数不正确");
        }
        if(userInfoDTO.getCampusId() == null){
            throw new InvalidRequestException("校区不正确");
        }

        if(userInfoDTO.getValidType() == UserConstants.VALID_TYPE_PERPETUAL){
            userInfoDTO.setValidStart(null).setValidEnd(null);
        }else if(userInfoDTO.getValidType() == UserConstants.VALID_TYPE_OVERDUE){
            userInfoDTO.setValidStart(null);
            if(userInfoDTO.getValidEnd() == null){
                throw new InvalidRequestException("过期时间不正确");
            }
        }else if(userInfoDTO.getValidType() == UserConstants.VALID_TYPE_SCOPE){
            if(userInfoDTO.getValidStart() == null){
                throw new InvalidRequestException("有效期开始时间不正确");
            }
            if(userInfoDTO.getValidEnd() == null){
                throw new InvalidRequestException("有效期过期时间不正确");
            }
            if(!userInfoDTO.getValidStart().before(userInfoDTO.getValidEnd())){
                throw new InvalidRequestException("有效时间不正确");
            }
        }else{
            throw new InvalidRequestException("有效类型不正确");
        }

    }

    /**
     * 用户信息
     * @param userId
     * @return
     */
    @GetMapping("/{userId}")
    public UserInfoDTO getUserInfo(@PathVariable("userId") long userId){
        UserInfoDTO userInfo = userService.getUserInfo(userId);
        if(userInfo == null){
            throw new ServiceException(ECUserExceptionEnum.USER_NOT_FOUND);
        }
        return userInfo;
    }

    /**
     * 修改密码
     * @return
     */
    @PutMapping("/{userId}/password")
    public boolean updatePwd(@PathVariable("userId") long userId,@RequestBody User user){

        String newPwd = user.getPassword();

        //校验密码
        if(StrUtil.isEmpty(newPwd)
                || !Pattern.matches(UserConstants.PATTERN_PWD, newPwd)){
            throw new InvalidRequestException("密码格式不正确");
        }
        User db_user = userService.getById(userId);
        if(db_user == null){
            throw new ServiceException(ECUserExceptionEnum.USER_NOT_FOUND);
        }
        if(db_user.getStatus() == UserConstants.USER_STATUS_DEL){
            throw new ServiceException(ECUserExceptionEnum.USER_DEL);
        }

        return userService.updatePwd(userId,db_user.getUuid(),newPwd);
    }

    /**
     * 批量修改用户状态
     * @param statusCode 状态码
     * @return
     */
    @PutMapping("/batch/status/{statusCode}")
    public void batchUpdateStatus(@PathVariable("statusCode") byte statusCode,@RequestBody Map<String,Object> userMap){
        List<Long> userIds = (List<Long>) userMap.get("userIds");
        if(IterUtil.isEmpty(userIds)){
            throw new InvalidRequestException("用户ID不能为空");
        }
        if(statusCode != UserConstants.USER_STATUS_DEL
                && statusCode != UserConstants.USER_STATUS_NORMAL
                && statusCode != UserConstants.USER_STATUS_LOCK){
            throw new InvalidRequestException("状态不正确");
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
            throw new ServiceException(ECUserExceptionEnum.USER_DEL);
        }
        userService.batchUpdateStatus(statusCode, userIds);
    }

    /**
     * 批量修改年级、从属机构
     * @param userInfo
     * @return
     */
    @PutMapping("/batch")
    public void batchUpdate(@RequestBody UserInfoDTO userInfo){
        if(ArrayUtil.isEmpty(userInfo.getUserIds())){
            throw new InvalidRequestException("用户ID不能为空");
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
            throw new ServiceException(ECUserExceptionEnum.USER_DEL);
        }

        if(ArrayUtil.isNotEmpty(userInfo.getGradeIds())){
            List<Grade> grades = remoteGradeService.getGradeByIds(userInfo.getGradeIds()).getData();
            if(IterUtil.isEmpty(grades) || grades.size() != userInfo.getGradeIds().length){
                throw new ServiceException(ECUserExceptionEnum.INCLUDE_INVALID_GRADE);
            }
        }
        if(userInfo.getCampusId() != null){
            Campus campus = campusService.getById(userInfo.getCampusId());
            if(campus == null){
                throw new ServiceException(ECUserExceptionEnum.CAMPUS_NOT_FOUND);
            }
        }

        userService.batchUpdate(userInfo);


    }


    /**
     * 分页查询用户列表
     * @param pageNum
     * @param userQuery
     * @return
     */
    @GetMapping("/page")
    public IPage<UserVO> userInfoPage(@RequestParam(value = "pageNum",defaultValue = CommonConstants.FIRST_PAGE) Integer pageNum,
                                 @RequestParam(value = "pageSize",defaultValue = CommonConstants.DEFAULT_PAGE_SIZE) Integer pageSize,
                                 @ModelAttribute UserQuery userQuery ){
        Page<UserVO> page = new Page<UserVO>().setCurrent(pageNum).setSize(pageSize);
        return userService.getUserPage(page,userQuery);
    }

}
