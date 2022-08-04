package jp.co.sample.repository;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Employee;

@Repository

public class EmployeeRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER = (rs, i) -> {
		Employee employee = new Employee();
		employee.setId(rs.getInt("id"));
		employee.setName(rs.getString("name"));
		employee.setImage(rs.getString("image"));
		employee.setGender(rs.getString("gender"));
		employee.setHireDate(rs.getDate("hireDate"));
		employee.setMailAddress(rs.getString("mailAddress"));
		employee.setZipCode(rs.getString("zipCode"));
		employee.setAddress(rs.getString("address"));
		employee.setTelephone(rs.getString("telephone"));
		employee.setSalary(rs.getInt("salary"));
		employee.setCharacteristics(rs.getString("characteristics"));
		employee.setDependentsCount(rs.getInt("dependentsCount"));
		return employee;
	};

	public List<Employee> findAll() {
		String sql = "select * from employees order by hire_date;";

		List<Employee> employeeList = template.query(sql, EMPLOYEE_ROW_MAPPER);
		return employeeList;

	}

	public Employee load(Integer id) {
		String sql = "select * from employees where id = :id;";

		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);

		Employee employee = template.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER);

		return employee;

	}

	public void update(Employee employee) {

		String sql = "update employees set id = :id, name = :name, image= :image, gender= :gender, hiteDate= :hireDate, mailAddress= : mailAddress,zipCode= :zipCode,address= :address,telephone= :telepnone, salary= :salary, characteristics= :characteristics,dependentsCount= :dependentsCount where id= :id;";

		SqlParameterSource param = new BeanPropertySqlParameterSource(employee);

		template.update(sql, param);

	}
}
