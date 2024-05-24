package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.KicMemberDAO;
import dao.myMemberDAO;
import kic.mskim.MskimRequestMapping;
import kic.mskim.RequestMapping;
import model.KicMember;
import model.myMember;

@WebServlet("/member/*")
public class myMemberController extends MskimRequestMapping{
	@RequestMapping("myPage")
	public String myPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "/view/myPage.jsp";
	} // end of myPage()
	
	@RequestMapping("myJoin")
	public String myJoin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "/view/myMember/myJoin.jsp";
	} // end of myJoin()
	
	@RequestMapping("myJoinPro")
	public String myJoinPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		int birth = Integer.parseInt(request.getParameter("birth"));
		String address = request.getParameter("address");
		String tel = request.getParameter("tel");
		String email = request.getParameter("email");
		
		myMemberDAO dao = new myMemberDAO();
		myMember mine = new myMember(); // DTO bean
		mine.setId(id);
		mine.setPwd(pwd);
		mine.setName(name);
		mine.setBirth(birth);
		mine.setEmail(address);
		mine.setTel(tel);
		mine.setEmail(email);
		
		int num = dao.insertMember(mine);
		
		String msg = "";
		String url = "myJoin";
		
		if(num > 0) {
			msg = name + "님의 회원가입이 완료되었습니다.";
			url = "myLogin";
		} else {
			msg = "회원가입이 실패 하였습니다.";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		return "/view/alert.jsp";
	} // end of myJoinPro()
	
	@RequestMapping("myMemberInfo")
	public String myMemberInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		myMemberDAO dao = new myMemberDAO();
		myMember mem = dao.getMember(id);
		
		request.setAttribute("mem", mem);
		return "/view/myMember/myMemberInfo.jsp";
	} // end of myMemberInfo()
	
	@RequestMapping("myMemberUpdateForm")
	public String myMemberUpdateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		myMemberDAO dao = new myMemberDAO();
		myMember mem = dao.getMember(id);
		request.setAttribute("mem", mem);

		return "/view/myMember/myMemberUpdateForm.jsp";
	} // end of myMemberUpdateForm()
	
	@RequestMapping("myMemberUpdatePro")
	public String myMemberUpdatePro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");
		
		String id = (String)session.getAttribute("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		int birth = Integer.parseInt(request.getParameter("birth"));
		String address = request.getParameter("address");
		String tel = request.getParameter("tel");
		String email = request.getParameter("email");
		
		myMemberDAO dao = new myMemberDAO();
		myMember memdb = dao.getMember(id);
		
		myMember mine = new myMember(); // DTO bean
		mine.setId(id);
		mine.setPwd(pwd);
		mine.setName(name);
		mine.setBirth(birth);
		mine.setEmail(address);
		mine.setTel(tel);
		mine.setEmail(email);
		
		String msg = "";
		String url = "myMemberUpdateForm";
		
		if (memdb != null) {
			if (memdb.getPwd().equals(pwd)) {
				msg = "수정 완료";
				dao.updateMember(mine);
				url = "myMemberInfo";
			} else {
				msg = "비밀번호가 틀렸습니다.";
				url = "myMemberUpdateForm";
			} // end of if (memdb.getPwd().equals(pwd))
		} else {
			msg = "수정할 수 없습니다.";
		} // end of if (memdb != null)
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		return "/view/alert.jsp";
	} // end of myMemberUpdatePro()
	
	@RequestMapping("myMemberDeleteForm")
	public String myMemberDeleteForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "/view/member/myMemberDeleteForm.jsp";
	} // end of myMemberDeleteForm()
	
	@RequestMapping("myMemberDeletePro")
	public String myMemberDeletePro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");
		
		String id = (String)session.getAttribute("id");
		String pwd = request.getParameter("pwd");
		
		myMemberDAO dao = new myMemberDAO();
		myMember memdb = dao.getMember(id);
		
		String msg = "";
		String url = "myMemberDeleteForm";
		
		if (memdb != null) {
			if (memdb.getPwd().equals(pwd)) {
				msg = "탈퇴 완료";
				session.invalidate();
				dao.deleteMember(id); 	
				url = "myPage";
			} else {
				msg = "비밀번호가 틀렸습니다.";
			} // end of if (memdb.getPwd().equals(pwd))
		} else {
			msg = "탈퇴할 수 없습니다.";
		} // end of if (memdb != null)
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		return "/view/alert.jsp";
	} // end of myMemberDeletePro()
	
	@RequestMapping("myMemberPassForm")
	public String myMemberPassForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		return "/view/member/myMemberPassForm.jsp";
	} // end of myMemberPassForm()
	
	@RequestMapping("myMemberPassPro")
	public String myMemberPassPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");
		
		String id = (String)session.getAttribute("id");
		String pwd = request.getParameter("pwd");
		String modPwd = request.getParameter("modPwd");
		
		myMemberDAO dao = new myMemberDAO();
		myMember memdb = dao.getMember(id);
		
		String msg = "";
		String url = "myMemberPassForm";
		
		if (memdb != null) {
			if (memdb.getPwd().equals(pwd)) {
				msg = "수정 완료";
				session.invalidate();
				dao.modifyPwd(id, modPwd); 	
				url = "myLogin";
			} else {
				msg = "비밀번호가 틀렸습니다.";
				url = "myMemberPassForm";
			} // end of if (memdb.getPwd().equals(pwd))
		} else {
			msg = "수정할 수 없습니다.";
		} // end of if (memdb != null)
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		return "/view/alert.jsp";
	} // end of myMemberPassPro()
	
	@RequestMapping("myLogin")
	public String myLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "/view/member/myLogin.jsp";
	} // end of myLogin()
	
	@RequestMapping("myLogout")
	public String myLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		request.setAttribute("msg", "로그아웃 되었습니다.");
		request.setAttribute("url", "myPage");
		return "/view/alert.jsp";
	} // end of myLogout()
	
	@RequestMapping("myLoginPro")
	public String myLoginPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");

		// Connection 객체
		myMemberDAO dao = new myMemberDAO();
		
		String msg = id + "님으로 로그인 하셨습니다.";
		String url = "myPage";
		
		myMember mem = dao.getMember(id);
		if (mem != null) {
			if (pwd.equals(mem.getPwd())) {
				session.setAttribute("id", id);
			} else {
				msg = "비밀번호가 틀렸습니다";
				url = "myLogin";
			} // end of if (pwd.equals(mem.getPwd())))
		} else {
			msg = "존재하지 않는 아이디입니다.";
			url = "myLogin";
		} // end of if (mem != null)
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return "/view/alert.jsp";
	} // end of myLoginPro()
	
	@RequestMapping("myMemberList")
	public String myMemberList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		myMemberDAO dao = new myMemberDAO();
		List<myMember> li = dao.myMemberList();
		
		request.setAttribute("li", li);
		return "/view/member/myMemberList.jsp";
	} // end of myMemberList()
}
