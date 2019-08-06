package com.iyundao.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.iyundao.base.BaseController;
import com.iyundao.base.utils.JsonResult;
import com.iyundao.base.utils.JsonUtils;
import com.iyundao.entity.*;
import com.iyundao.service.ActivityService;
import com.iyundao.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: ActivityRecordController
 * @project:
 * @author: 13620
 * @Date: 2019/8/6
 * @Description:    活动现场记录
 * @Version: V1.0
 */
@RestController
@RequestMapping("/activityRecord")
public class ActivityRecordController  extends BaseController{

    @Autowired
    private ActivityService activityService;

    @Autowired
    private UserService userService;

    /**
    * @api {POST} /activityRecord/uploadFrequency  上传音频文件
    * @apiGroup ActivityRecord
    * @apiVersion 1.0.0
    * @apiHeader {String} IYunDao-AssessToken token验证
    * @apiDescription 上传音频文件
    * @apiParam {MultipartFile} file  音频文件
    * @apiParam {String} userId  用户id
    * @apiParam {String} activityId  活动id
    * @apiParamExample {json} 请求样例:
    *                /activityRecord/uploadFrequency
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
    @PostMapping("/uploadFrequency")
    public JsonResult uploadFrequency(MultipartFile file,
                                      String userId,
                                      String activityId){
        Activity activity = activityService.find(activityId);
        if (activity == null){
            return JsonResult.notFound("找不到活动");
        }
        //这里不做判断没有用户就当匿名
        User user = userService.findById(userId);
       jsonResult = activityService.saveFrequency(file,user,activity);
        return jsonResult;
    }


    /**
     * @api {POST} /activityRecord/listPageFrequency  音频文件列表
     * @apiGroup ActivityRecord
     * @apiVersion 1.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 音频文件列表
     * @apiParam {int} page 查看的页数 默认1
     * @apiParam {int} size 每页的数量 默认10
     * @apiParamExample {json} 请求样例:
     *                /activityRecord/listPageFrequency
     * @apiSuccess (200) {String} code 200:成功</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *       "message": "成功",
     *           "data": {"total": 11,"size": 10,"page": 1,"content": [{"activityId": "888888878","name": "2ca659ba87f74a2da4aa64b9ad442bca","activityName": "11","suffix": "png","userName": "","userId": "","url": "activityfrequency\\2ca659ba87f74a2da4aa64b9ad442bca.png"}]}
     * }
     */
    @PostMapping("/listPageFrequency")
    public JsonResult listPageFrequency(@RequestParam(defaultValue = "1")int page,
                                        @RequestParam(defaultValue = "10")int size){
        List<ActivityFrequency> activityFrequencyList = activityService.findAllFrequency();
        List<ActivityFrequency> currentPageList = pageFrequency(activityFrequencyList,page,size);
        JSONObject obj = new JSONObject();
        obj.put("page",page);
        obj.put("size",size);
        obj.put("total",activityFrequencyList.size());
        obj.put("content",converFrequency(currentPageList));
        jsonResult.setData(obj);
        return jsonResult;
    }




    /**
     * @api {POST} /activityRecord/listPageFrequencyByActivityId  根据活动id找到音频文件列表
     * @apiGroup ActivityRecord
     * @apiVersion 1.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 根据活动id找到音频文件列表
     * @apiParam {String} activityId  活动id
     * @apiParam {int} page 查看的页数 默认1
     * @apiParam {int} size 每页的数量 默认10
     * @apiParamExample {json} 请求样例:
     *                activityRecord/listPageFrequencyByActivityId?activityId=888888878
     * @apiSuccess (200) {String} code 200:成功</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *       "data": {"total": 11,"size": 10,"page": 1,"content": [{"activityId": "888888878","name": "2ca659ba87f74a2da4aa64b9ad442bca","activityName": "11","suffix": "png","userName": "","userId": "","url": "activityfrequency\\2ca659ba87f74a2da4aa64b9ad442bca.png"}]}
     * }
     */
    @PostMapping("/listPageFrequencyByActivityId")
    public JsonResult listPageFrequencyByActivityId(String activityId,
                                                    @RequestParam(defaultValue = "1")int page,
                                                    @RequestParam(defaultValue = "10")int size){
        Activity activity = activityService.find(activityId);
        if (activity==null){
            return  JsonResult.notFound("没有找到活动实体");
        }
        List<ActivityFrequency> activityFrequencyList = activityService.findAllFrequencyByActivity(activity);
        List<ActivityFrequency> currentPageList = pageFrequency(activityFrequencyList,page,size);
        JSONObject obj = new JSONObject();
        obj.put("page",page);
        obj.put("size",size);
        obj.put("total",activityFrequencyList.size());
        obj.put("content",converFrequency(currentPageList));
        jsonResult.setData(obj);
        return jsonResult;
    }

