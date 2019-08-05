package com.iyundao.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.iyundao.base.BaseController;
import com.iyundao.base.Page;
import com.iyundao.base.Pageable;
import com.iyundao.base.annotation.CurrentSubject;
import com.iyundao.base.utils.JsonResult;
import com.iyundao.entity.*;
import com.iyundao.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: PositionController
 * @project: unitedfront
 * @author: 念
 * @Date: 2019/8/1 16:13
 * @Description: 控制层 - 职位
 * @Version: V1.0
 */
@RestController
@RequestMapping("/position")
public class PositionController extends BaseController {

    private final static long serialVersionUID = 1932949821739847198L;

    @Autowired
    private PositionService service;

    @Autowired
    private IndustryService industryService;

    @Autowired
    private DepartService departService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private UserService userService;

    /**
     * @api {POST} /position/add 添加岗位
     * @apiGroup Position
     * @apiVersion 2.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 查看
     * @apiParam {String} name 名称, 必填
     * @apiParam {String} remark 描述
     * @apiParam {String} industryId 所属行业ID, 必填
     * @apiParam {String} departId 部门ID,与小组ID二选一
     * @apiParam {String} groupId 小组ID,与部门ID二选一
     * @apiParam {String[]} userIds 用户ID集合
     * @apiParamExample {json} 请求样例
     *                /position/add?name=印刷工人&remark=负责印刷&industryId=10f36b0bfc1a470daea50ee15c26af26
     * @apiSuccess (200) {int} code 200:成功</br>
     *                              404:行业不存在</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *     "data": {"name": "印刷工人","remark": "负责印刷","id": "402881916c5071ac016c51168ed40004"
     *     }
     * }
     */
    @PostMapping("/add")
    public JsonResult add(String name,
                          String remark,
                          String industryId,
                          @CurrentSubject Subject subject,
                          String departId,
                          String groupId) {
        if (isBlank(name, industryId)) {
            return JsonResult.blank();
        }
        Industry industry = industryService.find(industryId);
        if (industry == null) {
            return JsonResult.notFound("行业不存在");
        }
        Depart depart = departService.findById(departId);
        Group group = groupService.findById(groupId);
        Position position = service.create(name, remark, industry, subject, depart, group);
        jsonResult.setData(convert(position));
        return jsonResult;
    }

    /**
     * @api {POST} /position/distribute 分配岗位
     * @apiGroup Position
     * @apiVersion 2.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 分配岗位
     * @apiParam {String} id 岗位ID, 必填
     * @apiParam {String[]} userIds 用户ID集合, 必填
     * @apiParamExample {json} 请求样例
     *                /position/distribute?id=0a4179fc06cb49e3ac0db7bcc8cf0882
     * @apiSuccess (200) {int} code 200:成功</br>
     *                              404:岗位不存在</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *     "data": [{    "id": "402881916c563cd8016c5640618c0001",    "position": {        "name": "印刷工人",        "remark": "负责印刷",        "id": "402881916c561b01016c56234bf00000"    },    "user": {        "password": "b356a1a11a067620275401a5a3de04300bf0c47267071e06",        "code": "000",        "salt": "3a10624a300f4670",        "sex": "0",        "name": "管理员",        "remark": "未填写",        "id": "0a4179fc06cb49e3ac0db7bcc8cf0882",        "account": "admin",        "status": "normal"    }}
     *     ]
     * }
     */
    @PostMapping("/distribute")
    public JsonResult distribute(String id,
                                 String[] userIds) {
        if (isBlank(id)) {
            return JsonResult.blank();
        }
        Position position = service.find(id);
        if (position == null) {
            return JsonResult.notFound("岗位不存在");
        }
        List<User> users = userService.findbyIds(userIds);
        List<PositionRelation> list = service.addRelation(position, users);
        JSONArray arr = new JSONArray();
        for (PositionRelation pr : list) {
            JSONObject json = getJson(pr);
            json.put("position", getJson(pr.getPosition()));
            json.put("user", getJson(pr.getUser()));
            arr.add(json);
        }
        jsonResult.setData(arr);
        return jsonResult;
    }

