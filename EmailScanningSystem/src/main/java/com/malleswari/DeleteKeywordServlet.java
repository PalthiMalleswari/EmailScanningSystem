package com.malleswari;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/deleteKeyword")
public class DeleteKeywordServlet extends HttpServlet {
    private KeywordDAO keywordDAO;

    @Override
    public void init() {
        keywordDAO = new KeywordDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String key = request.getParameter("keyword");

        keywordDAO.deleteKeyword(key);

        response.sendRedirect("viewKeyword.html");
    }
}
