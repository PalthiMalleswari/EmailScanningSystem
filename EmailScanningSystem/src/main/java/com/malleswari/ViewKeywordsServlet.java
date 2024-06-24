package com.malleswari;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/viewKeywords")
public class ViewKeywordsServlet extends HttpServlet {
    private KeywordDAO keywordDAO;

    @Override
    public void init() {
        keywordDAO = new KeywordDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Keyword> keywords = keywordDAO.getKeywords();

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<table>");
        out.println("<tr><th>ID</th><th>Keyword</th><th>Weight</th></tr>");
        for (Keyword keyword : keywords) {
            out.println("<tr><td>" + keyword.getId() + "</td><td>" + keyword.getKeyword() + "</td><td>" + keyword.getWeight() + "</td></tr>");
        }
        out.println("</table>");
      
    }
}
