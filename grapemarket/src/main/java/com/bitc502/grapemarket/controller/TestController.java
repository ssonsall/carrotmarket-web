package com.bitc502.grapemarket.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitc502.grapemarket.model.Board;
import com.bitc502.grapemarket.model.User;
import com.bitc502.grapemarket.payload.Statistic;
import com.bitc502.grapemarket.payload.Statistics;
import com.bitc502.grapemarket.repository.BoardRepository;
import com.bitc502.grapemarket.repository.ChatRepository;
import com.bitc502.grapemarket.repository.UserRepository;
import com.bitc502.grapemarket.repository.VisitorRepository;
import com.bitc502.grapemarket.security.UserPrincipal;
import com.bitc502.grapemarket.util.Script;
import com.grum.geocalc.BoundingArea;
import com.grum.geocalc.Coordinate;
import com.grum.geocalc.EarthCalc;
import com.grum.geocalc.Point;

@Controller
@RequestMapping("/test")
public class TestController {

	@Autowired
	private UserRepository uRepo;
	@Autowired
	private BoardRepository bRepo;
	@Autowired
	private VisitorRepository vRepo;
	@Autowired
	private ChatRepository cRepo;

	@GetMapping("/userAll")
	public @ResponseBody List<User> userAll() {
		List<User> Users = uRepo.findAll();
		return Users;
	}

	@GetMapping("/category")
	public @ResponseBody User category() {
		Optional<User> oUser = uRepo.findById(2);
		User user = oUser.get();
		return user;
	}

	@GetMapping("/board")
	public @ResponseBody List<Board> board() {
		List<Board> boards = bRepo.findAll();
		return boards;
	}

	@GetMapping("/boarddelete/{id}")
	public @ResponseBody String boardDelete(@PathVariable int id) {
		try {
			bRepo.deleteById(id);
			return Script.href("/test/board");
		} catch (Exception e) {
		}
		return Script.back("Fail Delete");
	}

	@GetMapping("/userdelete/{id}")
	public @ResponseBody String userDelete(@PathVariable int id) {
		try {
			uRepo.deleteById(id);
			return Script.href("/test/userAll");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Script.back("Fail Delete");
	}

	@GetMapping("/pagetest/{page}")
	public @ResponseBody Page<Board> getAccounts(Model model,
			@PageableDefault(sort = { "id" }, direction = Direction.DESC, size = 8) Pageable pageable,
			@PathVariable(value = "page", name = "page") int page) {
		Page<Board> boards = bRepo.findAll(pageable);
		return boards;
	}

	@GetMapping("/pagetest")
	public @ResponseBody Page<Board> getAccountsj(Model model,
			@PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC, size = 8) Pageable pageable) {
		Page<Board> boards = bRepo.findAll(pageable);
		return boards;
	}
//	direction = Direction.DESC,

//	@GetMapping("/page")
//	public @ResponseBody Page<Board> getlist(Model model, @PageableDefault(sort = { "id" }, direction = Direction.DESC, size = 8) Pageable pageable) {
//		Page<Board> boards = bRepo.findAll(pageable);
//		model.addAttribute("boards", boards.getContent());
//		return boards;
//	}

