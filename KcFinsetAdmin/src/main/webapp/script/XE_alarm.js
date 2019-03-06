var Alarm = {
	dom : null,
	listUrl : null,
	/*
	 * dom property
	 * 
	 * 	wrapper 
	 *	bar 
	 *	count 
	 *	alarm 
	 *	alarmMsg 
	 *	barCloseBtn 
	 *	alarmCloseBtn 
	 *	wrapList
	 *	listAlarm 
	 *	confirmBtn
	 *	progress
	 * 
	 * */
	worker : null,
	running : null,
	Tween : null,
	loadUnconfirmedAlarmLength : function(){
		AjaxRequest.post({
			url : this.listUrl.loadUnconfirmedAlarmLength,
			parameters : {id_alarm_target:Alarm.worker},
			onSuccess : function(response){
				var obj = JSON.parse(response.responseText);
				Alarm.dom.count.innerHTML = obj.result;
				if(obj.result == 0){
					Alarm.closeList();
				}
			}
		});
	},
	
	arrive : function(seq){
		this.clearRunning();
		
		AjaxRequest.post({
			url : this.listUrl.arrive,
			parameters : {seq_alarm:seq},
			onSuccess : function(response){
				if(response.responseText.length <= 0) return;
				listAlarmDetail.innerHTML = response.responseText;
			}
		});	

		this.openAlarm();
		
		this.dom.confirmBtn.onclick = function(){
			Alarm.confirm(seq);
		};
		
		this.dom.alarm.onmouseover = function(){
			Alarm.clearRunning();
		};
		
		this.dom.alarm.onmouseout = function(){
			Alarm.setRunning();
		};
		
		this.setRunning();
	},
	
	closeAlarm : function(){
		var alarm = this.dom.alarm;
		if(this.Tween){
			this.Tween.onMotionFinished = function(){
				alarm.style.display = "none";
			};
			this.Tween.setBegin(0);
			this.Tween.setFinish(-alarm.offsetHeight);
		    this.Tween.start();
		} else {
			alarm.style.bottom = -alarm.offsetHeight + "px";
			alarm.style.display = "none";
		}
	},
	
	openAlarm : function(){
		var alarm = this.dom.alarm;
		alarm.style.display = "block";
		if(this.Tween){
			this.Tween.onMotionFinished = null;
			this.Tween.setBegin(-alarm.offsetHeight);
			this.Tween.setFinish(0);
			this.Tween.start();
		} else {
			alarm.style.bottom = "0px";
			alarm.style.display = "none";
		}
	},
	
	setRunning : function(){
		this.running = window.setTimeout(function(){
			Alarm.closeAlarm();
			if(Alarm.listIsOpen()) {
				Alarm.loadList();
			} 

			Alarm.loadUnconfirmedAlarmLength();
			

		}, 3000);
	},
	
	clearRunning : function(){
		if(this.running == null) return;
		window.clearTimeout(this.running);
		this.running = null;
	},
	
	test : function(){
		AjaxRequest.post({
			url : this.listUrl.test,
			parameters : {mode:"sendAlarmTEST",id_alarm_target:Alarm.worker},
			onSuccess : function(response){
			}
		});	
	},
	
	loadList : function(){
		var wrapList = this.dom.wrapList,
			listAlarm = this.dom.listAlarm,
			progress = this.dom.progress;
		
		progress.style.display = "block";
		
		AjaxRequest.post({
			url : this.listUrl.loadList,
			onSuccess : function(response){
				if(response.responseText.length <= 0) return;
				listAlarm.innerHTML = response.responseText;
				
				if(!Alarm.listIsOpen()) {
					wrapList.style.display = "block";
				}
			},
			onComplete : function(){
				progress.style.display = "none";
			}
		});	
	},
	
	openList : function(){
		if(this.listIsOpen()) return;
		this.loadUnconfirmedAlarmLength();
		this.loadList();
	},
	
	closeList : function(){
		var list = this.dom.wrapList;
		
		if(this.listIsOpen()) {
			list.style.display = "none";
		}
		
	},
	
	listIsOpen : function(){
		return this.dom.wrapList.style.display == "block";
	},
	
	confirm : function(seq, comfirmBtn){
		AjaxRequest.post({
			url : this.listUrl.confirm,
			parameters : {id_alarm_target:Alarm.worker, seq_alarm:seq},
			onSuccess : function(response){
				var obj = JSON.parse(response.responseText),
					result = obj.returnData.cd_result;

				if(comfirmBtn){
					if(result == '00'){
						comfirmBtn.parentNode.parentNode.removeChild(comfirmBtn.parentNode);
						Alarm.loadUnconfirmedAlarmLength();
					}
				} else {
					Alarm.dom.alarm.onmouseout = null;
					Alarm.dom.alarm.onmouseover = null;
					Alarm.clearRunning();
					Alarm.closeAlarm();
					Alarm.loadUnconfirmedAlarmLength();
				}
			}
		});	
	},
	
	confirmAll : function(){
		if(!confirm('모든 알람을 확인합니다. 진행하시겠습니까?')) return;
		AjaxRequest.post({
			url : this.listUrl.confirmAll,
			onSuccess : function(response){
				var obj = JSON.parse(response.responseText),
					result = obj.returnData.cd_result;
				
				if(result == '00'){
					Alarm.closeList();
					Alarm.loadUnconfirmedAlarmLength();
				}
			}
		});	
	},
	
	hide : function(){
		this.dom.wrapper.style.display = "none";
		this.dom.hideAlarm.style.display = "block";
		
		this.dom.hideAlarm.onclick = function(){
			Alarm.show();
		}
	},
	
	show : function(){
		this.dom.wrapper.style.display = "block";
		this.dom.hideAlarm.style.display = "none";
	},
	
	init : function(obj){
		for(var prop in obj){
			this[prop] = obj[prop];
		}
		
		Alarm.dom.bar.onclick = function(e){
			Alarm.openList();
		};
		
		Alarm.dom.barCloseBtn.onclick = function(e){
			if(Alarm.listIsOpen()){
				Alarm.closeList();
			} else {
				Alarm.hide();
			}
			
			e = e || window.event;
			if(!e) return;
			if(typeof e.stopPropagation == 'function'){
				e.stopPropagation();
			} else {
				e.cancelBubble = true;
			}
		};
	}
};