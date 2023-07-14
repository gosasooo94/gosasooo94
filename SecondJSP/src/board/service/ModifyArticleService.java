package board.service;

import board.model.*;

public class ModifyArticleService {
	
	private static ModifyArticleService instance;
	public static ModifyArticleService getInstance()  throws BoardException{
		if( instance == null )
		{
			instance = new ModifyArticleService();
		}
		return instance;
	}
	
	public int update( BoardRec rec ) throws BoardException{
		
		// DB update
		int result = BoardDao.getInstance().update(rec);
	
		return result;
		
	}
}
