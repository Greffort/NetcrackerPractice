package com.greffort.java_ee.xml_lab;

import com.greffort.java_ee.xml_lab.model.Group;
import com.greffort.java_ee.xml_lab.model.MySubject;
import com.greffort.java_ee.xml_lab.model.Student;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MainXml {
    public static void main(String[] args) throws JAXBException {
        Group group;
        try {
            group = readXML();
            if (checkMark(group) | checkAverage(group)) {
                writeXML(group);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Group readXML() throws JAXBException, IOException {
        Scanner scanner = new Scanner(new FileReader("resources/java_ee/INXML.xml"));
        StringBuilder s = new StringBuilder();
        while (scanner.hasNext()) {
            s.append(scanner.nextLine());
        }
        StringReader reader = new StringReader(s.toString());
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

        FileWriter outputStream = new FileWriter(new File("resources/java_ee/OUTXML.xml"));
        outputStream.write(writer.toString());
        outputStream.flush();
        outputStream.close();
    }

    private static boolean checkMark(Group group) {
        boolean flag = false;
        for (int i = 0; i < group.getStudent().size(); i++) {
            for (int j = 0; j < group.getStudent().get(i).getSubjects().size(); j++) {
                switch (group.getStudent().get(i).getSubjects().get(j).getMark()) {
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    default:
                        group.getStudent().get(i).getSubjects().get(j).setMark(1, "");
                        flag = true;
                }
            }
        }
        return flag;
    }

    private static boolean checkAverage(Group group) {
        boolean flag = false;
        for (int i = 0; i < group.getStudent().size(); i++) {
            double getAverage = group.getStudent().get(i).getAverage();
            double countAverage = group.getStudent().get(i).countAverage();
            if (getAverage != countAverage) {
                group.getStudent().get(i).setAverage(countAverage, "");
                flag = true;
            }
        }
        return flag;
    }

    private static Group createGroup() {
        MySubject mySubject1 = new MySubject("math", 1);
        MySubject mySubject2 = new MySubject("math", 2);
        MySubject mySubject3 = new MySubject("math", 4);

        ArrayList<MySubject> listSubjects1 = new ArrayList<>();
        listSubjects1.add(mySubject1);
        listSubjects1.add(mySubject2);
        listSubjects1.add(mySubject3);

        ArrayList<MySubject> listSubjects2 = new ArrayList<>();
        listSubjects2.add(mySubject3);
        listSubjects2.add(mySubject3);
        listSubjects2.add(mySubject3);

        Student student1 = new Student("Иванов", "Иван", "1", listSubjects1);
        Student student2 = new Student("Петров", "Иван", "2", listSubjects1);
        Student student3 = new Student("Смирнов", "Иван", "3", listSubjects1);
        Student student4 = new Student("Леонтьев", "Никитич", "7", listSubjects2);

        ArrayList<Student> listStudents1 = new ArrayList<>();
        listStudents1.add(student1);
        listStudents1.add(student2);
        listStudents1.add(student3);
        listStudents1.add(student4);

        return new Group(listStudents1);
    }
}
