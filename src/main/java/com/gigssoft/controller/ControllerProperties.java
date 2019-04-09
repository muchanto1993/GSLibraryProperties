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

    private String filename = "auto-config.properties";
    private String directory = "file-config";

    public ControllerProperties() {
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String CheckLocation() {
        String workingDir = System.getProperty("user.dir");
        System.out.println("Current Working Directory : " + workingDir);

        return workingDir;
    }

    public String CreateDirectory() {
        String pathDirectory = this.CheckLocation() + "\\" + directory;
        Path path = Paths.get(pathDirectory);

        //if directory exists?
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
                System.out.println("Create Directory : " + pathDirectory);
            } catch (IOException io) {
                //fail to create directory
                JOptionPane.showMessageDialog(null, io.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                io.printStackTrace();
            }
        }

        return pathDirectory;
    }

    public void WriteToProperties(HashMap<String, String> mapProperties) {
        Properties prop = new Properties();
        OutputStream output = null;

        try {
            output = new FileOutputStream(this.CreateDirectory().toString() + "\\" + filename);

            // Get a set of the entries
            Set set = mapProperties.entrySet();

            // Get an iterator
            Iterator i = set.iterator();

            while (i.hasNext()) {
                Map.Entry me = (Map.Entry) i.next();

                prop.setProperty(me.getKey().toString(), me.getValue().toString());
            }

            // save properties to project root folder
            prop.store(output, null);
            System.out.println("Success Write To Properties");
        } catch (IOException io) {
            JOptionPane.showMessageDialog(null, io.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException io) {
                    JOptionPane.showMessageDialog(null, io.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    io.printStackTrace();
                }
            }
        }
    }

    public Properties LoadAProperties() {
        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream(this.CreateDirectory().toString() + "\\" + filename);

//            String filename = "app-config.properties";
//            input = getClass().getClassLoader().getResourceAsStream(filename);
            if (input == null) {
                System.out.println("Sorry, unable to find " + filename);
                JOptionPane.showMessageDialog(null, "Sorry, unable to find " + filename, "Information", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }

            // load a properties file
            prop.load(input);
        } catch (IOException io) {
            JOptionPane.showMessageDialog(null, io.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            io.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException io) {
                    JOptionPane.showMessageDialog(null, io.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    io.printStackTrace();
                }
            }
        }

        return prop;
    }
}
