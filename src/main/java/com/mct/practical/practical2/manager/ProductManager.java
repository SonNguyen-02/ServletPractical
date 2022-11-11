package com.mct.practical.practical2.manager;

import com.mct.practical.practical2.model.Paging;
import com.mct.practical.practical2.model.Product;
import com.mct.practical.practical2.validate.Validate;
import org.jetbrains.annotations.NotNull;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@WebServlet("/practical2/product")
public class ProductManager extends HttpServlet {

    private List<Product> products;

    @Override
    public void init() {
        products = new ArrayList<>();
        for (int i = 10; i < 80; i++) {
            Product product = new Product();
            product.setCode("AA00" + i);
            product.setName("Product " + i);
            product.setPrice(123f);
            product.setDesc("desc");
            products.add(product);
        }
    }

    @Override
    protected void doGet(@NotNull HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("t");
        if (type == null || type.isEmpty()) {
            type = "add";
        }
        if (type.equalsIgnoreCase("list")) {
            String query = req.getParameter("q");
            String page = req.getParameter("p");

            int mPage = Validate.getValidPage(page, 1);
            Paging<Product> productsPaging = new Paging<>(filter(products, query), mPage, 6);

            String url = "/practical2/product?t=list";
            if (query != null && !query.isEmpty()) {
                url += "&q=" + query;
            }
            url += "&p=";

            req.setAttribute("query", query);
            req.setAttribute("pageUrl", url);
            req.setAttribute("paging", productsPaging);
            req.setAttribute("body", "/practical2/product/list.jsp");
        } else {
            req.setAttribute("body", "/practical2/product/add.jsp");
        }

        req.getRequestDispatcher("/practical2/shared/_layout.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(@NotNull HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        String name = req.getParameter("name");
        String price = req.getParameter("price");
        String desc = req.getParameter("desc");

//        validateChay(req, code, name, price, desc);
        validateJavaBeans(req, code, name, price, desc);

        req.setAttribute("body", "/practical2/product/add.jsp");
        req.getRequestDispatcher("/practical2/shared/_layout.jsp").forward(req, resp);
    }

    private void validateChay(@NotNull HttpServletRequest req, String code, String name, String price, String desc) {

        boolean isValidForm = true;
        String errCode = "", errName = "", errPrice = "", errDesc = "";

        // start validate chay
        if (!Validate.isValidCode(code)) {
            isValidForm = false;
            errCode = "Code format invalid!";
        }

        if (products.stream().anyMatch(t -> t.getCode().equals(code))) {
            isValidForm = false;
            errCode = "Code is exits!";
        }

        if (!Validate.isValidName(name)) {
            isValidForm = false;
            errName = "Name format invalid!";
        }

        if (!Validate.isValidPrice(price)) {
            isValidForm = false;
            errPrice = "Price format invalid!";
        }

        if (!Validate.isValidDescription(desc)) {
            isValidForm = false;
            errDesc = "Please enter description.";
        }
        if (isValidForm) {
            Product product = new Product();
            product.setCode(code);
            product.setName(name);
            product.setPrice(Float.parseFloat(price));
            product.setDesc(desc);
            products.add(product);
            req.setAttribute("success", "Added product " + code);
        } else {
            setAttr(req, code, name, price, desc, errCode, errName, errPrice, errDesc);
        }
        // end validate chay
    }

    private void validateJavaBeans(HttpServletRequest req, String code, String name, String price, String desc) {

        boolean isValidForm;
        String errCode = "", errName = "", errPrice = "", errDesc = "";

        // start validate java beans
        Float mPrice = null;
        try {
            mPrice = Float.parseFloat(price);
        } catch (Throwable ignored) {
        }
        Product product = new Product();
        product.setCode(code.length() == 0 ? null : code.trim());
        product.setName(name.length() == 0 ? null : name.trim());
        product.setPrice(mPrice);
        product.setDesc(desc.length() == 0 ? null : desc.trim());
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<Product>> violations = validator.validate(product);
            for (ConstraintViolation<Product> violation : violations) {
                System.out.println(violation.getPropertyPath() + " - " + violation.getMessage());
                switch (violation.getPropertyPath().toString()) {
                    case "code":
                        errCode = violation.getMessage();
                        break;
                    case "name":
                        errName = violation.getMessage();
                        break;
                    case "desc":
                        errDesc = violation.getMessage();
                        break;
                    case "price":
                        errPrice = violation.getMessage();
                        break;
                }
            }
            isValidForm = violations.isEmpty();
        }
        if (products.stream().anyMatch(t -> t.getCode().equals(code))) {
            isValidForm = false;
            errCode = "Code is exits!";
        }
        if (isValidForm) {
            products.add(product);
            req.setAttribute("success", "Added product " + code);
        } else {
            setAttr(req, code, name, price, desc, errCode, errName, errPrice, errDesc);
        }
        // end validate java beans
    }

    private void setAttr(@NotNull HttpServletRequest req,
                         String code, String name, String price, String desc,
                         String errCode, String errName, String errPrice, String errDesc) {
        req.setAttribute("val_code", code);
        req.setAttribute("val_name", name);
        req.setAttribute("val_price", price);
        req.setAttribute("val_desc", desc);

        req.setAttribute("err_code", errCode);
        req.setAttribute("err_name", errName);
        req.setAttribute("err_price", errPrice);
        req.setAttribute("err_desc", errDesc);
    }

    private List<Product> filter(List<Product> list, String filter) {
        if (filter == null || filter.isEmpty()) {
            return list;
        }
        String f = filter.toLowerCase();
        return list.stream()
                .filter(p -> p.getCode().toLowerCase().contains(f) || p.getName().toLowerCase().contains(f))
                .collect(Collectors.toList());
    }
}
