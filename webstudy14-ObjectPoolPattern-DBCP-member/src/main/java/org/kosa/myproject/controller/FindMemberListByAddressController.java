package org.kosa.myproject.controller;

import java.util.ArrayList;

import org.kosa.myproject.model.MemberDao;
import org.kosa.myproject.model.MemberVo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FindMemberListByAddressController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("memberList",
				MemberDao.getInstance().findMemberListByAddress(request.getParameter("address")));
		return "member-list.jsp";

	}

}
