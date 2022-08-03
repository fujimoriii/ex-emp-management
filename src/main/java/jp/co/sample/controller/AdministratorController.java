package jp.co.sample.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.form.LoginForm;
import jp.co.sample.service.AdministratorService;

@Controller
@RequestMapping("/")
public class AdministratorController {

	@Autowired
	private AdministratorService administratorservice;

	@ModelAttribute
	public InsertAdministratorForm setUpInsertAdministratorForm() {
		InsertAdministratorForm insertAdministratorForm = new InsertAdministratorForm();
		return insertAdministratorForm;

	}

	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert";

	}

	@RequestMapping("/insert")
	public String insert(InsertAdministratorForm form, RedirectAttributes redirectAttributes) {
		Administrator administrator = new Administrator();
		BeanUtils.copyProperties(form, administrator);
		administratorservice.insert(administrator);
		return "/";

	}

	@ModelAttribute
	public LoginForm setUpLoginForm() {
		LoginForm loginForm = new LoginForm();
		return loginForm;
	}

	@RequestMapping("/")
	public String toLogin() {
		return "administrator/login";

	}
}
