package com.iyundao.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONReader;
import com.iyundao.base.BaseController;
import com.iyundao.base.utils.ExcelUtils;
import com.iyundao.base.utils.JsonResult;
import com.iyundao.base.utils.JsonUtils;
import com.iyundao.entity.*;
import com.iyundao.service.*;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: EvaluationController
 * @project: //todo
 * @author: 13620
 * @Date: 2019/8/3
 * @Description:  评价
 * @Version: V1.0
 */
@CrossOrigin
@RestController
@RequestMapping("/evaluation")
public class EvaluationController extends BaseController{

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private DepartService departService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private UserService userService;

    @Autowired
    private  IncidentService incidentService;

    @Autowired
    private EvaluationService evaluationService;

    @Autowired
    private SpeechService speechService;

    /**
    * @api {GET} /evaluation/downloadEvent 下载事件评价模板
    * @apiGroup Evaluation
    * @apiVersion 1.0.0
    * @apiHeader {String} IYunDao-AssessToken token验证
    * @apiDescription 下载事件评价模板
    * @apiParamExample {json} 请求样例:
    *                /evaluation/downloadEvent
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
    @GetMapping("/downloadEvent")
    public  JsonResult downloadEvent(HttpServletResponse resp){
        try {
            ExcelUtils.downloadWorkBook("事件评价",EvaluationEvent.class,resp);
        }catch (IOException e){
            e.printStackTrace();
        }
        return JsonResult.success();
    }


    /**
    * @api {POST} /evaluation/importEvent 导入事件评价
    * @apiGroup Evaluation
    * @apiVersion 1.0.0
    * @apiHeader {String} IYunDao-AssessToken token验证
    * @apiDescription 导入事件评价
    * @apiParamExample {json} 请求样例:
    *                /evaluation/importEvent
    * @apiSuccess (200) {String} code 200:成功</br>
    * @apiSuccess (200) {String} message 信息
    * @apiSuccess (200) {String} data 返回用户信息
    * @apiSuccessExample {json} 返回样例:
    * {
    *     "code": 200,
    *     "message": "成功",
    *     "data": [{"data": "11","beEvaluateUserCode": "11","departCode": "11","subjectCode": "11","content": "11","evaluateUserCode": "11","groupCode": "11"},]
    * }
    */
    @PostMapping("/importEvent")
    public JsonResult importEvent(MultipartFile file){
        List<EvaluationEvent> list = ExcelUtils.readExcel(file,EvaluationEvent.class);
        list = evaluationService.saveAllEvent(list);
        jsonResult.setData(converEvent(list));
        return jsonResult;
    }


    /**
     * @api {POST} /evaluation/listPageEvent 评价事件列表分页
     * @apiGroup Evaluation
     * @apiVersion 1.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 评价事件列表分页
     * @apiParam {int} page 查看的页数 默认1
     * @apiParam {int} size 每页的数量 默认10
     * @apiParamExample {json} 请求样例:
     *                /evaluation/listPageEvent?page=1&size=2
     * @apiSuccess (200) {String} code 200:成功</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *     "data": [{"data": "11","beEvaluateUserCode": "11","departCode": "11","subjectCode": "11","content": "11","evaluateUserCode": "11","groupCode": "11"},]
     * }
     */
    @PostMapping("/listPageEvent")
    public JsonResult listPageEvent(@RequestParam(defaultValue = "1")int page,
                                    @RequestParam(defaultValue = "10")int size){

        List<EvaluationEvent> evaluationEventList = evaluationService.findAllEvent();
        List<EvaluationEvent> currentPageList = pageEvent(evaluationEventList,page,size);
        jsonResult.setData(converEvent(currentPageList));
        return jsonResult;
    }

