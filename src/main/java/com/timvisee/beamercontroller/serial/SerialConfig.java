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
    public BaudRateType baudRateType;

    /**
     * Data bit type for this serial configuration.
     */
    private DataBitType dataBitType;

    /**
     * parity type for this serial configuration.
     */
    private ParityType parityType;

    /**
     * Stop bit type for this serial configuration.
     */
    private StopBitType stopBitType;

    /**
     * Flow control type for this serial configuration.
     */
    private FlowControlType flowControl;

    /**
     * Get the baud rate type.
     *
     * @return Baud rate type.
     */
    public BaudRateType getBaudRateType() {
        return this.baudRateType;
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
     * Get the parity type.
     *
     * @return Parity type.
     */
    public ParityType getParityType() {
        return this.parityType;
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
     * Get the flow control type.
     *
     * @return Flow control type.
     */
    public FlowControlType getFlowControl() {
        return this.flowControl;
    }
}