    /**
     * @api {POST} /activityRecord/delFrequency  根据音频ids找删除音频
     * @apiGroup ActivityRecord
     * @apiVersion 1.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 根据音频ids找删除音频
     * @apiParam {String[]} frequencyIds 音频id
     * @apiParamExample {json} 请求样例:
     *                activityRecord/listPageFrequencyByActivityId?activityId=888888878
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
    @PostMapping("/delFrequency")
    public JsonResult delFrequency(String[] frequencyIds){
           List<ActivityFrequency> af = activityService.findAllFrequencyByIds(frequencyIds);
           if (CollectionUtils.isEmpty(af)){
               return JsonResult.notFound("没有找到实体");
           }
           activityService.deleteFrequencys(af);
           return JsonResult.success();
    }



    /**
     * @api {POST} /activityRecord/uploadImage  上传图片
     * @apiGroup ActivityRecord
     * @apiVersion 1.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 上传图片
     * @apiParam {MultipartFile} file  图片
     * @apiParam {String} userId  用户id
     * @apiParam {String} activityId  活动id
     * @apiParamExample {json} 请求样例:
     *                /activityRecord/uploadImage
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
    @PostMapping("/uploadImage")
    public JsonResult uploadImage(MultipartFile file,
                                      String userId,
                                      String activityId){
        Activity activity = activityService.find(activityId);
        if (activity == null){
            return JsonResult.notFound("找不到活动");
        }
        //这里不做判断没有用户就当匿名
        User user = userService.findById(userId);
        jsonResult = activityService.saveImageWang(file,user,activity);
        return jsonResult;
    }


    /**
     * @api {POST} /activityRecord/listPageImage  图片文件列表
     * @apiGroup ActivityRecord
     * @apiVersion 1.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 图片文件列表
     * @apiParam {int} page 查看的页数 默认1
     * @apiParam {int} size 每页的数量 默认10
     * @apiParamExample {json} 请求样例:
     *                /activityRecord/listPageImage
     * @apiSuccess (200) {String} code 200:成功</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *        "data": {"total": 2,"size": 2,"page": 1,"content": [{"activityId": "888888878","name": "21fd1c4a50074fb78089964813cf368f","activityName": "11","suffix": "png","userName": "","userId": "","url": "activityimage\\21fd1c4a50074fb78089964813cf368f.png"}]}
     * }
     */
    @PostMapping("/listPageImage")
    public JsonResult listPageImage(@RequestParam(defaultValue = "1")int page,
                                    @RequestParam(defaultValue = "10")int size){
        List<ActivityImage> activityImageList = activityService.findAllImage();
        List<ActivityImage> currentPageList = pageImage(activityImageList,page,size);
        JSONObject obj = new JSONObject();
        obj.put("page",page);
        obj.put("size",size);
        obj.put("total",activityImageList.size());
        obj.put("content",converImage(currentPageList));
        jsonResult.setData(obj);
        return jsonResult;
    }


