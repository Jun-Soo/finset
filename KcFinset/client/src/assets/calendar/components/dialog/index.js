import Vue from 'vue'
import EventDialog from './EventDialog.vue'

function open (propsData) {
  const EventDialogComponent = Vue.extend(EventDialog)
  return new EventDialogComponent({
    el: document.createElement('div'),
    propsData
  })
}

export default {
  show (params, extraFields) {
    const defaultParam = {
      title: '일정',
      inputClass: null,
      overrideInputClass: false,
      createButtonLabel: '등록',
      //  -------------------------
      date: null,
      startTime: null,
      endTime: null,
      enableTimeInputs: false
    }

    const propsData = Object.assign(defaultParam, params)

    const defaultFields = [{
      name: 'date', //  Required
      type: 'date', //  def: 'text'
      label: '날짜', //  def: this.name
      // showLabel: false,    //  def: true
      required: true, //  def: false,
      value: propsData.date //  def: null
    }, {
      name: '테스트',
      type: 'text',
      label: '테스트',
      required: true,
      value: propsData.test
    }]

    if (propsData.enableTimeInputs) {
      defaultFields.splice(1, 0, {
        cFields: [{
          name: 'startTime',
          type: 'time',
          label: 'from',
          required: true,
          value: propsData.startTime
        },
        {
          name: 'endTime',
          type: 'time',
          label: 'to',
          required: true,
          value: propsData.endTime
        }
        ]
      })
    }
    propsData.cFields = extraFields ? defaultFields.concat(extraFields) : defaultFields
    return open(propsData)
  }
}
