/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics;

import Data.Orders;
import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Crimson
 */
public class MainController {

    public static Handler handler;

    //Process tab
    @FXML
    private Label ProcessLabel;
    @FXML
    private ScrollPane scroll;
    @FXML
    private TextArea area;

    //Map tab
    @FXML
    private TextArea map;

    //Crusty Crab tab
    @FXML
    private TextArea CrustyCrabOrders;
    @FXML
    private TextArea CrustyCrabSales;

    //Burger Krusty tab
    @FXML
    private TextArea BurgerKrustyOrders;
    @FXML
    private TextArea BurgerKrustySales;

    //Phum Bucket tab
    @FXML
    private TextArea PhumBucketOrders;
    @FXML
    private TextArea PhumBucketSales;

    //Dosneyland tab
    @FXML
    private TextArea DosneylandOrders;
    @FXML
    private TextArea DosneylandSales;

    //Area 76 tab
    @FXML
    private TextArea Area76Orders;
    @FXML
    private TextArea Area76Sales;

    //New Day window
    @FXML
    private TextField arrivalTime;
    @FXML
    private TextField restaurantName;
    @FXML
    private TextField foodName;
    @FXML
    private TextField remarks;
    @FXML
    private TextField cuscoordinate;
    @FXML
    private Label menuOutput;
    @FXML
    private Button addOrderButton;
    @FXML
    private Button doneAddingButton;
    //Add order buttons
    @FXML
    private Button CrabbyPatty;
    @FXML
    private Button CrabbyMeal;
    @FXML
    private Button SailorsSurprise;
    @FXML
    private Button PhumBurger;
    @FXML
    private Button PhumFries;
    @FXML
    private Button PhumPie;
    @FXML
    private Button TheKlogger;
    @FXML
    private Button FishSandwich;
    @FXML
    private Button TwistyLard;
    @FXML
    private Button MiniMics;
    @FXML
    private Button BoastedDuck;
    @FXML
    private Button PremiumHotdogs;
    @FXML
    private Button Rayduck;
    @FXML
    private Button GreenBeef;
    @FXML
    private Button BlueApples;

    public MainController() {
    }

    public MainController(Handler handler) {
        this.handler = handler;
    }

