package board.model;

public class BoardRec
{
	// member field
	private	int		articleId;
	private	int		groupId;
	private	String	sequenceNo;
	private	String	postingDate;
	private	int		readCount;
	private	String	writerName;
	private	String	password;
	private	String	title;
	private	String	content;
	
	
	// constructor method 
	public BoardRec()
	{
		
	}
	public BoardRec( int articleId,  int groupId, String sequenceNo, String postingDate, int readCount, String writerName, String password, String title,  String content )
	{
		this.articleId		= articleId;
		this.groupId		= groupId;
		this.sequenceNo		= sequenceNo;
		this.postingDate	= postingDate;
		this.readCount 		= readCount;
		this.writerName		= writerName;
		this.password		= password;
		this.title			= title;
		this.content		= content;
	}
	
	// setter
	public void setArticleId(int articleId) {	
		this.articleId = articleId;		
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public void setSequenceNo(String sequenceNo) {
		this.sequenceNo = sequenceNo;
	}	
	public void setPostingDate(String postingDate) {
		this.postingDate = postingDate;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}	
	public void setPassword(String password) {
		this.password = password;
	}	
	public void setTitle(String title) {
		this.title = title;
	}
	public void setContent(String content) {
		this.content = content;
	}	
	
	// getter
	public int getArticleId() {
		return articleId;
	}
	public int getGroupId() {
		return groupId;
	}
	public String getSequenceNo() {
		return sequenceNo;
	}
	public String getPostingDate() {
		return postingDate;
	}
	public int getReadCount() {
		return readCount;
	}
	public String getWriterName() {
		return writerName;
	}
	public String getPassword() {
		return password;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}	

	public int getLevel() // 댓글 단계 얻어오기
	{
		if( sequenceNo == null			)	{ return -1; }
		if( sequenceNo.length() != 16 	) 	{ return -1; }
		if( sequenceNo.endsWith("999999")) 	{ return 0; }
		if( sequenceNo.endsWith("9999")	) 	{ return 1; }
		if( sequenceNo.endsWith("99")	) 	{ return 2; }
		return 3;
	}
}

/*
	[ 참고 ]
	
	@ postingDate 가 실제 DB에서는 Date 형이지만, 날짜 값을 출력만 하기에 
	 Date 형이 아닌 String  형으로 받아도 된다.
	 나중에 날짜값을 사용할 일이 있으면 Date 형으로 변경 
 
*/