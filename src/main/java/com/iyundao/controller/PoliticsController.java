package com.iyundao.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.iyundao.base.BaseController;


import com.iyundao.base.Page;
import com.iyundao.base.Pageable;
import com.iyundao.base.utils.JsonResult;
import com.iyundao.base.utils.JsonUtils;
import com.iyundao.entity.*;
import com.iyundao.service.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.nio.cs.ext.SJIS;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: PoliticsController
 * @project:
 * @author: 13620
 * @Date: 2019/7/31
 * @Description: 参政议政
 * @Version: V1.0
 */
@RestController
@RequestMapping("/politics")
public class PoliticsController extends BaseController{

    @Autowired
    private PoliticsService politicsService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private DepartService departService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private UserService userService;

    /**
    * @api {POST} /politics/listPage  分页列表
    * @apiGroup Politics
    * @apiVersion 1.0.0
    * @apiHeader {String} IYunDao-AssessToken token验证
    * @apiDescription 分页列表
    * @apiParam {int} page 当前要展示的页数  默认1
    * @apiParam {int} size 每页展示的条数  默认10
    * @apiParamExample {json} 请求样例:
    *                /politics/listPage?page=1&size=2
    * @apiSuccess (200) {String} code 200:成功</br>
    * @apiSuccess (200) {String} message 信息
    * @apiSuccess (200) {String} data 返回用户信息
    * @apiSuccessExample {json} 返回样例:
    * {
    *     "code": 200,
    *     "message": "成功",
    *      "data": [{"startTime": "2018-12-12 12:12:12","id": "4028d8816c4aa85a016c4aa8913d0000","state": "unreviewed","endTime": "2019-12-1212:12:12","synopsis": "正文","title": "测试标题"},{"startTime": "2018-12-12 12:12:12","id": "4028d8816c4af660016c4b03b81d0000","state": "unreviewed","endTime": " 1212:12:12","synopsis": "正文","title": "测试标题"}]
    * }
    */
    @PostMapping("/listPage")
    public JsonResult listPage(@RequestParam(defaultValue = "1") int page,
                               @RequestParam(defaultValue = "10") int size){
        List<Politics> politics = politicsService.findAll();
        List<Politics> currentPageList = new ArrayList<>();
        if (politics != null && politics.size() > 0) {
            int currIdx = (page > 1 ? (page - 1) * size : 0);
            for (int i = 0; i < size && i < politics.size() - currIdx; i++) {
                Politics data = politics.get(currIdx + i);
                currentPageList.add(data);
            }
        }
        jsonResult.setData(converPolitics(currentPageList));
        return jsonResult;
    }



    /**
    * @api {POST} /politics/add  添加
    * @apiGroup Politics
    * @apiVersion 1.0.0
    * @apiHeader {String} IYunDao-AssessToken token验证
    * @apiDescription 添加
    * @apiParam {String} title 标题 必填
    * @apiParam {String} content 正文
    * @apiParam {String} startTime 开始时间
    * @apiParam {String} endTime 结束时间
    * @apiParam {int} state 审核状态 0-未审核 1-审核不通过 2-审核通过
    * @apiParam {String[]} subjectIds 机构id
    * @apiParam {String[]} departIds 部门id
    * @apiParam {String[]} groupsIds 组织id
    * @apiParam {String[]} userIds 用户id
    * @apiParamExample {json} 请求样例:
                    /politics/add?title=测试标题&synopsis=正文&startTime=2018-12-12 12:12:12&endTime=2019-12-12 12:12:12&state=0&subjectIds=402881916b9d3031016b9d626593000c&departIds&groupsIds&userIds
    * @apiSuccess (200) {String} code 200:成功</br>
    * @apiSuccess (200) {String} message 信息
    * @apiSuccess (200) {String} data 返回用户信息
    * @apiSuccessExample {json} 返回样例:
    * {
    *     "code": 200,
    *     "message": "成功",
    *      "data": {"startTime": "2018-12-12 12:12:12","id": "4028d8816c4af660016c4b03b81d0000","state": "unreviewed","endTime": "2019-12-12 12:12:12","synopsis": "正文","title": "测试标题"}
    * }
    */
    @PostMapping("/add")
    public JsonResult add(String title,
                          String content,
                          String startTime,
                          String endTime,
                          int state,
                          String[] subjectIds,
                          String[] departIds,
                          String[] groupsIds,
                          String[] userIds){
        if (StringUtils.isEmpty(title)){
            return JsonResult.paramError("标题不得为空");
        }
       Politics politics = politicsService.add(title,content,startTime,endTime,state);
            List<Subject> subjects = subjectService.findbyIds(subjectIds);
            List<Depart> departs = departService.findbyIds(departIds);
            List<Group> groups = groupService.findByIds(groupsIds);
            List<User> users = userService.findbyIds(userIds);
            politicsService.addDeptionAndPeople(politics,subjects,departs,groups,users);

        jsonResult.setData(JsonUtils.getJson(politics));
        return jsonResult;
    }


