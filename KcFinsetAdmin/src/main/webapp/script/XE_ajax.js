function AjaxRequest() {
	var req = {};
	
	req.timeout        = null;
	req.username       = null;
	req.password       = null;
	req.groupName      = null;
	req.responseText   = null;
	req.responseXML    = null;
	req.status         = null;
	req.statusText     = null;
	req.xmlHttpRequest = null;
	
	req.parameters   = {};	
	req.url          = window.location.href;
	req.method       = "GET";
	req.queryString  = "";

	req.async   = true;
	req.aborted = false;
	req.generateUniqueUrl = true;
	
	req.requestIndex = AjaxRequest.numAjaxRequests++;	

	// --------------
	// Event handlers
	// --------------
	req.onTimeout    = null; 
	req.onLoading    = null;
	req.onLoaded     = null;
	req.onComplete   = null;
	req.onSuccess    = null;
	req.onError      = null;
	req.onGroupBegin = null;
	req.onGroupEnd   = null;

	// Get the XMLHttpRequest object itself
	req.xmlHttpRequest = AjaxRequest.getXmlHttpRequest();
	if (req.xmlHttpRequest==null) { return null; }
	
	// -------------------------------------------------------
	// Attach the event handlers for the XMLHttpRequest object
	// -------------------------------------------------------
	req.xmlHttpRequest.onreadystatechange = 
	function() {
		if (req==null || req.xmlHttpRequest==null) { return; }
		if (req.xmlHttpRequest.readyState==1) { req.onLoadingInternal(req); }
		if (req.xmlHttpRequest.readyState==2) { req.onLoadedInternal(req); }
		if (req.xmlHttpRequest.readyState==4) { req.onCompleteInternal(req); }
	};
	
	req.onLoadingInternalHandled     = false;
	req.onLoadedInternalHandled      = false;
	req.onCompleteInternalHandled    = false;
	
	req.onLoadingInternal = 
		function() {
			if (req.onLoadingInternalHandled) { return; }
			AjaxRequest.numActiveAjaxRequests++;
			if (AjaxRequest.numActiveAjaxRequests==1 && typeof(window['AjaxRequestBegin'])=="function") {
				AjaxRequestBegin();
			}
			if (req.groupName!=null) {
				if (typeof(AjaxRequest.numActiveAjaxGroupRequests[req.groupName])=="undefined") {
					AjaxRequest.numActiveAjaxGroupRequests[req.groupName] = 0;
				}
				AjaxRequest.numActiveAjaxGroupRequests[req.groupName]++;
				if (AjaxRequest.numActiveAjaxGroupRequests[req.groupName]==1 && typeof(req.onGroupBegin)=="function") {
					req.onGroupBegin(req.groupName);
				}
			}
			if (typeof(req.onLoading)=="function") {
				req.onLoading(req);
			}
			req.onLoadingInternalHandled = true;
		};
	
	req.onLoadedInternal = 
		function() {
			if (req.onLoadedInternalHandled) { return; }
			if (typeof(req.onLoaded)=="function") {
				req.onLoaded(req);
			}
			req.onLoadedInternalHandled = true;
		};
	
	req.onCompleteInternal = 
		function() {
			if (req.onCompleteInternalHandled || req.aborted) { return; }
			req.onCompleteInternalHandled = true;
			AjaxRequest.numActiveAjaxRequests--;
			if (AjaxRequest.numActiveAjaxRequests==0 && typeof(window['AjaxRequestEnd'])=="function") {
				AjaxRequestEnd(req.groupName);
			}
			if (req.groupName!=null) {
				AjaxRequest.numActiveAjaxGroupRequests[req.groupName]--;
				if (AjaxRequest.numActiveAjaxGroupRequests[req.groupName]==0 && typeof(req.onGroupEnd)=="function") {
					req.onGroupEnd(req.groupName);
				}
			}
			req.status = req.xmlHttpRequest.status;
			req.statusText = req.xmlHttpRequest.statusText;
			req.responseText = req.xmlHttpRequest.responseText;
			req.responseXML = req.xmlHttpRequest.responseXML;
			if (typeof(req.onComplete)=="function") {
				req.onComplete(req);
			}
			if (req.xmlHttpRequest.status==200 && typeof(req.onSuccess)=="function") {
				req.onSuccess(req);
			}
			else if (typeof(req.onError)=="function") {
				req.onError(req);
			}

			// Clean up so IE doesn't leak memory
			delete req.xmlHttpRequest['onreadystatechange'];
			req.xmlHttpRequest = null;
		};
	
	req.onTimeoutInternal = 
		function() {
			if (req!=null && req.xmlHttpRequest!=null && !req.onCompleteInternalHandled) {
				req.aborted = true;
				req.xmlHttpRequest.abort();
				AjaxRequest.numActiveAjaxRequests--;
				if (AjaxRequest.numActiveAjaxRequests==0 && typeof(window['AjaxRequestEnd'])=="function") {
					AjaxRequestEnd(req.groupName);
				}
				if (req.groupName!=null) {
					AjaxRequest.numActiveAjaxGroupRequests[req.groupName]--;
					if (AjaxRequest.numActiveAjaxGroupRequests[req.groupName]==0 && typeof(req.onGroupEnd)=="function") {
						req.onGroupEnd(req.groupName);
					}
				}
				if (typeof(req.onTimeout)=="function") {
					req.onTimeout(req);
				}
			// Opera won't fire onreadystatechange after abort, but other browsers do. 
			// So we can't rely on the onreadystate function getting called. Clean up here!
			delete req.xmlHttpRequest['onreadystatechange'];
			req.xmlHttpRequest = null;
			}
		};

	// ----------------
	// Instance methods
	// ----------------
	req.process = 
		function() {
			if (req.xmlHttpRequest === null) return;
			// Some logic to get the real request URL
			if (req.generateUniqueUrl && req.method=="GET") {
				req.parameters["AjaxRequestUniqueId"] = new Date().getTime() + "" + req.requestIndex;
			}
			var content = null; // For POST requests, to hold query string
			for (var i in req.parameters) {
				if (req.queryString.length>0) { req.queryString += "&"; }
				req.queryString += encodeURIComponent(i) + "=" + encodeURIComponent(req.parameters[i]);
			}
			
			if (req.method=="GET" && req.queryString.length>0) 
					req.url += ((req.url.indexOf("?")>-1)?"&":"?") + req.queryString;
			
			req.xmlHttpRequest.open(req.method, req.url, req.async, req.username, req.password);
			
			if (req.method=="POST") {
				if (typeof(req.xmlHttpRequest.setRequestHeader)!="undefined") {
					req.xmlHttpRequest.setRequestHeader('Content-type', 'application/x-www-form-urlencoded; charset=UTF-8');
					req.xmlHttpRequest.setRequestHeader('Ajax', true);
				}
				content = req.queryString;
			}
			if (req.timeout>0) {
				setTimeout(req.onTimeoutInternal,req.timeout);
			}
			req.xmlHttpRequest.send(content);
		};

	req.handleArguments = 
		function(args) {
			for (var i in args) {
				// If the AjaxRequest object doesn't have a property which was passed, treat it as a url parameter
				if (typeof(req[i])=="undefined") {
					req.parameters[i] = args[i];
				}
				else {
					req[i] = args[i];
				}
			}
		};

	return req;
}

// ---------------------------------------
// Static methods of the AjaxRequest class
// ---------------------------------------

AjaxRequest.getXmlHttpRequest = function() {
	if (window.XMLHttpRequest) {
		return new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		try {
			return new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try {
				return new ActiveXObject("Microsoft.XMLHTTP");
			} catch (E) {
				return null;
			}
		}
	}
	else {
		return null;
	}
};

AjaxRequest.isActive = function() {
	return (AjaxRequest.numActiveAjaxRequests>0);
};

AjaxRequest.get = function(args) {
	AjaxRequest.doRequest("GET",args);
};

AjaxRequest.post = function(args) {
	AjaxRequest.doRequest("POST",args);
};

AjaxRequest.doRequest = function(method,args) {
	if (typeof args === "undefined" || args === null) return false; 
	var myRequest = new AjaxRequest();
	myRequest.method = method;
	myRequest.handleArguments(args);
	myRequest.process();
};

// -----------------------
// Static Class variables
// -----------------------
AjaxRequest.numAjaxRequests = 0;
AjaxRequest.numActiveAjaxRequests = 0;
AjaxRequest.numActiveAjaxGroupRequests = {};

