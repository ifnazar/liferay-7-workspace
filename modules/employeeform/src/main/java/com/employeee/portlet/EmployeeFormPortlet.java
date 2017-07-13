package com.employeee.portlet;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.employee.model.Employee;
import com.employee.service.EmployeeLocalServiceUtil;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;

/**
 * @author ifnazar
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true", "javax.portlet.display-name=Employee Form",
		"javax.portlet.init-param.template-path=/", "javax.portlet.init-param.view-template=/view-form.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)

public class EmployeeFormPortlet extends MVCPortlet {

	private static final Log _log = LogFactoryUtil.getLog(EmployeeFormPortlet.class.getName());

	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		_log.info(" >> doView: EmployeeFormPortlet");
		System.out.println(" >> doView : EmployeeFormPortlet");
		super.doView(renderRequest, renderResponse);
	}

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		_log.info(" >>  render: EmployeeFormPortlet");
		super.render(renderRequest, renderResponse);
	}

	public void newEmployee(ActionRequest request, ActionResponse response) throws IOException, PortletException {

		String name = ParamUtil.getString(request, "name");
		Employee employee = EmployeeLocalServiceUtil.createEmployee((int) CounterLocalServiceUtil.increment());
		employee.setName(name);
		EmployeeLocalServiceUtil.addEmployee(employee);

		_log.info("---------------");
		_log.info(employee.getEmpid());
		_log.info(employee.getName());
		_log.info("---------------");

	}
}