package Data;

import java.util.ArrayList;

public class Orders {

    private ArrayList<Restaurants> restlist;
    private String resname, dishname, specialreq;
    private int arrivaltime, finishedcookingtime, timetakentocook, deliverytime, totaltime, timedeliveredtocustomer, customerlocationX, customerlocationY, orderNo;
    private Branch branchTakeOrder;
    private Restaurants rest;
    private int profit;

    public Orders(String resname, String dishname, int arrivaltime, int customerlocationX, int customerlocationY, ArrayList<Restaurants> restlist, ArrayList<Orders> order, String specialreq) {//Restlist, dishpreptime
        this.restlist = restlist;
        this.resname = resname;
        this.dishname = dishname;
        this.arrivaltime = arrivaltime;
        this.customerlocationX = customerlocationX;
        this.customerlocationY = customerlocationY;
        this.timetakentocook = getTimeTakenToCook();
        this.finishedcookingtime = timetakentocook + arrivaltime;
        this.branchTakeOrder = determine(resname, dishname, customerlocationX, customerlocationY);
        this.deliverytime = getDeliverytime(branchTakeOrder);
        this.totaltime = this.timetakentocook + this.deliverytime;
        this.timedeliveredtocustomer = this.arrivaltime + this.totaltime;
        this.orderNo = order.size() + 1;
        this.specialreq = specialreq;
        this.profit = getProfit();
        this.rest = getRest();
        branchTakeOrder.acceptOrder(this);
    }

    public int getProfit() {
        ArrayList<Dishes> dishesList;
        int profit = 0;
        for (int i = 0; i < restlist.size(); i++) {
            if (restlist.get(i).getName().equals(resname)) {
                dishesList = restlist.get(i).getDishes();
                for (int j = 0; j < dishesList.size(); j++) {
                    if (dishesList.get(j).getDishName().equals(dishname)) {
                        profit = profit + dishesList.get(j).getPrice();
                    }
                }
            }
        }
        return profit;
    }

    public String getSpecialreq() {
        return specialreq;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public Branch getBranchTakeOrder() {
        return branchTakeOrder;
    }

    public ArrayList<Restaurants> getRestlist() {
        return restlist;
    }

    public int getTimetakentocook() {
        return timetakentocook;
    }

    public int getDeliverytime() {
        return deliverytime;
    }

    public Restaurants getRest() {
        for (int i = 0; i < restlist.size(); i++) {
            if (restlist.get(i).getName().equals(resname)) {
                return restlist.get(i);
            }
        }
        return null;
    }

    public int getTimeTakenToCook() {
        ArrayList<Dishes> dishesList;
        int preparationTime = 0;
        for (int i = 0; i < restlist.size(); i++) {
            if (restlist.get(i).getName().equals(resname)) {
                dishesList = restlist.get(i).getDishes();
                for (int j = 0; j < dishesList.size(); j++) {
                    if (dishesList.get(j).getDishName().equals(dishname)) {
                        preparationTime = dishesList.get(j).getPreparationTime();
                    }
                }
            }
        }
        return preparationTime;
    }

    public Branch determine(String resname, String dishname, int customerLocationX, int customerLocationY) { //Determines which branch will take the order
        ArrayList<Branch> branchList;
        int totalTimeProcessInc;            // Branch's total time left to process all orders including newOrder
        Branch branchTakeOrder = null;
        int minimum;

        for (int i = 0; i < restlist.size(); i++) {
            if (restlist.get(i).getName().equals(resname)) {
                branchList = restlist.get(i).getBranches();

                branchTakeOrder = branchList.get(0);
                totalTimeProcessInc = getDishPrepTime(resname, dishname) + branchList.get(0).getProcessTimeLeft() + branchTakeOrder.getDistance(customerlocationX, customerlocationY);
                for (int k = 0; k < branchTakeOrder.getOrderListSize(); k++) {
                    if (arrivaltime >= branchTakeOrder.getOrderList().getElement(k).timedeliveredtocustomer) {
                        totalTimeProcessInc = totalTimeProcessInc - branchTakeOrder.getOrderList().getElement(k).timedeliveredtocustomer;
                    }
                }
                minimum = totalTimeProcessInc;

                for (int j = 1; j < branchList.size(); j++) {
                    totalTimeProcessInc = getDishPrepTime(resname, dishname) + branchList.get(j).getProcessTimeLeft() + branchList.get(j).getDistance(customerlocationX, customerlocationY);
                    for (int k = 0; k < branchList.get(j).getOrderListSize(); k++) {
                        if (arrivaltime >= branchList.get(j).getOrderList().getElement(k).timedeliveredtocustomer) {
                            totalTimeProcessInc = totalTimeProcessInc - branchList.get(j).getOrderList().getElement(k).timedeliveredtocustomer;
                        }
                    }
                    if (totalTimeProcessInc < minimum) {
                        branchTakeOrder = branchList.get(j);
                        minimum = totalTimeProcessInc;
                    }
                }

            }
        }
        return branchTakeOrder;
    }

    public int getDeliverytime(Branch branchTakeOrder) {
        int deliveryTime = 0;
        deliveryTime = branchTakeOrder.getDistance(customerlocationX, customerlocationY);
        return deliveryTime;
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

    //Getters and Setters
    public int getArrivaltime() {
        return arrivaltime;
    }

    public int getFinishedcookingtime() {
        return finishedcookingtime;
    }

    public int getTimedeliveredtocustomer() {
        return timedeliveredtocustomer;
    }

    public int getCustomerlocationX() {
        return customerlocationX;
    }

    public int getCustomerlocationY() {
        return customerlocationY;
    }

    public void setArrivaltime(int arrivaltime) {
        this.arrivaltime = arrivaltime;
    }

    public String getResname() {
        return resname;
    }

    public void setResname(String resname) {
        this.resname = resname;
    }

    public int getTotaltime() {
        return totaltime;
    }

    public String getDishname() {
        return dishname;
    }

    public void setDishname(String dishname) {
        this.dishname = dishname;
    }

    public String toString(int i) {
        return "Order " + (i + 1) + "\nRestaurant name = " + resname + "\nBranch location : " + branchTakeOrder.getLocation() + "\nDish name = " + dishname + "\nArrival time = " + arrivaltime
                + "\nFinished cooking at = " + finishedcookingtime + "\nDelivered at = " + timedeliveredtocustomer + "\nCustomer location = " + "(" + customerlocationX + "," + customerlocationY + ")";

    }

}
