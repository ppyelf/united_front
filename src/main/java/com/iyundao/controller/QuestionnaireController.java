package com.iyundao.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONReader;
import com.iyundao.base.BaseController;
import com.iyundao.base.utils.JsonResult;
import com.iyundao.base.utils.JsonUtils;
import com.iyundao.entity.*;
import com.iyundao.service.QuestionnaireService;
import com.iyundao.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.net.idn.Punycode;

import javax.persistence.Entity;
import javax.persistence.Query;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: QuestionnaireController
 * @project:
 * @author: 13620
 * @Date: 2019/8/7
 * @Description: 问卷调查
 * @Version: V1.0
 */
@RestController
@RequestMapping("/questionnaire")
public class QuestionnaireController extends BaseController{

    @Autowired
    private QuestionnaireService questionnaireService;

    @Autowired
    private UserService userService;

   /**
   * @api {POST} /questionnaire/addQuestionnaire 添加问卷
   * @apiGroup Questionnaire
   * @apiVersion 1.0.0
   * @apiHeader {String} IYunDao-AssessToken token验证
   * @apiDescription 添加问卷
   * @apiParam jsong格式 {"title":"标题","content":"描述","titleAnswer":[{"title":"题目1","answerAll":[{"content":"选择1","istrue":"1"},{"content":"选择2","istrue":"2"}],"score":"5"},{"title":"题目2","answerAll":[{"content":"选择1","istrue":"1"},{"content":"选择2","istrue":"2"}],"score":"5"},{"title":"题目3","answerAll":[{"content":"选择1","istrue":"1"},{"content":"选择2","istrue":"2"}],"score":"5"}]}
   * @apiParamExample {json} 请求样例:
   *                /questionnaire/addQuestionnaire
   * @apiSuccess (200) {String} code 200:成功</br>
   * @apiSuccess (200) {String} message 信息
   * @apiSuccess (200) {String} data 返回用户信息
   * @apiSuccessExample {json} 返回样例:
   * {
   *     "code": 200,
   *     "message": "成功",
   *      "data": {"id": "4028d8816c6ebcc1016c6ec2c9270013","title": "标题","content": "描述"}
   * }
   */
    @PostMapping("/addQuestionnaire")
    public JsonResult addQuestionnaire(@RequestBody JSONObject param){
        String title = (String) param.get("title");
        String content = (String)param.get("content");
        List<Map<String,Object>> titleAnswer = (List<Map<String,Object>>)param.get("titleAnswer");
        Questionnaire questionnaire = questionnaireService.saveQuestionnaire(title,content,titleAnswer);
        jsonResult.setData(JsonUtils.getJson(questionnaire));
        return jsonResult;
    }



    /**
     * @api {POST} /questionnaire/listPgeQuestionnaire 问卷列表
     * @apiGroup Questionnaire
     * @apiVersion 1.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 问卷列表
     * @apiParam {int} page 查看的页数 默认1
     * @apiParam {int} size 每页的数量 默认10
     * @apiParamExample {json} 请求样例:
     *                /questionnaire/listPgeQuestionnaire
     * @apiSuccess (200) {String} code 200:成功</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *       "data": {"total": 3,"size": 2,"page": 1,"content": [{"id": "4028d8816c6ebcc1016c6ed381d90031","title": "标题","content": "描述"},{"id": "4028d8816c6ebcc1016c6ede4d28004f","title": "标题","content": "描述"}]}
     * }
     */
    @PostMapping("/listPgeQuestionnaire")
    public JsonResult listPgeQuestionnaire(@RequestParam(defaultValue = "1")int page,
                                           @RequestParam(defaultValue = "10")int size){
        List<Questionnaire> questionnaires = questionnaireService.findAll();
        List<Questionnaire> currentPageList = pageQuestionnaire(questionnaires,page,size);
        JSONObject obj = new JSONObject();
        obj.put("page",page);
        obj.put("size",size);
        obj.put("total",questionnaires.size());
        obj.put("content",converQuestionnaire(currentPageList));
        jsonResult.setData(obj);
        return jsonResult;
    }


