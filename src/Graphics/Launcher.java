/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics;

import Graphics.GUIMain;
import Data.Branch;
import Data.Orders;
import Data.Restaurants;
import Data.WaitLine;
import Map.Map;
import TextReaders.Customertxtreader;
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;

/**
 *
 * @author Crimson
 */
public class Launcher {
    protected ArrayList<Orders> ordersarr;
    protected ArrayList<Restaurants> restlist;
    protected Map newMap;
    protected WaitLine waitLine;
    protected Customertxtreader customerTR;
    protected Handler handler;
    protected int day;
    private String[] args;

    public Launcher(String filename) throws IOException {
        this.customerTR = new Customertxtreader(filename, "Input2.txt");
        this.ordersarr = customerTR.getOrdersarr();
        this.restlist = customerTR.getRestlist();
        this.newMap = new Map(restlist);
        this.day = 1;
        this.handler = new Handler(this);
        this.waitLine = new WaitLine(handler,day);
    }
    
    public Launcher(String filename, int day) throws IOException {
        this.customerTR = new Customertxtreader(filename, "Input2.txt");
        this.ordersarr = customerTR.getOrdersarr();
        this.restlist = customerTR.getRestlist();
        this.newMap = new Map(restlist);
        this.day = day;
        this.handler = new Handler(this);
        this.waitLine = new WaitLine(handler,day);
    }
    
    
    //Getters

    public ArrayList<Orders> getOrdersarr() {
        return ordersarr;
    }

    public ArrayList<Restaurants> getRestlist() {
        return restlist;
    }

    public Map getNewMap() {
        return newMap;
    }

    public WaitLine getWaitLine() {
        return waitLine;
    }

    public Customertxtreader getCustomerTR() {
        return customerTR;
    }

    public Handler getHandler() {
        return handler;
    }

    public int getDay() {
        return day;
    }
    
    public int incrementDay(){
        return day = day + 1;
    }
    
    
    
    
    
    
    
    

    
}
