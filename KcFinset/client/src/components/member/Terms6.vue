<template>
  <div id="wrapper" class="pop-full">
    <!-- Header  -->
    <div class="pop-top">
      <p class="title">{{title_p}}</p>
      <a class="btn-close" v-on:click="$emit('popclose')"></a>
    </div>
    <!-- Content -->
    <div class="container pop-wrap">
      <!-- <p class="date">{{date_p}}</p> -->
      <!-- tab1 v-for="explain in explains" v-bind:key="explain.value" -->
      <div id="tab1">
        <h2>{{subtitle_p}}</h2>
        <div v-for="article in articles" v-bind:key='article.value'>
          <h3>{{article.h3_c}}</h3>
          <p class="mt15" v-if="article.p_c && article.num!=8">{{article.p_c}}</p>
          <ol class="list-style03">
            <li class="mt15" v-for="listArg in listArgs" v-bind:key="listArg.key" v-if="article.num===listArg.num"> {{listArg.text}}</li>
            <!-- <ol class="list-style04">
							<li><span>{{listkey_p}}</span>{{sublistArg}}</li>
						</ol>	 -->
            <!-- <li>abcd</li>  -->
          </ol>
          <p class="mt15" v-if="article.p_c&& article.num===8">{{article.p_c}}</p>
        </div>
        <!-- <div>
				<h3>제12조 (개인정보의 처리)</h3>  
				<ol class="list-style03">
					<li>회사는 서비스 제공을 위하여 수집된 본인확인정보의 취급 및 관리 등의 업무를 스스로 수행함을 원칙으로 하나, 필요한 경우 아래 표와 같이 회사가 선정한 사업자에게 위탁할 수 있습니다.
						<div>
							<table>
								<caption>[개인정보의 취급 위탁]</caption>
								<colgroup>
									<col width="50%"><col width="50%">
								</colgroup>
								<thead>
									<tr>
										<th>수탁자</th>
										<th>위탁업무내용</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>서울신용평가정보(주)</td>
										<td>본인확인정보의 처리, 본인확인 업무대행</td>
									</tr>
									<tr>
										<td>한국모바일인증(주)</td>
										<td>본인확인 업무대행</td>
									</tr>
								</tbody>
							</table>
						</div>
					</li>
				</ol>
			</div> -->
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Terms6",
  data() {
    return {
      date_p: "",
      title_p: "",
      subtitle_p: "",
      articles: [], //조항
      //   explains,
      data: "",
      listArgs: [], //조항 안의 리스트 (조항num==listArg.num)
      //   sublistArgs,
      //   listkey_p,
      //   list: [
      //     {
      //       num: 2,
      //       h3_c: "",
      //       p_c: "",
      //       list_c: [
      //         { key: "", text: "", num: 2 },
      //         { key: "", text: "" },
      //         {
      //           key: "",
      //           text: "",
      //           sublist_c: [{ key: "", text: "" }, { key: "", text: "" }]
      //         }
      //       ]
      //     }

      //   ],
      groupList: []
    };
  },
  components: {},
  beforeCreate() {},
  created() {
    let _this = this;
    _this.data = _this.$parent.$parent.term_db;
    _this.title_p = _this.$parent.$parent.nm_code;
    _this.transfer(_this.data);
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  methods: {
    transfer: function(str) {
      let _this = this;
      let tmp = str;
      let tmplist = str.replace(/\\r/g, "\r").split("\\n");
      var num = 1;
      _this.subtitle_p = tmplist[0];
      for (var key in tmplist) {
        if (
          tmplist[key] == null ||
          tmplist[key] == "\r" ||
          parseInt(key) === tmplist.length - 1
        ) {
          //마지막 data 걸러내기
          continue;
        }
        //article
        if (tmplist[key].startsWith("제") && !tmplist[key].endsWith(".\r")) {
          //조항체크,
          let tmpJson = {};
          //tmplist[key].substring(1,2)
          tmpJson["num"] = num;
          tmpJson["h3_c"] = tmplist[key];
          //   tmpJson["list_c"] = [];

          for (
            var i = parseInt(key) + 1;
            tmplist[i].startsWith("제") == false;
            i++
          ) {
            //다음조항까지 거르기
            if (tmplist[i].indexOf("\\t") > 0) {
              var _key = tmplist[i].substring(0, tmplist[i].indexOf("\\t"));
              var _text = tmplist[i].substring(tmplist[i].indexOf("\\t") + 2);
              var tJson = {};
              tJson["key"] = _key;
              tJson["text"] = _text;
              tJson["num"] = num;
              //   tmpJson["list_c"].push(tJson);
              _this.listArgs.push(tJson);
            } else if (tmplist[i] == "\r") {
              break;
            } else if (tmpJson["p_c"]) {
              tmpJson["p_c"] += "\t" + tmplist[i];
            } else {
              tmpJson["p_c"] = tmplist[i];
            }
          }
          //   if (tmpJson["list_c"].length === 0) {
          //     delete tmpJson["list_c"];
          //   }
          //   else {
          //     _this.listArgs = tmpJson["list_c"];
          //   }
          _this.groupList.push(tmpJson);
          num++;
        } else if (tmplist[key].length < 10) {
          //부록 및 제목체크
          let tmpJson = {};
          tmpJson["num"] = num;
          tmpJson["h3_c"] = tmplist[key];
          //   tmpJson["list_c"] = [];
          for (var i = parseInt(key) + 1; tmplist[i] != "\r"; i++) {
            if (tmplist[i].indexOf("\\t") > 0) {
              var __key = tmplist[i].substring(0, tmplist[i].indexOf("\\t"));
              var __text = tmplist[i].substring(tmplist[i].indexOf("\\t") + 2);
              var ttJson = {};
              ttJson["key"] = __key;
              ttJson["text"] = __text;
              ttJson["num"] = num;
              //   tmpJson["list_c"].push(ttJson);
              _this.listArgs.push(ttJson);
            } else if (tmplist[i] == "\r") {
              break;
            } else {
              tmpJson["p_c"] = tmplist[i];
            }
          }
          //   if (tmpJson["list_c"].length === 0) delete tmpJson["list_c"];
          _this.groupList.push(tmpJson);
          num++;
        }
      } //for
      console.log(_this.groupList);
      console.log(_this.listArgs);
      _this.articles = _this.groupList;
      //   tmp = tmp.replace(/\\r/g, "\r");
      //   tmp = tmp.replace(/\\n/g, "\n");
      //   tmp = tmp.replace(/\\t/g, "\t");
      //   _this.explain = tmp;
    },

    makeListJson: function(n) {
      if (tmplist[i].indexOf("\\t") > 0) {
        var _key = tmplist[i].substring(0, tmplist[i].indexOf("\\t"));
        var _text = tmplist[n].substring(tmplist[i].indexOf("\\t") + 2);
        var tJson = {};
        tJson["key"] = _key;
        tJson["text"] = _text;
        tmpJson["list_c"].push(tJson);
      } else {
        tmpJson["p_c"] = tmplist[n];
      }
    }
  }
};
</script>

