/*
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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class HelloWar extends HttpServlet {
    private static final String TITLE = "Hello, World!";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Writer writer;
        String[] cmds;
        HttpSession session;

        response.setContentType("text/html");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
        writer = response.getWriter();
        cmds = request.getParameterValues("cmd");
        switch (cmds != null && cmds.length == 1 ? cmds[0] : "") {
            case "info":
                info(request, writer);
                break;
            case "create_session":
                page("Create Session", writer, "result", request.getSession(true).getId());
                break;
            case "invalidate_session":
                session = request.getSession(false);
                if (session != null) {
                    session.invalidate();
                }
                page("Invalidate Session", writer, "result", session == null ? "no session - nothing to do" : "done");
                break;
            case "properties":
                page("Properties", writer, sort((Map) System.getProperties()));
                break;
            case "environment":
                page("Environment", writer, sort(System.getenv()));
                break;
            case "statuscode":
                response.sendError(Integer.parseInt(parameter(request, "code")));
                break;
            case "runtimeexception":
                throw new RuntimeException("demo runtime exception");
            case "servletexception":
                throw new ServletException("demo servlet exception");
            case "ioexception":
                throw new IOException("demo IO exception");
            default:
                page(TITLE, writer);
                break;
        }
        writer.close();
    }

    private String parameter(HttpServletRequest request, String name) {
        String[] values;

        values = request.getParameterValues(name);
        switch (values.length) {
            case 0:
                return "";
            case 1:
                return values[0];
            default:
                return "(ambiguous)";
        }
    }

    private void info(HttpServletRequest request, Writer writer) throws IOException {
        String msg;
        InetAddress a;

        try {
            a = InetAddress.getLocalHost();
            msg = "ok";
        } catch (UnknownHostException e) {
            a = InetAddress.getLocalHost();
            msg = "error: " + e.getMessage();
        }
        page("Info", writer, "hellowar", getVersion(),
                "session", getSession(request),
                "contextPath", request.getContextPath(),
                "pathInfo", request.getPathInfo(),
                "requestUri", request.getRequestURI(),
                "docroot", request.getRealPath("/"),
                "getLocalHost", msg,
                "cannonicalHostName", a.getCanonicalHostName(),
                "hostName", a.getHostName(),
                "hostAddress", a.getHostAddress());
    }

    protected void page(String title, Writer writer, String... entries) throws IOException {
        Map<String, String> map;

        map = new LinkedHashMap<>();
        for (int i = 0; i < entries.length; i += 2) {
            map.put(entries[i], entries[i + 1]);
        }
        page(title, writer, map);
    }

    protected void page(String title, Writer writer, Map<String, String> map) throws IOException {
        writer.write("<html><header><title>" + TITLE + "</title><body>\n");
        navigation(writer);
        writer.write("<h2>" + title + "</h2>\n");
        writer.write("<ul>");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            writer.write("<li>");
            writer.write(entry.getKey());
            writer.write("=");
            writer.write(or(entry.getValue(), "(null)"));
            writer.write("</li>");
        }
        writer.write("</ul>");
        writer.write("</body></html>\n");
    }

    private static String or(String str, String dflt) {
        return str != null ? str : dflt;
    }

    private void navigation(Writer writer) throws IOException {
        writer.write("<a href='?cmd= '>Home</a> ");
        writer.write("<a href='?cmd=info'>Info</a> ");
        writer.write("<a href='?cmd=properties'>Properties</a> ");
        writer.write("<a href='?cmd=environment'>Environment</a> ");
        writer.write("<a href='?cmd=create_session'>Create Session</a> ");
        writer.write("<a href='?cmd=invalidate_session'>Invalidate Session</a> ");
        writer.write("<a href='?cmd=statuscode&code=401'>Status Code</a> ");
        writer.write("<a href='?cmd=runtimeexception'>RuntimeException</a> ");
        writer.write("<a href='?cmd=servletexception'>ServletException</a> ");
        writer.write("<a href='?cmd=servletexception'>ServletException</a> ");
        writer.write("\n<hr/>\n");

    }
    protected Map<String, String> sort(Map<String, String> map) throws IOException {
        List<String> keys;
        Map<String, String> result;

        keys = new ArrayList(map.keySet());
        Collections.sort(keys);
        result = new LinkedHashMap<>();
        for (String key : keys) {
            result.put(key, map.get(key));
        }
        return result;
    }

    private String getVersion() throws IOException {
        Properties p;

        p = new Properties();
        p.load(getClass().getClassLoader().getResourceAsStream("/META-INF/pominfo.properties"));
        return p.getProperty("version");
    }

    private String getSession(HttpServletRequest request) {
        HttpSession session;

        session = request.getSession(false);
        return session == null ? "" : session.getId();
    }
}
