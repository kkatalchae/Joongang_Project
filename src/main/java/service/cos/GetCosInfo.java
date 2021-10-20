package service.cos;

import dao.FacilitiesDAO;
import dao.FoodAndPlaceDAO;
import dto.FacilitiesDTO;
import dto.FoodAndPlaceDTO;
import service.Action;
import service.ActionForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CosDAO;
import dto.CosDTO;

import java.util.List;


public class GetCosInfo implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 코스 관련 정보
		System.out.println("GetCosInfo.java");
		String cosName = request.getParameter("cosName");
		
		CosDAO cosDAO = CosDAO.getInstance();
		
		CosDTO cosInfo = cosDAO.getCosInfo(cosName);
		
		request.setAttribute("cosInfo", cosInfo);

		// 맛집, 명소 정보
		FoodAndPlaceDAO fapDAO = FoodAndPlaceDAO.getInstance();

		List<FoodAndPlaceDTO> list = fapDAO.getList(cosName);

		request.setAttribute("list", list);

		// 코스별 시설 위치
		FacilitiesDAO facilDAO = FacilitiesDAO.getInstance();
		List<FacilitiesDTO> facilities = facilDAO.getInfo(cosName);
		request.setAttribute("facilities", facilities);


		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/cos/cos_board.jsp");
		
		return forward;
	}

}