    /**
    * @api {POST}  /politics/view 查看参政议政详情
    * @apiGroup Politics
    * @apiVersion 1.0.0
    * @apiHeader {String} IYunDao-AssessToken token验证
    * @apiDescription 功能说明
    * @apiParam {String} politicsId  参政议政id
    * @apiParamExample {json} 请求样例:
    *                /politics/view?politicsId=4028d8816c4aa85a016c4aa8913d0000
    * @apiSuccess (200) {String} code 200:成功</br>
    * @apiSuccess (200) {String} message 信息
    * @apiSuccess (200) {String} data 返回用户信息
    * @apiSuccessExample {json} 返回样例:
    * {
    *     "code": 200,
    *     "message": "成功",
    *      "data": {"all": [{"issueData": {"id": "4028d8816c4bb58d016c4bdd15a30003","sort": "1","title": "11","content": "11"},"discussData": [{"id": "4028d8816c4bb58d016c4c018bea0005","content": "11"},{"id": "4028d8816c4bb58d016c4c018bea005","content": "33"}],"resolutionData": {"id": "4028d8816c4bb58d016c4c179b530007","content": "33"}},{"issueData": {"id": "4028d8816c4bb58d016c4bdd15a70004","sort": "2","title": "22","content": "22"},"discussData": [{"id": "4028d8816c4bb58d016c4c018bed0006","content": "22"}],"resolutionData": {"id": "4028d8816c4bb58d016c4c179b540008","content": "44"}}],"politics": {"startTime": "2018-12-12 12:12:12","id": "4028d8816c4aa85a016c4aa8913d0000","state": "unreviewed","endTime": "2019-12-1212:12:12","title": "测试标题","content": ""}}
    * }
    */
    @PostMapping("/view")
    public JsonResult view(String politicsId){
        Politics politics = politicsService.findPoliticsById(politicsId);
        if (politics==null){
            return JsonResult.notFound("找不到参政议政");
        }
        List<PoliticsDeption> politicsDeptions = politicsService.findDeptionByPolitics(politics);
        List<PoliticsIssueData> politicsIssueData = politicsService.findIssueDataByPolitics(politics);
        JSONObject object = politicsService.selectByIssueDataAddDeption(politics, politicsDeptions, politicsIssueData);
        jsonResult.setData(object);
        return jsonResult;
    }


    /**
    * @api {POST} /politics/addIssueData 添加议政数据
    * @apiGroup Politics
    * @apiVersion 1.0.0
    * @apiHeader {String} IYunDao-AssessToken token验证
    * @apiDescription 添加议政数据
    * @apiParam jsong格式 {"politicsId" : "4028d8816c4aa85a016c4aa8913d0000","issueData" : [{"title":"11","content":"11","sort":"1"},{"title":"22","content":"22","sort":"2"}]}
    * @apiParamExample {json} 请求样例:
    *               /politics/addIssueData
    * @apiSuccess (200) {String} code 200:成功</br>
    * @apiSuccess (200) {String} message 信息
    * @apiSuccess (200) {String} data 返回用户信息
    * @apiSuccessExample {json} 返回样例:
    * {
    *     "code": 200,
    *     "message": "成功",
    *      "data": []
    * }
    */
    @PostMapping("/addIssueData")
    public JsonResult addIssueData(@RequestBody JSONObject param){
        String politicsId = (String) param.get("politicsId");
        Politics politics = politicsService.findPoliticsById(politicsId);
        if (politics==null){
            return JsonResult.notFound("找不到参政议政");
        }
        List<Map<String,String>> politicsIssueData=  (List<Map<String,String>>)param.get("issueData");
        politicsService.saveAllPoliticsIssueData(politics,politicsIssueData);
        return JsonResult.success();
    }

    /**
     * @api {POST} /politics/addDiscussData 添加讨论数据
     * @apiGroup Politics
     * @apiVersion 1.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 添加讨论数据
     * @apiParam jsong格式 {"politicsId":"4028d8816c4aa85a016c4aa8913d0000","discussData":[{"politicsIssueDataId":"4028d8816c4bb58d016c4bdd15a30003","userid":"402881916ba10b8a016ba113adbc0006","content":"11"},{"politicsIssueDataId":"4028d8816c4bb58d016c4bdd15a70004","userid":"","content":"22"}]}
     * @apiParamExample {json} 请求样例:
     *               /politics/addDiscussData
     * @apiSuccess (200) {String} code 200:成功</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *       "data": {"startTime": "2018-12-12 12:12:12","id": "4028d8816c4bb58d016c4bb701cf0000","state": "unreviewed","endTime": "2019-12-12 12:12:12","synopsis": "正文","title": "测试标题"}
     * }
     */
    @PostMapping("/addDiscussData")
    public JsonResult addDiscussData(@RequestBody JSONObject param){
        String politicsId = (String) param.get("politicsId");
        Politics politics = politicsService.findPoliticsById(politicsId);
        if (politics==null){
            return JsonResult.notFound("找不到参政议政");
        }
        List<Map<String,String>> politicsDiscussData=  (List<Map<String,String>>)param.get("discussData");
        politicsService.saveAllPoliticsDiscussData(politics,politicsDiscussData);
        return JsonResult.success();
    }

