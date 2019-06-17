/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Crimson
 */
public class GUIMain extends Application implements EventHandler<ActionEvent> {

    @Override
    public void start(Stage primaryStage) throws IOException {
        //Program stuff
        resetAll();
        Launcher launch = new Launcher("Customer.txt");
        Handler handler = launch.getHandler();

        //Graphics stuff
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Graphics/FXML.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        MainController mc = fxmlLoader.<MainController>getController();
        mc.setHandler(handler);
        Scene scene = new Scene(root);
        primaryStage.setTitle("CrabFood");
        primaryStage.setScene(scene);
        primaryStage.show();
        mc.displayMap();
        mc.displayOrders();

    }

    @Override
    public void handle(ActionEvent event) {

    }
    
    public void resetAll() {
        File Area76 = new File("Area 76.txt");
        File BurgerKrusty = new File("Burger Krusty.txt");
        File CrustyCrab = new File("Crusty Crab.txt");
        File Dosneyland = new File("Dosneyland.txt");
        File Log = new File("Log.txt");
        File PhumBucket = new File("Phum Bucket.txt");
        File temp = new File("temp.txt");
        File test = new File("test.txt");

//        if (Area76.delete() && BurgerKrusty.delete() && CrustyCrab.delete() && Dosneyland.delete() && Log.delete() && PhumBucket.delete() && temp.delete() && test.delete()) {
//            System.out.println("All files deleted successfully.");
//        } else {
//            System.out.println("Failed to delete files.");
//        }
        
        if (Area76.delete()) {
            System.out.println("Area76 files deleted successfully.");
        } else {
            System.out.println("Failed to delete files.");
        }
        
        if (BurgerKrusty.delete()) {
            System.out.println("Burger Krusty files deleted successfully.");
        } else {
            System.out.println("Failed to delete files.");
        }
        
        if (CrustyCrab.delete()) {
            System.out.println("Crusty Crab files deleted successfully.");
        } else {
            System.out.println("Failed to delete files.");
        }
        
        if (Dosneyland.delete()) {
            System.out.println("Dosneyland files deleted successfully.");
        } else {
            System.out.println("Failed to delete Dosneyland files.");
        }
        
        if (Log.delete()) {
            System.out.println("Log files deleted successfully.");
        } else {
            System.out.println("Failed to delete Log files.");
        }
        
        if (PhumBucket.delete()) {
            System.out.println("PhumBucket files deleted successfully.");
        } else {
            System.out.println("Failed to delete PhumBucket files.");
        }
        
        if (temp.delete()) {
            System.out.println("Temp files deleted successfully.");
        } else {
            System.out.println("Failed to delete Temp files.");
        }
        
        if (test.delete()) {
            System.out.println("test files deleted successfully.");
        } else {
            System.out.println("Failed to delete test files.");
        }
        
    }

}
