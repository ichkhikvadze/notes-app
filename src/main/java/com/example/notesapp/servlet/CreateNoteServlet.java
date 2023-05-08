package com.example.notesapp.servlet;

import com.example.notesapp.bean.Note;
import com.example.notesapp.dao.NoteDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/create-note")
public class CreateNoteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("username");
        String noteText = req.getParameter("note-text");

        NoteDao noteDao = NoteDao.getInstance();
        Note noteToAdd = new Note();
        noteToAdd.setUsername(username);
        noteToAdd.setNoteText(noteText);
        noteDao.createNote(noteToAdd);

        session.setAttribute("userNotes", noteDao.getUserNotes(username));
        req.getRequestDispatcher("/home.jsp").forward(req, resp);
    }
}
