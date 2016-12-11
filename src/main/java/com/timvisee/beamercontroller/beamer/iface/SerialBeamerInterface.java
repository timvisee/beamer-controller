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

package com.timvisee.beamercontroller.beamer.iface;

import com.timvisee.yamlwrapper.configuration.Configuration;
import com.timvisee.yamlwrapper.configuration.ConfigurationSection;

public class SerialBeamerInterface extends BeamerInterface {

    /**
     * Command prefix.
     */
    private String commandPrefix = "";

    /**
     * Command suffix.
     */
    private String commandSuffix = "";

    /**
     * Constructor.
     */
    public SerialBeamerInterface(String commandPrefix, String commandSuffix) {
        // Construct the super
        super(InterfaceType.SERIAL);

        // Set the command prefix/suffix
        this.commandPrefix = commandPrefix;
        this.commandSuffix = commandSuffix;
    }

    /**
     * Command prefix.
     *
     * @return Command prefix.
     */
    public String getCommandPrefix() {
        return this.commandPrefix;
    }

    /**
     * Command suffix.
     *
     * @return Command suffix.
     */
    public String getCommandSuffix() {
        return this.commandSuffix;
    }

    /**
     * Load a serial beamer interface from the given configuration section.
     *
     * @param config Configuration section to load the serial beamer interface from.
     *
     * @return Serial beamer interface instance.
     */
    public static SerialBeamerInterface load(ConfigurationSection config) {
        // Get the command prefix/suffix
        final String commandPrefix = config.getString("commandPrefix", "");
        final String commandSuffix = config.getString("commandSuffix", "");

        // Create a new serial beamer interface instance and return it
        return new SerialBeamerInterface(commandPrefix, commandSuffix);
    }
}
