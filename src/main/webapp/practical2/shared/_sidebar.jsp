<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10/20/2022
  Time: 10:02 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<nav class="sidebar sidebar-offcanvas custom-scroll" id="sidebar">
  <ul class="nav custom-scroll">
    <li class="nav-item nav-profile border-bottom">
      <a class="nav-link flex-column">
        <div class="nav-profile-image">
          <img src="${pageContext.request.contextPath}/public/images/user_placeholder.png" alt="profile"/>
          <!--change to offline or busy as needed-->
        </div>
        <div class="nav-profile-text d-flex ms-0 mb-3 flex-column">
          <span class="font-weight-semibold mb-1 mt-2 text-center">Hello Admin</span>
        </div>
      </a>
    </li>
    <li class="nav-item">
      <a class="nav-link d-block" href="">
        <img class="sidebar-brand-logo" src="${pageContext.request.contextPath}/public/images/logo.svg"
             alt=""/>
        <img class="sidebar-brand-logomini"
             src="${pageContext.request.contextPath}/public/images/logo-mini.svg" alt=""/>
      </a>
      <form class="d-flex align-items-center pt-2 pb-3" action="#">
        <div class="input-group">
          <div class="input-group-prepend">
            <i class="input-group-text border-0 mdi mdi-magnify"></i>
          </div>
          <input type="text" class="form-control border-0" placeholder="Search"/>
        </div>
      </form>
    </li>
    <li class="nav-item nav-item-hover">
      <a class="nav-link" href="">
        <i class="mdi mdi-compass-outline menu-icon"></i>
        <span class="menu-title">Dashboard</span>
      </a>
    </li>
    <li class="nav-item nav-item-hover">
      <a class="nav-link" href="">
        <i class="mdi mdi-application-parentheses-outline menu-icon"></i>
        <span class="menu-title">Test</span>
      </a>
    </li>
    <li class="nav-item nav-item-hover">
      <a class="nav-link" href="">
        <i class="mdi mdi-account-details menu-icon"></i>
        <span class="menu-title">Accounts</span>
      </a>
    </li>
    <li class="nav-item nav-item-hover">
      <a class="nav-link" href="">
        <i class="mdi mdi-chart-bar menu-icon"></i>
        <span class="menu-title">Report</span>
      </a>
    </li>
    <li class="nav-item nav-item-hover d-lg-none">
      <a class="nav-link" onclick="SignOut()">
        <i class="mdi mdi-logout-variant menu-icon"></i>
        <span class="menu-title">Logout</span>
      </a>
    </li>
  </ul>
</nav>
