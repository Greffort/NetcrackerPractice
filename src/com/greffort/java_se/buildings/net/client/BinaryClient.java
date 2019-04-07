package com.greffort.java_se.buildings.net.client;

import com.greffort.java_se.buildings.Buildings;
import com.greffort.java_se.interfaces.Building;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class BinaryClient {

    public static void main(String[] args) {

        try (
                Socket socket = new Socket(InetAddress.getLocalHost(), 6666);
                FileReader fileReader = new FileReader("1.txt");
                DataOutputStream outD = new DataOutputStream(socket.getOutputStream());
                DataInputStream inD = new DataInputStream(socket.getInputStream());
                Scanner scanner = new Scanner(new FileReader("InputTypeBuilding"));
                PrintStream printStream = new PrintStream(new FileOutputStream(new File("OUTPUT")))) {

            String lineType;
            Building building;

            while ((((building = Buildings.readBuilding(fileReader))) != null) && scanner.hasNextLine() && !socket.isOutputShutdown()) {
                lineType = scanner.nextLine();
                if (lineType.equals("")) {
                    continue;

                    }
                outD.writeUTF(lineType);
                outD.flush();
                System.out.println("Тип здания - " + lineType);
                System.out.println("Описание здания - " + building.toString());
                System.out.println("Тип здания отправлен на сервер");

                Buildings.outputBuilding(building, outD);
                outD.flush();
                System.out.println("Здание отправлено на сервер");

                String report = inD.readUTF();
                System.out.println("Стоимость здания: " + report);

                printStream.println(report);
                System.out.println("Оценка записана в файл");
            }
            outD.writeUTF("exit");
            outD.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        try (
//                Socket socket = new Socket(InetAddress.getLocalHost(), 6666);
//                FileWriter fileWriter = new FileWriter("OUTPUT");
//                BufferedReader bufferedReaderDescription = new BufferedReader(new FileReader("buildings.txt"));
//                BufferedReader bufferedReaderType = new BufferedReader(new FileReader("InputTypeBuilding"));
//                DataInputStream inD = new DataInputStream(socket.getInputStream());
//                DataOutputStream outD = new DataOutputStream(socket.getOutputStream())) {
//
//            String lineDescription;
//            String lineType;
//
//            while (((lineDescription = bufferedReaderDescription.readLine()) != null | (lineType = bufferedReaderType.readLine()) != null)) {
//                if (socket.isOutputShutdown()) {
//                    continue;
//                if (lineDescription.e//                }quals("") || lineType.equals("")) {
//                    continue;
//                }
//                System.out.println("Тип здания - " + lineType);
//                System.out.println("Описание здания - " + lineDescription);
//
//                outD.writeUTF(lineType);
//                outD.flush();
//                System.out.println("Тип здания отправлен на сервер");
//
////                b = Buildings.readBuilding(fileReader);
////                System.out.println(b.toString());
//
//                outD.writeUTF(lineDescription);
//                outD.flush();
//                System.out.println("Здание отправлено на сервер");
//
//                fileWriter.write(inD.readUTF() + "\n");
//                fileWriter.flush();
//                System.out.println("Оценка записана в файл");
//            }
//            outD.writeUTF("exit");
//            outD.flush();
//        } catch (UnknownHostException e) {
//            System.out.println(e);
//        } catch (IOException e) {
//            System.out.println(e);
//        }
    }

}
