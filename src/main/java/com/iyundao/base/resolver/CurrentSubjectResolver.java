package com.iyundao.base.resolver;

import com.iyundao.base.annotation.CurrentSubject;
import com.iyundao.base.shiro.SecurityConsts;
import com.iyundao.base.utils.JwtUtils;
import com.iyundao.entity.Subject;
import com.iyundao.entity.User;
import com.iyundao.entity.UserRelation;
import com.iyundao.service.UserRelationService;
import com.iyundao.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName: CurrentSubjectResolver
 * @project: IYunDao
 * @author: 念
 * @Date: 2019/7/13 16:48
 * @Description: 当前机构解析器
 * @Version: V2.0
 */
public class CurrentSubjectResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRelationService userRelationService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        org.apache.shiro.subject.Subject subject = SecurityUtils.getSubject();
        return parameter.getParameterType().isAssignableFrom(Subject.class)
                && parameter.hasParameterAnnotation(CurrentSubject.class)
                && subject.isAuthenticated();
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest req, WebDataBinderFactory webDataBinderFactory) throws Exception {
        Subject subject = (Subject) SecurityUtils.getSubject().getSession().getAttribute("currentSubject");
        if (subject == null) {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = servletRequestAttributes.getRequest();
            String token = request.getHeader("IYunDao-AssessToken");
            User user = userService.findByAccount(JwtUtils.getClaim(token, SecurityConsts.ACCOUNT));
            List<UserRelation> userRelations = userRelationService.findByUser(user);
            for (UserRelation userRelation : userRelations) {
                subject = userRelation.getSubject();
                SecurityUtils.getSubject().getSession().setAttribute("currentSubject", subject);
                break;
            }
        } 
        return subject == null ? null : subject;
    }
}
