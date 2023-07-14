package mybatis.guest.service;

import java.util.List;

import mybatis.guest.model.Comment;
import mybatis.guest.session.CommentRepository;

public class CommentService {
	private static CommentService service;

	public static CommentService getInstance() {
		if (service == null)
			service = new CommentService();
		return service;
	}

	private CommentRepository repo = new CommentRepository();
	// 글가져오기
	public Comment selectCommentByPrimaryKey(Long commentNo) {
		return repo.selectCommentByPrimaryKey(commentNo);
	}
	// 글 입력
	public Integer insertComment(Comment comment) {
		return repo.insertComment(comment);
	}
	
	public List<Comment> selectComment(){
	      return repo.selectComment();
	   }
	   
	public Integer deleteComment(Long commentNo) {
	      return repo.deleteComment(commentNo);
	   }
	public Integer updateComment(Comment comment) {
	      return repo.updateComment(comment);
	   }
	
}
