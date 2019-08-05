package com.iyundao.service;

import com.iyundao.base.Page;
import com.iyundao.base.Pageable;
import com.iyundao.entity.Label;
import com.iyundao.entity.UserLabel;

import java.util.List;

/**
 * @ClassName: LabelService
 * @project: unitedfront
 * @author: 念
 * @Date: 2019/8/3 11:35
 * @Description: 服务 - 标签
 * @Version: V1.0
 */
public interface LabelService {

    /**
     * 查询标签编号是否存在
     * @param code
     * @return
     */
    boolean existsCode(String code);

    /**
     * 添加标签
     * @param name
     * @param code
     * @param remark
     * @param type
     * @return
     */
    Label createLabel(String name, String code, String remark, Label.LABEL_TYPE type);

    /**
     * 获取所有标签的集合
     * @return
     */
    List<Label> findAllLabels();

    /**
     * 根据id查询标签实体集合
     * @param id
     * @return
     */
    Label findLabelById(String id);

    /**
     * 删除标签实体
     * @param label
     */
    void deleteLabel(Label label);

    /**
     * 根据IDS查询标签集合
     * @param labelIds
     * @return
     */
    List<Label> findLabelByIds(String[] labelIds);

    /**
     * 根据标签ID和用户ID查询用户标签
     * @param userId
     * @param labelId
     * @return
     */
    UserLabel findUserLabelByUserIdAndLabelId(String userId, String labelId);

    /**
     * 删除用户标签
     * @param userLabel
     */
    void delUserLabel(UserLabel userLabel);

    /**
     * 查询用户特征标签分页
     * @param pageable
     * @return
     */
    Page<Label> findUserLabelPage(Pageable pageable);

    /**
     * 查询活动特征标签分页
     * @param pageable
     * @return
     */
    Page<Label> findActivityLabelPage(Pageable pageable);
}