    /**
     * @api {POST} /evaluation/searchEvent 根据事件标题模糊查询事件列表分页
     * @apiGroup Evaluation
     * @apiVersion 1.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 根据事件标题模糊查询事件列表分页
     * @apiParam {String} likeIncidentTitle  模糊查询事件标题内容
     * @apiParam {int} page 查看的页数 默认1
     * @apiParam {int} size 每页的数量 默认10
     * @apiParamExample {json} 请求样例:
     *                /evaluation/searchEvent?page=1&size=10&likeIncidentTitle=事件
     * @apiSuccess (200) {String} code 200:成功</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *      "data": [{"data": "11","beEvaluateUserCode": "11","departCode": "11","incident": {"code": "004","dataTime": "发生时间","id": "4028d8816c562f32016c564c8a3a0003","title": "事件003","content": "事件正文"},"subjectCode": "11","content": "11","evaluateUserCode": "11","groupCode": "11"},{"data": "22","beEvaluateUserCode": "22","departCode": "22","incident": {"code": "001","dataTime": "发生时间","id": "4028d8816c562f32016c564c33f40000","title": "事件001","content": "事件正文"},"subjectCode": "22","content": "22","evaluateUserCode": "22","groupCode": "22"},{"data": "33","beEvaluateUserCode": "33","departCode": "33","incident": {"code": "003","dataTime": "发生时间","id": "4028d8816c562f32016c564c880f0002","title": "事件002","content": "事件正文"},"subjectCode": "33","content": "33","evaluateUserCode": "33","groupCode": "33"}]
     * }
     */
    @PostMapping("/searchEvent")
    public JsonResult searchEvent(String likeIncidentTitle,
                                  @RequestParam(defaultValue = "1")int page,
                                  @RequestParam(defaultValue = "10")int size){
        List<EvaluationEvent> evaluationEventList = evaluationService.findAllByLikeIncidentTitle(likeIncidentTitle);
        List<EvaluationEvent> currentPageList = pageEvent(evaluationEventList,page,size);
        jsonResult.setData(converEvent(currentPageList));
        return jsonResult;
    }


    /**
     * @api {GET} /evaluation/downloadOrganize 下载组织评价模板
     * @apiGroup Evaluation
     * @apiVersion 1.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 下载组织评价模板
     * @apiParamExample {json} 请求样例:
     *                /evaluation/downloadOrganize
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
    @GetMapping("/downloadOrganize")
    public  JsonResult downloadOrganize(HttpServletResponse resp){
        try {
            ExcelUtils.downloadWorkBook("组织评价",EvaluationOrganize.class,resp);
        }catch (IOException e){
            e.printStackTrace();
        }
        return JsonResult.success();
    }

    /**
     * @api {POST} /evaluation/importOrganize 导入组织评价
     * @apiGroup Evaluation
     * @apiVersion 1.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 导入组织评价
     * @apiParamExample {json} 请求样例:
     *                /evaluation/importOrganize
     * @apiSuccess (200) {String} code 200:成功</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *      "data": [{"score": 11,"timeHorizon": "11","data": "11","departCode": "11","subjectCode": "11","content": "11","userCode": "11","groupCode": "11"},{"score": 22,"timeHorizon": "22","data": "22","departCode": "22","subjectCode": "22","content": "22","userCode": "22","groupCode": "22"},]
     * }
     */
    @PostMapping("/importOrganize")
    public JsonResult importOrganize(MultipartFile file){
        List<EvaluationOrganize> list = ExcelUtils.readExcel(file,EvaluationOrganize.class);
        list = evaluationService.saveAllOrganize(list);
        jsonResult.setData(converOrganize(list));
        return jsonResult;
    }


    /**
     * @api {POST} /evaluation/listPageOrganize 组织评价列表分页
     * @apiGroup Evaluation
     * @apiVersion 1.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 组织评价列表分页
     * @apiParam {int} page 查看的页数 默认1
     * @apiParam {int} size 每页的数量 默认10
     * @apiParamExample {json} 请求样例:
     *                /evaluation/listPageOrganize?page=1&size=2
     * @apiSuccess (200) {String} code 200:成功</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *      "data": [{"score": 33,"timeHorizon": "33","data": "33","departCode": "33","subjectCode": "33","content": "33","userCode": "33","groupCode": "33"}]
     * }
     */
    @PostMapping("/listPageOrganize")
    public JsonResult listPageOrganize(@RequestParam(defaultValue = "1")int page,
                                       @RequestParam(defaultValue = "10")int size){
        List<EvaluationOrganize> evaluationOrganizeList = evaluationService.findAllOrganize();
        List<EvaluationOrganize> currentPageList = pageOrganize(evaluationOrganizeList,page,size);
        jsonResult.setData(converOrganize(currentPageList));
        return jsonResult;
    }



