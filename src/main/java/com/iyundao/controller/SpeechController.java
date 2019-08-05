package com.iyundao.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.iyundao.base.BaseController;
import com.iyundao.base.Page;
import com.iyundao.base.Pageable;
import com.iyundao.base.utils.JsonResult;
import com.iyundao.base.utils.JsonUtils;
import com.iyundao.entity.Politics;
import com.iyundao.entity.SpeechArticle;
import com.iyundao.entity.SpeechStudy;
import com.iyundao.entity.User;
import com.iyundao.service.SpeechService;
import com.iyundao.service.UserService;
import javafx.geometry.Pos;
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
 * @ClassName: SpeechController
 * @project: //todo 参与讨论信息先不写没表
 * @author: 13620
 * @Date: 2019/8/2
 * @Description:    言论记录
 * @Version: V1.0
 */

@RestController
@RequestMapping("/speech")
public class SpeechController extends BaseController{

    @Autowired
    private UserService userService;

    @Autowired
    private SpeechService speechService;

    /**
     * @api {POST} /speech/listPageArticles 文章列表
     * @apiGroup Speech
     * @apiVersion 1.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 文章列表
     * @apiParam {int} page 查看的页数 默认1
     * @apiParam {int} size 每页的数量 默认10
     * @apiParamExample {json} 请求样例:
     *                /speech/listPageArticles?page=1&size=10
     * @apiSuccess (200) {String} code 200:成功</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *       "data": [{"id": "4028d8816c51747c016c519f6eb70004","time": "2018.12.12 12:12:12","sort": "1","title": "标题","userName": "001","userId": "402881916ba10b8a016ba113adbc0006","content": "正文","url": "路径"}]
     * }
     */
    @PostMapping("/listPageArticles")
    public JsonResult listPageArticles(@RequestParam(defaultValue = "1")int page,
                               @RequestParam(defaultValue = "10")int size){
        List<SpeechArticle> speechArticles = speechService.findAllSpeechArticle();
        List<SpeechArticle>  currentPageList = pageSpeechArticle(speechArticles,page,size);
        jsonResult.setData(converSpeechArticle(currentPageList));
        return jsonResult;
    }


    /**
     * @api {POST} /speech/listPageArticlesByUserId 查询用户所有文章
     * @apiGroup Speech
     * @apiVersion 1.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 查询用户所有文章
     * @apiParam {int} page 查看的页数 默认1
     * @apiParam {int} size 每页的数量 默认10
     * @apiParam {String} userId 用户id 必填
     * @apiParamExample {json} 请求样例:
     *                /speech/listPageArticlesByUserId?page=1&size=10&userId=402881916ba10b8a016ba113adbc0006
     * @apiSuccess (200) {String} code 200:成功</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *        "data": [{"id": "4028d8816c51747c016c519f6eb70004","time": "2018.12.12 12:12:12","sort": "1","title": "标题","userName": "钱正","userId": "402881916ba10b8a016ba113adbc0006","content": "正文","url": "路径"},{"id": "4028d8816c51aa6f016c51b799ef0000","time": "2018.12.12 12:12:12","sort": "1","title": "标题","userName": "钱正","userId": "402881916ba10b8a016ba113adbc0006","content": "正文","url": "路径"}]
     * }
     */
    @PostMapping("/listPageArticlesByUserId")
    public JsonResult listPageArticlesByUserId(@RequestParam(defaultValue = "1")int page,
                                               @RequestParam(defaultValue = "10")int size,
                                               String userId){
        User user = userService.findById(userId);
        if (user ==null){
            return  JsonResult.notFound("用户不存在");
        }
        List<SpeechArticle> speechArticles = speechService.findAllSpeechArticleByUser(user);
        List<SpeechArticle> currentPageList = pageSpeechArticle(speechArticles,page,size);
        jsonResult.setData(converSpeechArticle(currentPageList));
        return jsonResult;

    }



