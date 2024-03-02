package org.xyc.domain.user.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.xyc.domain.base.model.Response;

/**
 * @author xuyachang
 * @date 2024/2/5
 */
@RestControllerAdvice
@Slf4j
public class WebExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Response<Object> bindExceptionHandler(Exception e) {
        return Response.fail(e.getMessage());
    }
}
