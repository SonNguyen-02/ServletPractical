<%--
  Created by IntelliJ IDEA.
  User: Son nc
  Date: 10/20/2022
  Time: 8:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <title>Plus Admin</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/public/images/logo-mini.svg"/>
    <!-- plugins:css -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@mdi/font@6.9.96/css/materialdesignicons.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/public/css/paged.list.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/public/css/toast.custom.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/public/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/public/css/custom.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-fileinput/4.4.7/css/fileinput.css" media="all"
          rel="stylesheet" type="text/css"/>
    <!-- end plugins:css -->
    <script src="${pageContext.request.contextPath}/public/js/jquery/jquery-3.6.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/public/js/admin/const.js"></script>



</head>

<body>
<div class="container-scroller">
    <!-- partial:partials/_sidebar.html -->
    <jsp:include page="_sidebar.jsp"/>
    <!-- partial -->
    <!-- page-body-wrapper start -->
    <div class="container-fluid page-body-wrapper">
        <!-- partial:partials/_settings-panel.html -->
        <jsp:include page="_settings-panel.jsp"/>
        <!-- partial -->
        <!-- partial:partials/_navbar.html -->
        <jsp:include page="_navbar.jsp"/>
        <!-- partial -->
        <!-- main-panel start -->
        <div class="main-panel">
            <!-- content-wrapper start -->
            <div id="contentBody" class="content-wrapper container-fluid pb-0 pt-3 position-relative">
                <jsp:include page="${body}"/>
            </div>
            <!-- content-wrapper ends -->
            <!-- partial:partials/_footer.html -->
            <jsp:include page="_footer.jsp"/>
            <!-- partial -->
        </div>
        <!-- main-panel ends -->
    </div>
    <!-- page-body-wrapper ends -->
    <!-- Confirm dialog -->
    <div class="modal fade" id="confirmDialog" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
         aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <span class="p-0 me-1"><i class="mdi mdi-logout-variant" style="font-size:1.5em"></i></span>
                    <h5 class="modal-title" id="staticBackdropLabel">Sign out</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                    <button type="button" class="btn btn-danger" submit>Yes</button>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- container-scroller -->

<script src="${pageContext.request.contextPath}/public/js/jquery/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/public/js/jquery/jquery.validate.unobtrusive.min.js"></script>
<script src="${pageContext.request.contextPath}/public/js/bootstrap/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/public/js/toast.custom.js"></script>
<script src="${pageContext.request.contextPath}/public/js/ajax.helper.js"></script>
<script src="${pageContext.request.contextPath}/public/js/admin/misc.js"></script>
<script src="${pageContext.request.contextPath}/public/js/admin/settings.js"></script>


<!-- endinject -->

</body>
</html>