package com.biz.cbt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.biz.cbt.util.ResultSQL;
import com.biz.cbt.vo.ResultVO;

public interface ResultDao {
	
	@Select(ResultSQL.RE_SELECTALL)
	public List<ResultVO> selectAll();
	
	@Select(ResultSQL.RE_FINDBYUSER)
	public ResultVO findByUser(String re_user);
	
	@Insert(ResultSQL.RE_INSERT)
	public int insert(ResultVO vo);
	
	@Update(ResultSQL.RE_UPDATE)
	public int update(ResultVO vo);
	
	@Delete(ResultSQL.RE_DELETE)
	public int delete(String re_user);
	
}