    /**
     * @api {POST} /evaluation/searchOrganize 根据名字模糊查询组织评价列表分页
     * @apiGroup Evaluation
     * @apiVersion 1.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 根据名字模糊查询组织评价列表分页
     * @apiParam {String} likeName  模糊查询正文
     * @apiParam {int} type  0-机构 1-部门 2-组织 默认0
     * @apiParam {int} page 查看的页数 默认1
     * @apiParam {int} size 每页的数量 默认10
     * @apiParamExample {json} 请求样例:
     *                /evaluation/searchOrganize?page=1&size=10&likeName=1&type=2
     * @apiSuccess (200) {String} code 200:成功</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *       "data": [{"score": 11,"timeHorizon": "11","data": "11","subject": {"code": "1","name": "测试机构1","id": "402881916b9d3031016b9d626593000c","subjectType": "part"},"depart": {"code": "1","name": "2321","id": "123"},"content": "11","userCode": "11","group": {"code": "0","name": "测试组织1","remark": "","id": "402881916b9d3031016b9d63a172000d"}}
     * }
     */
    @PostMapping("/searchOrganize")
    public JsonResult searchOrganize(String likeName,
                                     @RequestParam(defaultValue = "0")int type,
                                     @RequestParam(defaultValue = "1")int page,
                                     @RequestParam(defaultValue = "10")int size){
        List<EvaluationOrganize> eo =new ArrayList<>();
        switch (type){
            case 0:
                eo = evaluationService.findAllOrganizeBySubjectName(likeName);
                break;
            case 1:
                eo = evaluationService.findAllOrganizeByDepartName(likeName);
                break;
            case 2:
                eo = evaluationService.findAllOrganizeByGroupName(likeName);
                break;
            default:
                return JsonResult.paramError("类型输入有误");
        }
        List<EvaluationOrganize> currentPageList = pageOrganize(eo,page,size);
        jsonResult.setData(converOrganize(currentPageList));
        return jsonResult;
    }


    /**
     * @api {GET} /evaluation/downloadSelf 下载个人评价模板
     * @apiGroup Evaluation
     * @apiVersion 1.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 下载个人评价模板
     * @apiParamExample {json} 请求样例:
     *                /evaluation/downloadSelf
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
    @GetMapping("/downloadSelf")
    public  JsonResult downloadSelf(HttpServletResponse resp){
        try {
            ExcelUtils.downloadWorkBook("个人评价",EvaluationSelf.class,resp);
        }catch (IOException e){
            e.printStackTrace();
        }
        return JsonResult.success();
    }

    /**
     * @api {POST} /evaluation/importSelf 导入个人评价
     * @apiGroup Evaluation
     * @apiVersion 1.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 导入个人评价
     * @apiParamExample {json} 请求样例:
     *                /evaluation/importSelf
     * @apiSuccess (200) {String} code 200:成功</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *      "data": [{"score": 11,"timeHorizon": "11","data": "11","beEvaluateUserCode": "11","content": "11","evaluateUserCode": "11"},]
     * }
     */
    @PostMapping("/importSelf")
    public JsonResult importSelf(MultipartFile file){
        List<EvaluationSelf> list = ExcelUtils.readExcel(file,EvaluationSelf.class);
        list=evaluationService.saveAllSelf(list);
        jsonResult.setData(converSelf(list));
        return jsonResult;
    }