	@GetMapping("/page")
	public String getlist(Model model,
			@PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC, size = 8) Pageable pageable) {
		Page<Board> boards = bRepo.findAll(pageable);
		if (pageable.getPageNumber() >= boards.getTotalPages()) {
			return "redirect:/test/page?page=" + (boards.getTotalPages() - 1);
		}
		int category = 4;
		int countRow = bRepo.countFindByCategory(category);
		int count = 0;
		if (countRow % 8 == 0) {
			count = countRow / 8;
		} else {
			count = (countRow / 8) + 1;
		}
		System.out.println("count >>" + count);
		model.addAttribute("count", count);
		model.addAttribute("boards", boards.getContent());
		return "/board/list";
	}

	@GetMapping("/pageres")
	public @ResponseBody Page<Board> getlistres(Model model,
			@PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC, size = 8) Pageable pageable) {
		Page<Board> boards = bRepo.findAll(pageable);
		if (pageable.getPageNumber() >= boards.getTotalPages()) {
			return null;
		}
		int category = 4;
		int countRow = bRepo.countFindByCategory(category);
		int count = 0;
		if (countRow % 8 == 0) {
			count = countRow / 8;
		} else {
			count = (countRow / 8) + 1;
		}

		System.out.println("count >>" + count);
		model.addAttribute("count", count);
		model.addAttribute("boards", boards.getContent());
		return boards;
	}

	@GetMapping("/liketest")
	public @ResponseBody Page<Board> liketest(@RequestParam("con") String temp, @RequestParam("cate") String cate,
			Pageable page) {
		// 공백제거
		temp = temp.trim();
		// 정규식 형태 만들어주기
		temp = temp.replace(" ", ")(?=.*");
		temp = "(?=.*" + temp + ")";
		if (cate.equals("1"))
			cate = "1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16";
		Page<Board> testU = bRepo.search(cate, temp, page);
		return testU;
	}

	@GetMapping("/stest")
	public @ResponseBody Statistics dashboard(Model model) {
		Statistics stats = new Statistics();
		stats.setVisitorVolume(MaptoStatistic(vRepo.visitorVolume()));
		stats.setMemberVolume(MaptoStatistic(uRepo.memberVolume()));
		stats.setDealVolume(MaptoStatistic(bRepo.DealVolume()));
		stats.setCompletedDealVolume(MaptoStatistic(bRepo.completedDealVolume()));
		stats.setChatVolume(MaptoStatistic(cRepo.chatVolume()));
//		//신고도 추가해야함
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
		}
		System.out.println("statList" + statList);
		return statList;
	}

	@GetMapping("/getDistance")
	public @ResponseBody String getDistance(@AuthenticationPrincipal UserPrincipal userPrincipal) {

		double x1 = userPrincipal.getUser().getAddressX();
		double y1 = userPrincipal.getUser().getAddressY();

		Coordinate lat = Coordinate.fromDegrees(x1);
		Coordinate lng = Coordinate.fromDegrees(y1);
		Point Mine = Point.at(lat, lng);

		User Seoul = uRepo.findByUsername("Seoul");
		lat = Coordinate.fromDegrees(Seoul.getAddressX());
		lng = Coordinate.fromDegrees(Seoul.getAddressY());
		Point SeoulGPS = Point.at(lat, lng);

		double distance = EarthCalc.gcdDistance(SeoulGPS, Mine); // in meters

		System.out.println("distance : " + distance);
		String distanceKm = (distance / 1000) + "km";
		return distanceKm;
	}

	@GetMapping("/distance/{id}/{range}")
	public @ResponseBody List<User> PostDistance(@AuthenticationPrincipal UserPrincipal userPrincipal,
			@PathVariable int id,@PathVariable int range) {

		Coordinate lat = Coordinate.fromDegrees(userPrincipal.getUser().getAddressX());
		Coordinate lng = Coordinate.fromDegrees(userPrincipal.getUser().getAddressY());
		Point Mine = Point.at(lat, lng);

//		Optional<User> oUser = uRepo.findById(id);
//		User user = oUser.get();

//		lat = Coordinate.fromDegrees(user.getAddressX());
//		lng = Coordinate.fromDegrees(user.getAddressY());
//		Point SeoulGPS = Point.at(lat, lng);
//		double distance = EarthCalc.gcdDistance(SeoulGPS, Mine); // in meters
//		String distanceKm = (distance / 1000) + "km";
//		System.out.println("distance : " + distanceKm);

		BoundingArea area = EarthCalc.around(Mine, range*1000);
		Point nw = area.northWest;
		Point se = area.southEast;

		List<User> users = uRepo.findByGPS(nw.latitude, se.latitude, nw.longitude, se.longitude);
		
		System.out.println("nw.latitude : "+nw.latitude);
		System.out.println("se.latitude : "+se.latitude);
		System.out.println("nw.longitude : "+nw.longitude);
		System.out.println("se.longitude : "+se.longitude);

		return users;
	}
	
	@GetMapping("/maptest")
	public String maptest() {
		return "/map/Sample";
	}
}
