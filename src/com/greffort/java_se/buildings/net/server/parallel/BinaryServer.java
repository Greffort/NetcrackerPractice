package com.greffort.java_se.buildings.net.server.parallel;

import com.greffort.java_se.buildings.Buildings;
import com.greffort.java_se.exception.BuildingUnderArrestException;
import com.greffort.java_se.factory.DwellingFactory;
import com.greffort.java_se.factory.HotelFactory;
import com.greffort.java_se.factory.OfficeFactory;
import com.greffort.java_se.interfaces.Building;
import org.jetbrains.annotations.NotNull;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BinaryServer {
    private final static ExecutorService executeIt = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {

        try (ServerSocket server = new ServerSocket(6666)) {
            while (!server.isClosed()) {
                Socket client = server.accept();
                executeIt.execute(new MonoThreadClientHandler(client));
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
            try (DataInputStream in = new DataInputStream(clientDialog.getInputStream());
                 DataOutputStream out = new DataOutputStream(clientDialog.getOutputStream())) {
                System.out.println("Клиент подключился");

                String report;
                while (!clientDialog.isClosed()) {
                    String type = in.readUTF();

                    if (type.equals("exit")) {
                        System.out.println("Передача данных завершена" + "\n");
                        break;
                    }
                    System.out.println();
                    System.out.println("Получилди тип здания - " + type);

                    Building building = Buildings.inputBuilding(in);
                    System.out.println("Получили данные о здании - " + building.toString());

                    report = valuationBuilding(type, building);
                    System.out.println("Вычислена стоимость здания - " + report);

                    out.writeUTF(report);
                    out.flush();

                    System.out.println("Стоимость здания отправлена на клиент");
                }
            } catch (Exception x) {
                x.printStackTrace();
            }
        }

    }

    private static String valuationBuilding(@NotNull String type, @NotNull Building building) {
        try {
            arrest();
            double number;
            switch (type) {
                case "dwelling":
                    Buildings.setBuildingFactory(new OfficeFactory());
                    number = 1000;
                    System.out.println();
                    break;
                case "office":
                    Buildings.setBuildingFactory(new DwellingFactory());
                    number = 1500;
                    break;
                default:
                    System.out.println();
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

