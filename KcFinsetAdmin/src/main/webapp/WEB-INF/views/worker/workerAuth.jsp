<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<script type="text/javascript">
$(document).ready(function () {
	// toggle panel
	$(".toggle-panel").each(function(i){
		$(this).find(".h-sec > a").click(function(){
			$(this).toggleClass("closed");
			$(this).closest(".toggle-panel").find(".toggle-cont").toggle("blind", 200);
		});
	});
	
});
</script>
<c:if test="${!empty worker.id_emp}">

<div class="panel panel-default panel-sub toggle-panel">
	<div class="panel-heading">
		<h3 class="h-sec pull-left">
			<a href="#none"><span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span> 프로그램 권한관리</a>
		</h3>
	</div>
	<div class="panel-collapse toggle-cont">
		<form name="frmWorkerProgramAuth" id="frmWorkerProgramAuth">
		<input type="hidden" name="id_emp" value="${worker.id_emp}">
		<input type="hidden" name="id_program_list">
		<input type="hidden" name="id_program_add" id="id_program_add">
		<input type="hidden" name="id_program_sub" id="id_program_sub">
		<section class="tree-nav">
			<div class="tree-row">
				<div class="tree-cell" id="program">
				  <ul>
				  	<c:forEach var="main_program" items="${program}" varStatus="status">
				  	<c:if test="${ufn:isProgram(main_program.id_program,'','00','00')}">
				    <li id="${main_program.id_program}" data-jstree='{"opened":true}'>${ufn:getProgram(main_program.id_program,'NM')}
				      <ul>
				      	<c:forEach var="sub_program" items="${program}">
				      	<c:if test="${ufn:isProgram(sub_program.id_program,main_program.cd_system,ufn:getProgram(sub_program.id_program,'WORK'),'10')}">
				        <li id="${sub_program.id_program}" data-jstree='{"icon":"glyphicon glyphicon-folder-open"}'>${ufn:getProgram(sub_program.id_program,'NM')}
				        	<ul>
						   		<c:forEach var="child_program" items="${program}">
						      	<c:if test="${ufn:isProgram(child_program.id_program,main_program.cd_system,ufn:getProgram(sub_program.id_program,'WORK'),'20')}">
						        <li id="${child_program.id_program}" data-jstree='{"icon":"glyphicon glyphicon-tasks"}'>${ufn:getProgram(child_program.id_program,'NM')}</li>
						        </c:if>
						        </c:forEach>
							</ul>
				        </li>
				        </c:if>
				        </c:forEach>
				      	<c:forEach var="sub_program" items="${program}">
				      	<c:if test="${ufn:isProgram(sub_program.id_program,main_program.cd_system,ufn:getProgram(sub_program.id_program,'WORK'),'11')}">
				        <li id="${sub_program.id_program}" data-jstree='{"icon":"glyphicon glyphicon-folder-open"}'>${ufn:getProgram(sub_program.id_program,'NM')}
				        	<ul>
						   		<c:forEach var="child_program" items="${program}">
						      	<c:if test="${ufn:isProgram(child_program.id_program,main_program.cd_system,ufn:getProgram(sub_program.id_program,'WORK'),'21')}">
						        <li id="${child_program.id_program}" data-jstree='{"icon":"glyphicon glyphicon-tasks"}'>${ufn:getProgram(child_program.id_program,'NM')}</li>
						        </c:if>
						        </c:forEach>
							</ul>
				        </li>
				        </c:if>
				        </c:forEach>
				      </ul>
				    </li>
				    </c:if>
				    </c:forEach>
				  </ul>
				</div>
				<div class="tree-cell tree-btn">
					<button type="button" class="btn add" onclick="movePm('ADD')"><span class="glyphicon glyphicon-arrow-right" aria-hidden="true"></span><strong>추가</strong></button>
					<button type="button" class="btn del" onclick="movePm('SUB')"><span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span><strong>삭제</strong></button>
				</div>
				<div class="tree-cell" id="worker_program">
				  <ul>
				  	<c:forEach var="main_program" items="${workerProgramList}" varStatus="status">
				  	<c:if test="${ufn:isProgram(main_program.id_program,'','00','00')}">
				    <li id="${main_program.id_program}" data-jstree='{"opened":true}'>${ufn:getProgram(main_program.id_program,'NM')}
				      <ul>
				      	<c:forEach var="sub_program" items="${workerProgramList}">
				      	<c:if test="${ufn:isProgram(sub_program.id_program,ufn:getProgram(main_program.id_program,'SYSTEM'),ufn:getProgram(sub_program.id_program,'WORK'),'10')}">
				        <li id="${sub_program.id_program}" data-jstree='{"icon":"glyphicon glyphicon-folder-open"}'>${ufn:getProgram(sub_program.id_program,'NM')}
					        <ul>
					      	<c:forEach var="child_program" items="${workerProgramList}">
					      	<c:if test="${ufn:isProgram(child_program.id_program,ufn:getProgram(main_program.id_program,'SYSTEM'),ufn:getProgram(sub_program.id_program,'WORK'),'20')}">
					        <li id="${child_program.id_program}" data-jstree='{"icon":"glyphicon glyphicon-tasks"}'>${ufn:getProgram(child_program.id_program,'NM')}</li>
					        </c:if>
					        </c:forEach>
					        </ul>
				        </li>
				        </c:if>
				        </c:forEach>
				      	<c:forEach var="sub_program" items="${workerProgramList}">
				      	<c:if test="${ufn:isProgram(sub_program.id_program,ufn:getProgram(main_program.id_program,'SYSTEM'),ufn:getProgram(sub_program.id_program,'WORK'),'11')}">
				        <li id="${sub_program.id_program}" data-jstree='{"icon":"glyphicon glyphicon-folder-open"}'>${ufn:getProgram(sub_program.id_program,'NM')}
					        <ul>
					      	<c:forEach var="child_program" items="${workerProgramList}">
					      	<c:if test="${ufn:isProgram(child_program.id_program,ufn:getProgram(main_program.id_program,'SYSTEM'),ufn:getProgram(sub_program.id_program,'WORK'),'21')}">
					        <li id="${child_program.id_program}" data-jstree='{"icon":"glyphicon glyphicon-tasks"}'>${ufn:getProgram(child_program.id_program,'NM')}</li>
					        </c:if>
					        </c:forEach>
					        </ul>
				        </li>
				        </c:if>
				        </c:forEach>
				      </ul>
				    </li>
				    </c:if>
				    </c:forEach>
				  </ul>
				</div>
			</div>
		</section>
		<div id="event_program_add"></div>
		<div id="event_program_sub"></div>
		
		</form>
	</div>
