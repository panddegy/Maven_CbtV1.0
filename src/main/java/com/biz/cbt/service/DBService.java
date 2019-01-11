package com.biz.cbt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import com.biz.cbt.dao.CbtDao;
import com.biz.cbt.dao.ResultDao;
import com.biz.cbt.util.CbtDataSource;
import com.biz.cbt.vo.CbtVO;
import com.biz.cbt.vo.ResultVO;

public class DBService {

	SqlSessionFactory sqlSessionFactory;
	
	public DBService() {
		// TODO Auto-generated constructor stub
		
		String driver="oracle.jdbc.driver.OracleDriver";
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String user="cbtuser";
		String password="1234";
		
		Properties props=new Properties();
		
		props.put("DRIVER", driver);
		props.put("URL", url);
		props.put("USER", user);
		props.put("PASSWORD", password);
		
		CbtDataSource cbtDS=new CbtDataSource();
		
		cbtDS.setProperties(props);
		
		DataSource dataSource=cbtDS.getDataSource();
		
		TransactionFactory transactionFactory=new JdbcTransactionFactory();
		
		Environment env=new Environment("CbtEnv", transactionFactory, dataSource);
		
		Configuration config=new Configuration(env);
		config.addMapper(CbtDao.class);
		config.addMapper(ResultDao.class);
		
		this.sqlSessionFactory=new SqlSessionFactoryBuilder().build(config); 
		
	}
	
	public List<CbtVO> selectAllCbt(){
		
		SqlSession session=this.sqlSessionFactory.openSession();
		CbtDao cDao=session.getMapper(CbtDao.class);
		
		List<CbtVO> cbtList=new ArrayList();
		
		cbtList=cDao.selectAll();
		
		return cbtList;
	}
	
	public List<ResultVO> selectAllResult(){
		
		SqlSession session=this.sqlSessionFactory.openSession();
		ResultDao rDao=session.getMapper(ResultDao.class);
		
		List<ResultVO> resultList=new ArrayList();
		
		resultList=rDao.selectAll();
		
		return resultList;
	}
	
	public CbtVO findByIDCbt(long cb_id) {
		
		SqlSession session=this.sqlSessionFactory.openSession();
		CbtDao cDao=session.getMapper(CbtDao.class);
		
		CbtVO vo=cDao.findByID(cb_id);
		
		return vo;
		
	}
	
	public ResultVO findByUserResult(String re_user) {
		
		SqlSession session=this.sqlSessionFactory.openSession();
		ResultDao rDao=session.getMapper(ResultDao.class);
		
		ResultVO vo=rDao.findByUser(re_user);
		
		return vo;
	}
	
	public int insertCbt(CbtVO vo) {
		
		SqlSession session=this.sqlSessionFactory.openSession();
		CbtDao cDao=session.getMapper(CbtDao.class);
		
		int ret=cDao.insert(vo);
		session.commit();
		session.close();
		return ret;
	}
	
	public int insertResult(ResultVO vo) {
		
		SqlSession session=this.sqlSessionFactory.openSession();
		ResultDao rDao=session.getMapper(ResultDao.class);
		
		int ret=rDao.insert(vo);
		session.commit();
		session.close();
		return ret;
	}
	
	public int updateCbt(CbtVO vo) {
		
		SqlSession session=this.sqlSessionFactory.openSession();
		CbtDao cDao=session.getMapper(CbtDao.class);
		
		int ret=cDao.update(vo);
		session.commit();
		session.close();
		return ret;
	}
	
	public int updateResult(ResultVO vo) {
		
		SqlSession session=this.sqlSessionFactory.openSession();
		ResultDao rDao=session.getMapper(ResultDao.class);
		
		int ret=rDao.update(vo);
		session.commit();
		session.close();
		return ret;
	}
	
	public int deleteCbt(long cb_id) {
		
		SqlSession session=this.sqlSessionFactory.openSession();
		CbtDao cDao=session.getMapper(CbtDao.class);
		
		int ret=cDao.delete(cb_id);
		session.commit();
		session.close();
		return ret;
	}
	
	public int deleteResult(String re_user) {
		
		SqlSession session=this.sqlSessionFactory.openSession();
		ResultDao rDao=session.getMapper(ResultDao.class);
		
		int ret=rDao.delete(re_user);
		session.commit();
		session.close();
		return ret;
	}
	
}






















