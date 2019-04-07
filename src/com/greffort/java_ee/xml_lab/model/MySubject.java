package com.greffort.java_ee.xml_lab.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


@XmlType(name = "subject")
public class MySubject {
    @XmlAttribute(name = "title")
    private String title;
    @XmlAttribute(name = "mark")
    private int mark;

    public MySubject() {
        this.title = "not found";
        this.mark = 404;
    }

    public MySubject(String title, int mark) {
        this.title = title;
        this.mark = mark;
    }

    public int getMark() {
        return mark;
    }


}
