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

public class SerialConfig {

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
}
