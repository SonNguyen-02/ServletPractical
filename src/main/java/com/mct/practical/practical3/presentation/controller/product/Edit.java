package com.mct.practical.practical3.presentation.controller.product;

import com.mct.practical.practical3.di.DataInjection;
import com.mct.practical.practical3.domain.model.Category;
import com.mct.practical.practical3.domain.model.Product;
import com.mct.practical.practical3.presentation.controller.BaseController;
import com.mct.practical.practical3.presentation.internalmodel.Response;
import com.mct.practical.practical3.utils.Config;
import com.mct.practical.practical3.utils.RequestParams;
import com.mct.practical.practical3.utils.UploadManager;
import org.apache.commons.fileupload.FileItem;
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

@WebServlet("/practical3/product/edit")
public class Edit extends BaseController {

    @Override
    protected void doGet(@NotNull HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            int id = Integer.parseInt(req.getParameter("id"));

            Product product = DataInjection.provideProductUseCases().getGetProduct().invoke(id);
            if (product == null) {
                goto404(resp);
                return;
            }
            req.setAttribute("val_id", product.getId());
            req.setAttribute("val_cat_id", product.getCategoryId());
            req.setAttribute("val_code", product.getCode());
            req.setAttribute("val_name", product.getName());
            req.setAttribute("val_desc", product.getDesc());
            req.setAttribute("val_price", product.getPrice());
            req.setAttribute("val_image", product.getImage());
            req.setAttribute("val_cat_name", product.getCategoryName());
            req.setAttribute("edit", true);

            forwardTo(req, resp, "/practical3/product/add.jsp");
        } catch (NumberFormatException e) {
            goto404(resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestParams requestParams = RequestParams.parseRequest(req);
        Product product = null;
        try {
            long id = Long.parseLong(requestParams.getParam("id"));
            product = DataInjection.provideProductUseCases().getGetProduct().invoke(id);
        } catch (Throwable ignored) {
        }
        if (product == null) {
            showResponse(resp, Response.error("Not found product!").toJson());
            return;
        }

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
        product.setCategoryId(catId);
        product.setCode(requestParams.getParam("code"));
        product.setName(requestParams.getParam("name"));
        product.setDesc(requestParams.getParam("desc"));
        product.setPrice(mPrice);

        Map<String, String> errors = validateModel(product);

        if (DataInjection.provideCategoryUseCases().getGetCategory().invoke(product.getCategoryId()) == null) {
            errors.put("cat_id", "Please select a category!");
        }

        FileItem file = requestParams.getFileParam("image");

        if (file != null) {
            String uplError;
            if ((uplError = UploadManager.validateFile(file)) != null) {
                errors.put("image", uplError);
            }
        }

        if (!errors.isEmpty()) {
            showResponse(resp, Response.error(errors).toJson());
            return;
        }

        if (file != null) {
            String newName = UploadManager.generateFileName();
            if (!UploadManager.upload(file, absolutePath(Config.PRODUCT_UPLOAD_PATH), newName)) {
                showResponse(resp, Response.error("Have an error when save image!").toJson());
                return;
            }
            UploadManager.delete(absolutePath(Config.PRODUCT_UPLOAD_PATH), product.getImage());
            product.setImage(newName);
        }

        boolean success = DataInjection.provideProductUseCases().getUpdateProduct().invoke(product);
        if (success) {
            showResponse(resp, Response.success("Edit product success!").toJson());
        } else {
            UploadManager.delete(absolutePath(Config.PRODUCT_UPLOAD_PATH), product.getImage());
            showResponse(resp, Response.error("Have an error when edit product!").toJson());
        }
    }
}
