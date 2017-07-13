package com.employeee.portlet;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Component;

import com.employee.service.EmployeeLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;

/**
 * @author ifnazar
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true", "javax.portlet.display-name=Employee Grid",
		"javax.portlet.init-param.template-path=/", "javax.portlet.init-param.view-template=/view-grid.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)

public class EmployeeGridPortlet extends MVCPortlet {

	private static final Log _log = LogFactoryUtil.getLog(EmployeeGridPortlet.class.getName());

	public void deleteEmployee(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {
		String backURL = ParamUtil.getString(actionRequest, "backURL");
		int empid = ParamUtil.getInteger(actionRequest, "empid");
		try {
			EmployeeLocalServiceUtil.deleteEmployee(empid);
			_log.info(" >> Successfully Deleted Student of Id =>" + empid);

			actionResponse.sendRedirect(backURL);
		} catch (PortalException | SystemException e) {
			_log.error(e);
		}

	}

}