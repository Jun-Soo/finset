/*
 * common
 */
import Constant from './constant.js'

export default {

  init: function () {
    localStorage.setItem('accessToken', '')

    Constant.userAgent = this.getMobileOperatingSystem()
    Constant.params = this.getParams()

    // ios a, button 태그 이벤트
    $('body *').on('touchstart', function () {})

    // Tab
    $('.tabs a').click(function (e) {
      e.preventDefault()
      $(this).tab('show')
      $(document).scrollTop(0)
    })

    // loan select
    $('.loan-block > .loan-product').each(function (i, e) {
      $(this).click(function () {
        $('.loan-block > .loan-product').not(this).each(function (i, e) {
          $(this).removeClass('active')
        })
        $(this).addClass('active')
      })
    })

    // affix
    $('.affix-fixed').each(function () {
      if ($(this)) {
        $('body').affix({
          offset: {
            top: 1
          }
        })
      }
    })

    // mask
    if (!$('#mask').length > 0) {
      $('#wrapper').after("<div id='mask'></div>")
    }

    // selectpicker
    // $('.selectpicker').on('show.bs.select', function (e) {
    //   this.flag()
    // })
    // $('.selectpicker').on('hide.bs.select', function (e) {
    //   this.flag()
    // })
    // $('.selectbox').selectpicker()

    // collapse
    $('.collapse').on('show.bs.collapse', function (e) {
      $(this).siblings("[data-toggle='collapse'], .panel-heading").addClass('active')
    })
    $('.collapse').on('hide.bs.collapse', function (e) {
      $(this).siblings("[data-toggle='collapse'], .panel-heading").removeClass('active')
    })

    // 탭 활성화 표시 모션
    var actWidth = $('.tabs .active > a').innerWidth()
    var actPosition = $('.tabs .active > a').position()

    if (actWidth != null) {
      $('.tabs').append("<div class='pseudo-bar'></div>")

      $('.pseudo-bar').css({
        'width': actWidth,
        'left': actPosition.left
      })

      $('.tabs a').click(function () {
        var width = $(this).innerWidth()
        var position = $(this).position()

        $('.pseudo-bar').css({
          'width': width,
          'left': position.left
        })
      })
    }

    // 하단고정버튼이 있는 경우
    if (($('#menu-fixed-bottom').is(':visible') === true) || ($('.btn-fixed-bottom').is(':visible') === true)) {
      $('#content').css('padding-bottom', '64px')
    }
  },

  flag: function () {
    var body = $('body')
    var isOpen = false
    if (isOpen) {
      body.removeClass('swipe')
    } else {
      body.addClass('swipe')
    }
    isOpen = !isOpen
  },

  getMobileOperatingSystem: function () {
    var userAgent = navigator.userAgent || navigator.vendor || window.opera
    if (userAgent.match(/iPad/i) || userAgent.match(/iPhone/i) || userAgent.match(/iPod/i)) {
      return 'iOS'
    } else if (userAgent.match(/Android/i)) {
      return 'Android'
    } else {
      return 'unknown'
    }
  },

  getParams: function () {
    var vars = {}
    window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function (m, key, value) {
      vars[key] = value
    })
    return vars
  },

  affixBottom: function (flag) {
    var active = {}
    active = {
      show: function () {
        $('.btn-fixed-bottom').addClass('affix-bottom')
      },
      hide: function () {
        $('.btn-fixed-bottom').removeClass('affix-bottom')
      }
    }
    this.enableBottom('false')
    if (flag === 'show') {
      active.show()
    } else if (flag === 'hide') {
      active.hide()
    }
    active[flag]()
  },

  enableBottom: function (flag) {
    var active = {}
    active = {
      enabled: function () {
        $('.btn-fixed-bottom').find('.btn-disabled').addClass('btn-primary')
      },
      disabled: function () {
        $('.btn-fixed-bottom').find('.btn-disabled').removeClass('btn-primary')
      }
    }
    if (flag === 'true') {
      active.enabled()
    } else if (flag === 'false') {
      active.disabled()
    }
  }
}
