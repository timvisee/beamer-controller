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

package com.timvisee.beamercontroller.gui;

import com.timvisee.beamercontroller.App;
import com.timvisee.beamercontroller.BeamerController;
import jssc.SerialPortList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class SerialSelectDialog extends JDialog {

    /**
     * Window title.
     */
    private static final String WINDOW_TITLE = "Serial port";

    /**
     * Maximum window width.
     */
    private static final int WINDOW_SIZE_WIDTH_MAX = 500;

    /**
     * Serial port selection field.
     */
    private JComboBox<String> selectField;

    /**
     * Constructor.
     */
    public SerialSelectDialog() {
        this(null);
    }

    /**
     * Constructor.
     *
     * @param owner Owning window, or null.
     */
    public SerialSelectDialog(Window owner) {
        // Construct the super, with the window title
        super(owner, WINDOW_TITLE + " - " + BeamerController.APP_NAME);

        // Build the UI
        buildUi();

        // Pack the frame
        pack();

        // Set the close method
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        // Listen for close
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}

            @Override
            public void windowClosing(WindowEvent e) {
                // Dispose the frame
                dispose();

                // Properly exit the application
                App.getInstance().exit();
            }

            @Override
            public void windowClosed(WindowEvent e) {}

            @Override
            public void windowIconified(WindowEvent e) {}

            @Override
            public void windowDeiconified(WindowEvent e) {}

            @Override
            public void windowActivated(WindowEvent e) {}

            @Override
            public void windowDeactivated(WindowEvent e) {}
        });

        // Get the current dialog size
        final Dimension size = getSize();
        setMinimumSize(size);
        setMaximumSize(new Dimension(Math.max(size.width, WINDOW_SIZE_WIDTH_MAX), size.height));
        setPreferredSize(new Dimension(size.width + 80, size.height));
        setSize(new Dimension(size.width + 80, size.height));

        // Set the dialog's modality
        setModal(true);

        // Center the dialog to it's parent
        setLocationRelativeTo(owner);
    }

    /**
     * Show the dialog.
     */
    public static String showDialog() {
        return showDialog(null);
    }

    /**
     * Show the dialog.
     *
     * @param owner Owning window, or null.
     */
    public static String showDialog(Window owner) {
        // Create a new instance
        final SerialSelectDialog dialog = new SerialSelectDialog(owner);

        // Show the dialog
        dialog.setVisible(true);

        // Return the selected serial port
        return String.valueOf(dialog.selectField.getSelectedItem());
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

        // Add a selection box
        selectField = new JComboBox<>(getPorts());

        // Create a button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(8, 0, 0, 0));
        buttonPanel.setLayout(new GridLayout(1, 2, 8, 8));

        // Create an OK and cancel button
        JButton okButton = new JButton("Connect");
        JButton quitButton = new JButton("Quit");

        // Connect to the selected serial port when the OK button is pressed
        okButton.addActionListener(e -> {
            // Dispose the dialog
            dispose();
        });

        // Quit the application when the quit button is pressed
        quitButton.addActionListener(e -> App.getInstance().exit());

        // Add the buttons to the button panel
        buttonPanel.add(okButton);
        buttonPanel.add(quitButton);

        // Add the combo box
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(selectField, c);
        c.fill = GridBagConstraints.NONE;

        // Add the button panel
        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.EAST;
        mainPanel.add(buttonPanel, c);
    }

    /**
     * Get the list of serial ports that are available.
     *
     * @return List of ports.
     */
    public String[] getPorts() {
        return SerialPortList.getPortNames();
    }
}
