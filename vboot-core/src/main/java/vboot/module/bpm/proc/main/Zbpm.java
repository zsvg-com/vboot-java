package vboot.module.bpm.proc.main;

import lombok.Data;

@Data
public class Zbpm {
    private String todid;//待办ID

    private String temid;//流程模板ID

    private String proid;//流程实例ID

    private String prona;//流程实例名称

    private String nodid;//当前节点ID

    private String facno;//当前节点编号

    private String facna;//当前节点名称

    private String tarno;//目标节点编号

    private String tarna;//目标节点名称

    private Boolean retag=true;//驳回标记，驳回的节点通过后直接返回本节点

    private String bacid;//驳回后的流程重新提交时的bpm_proc_param的id

    private String tasid;//任务ID

    private String opnot;//操作：处理意见

    private String opurg;//操作：紧急程度

    private String opkey;//操作key:pass, reject

//    private String renod;//驳回的节点:N1,N2

    private String opinf;//操作名称:通过，驳回到谁，沟通谁

    private String chxml;//优化过的vboot可解析的的xml

    private String haman;//当前处理人ID

    private String exman;//应处理人ID

    public Zbpm() {
    }

    public Zbpm(String proid) {
        this.proid = proid;
    }

    public Zbpm(String proid,String prona) {
        this.proid = proid;
        this.prona = prona;
    }
}