    /**
     * @api {POST} /activityRecord/listPageImageByActivityId  根据活动id找到图片文件列表
     * @apiGroup ActivityRecord
     * @apiVersion 1.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 根据活动id找到图片文件列表
     * @apiParam {int} page 查看的页数 默认1
     * @apiParam {int} size 每页的数量 默认10
     * @apiParamExample {json} 请求样例:
                      /activityRecord/listPageImage?page=1&size=3&activityId=888888878
     * @apiSuccess (200) {String} code 200:成功</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *          "data": {"total": 2,"size": 3,"page": 1,"content": [{"activityId": "888888878","name": "21fd1c4a50074fb78089964813cf368f","activityName": "11","suffix": "png","userName": "","userId": "","url": "activityimage\\21fd1c4a50074fb78089964813cf368f.png"}]}
     * }
     */
    @PostMapping("/listPageImageByActivityId")
    public JsonResult listPageImageByActivityId(String activityId,
                                                @RequestParam(defaultValue = "1")int page,
                                                @RequestParam(defaultValue = "10")int size){
        Activity activity = activityService.find(activityId);
        if (activity==null){
            return  JsonResult.notFound("没有找到活动实体");
        }
        List<ActivityImage> activityImageList = activityService.findAllImageByActivity(activity);
        List<ActivityImage> currentPageList = pageImage(activityImageList,page,size);
        JSONObject obj = new JSONObject();
        obj.put("page",page);
        obj.put("size",size);
        obj.put("total",activityImageList.size());
        obj.put("content",converImage(currentPageList));
        jsonResult.setData(obj);
        return jsonResult;
    }


    /**
     * @api {POST} /activityRecord/delImage  根据图片ids找删除图片
     * @apiGroup ActivityRecord
     * @apiVersion 1.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 根据图片ids找删除图片
     * @apiParam {String[]} frequencyIds 音频id
     * @apiParamExample {json} 请求样例:
     *                activityRecord/listPageFrequencyByActivityId?activityId=888888878
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
    @PostMapping("/delImage")
    public JsonResult delImage(String[] imageIds){
        List<ActivityImage> ai  = activityService.findAllImageByIds(imageIds);
        if (CollectionUtils.isEmpty(ai)){
            return JsonResult.notFound("没有找到实体");
        }
        activityService.deleteImages(ai);
        return JsonResult.success();
    }



    /**
     * @api {POST} /activityRecord/uploadVideo  上传小视频
     * @apiGroup ActivityRecord
     * @apiVersion 1.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 上传小视频
     * @apiParam {MultipartFile} file  视频文件
     * @apiParam {String} userId  用户id
     * @apiParam {String} activityId  活动id
     * @apiParamExample {json} 请求样例:
     *                /activityRecord/uploadVideo
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
    @PostMapping("/uploadVideo")
    public JsonResult uploadVideo(MultipartFile file,
                                      String userId,
                                      String activityId){
        Activity activity = activityService.find(activityId);
        if (activity == null){
            return JsonResult.notFound("找不到活动");
        }
        //这里不做判断没有用户就当匿名
        User user = userService.findById(userId);
        jsonResult = activityService.saveVideo(file,user,activity);
        return jsonResult;
    }

    /**
     * @api {POST} /activityRecord/listPageVideo  小视频列表
     * @apiGroup ActivityRecord
     * @apiVersion 1.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 小视频列表
     * @apiParam {int} page 查看的页数 默认1
     * @apiParam {int} size 每页的数量 默认10
     * @apiParamExample {json} 请求样例:
     *                /activityRecord/listPageVideo?page=1&size=10
     * @apiSuccess (200) {String} code 200:成功</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *         "data": {"total": 3,"size": 10,"page": 1,"content": [{"activityId": "888888878","name": "61400672a7ed4c53a8537296e430a7e5","activityName": "11","suffix": "mp4","userName": "钱正","userId": "402881916ba10b8a016ba113adbc0006","url": "activityvideo\\61400672a7ed4c53a8537296e430a7e5.mp4"}]}
     * }
     */
    @PostMapping("/listPageVideo")
    public  JsonResult listPageVideo(@RequestParam(defaultValue = "1")int page,
                                     @RequestParam(defaultValue = "10")int size){
        List<ActivityVideo> activityVideotList = activityService.findAllVideo();
        List<ActivityVideo> currentPageList = pageVideo(activityVideotList,page,size);
        JSONObject obj = new JSONObject();
        obj.put("page",page);
        obj.put("size",size);
        obj.put("total",activityVideotList.size());
        obj.put("content",converVideo(currentPageList));
        jsonResult.setData(obj);
        return jsonResult;
    }