    /**
     * @api {POST} /questionnaire/viewQuestionnaire 查看问卷详情
     * @apiGroup Questionnaire
     * @apiVersion 1.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 查看问卷详情
     * @apiParam {String} questionnaireId  问卷id
     * @apiParamExample {json} 请求样例:
     *                /questionnaire/viewQuestionnaire?questionnaireId=4028d8816c6ebcc1016c6ede4d28004f
     * @apiSuccess (200) {String} code 200:成功</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *          "data": {"titleAnswer": [{"score": "5","answerAll": [{"answer": {"id": "4028d8816c6ebcc1016c6ede4d340051","content": "选择1","istrue": "0"}},{"answer": {"id": "4028d8816c6ebcc1016c6ede4d350052","content": "选择2","istrue": "1"}}],"title": "题目1"},{"score": "5","answerAll": [{"answer": {"id": "4028d8816c6ebcc1016c6ede4d350054","content": "选择1","istrue": "0"}},{"answer": {"id": "4028d8816c6ebcc1016c6ede4d350055","content": "选择2","istrue": "1"}}],"title": "题目2"},{"score": "5","answerAll": [{"answer": {"id": "4028d8816c6ebcc1016c6ede4d360057","content": "选择1","istrue": "0"}},{"answer": {"id": "4028d8816c6ebcc1016c6ede4d360058","content": "选择2","istrue": "1"}}],"title": "题目3"}],"questionnaire": {"id": "4028d8816c6ebcc1016c6ede4d28004f","title": "标题","content": "描述"}}
     * }
     */
    @PostMapping("/viewQuestionnaire")
    public JsonResult viewQuestionnaire(String questionnaireId){
        Questionnaire questionnaire = questionnaireService.findById(questionnaireId);
        if (questionnaire == null){
            return JsonResult.notFound("找不到实体");
        }
       List<QuestionnaireTitle> titles = questionnaireService.findTitleByQuestionnaire(questionnaire);
        if (CollectionUtils.isEmpty(titles)){
            jsonResult.setData(titles);
            return jsonResult;
        }
       JSONObject object =  questionnaireService.findView(questionnaire,titles);
        jsonResult.setData(object);
        return jsonResult;
    }


    /**
     * @api {POST} /questionnaire/delQuestionnaire 删除问卷
     * @apiGroup Questionnaire
     * @apiVersion 1.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 删除问卷
     * @apiParam {String[]} questionnaireIds 问卷调查id
     * @apiParamExample {json} 请求样例:
     *                /questionnaire/delQuestionnaire?questionnaireIds=4028d8816c6ebcc1016c6ed386de0045,4028d8816c6ebcc1016c6ed38466003b
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
    @PostMapping("/delQuestionnaire")
    public JsonResult delQuestionnaire(String[] questionnaireIds){
        List<Questionnaire> questionnaire = questionnaireService.findByIds(questionnaireIds);
       if (CollectionUtils.isEmpty(questionnaire)){
           return JsonResult.notFound("找不到实体");
       }
        questionnaireService.delete(questionnaire);
        return JsonResult.success();
    }


    /**
     * @api {POST} /questionnaire/handQuestionnaire 上交问卷
     * @apiGroup Questionnaire
     * @apiVersion 1.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 上交问卷
     * @apiParam jsong格式  {"questionnaireId":"4028d8816c6ebcc1016c6ee6b0160063","userId":"402881916ba10b8a016ba113adbc0006","answerAll":[{"titleId":"4028d8816c6ebcc1016c6ee6b01c0064","answerId":"4028d8816c6ebcc1016c6ee6b0240065"},{"titleId":"4028d8816c6ebcc1016c6ee6b0260067","answerId":"4028d8816c6ebcc1016c6ee6b0270069"},{"titleId":"4028d8816c6ebcc1016c6ee6b027006a","answerId":"4028d8816c6ebcc1016c6ee6b028006c"}]}
     * @apiParamExample {json} 请求样例:
     *               /questionnaire/handQuestionnaire
     * @apiSuccess (200) {String} code 200:成功</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *      "data": {"score": "10","id": "4028d8816c6f4cc2016c6fe6eeb60009"}
     * }
     */
    @PostMapping("/handQuestionnaire")
    public JsonResult handQuestionnaire(@RequestBody JSONObject param){
        String questionnaireId = (String) param.get("questionnaireId");
        Questionnaire questionnaire = questionnaireService.findById(questionnaireId);
        if (questionnaire == null){
            return JsonResult.notFound("找不到问卷调查实体");
        }
        String userId = (String)param.get("userId");
        User user = userService.findById(userId);
        if (user ==null){
            return JsonResult.notFound("找不到用户实体");
        }
        List<Map<String,String>> answerAll =(List<Map<String,String>>)param.get("answerAll");

        QuestionnaireScore questionnaireScore = questionnaireService.saveQuestionnaireUser(questionnaire, user, answerAll);
        jsonResult.setData(JsonUtils.getJson(questionnaireScore));
        return jsonResult;
    }


