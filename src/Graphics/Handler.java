/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics;

import Data.Branch;
import Data.Orders;
import Data.Restaurants;
import Data.WaitLine;
import Map.Map;
import TextReaders.Customertxtreader;
import java.util.ArrayList;

/**
 *
 * @author Crimson
 */
public class Handler {
    private Launcher launcher;

    public Handler(Launcher launcher) {
        this.launcher = launcher;
    }
    
    public ArrayList<Orders> getOrdersarr() {
        return launcher.getOrdersarr();
    }

    public ArrayList<Restaurants> getRestlist() {
        return launcher.getRestlist();
    }

    public Map getNewMap() {
        return launcher.getNewMap();
    }

    public WaitLine getWaitLine() {
        return launcher.getWaitLine();
    }

    public Customertxtreader getCustomerTR() {
        return launcher.getCustomerTR();
    }

    public int getDay(){
        return launcher.getDay();
    }
    
    public int incrementDay(){
        return launcher.incrementDay();
    }
    
}
