package com.nowcoder;

import org.springframework.boot.autoconfigure.web.AbstractErrorController;

/**
 * summary
 * descripttions
 *
 * @author Junqson
 * @date 2018/8/22 16:27
 */
public class ErrorHandleTest extends AbstractErrorController {
    @Override
    public String getErrorPath() {
        return null;
    }
}
