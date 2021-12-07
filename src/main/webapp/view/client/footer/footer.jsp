<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="/view/client/assets" var="url"/>

<!-- footer -->
<footer id="aa-footer">
    <!-- footer bottom -->
    <div class="aa-footer-top">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="aa-footer-top-area">
                        <div class="row">
                            <div class="col-md-4 col-sm-6">
                                <div class="aa-footer-widget">
                                    <a href="${pageContext.request.contextPath}/"><img src="${url}/images/Store.png"
                                                                                       alt="logo img" width="250px"
                                                                                       style="padding-left: 80px"></a>
                                    <ul class="aa-footer-nav">
                                        <li><p style="color:black; text-align: justify; width: 95%">Là nơi chuyên buôn
                                            bán tất cả các mặt hàng về nông sản chất lượng. Chúng tôi luôn đặt tiêu chí
                                            về chất lượng và uy tín lên hàng đầu.</p></li>
                                    </ul>
                                </div>
                            </div>
                            <div class="col-md-4 col-sm-6">
                                <div class="aa-footer-widget">
                                    <a href="${pageContext.request.contextPath}/"><img
                                            src="${url}/images/TULI_footer.png" alt="logo img" width="180px"
                                            style="padding-left: 45px; margin-left: 60px;"></a>
                                    <div class="aa-footer-widget">
                                        <h3>HỖ TRỢ KHÁCH HÀNG</h3>
                                        <ul class="aa-footer-nav">
                                            <li><a href="${pageContext.request.contextPath}/view/client/order">Hướng dẫn
                                                đặt hàng</a></li>
                                            <li><a href="${pageContext.request.contextPath}/view/client/payment">Hướng
                                                dẫn thanh toán</a></li>
                                            <li><a href="${pageContext.request.contextPath}/view/client/security">Chính
                                                sách bảo mật thông tin</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4 col-sm-6">
                                <div class="aa-footer-widget">
                                    <a href="${pageContext.request.contextPath}/"><img
                                            src="${url}/images/TULI_footer.png" alt="logo img" width="130px"
                                            style="padding-left: 0px; margin-left: 40px;"></a>
                                    <div class="aa-footer-widget">
                                        <h3>LIÊN HỆ</h3>
                                        <address>
                                            <p>Số 1 - Võ Văn Ngân - TP.Thủ Đức - TP HCM</p>
                                            <p><span class="fa fa-phone"></span>0865.765.201</p>
                                            <p><span class="fa fa-envelope"></span>ng.baotran.2k1@gmail.com</p>
                                        </address>
                                        <div class="aa-footer-social">
                                            <a href="#"><span class="fab fa-facebook"></span></a>
                                            <a href="#"><span class="fab fa-youtube"></span></a>
                                            <a href="#"><span class="fab fa-instagram"></span></a>
                                            <a href="#"><span class="fab fa-skype"></span></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- footer-bottom -->
</footer>
<!-- / footer -->

</body>
<jsp:include page="script.jsp" flush="true"/>

</html>
