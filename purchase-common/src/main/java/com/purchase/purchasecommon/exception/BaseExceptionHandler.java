package com.purchase.purchasecommon.exception;

import com.purchase.purchasecommon.lang.Response;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: ZhengHuaJing
 * @Date: 2020/12/21 08:55
 * @Description:
 */
@ControllerAdvice
public class BaseExceptionHandler {

    /***
     * 异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Response error(Exception e) {
        e.printStackTrace();
        return Response.fail();
    }
}