    /**
    * @api {POST} /speech/viewArticles 查看文章详情
    * @apiGroup Speech
    * @apiVersion 1.0.0
    * @apiHeader {String} IYunDao-AssessToken token验证
    * @apiDescription 查看文章详情
    * @apiParam {String} speechArticleId  文章id
    * @apiParamExample {json} 请求样例:
    *                /speech/viewArticles?speechArticleId=4028d8816c51747c016c5175548b0000
    * @apiSuccess (200) {String} code 200:成功</br>
    * @apiSuccess (200) {String} message 信息
    * @apiSuccess (200) {String} data 返回用户信息
    * @apiSuccessExample {json} 返回样例:
    * {
    *     "code": 200,
    *     "message": "成功",
    *       "data": {"id": "4028d8816c51747c016c5175548b0000","time": "2018.12.12 12:12:12","sort": "1","title": "标题","userName": "001","userId": "402881916ba10b8a016ba113adbc0006","url": "路径","content": "正文"}
    * }
    */
    @PostMapping("/viewArticles")
    public JsonResult viewArticles(String speechArticleId){
        SpeechArticle speechArticle = speechService.findSpeechArticleById(speechArticleId);
        if (speechArticle==null){
            return JsonResult.notFound("找不到文章");
        }
        JSONObject json = JsonUtils.getJson(speechArticle);
        json.put("userName",speechArticle.getUser().getName());
        json.put("userId",speechArticle.getUser().getId());
        jsonResult.setData(json);
        return jsonResult;
    }


    /**
    * @api {POST} /speech/addArticles 添加文章
    * @apiGroup Speech
    * @apiVersion 1.0.0
    * @apiHeader {String} IYunDao-AssessToken token验证
    * @apiDescription 添加文章
    * @apiParam {String} title  标题
    * @apiParam {String} content 正文
    * @apiParam {String} url 路径
    * @apiParam {String} userId 用户id
    * @apiParam {int}  sort    排序
    * @apiParam {String} time  发布时间
    * @apiParamExample {json} 请求样例:
    *                /speech/addArticles?title=标题&content=正文&url=路径&userid=402881916ba10b8a016ba113adbc0006&sort=1&time=2018.12.12 12:12:12
    * @apiSuccess (200) {String} code 200:成功</br>
    * @apiSuccess (200) {String} message 信息
    * @apiSuccess (200) {String} data 返回用户信息
    * @apiSuccessExample {json} 返回样例:
    * {
    *     "code": 200,
    *     "message": "成功",
    *       "data": {"id": "4028d8816c515f03016c515f9a470000","time": "2018.12.12 12:12:12","sort": "1","title": "标题","url": "路径","content": "正文"}
    * }
    */
    @PostMapping("/addArticles")
    public JsonResult addArticles(String title,
                          String content,
                          String url,
                          String userId,
                          int sort,
                          String time){
            if (StringUtils.isEmpty(title)){
                return JsonResult.paramError("标题不得为空");
            }
            User user = userService.findById(userId);
            if (user == null){
                return JsonResult.notFound("没有找到用户");
            }
        SpeechArticle speechArticle = speechService.saveSpeechArticle(title,content,url,user,sort,time);
        jsonResult.setData(JsonUtils.getJson(speechArticle));
        return jsonResult;
    }

    /**
    * @api {POST} /speech/delArticles 删除文章
    * @apiGroup Speech
    * @apiVersion 1.0.0
    * @apiHeader {String} IYunDao-AssessToken token验证
    * @apiDescription 删除文章
    * @apiParam {String[]} speechArticleIds  文章id
    * @apiParamExample {json} 请求样例:
    *                /speech/delArticles?speechArticleIds=4028d8816c51747c016c517fd2d20002,4028d8816c51747c016c519f6b950003
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
    @PostMapping("/delArticles")
    public JsonResult delArticles(String[] speechArticleIds){
        List<SpeechArticle> speechArticleList = speechService.findSpeechArticleByIds(speechArticleIds);
        if (CollectionUtils.isEmpty(speechArticleList)){
            return  JsonResult.notFound("找不到文章");
        }
        speechService.deleteAllSpeechArticle(speechArticleList);
        return JsonResult.success();
    }

    /**
     * @api {POST} /speech/listPageStudy 理论研究列表
     * @apiGroup Speech
     * @apiVersion 1.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 理论研究列表
     * @apiParam {int} page 查看的页数 默认1
     * @apiParam {int} size 每页的数量 默认10
     * @apiParamExample {json} 请求样例:
     *                /speech/listPageStudy?page=1&size=10
     * @apiSuccess (200) {String} code 200:成功</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *      "data": [{"result": "结果","startTime": "2018.12.12 12:12:12","id": "4028d8816c551b7e016c5527f4bb0002","state": "notStarted","endTime": "2018.12.12 12:12:12","title": "标题","userName": "钱正","userId": "402881916ba10b8a016ba113adbc0006","content": "正文"},{"result": "结果","startTime": "2018.12.12 12:12:12","id": "4028d8816c551b7e016c5527f76a0003","state": "notStarted","endTime": "2018.12.12 12:12:12","title": "标题","userName": "钱正","userId": "402881916ba10b8a016ba113adbc0006","content": "正文"}]
     * }
     */
    @PostMapping("/listPageStudy")
    public JsonResult listPageStudy(@RequestParam(defaultValue = "1")int page,
                                    @RequestParam(defaultValue = "10")int size){
            List<SpeechStudy> speechStudies = speechService.findAllSpeechStudy();
            List<SpeechStudy> currentPageList = pageSpeechStudy(speechStudies,page,size);
            jsonResult.setData(converSpeechStudy(currentPageList));
            return jsonResult;
    }

