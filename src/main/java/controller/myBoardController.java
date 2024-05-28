package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import dao.myBoardDAO;
import dao.myMemberDAO;
import kic.mskim.MskimRequestMapping;
import kic.mskim.RequestMapping;
import model.myBoard;
import model.myMember;

@WebServlet("/myBoard/*")
public class myBoardController extends MskimRequestMapping {

	HttpSession session;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		session = request.getSession();
		super.service(request, response);
	}

	@RequestMapping("myPage")
	public String myPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		return "/view/myPage.jsp";
	}

	@RequestMapping("myBoardForm")
	public String myBoardForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		return "/view/myBoard/myBoardForm.jsp";
	}

	@RequestMapping("myBoardInfo")
	public String myBoardInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// http://localhost:8080/kicmodel2/board/boardInfo?num=10

		int num = Integer.parseInt(request.getParameter("num"));
		System.out.println(num);
		myBoardDAO dao = new myBoardDAO();
		int count = dao.addReadCount(num);
		myBoard board = dao.getBoard(num);

		request.setAttribute("board", board);
		return "/view/byBoard/myBoardInfo.jsp";
	}

	@RequestMapping("myBoardUpdateForm")
	public String myBoardUpdateForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// http://localhost:8080/kicmodel2/board/boardInfo?num=10
		int num = Integer.parseInt(request.getParameter("num"));
		System.out.println(num);
		myBoardDAO dao = new myBoardDAO();
		myBoard board = dao.getBoard(num);

		request.setAttribute("board", board);
		return "/view/myBoard/myBoardUpdateForm.jsp";
	}

	@RequestMapping("myBoardUpdatePro")
	public String myBoardUpdatePro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// http://localhost:8080/kicmodel2/board/boardInfo?num=10
		String path = request.getServletContext().getRealPath("/") + "img/board/";
		MultipartRequest multi = new MultipartRequest(request, path, 10 * 1024 * 1024, "UTF-8");
		int num = Integer.parseInt(multi.getParameter("num"));
		String pwd = multi.getParameter("pwd");
		System.out.println(num);
		myBoardDAO dao = new myBoardDAO();
		myBoard board = new myBoard();
		myBoard boarddb = dao.getBoard(num);
		board.setNum(num);
		board.setContent(multi.getParameter("content"));
		board.setSubject(multi.getParameter("subject"));
		board.setName(multi.getParameter("name"));
		if (multi.getFilesystemName("file1") == null)
			board.setFile1(multi.getParameter("originfile"));
		else
			board.setFile1(multi.getFilesystemName("file1"));
		String msg = "수정 되지 않았습니다";
		String url = "myBoardUpdateForm?num=" + num;
		System.out.println(boarddb);
		if (boarddb != null) {
			if (pwd.equals(boarddb.getPwd())) {
				int count = dao.boardUpdate(board);
				if (count == 1) {
					msg = "수정 되었습니다";
					url = "myBoardInfo?num=" + num;
				}
			} else {
				msg = "비밀번호 확인 하세요";
			}
		} else {
			msg = "게시물이 없습니다";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return "/view/alert.jsp";
	}

	@RequestMapping("myBoardPro")
	public String myBoardPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletContext().getRealPath("/") + "img/board/";

		MultipartRequest multi = new MultipartRequest(request, path, 10 * 1024 * 1024, "UTF-8");
		String boardid = (String) session.getAttribute("boardid");// 1
		myBoard myBoard = new myBoard();
		myBoard.setName(multi.getParameter("name"));
		myBoard.setPwd(multi.getParameter("pwd"));
		myBoard.setSubject(multi.getParameter("subject"));
		myBoard.setContent(multi.getParameter("content"));
		myBoard.setFile1(multi.getFilesystemName("file1"));
		myBoard.setBoardid(boardid); // 2
		System.out.println(myBoard);
		myBoardDAO dao = new myBoardDAO();
		int num = dao.insertBoard(myBoard);
		String msg = "게시물 등록 성공";
		String url = "myBoardList?boardid=" + boardid;
		if (num == 0) {
			msg = "게시물 등록 실패";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return "/view/alert.jsp";
	}

	@RequestMapping("myBoardList")
	public String myBoardList(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		myBoardDAO dao = new myBoardDAO();
		String boardid = request.getParameter("boardid");
		session.setAttribute("boardid", boardid);
		
		if(session.getAttribute("boardid")==null) {
			boardid="1";
		}
		
		String pageNum = request.getParameter("pageNum");
		session.setAttribute("pageNum", pageNum);
		if(session.getAttribute("pageNum")==null) {
			pageNum="1";
		}
		
		String boardName = "";
		switch (boardid) {
		case "1":
			boardName = "공지사항";
			break;
		case "2":
			boardName = "자유게시판";
			break;
		case "3":
			boardName = "QnA";
			break;
		default:
			boardName = "공지사항";
		}
		int count = dao.boardCount(boardid);
		int limit = 3;
		int pageInt = Integer.parseInt(pageNum); // 페이지 목록 번호
		int boardNum = count - ((pageInt-1)*limit); // 각 페이지의 첫번째 게시글
		
		int bottomLine = 3; // 하단 페이지 목록 갯수
		int start = (pageInt - 1) / bottomLine * bottomLine + 1; // 하단 페이지 목록에서 start 페이지 번호
		int end = start + limit - 1; // 하단 페이지 목록 마지막 페이지 번호
		int maxPage = (int)Math.ceil((double)count/(double)limit); // 페이지 목록 갯수
		if(end>maxPage) {
			end = maxPage;
		}
		
		List<myBoard> li = dao.myBoardList(boardid, pageInt, limit);
		
		request.setAttribute("bottomLine", bottomLine);
		request.setAttribute("start", start);
		request.setAttribute("end", end);
		request.setAttribute("maxPage", maxPage);
		request.setAttribute("pageInt", pageInt);
		request.setAttribute("boardNum", boardNum);
		
		request.setAttribute("boardName", boardName);
		request.setAttribute("li", li);
		request.setAttribute("boardid", boardid);
		request.setAttribute("nav", boardid);
		request.setAttribute("count", count);

		return "/view/myBoard/myBoardList.jsp";
	}

	@RequestMapping("myBoardDeleteForm")
	public String myBoardDeleteForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("num", request.getParameter("num"));
		return "/view/myBoard/myBoardDeleteForm.jsp";
	}

	@RequestMapping("myBoardDeletePro")
	public String myBoardDeletePro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("num"));
		String pwd = request.getParameter("pwd");
		String boardid = (String) session.getAttribute("boardid"); // 1
		myBoardDAO dao = new myBoardDAO();
		myBoard boarddb = dao.getBoard(num);
		String msg = "삭제 되지 않았습니다";
		String url = "myBoardDeleteForm?num=" + num;
		if (boarddb != null) {
			if (pwd.equals(boarddb.getPwd())) {
				int count = dao.boardDelete(num);
				if (count == 1) {
					msg = "삭제 되었습니다";
					url = "myBoardList?boardid=" + boardid;// 2
				}
			} else {
				msg = "비밀번호 확인 하세요";
			}
		} else {
			msg = "게시물이 없습니다";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return "/view/alert.jsp";
	}

}