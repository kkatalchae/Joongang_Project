package service.cos;

import service.Action;
import service.ActionForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CosDAO;
import dto.CosDTO;



public class GetCosInfo implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("GetCosInfo.java");
		String cosName = request.getParameter("cosName");
		
		CosDAO cosDAO = CosDAO.getInstance();
		
		CosDTO cosInfo = cosDAO.getCosInfo(cosName);
		
		request.setAttribute("cosInfo", cosInfo);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/main/webapp/cos/cos_board.jsp");
		
		return forward;
	}

}
