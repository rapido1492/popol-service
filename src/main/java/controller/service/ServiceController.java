package controller.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import common.Common;
import dao.service.ServiceDAO;
import vo.service.accountVO;
import vo.service.serviceVO;

@Controller
public class ServiceController {
	ServiceDAO serviceDAO;
	
	public void setServiceDAO(ServiceDAO serviceDAO) {
		this.serviceDAO = serviceDAO;
	}

	@Autowired//자동 주입.(Spring으로 부터 application정보를 자동으로 받는다.)
	ServletContext application;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	HttpServletRequest request;
	
	@RequestMapping(value = {"/"})
	public String login_form( Model model ) { 
//		SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd");
//		Calendar time = Calendar.getInstance();
//		String format_time = format.format(time.getTime());
//		model.addAttribute("date",format_time);
//		
		return Common.loginPage.VIEW_PATH + "data_search.jsp";
	}
	
	@RequestMapping("/data_insert.do")
	public String move_insert() {
		return Common.loginPage.VIEW_PATH + "data_insert.jsp";
	}
	
	@RequestMapping("/save.do")
	public String save(serviceVO vo, HttpServletRequest req, accountVO avo) {
		vo.setBusi_num(vo.getBusi_num());
		vo.setCustom(vo.getCustom());
		vo.setSHORT(vo.getSHORT());
		vo.setCeo(vo.getCeo());
		vo.setCharge_person(vo.getCharge_person());
		vo.setBusi_condition(vo.getBusi_condition());
		vo.setItem(vo.getItem());
		vo.setPost_num(vo.getPost_num());
		vo.setAddr1(vo.getAddr1());
		vo.setAddr2(vo.getAddr2());
		vo.setTel(vo.getTel());
		vo.setFax(vo.getFax());
		vo.setHomepage(vo.getHomepage());
		vo.setCo_yn(vo.getCo_yn());
		vo.setForeign_yn(vo.getForeign_yn());
		vo.setTax_yn(vo.getTax_yn());
		vo.setCountry_eng(vo.getCountry_eng());
		vo.setCountry_kor(vo.getCountry_kor());
		if(vo.getSpecial_relation() == null) {
			vo.setSpecial_relation("n");
		}
		else {
			vo.setSpecial_relation(vo.getSpecial_relation());
		}
		if(vo.getTrade_stop() == null) {
			vo.setTrade_stop("n");
		}
		else {
			vo.setTrade_stop(vo.getTrade_stop());
		}
		vo.setContract_period_s(vo.getContract_period_s());
		vo.setContract_period_e(vo.getContract_period_e());
		vo.setRegi_info_man(vo.getRegi_info_man());
		vo.setRegi_info_date(vo.getRegi_info_date());
		vo.setModi_info_man(vo.getModi_info_man());
		vo.setModi_info_date(vo.getModi_info_date());
		
		avo.setBusi_num(vo.getBusi_num());
		avo.setAccount_num(req.getParameter("account_num"));
		avo.setFactory(req.getParameter("factory"));
		avo.setTrade_bank(req.getParameter("trade_bank"));
		
		int res = serviceDAO.insert(vo);
		int account_res = serviceDAO.account_insert(avo); 
		System.out.println(account_res);
		if(res==1) {
			System.out.println("성공");
		}
		else {
		System.out.println("등록실패");
		}
		return Common.loginPage.VIEW_PATH + "data_search.jsp";
	}
	
	@RequestMapping("/search_list.do")
	public String searchlist() {
		return Common.loginPage.VIEW_PATH + "data_search.jsp";
	}
	
	@RequestMapping("/search.do")
	public String search(Model model, HttpServletRequest req) {
		
		String busi_num = req.getParameter("busi_num");
		String custom = req.getParameter("custom");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("busi_num", busi_num);
		map.put("custom", custom);
		
		List<serviceVO> list = serviceDAO.search(map);
		model.addAttribute("search_list", list);
		
		return Common.loginPage.VIEW_PATH + "data_search.jsp";

	}
	
//	@RequestMapping("/view.do")
//	public String view(Model model, HttpServletRequest req) {
//		
//		String busi_num = req.getParameter("busi_num");
//		String custom = req.getParameter("custom");
//		
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("busi_num", busi_num);
//		map.put("custom", custom);
//		
//		System.out.println(map.get("busi_num"));
//		System.out.println(map.get("custom"));
//		
//		List<serviceVO> list = new ArrayList<serviceVO>();
//		list = logindao.search(map);
//		System.out.println("리스트 사이즈"+list.size());
//		for(int i = 0; i < list.size(); i++) {
//			System.out.println(list.get(i));
//		}
//		model.addAttribute("list", list);
//		
//		return Common.loginPage.VIEW_PATH + "data_modify.jsp";
//	}
	
	@RequestMapping("/view.do")
	public String view(Model model, String busi_num) {
		serviceVO vo = new serviceVO();
		System.out.println(busi_num);
		vo = serviceDAO.selectOne(busi_num);

		vo.setContract_period_s(vo.getContract_period_s().substring(0,10));
		vo.setContract_period_e(vo.getContract_period_e().substring(0, 10));
		
		model.addAttribute("modify", vo);
		
		model.addAttribute("account", vo.getVo());
		
		return Common.loginPage.VIEW_PATH + "data_modify.jsp";
	}
	
	@RequestMapping("/service_modify.do")
	public String modify(serviceVO vo, HttpServletRequest req) {
		
		vo.setBusi_num(vo.getBusi_num());
		vo.setCustom(vo.getCustom());
		vo.setSHORT(vo.getSHORT());	
		vo.setCeo(vo.getCeo());
		vo.setCharge_person(vo.getCharge_person());
		vo.setBusi_condition(vo.getBusi_condition());
		vo.setItem(vo.getItem());
		vo.setPost_num(vo.getPost_num());
		vo.setAddr1(vo.getAddr1());
		vo.setAddr2(vo.getAddr2());
		vo.setTel(vo.getTel());
		vo.setFax(vo.getFax());
		vo.setHomepage(vo.getHomepage());
		vo.setCo_yn(vo.getCo_yn());
		vo.setForeign_yn(vo.getForeign_yn());
		vo.setTax_yn(vo.getTax_yn());
		vo.setCountry_eng(vo.getCountry_eng());
		vo.setCountry_kor(vo.getCountry_kor());
		
		if(vo.getSpecial_relation() == null) {
			vo.setSpecial_relation("n");
		}
		else {
			vo.setSpecial_relation(vo.getSpecial_relation());
		}
		
		if(vo.getTrade_stop() == null) {
			vo.setTrade_stop("n");
		}
		else {
			vo.setTrade_stop(vo.getTrade_stop());
		}
		
		vo.setContract_period_s(vo.getContract_period_s());
		System.out.println(vo.getContract_period_s());
		vo.setContract_period_e(vo.getContract_period_e());
		vo.setRegi_info_man(vo.getRegi_info_man());
		vo.setRegi_info_date(vo.getRegi_info_date());
		vo.setModi_info_man(vo.getCeo());
		vo.setModi_info_date(vo.getModi_info_date());
		int res = serviceDAO.modify_update(vo);
		
		return Common.loginPage.VIEW_PATH + "data_search.jsp";
	}
	
	@RequestMapping("/service_delete.do")
	public String delete(String busi_num) {
		
		int delete = serviceDAO.delete(busi_num);
		int account_delete = serviceDAO.account_delete(busi_num);
		
		return Common.loginPage.VIEW_PATH + "data_search.jsp"; 
	}
}
