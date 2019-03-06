package com.koscom.oppf;

/**
 * Created by lee on 2017-10-20.
 */

public class OppfToken {
	
    private String access_token;
    private String token_type;
    private String expires_in;
    private String refresh_token;
    private String scope;
    private String expires_date;
    
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getToken_type() {
		return token_type;
	}
	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}
	public String getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}
	public String getRefresh_token() {
		return refresh_token;
	}
	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getExpires_date() {
		return expires_date;
	}
	public void setExpires_date(String expires_date) {
		this.expires_date = expires_date;
	}
}
