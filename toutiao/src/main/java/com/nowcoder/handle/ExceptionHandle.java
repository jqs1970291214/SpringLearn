package com.nowcoder.handle;


import com.nowcoder.exception.MyException;
import com.nowcoder.util.ApiResult;
import com.nowcoder.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * author:Junqson
 * create:2018/4/15 0:03
 * des: 统一异常处理
 */
@Component
@ControllerAdvice
@Slf4j
public class ExceptionHandle {

    /**
     * 处理系统异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ApiResult handle(Exception e) {
        ApiResult apiResult = ResultUtil.error();
        if (e instanceof MyException) {
            apiResult.setStatus(((MyException) e).getStatus());
            apiResult.setMsg(e.getMessage());
        } else {
             e.printStackTrace(); //输出到控制台或者记录日志，否则会丢失异常信息
            log.error("系统异常:[{}]",e.getMessage());
        }
        return apiResult;
    }
}
