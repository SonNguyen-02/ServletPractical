package com.mct.practical.practical3;

import com.google.gson.Gson;
import com.mct.practical.practical3.domain.model.Gallery;
import com.mct.practical.practical3.presentation.controller.BaseController;
import com.mct.practical.practical3.presentation.internalmodel.Response;
import com.mct.practical.practical3.utils.JsonHelper;
import com.mct.practical.practical3.utils.RequestParams;
import org.apache.commons.fileupload.FileItem;
import org.jetbrains.annotations.NotNull;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet("/practical3/test")
public class Test extends BaseController {

    @Override
    protected void doGet(@NotNull HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String path = req.getServletContext().getRealPath("public/images/upload");
        System.out.println(path);
        System.out.println(absolutePath(""));

//        String sql = StatementFactory.createSelectStatement()
//                .column("COUNT(1) as total")
//                .from("tblcategory")
//                .like("cat_name", "abc", LikeType.BOTH)
//                .buildStatement();

//        Random random = new Random();
//        Product product = new Product();
//        product.setCategoryId(random.nextInt(5) + 1);
//        product.setCode("AA" + (random.nextInt(8800) + 1000));
//        product.setName("Iphone 15");
//        product.setDesc("New phone!");
//        product.setPrice(random.nextInt(900) + 100);

//        long id = new ProductDao().addProduct(product);
//        boolean ok = new ProductDao().editProduct(product);
//        boolean ok = new ProductDao().deleteProduct(1);
//        List<Product> products = new ProductDao().getProducts("9", new Integer[]{1, 3, 4}, 100, 0);
//        System.out.println(products);

        Gallery gallery1 = new Gallery(0, "hello.jpg", "2010-10-10");
        Gallery gallery2 = new Gallery(1, "abc.jpg", "2020-05-12");
        Gallery gallery3 = new Gallery(0, "lol.jpg", "2021-05-30");

        List<Gallery> galleries = new ArrayList<>(Arrays.asList(gallery1, gallery2, gallery3));

        System.out.println(new Gson().toJson(galleries));

        String json = "[{\"type\":0,\"name\":\"hello.jpg\",\"date\":\"2010-10-10\"},{\"type\":1,\"name\":\"abc.jpg\",\"date\":\"2020-05-12\"},{\"type\":0,\"name\":\"lol.jpg\",\"date\":\"2021-05-30\"}]";
        List<Gallery> galleries1 = JsonHelper.jsonToList(json, Gallery.class);
        System.out.println(galleries1);

        forwardTo(req, resp, "/practical3/test.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestParams requestParams = RequestParams.parseRequest(req);
        FileItem fileItem = requestParams.getFileParam("galleries");
        System.out.println(fileItem);
//        if (!UploadManager.upload(fileItem, absolutePath(Config.TEST_UPLOAD_PATH), fileItem.getName())) {
//            showResponse(resp, Response.error("Have an error when save image!").toJson());
//            return;
//        }

//        UploadError error = new UploadError();
//        error.error = "file error";
//        showResponse(resp, error.toJson());
//        showResponse(resp, Response.success(fileItem.toString()).toJson());

        showResponse(resp, Response.error(fileItem.toString()).setError(fileItem.toString()).toJson());
    }


}
