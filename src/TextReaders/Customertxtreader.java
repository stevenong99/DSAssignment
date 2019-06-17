package TextReaders;

import Data.Branch;
import Data.Dishes;
import Data.Orders;
import Data.Queue;
import Data.Restaurants;
import Map.Map;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Customertxtreader {

    private String customerfilename, inputfilename;
    private ArrayList<Orders> ordersarr;
    private Queue<String> queue;
    private Map newMap;
    private Restaurants restaurant;
    private String filename;
    private ArrayList<Restaurants> restlist;
    private ArrayList<Branch> branchlist;
    private ArrayList<Dishes> disheslist;

    public Customertxtreader(String customerfilename, String inputfilename) {
        this.customerfilename = customerfilename;
        this.ordersarr = new ArrayList<>();
        extractInput(inputfilename);
        extractCustomer(customerfilename);
        displayorders();
        this.queue = new Queue<>();
    }

    public void extractInput(String filename) {
        try {
            Scanner scan = new Scanner(new FileInputStream(filename));
            restlist = new ArrayList<>();
            String dishName;
            int prepTime;
            int price;
            Dishes newDishes;
            
            while (scan.hasNext()) {
                {
                    String name = scan.nextLine();
                    int[][] branches = new int[1][2];
                    branchlist = new ArrayList<>();
                    
                    while (scan.hasNextInt() == true) {
                        branches[0][0] = scan.nextInt();
                        branches[0][1] = scan.nextInt();
                        branchlist.add(new Branch(branches[0][0], branches[0][1]));
                        scan.nextLine();
                    }
                    
                    disheslist = new ArrayList<>();
                    for (int i = 0; i < 3; i++) {
                        dishName = scan.nextLine();
                        prepTime = scan.nextInt();
                        scan.nextLine();
                        price = scan.nextInt();
                        newDishes = new Dishes(dishName, prepTime, price);
                        disheslist.add(newDishes);
                        if (scan.hasNextLine()) {
                            scan.nextLine();
                        }
                    }
                    restlist.add(new Restaurants(name, branchlist, disheslist));
                }
                if (scan.hasNextLine()) {
                    scan.nextLine();
                }
            }
            System.out.println("\nList of restaurants: ");
            for (int i = 0; i < restlist.size(); i++) {
                System.out.println(restlist.get(i));
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("The file cannot be found.");
        }
    }

    public void extractCustomer(String file) {
        try {
            Scanner s = new Scanner(new FileInputStream(file));
            String resname, dishname, specialreq = null;
            int customerlocationX, customerlocationY, arrivalTime;

            while (s.hasNext()) {
                arrivalTime = s.nextInt();
                s.nextLine();
                resname = s.nextLine();
                dishname = s.nextLine();
                if (s.hasNextInt() == false) {
                    specialreq = s.nextLine();

                }
                customerlocationX = s.nextInt();
                customerlocationY = s.nextInt();
                createOrder(resname, dishname, arrivalTime, customerlocationX, customerlocationY, specialreq);
                specialreq = null;
                if (s.hasNextLine()) {
                    s.nextLine();
                }
            }
            s.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Customer file is not found !");
        }

    }

    public void createOrder(String restName, String dishName, int arrivalTime, int customerLocationX, int customerLocationY, String specialreq) {
        Orders newOrder = new Orders(restName, dishName, arrivalTime, customerLocationX, customerLocationY, restlist, ordersarr, specialreq);
        Branch branchTakeOrder = newOrder.getBranchTakeOrder();
        ordersarr.add(newOrder);
        //branchTakeOrder.acceptOrder(newOrder);
//        for (int i = 0; i < restlist.size(); i++) {
//            if (restlist.get(i).getName().equals(restName)) {
//                restlist.get(i).setTotalIncome(newOrder.getProfit());
//                restlist.get(i).addOrderIntoArrayList(newOrder);
//            }
//        }
    }

    public ArrayList<Orders> getOrdersarr() {
        return ordersarr;
    }

    public void displayorders() {
        System.out.println("List of orders: ");
        for (int i = 0; i < ordersarr.size(); i++) {
            System.out.println(ordersarr.get(i).toString(i) + "\n");
        }
    }

    public int getDishPrepTime(String resname, String dishname) {
        int i = 0;
        while (i < restlist.size()) {
            if (resname.equals(restlist.get(i).getName())) {
                break;
            }
            i++;
        }
        return restlist.get(i).getTime(dishname);
    }

    public int getShortestDeliveryTime(int customerlocationX, int customerlocationY, String resname) {
        int i = 0;
        while (i < restlist.size()) {
            if (resname.equals(restlist.get(i).getName())) {
                break;
            }
            i++;
        }
        return restlist.get(i).closestBranch(customerlocationX, customerlocationY);
    }

    public void displayRestlist() {
        System.out.println("Welcome to CrabFood. We have the following restaurants:");
        for (int i = 0; i < restlist.size(); i++) {
            System.out.println(restlist.get(i));
        }
    }

    public String getFilename() {
        return filename;
    }

    public ArrayList<Restaurants> getRestlist() {
        return restlist;
    }

    public ArrayList<Branch> getBranchList() {
        return branchlist;
    }

}
