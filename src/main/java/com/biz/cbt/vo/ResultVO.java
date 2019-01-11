package com.biz.cbt.vo;

public class ResultVO {
	
	private String re_user;
	private String re_qid;
	private String re_answer;
	
	public ResultVO() {
		super();
	}
	public ResultVO(String re_user) {
		super();
		this.re_user = re_user;
	}
	public ResultVO(String re_user, String re_qid, String re_answer) {
		super();
		this.re_user = re_user;
		this.re_qid = re_qid;
		this.re_answer = re_answer;
	}
	@Override
	public String toString() {
		return "ResultVO [re_user=" + re_user + ", re_id=" + re_qid + ", re_answer=" + re_answer + "]";
	}
	public String getRe_user() {
		return re_user;
	}
	public void setRe_user(String re_user) {
		this.re_user = re_user;
	}
	public String getRe_qid() {
		return re_qid;
	}
	public void setRe_qid(String re_qid) {
		this.re_qid = re_qid;
	}
	public String getRe_answer() {
		return re_answer;
	}
	public void setRe_answer(String re_answer) {
		this.re_answer = re_answer;
	}
	
}
