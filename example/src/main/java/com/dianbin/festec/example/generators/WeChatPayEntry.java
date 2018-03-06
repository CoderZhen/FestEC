package com.dianbin.festec.example.generators;

import com.dianbin.latte.wechat.templates.WXPayEntryTemplate;
import com.dianbin.latte_annotations.annotations.PayEntryGenerator;

/**
 * Created by ZHEN on 2018/3/3.
 */
@PayEntryGenerator(
        packageName = "com.dianbin.festec.example",
        payEntryTemplete = WXPayEntryTemplate.class
)
public interface WeChatPayEntry {
}
