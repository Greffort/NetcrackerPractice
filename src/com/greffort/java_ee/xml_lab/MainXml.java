package com.greffort.java_ee.xml_lab;

import com.greffort.java_ee.xml_lab.model.Group;
import com.greffort.java_ee.xml_lab.model.MySubject;
import com.greffort.java_ee.xml_lab.model.Student;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.Scanner;

public class MainXml {
    public static void main(String[] args) throws JAXBException {
//        MySubject mySubject1 = new MySubject("math", 1);
//        MySubject mySubject2 = new MySubject("math", 2);
//        MySubject mySubject3 = new MySubject("math", 4);
//
//        ArrayList<MySubject> listSubjects1 = new ArrayList();
//        listSubjects1.add(mySubject1);
//        listSubjects1.add(mySubject2);
//        listSubjects1.add(mySubject3);
//
//        ArrayList<MySubject> listSubjects2 = new ArrayList();
//        listSubjects2.add(mySubject3);
//        listSubjects2.add(mySubject3);
//        listSubjects2.add(mySubject3);
//
//        Student student1 = new Student("Иванов", "Иван", "1", listSubjects1);
//        Student student2 = new Student("Петров", "Иван", "2", listSubjects1);
//        Student student3 = new Student("Смирнов", "Иван", "3", listSubjects1);
//        Student student4 = new Student("Леонтьев", "Никитич", "7", listSubjects2);
//
//        ArrayList<Student> listStudents1 = new ArrayList();
//        listStudents1.add(student1);
//        listStudents1.add(student2);
//        listStudents1.add(student3);
//        listStudents1.add(student4);
//
//        Group group = new Group(listStudents1);
//
        Group group = null;
        try {
            group = readXML();
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean flag = false;
        for (int i = 0; i < group.getStudent("").size(); i++) {
            double getAverage = group.getStudent("").get(i).getAverage("");
            double countAverage = group.getStudent("").get(i).countAverage("");
            if (getAverage != countAverage) {
                group.getStudent("").get(i).setAverage(countAverage, "");
                flag = true;
            }
        }
        if (flag) {
            try {
                writeXML(group);
            } catch (JAXBException | IOException ignored) {
            }
        }
    }

    private static Group readXML() throws JAXBException, IOException {
        Scanner scanner = new Scanner(new FileReader("INXML.xml"));
        String s = "";
        while (scanner.hasNext()) {
            s += scanner.nextLine();
        }
        StringReader reader = new StringReader(s);
        JAXBContext context = JAXBContext.newInstance(Group.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (Group) unmarshaller.unmarshal(reader);
    }

    private static void writeXML(Group group) throws JAXBException, IOException {
        StringWriter writer = new StringWriter();

        JAXBContext context = JAXBContext.newInstance(MySubject.class, Student.class, Group.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        marshaller.marshal(group, writer);

        FileWriter outputStream = new FileWriter(new File("OUTXML.xml"));
        outputStream.write(writer.toString());
        outputStream.flush();
        outputStream.close();
    }
}