    /**
     * @api {POST} /evaluation/listPageSelf 个人评价列表分页
     * @apiGroup Evaluation
     * @apiVersion 1.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 个人评价列表分页
     * @apiParam {int} page 查看的页数 默认1
     * @apiParam {int} size 每页的数量 默认10
     * @apiParamExample {json} 请求样例:
     *               /evaluation/listPageOrganize?page=1&size=2
     * @apiSuccess (200) {String} code 200:成功</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *       "data": [{"score": 11,"timeHorizon": "11","data": "11","subject": {"code": "1","name": "测试机构1","id": "402881916b9d3031016b9d626593000c","subjectType": "part"},"depart": {"code": "1","name": "2321","id": "123"},"content": "11","userCode": "11","group": {"code": "0","name": "测试组织1","remark": "","id": "402881916b9d3031016b9d63a172000d"}}]
     * }
     */
    @PostMapping("/listPageSelf")
    public JsonResult listPageSelf(@RequestParam(defaultValue = "1")int page,
                                   @RequestParam(defaultValue = "10")int size){
        List<EvaluationSelf> evaluationSelfList = evaluationService.findAllSelf();
        List<EvaluationSelf> currentPageList = pageSelt(evaluationSelfList,page,size);
        jsonResult.setData(converSelf(currentPageList));
        return jsonResult;
    }


    /**
     * @api {POST} /evaluation/searchSelf 根据用户名模糊查询个人评价列表分页
     * @apiGroup Evaluation
     * @apiVersion 1.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 根据用户名模糊查询个人评价列表分页
     * @apiParam {String} likeName  模糊查询正文
     * @apiParam {int} page 查看的页数 默认1
     * @apiParam {int} size 每页的数量 默认10
     * @apiParamExample {json} 请求样例:
                   /evaluation/searchSelf?page=1&size=10&likeName=钱
     * @apiSuccess (200) {String} code 200:成功</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *      "data": [{"score": 11,"timeHorizon": "11","data": "11","beEvaluateUserCode": "11","evaluateUser": {"password": "6A36E430976A64EA","code": "001","salt": "45a1d914886d4a92b6835a181b2a20d8","sex": "0","name": "钱正","remark": "未填写","id": "402881916ba10b8a016ba113adbc0006","account": "user","status": "normal"},"content": "11"}]
     * }
     */
    @PostMapping("/searchSelf")
    public JsonResult searchSelf(String likeName,
                                 @RequestParam(defaultValue = "1")int page,
                                 @RequestParam(defaultValue = "10")int size){
        List<EvaluationSelf> evaluationSelfList = evaluationService.findAllSelfByUserName(likeName);
        List<EvaluationSelf> currentPageList = pageSelt(evaluationSelfList,page,size);
        jsonResult.setData(converSelf(currentPageList));
        return jsonResult;
    }

    /**
     * @api {GET} /evaluation/downloadSpeech 下载言论评价模板
     * @apiGroup Evaluation
     * @apiVersion 1.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 下载言论评价模板
     * @apiParamExample {json} 请求样例:
     *                /evaluation/downloadSpeech
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
    @GetMapping("/downloadSpeech")
    public  JsonResult downloadSpeech(HttpServletResponse resp){
        try {
            ExcelUtils.downloadWorkBook("言论评价",EvaluationSpeech.class,resp);
        }catch (IOException e){
            e.printStackTrace();
        }
        return JsonResult.success();
    }


    /**
     * @api {POST} /evaluation/importSpeech 导入言论评价
     * @apiGroup Evaluation
     * @apiVersion 1.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 导入言论评价
     * @apiParamExample {json} 请求样例:
     *                /evaluation/importSpeech
     * @apiSuccess (200) {String} code 200:成功</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *       "data": [{"speechStudyCode": "11","data": "11","speechDiscussionCode": "11","content": "11","userCode": "11","speechArticleCode": "11"}]
     * }
     */
    @PostMapping("/importSpeech")
    public JsonResult importSpeech(MultipartFile file){
        List<EvaluationSpeech> list = ExcelUtils.readExcel(file,EvaluationSpeech.class);
       list = evaluationService.saveAllSpeech(list);
       jsonResult.setData(converSpeech(list));
        return jsonResult;
    }



