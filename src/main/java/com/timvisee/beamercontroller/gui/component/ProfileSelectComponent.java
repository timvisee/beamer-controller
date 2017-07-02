package com.timvisee.beamercontroller.gui.component;

import java.util.ArrayList;
import java.util.List;

public class ProfileSelectComponent extends AdvancedComboComponent<String> {

    /**
     * Constructor.
     */
    public ProfileSelectComponent() {
        super(true);

        // Set the dummy values
        setValues(new ArrayList<String>() {{
            add("My profile");
            add("Dummy value 1");
            add("Dummy value 2");
        }});
    }
}
