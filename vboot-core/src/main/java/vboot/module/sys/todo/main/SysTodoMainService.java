package vboot.module.sys.todo.main;

import vboot.module.bpm.proc.main.Zbpm;
import vboot.module.bpm.proc.main.Znode;
import vboot.common.mvc.dao.JdbcDao;
import vboot.common.util.lang.XstringUtil;
import vboot.module.sys.todo.done.SysTodoDone;
import vboot.module.sys.todo.done.SysTodoDoneRepo;
import vboot.module.sys.todo.user.SysTodoUser;
import vboot.module.sys.todo.user.SysTodoUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;


@Service
@Transactional(rollbackFor = Exception.class)
public class SysTodoMainService {

    public void sendTodo(Zbpm zbpm, Znode znode)  {
        SysTodoMain todo = new SysTodoMain();
        todo.setId(XstringUtil.getUUID());
        todo.setName(zbpm.getProna());
        todo.setLink("/#/page/ofmv?id="+zbpm.getProid());
        todo.setModid(zbpm.getProid());


        SysTodoUser todoTarget = new SysTodoUser();
        todoTarget.setId(XstringUtil.getUUID());
        todoTarget.setTid(todo.getId());
        todoTarget.setUid(znode.getExman());
        repo.save(todo);
        targetRepo.save(todoTarget);
    }

    public void doneTodo(Zbpm zbpm)  {
        String sql="select m.id,t.id as tid from sys_todo_main m inner join sys_todo_user t on t.tid=m.id " +
                "where t.uid=? and m.modid=?";
        Map<String, Object> map = jdbcDao.findMap(sql, zbpm.getHaman(), zbpm.getProid());
        String delsql = "delete from sys_todo_user where id=?";
        jdbcDao.update(delsql,map.get("tid"));

        SysTodoDone done = new SysTodoDone();
        done.setId(XstringUtil.getUUID());
        done.setTid(""+map.get("id"));
        done.setUid(zbpm.getHaman());
        doneRepo.save(done);
    }



    public SysTodoMain insert(SysTodoMain main)  {
        if (main.getId() == null || "".equals(main.getId())) {
            main.setId(XstringUtil.getUUID());
        }
        main.setCrtim(new Date());
        return repo.save(main);
    }


    public SysTodoMain update(SysTodoMain main) {
        return repo.save(main);
    }

    public int delete(String[] ids) {
        for (String id : ids) {
            repo.deleteById(id);
        }
        return ids.length;
    }

    @Transactional(readOnly = true)
    public SysTodoMain findOne(String id) {
        return repo.findById(id).get();
    }


    //----------bean注入------------
    @Autowired
    private JdbcDao jdbcDao;


    @Autowired
    private SysTodoMainRepo repo;

    @Autowired
    private SysTodoUserRepo targetRepo;

    @Autowired
    private SysTodoDoneRepo doneRepo;

}
