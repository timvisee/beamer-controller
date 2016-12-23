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

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

public class ResourceManager {

    /**
     * Resource directory.
     */
    public static final String RESOURCE_ROOT = "/res/";

    /**
     * Get the URL for a resource.
     *
     * @return File URI.
     *
     * @throws URISyntaxException Throws if the path is invalid.
     */
    public static URI getFileUri(String path) throws URISyntaxException {
        return ResourceManager.class.getResource(RESOURCE_ROOT + path).toURI();
    }

    /**
     * Get a resource file by it's path.
     *
     * @param path Path of the file.
     *
     * @return Resource file.
     *
     * @throws URISyntaxException Throws if the path is invalid.
     */
    public static File getFile(String path) throws URISyntaxException {
        return new File(getFileUri(path));
    }
}
