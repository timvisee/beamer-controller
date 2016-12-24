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

package com.timvisee.beamercontroller;

import com.timvisee.beamercontroller.beamer.BeamerManager;
import com.timvisee.beamercontroller.gui.SerialSelectDialog;

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

        // Show the serial select dialog
        SerialSelectDialog.showDialog();
    }
}
