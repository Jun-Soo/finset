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
    
    $(document).on("click",".btn-search",function(){
        
        $("aside.search-wrap").addClass("on");
        
    });
    
    $(document).on("click",".search-wrap .top button",function(){
        
        $("aside.search-wrap").removeClass("on");
        
    });
    
    
    ////////////////////     리스트 옵션     ////////////////////
    
    $(document).on("click",".list02 .btn button",function(){
        
        $(this).closest(".btn").toggleClass("on");
        
    });
    
    
    ////////////////////     아코디온     ////////////////////
    
    
    $(document).on("click",".accodion .top a",function(e){
        
        $(this).closest("li").toggleClass("on");
        e.preventDefault();
        
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
