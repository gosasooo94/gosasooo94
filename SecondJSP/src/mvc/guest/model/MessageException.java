package mvc.guest.model;

public class MessageException extends Exception
{
  public MessageException(){
  		super();
  	}
  	
  public MessageException(String error){
  		super( error );
  	}
 		
}