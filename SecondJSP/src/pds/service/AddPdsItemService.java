package pds.service;

import java.sql.Connection;
import java.sql.SQLException;

import pds.model.PdsItem;
import pds.model.PdsItemDao;
import pds.model.jdbc.ConnectionProvider;
import pds.model.jdbc.JdbcUtil;

public class AddPdsItemService {

	private static AddPdsItemService instance = new AddPdsItemService();
	public static AddPdsItemService getInstance() {
		return instance;
	}
	
	private AddPdsItemService() {
	}
	
	public PdsItem add(PdsItem pdsItem) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			int id = PdsItemDao.getInstance().insert(conn, pdsItem);
			if (id == -1) {
				JdbcUtil.rollback(conn);
				throw new RuntimeException("DB 입력 실퍠 ");
			}
			pdsItem.setId(id);
			
			conn.commit();
			
			return pdsItem;
		} catch (Exception e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.setAutoCommit(true);
				} catch (SQLException e) {
				}
			}
			//JdbcUtil.close(conn);
		}
	}
}
