package com.cmt.extension.consumer.service;

import com.cmt.extension.core.annotation.Extension;
import com.cmt.extension.core.common.ExtesionTypeEnum;
import com.cmt.extension.spi.IHelloService;

/**
 * @author shengchaojie
 * @date 2019-10-25
 **/
@Extension(bizCode = "f",invokeMethod = ExtesionTypeEnum.LOCAL)
public class LocalHelloServiceImpl2 implements IHelloService {
    public String hello() {
        return "helloF";
    }
}
