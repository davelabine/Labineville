package com.labineville;


import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;



/**
 * Created by davidl on 2/17/15.
 */
public class LabinevilleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("testing") == null) {
            response.setContentType("text/plain");
            response    .getWriter().println("Hello, this is a testing servlet. \n\n");
            Properties p = System.getProperties();
            p.list(response.getWriter());

        } else {
            UserService userService = UserServiceFactory.getUserService();
            User currentUser = userService.getCurrentUser();

            if (currentUser != null) {
                response.setContentType("text/plain");
                response.getWriter().println("This is the Servlet " + currentUser.getNickname());
            } else {
                response.sendRedirect(userService.createLoginURL(request.getRequestURI()));
            }
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


}
