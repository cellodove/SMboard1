package min.board.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import min.board.action.Action;
import min.board.command.ActionCommand;
import min.board.dao.BoardDAO;

public class BoardListService implements Action {
	@Override
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardDAO boardDAO = new BoardDAO();
		
		//리스트는 대부분 데이터 저장용
		List<?> boardList = new ArrayList<Object>();
		
		//기본적으로1페이지부터 시작
		int page=1;
		//글의갯수 최대10개
		int limit=10;
		
		//page파라미터로 전달받은 페이지 수를 할당한다.
		if (request.getParameter("page")!=null) {
			page=Integer.parseInt(request.getParameter("page"));
		}
		
		//게시판의 총 글 수를 호출한다.
		int listcount = boardDAO.getListCount();
		//페이지와 리스트에 표시할 글 수를 파라미터로 전달하여 글 목록을 호출한다.
		boardList=boardDAO.getBoardList(page, limit);
		
		// 총 글 개수에서 표시할 글의 수를 나눈 후 0.95를 더해서 총 페이지 수를 구한다.
		int maxpage = (int) ((double) listcount / limit + 0.95);
		// 현재 페이지에 보여줄 시작 페이지 수를 구한다.
		int startpage = (((int) ((double) page / 10 + 0.9)) - 1) * 10 + 1;
		// 현재 페이지에서 보여줄 마지막 페이지 수를 구한다.
		int endpage = startpage + 10 - 1;
		
		// 마지막 페이지 그룹이라면 마지막 그룹의 페이지 번호는 최대 페이지 개수까지만 출력한다.
		if(endpage > maxpage) {
		endpage = maxpage;
		}
		//DAO계산된거를 추합해서 리퀘스트셋해서 보드리스트로 넘겨주어 보이게해준다.
		request.setAttribute("page", page);
		request.setAttribute("maxpage", maxpage);
		request.setAttribute("startpage", startpage);
		request.setAttribute("endpage", endpage);
		request.setAttribute("listcount", listcount);
		request.setAttribute("boardList", boardList);
		ActionCommand actionCommand = new ActionCommand();
		//리다이렉트안하니깐 포워드로하고
		actionCommand.setRedirect(false);
		//글목록 페이지로 이동한다.
		actionCommand.setPath("./board/board_list.jsp");
		//저 두개내용을 리턴해준다.
		return actionCommand;
	}

}

