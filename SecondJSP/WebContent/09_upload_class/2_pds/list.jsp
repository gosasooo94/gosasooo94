<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="pds.service.ListPdsItemService"%>
<%@ page import="pds.model.PdsItem"%>
<%@ page import="java.util.List" %>
<%

	ListPdsItemService listSerivce = ListPdsItemService.getInstance();
	List<PdsItem> itemListModel = listSerivce.getPdsItemList();
	request.setAttribute("listModel", itemListModel);
	 
%>
<jsp:forward page="list_view.jsp" />