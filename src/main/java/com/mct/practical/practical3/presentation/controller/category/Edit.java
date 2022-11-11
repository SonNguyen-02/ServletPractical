package com.mct.practical.practical3.presentation.controller.category;

import com.mct.practical.practical3.di.DataInjection;
import com.mct.practical.practical3.domain.model.Category;
import com.mct.practical.practical3.presentation.controller.BaseController;
import com.mct.practical.practical3.presentation.internalmodel.Response;
import org.jetbrains.annotations.NotNull;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/practical3/category/edit")
public class Edit extends BaseController {

    @Override
    protected void doGet(@NotNull HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));

            Category category = DataInjection.provideCategoryUseCases().getGetCategory().invoke(id);
            if (category == null) {
                goto404(resp);
                return;
            }
            req.setAttribute("val_id", category.getId());
            req.setAttribute("val_name", category.getName());
            req.setAttribute("val_desc", category.getDesc());
            req.setAttribute("edit", true);

            forwardTo(req, resp, "/practical3/category/add.jsp");
        } catch (NumberFormatException e) {
            goto404(resp);
        }
    }

    @Override
    protected void doPost(@NotNull HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String desc = req.getParameter("desc");
        Category category = new Category();

        category.setId(Long.parseLong(id));
        category.setName(name.length() == 0 ? null : name.trim());
        category.setDesc(desc.length() == 0 ? null : desc.trim());

        Map<String, String> errors = validateModel(category);

        if (DataInjection.provideCategoryUseCases().getCheckExistsCategory().invoke(category)) {
            errors.put("name", "Category name is exists!");
        }

        if (!errors.isEmpty()) {
            showResponse(resp, Response.error(errors).toJson());
            return;
        }

        boolean success = DataInjection.provideCategoryUseCases().getUpdateCategory().invoke(category);
        if (success) {
            showResponse(resp, Response.success("Edit category success!").toJson());
        } else {
            showResponse(resp, Response.error("Edit category false!").toJson());
        }

    }
}
