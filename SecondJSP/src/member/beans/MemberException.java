package member.beans;

public class MemberException extends Exception
{
	  public MemberException(){
	  		super();
	  	}
	  	
	  public MemberException(String error){
	  		super( error );
	  	}
}
