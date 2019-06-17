package Data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Restaurants {

    private String name;
    private ArrayList<Branch> branches;
    private ArrayList<Dishes> dishes;
    private ArrayList<Orders> orderList;
    private int numberofbranches;
    private int totalIncome;

    public Restaurants(String name, ArrayList<Branch> branches, ArrayList<Dishes> dishes) {
        this.name = name;
        this.branches = branches;
        this.dishes = dishes;
        this.numberofbranches = 3;
        this.totalIncome = 0;
        this.orderList = new ArrayList<>();
    }

    @Override
    public String toString() {
        String branches = "";
        for (int i = 0; i < this.branches.size(); i++) {
            if (i != this.branches.size() - 1) {
                branches = branches + this.branches.get(i).getLocation() + ",";
            } else {
                branches = branches + this.branches.get(i).getLocation() + ".";
            }
        }
        return name + " located at " + branches + "\n" + "It provides the following meals:\n" + DishesServed();
    }

    public int getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(int totalIncome) {
        this.totalIncome = this.totalIncome + totalIncome;
    }

    public void addOrderIntoArrayList(Orders order) {
        orderList.add(order);
    }

    public String DishesServed() {
        String output = "";
        for (int i = 0; i < this.dishes.size(); i++) {
            output = output + this.dishes.get(i) + "\n";
        }
        return output;
    }

    public ArrayList<Dishes> getDishes() {
        return dishes;
    }

    public char compareLocation(int x, int y) {
        boolean exist = false;
        for (int a = 0; a < this.branches.size(); a++) {
            if (this.branches.get(a).getCoordinateX() == x) {
                if (this.branches.get(a).getCoordinateY() == y) {
                    exist = true;
                }
            }
        }
        if (exist == true) {
            return name.charAt(0);
        } else {
            return '0';
        }
    }

    public int getTime(String dishname) {
        int i = 0;
        while (i < dishes.size()) {
            if (dishname.equals(dishes.get(i).getDishName())) {
                break;
            }
            i++;
        }
        return dishes.get(i).getPreparationTime();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Orders> getOrderList() {
        return orderList;
    }

    public int closestBranch(int customerlocationX, int customerlocationY) {
        int restX = this.branches.get(0).getCoordinateX();
        int restY = this.branches.get(0).getCoordinateY();
        int closestX = 0;
        int closestY = 0;
        int time = (int) Math.pow(customerlocationX - restX, 2) + (int) Math.pow(customerlocationY - restY, 2);
        for (int i = 1; i < this.branches.size(); i++) {
            restX = this.branches.get(i).getCoordinateX();
            restY = this.branches.get(i).getCoordinateY();
            int newTime = (int) Math.pow(customerlocationX - restX, 2) + (int) Math.pow(customerlocationY - restY, 2);
            if (newTime < time) {
                time = newTime;
                closestX = restX;
                closestY = restY;
            }
        }
        int deliverytime = (int) (Math.abs(customerlocationX - closestX) + Math.abs(customerlocationY - closestY));
        return deliverytime;
    }

    public ArrayList<Branch> getBranches() {
        return branches;
    }

    public void generateLog(int day) {
        try {
            copy(name + ".txt");
            Scanner s = new Scanner(new FileInputStream("temp.txt"));
            PrintWriter pw = new PrintWriter(new FileOutputStream(name + ".txt"));
            pw.println("Day "+ day);
            pw.printf("%-9s|%-8s|%-11s|%-22s|%-14s|%-11s", "No.", "Arrival", "Order Time", "Finished Cooking Time", "Delivery Time", "Total Time");
            pw.println();
            for (int i = 0; i < orderList.size(); i++) {
                pw.printf("%-9d|%-8d|%-11d|%-22d|%-14d|%-11d", i + 1, orderList.get(i).getArrivaltime(), orderList.get(i).getArrivaltime(), orderList.get(i).getFinishedcookingtime(), orderList.get(i).getDeliverytime(), orderList.get(i).getTotaltime());
                pw.println();
            }
            pw.println();
            while (s.hasNextLine()) {
                pw.println(s.nextLine());
            }
            pw.close();
            s.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Log file is not found!");
        }

    }

    public void copy(String from) {
        try {
            Scanner s = new Scanner(new FileInputStream(from));
            PrintWriter pw = new PrintWriter(new FileOutputStream("temp.txt"));
            while (s.hasNextLine()) {
                pw.println(s.nextLine());
            }
            pw.close();
            s.close();

        } catch (FileNotFoundException ex) {
            System.out.println("Copy file is not found! ");
        }

    }

}
