/******************************************************************************
 * Copyright (c) Tim Visee 2016-2017. All rights reserved.                    *
 *                                                                            *
 * @author Tim Visee                                                          *
 *                                                                            *
 * Open Source != No Copyright                                                *
 *                                                                            *
 * Permission is hereby granted, free of charge, to any person obtaining a    *
 * copy of this software and associated documentation files (the "Software")  *
 * to deal in the Software without restriction, including without limitation  *
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,   *
 * and/or sell copies of the Software, and to permit persons to whom the      *
 * Software is furnished to do so, subject to the following conditions:       *
 *                                                                            *
 * The above copyright notice and this permission notice shall be included    *
 * in all copies or substantial portions of the Software.                     *
 *                                                                            *
 * You should have received a copy of The MIT License (MIT) along with this   *
 * program. If not, see <http://opensource.org/licenses/MIT/>.                *
 ******************************************************************************/

package com.timvisee.beamercontroller;

import com.timvisee.beamercontroller.beamer.Beamer;
import com.timvisee.beamercontroller.beamer.BeamerManager;
import com.timvisee.beamercontroller.beamer.iface.SerialBeamerInterface;
import com.timvisee.beamercontroller.gui.ConnectDialog;
import com.timvisee.beamercontroller.gui.DashboardFrame;
import com.timvisee.beamercontroller.gui.SerialSelectDialog;
import com.timvisee.beamercontroller.serial.BaudRateType;
import com.timvisee.beamercontroller.util.CommandUtils;
import com.timvisee.beamercontroller.util.ProgressDialog;
import com.timvisee.beamercontroller.util.swing.SwingUtils;
import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

import javax.swing.*;

public class App {

    /**
     * Singleton instance.
     */
    private static App instance = null;

    /**
     * Beamer manager.
     */
    private BeamerManager beamerManager = new BeamerManager();

    /**
     * Baud rate to use.
     */
    // TODO: This should be removed. This is used for a rapid release snapshot.
    public int baud = 9600;

    /**
     * Constructor.
     */
    public App() {}

    /**
     * Get the singleton instance of the app.
     *
     * @return Singleton instance.
     */
    public static App getInstance() {
        // Return the instance if it exists
        if(instance != null)
            return instance;

        // Create a new instance, and return it
        instance = new App();

        // Return the instance
        return instance;
    }

    /**
     * Initialize the application.
     */
    public void init() {
        // Load the beamer types
        beamerManager.load();

        // Use systems look and feel
        SwingUtils.useNativeLookAndFeel();

        // Show the connection dialog
        ConnectDialog.showDialog();

        // Make sure enough serial ports are available
        if(SerialPortList.getPortNames().length == 0) {
            // Show a notification
            JOptionPane.showMessageDialog(null, "No serial ports available to connect to. The application will now quit.", BeamerController.APP_NAME, JOptionPane.WARNING_MESSAGE);

            // Exit the application
            System.exit(0);
        }

        // Show the serial select dialog
        String serialPortName = SerialSelectDialog.showDialog();

        // Show a progress dialog for the connection progress
        ProgressDialog dialog = new ProgressDialog(null, "Connecting to beamer...", false);
        dialog.setStatus("Connecting to beamer...");
        dialog.setShowProgress(false);
        dialog.setVisible(true);

        // Set up the serial port
        final SerialPort port = new SerialPort(serialPortName);

        try {
            // Open the serial port
            dialog.setStatus("Opening port...");
            port.openPort();

            // Configure the port
            dialog.setStatus("Configuring port...");

            // Find the beamer to use
            final Beamer beamer = beamerManager.getBeamers().get(0);

            // Find the serial interface configuration for the beamer
            SerialBeamerInterface serialBeamerInterface = (SerialBeamerInterface) beamer.getBeamerInterfaceManager().getInterfaces().get(0);

            // Set the custom baud rate
            serialBeamerInterface.getSerialConfig().setBaudRateType(BaudRateType.getByRate(this.baud));

            // Apply the serial beamer configuration to the selected port
            serialBeamerInterface.getSerialConfig().applyToPort(port);

            // Read data from the beamer and print it to the console
            port.addEventListener(event -> {
                if(event.isRXCHAR() && event.getEventValue() > 0) {
                    try {
                        final String receivedData = port.readString(event.getEventValue());
                        System.out.println("Received from beamer: " + CommandUtils.formatCommand(receivedData));

                    } catch (SerialPortException ex) {
                        System.out.println("Error in receiving string from COM-port: " + ex);
                    }
                }
            });

            // Hide the progress dialog
            dialog.setStatus("Loading dashboard...");
            dialog.setVisible(false);

            // Show the dashboard
            DashboardFrame.showFrame(port, beamer);

        } catch(SerialPortException e) {
            // Hide the progress dialog
            dialog.setVisible(false);

            // Print the stack trace
            e.printStackTrace();

            // Show an error notification
            JOptionPane.showMessageDialog(null, "Failed to connect through the selected serial port. The application will now quit.", BeamerController.APP_NAME, JOptionPane.ERROR_MESSAGE);

            // Exit the application
            System.exit(0);
        }
    }

    /**
     * (Force) exit the application.
     */
    public void exit() {
        // Show a status message
        System.out.println("The application will now exit.");

        // Exit
        System.exit(0);
    }

    /**
     * Get the beamer manager.
     *
     * @return Beamer manager.
     */
    public BeamerManager getBeamerManager() {
        return beamerManager;
    }
}
