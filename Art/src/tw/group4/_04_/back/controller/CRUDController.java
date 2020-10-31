package tw.group4._04_.back.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import tw.group4._04_.back.model.ShowBean;
import tw.group4._04_.back.model.ShowBeanService;




@Controller
public class CRUDController {
	
    //@ModelAttribute設定傳入參數
    //Model 類似request的功能
	//@SessionAttributes(names = {"name"})可以將參數設為session
	// Action導到的名稱
	@RequestMapping(path = "/SearchAll.controller", method = RequestMethod.POST)
	public String processSearchAll() {

		
		
		return "04/SearchAll";
	}

	@RequestMapping(path = "/Category.controller", method = RequestMethod.POST)
	public String processCategorySearch(String category ,String p,Model model) {

		
		System.out.println("category="+category);
//		System.out.println("page="+p);

		
		List<Map> list = new ArrayList<Map>();
		
	
		
//
		System.out.println("here");
		ShowBeanService showService = new ShowBeanService();
//		ShowBeanDAO SDao = new ShowBeanDAO();
		
		
		List<ShowBean> showList = showService.selectAll();
		System.out.println("here2");
		for (ShowBean showBean : showList) {
			String categoryString = Integer.toString(showBean.getACT_CATEGORY());

			int noint = showBean.getACT_NO();
			String titleString = showBean.getACT_TITLE();
			String siteString = showBean.getACT_LOCATION_NAME();

			if (category.equals(categoryString)) {

				Map map = new HashMap();
				map.put("no", noint);
				map.put("title", titleString);
				map.put("site", siteString);
				// 存入map集合中
					System.out.println(map);
				list.add(map);// 將map集合放入list集合
//					System.out.println("放入集合");

//				String p = request.getParameter("page");
				int page;
				try {
					// 當前頁數

					page = Integer.valueOf(p);
				} catch (NumberFormatException e) {
					page = 1;
				}
				// 搜尋後總活動數
				int totalnum = list.size();
//					System.out.println("共"+totalnum+"筆資料");
				// 每頁顯示活動數
				int PerPage = 100;
				// 總頁數
				int totalPages = totalnum % PerPage == 0 ? totalnum / PerPage : totalnum / PerPage + 1;
				// 本頁起始使用者序號
				int beginIndex = (page - 1) * PerPage;
				// 本頁末尾使用者序號的下一個
				int endIndex = beginIndex + PerPage;
				if (endIndex > totalnum)
					endIndex = totalnum;
				
//				model.addAttribute(attributeName, attributeValue)
				model.addAttribute("totalnum", totalnum);
				model.addAttribute("PerPage", PerPage);
				model.addAttribute("totalPages", totalPages);
				model.addAttribute("beginIndex", beginIndex);
				model.addAttribute("endIndex", endIndex);
				model.addAttribute("page", page);
				model.addAttribute("category", category);

				model.addAttribute("key_list", list);// 将list放入request中
			}
		}
		int listsize = list.size();
		System.out.println("共" + listsize + "筆資料");

		return "04/SearchAll";
	}

	@RequestMapping(path = "/aaaaaa", method = RequestMethod.GET)
	public String processEntryPage() {
		return "form";
	}
}
