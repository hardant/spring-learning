package com.example.demospringhandlermethodargumentresolver;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;


public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {
    /**
     * 此方法确定当前参数是否需要处理
     * @param parameter
     * @return
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        if (parameter.hasParameterAnnotation(UserAnnotation.class)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 此方法是对参数的处理结果，返回值将被赋值给handler method的对应参数
     * @param parameter
     * @param mavContainer
     * @param webRequest
     * @param binderFactory
     * @return
     * @throws Exception
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        User user = new User();
        user.setUserName("Brandon");
        user.setAge(18);

        String userName = webRequest.getParameter("userName");
        webRequest.getHeader("Token");
        System.out.println(userName);
        return user;
    }
}
