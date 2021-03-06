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

package com.timvisee.beamercontroller.gui.component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public abstract class AdvancedComboComponent<E> extends JPanel {

    /**
     * Combo box.
     */
    private JComboBox<E> comboBox = null;

    /**
     * More button.
     */
    private JButton moreButton = null;

    /**
     * Basic change listener, runnables are called when the field is changed.
     */
    private List<Runnable> changeListeners;

    /**
     * Basic more listener, runnables are called when the more button is invoked.
     */
    private List<Runnable> moreListeners;

    /**
     * Constructor.
     */
    AdvancedComboComponent(boolean moreButton, boolean allowAny) {
        // Build the combo box
        comboBox = new JComboBox<>();
        comboBox.setEditable(allowAny);

        // Link the change listeners
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // Invoke the change listeners
                invokeChangeListeners();
            }
        });

        if(moreButton) {
            // Build the more button
            this.moreButton = new JButton("...");

            // Link the more listeners
            this.moreButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    // Invoke the more listeners
                    invokeMoreListeners();
                }
            });
        }

        // Build the component UI
        buildUi();
    }

    /**
     * Build and configure the component UI.
     */
    private void buildUi() {
        // Set the layout
        setLayout(new GridBagLayout());

        // Create a grid bag constraints object
        GridBagConstraints c = new GridBagConstraints();

        // Add the combo box
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(comboBox, c);

        // Add the more button
        if(hasMoreButton()) {
            c.gridx = 1;
            c.gridy = 0;
            c.weightx = 0;
            c.fill = GridBagConstraints.NONE;
            c.insets = new Insets(0, 8, 0, 0);
            add(moreButton, c);
        }
    }

    /**
     * Get the combo box.
     *
     * @return Combo box.
     */
    public JComboBox<E> getComboBox() {
        return comboBox;
    }

    /**
     * Get the more button, if available.
     *
     * @return More button, or null.
     */
    public JButton getMoreButton() {
        return moreButton;
    }

    /**
     * Check whether this combo component has a more button.
     *
     * @return True if this combo component has a more button, false if not.
     */
    public boolean hasMoreButton() {
        return moreButton != null;
    }

    /**
     * Set the combo box values.
     *
     * @param values Box values.
     */
    public void setValues(E[] values) {
        comboBox.setModel(new DefaultComboBoxModel<E>(values));
    }

    /**
     * Set the combo box values.
     *
     * @param values Box values.
     */
    public void setValues(List<E> values) {
        setValues((E[]) values.toArray());
    }

    /**
     * Check whether any value is allowed by the user.
     *
     * @return True if any value is allowed, false if not.
     */
    public boolean isAllowAny() {
        return comboBox.isEditable();
    }

    /**
     * Add the given change listener.
     *
     * @param runnable Runnable.
     */
    public void registerChangeListener(Runnable runnable) {
        changeListeners.add(runnable);
    }

    /**
     * Invoke all the registered change listeners.
     */
    private void invokeChangeListeners() {
        for (Runnable changeListener : changeListeners)
            changeListener.run();
    }

    /**
     * Add the given more listener.
     *
     * @param runnable Runnable.
     */
    public void registerMoreListener(Runnable runnable) {
        moreListeners.add(runnable);
    }

    /**
     * Invoke all the registered more listeners.
     */
    private void invokeMoreListeners() {
        for (Runnable moreListener : moreListeners)
            moreListener.run();
    }
}
