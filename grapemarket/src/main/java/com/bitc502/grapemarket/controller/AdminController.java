
package com.bitc502.grapemarket.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitc502.grapemarket.model.Board;
import com.bitc502.grapemarket.model.Message;
import com.bitc502.grapemarket.model.Report;
import com.bitc502.grapemarket.model.User;
import com.bitc502.grapemarket.payload.AdminDashBoard;
import com.bitc502.grapemarket.payload.AdminReportDetail;
import com.bitc502.grapemarket.payload.StatisticVolumes;
import com.bitc502.grapemarket.payload.Statistics;
import com.bitc502.grapemarket.service.AdminService;
import com.bitc502.grapemarket.util.Script;
import com.bitc502.grapemarket.util.VisitorCounter;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminServ;

	@GetMapping({ "/", "" })
	public String dashboard(Model model) {
		AdminDashBoard ad = adminServ.setAdminDashBoardData();
		StatisticVolumes sv = adminServ.setStatVol();
		model.addAttribute("AdminDashBoard", ad);
		model.addAttribute("countStatVol", sv);
		return "admin/dashboard";
	}

	@GetMapping("/user")
	public String userTable(Model model) {
		List<User> users = adminServ.getAllUser();
		StatisticVolumes sv = adminServ.setStatVol();
		model.addAttribute("users", users);
		model.addAttribute("countStatVol", sv);
		return "admin/userTable";
	}

	@GetMapping("/stats")
	public String stats(Model model) {
		Statistics stats = adminServ.setStatistics();
		StatisticVolumes sv = adminServ.setStatVol();
		model.addAttribute("currentVisitorCount", VisitorCounter.currentVisitorCount);
		model.addAttribute("Statistics", stats);
		model.addAttribute("countStatVol", sv);
		return "admin/stats";
	}

	@GetMapping({ "/detail/{id}" })
	public String stats(@PathVariable int id, Model model) {
		User user = adminServ.getUserById(id);
		StatisticVolumes sv = adminServ.setStatVol();
		model.addAttribute("user", user);
		model.addAttribute("countStatVol", sv);
		return "admin/userDetail";
	}

	@GetMapping({ "/userDelete/{id}" })
	public @ResponseBody String userDelete(@PathVariable int id, Model model) {
		int result = adminServ.deleteUserById(id);
		if (result == -1) {
			return Script.back("오류 발생");
		}
		return Script.href("/admin/user");
	}

	@GetMapping({ "/changeRole/{id}/{role}" })
	public @ResponseBody String changeRole(@PathVariable int id, @PathVariable String role, Model model) {
		int result = adminServ.changeUserRole(id, role);
		if (result == -1) {
			return Script.back("오류 발생");
		}
		return Script.hrefAndAlert("/admin/detail/" + id, "권한 변경완료");
	}

	@GetMapping({ "/userPostList/{id}" })
	public String userPostList(@PathVariable int id, Model model) {
		User user = adminServ.getUserById(id);
		List<Board> boards = adminServ.getBoardByUserId(id);
		StatisticVolumes sv = adminServ.setStatVol();
		model.addAttribute("user", user);
		model.addAttribute("boards", boards);
		model.addAttribute("countStatVol", sv);
		return "admin/userPostList";
	}

	@GetMapping("/report")
	public String reportTable(Model model) {
		List<Report> reports = adminServ.getUnprocessedReports();
		StatisticVolumes sv = adminServ.setStatVol();
		model.addAttribute("reports", reports);
		model.addAttribute("countStatVol", sv);
		return "admin/reportTable";
	}

	@GetMapping("/reportDetail")
	public String reportDetail(HttpServletRequest request, Model model) {
		int id = Integer.parseInt(request.getParameter("id"));
		AdminReportDetail ad = adminServ.getAdminReportDetailData(id);
		StatisticVolumes sv = adminServ.setStatVol();
		model.addAttribute("AdminReportDetail", ad);
		model.addAttribute("countStatVol", sv);
		return "admin/reportDetail";
	}

	@GetMapping("/restriction")
	public @ResponseBody String restriction(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		int reportId = Integer.parseInt(request.getParameter("reportId"));
		String sort = request.getParameter("sort");
		int result = adminServ.restriction(id, reportId, sort);
		if (result == -1) {
			return Script.back("오류발생");

		}
		return Script.hrefAndAlert("/admin/report", "정상 처리되었습니다.");
	}

	@GetMapping("/chatLog")
	public String chatLog(HttpServletRequest request, Model model) {
		int id = Integer.parseInt(request.getParameter("id"));
		int reportId = Integer.parseInt(request.getParameter("reportId"));
		List<Message> messages = adminServ.getMessagesByChatIdOrderByCreateDateDesc(id);
		Report report = adminServ.getReportById(reportId);
		model.addAttribute("messages", messages);
		model.addAttribute("report", report);
		return "admin/userChatList";

	}

}
