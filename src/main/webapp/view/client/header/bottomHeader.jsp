<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:url value="/view/client/assets" var="url"/>
<div class="aa-header-bottom">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="aa-header-bottom-area">
                    <!-- logo  -->
                    <div class="aa-logo">
                        <a href="${pageContext.request.contextPath}/"><img src="${url}/images/LTS_Logo.png"
                                                                           style="height: 100px" alt="logo img"
                                                                           width="100%"></a>
                    </div>
                    <!-- / logo  -->
                    <!-- Shipping service -->
                    <div class="aa-shipping-box">
                        <a class="aa-shipping-boxsmall">
                            <span class="aa-shipping-boxsmall-icon fas fa-shipping-fast"></span>
                            <span class="aa-shipping-box-text">
                          <div class="aa-shipping-title">Miễn phí vận chuyển</div>
                      <div class="">Khu vực TP HCM</div>
                      </span>

                        </a>
                        <a class="aa-shipping-boxsmall">
                            <span class="aa-shipping-boxsmall-icon fas fa-phone"></span>
                            <span class="aa-shipping-box-text">
                          <div class="aa-shipping-title">Hỗ trợ: 0865.765.201</div>
                          <div class="">Tư vấn 24/7 miễn phí</div>
                      </span>

                        </a>
                        <a class="aa-shipping-boxsmall">
                            <span class="aa-shipping-boxsmall-icon fas fa-shipping-fast"></span>
                            <span class="aa-shipping-box-text">
                          <div class="aa-shipping-title">Giao hàng toàn quốc</div>
                      <div class="">Đảm bảo uy tín, chất lượng</div>
                      </span>

                        </a>
                    </div>
                    <!-- cart box -->
                    <div class="aa-cartbox">
                        <a class="aa-cart-link" href="${pageContext.request.contextPath}/view/client/cart">
                            <span class="fas fa-cart-arrow-down"></span>
                            <span class="aa-cart-title">GIỎ HÀNG</span>

                            <c:if test="${length_order != NULL}">
                                <span class="aa-cart-notify">${length_order}</span>
                            </c:if>
                        </a>
                        <div class="aa-cartbox-summary">
                            <ul class="scroll-product">
                                <c:forEach items="${order.items}" var="item">
                                    <li>
                                        <a class="aa-cartbox-img"
                                           href="${pageContext.request.contextPath}/view/client/cart"><img
                                                src="${item.product.image_link}"
                                                alt="img"></a>
                                        <div class="aa-cartbox-info">
                                            <h4>
                                                <a href="${pageContext.request.contextPath}/view/client/cart">${item.product.name}</a>
                                            </h4>
                                            <p><del><fmt:formatNumber value="${item.product.price}" type="number"/> VNĐ</del></p>
                                            <c:set var="itemProductDiscount"
                                                   value="${item.qty * (item.product.price * (1.0 - item.product.discount/100.0))}"></c:set>
                                            <p><fmt:formatNumber value="${itemProductDiscount}" type="number"/> VNĐ</p>
                                        </div>
                                    </li>
                                </c:forEach>
                            </ul>
                            <div class="total-detailproduct">
                  		<span class="aa-cartbox-total-title">
                        <b>Tổng:</b>
                      </span>
                                <span class="aa-cartbox-total-price">
                        <fmt:formatNumber value="${sumprice}" type="number"/> VNĐ
                      </span>
                            </div>
                            <a class="aa-cartbox-checkout aa-primary-btn"
                               href="${pageContext.request.contextPath}/view/client/cart">Chi tiết</a>
                            <a class="aa-cartbox-checkout aa-primary-btn"
                               href="${pageContext.request.contextPath}/view/client/checkout">Thanh toán</a>
                        </div>
                    </div>
                    <!-- / cart box -->
                </div>
            </div>
        </div>
    </div>
</div>
