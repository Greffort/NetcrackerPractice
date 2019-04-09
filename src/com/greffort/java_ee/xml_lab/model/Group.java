package com.greffort.java_ee.xml_lab.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

public @XmlType(name = "group")
@XmlRootElement
class Group {

//    @XmlElementWrapper(name = "students", nillable = true)
//    @XmlElement(name = "student")
//    private List<Student> student;


    @XmlElements
            ({@XmlElement(name = "student", type = Student.class)})
    private List<Student> student;

    public Group() {
        this.student = new ArrayList<>();
    }

    public Group(List<Student> students) {
        this.student = students;
    }

    public List<Student> getStudent() {
        return student;
    }
}
