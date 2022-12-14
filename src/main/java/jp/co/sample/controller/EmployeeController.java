package jp.co.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Employee;
import jp.co.sample.form.UpdateEmployeeForm;
import jp.co.sample.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping("/showList")
	public String showList(Model model) {
		List<Employee> emp = employeeService.showList(); // 型、これでよいのか？？
		model.addAttribute("employeeList", emp);
		return "employee/list";

	}

	@ModelAttribute
	public UpdateEmployeeForm setUpUpdateEmployeeForm() {
		UpdateEmployeeForm updateEmployeeForm = new UpdateEmployeeForm();
		return updateEmployeeForm;

	}

	@RequestMapping("/showDetail")
	public String showDetail(String id, Model model) {
		int id1 = Integer.parseInt(id);
		Employee emp2 = employeeService.showDetail(id1);
		model.addAttribute("employee", emp2);
		return "employee/detail";

	}

}
