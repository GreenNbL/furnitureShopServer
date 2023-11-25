package server;
import models.*;
import services.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Server extends Thread
{
    private static int[][] matrix = new int[3][3];
    private Socket clientAccepted;
    private static int number=0;
    public Server(Socket clientAccepted)
    {
        this.clientAccepted=clientAccepted;
    }

    public static void main(String[] arg) throws IOException {
        ServerSocket serverSocket = null;
        Socket clientAccepted     = null;//объявление объекта класса Socket

        System.out.println("server starting....");
        serverSocket = new ServerSocket(2525);//создание сокета сервера для //заданного порта
        try {
            while (true) {
                clientAccepted = serverSocket.accept();//выполнение метода, который //обеспечивает реальное подключение сервера к клиенту
                System.out.println("connection established....");//создание потока ввода
                Server server = new Server(clientAccepted);
                server.start();
                number++;
                System.out.println("Number of connections: "+number);
            }
        }
        finally {
            clientAccepted.close();//закрытие сокета, выделенного для клиента
            serverSocket.close();//закрытие сокета сервера
        }

    }
    @Override
    public void run()
    {//объявление объекта класса ServerSocket
        ObjectInputStream  sois   = null;//объявление байтового потока ввода
        ObjectOutputStream soos   = null;//объявление байтового потока вывода
        try {
            sois = new ObjectInputStream(clientAccepted.getInputStream());
            soos = new ObjectOutputStream(clientAccepted.getOutputStream());//создание потока//вывода
            while(clientAccepted.isConnected()) {
                String command = (String) sois.readObject();
                switch (command) {
                    case "SignUp": {
                        System.out.println("SignUp");
                        SignUp signUp = new SignUp(sois, soos);
                        signUp.Signingup();
                        break;
                    }
                    case "LogIn": {
                        System.out.println("LogIn");
                        LogIn logIn = new LogIn(sois, soos);
                        logIn.LogingIn();
                        break;
                    }
                    case "GetAllUsers": {
                        System.out.println("GetAllUsers");
                        UserService userService=new UserService();
                        List<User> users=new ArrayList<User>();
                        users=userService.findAllUsers();
                        System.out.println(users);
                        soos.writeObject(users);
                        break;
                    }
                    case "EditUser": {
                        System.out.println("EditUser");
                        UserService userService=new UserService();
                        User user=new User();
                        user=(User)sois.readObject();
                        System.out.println(user.toString());
                        userService.updateUser(user);
                        System.out.println("EditUser2");
                        break;
                    }
                    case "DeleteUser": {
                        System.out.println("DeleteUser");
                        UserService userService=new UserService();
                        User user=new User();
                        user=(User)sois.readObject();
                        userService.deleteUser(user);
                        break;
                    }
                    case "FindUserById": {
                        System.out.println("FindUserById");
                        UserService userService=new UserService();
                        User user=new User();
                        int id=(int)sois.readObject();
                        user=userService.findUser(id);
                        soos.writeObject(user);
                        break;
                    }
                    case "GetAllProviders": {
                        System.out.println("GetAllProviders");
                        ProviderService providerService=new ProviderService();
                        List<Provider> providers=new ArrayList<Provider>();
                        providers=providerService.findAllProviders();
                        System.out.println(providers);
                        soos.writeObject(providers);
                        break;
                    }
                    case "EditProvider": {
                        System.out.println("EditProvider");
                        ProviderService providerService=new ProviderService();
                        Provider provider =new Provider();
                        provider=(Provider) sois.readObject();
                        providerService.updateProvider(provider);
                        break;
                    }
                    case "AddProvider": {
                        System.out.println("AddProvider");
                        ProviderService providerService=new ProviderService();
                        Provider provider =new Provider();
                        provider=(Provider) sois.readObject();
                        providerService.saveProvider(provider);
                        break;
                    }
                    case "DeleteProvider": {
                        System.out.println("DeleteProvider");
                        ProviderService providerService=new ProviderService();
                        Provider provider =new Provider();
                        provider=(Provider) sois.readObject();
                        providerService.deleteProvider(provider);
                        break;
                    }
                    case "FindProviderById": {
                        System.out.println("FindProviderById");
                        ProviderService providerService=new ProviderService();
                        Provider provider =new Provider();
                        int id=(int)sois.readObject();
                        System.out.println(id);
                        provider=providerService.findProvider(id);
                        soos.writeObject(provider);
                        break;
                    }
                    case "FindFurnitureById": {
                        System.out.println("FindFurnitureById");
                        FurnitureService furnitureService=new FurnitureService();
                        Furniture furniture = new Furniture();
                        int id=(int)sois.readObject();
                        furniture=furnitureService.findFurniture(id);
                        soos.writeObject(furniture);
                        break;
                    }
                    case "GetAllFurnitures": {
                        System.out.println("GetAllFurnitures");
                        FurnitureService furnitureService=new FurnitureService();
                        List<Furniture> furnitures=new ArrayList<Furniture>();
                        furnitures=furnitureService.findAllFurnitures();
                        System.out.println(furnitures);
                        soos.writeObject(furnitures);
                        break;
                    }
                    case "DeleteFurniture": {
                        System.out.println("DeleteFurniture");
                        FurnitureService furnitureService=new FurnitureService();
                        Furniture furniture = new Furniture();
                        furniture=(Furniture) sois.readObject();
                        furnitureService.deleteFurniture(furniture);
                        break;
                    }
                    case "EditFurniture": {
                        System.out.println("EditFurniture");
                        FurnitureService furnitureService=new FurnitureService();
                        Furniture furniture =new Furniture();
                        furniture=(Furniture) sois.readObject();
                        furnitureService.updateFurniture(furniture);
                        break;
                    }
                    case "AddFurniture": {
                        System.out.println("AddFurniture");
                        FurnitureService furnitureService=new FurnitureService();
                        Furniture furniture =new Furniture();
                        furniture=(Furniture) sois.readObject();
                        furnitureService.saveFurniture(furniture);
                        break;
                    }
                    case "DeleteDelivery": {
                        System.out.println("DeleteDelivery");
                        DeliveryService deliveryService=new DeliveryService();
                        Delivery delivery=new Delivery();
                        delivery=(Delivery) sois.readObject();
                        deliveryService.deleteDelivery(delivery);
                        break;
                    }
                    case "EditDelivery": {
                        System.out.println("EditDelivery");
                        DeliveryService deliveryService=new DeliveryService();
                        Delivery delivery=new Delivery();
                        delivery=(Delivery) sois.readObject();
                        System.out.println(delivery);
                        deliveryService.updateDelivery(delivery);
                        break;
                    }
                    case "AddDelivery": {
                        System.out.println("AddDelivery");
                        DeliveryService deliveryService=new DeliveryService();
                        Delivery delivery=new Delivery();
                        delivery=(Delivery) sois.readObject();
                        deliveryService.saveDelivery(delivery);
                        break;
                    }
                    case "GetAllDeliveries": {
                        System.out.println("GetAllDeliveries");
                        DeliveryService deliveryService=new DeliveryService();
                        List<Delivery> deliveries=new ArrayList<Delivery>();
                        deliveries=deliveryService.findAllDeliveries();
                        System.out.println(deliveries.toString());
                        soos.writeObject(deliveries);
                        break;
                    }
                    case "FindDeliveryById": {
                        System.out.println("FindDeliveryById");
                        DeliveryService deliveryService=new DeliveryService();
                        Delivery delivery =new Delivery();
                        int id=(int)sois.readObject();
                        delivery=deliveryService.findDelivery(id);
                        soos.writeObject(delivery);
                        break;
                    }
                    case "GetAllOrders": {
                        System.out.println("GetAllOrders");
                        OrderService orderService=new OrderService();
                        List<Order> orders=new ArrayList<Order>();
                        orders=orderService.findAllOrders();
                        System.out.println(orders);
                        soos.writeObject(orders);
                        break;
                    }
                    case "GetOrderByPeriod": {
                        System.out.println("GetOrderByPeriod");
                        OrderService orderService=new OrderService();
                        List<Order> orders=new ArrayList<Order>();
                        Date startDate=(Date)sois.readObject();
                        Date endDate=(Date)sois.readObject();
                        orders=orderService.findAllByPeriod(startDate,endDate);
                        System.out.println(orders);
                        soos.writeObject(orders);
                        break;
                    }
                    case "GetOrderByPeriodAndIdFurniture": {
                        System.out.println("GetOrderByPeriodAndIdFurniture");
                        OrderService orderService=new OrderService();
                        List<Order> orders=new ArrayList<Order>();
                        Date startDate=(Date)sois.readObject();
                        Date endDate=(Date)sois.readObject();
                        int id =(int)sois.readObject();
                        System.out.println(id);
                        orders=orderService.findAllByPeriodAndIdFurniture(startDate,endDate,id);
                        System.out.println(orders);
                        soos.writeObject(orders);
                        break;
                    }
                    case "AddOrder": {
                        System.out.println("AddOrder");
                        OrderService orderService=new OrderService();
                        Order order =new Order();
                        order=(Order) sois.readObject();
                        orderService.saveOrder(order);
                        break;
                    }
                    case "EditOrder": {
                        System.out.println("Order");
                        OrderService orderService=new OrderService();
                        Order order =new Order();
                        order=(Order) sois.readObject();
                        orderService.updateOrder(order);
                        break;
                    }
                    case "FindOrderById": {
                        System.out.println("FindOrderById");
                        OrderService orderService=new OrderService();
                        Order order =new Order();
                        int id=(int)sois.readObject();
                        order=orderService.findOrder(id);
                        soos.writeObject(order);
                        break;
                    }
                    case "FindActiveOrders": {
                        System.out.println("FindActiveOrders");
                        OrderService orderService=new OrderService();
                        List<Order> orders=new ArrayList<Order>();
                        orders=orderService.findAllActiveOrders();
                        soos.writeObject(orders);
                        break;
                    }
                    case "FindOrderByUserId": {
                        System.out.println("FindOrderByUserId");
                        OrderService orderService=new OrderService();
                        List<Order> orders=new ArrayList<Order>();
                        int id=(int)sois.readObject();
                        orders=orderService.findAllOrdersByUserId(id);
                        soos.writeObject(orders);
                        break;
                    }
                    case "FindOrderByFurnitureId": {
                        System.out.println("FindOrderByFurnitureId");
                        OrderService orderService=new OrderService();
                        List<Order> orders=new ArrayList<Order>();
                        int id=(int)sois.readObject();
                        orders=orderService.findAllOrdersByFurnitureId(id);
                        soos.writeObject(orders);
                        break;
                    }
                    case "DeleteOrder": {
                        System.out.println("DeleteOrder");
                        OrderService orderService=new OrderService();
                        Order order =new Order();
                        order=(Order) sois.readObject();
                        orderService.deleteOrder(order);
                        break;
                    }
                }
            }


        }catch(Exception e)  {
            throw new RuntimeException(e);
        } finally {
            try {
                sois.close();//закрытие потока ввода
                soos.close();//закрытие потока вывода
                number --;
                System.out.println("Number of connections: "+number);
            } catch(Exception e) {
                e.printStackTrace();//вызывается метод исключения е
            }
        }
    }
}
