// JavaScript Document

$(function () {
  $(document).on('click', 'aside button', function () {
    $('aside').toggleClass('on')
  })

  /*   GNB 로드   */
  // $('header').load('../include/gnb.html')

  $(document).on('click', 'header .open-menu', function () {
    $('.gnb-wrap').addClass('on')
    $('body').addClass('not-scroll')
  })

  $(document).on('click', 'header .gnb-close', function () {
    $('.gnb-wrap').removeClass('on')
    $('body').removeClass('not-scroll')
  })

  $(document).on('click', '.btn-expand', function () {
    if ($(this).hasClass('on')) {
      $(this).closest('.item').find('.hide-con').slideUp(500, 'easeInOutExpo')
    } else {
      $(this).closest('.item').find('.hide-con').slideDown(500, 'easeInOutExpo')
    }
    $(this).toggleClass('on')
  })

  var owl = $('.owl-carousel')

  owl.owlCarousel({
    items: 1,
    loop: true,
    margin: 21,
    padding: 21
  })
})
