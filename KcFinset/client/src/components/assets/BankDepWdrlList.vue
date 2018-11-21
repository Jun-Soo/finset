<template>
  <div>
    <section>
      <div class="gray-search-box">
        <div class="search">
          <div class="left">
            <button class="on">1주일</button>
            <button>3개월</button>
            <button>6개월</button>
          </div>
          <div class="right">
            <button class="acco">조건검색</button>
          </div>
        </div>
        <div class="form">
          <p>은행계좌선택</p>
          <p>
            <select v-for="account in accountList" :key="account.index" :value="account.no_account">
              <!-- TODO accountList 가져올 때 하단에서 option명 '전체' 추가-->
              <option>{{account.nm_fc}}({{account.no_account}})</option>
            </select>
          </p>
        </div>
        <div class="wrap">
          <div class="date-pick">
            <p>
              <input type="text"><button></button>
            </p>
            <p>
              <input type="text"><button></button>
            </p>
          </div>
          <div class="btn-wrap mt20">
            <a class="solid blue">검색</a>
          </div>
        </div>
      </div>

      <div class="bank-detail noMG">
        <div class="select">
          <div class="left">
            <select v-model="actType" @change="searchList()">
              <option v-for="option in actTypeOptions" :key="option.index" :value="option.value">
                {{ option.text }}
              </option>
            </select>
          </div>
          <div class="right">
            <button class="btn-search"></button>
          </div>
        </div>

        <div class="inout">
          <div>
            <p class="key red">입금<em>(원)</em></p>
            <p class="number">4,350,000</p>
          </div>
          <div>
            <p class="key blue">출금<em>(원)</em></p>
            <p class="number">4,350,000</p>
          </div>
        </div>

        <div class="nobox-list">
          <p class="date">07.12</p>
          <div class="item">
            <div class="flex">
              <p><em class="circle red">준수</em><em>급여</em></p>
              <p><em class="number red">5,340,000</em>원</p>
            </div>
            <div class="flex">
              <p class="key">신한은행</p>
              <p class="key">116-01-01919</p>
            </div>
          </div>
          <div class="item">
            <div class="flex">
              <p><em class="circle blue">준수</em><em>급여</em></p>
              <p><em class="number blue">5,340,000</em>원</p>
            </div>
            <div class="flex">
              <p class="key">신한은행</p>
              <p class="key">116-01-01919</p>
            </div>
          </div>

          <p class="date">07.12</p>
          <div class="item">
            <div class="flex">
              <p><em class="circle red">준수</em><em>급여</em></p>
              <p><em class="number red">5,340,000</em>원</p>
            </div>
            <div class="flex">
              <p class="key">신한은행</p>
              <p class="key">116-01-01919</p>
            </div>
          </div>
          <div class="item">
            <div class="flex">
              <p><em class="circle blue">준수</em><em>급여</em></p>
              <p><em class="number blue">5,340,000</em>원</p>
            </div>
            <div class="flex">
              <p class="key">신한은행</p>
              <p class="key">116-01-01919</p>
            </div>
          </div>
        </div>
      </div>
    </section>

    <aside class="search-wrap">
      <div class="top">
        <button>검색</button>
      </div>
      <div class="wrap">
        <div class="hash">
          <a># 이체</a>
          <a># 국민대출</a>
          <a># 급여</a>
          <a># 황</a>
          <a># 통신비</a>
          <a># N빵</a>
          <a># 보험료</a>
          <a># 쇼파</a>
          <a># 신용카드</a>
          <a># 어머니 생신</a>
          <a># 입출금이자</a>
          <a># 자전거 계약금</a>
          <a># 대출</a>
          <a># 김수진</a>
          <a># 국민입금</a>
          <a># 공기청청기</a>
          <a># 집대출</a>
        </div>
      </div>
    </aside>
  </div>
</template>

<script>
export default {
  name: "AssetsAccountWdrlDetail",
  data() {
    return {
      accountList: [{ nm_fc: "전체", no_account: "" }],
      actTypeOptions: [
        { text: "전체", value: "01" },
        { text: "입금", value: "02" },
        { text: "출금", value: "03" }
      ],
      actType: "01"
    };
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "입출금내역";
  },
  created() {},
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    //목록 조회
    searchList: function() {
      var _this = this;
      _this.page = 1;
      Common.pagination(_this.listBankAct);
    },
    listBankAct: function(callback) {
      var _this = this;

      console.log("actType" + _this.actType);
      console.log("scKeyword" + _this.scKeyword);

      var formData = new FormData();
      formData.append("page", _this.page);
      formData.append("actType", _this.actType);
      formData.append("scKeyword", _this.scKeyword);

      this.$http
        .post("/m/news/listNews.json", formData)
        .then(function(response) {
          var actList = response.data.accountList;
          for (var i = 0; i < act.length; i++) {
            _this.accountList.append(act[i]);
          }

          //썸네일 이미지 셋팅
          var list = response.data.pagedList.source;
          for (var i = 0; i < list.length; i++) {
            if (list[i].seq_thum_file != null) {
              list[i].thumImg =
                "/m/news/getApiNewsImg.json?seq_news=" +
                list[i].seq_news +
                "&file_type=01";
            }
          }

          //pagination
          if (list.length === 0) {
            callback();
            _this.newsList = [];
            _this.seen = true;
            return;
          }
          //스크롤시 계속 페이지 추가되도록
          if (_this.page == 1) {
            _this.newsList = list;
          } else {
            for (var key in list) {
              _this.newsList.push(list[key]);
            }
          }
          _this.totalPage = response.data.pagedList.pageCount;
          _this.page++;
          //pagination

          _this.seen = true;
        })
        .catch(e => {
          _this.$toast.center(ko.messages.error);
        });
    }
  }
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang="scss">
</style>
