package com.iyundao.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.iyundao.base.BaseController;
import com.iyundao.base.utils.JsonResult;
import com.iyundao.base.utils.JsonUtils;
import com.iyundao.entity.Activity;
import com.iyundao.entity.GeographicPosition;
import com.iyundao.service.ActivityService;
import com.iyundao.service.GeographicService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: GeographicController
 * @project:
 * @author: 13620
 * @Date: 2019/8/7
 * @Description:
 * @Version: V1.0
 */
@RestController
@RequestMapping("/geographic")
public class GeographicController extends BaseController{

    @Autowired
    private GeographicService geographicService;

    @Autowired
    private ActivityService activityService;


    /**
    * @api {POST} /geographic/add 添加
    * @apiGroup Geographic
    * @apiVersion 1.0.0
    * @apiHeader {String} IYunDao-AssessToken token验证
    * @apiDescription 添加
    * @apiParam {String} name 名字
    * @apiParam {String} longitude  经度
    * @apiParam {String} latitude  纬度
    * @apiParam {int}  type 类型  0-固定场所 1-活动场所
    * @apiParam {String} activityId  活动id
    * @apiParamExample {json} 请求样例:
    *                /geographic/add?name=名字&x=11.11&y=22.22&activityId&type=0
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
    @PostMapping("/add")
    public JsonResult add(String name,
                          String longitude,
                          String latitude,
                          @RequestParam(defaultValue = "0")int type,
                          String activityId){

        //这里不作非空判断，
        Activity activity = activityService.find(activityId);
        GeographicPosition geographicPosition =  geographicService.save(name,longitude,latitude,type,activity);
        jsonResult.setData(JsonUtils.getJson(geographicPosition));
        return jsonResult;
    }

    /**
     * @api {POST} /geographic/listPage 列表
     * @apiGroup Geographic
     * @apiVersion 1.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 列表
     * @apiParam {int} page 查看的页数 默认1
     * @apiParam {int} size 每页的数量 默认10
     * @apiParamExample {json} 请求样例:
     *                /geographic/listPage?page=1&size=10
     * @apiSuccess (200) {String} code 200:成功</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *      "data": {"total": 8,"size": 10,"page": 1,"content": [{"activityId": "","name": "名字","x": "11.11","activityName": "","y": "22.22","type": "fixedPlace"},{"activityId": "","name": "名字","x": "11.11","activityName": "","y": "22.22","type": "fixedPlace"},{"activityId": "","name": "名字","x": "11.11","activityName": "","y": "22.22","type": "fixedPlace"},{"activityId": "","name": "名字","x": "11.11","activityName": "","y": "22.22","type": "fixedPlace"},{"activityId": "","name": "名字","x": "11.11","activityName": "","y": "22.22","type": "fixedPlace"},{"activityId": "","name": "名字","x": "11.11","activityName": "","y": "22.22","type": "fixedPlace"},{"activityId": "","name": "名字","x": "11.11","activityName": "","y": "22.22","type": "fixedPlace"},{"activityId": "","name": "名字","x": "11.11","activityName": "","y": "22.22","type": "fixedPlace"}]}
     * }
     */
    @PostMapping("/listPage")
    public  JsonResult listPage(@RequestParam(defaultValue = "1")int page,
                                @RequestParam(defaultValue = "10")int size){
        List<GeographicPosition> geographicPositions = geographicService.findAll();
        List<GeographicPosition> currentPageList =pageGeographic(geographicPositions,page,size);
        JSONObject obj = new JSONObject();
        obj.put("page",page);
        obj.put("size",size);
        obj.put("total",geographicPositions.size());
        obj.put("content",converGeographic(currentPageList));
        jsonResult.setData(obj);
        return jsonResult;
    }



