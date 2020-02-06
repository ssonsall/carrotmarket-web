package com.bitc502.grapemarket.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitc502.grapemarket.common.ReportType;
import com.bitc502.grapemarket.common.Role;
import com.bitc502.grapemarket.model.Board;
import com.bitc502.grapemarket.model.Chat;
import com.bitc502.grapemarket.model.Comment;
import com.bitc502.grapemarket.model.Message;
import com.bitc502.grapemarket.model.Report;
import com.bitc502.grapemarket.model.User;
import com.bitc502.grapemarket.payload.AdminDashBoard;
import com.bitc502.grapemarket.payload.AdminReportDetail;
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
import com.bitc502.grapemarket.util.VisitorCounter;

import io.sentry.Sentry;

@Service
public class AdminService {

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

	public List<User> getAllUser() {
		List<User> users = new ArrayList<User>();
		try {
			users = uRepo.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			Sentry.capture(e);
		}
		return users;
	}

	public List<Board> getBoardByUserId(int id) {
		List<Board> boards = new ArrayList<Board>();
		try {
			boards = bRepo.findByUserIdOrderByCreateDateDesc(id);
		} catch (Exception e) {
			e.printStackTrace();
			Sentry.capture(e);
		}
		return boards;
	}

	public AdminDashBoard setAdminDashBoardData() {
		AdminDashBoard ad = new AdminDashBoard();
		try {
			ad.setCurrentVisitorCount(VisitorCounter.currentVisitorCount);
			ad.setUsers(uRepo.findTop3ByOrderByIdDesc());
			ad.setBoards(bRepo.findTop3ByOrderByIdDesc());
			ad.setReports(rRepo.findTop3ByOrderByIdDesc());
			ad.setSearchs(sRepo.wholeTimePopularThreeKeyword());
		} catch (Exception e) {
			e.printStackTrace();
			Sentry.capture(e);
		}
		return ad;
	}

	public Statistics setStatistics() {
		Statistics stats = new Statistics();
		try {
			stats.setVisitorVolume(MaptoStatistic(vRepo.visitorVolume()));
			stats.setMemberVolume(MaptoStatistic(uRepo.memberVolume()));
			stats.setDealVolume(MaptoStatistic(bRepo.DealVolume()));
			stats.setCompletedDealVolume(MaptoStatistic(bRepo.completedDealVolume()));
			stats.setChatVolume(MaptoStatistic(cRepo.chatVolume()));
			stats.setReportVolume(MaptoStatistic(rRepo.reportVolume()));
		} catch (Exception e) {
			e.printStackTrace();
			Sentry.capture(e);
		}
		return stats;
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
			Sentry.capture(e);
		}
		return statList;
	}

	public User getUserById(int id) {
		User user = new User();
		try {
			Optional<User> oUser = uRepo.findById(id);
			user = oUser.get();
		} catch (Exception e) {
			e.printStackTrace();
			Sentry.capture(e);
		}
		return user;
	}

	public int deleteUserById(int id) {
		try {
			uRepo.deleteById(id);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			Sentry.capture(e);
		}
		return -1;
	}

	public int changeUserRole(int id, String role) {
		try {
			Optional<User> oUser = uRepo.findById(id);
			User user = oUser.get();
			if (role.equals("ADMIN")) {
				user.setRole(Role.valueOf("ADMIN"));
			} else if (role.equals("USER")) {
				user.setRole(Role.valueOf("USER"));
			}
			uRepo.save(user);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			Sentry.capture(e);
		}
		return -1;
	}

	public StatisticVolumes setStatVol() {
		StatisticVolumes sv = new StatisticVolumes();
		try {
			sv.setMemberVolume(uRepo.count());
			sv.setDealVolume(bRepo.count());
			sv.setChatVolume(mRepo.count());
			sv.setReportVolume(rRepo.countByState("0"));
		} catch (Exception e) {
			e.printStackTrace();
			Sentry.capture(e);
		}
		return sv;
	}

	public Report getReportById(int id) {
		Report report = new Report();
		try {
			Optional<Report> oReport = rRepo.findById(id);
			report = oReport.get();
		} catch (Exception e) {
			e.printStackTrace();
			Sentry.capture(e);
		}
		return report;
	}

	public List<Report> getUnprocessedReports() {
		List<Report> reports = new ArrayList<Report>();
		try {
			reports = rRepo.findByState("0");
		} catch (Exception e) {
			e.printStackTrace();
			Sentry.capture(e);
		}
		return reports;
	}

	public AdminReportDetail getAdminReportDetailData(int id) {
		AdminReportDetail ad = new AdminReportDetail();
		try {
			Optional<Report> oReport = rRepo.findById(id);
			Report report = oReport.get();
			ad.setReport(report);
			if (report.getReportType().equals(ReportType.board)) {
				Optional<Board> oBoard = bRepo.findById(report.getReportId());
				Board board = oBoard.get();
				ad.setReportType(board);
			} else if (report.getReportType().equals(ReportType.comment)) {
				Optional<Comment> oComment = commentRepo.findById(report.getReportId());
				Comment comment = oComment.get();
				ad.setReportType(comment);
			} else if (report.getReportType().equals(ReportType.message)) {
				Optional<Message> oMessage = mRepo.findById(report.getReportId());
				Message message = oMessage.get();
				Chat chat = cRepo.findById(message.getChat().getId());
				ad.setReportType(message);
				ad.setChat(chat);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Sentry.capture(e);
		}
		return ad;
	}

	public List<Message> getMessagesByChatIdOrderByCreateDateDesc(int id) {
		List<Message> messages = new ArrayList<Message>();
		try {
			messages = mRepo.findByChatIdOrderByCreateDateDesc(id);
		} catch (Exception e) {
			e.printStackTrace();
			Sentry.capture(e);
		}
		return messages;
	}

	public int restriction(int id, int reportId, String sort) {
		try {
			if (!sort.equals("deleteReport")) {
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
			}
			Optional<Report> oReport = rRepo.findById(reportId);
			Report report = oReport.get();
			report.setState("1");
			rRepo.save(report);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			Sentry.capture(e);
		}
		return -1;
	}

}
