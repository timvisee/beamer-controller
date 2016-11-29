/******************************************************************************
 * Copyright (c) Tim Visee 2016. All rights reserved.                         *
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

package com.timvisee.beamercontroller.serial;

import com.timvisee.yamlwrapper.configuration.ConfigurationSection;
import jssc.SerialPort;
import jssc.SerialPortException;

public class SerialConfig {

    /**
     * Configuration key for the baud rate property.
     */
    public static final String CONFIG_BAUD_RATE_KEY = "baud";

    /**
     * Configuration key for the data bit type property.
     */
    public static final String CONFIG_DATA_BIT_KET = "databit";

    /**
     * Configuration key for the parity type property.
     */
    public static final String CONFIG_PARITY_KEY = "parity";

    /**
     * Configuration key for the stop bit type property.
     */
    public static final String CONFIG_STOP_BIT_KEY = "stopbit";

    /**
     * Configuration key for the flow control type property.
     */
    public static final String CONFIG_FLOW_CONTROL_KEY = "flowcontrol";

    /**
     * Baud rate type for this serial configuration.
     */
    private BaudRateType baudRateType = null;

    /**
     * Data bit type for this serial configuration.
     */
    private DataBitType dataBitType = null;

    /**
     * parity type for this serial configuration.
     */
    private ParityType parityType = null;

    /**
     * Stop bit type for this serial configuration.
     */
    private StopBitType stopBitType = null;

    /**
     * Flow control type for this serial configuration.
     */
    private FlowControlType flowControl = null;

    /**
     * Constructor.
     *
     * @param baudRateType Baud rate type, or null.
     * @param dataBitType Data bit type, or null.
     * @param parityType Parity type, or null.
     * @param stopBitType Stop bit type, or null.
     * @param flowControl Flow control, or null.
     */
    public SerialConfig(BaudRateType baudRateType, DataBitType dataBitType, ParityType parityType, StopBitType stopBitType, FlowControlType flowControl) {
        this.baudRateType = baudRateType;
        this.dataBitType = dataBitType;
        this.parityType = parityType;
        this.stopBitType = stopBitType;
        this.flowControl = flowControl;
    }

    /**
     * Get the baud rate type.
     *
     * @return Baud rate type.
     */
    public BaudRateType getBaudRateType() {
        return this.baudRateType;
    }

    /**
     * Check whether the baud rate type is configured.
     *
     * @return True if the baud rate type is configured.
     */
    public boolean hasBaudRateType() {
        return this.baudRateType != null;
    }

    /**
     * Set the baud rate type.
     *
     * @param baudRateType Baud rate type.
     */
    public void setBaudRateType(BaudRateType baudRateType) {
        this.baudRateType = baudRateType;
    }

    /**
     * Get the data bit type.
     *
     * @return Data bit type.
     */
    public DataBitType getDataBitType() {
        return this.dataBitType;
    }

    /**
     * Check whether the data bit type is configured.
     *
     * @return True if the data bit type is configured.
     */
    public boolean hasDataBitType() {
        return this.dataBitType != null;
    }

    /**
     * Set the data bit type.
     *
     * @param dataBitType Data bit type.
     */
    public void setDataBitType(DataBitType dataBitType) {
        this.dataBitType = dataBitType;
    }

    /**
     * Get the parity type.
     *
     * @return Parity type.
     */
    public ParityType getParityType() {
        return this.parityType;
    }

    /**
     * Check whether the parity type is configured.
     *
     * @return True if the parity type is configured.
     */
    public boolean hasParityType() {
        return this.parityType != null;
    }

    /**
     * Set the parity type.
     *
     * @param parityType Parity type.
     */
    public void setParityType(ParityType parityType) {
        this.parityType = parityType;
    }

    /**
     * Get the stop bit type.
     *
     * @return Stop bit type.
     */
    public StopBitType getStopBitType() {
        return this.stopBitType;
    }

    /**
     * Check whether the stop bit type is configured.
     *
     * @return True if the stop bit type is configured.
     */
    public boolean hasStopBitType() {
        return this.stopBitType != null;
    }

    /**
     * Set the stop bit type.
     *
     * @param stopBitType Stop bit type.
     */
    public void setStopBitType(StopBitType stopBitType) {
        this.stopBitType = stopBitType;
    }

    /**
     * Get the flow control type.
     *
     * @return Flow control type.
     */
    public FlowControlType getFlowControl() {
        return this.flowControl;
    }

    /**
     * Check whether the flow control type is configured.
     *
     * @return True if the flow control type is configured.
     */
    public boolean hasFlowControl() {
        return this.flowControl != null;
    }

    /**
     * Set the flow control type.
     *
     * @param flowControl Flow control type.
     */
    public void setFlowControl(FlowControlType flowControl) {
        this.flowControl = flowControl;
    }

    /**
     * Check whether the serial configuration has all required data configured to establish a serial connection.
     *
     * @return True if fully configured, false if not.
     */
    public boolean isConfigured() {
        return hasBaudRateType() &&
                hasDataBitType() &&
                hasParityType() &&
                hasStopBitType() &&
                hasFlowControl();
    }

    /**
     * Apply the serial config to a serial port.
     *
     * @param port Serial port to apply the configuration to.
     *
     * @throws SerialPortException if failed to apply to the serial port.
     */
    public void applyToPort(SerialPort port) throws SerialPortException {
        // Make sure everything is configured properly
        if(!isConfigured())
            throw new RuntimeException("can't apply configuration to serial port, as the configuration isn't complete");

        // Set the parameters
        port.setParams(
                this.baudRateType.toJsscValue(),
                this.dataBitType.toJsscValue(),
                this.stopBitType.toJsscValue(),
                this.parityType.toJsscValue()
        );

        // TODO: Set the flow control!
    }

    /**
     * Save the serial configuration to a configuration section.
     *
     * @param section Configuration section to save the configuration to.
     */
    public void save(ConfigurationSection section) {
        // Store the baud rate
        if(hasBaudRateType())
            section.set(CONFIG_BAUD_RATE_KEY, this.baudRateType.getId());

        // Store the data bit type
        if(hasDataBitType())
            section.set(CONFIG_DATA_BIT_KET, this.dataBitType.getId());

        // Store the parity type
        if(hasParityType())
            section.set(CONFIG_PARITY_KEY, this.parityType.getId());

        // Store the stop bit type
        if(hasStopBitType())
            section.set(CONFIG_STOP_BIT_KEY, this.stopBitType.getId());

        // Store the flow control type
        if(hasFlowControl())
            section.set(CONFIG_FLOW_CONTROL_KEY, this.flowControl.getId());
    }

    /**
     * Load a serial configuration from a configuration section.
     *
     * @param section Configuration section to load the configuration from.
     *
     * @return Loaded serial configuration.
     */
    public SerialConfig load(ConfigurationSection section) {
        // Construct a new serial configuration, and load all proper settings from the section
        return new SerialConfig(
                BaudRateType.getById(section.getInt(CONFIG_BAUD_RATE_KEY, -1)),
                DataBitType.getById(section.getInt(CONFIG_DATA_BIT_KET, -1)),
                ParityType.getById(section.getInt(CONFIG_PARITY_KEY, -1)),
                StopBitType.getById(section.getInt(CONFIG_STOP_BIT_KEY, -1)),
                FlowControlType.getById(section.getInt(CONFIG_FLOW_CONTROL_KEY, -1))
        );
    }
}
