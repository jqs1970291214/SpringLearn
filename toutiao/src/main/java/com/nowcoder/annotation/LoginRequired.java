package com.nowcoder.annotation;

import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.annotation.*;

/**
 * summary
 * descripttions
 *
 * @author Junqson
 * @date 2018/8/16 21:17
 */

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginRequired {
    String value() default "";
}
