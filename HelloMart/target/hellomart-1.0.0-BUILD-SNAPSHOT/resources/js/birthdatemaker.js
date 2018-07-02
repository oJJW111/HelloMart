var BIRTHDATEMAKER = function() {
	var attr = {
		year : '#',
		month : '#',
		day : '#',
		begin : 0,
		end : 0,
		yearDefault : '생년',
		monthDefault : '월',
		dayDefault : '일',
		yearDefaultAttr : '',
		monthDefaultAttr : '',
		dayDefaultAttr : '',
	}
	var dayOfMonth = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
	var appendOption = function(id, value, valueAttr) {
		$(id).append('<option value=' + valueAttr + '>' + value + '</option>');
	}
	var list = function(id, n, m) {
		for(i=n; i<=m; i++){
			appendOption(id, i, i);
		}
	}
	var listWithTitle = function(id, value, valueAttr, n, m) {
		appendOption(id, value, valueAttr);
		list(id, n, m);
	}
	var getSelectedVal = function(id) {
		return $(id + ' option:selected').attr("value");
	}
	var dayEmpty = function() {
		$(attr.day).empty();
	}
 	var registerEvent = function(x, y, m) {
 		$(x).on("change", function() {
 			var a = getSelectedVal(y);
			var b = getSelectedVal(m);
			calculateDay(a, b);
		});
	};
	var isLeafYear = function(y) {
		var b = false;
		if((y % 400) == 0) {
			b = true;
		} else if((y % 4) == 0 && (y % 100) != 0) {
			b = true;
		}
		return b;
	}
	var calculateDay = function(y, m) {
		if(y == attr.yearDefaultAttr || m == attr.monthDefaultAttr) return;
		
		dayEmpty();
		
		var tempOfDay = dayOfMonth[m-1];
		
		var n = tempOfDay;
		
		var b = false;
		if(m == 2) {
			b = isLeafYear(y);
		}
		
		if(b) {
			n++;
		}
		
		listWithTitle(attr.day, attr.dayDefault, attr.dayDefaultAttr, 1, n);
	}
	var matchInitVal = function(z) {
		initAddVal(attr, z.year, "year");
		initAddVal(attr, z.month, "month");
		initAddVal(attr, z.day, "day");
		initVal(attr, z.begin, "begin");
		initVal(attr, z.end, "end");
		initVal(attr, z.yearDefault, "yearDefault");
		initVal(attr, z.monthDefault, "monthDefault");
		initVal(attr, z.dayDefault, "dayDefault");
		initVal(attr, z.yearDefaultAttr, "yearDefaultAttr");
		initVal(attr, z.monthDefaultAttr, "monthDefaultAttr");
		initVal(attr, z.dayDefaultAttr, "dayDefaultAttr");
	}
	var initVal = function(x, y, z) {
		if(!(y === undefined)) {
			x[z] = y;
		}
	}
	var initAddVal = function(x, y, z) {
		if(!(y === undefined)) {
			x[z] += y;
		}
	}
	return {
		make : function(z) {
			matchInitVal(z);
			
			appendOption(attr.day, attr.dayDefault, attr.dayDefaultAttr);
			
			listWithTitle(attr.year, attr.yearDefault, attr.yearDefaultAttr, attr.begin, attr.end);
			listWithTitle(attr.month, attr.monthDefault, attr.monthDefaultAttr, 1, 12);
			
			registerEvent(attr.year, attr.year, attr.month)
			registerEvent(attr.month, attr.year, attr.month)
		}
	};
}();