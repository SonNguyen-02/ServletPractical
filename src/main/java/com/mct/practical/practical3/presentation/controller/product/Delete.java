package com.mct.practical.practical3.presentation.controller.product;

import com.mct.practical.practical3.di.DataInjection;
import com.mct.practical.practical3.domain.model.Product;
import com.mct.practical.practical3.domain.use_cases.ProductUseCases;
import com.mct.practical.practical3.presentation.controller.BaseController;
import com.mct.practical.practical3.presentation.internalmodel.Response;
import com.mct.practical.practical3.utils.Config;
import com.mct.practical.practical3.utils.UploadManager;
import org.jetbrains.annotations.NotNull;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/practical3/product/delete")
public class Delete extends BaseController {

    @Override
    protected void doPost(@NotNull HttpServletRequest req, @NotNull HttpServletResponse resp) throws IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            ProductUseCases useCases = DataInjection.provideProductUseCases();
            Product product = useCases.getGetProduct().invoke(id);
            if (product == null) {
                showResponse(resp, Response.error("Product not found!").toJson());
            } else {
                boolean success = useCases.getRemoveProduct().invoke(product);
                if (success) {
                    UploadManager.delete(absolutePath(Config.PRODUCT_UPLOAD_PATH), product.getImage());
                    showResponse(resp, Response.success("Delete product success!").toJson());
                } else {
                    showResponse(resp, Response.error("Delete product error!").toJson());
                }
            }
        } catch (NumberFormatException e) {
            showResponse(resp, Response.error("Invalid data!").toJson());
        }
    }
}
