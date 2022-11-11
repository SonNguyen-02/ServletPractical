<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10/22/2022
  Time: 11:01 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>

<div class="row justify-content-center">
    <div class="col-lg-6 col-md-10">
        <div class="card">
            <div class="card-header bg-primary text-white d-flex align-items-center">
                <button class="btn btn-behance btn-sm me-1" onclick="back()">
                    <i class="mdi mdi-arrow-left"></i>
                </button>
                <h5 class="modal-title" id="exampleModalLabel">${edit==null?"Add":"Edit"} Category</h5>
            </div>
            <form>
                <div class="card-body">
                    <input id="id" name="id" hidden value="${val_id}">
                    <div class="mb-3">
                        <label for="name" class="form-label">Name</label>
                        <input type="text" class="form-control" id="name" name="name" value="${val_name}"
                               placeholder="Name">
                        <span class="error text-danger font-14"></span>
                    </div>
                    <div class="mb-3">
                        <label for="desc" class="form-label">Description</label>
                        <textarea class="form-control" id="desc" name="desc" rows="3"
                                  placeholder="Description">${val_desc}</textarea>
                        <span class="error text-danger font-14"></span>
                    </div>
                </div>
                <div class="card-footer d-flex justify-content-center">
                    <button type="button" class="btn btn-primary"
                            data-is-add="${edit==null?'true':'false'}"
                            onclick="submitCategoryForm($(this))">Submit
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    $(document).ready(function () {
        if (!window.CategoryIndex) {
            loadScripts("${pageContext.request.contextPath}/public/js/admin/practical3/CategoryController.js")
        }
    })
</script>

