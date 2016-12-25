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

package com.timvisee.beamercontroller;

import com.timvisee.yamlwrapper.configuration.YamlConfiguration;

import javax.print.DocFlavor;
import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

public class ResourceManager {

    /**
     * Resource directory.
     */
    public static final String RESOURCE_ROOT = "/res/";

    /**
     * Get the input stream for the given resource.
     *
     * @return Input stream.
     */
    public static InputStream getResourceStream(String path) throws URISyntaxException {
        return ResourceManager.class.getResourceAsStream(RESOURCE_ROOT + path);
    }

    /**
     * Load a configuration file from the bundled resources.
     *
     * @param path Resource path.
     *
     * @return Loaded configuration file, or null if loading failed.
     *
     * @throws URISyntaxException Throws if the path is invalid.
     */
    public static YamlConfiguration loadConfig(String path) throws URISyntaxException {
        return YamlConfiguration.loadConfiguration(getResourceStream(path));
    }
}
