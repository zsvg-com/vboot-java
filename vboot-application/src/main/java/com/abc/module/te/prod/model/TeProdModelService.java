package com.abc.module.te.prod.model;

import vboot.common.mvc.dao.Sqler;
import vboot.common.mvc.pojo.Zinp;
import vboot.common.mvc.service.BaseMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TeProdModelService extends BaseMainService<TeProdModel> {

    public Zmodel getZmodel(String modid){
        String sql = "select m.catid,m.id,m.name,v.coway,v.paway,v.patid from te_prod_model m inner join te_prod_version v on v.modid=m.id" +
                " and v.detag=1 where m.id=?";
        Map<String, Object> map = jdbcDao.findMap(sql, modid);
        Zmodel zmodel = new Zmodel();
        zmodel.setId(modid);
        zmodel.setName(""+map.get("name"));
        zmodel.setCatid(""+map.get("catid"));
        setScreens(zmodel);

        return zmodel;
    }

    private void setScreens(Zmodel zmodel){
        Sqler sqler2=new Sqler("te_feat_screen");
        sqler2.addEqual("t.catid", zmodel.getCatid());
        sqler2.addOrder("t.ornum");
        List<Zscreen> zscreens=jdbcDao.getTp().query(sqler2.getSql(),sqler2.getParams(), new BeanPropertyRowMapper<>(Zscreen.class));

        List<Zparam> zparams = new ArrayList<>();
        String sql3 = "select id,name,shmod,code,scrid,ornum from te_feat_param where scrid in (";
        String ins = "";
        for (Zscreen zscreen : zscreens) {
            ins += "'" + zscreen.getId() + "',";
        }
        if (!"".equals(ins)) {
            ins = ins.substring(0, ins.length() - 1);
            sql3 += ins + ") order by scrid,ornum";
            zparams=jdbcDao.getTp().query(sql3, new BeanPropertyRowMapper<>(Zparam.class));
        }

        for (Zscreen zscreen : zscreens) {
            for (Zparam zparam : zparams) {
                if(zparam.getScrid().equals(zscreen.getId())){
                    zscreen.getParams().add(zparam);
                }
            }
        }
        zmodel.setScreens(zscreens);
    }


    public List<Zinp> findTreeList(String name) {
        Sqler cateSqler = new Sqler("te_prod_cate");
        cateSqler.addSelect("null as pid");
        cateSqler.addOrder("t.ornum");
        List<Zinp> list = jdbcDao.findInpList(cateSqler);

        Sqler serieSqler = new Sqler("te_prod_serie");
        serieSqler.addSelect("t.catid as pid");
        serieSqler.addOrder("t.ornum");
        List<Zinp> list2 = jdbcDao.findInpList(serieSqler);
        list.addAll(list2);

        Sqler modelSqler = new Sqler("te_prod_model");
        modelSqler.addSelect("t.serid as pid");
        modelSqler.addOrder("t.ornum");
        List<Zinp> list3 = jdbcDao.findInpList(modelSqler);
        list.addAll(list3);

        return buildByRecursive(list);
    }

    //使用递归方法建树
    private List<Zinp> buildByRecursive(List<Zinp> nodes) {
        List<Zinp> list = new ArrayList<>();
        for (Zinp node : nodes) {
            if (node.getPid() == null) {
                list.add(findChildrenByTier(node, nodes));
            } else {
                boolean flag = false;
                for (Zinp node2 : nodes) {
                    if (node.getPid().equals(node2.getId())) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    list.add(findChildrenByTier(node, nodes));
                }
            }
        }
        return list;
    }


    //递归查找子节点
    private Zinp findChildrenByTier(Zinp node, List<Zinp> nodes) {
        for (Zinp item : nodes) {
            if (node.getId().equals(item.getPid())) {
                if (node.getChildren() == null) {
                    node.setChildren(new ArrayList<>());
                }
                node.getChildren().add(findChildrenByTier(item, nodes));
            }
        }
        return node;
    }


    //----------bean注入------------
    @Autowired
    private TeProdModelRepo repo;

    @PostConstruct
    public void initDao() {
        super.setRepo(repo);
    }

}
