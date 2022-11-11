<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10/22/2022
  Time: 11:03 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="row justify-content-center">
    <div>
        <h3>PRODUCT</h3>
    </div>
    <div class="d-flex justify-content-between my-3">
        <div>
            <div class="input-group shadow-sm">
                <input id="searchProduct" class="form-control form-control-sm " type="text"
                       placeholder="Search"
                       data-param-key="q"
                       data-url="/practical3/product/list"
                       value="${query}">
                <button class="input-group-append btn btn-info" type="button">
                    <i class="mdi mdi-magnify"></i>
                </button>
            </div>
        </div>
        <button class="btn btn-success" onclick="gotoAddProduct()">
            <i class="mdi mdi-plus"></i>
        </button>
    </div>
    <div id="tableProduct">
        <jsp:include page="list.jsp"/>
    </div>
</div>

<script>
    $(document).ready(function () {
        if (!window.ProductIndex) {
            loadScripts("${pageContext.request.contextPath}/public/js/admin/practical3/ProductController.js")
        }
        ProductIndex();
    })
</script>