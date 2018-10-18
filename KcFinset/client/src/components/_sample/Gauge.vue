<template>
  <div id="container">
    <svg :viewBox="viewBoxDimensions" preserveAspectRatio="xMidYMid meet">
      <!-- background circle -->
      <path
        id="backgroundBar"
        :d="backgroundBarPath"
        :stroke-width="strokeWidth"
        fill="none"
      />

      <!-- data circle -->
      <path
        id="dataBar"
        :d="dataBarPath"
        :stroke-width="strokeWidth"
        fill="none"
      />
      
      <!-- hide bottom half of circle-->
      <rect id="clipRectangle"
        :x="rectOffsetX"
        :y="centerY"
        :width="rectWidth"
        :height="rectHeight"
      />

      <!-- beautify -->
      <circle id="leftEndPoint" :cx="leftX" :cy="centerY" :r="beautifyCircleRadius"/>
      <circle id="rightEndPoint" :cx="rightX" :cy="centerY" :r="beautifyCircleRadius"/>

      <!-- data point (beautify) -->
      <circle id="dataPoint" :cx="leftX" :cy="centerY" :r="beautifyCircleRadius"/>

      <text id="centerText" text-anchor="middle" :x="centerX" :y="centerY">
        {{ this.text }}
      </text>

      <!-- dataBar animation -->
      <animateTransform
        ref="dataBarAnimate"
        xlink:href="#dataBar"
        attributeName="transform"
        type="rotate"
        :from="rotateFrom"
        :to="rotateTo"
        dur="2s"
        begin="0s"
        fill="freeze"
        keySplines="0.1 0.8 0.2 1;"
        keyTimes="0; 1"
        calcMode="spline"
      />
      <!-- dataBar point animation -->
      <animateTransform
        ref="dataPointAnimate"
        xlink:href="#dataPoint"
        attributeName="transform"
        type="rotate"                                     
        :from="rotateFrom"
        :to="rotateTo"
        dur="2s"
        begin="0s"
        fill="freeze"
        keySplines="0.1 0.8 0.2 1;"
        keyTimes="0; 1"
        calcMode="spline"
      />
    </svg>
  </div>
</template>

<script>
export default {
  name: 'GaugeCircle',
  data: function() {
    return {
      // viewBox 설정시, svg 안에 모든 element들은 실제 크기와 별개로 아래 (width/height)을 기준으로 한다.
      // 실제 svg 크기를 지정하려면 아래 #container의 width/height 를 변경.
      viewBoxX: 0,        // svg x-offset
      viewBoxY: 0,        // svg y-offset
      viewBoxWidth: 100,  // svg안 viewBox의 width.
      viewBoxHeight: 50,  // svg안 viewBox의 height.
      radius: 50,         // 게이지 반지름.
      strokeWidth: 5      //게이지 두께.
    }
  },
  props: {
    value: { // 게이지 값. 0~1.
      type: Number,
      default: .95,
    },
    text: { // 게이지 중앙에 보이는 텍스트.
      type: String,
      default: '2 등급'
    }
  },
  computed: {
    viewBoxDimensions() {
      let xOffset = this.viewBoxX-this.strokeWidth/2;
      let yOffset = this.viewBoxY-this.strokeWidth/2;
      let boxWidth = this.viewBoxWidth+this.strokeWidth;
      let boxHeight = this.viewBoxHeight+this.strokeWidth;
      // 위 viewBoxWidth/Height 는 게이지 두께의 중앙 지점을 기준으로함.
      // => 게이지의 바깥쪽 부분이 잘려보일 수 있음
      // => 게이지의 두께만큼 viewbox를 늘려야함
      // ==> xOffset = 좌로 두께의 반 만큼 당김
      // ==> yOffset = 위로 두께의 반 만큼 당김
      // ==> boxWidth = width를 두께 만큼 (좌로 .5, 우로 .5) 만큼 늘림
      // ==> boxheight = height를 두께 만큼 (위로 .5 아래로 .5) 만큼 늘림
      return [xOffset, yOffset, boxWidth, boxHeight].join(" ");
    },
    backgroundBarPath() {
      let startingPoint = 'M' + this.leftX + ',' + this.centerY;
      let arc = 'a1,1 0 0 1' + this.radius * 2 + ',0';
      // e.g. M0,0 a1,1 0 0 1 100,0'
      // M0,0 = 0,0 으로 포인터 옮김
      // a = arc 를 그림 (현재 포인터 기준)
      // 1,1 = 가로 1, 세로1 비율
      // 0 = ??
      // 0 = ??
      // 1 = x-axis로 위아래 반전
      // 100,0 끝나는 좌표
      return startingPoint + ' ' + arc;
    },
    dataBarPath() {
      let startingPoint = 'M' + this.leftX + ',' + this.centerY;
      let arc = 'a1,1 1 0 0' + this.radius * 2 + ',0';
      return startingPoint + ' ' + arc;
    },
    centerX() {
      return this.viewBoxWidth / 2;
    },
    centerY() {
      return this.viewBoxHeight;
    },
    leftX() {
      return this.centerX - this.radius;
    },
    rightX() {
      return this.centerX + this.radius;
    },
    beautifyCircleRadius() {
      return this.strokeWidth / 2;
    },
    rectOffsetX() {
      return this.strokeWidth * -1/2;
    },
    rectWidth() {
      return this.viewBoxWidth + this.strokeWidth * 2;
    },
    rectHeight() {
      return this.radius + this.strokeWidth;
    },
    rotateAngle() {
      let angle = this.value * 180;
      return angle;
    },
    rotateFrom() {
      return [0, this.centerX, this.centerY].join(" ");
    },
    rotateTo() {
      let angle = 180 * this.value;
      return [angle, this.centerX, this.centerY].join(" ");
    }
  }
}
</script>

<style scoped>
#container {
  /* width: 400px; */
  width: 400px;
  margin: auto;
  padding: 0;
  --backgroundColor: #ffffff; /* svg 백그라운드 색 */
  --backgroundBarColor: #D2D3D7; /* 게이지 기본 색 */
  --dataBarColor: #FC6E6D; /* 게이지 data 색 */
}

svg {
  width: 100%;
  height: 100%;
  background-color: var(--backgroundColor);
}

#backgroundBar { /* background circle */
  stroke: var(--backgroundBarColor);
}

#dataBar { /* data circle */
  stroke: var(--dataBarColor);
  clip-path: url(#clipRectangle);
}

#clipRectangle { /* dataBar 가림용 */
  fill: var(--backgroundColor);
}

#leftEndPoint { /* backgroundBar 왼쪽 끝 포인트, dataBar 와 같은 색. */
  fill: var(--dataBarColor);
}
#rightEndPoint { /* backgroundBar 오른쪽 끝 포인트, backgroundBar와 같은 색. */
  fill: var(--backgroundBarColor);
}

#dataPoint {
  fill: var(--dataBarColor);
}

#centerText {
  font-weight: normal;
  font-size: 15pt;
  fill: #000000;
}
</style>