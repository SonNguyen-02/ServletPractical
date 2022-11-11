package com.mct.practical.practical3.presentation.controller.product;

import com.mct.practical.practical3.di.DataInjection;
import com.mct.practical.practical3.domain.model.Product;
import com.mct.practical.practical3.presentation.controller.BaseController;
import com.mct.practical.practical3.presentation.internalmodel.Response;
import com.mct.practical.practical3.utils.RequestParams;
import org.jetbrains.annotations.NotNull;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@WebServlet("/practical3/product/add")
public class Add extends BaseController {

    @Override
    protected void doGet(@NotNull HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forwardTo(req, resp, "/practical3/product/add.jsp");
    }

    @Override
    protected void doPost(@NotNull HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestParams requestParams = RequestParams.parseRequest(req);

        int catId = -1;
        try {
            catId = Integer.parseInt(requestParams.getParam("cat_id"));
        } catch (Throwable ignored) {
        }
        Float mPrice = null;
        try {
            mPrice = Float.parseFloat(requestParams.getParam("price"));
        } catch (Throwable ignored) {
        }
        Product product = new Product();
        product.setCategoryId(catId);
        product.setCode(requestParams.getParam("code"));
        product.setName(requestParams.getParam("name"));
        product.setDesc(requestParams.getParam("desc"));
        product.setPrice(mPrice);
        /*
        product.setImage(UploadManager.generateFileName());
        */
        Map<String, String> errors = validateModel(product);

        if (DataInjection.provideCategoryUseCases().getGetCategory().invoke(product.getCategoryId()) == null) {
            errors.put("cat_id", "Please select a category!");
        }
        /*
        FileItem file = requestParams.getFileParam("image");

        String uplError;
        if ((uplError = UploadManager.validateFile(file)) != null) {
            errors.put("image", uplError);
        }
        */
        if (!errors.isEmpty()) {
            showResponse(resp, Response.error(errors).toJson());
            return;
        }
        /*
        if (!UploadManager.upload(file, absolutePath(Config.PRODUCT_UPLOAD_PATH), product.getImage())) {
            showResponse(resp, Response.error("Have an error when save image!").toJson());
            return;
        }
        */
        long id = DataInjection.provideProductUseCases().getCreateProduct().invoke(product);
        if (id != -1) {
            showResponse(resp, Response.success("Add product success!").toJson());
        } else {
            /*
            UploadManager.delete(absolutePath(Config.PRODUCT_UPLOAD_PATH), product.getImage());
            */
            showResponse(resp, Response.error("Have an error when create product!").toJson());
        }
    }

}



