package com.iyundao.controller;

import com.iyundao.base.BaseController;


import com.iyundao.base.Page;
import com.iyundao.base.Pageable;
import com.iyundao.base.utils.JsonResult;
import com.iyundao.base.utils.JsonUtils;
import com.iyundao.entity.Politics;
import com.iyundao.service.PoliticsService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

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


    @PostMapping("/listPage")
    public JsonResult listPage(@RequestParam(defaultValue = "0")int page,
                               @RequestParam(defaultValue = "10")int size){
        Page<Politics> politicsPage = politicsService.findAllForPage(new Pageable(page,size));
        jsonResult.setData(JsonUtils.getPage(politicsPage));
        return jsonResult;
    }

    /**
    * @api {POST} /politics/add  添加
    * @apiGroup Politics
    * @apiVersion 1.0.0
    * @apiHeader {String} IYunDao-AssessToken token验证
    * @apiDescription 添加
    * @apiParam {String} title 标题 必填
    * @apiParam {String} synopsis 简介
    * @apiParam {String} startTime 开始时间
    * @apiParam {String} endTime 结束时间
    * @apiParam {int} state 审核状态 0-未审核 1-审核不通过 2-审核通过
    * @apiParamExample {json} 请求样例:
    *                全路径加参数
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
    public JsonResult add(String title,
                          String synopsis,
                          String startTime,
                          String endTime,
                          int state){
        if (StringUtils.isEmpty(title)){
            return JsonResult.paramError("标题不得为空");
        }
       Politics politics = politicsService.add(title,synopsis,startTime,endTime,state);
        jsonResult.setData(JsonUtils.getJson(politics));
        return jsonResult;
    }

}
