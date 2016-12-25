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

import java.io.File;

public class DirectoryUtils {

    /**
     * Name of the applications data directory.
     */
    public static final String APPLICATION_DIRECTORY_NAME = "beamer-controller";

    /**
     * Get the application data directory for the current system.
     *
     * @return Application data directory.
     */
    public static File getSystemAppDataDirectory() {
        // Get the home directory of the user
        final String homeDir = System.getProperty("user.home", ".");

        switch (Platform.getPlatform()) {
            case WINDOWS:
                String applicationData = System.getenv("APPDATA");
                if (applicationData != null)
                    return new File(applicationData);
                else
                    return new File(homeDir);

            case LINUX:
            case SOLARIS:
                return new File(homeDir);

            case MAC_OS_X:
                return new File(homeDir + "/Library/Application Support");

            default:
                return new File(homeDir);
        }
    }

    /**
     * Get the data directory of the current application.
     *
     * @return Application data directory.
     */
    public static File getApplicationDirectory() {
        // Determine whether to prefix a dot
        final Platform platform = Platform.getPlatform();
        final boolean prefixDot = platform.equals(Platform.LINUX) || platform.equals(Platform.SOLARIS);

        // Build the application path and return it
        return new File(getSystemAppDataDirectory(), (prefixDot ? "." : "") + APPLICATION_DIRECTORY_NAME);
    }
}