    /**
     * @api {POST} /politics/addResolutionData 添加决议数据
     * @apiGroup Politics
     * @apiVersion 1.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 添加决议数据
     * @apiParam jsong格式 {"politicsId":"4028d8816c4aa85a016c4aa8913d0000","resolutionData":[{"politicsIssueDataId":"4028d8816c4bb58d016c4bdd15a30003","content":"33"},{"politicsIssueDataId":"4028d8816c4bb58d016c4bdd15a70004","content":"44"}]}
     * @apiParamExample {json} 请求样例:
     *               /politics/addResolutionData
     * @apiSuccess (200) {String} code 200:成功</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *       "data": {"startTime": "2018-12-12 12:12:12","id": "4028d8816c4bb58d016c4bb701cf0000","state": "unreviewed","endTime": "2019-12-12 12:12:12","synopsis": "正文","title": "测试标题"}
     * }
     */
    @PostMapping("/addResolutionData")
    public JsonResult addResolutionData(@RequestBody JSONObject param){
        String politicsId = (String) param.get("politicsId");
        Politics politics = politicsService.findPoliticsById(politicsId);
        if (politics==null){
            return JsonResult.notFound("找不到参政议政");
        }
        List<Map<String,String>> politicsResolutionData=  (List<Map<String,String>>)param.get("resolutionData");
        politicsService.saveAllPoliticsResolutionData(politics,politicsResolutionData);
        return JsonResult.success();
    }

    /**
    * @api {POST} /politics/del 删除参政议政
    * @apiGroup Politics
    * @apiVersion 1.0.0
    * @apiHeader {String} IYunDao-AssessToken token验证
    * @apiDescription 功能说明
    * @apiParam {String} politicsId 参政议政id  必填
    * @apiParamExample {json} 请求样例:
    *                /politics/del?politicsId=4028d8816c4aa85a016c4aa8913d0000
    * @apiSuccess (200) {String} code 200:成功</br>
    * @apiSuccess (200) {String} message 信息
    * @apiSuccess (200) {String} data 返回用户信息
    * @apiSuccessExample {json} 返回样例:
    * {
    *     "code": 200,
    *     "message": "成功",
    *      "data": []
    * }
    */
    @PostMapping("/del")
    public JsonResult del(String politicsId){
        Politics politics = politicsService.findPoliticsById(politicsId);
        if (politics == null){
            return JsonResult.notFound("没有找到数据");
        }
        politicsService.delete(politics);
        return JsonResult.success();
    }


    /**
     * @api {POST} /politics/delIssueData 删除议题数据id
     * @apiGroup Politics
     * @apiVersion 1.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 功能说明
     * @apiParam {String} issueDataIds 议题数据id  必填
     * @apiParamExample {json} 请求样例:
     *                /politics/delIssueData?issueDataIds=4028d8816c4ccccf016c4ccdbb0b000a
     * @apiSuccess (200) {String} code 200:成功</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *      "data": []
     * }
     */
    @PostMapping("/delIssueData")
    public JsonResult delIssueData(String[] issueDataIds){
        List<PoliticsIssueData> politicsIssueData = politicsService.findIssueDataByIssueDataId(issueDataIds);
        if (CollectionUtils.isEmpty(politicsIssueData)){
            return JsonResult.notFound("没有中找到数据");
        }
        politicsService.deleteIssueDatas(politicsIssueData);
        return  JsonResult.success();
    }


    /**
     * polotics转换成json格式
     * @param currentPageList
     * @return
     */
    private JSONArray converPolitics(List<Politics> currentPageList) {
        JSONArray jsonArray = new JSONArray();
        for (Politics politics : currentPageList) {
            jsonArray.add(JsonUtils.getJson(politics));
        }
        return jsonArray;
    }

    /**
     * poloticsIssueData转换成json格式
     * @param politicsIssueData1
     * @return
     */
    private JSONArray converPoliticsIssueData1(List<PoliticsIssueData> politicsIssueData1) {
        JSONArray jsonArray = new JSONArray();
        for (PoliticsIssueData politics : politicsIssueData1) {
            jsonArray.add(JsonUtils.getJson(politics));
        }
        return jsonArray;
    }
}
