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

package com.timvisee.beamercontroller.serial;

import jssc.SerialPort;
import jssc.SerialPortException;

public class FlowControlConfig {

    /**
     * No flow control.
     */
    private static final int FLOWCONTROL_NONE = 0;

    /**
     * RTC/CTS flow control for input.
     */
    private static final int FLOWCONTROL_RTSCTS_IN = 1;

    /**
     * RTC/CTS flow control for output.
     */
    private static final int FLOWCONTROL_RTSCTS_OUT = 2;

    /**
     * XON/XOFF flow control for input.
     */
    private static final int FLOWCONTROL_XONXOFF_IN = 4;

    /**
     * XON/XOFF flow control for output.
     */
    private static final int FLOWCONTROL_XONXOFF_OUT = 8;

    /**
     * Mask value defining the flow control
     */
    private int mask = 0;

    /**
     * Constructor.
     * The flow control will be none by default.
     */
    public FlowControlConfig() {}

    /**
     * Constructor.
     *
     * @param mask Flow control mask.
     */
    public FlowControlConfig(int mask) {
        setMask(mask);
    }

    /**
     * Create a flow control configuration with the none setting.
     *
     * @return Flow control configuration with none.
     */
    public static FlowControlConfig createNone() {
        return new FlowControlConfig();
    }

    /**
     * Get the flow control mask.
     *
     * @return Flow control mask.
     */
    public int getMask() {
        return this.mask;
    }

    /**
     * Set the flow control by a mask.
     *
     * @param mask Value.
     */
    public void setMask(int mask) {
        // Make sure the number is in-bound
        if(mask < 0 || mask >= FLOWCONTROL_XONXOFF_OUT * 2)
            throw new RuntimeException("Invalid flow control mask");

        // Set the actual mask
        this.mask = mask;
    }

    /**
     * Get the jSSC flow control mask.
     *
     * @return jSSC flow control mask.
     */
    public int getJsscValue() {
        return getMask();
    }

    /**
     * Set the flow control by a jSSC flow control mask.
     *
     * @param value jSSC flow control mask.
     */
    public void setJsscValue(int value) {
        setMask(value);
    }

    /**
     * Check whether the flow control is none.
     *
     * @return True if the flow control is none.
     */
    public boolean isNone() {
        return this.mask == FLOWCONTROL_NONE;
    }

    /**
     * Set the flow control to none.
     */
    public void setNone() {
        this.mask = FLOWCONTROL_NONE;
    }

    /**
     * Check whether RTS/CTS input mode is enabled.
     *
     * @return True if enabled, false if not.
     */
    public boolean isRtsCtsIn() {
        return (this.mask & FLOWCONTROL_RTSCTS_IN) > 0;
    }

    /**
     * Toggle whether RTS/CTS input mode is enabled.
     */
    public void toggleRtsCtsIn() {
        setRtsCtsIn(!isRtsCtsIn());
    }

    /**
     * Set whether RTS/CTS input mode is enabled.
     *
     * @param enabled True if enabled, false if not.
     */
    public void setRtsCtsIn(boolean enabled) {
        if(enabled)
            this.mask |= FLOWCONTROL_RTSCTS_IN;
        else
            this.mask = ~(~this.mask | ~FLOWCONTROL_RTSCTS_IN);
    }

    /**
     * Check whether RTS/CTS output mode is enabled.
     *
     * @return True if enabled, false if not.
     */
    public boolean isRtsCtsOut() {
        return (this.mask & FLOWCONTROL_RTSCTS_OUT) > 0;
    }

    /**
     * Toggle whether RTS/CTS output mode is enabled.
     */
    public void toggleRtsCtsOut() {
        setRtsCtsOut(!isRtsCtsOut());
    }

    /**
     * Set whether RTS/CTS output mode is enabled.
     *
     * @param enabled True if enabled, false if not.
     */
    public void setRtsCtsOut(boolean enabled) {
        if(enabled)
            this.mask |= FLOWCONTROL_RTSCTS_OUT;
        else
            this.mask = ~(~this.mask | ~FLOWCONTROL_RTSCTS_OUT);
    }

    /**
     * Check whether XON/XOFF input mode is enabled.
     *
     * @return True if enabled, false if not.
     */
    public boolean isXonXoffIn() {
        return (this.mask & FLOWCONTROL_XONXOFF_IN) > 0;
    }

    /**
     * Toggle whether XON/XOFF input mode is enabled.
     */
    public void toggleXonXoffIn() {
        setXonXoffIn(!isXonXoffIn());
    }

    /**
     * Set whether XON/XOFF input mode is enabled.
     *
     * @param enabled True if enabled, false if not.
     */
    public void setXonXoffIn(boolean enabled) {
        if(enabled)
            this.mask |= FLOWCONTROL_XONXOFF_IN;
        else
            this.mask = ~(~this.mask | ~FLOWCONTROL_XONXOFF_IN);
    }

    /**
     * Check whether XON/XOFF output mode is enabled.
     *
     * @return True if enabled, false if not.
     */
    public boolean isXonXoffOut() {
        return (this.mask & FLOWCONTROL_XONXOFF_OUT) > 0;
    }

    /**
     * Toggle whether XON/XOFF output mode is enabled.
     */
    public void toggleXonXoffOut() {
        setXonXoffOut(!isXonXoffOut());
    }

    /**
     * Set whether XON/XOFF output mode is enabled.
     *
     * @param enabled True if enabled, false if not.
     */
    public void setXonXoffOut(boolean enabled) {
        if(enabled)
            this.mask |= FLOWCONTROL_XONXOFF_OUT;
        else
            this.mask = ~(~this.mask | ~FLOWCONTROL_XONXOFF_OUT);
    }

    /**
     * Apply the flow control configuration to a serial port instance.
     *
     * @param port Serial port instance to apply to.
     *
     * @throws SerialPortException when applying the flow control mode failed.
     */
    public void applyToPort(SerialPort port) throws SerialPortException {
        port.setFlowControlMode(getMask());
    }
}