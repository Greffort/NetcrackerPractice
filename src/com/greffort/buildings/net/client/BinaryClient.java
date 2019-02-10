package com.greffort.buildings.net.client;

/**
 * @author BinaryClient
 */

import com.greffort.buildings.Buildings;
import com.greffort.interfaces.Building;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Задание 1
 * Реализуйте клиентскую часть приложения в новом классе buildings.net.client.BinaryClient, содержащем метод main().
 * <p>
 * 1.	Задайте имена трех файлов.
 * •	Первый файл существует на момент запуска программы и содержит в текстовом виде информацию о зданиях (например, одна строка – одно здание).
 * •	Второй файл существует на момент запуска программы и содержит в текстовом виде информацию о видах зданий (например, одна строка – одно слово,
 * определяющее вид здания). Количество записей в первом и втором файле можно считать соответствующим друг другу, но неизвестным заранее
 * (т.е. оно даже не записано в первой строке файлов, но точно не одно здание). Файлы можно считать корректными.
 * •	Третий файл должен быть создан программой в ходе работы и должен хранить в текстовом виде оценки стоимости зданий (например, одна строчка – одна оценка стоимости).
 * <p>
 * <p>
 * Программа должна установить через сокетов соединение с сервером, после чего считывать из первого и второго файла данные о здании, передавать их в бинарной форме серверу
 * и получать от него результат оценки стоимости здания, и так по очереди для всех исходных данных.
 * <p>
 * Для обеспечения работы приложения потребуется определение протокола взаимодействия клиентской и серверной частей: порядка передачи данных, определения условия завершения передачи данных.
 * Считывание данных из первого файла, а также запись данных в поток сокета рекомендуется реализовать с помощью средств класса Buildings. Также рекомендуется реализовать вывод информации
 * о текущей операции в консоль (например, с помощью ранее реализованных методов toString () зданий).
 */
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

            while ((building = Buildings.readBuilding(fileReader)) != null && scanner.hasNextLine() && !socket.isOutputShutdown()) {
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
        } catch (UnknownHostException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
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
//                }
//                if (lineDescription.equals("") || lineType.equals("")) {
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