    /**
     * @api {POST} /speech/listPageStudyByUserId 查询用户所有理论研究
     * @apiGroup Speech
     * @apiVersion 1.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 查询用户所有理论研究
     * @apiParam {int} page 查看的页数 默认1
     * @apiParam {int} size 每页的数量 默认10
     * @apiParam {String} userId 用户id 必填
     * @apiParamExample {json} 请求样例:
     *                /speech/listPageStudyByUserId?page=1&size=10&userId=402881916ba10b8a016ba113adbc0006
     * @apiSuccess (200) {String} code 200:成功</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *        "data": [{"result": "结果","startTime": "2018.12.12 12:12:12","id": "4028d8816c551b7e016c5527f4bb0002","state": "notStarted","endTime": "2018.12.12 12:12:12","title": "标题","userName": "钱正","userId": "402881916ba10b8a016ba113adbc0006","content": "正文"},{"result": "结果","startTime": "2018.12.12 12:12:12","id": "4028d8816c551b7e016c5527f76a0003","state": "notStarted","endTime": "2018.12.12 12:12:12","title": "标题","userName": "钱正","userId": "402881916ba10b8a016ba113adbc0006","content": "正文"}]
     * }
     */
    @PostMapping("/listPageStudyByUserId")
    public JsonResult listPageStudyByUserId (@RequestParam(defaultValue = "1")int page,
                                             @RequestParam(defaultValue = "10")int size,
                                             String userId){
        User user = userService.findById(userId);
        if (user ==null){
            return  JsonResult.notFound("用户不存在");
        }
        List<SpeechStudy> speechStudies = speechService.findAllSpeechStudyByUser(user);
        List<SpeechStudy> currentPageList = pageSpeechStudy(speechStudies,page,size);
        jsonResult.setData(converSpeechStudy(currentPageList));
        return jsonResult;
    }


    /**
     * @api {POST} /speech/viewStudy 查看理论研究详情
     * @apiGroup Speech
     * @apiVersion 1.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 查看理论研究详情
     * @apiParam {String} speechArticleId  理论研究id
     * @apiParamExample {json} 请求样例:
     *                /speech/viewStudy?speechStudyId=4028d8816c551b7e016c5527f4bb0002
     * @apiSuccess (200) {String} code 200:成功</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *       "data": {"result": "结果","startTime": "2018.12.12 12:12:12","id": "4028d8816c551b7e016c5527f4bb0002","state": "notStarted","endTime": "2018.12.12 12:12:12","title": "标题""userName": "钱正","userId": "402881916ba10b8a016ba113adbc0006","content": "正文"}
     * }
     */
    @PostMapping("/viewStudy")
    public JsonResult viewStudy(String speechStudyId){
        SpeechStudy speechStudy = speechService.findSpeechStudyById(speechStudyId);
        if (speechStudy==null){
            return JsonResult.notFound("找不到理论研究");
        }
        JSONObject json = JsonUtils.getJson(speechStudy);
        json.put("userName",speechStudy.getUser().getName());
        json.put("userId",speechStudy.getUser().getId());
        jsonResult.setData(json);
        return jsonResult;

    }

