package com.biz.cbt.util;

public class CbtSQL {

	public static final String CBT_SELECTALL=" select * from tbl_cbt ";
	public static final String CBT_FINDBYID=" select * from tbl_cbt where cb_id=#{cb_id} ";
	public static final String CBT_INSERT=" insert into tbl_cbt "
			+ " values(seq_cbt.nextval, #{cb_question}, #{cb_answer}, #{cb_example}) ";
	public static final String CBT_UPDATE=" update tbl_cbt "
			+ " set cb_question=#{cb_question}, cb_answer=#{cb_answer}, cb_example=#{cb_example} where cb_id=#{cb_id} ";
	public static final String CBT_DELETE=" delete from tbl_cbt where cb_id=#{cb_id} " ;
	
}