    /**
     * @api {POST} /activityRecord/listPageVideoByActivityId  根据活动id找到小视频列表
     * @apiGroup ActivityRecord
     * @apiVersion 1.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 根据活动id找到小视频列表
     * @apiParam {String} activityId  活动id
     * @apiParam {int} page 查看的页数 默认1
     * @apiParam {int} size 每页的数量 默认10
     * @apiParamExample {json} 请求样例:
     *               /activityRecord/listPageVideo?page=1&size=2&activityId=888888878
     * @apiSuccess (200) {String} code 200:成功</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *         "data": {"total": 11,"size": 2,"page": 1,"content": [{"activityId": "888888878","name": "2ca659ba87f74a2da4aa64b9ad442bca","activityName": "11","suffix": "png","userName": "","userId": "","url": "activityfrequency\\2ca659ba87f74a2da4aa64b9ad442bca.png"}]}
     * }
     */
    @PostMapping("/listPageVideoByActivityId")
    public JsonResult listPageVideoByActivityId(String activityId,
                                                    @RequestParam(defaultValue = "1")int page,
                                                    @RequestParam(defaultValue = "10")int size){
        Activity activity = activityService.find(activityId);
        if (activity==null){
            return  JsonResult.notFound("没有找到活动实体");
        }
       List<ActivityVideo> activityVideoList = activityService.findAllVideoByActivity(activity);
        List<ActivityVideo> currentPageList = pageVideo(activityVideoList,page,size);
        JSONObject obj = new JSONObject();
        obj.put("page",page);
        obj.put("size",size);
        obj.put("total",activityVideoList.size());
        obj.put("content",converVideo(currentPageList));
        jsonResult.setData(obj);
        return jsonResult;
    }


    /**
     * @api {POST} /activityRecord/delVideo  根据小视频ids找删除小视频
     * @apiGroup ActivityRecord
     * @apiVersion 1.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 根据小视频ids找删除小视频
     * @apiParam {String[]} videoIds 小视频id
     * @apiParamExample {json} 请求样例:
     *               /activityRecord/delVideo?videoIds=4028d8816c65bb66016c65eeddd60009,4028d8816c65bb66016c65ef066c000a
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
    @PostMapping("/delVideo")
    public JsonResult delVideo(String[] videoIds){
        List<ActivityVideo> activityVideoList = activityService.findAllVideoByIds(videoIds);
        if (CollectionUtils.isEmpty(activityVideoList)){
            return JsonResult.notFound("没有找到实体");
        }
        activityService.deleteVideo(activityVideoList);
        return JsonResult.success();
    }



    /**
     * @api {POST} /activityRecord/addText   添加现场文字
     * @apiGroup ActivityRecord
     * @apiVersion 1.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 添加现场文字
     * @apiParam {String} content 现场文字
     * @apiParam {String} userId  用户id  可以为空即匿名
     * @apiParam {String} activityId 活动id  不得为空
     * @apiParamExample {json} 请求样例:
     *               /activityRecord/addText?content=11&userId=0a4179fc06cb49e3ac0db7bcc8cf0882&activityId=888888878
     * @apiSuccess (200) {String} code 200:成功</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *          "data": {"id": "4028d8816c65bb66016c66131fea0012","content": "11"}
     * }
     */
    @PostMapping("/addText")
    public JsonResult addText(String content,
                              String userId,
                              String activityId){
        Activity activity = activityService.find(activityId);
        if (activity == null){
            return JsonResult.notFound("找不到活动");
        }
        //这里不做判断没有用户就当匿名
        User user = userService.findById(userId);
        ActivityText activityText = activityService.saveText(content,user,activity);
        jsonResult.setData(JsonUtils.getJson(activityText));
        return jsonResult;
    }

    /**
     * @api {POST} /activityRecord/listPageText  现场文字列表
     * @apiGroup ActivityRecord
     * @apiVersion 1.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 现场文字列表
     * @apiParam {int} page 查看的页数 默认1
     * @apiParam {int} size 每页的数量 默认10
     * @apiParamExample {json} 请求样例:
     *               /activityRecord/listPageText?page=1&size=2
     * @apiSuccess (200) {String} code 200:成功</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *        "data": {"total": 3,"size": 2,"page": 1,"content": [{"activityId": "888888878","activityName": "11","userName": "管理员","userId": "0a4179fc06cb49e3ac0db7bcc8cf0882","content": "11"}]}
     * }
     */
    @PostMapping("/listPageText")
    public  JsonResult listPageText (@RequestParam(defaultValue = "1")int page,
                                    @RequestParam(defaultValue = "10")int size){
            List<ActivityText> activityTextList = activityService.findAllText();
            List<ActivityText> currentPageList = pageText(activityTextList,page,size);
              JSONObject obj = new JSONObject();
               obj.put("page",page);
               obj.put("size",size);
               obj.put("total",activityTextList.size());
               obj.put("content",converText(currentPageList));
            jsonResult.setData(obj);
            return  jsonResult;
    }


