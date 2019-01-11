package com.biz.cbt.util;

public class ResultSQL {

	public static final String RE_SELECTALL=" select * from tbl_result ";
	public static final String RE_FINDBYUSER=" select * from tbl_result where re_user=#{re_user} ";
	public static final String RE_INSERT=" insert into tbl_result "
			+ " values(#{re_user}, #{re_qid}, #{re_answer}) ";
	public static final String RE_UPDATE=" update tbl_result "
			+ " set re_qid=#{re_qid}, re_answer=#{re_answer} where re_user=#{re_user} ";
	public static final String RE_DELETE=" delete from tbl_result where re_user=#{re_user} " ;
	
}
