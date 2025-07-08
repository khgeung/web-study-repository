package org.kosa.job.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.kosa.job.model.MemberDao;
import org.kosa.job.model.MemberVo;

/**
 * Servlet implementation class Test05Servlet
 */
@WebServlet("/Test05Servlet")
public class Test05Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String address = request.getParameter("address");
		ArrayList<MemberVo> list;
		try {
			list = MemberDao.getInstance().findMemberByAddress(address);
			request.setAttribute("memberList", list);
			request.getRequestDispatcher("step5-jstl-forEach-member-list.jsp").forward(request, response);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
	}

}