    public void DisplayProcess(ActionEvent event) {
        String wholeprocess = "";
        ArrayList<Orders> ordersarr = handler.getOrdersarr();
        wholeprocess = wholeprocess + ("Day " + handler.getDay() + "\n");
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
                wholeprocess = wholeprocess + ("0: A new day has start !") + "\n";
            }
            for (int i = 0; i < ordersarr.size(); i++) {
                if (ordersarr.get(i).getArrivaltime() == clock) {
                    if (ordersarr.get(i).getSpecialreq() != null) {
                        wholeprocess = wholeprocess + (clock + ": Customer " + (ordersarr.indexOf(ordersarr.get(i)) + 1) + "(" + ordersarr.get(i).getCustomerlocationX() + "," + ordersarr.get(i).getCustomerlocationY() + ")" + " wants to order " + ordersarr.get(i).getDishname() + " (" + ordersarr.get(i).getSpecialreq() + ") from " + ordersarr.get(i).getResname() + "." + "\n");
                    } else {
                        wholeprocess = wholeprocess + (clock + ": Customer " + (ordersarr.indexOf(ordersarr.get(i)) + 1) + "(" + ordersarr.get(i).getCustomerlocationX() + "," + ordersarr.get(i).getCustomerlocationY() + ")" + " wants to order " + ordersarr.get(i).getDishname() + " from " + ordersarr.get(i).getResname() + "." + "\n");
                    }
                    wholeprocess = wholeprocess + (clock + ": Branch of " + ordersarr.get(i).getResname() + " at " + ordersarr.get(i).getBranchTakeOrder().getLocation() + " takes the order." + "\n");
                    //wholeprocess = wholeprocess + ("Number of orders to be processed : " + ordersarr.get(i).getBranchTakeOrder().getTotalOrder());
                } else if (ordersarr.get(i).getFinishedcookingtime() == clock) {
                    wholeprocess = wholeprocess + (clock + ": Branch of " + ordersarr.get(i).getResname() + " at " + ordersarr.get(i).getBranchTakeOrder().getLocation() + " finished the order and starts delivering the food to customer " + (ordersarr.indexOf(ordersarr.get(i)) + 1) + "(" + ordersarr.get(i).getCustomerlocationX() + "," + ordersarr.get(i).getCustomerlocationY() + ")" + "." + "\n");
                } else if (ordersarr.get(i).getTimedeliveredtocustomer() == clock) {
                    wholeprocess = wholeprocess + (clock + ": The food have been delivered to customer " + (ordersarr.indexOf(ordersarr.get(i)) + 1) + "(" + ordersarr.get(i).getCustomerlocationX() + "," + ordersarr.get(i).getCustomerlocationY() + ")" + "." + "\n");
                    //ordersarr.get(i).getBranchTakeOrder().completeOrder(ordersarr.get(i));
                    //wholeprocess = wholeprocess + ("Number of orders to be processed : " + ordersarr.get(i).getBranchTakeOrder().getTotalOrder());
                }
            }
            for (int j = 0; j < ordersarr.size(); j++) {
                if (ordersarr.get(j).getBranchTakeOrder().getProcessTimeLeft() == 0) {
                    complete = true;
                } else {
                    complete = false;
                }
            }
            if (clock == workingHour - 1 && complete == true) {
                wholeprocess = wholeprocess + (clock + ": All customers are served and shops are closed." + "\n");
            }
        }
        //ProcessLabel.setText(wholeprocess);
        area.setText(wholeprocess);
        scroll.setContent(area);
    }

    public void newDay() throws IOException {
        File test = new File("./test.txt");
        if (test.exists()) {
            test.delete();
            System.out.println("Test.txt deleted.");
        }
        if (test.exists()) {
            test.delete();
            System.out.println("Test.txt deleted.");
        }
        FXMLLoader loader = new FXMLLoader(GUIMain.class.getResource("/Graphics/addOrderWindow.fxml"));
        Parent addOrderWindow = loader.load();

        Stage addOrderStage = new Stage();
        addOrderStage.setTitle("New Day!");
        addOrderStage.initModality(Modality.WINDOW_MODAL);
        Scene scene = new Scene(addOrderWindow);
        addOrderStage.setScene(scene);
        addOrderStage.showAndWait();
    }

    public void addToOrder(ActionEvent event) throws IOException {
        Button btn = (Button) event.getSource();
        if (btn.equals(CrabbyPatty)) {
            restaurantName.setText("Crusty Crab");
            foodName.setText("Crabby Patty");
        } else if (btn.equals(CrabbyMeal)) {
            restaurantName.setText("Crusty Crab");
            foodName.setText("Crabby Meal");
        } else if (btn.equals(SailorsSurprise)) {
            restaurantName.setText("Crusty Crab");
            foodName.setText("Sailors Surprise");
        } else if (btn.equals(PhumBurger)) {
            restaurantName.setText("Phum Bucket");
            foodName.setText("Phum Burger");
        } else if (btn.equals(PhumFries)) {
            restaurantName.setText("Phum Bucket");
            foodName.setText("Phum Fries");
        } else if (btn.equals(PhumPie)) {
            restaurantName.setText("Phum Bucket");
            foodName.setText("Phum Pie");
        } else if (btn.equals(TheKlogger)) {
            restaurantName.setText("Burger Krusty");
            foodName.setText("The Klogger");
        } else if (btn.equals(FishSandwich)) {
            restaurantName.setText("Burger Krusty");
            foodName.setText("Fish Sandwich");
        } else if (btn.equals(TwistyLard)) {
            restaurantName.setText("Burger Krusty");
            foodName.setText("Twisty Lard");
        } else if (btn.equals(MiniMics)) {
            restaurantName.setText("Dosneyland");
            foodName.setText("Mini Mics");
        } else if (btn.equals(BoastedDuck)) {
            restaurantName.setText("Dosneyland");
            foodName.setText("Boasted Duck");
        } else if (btn.equals(PremiumHotdogs)) {
            restaurantName.setText("Dosneyland");
            foodName.setText("Premium Hotdogs");
        } else if (btn.equals(Rayduck)) {
            restaurantName.setText("Area 76");
            foodName.setText("Rayduck");
        } else if (btn.equals(GreenBeef)) {
            restaurantName.setText("Area 76");
            foodName.setText("Green Beef");
        } else if (btn.equals(BlueApples)) {
            restaurantName.setText("Area 76");
            foodName.setText("Blue Apples");
        }
    }

    public void addOrder() throws IOException {
        Boolean noerror = true;
        String aTime, resname, dishname, customerremark, customerlocation;
        aTime = arrivalTime.getText();
        resname = restaurantName.getText();
        dishname = foodName.getText();
        customerremark = remarks.getText();
        customerlocation = cuscoordinate.getText();

        if (!aTime.matches("[0-9]+")) {
            arrivalTime.setStyle("-fx-border-color: red");
            noerror = false;
        } else {
            noerror = true;
            arrivalTime.setStyle("-fx-border-color: lightGray");
        }

        if (customerlocation.matches("[^0-9 ]+") || !customerlocation.contains(" ")) {
            cuscoordinate.setStyle("-fx-border-color: red");
            noerror = false;
        } else {
            noerror = true;
            cuscoordinate.setStyle("-fx-border-color: lightGray");
        }

        if (noerror) {
            menuOutput.setText("Order successfully submitted.");
            FileWriter fw = new FileWriter("test.txt", true);
            fw.write(aTime + "\n");
            fw.write(resname + "\n");
            fw.write(dishname + "\n");
            if (customerremark.length() != 0) {
                fw.write(customerremark + "\n");
            }
            fw.write(customerlocation + "\n");
            System.out.println("");
            fw.close();

            arrivalTime.setText("");
            restaurantName.setText("");
            foodName.setText("");
            remarks.setText("");
            cuscoordinate.setText("");
        } else {
            menuOutput.setText("Error!");
        }

    }

    public void finishAddingOrders() throws IOException {
        Stage stage = (Stage) doneAddingButton.getScene().getWindow();
        stage.close();

        int day = handler.getDay();
        try{
            day++;
            System.out.println("");
            Launcher newlaunch = new Launcher("test.txt", day);
            Handler newhandler = newlaunch.getHandler();
            this.setHandler(newhandler);
        } catch (Exception e) {
            System.out.println(e);
            day--;
            newDay();
        }

    }

    public void displayMap() throws IOException {
        char[][] newmap = handler.getNewMap().getMap();
        int length = handler.getNewMap().getLength();
        int width = handler.getNewMap().getWidth();
        String display = "";

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                display = display + (newmap[i][j]);
            }
            display = display + "\n";
        }
        map.setText(display);
    }

    public void displayOrders() throws IOException {
        String ccorder = "", bkorder = "", pborder = "", dorder = "", a76order = "", ccsales = "", bksales = "", pbsales = "", dsales = "", a76sales = "";

        //Crusty Crab
        for (int k = 0; k < handler.getRestlist().get(0).getOrderList().size(); k++) {
            ccorder = ccorder + (handler.getRestlist().get(0).getOrderList().get(k).toString(k) + "\n\n");
        }
        for (int j = 0; j < handler.getRestlist().get(0).getBranches().size(); j++) {
            ccsales = ccsales + ("Branch at " + handler.getRestlist().get(0).getBranches().get(j).getLocation() + " earns RM " + handler.getRestlist().get(0).getBranches().get(j).getTotalProfit() + "\n");
        }
        ccsales = ccsales +("\n--------------------------------------------------------------------------");
        ccsales = ccsales +("\n" + handler.getRestlist().get(0).getName() + " earns total of RM " + handler.getRestlist().get(0).getTotalIncome());
        ccsales = ccsales +("\n--------------------------------------------------------------------------\n");
        CrustyCrabOrders.setText(ccorder);
        CrustyCrabSales.setText(ccsales);
        
        //Phum Bucket
        for (int k = 0; k < handler.getRestlist().get(1).getOrderList().size(); k++) {
            pborder = pborder + (handler.getRestlist().get(1).getOrderList().get(k).toString(k) + "\n\n");
        }
        for (int j = 0; j < handler.getRestlist().get(1).getBranches().size(); j++) {
            pbsales = pbsales + ("Branch at " + handler.getRestlist().get(1).getBranches().get(j).getLocation() + " earns RM " + handler.getRestlist().get(1).getBranches().get(j).getTotalProfit() + "\n");
        }
        pbsales = pbsales +("\n--------------------------------------------------------------------------");
        pbsales = pbsales +("\n" + handler.getRestlist().get(1).getName() + " earns total of RM " + handler.getRestlist().get(1).getTotalIncome());
        pbsales = pbsales +("\n--------------------------------------------------------------------------\n");
        PhumBucketOrders.setText(pborder);
        PhumBucketSales.setText(pbsales);
        
        //Burger Krusty
        for (int k = 0; k < handler.getRestlist().get(2).getOrderList().size(); k++) {
            bkorder = bkorder + (handler.getRestlist().get(2).getOrderList().get(k).toString(k) + "\n\n");
        }
        for (int j = 0; j < handler.getRestlist().get(2).getBranches().size(); j++) {
            bksales = bksales + ("Branch at " + handler.getRestlist().get(2).getBranches().get(j).getLocation() + " earns RM " + handler.getRestlist().get(2).getBranches().get(j).getTotalProfit() + "\n");
        }
        bksales = bksales +("\n--------------------------------------------------------------------------");
        bksales = bksales +("\n" + handler.getRestlist().get(2).getName() + " earns total of RM " + handler.getRestlist().get(2).getTotalIncome());
        bksales = bksales +("\n--------------------------------------------------------------------------\n");
        BurgerKrustyOrders.setText(bkorder);
        BurgerKrustySales.setText(bksales);
        
        //Dosneyland
        for (int k = 0; k < handler.getRestlist().get(3).getOrderList().size(); k++) {
            dorder = dorder + (handler.getRestlist().get(3).getOrderList().get(k).toString(k) + "\n\n");
        }
        for (int j = 0; j < handler.getRestlist().get(3).getBranches().size(); j++) {
            dsales = dsales + ("Branch at " + handler.getRestlist().get(3).getBranches().get(j).getLocation() + " earns RM " + handler.getRestlist().get(3).getBranches().get(j).getTotalProfit() + "\n");
        }
        dsales = dsales +("\n--------------------------------------------------------------------------");
        dsales = dsales +("\n" + handler.getRestlist().get(3).getName() + " earns total of RM " + handler.getRestlist().get(3).getTotalIncome());
        dsales = dsales +("\n--------------------------------------------------------------------------\n");
        DosneylandOrders.setText(dorder);
        DosneylandSales.setText(dsales);
        
        //Area 76
        for (int k = 0; k < handler.getRestlist().get(4).getOrderList().size(); k++) {
            a76order = a76order + (handler.getRestlist().get(4).getOrderList().get(k).toString(k) + "\n\n");
        }
        for (int j = 0; j < handler.getRestlist().get(4).getBranches().size(); j++) {
            a76sales = a76sales + ("Branch at " + handler.getRestlist().get(4).getBranches().get(j).getLocation() + " earns RM " + handler.getRestlist().get(4).getBranches().get(j).getTotalProfit() + "\n");
        }
        a76sales = a76sales +("\n--------------------------------------------------------------------------");
        a76sales = a76sales +("\n" + handler.getRestlist().get(4).getName() + " earns total of RM " + handler.getRestlist().get(4).getTotalIncome());
        a76sales = a76sales +("\n--------------------------------------------------------------------------\n");
        Area76Orders.setText(a76order);
        Area76Sales.setText(a76sales);
    }

    public void resetAll() throws IOException {
        //Deleting files
        File Area76 = new File("Area 76.txt");
        File BurgerKrusty = new File("Burger Krusty.txt");
        File CrustyCrab = new File("Crusty Crab.txt");
        File Dosneyland = new File("Dosneyland.txt");
        File Log = new File("Log.txt");
        File PhumBucket = new File("Phum Bucket.txt");
        File temp = new File("temp.txt");
        File test = new File("test.txt");

        if (Area76.delete() && BurgerKrusty.delete() && CrustyCrab.delete() && Dosneyland.delete() && Log.delete() && PhumBucket.delete() && temp.delete() && test.delete()) {
            System.out.println("All files deleted successfully.");
        } else {
            System.out.println("Failed to delete files.");
        }

        //Resetting certain parts of GUI
        area.setText("");
        CrustyCrabOrders.setText("");
        CrustyCrabSales.setText("");
        BurgerKrustyOrders.setText("");
        BurgerKrustySales.setText("");
        PhumBucketOrders.setText("");
        PhumBucketSales.setText("");
        DosneylandOrders.setText("");
        DosneylandSales.setText("");
        Area76Orders.setText("");
        Area76Sales.setText("");

        System.out.println("");
        Launcher launch = new Launcher("Customer.txt", 1);
        Handler handler = launch.getHandler();
        this.setHandler(handler);
        displayOrders();

    }

    public void openLogFile() throws IOException {
        if (Desktop.isDesktopSupported()) {
            try {
                File myFile = new File("Log.txt");
                Desktop.getDesktop().open(myFile);
            } catch (IOException ex) {
            }
        }
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

}
