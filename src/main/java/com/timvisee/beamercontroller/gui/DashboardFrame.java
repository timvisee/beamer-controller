/*
 * Copyright (c) Tim Visee 2016. All rights reserved.
 *
 * @author Tim Visee
 *
 * Open Source != No Copyright
 *
 * Permission is hereby granted, free of charge, to any person obtaining a
 * copy of this software and associated documentation files (the "Software")
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * You should have received a copy of The MIT License (MIT) along with this
 * program. If not, see <http://opensource.org/licenses/MIT/>.
 *
 */

package com.timvisee.beamercontroller.gui;

import com.timvisee.beamercontroller.BeamerController;
import com.timvisee.beamercontroller.beamer.Beamer;
import com.timvisee.beamercontroller.beamer.command.Command;
import com.timvisee.beamercontroller.beamer.command.CommandManager;
import com.timvisee.beamercontroller.beamer.iface.SerialBeamerInterface;
import com.timvisee.beamercontroller.util.ProgressDialog;
import jssc.SerialPort;
import jssc.SerialPortException;

import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends JFrame {

    /**
     * Window title.
     */
    private static final String WINDOW_TITLE = "Dashboard";

    /**
     * Serial port instance.
     */
    // TODO: This should be removed, this is only used for rapid snapshot release.
    private SerialPort port;

    /**
     * Beamer instance.
     */
    // TODO: This should be removed, this is only used for rapid snapshot release.
    private Beamer beamer;

    /**
     * Constructor.
     *
     * @param port Serial port.
     * @param beamer Beamer instance.
     */
    public DashboardFrame(SerialPort port, Beamer beamer) {
        this(null, port, beamer);
    }

    /**
     * Constructor.
     *
     * @param owner Owning window, or null.
     * @param port Serial port.
     * @param beamer Beamer instance.
     */
    public DashboardFrame(Window owner, SerialPort port, Beamer beamer) {
        // Construct the super, with the window title
        super(WINDOW_TITLE + " - " + BeamerController.APP_NAME);

        // Set the serial port and beamer
        this.port = port;
        this.beamer = beamer;

        // Build the UI
        buildUi();

        // Pack the frame
        pack();

        // Make the frame non-resizable
        setResizable(false);

        // Center the dialog to it's parent
        setLocationRelativeTo(owner);
    }

    /**
     * Show the dialog.
     *
     * @param port Serial port.
     * @param beamer Beamer instance.
     */
    public static void showFrame(SerialPort port, Beamer beamer) {
        showFrame(null, port, beamer);
    }

    /**
     * Show the dialog.
     *
     * @param owner Owning window, or null.
     * @param port Serial port.
     * @param beamer Beamer instance.
     */
    public static void showFrame(Window owner, SerialPort port, Beamer beamer) {
        // Create a new instance
        final DashboardFrame frame = new DashboardFrame(owner, port, beamer);

        // Show the dialog
        frame.setVisible(true);
    }

    /**
     * Build the dialog UI.
     */
    public void buildUi() {
        // Set the window layout
        setLayout(new BorderLayout());

        // Create the main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        mainPanel.setLayout(new GridBagLayout());

        // Add the main panel to the dialog
        add(mainPanel);

        // Create a grid bag constraints object
        GridBagConstraints c = new GridBagConstraints();

        // Create a button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(14, 1, 8, 8));

        // Set up buttons
        JButton onButton = new JButton("Power: On");
        JButton offButton = new JButton("Power: Off");
        JButton hdmiButton = new JButton("Source: HDMI");
        JButton hdmi2Button = new JButton("Source: HDMI 2");
        JButton computerButton = new JButton("Source: Computer");
        JButton computer2Button = new JButton("Source: Computer 2");
        JButton componentButton = new JButton("Source: Component");
        JButton dviAButton = new JButton("Source: DVI-A");
        JButton dviDButton = new JButton("Source: DVI-D");
        JButton compositeButton = new JButton("Source: Composite");
        JButton sVideoButton = new JButton("Source: S-Video");
        JButton networkButton = new JButton("Source: Network");
        JButton usbDisplayButton = new JButton("Source: USB Reader");
        JButton usbReaderButton = new JButton("Source: USB Display");

        // Link the commands to the buttons
        onButton.addActionListener(e -> runCommand("powerSetOn"));
        offButton.addActionListener(e -> runCommand("powerSetOff"));
        hdmiButton.addActionListener(e -> runCommand("sourceSetHdmi"));
        hdmi2Button.addActionListener(e -> runCommand("sourceSetHdmi2"));
        computerButton.addActionListener(e -> runCommand("sourceSetComputer"));
        computer2Button.addActionListener(e -> runCommand("sourceSetComputer2"));
        componentButton.addActionListener(e -> runCommand("sourceSetComponent"));
        dviAButton.addActionListener(e -> runCommand("sourceSetDviA"));
        dviDButton.addActionListener(e -> runCommand("sourceSetDviD"));
        compositeButton.addActionListener(e -> runCommand("sourceSetComposite"));
        sVideoButton.addActionListener(e -> runCommand("sourceSetSvideo"));
        networkButton.addActionListener(e -> runCommand("sourceSetNetwork"));
        usbDisplayButton.addActionListener(e -> runCommand("sourceSetUsbDisplay"));
        usbReaderButton.addActionListener(e -> runCommand("sourceSetUsbReader"));

        // Add the buttons
        buttonPanel.add(onButton);
        buttonPanel.add(offButton);
        buttonPanel.add(hdmiButton);
        buttonPanel.add(hdmi2Button);
        buttonPanel.add(computerButton);
        buttonPanel.add(computer2Button);
        buttonPanel.add(componentButton);
        buttonPanel.add(dviAButton);
        buttonPanel.add(dviDButton);
        buttonPanel.add(compositeButton);
        buttonPanel.add(sVideoButton);
        buttonPanel.add(networkButton);
        buttonPanel.add(usbDisplayButton);
        buttonPanel.add(usbReaderButton);

        // Add the combo box
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        mainPanel.add(buttonPanel, c);
    }

    /**
     * Run the command with the given name.
     *
     * @param id Command ID.
     */
    public void runCommand(String id) {
        // Get the beamer's command manager and serial interface
        CommandManager commandManager = this.beamer.getCommandManager();
        SerialBeamerInterface serialBeamerInterface = (SerialBeamerInterface) this.beamer.getBeamerInterfaceManager().getInterfaces().get(0);

        // Loop through the commands, and find the correct one
        for(Command command : commandManager.getCommands()) {
            // Continue if this command isn't correct
            if(!command.getId().equalsIgnoreCase(id))
                continue;

            // Create a progress dialog
            ProgressDialog progressDialog = new ProgressDialog(this, BeamerController.APP_NAME, false);
            progressDialog.setStatus("Executing command...");
            progressDialog.setShowProgress(false);
            progressDialog.setVisible(true);

            try {
                // Execute the command
                serialBeamerInterface.executeCommand(command, this.port);

                // Dispose the progress dialog
                progressDialog.dispose();

                // Show a status message in the console
                System.out.println("Executed beamer command: " + command.getName());

            } catch(SerialPortException e) {
                // Dispose the progress dialog
                progressDialog.dispose();

                // Show an error dialog
                JOptionPane.showMessageDialog(this, "Failed to execute command!", BeamerController.APP_NAME, JOptionPane.ERROR_MESSAGE);

                // Print an error to the console
                e.printStackTrace();
            }

            // Return when done
            return;
        }

        // Show an error dialog
        JOptionPane.showMessageDialog(this, "Unknown command.", BeamerController.APP_NAME, JOptionPane.ERROR_MESSAGE);
    }
}
