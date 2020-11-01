package tw.group4._04_.back.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;


@Repository("showBeanDao")
public class ShowBeanDAO {
	
	private SessionFactory sessionFacory;
	private Session session;
	
//	public ShowBeanDAO() {
//	
//	}	
	//DAO一叫出來就幫忙取完session了
	//現在都在DAO層用SessionFactory幫忙做事
	//不再傳遞session一層一層下去
	@Autowired 
	public ShowBeanDAO(@Qualifier("sessionFactory") SessionFactory sessionFacory) {
		this.sessionFacory = sessionFacory;
	}

//	@Autowired 
//	public ShowBeanDAO(Session session) {
//		this.sessionFacory = sessionFacory;
//	}
	

	// 新增
	public ShowBean insert(ShowBean showbean) {
		Session session = sessionFacory.getCurrentSession();
		ShowBean resultBean = session.get(ShowBean.class, showbean.getACT_NO());

		if (resultBean == null) {
			session.save(showbean);
			return showbean;
		}
		return null;
	}

	// 查詢
	public ShowBean select(int actid) {
		Session session = sessionFacory.getCurrentSession();
		return session.get(ShowBean.class, actid);
	}

	// 查詢多筆
	public List<ShowBean> selectAll() {
		// "From ShowBean"為createQuery
		//
		Session session = sessionFacory.getCurrentSession();
		Query<ShowBean> query = session.createQuery("From ShowBean SB ORDER BY SB.ACT_NO", ShowBean.class);
		List<ShowBean> list = query.list();
		return list;
	}

	// 查詢多筆+分頁
	public List<ShowBean> selectAll2() {
		// "From ShowBean"為createQuery
		Session session = sessionFacory.getCurrentSession();
		Query<ShowBean> query = session.createQuery("From ShowBean", ShowBean.class);
		query.setFirstResult(1);
		query.setMaxResults(100);
		List<ShowBean> list = query.list();
		return list;
	}

	// 修改
	public ShowBean update(int actno, String title, int category, String location, String locationName,
			String mainunit, String showunit, String description, String startdate, String enddate) {
		
		Session session = sessionFacory.getCurrentSession();
		ShowBean showBean = session.get(ShowBean.class, actno);

		if (showBean != null) {
			showBean.setACT_TITLE(title);
			showBean.setACT_CATEGORY(category);
			showBean.setACT_LOCATION(locationName);
			showBean.setACT_MAINUNIT(mainunit);
			showBean.setACT_SHOWUNIT(showunit);
			showBean.setACT_DESCRIPTION(description);
			showBean.setACT_STARTDATE(startdate);
			showBean.setACT_ENDDATE(enddate);
		}

		return showBean;
	}

	// 刪除
	public boolean delete(int actid) {
		
		Session session = sessionFacory.getCurrentSession();
		ShowBean result = session.get(ShowBean.class, actid);

		if (result != null) {
			session.delete(result);
			return true;
		}

		return false;
	}

	// 模糊查詢
	// find內預設傳入String searchString參數
	public List<ShowBean> find(String searchString) {
		
		Session session = sessionFacory.getCurrentSession();
		String queryString = "from ShowBean where ACT_TITLE like'%" + searchString + "%'";
		Query queryObject = session.createQuery(queryString);

		return queryObject.list();

	}

}
