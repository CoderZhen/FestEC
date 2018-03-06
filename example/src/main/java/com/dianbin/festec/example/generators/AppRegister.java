package com.dianbin.festec.example.generators;

import com.dianbin.latte.wechat.templates.AppRegisterTemplate;
import com.dianbin.latte_annotations.annotations.AppRegisterGenerator;

/**
 * Created by ZHEN on 2018/3/3.
 */
@AppRegisterGenerator(
        packageName = "com.dianbin.festec.example",
        registerTemplate = AppRegisterTemplate.class
)
public interface AppRegister {
}
