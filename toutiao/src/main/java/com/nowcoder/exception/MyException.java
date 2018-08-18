package com.nowcoder.exception;


import com.nowcoder.util.ResultEnum;

/**
 * author:Junqson
 * create:2018/4/15 0:40
 * des: 处理异常
 */
public class MyException extends RuntimeException {

    private Integer status;

    public MyException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.status = resultEnum.getStatus();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
