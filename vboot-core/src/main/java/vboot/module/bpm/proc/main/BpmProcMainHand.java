package vboot.module.bpm.proc.main;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.hibernate.internal.util.xml.XmlDocument;
import org.springframework.stereotype.Component;
import vboot.common.mvc.pojo.Zinp;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class BpmProcMainHand {

    public Znode procFlow(Zbpm zbpm, List<Znode> list, Znode znode) {
        //根据temid寻找xml的filename
        SAXReader reader = new SAXReader();
        Reader stringReader = new StringReader(zbpm.getChxml());
        Document document = null;
        try {
            document = reader.read(stringReader);
        } catch (DocumentException e) {
            System.out.println("xml解析异常");
            e.printStackTrace();
        }
        Element rootNode = document.getRootElement();
        Iterator<Element> it = rootNode.elementIterator();
        //查找当前节点的目标节点，如果已有（比如是驳回返回的）则不需要额外查找
        if (znode.getTarno() == null) {
            while (it.hasNext()) {
                Element node = it.next();
                if ("sequenceFlow".equals(node.getName())) {
                    if (znode.getFacno().equals(node.attribute("sourceRef").getValue())) {
//                    nextNode.setId(node.attribute("targetRef").getValue());
                        znode.setTarno(node.attribute("targetRef").getValue());
                        break;
                    }
                }
            }
        }
        //判断nextNode是否为审批节点
        return nodeFlow(rootNode, list, znode);
    }


    //节点流转
    //判断节点是否为审批节点，如果为审批节点则取处理人
    //      如果是条件分支，则根据分支条件流转到下一个节点，直到找到审批节点
    private Znode nodeFlow(Element rootNode, List<Znode> list, Znode znode) {
        if ("N3".equals(znode.getTarno())) {
            znode.setTarna("结束节点");
            Znode endNode = new Znode();
            endNode.setFacno("N3");
            endNode.setFacna("结束节点");
            endNode.setFacty("end");
            return endNode;
        }
        Iterator<Element> it = rootNode.elementIterator();
        String userid = "";
        while (it.hasNext()) {
            Element node = it.next();
            if ("task".equals(node.getName()) || "userTask".equals(node.getName())) {//节点为审批节点
                if (znode.getTarno().equals(node.attribute("id").getValue())) {
                    userid = node.attribute("assignee").getValue();
                    znode.setTarna(node.attribute("name").getValue());
                    System.out.println(znode);
                    //list.add(znode);
                    Znode nextNode = new Znode();
                    nextNode.setFacno(znode.getTarno());
                    nextNode.setFacna(znode.getTarna());
                    nextNode.setFacty("review");
                    nextNode.setExman(userid);
                    return nextNode;

//                    System.out.println("进入了：task");
//                    Iterator<Element> sonit = node.elementIterator();
//                    while (sonit.hasNext()) {
//                        Element son = sonit.next();
//                        if ("extensionElements".equals(son.getName())) {
//                            System.out.println("进入了：extensionElements");
//                            Iterator<Element> sunit = son.elementIterator();
//                            while (sunit.hasNext()) {
//                                Element sun = sunit.next();
//                                if ("formProperty".equals(sun.getName())) {
//                                    System.out.println("进入了：formProperty");
//                                    if ("userid".equals(sun.attribute("id").getValue())) {
//                                        userid = sun.attribute("name").getValue();
//                                        znode.setTarna(node.attribute("name").getValue());
//                                        System.out.println(znode);
//                                        //list.add(znode);
//                                        Znode nextNode = new Znode();
//                                        nextNode.setFacno(znode.getTarno());
//                                        nextNode.setFacna(znode.getTarna());
//                                        nextNode.setFacty("review");
//                                        nextNode.setExman(userid);
//                                        return nextNode;
//                                    }
//                                }
//                            }
//                            break;
//                        }
//                    }
                }
            } else if ("exclusiveGateway".equals(node.getName())) {//节点为条件分支
                String nextNodeId = "";
                if (znode.getTarno().equals(node.attribute("id").getValue())) {
                    znode.setTarna(node.attribute("name").getValue());
                    //list.add(znode);
                    it = rootNode.elementIterator();
                    while (it.hasNext()) {
                        Element xnode = it.next();
                        if ("sequenceFlow".equals(xnode.getName())) {
                            if (znode.getTarno().equals(xnode.attribute("sourceRef").getValue())) {
                                Iterator<Element> sunit = xnode.elementIterator();
                                if (checkConds(sunit)) {//满足条件时
                                    nextNodeId = xnode.attribute("targetRef").getValue();
                                    System.out.println("条件分支转到:" + nextNodeId);
                                    Znode nextNode = new Znode();
                                    nextNode.setFacno(znode.getTarno());
                                    nextNode.setFacna(znode.getTarna());
                                    nextNode.setFacty("condtion");
                                    nextNode.setTarno(nextNodeId);
                                    list.add(nextNode);
                                    return nodeFlow(rootNode, list, nextNode);
                                }
                            }
                        }
                    }
                    break;
                }
            }
            if (!"".equals(userid)) {
                break;
            }
        }
        return null;
    }

    private boolean checkConds(Iterator<Element> it) {
        while (it.hasNext()) {
            Element son = it.next();
            if ("extensionElements".equals(son.getName())) {
                System.out.println("进入了：extensionElements");
                Iterator<Element> sunit = son.elementIterator();
                while (sunit.hasNext()) {
                    Element sun = sunit.next();
                    if ("executionListener".equals(sun.getName())) {
                        System.out.println("进入了：executionListener");
                        if (!checkCond(sun.attribute("expression").getValue())) {
                            return false;
                        }
                        break;
                    }
                }
                break;
            }
        }
        return true;
    }

    private boolean checkCond(String expression) {
        System.out.println("条件为：" + expression);
        return true;
    }

    public Znode getNodeInfo(String chxml, String facno) {
        SAXReader reader = new SAXReader();
        Reader stringReader = new StringReader(chxml);
        Document document = null;
        try {
            document = reader.read(stringReader);
        } catch (DocumentException e) {
            System.out.println("xml解析异常");
            e.printStackTrace();
        }
        Element rootNode = document.getRootElement();
        Iterator<Element> it = rootNode.elementIterator();
        while (it.hasNext()) {
            Element node = it.next();
            if ("userTask".equals(node.getName()) || "task".equals(node.getName())) {
                if (facno.equals(node.attribute("id").getValue())) {
                    Znode znode = new Znode();
                    znode.setFacno(facno);
                    znode.setFacna(node.attribute("name").getValue());
                    znode.setExman(node.attribute("assignee").getValue());
                    return znode;
                }
            }
        }
        return null;
    }

    public Znode calcTarget(String chxml, String facno) {
        SAXReader reader = new SAXReader();
        Reader stringReader = new StringReader(chxml);
        Document document = null;
        try {
            document = reader.read(stringReader);
        } catch (DocumentException e) {
            System.out.println("xml解析异常");
            e.printStackTrace();
        }
        Element rootNode = document.getRootElement();
        Iterator<Element> it = rootNode.elementIterator();
        Znode currNode = new Znode();
        currNode.setFacno(facno);
        while (it.hasNext()) {
            Element node = it.next();
            if ("sequenceFlow".equals(node.getName())) {
                if (facno.equals(node.attribute("sourceRef").getValue())) {
                    currNode.setTarno(node.attribute("targetRef").getValue());
                    break;
                }
            }
        }
        List<Znode> list = new ArrayList<>();
        Znode nextNode = nodeFlow(rootNode, list, currNode);
        return nextNode;
    }

    public Znode getFirstNode(String xml, String facno) {
        //根据temid寻找xml的filename
        xml = "<?xml version=\"1.0\" encoding=\"gb2312\"?>"
                + "\n<process" +
                xml.split("bpmn2:process")[1].replaceAll("bpmn2:", "").replaceAll("activiti:", "") + "process>";

        SAXReader reader = new SAXReader();
        Reader stringReader = new StringReader(xml);
        Document document = null;
        try {
            document = reader.read(stringReader);
        } catch (DocumentException e) {
            System.out.println("xml解析异常");
            e.printStackTrace();
        }
        Element rootNode = document.getRootElement();

        Znode currNode = new Znode();
        currNode.setFacno(facno);

        Iterator<Element> it = rootNode.elementIterator();
        while (it.hasNext()) {
            Element node = it.next();
            if ("sequenceFlow".equals(node.getName())) {
                if (facno.equals(node.attribute("sourceRef").getValue())) {
                    currNode.setTarno(node.attribute("targetRef").getValue());
                    break;
                }
            }
        }
        List<Znode> list = new ArrayList<>();
        Znode nextNode = nodeFlow(rootNode, list, currNode);
        return nextNode;
    }

    public List<Zinp> GetAllLineList(String xml) {
        //根据temid寻找xml的filename
        xml = "<?xml version=\"1.0\" encoding=\"gb2312\"?>"
                + "\n<process" +
                xml.split("bpmn2:process")[1].replaceAll("bpmn2:", "").replaceAll("activiti:", "") + "process>";

        SAXReader reader = new SAXReader();
        Reader stringReader = new StringReader(xml);
        Document document = null;
        try {
            document = reader.read(stringReader);
        } catch (DocumentException e) {
            System.out.println("xml解析异常");
            e.printStackTrace();
        }
        Element rootNode = document.getRootElement();

        List<Zinp> list = new ArrayList<>();
        Iterator<Element> it = rootNode.elementIterator();
        while (it.hasNext()) {
            Element node = it.next();
            if ("sequenceFlow".equals(node.getName())) {
                Zinp zinp = new Zinp();
                zinp.setId(node.attribute("id").getValue() + "");
                zinp.setName(node.attribute("sourceRef").getValue() + "");
                zinp.setPid(node.attribute("targetRef").getValue() + "");
                list.add(zinp);
            }
        }
        return list;
    }


//    String xmlstr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
//            "<bpmn2:definitions xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:bpmn2=\"http://www.omg.org/spec/BPMN/20100524/MODEL\" xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\" xmlns:dc=\"http://www.omg.org/spec/DD/20100524/DC\" xmlns:di=\"http://www.omg.org/spec/DD/20100524/DI\" xmlns:activiti=\"http://activiti.org/bpmn\" id=\"sample-diagram\" targetNamespace=\"http://activiti.org/bpmn\" xsi:schemaLocation=\"http://www.omg.org/spec/BPMN/20100524/MODEL BPMN20.xsd\">\n" +
//            "  <bpmn2:process id=\"Process_1\" name=\"1\" isExecutable=\"true\">\n" +
//            "    <bpmn2:startEvent id=\"N1\" name=\"开始节点\">\n" +
//            "      <bpmn2:outgoing>L1</bpmn2:outgoing>\n" +
//            "    </bpmn2:startEvent>\n" +
//            "    <bpmn2:sequenceFlow id=\"L1\" sourceRef=\"N1\" targetRef=\"N2\" />\n" +
//            "    <bpmn2:exclusiveGateway id=\"N9\" name=\"条件分支\">\n" +
//            "      <bpmn2:documentation>判断审批金额是否大于100</bpmn2:documentation>\n" +
//            "      <bpmn2:incoming>L4</bpmn2:incoming>\n" +
//            "      <bpmn2:outgoing>L6</bpmn2:outgoing>\n" +
//            "      <bpmn2:outgoing>L5</bpmn2:outgoing>\n" +
//            "    </bpmn2:exclusiveGateway>\n" +
//            "    <bpmn2:sequenceFlow id=\"L4\" sourceRef=\"N4\" targetRef=\"N9\" />\n" +
//            "    <bpmn2:sequenceFlow id=\"L6\" name=\"王五分支线\" sourceRef=\"N9\" targetRef=\"N6\">\n" +
//            "      <bpmn2:extensionElements>\n" +
//            "        <activiti:executionListener expression=\"money&#60;=100\" event=\"take\" />\n" +
//            "      </bpmn2:extensionElements>\n" +
//            "    </bpmn2:sequenceFlow>\n" +
//            "    <bpmn2:sequenceFlow id=\"L5\" name=\"李四分支线\" sourceRef=\"N9\" targetRef=\"N5\">\n" +
//            "      <bpmn2:extensionElements>\n" +
//            "        <activiti:executionListener expression=\"money&#62;100\" event=\"take\" />\n" +
//            "      </bpmn2:extensionElements>\n" +
//            "    </bpmn2:sequenceFlow>\n" +
//            "    <bpmn2:sequenceFlow id=\"L7\" sourceRef=\"N5\" targetRef=\"N7\" />\n" +
//            "    <bpmn2:sequenceFlow id=\"L8\" sourceRef=\"N6\" targetRef=\"N7\" />\n" +
//            "    <bpmn2:endEvent id=\"N3\" name=\"结束节点\">\n" +
//            "      <bpmn2:incoming>L2</bpmn2:incoming>\n" +
//            "    </bpmn2:endEvent>\n" +
//            "    <bpmn2:sequenceFlow id=\"L2\" sourceRef=\"N7\" targetRef=\"N3\" />\n" +
//            "    <bpmn2:sequenceFlow id=\"L3\" sourceRef=\"N2\" targetRef=\"N4\" />\n" +
//            "    <bpmn2:userTask id=\"N2\" name=\"起草节点\" activiti:assignee=\"sa\" activiti:candidateUsers=\"\">\n" +
//            "      <bpmn2:documentation>起草节点，表单数据一般从绑定的表单提取</bpmn2:documentation>\n" +
//            "      <bpmn2:extensionElements>\n" +
//            "        <activiti:formProperty id=\"userid\" type=\"string\" />\n" +
//            "        <activiti:formProperty id=\"money\" type=\"int\" />\n" +
//            "        <activiti:properties>\n" +
//            "          <activiti:property name=\"编辑\" value=\"edit\" />\n" +
//            "          <activiti:property name=\"撤回\" value=\"back\" />\n" +
//            "          <activiti:property name=\"提交\" value=\"commit\" />\n" +
//            "        </activiti:properties>\n" +
//            "      </bpmn2:extensionElements>\n" +
//            "      <bpmn2:incoming>L1</bpmn2:incoming>\n" +
//            "      <bpmn2:outgoing>L3</bpmn2:outgoing>\n" +
//            "    </bpmn2:userTask>\n" +
//            "    <bpmn2:userTask id=\"N4\" name=\"张三审批\" activiti:assignee=\"z3\" activiti:candidateUsers=\"\">\n" +
//            "      <bpmn2:documentation>审批节点</bpmn2:documentation>\n" +
//            "      <bpmn2:extensionElements>\n" +
//            "        <activiti:properties>\n" +
//            "          <activiti:property name=\"审批\" value=\"approve\" />\n" +
//            "          <activiti:property name=\"驳回\" value=\"reject\" />\n" +
//            "        </activiti:properties>\n" +
//            "      </bpmn2:extensionElements>\n" +
//            "      <bpmn2:incoming>L3</bpmn2:incoming>\n" +
//            "      <bpmn2:outgoing>L4</bpmn2:outgoing>\n" +
//            "    </bpmn2:userTask>\n" +
//            "    <bpmn2:userTask id=\"N5\" name=\"李四审批\" activiti:assignee=\"l4\" activiti:candidateUsers=\"\">\n" +
//            "      <bpmn2:extensionElements>\n" +
//            "        <activiti:properties>\n" +
//            "          <activiti:property name=\"审批\" value=\"approve\" />\n" +
//            "          <activiti:property name=\"驳回\" value=\"reject\" />\n" +
//            "        </activiti:properties>\n" +
//            "      </bpmn2:extensionElements>\n" +
//            "      <bpmn2:incoming>L5</bpmn2:incoming>\n" +
//            "      <bpmn2:outgoing>L7</bpmn2:outgoing>\n" +
//            "    </bpmn2:userTask>\n" +
//            "    <bpmn2:userTask id=\"N6\" name=\"王五审批\" activiti:assignee=\"w5\" activiti:candidateUsers=\"\">\n" +
//            "      <bpmn2:extensionElements>\n" +
//            "        <activiti:properties>\n" +
//            "          <activiti:property name=\"审批\" value=\"approve\" />\n" +
//            "          <activiti:property name=\"驳回\" value=\"reject\" />\n" +
//            "        </activiti:properties>\n" +
//            "      </bpmn2:extensionElements>\n" +
//            "      <bpmn2:incoming>L6</bpmn2:incoming>\n" +
//            "      <bpmn2:outgoing>L8</bpmn2:outgoing>\n" +
//            "    </bpmn2:userTask>\n" +
//            "    <bpmn2:userTask id=\"N7\" name=\"赵六审批\" activiti:assignee=\"zhao6\" activiti:candidateUsers=\"\">\n" +
//            "      <bpmn2:extensionElements>\n" +
//            "        <activiti:formProperty id=\"userid\" type=\"string\" />\n" +
//            "        <activiti:taskListener class=\"do some thing\" event=\"complete\" />\n" +
//            "        <activiti:properties>\n" +
//            "          <activiti:property name=\"审批\" value=\"approve\" />\n" +
//            "        </activiti:properties>\n" +
//            "      </bpmn2:extensionElements>\n" +
//            "      <bpmn2:incoming>L7</bpmn2:incoming>\n" +
//            "      <bpmn2:incoming>L8</bpmn2:incoming>\n" +
//            "      <bpmn2:outgoing>L2</bpmn2:outgoing>\n" +
//            "    </bpmn2:userTask>\n" +
//            "  </bpmn2:process>\n" +
//            "  <bpmndi:BPMNDiagram id=\"BPMNDiagram_1\">\n" +
//            "    <bpmndi:BPMNPlane id=\"BPMNPlane_1\" bpmnElement=\"Process_1\">\n" +
//            "      <bpmndi:BPMNEdge id=\"Flow_0uh8wmt_di\" bpmnElement=\"L3\">\n" +
//            "        <di:waypoint x=\"560\" y=\"200\" />\n" +
//            "        <di:waypoint x=\"560\" y=\"250\" />\n" +
//            "      </bpmndi:BPMNEdge>\n" +
//            "      <bpmndi:BPMNEdge id=\"Flow_01hb865_di\" bpmnElement=\"L2\">\n" +
//            "        <di:waypoint x=\"560\" y=\"670\" />\n" +
//            "        <di:waypoint x=\"560\" y=\"732\" />\n" +
//            "      </bpmndi:BPMNEdge>\n" +
//            "      <bpmndi:BPMNEdge id=\"Flow_1t81399_di\" bpmnElement=\"L8\">\n" +
//            "        <di:waypoint x=\"690\" y=\"550\" />\n" +
//            "        <di:waypoint x=\"690\" y=\"630\" />\n" +
//            "        <di:waypoint x=\"610\" y=\"630\" />\n" +
//            "      </bpmndi:BPMNEdge>\n" +
//            "      <bpmndi:BPMNEdge id=\"Flow_00vdr2t_di\" bpmnElement=\"L7\">\n" +
//            "        <di:waypoint x=\"430\" y=\"550\" />\n" +
//            "        <di:waypoint x=\"430\" y=\"630\" />\n" +
//            "        <di:waypoint x=\"510\" y=\"630\" />\n" +
//            "      </bpmndi:BPMNEdge>\n" +
//            "      <bpmndi:BPMNEdge id=\"Flow_1bnjbaa_di\" bpmnElement=\"L5\">\n" +
//            "        <di:waypoint x=\"535\" y=\"400\" />\n" +
//            "        <di:waypoint x=\"430\" y=\"400\" />\n" +
//            "        <di:waypoint x=\"430\" y=\"470\" />\n" +
//            "        <bpmndi:BPMNLabel>\n" +
//            "          <dc:Bounds x=\"456\" y=\"382\" width=\"55\" height=\"14\" />\n" +
//            "        </bpmndi:BPMNLabel>\n" +
//            "      </bpmndi:BPMNEdge>\n" +
//            "      <bpmndi:BPMNEdge id=\"Flow_12ug1tp_di\" bpmnElement=\"L6\">\n" +
//            "        <di:waypoint x=\"585\" y=\"400\" />\n" +
//            "        <di:waypoint x=\"690\" y=\"400\" />\n" +
//            "        <di:waypoint x=\"690\" y=\"470\" />\n" +
//            "        <bpmndi:BPMNLabel>\n" +
//            "          <dc:Bounds x=\"611\" y=\"382\" width=\"56\" height=\"14\" />\n" +
//            "        </bpmndi:BPMNLabel>\n" +
//            "      </bpmndi:BPMNEdge>\n" +
//            "      <bpmndi:BPMNEdge id=\"Flow_1miguqf_di\" bpmnElement=\"L4\">\n" +
//            "        <di:waypoint x=\"560\" y=\"330\" />\n" +
//            "        <di:waypoint x=\"560\" y=\"375\" />\n" +
//            "      </bpmndi:BPMNEdge>\n" +
//            "      <bpmndi:BPMNEdge id=\"Flow_1u6pmzo_di\" bpmnElement=\"L1\">\n" +
//            "        <di:waypoint x=\"560\" y=\"68\" />\n" +
//            "        <di:waypoint x=\"560\" y=\"120\" />\n" +
//            "      </bpmndi:BPMNEdge>\n" +
//            "      <bpmndi:BPMNShape id=\"Event_0byql27_di\" bpmnElement=\"N1\">\n" +
//            "        <dc:Bounds x=\"542\" y=\"32\" width=\"36\" height=\"36\" />\n" +
//            "        <bpmndi:BPMNLabel>\n" +
//            "          <dc:Bounds x=\"539\" y=\"2\" width=\"43\" height=\"14\" />\n" +
//            "        </bpmndi:BPMNLabel>\n" +
//            "      </bpmndi:BPMNShape>\n" +
//            "      <bpmndi:BPMNShape id=\"Gateway_0qd58o6_di\" bpmnElement=\"N9\" isMarkerVisible=\"true\">\n" +
//            "        <dc:Bounds x=\"535\" y=\"375\" width=\"50\" height=\"50\" />\n" +
//            "        <bpmndi:BPMNLabel>\n" +
//            "          <dc:Bounds x=\"538\" y=\"432\" width=\"44\" height=\"14\" />\n" +
//            "        </bpmndi:BPMNLabel>\n" +
//            "      </bpmndi:BPMNShape>\n" +
//            "      <bpmndi:BPMNShape id=\"Event_1h4oob7_di\" bpmnElement=\"N3\">\n" +
//            "        <dc:Bounds x=\"542\" y=\"732\" width=\"36\" height=\"36\" />\n" +
//            "        <bpmndi:BPMNLabel>\n" +
//            "          <dc:Bounds x=\"539\" y=\"775\" width=\"43\" height=\"14\" />\n" +
//            "        </bpmndi:BPMNLabel>\n" +
//            "      </bpmndi:BPMNShape>\n" +
//            "      <bpmndi:BPMNShape id=\"Activity_0g48n8q_di\" bpmnElement=\"N2\">\n" +
//            "        <dc:Bounds x=\"510\" y=\"120\" width=\"100\" height=\"80\" />\n" +
//            "      </bpmndi:BPMNShape>\n" +
//            "      <bpmndi:BPMNShape id=\"Activity_04wpn1b_di\" bpmnElement=\"N4\">\n" +
//            "        <dc:Bounds x=\"510\" y=\"250\" width=\"100\" height=\"80\" />\n" +
//            "      </bpmndi:BPMNShape>\n" +
//            "      <bpmndi:BPMNShape id=\"Activity_0j8meci_di\" bpmnElement=\"N5\">\n" +
//            "        <dc:Bounds x=\"380\" y=\"470\" width=\"100\" height=\"80\" />\n" +
//            "      </bpmndi:BPMNShape>\n" +
//            "      <bpmndi:BPMNShape id=\"Activity_0pa1f64_di\" bpmnElement=\"N6\">\n" +
//            "        <dc:Bounds x=\"640\" y=\"470\" width=\"100\" height=\"80\" />\n" +
//            "      </bpmndi:BPMNShape>\n" +
//            "      <bpmndi:BPMNShape id=\"Activity_0qmb4lo_di\" bpmnElement=\"N7\">\n" +
//            "        <dc:Bounds x=\"510\" y=\"590\" width=\"100\" height=\"80\" />\n" +
//            "      </bpmndi:BPMNShape>\n" +
//            "    </bpmndi:BPMNPlane>\n" +
//            "  </bpmndi:BPMNDiagram>\n" +
//            "</bpmn2:definitions>\n";
//
//    String xmlstr2 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
//            "<bpmn2:definitions xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:bpmn2=\"http://www.omg.org/spec/BPMN/20100524/MODEL\" xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\" xmlns:dc=\"http://www.omg.org/spec/DD/20100524/DC\" xmlns:di=\"http://www.omg.org/spec/DD/20100524/DI\" xmlns:activiti=\"http://activiti.org/bpmn\" id=\"sample-diagram\" targetNamespace=\"http://activiti.org/bpmn\" xsi:schemaLocation=\"http://www.omg.org/spec/BPMN/20100524/MODEL BPMN20.xsd\">\n" +
//            "  <bpmn2:process id=\"Process_1\" name=\"1\" isExecutable=\"true\">\n" +
//            "    <bpmn2:startEvent id=\"N1\" name=\"开始节点\">\n" +
//            "      <bpmn2:outgoing>L1</bpmn2:outgoing>\n" +
//            "    </bpmn2:startEvent>\n" +
//            "    <bpmn2:task id=\"N4\" name=\"部门经理张三审批\">\n" +
//            "      <bpmn2:documentation>审批节点</bpmn2:documentation>\n" +
//            "      <bpmn2:extensionElements>\n" +
//            "        <activiti:formProperty id=\"userid\" type=\"string\" name=\"z3\" />\n" +
//            "        <activiti:properties>\n" +
//            "          <activiti:property name=\"审批\" value=\"approve\" />\n" +
//            "          <activiti:property name=\"驳回\" value=\"reject\" />\n" +
//            "        </activiti:properties>\n" +
//            "      </bpmn2:extensionElements>\n" +
//            "      <bpmn2:incoming>L3</bpmn2:incoming>\n" +
//            "      <bpmn2:outgoing>L4</bpmn2:outgoing>\n" +
//            "    </bpmn2:task>\n" +
//            "    <bpmn2:sequenceFlow id=\"L1\" sourceRef=\"N1\" targetRef=\"N2\" />\n" +
//            "    <bpmn2:exclusiveGateway id=\"N9\" name=\"条件分支\">\n" +
//            "      <bpmn2:documentation>判断审批金额是否大于100</bpmn2:documentation>\n" +
//            "      <bpmn2:incoming>L4</bpmn2:incoming>\n" +
//            "      <bpmn2:outgoing>L6</bpmn2:outgoing>\n" +
//            "      <bpmn2:outgoing>L5</bpmn2:outgoing>\n" +
//            "    </bpmn2:exclusiveGateway>\n" +
//            "    <bpmn2:sequenceFlow id=\"L4\" sourceRef=\"N4\" targetRef=\"N9\" />\n" +
//            "    <bpmn2:task id=\"N5\" name=\"李四审批\">\n" +
//            "      <bpmn2:extensionElements>\n" +
//            "        <activiti:formProperty id=\"userid\" type=\"string\" name=\"l4\" />\n" +
//            "        <activiti:properties>\n" +
//            "          <activiti:property name=\"审批\" value=\"approve\" />\n" +
//            "          <activiti:property name=\"驳回\" value=\"reject\" />\n" +
//            "        </activiti:properties>\n" +
//            "      </bpmn2:extensionElements>\n" +
//            "      <bpmn2:incoming>L5</bpmn2:incoming>\n" +
//            "      <bpmn2:outgoing>L7</bpmn2:outgoing>\n" +
//            "    </bpmn2:task>\n" +
//            "    <bpmn2:task id=\"N6\" name=\"王五审批\">\n" +
//            "      <bpmn2:extensionElements>\n" +
//            "        <activiti:formProperty id=\"userid\" type=\"string\" name=\"w5\" />\n" +
//            "        <activiti:properties>\n" +
//            "          <activiti:property name=\"审批\" value=\"approve\" />\n" +
//            "          <activiti:property name=\"驳回\" value=\"reject\" />\n" +
//            "        </activiti:properties>\n" +
//            "      </bpmn2:extensionElements>\n" +
//            "      <bpmn2:incoming>L6</bpmn2:incoming>\n" +
//            "      <bpmn2:outgoing>L8</bpmn2:outgoing>\n" +
//            "    </bpmn2:task>\n" +
//            "    <bpmn2:sequenceFlow id=\"L6\" name=\"王五分支线\" sourceRef=\"N9\" targetRef=\"N6\">\n" +
//            "      <bpmn2:extensionElements>\n" +
//            "        <activiti:executionListener expression=\"money&#60;=100\" event=\"take\" />\n" +
//            "      </bpmn2:extensionElements>\n" +
//            "    </bpmn2:sequenceFlow>\n" +
//            "    <bpmn2:sequenceFlow id=\"L5\" name=\"李四分支线\" sourceRef=\"N9\" targetRef=\"N5\">\n" +
//            "      <bpmn2:extensionElements>\n" +
//            "        <activiti:executionListener expression=\"money&#62;100\" event=\"take\" />\n" +
//            "      </bpmn2:extensionElements>\n" +
//            "    </bpmn2:sequenceFlow>\n" +
//            "    <bpmn2:task id=\"N7\" name=\"赵六审批\">\n" +
//            "      <bpmn2:extensionElements>\n" +
//            "        <activiti:formProperty id=\"userid\" type=\"string\" name=\"zhao6\" />\n" +
//            "        <activiti:taskListener class=\"do some thing\" event=\"complete\" />\n" +
//            "        <activiti:properties>\n" +
//            "          <activiti:property name=\"审批\" value=\"approve\" />\n" +
//            "        </activiti:properties>\n" +
//            "      </bpmn2:extensionElements>\n" +
//            "      <bpmn2:incoming>L7</bpmn2:incoming>\n" +
//            "      <bpmn2:incoming>L8</bpmn2:incoming>\n" +
//            "      <bpmn2:outgoing>L2</bpmn2:outgoing>\n" +
//            "    </bpmn2:task>\n" +
//            "    <bpmn2:sequenceFlow id=\"L7\" sourceRef=\"N5\" targetRef=\"N7\" />\n" +
//            "    <bpmn2:sequenceFlow id=\"L8\" sourceRef=\"N6\" targetRef=\"N7\" />\n" +
//            "    <bpmn2:endEvent id=\"N3\" name=\"结束节点\">\n" +
//            "      <bpmn2:incoming>L2</bpmn2:incoming>\n" +
//            "    </bpmn2:endEvent>\n" +
//            "    <bpmn2:sequenceFlow id=\"L2\" sourceRef=\"N7\" targetRef=\"N3\" />\n" +
//            "    <bpmn2:task id=\"N2\" name=\"起草节点\">\n" +
//            "      <bpmn2:documentation>起草节点，表单数据一般从绑定的表单提取</bpmn2:documentation>\n" +
//            "      <bpmn2:extensionElements>\n" +
//            "        <activiti:formProperty id=\"userid\" type=\"string\" name=\"sa\" />\n" +
//            "        <activiti:formProperty id=\"money\" type=\"int\" name=\"100\" />\n" +
//            "        <activiti:properties>\n" +
//            "          <activiti:property name=\"编辑\" value=\"edit\" />\n" +
//            "          <activiti:property name=\"撤回\" value=\"back\" />\n" +
//            "          <activiti:property name=\"提交\" value=\"commit\" />\n" +
//            "        </activiti:properties>\n" +
//            "      </bpmn2:extensionElements>\n" +
//            "      <bpmn2:incoming>L1</bpmn2:incoming>\n" +
//            "      <bpmn2:outgoing>L3</bpmn2:outgoing>\n" +
//            "    </bpmn2:task>\n" +
//            "    <bpmn2:sequenceFlow id=\"L3\" sourceRef=\"N2\" targetRef=\"N4\" />\n" +
//            "  </bpmn2:process>\n" +
//            "  <bpmndi:BPMNDiagram id=\"BPMNDiagram_1\">\n" +
//            "    <bpmndi:BPMNPlane id=\"BPMNPlane_1\" bpmnElement=\"Process_1\">\n" +
//            "      <bpmndi:BPMNEdge id=\"Flow_0uh8wmt_di\" bpmnElement=\"L3\">\n" +
//            "        <di:waypoint x=\"560\" y=\"200\" />\n" +
//            "        <di:waypoint x=\"560\" y=\"250\" />\n" +
//            "      </bpmndi:BPMNEdge>\n" +
//            "      <bpmndi:BPMNEdge id=\"Flow_01hb865_di\" bpmnElement=\"L2\">\n" +
//            "        <di:waypoint x=\"560\" y=\"670\" />\n" +
//            "        <di:waypoint x=\"560\" y=\"732\" />\n" +
//            "      </bpmndi:BPMNEdge>\n" +
//            "      <bpmndi:BPMNEdge id=\"Flow_1t81399_di\" bpmnElement=\"L8\">\n" +
//            "        <di:waypoint x=\"690\" y=\"550\" />\n" +
//            "        <di:waypoint x=\"690\" y=\"630\" />\n" +
//            "        <di:waypoint x=\"610\" y=\"630\" />\n" +
//            "      </bpmndi:BPMNEdge>\n" +
//            "      <bpmndi:BPMNEdge id=\"Flow_00vdr2t_di\" bpmnElement=\"L7\">\n" +
//            "        <di:waypoint x=\"430\" y=\"550\" />\n" +
//            "        <di:waypoint x=\"430\" y=\"630\" />\n" +
//            "        <di:waypoint x=\"510\" y=\"630\" />\n" +
//            "      </bpmndi:BPMNEdge>\n" +
//            "      <bpmndi:BPMNEdge id=\"Flow_1bnjbaa_di\" bpmnElement=\"L5\">\n" +
//            "        <di:waypoint x=\"535\" y=\"400\" />\n" +
//            "        <di:waypoint x=\"430\" y=\"400\" />\n" +
//            "        <di:waypoint x=\"430\" y=\"470\" />\n" +
//            "        <bpmndi:BPMNLabel>\n" +
//            "          <dc:Bounds x=\"456\" y=\"382\" width=\"55\" height=\"14\" />\n" +
//            "        </bpmndi:BPMNLabel>\n" +
//            "      </bpmndi:BPMNEdge>\n" +
//            "      <bpmndi:BPMNEdge id=\"Flow_12ug1tp_di\" bpmnElement=\"L6\">\n" +
//            "        <di:waypoint x=\"585\" y=\"400\" />\n" +
//            "        <di:waypoint x=\"690\" y=\"400\" />\n" +
//            "        <di:waypoint x=\"690\" y=\"470\" />\n" +
//            "        <bpmndi:BPMNLabel>\n" +
//            "          <dc:Bounds x=\"611\" y=\"382\" width=\"55\" height=\"14\" />\n" +
//            "        </bpmndi:BPMNLabel>\n" +
//            "      </bpmndi:BPMNEdge>\n" +
//            "      <bpmndi:BPMNEdge id=\"Flow_1miguqf_di\" bpmnElement=\"L4\">\n" +
//            "        <di:waypoint x=\"560\" y=\"330\" />\n" +
//            "        <di:waypoint x=\"560\" y=\"375\" />\n" +
//            "      </bpmndi:BPMNEdge>\n" +
//            "      <bpmndi:BPMNEdge id=\"Flow_1u6pmzo_di\" bpmnElement=\"L1\">\n" +
//            "        <di:waypoint x=\"560\" y=\"68\" />\n" +
//            "        <di:waypoint x=\"560\" y=\"120\" />\n" +
//            "      </bpmndi:BPMNEdge>\n" +
//            "      <bpmndi:BPMNShape id=\"Event_0byql27_di\" bpmnElement=\"N1\">\n" +
//            "        <dc:Bounds x=\"542\" y=\"32\" width=\"36\" height=\"36\" />\n" +
//            "        <bpmndi:BPMNLabel>\n" +
//            "          <dc:Bounds x=\"539\" y=\"2\" width=\"43\" height=\"14\" />\n" +
//            "        </bpmndi:BPMNLabel>\n" +
//            "      </bpmndi:BPMNShape>\n" +
//            "      <bpmndi:BPMNShape id=\"Activity_03bpqkm_di\" bpmnElement=\"N4\">\n" +
//            "        <dc:Bounds x=\"510\" y=\"250\" width=\"100\" height=\"80\" />\n" +
//            "      </bpmndi:BPMNShape>\n" +
//            "      <bpmndi:BPMNShape id=\"Gateway_0qd58o6_di\" bpmnElement=\"N9\" isMarkerVisible=\"true\">\n" +
//            "        <dc:Bounds x=\"535\" y=\"375\" width=\"50\" height=\"50\" />\n" +
//            "        <bpmndi:BPMNLabel>\n" +
//            "          <dc:Bounds x=\"538\" y=\"432\" width=\"44\" height=\"14\" />\n" +
//            "        </bpmndi:BPMNLabel>\n" +
//            "      </bpmndi:BPMNShape>\n" +
//            "      <bpmndi:BPMNShape id=\"Activity_09lsftt_di\" bpmnElement=\"N5\">\n" +
//            "        <dc:Bounds x=\"380\" y=\"470\" width=\"100\" height=\"80\" />\n" +
//            "      </bpmndi:BPMNShape>\n" +
//            "      <bpmndi:BPMNShape id=\"Activity_1fk25zh_di\" bpmnElement=\"N6\">\n" +
//            "        <dc:Bounds x=\"640\" y=\"470\" width=\"100\" height=\"80\" />\n" +
//            "      </bpmndi:BPMNShape>\n" +
//            "      <bpmndi:BPMNShape id=\"Activity_07aweq6_di\" bpmnElement=\"N7\">\n" +
//            "        <dc:Bounds x=\"510\" y=\"590\" width=\"100\" height=\"80\" />\n" +
//            "      </bpmndi:BPMNShape>\n" +
//            "      <bpmndi:BPMNShape id=\"Event_1h4oob7_di\" bpmnElement=\"N3\">\n" +
//            "        <dc:Bounds x=\"542\" y=\"732\" width=\"36\" height=\"36\" />\n" +
//            "      </bpmndi:BPMNShape>\n" +
//            "      <bpmndi:BPMNShape id=\"Activity_0nlub11_di\" bpmnElement=\"N2\">\n" +
//            "        <dc:Bounds x=\"510\" y=\"120\" width=\"100\" height=\"80\" />\n" +
//            "      </bpmndi:BPMNShape>\n" +
//            "    </bpmndi:BPMNPlane>\n" +
//            "  </bpmndi:BPMNDiagram>\n" +
//            "</bpmn2:definitions>\n";
}