    /**
     * @api {POST} /activityRecord/listPageTextByActivityId  根据活动id找到现场文字列表
     * @apiGroup ActivityRecord
     * @apiVersion 1.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 根据活动id找到现场文字列表
     * @apiParam {String} activityId  活动id
     * @apiParam {int} page 查看的页数 默认1
     * @apiParam {int} size 每页的数量 默认10
     * @apiParamExample {json} 请求样例:
     *                activityRecord/listPageFrequencyByActivityId?activityId=888888878
     * @apiSuccess (200) {String} code 200:成功</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "code": 200,
     *     "message": "成功",
     *        "data": {"total": 3,"size": 2,"page": 1,"content": [{"activityId": "888888878","activityName": "11","userName": "管理员","userId": "0a4179fc06cb49e3ac0db7bcc8cf0882","content": "11"}]}
     * }
     */
    @PostMapping("/listPageTextByActivityId")
    public JsonResult listPageTextByActivityId(String activityId,
                                               @RequestParam(defaultValue = "1")int page,
                                               @RequestParam(defaultValue = "10")int size){
        Activity activity = activityService.find(activityId);
        if (activity==null){
            return  JsonResult.notFound("没有找到活动实体");
        }
        List<ActivityText> activityTextList = activityService.findAllTextByActivity(activity);
        List<ActivityText> currentPageList = pageText(activityTextList,page,size);
        JSONObject obj = new JSONObject();
        obj.put("page",page);
        obj.put("size",size);
        obj.put("total",activityTextList.size());
        obj.put("content",converText(currentPageList));
        jsonResult.setData(obj);
        return jsonResult;
    }

    /**
     * @api {POST} /activityRecord/delText  根据现场文字ids删除实体
     * @apiGroup ActivityRecord
     * @apiVersion 1.0.0
     * @apiHeader {String} IYunDao-AssessToken token验证
     * @apiDescription 根据现场文字ids删除实体
     * @apiParam {String[]} textIds 现场文字id
     * @apiParamExample {json} 请求样例:
     *                /activityRecord/delText?textIds=4028d8816c65bb66016c6612bfeb0010,4028d8816c65bb66016c66128685000e
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
    @PostMapping("/delText")
    public JsonResult delText(String[] textIds){
        List<ActivityText> at = activityService.findAllTextByIds(textIds);
        if (CollectionUtils.isEmpty(at)){
            return JsonResult.notFound("没有找到实体");
        }
        activityService.deleteText(at);
        return JsonResult.success();
    }

    /**
     * Text转换成json格式
     * @param currentPageList
     * @return
     */
    private JSONArray converText(List<ActivityText> currentPageList) {
        JSONArray arr = new JSONArray();
        for (ActivityText text : currentPageList) {
            JSONObject obj = new JSONObject();
            obj.put("content",text.getContent());
            if (text.getUser()!=null){
                obj.put("userName",text.getUser().getName());
                obj.put("userId",text.getUser().getId());
            }else {
                obj.put("userName","");
                obj.put("userId","");
            }
            obj.put("activityName",text.getActivity().getName());
            obj.put("activityId",text.getActivity().getId());
            arr.add(obj);
        }
        return arr;
    }


    /**
     *  List<ActivityText>分页
     */
    private List<ActivityText> pageText(List<ActivityText> activityTextList, int page, int size) {
        List<ActivityText> currentPageList = new ArrayList<>();
        if (activityTextList != null && activityTextList.size() > 0) {
            int currIdx = (page > 1 ? (page - 1) * size : 0);
            for (int i = 0; i < size && i < activityTextList.size() - currIdx; i++) {
                ActivityText data = activityTextList.get(currIdx + i);
                currentPageList.add(data);
            }
        }
        return currentPageList;

    }


