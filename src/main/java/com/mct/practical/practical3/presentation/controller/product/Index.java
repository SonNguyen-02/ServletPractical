package com.mct.practical.practical3.presentation.controller.product;

import com.mct.practical.practical3.presentation.controller.BaseController;
import org.jetbrains.annotations.NotNull;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/practical3/product")
public class Index extends BaseController {

    @Override
    protected void doGet(@NotNull HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List.setupTable(req);
        forwardTo(req, resp, "/practical3/product/index.jsp");
    }
}
