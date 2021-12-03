package com.zsvg.vboot.module.ass.num.main;

import com.zsvg.vboot.common.mvc.api.PageData;
import com.zsvg.vboot.common.mvc.dao.JdbcDao;
import com.zsvg.vboot.common.mvc.dao.Sqler;
import com.zsvg.vboot.common.util.lang.XstringUtil;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class AssNumMainService {

    public String getDateNum(AssNumMain num, String dateType) {
        //获取 是否被修改过或新添加的 字段的值
        //如果  是否被修改过或新添加的=='Y'
        if (num.getNflag()) {
            //生成第一个流水号 0001
            String firstSerialNum = NumUtil.getFirstSerialNum(num.getNulen());
            //计算下一个流水号 0002
            String nextSerialNum = NumUtil.getNextSerialNum(firstSerialNum);
            //获取系统的当前时间 格式yyyyMMdd  20140906
            String curDate = DateFormatUtils.format(new Date(), dateType);

            //生成客户编码
            //编码前缀+"-"+利用日期位格式生成当前的日期[yyyyMMdd ]+"-"+0001  c-20110914-0001
            String back = (num.getNupre() == null ? "" : num.getNupre()) + DateFormatUtils.format(new Date(), dateType) + firstSerialNum;
            //修改代码规则表
            //下一个流水号="0002"
            num.setNunex(nextSerialNum);
            //当前日期  20140907
            num.setCudat(curDate);
            //是否被修改过='N'
            num.setNflag(false);
            repo.save(num);
            return back;
        } else {
            //是否被修改过或新添加的=='N'
            //获取代码规则表中的当前日期字段的值
            String curDate = num.getCudat();
            //获取系统的当前日期
            String sysCurDate = DateFormatUtils.format(new Date(), dateType);
            //如果代码规则表中的当前日期字段的值==系统的当前日期
            if (curDate.equals(sysCurDate)) {
                //获取下一个流水号 ="0002"
                String nextseq = num.getNunex();
                //计算新的流水号 0003
                String nextSerialNum = NumUtil.getNextSerialNum(nextseq);
                //生成客户编码
                //编码前缀+"-"+利用日期位格式生成当前的日期[yyyyMMdd ]+"-"+0001
                String back = (num.getNupre() == null ? "" : num.getNupre()) + DateFormatUtils.format(new Date(), dateType) + nextseq;
                //修改代码规则表
                //下一个流水号="0003"
                num.setNunex(nextSerialNum);
                //当前日期  20140908
                //是否被修改过='N'
                repo.save(num);
                return back;
            } else { //如果代码规则表中的当前日期字段的值!=系统的当前日期、

                //生成第一个流水号 0001
                String firstSerialNum = NumUtil.getFirstSerialNum(num.getNulen());
                //计算下一个流水号 0002
                String nextSerialNum = NumUtil.getNextSerialNum(firstSerialNum);
                //生成客户编码
                //编码前缀+"-"+利用日期位格式生成当前的日期[yyyyMMdd ]+"-"+0001
                String back = (num.getNupre() == null ? "" : num.getNupre()) + DateFormatUtils.format(new Date(), dateType) + firstSerialNum;
                //修改代码规则表
                //下一个流水号="0002"
                num.setNunex(nextSerialNum);
                //当前日期  20110915
                num.setCudat(sysCurDate);
                //是否被修改过='N'
                num.setNflag(false);
                repo.save(num);
                return back;
            }
        }
    }


    public String getPureNum(AssNumMain num) {
        if (num.getNflag()) {
            String firstSerialNum = NumUtil.getFirstSerialNum(num.getNulen());
            String nextSerialNum = NumUtil.getNextSerialNum(firstSerialNum);
            num.setNunex(nextSerialNum);
            num.setNflag(false);
            repo.save(num);
            return (num.getNupre() == null ? "" : num.getNupre()) + firstSerialNum;
        } else {
            String nextseq = num.getNunex();
            String nextSerialNum = NumUtil.getNextSerialNum(nextseq);
            num.setNunex(nextSerialNum);
            repo.save(num);
            return (num.getNupre() == null ? "" : num.getNupre()) + nextseq;
        }
    }

//     <select name="mode">
//      <option value="uuid">UUID</option>
//      <option value="manual">手动改</option>
//      <option value="nodate">无日期</option>
//      <option value="yyyymmdd">年月日YYYYMMDD</option>
//      <option value="yymmdd">年月日YYMMDD</option>
//      <option value="yyyymm">年月YYYYMM</option>
//      <option value="yymm">年月YYMM</option>
//      <option value="yy">年YY</option>
//     </select>

    public String getNum(String id){
        AssNumMain num = repo.findById(id).get();
        String number = "";
        switch(num.getNumod()){
            case "uuid":
                number = XstringUtil.getUUID();
                break;
            case "nodate":
                number = getPureNum(num);
                break;
            case "yyyymmdd":
                number = getDateNum(num,"yyyyMMdd");
                break;
            case "yymmdd":
                number = getDateNum(num,"yyMMdd");
                break;
            case "yyyymm":
                number = getDateNum(num,"yyyyMM");
                break;
            case "yymm":
                number = getDateNum(num,"yyMM");
                break;
            case "yy":
                number = getDateNum(num,"yy");
                break;
        }
        return number;
    }

    @Transactional(readOnly = true)
    public PageData findPageData(Sqler sqler) {
        return jdbcDao.findPageData(sqler);
    }

    @Autowired
    protected JdbcDao jdbcDao;


    @Autowired
    private AssNumMainRepo repo;


}
