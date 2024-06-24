package com.malleswari;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/scanEmail")
public class ScanEmailServlet extends HttpServlet {
    private KeywordDAO keywordDAO;
    private SuspicionCalculator suspicionCalculator;

    @Override
    public void init() {
        keywordDAO = new KeywordDAO();
        suspicionCalculator = new SuspicionCalculator(keywordDAO);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String emailContent = request.getParameter("emailContent");

        double suspicionPercentage = suspicionCalculator.calculateSuspicionPercent(emailContent);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Scan Results</h1>");
        out.printf("Suspicion Percentage: %.2f%%\n<br>", suspicionPercentage);
        out.println("<a href='home.html'>Back to Home</a>");
        out.println("</body></html>");
    }
}
