package com.nowcoder.service;

import com.nowcoder.dao.NewsDao;
import com.nowcoder.model.News;
import com.nowcoder.util.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

/**
 * author:Junqson
 * create:2018/3/31 15:24
 * des:
 */
@Service
public class NewsService {
    @Autowired
    private NewsDao newsDao;

    public List<News> getLatestNews(int userId, int offset, int limit) {
        return newsDao.selectByUserIdAndOffset(userId, offset, limit);
    }

    public String saveImage(MultipartFile file) throws IOException {

        String filename = file.getOriginalFilename();
        int dotIndex = filename.lastIndexOf(".");
        if (dotIndex < 0) {
            return null;
        }
        String fileExt = filename.substring(dotIndex + 1);
        //如果允许上传
        if (!AppUtils.isFileAllowed(fileExt)) {
            return null;
        }

        //屏蔽掉原来的文件名
        String name = UUID.randomUUID().toString().replace("-", "").substring(0, 10) + "." + fileExt;
        File toSave = new File(AppUtils.IMAGE_DIR + name);
        //如果存在就替换掉
        Files.copy(file.getInputStream(), toSave.toPath(), StandardCopyOption.REPLACE_EXISTING);

        return AppUtils.DOMAIN + "img?name=" + name;
    }

}
