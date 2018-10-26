<template>
  <div class="main">
    <draggable v-model="myArray" @start="drag=true" @end="drag=false">
      <div class="card" v-for="element in myArray" :key="element.order"><span class="left">=</span> {{element.name}}</div>
    </draggable>
  </div>
</template>

<script>
import draggable from 'vuedraggable'

export default {
  name: "ConsumeDraggable",
  data() {
    return {
      myArray: [
        {
          name: "Sortablejs",
          order: 8,
          fixed: false
        },
        {
          name: "for",
          order: 4,
          fixed: false
        },
        {
          name: "draggable",
          order: 2,
          fixed: false
        },
        {
          name: "on",
          order: 7,
          fixed: false
        },
        {
          name: "vue.draggable",
          order: 1,
          fixed: false
        },
        {
          name: "component",
          order: 3,
          fixed: false
        },
        {
          name: "based",
          order: 6,
          fixed: false
        },
        {
          name: "vue.js 2.0",
          order: 5,
          fixed: false
        }
      ]
    };
  },
  components: {
    draggable,
  },
  // computed () {
  // },
  beforeCreate() {},
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
<style scoped>
.card {
  width: 100%;
  height: 50px;
  text-align: center;
  border: 1px solid #000;
}
.left {
  float: left;
  font-size: 30px;
}
</style>
