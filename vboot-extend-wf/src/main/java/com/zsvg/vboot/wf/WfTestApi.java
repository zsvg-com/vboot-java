package com.zsvg.vboot.wf;

import com.zsvg.vboot.common.mvc.api.RestResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("wf")
public class WfTestApi {

    @GetMapping("test")
    public RestResult get() {
        return RestResult.ok("这里可以放扩展模块");
    }

}