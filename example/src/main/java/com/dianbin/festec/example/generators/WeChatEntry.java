package com.dianbin.festec.example.generators;

import com.dianbin.latte.wechat.templates.WXEntryTemplate;
import com.dianbin.latte_annotations.annotations.EntryGenerator;

/**
 * Created by ZHEN on 2018/3/3.
 */
@EntryGenerator(
        packageName = "com.dianbin.festec.example",
        entryTemplete = WXEntryTemplate.class
)
public interface WeChatEntry {

}
