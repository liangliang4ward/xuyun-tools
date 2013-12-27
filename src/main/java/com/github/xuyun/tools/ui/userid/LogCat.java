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
package com.github.xuyun.tools.ui.userid;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author shizihu
 */
public class LogCat {

    private final JFrame frame;
    private final JTextArea jta;

    public LogCat(JFrame frame, JTextArea jta) {
        this.frame = frame;
        this.jta = jta;
    }

    public LogCat error(String txt) {
        this.print("ERROR", txt);

        return this;
    }

    public LogCat warn(String txt) {
        this.print("WARN", txt);

        return this;
    }

    public LogCat info(String txt) {
        this.print("INFO", txt);

        return this;
    }

    private LogCat print(String tag, String txt) {
        String pre = this.jta.getText();
        if (StringUtils.isNotBlank(pre)) {
            pre = pre + IOUtils.LINE_SEPARATOR_WINDOWS;
        }

        StringBuilder sb = new StringBuilder(pre);
        sb.append("[").append(tag).append("] ").append(this.dt());
        sb.append(" ").append(txt);

        this.jta.setText(sb.toString());

        return this;
    }

    private String dt() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

}