    /**
     * Video转换成json格式
     * @param currentPageList
     * @return
     */
    private JSONArray converVideo(List<ActivityVideo> currentPageList) {
        JSONArray arr = new JSONArray();
        for (ActivityVideo ai : currentPageList) {
            JSONObject obj = new JSONObject();
            obj.put("name",ai.getName());
            obj.put("url",ai.getUrl());
            obj.put("suffix",ai.getSuffix());
            if (ai.getUser()!=null){
                obj.put("userName",ai.getUser().getName());
                obj.put("userId",ai.getUser().getId());
            }else {
                obj.put("userName","");
                obj.put("userId","");
            }
            obj.put("activityName",ai.getActivity().getName());
            obj.put("activityId",ai.getActivity().getId());
            arr.add(obj);
        }
        return arr;
    }


    /**
     *  List<ActivityVideo>分页
     */
    private List<ActivityVideo> pageVideo(List<ActivityVideo> activityVideotList, int page, int size) {
        List<ActivityVideo> currentPageList = new ArrayList<>();
        if (activityVideotList != null && activityVideotList.size() > 0) {
            int currIdx = (page > 1 ? (page - 1) * size : 0);
            for (int i = 0; i < size && i < activityVideotList.size() - currIdx; i++) {
                ActivityVideo data = activityVideotList.get(currIdx + i);
                currentPageList.add(data);
            }
        }
        return currentPageList;
    }

    /**
     * Image转换成json格式
     * @param currentPageList
     * @return
     */
    private JSONArray converImage(List<ActivityImage> currentPageList) {
        JSONArray arr = new JSONArray();
        for (ActivityImage ai : currentPageList) {
            JSONObject obj = new JSONObject();
            obj.put("name",ai.getName());
            obj.put("url",ai.getUrl());
            obj.put("suffix",ai.getSuffix());
            if (ai.getUser()!=null){
                obj.put("userName",ai.getUser().getName());
                obj.put("userId",ai.getUser().getId());
            }else {
                obj.put("userName","");
                obj.put("userId","");
            }
            obj.put("activityName",ai.getActivity().getName());
            obj.put("activityId",ai.getActivity().getId());
            arr.add(obj);
        }
        return arr;
    }


    /**
     *  List<ActivityImage>分页
     */
    private List<ActivityImage> pageImage(List<ActivityImage> activityImageList, int page, int size) {
        List<ActivityImage> currentPageList = new ArrayList<>();
        if (activityImageList != null && activityImageList.size() > 0) {
            int currIdx = (page > 1 ? (page - 1) * size : 0);
            for (int i = 0; i < size && i < activityImageList.size() - currIdx; i++) {
                ActivityImage data = activityImageList.get(currIdx + i);
                currentPageList.add(data);
            }
        }
        return currentPageList;

    }


    /**
     * Frequency转换成json格式
     * @param currentPageList
     * @return
     */
    private JSONArray converFrequency(List<ActivityFrequency> currentPageList) {
        JSONArray arr = new JSONArray();
        for (ActivityFrequency af : currentPageList) {
            JSONObject obj = new JSONObject();
            obj.put("name",af.getName());
            obj.put("url",af.getUrl());
            obj.put("suffix",af.getSuffix());
            if (af.getUser()!=null){
                obj.put("userName",af.getUser().getName());
                obj.put("userId",af.getUser().getId());
            }else {
                obj.put("userName","");
                obj.put("userId","");
            }
            obj.put("activityName",af.getActivity().getName());
            obj.put("activityId",af.getActivity().getId());
            arr.add(obj);
        }
        return arr ;
    }


    /**
     *  List<ActivityFrequency>分页
     */
    private List<ActivityFrequency> pageFrequency(List<ActivityFrequency> activityFrequencyList, int page, int size) {
        List<ActivityFrequency> currentPageList = new ArrayList<>();
        if (activityFrequencyList != null && activityFrequencyList.size() > 0) {
            int currIdx = (page > 1 ? (page - 1) * size : 0);
            for (int i = 0; i < size && i < activityFrequencyList.size() - currIdx; i++) {
                ActivityFrequency data = activityFrequencyList.get(currIdx + i);
                currentPageList.add(data);
            }
        }
        return currentPageList;
    }


}
