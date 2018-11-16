<template>
  <section>
    <div class="container">
      <div class="debt-regist">
        <p class="title"><em>친구에게 빌려준 돈을 등록하고</em><br>편리하게 관리하세요</p>
        <div class="list">
          <ul class="flex">
            <li class="key">이름</li>
            <li class="value">
              <input type="text" placeholder="친구를 검색하세요">
              <button class="human"></button>
            </li>
          </ul>
          <ul class="flex">
            <li class="key">금액</li>
            <li class="value">
              <input type="text" placeholder="거래금액을 입력하세요">
              <em>만원</em>
            </li>
          </ul>
          <ul class="flex">
            <li class="key">이자</li>
            <li class="value">
              <input type="text" placeholder="이자를 입력하세요">
              <button class="onoff per on">%</button><button class="onoff won">원</button>
            </li>
          </ul>
          <ul class="flex">
            <li class="key">계약일</li>
            <li class="value">
              <input type="text" placeholder="계약일을 입력하세요">
              <button class="cal"></button>
            </li>
          </ul>
          <ul class="flex">
            <li class="key">만기일</li>
            <li class="value">
              <input type="text" placeholder="만기일을 입력하세요">
              <button class="cal"></button>
            </li>
          </ul>
        </div>

        <div class="debt-acco">
          <ul>
            <li>
              <div class="top">
                <a href="#" data-acco="0">추가 항목</a>
              </div>
              <div class="body">
                <div class="flex">
                  <p>상환방법</p>
                  <p>
                    <select>
                      <option>선택</option>
                    </select>
                  </p>
                </div>
                <div class="flex">
                  <p>이자납입주기</p>
                  <p>
                    <select>
                      <option>선택</option>
                    </select>
                  </p>
                </div>
                <div class="flex">
                  <p>이자납입일</p>
                  <p>
                    <select>
                      <option>선택</option>
                    </select>
                  </p>
                </div>
              </div>
            </li>
          </ul>
        </div>

      </div>
      <div class="btn-wrap float">
        <a href="#" class="solid blue box">등록하기</a>
      </div>
    </div>

  </section>
</template>

<script>
export default {
  name: "DebtRegister",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "기타부채등록";
  },
  created() {},
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {}
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
