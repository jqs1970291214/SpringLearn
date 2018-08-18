package com.nowcoder.util;
import java.util.Map;

/**
 * 处理result集
 * 依赖ResultEnum
 *
 * @author Junqson
 * @date 2018/4/15 0:33
 * @Version 1.1/2018.8.14
 *
 */
public class ResultUtil {
    /**
     * 默认成功
     * @return
     */
    public static ApiResult success() {
        ApiResult result = new ApiResult();
        result.setStatus(ResultEnum.SUCCESS.getStatus());
        result.setMsg(ResultEnum.SUCCESS.getMsg());
        return result;
    }

    /**
     * 指定消息的成功
     * @param msg
     * @return
     */
    public static ApiResult success(String msg) {
        ApiResult result = ResultUtil.success();
        result.setMsg(msg);
        return result;
    }

    /**
     * 携带数据的成功
     * @param data
     * @return
     */
    public static ApiResult success(Map<String,Object> data) {
        ApiResult result = ResultUtil.success();
        result.setData(data);
        return result;
    }

    /**
     * 默认错误
     * @return
     */
    public static ApiResult error() {
        ApiResult result = new ApiResult();
        result.setStatus(ResultEnum.ERROR.getStatus());
        result.setMsg(ResultEnum.ERROR.getMsg());
        return result;
    }

    /**
     * 指定消息的error
     * @param msg
     * @return
     */
    public static ApiResult error(String msg) {
        ApiResult result = ResultUtil.error();
        result.setMsg(msg);
        return result;
    }

    /**
     * 枚举
     * @param resultEnum
     * @return
     */
    public static ApiResult result(ResultEnum resultEnum) {
        ApiResult result = new ApiResult();
        result.setStatus(resultEnum.getStatus());
        result.setMsg(resultEnum.getMsg());
        return result;
    }
}
