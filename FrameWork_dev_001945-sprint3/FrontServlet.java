package etu1945.framework.servlet;

import java.text.*;
import java.util.*;
import java.io.File;
import java.net.URL;
import java.net.URI;
import java.lang.Package;
import jakarta.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.http.*;
import java.lang.reflect.Method;
import java.net.URISyntaxException;

import etu1945.framework.*;

public class FrontServlet extends HttpServlet {

    HashMap<String,Mapping> MappingUrls = new HashMap<>();

    public  void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException  {
            processRequest(request, response);
    }

    public  void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException  {

	}

    public void processRequest(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException  {
    
        response.setContentType("text/html");
        PrintWriter out = response.getWriter(); 

        out.println("<h1>" + this.MappingUrls + "</h1>");
        out.println("<h1>" + request.getRequestURI().substring(request.getContextPath().length()) + "</h1>");
    }

    
    public void init() throws ServletException {
        try {
            super.init();
            // String packages = "etu1945.models";
            String packages = String.valueOf(getInitParameter("packages"));
            String path = packages.replaceAll("[.]", "/");
            URL packagesUrl = Thread.currentThread().getContextClassLoader().getResource(path);
            File packDir = new File(packagesUrl.toURI());
            File[] inside = packDir.listFiles(file->file.getName().endsWith(".class"));
            List<Class> list = new ArrayList<>();
            for(File f : inside) {
                String className = packages+"."+f.getName().substring(0, f.getName().lastIndexOf("."));
                list.add(Class.forName(className));
            }
            for(Class c : list) {
                Method[] methods = c.getDeclaredMethods();
                for(Method m : methods) {
                    if (m.isAnnotationPresent(etu1945.framework.Lien.class)) {
                        Lien url = m.getAnnotation(etu1945.framework.Lien.class);
                        if (!url.url().isEmpty() && url.url() != null ) {
                            Mapping map = new Mapping( c.getName(), m.getName() ) ;
                            this.MappingUrls.put( url.url(), map ) ;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}