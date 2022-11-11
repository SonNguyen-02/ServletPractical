<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10/22/2022
  Time: 11:01 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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


<%--<!-- bootstrap 5.x or 4.x is supported. You can also use the bootstrap css 3.3.x versions -->--%>
<%--<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" crossorigin="anonymous">--%>

<!-- default icons used in the plugin are from Bootstrap 5.x icon library (which can be enabled by loading CSS below) -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.min.css" rel="stylesheet"
      crossorigin="anonymous">

<!-- alternatively you can use the font awesome icon library if using with `fas` theme (or Bootstrap 4.x) by uncommenting below. -->
<!-- link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" crossorigin="anonymous" -->

<!-- the fileinput plugin styling CSS file -->
<link href="https://cdn.jsdelivr.net/gh/kartik-v/bootstrap-fileinput@5.5.2/css/fileinput.min.css" media="all"
      rel="stylesheet" type="text/css"/>

<!-- if using RTL (Right-To-Left) orientation, load the RTL CSS file after fileinput.css by uncommenting below -->
<!-- link href="https://cdn.jsdelivr.net/gh/kartik-v/bootstrap-fileinput@5.5.2/css/fileinput-rtl.min.css" media="all" rel="stylesheet" type="text/css" /-->

<style>
    .file-preview {
        margin-bottom: 8px;
    }

    .file-preview .fileinput-remove {
        display: none;
    }

    .file-caption .file-caption-name {
        height: 42px;
        width: auto;
    }

</style>


<div class="row justify-content-center">
    <div class="col-lg-9">
        <div class="card">
            <div class="card-header bg-primary text-white d-flex align-items-center">
                <button class="btn btn-behance btn-sm me-1" onclick="back()">
                    <i class="mdi mdi-arrow-left"></i>
                </button>
                <h5 class="modal-title" id="exampleModalLabel">${edit==null?"Add":"Edit"} Product</h5>
            </div>
            <form enctype="multipart/form-data">
                <div class="card-body">
                    <input id="id" name="id" hidden value="${val_id}">
                    <div class="d-flex">
                        <div class="d-none align-items-center justify-content-center flex-column me-4 ">
                            <div class="drop-zone" data-name="${val_image}"
                                 data-src="/public/images/upload/product/${val_image}"
                                 style="width: 180px; height: 180px">
                                <span class="drop-zone__prompt"><i class="fas fa-cloud-upload-alt"></i><br/>Browse to upload file</span>
                                <input id="image" type="file" class="drop-zone__input" accept="image/*">
                            </div>
                            <span class="error text-danger mt-2 font-14"></span>
                        </div>
                        <div class="flex-grow-1">
                            <div class="mb-3 position-relative">
                                <label for="categoryId" class="form-label">Category</label>
                                <input type="text" class="form-control complete-category-input" id="categoryId"
                                       data-id="${val_cat_id}" value="${val_cat_name}"
                                       placeholder="Category">
                                <span class="error text-danger font-14"></span>

                                <div class="complete-category-box custom-scroll shadow" style="display:none;">
                                    <%--<span data-id="1">category name</span>--%>
                                    <div class="complete-category-box__hint">
                                        <span>Search category</span>
                                        <i class="mdi mdi-text-search-variant"></i>
                                    </div>
                                </div>
                            </div>
                            <div class="mb-3">
                                <label for="code" class="form-label">Code</label>
                                <input type="text" class="form-control" id="code" value="${val_code}"
                                       placeholder="Code">
                                <span class="error text-danger font-14"></span>
                            </div>
                            <div class="mb-3">
                                <label for="price" class="form-label">Price</label>
                                <input type="text" class="form-control" id="price" value="${val_price}"
                                       placeholder="Price">
                                <span class="error text-danger font-14"></span>
                            </div>
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="name" class="form-label">Name</label>
                        <input type="text" class="form-control" id="name" value="${val_name}" placeholder="Name">
                        <span class="error text-danger font-14"></span>
                    </div>
                    <div class="mb-3">
                        <label for="desc" class="form-label">Description</label>
                        <textarea class="form-control" id="desc" rows="3"
                                  placeholder="Description">${val_desc}</textarea>
                        <span class="error text-danger font-14"></span>
                    </div>
                    <c:if test="${edit!=null}">
                        <button type="button" class="btn btn-outline-github" onclick="showGalleries($(this))">Modify
                            galleries
                        </button>
                    </c:if>
                </div>
                <div class="card-footer d-flex justify-content-center">
                    <button type="button" class="btn btn-primary"
                            data-is-add="${edit==null?'true':'false'}"
                            onclick="submitProductForm($(this))">Submit
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div class="modal fade" id="galleriesDialog" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
     aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg  modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <span class="p-0 me-1"><i class="mdi mdi-image-multiple-outline" style="font-size:1.5em"></i></span>
                <h5 class="modal-title" id="staticBackdropLabel">Galleries</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body custom-scroll">
                <div class="mb-3">
                    <input id="galleries" name="galleries" type="file" multiple accept="image">
                    <span class="error text-danger font-14"></span>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    function showGalleries(ele) {
        $('#galleriesDialog').modal('show');
    }

    $(document).ready(function () {
        loadScripts("${pageContext.request.contextPath}/public/js/drag_drop_arena.js")
        if (!window.PrepareProductForm) {
            loadScripts("${pageContext.request.contextPath}/public/js/admin/practical3/ProductController.js")
        }
        PrepareProductForm();

    })
