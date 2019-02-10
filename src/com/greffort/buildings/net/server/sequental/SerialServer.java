package com.greffort.buildings.net.server.sequental;

/**
 * @author SerialServer
 */

import com.greffort.buildings.Buildings;
import com.greffort.exception.BuildingUnderArrestException;
import com.greffort.factory.DwellingFactory;
import com.greffort.factory.HotelFactory;
import com.greffort.factory.OfficeFactory;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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
    public static void main(String[] ar) {
        int port = 6666;
        try (ServerSocket serverSocket = new ServerSocket(port);
             Socket socket = serverSocket.accept();
             DataInputStream in = new DataInputStream(socket.getInputStream());
             DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

            System.out.println("Клиент подключился");

            String line;
            String type;
            String report;
            while (!socket.isClosed()) {
                type = in.readUTF();
                System.out.println("Получилди тип здания - " + type);

                if (type.equals("exit")) {
                    System.out.println("Передача данных завершена");
                    break;
                }

                line = in.readUTF();
                System.out.println("Получили данные о здании - " + line);

                report = valuationBuilding(type, line);
                System.out.println("Вычислена стоимость здания - " + report);

                out.writeUTF(report);
                out.flush();
                System.out.println("Стоимость здания отправлена на клиент");
            }
        } catch (Exception x) {
            x.printStackTrace();
        }
    }

    private static String valuationBuilding(String type, String in) {
        try (Scanner scanner = new Scanner(in)) {
            arrest();
            int number = 0;
//            if (type.equals("office")) {
//                Buildings.setBuildingFactory(new OfficeFactory());
//                number = 1000;
//            } else if (type.equals("dwelling")) {
//                Buildings.setBuildingFactory(new DwellingFactory());
//                number = 1500;
//            } else {
//                Buildings.setBuildingFactory(new HotelFactory());
//                number = 2000;
//            }
            return String.valueOf(number * Buildings.readBuilding(scanner).getTotalSquare());
        } catch (IOException e) {
            System.out.println(e);
        } catch (BuildingUnderArrestException e){
            return "Здание под арестом";
        }

        return "Ошибка";
//        return null;
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
