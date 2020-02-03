package com.bitc502.grapemarket.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitc502.grapemarket.model.Report;
import com.bitc502.grapemarket.payload.ReportFormData;
import com.bitc502.grapemarket.security.UserPrincipal;
import com.bitc502.grapemarket.service.ReportService;
import com.bitc502.grapemarket.util.Script;

@Controller
@RequestMapping("/report")
public class ReportController {

	@Autowired
	ReportService reportServ;

	@GetMapping("/ReportForm")
	public String ReportForm(HttpServletRequest request, Model model) {
		int id = Integer.parseInt(request.getParameter("id"));
		String type = request.getParameter("type");
		ReportFormData rf = reportServ.reportFormData(id, type);
		model.addAttribute("ReportFormData", rf);
		return "report/reportForm";
	}

	@PostMapping("/reportProc")
	public @ResponseBody String reportProc(Report report, @AuthenticationPrincipal UserPrincipal userPrincipal) {
		int result = reportServ.reportProc(report, userPrincipal.getUser());
		if (result == -1) {
			return Script.back("오류");
		}
		return Script.closePopup("정상 처리되었습니다.");
	}
}
