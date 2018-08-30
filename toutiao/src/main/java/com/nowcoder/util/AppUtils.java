package com.nowcoder.util;

/**
 * 其他一些本应用使用的工具
 * descripttions
 *
 * @author Junqson
 * @date 2018/8/18 13:17
 */
public class AppUtils {

    //匿名用户，系统管理员账户
    public static int ADMIN_ID = 0;


    //七牛bucket测试域名
    public static String QINIU_DOMAIN = "http://pdngs0qa5.bkt.clouddn.com/";


    //本应用域名
    public static String DOMAIN = "http://localhost:8080/";

    // 允许的图片后缀
    public static String[] IMAGE_EXT = {"jpg", "png", "bmp", "jpeg"};

    //图片的保存路径
    public static String IMAGE_DIR = "D:/servletupload/";

    //是否允许上传
    public static boolean isFileAllowed(String ext) {
        for (String aext : IMAGE_EXT) {
            if (aext.equalsIgnoreCase(ext)) {
                return true;
            }
        }

        return false;
    }
}
