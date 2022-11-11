<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10/23/2022
  Time: 3:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="card card-body">
    <table class="table table-hover table-bordered">
        <thead>
        <tr class="text-center">
            <th class="p-3" style="width:80px">#</th>
            <th class="p-3">Name</th>
            <th class="p-3">Desc</th>
            <th class="p-3" style="width:150px">Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${paging!=null &&!paging.getPagedList().isEmpty()}">
                <c:forEach var="item" items="${paging.getPagedList()}" varStatus="loop">
                    <tr class="text-center" data-id="${item.getId()}">
                        <td>${(paging.getPageNumber() - 1) * paging.getPageSize() + loop.index + 1}</td>
                        <td>${item.getName()}</td>
                        <td>${item.getDesc()}</td>
                        <td>
                            <button class="btn btn-warning btn-sm" onclick="gotoEditCategory(${item.getId()})">
                                <i class="mdi mdi-pencil"></i>
                            </button>
                            <button class="btn btn-primary btn-sm ms-1 me-1" onclick="gotoDetailCategory(${item.getId()})">
                                <i class="mdi mdi-text-box-multiple-outline"></i>
                            </button>
                            <button class="btn btn-danger btn-sm" onclick="showDeleteCategoryModal($(this))">
                                <i class="mdi mdi-delete-outline"></i>
                            </button>
                        </td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <tr>
                    <td colspan="4">
                        <h6 class="text-danger text-center pt-2">
                            No have category!
                        </h6>
                    </td>
                </tr>
            </c:otherwise>
        </c:choose>
        </tbody>
    </table>
    <div id="contentPager" class="mt-3">
        Page ${paging.getPageNumber()} of ${paging.getTotalPage()}
        ${paging.getPageContainer(pageUrl)}
    </div>
</div>
<script>
    if (window.CategoryIndex) {
        CategoryIndex();
    }
</script>