    /**
     * @api {POST} /questionnaire/findScoreById 根据调查问卷查询分数
     * @apiGroup Questionnaire
     * @apiVersion 1.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 根据调查问卷查询分数
     * @apiParam {String} questionnaireId  调查问卷id
     * @apiParam {int} page 查看的页数 默认1
     * @apiParam {int} size 每页的数量 默认10
     * @apiParamExample {json} 请求样例:
     *               /questionnaire/findScoreById?questionnaireId=4028d8816c6ebcc1016c6ee6b0160063
     * @apiSuccess (200) {String} code 200:成功</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *       "data": {"total": 2,"size": 10,"page": 1,"content": [{"score": "10","questionnaireTitle": "标题","questionnaireId": "4028d8816c6ebcc1016c6ee6b0160063","userName": "钱正","userId": "402881916ba10b8a016ba113adbc0006"},{"score": "10","questionnaireTitle": "标题","questionnaireId": "4028d8816c6ebcc1016c6ee6b0160063","userName": "钱正","userId": "402881916ba10b8a016ba113adbc0006"}]}
     * }
     */
    @PostMapping("/findScoreById")
    public JsonResult findScoreById(String questionnaireId,
                                    @RequestParam(defaultValue = "1")int page,
                                    @RequestParam(defaultValue = "10")int size){
        Questionnaire questionnaire = questionnaireService.findById(questionnaireId);
        if (questionnaire == null){
            return JsonResult.notFound("没有找到实体");
        }
        List<QuestionnaireScore> questionnaireScoreList = questionnaireService.findScoreByQuestionnaire(questionnaire);
        if (CollectionUtils.isEmpty(questionnaireScoreList)){
            return JsonResult.notFound("没有找到分数实体");
        }
        List<QuestionnaireScore> currentPageList = pageQuestionnaireScore(questionnaireScoreList,page,size);
        JSONObject obj = new JSONObject();
        obj.put("page",page);
        obj.put("size",size);
        obj.put("total",questionnaireScoreList.size());
        obj.put("content",converQuestionnaireScore(currentPageList));
        jsonResult.setData(obj);
        return jsonResult;
    }



