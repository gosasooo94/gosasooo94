package mvc.guest.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MessageDao {

	// Single Pattern 
	private static MessageDao instance;
	
	// DB 연결시  관한 변수 
	private static final String dbDriver = "oracle.jdbc.driver.OracleDriver";
	private static final String dbUrl = "jdbc:oracle:thin:@192.168.0.36:1521:orcl"; 
	private static final String dbUser = "lts";
	private static final String dbPass = "soon";

	ResultSet rs = null;
	Statement stmt	= null;
	PreparedStatement ps = null;
	
	
	private Connection	 		con;	
	
	//--------------------------------------------
	//#####	 객체 생성하는 메소드 
	public static MessageDao	getInstance() throws MessageException
	{
		if( instance == null )
		{
			instance = new MessageDao();
		}
		return instance;
	}
	
	private MessageDao() throws MessageException
	{
	
		try{
			
			/********************************************
				1. 오라클 드라이버를 로딩
					( DBCP 연결하면 삭제할 부분 )
			*/
			Class.forName( dbDriver );	
		}catch( Exception ex ){
			throw new MessageException("방명록 ) DB 연결시 오류  : " + ex.toString() );	
		}
		
	}
	
	
	/*
	 * 메세지를 입력하는 함수
	 */
	public int insert(Message rec) throws MessageException
	{

		PreparedStatement ps = null;
		try{

			con	= DriverManager.getConnection( dbUrl, dbUser, dbPass );
			
			String sql		= "INSERT INTO guestTB VALUES(seq_messageId_guestTb.nextval,?,?,?)";  

			ps		= con.prepareStatement( sql );
			
			ps.setString	( 1, rec.getGuestName()	);
			ps.setString	( 2, rec.getPassword()	);
			ps.setString	( 3, rec.getMessage()	);
			
			return ps.executeUpdate();
					
		}catch( Exception ex ){
			throw new MessageException("방명록 ) DB에 입력시 오류  : " + ex.toString() );	
		} finally{
			if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
			if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
		}
	
	}
	
	/*
	 * 메세지 목록 전체를 얻어올 때
	 */
	public List<Message> selectList() throws MessageException
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Message> mList = new ArrayList<Message>();
		boolean isEmpty = true;
		
		try{

			con	= DriverManager.getConnection( dbUrl, dbUser, dbPass );
			String sql		= "SELECT * FROM guestTB order by message_id desc";  
			ps		= con.prepareStatement( sql );
			rs = ps.executeQuery();
			while( rs.next())
			{
				isEmpty = false;
				
				int message_id = rs.getInt("message_id");
				String guest_name = rs.getString("guest_name");
				String password = rs.getString("password");
				String message = rs.getString("message");
				
				Message m = new Message(message_id, guest_name, password, message );
				mList.add(m);
			}
			
			if( isEmpty ) return Collections.emptyList();
			
			return mList;
		}catch( Exception ex ){
			throw new MessageException("방명록 ) DB에 목록 검색시 오류  : " + ex.toString() );	
		} finally{
			if( rs   != null ) { try{ rs.close();  } catch(SQLException ex){} }
			if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
			if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
		}		
	}
	

	/* -------------------------------------------------------
	 * 현재 페이지에 보여울 메세지 목록  얻어올 때
	 */
	public List<Message> selectList(int firstRow, int endRow) throws MessageException
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Message> mList = new ArrayList<Message>();
		boolean isEmpty = true;
		
		try{

			con	= DriverManager.getConnection( dbUrl, dbUser, dbPass );
			String sql		= "SELECT * FROM guestTB	"
								+ "WHERE message_id IN "
								+ "	( SELECT message_id FROM ( SELECT message_id, rownum AS rnum "
								+ "	  FROM ( SELECT message_id FROM guestTB  ORDER BY message_id DESC ) ) "
								+ "	  WHERE rnum >= ? AND rnum <= ? ) "
								+ " ORDER BY message_id DESC";  
			ps		= con.prepareStatement( sql );
			ps.setInt(1, firstRow);
			ps.setInt(2, endRow );
			rs = ps.executeQuery();
			while( rs.next())
			{
				isEmpty = false;
				
				int message_id = rs.getInt("message_id");
				String guest_name = rs.getString("guest_name");
				String password = rs.getString("password");
				String message = rs.getString("message");
				
				Message m = new Message(message_id, guest_name, password, message );
				mList.add(m);
			}
			
			if( isEmpty ) return Collections.emptyList();
			
			return mList;
		}catch( Exception ex ){
			throw new MessageException("방명록 ) DB에 목록 검색시 오류  : " + ex.toString() );	
		} finally{
			if( rs   != null ) { try{ rs.close();  } catch(SQLException ex){} }
			if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
			if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
		}		
	}
	
	
	
	/* -------------------------------------------------------
	 * 메세지 전체 레코드 수를 검색
	 */
	
	public int getTotalCount() throws MessageException{
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			con	= DriverManager.getConnection( dbUrl, dbUser, dbPass );
			String sql		= "SELECT count(*) FROM guestTB";  
			ps		= con.prepareStatement( sql );
			rs = ps.executeQuery();
			rs.next();		
			return  rs.getInt(1);
			
		}catch( Exception ex ){
			throw new MessageException("방명록 ) DB에 목록 검색시 오류  : " + ex.toString() );	
		} finally{
			if( rs   != null ) { try{ rs.close();  } catch(SQLException ex){} }
			if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
			if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
		}			
	}
	
	/*
	 * 메세지 번호와 비밀번호에 의해 메세지 삭제
	 */
	public int delete( int messageId, String password ) throws MessageException
	{
		PreparedStatement ps = null;
		try{

			con	= DriverManager.getConnection( dbUrl, dbUser, dbPass );
			
			String sql		= "DELETE FROM guestTB WHERE message_id=? AND password=?";  

			ps		= con.prepareStatement( sql );
			
			ps.setInt	( 1, messageId	);
			ps.setString	( 2, password	);
			
			return ps.executeUpdate();
					
		}catch( Exception ex ){
			throw new MessageException("방명록 ) DB에 삭제시 오류  : " + ex.toString() );	
		} finally{
			if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
			if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
		}		
	}
}
