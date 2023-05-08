package com.example.notesapp.servlet;

import com.example.notesapp.bean.User;
import com.example.notesapp.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/signup.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();

        user.setFirstName(req.getParameter("first-name"));
        user.setLastName(req.getParameter("last-name"));
        user.setUsername(req.getParameter("username"));
        user.setPassword(req.getParameter("password"));

        UserDao userDao = UserDao.getInstance();

        if (userDao.createUser(user)) {
            req.getRequestDispatcher("/successfully-signed-up.jsp").forward(req, resp);
        } else {
            String errorMessage = "User with this username already exists! Try again!";

            req.setAttribute("error", errorMessage);
            req.getRequestDispatcher("/signup.jsp").forward(req, resp);
        }
    }
}
