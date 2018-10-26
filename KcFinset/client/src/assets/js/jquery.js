// JavaScript Document

$(function () {
  $(document).on('click', 'aside button', function () {
    $('aside').toggleClass('on')
  })

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

  $(document).on('click', '.search-wrap .top button', function () {
    $('aside.search-wrap').removeClass('on')
  })

  $(document).on('click', '.list02 .btn button', function () {
    $(this).closest('.btn').toggleClass('on')
  })

  $(document).on('click', '.accodion .top a', function (e) {
    $(this).closest('li').toggleClass('on')
    e.preventDefault()
  })

  $(document).on('click', '.btn-onoff', function (e) {
    $(this).toggleClass('on')
  })

  function modal () {
    var openBtn = '[data-modal]'
    var closeBtn = '.modal-close'

    function getTarget (t) {
      return $(t).attr('data-modal')
    }

    function open (t) {
      var showTarget = $('[data-modal-con= + t + ]')

      window.setTimeout(function () {
        var popHeight = showTarget.height() / -2
        var popWidth = showTarget.innerWidth() / -2
        showTarget.attr('tabindex', '0')
        showTarget.show().css({
          'margin-top': popHeight,
          'margin-left': popWidth
        })
        showTarget.focus()
        showTarget.find('.modal-close').data('activeTarget', t)
      }, 500)
    }

    function close (t) {
      var activeTarget = $('[data-modal-con= + t + ]')
      activeTarget.hide()
      $('[data-modal=' + t + ']').focus()
    }

    $(document).on('click', openBtn, function (e) {
      e.preventDefault()
      open(getTarget($(this)))
      $('#modal-wrap').show()
    }).on('click', closeBtn, function (e) {
      e.preventDefault()
      close($(this).data('activeTarget'))
      $('#modal-wrap').hide()
    })
  }

  modal()
})
