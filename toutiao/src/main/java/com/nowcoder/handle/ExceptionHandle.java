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
     * 其他的异常处理方式
     * 1、error.html异常视图
     * 2、/error路由对应的默认异常处理器
     * 3、覆盖默认的/error控制器继承AbstractErrorController,BasicErrorController提供默认实现
     * 4、各个控制层业务层自行trycatch处理
     */

    /**
     * 处理系统异常，作为各个控制器的辅助。
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
            //e.printStackTrace(); //输出到控制台或者记录日志，否则会丢失异常信息
            log.error("系统异常:[{}]",e.getMessage());
        }

        return apiResult;
    }
}
