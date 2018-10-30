<template>
    <section>
        <div class="con-top goal-top">
            <p><em>잠깐 !!</em><br>예산 사용 통계를 확인해<br>보시고 설정하세요</p>
        </div>
        
        <div class="tab">
            <div class="wrap">
                <a href="#" class="on">분류별</a>
                <a href="#">수단별</a>
            </div>
        </div>
        
        <div class="box-list noMG">
            
            <div class="filter-wrap">
                <div class="filter blue">
                    <input type="checkbox" id="chk1"><label for="chk1">3개월평균</label>
                </div>
                <div class="filter blue">
                    <input type="checkbox" id="chk2"><label for="chk2">전월사용</label>
                </div>
                <div class="filter blue">
                    <input type="checkbox" id="chk3"><label for="chk3">사용자 지정</label>
                </div>
            </div>
            
            <div class="goal-list-wrap">
                <dl>
                    <dt class="sum">
                        <p class="title">합계</p>
                        <p><input type="text"></p>
                    </dt>
                    <dd>
                        <p>교통/차량</p>
                        <p><input type="text"></p>
                    </dd>
                    <dd>
                        <p>문화/생활</p>
                        <p><input type="text"></p>
                    </dd>
                    <dd>
                        <p>마트/편의점</p>
                        <p><input type="text"></p>
                    </dd>
                    <dd>
                        <p>패션/미용</p>
                        <p><input type="text"></p>
                    </dd>
                    <dd>
                        <p>생활용품</p>
                        <p><input type="text"></p>
                    </dd>
                </dl>
            </div>
            
        </div>
        
        <div class="btn-wrap float">
                <a href="#" class="solid blue box">검색</a>
        </div>
    
    </section>
</template>

<script>
export default {
  name: "ConsumeRegGoal",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "예산설정";
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
