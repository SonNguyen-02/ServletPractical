<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container-fluid">
    <div class="row justify-content-center mb-3">
        <div class="col-10">
            <a class="btn btn-warning" href="${pageContext.request.contextPath}/practical2/product">Back</a>
        </div>
    </div>
    <div class="row justify-content-center mb-4">
        <div class="col-10">
            <form method="get" action="${pageContext.request.contextPath}/practical2/product">
                <div class="input-group shadow-sm w-25">
                    <input hidden name="t" value="list">
                    <input name="q" class="form-control form-control-sm " type="text" placeholder="Search"
                           value="${query}">
                    <button class="input-group-append btn btn-info" type="submit">
                        <i class="mdi mdi-magnify"></i>
                    </button>
                </div>
            </form>
        </div>
    </div>
    <div class="row justify-content-center">
        <div class="col-10">
            <div class="card">
                <div class="card-header">
                    <div class="card-title my-2">List Product</div>
                </div>
                <div class="card-body">
                    <c:choose>
                        <c:when test="${!paging.getPagedList().isEmpty()}">
                            <table class="table table-hover table-striped table-light">
                                <thead>
                                <tr class="text-center">
                                    <th> #</th>
                                    <th> Code</th>
                                    <th> Name</th>
                                    <th> Price</th>
                                    <th> Desc</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="item" items="${paging.getPagedList()}" varStatus="loop">
                                    <tr class="text-center">
                                        <td>${(paging.getPageNumber() - 1) * paging.getPageSize() + loop.index + 1}</td>
                                        <td>${item.getCode()}</td>
                                        <td>${item.getName()}</td>
                                        <td>${item.getPrice()}</td>
                                        <td>${item.getDesc()}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <div class="mt-3">
                                Page ${paging.getPageNumber()} of ${paging.getTotalPage()}
                                    ${paging.getPageContainer(pageUrl)}
                            </div>
                        </c:when>
                        <c:otherwise>
                            <h6 class="text-danger">
                                No have product!
                            </h6>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    if (window.history.replaceState) {
        window.history.replaceState(null, null, window.location.href);
    }
</script>