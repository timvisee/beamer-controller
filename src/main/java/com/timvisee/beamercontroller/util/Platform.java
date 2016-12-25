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

package com.timvisee.beamercontroller.util;

public enum Platform {

    /**
     * Windows platform.
     */
    WINDOWS,

    /**
     * Mac OS X platform.
     */
    MAC_OS_X,

    /**
     * Linux platform.
     */
    LINUX,

    /**
     * Solaris platform.
     */
    SOLARIS,

    /**
     * Unknown platform.
     */
    UNKNOWN;

    /**
     * Get the platform the application is currently running on.
     *
     * @return Current platform.
     */
    public static Platform getPlatform() {
        // Get the name of the current OS
        String osName = System.getProperty("os.name").toLowerCase();

        // Check whether this is a Windows OS
        if (osName.contains("win"))
            return Platform.WINDOWS;

        // Check whether this is a Mac OS X OS
        if (osName.contains("mac"))
            return Platform.MAC_OS_X;

        // Check whether this is a linux OS
        if (osName.contains("linux") || osName.contains("unix"))
            return Platform.LINUX;

        // Check whether this is a Solaris OS
        if (osName.contains("solaris") || osName.contains("sunos"))
            return Platform.SOLARIS;

        // The OS is unknown
        return Platform.UNKNOWN;
    }
}

