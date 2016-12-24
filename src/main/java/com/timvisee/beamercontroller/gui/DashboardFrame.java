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
        buttonPanel.setLayout(new GridBagLayout());

        // Link the commands to the buttons
        createButton("Power: On", "powerSetOn", buttonPanel, 0, 0);
        createButton("Power: Off", "powerSetOff", buttonPanel, 0, 1);
        createButton("Source: HDMI", "sourceSetHdmi", buttonPanel, 1, 0);
        createButton("Source: HDMI 2", "sourceSetHdmi2", buttonPanel, 1, 1);
        createButton("Source: Computer", "sourceSetComputer", buttonPanel, 1, 2);
        createButton("Source: Computer 2", "sourceSetComputer2", buttonPanel, 1, 3);
        createButton("Source: Component", "sourceSetComponent", buttonPanel, 1, 4);
        createButton("Source: DVI-A", "sourceSetDviA", buttonPanel, 1,5);
        createButton("Source: DVI-D", "sourceSetDviD", buttonPanel, 1, 6);
        createButton("Source: Composite", "sourceSetComposite", buttonPanel, 1, 7);
        createButton("Source: S-Video", "sourceSetSvideo", buttonPanel, 1,8);
        createButton("Source: Network", "sourceSetNetwork", buttonPanel, 1, 9);
        createButton("Source: USB Display", "sourceSetUsbDisplay", buttonPanel, 1, 10);
        createButton("Source: USB Reader", "sourceSetUsbReader", buttonPanel, 1, 11);
        createButton("Mode: Dynamic", "pictureModeSetDynamic", buttonPanel, 2, 0);
        createButton("Mode: Presentation", "pictureModeSetPresentation", buttonPanel, 2, 1);
        createButton("Mode: sRGB", "pictureModeSetSrgb", buttonPanel, 2, 2);
        createButton("Mode: Bright", "pictureModeSetBright", buttonPanel, 2, 3);
        createButton("Mode: Living Room", "pictureModeSetLivingRoom", buttonPanel, 2, 4);
        createButton("Mode: Game", "pictureModeSetGame", buttonPanel, 2, 5);
        createButton("Mode: Cinema", "pictureModeSetCinema", buttonPanel, 2, 6);
        createButton("Mode: Standard", "pictureModeSetStandard", buttonPanel, 2, 7);
        createButton("Mode: User 1", "pictureModeSetUser1", buttonPanel, 2, 8);
        createButton("Mode: User 2", "pictureModeSetUser2", buttonPanel, 2, 9);
        createButton("Mode: User 3", "pictureModeSetUser3", buttonPanel, 2, 10);

        // Show a notification
//            JOptionPane.showMessageDialog(this, "The beamer is now turning on. This might take a while.", BeamerController.APP_NAME, JOptionPane.INFORMATION_MESSAGE);

        // Add the button panel to the main panel
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        mainPanel.add(buttonPanel, c);

        // Create a simple menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);
        JMenu helpMenu = new JMenu("Help");
        menuBar.add(helpMenu);
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(e -> JOptionPane.showMessageDialog(this, BeamerController.APP_NAME + " v" + BeamerController.APP_VERSION_NAME + " (" + BeamerController.APP_VERSION_CODE + ")\n\nDeveloped by:\nTim Visee, timvisee.com\n\nSource:\nhttps://github.com/timvisee/beamer-controller", "About" + BeamerController.APP_NAME, JOptionPane.INFORMATION_MESSAGE));
        helpMenu.add(aboutItem);
        add(menuBar, BorderLayout.PAGE_START);
    }

    /**
     * Create a button for the given command.
     *
     * @param name Name of the button.
     * @param commandId Command ID.
     * @param buttonPanel Button panel to add the button to.
     * @param x X coordinate of the button to add.
     * @param y Y coordinate of the button to add.
     */
    public void createButton(String name, String commandId, JPanel buttonPanel, int x, int y) {
        // Create the button
        final JButton button = new JButton(name);

        // Link the button action
        button.addActionListener(e -> runCommand(commandId));

        // Create the grid bag constraints configuration
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = x;
        c.gridy = y;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;

        // Add the button to the panel
        buttonPanel.add(button, c);
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