    /**
     * @api {GET} /position/list 岗位分页
     * @apiGroup Position
     * @apiVersion 2.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 岗位分页
     * @apiParam {String} key 查询字段
     * @apiParam {String} value 查询值
     * @apiParam {int} page 页数,默认0
     * @apiParam {int} size 长度,默认10
     * @apiParamExample {json} 请求样例
     *                /position/list?key=name&value=1234
     * @apiSuccess (200) {int} code 200:成功</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *     "data": {"total": 1,"totalPage": 1,"page": 0,"content": [    {        "name": "印刷工人",        "remark": "负责印刷",        "id": "402881916c561b01016c56234bf00000"    }]
     *     }
     * }
     */
    @GetMapping("/list")
    public JsonResult list(String key,
                           String value,
                           @RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = new Pageable();
        if (!isBlank(key, value)) {
            pageable.setSearchKey(key);
            pageable.setSearchValue(value);
        }
        pageable.setPageNumber(page);
        pageable.setPageSize(size);
        Page<Position> positionPage = service.findPage(pageable);
        jsonResult.setData(getPage(positionPage));
        return jsonResult;
    }

    /**
     * @api {POST} /position/getDepartPosition 获取部门岗位
     * @apiGroup Position
     * @apiVersion 2.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 查看
     * @apiParam {String} departId 部门ID
     * @apiParamExample {json} 请求样例
     *                /position/getDepartPosition?id=0a4179fc06cb49e3ac0db7bcc8cf0882
     * @apiSuccess (200) {int} code 200:成功</br>
     *                              404:岗位不存在</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *     "data": {"name": "印刷工人","remark": "负责印刷","id": "402881916c561b01016c56234bf00000"
     *     }
     * }
     */
    @PostMapping("/getDepartPosition")
    public JsonResult getDepartPosition(String departId) {
        List<Position> list = service.findByDepartId(departId);
        JSONArray arr = new JSONArray();
        for (Position position : list) {
            arr.add(getJson(position));
        }
        jsonResult.setData(arr);
        return jsonResult;
    }

    /**
     * @api {POST} /position/getGroupPosition 获取小组岗位
     * @apiGroup Position
     * @apiVersion 2.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 获取小组岗位
     * @apiParam {String} groupId 小组ID
     * @apiParamExample {json} 请求样例
     *                /position/getGroupPosition?id=0a4179fc06cb49e3ac0db7bcc8cf0882
     * @apiSuccess (200) {int} code 200:成功</br>
     *                              404:岗位不存在</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *     "data": {"name": "印刷工人","remark": "负责印刷","id": "402881916c561b01016c56234bf00000"
     *     }
     * }
     */
    @PostMapping("/getGroupPosition")
    public JsonResult getGroupPosition(String groupId) {
        List<Position> list = service.findByGroupId(groupId);
        JSONArray arr = new JSONArray();
        for (Position position : list) {
            arr.add(getJson(position));
        }
        jsonResult.setData(arr);
        return jsonResult;
    }

    /**
     * @api {POST} /position/view 查看岗位
     * @apiGroup Position
     * @apiVersion 2.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 查看
     * @apiParam {String} id 查询字段
     * @apiParamExample {json} 请求样例
     *                /position/view?id=0a4179fc06cb49e3ac0db7bcc8cf0882
     * @apiSuccess (200) {int} code 200:成功</br>
     *                              404:岗位不存在</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *     "data": {"name": "印刷工人","remark": "负责印刷","id": "402881916c561b01016c56234bf00000"
     *     }
     * }
     */
    @PostMapping("/view")
    public JsonResult view(String id) {
        if (isBlank(id)) {
            return JsonResult.blank();
        }
        Position position = service.find(id);
        if (position == null) {
            return JsonResult.notFound("岗位不存在");
        }
        jsonResult.setData(convert(position));
        return jsonResult;
    }

    /**
     * @api {POST} /position/del 删除岗位
     * @apiGroup Position
     * @apiVersion 2.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 删除岗位
     * @apiParam {String} id 查询字段
     * @apiParamExample {json} 请求样例
     *                /position/del?id=0a4179fc06cb49e3ac0db7bcc8cf0882
     * @apiSuccess (200) {int} code 200:成功</br>
     *                              404:岗位不存在</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *     "data": []
     * }
     */
    @PostMapping("/del")
    public JsonResult del(String id) {
        if (isBlank(id)) {
            return JsonResult.blank();
        }
        Position position = service.find(id);
        if (position == null) {
            return JsonResult.notFound("岗位不存在");
        }
        service.delete(position);
        return jsonResult;
    }

    private JSONObject convert(Position p) {
        JSONObject json = getJson(p);
        if (p.getDepart() != null) {
            json.put("depart", getJson(p.getDepart()));
        }
        if (p.getGroup() != null) {
            json.put("group", getJson(p.getGroup()));
        }
        return json;
    }
}
