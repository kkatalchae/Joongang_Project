package service.cos;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CosDAO;
import dto.CosDTO;
import service.Action;
import service.ActionForward;

public class GetCosList implements Action {


	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("CosListAction.java");

		int page = 1; // 현재 페이지 번호
		int limit = 10; // 한 화면에 출력할 데이터 갯수

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		// page=1 : startRow=1, endRow=10
		// page=2 : startRow=11, endRow=20
		int startRow = (page - 1) * limit + 1;
		int endRow = page * limit;

		
		CosDAO dao = CosDAO.getInstance();
	    int listcount = dao.getCount(); // 총 데이터 갯수
		System.out.println("listcount:" + listcount);

		List<CosDTO> coslist = dao.getCosList(startRow, endRow);
		System.out.println("coslist:" + coslist);		
		
		// 총 페이지
		int pageCount = listcount / limit + ((listcount % limit == 0) ? 0 : 1);

		int startPage = ((page - 1) / 10) * limit + 1; // 1, 11, 21...
		int endPage = startPage + 10 - 1; // 10, 20, 30...

		if (endPage > pageCount)endPage = pageCount;

		request.setAttribute("page", page); //현재 페이지 수 
		request.setAttribute("coslist", coslist); // 
 		request.setAttribute("pageCount", pageCount); //
		request.setAttribute("startPage", startPage); // 현재 페이지에 표시할 첫 페이지수
		request.setAttribute("endPage", endPage); // 현재 페이지에 표시할 끝 페이지 수 
		request.setAttribute("listcount", listcount);
		ActionForward forward = new ActionForward();

		// request 객체로 공유를 한 경우에는 dispatcher방식으로 포워딩이 되어야,
		// view 페이지에서 공유한 값에 접근할 수 있다.
		forward.setRedirect(false); // dispatcher 방식으로 포워딩
		forward.setPath("/cos/cos_list.jsp");

		return forward;
	}

}
