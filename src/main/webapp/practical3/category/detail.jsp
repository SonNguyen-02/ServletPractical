<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10/23/2022
  Time: 2:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>

<div class="row justify-content-center">
    <div class="col-lg-6">
        <div class="card">
            <div class="card-header bg-primary text-white d-flex align-items-center">
                <button class="btn btn-behance btn-sm me-1" onclick="back()">
                    <i class="mdi mdi-arrow-left"></i>
                </button>
                <h5 class="modal-title" id="exampleModalLabel">Detail Category</h5>
            </div>
            <div class="card-body">
                <div class="mb-3 p-3 border-1 border-danger border-dashed">
                    <span class="form-label">Name: </span>
                    <span class="form-label">${val_name}</span>
                </div>
                <div class="mb-3 p-3 border-1 border-danger border-dashed">
                    <span class="form-label">Description: </span>
                    <span class="form-label">${val_desc}</span>
                </div>
            </div>
        </div>
    </div>
</div>