import ko from 'vee-validate/dist/locale/ko.js'

ko.messages.error = '시스템 에러가 발생하였습니다. 다시 시도해 주시기 바랍니다.'
ko.messages.loginErr = '로그인에 실패하였습니다. 다시 시도해 주시기 바랍니다.'
ko.messages.require = '입력값을 확인해 주시기 </br>바랍니다.'

ko.messages.notMatchPwd = '입력하신 비밀번호가 일치하지 않습니다.'

ko.messages.email = (field) => `${field}은/는 올바른 이메일 형식이어야 합니다.`
ko.messages.required = (field) => `${field} 항목은 필수 정보입니다.`
