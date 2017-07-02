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

package com.timvisee.beamercontroller.beamer.command;

import com.timvisee.beamercontroller.serial.SerialConfig;
import com.timvisee.yamlwrapper.ConfigurationSection;

public class BeamerSerialConfig {

    /**
     * Serial configuration section key.
     */
    private static final String CONFIG_SERIAL_SECTION = "serial";

    /**
     * Serial default configuration section key.
     */
    private static final String CONFIG_SERIAL_DEFAULT_SECTION = "default";

    /**
     * Serial interface configuration section key.
     */
    private static final String CONFIG_SERIAL_INTERFACE_SECTION = "interface";

    /**
     * Serial support configuration key.
     */
    private static final String CONFIG_SERIAL_SUPPORT_KEY = "key";

    /**
     * Serial interface command prefix configuration key.
     */
    private static final String CONFIG_INTERFACE_COMMAND_PREFIX = "commandPrefix";

    /**
     * Serial interface command suffix configuration key.
     */
    private static final String CONFIG_INTERFACE_COMMAND_SUFFIX = "commandSuffix";

    /**
     * True if serial connectivity is supported.
     */
    private boolean supportSerial;

    /**
     * Default serial configuration for serial connectivity.
     */
    private SerialConfig serialDefault;

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
     *
     * Creates beamer configuration without any connectivity support by default.
     */
    public BeamerSerialConfig() {
        this(false);
    }

    /**
     * Constructor.
     *
     * @param supportSerial True if serial connectivity is supported.
     */
    public BeamerSerialConfig(boolean supportSerial) {
        this.supportSerial = supportSerial;
    }

    /**
     * Check whether serial connectivity is supported.
     *
     * @return True if supported, false if not.
     */
    public boolean isSupportSerial() {
        return this.supportSerial;
    }

    /**
     * Set whether serial connectivity is supported.
     *
     * @param supportSerial True if supported, false if not.
     */
    public void setSupportSerial(boolean supportSerial) {
        this.supportSerial = supportSerial;
    }

    /**
     * Get the serial connectivity defaults.
     *
     * @return Defaults if set, null otherwise.
     */
    public SerialConfig getSerialDefault() {
        return this.serialDefault;
    }

    /**
     * Set the serial connectivity defaults.
     *
     * @param serialDefault Serial defaults.
     */
    public void setSerialDefault(SerialConfig serialDefault) {
        this.serialDefault = serialDefault;
    }

    /**
     * Get the command prefix.
     *
     * @return Command prefix.
     */
    public String getCommandPrefix() {
        return this.commandPrefix;
    }

    /**
     * Set the command prefix.
     *
     * @param commandPrefix Command prefix.
     */
    public void setCommandPrefix(String commandPrefix) {
        this.commandPrefix = commandPrefix;
    }

    /**
     * Get the command suffix.
     *
     * @return Command suffix.
     */
    public String getCommandSuffix() {
        return this.commandSuffix;
    }

    /**
     * Set the command suffix.
     *
     * @param commandSuffix Command suffix.
     */
    public void setCommandSuffix(String commandSuffix) {
        this.commandSuffix = commandSuffix;
    }

    /**
     * Save the configuration to a configuration section.
     *
     * @param section Section to save in.
     */
    public void save(ConfigurationSection section) {
        // Create a serial section
        final ConfigurationSection serialSection = section.createSection(CONFIG_SERIAL_SECTION);

        // Set whether serial connectivity is supported
        serialSection.set(CONFIG_SERIAL_SUPPORT_KEY, this.supportSerial);

        // Set other serial properties if serial is supported
        if(this.supportSerial) {
            // Create a serial default and interface section
            final ConfigurationSection defaultSection = section.createSection(CONFIG_SERIAL_DEFAULT_SECTION);
            final ConfigurationSection interfaceSection = section.createSection(CONFIG_SERIAL_INTERFACE_SECTION);

            // Save the serial connection defaults
            this.serialDefault.save(defaultSection);

            // Set the command prefix/suffix
            interfaceSection.set(CONFIG_INTERFACE_COMMAND_PREFIX, this.commandPrefix);
            interfaceSection.set(CONFIG_INTERFACE_COMMAND_SUFFIX, this.commandSuffix);
        }
    }

    /**
     * Load the beamer serial configuration from the given configuration section.
     *
     * @param section Configuration section to load the beamer serial configuration from.
     *
     * @return Loaded beamer serial configuration.
     */
    public static BeamerSerialConfig load(ConfigurationSection section) {
        // Check whether serial connectivity is supported
        final boolean supportSerial = section.getBoolean(CONFIG_SERIAL_SUPPORT_KEY, false);

        // Create a new beamer serial configuration obejct
        BeamerSerialConfig beamerSerialConfig = new BeamerSerialConfig(supportSerial);

        // Load the other serial connectivity properties
        if(supportSerial) {
            // Get the default connectivity and interface sections
            final ConfigurationSection serialConnectivitySection = section.getSection(CONFIG_SERIAL_DEFAULT_SECTION);
            final ConfigurationSection interfaceSection = section.getSection(CONFIG_SERIAL_INTERFACE_SECTION);

            // Load the default connectivity details
            final SerialConfig defaultSerial = SerialConfig.load(serialConnectivitySection);

            // Get the command prefix/suffix
            final String commandPrefix = interfaceSection.getString(CONFIG_INTERFACE_COMMAND_PREFIX);
            final String commandSuffix = interfaceSection.getString(CONFIG_INTERFACE_COMMAND_SUFFIX);

            // Set the properties
            beamerSerialConfig.setSerialDefault(defaultSerial);
            beamerSerialConfig.setCommandPrefix(commandPrefix);
            beamerSerialConfig.setCommandSuffix(commandSuffix);
        }

        // Return the beamer serial configuration
        return beamerSerialConfig;
    }
}
