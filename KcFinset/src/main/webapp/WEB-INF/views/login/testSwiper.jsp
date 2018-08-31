<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">

<%@ include file="/WEB-INF/include/headComm.jsp"%>

<!DOCTYPE html> 
<html lang="en"> 
<head> 
  <meta charset="utf-8"> 
  <title>Swiper demo</title> 
  <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1"> 

 
 
 <style> 
    html, body { 
      position: relative; 
      height: 100%; 
    } 
    body { 
      background: #eee; 
      font-family: Helvetica Neue, Helvetica, Arial, sans-serif; 
      font-size: 14px; 
      color:#000; 
      margin: 0; 
      padding: 0; 
    } 
    .swiper-container { 
      width: 100%;                                           
      height: 100%; 
    } 
    .swiper-slide { 
      text-align: center; 
      font-size: 18px; 
      background: #fff; 
 
      /* Center slide text vertically */ 
      display: -webkit-box; 
      display: -ms-flexbox; 
      display: -webkit-flex; 
      display: flex; 
      -webkit-box-pack: center; 
      -ms-flex-pack: center; 
      -webkit-justify-content: center; 
      justify-content: center; 
      -webkit-box-align: center; 
      -ms-flex-align: center; 
      -webkit-align-items: center; 
      align-items: center; 
    }
    .swiper-container-v1 { 
      background: #eee; 
    }
    .swiper-container-v2 { 
      background: #eee; 
    }  
</style> 
  
</head> 
<body> 
  <!-- Swiper --> 
  <div class="swiper-container swiper-container-h"> 
    <div class="swiper-wrapper"> 
      <div class="swiper-slide">Slide 1</div>
      <div class="swiper-slide">
        <div class="swiper-container swiper-container-v1"> 
          <div class="swiper-wrapper"> 
            <div class="swiper-slide">Vertical Slide 1-1</div> 
            <div class="swiper-slide">Vertical Slide 1-2</div> 
            <div class="swiper-slide">Vertical Slide 1-3</div> 
          </div> 
          <div class="swiper-pagination swiper-pagination-v"></div> 
        </div> 
      </div> 
      <div class="swiper-slide"> 
        <div class="swiper-container swiper-container-v2"> 
          <div class="swiper-wrapper"> 
            <div class="swiper-slide">Vertical Slide 2-1</div> 
            <div class="swiper-slide">Vertical Slide 2-2</div> 
            <div class="swiper-slide">Vertical Slide 2-3</div> 
            <div class="swiper-slide">Vertical Slide 2-4</div> 
            <div class="swiper-slide">Vertical Slide 2-6</div>
            <div class="swiper-slide">Vertical Slide 2-7</div>
            <div class="swiper-slide">Vertical Slide 2-8</div>
            <div class="swiper-slide">Vertical Slide 2-9</div> 
          </div> 
          <div class="swiper-pagination swiper-pagination-v"></div> 
        </div> 
      </div> 
      <div class="swiper-slide">
        <div class="swiper-container swiper-container-v3"> 
          <div class="swiper-wrapper"> 
            <div class="swiper-slide">Vertical Slide 3-1</div>
            <div class="swiper-slide">Vertical Slide 3-2</div> 
            <div class="swiper-slide">Vertical Slide 3-3</div> 
            <div class="swiper-slide">Vertical Slide 3-4</div> 
            <div class="swiper-slide">Vertical Slide 3-6</div>
            <div class="swiper-slide">Vertical Slide 3-7</div>
            <div class="swiper-slide">Vertical Slide 3-8</div>
            <div class="swiper-slide">Vertical Slide 3-9</div> 
          </div> 
          <div class="swiper-pagination swiper-pagination-v"></div> 
        </div>
      </div> 
    </div> 
    <!-- Add Pagination --> 
    <div class="swiper-pagination swiper-pagination-h"></div> 
  </div> 

  <script> 
    var swiperH = new Swiper('.swiper-container-h', { 
      spaceBetween: 50, 
      pagination: { 
        el: '.swiper-pagination-h', 
        clickable: true, 
      }, 
    });
    swiperH.on('slideChange', function () {
    	  console.log('slide H changed');
    });
    var swiperV1 = new Swiper('.swiper-container-v1', { 
      direction: 'vertical',
      slidesPerView: 1,
      spaceBetween: 50, 
      pagination: { 
        el: '.swiper-pagination-v', 
        clickable: true, 
      }, 
    });
    swiperV1.on('slideChange', function () {
  	  console.log('slide V changed');
    });
    var swiperV2 = new Swiper('.swiper-container-v2', { 
        direction: 'vertical',
        slidesPerView: 3,
        freeMode: true,
        spaceBetween: 10, 
        pagination: { 
          //el: '.swiper-pagination-v', 
          clickable: true, 
        }, 
      });
    var swiperV3 = new Swiper('.swiper-container-v3', { 
        direction: 'vertical',
        slidesPerView: 'auto',
        freeMode: true,
        spaceBetween: 10, 
        pagination: { 
          //el: '.swiper-pagination-v', 
          clickable: true, 
        }, 
      });
  </script> 
</body> 
</html> 