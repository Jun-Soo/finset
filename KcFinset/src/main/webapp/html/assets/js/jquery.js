// JavaScript Document


$(function(){
	
    $(document).on("click","aside button",function(){
        
        $("aside").toggleClass("on");
        
    });
    
	
	////////////////////     HEADER     ////////////////////
    
    
    /*   GNB 로드   */
	$("header").load('../include/gnb.html');
    
    
    
    $(document).on("click","header .open-menu",function(){
        
        $(".gnb-wrap").addClass("on");
        $("body").addClass("not-scroll");
        
    });
    
    $(document).on("click","header .gnb-close",function(){
        
        $(".gnb-wrap").removeClass("on");
        $("body").removeClass("not-scroll");
        
    });
    
	
    ////////////////////     콘텐츠 확장     ////////////////////
    
    
    $(document).on("click",".btn-expand",function(){
        
        if($(this).hasClass("on")){
            $(this).closest(".item").find(".hide-con").slideUp(500,"easeInOutExpo");
        }else{
            $(this).closest(".item").find(".hide-con").slideDown(500,"easeInOutExpo");
        }
        
        $(this).toggleClass("on");
        
    });
    
    ////////////////////     SEARCH     ////////////////////
    
    $(document).on("click",".btn-search,.add-cate",function(){
        
        $("aside.search-wrap").addClass("on");
        
    });
    
    $(document).on("click",".search-wrap .top button",function(){
        
        $("aside.search-wrap").removeClass("on");
        
    });
    
    
    ////////////////////     리스트 옵션     ////////////////////
    
    $(document).on("click",".btn-menu-pop,.btn-interlock",function(e){
        
        $(this).closest(".btn-menu-wrap").toggleClass("on");
        e.preventDefault();
        
    });
    
    
    ////////////////////     아코디온     ////////////////////
    
    
    $(document).on("click",".accodion .top a",function(e){
        
        $(this).closest("li").toggleClass("on");
        e.preventDefault();
        
    });
    
    
    ////////////////////     설정버튼     ////////////////////
    
    $(document).on("click",".btn-onoff,.btn-star",function(e){
        
        $(this).toggleClass("on");
        
    });
    
    ////////////////////     Modal     ////////////////////
    
    function modal() {
		
		var openBtn = '[data-modal]', 
			closeBtn = '.modal-close'; 
		
		function getTarget(t) { 
			
			return $(t).attr('data-modal'); 
		
		} 
		
		function open(t) { 
			
			var showTarget = $('[data-modal-con="' + t + '"]');
			
			window.setTimeout(function(){
				var pop_height = showTarget.height()/-2;
				var pop_width = showTarget.innerWidth()/-2;
				showTarget.attr("tabindex","0");
				showTarget.show().css({"margin-top":pop_height,"margin-left":pop_width});
				showTarget.focus();
				showTarget.find('.modal-close').data('activeTarget', t);
			},500);
			
		} 
		
		function close(t) { 
			
			var activeTarget = $('[data-modal-con="' + t + '"]'); 
			activeTarget.hide(); 
			$('[data-modal="' + t + '"]').focus(); 
		} 
		
		$(document).on('click', openBtn, function(e){ 
			e.preventDefault(); 
			open(getTarget($(this)));
			$("#modal-wrap").show();
		}) .on('click', closeBtn, function(e) { 
			e.preventDefault(); 
			close($(this).data('activeTarget'));
			$("#modal-wrap").hide();
		}); 
	}
	
	modal(); 
    
    
    ////////////////////     지출 카테고리 설정     ////////////////////
    
    $(document).on("click",".consume-cate-list>li>.wrap",function(){
        
        if($(this).closest("li").hasClass("on")){
         
            $(this).closest("li").find("ul").slideUp(500,"easeInOutExpo");
            $(this).closest("li").removeClass("on");
            
        }else{
           
            $(this).closest("li").find("ul").slideDown(500,"easeInOutExpo");
            $(this).closest("li").addClass("on");
            
        }
        
    });
    
    
    ////////////////////     계산기 아코디온     ////////////////////
    
    $(document).on("click",".calc-acco .top .ui",function(){
        
        if($(this).hasClass("on")){
         
            $(this).closest(".calc-acco").find(".acco-body-wrap").slideUp(500,"easeInOutExpo");
            $(this).removeClass("on");
            
        }else{
           
            $(this).closest(".calc-acco").find(".acco-body-wrap").slideDown(500,"easeInOutExpo");
            $(this).addClass("on");
            
        }
        
    });
    
    
    ////////////////////     Image Slide     ////////////////////
	
	var owl = $(".owl-carousel");

	var currentItem = 0;
	owl.owlCarousel({
		items:1,
        loop:true,
        margin:21,
        padding:21,
		//margin:10,
	});
    
});
