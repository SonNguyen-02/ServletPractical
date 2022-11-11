package com.mct.practical.practical3.presentation.controller.category;

import com.mct.practical.practical3.di.DataInjection;
import com.mct.practical.practical3.domain.model.Category;
import com.mct.practical.practical3.domain.use_cases.CategoryUseCases;
import com.mct.practical.practical3.presentation.controller.BaseController;
import com.mct.practical.practical3.presentation.internalmodel.Response;
import org.jetbrains.annotations.NotNull;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/practical3/category/delete")
public class Delete extends BaseController {

    @Override
    protected void doPost(@NotNull HttpServletRequest req, @NotNull HttpServletResponse resp) throws IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            CategoryUseCases useCases = DataInjection.provideCategoryUseCases();
            Category category = useCases.getGetCategory().invoke(id);
            if (category == null) {
                showResponse(resp, Response.error("Category not found!").toJson());
            } else {
                boolean success = useCases.getRemoveCategory().invoke(category);
                if (success) {
                    showResponse(resp, Response.success("Delete category success!").toJson());
                } else {
                    showResponse(resp, Response.error("Delete category error!").toJson());
                }
            }
        } catch (NumberFormatException e) {
            showResponse(resp, Response.error("Invalid data!").toJson());
        }
    }
}
