package min.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import min.board.action.Action;
import min.board.command.ActionCommand;
import min.board.dao.BoardDAO;
import min.board.model.BoardDTO;

public class BoardAddService implements Action {

	@Override
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardDAO boardDAO = new BoardDAO();
		BoardDTO boardDTO = new BoardDTO();
		ActionCommand actionCommand = new ActionCommand();

		String realFolder = "";
		String saveFolder = "./boardUpload";

		realFolder = request.getSession().getServletContext().getRealPath(saveFolder);
		int filesize = 100 * 1024 * 1024;
		boolean result = false;
		try {
			MultipartRequest multipartRequest = new MultipartRequest(request, realFolder, filesize, "utf-8",
					new DefaultFileRenamePolicy());
			boardDTO.setName(multipartRequest.getParameter("name"));
			boardDTO.setPass(multipartRequest.getParameter("pass"));
			boardDTO.setSubject(multipartRequest.getParameter("subject"));
			boardDTO.setContent(multipartRequest.getParameter("content"));
			boardDTO.setAttached_file(
					multipartRequest.getFilesystemName((String) multipartRequest.getFileNames().nextElement()));

			result = boardDAO.boardInsert(boardDTO);
			if (result == false) {
				System.out.println("게시판등록실패");
				return null;
			}
			System.out.println("게시판 등록 완료");

			actionCommand.setRedirect(true);
			actionCommand.setPath("./BoardList.do");
			return actionCommand;

		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;
	}

}
