package com.iyundao.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONReader;
import com.iyundao.base.BaseController;
import com.iyundao.base.utils.JsonResult;
import com.iyundao.base.utils.JsonUtils;
import com.iyundao.entity.DoorMessage;
import com.iyundao.entity.SpeechStudy;
import com.iyundao.service.DoorService;
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
 * @ClassName: DoorController
 * @project:
 * @author: 13620
 * @Date: 2019/8/9
 * @Description: 应用门户
 * @Version: V1.0
 */
@RestController
@RequestMapping("/door")
public class DoorController extends BaseController{

    @Autowired
    private DoorService doorService;



    /**
    * @api {POST} /door/addMessage 添加应用门户信息
    * @apiGroup Door
    * @apiVersion 1.0.0
    * @apiHeader {String} IYunDao-AssessToken token验证
    * @apiDescription 添加应用门户信息
    * @apiParam {String} title 标题
    * @apiParam {String} content  正文
    * @apiParam {int} state 状态 0-未展示  1-展示中
    * @apiParamExample {json} 请求样例:
    *                /door/addMessage?title=标题&content=正文&state=1
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
    @PostMapping("/addMessage")
    public JsonResult addMessage(String title,
                                 String content,
                                 @RequestParam(defaultValue = "0")int state){
        if (StringUtils.isEmpty(title)){
            return JsonResult.paramError("标题不得为空");
        }
        if (state != 0 && state !=1){
            return JsonResult.paramError("选择的类型有误");
        }
        DoorMessage doorMessage = doorService.saveMessage(title,content,state);
        jsonResult.setData(JsonUtils.getJson(doorMessage));
        return jsonResult;
    }


    /**
     * @api {POST} /door/listMessage 门户信息列表
     * @apiGroup Door
     * @apiVersion 1.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 门户信息列表
     * @apiParam {int} page 查看的页数 默认1
     * @apiParam {int} size 每页的数量 默认10
     * @apiParamExample {json} 请求样例:
     *                /door/listMessage?page=1&size=2
     * @apiSuccess (200) {String} code 200:成功</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *        "data": [{"id": "4028d8816c7483ba016c74f926d80002","state": "inTheShow","title": "标题","content": "正文"},{"id": "4028d8816c7483ba016c74f924a90001","state": "inTheShow","title": "标题","content": "正文"}]
     * }
     */
    @PostMapping("/listMessage")
    public JsonResult listMessage(@RequestParam(defaultValue = "1") int page,
                                  @RequestParam(defaultValue = "10") int size){
        List<DoorMessage> doorMessages = doorService.findAll();
        List<DoorMessage> currentPageList =  pageMessage(doorMessages,page,size);
        jsonResult.setData(converMessage(currentPageList));
        return jsonResult;
    }

    /**
     * @api {POST} /door/modifyMessageState 修改门户信息状态
     * @apiGroup Door
     * @apiVersion 1.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 门户信息列表
     * @apiParam {String} messageId  门户信息id
     * @apiParam {int} state 修改的状态  0-未展示  1-展示中
     * @apiParamExample {json} 请求样例:
     *                /door/modifyMessageState?messageId=4028d8816c7483ba016c74f926d80002&state=0
     * @apiSuccess (200) {String} code 200:成功</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *        "data": []
     * }
     */
    @PostMapping("/modifyMessageState")
    public JsonResult modifyMessageState(String messageId,
                                         int state){
        DoorMessage doorMessage = doorService.findByMessageId(messageId);
        if (doorMessage == null){
            return JsonResult.notFound("找不到实体");
        }
        doorService.modifyState(messageId,state);
        return JsonResult.success();
    }


    /**
     * @api {POST} /door/delMessage 删除门户信息
     * @apiGroup Door
     * @apiVersion 1.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 删除门户信息
     * @apiParam {String[]} messageIds  门户信息ids
     * @apiParamExample {json} 请求样例:
     *               /door/delMessage?messageIds=4028d8816c7483ba016c74f924a90001
     * @apiSuccess (200) {String} code 200:成功</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *        "data": []
     * }
     */
    @PostMapping("/delMessage")
    public JsonResult delMessage(String[] messageIds){
        List<DoorMessage> doorMessage = doorService.findByMessageIds(messageIds);
        if (CollectionUtils.isEmpty(doorMessage)){
            return JsonResult.notFound("找不到实体");
        }
        doorService.delAllMessage(doorMessage);
        return JsonResult.success();
    }


    /**
     * 转换成json格式
     * @param currentPageList
     * @return
     */
    private JSONArray converMessage(List<DoorMessage> currentPageList) {
        JSONArray arr = new JSONArray();
        for (DoorMessage message : currentPageList) {
            arr.add(JsonUtils.getJson(message));
        }
        return arr;
    }


    /**
     *  Message分页
     * @param doorMessages
     * @param page
     * @param size
     * @return
     */
    private List<DoorMessage> pageMessage(List<DoorMessage> doorMessages, int page, int size) {
        List<DoorMessage> currentPageList = new ArrayList<>();
        if (doorMessages != null && doorMessages.size() > 0) {
            int currIdx = (page > 1 ? (page - 1) * size : 0);
            for (int i = 0; i < size && i < doorMessages.size() - currIdx; i++) {
                DoorMessage data = doorMessages.get(currIdx + i);
                currentPageList.add(data);
            }
        }
        return currentPageList;
    }
}
