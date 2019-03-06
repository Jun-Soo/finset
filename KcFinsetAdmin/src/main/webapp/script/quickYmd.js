//날짜 구하기
function getDate(gubn, fromDate, toDate){
	
	var bar = "-";

	var from = "input[name="+fromDate+"]";
	var to = "input[name="+toDate+"]";
	
	var d = new Date();
	
	var yearFrom = d.getFullYear(); //년도 yyyy
	var yearTo = d.getFullYear(); //년도 yyyy
	var monthFrom = d.getMonth()+1; //월 mm
	var monthTo = d.getMonth()+1; //월 mm
	var dateFrom = d.getDate();	//일 dd
	var dateTo = d.getDate();	//일 dd
	var day = d.getDay();		//요일 (0~6)
	var lastDay = (new Date(yearFrom, monthFrom, 0)).getDate();
	
	if(gubn == "T") {
		// 당일 날짜 구하기
	}
	else if(gubn == "Y") {
		// 전일 날짜 구하기
	    if(dateFrom == 1)
    	{
	    	if(monthFrom == 1){
	    		yearFrom = yearFrom-1;
	    		yearTo = yearTo-1;
	    		monthFrom = 12;
	    		monthTo = 12;
	    	}else{
		    	monthFrom = monthFrom-1
		    	monthTo = monthTo-1
	    	}
	    	dateFrom = (new Date(yearFrom, monthFrom, 0)).getDate();
	    	dateTo = (new Date(yearFrom, monthFrom, 0)).getDate();
    	} 
	    else 
	    {
    		dateFrom = dateFrom-1;
    	  	dateTo = dateTo-1;
    	}
	}
	else if(gubn =="TM") {	
		// 익일 날짜 구하기
		if(dateFrom == lastDay)
		{
			if(monthFrom == 12)
			{
				yearFrom = yearFrom +1;
				yearTo = yearTo +1;
				monthFrom = 1;
				monthTo = 1;
			}
			else 
			{	
				monthFrom = monthFrom +1;
				monthTo = monthTo +1;
			}
			dateFrom = 1;
			dateTo = 1;
		}
		else 
		{	
		  	dateFrom = dateFrom+1;
		  	dateTo = dateTo+1;
		}
	}
	else if(gubn =="W") {
	    // 이번주 날짜 구하기  
		dateFrom = dateFrom-day;
	  	dateTo = dateTo+(6-day);

	  	if(dateFrom < 1)
  		{
	  		if(monthFrom == 1){
	  			yearFrom = yearFrom-1;
	  			monthFrom = 12;
	  		}else{
	  			monthFrom = monthFrom-1;
	  		}
	  		lastDay = (new Date(yearFrom, monthFrom, 0)).getDate();
	  		dateFrom = lastDay + dateFrom;
  		}
	  	
	  	lastDay = (new Date(yearTo, monthTo, 0)).getDate();
	  	
	  	if(dateTo > lastDay){
	  		
	  		if(monthTo == 12){
	  			yearTo= yearTo+1;
	  			monthTo = 1;
	  		}else{
	  			monthTo = monthTo+1;
	  		}
	  		dateTo = dateTo - lastDay;
	  	}
	  	
	}
	else if(gubn =="LW") {
	    // 지난주 날짜 구하기
		dateFrom = (dateFrom-day)-7;
	  	dateTo = (dateTo-day)-1;
		
	  	if(dateFrom < 1)
	  	{	
	  		monthFrom = monthFrom-1;
	  		
	  		if(monthFrom == 0){
	  			monthFrom = 12;
	  			yearFrom = yearFrom-1;
	  		}
	  		lastDay = (new Date(yearFrom, monthFrom, 0)).getDate();
	  		dateFrom = lastDay + dateFrom;
  		}
	  	if(dateTo < 1)
  		{
	  		monthTo = monthTo-1;
	  		
	  		if(monthTo == 0){
	  			monthTo = 12;
	  			yearTo = yearTo-1;
	  		}
	  		lastDay = (new Date(yearTo, monthTo, 0)).getDate();
	  		dateTo = lastDay + dateTo;
  		}
	}
	else if(gubn == "M") {
   		// 당월 날짜구하기
		dateFrom = (new Date(yearFrom, monthFrom, 1)).getDate();
		dateTo = (new Date(yearTo,monthTo,0)).getDate();
	}
	else if(gubn == "LM") {
	    // 전월 날짜구하기
		monthFrom = monthFrom-1;
		monthTo = monthTo-1;

		yearFrom = (monthFrom == 0) ? yearFrom-1 : yearFrom;
		yearTo = yearFrom;
		
		monthFrom = (monthFrom == 0) ? 12 : monthFrom;
		monthTo = (monthTo == 0) ? 12 : monthTo;

		dateFrom = (new Date(yearFrom, monthFrom, 1)).getDate();
		dateTo = (new Date(yearTo,monthTo,0)).getDate();
	}
	
	monthFrom = (monthFrom < 10) ? "0"+monthFrom : monthFrom;
    monthTo = (monthTo < 10) ? "0"+monthTo : monthTo;
	dateFrom = (dateFrom < 10) ? "0"+dateFrom : dateFrom;
	dateTo = (dateTo < 10) ? "0"+dateTo : dateTo;
	
	if(toDate == '' || toDate == null)
		$(from).val(yearFrom+bar+monthTo+bar+dateTo);
	else
	{
		$(from).val(yearFrom+bar+monthFrom+bar+dateFrom);
		$(to).val(yearTo+bar+monthTo+bar+dateTo);
	}
    
}