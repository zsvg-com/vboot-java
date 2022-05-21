package com.abc.test.mybpmn;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.Iterator;
import java.util.List;


@SpringBootTest
public class MyTest1 {

    @Test
    public void parse(){
        String filename="G:\\vboot\\vboot-java\\vboot-application\\src\\test\\java\\com\\abc\\test\\mybpmn\\bpmn.xml";
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(new File(filename));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        // 通过document对象获取根节点bookstore
        Element bookStore = document.getRootElement();
        // 通过element对象的elementIterator方法获取迭代器
        Iterator it = bookStore.elementIterator();
        // 遍历迭代器，获取根节点中的信息（书籍）
        while (it.hasNext()) {
            System.out.println("=====开始遍历某一本书=====");
            Element book = (Element) it.next();
            // 获取book的属性名以及 属性值
            List<Attribute> bookAttrs = book.attributes();
            for (Attribute attr : bookAttrs) {
                System.out.println("属性名：" + attr.getName() + "--属性值："
                        + attr.getValue());
            }
            Iterator itt = book.elementIterator();
            while (itt.hasNext()) {
                Element bookChild = (Element) itt.next();
                System.out.println("节点名：" + bookChild.getName() + "--节点值：" + bookChild.getStringValue());
            }
            System.out.println("=====结束遍历某一本书=====");
        }

    }

//    @Test
//    public void parse2(){
//        XtimeUtil.calStart();
//        try {
//            operService.dStart("");
//        } catch (DocumentException e) {
//            e.printStackTrace();
//        }
//        XtimeUtil.calEnd();
//    }
//    @Autowired
//    private WfInsOperService operService;




}