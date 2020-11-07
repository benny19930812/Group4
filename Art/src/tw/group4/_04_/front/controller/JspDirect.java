package tw.group4._04_.front.controller;

import java.text.ParseException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tw.group4._04_.back.model.ShowBean;

@Controller
public class JspDirect {
	@RequestMapping(path = "/04/Booking1", method = RequestMethod.GET)
	public String processShowDetail(@ModelAttribute("showitem")ShowBean showBean,Model model ) {
		System.out.println(showBean.getACT_TITLE());
		System.out.println(showBean.getACT_LOCATION_NAME());
		System.out.println(showBean.getACT_NO());
		
		
		
		return "/04/front/_04_ST/04_Booking";
	}
}
