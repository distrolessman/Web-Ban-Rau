<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- Start header section -->
<jsp:include page="./header/header.jsp" flush="true"/>

<div class="content-wrapper">
    <div class="container-fluid">
        <div class="row mt-3">
            <div class="col-lg-12">
                <div class="card">
                    <div class="card-body">
                        <div class="card-title">Sửa sản phẩm</div>
                        <hr>
                        <form id="form-product" method="post" action="${pageContext.request.contextPath}/admin/product/edit">
                            <div class="form-group">
                                <label for="input-1">Mã sản phẩm</label>
                                <input type="text" class="form-control" readonly="readonly" id="product-sku"
                                       placeholder="Mã sản phẩm" name="product-sku" value="${product.id}">
                            </div>
                            <div class="form-group">
                                <label for="input-1">Tên sản phẩm</label>
                                <input type="text" class="form-control" id="input-1" placeholder="Tên sản phẩm"
                                       name="product-name" value="${product.name}">
                            </div>
                            <div class="form-group">
                                <label for="input-2">Chuyên mục</label>
                                <div>
                                    <select class="form-control valid" id="input-6" name="product-cate"
                                            aria-invalid="false">
                                        <c:forEach items="${catelist}" var="cate">
                                            <option value="${cate.id }" selected="selected">${cate.name }</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="input-1">Ngày</label>
                                <fmt:formatDate value="${product.created}" var="productCreated" pattern="yyyy-MM-dd"/>
                                <input type="date" class="form-control" id="input-1" placeholder="Ngày đăng"
                                       name="product-day" value="${productCreated}">
                            </div>
                            <div class="form-group">
                                <label for="input-1">Giá</label>
                                <input type="text" pattern="[0-9]" class="form-control" id="input-1" placeholder="Giá"
                                       name="product-price" value="${product.price}" type="number"/>
                            </div>
                            <div class="form-group">
                                <label for="input-2">Trạng thái</label>
                                <div>
                                    <select class="form-control valid" id="input-6" name="product-status" required
                                            aria-invalid="false">
                                        <option value="1">Còn hàng</option>
                                        <option value="0">Hết hàng</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="input-2">Giảm giá</label>
                                <div class="input-group">
                                    <input type="text" class="form-control" placeholder="Giảm ... %"
                                           name="product-discount" value="${product.discount}">
                                    <div class="input-group-append">
                                        <button class="btn btn-light" type="button">%</button>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="input-2" class="col-form-label">Mô tả</label>
                                <div>
                                    <textarea class="form-control" rows="4" id="input-17"
                                              name="product-desc">${product.description}</textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="input-2" class="col-form-label">Nội dung</label>
                                <div>
                                    <textarea class="form-control" rows="4" id="input-17"
                                              name="product-content">${product.content}</textarea>
                                </div>
                            </div>
                            <label for="input-1">Ảnh đại diện</label>
                            <div class="form-group">
                                <img src="${product.image_link}" alt="farm products" height="240px" width="470px"
                                     id="product-image-present">
                                <input type="file" id="product-image-input">
                                <input type="text" id="product-image-name" value="${product.image_link}" name="product-image" hidden="true">
                            </div>
                            <div class="form-footer">
                                <button class="btn btn-danger"><a
                                        href="${pageContext.request.contextPath}/admin/product/list">Hủy</a></button>
                                <button type="button" id="request-url-upload" onclick="requestUploadURL()" class="btn btn-success">Cập nhật</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="overlay toggle-menu"></div>
    </div>
</div>
<script>
    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('#product-image-present').attr('src', e.target.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }

    $("#product-image-input").change(function () {
        readURL(this);
    });

    function requestUploadURL(){
        var productId = document.getElementById('product-sku').value;
        const file = document.querySelector('#product-image-input').files[0];
        var link = document.getElementById('product-image-name').value.replace('https://firebasestorage.googleapis.com/v0/b/vegetables-shop-2c68e.appspot.com/o/', '')
            .replace('?alt=media', '')
        if(!file){
            document.getElementById('product-image-name').value = link
            $('#form-product').submit();
            return;
        }
        $.ajax({
            url: `/upload-image?path=products&id=\${productId}`,
            type: 'post',
            contentType: 'application/json',
            dataType: 'json'
        }).then(data => uploadImage(data))
    }
    function uploadImage(data){
        let fileName = data.fileName;
        const file = document.querySelector('#product-image-input').files[0];
        let blob = new Blob([file], {type: 'image/png'})
        $.ajax({
            url: data.url,
            type: 'PUT',
            data: file,
            processData: false,
            contentType: false,
        }).then(data => {
            document.getElementById('product-image-name').value = fileName;
            $('#form-product').submit();
        })
    }
</script>

<jsp:include page="./footer/footer.jsp" flush="true"/>