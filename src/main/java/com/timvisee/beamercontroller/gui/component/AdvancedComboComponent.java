package com.timvisee.beamercontroller.gui.component;

import javax.swing.*;
import java.awt.*;
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
     * Constructor.
     */
    AdvancedComboComponent(boolean moreButton, boolean allowAny) {
        // Build the combo box
        comboBox = new JComboBox<>();
        comboBox.setEditable(allowAny);

        // Build the more button
        if(moreButton)
            this.moreButton = new JButton("...");

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
}
