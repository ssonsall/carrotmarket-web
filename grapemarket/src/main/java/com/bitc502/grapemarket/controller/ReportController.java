package com.bitc502.grapemarket.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitc502.grapemarket.common.ReportType;
import com.bitc502.grapemarket.model.Board;
import com.bitc502.grapemarket.model.Report;
import com.bitc502.grapemarket.repository.BoardRepository;
import com.bitc502.grapemarket.repository.CommentRepository;
import com.bitc502.grapemarket.repository.ReportRepository;
import com.bitc502.grapemarket.security.UserPrincipal;
import com.bitc502.grapemarket.util.Script;

@Controller
@RequestMapping("/report")
public class ReportController {

	@Autowired
	private BoardRepository bRepo;

	@Autowired
	private CommentRepository cRepo;

	@Autowired
	private ReportRepository rRepo;

	@GetMapping("/boardReportForm")
	public String boardReportForm(HttpServletRequest request, Model model) {
		int id = Integer.parseInt(request.getParameter("id"));
		Optional<Board> oBoard = bRepo.findById(id);
		Board board = oBoard.get();
		model.addAttribute("board", board);
		return "board/reportForm";
	}

	@PostMapping("/boardReport")
	public @ResponseBody String boardReport(Report report, @AuthenticationPrincipal UserPrincipal userPrincipal) {
		try {
			report.setReportType(ReportType.board);
			report.setUser(userPrincipal.getUser());
			rRepo.save(report);
		} catch (Exception e) {
			return Script.back("오류");
		}
		return Script.back("정상적으로 처리되었습니다.");
	}
}