    /**
     * @api {POST} /evaluation/listPageSpeech 言论评价列表分页
     * @apiGroup Evaluation
     * @apiVersion 1.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 言论评价列表分页
     * @apiParam {int} page 查看的页数 默认1
     * @apiParam {int} size 每页的数量 默认10
     * @apiParamExample {json} 请求样例:
     *                /evaluation/listPageSpeech?page=1&size=2
     * @apiSuccess (200) {String} code 200:成功</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *      "data": [{"speechStudyCode": "11","data": "11","speechDiscussionCode": "11","content": "11","userCode": "11","speechArticleCode": "11"},{"speechStudyCode": "22","data": "22","speechDiscussionCode": "22","content": "22","userCode": "22","speechArticleCode": "22"}]
     * }
     */
    @PostMapping("/listPageSpeech")
    public JsonResult listPageSpeech(@RequestParam(defaultValue = "1")int page,
                                     @RequestParam(defaultValue = "10")int size){
        List<EvaluationSpeech> evaluationSpeechList = evaluationService.findAllSpeech();
        List<EvaluationSpeech> currentPageList = pageEvaluationSpeech(evaluationSpeechList,page,size);
        jsonResult.setData(converSpeech(currentPageList));
        return jsonResult;
    }


    /**
     * @api {POST} /evaluation/searchSpeech 根据标题模糊查询言论评价列表分页
     * @apiGroup Evaluation
     * @apiVersion 1.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 根据标题模糊查询言论评价列表分页
     * @apiParam {String} likeName  模糊查询正文
     * @apiParam {int} type  0-文章 1-参与讨论信息 2-理论研究 默认0
     * @apiParam {int} page 查看的页数 默认1
     * @apiParam {int} size 每页的数量 默认10
     * @apiParamExample {json} 请求样例:
     *                /evaluation/searchSpeech?page=1&size=10&likeName=11&type=4
     * @apiSuccess (200) {String} code 200:成功</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *        "data": [{"data": "11","speechStudy": {"result": "结果","code": "001","startTime": "2018.12.12 12:12:12","id": "4028d8816c551b7e016c5527f4bb0002","state": "notStarted","endTime": "2018.12.12 12:12:12","title": "标题11","content": "正文"},"speechDiscussion": {"code": "001","id": "","time": "","title": "标题11","content": ""},"content": "11","userCode": "11","speechArticle": {"code": "001","id": "4028d8816c51747c016c519f6eb70004","time": "2018.12.12 12:12:12","sort": "1","title": "标题11","url": "路径","content": "正文"}}]
     * }
     */
    @PostMapping("/searchSpeech")
    public JsonResult searchSpeech(String likeName,
                                   @RequestParam(defaultValue = "0")int type,
                                   @RequestParam(defaultValue = "1")int page,
                                   @RequestParam(defaultValue = "10")int size){
        List<EvaluationSpeech> es = new ArrayList<>();
        switch (type){
            case 0:
                es = evaluationService.findAllSpeechByArticleName(likeName);
                break;
            case 1:
                es = evaluationService.findAllSpeechByDiscussionName(likeName);
                break;
            case 2:
                es = evaluationService.findAllSpeechByStudyName(likeName);
                break;
            default:
                return JsonResult.paramError("类型只能选择0,1,2");
        }
        List<EvaluationSpeech> currentPageList = pageEvaluationSpeech(es,page,size);
        jsonResult.setData(converSpeech(currentPageList));
        return jsonResult;
    }


