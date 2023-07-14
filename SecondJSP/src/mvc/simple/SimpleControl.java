package mvc.simple;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SimpleControl
 */
//@WebServlet("/SimpleControl")
public class SimpleControl extends HttpServlet {
   private static final long serialVersionUID = 1L;
   
   private String jspDir = "/05_mvc_class/1_mvcSimple/";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SimpleControl() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      processRequest(request, response);   
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      processRequest(request, response);         //1. Http 요청을 받음
   }
   
   private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String type=request.getParameter("type");   //2. request 객체로부터 사용자의 요청을 분석
      
      String value = "";                     //3. 사용자의 요청에 따라 알맞은 기능을 수행
      if(type == null) value = "Hello!!";
      else if(type.equals("first"))value="Terence님 화이팅";
      
      request.setAttribute("param", value);       //4. request나 session에 처리 결과를 저장
      
      //우체부 
      RequestDispatcher dispatcher = request.getRequestDispatcher(jspDir + "simpleView.jsp");
      dispatcher.forward(request, response);      //5. RequestDispatcher를 이용하여 알맞은 뷰단의 화일로 포워딩-> <jsp:forward>의 서블릿 코드
   }
}