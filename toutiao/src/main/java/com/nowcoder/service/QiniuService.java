package com.nowcoder.service;

import com.google.gson.Gson;
import com.nowcoder.util.AppUtils;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;


/**
 * summary
 * descripttions
 *
 * @author Junqson
 * @date 2018/8/18 17:11
 */
@Service
@Slf4j
public class QiniuService {

    //构造一个带指定Zone对象的配置类，华东
    private Configuration cfg = new Configuration(Zone.zone0());

    //...其他参数参考类注释
    private UploadManager uploadManager = new UploadManager(cfg);

    //...生成上传凭证，然后准备上传
    private String accessKey = "CgT-3_wx9bJD7nBjRwZ2A6LOVNagRgoBMEHcCt3p";
    private String secretKey = "SPDe9S3BRcdupt7Ckr-Pqp-tf4vM5DIS1poqqBgm";
    private String bucket = "filesave";




    public String qiniuUpload(MultipartFile file) throws IOException {
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;
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
        key = UUID.randomUUID().toString().replace("-", "").substring(0, 10) + "." + fileExt;


        byte[] uploadBytes = file.getBytes();
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(uploadBytes, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        } catch (QiniuException ex) {
            log.error("七牛异常: " + ex.getMessage());
        }

        return null;
    }



}
