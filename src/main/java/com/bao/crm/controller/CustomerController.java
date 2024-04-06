package com.bao.crm.controller;

import com.bao.crm.dto.CustomerParams;
import com.bao.crm.entity.Customer;
import com.bao.crm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ModelAttribute("currentUrl")
    public String getCurrentUrl(HttpServletRequest request) {
        return request.getRequestURI();
    }

    @RequestMapping("/list")
    public String listCustomers(CustomerParams params, Model model ){
        List<Customer> customers = customerService.getCustomers(params);
        model.addAttribute("customerParams", params);
        model.addAttribute("customers", customers);
        int customersCount = customerService.getCustomerCount(params);
        int totalPages = (int) Math.ceil((double) customersCount / params.getPageSize());
        model.addAttribute("totalCount", customersCount);
        model.addAttribute("totalPages", totalPages);
        return "customer/list-customer";
    }

    @RequestMapping("/new")
    public String showFormForAdd(Model model){
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "customer/customer-form";
    }

    @GetMapping("/edit/{id}")
    public String showFormForEdit(@PathVariable int id, Model model) {
        Customer customer = customerService.getCustomerById(id);
        model.addAttribute("customer", customer);
        return "customer/customer-form";
    }

    @PostMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable int id) {
        customerService.deleteCustomer(id);
        return "redirect:/customer/list";
    }

    @PostMapping("/save")
    public String saveCustomer(@Valid @ModelAttribute("customer") Customer customer,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes,
                               Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("customer", customer);
            return "customer/customer-form";
        }

        Customer existingCustomer = customerService.getCustomerByEmail(customer.getEmail());

        if (existingCustomer != null && existingCustomer.getId() != customer.getId()) {
            bindingResult.rejectValue("email", "error.email", "Email already exists!");
            model.addAttribute("customer", customer);
            return "customer/customer-form";
        }

        if (customer.getId() != 0){
            redirectAttributes.addFlashAttribute("success", "Customer updated successfully!");
        } else {
            redirectAttributes.addFlashAttribute("success", "Customer saved successfully!");
        }

        customerService.saveCustomer(customer);

        return "redirect:/customer/list";
    }
}
