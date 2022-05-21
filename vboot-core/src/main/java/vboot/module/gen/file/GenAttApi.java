package vboot.module.gen.file;

import vboot.module.ass.file.att.AssFileAtt;
import vboot.module.ass.file.att.AssFileAttRepo;
import vboot.module.ass.file.att.AssFileAttHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("gen/att")
public class GenAttApi {

    @PostMapping(value="up",produces = "text/html;charset=UTF-8")
    public String upload(@RequestParam(value = "file", required = false) MultipartFile file) throws Exception {
        System.out.println("上传xxxxxx");
        AssFileAtt att = handler.saveFile(file);
        repo.save(att);
        return "{\"id\":\"" + att.getId() + "\",\"address\":\"" + att.getAddress() + "\",\"pname\":\"" + att.getPname() + "\",\"sname\":\"" + att.getSname() + "\",\"zsize\":\"" + att.getZsize() + "\",\"zimg\":\"" + att.getZimg() + "\"}";
    }

    @GetMapping(value="one/{id}")
    public void getOne(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AssFileAtt att=repo.findById(id).get();
        handler.download(request,response,att);
    }

    @GetMapping(value="pdf")
    public void pdf(String f, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AssFileAtt att=repo.findById(f).get();
        handler.download(request,response,att);
    }


    @GetMapping(value="path")
    public void downloadByPath(String name, String path, HttpServletRequest request, HttpServletResponse response) throws Exception {
        handler.downloadByPath(request,response,name,path);
    }

    @Autowired
    private AssFileAttRepo repo;

    @Autowired
    private AssFileAttHandler handler;

}
