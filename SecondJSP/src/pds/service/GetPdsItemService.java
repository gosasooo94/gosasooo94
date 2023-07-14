package pds.service;

import java.sql.Connection;
import java.sql.SQLException;

import pds.model.PdsItem;
import pds.model.PdsItemDao;
import pds.model.jdbc.ConnectionProvider;
import pds.model.jdbc.JdbcUtil;


public class GetPdsItemService {

	private static GetPdsItemService instance = new GetPdsItemService();

	public static GetPdsItemService getInstance() {
		return instance;
	}

	private GetPdsItemService() {
	}

	public PdsItem getPdsItem(int id) throws PdsItemNotFoundException {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			PdsItem pdsItem = PdsItemDao.getInstance().selectById(conn, id);
			if (pdsItem == null) {
				throw new PdsItemNotFoundException("해당 게시물이 없습니다:" + id);
			}
			return pdsItem;
		} catch (Exception e) {
			throw new RuntimeException("DB 검색 실패: " + e.getMessage(), e);
		} finally {
			//JdbcUtil.close(conn);
		}
	}
}
