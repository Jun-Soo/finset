<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!-- Modal -->
<!-- <div class="modal fade" id="popModal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" data-keyboard="false" data-backdrop="static">
	<div class="modal-dialog" role="document" >
		<div class="modal-content" id="modal-content">
		</div>
	</div>
</div>

 -->

<!-- Modal -->
<div class="modal fade" id="popModal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" data-keyboard="false" data-backdrop="static">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="modalLabel"></h4>
			</div>
			<div class="modal-body-block">
				<!-- modal content -->
			</div>
		</div>
	</div>
</div>
<!-- Alert -->
<div class="modal fade" id="alertMsg" tabindex="-1" role="dialog" aria-labelledby="modalLabel" data-keyboard="false" data-backdrop="static">
	<div class="modal-dialog" role="document">
		<div class="alert-content" id="alertContent">
		    <div class="alert-body" id="alertText"></div>
		    <div class="alert-footer"> 
		    	<button type="button" class="btn btn-lg btn-default" data-dismiss="modal" data-val="N">아니오</button>
				<button type="button" class="btn btn-lg btn-default" data-dismiss="modal" data-val="Y">예</button>
		    </div>
		</div>
	</div>
</div>

<div class="modal fade" id="updateMsg" tabindex="-1" role="dialog" aria-labelledby="modalLabel" data-keyboard="false" data-backdrop="static">
	<div class="modal-dialog" role="document">
		<div class="alert-content" id="updateContent">
		    <div class="alert-body" id="updateText"></div>
		    <div class="alert-footer"> 
		    	<!-- <button type="button" class="btn btn-lg btn-default" data-dismiss="modal" data-val="N">아니오</button> -->
				<button type="button" class="btn btn-lg btn-default" data-dismiss="modal" data-val="Y">예</button>
		    </div>
		</div>
	</div>
</div>