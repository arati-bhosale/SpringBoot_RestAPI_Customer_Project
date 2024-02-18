package com.sunbase.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sunbase.customer.model.Customer;
import com.sunbase.customer.model.MyCustomerList;
import com.sunbase.customer.service.CustomerService;
import com.sunbase.customer.service.MyCustomerListService;

@Controller
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private MyCustomerListService myCustomerService;

	@GetMapping("/")
	public String customerr() {

		return "home";
	}

	@GetMapping("/customer_register")
	public String customerRegister() {
		return "customerRegister";
	}

	@GetMapping("/available_customers")
	public ModelAndView getAllCustomers(@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "id") String sortBy) {

		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

		Page<Customer> page = customerService.getAllCustomer(pageable);

		ModelAndView modelAndView = new ModelAndView("customerList");
		modelAndView.addObject("customer", page.getContent());
		modelAndView.addObject("totalPages", page.getTotalPages());
		modelAndView.addObject("totalItems", page.getTotalElements());
		modelAndView.addObject("currentPage", pageNo);

		return modelAndView;
	}

	@PostMapping("/save")
	public String addCustomer(@ModelAttribute Customer c) {
		customerService.save(c);
		return "redirect:/available_customers";

	}

	@GetMapping("/my_customers")
	public String getMyCustomer(Model model, @RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "id") String sortBy) {
		Pageable pageable = PageRequest.of(pageNo, 5, Sort.by(sortBy));
		Page<MyCustomerList> page = myCustomerService.getAllMyCustomers(pageable);

		model.addAttribute("customer", page.getContent());
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("sortBy", sortBy);

		return "myCustomer"; // Return the view name
	}

	@RequestMapping("/mylist/{id}")
	public String getMyList(@PathVariable("id") int id) {
		Customer c = customerService.getCustomerById(id);
		MyCustomerList mc = new MyCustomerList(c.getId(), c.getFirstName(), c.getLastName(), c.getStreet(),
				c.getAddress(), c.getCity(), c.getState(), c.getEmail(), c.getPhoneNo());
		myCustomerService.saveMyCustomers(mc);
		return "redirect:/my_customers";
	}

	@RequestMapping("/editCustomer/{id}")
	public String editCustomer(@PathVariable("id") int id, Model model) {
		Customer c = customerService.getCustomerById(id);
		model.addAttribute("customer", c);
		return "customerEdit";
	}

	@RequestMapping("/deleteCustomer/{id}")
	public String deleteCustomer(@PathVariable("id") int id) {
		customerService.deleteById(id);
		return "redirect:/available_customers";
	}

	@RequestMapping("/login")
	public String loginPage(Model model) {
		Customer c = new Customer();
		model.addAttribute("customer", c);
		return "customerLogin";
	}

	@PostMapping("/userLogin")
	public String loginCustomer(@ModelAttribute("customer") Customer c, int id) {

		Customer userData = customerService.findByUserId(id);

		if (c.getPassword().equals(userData.getPassword())) {
			return "redirect:/available_customers";
		}

		else {
			return "error";
		}
	}

	@GetMapping("/search")
	public String searchCustomer(@RequestParam(required = false) Integer id,
			@RequestParam(required = false) String firstName, Model model) {
		Customer customer = null;
		if (id != null && firstName != null) {
			customer = customerService.searchCustomerByIdAndFirstName(id, firstName);
		}
		model.addAttribute("customer", customer);
		return "customerSearch";
	}

	@GetMapping("/signin")
	public String signin() {
		return "login";

	}
}