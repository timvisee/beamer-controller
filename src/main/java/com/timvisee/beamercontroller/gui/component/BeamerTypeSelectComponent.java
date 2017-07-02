package com.timvisee.beamercontroller.gui.component;

import com.timvisee.beamercontroller.App;
import com.timvisee.beamercontroller.beamer.Beamer;

public class BeamerTypeSelectComponent extends AdvancedComboComponent<Beamer> {

    /**
     * Constructor.
     */
    public BeamerTypeSelectComponent() {
        super(true);

        // Set the values
        setValues(App.getInstance().getBeamerManager().getBeamers());
    }
}
