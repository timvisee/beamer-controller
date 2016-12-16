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

import com.timvisee.beamercontroller.beamer.iface.BeamerInterfaceManager;
import com.timvisee.yamlwrapper.configuration.ConfigurationSection;

public class Beamer {

    /**
     * Beamer ID.
     */
    private String id;

    /**
     * Beamer type name.
     */
    private String name;

    /**
     * Beamer interface manager.
     */
    private BeamerInterfaceManager beamerInterfaceManager = new BeamerInterfaceManager();

    /**
     * Constructor.
     *
     * @param id Beamer id.
     * @param name Beamer name.
     */
    public Beamer(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Beamer ID.
     *
     * @return Beamer ID.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Get the beamer type name.
     *
     * @return Beamer type name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get the beamer interface manager.
     *
     * @return Beamer interface manager.
     */
    public BeamerInterfaceManager getBeamerInterfaceManager() {
        return this.beamerInterfaceManager;
    }

    /**
     * Load the beamer from the given configuration section.
     *
     * @param beamerSection Configuration section to load the beamer from.
     *
     * @return Loaded beamer.
     */
    public static Beamer load(ConfigurationSection beamerSection) {
        // Get the beamer ID and name
        final String id = beamerSection.getString("id");
        final String name = beamerSection.getString("name", "Unknown beamer");

        // Create a new beamer instance
        Beamer beamer = new Beamer(id, name);

        // Load the beamer interfaces
        beamer.beamerInterfaceManager.load(beamerSection.getConfigurationSection("interfaces"));

        // Return the beamer instance
        return beamer;
    }
}
