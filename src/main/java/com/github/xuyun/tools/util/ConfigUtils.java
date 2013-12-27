/*
 * Copyright 2013 shizihu.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.xuyun.tools.util;

import com.alibaba.fastjson.util.IOUtils;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import javax.swing.JButton;
import org.apache.commons.io.FileUtils;

/**
 * 非线程安全
 *
 * @author shizihu
 */
public class ConfigUtils {

    private static Config config;

    public static final Config get() {
        if (config == null) {
            InputStream input = null;
            try {
                input = new FileInputStream(findXml());
                XMLDecoder d = new XMLDecoder(input);
                Object object = d.readObject();
                if (object != null && Config.class.isAssignableFrom(object.getClass())) {
                    config = (Config) object;
                }
                d.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                IOUtils.close(input);
            }
        }

        if (config == null) {
            config = new Config();
            save();
        }

        return config;
    }

    public static final void save() {
        OutputStream output = null;
        try {
            output = new FileOutputStream(findXml());
            XMLEncoder e = new XMLEncoder(output);
            e.writeObject(config);
            e.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.close(output);
        }
    }

    private static final File findXml() {
        return new File(FileUtils.getUserDirectory(), "xuyun-tools-config.xml");
    }

}
