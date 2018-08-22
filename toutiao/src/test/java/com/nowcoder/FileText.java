package com.nowcoder;

import java.io.File;
import java.io.InputStreamReader;

/**
 * summary
 * descripttions
 *
 * @author Junqson
 * @date 2018/8/14 22:03
 */
public class FileText {
    public static void main(String[] args) {
        File file = new File("");
        System.out.println(file.getPath());
        if (file != null) {
            String[] filelist = file.list();
            for (String filepath : filelist) {
                System.out.println(filepath);
                System.out.println(new File(filepath).length());
            }

        }

        System.out.println(file.getAbsolutePath());
        System.out.println(file.getPath());


    }
}
