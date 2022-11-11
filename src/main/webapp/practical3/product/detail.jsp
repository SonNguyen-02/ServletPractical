<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10/22/2022
  Time: 00:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/public/css/drag_drop_arena.css">
<style>
    .complete-category-box {
        position: absolute;
        left: 0;
        right: 0;
        bottom: -2px;
        transform: translateY(100%);
        border: 1px solid #cdcdcd;
        background: white;
        height: 180px;
        user-select: none;
        overflow-y: auto;
    }

    .complete-category-box > span {
        padding: 1rem;
        display: block;
        border-bottom: solid 1px #cdcdcd;
    }

    .complete-category-box > span:hover {
        background: rgba(120, 210, 255, 0.15);
        cursor: pointer;
    }

    .complete-category-box__hint {
        color: rgba(70, 70, 70, 0.7);
        display: flex;
        align-items: center;
        justify-content: center;
        height: 100%;
    }

    .complete-category-box__hint i {
        font-size: 24px;
        margin-left: 8px;
    }
</style>
<div class="row justify-content-center">
    <div class="col-lg-8">
        <div class="card">
            <div class="card-header bg-primary text-white d-flex align-items-center">
                <button class="btn btn-behance btn-sm me-1" onclick="back()">
                    <i class="mdi mdi-arrow-left"></i>
                </button>
                <h5 class="modal-title" id="exampleModalLabel">Detail Product</h5>
            </div>
            <div class="card-body">
                <div class="d-flex">
                    <div class="d-flex align-items-center justify-content-center flex-column me-4">
                        <img style="height: 180px; width: 180px"
                             src="${pageContext.request.contextPath}/public/images/upload/product/${product.getImage()}"
                             alt="icon">
                    </div>
                    <div class="flex-grow-1">
                        <div class="mb-3 position-relative">
                            <label for="categoryId" class="form-label">Category</label>
                            <input type="text" class="form-control complete-category-input" id="categoryId"
                                   value="${product.getCategoryName()}" disabled>
                        </div>
                        <div class="mb-3">
                            <label for="code" class="form-label">Code</label>
                            <input type="text" class="form-control" id="code" value="${product.getCode()}" disabled>
                        </div>
                        <div class="mb-3">
                            <label for="price" class="form-label">Price</label>
                            <input type="text" class="form-control" id="price" value="${product.getPrice()}" disabled>
                        </div>
                    </div>
                </div>
                <div class="mb-3">
                    <label for="name" class="form-label">Name</label>
                    <input type="text" class="form-control" id="name" value="${product.getName()}" disabled>
                </div>
                <div class="mb-3">
                    <label for="desc" class="form-label">Description</label>
                    <textarea class="form-control" id="desc" rows="3" disabled>${product.getDesc()}</textarea>
                </div>
            </div>
        </div>
    </div>
</div>
