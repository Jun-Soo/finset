export default {
  data () {
    return {
      isShow: false,
      setIsShow: false,
      selected: '',
      selectext1: '',
      selectext2: '',
      selected1: '',
      selected2: ''
    }
  },
  props: {
    /**
     * Defines a static height for the modal.
     *
     * @type {String|Number|null}
     */
    height: {
      type: [String, Number],
      required: false,
      default: 'auto'
    },
    title: {
      type: [String],
      required: true
    },
    onClose: {
      type: Function,
      required: false
    },
    /**
     * Decide whether to filter the results based on search query.
     * Useful for async filtering, where we search through more complex data.
     * @type {Boolean}
     */
    internalSearch: {
      type: Boolean,
      default: true
    },
    /**
     * Array of available options: Objects, Strings or Integers.
     * If array of objects, visible label will default to option.label.
     * If `labal` prop is passed, label will equal option['label']
     * @type {Array}
     */
    options: {
      type: [Array, Object],
      required: true
    },
    /**
     * Equivalent to the `multiple` attribute on a `<select>` input.
     * @default false
     * @type {Boolean}
     */
    multiple: {
      type: Boolean,
      default: false
    },
    /**
     * Presets the selected options value.
     * @type {Object||Array||String||Integer}
     */
    value: {
      type: null,
      default () {
        return []
      }
    },
    /**
     * Key to compare objects
     * @default 'id'
     * @type {String}
     */
    trackBy: {
      type: String
    },
    /**
     * Label to look for in option Object
     * @default 'label'
     * @type {String}
     */
    label: {
      type: String
    },
    /**
     * Enable/disable search in options
     * @default true
     * @type {Boolean}
     */
    searchable: {
      type: Boolean,
      default: true
    },
    /**
     * Clear the search input after `)
     * @default true
     * @type {Boolean}
     */
    clearOnSelect: {
      type: Boolean,
      default: true
    },
    /**
     * Hide already selected options
     * @default false
     * @type {Boolean}
     */
    hideSelected: {
      type: Boolean,
      default: false
    },
    /**
     * Equivalent to the `placeholder` attribute on a `<select>` input.
     * @default 'Select option'
     * @type {String}
     */
    placeholder: {
      type: String,
      default: ''
    },
    /**
     * Allow to remove all selected values
     * @default true
     * @type {Boolean}
     */
    allowEmpty: {
      type: Boolean,
      default: true
    },
    /**
     * Reset this.internalValue, this.search after this.internalValue changes.
     * Useful if want to create a stateless dropdown.
     * @default false
     * @type {Boolean}
     */
    resetAfter: {
      type: Boolean,
      default: false
    },
    /**
     * Enable/disable closing after selecting an option
     * @default true
     * @type {Boolean}
     */
    closeOnSelect: {
      type: Boolean,
      default: true
    },
    /**
     * Will be passed with all events as second param.
     * Useful for identifying events origin.
     * @default null
     * @type {String|Integer}
     */
    id: {
      type: String,
      default: null
    },
    /**
     * Disables the multiselect if true.
     * @default false
     * @type {Boolean}
     */
    disabled: {
      type: Boolean,
      default: false
    }
  },
  mounted () {
  },
  computed: {
    internalValue () {
      return this.value || this.value === 0
        ? Array.isArray(this.value) ? this.value : [this.value] : []
    }
  },
  watch: {
    value: function (obj) {
      if (obj === null) {
        if ((this.placeholder || '') !== '') {
          this.selected = this.placeholder
        } else {
          this.selected = ''
        }
        this.selected1 = ''
        this.selectext1 = ''
        this.selected2 = ''
        this.selectext2 = ''
        return
      }
      if ((obj.value || '') === '') {
        return
      }
      var selOpt = this.options.filter(option => option.value === obj.value)

      if ((selOpt || '') !== '' && selOpt.length === 1) {
        this.selected = selOpt[0].text
        this.selected1 = selOpt[0].value
      }
    }
  },
  methods: {
    /**
     * Returns the internalValue in a way it can be emited to the parent
     * @returns {Object||Array||String||Integer}
     */
    getValue () {
      return this.multiple
        ? this.internalValue
        : this.internalValue.length === 0
          ? null
          : this.internalValue[0]
    },
    /**
     * Finds out if the given element is already present
     * in the result value
     * @param  {Object||String||Integer} option passed element to check
     * @returns {Boolean} returns true if element is selected
     */
    isSelected (option) {
      const opt = this.trackBy
        ? option[this.trackBy]
        : option
      return this.valueKeys.indexOf(opt) > -1
    },
    open: function () {
      console.log('startOpen')
      this.isShow = true
      console.log('endOpen')
    },
    close: function () {
      this.isShow = false
    },
    click: function (option) {
      this.selected1 = option.value
      this.selected = option.text
      this.isShow = false
      if (this.onClose) this.onClose(option)
      this.$emit('input', option)
    },
    multiclick: function (option, key) {
      switch (key) {
        case 'm':
          this.selected1 = option.value
          this.selected2 = ''
          this.selectext1 = option.text
          break
        case 'd':
          this.selected2 = option.value
          this.selectext2 = option.text
          break
        default:
          break
      }
    },
    clickConfirm: function () {
      this.selected = this.selectext1 + '-' + this.selectext2
      var obj = []
      obj.push({
        'value': this.selected1,
        'text': this.selectext1
      })
      obj.push({
        'value': this.selected2,
        'text': this.selectext2
      })
      this.$emit('input', obj)
      this.close()
    },
    chkSelectValue: function () {
      if ((this.value || '') === '') {
        return
      }
      var selOpt = this.options.filter(option => option.value === this.value.value)

      if ((selOpt || '') !== '' && selOpt.length === 1) {
        this.selected = selOpt[0].text
        this.selected1 = selOpt[0].value
      }
    },
    cateSize: function (obj) {
      if (obj.$el) {
        let objWidth1 = Number(obj.$el.children[1].children[1].clientWidth.toString())
        let objWidth2 = Number(obj.$el.children[1].children[1].children[0].clientWidth.toString())
        let fullWidth = Number(obj.$el.clientWidth.toString())
        let parentWidth = Number(obj.$el.parentElement.parentElement.clientWidth.toString())

        if (objWidth2 !== parentWidth && objWidth2 !== fullWidth && objWidth1 !== parentWidth) {
          obj.$el.children[1].children[1].children[0].style.width = (objWidth1 - 80) + 'px'
          obj.$el.children[1].children[1].style.width = (objWidth1 - 40) + 'px'
        }

        let ht = this.options.length * 40
        return 'height: ' + (ht > 300 ? 300 : ht) + 'px;'
      }
    }
  }
}
