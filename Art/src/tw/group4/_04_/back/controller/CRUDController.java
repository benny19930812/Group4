package tw.group4._04_.back.controller;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	// 標註@Autowired，注入dependency
	@Autowired
	private ShowBean showBean;

	@Autowired
	private ShowBeanService showBeanService;

	// @ModelAttribute設定傳入參數
	// Model 類似request的功能
	// @SessionAttributes(names = {"name"})可以將參數設為session
	// Action導到的名稱

	@RequestMapping(path = "/SearchAll.controller", method = RequestMethod.GET)
	public String processSearchAll(String searchString, String p, Model model) {

		System.out.println("searchString=" + searchString);
		List<Map> list = new ArrayList<Map>();
//		不用再new ShowBeanService因為已經用@Autowired  private ShowBeanService showBeanService依賴注入
//		ShowBeanService showService = new ShowBeanService();
		List<ShowBean> showList = showBeanService.find(searchString);
		for (ShowBean showBean : showList) {
			String category = Integer.toString(showBean.getACT_CATEGORY());

			int noint = showBean.getACT_NO();
			String titleString = showBean.getACT_TITLE();
			String siteString = showBean.getACT_LOCATION_NAME();

			Map map = new HashMap();
			map.put("no", noint);
			map.put("title", titleString);
			map.put("site", siteString);
			// 存入map集合中
//			System.out.println(map);
			list.add(map);// 將map集合放入list集合
//			System.out.println("放入集合");

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
		int listsize = list.size();
		System.out.println("共" + listsize + "筆資料");

		return "04/SearchAll";
	}

	@RequestMapping(path = "/Category.controller", method = RequestMethod.GET)
	public String processCategorySearch(String category, String p, Model model) {

		System.out.println("category=" + category);
//		System.out.println("page="+p);

		List<Map> list = new ArrayList<Map>();

		List<ShowBean> showList = showBeanService.selectAll();

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

		return "04/categorySearch";
	}

	@RequestMapping(path = "/delete.ctrl", method = RequestMethod.GET)
	public String processDelete(int actno, String page, String category, String searchString) {

		System.out.println(actno);
		System.out.println(searchString);
		showBeanService.delete(actno);

		// 導回前頁
//		if (category.equals("")) {
//			return"form";
//			response.sendRedirect("/ArtCMS/SearchAll?page="+ page + "&searchString="+URLEncoder.encode(searchString,"utf-8"));
//			System.out.println("&searchString="+searchString);
//		}
//		else {
//			response.sendRedirect("/ArtCMS/AAArtAction?page=" + page + "&category=" + category);
//		}
		return "04/categorySearch";
	}

	@RequestMapping(path = "/update1.ctrl", method = RequestMethod.GET)
	public String processUpdate(int actno, String page, String category, String searchString, Model model) {

		System.out.println(actno);
		System.out.println(searchString);
		System.out.println(page);

		ShowBean showBean = showBeanService.select(actno);

		String title = showBean.getACT_TITLE();
		System.out.println(title);
		int category2 = showBean.getACT_CATEGORY();
		System.out.println(category);
		String location = showBean.getACT_LOCATION();
		String locationName = showBean.getACT_LOCATION_NAME();
		String mainunit = showBean.getACT_MAINUNIT();
		String showunit = showBean.getACT_SHOWUNIT();
		String description = showBean.getACT_DESCRIPTION();
		String startdate = showBean.getACT_STARTDATE();

		String enddate = showBean.getACT_ENDDATE();

		model.addAttribute("actno", actno);
		model.addAttribute("title", title);
		model.addAttribute("category", category2);
		model.addAttribute("location", location);
		model.addAttribute("locationName", locationName);
		model.addAttribute("mainunit", mainunit);
		model.addAttribute("showunit", showunit);
		model.addAttribute("description", description);
		model.addAttribute("page", page);
		model.addAttribute("searchString", searchString);

		return "04/UpdateAction";
	}

	@RequestMapping(path = "/update2.ctrl", method = RequestMethod.GET)
	public String processUpdate2(int actno, String title, int category, String location, String locationName,
			String mainunit, String showunit, String description, String startdate, String enddate) {

//		System.out.println(actno);
//		System.out.println(searchString);
//		System.out.println(page);

		ShowBean showBean = showBeanService.update(actno, title, category, location, locationName, mainunit, showunit,
				description, startdate, enddate);

		return "04/categorySearch";
	}

	@RequestMapping(path = "/insert", method = RequestMethod.GET)
	public String processUpdate2() {

		return "04/InsertAction";
	}

	@RequestMapping(path = "/insert.ctrl", method = RequestMethod.GET)
	public String processInsert(String title, int category, String location, String locationName, String mainunit,
			String showunit, String description, String startdate, String enddate) {

		showBean.setACT_TITLE(title);
		showBean.setACT_CATEGORY(category);
		showBean.setACT_LOCATION(location);
		showBean.setACT_LOCATION_NAME(locationName);
		showBean.setACT_MAINUNIT(mainunit);
		showBean.setACT_SHOWUNIT(showunit);
		showBean.setACT_DESCRIPTION(description);
		showBean.setACT_STARTDATE(startdate);
		showBean.setACT_ENDDATE(enddate);

		showBeanService.insert(showBean);

		return "04/categorySearch";
	}

}
