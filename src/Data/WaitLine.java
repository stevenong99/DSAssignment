/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Graphics.Handler;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Crimson
 */
public class WaitLine {

    private Queue<Orders> line;
    private ArrayList<Orders> ordersarr;
    private ArrayList<Restaurants> restlist;
    private int numberOfArrivals;
    private int numberServed;
    private int totalTimeWaited;
    private int day;
    private Handler handler;

    public WaitLine(Handler handler, int day) {
        this.handler = handler;
        this.ordersarr = handler.getOrdersarr();
        this.restlist = handler.getRestlist();
        this.day = day;
        simulate(ordersarr, day);
        System.out.println("");
        generateSales(restlist);

        //reset();
    }

    public void simulate(ArrayList<Orders> ordersarr, int day) {
        System.out.println("\nProcess list:");
        System.out.println("--------------------------- DAY " + day + " ---------------------------");
        int workingHour = ordersarr.get(0).getTimedeliveredtocustomer();
        boolean complete = false;

        for (int i = 0; i < ordersarr.size(); i++) {
            if (ordersarr.get(i).getTimedeliveredtocustomer() > workingHour) {
                workingHour = ordersarr.get(i).getTimedeliveredtocustomer();
            }
        }
        workingHour++;
        for (int clock = 0; clock < workingHour; clock++) {
            if (clock == 0) {
                System.out.println("0: A new day has start !");
            }
            for (int i = 0; i < ordersarr.size(); i++) {
                if (ordersarr.get(i).getArrivaltime() == clock) {
                    if (ordersarr.get(i).getSpecialreq() != null) {
                        System.out.println(clock + ": Customer " + (ordersarr.indexOf(ordersarr.get(i)) + 1) + "(" + ordersarr.get(i).getCustomerlocationX() + "," + ordersarr.get(i).getCustomerlocationY() + ")" + " wants to order " + ordersarr.get(i).getDishname() + " (" + ordersarr.get(i).getSpecialreq() + ") from " + ordersarr.get(i).getResname() + ".");
                    } else {
                        System.out.println(clock + ": Customer " + (ordersarr.indexOf(ordersarr.get(i)) + 1) + "(" + ordersarr.get(i).getCustomerlocationX() + "," + ordersarr.get(i).getCustomerlocationY() + ")" + " wants to order " + ordersarr.get(i).getDishname() + " from " + ordersarr.get(i).getResname() + ".");
                    }
                    System.out.println(clock + ": Branch of " + ordersarr.get(i).getResname() + " at " + ordersarr.get(i).getBranchTakeOrder().getLocation() + " takes the order.");
//                    ordersarr.get(i).getBranchTakeOrder().acceptOrder(ordersarr.get(i));
                    ordersarr.get(i).getRest().addOrderIntoArrayList(ordersarr.get(i));
                    ordersarr.get(i).getRest().setTotalIncome(ordersarr.get(i).getProfit());
                    //System.out.println("Number of orders to be processed : " + ordersarr.get(i).getBranchTakeOrder().getTotalOrder());
                } else if (ordersarr.get(i).getFinishedcookingtime() == clock) {
                    System.out.println(clock + ": Branch of " + ordersarr.get(i).getResname() + " at " + ordersarr.get(i).getBranchTakeOrder().getLocation() + " finished the order and starts delivering the food to customer " + (ordersarr.indexOf(ordersarr.get(i)) + 1) + "(" + ordersarr.get(i).getCustomerlocationX() + " " + ordersarr.get(i).getCustomerlocationY() + ")" + ".");
                } else if (ordersarr.get(i).getTimedeliveredtocustomer() == clock) {
                    System.out.println(clock + ": The food have been delivered to customer " + (ordersarr.indexOf(ordersarr.get(i)) + 1) + "(" + ordersarr.get(i).getCustomerlocationX() + "," + ordersarr.get(i).getCustomerlocationY() + ")" + "." );
                    ordersarr.get(i).getBranchTakeOrder().completeOrder();
                    //System.out.println("Number of orders to be processed : " + ordersarr.get(i).getBranchTakeOrder().getTotalOrder());
                }

                if (ordersarr.get(i).getBranchTakeOrder().getProcessTimeLeft() == 0) {
                    complete = true;
                } else {
                    complete = false;
                }
            }
            if (clock == workingHour - 1 && complete == true) {
                System.out.println(clock + ": All customers are served and shops are closed.");
            }
        }
        generateLogFile(ordersarr);
        generateRestLog(restlist);
    }

