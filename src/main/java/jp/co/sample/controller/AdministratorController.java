package jp.co.sample.controller;

import javax.servlet.http.HttpSession;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
	private HttpSession session;

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
	public String toLogin(Model model) {
		return "administrator/login";

	}

	@RequestMapping("/login")
	public String login(LoginForm form,  Model model) {
		Administrator admin = administratorservice.login(form.getMailAddress(), form.getPassword());

		if (admin == null) {
			model.addAttribute("message" , "メールアドレスまたはパスワードが不正です");
			//Modelオブジェクトにセット
			return "administrator/login";

		} else {

			session.setAttribute("administratorName", admin.getName());
			return "forword:/employee/showList";

//		if(admin == null) {
//		sippai
//			
//		} else {
//			seikou
//			session.setAttribute("administratorName", admin.getName());
//		}
//		

		}

	}

}
