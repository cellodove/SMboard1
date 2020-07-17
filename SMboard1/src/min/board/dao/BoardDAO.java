package min.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import min.board.model.BoardDTO;

public class BoardDAO {
	
	//일반메소드로 이클래스에 접근하면 DB에 연결이됐는지 처음먼저 확인한다.
	public BoardDAO() {
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:comp/env/jdbc");
			System.out.println(dataSource+"연결되었습니다.");
		} catch (NamingException e) {
			System.out.println("DB 연결 실패 ㅠㅠ");
			e.printStackTrace();
		}
	}
	/////////////////////////////////////////////////////////////////////////////////////////////
	//개시판의 글 개수를 계산한다.
	public int getListCount() {
		//카운트하는 변수
		int i=0;
		
		//DB연결하고 sql문 넣고 결과 초기화
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection();
			
			String sql = "select count(*) form jboard";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			//만약 결과가있다면
			if (resultSet.next()) {
				i=resultSet.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("글의 갯수를 구하지 못했다 ㅠㅠ");
			e.printStackTrace();
		}finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (Exception e) {
				System.out.println("글갯수 DB연결을 종료하지 못했다 ㅠㅠ");
				e.printStackTrace();
			}
		}
		return i;
	}
	/////////////////////////////////////////////////////////////////////////////////////////////
	//게시판의 글목록을 반환한다.
	public List<BoardDTO> getBoardList(int page, int limit) {
		//글목록을 어레이 리스트에 저장한다.
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		
		//아직작동방법모름 알게되면 주석작성
		int startRow = (page-1)*10+1;
		int endRow = startRow+limit-1;
		
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection();
			//sql문을 이용해서 레코드들을 정리 출력한다.
			String sql = "select * from(select rownum rnum,num,name,subject,content,";
			sql+="attached_file,answer_num,answer_lev,answer_seq,read_count,write_date";
			sql+=" from(select*from jboard order by answer_num desc,answer_seq asc))";
			sql+=" where rnum>=? and rnum<=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, startRow);
			preparedStatement.setInt(2, endRow);
			resultSet = preparedStatement.executeQuery();
			
			//결과들을 뽑아서 DTO에 넣는다.
			while (resultSet.next()){
				BoardDTO boardDTO = new BoardDTO();
				boardDTO.setNum(resultSet.getInt("num"));
				boardDTO.setName(resultSet.getString("name"));
				boardDTO.setSubject(resultSet.getString("subject"));
				boardDTO.setContent(resultSet.getString("content"));
				boardDTO.setAttached_file(resultSet.getString("attached_file"));
				boardDTO.setAnswer_num(resultSet.getInt("answer_num"));
				boardDTO.setAnswer_lev(resultSet.getInt("answer_lev"));
				boardDTO.setAnswer_seq(resultSet.getInt("answer_seq"));
				boardDTO.setRead_count(resultSet.getInt("read_count"));
				boardDTO.setWrite_date(resultSet.getDate("write_date"));
				//결과뽑아올때마다 리스트에 넣는다.
				list.add(boardDTO);
			}
		} catch (Exception e) {
			System.out.println("글목록보기 실패했습니다 ㅠㅠ");
			e.printStackTrace();
			
		}finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (Exception e) {
				System.out.println("글목록불러오기 DB종료하지못함 ㅠㅠ");
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	
	
	
	
	
	
	
}
