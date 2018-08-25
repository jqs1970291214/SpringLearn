package com.nowcoder.controller;

import com.nowcoder.model.Message;
import com.nowcoder.service.MessageService;
import com.nowcoder.util.ApiResult;
import com.nowcoder.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * summary
 * descripttions
 *
 * @author Junqson
 * @date 2018/8/25 21:41
 */
@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;

    @RequestMapping("/msg/addMessage")
    @ResponseBody
    public ApiResult addMessage() {

        return ResultUtil.success();
    }

}
