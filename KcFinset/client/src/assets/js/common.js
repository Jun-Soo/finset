/*
 * common
 */
import Constant from './constant.js'

export default {
  back: function () {
    console.log('back')
    this.$route.go(-1)
  },
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

  nvl: function (str, defaultVal) {
    var defaultValue = ''
    if (typeof defaultVal !== 'undefined') {
      defaultValue = defaultVal
    }
    if (typeof str === 'undefined' || str == null || str === '' || str === 'undefined') {
      return defaultValue
    }
    return str
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
  },
  formatNumber: function (number, isMinus, isPlus) {
    if (typeof number === 'number') {
      number += ''
    }
    var regex = /^[-]?\d+(?:[.]\d+)?$/
    var formatNum = ''
    if (!regex.test(number)) {
      if (number === '-') {
        return '-'
      } else {
        return 0
      }
    } else {
      if (number.match('-')) {
        number = number.replace('-', '')
        isMinus = true
      }
      formatNum = Number(number).toLocaleString('en').split('.')[0]
      if (isMinus) {
        return '-' + formatNum
      } else {
        if (isPlus) {
          return '+' + formatNum
        } else {
          return formatNum
        }
      }
    }
  },
  formatDate: function (date, pattern) {
    var yyyy = ''
    var mm = ''
    var dd = ''
    if (typeof date === 'string') {
      yyyy = date.substring(0, 4)
      mm = date.substring(4, 6)
      dd = date.substring(6, 8)
    } else if (typeof date === 'object') {
      yyyy = date.getFullYear()
      mm = date.getMonth() + 1
      if (mm < 10) {
        mm = '0' + mm
      }
      dd = date.getDate()
      if (dd < 10) {
        dd = '0' + dd
      }
    }
    if (((pattern || '') === '') || pattern === 'yyyymmdd') {
      return yyyy + '-' + mm + '-' + dd
    } else if (pattern === 'yyyymm') {
      return yyyy + '-' + mm
    } else if (pattern === 'mmdd') {
      return mm + '-' + dd
    } else {
      return undefined
    }
  },
  formatDateDot: function (date, pattern) {
    var yyyy = ''
    var mm = ''
    var dd = ''
    if (typeof date === 'string') {
      yyyy = date.substring(0, 4)
      mm = date.substring(4, 6)
      dd = date.substring(6, 8)
    } else if (typeof date === 'object') {
      yyyy = date.getFullYear()
      mm = date.getMonth() + 1
      if (mm < 10) {
        mm = '0' + mm
      }
      dd = date.getDate()
      if (dd < 10) {
        dd = '0' + dd
      }
    }
    if (date != null && date !== '') {
      if (((pattern || '') === '') || pattern === 'yyyymmdd') {
        return yyyy + '.' + mm + '.' + dd
      } else if (pattern === 'yyyymm') {
        return yyyy + '.' + mm
      } else if (pattern === 'mmdd') {
        return mm + '.' + dd
      } else {
        return undefined
      }
    } else {
      return ''
    }
  },
  getCodeName: function (group, code) {
    var data = {
      'group': group,
      'code': code
    }
    var name = ''
    $.ajax({
      url: '/m/comm/getCodeName.json',
      data: data,
      contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
      type: 'POST',
      async: false,
      success: function (result) {
        name = result.name
      },
      error: function (e) {
        name = code
      }
    })
    return name
  },
  // pagination 사용법
  // 필요한 함수를 작성하되, 함수 파라미터로 callback을 선언
  pagination: function (callback) {
    Constant._this = this
    Constant._callback = callback
    Constant._this.addScroll()
    Constant._callback(function () {})
  },
  handleScroll: function () {
    var html = document.documentElement
    var docHeight = html.scrollHeight
    var viewHeight = html.offsetHeight > html.clientHeight ? html.clientHeight : html.offsetHeight
    var scrollY = window.scrollY
    var scrollBottom = docHeight - viewHeight - scrollY
    if (scrollBottom === 0) {
      Constant._callback(Constant._this.removeScroll)
    }
  },
  addScroll: function () {
    window.addEventListener('scroll', Constant._this.handleScroll)
  },
  removeScroll: function () {
    window.removeEventListener('scroll', Constant._this.handleScroll)
  }
}
