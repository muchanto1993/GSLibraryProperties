/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gigssoft;

import com.gigssoft.controller.ControllerProperties;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;
import java.util.Scanner;
//import java.util.Set;
import javax.swing.JOptionPane;

/**
 *
 * @author MAB
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("----- Menu Properties -----");
            System.out.println("1. Create Properties");
            System.out.println("2. Load Custom Properties");
            System.out.println("3. Load Default Properties");
            System.out.println("4. Exit");
            System.out.println("---------------------------");
            System.out.print("Choose : ");
            int idMenu = scanner.nextInt();
            System.out.println("---------------------------");

            ControllerProperties cp = new ControllerProperties();
            Properties prop;
            switch (idMenu) {
                case 1:
                    System.out.println("----- Create Properties -----");
                    System.out.print("Please Input Directory Name : ");
                    String directoryName = scanner.next();
                    System.out.print("Please Input File Name : ");
                    String fileName = scanner.next();

                    if (directoryName.equals("") || fileName.equals("")) {
                        System.out.println("Please Input Directory Name or File Name");
                    } else {
                        cp.setDirectory(directoryName);
                        cp.setFilename(fileName + ".properties");

                        // Create a hash map
                        HashMap hm = new HashMap();

                        // Put elements to the map
                        hm.put("dbpassword", "password");
                        hm.put("database", "localhost");
                        hm.put("dbuser", "mkyong");

                        cp.WriteToProperties(hm);
                    }

                    prop = cp.LoadCustomProperties();
                    String text = "";

                    Enumeration<?> e = prop.propertyNames();
                    while (e.hasMoreElements()) {
                        String key = (String) e.nextElement();
                        String value = prop.getProperty(key);

                        System.out.println("Key : " + key + ", Value : " + value);
                        text += "Key : " + key + ", Value : " + value + "\n";
                    }
                    JOptionPane.showMessageDialog(null, text);
                    System.out.println("-----------------------------");
                    break;

                case 2:
                    String FILE_DIR = "c:\\folder";
                    String FILE_TEXT_EXT = ".jpg";
                    
                    cp.listFile(FILE_DIR, FILE_TEXT_EXT);
                    break;

                case 3:
                    System.out.println("----- Load Default Properties -----");
                    prop = cp.LoadDefaultProperties();

                    // Java 8 , print key and values
                    prop.forEach((key, value) -> System.out.println("Key : " + key + ", Value : " + value));

                    // Java 8 Get all keys3
                    /*
                    prop.keySet().forEach(x -> System.out.println(x));

                    Set<Object> objects = prop.keySet();

                    Enumeration e = prop.propertyNames();
                    while (e.hasMoreElements()) {
                        String key = (String) e.nextElement();
                        String value = prop.getProperty(key);
                        System.out.println("Key : " + key + ", Value : " + value);
                    }
                     */
                    System.out.println("-----------------------------------");
                    break;

                case 4:
                    System.exit(0);
                    break;

                default:
                    System.out.println("Menu is not exist");
                    break;
            }

        }
    }

}
