package com.iyundao.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.iyundao.base.BaseController;
import com.iyundao.base.Page;
import com.iyundao.base.Pageable;
import com.iyundao.base.annotation.CurrentSubject;
import com.iyundao.base.utils.JsonResult;
import com.iyundao.base.utils.JsonUtils;
import com.iyundao.entity.*;
import com.iyundao.service.ActivityService;
import com.iyundao.service.PermissionService;
import com.iyundao.service.RoleService;
import com.iyundao.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;
import java.util.List;

import static com.iyundao.base.BaseController.ROLE_ADMIN;


/**
 * @ClassName: UserController
 * @project: IYunDao
 * @author: 念
 * @Date: 2019/5/13 16:24
 * @Description: 控制层 - 用户
 * @Version: V2.0
 */
@RequiresUser
@RequiresRoles(ROLE_ADMIN)
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    /**
     * @api {POST} /user/checkCode 检测code
     * @apiGroup User
     * @apiVersion 2.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 检测编号是否存在
     * @apiParam {String} code
     * @apiParamExample {json} 请求样例：
     *                ?code=1234
     * @apiSuccess (200) {String} code 200:成功</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *     "data": "可以使用"
     * }
     */
    @PostMapping("/checkCode")
    public JsonResult existCode(String code) {
        jsonResult.setData(userService.existsCode(code) ? "已存在" : "可以使用");
        return jsonResult;
    }

    /**
     * @api {POST} /user/checkLabelCode 检测标签code
     * @apiGroup User
     * @apiVersion 2.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 检测标签编号是否存在
     * @apiParam {String} code
     * @apiParamExample {json} 请求样例：
     *                ?checkLabelCode=123456
     * @apiSuccess (200) {String} code 200:可以使用</br>
     *                            code 400:已存在</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "已存在",
     *     "data": []
     * }
     */
    @PostMapping("/checkLabelCode")
    public JsonResult checkLabelCode(String code) {
        jsonResult = JsonResult.success();
        if (userService.existsLabelCode(code)) {
            jsonResult.setCode(400);
            jsonResult.setMessage("已存在");
        } else {
            jsonResult.setCode(200);
            jsonResult.setMessage("可以使用");
        }
        return jsonResult;
    }

    /**
     * @api {POST} /user/getIndustry 获取行业
     * @apiGroup User
     * @apiVersion 2.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 获取行业(三级联动)
     * @apiParam {String} id 行业ID,选填,获取子行业时使用
     * @apiParamExample {json} 请求样例：
     *                ?id=c44802e6de624581a8452357fc644500
     * @apiSuccess (200) {String} code 200:成功</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *     "data": [{    "code": "A02",    "name": "林业",    "id": "16866ade43d349708200e4983fa3f71b"},{    "code": "A03",    "name": "畜牧业",    "id": "6587acb48efd4bfc81bc75345378486e"},{    "code": "A05",    "name": "农、林、牧、渔服务业",    "id": "958166fe7ff34e1496d09d10f9af04a3"},{    "code": "A01",    "name": "农业",    "id": "a614a71f14e345b5b4007f36263c9aa0"},{    "code": "A04",    "name": "渔业",    "id": "d1d7469307a342bcb461bbe0716e8ba2"}
     *     ]
     * }
     */
    @GetMapping("/getIndustry")
    public JsonResult getIndustry(String id) {
        List<Industry> list = null;
        if (StringUtils.isBlank(id)) {
            list = userService.findByFatherIsNull();
        } else {
            list = userService.findByFatherId(id);
        }
        JSONArray arr = new JSONArray();
        for (Industry industry : list) {
            JSONObject json = JsonUtils.getJson(industry);
            arr.add(json);
        }
        jsonResult.setData(arr);
        return jsonResult;
    }

    /**
     * @api {POST} /user/checkAccount 检测账号
     * @apiGroup User
     * @apiVersion 2.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 检测账号是否存在
     * @apiParam {String} code
     * @apiParamExample {json} 请求样例：
     *                ?code=1234
     * @apiSuccess (200) {String} code 200:成功</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *     "data": "可以使用"
     * }
     */
    @PostMapping("/checkAccount")
    public JsonResult checkAccount(String account) {
        jsonResult.setData(userService.findByAccount(account) != null ? "已存在" : "可以使用");
        return jsonResult;
    }

    /**
     * @api {POST} /user/search 用户搜索
     * @apiName search
     * @apiGroup User
     * @apiVersion 2.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 用户搜索
     * @apiParam {String} key 搜索条件
     * @apiParam {String} value 查询值
     * @apiParam {int} page 页数(默认:1)
     * @apiParam {int} size 长度(默认:10)
     * @apiParamExample {json} 请求样例
     *                ?key=张三&page=1&size=10
     * @apiSuccess (200) {int} code 200:成功</br>
     *                              404:不存在此用户</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *     "data": "{'pages':1,'elements':1,'entity':[{'version':'0','id':'0a4179fc06cb49e3ac0db7bcc8cf0882','createdDate':'20190517111111','lastModifiedDate':'20190517111111','name':'管理员','status':'normal','password':'b356a1a11a067620275401a5a3de04300bf0c47267071e06','remark':'未填写','sex':'0','salt':'3a10624a300f4670','userType':'amdin','account':'admin'}]}"
     * }
     */
    @PostMapping("/search")
    public JsonResult search(String key,
                             String value,
                             @RequestParam(defaultValue = "0") int page,
                             @RequestParam(defaultValue = "10") int size) {
        if (StringUtils.isBlank(key)) {
            return JsonResult.paramError();
        }
        Pageable pageable = new Pageable(page, size);
        pageable.setSearchProperty(key);
        pageable.setSearchValue(value);
        Page<User> userPage = userService.findByKey(pageable);
        if (CollectionUtils.isEmpty(userPage.getContent())) {
            return JsonResult.notFound("不存在此用户");
        }
        jsonResult.setData(JsonUtils.getPage(userPage));
        return jsonResult;
    }

    /**
     * @api {POST} /user/add 新建用户
     * @apiGroup User
     * @apiVersion 2.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 新建用户
     * @apiParam {String} account 账号 必填
     * @apiParam {String} name 姓名 必填
     * @apiParam {String} code 编号 必填
     * @apiParam {int} sex 性别
     * @apiParam {String} departId 部门ID 必填
     * @apiParam {String} groupsId 组织ID 必填
     * @apiParam {String} remark 描述
     * @apiParam {String} password 密码 必填
     * @apiParam {String[]} roleIds 角色IDS 必填
     * @apiParam {String[]} permissionIds 权限IDS 必填
     * @apiParam {String[]} labelIds 标签IDS
     * @apiParamExample {json} 请求样例：
     *                /user/add?key=张三&page=1&size=10
     * @apiSuccess (200) {int} code 200:成功</br>
     *                                 201:用户名密码错误</br>
     *                                 601:用户必须有所属的机构/部门/组织</br>
     *                                 602:用户类型设置异常</br>
     *                                 603:部门/组织不存在</br>
     *                                 604:机构不存在</br>
     *                                 605:账号必须分配角色,权限</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *     "data": {"salt": "45a1d914886d4a92b6835a181b2a20d8","lastModifiedDate": "20190621102140","sex": "0","remark": "","version": "0","userRoles": [    {        "createdDate": "20190517111111",        "lastModifiedDate": "20190517111111",        "level": "0",        "name": "user",        "id": "b08a1e16dfe04d6c98e1599007c31490",        "version": "1"    },    {        "createdDate": "20190517111111",        "lastModifiedDate": "20190620141259",        "level": "1",        "name": "admin",        "id": "c7717f9578b64a819cbfcf75848fcc2a",        "version": "4"    }],"password": "B07EEB8D7F62FBD7","createdDate": "20190621102140","userRelations": [    {        "subject": {            "createdDate": "20190517111111",            "lastModifiedDate": "20190517111111",            "name": "总院",            "id": "bd6886bc88e54ef0a36472efd95c744c",            "version": "1",            "subjectType": "head"        },        "groups": {            "createdDate": "20190517111111",            "lastModifiedDate": "20190517111111",            "name": "总-组织",            "info1": "",            "remark": "",            "id": "ec0e291d5bfd4e98a33cd610c9b1d330",            "info5": "",            "version": "1",            "info4": "",            "info3": "",            "info2": ""        }    }],"name": "测试账号","id": "402881916b77bff1016b77d6e37a002e","userType": "admin","account": "a4","status": ""
     *     }
     * }
     */
    @PostMapping("/add")
    public JsonResult add(String account,
                          String name,
                          String code,
                          @RequestParam(defaultValue = "0") int sex,
                          @RequestParam(defaultValue = "0") int userType,
                          @CurrentSubject Subject subject,
                          String departId,
                          String groupsId,
                          String remark,
                          String password,
                          String[] roleIds,
                          String[] permissionIds,
                          String[] labelIds) {
        if (StringUtils.isBlank(account)
                || StringUtils.isBlank(name)
                || StringUtils.isBlank(password)) {
            return JsonResult.failure(603, "用户名/账号/密码不能为空");
        } 
        User user = new User();
        user.setCreatedDate(new Date());
        user.setLastModifiedDate(new Date());
        user.setAccount(account);
        user.setName(name);
        user.setCode(code);
        user.setSex(sex);
        user.setSalt(getSalt());
        user.setPassword(setPassword(password, user.getSalt()));
        List<Role> roles = roleService.findByRoleIds(roleIds);
        List<Permission> permissions = permissionService.findByIds(permissionIds);
        if (CollectionUtils.isEmpty(roles) || CollectionUtils.isEmpty(permissions)) {
            return JsonResult.failure(605, "账号必须分配角色,权限");
        }
        List<Label> labels = userService.findLabelByIds(labelIds);
        user.setRemark(remark);
        return userService.save(user, subject, departId, groupsId, roles, permissions, labels, jsonResult);
    }

    /**
     * @api {POST} /user/view 查看用户
     * @apiGroup User
     * @apiVersion 2.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 查看
     * @apiParam {String} id 用户ID
     * @apiParamExample {json} 请求样例
     *                ?id=0a4179fc06cb49e3ac0db7bcc8cf0882
     * @apiSuccess (200) {int} code 200:成功</br>
     *                              404:此用户不存在</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *     "data": {"salt": "45a1d914886d4a92b6835a181b2a20d8","lastModifiedDate": "20190621102140","sex": "0","remark": "","version": "0","userRoles": [    {        "level": 0,        "name": "user",        "id": "b08a1e16dfe04d6c98e1599007c31490"    },    {        "level": 1,        "name": "admin",        "id": "c7717f9578b64a819cbfcf75848fcc2a"    }],"password": "B07EEB8D7F62FBD7","createdDate": "20190621102140","userRelations": [    {        "subject": {            "name": "总院",            "id": "bd6886bc88e54ef0a36472efd95c744c"        },        "groups": {            "name": "总-组织",            "id": "ec0e291d5bfd4e98a33cd610c9b1d330"        }    }],"name": "测试账号","id": "402881916b77bff1016b77d6e37a002e","userType": "admin","account": "a4","status": ""
     *     }
     * }
     */
    @PostMapping("/view")
    public JsonResult view(String id) {
        User user = userService.findById(id);
        if (user == null) {
            return JsonResult.notFound("此用户不存在");
        }
        jsonResult.setData(userService.getUserInfoJson(user));
        return jsonResult;
    }

    /**
     * @api {POST} /user/addUserInfo 添加用户详情
     * @apiGroup User
     * @apiVersion 1.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 添加用户详情,必须在建立个人账号认账之后进行
     * @apiParam {String} userId 用户ID, 必填
     * @apiParam {String} name 真实姓名
     * @apiParam {String} nation 民族
     * @apiParam {int} age 年龄
     * @apiParam {String} political 政治面貌
     * @apiParam {String} nativePlace 籍贯
     * @apiParam {String} university 毕业院校
     * @apiParam {String} major 专业
     * @apiParam {String} education 学历
     * @apiParam {String} birthDay 出生日期
     * @apiParam {String} address 地址
     * @apiParam {long} tel 电话
     * @apiParam {String} email 邮箱
     * @apiParam {String} wx 微信
     * @apiParam {String} qq QQ
     * @apiParam {String} iCard 身份证号
     * @apiParam {String} selfEvaluation 自我评价
     * @apiParamExample {json} 请求样例：
     *                /user/addUserInfo?userId=0a4179fc06cb49e3ac0db7bcc8cf0882&name=张三&nation=汉族&age=18&political=党员&nativePlace=北京北京&university=北京大学&major=计算机科学与技术&education=博士&birthDay=20190801&address=北京海淀&tel=13839724066&email=11@qq.com&wx=test&qq=13839724&iCard=411526198701191036&selfEvaluation=幽默
     * @apiSuccess (200) {int} code 200:成功</br>
     *                                 404:用户不存在</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *     "data": {"qq": "13839724","wx": "test","birthDay": "20190801","address": "北京海淀","education": "博士","nation": "汉族","university": "北京大学","political": "党员","selfEvaluation": "幽默","major": "计算机科学与技术","name": "张三","nativePlace": "北京北京","tel": "13839724066","id": "402881916c4b283e016c4b34632e0001","iCard": "","email": "11@qq.com","age": "18"
     *     }
     * }
     */
    @PostMapping("/addUserInfo")
    public JsonResult addUserInfo(String userId,
                                  String name,
                                  String nation,
                                  @RequestParam(defaultValue = "0") int age,
                                  String political,
                                  String nativePlace,
                                  String university,
                                  String major,
                                  String education,
                                  String birthDay,
                                  String address,
                                  @RequestParam(defaultValue = "0L") long tel,
                                  String email,
                                  String wx,
                                  String qq,
                                  String iCard,
                                  String selfEvaluation) {
        if (isBlank(userId)) {
            return JsonResult.blank();
        }
        User user = userService.findById(userId);
        if (user == null) {
            return JsonResult.notFound("用户不存在");
        }
        UserInfo userInfo = userService.saveUserInfo(user, name, nation, age,
                political, nativePlace, university, major,
                education, birthDay, address, tel,
                email, wx, qq, iCard, selfEvaluation);
        jsonResult.setData(JsonUtils.getJson(userInfo));
        return jsonResult;
    }

    /**
     * @api {POST} /user/modifyUserInfo 修改用户详情
     * @apiGroup User
     * @apiVersion 1.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 修改用户详情
     * @apiParam {String} id 用户详情ID, 必填
     * @apiParam {String} name 真实姓名
     * @apiParam {String} nation 民族
     * @apiParam {int} age 年龄
     * @apiParam {String} political 政治面貌
     * @apiParam {String} nativePlace 籍贯
     * @apiParam {String} university 毕业院校
     * @apiParam {String} major 专业
     * @apiParam {String} education 学历
     * @apiParam {String} birthDay 出生日期
     * @apiParam {String} address 地址
     * @apiParam {long} tel 电话
     * @apiParam {String} email 邮箱
     * @apiParam {String} wx 微信
     * @apiParam {String} qq QQ
     * @apiParam {String} iCard 身份证号
     * @apiParam {String} selfEvaluation 自我评价
     * @apiParamExample {json} 请求样例：
     *                /user/modifyUserInfo?id=402881916c4b283e016c4b34632e0001&name=张三-改&nation=汉族-改&age=18&political=党员-改&nativePlace=北京北京-改&university=北京大学-改&major=计算机科学与技术-改&education=博士-改&birthDay=20190801&address=北京海淀-改&tel=13839724066&email=11@qq.com-改&wx=test-改&qq=13839724-改&iCard=411526198701191036&selfEvaluation=幽默-改
     * @apiSuccess (200) {int} code 200:成功</br>
     *                                 404:用户详情不存在</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *     "data": {"qq": "13839724-改","wx": "test-改","birthDay": "20190801","address": "北京海淀-改","education": "博士-改","nation": "汉族-改","university": "北京大学-改","political": "党员-改","selfEvaluation": "幽默-改","major": "计算机科学与技术-改","name": "张三-改","nativePlace": "北京北京-改","tel": "13839724066","id": "402881916c4b283e016c4b34632e0001","iCard": "","email": "11@qq.com-改","age": "18"
     *     }
     * }
     */
    @PostMapping("/modifyUserInfo")
    public JsonResult modifyUserInfo(String id,
                                     String name,
                                     String nation,
                                     @RequestParam(defaultValue = "0") int age,
                                     String political,
                                     String nativePlace,
                                     String university,
                                     String major,
                                     String education,
                                     String birthDay,
                                     String address,
                                     @RequestParam(defaultValue = "0L") long tel,
                                     String email,
                                     String wx,
                                     String qq,
                                     String iCard,
                                     String selfEvaluation) {
        if (isBlank(id)) {
            return JsonResult.blank();
        }
        UserInfo userInfo = userService.findUserInfoById(id);
        if (userInfo == null) {
            return JsonResult.notFound("用户详情不存在");
        }
        userInfo = userService.modifyUserInfo(userInfo, name, nation, age,
                political, nativePlace, university, major,
                education, birthDay, address, tel,
                email, wx, qq, iCard, selfEvaluation);
        jsonResult.setData(JsonUtils.getJson(userInfo));
        return jsonResult;
    }

    /**
     * @api {post} /user/groupUser 组织用户分页
     * @apiGroup User
     * @apiVersion 2.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 组织用户分页
     * @apiParam {String} groupId 组织ID
     * @apiParam {int} page 页数
     * @apiParam {int} size 长度
     * @apiParamExample {json} 请求样例
     *                ?page
     * @apiSuccess (200) {int} code 200:成功</br>
     *                              600:参数异常</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *     "data": "{'total':3,'content':[{'id':'0a4179fc06cb49e3ac0db7bcc8cf0882','account':'admin','sex':'男','userType':'管理员','status':'正常','createdTime':'20190517111111','relation':['总院-分-部门-无','分院-总-部门-无'],'remark':'未填写'},{'id':'5cf0d3c3b0da4cbaad179e0d6d230d0c','account':'test','sex':'男','userType':'普通用户','status':'正常','createdTime':'20190517111111','relation':['总院-总-部门-无'],'remark':'未填写'},{'id':'cd22e3407ace4d86bac92f92b9e9dd3e','account':'user','sex':'男','userType':'普通用户','status':'正常','createdTime':'20190517111111','relation':[],'remark':'未填写'}]}"
     * }
     */
    @PostMapping("/groupUser")
    public JsonResult groupUser(String groupId,
                           @RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "10") int size) {
        //todo 需要整改小组用户的分页查询
        List<User> userPage = userService.findByGroupIdForPage(groupId);
        JSONArray pageArray = new JSONArray();
        for (User user : userPage) {
            pageArray.add(convertUser(user));
        }
        jsonResult.setData(pageArray);
        return jsonResult;
    }

    /**
     * @api {post} /user/departUser 部门用户分页
     * @apiGroup User
     * @apiVersion 2.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 部门用户分页
     * @apiParam {String} departId 部门ID
     * @apiParam {int} page 页数
     * @apiParam {int} size 长度
     * @apiParamExample {json} 请求样例
     *                ?page
     * @apiSuccess (200) {int} code 200:成功</br>
     *                              600:参数异常</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *     "data": "{'total':3,'content':[{'id':'0a4179fc06cb49e3ac0db7bcc8cf0882','account':'admin','sex':'男','userType':'管理员','status':'正常','createdTime':'20190517111111','relation':['总院-分-部门-无','分院-总-部门-无'],'remark':'未填写'},{'id':'5cf0d3c3b0da4cbaad179e0d6d230d0c','account':'test','sex':'男','userType':'普通用户','status':'正常','createdTime':'20190517111111','relation':['总院-总-部门-无'],'remark':'未填写'},{'id':'cd22e3407ace4d86bac92f92b9e9dd3e','account':'user','sex':'男','userType':'普通用户','status':'正常','createdTime':'20190517111111','relation':[],'remark':'未填写'}]}"
     * }
     */
    @PostMapping("/departUser")
    public JsonResult departUser(String departId,
                           @RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "10") int size) {
        //todo 需要整改部门用户的分页查询
        org.springframework.data.domain.Pageable pageable = PageRequest.of(page, size);
        List<User> userPage = userService.findByDepartIdForPage(departId);
        if (userPage == null) {
            return JsonResult.success();
        }
        JSONObject pageJson = new JSONObject();
        JSONArray pageArray = new JSONArray();
        pageJson.put("content", pageArray);
        jsonResult.setData(pageJson);
        return jsonResult;
    }


    /**
     * @api {POST} /user/addTrain 添加培训经历
     * @apiGroup User
     * @apiVersion 2.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 添加个人的培训经历
     * @apiParam {String} name 名称,必填
     * @apiParam {String} startTime 开始时间,必填
     * @apiParam {String} endTime 结束时间,必填
     * @apiParam {String} honor 所获荣誉
     * @apiParam {String} remark 描述
     * @apiParam {String} userId 用户ID,必填
     * @apiParamExample {json} 请求样例
     *                /user/addTrain?name=教育培训&startTime=20190731000000&endTime=20190731000000&honor=毕业&remark=成就一生的学习&userId=0a4179fc06cb49e3ac0db7bcc8cf0882
     * @apiSuccess (200) {int} code 200:成功</br>
     *                              404:用户不存在</br>
     *                              601:必填字段不能为空</br>
     *                              602:时间格式不正确,必须为:yyyyMMddHHmmss
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *     "data": {"honor": "毕业","name": "教育培训","remark": "成就一生的学习","startTime": "20190731000000","id": "402881916c45ec9a016c45ed04b40000","endTime": "20190731000000"
     *     }
     * }
     */
    @PostMapping("/addTrain")
    public JsonResult addTrain(String name,
                               String startTime,
                               String endTime,
                               String honor,
                               String remark,
                               String userId) {
        if (isBlank(name, startTime, endTime, userId)) {
            return JsonResult.blank();
        }
        if (isTimeFormat(startTime, endTime)) {
            return JsonResult.errorTime();
        }
        User user = userService.findById(userId);
        if (user == null) {
            return JsonResult.notFound("用户不存在");
        }
        UserTrain ut = userService.saveUserTrain(name, startTime, endTime, honor, remark, user);
        jsonResult.setData(JsonUtils.getJson(ut));
        return jsonResult;
    }

    /**
     * @api {POST} /user/viewTrain 查看培训经历
     * @apiGroup User
     * @apiVersion 2.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 查看个人的培训经历
     * @apiParam {String} id 培训经历ID
     * @apiParamExample {json} 请求样例
     *                /user/viewTrain?id=402881916c45f80a016c45f970160000
     * @apiSuccess (200) {int} code 200:成功</br>
     *                              404:培训经历不存在</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *     "data": {"honor": "毕业","name": "教育培训","remark": "成就一生的学习","startTime": "20190731000000","id": "402881916c45ec9a016c45ed04b40000","endTime": "20190731000000"
     *     }
     * }
     */
    @PostMapping("/viewTrain")
    public JsonResult viewTrain(String id) {
        UserTrain train = userService.findUserTrainById(id);
        if (train == null) {
            return JsonResult.notFound("培训经历不存在");
        }
        jsonResult.setData(JsonUtils.getJson(train));
        return jsonResult;
    }

    /**
     * @api {POST} /user/delTrain 删除培训经历
     * @apiGroup User
     * @apiVersion 2.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 删除个人培训经历
     * @apiParam {String} id 培训经历ID
     * @apiParamExample {json} 请求样例
     *                /user/delTrain?id=402881916c45f80a016c45f970160000
     * @apiSuccess (200) {int} code 200:成功</br>
     *                              404:培训经历不存在</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *     "data": []
     * }
     */
    @PostMapping("/delTrain")
    public JsonResult delTrain(String id) {
        UserTrain train = userService.findUserTrainById(id);
        if (train == null) {
            return JsonResult.notFound("培训经历不存在");
        }
        userService.deleteUserTrain(train);
        return JsonResult.success();
    }

    /**
     * @api {POST} /user/addWork 添加工作履历
     * @apiGroup User
     * @apiVersion 2.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 添加个人工作履历
     * @apiParam {String} companyName 单位名称,必填
     * @apiParam {String} industryId 所属行业id,必填
     * @apiParam {String} positionName 职位名称,必填
     * @apiParam {String} startTime 开始时间,必填
     * @apiParam {String} endTime 结束时间,必填
     * @apiParam {String} userId 所属用户,必填
     * @apiParamExample {json} 请求样例
     *                /user/addWork?companyName=中国文学出版社&industryId=10f36b0bfc1a470daea50ee15c26af26&positionName=经理&startTime=20190731000000&endTime=20190731000000&userId=0a4179fc06cb49e3ac0db7bcc8cf0882
     * @apiSuccess (200) {int} code 200:成功</br>
     *                              404:行业不存在</br>
     *                              404:用户不存在</br>
     *                              601:必填字段不能为空</br>
     *                              602:时间格式不正确,必须为:yyyyMMddHHmmss
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *     "data": {"positionName": "经理","companyName": "中国文学出版社","startTime": "20190731000000","id": "402881916c46c2a1016c46c70c9a0000","endTime": "20190731000000"
     *     }
     * }
     */
    @PostMapping("/addWork")
    public JsonResult addWork(String companyName,
                              String industryId,
                              String positionName,
                              String startTime,
                              String endTime,
                              String userId) {
        if (isBlank(companyName, industryId, positionName, startTime, endTime, userId)) {
            return JsonResult.blank();
        } 
        if (isTimeFormat(startTime, endTime)) {
            return JsonResult.errorTime();
        }
        Industry industry = userService.findIndustryById(industryId);
        if (industry == null) {
            return JsonResult.notFound("行业不存在");
        }
        User user = userService.findById(userId);
        if (user == null) {
            return JsonResult.notFound("用户不存在");
        }
        UserWork work = userService.saveUserWork(companyName, industry, positionName, startTime, endTime, user);
        jsonResult.setData(JsonUtils.getJson(work));
        return jsonResult;
    }

    /**
     * @api {POST} /user/viewWork 查看工作履历
     * @apiGroup User
     * @apiVersion 2.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 查看个人工作履历
     * @apiParam {String} id 工作履历ID,必填
     * @apiParamExample {json} 请求样例
     *                /user/viewWork?id=402881916c46c2a1016c46c70c9a0000
     * @apiSuccess (200) {int} code 200:成功</br>
     *                              404:用户工作履历不存在</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *     "data": {"positionName": "经理","companyName": "中国文学出版社","startTime": "20190731000000","id": "402881916c46c2a1016c46c70c9a0000","endTime": "20190731000000"
     *     }
     * }
     */
    @PostMapping("/viewWork")
    public JsonResult viewWork(String id) {
        UserWork work = userService.findUserWorkById(id);
        if (work == null) {
            return JsonResult.notFound("用户工作履历不存在");
        }
        jsonResult.setData(JsonUtils.getJson(work));
        return jsonResult;
    }

    /**
     * @api {POST} /user/delWork 删除工作履历
     * @apiGroup User
     * @apiVersion 2.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 删除个人工作履历
     * @apiParam {String} id 工作履历ID,必填
     * @apiParamExample {json} 请求样例
     *                /user/delWork?id=402881916c45f80a016c45f970160000
     * @apiSuccess (200) {int} code 200:成功</br>
     *                              404:用户工作履历不存在</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *     "data": []
     * }
     */
    @PostMapping("/delWork")
    public JsonResult delWork(String id) {
        UserWork work = userService.findUserWorkById(id);
        if (work == null) {
            return JsonResult.notFound("用户工作履历不存在");
        }
        userService.deleteUserWork(work);
        return JsonResult.success();
    }

    /**
     * @api {POST} /user/addLabel 添加标签
     * @apiGroup User
     * @apiVersion 2.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 添加标签
     * @apiParam {String} name 名称,必填
     * @apiParam {String} code 编号,必填
     * @apiParam {String} remark 描述
     * @apiParamExample {json} 请求样例
     *                /user/addLabel?name=高知群体&code=123456&remark=告知群体
     * @apiSuccess (200) {int} code 200:成功</br>
     *                              400:标签编号已存在</br>
     *                              404:用户工作履历不存在</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *     "data": {"code": "123456","name": "高知群体","remark": "告知群体","id": "402881916c471adc016c4721d2250000"
     *     }
     * }
     */
    @PostMapping("/addLabel")
    public JsonResult addLabel(String name,
                               String code,
                               String remark) {
        if (isBlank(name, code)) {
            return JsonResult.blank();
        }
        if (userService.existsLabelCode(code)) {
            return JsonResult.failure(400, "标签编号已存在");
        }
        Label label = userService.createLabel(name, code, remark);
        jsonResult.setData(JsonUtils.getJson(label));
        return jsonResult;
    }

    /**
     * @api {GET} /user/labelList 标签列表
     * @apiGroup User
     * @apiVersion 2.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 添加标签
     * @apiParamExample {json} 请求样例
     *                /user/labelList
     * @apiSuccess (200) {int} code 200:成功</br>
     *                              404:用户工作履历不存在</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 400,
     *     "message": "已存在",
     *     "data": [{    "code": "123456",    "name": "高知群体",    "remark": "告知群体",    "id": "402881916c471adc016c4721d2250000"},{    "code": "12345",    "name": "老群团",    "remark": "老群团",    "id": "402881916c471adc016c472906340003"}
     *     ]
     * }
     */
    @GetMapping("/labelList")
    public JsonResult labelList() {
        List<Label> list = userService.findAllLabels();
        JSONArray arr = new JSONArray();
        for (Label l : list) {
            JSONObject json = JsonUtils.getJson(l);
            arr.add(json);
        }
        jsonResult.setData(arr);
        return jsonResult;
    }

    /**
     * @api {POST} /user/delLabel 删除标签
     * @apiGroup User
     * @apiVersion 2.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 删除标签
     * @apiParam {String} id 标签ID,必填
     * @apiParamExample {json} 请求样例
     *                /user/delLabel?id=402881916c471adc016c4721d2250000
     * @apiSuccess (200) {int} code 200:成功</br>
     *                              404:标签不存在</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *     "data": []
     * }
     */
    @PostMapping("/delLabel")
    public JsonResult delLabel(String id) {
        Label label = userService.findLabelById(id);
        if (label == null) {
            return JsonResult.notFound("标签不存在");
        }
        userService.deleteLabel(label);
        return JsonResult.success();
    }

    /**
     * @api {POST} /user/delUserLabel 删除用户标签
     * @apiGroup User
     * @apiVersion 2.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 删除用户标签
     * @apiParam {String} labelId 标签ID,必填
     * @apiParam {String} userId 用户ID,必填
     * @apiParamExample {json} 请求样例
     *                /user/delUserLabel?labelId=402881916c471adc016c472906340013&userId=402881916c476c6a016c47716397000a
     * @apiSuccess (200) {int} code 200:成功</br>
     *                              404:用户标签不存在</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *     "data": []
     * }
     */
    @PostMapping("/delUserLabel")
    public JsonResult delUserLabel(String labelId,
                                   String userId) {
        UserLabel userLabel = userService.findUserLabelByUserIdAndLabelId(userId, labelId);
        if (userLabel == null) {
            return JsonResult.notFound("用户标签不存在");
        }
        userService.delUserLabel(userLabel);
        return JsonResult.success();
    }

    /**
     * @api {POST} /user/sign 用户签到
     * @apiGroup User
     * @apiVersion 2.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 查看
     * @apiParam {String} userId 用户ID
     * @apiParam {int} type 签到类型
     * @apiParam {String} activityId 活动ID
     * @apiParam {String} singTime 签到时间
     * @apiParam {String} axisx 坐标x
     * @apiParam {String} axisy 坐标y
     * @apiParamExample {json} 请求样例
     *                /user/sign?activityId=402881916b2a3187016b2a3247350002&userId=0a4179fc06cb49e3ac0db7bcc8cf0882&singTime=now&axisx=123&axisy=456
     * @apiSuccess (200) {int} code 200:成功</br>
     *                              601:用户不存在</br>
     *                              602:活动不存在</br>
     *                              603:签到类型不能为空</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *     "data": "{"version":"0","id":"2c92eb816b32f12a016b33962eb90012","createdDate":"20190608041648","lastModifiedDate":"20190608041648","userId":"0a4179fc06cb49e3ac0db7bcc8cf0882","axisx":"123","axisy":"456","info1":"","info3":"","info4":"","info5":"","info2":"","signTime":"now","signType":"normal"}"
     * }
     */
    @PostMapping("/sign")
    public JsonResult sign(String userId,
                           @RequestParam(defaultValue = "0") int type,
                           String activityId,
                           String singTime,
                           String axisx,
                           String axisy) {
        User user = userService.findById(userId);
        if (user == null) {
            return JsonResult.failure(601, "用户不存在");
        }
        Activity activity = activityService.find(activityId);
        if (activity == null) {
            return JsonResult.failure(602, "活动不存在");
        }
        Sign s = new Sign();
        s.setCreatedDate(new Date());
        s.setLastModifiedDate(new Date());
        s.setSignTime(singTime);
        s.setAxisx(axisx);
        s.setAxisy(axisy);
        s.setUserId(userId);
        s.setActivity(activity);
        for (Sign.SIGN_TYPE st : Sign.SIGN_TYPE.values()) {
            if (st.ordinal() == type) {
                s.setSignType(st);
                break;
            } 
        }
        if (s.getSignType() == null) {
            return JsonResult.failure(603, "签到类型不能为空");
        }
        s = activityService.saveUserSign(s);
        jsonResult.setData(JsonUtils.getJson(s));
        return jsonResult;
    }

    private JSONObject convertUser(User user) {
        JSONObject json = JsonUtils.getJson(user);
        if (user.getStatus() != null) {
            switch (user.getStatus().ordinal()) {
                case  0:
                    json.put("status", "禁用");
                    break;
                case  1:
                    json.put("status", "锁定");
                    break;
                case  2:
                    json.put("status", "正常");
                    break;
            }
        }
        if (CollectionUtils.isNotEmpty(user.getUserRelations())) {
            JSONArray arr = new JSONArray();
            for (UserRelation ur : user.getUserRelations()) {
                JSONObject j = new JSONObject();
                if (ur.getSubject() == null) {
                    continue;
                }
                j.put("subjectId", ur.getSubject().getId());
                j.put("subjectName", ur.getSubject().getName());
                if (ur.getDepart() != null) {
                    j.put("departId", ur.getDepart().getId());
                    j.put("departName", ur.getDepart().getName());
                }
                if (ur.getGroup() != null) {

                    j.put("groupsId", ur.getGroup().getId());
                    j.put("groupsName", ur.getGroup().getName());
                }
                arr.add(j);
            }
            json.put("userRelation", arr);
        }
        return json;
    }
}
