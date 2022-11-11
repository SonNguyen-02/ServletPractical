<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="d-flex justify-content-center mb-3">
    <a class="btn btn-warning mx-1" href="${pageContext.request.contextPath}/practical2/product?t=list">List Product</a>
</div>
<div class="d-flex justify-content-center">
    <span class="text-success fw-bold ${success != null ? 'pb-4' : ''}">${success}</span>
</div>
<div class="d-flex justify-content-center align-items-center">
    <div class="card w-50">
        <div class="card-header bg-primary text-white">
            <h5 class="modal-title" id="exampleModalLabel">Add Product</h5>
        </div>
        <form method="post">
            <div class="card-body">
                <div class="mb-3">
                    <label for="code" class="form-label">Code</label>
                    <input type="text" class="form-control" id="code" name="code" value="${val_code}">
                    <span class="error text-danger" style="font-size: 14px">${err_code}</span>
                </div>
                <div class="mb-3">
                    <label for="name" class="form-label">Name</label>
                    <input type="text" class="form-control" id="name" name="name" value="${val_name}">
                    <span class="error text-danger" style="font-size: 14px">${err_name}</span>
                </div>
                <div class="mb-3">
                    <label for="price" class="form-label">Price</label>
                    <input type="text" class="form-control" id="price" name="price" value="${val_price}">
                    <span class="error text-danger" style="font-size: 14px">${err_price}</span>
                </div>
                <div class="mb-3">
                    <label for="desc" class="form-label">Description</label>
                    <textarea class="form-control" id="desc" name="desc" rows="3">${val_desc}</textarea>
                    <span class="error text-danger" style="font-size: 14px">${err_desc}</span>
                </div>
            </div>
            <div class="card-footer d-flex justify-content-center">
                <button type="submit" class="btn btn-primary">Submit</button>
            </div>
        </form>
    </div>
</div>
