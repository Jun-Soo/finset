<template>
  <div>
    <section>
      <div class="tab">
        <div class="wrap">
          <a href="#" class="on">지출</a>
          <a href="#">수입</a>
        </div>
      </div>

      <div class="container pb90">
        <ul class="consume-detail">
          <li>
            <p class="key">결제수단</p>
            <p>
              <select>
                <option>신한카드</option>
              </select>
            </p>
          </li>
          <li>
            <p class="key">금액</p>
            <p><input type="text"><em>%</em></p>
          </li>
          <li>
            <p class="key">카테고리</p>
            <p><button class="btn-cate btn-search">건강관리</button></p>
          </li>
          <li>
            <p class="key">결제처</p>
            <p><input type="text"></p>
          </li>
          <li>
            <p class="key">날짜</p>
            <p>
              <select>
                <option>2018.08.21</option>
              </select>
            </p>
          </li>
          <li class="memo">
            <p class="key">메모</p>
            <p><input type="text"></p>
          </li>
        </ul>

        <div class="consume-comment">
          <a href="#">이번달에 <em>#번 5300원</em> 소비하였네요</a>
        </div>

        <div class="btn-wrap col2">
          <a href="#">삭제</a>
          <a href="#" class="btn-solid">재설정</a>
        </div>
      </div>

    </section>

    <aside class="search-wrap">
      <div class="top">
        <button>카테고리</button>
        <a class="btn-setting"></a>
      </div>
      <div class="select-cate">
        <div class="cate-wrap">
          <ul>
            <li class="on">식비</li>
            <li>교육</li>
            <li>교통</li>
            <li>문화생활</li>
            <li>마트</li>
            <li>마트</li>
          </ul>
        </div>
        <div class="cate-wrap">
          <ul>
            <li class="on">식료품</li>
            <li>간식</li>
            <li>외식</li>
            <li>음료</li>
            <li>마트</li>
            <li>마트</li>
          </ul>
        </div>
      </div>
      <div class="action btn1">
        <a href="#" class="solid">확인</a>
      </div>
    </aside>
  </div>
</template>

<script>
export default {
  name: "ConsumeConsumeDetail",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "정보수정";
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
