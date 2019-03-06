package com.koscom.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class PushEachInfo implements Serializable {

    private static final long serialVersionUID = 7930820139417625531L;

    private String no_person;	// 고객명
    private long seq_push;		// seq
    private String title;		// 제목
    private String body;		// 내용
    private String link_addr;   // 링크주소
    private String dt_reserv;   // 예약보내기시간
    private String yn_push;   // 푸쉬보낸여부
    private String yn_push2;   // 푸쉬보낼지여부(신용)
    private String yn_push3;   // 푸쉬보낼지여부(부채)
    private String yn_display;   // 마이페이지에 보여지는 여부
    private String yn_process;   // 처리여부
    private String yn_send;   // 푸쉬보낸여부
    private String id_frt;		// 작성자 아이디
    private String dt_frt;	    // 작성일
    private String cd_push;	    // 푸쉬타입
    private String push_divcd; //푸쉬구분
    private String fcm_token; //푸쉬구분
    private String yn_os; //운영체제여부

    private String id_lst = "";	//최종입력아이디
    private String dt_lst = "";	//최종입력날짜

    public long getSeq_push() {
        return seq_push;
    }

    public void setSeq_push(long seq_push) {
        this.seq_push = seq_push;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getLink_addr() {
        return link_addr;
    }

    public void setLink_addr(String link_addr) {
        this.link_addr = link_addr;
    }

    public String getId_frt() {
        return id_frt;
    }

    public void setId_frt(String id_frt) {
        this.id_frt = id_frt;
    }

    public String getDt_frt() {
        return dt_frt;
    }

    public void setDt_frt(String dt_frt) {
        this.dt_frt = dt_frt;
    }
    public String getNo_person() {
        return no_person;
    }

    public void setNo_person(String no_person) {
        this.no_person = no_person;
    }

    public String getDt_reserv() {
        return dt_reserv;
    }

    public void setDt_reserv(String dt_reserv) {
        this.dt_reserv = dt_reserv;
    }

    public String getCd_push() {
        return cd_push;
    }

    public void setCd_push(String cd_push) {
        this.cd_push = cd_push;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.MULTI_LINE_STYLE);
    }

    public String getYn_push() {
        return yn_push;
    }
    public void setYn_push(String yn_push) {
        this.yn_push = yn_push;
    }

    public String getYn_push2() {
        return yn_push2;
    }

    public void setYn_push2(String yn_push2) {
        this.yn_push2 = yn_push2;
    }

    public String getYn_push3() {
        return yn_push3;
    }

    public void setYn_push3(String yn_push3) {
        this.yn_push3 = yn_push3;
    }

    public String getYn_display() {
        return yn_display;
    }

    public void setYn_display(String yn_display) {
        this.yn_display = yn_display;
    }

    public String getYn_process() {
        return yn_process;
    }

    public void setYn_process(String yn_process) {
        this.yn_process = yn_process;
    }

    public String getYn_send() {
        return yn_send;
    }

    public void setYn_send(String yn_send) {
        this.yn_send = yn_send;
    }

    public String getPush_divcd() {
        return push_divcd;
    }

    public void setPush_divcd(String push_divcd) {
        this.push_divcd = push_divcd;
    }

    public String getFcm_token() {
        return fcm_token;
    }

    public void setFcm_token(String fcm_token) {
        this.fcm_token = fcm_token;
    }

    public String getYn_os() {
        return yn_os;
    }

    public void setYn_os(String yn_os) {
        this.yn_os = yn_os;
    }

    public String getId_lst() {
        return id_lst;
    }

    public void setId_lst(String id_lst) {
        this.id_lst = id_lst;
    }

    public String getDt_lst() {
        return dt_lst;
    }

    public void setDt_lst(String dt_lst) {
        this.dt_lst = dt_lst;
    }

}
