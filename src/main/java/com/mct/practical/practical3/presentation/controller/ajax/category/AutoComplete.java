package com.mct.practical.practical3.presentation.controller.ajax.category;

import com.mct.practical.practical3.di.DataInjection;
import com.mct.practical.practical3.presentation.controller.BaseController;
import com.mct.practical.practical3.presentation.internalmodel.Response;
import org.jetbrains.annotations.NotNull;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/practical3/ajax/category/autocomplete")
public class AutoComplete extends BaseController {

    @Override
    protected void doPost(@NotNull HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String search = req.getParameter("search");
        Response response = Response.success();
        response.setData(DataInjection.provideCategoryUseCases().getGetCategories().invoke(search, 50, 0));
        showResponse(resp, response.toJson());
    }
}
