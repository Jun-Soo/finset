export const defaultLabels = {
  today: '오늘'
}

export const defaultViews = ['month']

let config = {
  locale: 'en',
  labels: defaultLabels,
  initialDate: new Date(),
  initialView: 'month',
  eventDisplay: null
}

export default config

export const setOptions = options => {
  config = options
}
