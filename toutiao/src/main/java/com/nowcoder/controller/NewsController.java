package com.nowcoder.controller;

import com.nowcoder.annotation.LoginRequired;
import com.nowcoder.exception.MyException;
import com.nowcoder.model.News;
import com.nowcoder.model.UserHolder;
import com.nowcoder.service.NewsService;
import com.nowcoder.service.QiniuService;
import com.nowcoder.util.ApiResult;
import com.nowcoder.util.AppUtils;
import com.nowcoder.util.ResultEnum;
import com.nowcoder.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationPid;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.util.Date;

/**
 * 消息相关
 * descripttions
 *
 * @author Junqson
 * @date 2018/8/18 13:03
 */
@Controller
@Slf4j
public class NewsController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private QiniuService qiniuService;

    @Autowired
    private UserHolder holder;

    @LoginRequired
    @RequestMapping("/addNews")
    @ResponseBody
    public ApiResult addNews(@RequestParam("title") String title,
                             @RequestParam("link") String link,
                             @RequestParam("img") String img) {
        News news = new News();
        news.setImage(img);
        news.setCommentCount(0);
        news.setCreateDate(new Date());
        news.setLikeCount(0);
        news.setLink(link);
        news.setTitle(title);
        news.setUserId(holder.getUser().getId());
        try {
            newsService.addNews(news);
        } catch (Exception e) { //处理特定异常
            //记录日志返回前端标准格式
            log.error("添加资讯失败:[{}]",e.getMessage());
            return ResultUtil.error("添加资讯失败");
        }

        return ResultUtil.success();

    }



    // *** 图片上传与展示 ***


    //图片上传到服务器
    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            String fileUrl = newsService.saveImage(file);
            if (fileUrl == null) {
                return ResultUtil.result(ResultEnum.UPLOAD_FAILED);
            }

            return ResultUtil.success(fileUrl);

        } catch (Exception e) {
            log.error("上传图片失败:[{}]",e.getMessage());
            throw new MyException(ResultEnum.UPLOAD_FAILED);
        }
    }


    //图片上传到七牛
    @RequestMapping(value = "/uploadToQiniu", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult uploadToQiniu(@RequestParam("file") MultipartFile file) {
        try {
            String fileUrl = qiniuService.qiniuUpload(file);
            if (fileUrl == null) {
                return ResultUtil.result(ResultEnum.UPLOAD_FAILED);
            }
            fileUrl = AppUtils.QINIU_DOMAIN + fileUrl;
            return ResultUtil.success(fileUrl);
        } catch (Exception e) {
            log.error("上传图片失败:[{}]",e.getMessage());
            throw new MyException(ResultEnum.UPLOAD_FAILED);
        }
    }


    //图片展示
    @RequestMapping("/img")
    @ResponseBody
    public void img(@RequestParam("name") String name, HttpServletResponse response) {
        try {
            //指定为未分类的二进制文件，浏览器不知道文件类型
            //response.setContentType("application/octet-stream");
            //这样浏览器就知道是jpeg图片了
            response.setContentType("image/jpeg");
            //这里指定以附件的形式下载，对于图片来说，不指定浏览器默认不会下载，指定了就会下载
            //response.setHeader("Content-Disposition", "attachment;filename=" + name);


            //从文件流读取写入Servlet输出流
            StreamUtils.copy(new FileInputStream(AppUtils.IMAGE_DIR + name), response.getOutputStream());
        } catch (Exception e) {
            log.error("读取图片错误:[{}]",e.getMessage());
            throw new MyException(ResultEnum.FILE_READ_FAILED);
        }


    }



}
