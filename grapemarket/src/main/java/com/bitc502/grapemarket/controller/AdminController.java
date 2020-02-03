
package com.bitc502.grapemarket.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitc502.grapemarket.common.ReportType;
import com.bitc502.grapemarket.common.Role;
import com.bitc502.grapemarket.model.Board;
import com.bitc502.grapemarket.model.Chat;
import com.bitc502.grapemarket.model.Comment;
import com.bitc502.grapemarket.model.Message;
import com.bitc502.grapemarket.model.Report;
import com.bitc502.grapemarket.model.User;
import com.bitc502.grapemarket.payload.AdminDashBoard;
import com.bitc502.grapemarket.payload.Statistic;
import com.bitc502.grapemarket.payload.StatisticVolumes;
import com.bitc502.grapemarket.payload.Statistics;
import com.bitc502.grapemarket.repository.BoardRepository;
import com.bitc502.grapemarket.repository.ChatRepository;
import com.bitc502.grapemarket.repository.CommentRepository;
import com.bitc502.grapemarket.repository.MessageRepository;
import com.bitc502.grapemarket.repository.ReportRepository;
import com.bitc502.grapemarket.repository.SearchRepository;
import com.bitc502.grapemarket.repository.UserRepository;
import com.bitc502.grapemarket.repository.VisitorRepository;
import com.bitc502.grapemarket.util.Script;
import com.bitc502.grapemarket.util.VisitorCounter;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserRepository uRepo;
	@Autowired
	private BoardRepository bRepo;
	@Autowired
	private VisitorRepository vRepo;
	@Autowired
	private ChatRepository cRepo;
	@Autowired
	private CommentRepository commentRepo;
	@Autowired
	private MessageRepository mRepo;
	@Autowired
	private SearchRepository sRepo;
	@Autowired
	private ReportRepository rRepo;

	@GetMapping({ "/", "" })
	public String dashboard(Model model) {
		AdminDashBoard ad = new AdminDashBoard();
		ad.setCurrentVisitorCount(VisitorCounter.currentVisitorCount);
		ad.setUsers(uRepo.findTop3ByOrderByIdDesc());
		ad.setBoards(bRepo.findTop3ByOrderByIdDesc());
		ad.setReports(rRepo.findTop3ByOrderByIdDesc());
		ad.setSearchs(sRepo.wholeTimePopularThreeKeyword());
		model.addAttribute("AdminDashBoard", ad);
		model.addAttribute("countStatVol", countStatVol());
		return "admin/dashboard";
	}

	@GetMapping({ "/user" })
	public String userTable(Model model) {
		List<User> users = uRepo.findAll();
		model.addAttribute("users", users);
		model.addAttribute("countStatVol", countStatVol());
		return "admin/userTable";
	}

	@GetMapping({ "/stats" })
	public String stats(Model model) {
		Statistics stats = new Statistics();
		stats.setVisitorVolume(MaptoStatistic(vRepo.visitorVolume()));
		stats.setMemberVolume(MaptoStatistic(uRepo.memberVolume()));
		stats.setDealVolume(MaptoStatistic(bRepo.DealVolume()));
		stats.setCompletedDealVolume(MaptoStatistic(bRepo.completedDealVolume()));
		stats.setChatVolume(MaptoStatistic(cRepo.chatVolume()));
		stats.setReportVolume(MaptoStatistic(rRepo.reportVolume()));

		// 신고도 추가해야함
		model.addAttribute("currentVisitorCount", VisitorCounter.currentVisitorCount);
		model.addAttribute("Statistics", stats);
		model.addAttribute("countStatVol", countStatVol());
		return "admin/stats";
	}

	public List<Statistic> MaptoStatistic(List<Map<String, Object>> list) {
		List<Statistic> statList = new ArrayList<>();
		try {
			for (Map<String, Object> map : list) {
				Statistic stat = new Statistic();
				stat.setCreateDate(map.get("date").toString());
				Long count = (long) Integer.parseInt(map.get("count").toString());
				stat.setCount(count);
				statList.add(stat);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return statList;
	}

	@GetMapping({ "/detail/{id}" })
	public String stats(@PathVariable int id, Model model) {
		Optional<User> oUser = uRepo.findById(id);
		User user = oUser.get();
		model.addAttribute("user", user);
		model.addAttribute("countStatVol", countStatVol());
		return "admin/userDetail";
	}

	@GetMapping({ "/userDelete/{id}" })
	public String userDelete(@PathVariable int id, Model model) {
		uRepo.deleteById(id);
		return "redirect:/admin/user";
	}

	@GetMapping({ "/changeRole/{id}/{role}" })
	public @ResponseBody String changeRole(@PathVariable int id, @PathVariable String role, Model model) {
		Optional<User> oUser = uRepo.findById(id);
		User user = oUser.get();
		if (role.equals("ADMIN")) {
			user.setRole(Role.valueOf("ADMIN"));
		} else if (role.equals("USER")) {
			user.setRole(Role.valueOf("USER"));
		}
		uRepo.save(user);
		return Script.hrefAndAlert("/admin/detail/" + id, "권한 변경완료");
	}

	@GetMapping({ "/userPostList/{id}" })
	public String userPostList(@PathVariable int id, Model model) {
		Optional<User> oUser = uRepo.findById(id);
		User user = oUser.get();
		List<Board> boards = bRepo.findByUserIdOrderByCreateDateDesc(id);

		model.addAttribute("user", user);
		model.addAttribute("boards", boards);
		model.addAttribute("countStatVol", countStatVol());
		return "admin/userPostList";
	}

	@GetMapping("/report")
	public String reportTable(Model model) {
		List<Report> reports = rRepo.findAll();
		model.addAttribute("reports", reports);
		model.addAttribute("countStatVol", countStatVol());
		return "admin/reportTable";
	}

	@GetMapping("/reportDetail")
	public String reportDetail(HttpServletRequest request, Model model) {
		int id = Integer.parseInt(request.getParameter("id"));
		Optional<Report> oReport = rRepo.findById(id);
		Report report = oReport.get();
		if (report.getReportType().equals(ReportType.board)) {
			Optional<Board> oBoard = bRepo.findById(report.getReportId());
			Board board = oBoard.get();
			model.addAttribute("reportType", board);
		} else if (report.getReportType().equals(ReportType.comment)) {
			Optional<Comment> oComment = commentRepo.findById(report.getReportId());
			Comment comment = oComment.get();
			model.addAttribute("reportType", comment);
		} else if (report.getReportType().equals(ReportType.message)) {
			Optional<Message> oMessage = mRepo.findById(report.getReportId());
			Message message = oMessage.get();
			Chat chat = cRepo.findById(message.getChat().getId());
			model.addAttribute("reportType", message);
			model.addAttribute("chat", chat);

		}
		model.addAttribute("report", report);
		model.addAttribute("countStatVol", countStatVol());
		return "admin/reportDetail";
	}

	@GetMapping("/restriction")
	public @ResponseBody String restriction(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		String sort = request.getParameter("sort");
		try {
			Optional<User> oUser = uRepo.findById(id);
			User user = oUser.get();
			if (sort.equals("caution1")) {
				user.setRole(Role.CAUTION1);
			} else if (sort.equals("caution2")) {
				user.setRole(Role.CAUTION2);
			} else if (sort.equals("ban")) {
				user.setRole(Role.BANNED);
			}
			uRepo.save(user);
		} catch (Exception e) {
			return Script.back("오류발생");
		}

		return Script.back("정상 처리되었습니다.");
	}

	public StatisticVolumes countStatVol() {
		StatisticVolumes sv = new StatisticVolumes();
		sv.setMemberVolume(uRepo.count());
		sv.setDealVolume(bRepo.count());
		sv.setChatVolume(mRepo.count());
		sv.setReportVolume(rRepo.count());
		return sv;
	}

	@GetMapping("/chatLog")
	public String chatLog(HttpServletRequest request, Model model) {
		int id = Integer.parseInt(request.getParameter("id"));
		int reportId = Integer.parseInt(request.getParameter("reportId"));
		List<Message> messages = mRepo.findByChatIdOrderByCreateDateDesc(id);
		Optional<Report> oReport = rRepo.findById(reportId);
		Report report = oReport.get();
		model.addAttribute("messages", messages);
		model.addAttribute("report", report);
		return "admin/userChatList";

	}

}
