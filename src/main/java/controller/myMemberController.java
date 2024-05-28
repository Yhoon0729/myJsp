package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import dao.myMemberDAO;
import kic.mskim.MskimRequestMapping;
import kic.mskim.RequestMapping;
import model.myMember;

@WebServlet("/myMember/*")
public class myMemberController extends MskimRequestMapping {
	HttpSession session;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		session = request.getSession();
		System.out.println("service");
		super.service(request, response);
	}

	@RequestMapping("myPage")
	public String myPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("nav", "myPage");
		return "/view/myPage.jsp";
	} // end of myPage()

	@RequestMapping("myJoin")
	public String myJoin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("nav", "myJoin");
		return "/view/myMember/myJoin.jsp";
	} // end of myJoin()

	@RequestMapping("myJoin2")
	public String myJoin2(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("nav", "myJoin2");
		return "/view/myMember/myJoin2.jsp";
	} // end of myJoin()

	@RequestMapping("myJoinPro")
	public String myJoinPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		mine.setAddress(address);
		mine.setTel(tel);
		mine.setEmail(email);

		int num = dao.insertMember(mine);

		String msg = "";
		String url = "myJoin";

		if (num > 0) {
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
	public String myMemberInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = (String) session.getAttribute("id");
		myMemberDAO dao = new myMemberDAO();
		myMember mem = dao.getMember(id);

		request.setAttribute("mem", mem);
		request.setAttribute("nav", "myMemberInfo");
		return "/view/myMember/myMemberInfo.jsp";
	} // end of myMemberInfo()

	@RequestMapping("myMemberUpdateForm")
	public String myMemberUpdateForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = (String) session.getAttribute("id");
		myMemberDAO dao = new myMemberDAO();
		myMember mem = dao.getMember(id);
		request.setAttribute("mem", mem);

		return "/view/myMember/myMemberUpdateForm.jsp";
	} // end of myMemberUpdateForm()

	@RequestMapping("myMemberUpdatePro")
	public String myMemberUpdatePro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");

		String id = (String) session.getAttribute("id");
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
		mine.setAddress(address);
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
			} // end of if (memdb.getPass().equals(pass))
		} else {
			msg = "수정할 수 없습니다.";
		} // end of if (memdb != null)

		request.setAttribute("msg", msg);
		request.setAttribute("url", url);

		return "/view/alert.jsp";
	} // end of myMemberUpdatePro()

	@RequestMapping("myMemberDeleteForm")
	public String myMemberDeleteForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return "/view/myMember/myMemberDeleteForm.jsp";
	} // end of myMemberDeleteForm()

	@RequestMapping("myMemberDeletePro")
	public String myMemberDeletePro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");

		String id = (String) session.getAttribute("id");
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

	@RequestMapping("myMemberPwdForm")
	public String myMemberPwdForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		return "/view/myMember/myMemberPwdForm.jsp";
	} // end of myMemberPwdForm()

	@RequestMapping("myMemberPwdPro")
	public String myMemberPwdPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String id = (String) session.getAttribute("id");
		String pwd = request.getParameter("pwd");
		String modPwd = request.getParameter("modPwd");

		myMemberDAO dao = new myMemberDAO();
		myMember memdb = dao.getMember(id);

		String msg = "";
		String url = "myMemberPwdForm";

		if (memdb != null) {
			if (memdb.getPwd().equals(pwd)) {
				msg = "수정 완료";
				session.invalidate();
				dao.modifyPwd(id, modPwd);
				url = "myLogin";
			} else {
				msg = "비밀번호가 틀렸습니다.";
				url = "myMemberPwdForm";
			} // end of if (memdb.getPwd().equals(pwd))
		} else {
			msg = "수정할 수 없습니다.";
		} // end of if (memdb != null)

		request.setAttribute("msg", msg);
		request.setAttribute("url", url);

		return "/view/alert.jsp";
	} // end of myMemberPwdPro()

	@RequestMapping("myLogin")
	public String myLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("nav", "myLogin");
		return "/view/myMember/myLogin.jsp";
	} // end of myLogin()

	@RequestMapping("myLogout")
	public String myLogout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		session.invalidate();
		request.setAttribute("msg", "로그아웃 되었습니다.");
		request.setAttribute("url", "myPage");
		return "/view/alert.jsp";
	} // end of myLogout()

	@RequestMapping("myLoginPro")
	public String myLoginPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");

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
	public String myMemberList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		myMemberDAO dao = new myMemberDAO();
		List<myMember> li = dao.memberList();

		request.setAttribute("li", li);
		return "/view/member/myMemberList.jsp";
	} // end of memberList()

	@RequestMapping("pictureimgForm")
	public String pictureimgForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return "/single/pictureimgForm.jsp";
	} // end of pictureimgForm()

	@RequestMapping("picturePro")
	public String picturePro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletContext().getRealPath("/") + "/img/member/picture/";
		System.out.println(path);

		String filename = null;

		MultipartRequest multi = new MultipartRequest(request, path, 10 * 1024 * 1024, "UTF-8");

		filename = multi.getFilesystemName("picture");

		System.out.println(filename);
		request.setAttribute("filename", filename);

		return "/single/picturePro.jsp";
	} // end of picturePro()
}