    /**
     * EvaluationSpeech转换成json格式
     * @param list
     * @return
     */
    private JSONArray converEvent(List<EvaluationEvent> list) {
        JSONArray arr = new JSONArray();
        for (EvaluationEvent ee : list) {
            JSONObject obj = new JSONObject();
            obj.put("content",ee.getContent());
            obj.put("data",ee.getData());
            //如果找到对象赋值对象，没有赋值code
            if (incidentService.findByCode(ee.getIncidentCode())!=null){
                obj.put("incident",JsonUtils.getJson(incidentService.findByCode(ee.getIncidentCode())));
            }else{
                obj.put("incidentCode",ee.getIncidentCode());
            }
            if (subjectService.findByCode(ee.getSubjectCode())!=null){
                obj.put("subject",JsonUtils.getJson(subjectService.findByCode(ee.getSubjectCode())));
            }else {
                obj.put("subjectCode",ee.getSubjectCode());
            }
            if (departService.findByCode(ee.getDepartCode())!=null){
                obj.put("depart",JsonUtils.getJson(departService.findByCode(ee.getDepartCode())));
            }else {
                obj.put("departCode",ee.getDepartCode());
            }
            if (groupService.findByCode(ee.getGroupCode())!=null){
                obj.put("group",JsonUtils.getJson(groupService.findByCode(ee.getGroupCode())));
            }else {
                obj.put("groupCode",ee.getGroupCode());
            }
            if (userService.findByCode(ee.getEvaluateUserCode())!=null){
                obj.put("evaluateUser",JsonUtils.getJson(userService.findByCode(ee.getEvaluateUserCode())));
            }else {
                obj.put("evaluateUserCode",ee.getEvaluateUserCode());
            }
            if (userService.findByCode(ee.getBeEvaluateUserCode())!=null){
                obj.put("beEvaluateUser",JsonUtils.getJson(userService.findByCode(ee.getBeEvaluateUserCode())));
            }else {
                obj.put("beEvaluateUserCode",ee.getBeEvaluateUserCode());
            }
            arr.add(obj);
        }
        return arr;
    }

    /**
     *  List<EvaluationEvent>分页
     */
    private List<EvaluationEvent> pageEvent(List<EvaluationEvent> evaluationEventList, int page, int size) {
        List<EvaluationEvent> currentPageList = new ArrayList<>();
        if (evaluationEventList != null && evaluationEventList.size() > 0) {
            int currIdx = (page > 1 ? (page - 1) * size : 0);
            for (int i = 0; i < size && i < evaluationEventList.size() - currIdx; i++) {
                EvaluationEvent data = evaluationEventList.get(currIdx + i);
                currentPageList.add(data);
            }
        }
        return currentPageList;
    }

    /**
     * EvaluationOrganize转换成json格式
     * @param list
     * @return
     */
    private JSONArray converOrganize(List<EvaluationOrganize> list) {
        JSONArray arr = new JSONArray();
        for (EvaluationOrganize eo : list) {
            JSONObject obj =  new JSONObject();
            obj.put("content",eo.getContent());
            obj.put("data",eo.getData());
            obj.put("timeHorizon",eo.getTimeHorizon());
            obj.put("score",eo.getScore());
            if (subjectService.findByCode(eo.getSubjectCode())!=null){
                obj.put("subject",JsonUtils.getJson(subjectService.findByCode(eo.getSubjectCode())));
            }else {
                obj.put("subjectCode",eo.getSubjectCode());
            }
            if (departService.findByCode(eo.getDepartCode())!=null){
                obj.put("depart",JsonUtils.getJson(departService.findByCode(eo.getDepartCode())));
            }else {
                obj.put("departCode",eo.getDepartCode());
            }
            if (groupService.findByCode(eo.getGroupCode())!=null){
                obj.put("group",JsonUtils.getJson(groupService.findByCode(eo.getGroupCode())));
            }else {
                obj.put("groupCode",eo.getGroupCode());
            }
            if (userService.findByCode(eo.getUserCode())!=null){
                obj.put("user",JsonUtils.getJson(userService.findByCode(eo.getUserCode())));
            }else {
                obj.put("userCode",eo.getUserCode());
            }
            arr.add(obj);
        }
        return arr;
    }


