package com.bitc502.grapemarket.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitc502.grapemarket.model.Board;
import com.bitc502.grapemarket.model.Role;
import com.bitc502.grapemarket.model.Statistic;
import com.bitc502.grapemarket.model.Statistics;
import com.bitc502.grapemarket.model.User;
import com.bitc502.grapemarket.repository.BoardRepository;
import com.bitc502.grapemarket.repository.ChatRepository;
import com.bitc502.grapemarket.repository.UserRepository;
import com.bitc502.grapemarket.repository.VisitorRepository;
import com.bitc502.grapemarket.util.Script;
import com.bitc502.grapemarket.util.VisitorCounter;

@Controller
@RequestMapping("/admin")
public class AdminTestController {

	@Autowired
	private UserRepository uRepo;
	@Autowired
	private BoardRepository bRepo;
	@Autowired
	private VisitorRepository vRepo;
	@Autowired
	private ChatRepository cRepo;
	

	@GetMapping({ "/", "" })
	public String dashboard(Model model) {
		
		model.addAttribute("currentVisitorCount", VisitorCounter.currentVisitorCount);
		return "admin/dashboard";
	}

	@GetMapping({ "/user" })
	public String userTable(Model model) {
		List<User> users = uRepo.findAll();
		model.addAttribute("users", users);
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
		//신고도 추가해야함		
		model.addAttribute("currentVisitorCount", VisitorCounter.currentVisitorCount);
		model.addAttribute("stats", stats);
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
		System.out.println("statList" + statList);
		return statList;
	}
	
	@GetMapping({ "/detail/{id}" })
	public String stats(@PathVariable int id, Model model) {
		Optional<User> oUser = uRepo.findById(id);
		User user = oUser.get();
		model.addAttribute("user", user);
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
		List<Board> boards= bRepo.findByUserIdOrderByCreateDateDesc(id);

		model.addAttribute("user", user);
		model.addAttribute("boards", boards);
		return "admin/userPostList";
	}
	
}