    /**
     * @api {POST} /geographic/listPageByActivityId  根据活动id查找地理位置列表
     * @apiGroup Geographic
     * @apiVersion 1.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 根据活动id查找地理位置列表
     * @apiParam {String} activityId  活动id 必填
     * @apiParam {int} page 查看的页数 默认1
     * @apiParam {int} size 每页的数量 默认10
     * @apiParamExample {json} 请求样例:
     *                /geographic/listPageByActivityId?page=1&size=10&activityId=888888878
     * @apiSuccess (200) {String} code 200:成功</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *      "data": {"total": 2,"size": 10,"page": 1,"content": [{"activityId": "888888878","name": "名字","x": "11.11","activityName": "11","y": "22.22","type": "fixedPlace"},{"activityId": "888888878","name": "名字","x": "11.11","activityName": "11","y": "22.22","type": "fixedPlace"}]}
     * }
     */
    @PostMapping("/listPageByActivityId")
    public JsonResult listPageByActivityId(String activityId,
                                           @RequestParam(defaultValue = "1")int page,
                                           @RequestParam(defaultValue = "10")int size){
        Activity activity = activityService.find(activityId);
        if (activity == null){
            return JsonResult.notFound("没有找到实体");
        }
        List<GeographicPosition> geographicPositions = geographicService.findByActivity(activity);
        List<GeographicPosition> currentPageList =pageGeographic(geographicPositions,page,size);
        JSONObject obj = new JSONObject();
        obj.put("page",page);
        obj.put("size",size);
        obj.put("total",geographicPositions.size());
        obj.put("content",converGeographic(currentPageList));
        jsonResult.setData(obj);
        return jsonResult;
    }

    /**
     * @api {POST} /geographic/delByActivityId   根据活动删除所有地理位置
     * @apiGroup Geographic
     * @apiVersion 1.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 根据活动删除所有地理位置
     * @apiParam {String} activityId  活动id 必填
     * @apiParamExample {json} 请求样例:
     *                /geographic/delByActivityId?activityId=888888878
     * @apiSuccess (200) {String} code 200:成功</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *       "data": []
     * }
     */
    @PostMapping("/delByActivityId")
    public JsonResult delByActivityId(String activityId){
        Activity activity = activityService.find(activityId);
        if (activity == null){
            return JsonResult.notFound("没有找到实体");
        }
        geographicService.deleteByActivity(activity);
        return JsonResult.success();
    }

    /**
     * @api {POST} /geographic/delByIds   根据地理位置id删除地理位置
     * @apiGroup Geographic
     * @apiVersion 1.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 根据地理位置id删除地理位置
     * @apiParam {String[]} geographicIds  地理位置id 必填
     * @apiParamExample {json} 请求样例:
     *                /geographic/delByIds?geographicIds=4028d8816c69c798016c69c82f510006,4028d8816c69c798016c69c831060007
     * @apiSuccess (200) {String} code 200:成功</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *       "data": []
     * }
     */
    @PostMapping("/delByIds")
    public JsonResult delByIds(String[] geographicIds){
        List<GeographicPosition> geographicPositions = geographicService.findAllByIds(geographicIds);
        if (CollectionUtils.isEmpty(geographicPositions)){
            return JsonResult.notFound("找不到实体");
        }
        geographicService.delete(geographicPositions);
        return JsonResult.success();
    }


    /**
     *  GeographicPosition转换成Json
     * @param currentPageList
     * @return
     */
    private JSONArray converGeographic(List<GeographicPosition> currentPageList) {
        JSONArray arr = new JSONArray();
        for (GeographicPosition gp : currentPageList) {
            JSONObject obj = new JSONObject();
            obj.put("name",gp.getName());
            obj.put("longitude",gp.getLongitude());
            obj.put("latitude",gp.getLatitude());
            obj.put("type",gp.getType());
            if (gp.getActivity() != null){
                obj.put("activityName",gp.getActivity().getName());
                obj.put("activityId",gp.getActivity().getId());
            }else {
                obj.put("activityName","");
                obj.put("activityId","");
            }
            arr.add(obj);
        }
        return arr;
    }


    /**
     * GeographicPosition  分页
     * @param geographicPositions
     * @param page
     * @param size
     * @return
     */
    private List<GeographicPosition> pageGeographic(List<GeographicPosition> geographicPositions, int page, int size) {
        List<GeographicPosition> currentPageList = new ArrayList<>();
        if (geographicPositions != null && geographicPositions.size() > 0) {
            int currIdx = (page > 1 ? (page - 1) * size : 0);
            for (int i = 0; i < size && i < geographicPositions.size() - currIdx; i++) {
                GeographicPosition data = geographicPositions.get(currIdx + i);
                currentPageList.add(data);
            }
        }
        return currentPageList;

    }

}
