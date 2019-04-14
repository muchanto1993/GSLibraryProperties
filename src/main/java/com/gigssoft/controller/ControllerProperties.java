/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gigssoft.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import javax.swing.JOptionPane;

/**
 *
 * @author MAB
 */
public class ControllerProperties {

    private String filename = "";
    private String directory = "";

    public String getFilename() {
        if ("".equals(filename)) {
            filename = "auto-config.properties";
        }

        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getDirectory() {
        if ("".equals(directory)) {
            directory = "file-config";
        }

        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    /* Main Method - Begin*/
    public String CheckLocation() {
        String workingDir = System.getProperty("user.dir");
        System.out.println("Current Working Directory : " + workingDir);

        return workingDir;
    }

    public String CreateDirectory() {
        String pathDirectory = this.CheckLocation() + "\\" + getDirectory();
        Path path = Paths.get(pathDirectory);

        // If directory exists?
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
                System.out.println("Create Directory : " + pathDirectory);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Err : " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
        }else{
            System.out.println("Directory is Exist");
        }

        return pathDirectory;
    }

    public void WriteToProperties(HashMap<String, String> mapProperties) {
        Properties prop = new Properties();
        OutputStream output = null;

        try {
            output = new FileOutputStream(this.CreateDirectory() + "\\" + getFilename());

            // Get a set of the entries
            Set set = mapProperties.entrySet();

            // Get an iterator
            Iterator i = set.iterator();
            while (i.hasNext()) {
                Map.Entry me = (Map.Entry) i.next();

                prop.setProperty(me.getKey().toString(), me.getValue().toString());
            }

            // Save properties to project root folder
            prop.store(output, null);
            System.out.println("Success Write To Properties, file name : " + getFilename());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Err : " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Err : " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    System.exit(0);
                }
            }
        }
    }

    public Properties LoadAProperties() {
        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream(this.CreateDirectory() + "\\" + getFilename());

            // Load a properties file
            prop.load(input);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Err : " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Err : " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    System.exit(0);
                }
            }
        }

        return prop;
    }
    /* Main Method - End*/
}
