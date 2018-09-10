
export const API_END_POINT = 'http://localhost:8080/api'

export const getHeader = function () {
  const tokenData = JSON.parse(window.localStorage.getItem('lbUser'))
  const headers = {
    'Accept': 'application/json',
    'Authorization': 'Bearer' + tokenData.access_token
  }
  return headers
}