    /**
     *  List<EvaluationOrganize>分页
     */
    private List<EvaluationOrganize> pageOrganize(List<EvaluationOrganize> evaluationOrganizeList, int page, int size) {
        List<EvaluationOrganize> currentPageList = new ArrayList<>();
        if (evaluationOrganizeList != null && evaluationOrganizeList.size() > 0) {
            int currIdx = (page > 1 ? (page - 1) * size : 0);
            for (int i = 0; i < size && i < evaluationOrganizeList.size() - currIdx; i++) {
                EvaluationOrganize data = evaluationOrganizeList.get(currIdx + i);
                currentPageList.add(data);
            }
        }
        return currentPageList;
    }
    /**
     * EvaluationSelf转换成json格式
     * @param list
     * @return
     */
    private JSONArray converSelf(List<EvaluationSelf> list) {
        JSONArray arr =new JSONArray();
        for (EvaluationSelf es : list) {
            JSONObject obj = new JSONObject();
            obj.put("content",es.getContent());
            obj.put("data",es.getData());
            obj.put("timeHorizon",es.getTimeHorizon());
            obj.put("score",es.getScore());
            if (userService.findByCode(es.getEvaluateUserCode())!=null){
                obj.put("evaluateUser",JsonUtils.getJson(userService.findByCode(es.getEvaluateUserCode())));
            }else {
                obj.put("evaluateUserCode",es.getEvaluateUserCode());
            }
            if (userService.findByCode(es.getBeEvaluateUserCode())!=null){
                obj.put("beEvaluateUser",JsonUtils.getJson(userService.findByCode(es.getBeEvaluateUserCode())));
            }else {
                obj.put("beEvaluateUserCode",es.getBeEvaluateUserCode());
            }
            arr.add(obj);
        }
        return arr;
    }

    /**
     *  List<EvaluationSelf>分页
     */
    private List<EvaluationSelf> pageSelt(List<EvaluationSelf> evaluationSelfList, int page, int size) {
        List<EvaluationSelf> currentPageList = new ArrayList<>();
        if (evaluationSelfList != null && evaluationSelfList.size() > 0) {
            int currIdx = (page > 1 ? (page - 1) * size : 0);
            for (int i = 0; i < size && i < evaluationSelfList.size() - currIdx; i++) {
                EvaluationSelf data = evaluationSelfList.get(currIdx + i);
                currentPageList.add(data);
            }
        }
        return currentPageList;
    }


    /**
     * EvaluationSpeech转换成json格式
     * @param list
     * @return
     */
    private JSONArray converSpeech(List<EvaluationSpeech> list) {
        JSONArray arr = new JSONArray();
        for (EvaluationSpeech es : list) {
            JSONObject obj = new JSONObject();
            obj.put("content",es.getContent());
            obj.put("data",es.getData());
            if (userService.findByCode(es.getUserCode())!=null){
                obj.put("user",JsonUtils.getJson(userService.findByCode(es.getUserCode())));
            }else {
                obj.put("userCode",es.getUserCode());
            }
            if (speechService.findByArticleCode(es.getSpeechArticleCode())!=null){
                obj.put("speechArticle",JsonUtils.getJson(speechService.findByArticleCode(es.getSpeechArticleCode())));
            }else {
                obj.put("speechArticleCode",es.getSpeechArticleCode());
            }
            if (speechService.findByDiscussionCode(es.getSpeechDiscussionCode())!=null){
                obj.put("speechDiscussion",JsonUtils.getJson(speechService.findByDiscussionCode(es.getSpeechDiscussionCode())));
            }else {
                obj.put("speechDiscussionCode",es.getSpeechDiscussionCode());
            }
            if (speechService.findByStudyCode(es.getSpeechStudyCode())!=null){
                obj.put("speechStudy",JsonUtils.getJson(speechService.findByStudyCode(es.getSpeechStudyCode())));
            }else {
                obj.put("speechStudyCode",es.getSpeechStudyCode());
            }
            arr.add(obj);
        }
        return arr;
    }




    /**
     *  List<EvaluationSpeech>分页
     */
    private List<EvaluationSpeech> pageEvaluationSpeech(List<EvaluationSpeech> evaluationSpeechList, int page, int size){
        List<EvaluationSpeech> currentPageList = new ArrayList<>();
        if (evaluationSpeechList != null && evaluationSpeechList.size() > 0) {
            int currIdx = (page > 1 ? (page - 1) * size : 0);
            for (int i = 0; i < size && i < evaluationSpeechList.size() - currIdx; i++) {
                EvaluationSpeech data = evaluationSpeechList.get(currIdx + i);
                currentPageList.add(data);
            }
        }
        return currentPageList;
    }


}
