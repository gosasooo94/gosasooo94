package pds.service;

import java.sql.Connection;

import pds.model.PdsItemDao;
import pds.model.jdbc.ConnectionProvider;
import pds.model.jdbc.JdbcUtil;


public class IncreaseDownloadCountService {
	private static IncreaseDownloadCountService instance = new IncreaseDownloadCountService();

	public static IncreaseDownloadCountService getInstance() {
		return instance;
	}

	private IncreaseDownloadCountService() {
	}

	public boolean increaseCount(int id) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			int updatedCount = PdsItemDao.getInstance().increaseCount(conn, id);
			return updatedCount == 0 ? false : true;
		} catch (Exception e) {
			throw new RuntimeException("DB에서 다운로드 실퍠: " + e.getMessage(), e);
		} finally {
			//JdbcUtil.close(conn);
		}

	}
}