    /**
     * @api {POST} /questionnaire/findScoreByUserId 根据用户id查询分数
     * @apiGroup Questionnaire
     * @apiVersion 1.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 根据用户id查询分数
     * @apiParam {String} userId  用户id
     * @apiParam {int} page 查看的页数 默认1
     * @apiParam {int} size 每页的数量 默认10
     * @apiParamExample {json} 请求样例:
     *              /questionnaire/findScoreByUserId?userId=402881916ba10b8a016ba113adbc0006
     * @apiSuccess (200) {String} code 200:成功</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *      "data": {"total": 2,"size": 10,"page": 1,"content": [{"score": "10","questionnaireTitle": "标题","questionnaireId": "4028d8816c6ebcc1016c6ee6b0160063","userName": "钱正","userId": "402881916ba10b8a016ba113adbc0006"},{"score": "10","questionnaireTitle": "标题","questionnaireId": "4028d8816c6ebcc1016c6ee6b0160063","userName": "钱正","userId": "402881916ba10b8a016ba113adbc0006"}]}
     * }
     */
    @PostMapping("/findScoreByUserId")
    public JsonResult findScoreByUserId(String userId,
                                        @RequestParam(defaultValue = "1")int page,
                                        @RequestParam(defaultValue = "10")int size){
            User user = userService.findById(userId);
            if (user ==null){
                return JsonResult.notFound("找不到用户实体");
            }
            List<QuestionnaireScore> questionnaireScores = questionnaireService.findScoreByUser(user);
        List<QuestionnaireScore> currentPageList = pageQuestionnaireScore(questionnaireScores,page,size);
        JSONObject obj = new JSONObject();
        obj.put("page",page);
        obj.put("size",size);
        obj.put("total",questionnaireScores.size());
        obj.put("content",converQuestionnaireScore(currentPageList));
        jsonResult.setData(obj);
        return jsonResult;

    }


    /**
     * QuestionnaireScore转换成Json
     * @param currentPageList
     * @return
     */
    private JSONArray converQuestionnaireScore(List<QuestionnaireScore> currentPageList) {
            JSONArray arr =  new JSONArray();
        for (QuestionnaireScore score : currentPageList) {
            JSONObject object = new JSONObject();
            object.put("userId",score.getUser().getId());
            object.put("userName",score.getUser().getName());
            object.put("questionnaireId",score.getQuestionnaire().getId());
            object.put("questionnaireTitle",score.getQuestionnaire().getTitle());
            object.put("score",score.getScore());
            arr.add(object);
        }
        return arr;
    }


    /**
     * QuestionnaireScore 分页
     */
    private List<QuestionnaireScore> pageQuestionnaireScore(List<QuestionnaireScore> questionnaireScoreList, int page, int size) {
        List<QuestionnaireScore> currentPageList = new ArrayList<>();
        if (questionnaireScoreList != null && questionnaireScoreList.size() > 0) {
            int currIdx = (page > 1 ? (page - 1) * size : 0);
            for (int i = 0; i < size && i < questionnaireScoreList.size() - currIdx; i++) {
                QuestionnaireScore data = questionnaireScoreList.get(currIdx + i);
                currentPageList.add(data);
            }
        }
        return currentPageList;
    }



    /**
     * Questionnaire转换成Json
     * @param currentPageList
     * @return
     */
    private JSONArray converQuestionnaire(List<Questionnaire> currentPageList) {
        JSONArray arr = new JSONArray();
        for (Questionnaire questionnaire : currentPageList) {
            arr.add(JsonUtils.getJson(questionnaire));
        }
        return arr;
    }



    /**
     * Questionnaire分页
     * @param questionnaires
     * @param page
     * @param size
     * @return
     */
    private List<Questionnaire> pageQuestionnaire(List<Questionnaire> questionnaires, int page, int size) {
        List<Questionnaire> currentPageList = new ArrayList<>();
        if (questionnaires != null && questionnaires.size() > 0) {
            int currIdx = (page > 1 ? (page - 1) * size : 0);
            for (int i = 0; i < size && i < questionnaires.size() - currIdx; i++) {
                Questionnaire data = questionnaires.get(currIdx + i);
                currentPageList.add(data);
            }
        }
        return currentPageList;
    }

}
