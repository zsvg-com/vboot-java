package com.abc.demo;

import com.zsvg.vboot.common.mvc.api.RestResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("demo")
public class DemoApi {

    @GetMapping
    public RestResult get() {
        return RestResult.ok("这里可写实际业务模块");
    }



}
