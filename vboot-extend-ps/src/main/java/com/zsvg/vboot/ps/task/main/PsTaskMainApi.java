package com.zsvg.vboot.ps.task.main;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.util.ListUtils;
import com.zsvg.vboot.common.mvc.api.RestResult;
import com.zsvg.vboot.common.mvc.dao.Sqler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("ps/task/main")
public class PsTaskMainApi {

    private String table = "ps_task_main";

//    @GetMapping
//    public RestResult get(String name) {
//        Sqler sqler = new Sqler(table);
//        sqler.addLike("t.name", name);
//        return RestResult.ok(service.findPageData(sqler));
//    }

    @GetMapping
    public RestResult get(String name,String stageid) {
        Sqler sqler = new Sqler(table);
        sqler.addSelect("t.pid,t.crtim,t.uptim");
        sqler.addLike("t.name", name);
        sqler.addEqual("t.stageid", stageid);
        sqler.addOrder("t.ornum");
        return RestResult.ok(service.findTree(sqler));
    }


    @GetMapping("one/{id}")
    public RestResult getOne(@PathVariable String id) {
        PsTaskMain main = service.findOne(id);
        return RestResult.ok(main);
    }

    @GetMapping("exp/{id}")
    public void getExp(@PathVariable String id,HttpServletResponse response) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        System.out.println("test");
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("测试", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), PsTaskMain.class).sheet("模板").doWrite(data());
    }

    @PostMapping("imp")
    public RestResult postImp(MultipartFile file) throws IOException {
        System.out.println(file.getName());
        System.out.println(file.getSize());
        System.out.println(file.getInputStream());
        EasyExcel.read(file.getInputStream(), PsTaskMain.class, new UploadDataListener()).sheet().doRead();
        return RestResult.ok("11");
    }

    private List<PsTaskMain> data() {
        List<PsTaskMain> list = ListUtils.newArrayList();
        for (int i = 0; i < 10; i++) {
            PsTaskMain data = new PsTaskMain();
            data.setString2("字符串" + 0);
            data.setDate2(new Date());
            data.setDoubleData(0.56);
            list.add(data);
        }
        return list;
    }

    @PostMapping
    public RestResult post(@RequestBody PsTaskMain main) {
        return RestResult.ok(service.insert(main));
    }

    @PutMapping
    public RestResult put(@RequestBody PsTaskMain main) {
        return RestResult.ok(service.update(main));
    }

    @DeleteMapping("{ids}")
    public RestResult delete(@PathVariable String[] ids) {
        return RestResult.ok(service.delete(ids));
    }

    @Autowired
    private PsTaskMainService service;

}