</script>

<!-- the jQuery Library -->
<%--<script src="https://code.jquery.com/jquery-3.6.0.min.js" crossorigin="anonymous"></script>--%>

<!-- buffer.min.js and filetype.min.js are necessary in the order listed for advanced mime type parsing and more correct
preview. This is a feature available since v5.5.0 and is needed if you want to ensure file mime type is parsed
correctly even if the local file's extension is named incorrectly. This will ensure more correct preview of the
selected file (note: this will involve a small processing overhead in scanning of file contents locally). If you
do not load these scripts then the mime type parsing will largely be derived using the extension in the filename
and some basic file content parsing signatures. -->
<script src="https://cdn.jsdelivr.net/gh/kartik-v/bootstrap-fileinput@5.5.2/js/plugins/buffer.min.js"
        type="text/javascript"></script>
<script src="https://cdn.jsdelivr.net/gh/kartik-v/bootstrap-fileinput@5.5.2/js/plugins/filetype.min.js"
        type="text/javascript"></script>

<!-- piexif.min.js is needed for auto orienting image files OR when restoring exif data in resized images and when you
wish to resize images before upload. This must be loaded before fileinput.min.js -->
<script src="https://cdn.jsdelivr.net/gh/kartik-v/bootstrap-fileinput@5.5.2/js/plugins/piexif.min.js"
        type="text/javascript"></script>

<!-- sortable.min.js is only needed if you wish to sort / rearrange files in initial preview.
This must be loaded before fileinput.min.js -->
<script src="https://cdn.jsdelivr.net/gh/kartik-v/bootstrap-fileinput@5.5.2/js/plugins/sortable.min.js"
        type="text/javascript"></script>

<!-- bootstrap.bundle.min.js below is needed if you wish to zoom and preview file content in a detail modal
dialog. bootstrap 5.x or 4.x is supported. You can also use the bootstrap js 3.3.x versions. -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>

<!-- the main fileinput plugin script JS file -->
<script src="https://cdn.jsdelivr.net/gh/kartik-v/bootstrap-fileinput@5.5.2/js/fileinput.min.js"></script>

<!-- optionally if you need translation for your language then include the locale file as mentioned below (replace LANG.js with your language locale) -->
<script src="https://cdn.jsdelivr.net/gh/kartik-v/bootstrap-fileinput@5.5.2/js/locales/LANG.js"></script>

<script>
    $(document).ready(function () {
        // initialize with defaults
        $('.file-caption .file-caption-icon').removeClass('icon-visible')
        $('#galleries').fileinput({
            uploadUrl: '/practical3/product/add/gallery',
            previewFileType: 'text',
            mergeAjaxCallbacks: true,
            ajaxSettings: {
                success: function (data) {
                    console.log("--> success " + data)
                }
            },
            maxFileCount: 10,
            validateInitialCount: true,
            overwriteInitial: false,
            initialPreview: [
                "<img class='kv-preview-data file-preview-image' src='https://picsum.photos/id/909/1920/1080'>",
                "<img class='kv-preview-data file-preview-image' src='https://picsum.photos/id/910/1920/1080'>",
                "<img class='kv-preview-data file-preview-image' src='https://picsum.photos/id/920/1920/1080'>"
            ],
            initialPreviewConfig: [
                {
                    caption: "Scene-1.jpg",
                    description: "<h5>Number One</h5> This is a representative placeholder description number one for this image..",
                    size: 628782,
                    width: "120px",
                    url: "/site/file-delete",
                    key: 1
                },
                {
                    caption: "Scene-2.jpg",
                    description: "<h5>Number Two</h5> This is a representative placeholder description number two for this image.",
                    size: 982873,
                    width: "120px",
                    url: "/site/file-delete",
                    key: 2
                },
                {
                    caption: "Scene-3.jpg",
                    description: "<h5>Number Three</h5> This is a representative placeholder description number three for this image.",
                    size: 567728,
                    width: "120px",
                    url: "/site/file-delete",
                    key: 3
                }
            ],
            /*
            allowedFileExtensions: ['jpg', 'png'],
            maxFileSize: 10000,
            */
        })
    });
</script>
