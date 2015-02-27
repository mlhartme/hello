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
import java.io.IOException;
import java.io.Writer;
import java.util.Map;
import java.util.Properties;

public class HelloWar extends HttpServlet {
    protected void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp)
            throws javax.servlet.ServletException, java.io.IOException {
        Writer writer;

        resp.setContentType("text/html");
        writer = resp.getWriter();
        writer.write("<html><body><h1>Hello, World</h1>\n");
        writer.write("<h2>version: " + getVersion() + "</h2>");
        writer.write("<h2>contextPath: " + req.getContextPath() + "<h2>");
        writer.write("<h2>pathInfo: " + req.getPathInfo() + "</h2>");
        writer.write("<h2>requestUri: " + req.getRequestURI() + "</h2>");
        writer.write("<h2>docroot: " + req.getRealPath("/") + "</h2>");
        writer.write("<h2>environment</h2>");
        for (Map.Entry entry : System.getProperties().entrySet()) {
            writer.write("<p>&#20;&#20;&#20;");
            writer.write(entry.getKey().toString());
            writer.write("=");
            writer.write(entry.getValue().toString());
            writer.write("</p>");
        }
        writer.write("</body></html>\n");
        writer.close();
    }

    private String getVersion() throws IOException {
        Properties p;

        p = new Properties();
        p.load(getClass().getClassLoader().getResourceAsStream("/META-INF/pominfo.properties"));
        return p.getProperty("version");
    }

}
