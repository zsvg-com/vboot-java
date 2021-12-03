package com.zsvg.vboot.module.gen.num;

import com.zsvg.vboot.common.mvc.api.RestResult;
import com.zsvg.vboot.common.util.lang.XstringUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("gen/num")
public class GenNumApi {

    @GetMapping("uuid")
    public RestResult uuid() {
        return RestResult.ok(XstringUtil.getUUID());
    }

}
