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
     * Value defining the flow control
     */
    private int value = 0;

    /**
     * Constructor.
     * The flow control will be none by default.
     */
    public FlowControlConfig() {}

    /**
     * Constructor.
     *
     * @param value Flow control value.
     */
    public FlowControlConfig(int value) {
        setValue(value);
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
     * Get the flow control value.
     *
     * @return Flow control value.
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Set the flow control by a value.
     *
     * @param value Value.
     */
    public void setValue(int value) {
        // Make sure the number is in-bound
        if(value < 0 || value >= FLOWCONTROL_XONXOFF_OUT * 2)
            throw new RuntimeException("Invalid flow control value");

        // Set the actual value
        this.value = value;
    }

    /**
     * Get the jSSC flow control value.
     *
     * @return jSSC flow control value.
     */
    public int getJsscValue() {
        return getValue();
    }

    /**
     * Set the flow control by a jSSC flow control value.
     *
     * @param value jSSC flow control value.
     */
    public void setJsscValue(int value) {
        setValue(value);
    }

    /**
     * Check whether the flow control is none.
     *
     * @return True if the flow control is none.
     */
    public boolean isNone() {
        return this.value == FLOWCONTROL_NONE;
    }

    /**
     * Set the flow control to none.
     */
    public void setNone() {
        this.value = FLOWCONTROL_NONE;
    }

    /**
     * Check whether RTS/CTS input mode is enabled.
     *
     * @return True if enabled, false if not.
     */
    public boolean isRtsCtsIn() {
        return (this.value & FLOWCONTROL_RTSCTS_IN) > 0;
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
            this.value |= FLOWCONTROL_RTSCTS_IN;
        else
            this.value = ~(~this.value | ~FLOWCONTROL_RTSCTS_IN);
    }

    /**
     * Check whether RTS/CTS output mode is enabled.
     *
     * @return True if enabled, false if not.
     */
    public boolean isRtsCtsOut() {
        return (this.value & FLOWCONTROL_RTSCTS_OUT) > 0;
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
            this.value |= FLOWCONTROL_RTSCTS_OUT;
        else
            this.value = ~(~this.value | ~FLOWCONTROL_RTSCTS_OUT);
    }

    /**
     * Check whether XON/XOFF input mode is enabled.
     *
     * @return True if enabled, false if not.
     */
    public boolean isXonXoffIn() {
        return (this.value & FLOWCONTROL_XONXOFF_IN) > 0;
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
            this.value |= FLOWCONTROL_XONXOFF_IN;
        else
            this.value = ~(~this.value | ~FLOWCONTROL_XONXOFF_IN);
    }

    /**
     * Check whether XON/XOFF output mode is enabled.
     *
     * @return True if enabled, false if not.
     */
    public boolean isXonXoffOut() {
        return (this.value & FLOWCONTROL_XONXOFF_OUT) > 0;
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
            this.value |= FLOWCONTROL_XONXOFF_OUT;
        else
            this.value = ~(~this.value | ~FLOWCONTROL_XONXOFF_OUT);
    }
}
