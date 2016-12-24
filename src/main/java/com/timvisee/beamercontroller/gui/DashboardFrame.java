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
import jssc.SerialPort;

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
     * Constructor.
     *
     * @param port Serial port.
     */
    public DashboardFrame(SerialPort port) {
        this(null, port);
    }

    /**
     * Constructor.
     *
     * @param owner Owning window, or null.
     * @param port Serial port.
     */
    public DashboardFrame(Window owner, SerialPort port) {
        // Construct the super, with the window title
        super(WINDOW_TITLE + " - " + BeamerController.APP_NAME);

        // Set the serial port
        this.port = port;

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
     */
    public static void showFrame(SerialPort port) {
        showFrame(null, port);
    }

    /**
     * Show the dialog.
     *
     * @param owner Owning window, or null.
     * @param port Serial port.
     */
    public static void showFrame(Window owner, SerialPort port) {
        // Create a new instance
        final DashboardFrame frame = new DashboardFrame(owner, port);

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
        buttonPanel.setLayout(new GridLayout(5, 1, 8, 8));

        // Set up buttons
        JButton onButton = new JButton("Power: On");
        JButton offButton = new JButton("Power: Off");
        JButton hdmiButton = new JButton("Source: HDMI");
        JButton hdmi2Button = new JButton("Source: HDMI 2");
        JButton rgbButton = new JButton("Source: RGB");

        // Add the buttons
        buttonPanel.add(onButton);
        buttonPanel.add(offButton);
        buttonPanel.add(hdmiButton);
        buttonPanel.add(hdmi2Button);
        buttonPanel.add(rgbButton);

        // Add the combo box
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        mainPanel.add(buttonPanel, c);
    }
}
