<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>관리자 페이지</title>
<link href='/resources/css/adminPage.css' rel="stylesheet"
	type="text/css">
<script src="/resources/jQuery/jQuery-2.1.3.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#deleteAccount').on('click', function() {
			var deleteList = new Array;
			var i = 0;
			$('input:checkbox[name="accountChoice"]').each(function() {
				if (this.checked) {//checked 처리된 항목의 값
					deleteList[i++] = this.value;	
				}
			});
			alert("계정이 삭제되었습니다.");
			document.adminForm.accountList.value = deleteList;
			document.adminForm.action = "/admin/deleteAccount";
			document.adminForm.submit();
		});
		$('#sellerApproval').on('click', function() {
			var sellerList = new Array;
			var i = 0; var failFlag = 0;
			var accountRole = "";
			$('input:checkbox[name="accountChoice"]').each(function() {
				if (this.checked) {//checked 처리된 항목의 값
					accountRole = $(this).next().val();
					if(accountRole != 'SELLER_READY'){
						alert("판매준비중인 권한만 판매승인가능합니다.");
						failFlag = 1;
						return false;
					}
					sellerList[i++] = this.value;	
				}
			});
			if(failFlag != 1){
				alert("판매자 승인이 되었습니다.");
				document.adminForm.accountList.value = sellerList;
				document.adminForm.action = "/admin/sellerApproval";
				document.adminForm.submit();
			}
		});
	});
</script>
</head>
<body>
	<form method="post" name="adminForm">
		<div class="divTable">
			<div class="divTableBody">
				<div class="divTableRow">
					<div class="divTableCell">아이디</div>
					<div class="divTableCell">권한</div>
					<div class="divTableCell">계정선택</div>
				</div>
<c:forEach var="account" items="${accountList }">
				<div class="divTableRow">
					<div class="divTableCell">${account.id }</div>
					<div class="divTableCell">${account.role }</div>
					<div class="divTableCell">
						<input type="checkbox" name="accountChoice" value="${account.id }">
						<input type="hidden" id="accountRole" value="${account.role }">
					</div>
				</div>
</c:forEach>
				<input type="hidden" name="accountList">
			</div>
		</div>
	</form>
	<div class="adminButton">
		<input type="button" id="deleteAccount" value="계정삭제"> <input
			type="button" id="sellerApproval" value="판매자승인">
	</div>
</body>
</html>