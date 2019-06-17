/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

/**
 *
 * @author TAN JIA QIN (WIF180073)
 */
public class Branch {

    private int CoordinateX;
    private int CoordinateY;
    private int totalOrder;
    private Queue<Orders> orderList;
    private int ProcessTimeLeft;
    private int totalProfit;

    public Branch(int x, int y) {
        CoordinateX = x;
        CoordinateY = y;
        this.totalOrder = 0;
        this.orderList = new Queue<>();
        this.ProcessTimeLeft = 0;
        this.totalProfit = 0;
    }

    public void acceptOrder(Orders newOrder) {
        orderList.enqueue(newOrder);
        totalProfit = totalProfit + newOrder.getProfit();
        totalOrder++;
        this.ProcessTimeLeft = ProcessTimeLeft + newOrder.getTotaltime();
    }

    public int getTotalProfit() {
        return totalProfit;
    }

    public void completeOrder() {
        this.ProcessTimeLeft = ProcessTimeLeft - orderList.getElement(0).getTotaltime();
        orderList.dequeue();
        totalOrder--;
    }

    public int getTotalOrder() {
        return totalOrder;
    }

    public int getProcessTimeLeft() {
        return ProcessTimeLeft;
    }

    public int getFirstOrderTime() {
        if (orderList.isEmpty() == false) {
            int time = orderList.getElement(0).getTotaltime();
            return time;
        }
        return 0;
    }

    public String getLocation() {
        String location = "";
        location = '(' + location + this.CoordinateX + ',' + this.CoordinateY + ')';
        return location;
    }

    public int getCoordinateX() {
        return CoordinateX;
    }

    public int getCoordinateY() {
        return CoordinateY;
    }

    public Queue<Orders> getOrderList() {
        return orderList;
    }

    public int getOrderListSize() {
        return orderList.getSize();
    }

    @Override
    public String toString() {
        return "Order List=" + orderList + "\nProcess Time Left=" + ProcessTimeLeft;
    }

    public int getDistance(int customerlocationX, int customerlocationY) {
        return Math.abs(customerlocationX - CoordinateX) + Math.abs(customerlocationY - CoordinateY);
    }

}
