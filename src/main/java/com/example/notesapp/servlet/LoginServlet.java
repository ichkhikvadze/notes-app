package com.example.notesapp.servlet;

import com.example.notesapp.bean.Note;
import com.example.notesapp.bean.User;
import com.example.notesapp.dao.NoteDao;
import com.example.notesapp.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        UserDao userDao = UserDao.getInstance();
        User user = userDao.getUser(username);

        if (user == null) {
            String errorMessage = "Wrong username or password";
            req.setAttribute("error", errorMessage);
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        } else if (user.getPassword().equals(password)) {
            HttpSession session = req.getSession();

            NoteDao noteDao = NoteDao.getInstance();
            List<Note> userNotes = noteDao.getUserNotes(username);

            session.setAttribute("username", username);
            session.setAttribute("userNotes", userNotes);
            req.getRequestDispatcher("/home.jsp").forward(req, resp);
        } else {
            String errorMessage = "Wrong username or password";
            req.setAttribute("error", errorMessage);
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}
