<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- JQ-PLOT의 CSS를 설정 -->
<link class="include" href='<c:url value="/jqplot/jquery.jqplot.min.css" />' rel="stylesheet" type="text/css" />
<!-- JQ-PLOT의 기본 설정 -->
<script src='<c:url value="/jqplot/jquery.jqplot.min.js" />' type="text/javascript"></script>
<!-- 축의 데이터의 Label Option을 설정 -->
<script src='<c:url value="/jqplot/plugins/jqplot.canvasAxisLabelRenderer.js"/>' type="text/javascript"></script>
<!-- Highlighter(마우스 접근시 데이터정보 표시) 설정 -->
<script src='<c:url value="/jqplot/plugins/jqplot.highlighter.js"/>' type="text/javascript"></script>
<!-- Legend(Line에대한 간단한 범례)의 Option을 설정 -->
<script src='<c:url value="/jqplot/plugins/jqplot.enhancedLegendRenderer.js"/>' type="text/javascript"></script>
<!-- 축의 데이터 표현설정과 그래프위의 점의 Option을 설정 -->
<script src='<c:url value="/jqplot/plugins/jqplot.canvasAxisTickRenderer.js"/>' type="text/javascript"></script> 
<!-- 좌표에 관한 정보나 Zoom 기능 사용시 설정 -->
<script src='<c:url value="/jqplot/plugins/jqplot.cursor.js"/>' type="text/javascript"></script>
<!-- 축의 데이터를 순서에 상관없이 자동정렬을 설정 -->
<script src='<c:url value="/jqplot/plugins/jqplot.categoryAxisRenderer.js"/>' type="text/javascript"></script>
<script src='<c:url value="/jqplot/plugins/jqplot.pieRenderer.min.js"/>' type="text/javascript"></script>
<script src='<c:url value="/jqplot/plugins/jqplot.pointLabels.min.js"/>' type="text/javascript"></script>