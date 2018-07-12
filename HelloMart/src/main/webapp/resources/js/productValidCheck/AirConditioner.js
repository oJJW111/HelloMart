function productValidCheck(specEngNameList, specKorNameList){
	var flag = 0;
	var SquareMeasure = $("#"+specEngNameList[0]).val();
	var Cooling = $("#"+specEngNameList[1]).val();
	var Dehumidification = $("#"+specEngNameList[2]+" option:selected").val();
	var AirPurification = $("#"+specEngNameList[3]+" option:selected").val();
	var LowNoise = $("#"+specEngNameList[4]+" option:selected").val();
	var Type = $("#"+specEngNameList[5]+" option:selected").val();
	
	if(SquareMeasure == '' || SquareMeasure == '0.0'){
		flag = 1;
		$("#" + specEngNameList[0] + "Errors").html(specKorNameList[0] +"를 입력하시지 않았습니다.");
	}else if(SquareMeasure != '22.8' && SquareMeasure != '32.5'
			&& SquareMeasure != '48.8' && SquareMeasure !=  '52.8'
			&& SquareMeasure != '65.9' && SquareMeasure != '75.5'){
		$("#" + specEngNameList[0] + "Errors").html("입력하신 값이 아닙니다. 6가지 실수중 하나만 입력하세요.");
	}
	else{
		$("#" + specEngNameList[0] + "Errors").html("");
	}
	
    return flag;
}

