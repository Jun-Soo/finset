/*
 * common
 */
export default {

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
