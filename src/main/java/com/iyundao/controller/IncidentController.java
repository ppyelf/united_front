package com.iyundao.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.iyundao.base.BaseController;
import com.iyundao.base.utils.JsonResult;
import com.iyundao.base.utils.JsonUtils;
import com.iyundao.entity.Incident;
import com.iyundao.entity.SpeechArticle;
import com.iyundao.service.IncidentService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: IncidentController
 * @project: //todo
 * @author: 13620
 * @Date: 2019/8/3
 * @Description:
 * @Version: V1.0
 */
@RestController
@RequestMapping("/incident")
public class IncidentController extends BaseController{

    @Autowired
    private IncidentService incidentService;

    /**
     * @api {POST} /incident/page 事件列表
     * @apiGroup Incident
     * @apiVersion 1.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 事件列表
     * @apiParam {int} page 查看的页数 默认1
     * @apiParam {int} size 每页的数量 默认10
     * @apiParamExample {json} 请求样例:
     *                /incident/page?page=1&size=2
     * @apiSuccess (200) {String} code 200:成功</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *      "data": [{"dataTime": "发生时间","id": "4028d8816c562f32016c564c33f40000","title": "事件标题","content": "事件正文"},{"dataTime": "发生时间","id": "4028d8816c562f32016c564c72a80001","title": "事件标题","content": "事件正文"}]
     * }
     */
    @PostMapping("/page")
    public JsonResult Page(@RequestParam(defaultValue = "1")int page,
                           @RequestParam(defaultValue = "10")int size){
        List<Incident> incidentList =   incidentService.findAll();
        List<Incident> currentPageList = pageIncident(incidentList,page,size);
        jsonResult.setData(converIncident(currentPageList));
        return jsonResult;
    }


    /**
    * @api {POST} /incident/add 添加事件
    * @apiGroup Incident
    * @apiVersion 1.0.0
    * @apiHeader {String} IYunDao-AssessToken token验证
    * @apiDescription 添加事件
    * @apiParam {String} title  标题 必填
    * @apiParam {String} content  事件内容
    * @apiParam {String} dataTime  发生时间
    * @apiParamExample {json} 请求样例:
    *                /incident/add?title=事件标题&content=事件正文&dataTime=发生时间
    * @apiSuccess (200) {String} code 200:成功</br>
    * @apiSuccess (200) {String} message 信息
    * @apiSuccess (200) {String} data 返回用户信息
    * @apiSuccessExample {json} 返回样例:
    * {
    *     "code": 200,
    *     "message": "成功",
    *       "data": {"dataTime": "发生时间","id": "4028d8816c562f32016c564cafff0006","title": "事件标题","content": "事件正文"}
    * }
    */
    @PostMapping("/add")
    public JsonResult add(String title,
                          String content,
                          String dataTime){
        if (StringUtils.isEmpty(title)){
            return JsonResult.paramError("标题不得为空");
        }
        Incident incident = incidentService.saveIncident(title,content,dataTime);
        jsonResult.setData(JsonUtils.getJson(incident));
        return jsonResult;
    }


    /**
     * @api {POST} /incident/view 查看事件详情
     * @apiGroup Incident
     * @apiVersion 1.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 查看事件详情
     * @apiParam {String} incidentId  事件id 必填
     * @apiParamExample {json} 请求样例:
     *              /incident/view?incidentId=4028d8816c562f32016c564cafff0006
     * @apiSuccess (200) {String} code 200:成功</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *       "data": {"dataTime": "发生时间","id": "4028d8816c562f32016c564cafff0006","title": "事件标题","content": "事件正文"}
     * }
     */
    @PostMapping("/view")
    public JsonResult view(String incidentId){
        Incident incident = incidentService.findById(incidentId);
        if (incident == null){
            return JsonResult.notFound("找不到事件");
        }
        jsonResult.setData(JsonUtils.getJson(incident));
        return jsonResult;
    }


    /**
     * @api {POST} /incident/del 删除事件
     * @apiGroup Incident
     * @apiVersion 1.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 删除事件
     * @apiParam {String[]} incidentIds  事件id 必填
     * @apiParamExample {json} 请求样例:
     *              /incident/del?incidentIds=4028d8816c562f32016c564cafff0006
     * @apiSuccess (200) {String} code 200:成功</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *       "data": {"dataTime": "发生时间","id": "4028d8816c562f32016c564cafff0006","title": "事件标题","content": "事件正文"}
     * }
     */
    @PostMapping("/del")
    public JsonResult del(String[] incidentIds){
        List<Incident> incidentList = incidentService.findAllById(incidentIds);
        if (CollectionUtils.isEmpty(incidentList)){
            return JsonResult.notFound("找不到事件");
        }
        incidentService.deleteAll(incidentList);
        return JsonResult.success();
    }



    /**
     *  List<Incident>分页
     */
    private List<Incident> pageIncident(List<Incident> incidentList, int page, int size){
        List<Incident> currentPageList = new ArrayList<>();
        if (incidentList != null && incidentList.size() > 0) {
            int currIdx = (page > 1 ? (page - 1) * size : 0);
            for (int i = 0; i < size && i < incidentList.size() - currIdx; i++) {
                Incident data = incidentList.get(currIdx + i);
                currentPageList.add(data);
            }
        }
        return currentPageList;
    }


    /**
     * Incident转换成json格式
     * @param currentPageList
     * @return
     */
    private JSONArray converIncident(List<Incident> currentPageList) {
        JSONArray jsonArray = new JSONArray();
        for (Incident incident : currentPageList) {
            JSONObject json = JsonUtils.getJson(incident);
            jsonArray.add(json);
        }
        return jsonArray;
    }
}
