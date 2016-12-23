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

package com.timvisee.beamercontroller.beamer;

import com.timvisee.beamercontroller.ResourceManager;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class BeamerManager {

    /**
     * List of loaded beamer types.
     */
    private List<Beamer> beamers = new ArrayList<>();

    /**
     * Constructor.
     */
    public BeamerManager() {}

    /**
     * Get the list of loaded beamer types.
     *
     * @return Loaded beamer types.
     */
    public List<Beamer> getBeamers() {
        return this.beamers;
    }

    /**
     * Get the number of loaded beamer configurations.
     *
     * @return Number of loaded beamer configurations.
     */
    public int getBeamerCount() {
        return this.beamers.size();
    }

    /**
     * Load the beamers.
     */
    public void load() {
        // Clear the list of beamers
        this.beamers.clear();

        // Create a list of bundled resources paths for files defining beamer types
        List<String> beamerPaths = new ArrayList<>();

        // Manually add beamer configuration files
        // TODO: Scan the bundled resources for beamer types, instead of adding it manually!
        beamerPaths.add("beamer/benq.yml");

        // Load the resource files, and parse them as configuration
        for(String beamerPath : beamerPaths) {
            try {
                // Load bundled beamer configuration, and parse it as beamer
                final Beamer beamer = Beamer.load(ResourceManager.loadConfig(beamerPath));

                // Add the beamer to the list
                this.beamers.add(beamer);

                // Show a status message
                System.out.println("Loaded beamer configuration: " + beamer.getName());

            } catch(URISyntaxException e) {
                // Show an error message
                System.out.println("Failed to load beamer configuration: " + beamerPath);

                // Print the stack trace
                e.printStackTrace();
            }
        }

        // Show a status message
        System.out.println("Loaded " + getBeamerCount() + " beamer configuration" + (getBeamerCount() != 1 ? "s" : "") + ".");
    }
}
