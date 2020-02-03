<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<script src="/assets/lib/jquery/dist/jquery.js"></script>
<div class="span3" id="sidebar">
	<ul id="sidebarUl"
		class="nav nav-list bs-docs-sidenav nav-collapse collapse">
		<li id="adminLi"><a href="/admin/"><i
				class="icon-chevron-right"></i> Ȩ</a></li>
		<li id="statsLi"><a href="/admin/stats"><i
				class="icon-chevron-right"></i> ���</a></li>
		<li id="adminLi"><a href="/admin/user"><i
				class="icon-chevron-right"></i> ���� ����</a></li>
		<li id="adminLi"><a href="/admin/report"><i
				class="icon-chevron-right"></i> �Ű�</a></li>
		<li><a href="#"><span class="badge badge-info pull-right">${countStatVol.memberVolume }</span>
				����</a></li>
		<li><a href="#"><span class="badge badge-success pull-right">${countStatVol.dealVolume }</span>
				�ŷ���</a></li>
		<li><a href="#"><span class="badge badge-warning pull-right">${countStatVol.chatVolume }</span>
				ä�÷�</a></li>
		<li><a href="#"><span
				class="badge badge-important pull-right">${countStatVol.reportVolume }</span>
				�Ű�</a></li>
	</ul>
</div>

<script>
    var pathname = window.location.pathname

    cateNum = pathname.substr(7);
    if (cateNum === "") {
        cateNum = 'admin';
    }

    var id = document.getElementById(cateNum + "Li")

    $(document).ready(function () {
        $(id).addClass('active');
    });
</script>