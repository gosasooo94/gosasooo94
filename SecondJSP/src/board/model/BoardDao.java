package board.model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BoardDao
{
	
	// Single Pattern 
	private static BoardDao instance;
	
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
	public static BoardDao	getInstance() throws BoardException
	{
		if( instance == null )
		{
			instance = new BoardDao();
		}
		return instance;
	}
	
	private BoardDao() throws BoardException
	{
	
		try{
			
			/********************************************
				1. 오라클 드라이버를 로딩
					( DBCP 연결하면 삭제할 부분 )
			*/
			Class.forName( dbDriver );	
		}catch( Exception ex ){
			throw new BoardException("DB 연결시 오류  : " + ex.toString() );	
		}
		
	}
	
	
	//--------------------------------------------
	//#####	 게시글 입력전에 그 글의 그룹번호를 얻어온다
	public int getGroupId() throws BoardException
	{
		
		int groupId=1;
		try{
			con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
			String sql = "select SEQ_GROUP_ID_ARTICLE.nextval as group_id from dual";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
					if(rs.next()) {
						groupId = rs.getInt("group_id");
					}
					
			return groupId;
		}catch( Exception ex ){
			throw new BoardException("게시판 ) 게시글 입력 전에 그룹번호 얻어올 때  : " + ex.toString() );	
		} finally{
			if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
			if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
		}		
	}

	//--------------------------------------------
	//#####	 게시판에 글을 입력시 DB에 저장하는 메소드 
	public int insert( BoardRec rec ) throws BoardException
	{
		
		/************************************************
		*/
	
		try{
			con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
			String sql = "insert into article(ARTICLE_ID, GROUP_ID, SEQUENCE_NO, POSTING_DATE, READ_COUNT, WRITER_NAME, PASSWORD, TITLE, CONTENT) values(SEQ_ARTICLE_ID_ARTICLE.nextval,?,?,sysdate,0,?,?,?,?)";
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, rec.getGroupId());
			ps.setString(2, rec.getSequenceNo());
			ps.setString(3, rec.getWriterName());
			ps.setString(4, rec.getPassword());
			ps.setString(5, rec.getTitle());
			ps.setString(6, rec.getContent());
			
			int insertedCount = ps.executeUpdate();
			
			//BoardSave.jsp에서 새로고침을 하면 중복으로 입력됨
			if(insertedCount>0) {
				stmt = con.createStatement();
				rs = stmt.executeQuery("select SEQ_ARTICLE_ID_ARTICLE.currval from dual");
				if(rs.next()) {
					return rs.getInt(1);
				}
			}
			
			return -1;
		
		}catch( Exception ex ){
			throw new BoardException("게시판 ) DB에 입력시 오류  : " + ex.toString() );	
		} finally{
			if( rs   != null ) { try{ rs.close();  } catch(SQLException ex){} }
			if( stmt != null ) { try{ stmt.close(); } catch(SQLException ex){} }
			if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
			if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
		}
		
	}

	//--------------------------------------------
	//#####	 전체 레코드를 검색하는 함수
	// 리스트에 보여줄거나 필요한 컬럼 : 게시글번호, 그룹번호, 순서번호, 게시글등록일시, 조회수, 작성자이름,  제목
	//							( 내용, 비밀번호  제외 )
	// 순서번호(sequence_no)로 역순정렬
	public List<BoardRec> selectList() throws BoardException
	{
	
		
		List<BoardRec> mList = new ArrayList<BoardRec>();
		boolean isEmpty = false;
		
		try{
			con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
			String sql = "select * from article order by SEQUENCE_NO desc";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				isEmpty = false;
				BoardRec br = new BoardRec();
				br.setArticleId(rs.getInt("ARTICLE_ID"));
				br.setGroupId(rs.getInt("GROUP_ID"));
				br.setSequenceNo(rs.getString("SEQUENCE_NO"));
				br.setPostingDate(rs.getString("POSTING_DATE"));
				br.setReadCount(rs.getInt("READ_COUNT"));
				br.setTitle(rs.getString("TITLE"));
				br.setWriterName(rs.getString("WRITER_NAME"));
				br.setPostingDate(rs.getString("POSTING_DATE"));
				br.setContent(rs.getString("CONTENT"));
			
				mList.add(br);
			}
			
			if( isEmpty ) return Collections.emptyList();
			
			return mList;
		}catch( Exception ex ){
			throw new BoardException("게시판 ) DB에 목록 검색시 오류  : " + ex.toString() );	
		} finally{
			if( rs   != null ) { try{ rs.close();  } catch(SQLException ex){} }
			if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
			if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
		}		
	}
	
	//--------------------------------------------
	//#####	 게시글번호에 의한 레코드 검색하는 함수
	public BoardRec selectById(int id) throws BoardException
	{
	
		
		BoardRec rec = new BoardRec();
		
		try{
			con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
			String sql = "select * from article where ARTICLE_ID=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
			
				rec.setArticleId(rs.getInt("ARTICLE_ID"));
				rec.setGroupId(rs.getInt("GROUP_ID"));
				rec.setSequenceNo(rs.getString("SEQUENCE_NO"));
				rec.setPostingDate(rs.getString("POSTING_DATE"));
				rec.setReadCount(rs.getInt("READ_COUNT"));
				rec.setTitle(rs.getString("TITLE"));
				rec.setWriterName(rs.getString("WRITER_NAME"));
				rec.setPostingDate(rs.getString("POSTING_DATE"));
				rec.setContent(rs.getString("CONTENT"));
			}
			
			return rec;
		}catch( Exception ex ){
			throw new BoardException("게시판 ) DB에 글번호에 의한 레코드 검색시 오류  : " + ex.toString() );	
		} finally{
			if( rs   != null ) { try{ rs.close();  } catch(SQLException ex){} }
			if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
			if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
		}		
	}

	//--------------------------------------------
	//#####	 게시글 보여줄 때 조회수 1 증가
	public void increaseReadCount( int article_id ) throws BoardException
	{

		
		try{
			con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
			String sql = "update article set read_count = read_count+1 where article_id=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, article_id);
			
			ps.executeUpdate();
			
		}catch( Exception ex ){
			throw new BoardException("게시판 ) 게시글 볼 때 조회수 증가시 오류  : " + ex.toString() );	
		} finally{
			if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
			if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
		}
		
	}
	//--------------------------------------------
	//#####	 게시글 수정할 때
	//		( 게시글번호와 패스워드에 의해 수정 )
	public int update( BoardRec rec ) throws BoardException
	{
		
		
		try{
			con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
			String sql = "update article set TITLE=?, CONTENT=? where ARTICLE_ID=? and PASSWORD=?";
			ps= con.prepareStatement(sql);
			
			ps.setString(1, rec.getTitle());
			ps.setString(2, rec.getContent());
			ps.setInt(3, rec.getArticleId());
			ps.setString(4, rec.getPassword());
			
			
			return ps.executeUpdate(); // 나중에 수정된 수를 리턴하시오.
		
		}catch( Exception ex ){
			throw new BoardException("게시판 ) 게시글 수정시 오류  : " + ex.toString() );	
		} finally{
			if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
			if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
		}
		
	}
	
	
	//--------------------------------------------
	//#####	 게시글 삭제할 때
	//		( 게시글번호와 패스워드에 의해 삭제 )
	public int delete( int article_id, String password ) throws BoardException
	{

		
		try{
			con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
			String sql = "delete from article where ARTICLE_ID=? and PASSWORD=?";
			ps= con.prepareStatement(sql);
			ps.setInt(1, article_id);
			ps.setString(2, password);
			
			int result = ps.executeUpdate();
			
			return result; // 나중에 수정된 수를 리턴하시오.
		
		}catch( Exception ex ){
			throw new BoardException("게시판 ) 게시글 수정시 오류  : " + ex.toString() );	
		} finally{
			if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
			if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
		}
		
	}
	
	
	//----------------------------------------------------
	//#####  부모레코드의 자식레코드 중 마지막 레코드의 순서번호를 검색
	//       ( 제일 작은 번호값이 마지막값임)
	public String selectLastSequenceNumber( String maxSeqNum, String minSeqNum ) throws BoardException
	{
		

		try{
			con	= DriverManager.getConnection( dbUrl, dbUser, dbPass );
			String sql		= "SELECT min(SEQUENCE_NO) as minseq FROM article WHERE SEQUENCE_NO < ? AND SEQUENCE_NO >= ?";  
			ps		= con.prepareStatement( sql );
			ps.setString(1, maxSeqNum);
			ps.setString(2, minSeqNum);
			rs = ps.executeQuery();
			if( !rs.next())
			{				
				return null;
			}
			
			return rs.getString("minseq");
		}catch( Exception ex ){
			throw new BoardException("게시판 ) 부모와 연관된 자식 레코드 중 마지막 순서번호 얻어오기  : " + ex.toString() );	
		} finally{
			if( rs   != null ) { try{ rs.close();  } catch(SQLException ex){} }
			if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
			if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
		}			
	}
	/* -------------------------------------------------------
	 * 현재 페이지에 보여울 메세지 목록  얻어올 때
	 */
	public List<BoardRec> selectList(int firstRow, int endRow) throws BoardException
	{

		List<BoardRec> mList = new ArrayList<BoardRec>();
		boolean isEmpty = true;
		
		try{
		con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
			String sql = "SELECT ARTICLE_ID, GROUP_ID, SEQUENCE_NO, POSTING_DATE, READ_COUNT, WRITER_NAME, PASSWORD, TITLE, CONTENT FROM article WHERE SEQUENCE_NO IN ( SELECT SEQUENCE_NO FROM ( SELECT SEQUENCE_NO, rownum AS rnum FROM ( SELECT SEQUENCE_NO FROM ARTICLE ORDER BY SEQUENCE_NO DESC )) WHERE rnum >= ? AND rnum <= ? ) ORDER BY SEQUENCE_NO DESC";
			ps= con.prepareStatement(sql);
			ps.setInt(1, firstRow);
			ps.setInt(2, endRow);
			rs = ps.executeQuery();
			while(rs.next()) {
				isEmpty = false;
				BoardRec ms = new BoardRec();
				ms.setArticleId(rs.getInt("ARTICLE_ID"));
				ms.setGroupId(rs.getInt("GROUP_ID"));
				ms.setSequenceNo(rs.getString("SEQUENCE_NO"));
				ms.setPostingDate(rs.getString("POSTING_DATE"));
				ms.setReadCount(rs.getInt("READ_COUNT"));
				ms.setTitle(rs.getString("TITLE"));
				ms.setWriterName(rs.getString("WRITER_NAME"));
				ms.setPostingDate(rs.getString("POSTING_DATE"));
				ms.setContent(rs.getString("CONTENT"));
				
				mList.add(ms);
			}
		
			
			if( isEmpty ) return Collections.emptyList();
			
			return mList;
		}catch( Exception ex ){
			throw new BoardException("방명록 ) DB에 목록 검색시 오류  : " + ex.toString() );	
		} finally{
			if( rs   != null ) { try{ rs.close();  } catch(SQLException ex){} }
			if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
			if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
		}		
	}
	
	
	
	/* -------------------------------------------------------
	 * 메세지 전체 레코드 수를 검색
	 */
	
	public int getTotalCount() throws BoardException {

		int count = 0;

		try{
		con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
			
			String sql = "select count(*) as cnt from article";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			
			return  rs.getInt("cnt");
			
		}catch( Exception ex ){
			throw new BoardException("방명록 ) DB에 목록 검색시 오류  : " + ex.toString() );	
		} finally{
			if( rs   != null ) { try{ rs.close();  } catch(SQLException ex){} }
			if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
			if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
		}			
	}
}