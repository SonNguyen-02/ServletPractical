package com.mct.practical.practical3.presentation.controller.category;

import com.mct.practical.practical3.di.DataInjection;
import com.mct.practical.practical3.domain.model.Category;
import com.mct.practical.practical3.domain.use_cases.CategoryUseCases;
import com.mct.practical.practical3.presentation.controller.BaseController;
import com.mct.practical.practical3.utils.Paging;
import com.mct.practical.practical3.utils.Validate;
import org.jetbrains.annotations.NotNull;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/practical3/category/list")
public class List extends BaseController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setupTable(req);
        forwardTo(req, resp, "/practical3/category/list.jsp");
    }

    public static void setupTable(@NotNull HttpServletRequest req) {
        String query = req.getParameter("q");
        String page = req.getParameter("p");
        int mPage = Validate.getValidPage(page, 1);
        final int pageSize = 3;
        CategoryUseCases useCases = DataInjection.provideCategoryUseCases();

        Paging<Category> paging = new Paging<>(
                useCases.getGetCategories().invoke(query, pageSize, (mPage - 1) * pageSize),
                useCases.getGetCategories().count(query), mPage, pageSize);

        String url = "/practical3/category/list";
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
