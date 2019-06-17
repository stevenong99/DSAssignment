package Map;

import Data.Restaurants;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Map {

    private char[][] map;
    private int length, width;
    private ArrayList<Restaurants> list;

    public Map(ArrayList<Restaurants> list) {
        this.length = 14;
        this.width = 25;
        this.map = new char[length][width];
        this.list = list;
        createMap();
        writetoFile();
        display();
    }

    public void display() {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println("");
        }
    }

    public void createMap() {
        System.out.println("\nMap:");

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                map[i][j] = '0';
            }
        }

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                int k = 0;
                while (k < list.size()) {
                    if (map[j][i] != '0') {
                        break;
                    }
                    map[j][i] = list.get(k).compareLocation(i, j);
                    k++;
                }
            }
        }
    }

    public void writetoFile() {
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream("res/worlds/world2.txt"));
            pw.println(width + " " + length);
            
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < width; j++) {
                    if(map[i][j] == 'C'){//Crusty Crab
                        pw.print(1);
                    }
                    else if(map[i][j] == 'P'){//Phum Bucket
                        pw.print(2);
                    }
                    else if(map[i][j] == 'B'){//Burger Krusty
                        pw.print(3);
                    }
                    else if(map[i][j] == 'D'){//Dosneyland
                        pw.print(4);
                    }
                    else if(map[i][j] == 'A'){//Area 76
                        pw.print(5);
                    }
                    else
                        pw.print(map[i][j]);
                }
                pw.println("");
            }
            
            pw.close();

        } catch (FileNotFoundException ex) {
            System.out.println("File not found exception.");;
        }
    }

    public char[][] getMap() {
        return map;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }
    
    
    
    

}
