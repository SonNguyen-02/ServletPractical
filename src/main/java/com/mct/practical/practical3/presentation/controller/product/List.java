package com.mct.practical.practical3.presentation.controller.product;

import com.mct.practical.practical3.di.DataInjection;
import com.mct.practical.practical3.domain.model.Category;
import com.mct.practical.practical3.domain.model.Product;
import com.mct.practical.practical3.domain.use_cases.CategoryUseCases;
import com.mct.practical.practical3.domain.use_cases.ProductUseCases;
import com.mct.practical.practical3.presentation.controller.BaseController;
import com.mct.practical.practical3.utils.Paging;
import com.mct.practical.practical3.utils.Validate;
import org.jetbrains.annotations.NotNull;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/practical3/product/list")
public class List extends BaseController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setupTable(req);
        forwardTo(req, resp, "/practical3/product/list.jsp");
    }

    public static void setupTable(@NotNull HttpServletRequest req) {
        String query = req.getParameter("q");
        String page = req.getParameter("p");
        int mPage = Validate.getValidPage(page, 1);
        final int pageSize = 3;
        ProductUseCases useCases = DataInjection.provideProductUseCases();

        Paging<Product> paging = new Paging<>(
                useCases.getGetProducts().invoke(query, null, pageSize, (mPage - 1) * pageSize),
                useCases.getGetProducts().count(query, null), mPage, pageSize);

        String url = "/practical3/product/list";
        if (query != null && !query.isEmpty()) {
            url += "?q=" + query + "&p=";
        } else {
            url += "?p=";
        }

        req.setAttribute("query", query);
        req.setAttribute("pageUrl", url);
        req.setAttribute("paging", paging);
    }
}
