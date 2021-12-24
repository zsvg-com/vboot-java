package com.abc.module.sd.cust.main;

import com.abc.module.sd.proj.main.SdProjMain;
import com.abc.module.sd.proj.main.SdProjMainRepo;
import com.zsvg.vboot.common.util.lang.XstringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service
@Transactional(rollbackFor = Exception.class)
public class SdCustMainService {

    public SdCustMain findOne(String id) {

        return custMapper.selectById(id);
    }

    public String insert(SdCustMain cust) {
        cust.setId(XstringUtil.getUUID());
        custMapper.insert(cust);
        return cust.getId();
    }

    public String update(SdCustMain cust) {
        custMapper.updateById(cust);
        return cust.getId();
    }

    public int delete(String[] ids) {
        for (String id : ids) {
            custMapper.deleteById(id);
        }
        return ids.length;
    }

    public void test() {
        //先通过JPA新增一个项目
        SdProjMain projMain=new SdProjMain();
        projMain.setId(XstringUtil.getUUID());
        projMain.setName("有事务的项目");
        projRepo.save(projMain);

        //再通过mybatis-plus新增一个项目，并让它报错
        SdCustMain sdCustMain=new SdCustMain();
        sdCustMain.setId(XstringUtil.getUUID()+"XXXXXXXXXXXXXXXX");//超出长度保存出错
        sdCustMain.setNotes("有事务的客户");
        custMapper.insert(sdCustMain);
    }

    @Resource
    private SdProjMainRepo projRepo;

    @Resource
    private SdCustMainMapper custMapper;





}

