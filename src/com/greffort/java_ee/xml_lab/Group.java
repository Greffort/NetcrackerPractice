package com.greffort.java_ee.xml_lab;

import jdk.nashorn.internal.objects.annotations.Getter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

public @XmlType(name = "group")
@XmlRootElement
class Group{

    @XmlElementWrapper(name = "students", nillable = true)
    @XmlElement(name="student")
    private List<Student> student;

    public Group() {
        this.student = new ArrayList<>();
    }

    public Group(List<Student> students) {
        this.student = students;
    }

    public List<Student> getStudent(String kostil) {
        return student;
    }

    public void setStudent(List<Student> student,String kostil) {
        this.student = student;
    }
}
