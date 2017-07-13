<%@ include file="/init.jsp"%>


<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="com.employee.model.Employee"%>
<%@page import="java.util.List"%>
<%@page import="com.employee.service.EmployeeLocalServiceUtil"%>

<%@page import="com.liferay.portal.kernel.util.PortalUtil"%>
<%@ page import="com.liferay.portal.kernel.util.ListUtil"%>
<%@ page import="javax.portlet.PortletURL"%>



<%
	String currentURL = PortalUtil.getCurrentURL(request);
 PortletURL iteratorURL = renderResponse.createRenderURL(); 
 
%>

<liferay-ui:search-container var="searchContainer" delta="5"
	emptyResultsMessage="There are no record to display."
	iteratorURL="<%=iteratorURL%>">

	<liferay-ui:search-container-results>
         <%
			results = EmployeeLocalServiceUtil.getEmployees(searchContainer.getStart(), searchContainer.getEnd());
            total = EmployeeLocalServiceUtil.getEmployeesCount();
     
            searchContainer.setTotal(total);
            searchContainer.setResults(results);

        %>
    </liferay-ui:search-container-results>		
		
		
	<liferay-ui:search-container-row className="com.employee.model.Employee" modelVar="employee" keyProperty="empid">

		<liferay-ui:search-container-column-text property="name" name="Name" />


		<!-- For Delete URL  -->
		<portlet:actionURL var="deleteEmployee" name="deleteEmployee">
			<portlet:param name="backURL" value="<%=currentURL%>" />
			<portlet:param name="empid" value="${employee.empid}" />
		</portlet:actionURL>
		<liferay-ui:search-container-column-text name="Delete Employee"
			value="Delete" href="${deleteEmployee}" />

	</liferay-ui:search-container-row>
	<liferay-ui:search-iterator />
</liferay-ui:search-container>
