package tw.group4._04_.front.controller;

import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class CRUDCtrl {

	// 標註@Autowired，注入dependency
	@Autowired
	private ShowBean showBean;

	@Autowired
	private ShowBeanService showBeanService;

	int pageNo = 0;
	// @ModelAttribute設定傳入參數
	// Model 類似request的功能
	// @SessionAttributes(names = {"name"})可以將參數設為session
	// Action導到的名稱
	
	@RequestMapping(path = "/SearchAll_.ctrl", method = RequestMethod.GET)
	public String processSearchAll(String searchString, String page,String site,String category,String startdate,String enddate ,Model model) throws ParseException {

		
		//使用.equals("")來判定字串是否相同 ==判定的是物件位址 

		if (!searchString.equals("")) {	
			System.out.println("模糊查詢");
			return  processSearchString(searchString, page, model);
		}
		else if (!site.equals("")) {
			
			return  processSearchString(site, page, model);
		}
		else if (!category.equals("")) {
			return  processCategorySearch(category, page, model);	
		}
		else if (!startdate.equals("")) {
			return  processStartDateSearch(searchString, page, site, category, startdate, enddate, model);
		}
		else if (!enddate.equals("")) {
				
		}
		else {
			
		}


		return "04/SearchAll";
	}
	
	
	//模糊查詢
	@RequestMapping(path = "/SearchAll_Front.ctrl", method = RequestMethod.GET)
	public String processSearchString(String searchString, String page, Model model) {

		System.out.println("searchString=" + searchString);
		List<Map> list = new ArrayList<Map>();
//		不用再new ShowBeanService因為已經用@Autowired  private ShowBeanService showBeanService依賴注入
//		ShowBeanService showService = new ShowBeanService();
		List<ShowBean> showList = showBeanService.find(searchString);
		for (ShowBean showBean : showList) {
//			String category = Integer.toString(showBean.getACT_CATEGORY());

			int noint = showBean.getACT_NO();
			String titleString = showBean.getACT_TITLE();
			String siteString = showBean.getACT_LOCATION_NAME();
			String startdate = showBean.getACT_STARTDATE();

			Map map = new HashMap();
			map.put("no", noint);
			map.put("title", titleString);
			map.put("site", siteString);
			map.put("startdate", startdate);
			// 存入map集合中
//			System.out.println(map);
			list.add(map);// 將map集合放入list集合
//			System.out.println("放入集合");

//				String p = request.getParameter("page");
			int page2;
			try {
				// 當前頁數
				page2 = Integer.valueOf(page);
			} catch (NumberFormatException e) {
				page2 = 1;
			}
			// 搜尋後總活動數
			int totalnum = list.size();
//					System.out.println("共"+totalnum+"筆資料");
			// 每頁顯示活動數
			int PerPage = 100;
			// 總頁數
			int totalPages = totalnum % PerPage == 0 ? totalnum / PerPage : totalnum / PerPage + 1;
			// 本頁起始使用者序號
			int beginIndex = (page2 - 1) * PerPage;
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
			model.addAttribute("page", page2);
//			model.addAttribute("category", category);
			model.addAttribute("searchString", searchString);

			model.addAttribute("key_list", list);// 将list放入request中
		}
		int listsize = list.size();
		System.out.println("共" + listsize + "筆資料");


		return "04/front/_04_ST/04_select";
	}
	//分類查詢
	@RequestMapping(path = "/Category_Front.ctrl", method = RequestMethod.GET)
	public String processCategorySearch(String category, String page, Model model) {

		System.out.println("category=" + category);
//		System.out.println("page="+p);

		List<Map> list = new ArrayList<Map>();

		List<ShowBean> showList = showBeanService.selectAll_startdate();

		for (ShowBean showBean : showList) {
			String categoryString = Integer.toString(showBean.getACT_CATEGORY());
//			System.out.println(categoryString);
			int noint = showBean.getACT_NO();
			String titleString = showBean.getACT_TITLE();
			String siteString = showBean.getACT_LOCATION_NAME();
			String startdate = showBean.getACT_STARTDATE();
			if (category.equals(categoryString)) {

				Map map = new HashMap();
				map.put("no", noint);
				map.put("title", titleString);
				map.put("site", siteString);
				map.put("startdate", startdate);
				// 存入map集合中
//				System.out.println(map);
				list.add(map);// 將map集合放入list集合
//					System.out.println("放入集合");

//				String p = request.getParameter("page");
				int page2;
				try {
					// 當前頁數
					page2 = Integer.valueOf(page);
				} catch (NumberFormatException e) {
					page2 = 1;
				}
				// 搜尋後總活動數
				int totalnum = list.size();
//					System.out.println("共"+totalnum+"筆資料");
				// 每頁顯示活動數
				int PerPage = 100;
				// 總頁數
				int totalPages = totalnum % PerPage == 0 ? totalnum / PerPage : totalnum / PerPage + 1;
				// 本頁起始使用者序號
				int beginIndex = (page2 - 1) * PerPage;
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
				model.addAttribute("page", page2);
				model.addAttribute("category", category);

				model.addAttribute("key_list", list);// 将list放入request中
			}
		}
		int listsize = list.size();
		System.out.println("共" + listsize + "筆資料");

		return "04/front/_04_ST/04_select";
	}
	
	//日期查詢
	@RequestMapping(path = "/StartDateSearch.ctrl", method = RequestMethod.GET)
	public String processStartDateSearch(String searchString, String page,String site,String category,String startdate,String enddate ,Model model) throws ParseException {

		System.out.println("startdate=" + startdate);
//		System.out.println("page="+p);

		List<Map> list = new ArrayList<Map>();

		List<ShowBean> showList = showBeanService.selectAll_startdate();

		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");

		Date requestDate = sdf2.parse(startdate);
		System.out.println(requestDate);
		// 帶入list內日期字串 轉為date格式

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		//將日期轉為年-月-日-星期格式
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-EE");
		for (ShowBean showbean : showList) {
			String dateString = showbean.getACT_STARTDATE();
			int noint = showbean.getACT_NO();
			String titleString = showbean.getACT_TITLE();
			String description = showbean.getACT_DESCRIPTION();
			String siteString = showbean.getACT_LOCATION_NAME();
			Date date;
			date = sdf.parse(dateString);
			// System.out.println(date);
//			request.getAttribute("");
			if (date.after(requestDate)) {
				Map map = new HashMap();
				map.put("no", noint);
				map.put("title", titleString);
				map.put("site", siteString);
				map.put("startdate", startdate);
//				map.put("startdate", dateFormat.format(date));

				// 存入map集合中
//				System.out.println(map);
				list.add(map);// 將map集合放入list集合
//				System.out.println("放入集合");
				for (Map map_1 : list) {
//					System.out.println(map_1);
				}
		

		
//				String p = request.getParameter("page");
				int page2;
				try {
					// 當前頁數
					page2 = Integer.valueOf(page);
				} catch (NumberFormatException e) {
					page2 = 1;
				}
				// 搜尋後總活動數
				int totalnum = list.size();
//					System.out.println("共"+totalnum+"筆資料");
				// 每頁顯示活動數
				int PerPage = 100;
				// 總頁數
				int totalPages = totalnum % PerPage == 0 ? totalnum / PerPage : totalnum / PerPage + 1;
				// 本頁起始使用者序號
				int beginIndex = (page2 - 1) * PerPage;
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
				model.addAttribute("page", page2);
				model.addAttribute("category", category);

				model.addAttribute("key_list", list);// 将list放入request中
			}
		}
		int listsize = list.size();
		System.out.println("共" + listsize + "筆資料");

		return "04/front/_04_ST/04_select";
	}
//
//	@RequestMapping(path = "/delete.ctrl", method = RequestMethod.GET)
//	public String processDelete(int actno, String page, String category, String searchString) {
//
//		System.out.println(actno);
//		System.out.println(searchString);
//		showBeanService.delete(actno);
//
//		// 導回前頁
////		if (category.equals("")) {
////			return"form";
////			response.sendRedirect("/ArtCMS/SearchAll?page="+ page + "&searchString="+URLEncoder.encode(searchString,"utf-8"));
////			System.out.println("&searchString="+searchString);
////		}
////		else {
////			response.sendRedirect("/ArtCMS/AAArtAction?page=" + page + "&category=" + category);
////		}
//		return "04/categorySearch";
//	}
//
//	@RequestMapping(path = "/update1.ctrl", method = RequestMethod.GET)
//	public String processUpdate(int actno, String page, String category, String searchString, Model model) {
//
//		System.out.println(actno);
//		System.out.println(searchString);
//		System.out.println(page);
//
//		ShowBean showBean = showBeanService.select(actno);
//
//		String title = showBean.getACT_TITLE();
//		System.out.println(title);
//		int category2 = showBean.getACT_CATEGORY();
//		System.out.println(category);
//		String location = showBean.getACT_LOCATION();
//		String locationName = showBean.getACT_LOCATION_NAME();
//		String mainunit = showBean.getACT_MAINUNIT();
//		String showunit = showBean.getACT_SHOWUNIT();
//		String description = showBean.getACT_DESCRIPTION();
//		String startdate = showBean.getACT_STARTDATE();
//
//		String enddate = showBean.getACT_ENDDATE();
//
//		model.addAttribute("actno", actno);
//		model.addAttribute("title", title);
//		model.addAttribute("category", category2);
//		model.addAttribute("location", location);
//		model.addAttribute("locationName", locationName);
//		model.addAttribute("mainunit", mainunit);
//		model.addAttribute("showunit", showunit);
//		model.addAttribute("description", description);
//		model.addAttribute("page", page);
//		model.addAttribute("searchString", searchString);
//
//		return "04/UpdateAction";
//	}
//
//	@RequestMapping(path = "/update2.ctrl", method = RequestMethod.GET)
//	public String processUpdate2(int actno, String title, int category, String location, String locationName,
//			String mainunit, String showunit, String description, String startdate, String enddate) {
//
////		System.out.println(actno);
////		System.out.println(searchString);
////		System.out.println(page);
//
//		ShowBean showBean = showBeanService.update(actno, title, category, location, locationName, mainunit, showunit,
//				description, startdate, enddate);
//
//		return "04/categorySearch";
//	}
//
//	@RequestMapping(path = "/insert", method = RequestMethod.GET)
//	public String processUpdate2() {
//
//		return "04/InsertAction";
//	}
//
//	@RequestMapping(path = "/insert.ctrl", method = RequestMethod.GET)
//	public String processInsert(String title, int category, String location, String locationName, String mainunit,
//			String showunit, String description, String startdate, String enddate) {
//
//		showBean.setACT_TITLE(title);
//		showBean.setACT_CATEGORY(category);
//		showBean.setACT_LOCATION(location);
//		showBean.setACT_LOCATION_NAME(locationName);
//		showBean.setACT_MAINUNIT(mainunit);
//		showBean.setACT_SHOWUNIT(showunit);
//		showBean.setACT_DESCRIPTION(description);
//		showBean.setACT_STARTDATE(startdate);
//		showBean.setACT_ENDDATE(enddate);
//
//		showBeanService.insert(showBean);
//
//		return "04/categorySearch";
//	}
//
//	//分類查詢+分頁
//	@RequestMapping(path = "/Category2.ctrl", method = RequestMethod.GET)
//	public String processCategorySearch2(int category,String p, Model model) {
//
//		// 設定頁數
//
//		int pageNo;
//		try {
//			// 當前頁數
//			pageNo = Integer.valueOf(p);
//		} catch (NumberFormatException e) {
//			pageNo = 1;
//		}
//		System.out.println("pageNo" + pageNo);
//		System.out.println("category" + category);
//
//		List<Map> list = new ArrayList<Map>();
//
//		List<ShowBean> showList = showBeanService.selectAll3(pageNo, category);
//		for (ShowBean showBean : showList) {
//			String categoryString = Integer.toString(showBean.getACT_CATEGORY());
//			System.out.println(categoryString);
//			int noint = showBean.getACT_NO();
//			String titleString = showBean.getACT_TITLE();
//			String siteString = showBean.getACT_LOCATION_NAME();
//
//			Map map = new HashMap();
//			map.put("no", noint);
//			map.put("title", titleString);
//			map.put("site", siteString);
//			// 存入map集合中
//			System.out.println(map);
//			list.add(map);// 將map集合放入list集合
////						System.out.println("放入集合");
//
//			int totalPage = showBeanService.getTotalPages();
//
//			List<Integer> totalPages = new ArrayList<Integer>();
//			for (int i = 1; i <= totalPage; i++) {
//				totalPages.add(i);
//			}
//
//			int PerPage = 100;
//
//			model.addAttribute("PerPage", PerPage);
//			model.addAttribute("category", category);
//			model.addAttribute("key_list", list);// 将list放入request中
//			model.addAttribute("pageNo", String.valueOf(pageNo));
//			model.addAttribute("totalPages", totalPage);
//			model.addAttribute("pages", totalPages);
//
//		}
//		int listsize = list.size();
//		System.out.println("共" + listsize + "筆資料");
//
//		return "04/index2";
//	}

}
