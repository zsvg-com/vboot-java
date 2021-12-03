package com.zsvg.vboot.module.gen.file;

import com.zsvg.vboot.common.util.file.XqcodeUtil;
import com.zsvg.vboot.module.ass.file.att.AssFileAttHandler;
import com.zsvg.vboot.module.ass.file.att.AssFileAtt;
import com.zsvg.vboot.module.ass.file.att.AssFileAttRepo;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("gen/img")
public class GenImgApi {

    //{
    //  "code": 0 //0表示成功，其它失败
    //  ,"msg": "" //提示信息 //一般上传失败后返回
    //  ,"data": {
    //    "src": "图片路径"
    //    ,"title": "图片名称" //可选
    //  }
    //}

    @PostMapping(value="up",produces = "text/html;charset=UTF-8")
    public String upload(@RequestParam(value = "file", required = false) MultipartFile file) throws Exception {
        AssFileAtt att = handler.saveFile2(file);
        repo.save(att);
        return "{\"code\":\"" + 0 + "\",\"msg\":\"" + "\",\"data\":{\"src\":\"" + "co/img/one/"+att.getId() + "\",\"title\":\"" + att.getPname()+"."+att.getSname() + "\"}}";
    }

    @GetMapping(value="one/{id}")
    public void getOne(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AssFileAtt att=repo.findById(id).get();
        handler.download(request,response,att);
    }

    @RequestMapping(value="qr",produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] getImage(String label,Integer size) throws IOException, WriterException {
        if(size==null||size==0)
        {
            size=900;
        }
        InputStream inputStream= XqcodeUtil.createQrCode(label, size, "JPEG");
//        File file = new File("D:/qrcode.jpg");
//        FileInputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes, 0, inputStream.available());
        return bytes;
    }

    @Autowired
    private AssFileAttRepo repo;

    @Autowired
    private AssFileAttHandler handler;

}