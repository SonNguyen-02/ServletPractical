const _CategoryTable = '#tableCategory';
const _ACTION_Category_Index = _PREFIX + "/category"
const _ACTION_Category_Add = _ACTION_Category_Index + "/add"
const _ACTION_Category_Edit = _ACTION_Category_Index + "/edit"
const _ACTION_Category_Delete = _ACTION_Category_Index + "/delete"
const _ACTION_Category_Detail = _ACTION_Category_Index + '/detail'
const _ACTION_Category_List = _ACTION_Category_Index + '/list'

function CategoryIndex() {
    prepareKeyboardAction('#searchCategory', _CategoryTable, _ACTION_Category_Index)
    prepareMouseAction('#contentPager a[href]', _CategoryTable, _ACTION_Category_Index);
}


function gotoAddCategory() {
    load(_ACTION_Category_Add, ContentBody, _ACTION_Category_Add);
}

function gotoEditCategory(id) {
    load(_ACTION_Category_Edit + "?id=" + id, ContentBody, _ACTION_Category_Edit);
}

function gotoDetailCategory(id) {
    load(_ACTION_Category_Detail + "?id=" + id, ContentBody, _ACTION_Category_Detail);
}

function showDeleteCategoryModal(ele) {
    showConfirm(
        "Delete",
        "Do you want to delete this category?",
        "danger",
        "delete-outline",
        function () {
            loadUrl(_ACTION_Category_Delete, data => {
                showToast(data.message, data.level)
                if (data.success) {
                    load(_ACTION_Category_List + location.search, _CategoryTable, null)
                }
            }, null, "POST", {id: ele.closest("tr").data("id")})
        }
    )
}

function submitCategoryForm(ele) {
    let isAdd = ele.data('is-add');
    let mForm = ele.closest('form');
    let edtId = mForm.find("#id");
    let edtName = mForm.find("#name");
    let edtDesc = mForm.find("#desc");

    let data = {
        id: edtId.val(),
        name: edtName.val(),
        desc: edtDesc.val()
    }

    let success = data => {
        if(!data) return;
        if (data.errors) {
            edtName.next().text(data.errors.name ? data.errors.name : "")
            edtDesc.next().text(data.errors.desc ? data.errors.desc : "")
        } else {
            if (isAdd) clearFormElements(mForm)
            mForm.find("span.error").text("")
            showToast(data.message, data.level)
        }
    }

    if (isAdd) {
        loadUrl(_ACTION_Category_Add, success, null, "POST", data)
    } else {
        loadUrl(_ACTION_Category_Edit, success, null, "POST", data)
    }
}
