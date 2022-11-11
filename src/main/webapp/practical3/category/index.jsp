<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10/22/2022
  Time: 11:03 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>

<div class="row justify-content-center">
    <div>
        <h3>CATEGORY</h3>
    </div>
    <div class="d-flex justify-content-between my-3">
        <div>
            <div class="input-group shadow-sm">
                <input id="searchCategory" class="form-control form-control-sm " type="text"
                       placeholder="Search"
                       data-param-key="q"
                       data-url="/practical3/category/list"
                       value="${query}">
                <button class="input-group-append btn btn-info" type="button">
                    <i class="mdi mdi-magnify"></i>
                </button>
            </div>
        </div>
        <button class="btn btn-success" onclick="gotoAddCategory()">
            <i class="mdi mdi-plus"></i>
        </button>
    </div>
    <div id="tableCategory">
        <jsp:include page="list.jsp"/>
    </div>
</div>

<script>
    $(document).ready(function () {
        if (!window.CategoryIndex) {
            loadScripts("${pageContext.request.contextPath}/public/js/admin/practical3/CategoryController.js")
        }
        CategoryIndex();
    })
</script>

