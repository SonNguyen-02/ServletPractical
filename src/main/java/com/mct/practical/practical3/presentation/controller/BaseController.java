package com.mct.practical.practical3.presentation.controller;

import org.jetbrains.annotations.NotNull;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class BaseController extends HttpServlet {

    protected String absolutePath(String path) {
        return getServletContext().getRealPath(path);
    }

    protected Object getSessionAttr(@NotNull HttpServletRequest req, String name) {
        return req.getSession().getAttribute(name);
    }

    protected void setSessionAttr(@NotNull HttpServletRequest req, String name, Object value) {
        req.getSession().setAttribute(name, value);
    }

    protected <T> Map<String, String> validateModel(T t) {
        Map<String, String> errors = new HashMap<>();
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<T>> violations = validator.validate(t);
            for (ConstraintViolation<T> violation : violations) {
                errors.put(violation.getPropertyPath().toString(), violation.getMessage());
            }
        }
        return errors;
    }

    protected void goto404(@NotNull HttpServletResponse resp) throws IOException {
        resp.sendRedirect("https://http.cat/404");
    }

    protected void showResponse(@NotNull HttpServletResponse resp, String json) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.print(json);
        writer.flush();
    }

    protected void forwardTo(@NotNull HttpServletRequest req, HttpServletResponse resp, String path) throws ServletException, IOException {
        if (req.getParameter("ajax") != null) {
            req.getRequestDispatcher(path).forward(req, resp);
        } else {
            req.setAttribute("body", path);
            req.getRequestDispatcher("/practical3/shared/_layout.jsp").forward(req, resp);
        }
    }

}
