package mybatis.guest.session;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import mybatis.guest.model.Comment;

public class CommentRepository {
	private final String namespace = "mybatis.guest.mapper.CommentMapper"; // Mapper 등록

	private SqlSessionFactory getSqlSessionFactory() {

		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream("mybatis-config.xml");
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
		SqlSessionFactory sessFactory = new SqlSessionFactoryBuilder().build(inputStream);
		return sessFactory;
	}
	//글 번호로 검색
	public Comment selectCommentByPrimaryKey(Long commentNo) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try {
			HashMap hashmap = new HashMap();
			hashmap.put("commentNo", commentNo);
			Comment comment = (Comment) sqlSession.selectOne(namespace + ".selectCommentByPrimaryKey", hashmap);
			return comment;
		} finally {
			sqlSession.close();
		}
	}
	
	//글 입력
	public Integer insertComment(Comment comment) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try {
			String statement = namespace + ".insertComment";
			int result = sqlSession.insert(statement, comment);
			if(result > 0 ) {
				sqlSession.commit();
			}else {
				sqlSession.rollback();
			}
			return result;
		}finally {
			sqlSession.close();
		}
		
	}
	//글 전체 가져오기(selectList() 이용)
	public List<Comment> selectComment(){
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try {
			return sqlSession.selectList(namespace+".selectCommentByCondition");
		}finally {
			sqlSession.close();
		}
		
	}
		
	//글 수정(업데이트)
	public Integer updateComment(Comment comment) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try {
			String statement = namespace + ".updateComment";
			int result = sqlSession.update(statement, comment);
			if(result > 0 ) {
				sqlSession.commit();
			}else {
				sqlSession.rollback();
			}
			return result;
		}finally {
			sqlSession.close();
		}
	}
	
	//글 삭제(delete)
	public Integer deleteComment(Long commentNo) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try {
			String statement = namespace + ".deleteComment";
			int result = sqlSession.delete(statement, commentNo);
			if(result > 0 ) {
				sqlSession.commit();
			}else {
				sqlSession.rollback();
			}
			return result;
		}finally {
			sqlSession.close();
		}
	}
	
	
}
