package com.nowcoder;

import java.io.BufferedInputStream;
import java.io.File;
import java.lang.annotation.*;
import java.util.Date;
import java.util.TreeSet;

/**
 * author:Junqson
 * create:2018/3/28 19:15
 * des:
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
@Documented
@Inherited
public @interface CusAnoo {
    String value() default "Hello World";
    String name() default "StrangeAno";
    int id() default 0;

}
