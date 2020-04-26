package dao.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import vo.service.accountVO;
import vo.service.serviceVO;

public class ServiceDAO {
	SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		System.out.println("loginDAO 생성자 생성");
		this.sqlSession = sqlSession;
	}
	
	public int insert(serviceVO vo) {
		int result = sqlSession.insert("service.service_insert",vo);
		return result;
	}
	
	public List<serviceVO> search(Map<String, String> map) {
		List<serviceVO> list = sqlSession.selectList("service.service_searchlist", map);
		return list;
	}
	
	public serviceVO selectOne(String busi_num) {
		int num = Integer.parseInt(busi_num); 
		return sqlSession.selectOne("service.service_modify", num);
	}
	
	public int modify_update(serviceVO ovo) {
		int res = sqlSession.update("service.service_modify_update",ovo);
		return res;
	}
	
	public int delete(String busi_num) {
//		int num= Integer.parseInt(busi_num);
		int res = sqlSession.delete("service.service_delete", busi_num);
		return res;
	}
	public int account_delete(String busi_num) {
//		int num = Integer.parseInt(busi_num); 
		if(busi_num.length() <= 20) {
			for(int i = 0; i <= 20; i++) {
				
			}
			System.out.println(busi_num.length());
		}
		int res = sqlSession.delete("service.account_delete", busi_num);
		
		return res;
	}
	
	public int account_insert(accountVO vo) {
		return sqlSession.insert("service.account_insert",vo);
	}
}
