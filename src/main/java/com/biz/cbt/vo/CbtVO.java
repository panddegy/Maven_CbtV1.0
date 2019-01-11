package com.biz.cbt.vo;

public class CbtVO {

	private long cb_id;
	private String cb_question;
	private String cb_example;
	private String cb_answer;
	
	public CbtVO() {
		super();
	}
	public CbtVO(String cb_question, String cb_example, String cb_answer) {
		super();
		this.cb_question = cb_question;
		this.cb_example = cb_example;
		this.cb_answer = cb_answer;
	}
	public CbtVO(long cb_id, String cb_question, String cb_example, String cb_answer) {
		super();
		this.cb_id = cb_id;
		this.cb_question = cb_question;
		this.cb_example = cb_example;
		this.cb_answer = cb_answer;
	}
	public long getCb_id() {
		return cb_id;
	}
	public void setCb_id(long cb_id) {
		this.cb_id = cb_id;
	}
	public String getCb_question() {
		return cb_question;
	}
	public void setCb_question(String cb_question) {
		this.cb_question = cb_question;
	}
	public String getCb_example() {
		return cb_example;
	}
	public void setCb_example(String cb_example) {
		this.cb_example = cb_example;
	}
	public String getCb_answer() {
		return cb_answer;
	}
	public void setCb_answer(String cb_answer) {
		this.cb_answer = cb_answer;
	}
	@Override
	public String toString() {
		return "CbtVO [cb_id=" + cb_id + ", cb_question=" + cb_question + ", cb_example=" + cb_example + ", cb_answer="
				+ cb_answer + "]";
	}
					
}
