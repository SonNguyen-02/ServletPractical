package com.mct.practical.practical3.presentation.controller.product;

import com.mct.practical.practical3.di.DataInjection;
import com.mct.practical.practical3.domain.model.Category;
import com.mct.practical.practical3.domain.model.Product;
import com.mct.practical.practical3.presentation.controller.BaseController;
import org.jetbrains.annotations.NotNull;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/practical3/product/detail")
public class Detail extends BaseController {

    @Override
    protected void doGet(@NotNull HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));

            Product product = DataInjection.provideProductUseCases().getGetProduct().invoke(id);
            if (product == null) {
                goto404(resp);
                return;
            }
            req.setAttribute("product", product);
            forwardTo(req, resp, "/practical3/product/detail.jsp");
        } catch (NumberFormatException e) {
            goto404(resp);
        }
    }
}
