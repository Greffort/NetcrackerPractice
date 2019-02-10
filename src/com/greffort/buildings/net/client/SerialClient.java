package com.greffort.buildings.net.client;

import com.greffort.buildings.Buildings;
import com.greffort.interfaces.Building;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * @author SerialClient
 */
public class SerialClient {
    public static void main(String[] args) {

        try (
                Socket socket = new Socket(InetAddress.getLocalHost(), 6666);
                FileReader fileReader = new FileReader("1.txt");
                ObjectOutputStream outD = new ObjectOutputStream(new DataOutputStream(socket.getOutputStream()));
                ObjectInputStream inD = new ObjectInputStream(new DataInputStream(socket.getInputStream()));
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
                System.out.println();
                System.out.println("Тип здания - " + lineType);
                System.out.println("Описание здания - " + building.toString());
                System.out.println("Тип здания отправлен на сервер");

                outD.writeObject(building);
                outD.flush();
                System.out.println("Здание отправлено на сервер");

                Object report = inD.readObject();
                System.out.println("Стоимость здания: " + report);

                printStream.println(report);
                System.out.println("Оценка записана в файл");
            }
            outD.writeUTF("exit");
            outD.flush();
        } catch (UnknownHostException e) {
            e.getStackTrace();
        } catch (IOException e) {
            e.getStackTrace();
        } catch (ClassNotFoundException e) {
            e.getStackTrace();
        }
    }
}
