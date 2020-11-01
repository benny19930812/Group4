package tw.group4._04_.back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tw.group4._04_.back.model.ShowBean;
import tw.group4._04_.back.model.ShowBeanDAO;
import tw.group4._04_.back.model.ShowBeanService;
@Controller
public class Test2 {

	
	@Autowired
	private ShowBean showBean;
	
	@Autowired
	private ShowBeanService showBeanService;
	public static void main(String[] args) {
		
		
		System.out.println(showBeanService.select(500));
		

	}

}
