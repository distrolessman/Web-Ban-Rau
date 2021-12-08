<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- Start header section -->
<jsp:include page="./header/header.jsp" flush="true"/>
<div class="content-wrapper">
    <div class="container-fluid">
        <div class="row mt-3">
            <div class="col-lg-8">
                <div class="card">
                    <div class="card-body">
                        <div class="card-title">Sửa tin tức</div>
                        <hr>
                        <form id="form-edit-boardnew" action="${pageContext.request.contextPath}/admin/new/edit" method="post">
                            <div class="form-group">
                                <label for="input-1">ID</label>
                                <input type="text" class="form-control" readonly id="boardnewId" placeholder="ID"
                                       value="${boardnew.id}" name="new-id">
                            </div>
                            <div class="form-group">
                                <label for="input-2">Tên tin tức</label>
                                <input type="text" class="form-control" id="input-2" placeholder="Tên tin tức"
                                       value="${boardnew.title}" name="new-title">
                            </div>
                            <div class="form-group">
                                <label for="input-3">Nội dung</label>
                                <textarea class="form-control" rows="4" id="input-17"
                                          name="new-content">${boardnew.content}</textarea>
                            </div>
                            <div class="form-group">
                                <label for="input-4">Người đăng</label>
                                <div>
                                    <select class="form-control valid" id="input-4" name="new-author">
                                    <option id="NULL">NULL</option>
                                    <option id="Thịnh Dương">Thịnh Dương</option>
                                    <option id="Lê Thạch">Lê Thạch</option>
                                    <option id="Lan Ngọc">Lan Ngọc</option>
                                    <option id="Kim Vy">Kim Vy</option>
                                    </select>
                                </div>
                                <script>
                                    $('[id=\'${boardnew.author}\']').attr('selected', 'selected')
                                </script>
                            </div>
                            <div class="form-group">
                                <fmt:formatDate value="${boardnew.created}" var="boardnewCreated" pattern="yyyy-MM-dd"/>
                                <label for="input-5">Ngày đăng</label>
                                <input type="date" class="form-control" id="input-5" value="${boardnewCreated}"
                                       name="new-created">
                            </div>
                            <label for="input-2">Hình ảnh</label>
                            <div class="form-group">
                                <img src="${boardnew.image_link}" alt="farm products" height="240px" width="470px"
                                     id="boardnew-image-present">
                                <input type="file" id="boardnew-image-input">
                                <input type="text" id="board-image-name" name="new-image_link" hidden="true">
                            </div>
                            <div class="form-footer">
                                <button class="btn btn-danger"><a
                                        href="${pageContext.request.contextPath}/admin/product/list">Hủy</a></button>
                                <button type="button" class="btn btn-success" id="request-url-upload" onclick="requestUploadURL()">Cập nhật</button>
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
                $('#boardnew-image-present').attr('src', e.target.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }

    $("#boardnew-image-input").change(function () {
        readURL(this);
    });

    function requestUploadURL(){
        var boardnewId = document.getElementById('boardnewId').value;
        $.ajax({
            url: `/upload-image?path=boardnews&id=\${boardnewId}`,
            type: 'post',
            contentType: 'application/json',
            dataType: 'json'
        }).then(data => uploadImage(data))
    }
    function uploadImage(data){
        let fileName = data.fileName;
        const file = document.querySelector('#boardnew-image-input').files[0];
        let blob = new Blob([file], {type: 'image/png'})
        $.ajax({
            url: data.url,
            type: 'PUT',
            data: file,
            processData: false,
            contentType: false,
        }).then(data => {
            document.getElementById('board-image-name').value = fileName;
            $('#form-edit-boardnew').submit();
        })
    }
</script>
<jsp:include page="./footer/footer.jsp" flush="true"/>