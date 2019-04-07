package com.greffort.java_se.buildings.net.server.parallel;

import com.greffort.java_se.buildings.Buildings;
import com.greffort.java_se.exception.BuildingUnderArrestException;
import com.greffort.java_se.factory.DwellingFactory;
import com.greffort.java_se.factory.HotelFactory;
import com.greffort.java_se.factory.OfficeFactory;
import com.greffort.java_se.interfaces.Building;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SerialServer {
    private final static ExecutorService executeIt = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {

        try (ServerSocket server = new ServerSocket(6666)) {
            while (!server.isClosed()) {
                Socket client = server.accept();
                executeIt.execute(new SerialServer.MonoThreadClientHandler(client));
                System.out.print("Connection accepted.");
            }
            executeIt.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class MonoThreadClientHandler implements Runnable {

        private static Socket clientDialog;

        MonoThreadClientHandler(Socket client) {
            MonoThreadClientHandler.clientDialog = client;
        }

        @Override
        public void run() {
            try (ObjectInputStream in = new ObjectInputStream(new DataInputStream(clientDialog.getInputStream()));
                 ObjectOutputStream out = new ObjectOutputStream(new DataOutputStream(clientDialog.getOutputStream()))) {
                System.out.println("Клиент подключился");

                Object report;
                while (!clientDialog.isClosed()) {
                    String type = in.readUTF();

                    if (type.equals("exit")) {
                        System.out.println("Передача данных завершена" + "\n");
                        break;
                    }

                    System.out.println("Получилди тип здания - " + type);

                    Building building =  Buildings.deserializeBuilding(in);//(Building) in.readObject();
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
                        System.out.println();
                        System.out.println();
                        Buildings.setBuildingFactory(new HotelFactory());
                        number = 2000;
                        break;
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
}