    public void generateSales(ArrayList<Restaurants> restlist) {
        System.out.println("Sales list according to each restaurant: ");
        for (int i = 0; i < restlist.size(); i++) {
            System.out.println("--------------------------------------------------------------------------");
            System.out.println("                              " +restlist.get(i).getName());
            System.out.println("--------------------------------------------------------------------------\n");
            for (int k = 0; k < restlist.get(i).getOrderList().size(); k++) {
                System.out.println(restlist.get(i).getOrderList().get(k).toString(k) + "\n");
            }
            for (int j = 0; j < restlist.get(i).getBranches().size(); j++) {
                System.out.println("Branch at " + restlist.get(i).getBranches().get(j).getLocation() + " earns RM " + restlist.get(i).getBranches().get(j).getTotalProfit());
            }
            System.out.println("--------------------------------------------------------------------------");
            System.out.println(restlist.get(i).getName() + " earns total of RM " + restlist.get(i).getTotalIncome());
            System.out.println("--------------------------------------------------------------------------\n");
        }
    }

    public void generateLogFile(ArrayList<Orders> ordersarr) {
        PrintWriter print = null;
        try {
            File check = new File("Log.txt");
            if (!check.exists()) {
                try {
                    check.createNewFile();
                } catch (IOException ex) {
                    Logger.getLogger(WaitLine.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            copy("Log.txt");
            Scanner s = new Scanner(new FileInputStream("temp.txt"));
            print = new PrintWriter(new FileOutputStream("Log.txt"));
            print.println("Day " + this.handler.getDay());
            print.printf("%-9s|%-8s|%-11s|%-22s|%-14s|%-11s", "Customer", "Arrival", "Order Time", "Finished Cooking Time", "Delivery Time", "Total Time");
            print.println();
            for (int i = 0; i < ordersarr.size(); i++) {
                print.printf("%-9d|%-8d|%-11d|%-22d|%-14d|%-11d", ordersarr.get(i).getOrderNo(), ordersarr.get(i).getArrivaltime(), ordersarr.get(i).getArrivaltime(), ordersarr.get(i).getFinishedcookingtime(), ordersarr.get(i).getDeliverytime(), ordersarr.get(i).getTotaltime());
                print.println();
            }
            print.println();
            while (s.hasNextLine()) {
                print.println(s.nextLine());
            }
            s.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Log File is not found!");
        } finally {
            print.close();
        }
    }

    public void generateRestLog(ArrayList<Restaurants> restlist) {
        for (int i = 0; i < restlist.size(); i++) {
            restlist.get(i).generateLog(this.handler.getDay());
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
            System.out.println("Copy File is not found! ");
        }

    }

    /**
     * Displays summary results of the simulation.
     */
    public void displayResults() {
        System.out.println();
        System.out.println("Number served = " + numberServed);
        System.out.println("Total time waited = " + totalTimeWaited);
        double averageTimeWaited = ((double) totalTimeWaited) / numberServed;
        System.out.println("Average time waited = " + averageTimeWaited);
        int leftInLine = numberOfArrivals - numberServed;
        System.out.println("Number left in line = " + leftInLine);
    } // end displayResults

    /**
     * Initializes the simulation.
     */
    public final void reset() {
        line.clear();
        numberOfArrivals = 0;
        numberServed = 0;
        totalTimeWaited = 0;
    } // end reset

}
