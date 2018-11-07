<template>
  <section>
    <div class="spend-top">
      <div class="date-wrap">
        <button class="prev"></button>
        <p>2018.07</p>
        <button class="next"></button>
        <button class="setting"></button>
      </div>
    </div>

    <div class="check-flex">
      <div>
        <button class="today">TODAY</button>
      </div>
      <div class="wrap">
        <button class="income">수입</button>
        <button class="debt">지출</button>
        <button class="loan">대출</button>
      </div>
    </div>

    <div class="calendar">
      <div class="wrap">
        <table>
          <caption></caption>
          <colgroup>
            <col width="14.28%">
            <col width="14.28%">
            <col width="14.28%">
            <col width="14.28%">
            <col width="14.28%">
            <col width="14.28%">
            <col width="14.28%">
          </colgroup>
          <thead>
            <tr>
              <th>SUN</th>
              <th>MON</th>
              <th>TUE</th>
              <th>WED</th>
              <th>THU</th>
              <th>FRI</th>
              <th>SAT</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>
                <a href="#">
                  <p class="day"><span>1</span></p>
                  <em class="debt">32,000</em>
                </a>
              </td>
              <td>
                <a href="#">
                  <p class="day"><span>2</span></p>
                  <em class="debt">32,000</em>
                </a>
              </td>
              <td>
                <a href="#">
                  <p class="day"><span>3</span></p>
                  <em class="debt">32,000</em>
                </a>
              </td>
              <td>
                <a href="#">
                  <p class="day"><span>4</span></p>
                  <em class="debt">32,000</em>
                </a>
              </td>
              <td>
                <a href="#">
                  <p class="day"><span>5</span></p>
                  <em class="debt">32,000</em>
                </a>
              </td>
              <td>
                <a href="#">
                  <p class="day"><span>6</span></p>
                  <em class="income">465,000</em>
                  <em class="debt">32,000</em>
                  <em class="loan">100,000,000</em>
                </a>
              </td>
              <td>
                <a href="#">
                  <p class="day"><span>7</span></p>
                  <em class="debt">32,000</em>
                </a>
              </td>
            </tr>
            <tr>
              <td>
                <a href="#">
                  <p class="day"><span>8</span></p>
                  <em class="debt">32,000</em>
                </a>
              </td>
              <td>
                <a href="#" class="on">
                  <p class="day"><span>9</span></p>
                  <em class="debt">32,000</em>
                </a>
              </td>
              <td>
                <a href="#">
                  <p class="day"><span>10</span></p>
                  <em class="debt">32,000</em>
                </a>
              </td>
              <td>
                <a href="#">
                  <p class="day"><span>11</span></p>
                  <em class="debt">32,000</em>
                </a>
              </td>
              <td>
                <a href="#">
                  <p class="day"><span>12</span></p>
                  <em class="debt">32,000</em>
                </a>
              </td>
              <td>
                <a href="#">
                  <p class="day"><span class="today">13</span></p>
                  <em class="debt">32,000</em>
                </a>
              </td>
              <td>
                <a href="#">
                  <p class="day"><span>14</span></p>
                  <em class="debt">32,000</em>
                </a>
              </td>
            </tr>
            <tr>
              <td>
                <a href="#">
                  <p class="day"><span>15</span></p>
                  <em class="debt">32,000</em>
                </a>
              </td>
              <td>
                <a href="#">
                  <p class="day"><span>16</span></p>
                  <em class="debt">32,000</em>
                </a>
              </td>
              <td>
                <a href="#">
                  <p class="day"><span>17</span></p>
                  <em class="debt">32,000</em>
                </a>
              </td>
              <td>
                <a href="#">
                  <p class="day"><span>18</span></p>
                  <em class="debt">32,000</em>
                </a>
              </td>
              <td>
                <a href="#">
                  <p class="day"><span>19</span></p>
                  <em class="debt">32,000</em>
                </a>
              </td>
              <td>
                <a href="#">
                  <p class="day"><span>20</span></p>
                  <em class="debt">32,000</em>
                </a>
              </td>
              <td>
                <a href="#">
                  <p class="day"><span>21</span></p>
                  <em class="debt">32,000</em>
                </a>
              </td>
            </tr>
            <tr>
              <td>
                <a href="#">
                  <p class="day"><span>22</span></p>
                  <em class="debt">32,000</em>
                </a>
              </td>
              <td>
                <a href="#">
                  <p class="day"><span>23</span></p>
                  <em class="debt">32,000</em>
                </a>
              </td>
              <td>
                <a href="#">
                  <p class="day"><span>24</span></p>
                  <em class="debt">32,000</em>
                </a>
              </td>
              <td>
                <a href="#">
                  <p class="day"><span>25</span></p>
                  <em class="debt">32,000</em>
                </a>
              </td>
              <td>
                <a href="#">
                  <p class="day"><span>26</span></p>
                  <em class="debt">32,000</em>
                </a>
              </td>
              <td>
                <a href="#">
                  <p class="day"><span>27</span></p>
                  <em class="debt">32,000</em>
                </a>
              </td>
              <td>
                <a href="#">
                  <p class="day"><span>28</span></p>
                  <em class="debt">32,000</em>
                </a>
              </td>
            </tr>
            <tr>
              <td>
                <a href="#">
                  <p class="day"><span>29</span></p>
                  <em class="debt">32,000</em>
                </a>
              </td>
              <td>
                <a href="#">
                  <p class="day"><span>30</span></p>
                  <em class="debt">32,000</em>
                </a>
              </td>
              <td>
                <a href="#">
                  <p class="day"><span>31</span></p>
                  <em class="debt">32,000</em>
                </a>
              </td>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
    <button class="btn-spend-add"></button>

  </section>
</template>

<script>
export default {
  name: "CommonMonthCal",
  data() {
    return {};
  },
  components: {},
  computed: {},
  beforeCreate() {
    this.$store.state.header.type = "sub";
    this.$store.state.title = "습관달력";
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
