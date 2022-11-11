const _ProductTable = '#tableProduct';
const _ACTION_Product_Index = _PREFIX + "/product"
const _ACTION_Product_Add = _ACTION_Product_Index + "/add"
const _ACTION_Product_Edit = _ACTION_Product_Index + "/edit"
const _ACTION_Product_Delete = _ACTION_Product_Index + "/delete"
const _ACTION_Product_Detail = _ACTION_Product_Index + '/detail'
const _ACTION_Product_List = _ACTION_Product_Index + '/list'
const _ACTION_Category_Autocomplete = '/practical3/ajax/category/autocomplete';

function ProductIndex() {
    prepareKeyboardAction('#searchProduct', _ProductTable, _ACTION_Product_Index)
    prepareMouseAction('#contentPager a[href]', _ProductTable, _ACTION_Product_Index);
}

function PrepareProductForm() {
    let mInput = $(".complete-category-input");
    let mBox = $(".complete-category-box");

    let load = (value) => {
        loadUrl(_ACTION_Category_Autocomplete, data => {
            if (!data.success || data.data.length === 0) {
                mBox.html(`<div class="complete-category-box__hint">
                                <span>No result</span>
                                <i class="mdi mdi-emoticon-dead-outline"></i>
                            </div>`)
            } else {
                mBox.html("")
                for (let i = 0; i < data.data.length; i++) {
                    mBox.append(`<span class="item" data-id="` + data.data[i].id + `">` + data.data[i].name + `</span>`)
                }
                mBox.find(".item").click(function () {
                    mInput.data('id', $(this).data('id'))
                    mInput.val($(this).text())
                    mInput.next().html("")
                    mInput.blur()
                })
            }
        }, null, "POST", {search: value})
    }

    mInput.closest("form").find("textarea, input:not(.complete-category-input)").each(function () {
        $(this).off('keyup').on('keyup', function () {
            $(this).next().html("")
        })
    })

    mInput.off('focus').on('focus', function () {
        if (!mBox.data('search')) {
            mBox.data('search', true);
            let value = mInput.val().trim();
            if (value) load(value);
        }
        mBox.fadeIn(100);
        mInput.next().hide()
    }).off('blur').on('blur', function () {
        mBox.fadeOut(100);
        mInput.next().show()
    });
    mBox.on('mousedown', function (e) {
        e.preventDefault();
    });
    mInput.off('keyup').on('keyup', function () {
        let value = mInput.val().trim();
        mInput.data('id', -1)
        if (value.length === 0) {
            mBox.html(`<div class="complete-category-box__hint">
                            <span>Search category</span>
                            <i class="mdi mdi-text-search-variant"></i>
                        </div>`)
            return;
        }
        load(value);
    });


}

function gotoAddProduct() {
    load(_ACTION_Product_Add, ContentBody, _ACTION_Product_Add);
}

function gotoEditProduct(id) {
    load(_ACTION_Product_Edit + "?id=" + id, ContentBody, _ACTION_Product_Edit);
}

function gotoDetailProduct(id) {
    load(_ACTION_Product_Detail + "?id=" + id, ContentBody, _ACTION_Product_Detail);
}

function showDeleteProductModal(ele) {
    showConfirm(
        "Delete",
        "Do you want to delete this product?",
        "danger",
        "delete-outline",
        function () {
            loadUrl(_ACTION_Product_Delete, data => {
                showToast(data.message, data.level)
                if (data.success) {
                    load(_ACTION_Product_List + location.search, _ProductTable, null)
                }
            }, null, "POST", {id: ele.closest("tr").data("id")})
        }
    )
}

function submitProductForm(ele) {

    let isAdd = ele.data('is-add');

    let mForm = ele.closest('form');
    let edtId = mForm.find("#id");
    let edtCategoryId = mForm.find("#categoryId")
    let edtCode = mForm.find("#code")
    let edtName = mForm.find("#name")
    let edtDesc = mForm.find("#desc")
    let edtPrice = mForm.find("#price")
    let edtImage = mForm.find("#image")

    let data = new FormData();
    let catId = edtCategoryId.data('id');
    if (typeof catId == "undefined") {
        catId = -1;
    }
    data.append('id', edtId.val().trim())
    data.append('cat_id', catId)
    data.append('code', edtCode.val().trim())
    data.append('price', edtPrice.val().trim())
    data.append('name', edtName.val().trim())
    data.append('desc', edtDesc.val().trim())
    data.append("image", edtImage[0].files[0]);

    let success = data => {
        if (!data) return;
        if (data.errors) {
            edtCategoryId.next().text(data.errors.cat_id ? data.errors.cat_id : "")
            edtCode.next().text(data.errors.code ? data.errors.code : "")
            edtName.next().text(data.errors.name ? data.errors.name : "")
            edtDesc.next().text(data.errors.desc ? data.errors.desc : "")
            edtPrice.next().text(data.errors.price ? data.errors.price : "")
            edtImage.closest(".drop-zone").next().text(data.errors.image ? data.errors.image : "")
        } else {
            if (isAdd) {
                clearFormElements(mForm)
                const dropZoneElement = mForm.find(".drop-zone");
                dropZoneElement.children("span, div").remove();
                dropZoneElement.append(
                    `<span class='drop-zone__prompt'>
                        <i class='fas fa-cloud-upload-alt'></i><br />Browse to upload file
                    </span>`
                );
            }
            mForm.find("span.error").text("")
            showToast(data.message, data.level)
        }
    }
    const $param = {
        type: "POST",
        url: isAdd ? _ACTION_Product_Add : _ACTION_Product_Edit,
        data: data,
        success: success,
        error: null
    };

    adapter_ajax_with_file($param)

}



