<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:url value="/view/client/assets" var="url"/>

<!-- Start header section -->
<jsp:include page="./header/mainHeader.jsp" flush="true"/>
<!-- / header section -->

<!-- content -->
<!-- catg header banner section -->
<section id="aa-catg-head-banner">
    <img src="${pageContext.request.contextPath}/view/client/assets/images/banner-product.png" alt="banner sản phẩm">
    <div class="aa-catg-head-banner-area">
        <div class="container">
            <div class="aa-catg-head-banner-content">
                <h2>Chi tiết sản phẩm</h2>
                <ol class="breadcrumb">
                    <li><a href="${pageContext.request.contextPath}">Trang chủ</a></li>
                    <li><a href="${pageContext.request.contextPath}/view/client/product">Sản phẩm</a></li>
                    <li style="color:#fff">${productDetails.name }</li>
                </ol>
            </div>
        </div>
    </div>
</section>
<!-- / catg header banner section -->

<!-- product category -->
<section id="aa-product-details">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="aa-product-details-area">
                    <div class="aa-product-details-content">
                        <div class="row">
                            <!-- Modal view slider -->
                            <div class="col-md-5 col-sm-5 col-xs-12">
                                <div class="aa-product-view-slider">
                                    <div id="demo-1" class="simpleLens-gallery-container">
                                        <div class="simpleLens-container">
                                            <div class="simpleLens-big-image-container"><a
                                                    data-lens-image="${productDetails.image_link}"
                                                    class="simpleLens-lens-image"><img
                                                    src="${productDetails.image_link}"
                                                    class="simpleLens-big-image"></a></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- Modal view content -->

                            <div class="col-md-7 col-sm-7 col-xs-12">
                                <div class="aa-product-view-content">
                                    <h3>${productDetails.name }</h3>
                                    <div class="aa-price-block">
                                        <c:choose>
                                            <c:when test="${productDetails.discount == 0}">
                                                <span class="aa-product-price"><fmt:formatNumber value="${productDetails.price}" type="number"/> VNĐ</span><span
                                                    class="aa-product-price"></span>
                                            </c:when>
                                            <c:otherwise>
                                                <c:set var="discountPrice"
                                                       value="${productDetails.price * (1.0 - productDetails.discount/100.0)}"></c:set>
                                                <span class="aa-product-price"><fmt:formatNumber value="${discountPrice}" type="number"/> VNĐ</span>
                                                <span class="aa-product-price"><del><fmt:formatNumber value="${productDetails.price}" type="number"/> VNĐ</del></span>
                                            </c:otherwise>
                                        </c:choose>
                                        <p class="aa-product-avilability">Tình trạng: <span>
                                        <c:choose>
                                            <c:when test="${productDetails.status == 1}">
                                                <c:out value="Còn hàng"/>
                                            </c:when>
                                            <c:otherwise>
                                                <c:out value="Hết hàng"/>
                                            </c:otherwise>
                                        </c:choose>
                                        </span></p>
                                    </div>
                                    <p>${productDetails.description}</p>
                                    <div class="aa-prod-quantity">
                                        <p class="aa-prod-category">
                                            <strong>Danh mục:</strong> <a
                                                href="product.jsp">${productDetails.catalog.name}</a>
                                        </p>
                                    </div>
                                    <div class="aa-prod-view-bottom">
                                        <a class="aa-add-to-cart-btn"
                                           href="${pageContext.request.contextPath}/view/client/add-cart?product-id=${productDetails.id}">Thêm
                                            vào giỏ hàng</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="aa-product-details-bottom">
                        <ul class="nav nav-tabs aa-products-tab" id="myTab2">
                            <li class="active"><a href="#description" data-toggle="tab" class="">Mô tả</a></li>
                            <li><a href="#review" data-toggle="tab">Đánh giá</a></li>
                        </ul>
                        <!-- Tab panes -->
                        <div class="tab-content">
                            <div class="tab-pane fade in active" id="description">
                                <p>${productDetails.content }</p>
                            </div>
                            <div class="tab-pane fade " id="review">
                                <div class="aa-product-review-area">
                                    <ul class="aa-review-nav">
                                        <c:forEach items="${listReviewsByProduct}" var="review">
                                            <li>
                                                <div class="media">
                                                    <div class="media-body">
                                                        <h4 class="media-heading"><strong>${review.name}</strong> -
                                                            <span>${review.created}</span></h4>
                                                        <div class="aa-product-rating">
                                                            <span class="fa fa-star"></span>
                                                            <span class="fa fa-star"></span>
                                                            <span class="fa fa-star"></span>
                                                            <span class="fa fa-star"></span>
                                                            <span class="fa fa-star"></span>
                                                        </div>
                                                        <p>${review.content}</p>
                                                    </div>
                                                </div>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                    <h4>Thêm đánh giá</h4>
                                    <!-- review form -->
                                    <form action="${pageContext.request.contextPath}/view/client/review?id=${productDetails.id}"
                                          method="post" class="aa-review-form">
                                        <div class="form-group">
                                            <label for="name">Tên</label>
                                            <input type="text" class="form-control" id="name" placeholder="Name"
                                                   name="name">
                                        </div>
                                        <div class="form-group">
                                            <label for="email">Email</label>
                                            <input type="email" class="form-control" id="email"
                                                   placeholder="example@gmail.com" name="email">
                                        </div>
                                        <div class="form-group">
                                            <label for="content">Đánh giá của bạn</label>
                                            <textarea class="form-control" rows="3" id="content"
                                                      name="content"></textarea>
                                        </div>
                                        <button type="submit" class="btn btn-default aa-review-submit">Gửi</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Related product -->
                    <div class="aa-product-related-item">
                        <h3>SẢN PHẨM LIÊN QUAN</h3>
                        <ul class="aa-product-catg aa-related-item-slider">
                            <!-- start single product item -->
                            <c:forEach items="${listProductsSameCategory}" var="product">
                                <li>
                                    <figure>
                                        <a class="aa-product-img"
                                           href="${pageContext.request.contextPath}/view/client/product-detail?id=${product.id}"><img
                                                src="${product.image_link}"
                                                alt=""></a>
                                        <a class="aa-add-card-btn"
                                           href="${pageContext.request.contextPath}/view/client/add-cart?product-id=${product.id}"><span
                                                class="fa fa-shopping-cart"></span>Thêm vào giỏ hàng</a>
                                        <figcaption>
                                            <h4 class="aa-product-title"><a
                                                    href="${pageContext.request.contextPath}/view/client/product-detail?id=${product.id}">${product.name}</a>
                                            </h4>
                                            <c:choose>
                                                <c:when test="${product.discount == 0}">
                                                    <span class="aa-product-price"><fmt:formatNumber value="${product.price}" type="number"/> <u>VNĐ</u></span><span
                                                        class="aa-product-price"></span>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:set var="discountPrice"
                                                           value="${product.price * (1.0 - product.discount/100.0)}"></c:set>
                                                    <span class="aa-product-price"><fmt:formatNumber value="${discountPrice}" type="number"/> VNĐ</span>
                                                    <span class="aa-product-price"><del><fmt:formatNumber value="${product.price}" type="number"/> VNĐ</del></span>
                                                </c:otherwise>
                                            </c:choose>
                                        </figcaption>
                                    </figure>
                                    <c:if test="${product.discount != 0}">
                                        <!-- product badge -->
                                        <span class="aa-badge aa-sale">- ${product.discount}%</span>
                                    </c:if>
                                </li>
                            </c:forEach>
                            <!-- start single product item -->
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- / product category -->
<!-- end content-->

<!-- footer-->
<jsp:include page="./footer/footer.jsp" flush="true"/>
<!-- end footer-->


