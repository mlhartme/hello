/**
 * Copyright 1&1 Internet AG, https://github.com/1and1/
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.oneandone.hellowar;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class HelloWar extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Writer writer;

        response.setContentType("text/html");
        writer = response.getWriter();
        switch (request.getPathInfo()) {
            case "/properties":
                list(writer, System.getProperties());
                break;
            case "/environment":
                list(writer, System.getenv());
                break;
            default:
                index(request, writer);
                break;
        }
        writer.close();
    }

    protected void index(HttpServletRequest request, Writer writer) throws IOException {
        writer.write("<html><body><h1>Hello, World</h1>\n");
        writer.write("<h2>version: " + getVersion() + "</h2>");
        writer.write("<h2>contextPath: " + request.getContextPath() + "<h2>");
        writer.write("<h2>pathInfo: " + request.getPathInfo() + "</h2>");
        writer.write("<h2>requestUri: " + request.getRequestURI() + "</h2>");
        writer.write("<h2>docroot: " + request.getRealPath("/") + "</h2>");
        writer.write("<h2><a href='properties'>Properties</a></h2>");
        writer.write("<h2><a href='environment'>Environment</a></h2>");
        writer.write("</body></html>\n");
    }

    protected void list(Writer writer, Map map) throws IOException {
        List<String> keys;

        keys = new ArrayList(map.keySet());
        Collections.sort(keys);
        writer.write("<ul>");
        for (String key : keys) {
            writer.write("<li>");
            writer.write(key);
            writer.write("=");
            writer.write(map.get(key).toString());
            writer.write("</li>");
        }
        writer.write("</ul>");
    }

    private String getVersion() throws IOException {
        Properties p;

        p = new Properties();
        p.load(getClass().getClassLoader().getResourceAsStream("/META-INF/pominfo.properties"));
        return p.getProperty("version");
    }

}