    /**
     * @api {POST} /speech/addStudy 添加理论研究
     * @apiGroup Speech
     * @apiVersion 1.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 添加理论研究
     * @apiParam {String} title  标题
     * @apiParam {String} content 正文
     * @apiParam {String} userId 用户id
     * @apiParam {int}   state   状态 0-未开始 1-进行中 2-已结束
     * @apiParam {String} startTime  研究开始时间
     * @apiParam {String} endTime  研究结束时间
     * @apiParam {String} result  研究结果
     * @apiParamExample {json} 请求样例:
     *               /speech/addStudy?title=标题&content=正文&userId=402881916ba10b8a016ba113adbc0006&state=0&startTime=2018.12.12 12:12:12&endTime=2018.12.12 12:12:12&result=结果
     * @apiSuccess (200) {String} code 200:成功</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *       "data": {"result": "结果","startTime": "2018.12.12 12:12:12","id": "4028d8816c551b7e016c551ecba80000","state": "notStarted","endTime": "2018.12.12 12:12:12","title": "标题","content": "正文"}
     * }
     */
    @PostMapping("/addStudy")
    public JsonResult addStudy(String title,
                               String content,
                               String userId,
                               int state,
                               String startTime,
                               String endTime,
                               String result){
        if (StringUtils.isEmpty(title)){
            return JsonResult.paramError("标题不得为空");
        }
        User user = userService.findById(userId);
        if (user == null){
            return JsonResult.notFound("没有找到用户");
        }
        SpeechStudy speechStudy = speechService.saveSpeechStudy(title,content,user,state,startTime,endTime,result);
        jsonResult.setData(JsonUtils.getJson(speechStudy));
        return jsonResult;
    }

    /**
     * @api {POST} /speech/delStudy 删除理论研究
     * @apiGroup Speech
     * @apiVersion 1.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 删除理论研究
     * @apiParam {String[]} speechStudyIds  理论研究id
     * @apiParamExample {json} 请求样例:
     *               /speech/delStudy?speechStudyIds=4028d8816c551b7e016c554267950006,4028d8816c551b7e016c554260fc0005
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
    @PostMapping("/delStudy")
    public JsonResult delStudy(String[] speechStudyIds){
        List<SpeechStudy> speechStudyList = speechService.findSpeechStudyByIds(speechStudyIds);
        if (CollectionUtils.isEmpty(speechStudyList)){
            return JsonResult.notFound("找不到文章");
        }
        speechService.deleteAllSpeechStudy(speechStudyList);
        return JsonResult.success();
    }

    /**
     *  List<SpeechArticle>分页
     */
    private List<SpeechArticle> pageSpeechArticle(List<SpeechArticle> speechArticles,int page,int size){
        List<SpeechArticle> currentPageList = new ArrayList<>();
        if (speechArticles != null && speechArticles.size() > 0) {
            int currIdx = (page > 1 ? (page - 1) * size : 0);
            for (int i = 0; i < size && i < speechArticles.size() - currIdx; i++) {
                SpeechArticle data = speechArticles.get(currIdx + i);
                currentPageList.add(data);
            }
        }
        return currentPageList;
    }


    /**
     * SpeechArticle转换成json格式
     * @param currentPageList
     * @return
     */
    private JSONArray converSpeechArticle(List<SpeechArticle> currentPageList) {
        JSONArray jsonArray = new JSONArray();
        for (SpeechArticle speechArticle : currentPageList) {
            JSONObject json = JsonUtils.getJson(speechArticle);
            json.put("userName",speechArticle.getUser().getName());
            json.put("userId",speechArticle.getUser().getId());
            jsonArray.add(json);
        }
        return jsonArray;
    }

    /**
     *  List<SpeechStudy>分页
     */
    private List<SpeechStudy> pageSpeechStudy(List<SpeechStudy> speechStudies,int page,int size){
        List<SpeechStudy> currentPageList = new ArrayList<>();
        if (speechStudies != null && speechStudies.size() > 0) {
            int currIdx = (page > 1 ? (page - 1) * size : 0);
            for (int i = 0; i < size && i < speechStudies.size() - currIdx; i++) {
                SpeechStudy data = speechStudies.get(currIdx + i);
                currentPageList.add(data);
            }
        }
        return currentPageList;
    }

    /**
     * SpeechArticle转换成json格式
     * @param currentPageList
     * @return
     */
    private JSONArray converSpeechStudy(List<SpeechStudy> currentPageList) {
        JSONArray jsonArray = new JSONArray();
        for (SpeechStudy speechStudy : currentPageList) {
            JSONObject json = JsonUtils.getJson(speechStudy);
            json.put("userName",speechStudy.getUser().getName());
            json.put("userId",speechStudy.getUser().getId());
            jsonArray.add(json);
        }
        return jsonArray;
    }
}
