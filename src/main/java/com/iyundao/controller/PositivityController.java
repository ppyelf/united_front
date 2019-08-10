package com.iyundao.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.iyundao.base.BaseController;
import com.iyundao.base.utils.JsonResult;
import com.iyundao.entity.QuestionnaireScore;
import com.iyundao.entity.User;
import com.iyundao.service.ActivityService;
import com.iyundao.service.QuestionnaireService;
import com.iyundao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: PositivityController
 * @project:
 * @author: 13620
 * @Date: 2019/8/8
 * @Description:  积极性信息
 * @Version: V1.0
 */
@RestController
@RequestMapping("/positivity")
public class PositivityController extends BaseController{

    @Autowired
    private QuestionnaireService questionnaireService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private UserService userService;

    /**
    * @api {POST} /positivity/questionnaire  调查参与积极性排名
    * @apiGroup Positivity
    * @apiVersion 1.0.0
    * @apiHeader {String} IYunDao-AssessToken token验证
    * @apiDescription 调查参与积极性排名
    * @apiParam {int} type  0-本日 1-本周 2-本月-3本年 默认0
    * @apiParamExample {json} 请求样例:
    *                /positivity/questionnaire?type=3
    * @apiSuccess (200) {String} code 200:成功</br>
    * @apiSuccess (200) {String} message 信息
    * @apiSuccess (200) {String} data 返回用户信息
    * @apiSuccessExample {json} 返回样例:
    * {
    *     "code": 200,
    *     "message": "成功",
    *       "data": [{"score": 20,"userName": "管理员","userId": "0a4179fc06cb49e3ac0db7bcc8cf0882"},{"score": 30,"userName": "钱正","userId": "402881916ba10b8a016ba113adbc0006"}]
    * }
    */
    @PostMapping("/questionnaire")
    public JsonResult positivityQuestionnaire(@RequestParam(defaultValue = "0")int type){
        if (type != 0 &&type !=1 && type !=2 && type !=3){
            return JsonResult.paramError("类型输入有误");
        }
        List<Map<String,Object>> map = questionnaireService.findAllByType(type);
      JSONArray arr = new JSONArray();
        for (Map<String, Object> stringObjectMap : map) {
            JSONObject obj = new JSONObject();
            obj.put("userName",stringObjectMap.get("userName"));
            obj.put("userId",stringObjectMap.get("userId"));
            obj.put("score",stringObjectMap.get("score"));
            arr.add(obj);
        }
        jsonResult.setData(arr);
        return jsonResult;
    }


    /**
     * @api {POST} /positivity/activityScore  活动参与积极性排名
     * @apiGroup Positivity
     * @apiVersion 1.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 调查参与积极性排名
     * @apiParam {int} type  0-本日 1-本周 2-本月-3本年 默认0
     * @apiParamExample {json} 请求样例:
     *                /positivity/activityScore?type=3
     * @apiSuccess (200) {String} code 200:成功</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *         "data": [{"score": 5,"userName": "钱正","userId": "402881916ba10b8a016ba113adbc0006"},{"score": 20,"userName": "管理员","userId": "0a4179fc06cb49e3ac0db7bcc8cf0882"}]
     * }
     */
    @PostMapping("/activityScore")
    public  JsonResult activityScore(@RequestParam(defaultValue = "0")int type){
        if (type != 0 &&type !=1 && type !=2 && type !=3){
            return JsonResult.paramError("类型输入有误");
        }
        List<Map<String,Object>> map =activityService.findAllByType(type);
        JSONArray arr = new JSONArray();
        for (Map<String, Object> stringObjectMap : map) {
            JSONObject obj = new JSONObject();
            obj.put("score",stringObjectMap.get("score"));
            obj.put("userId",userService.findById(stringObjectMap.get("userId").toString()).getId());
            obj.put("userName",userService.findById(stringObjectMap.get("userId").toString()).getName());
            arr.add(obj);
        }
        jsonResult.setData(arr);
        return jsonResult;
    }

}
