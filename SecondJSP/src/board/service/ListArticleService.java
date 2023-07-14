package board.service;

import java.util.List;

import board.model.BoardDao;
import board.model.BoardException;
import board.model.BoardRec;

public class ListArticleService {
	private int totalRecCount;		// 전체 레코드 수	
	private int pageTotalCount;		// 전체 페이지 수
	private int COUNT_PER_PAGE = 5;	// 한페이지당 레코드 수
	private static ListArticleService instance;
	
	public static ListArticleService getInstance()  throws BoardException{
		if( instance == null )
		{
			instance = new ListArticleService();
		}
		return instance;
	}
	
	public List <BoardRec> getArticleList() throws BoardException
	{
		// 
		 List <BoardRec> mList = BoardDao.getInstance().selectList();			
		return mList;
	}
	private ListArticleService() {
		// TODO Auto-generated constructor stub
	}
	
	public int getTotalCount() throws BoardException {		
		
		int totalRecordCount = BoardDao.getInstance().getTotalCount();
		pageTotalCount = totalRecordCount / COUNT_PER_PAGE;
		if( totalRecordCount%COUNT_PER_PAGE > 0) pageTotalCount++;
		return pageTotalCount;
	}
	
	//페이지 번호에 따른 레코드 가져오기
	public List <BoardRec> getMessageList(int pNum) throws BoardException{
		
		int pageNum = 1;
		int firstRow = (pNum-1)*COUNT_PER_PAGE + 1; //현재 페이지에서 보여줄 시작 레코드
		int endRow = pNum * COUNT_PER_PAGE;	//현재 페이지에서 보여줄 마지막 레코드
		
		List<BoardRec> mList = BoardDao.getInstance().selectList(firstRow, endRow);
		return mList;
	}
		
}
