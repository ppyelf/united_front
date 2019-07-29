package com.iyundao.service.impl;

import com.iyundao.entity.User;
import com.iyundao.entity.UserApp;
import com.iyundao.repository.UserAppRepository;
import com.iyundao.repository.UserRepository;
import com.iyundao.service.UserAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @ClassName: UserAppServiceImpl
 * @project: IYunDao
 * @author: 念
 * @Date: 2019/7/20 16:54
 * @Description: 实现 - 用户授权
 * @Version: V2.0
 */
@Service
public class UserAppServiceImpl implements UserAppService {

    @Autowired
    private UserAppRepository userAppRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserApp findByOpenId(String openId) {
        return userAppRepository.findByOpenId(openId);
    }

    @Override
    public UserApp save(UserApp userApp) {
        userApp = userAppRepository.save(userApp);
        return userApp;
    }

    @Override
    @Transactional(rollbackFor = {})
    public UserApp create(String open_id, String avatarUrl, String nickName, String city, String province, UserApp.APP_TYPE type, User user) {
        UserApp app = new UserApp();
        app.setCreatedDate(new Date());
        app.setLastModifiedDate(new Date());
        app.setLastLoginTime(new Date());
        app.setOpenId(open_id);
        app.setAvatarUrl(avatarUrl);
        app.setNickName(nickName);
        app.setCity(city);
        app.setProvince(province);
        app.setType(type);
        app.setUser(user);
        app = userAppRepository.save(app);
        return app;
    }
}
