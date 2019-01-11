package com.biz.cbt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.biz.cbt.util.CbtSQL;
import com.biz.cbt.vo.CbtVO;

public interface CbtDao {

	@Select(CbtSQL.CBT_SELECTALL)
	public List<CbtVO> selectAll();
	
	@Select(CbtSQL.CBT_FINDBYID)
	public CbtVO findByID(long cb_id);
	
	@Insert(CbtSQL.CBT_INSERT)
	public int insert(CbtVO vo);
	
	@Update(CbtSQL.CBT_UPDATE)
	public int update(CbtVO vo);
	
	@Delete(CbtSQL.CBT_DELETE)
	public int delete(long cb_id);
	
}
