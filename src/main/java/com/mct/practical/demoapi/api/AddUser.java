package com.mct.practical.demoapi.api;

import com.mct.practical.demoapi.di.DataInjection;
import com.mct.practical.demoapi.model.Response;
import com.mct.practical.demoapi.model.User;
import org.jetbrains.annotations.NotNull;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/api/adduser2")
public class AddUser extends HttpServlet {

    @Override
    protected void doPost(@NotNull HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String fullname = req.getParameter("fullname");

        Map<String, String> errors = new HashMap<>();

        if (username == null || (username = username.trim()).isEmpty()) {
            errors.put("username", "Username can't null");
        }

        if (password == null || (password = password.trim()).isEmpty()) {
            errors.put("password", "Password can't null");
        }

        if (fullname == null || (fullname = fullname.trim()).isEmpty()) {
            errors.put("fullname", "Fullname can't null");
        }

        if ("admin".equals(username)) {
            errors.put("username", "Username is exists!");
        }

        if (!errors.isEmpty()) {
            showResponse(resp, Response.error(errors).toJson());
            return;
        }

        User user = new User(-1, username, password, fullname);

        long id = DataInjection.provideUserRepo().addUser(user);

        if (id == -1) {
            showResponse(resp, Response.error("Add user error").toJson());
        } else {
            showResponse(resp, Response.success("Add user success!").toJson());
        }

    }

    protected void showResponse(@NotNull HttpServletResponse resp, String json) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.print(json);
        writer.flush();
    }

}