</div>


<script>
$(function() {
	  $('#program').jstree({
		'plugins': ["default", "checkbox"],
	    'core': {
	        'themes': {
	            'name': 'default',
	            'responsive': true
	        }
	    }
	  });
	  $('#worker_program').jstree({
		'plugins': ["default", "checkbox"],
	    'core': {
	        'themes': {
	            'name': 'default',
	            'responsive': true
	        }
	    }
	  });
	  
	  $('#approval').jstree({
		'plugins': ["default", "checkbox"],
	    'core': {
	        'themes': {
	            'name': 'default',
	            'responsive': true
	        }
	    }
	  });
	  $('#worker_approval').jstree({
		'plugins': ["default", "checkbox"],
	    'core': {
	        'themes': {
	            'name': 'default',
	            'responsive': true
	        }
	    }
	  });
	  
	  $('#program')
		// listen for event
		.on('changed.jstree', function (e, data) {
		  var i, j, r = [],id_r = [];
		  for(i = 0, j = data.selected.length; i < j; i++) {
		    r.push(data.instance.get_node(data.selected[i]).text);
		    id_r.push(data.instance.get_node(data.selected[i]).id);
		  }
		  $('#event_program_add').html('추가: ' + r.join(', '));
		  $('#id_program_add').val(id_r.join(','));
		})
		// create the instance
		.jstree();
	  
	  $('#worker_program')
		// listen for event
		.on('changed.jstree', function (e, data) {
		  var i, j, r = [],id_r = [];
		  for(i = 0, j = data.selected.length; i < j; i++) {
		    r.push(data.instance.get_node(data.selected[i]).text);
		    id_r.push(data.instance.get_node(data.selected[i]).id);
		  }
		  $('#event_program_sub').html('삭제: ' + r.join(', '));
		  $('#id_program_sub').val(id_r.join(','));
		})
		// create the instance
		.jstree();
	  
	  $('#approval')
		// listen for event
		.on('changed.jstree', function (e, data) {
		  var i, j, r = [],id_r = [];
		  for(i = 0, j = data.selected.length; i < j; i++) {
		    r.push(data.instance.get_node(data.selected[i]).text);
		    id_r.push(data.instance.get_node(data.selected[i]).id);
		  }
		  $('#event_appr_add').html('추가: ' + r.join(', '));
		  $('#id_appr_add').val(id_r.join(','));
		})
		// create the instance
		.jstree();
	  
	  $('#worker_approval')
		// listen for event
		.on('changed.jstree', function (e, data) {
		  var i, j, r = [],id_r = [];
		  for(i = 0, j = data.selected.length; i < j; i++) {
		    r.push(data.instance.get_node(data.selected[i]).text);
		    id_r.push(data.instance.get_node(data.selected[i]).id);
		  }
		  $('#event_appr_sub').html('삭제: ' + r.join(', '));
		  $('#id_appr_sub').val(id_r.join(','));
		})
		// create the instance
		.jstree();
	  
	});
</script>
</c:if>