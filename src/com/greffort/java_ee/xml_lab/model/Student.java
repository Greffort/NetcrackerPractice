package com.greffort.java_ee.xml_lab.model;

import com.greffort.java_ee.xml_lab.model.MySubject;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@XmlType(name = "student")
public class Student {
    @XmlAttribute(name = "firstname")
    private String firstname;
    @XmlAttribute(name = "lastname")
    private String lastname;
    @XmlAttribute(name = "groupnumber")
    private String groupnumber;

//    @XmlElementWrapper(nillable = true)
//    @XmlElement(name = "subject")
//    private List<MySubject> subjects;

@XmlElements({@XmlElement (name = "subject", type = MySubject.class)})
private List<MySubject> subjects;
    @XmlElement(name = "average")
    private double average;

    public Student() {
        this.firstname = "not found";
        this.lastname = "not found";
        this.groupnumber = "not found";
        this.subjects = new ArrayList<>();
        this.average = -1;
    }

    public Student(String firstname, String lastname, String groupnumber, List<MySubject> subject) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.groupnumber = groupnumber;
        this.subjects = subject;
        this.average = -1;
    }


    public Student(String firstname, String lastname, String groupnumber, List<MySubject> subject, double average) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.groupnumber = groupnumber;
        this.subjects = subject;
        this.average = average;
    }

    public double countAverage() {
        int[] arr = new int[this.subjects.size()];
        final double average;
        if (arr != null) {
            for (int i = 0; i < subjects.size(); i++) {
                arr[i] = subjects.get(i).getMark();
            }
            average = IntStream.of(arr).average().getAsDouble();
        } else {
            return -1;
        }
        return average;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average, String s) {
        this.average = average;
    }

    public List<MySubject> getSubjects() {
        return subjects;
    }

    //добавить список предметов, и средний балл

}



