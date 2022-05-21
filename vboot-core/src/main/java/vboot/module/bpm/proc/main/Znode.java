package vboot.module.bpm.proc.main;

import lombok.Data;

@Data
public class Znode {

    private String nodid;//节点ID

    private String facno;//当前节点编号:N1,N2

    private String facna;//当前节点名称

    private String facty;//当前节点类型

    private String tarno;//目标节点编号

    private String tarna;//目标节点名称

    private String exman;//应处理人

    public Znode(String facno) {
        this.facno = facno;
    }

    public Znode() {

    }
}
