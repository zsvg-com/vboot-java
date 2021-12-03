package com.zsvg.vboot.bi;

import com.zsvg.vboot.common.mvc.api.RestResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bi")
public class BiTestApi {

    @GetMapping("test")
    public RestResult get() {
        return RestResult.ok("这里可以放扩展模块1");
    }

}


