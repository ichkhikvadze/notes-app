package com.example.notesapp.servlet;

import com.example.notesapp.dao.NoteDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/delete-note/*")
public class DeleteNoteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("username");
        String uri = req.getRequestURI();
        char lastSymbol = uri.charAt(uri.length() - 1);
        if (lastSymbol >= '0' && lastSymbol <= '9') {
            long noteId = Long.parseLong(uri.substring(13));
            NoteDao noteDao = NoteDao.getInstance();
            noteDao.deleteNote(noteId);
            session.setAttribute("userNotes", noteDao.getUserNotes(username));
        }
        req.getRequestDispatcher("/home.jsp").forward(req, resp);
    }
}
