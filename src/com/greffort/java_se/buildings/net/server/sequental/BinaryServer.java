package com.greffort.java_se.buildings.net.server.sequental;

import com.greffort.java_se.buildings.Buildings;
import com.greffort.java_se.exception.BuildingUnderArrestException;
import com.greffort.java_se.factory.DwellingFactory;
import com.greffort.java_se.factory.HotelFactory;
import com.greffort.java_se.factory.OfficeFactory;
import com.greffort.java_se.interfaces.Building;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class BinaryServer {
    public static void main(String[] arg) {

        try (ServerSocket serverSocket = new ServerSocket(6666);
             Socket socket = serverSocket.accept();
             DataInputStream in = new DataInputStream(socket.getInputStream());
             DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {
            System.out.println("Клиент подключился");

            String report;
            while (!socket.isClosed()) {
                String type = in.readUTF();

                if (type.equals("exit")) {
                    System.out.println("Передача данных завершена" + "\n");
                    break;
                }

                System.out.println("Получилди тип здания - " + type);

                Building building = Buildings.inputBuilding(in);
                System.out.println("Получили данные о здании - " + building.toString());

                report = valuationBuilding(type, building);
                System.out.println("Вычислена стоимость здания - " + report);
                System.out.println(building.toString());

                out.writeUTF(report);
                out.flush();

                System.out.println("Стоимость здания отправлена на клиент");
            }
        } catch (Exception x) {
            x.printStackTrace();
        }
    }

    private static String valuationBuilding(String type, Building building) {
        try {
            arrest();
            double number;
            switch (type) {
                case "dwelling":
                    Buildings.setBuildingFactory(new OfficeFactory());
                    number = 1000;
                    break;
                case "office":
                    Buildings.setBuildingFactory(new DwellingFactory());
                    number = 1500;
                    break;
                default:
                    Buildings.setBuildingFactory(new HotelFactory());
                    number = 2000;
                    break;
            }
            return String.valueOf(building.getTotalSquare() * number);
        } catch (BuildingUnderArrestException e) {
            return "Здание под арестом";
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
