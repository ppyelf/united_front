package com.iyundao.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.iyundao.base.BaseController;
import com.iyundao.base.utils.JsonResult;
import com.iyundao.base.utils.JsonUtils;
import com.iyundao.entity.*;
import com.iyundao.service.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: LearnController
 * @project:
 * @author: 13620
 * @Date: 2019/8/9
 * @Description: 学习
 * @Version: V1.0
 */
@RestController
@RequestMapping("/learn")
public class LearnController extends BaseController {


    @Autowired
    private LearnService learnService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private DepartService departService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private UserService userService;


    /**
    * @api {POST}  /learn/addLearn  添加学习任务
    * @apiGroup Learn
    * @apiVersion 1.0.0
    * @apiHeader {String} IYunDao-AssessToken token验证
    * @apiDescription 添加学习任务
    * @apiParam {MultipartFile} file 文件
    * @apiParam {String} title  标题
    * @apiParam {String} content 正文
    * @apiParam {String[]} subjects 部门id
    * @apiParam {String[]} departs
    * @apiParam {String[]} groups
    * @apiParam {String[]} users
    * @apiParamExample {json} 请求样例:
    *                /learn/addLearn
    * @apiSuccess (200) {String} code 200:成功</br>
    * @apiSuccess (200) {String} message 信息
    * @apiSuccess (200) {String} data 返回用户信息
    * @apiSuccessExample {json} 返回样例:
    * {
    *     "code": 200,
    *     "message": "成功",
    *     "data": {"id": "4028d8816c78fded016c7950b4d5001c","title": "标题","content": "正文"}
    * }
    */
    @PostMapping("/addLearn")
    public JsonResult addLearn(MultipartFile file,
                               String title,
                               String content,
                               String[] subjects,
                               String[] departs,
                               String[] groups,
                               String[] users){
        if (StringUtils.isEmpty(title)){
            return  JsonResult.notFound("请输入标题");
        }
         jsonResult = learnService.saveAll(file,title,content,subjects,departs,groups,users);
        return jsonResult;
    }


    /**
     * @api {POST} /learn/listPageLearn  学习列表
     * @apiGroup Learn
     * @apiVersion 1.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 学习列表
     * @apiParam {int} page 查看的页数 默认1
     * @apiParam {int} size 每页的数量 默认10
     * @apiParamExample {json} 请求样例:
     *               /learn/listPageLearn?page=1&size=10
     * @apiSuccess (200) {String} code 200:成功</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *         "data": {"total": 4,"size": 10,"page": 1,"content": [{"id": "4028d8816c78fded016c7950b4d5001c","title": "标题","content": "正文"},{"id": "4028d8816c78fded016c7950b25f0015","title": "标题","content": "正文"},{"id": "4028d8816c78fded016c7950afa7000e","title": "标题","content": "正文"},{"id": "4028d8816c78fded016c795059f30007","title": "标题","content": "正文"}]}
     * }
     */
    @PostMapping("/listPageLearn")
    public JsonResult listPageLearn(@RequestParam(defaultValue = "1")int page,
                               @RequestParam(defaultValue = "10")int size){
        List<Learn> learnList = learnService.findAll();
        List<Learn> currentPageList = pageLearn(learnList,page,size);
        JSONObject obj = new JSONObject();
        obj.put("page",page);
        obj.put("size",size);
        obj.put("total",learnList.size());
        obj.put("content",converLearn(currentPageList));
        jsonResult.setData(obj);
        return jsonResult;
    }



    /**
     * @api {POST} /learn/viewLearn  查看学习详情
     * @apiGroup Learn
     * @apiVersion 1.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 查看学习详情
     * @apiParam {String} learnId 学习任务
     * @apiParamExample {json} 请求样例:
     *               /learn/viewLearn?learnId=4028d8816c78fded016c795059f30007
     * @apiSuccess (200) {String} code 200:成功</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *          "data": {"learnDetails": [{"state": "didNotLearn","id": "4028d8816c78fded016c79505ab6000c","userName": "管理员","userId": "0a4179fc06cb49e3ac0db7bcc8cf0882"},{"state": "didNotLearn","id": "4028d8816c78fded016c79505ac4000d","userName": "钱正","userId": "402881916ba10b8a016ba113adbc0006"}],"learn": {"id": "4028d8816c78fded016c795059f30007","title": "标题","content": "正文"},"learnFile": [{"name": "17274ec327ef4d279520945316ae6eff","id": "4028d8816c78fded016c79505a050008","suffix": "xls","url": "learnfile\\17274ec327ef4d279520945316ae6eff.xls"}],"learnParticipation": [{"id": "4028d8816c78fded016c79505a320009","subecjtId": "402881916b9d3031016b9d626593000c","subecjtName": "测试机构1"},"groupName": "测试组织3","groupId": "402881916b9d3031016b9d706b4e0012","id": "4028d8816c78fded016c79505a85000a"},{"id": "4028d8816c78fded016c79505a9a000b","userName": "钱正","userId": "402881916ba10b8a016ba113adbc0006"}]}
     * }
     */
    @PostMapping("/viewLearn")
    public JsonResult viewLearn(String learnId){
        Learn learn = learnService.findById(learnId);
        if (learn == null ){
            return JsonResult.notFound("找不到实体");
        }
        JSONObject jsonObject = learnService.findView(learn);
        jsonResult.setData(jsonObject);
        return jsonResult;
    }


    /**
     * @api {POST} /learn/delLearn  删除学习详情
     * @apiGroup Learn
     * @apiVersion 1.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 删除学习详情
     * @apiParam {String} learnId 学习任务id
     * @apiParamExample {json} 请求样例:
     *               /learn/delLearn?learnId=4028d8816c78fded016c795059f30007
     * @apiSuccess (200) {String} code 200:成功</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *          "data": []
     * }
     */
    @PostMapping("/delLearn")
    public JsonResult delLearn(String learnId){
        Learn learn = learnService.findById(learnId);
        if (learn == null ){
            return JsonResult.notFound("找不到实体");
        }
        learnService.delLearn(learn);

        return JsonResult.success();
    }



    /**
     * learn转换成json格式
     * @param currentPageList
     * @return
     */
    private JSONArray converLearn(List<Learn> currentPageList) {
        JSONArray arr = new JSONArray();
        for (Learn learn : currentPageList) {
            arr.add(JsonUtils.getJson(learn));
        }
        return arr;
    }


    /**
     * Learn分页
     * @param learnList
     * @param page
     * @param size
     * @return
     */
    private List<Learn> pageLearn(List<Learn> learnList, int page, int size) {
        List<Learn> currentPageList = new ArrayList<>();
        if (learnList != null && learnList.size() > 0) {
            int currIdx = (page > 1 ? (page - 1) * size : 0);
            for (int i = 0; i < size && i < learnList.size() - currIdx; i++) {
                Learn data = learnList.get(currIdx + i);
                currentPageList.add(data);
            }
        }
        return currentPageList;

    }
}
