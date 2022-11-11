package com.mct.practical.practical1;

import org.jetbrains.annotations.NotNull;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/practical1/calc")
public class Calculator extends HttpServlet {

    @Override
    protected void doGet(@NotNull HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("/practical1/calc.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(@NotNull HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        float a, b, result;
        String error = "";

        try {
            a = Float.parseFloat(req.getParameter("txtNum1"));
            b = Float.parseFloat(req.getParameter("txtNum2"));
        } catch (NumberFormatException e) {
            a = b = 0;
            error = "Number invalid!";
        }

        String operator = req.getParameter("btnCalc");
        switch (operator) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "x":
                result = a * b;
                break;
            case "/":
                result = a / b;
                break;
            default:
                result = 0;
                error = "Operator invalid!";
        }
        req.setAttribute("error", error);
        if (error.isEmpty()) {
            req.setAttribute("result", a + " " + operator + " " + b + " = " + result);
        }
        RequestDispatcher rd = req.getRequestDispatcher("/practical1/calc.jsp");
        rd.forward(req, resp);
    }

}
