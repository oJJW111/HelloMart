$(document).ready(function(){

});

function makeDigit(num){
	if(num<10){
		num = "0"+num;
	}
	else{
		num = ""+num;
	}
	return num;
}
var API_SUCCESS = 'S000001';
var API_FAILED_LOGIN_NO_ID ='E001001';
var API_FAILED_LOGIN_NO_PASSWORD ='E001002';
var API_SUCCESS_LOGIN_NO_AUTH = 'S001001';
var SUCCESS = '0';
function requestApi(/*String*/_method, /*String*/_uri, /*Object*/_data, /*function*/_callback, _mimeType)
{
	if(_mimeType == "data"){
		$.ajax({
		    type: _method,
		    url: 'http://' + location.host + '/api/async' + _uri,
		    data: _data,
		    mimeType: "multipart/form-data",
		    contentType: false,
		    casch: false,
		    processData: false,
		    dataType: 'json',
		    success: function(data, textStatus, jqXHR) {
		    	if((typeof(_callback)).toLowerCase() == 'function') {
		    		_callback(data);
		    	}
		    },
		    error: function(jqXHR, textStatus, errorThrown) {}
		});
	}
	else{
		$.ajax({
		    type: _method,
		    url: 'http://' + location.host + '/api/async' + _uri,
		    data: _data,
		    beforeSend: function(request) {
		        request.setRequestHeader('Accept', 'application/json');
		        request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');
		    },
		    success: function(data, textStatus, jqXHR) {
		    	if((typeof(_callback)).toLowerCase() == 'function') {
		    		_callback(data);
		    	}
		    },
		    error: function(jqXHR, textStatus, errorThrown) {}
		});
	}
}
function requestAdminApi(/*String*/_method, /*String*/_uri, /*Object*/_data, /*function*/_callback, _mimeType)
{
	if(_mimeType == "data"){
		$.ajax({
		    type: _method,
		    url: 'http://' + location.host + '/Admin/common/api' + _uri,
		    data: _data,
		    mimeType: "multipart/form-data",
		    contentType: false,
		    casch: false,
		    processData: false,
		    dataType: 'json',
		    success: function(data, textStatus, jqXHR) {
		    	if((typeof(_callback)).toLowerCase() == 'function') {
		    		_callback(data);
		    	}
		    },
		    error: function(jqXHR, textStatus, errorThrown) {}
		});
	}
	else{
		$.ajax({
		    type: _method,
		    url: 'http://' + location.host + '/Admin/common/api' + _uri,
		    data: _data,
		    beforeSend: function(request) {
		        request.setRequestHeader('Accept', 'application/json');
		        request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');
		    },
		    success: function(data, textStatus, jqXHR) {
		    	if((typeof(_callback)).toLowerCase() == 'function') {
		    		_callback(data);
		    	}
		    },
		    error: function(jqXHR, textStatus, errorThrown) {}
		});
	}
}
//�대찓�� 寃�利� �⑥닔 由ы꽩�� false �쇰㈃ invalid email
function checkEmail(email){
	var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	if (!filter.test(email)) {
		return false;
	}
	else{
		return true;
	}
}
function apiList(name){
	var putUrl = "/";
	var list = {
			selectNoticeList: putUrl+"selectNoticeList.php",
			selectEventList: putUrl+"selectEventList.php",
			insertQna: putUrl+"insertQna.php",
			insertReserve: putUrl+"insertReserve.php",
					
	};
	
	if(list[name]){
		return list[name];
	}
	else{
		alert("�대떦 �섏씠吏�瑜� 李얠쓣�� �놁뒿�덈떎.");
		return;
	}
}
function pageMove(page, pageOpt){
	var url = location.protocol+"//"+location.host;
	if(pageOpt){
		location.href = url+page;
	}
	else{
		location.href = url+pageList(page);
	}

}

function pageSubmit(page, frm, method, enctype){
	if(enctype){
		frm.attr({enctype: enctype});
	}
	var url = location.protocol+"//"+location.host+"/mall";
	url = url+pageList(page);
	method = method || 'GET';
	frm.attr({action: url, method: method}).submit();
}
function pageList(page){
	var putUrl = "/";
	var putUrl_m = "/m/";
	var data = {
			notice: putUrl+"notice.php",
			notice_detail: putUrl+"notice_detail.php",
			event: putUrl+"event.php",
			event_detail: putUrl+"event_detail.php",
			sub: putUrl+"sub.php",
			sub_detail: putUrl+"sub_detail.php",
			m_sub: putUrl_m+"sub.php",
			m_sub_detail: putUrl_m+"sub_detail.php",
			m_event_detail: putUrl_m+"event_detail.php",
	};
	if(data[page]){
		return data[page];
	}
	else{
		alert("�대떦 �섏씠吏�瑜� 李얠쓣�� �놁뒿�덈떎.");
		return;
	}
}
function setNewPage(page,target, total, count, pageId){
	
	var total = total || $('#total').val();
	var count = count || $('#count').val();
	var pageObj = (pageId)?$('#'+pageId):$('#page');
	var max_page = Math.ceil(total/count);
	if(page<1){
		alert("泥ル쾲吏� �섏씠吏� �낅땲��.");
		return;
	}
	else if(page > max_page){
		alert("留덉�留� �섏씠吏� �낅땲��.");
		return;
	}
	
	pageObj.val(page);
	pageSubmit(target, $('.frm_page'), 'GET');
	
}

function scrollEvtX(){
	$(document).on("mousewheel.disableScroll DOMMouseScroll.disableScroll touchmove.disableScroll", function(e) {
        e.preventDefault();
        return;
    });
    $(document).on("keydown.disableScroll", function(e) {
        var eventKeyArray = [32, 33, 34, 35, 36, 37, 38, 39, 40];
        for (var i = 0; i < eventKeyArray.length; i++) {
            if (e.keyCode === eventKeyArray [i]) {
                e.preventDefault();
                return;
            }
        }
    });
}
function scrollEvtO(){
	 $(document).off(".disableScroll");
}