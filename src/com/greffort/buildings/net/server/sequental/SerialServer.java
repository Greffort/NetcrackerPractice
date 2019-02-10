package com.greffort.buildings.net.server.sequental;

/**
 * @author SerialServer
 */

import com.greffort.buildings.Buildings;
import com.greffort.exception.BuildingUnderArrestException;
import com.greffort.factory.DwellingFactory;
import com.greffort.factory.HotelFactory;
import com.greffort.factory.OfficeFactory;
import com.greffort.interfaces.Building;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

/**
 * Создайте новые классы  решающие ту же задачу, но отличающиеся по протоколу взаимодействия:
 * для передачи данных следует использовать сериализацию. Данные о здании передаются в виде объекта,
 * исключение передаётся клиенту в виде объекта и т.д.
 */
public class SerialServer {
    public static void main(String[] arg) {

        try (ServerSocket serverSocket = new ServerSocket(6666);
             Socket socket = serverSocket.accept();
             ObjectInputStream in = new ObjectInputStream(new DataInputStream(socket.getInputStream()));
             ObjectOutputStream out = new ObjectOutputStream(new DataOutputStream(socket.getOutputStream()))) {
            System.out.println("Клиент подключился");

            Object report;
            while (!socket.isClosed()) {
                String type = in.readUTF();

                if (type.equals("exit")) {
                    System.out.println("Передача данных завершена"+"\n");
                    break;
                }

                System.out.println("Получилди тип здания - " + type);
                System.out.println();

                Building building = (Building) in.readObject();
                System.out.println("Получили данные о здании - " + building.toString());

                report = valuationBuilding(type, building);
                System.out.println("Вычислена стоимость здания - " + report);

                out.writeObject(report);
                out.flush();

                System.out.println("Стоимость здания отправлена на клиент");
            }
        } catch (Exception x) {
            x.printStackTrace();
        }
    }

    private static Object valuationBuilding(String type, Building building) {
        try {
            arrest();
            double number = 0;
            if (type.equals("dwelling")) {
                Buildings.setBuildingFactory(new OfficeFactory());
                number = 1000;
            } else if (type.equals("office")) {
                Buildings.setBuildingFactory(new DwellingFactory());
                number = 1500;
            } else {
                System.out.println();
                System.out.println();
                Buildings.setBuildingFactory(new HotelFactory());
                number = 2000;
            }
            return String.valueOf(building.getTotalSquare() * number);
        } catch (BuildingUnderArrestException e) {
            return e;
        }
    }


    private static void arrest() throws BuildingUnderArrestException {
        Random random = new Random();
        int bool = random.nextInt(10);
//        int bool = 7;
        if (bool == 7) {
            throw new BuildingUnderArrestException();
        }
    }
}
