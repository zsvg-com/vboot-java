package vboot.bi.demo.student;

import vboot.common.mvc.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Slf4j
public class BiDemoStudentService extends BaseService<BiDemoStudent> {

    //----------bean注入------------
    @Autowired
    private BiDemoStudentRepo repo;

    @PostConstruct
    public void initDao() {
        super.setRepo(repo);
    }
}
