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

public class BeamerController {

    /**
     * Application name.
     */
    public static final String APP_NAME = "BeamerController";

    /**
     * Application version name.
     */
    public static final String APP_VERSION_NAME = "0.1-dev-snapshot";

    /**
     * Application version code.
     */
    public static final int APP_VERSION_CODE = 1;

    /**
     * Application about.
     */
    public static final String APP_ABOUT = "Tim Visée, www.timvisee.com";

    /**
     * Application source URL.
     */
    public static final String APP_SOURCE_URL = "https://github.com/timvisee/beamer-controller";

    /**
     * Main method, called on start.
     *
     * @param args Program arguments.
     */
    public static void main(String[] args) {
        // The application is starting, show a status message
        System.out.println(APP_NAME + " v" + APP_VERSION_NAME + " (" + APP_VERSION_CODE + ").");
        System.out.println("Developed by " + APP_ABOUT + "\n");
        System.out.println("Starting " + APP_NAME + "...");

        // Get the application instance
        App app = App.getInstance();

        // Check whether a custom baud rate is given
        if(args.length > 0) {
            // Set the rate
            app.baud = Integer.valueOf(args[0]);

            // Show a status message
            System.out.println("Using custom baud rate: " + app.baud);
        }

        // Initialize the app
        app.init();
    }
}
