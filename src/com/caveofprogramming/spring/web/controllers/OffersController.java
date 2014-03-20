package com.caveofprogramming.spring.web.controllers;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;



import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.caveofprogramming.spring.web.dao.Offer;
import com.caveofprogramming.spring.web.service.OffersService;

@Controller
public class OffersController {
	
	private OffersService offersService;
		
	@Autowired
	public void setOffersService(OffersService offersService) {
		this.offersService = offersService;
	}
	
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public String showTest(Model model, @RequestParam("id") String id) {
		
		System.out.println("Id is: " + id);
		return "home";
	}
	
	/*/ handling database exceptions....
	@ExceptionHandler(DataAccessException.class)
	public String handleDatabaseException(DataAccessException ex){
		return "error";
	}*/
	
	@RequestMapping("/offers")
	public String showOffers(Model model) {

		//offersService.throwTestException(); //testing exception
		
		List<Offer> offers = offersService.getCurrent();
		model.addAttribute("offers", offers);
				
		return "offers";
	}
	
	@RequestMapping("/createoffer")
	public String createOffers(Model model) {
		
		model.addAttribute("offer", new Offer());
		return "createoffer";
	}
	
	@RequestMapping(value="/docreate", method=RequestMethod.POST)
	public String doCreate(Model model, @Valid Offer offer, BindingResult result) {
		System.out.println(offer);
		
		if(result.hasErrors()){
			// console debugger lines
			System.out.println("Form does not validate.");
			List<ObjectError> errors = result.getAllErrors();
			for(ObjectError error : errors){
				System.out.println(error.getDefaultMessage());
			}
			// console debugger lines
						
			return "createoffer";
	
		} else {
			System.out.println("Form validated");
		}
		
		offersService.create(offer);
		
		return "offercreated";
	}
}
