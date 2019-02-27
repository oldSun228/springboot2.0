package com.xch.study.biz.threaddemo1.controller;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;

import java.io.File;

/**
 * @author fgs
 * @data 2019/1/7 17:29
 */
public class Demo2Controller {
    public static void main(String[] args) {
        String xmlFileName = "C:\\Users\\fan\\Desktop\\JZCX.xml";
        //读xml
        String xmlString = Demo2Controller.xmlToString(xmlFileName);
        System.out.println(xmlString);

    }

    public static String xmlToString(String xmlFileName) {
        SAXReader saxReader = new SAXReader();
        Document document;
        String xmlString = "";
        try {
            document = saxReader.read(new File(xmlFileName));
            xmlString = document.asXML();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            xmlString = "";
        }
        return xmlString;
    }
}
