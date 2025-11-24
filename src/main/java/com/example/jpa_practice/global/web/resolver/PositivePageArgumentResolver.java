package com.example.jpa_practice.global.web.resolver;

import com.example.jpa_practice.global.annotation.PositivePage;
import com.example.jpa_practice.global.apiPayload.code.GeneralErrorCode;
import com.example.jpa_practice.global.exception.CustomException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class PositivePageArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(PositivePage.class)
                && (int.class.equals(parameter.getParameterType())
                || Integer.class.equals(parameter.getParameterType()));
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {

        PositivePage annotation = parameter.getParameterAnnotation(PositivePage.class);
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);

        String paramName = resolveParamName(parameter, annotation);
        String rawValue = request != null ? request.getParameter(paramName) : null;

        int page = parsePage(rawValue, annotation.defaultValue());

        if (page <= 0) {
            throw new CustomException(
                    GeneralErrorCode.BAD_REQUEST,
                    "page 파라미터는 1 이상의 값이어야 합니다."
            );
        }

        return page - 1; // convert to zero-based index
    }

    private String resolveParamName(MethodParameter parameter, PositivePage annotation) {
        if (annotation != null && StringUtils.hasText(annotation.value())) {
            return annotation.value();
        }
        return parameter.getParameterName();
    }

    private int parsePage(String rawValue, int defaultValue) {
        if (!StringUtils.hasText(rawValue)) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(rawValue);
        } catch (NumberFormatException e) {
            throw new CustomException(
                    GeneralErrorCode.BAD_REQUEST,
                    "page 파라미터는 숫자여야 합니다."
            );
        }
    }
}

