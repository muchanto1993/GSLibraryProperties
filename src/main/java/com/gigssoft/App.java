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
        // Create a hash map
        HashMap hm = new HashMap();

        // Put elements to the map
        hm.put("dbpassword", "password");
        hm.put("database", "localhost");
        hm.put("dbuser", "mkyong");

        ControllerProperties cp = new ControllerProperties();

        System.out.println("-WriteToProperties Begin-");
        cp.WriteToProperties(hm);
        System.out.println("-WriteToProperties End-");

        System.out.println("-LoadAProperties Begin-");
        Properties prop = cp.LoadAProperties();
        String text = "";

        Enumeration<?> e = prop.propertyNames();
        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            String value = prop.getProperty(key);

            System.out.println("Key : " + key + ", Value : " + value);
            text += "Key : " + key + ", Value : " + value + "\n";
        }
        JOptionPane.showMessageDialog(null, text);
        System.out.println("-LoadAProperties end-");
    }

}
