package com.iyundao.base.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iyundao.base.BaseComponent;
import com.iyundao.base.BaseEntity;
import com.iyundao.base.Page;
import org.springframework.util.Assert;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

/**
 * @ClassName: JsonUtils
 * @project: IYunDao
 * @author: 念
 * @Date: 2019/5/21 14:25
 * @Description: Json工具类
 * @Version: V2.0
 */
public class JsonUtils {

    /**
     * ObjectMapper
     */
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * 不可实例化
     */
    private JsonUtils() {
    }

    /**
     * 将对象转换为JSON字符串
     *
     * @param value
     *            对象
     * @return JSON字符串
     */
    public static String toJson(Object value) {
        Assert.notNull(value, "");

        try {
            return OBJECT_MAPPER.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 将Entity转化为Json字符串
     * @param obj
     * @return
     * @author 念
     */
    public static com.alibaba.fastjson.JSONObject getJson(Object obj) {
        Map<String, Field> map = ClassUtils.getDeclaredFieldsWithSuper(obj.getClass());
        com.alibaba.fastjson.JSONObject json = new com.alibaba.fastjson.JSONObject();
        //排除字段
        String[] names = new String[]{"createdDate", "lastModifiedDate", "version", "info1", "info2", "info3", "info4", "info5"};
        for (Map.Entry<String, Field> entry : map.entrySet()) {
            String key = entry.getKey();
            Field field = entry.getValue();
            Class cls = field.getType();
            if (Arrays.asList(names).contains(key)) {
                continue;
            } 
            if (!BaseComponent.class.isAssignableFrom(cls)
                    && !BaseEntity.class.isAssignableFrom(cls)
                    && !Collection.class.isAssignableFrom(cls)
                    && !Map.class.isAssignableFrom(cls)) {
                json.put(key, ClassUtils.getBrieflyProperty(obj, field.getName()));
            }
        }
        return json;
    }

    /**
     * 将Entity转化为Json字符串
     * @param obj
     * @return
     * @author 念
     */
    public static com.alibaba.fastjson.JSONObject getSimpleJson(Object obj, String[] names) {
        Map<String, Field> map = ClassUtils.getDeclaredFieldsWithSuper(obj.getClass());
        com.alibaba.fastjson.JSONObject json = new com.alibaba.fastjson.JSONObject();
        for (Map.Entry<String, Field> entry : map.entrySet()) {
            String key = entry.getKey();
            Field field = entry.getValue();
            for (String name : names) {
                if (name.equals(key)) {
                    json.put(key, ClassUtils.getBrieflyProperty(obj, field.getName()));
                }
            }
        }
        return json;
    }

    public static com.alibaba.fastjson.JSONObject getPage(Page<?> page) {
        com.alibaba.fastjson.JSONObject jsonObject = new com.alibaba.fastjson.JSONObject();
        com.alibaba.fastjson.JSONArray arr = new com.alibaba.fastjson.JSONArray();
        for (Object o : page.getContent()) {
            arr.add(getJson(o));
        }
        jsonObject.put("total", page.getTotal());
        jsonObject.put("totalPage", page.getTotalPages());
        jsonObject.put("page", page.getPageNumber());
        jsonObject.put("content", arr);
        return jsonObject;
    }
}
