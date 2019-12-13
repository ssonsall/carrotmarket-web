package com.bitc502.grapemarket.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitc502.grapemarket.model.Board;
import com.bitc502.grapemarket.model.Chat;
import com.bitc502.grapemarket.model.User;
import com.bitc502.grapemarket.repository.BoardRepository;
import com.bitc502.grapemarket.repository.ChatRepository;
import com.bitc502.grapemarket.repository.UserRepository;
import com.bitc502.grapemarket.util.Script;

@Controller
@RequestMapping("/test")
public class TestController {

	@Autowired
	private UserRepository uRepo;

	@Autowired
	private BoardRepository bRepo;
	
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
	
	//chat page test용
	@GetMapping("/chat")
	public String testChat1(Model model) {
		int userId = 1;
		
		//AuthenticationPrincipal(?) 에서 가져온 user id로 유저 가져오기
		Optional<User> oUser = uRepo.findById(userId);
		User user = oUser.get();
		
		//위에서 얻은 유저로 해당 유저와 관련된 채팅 찾기
		List<Chat> chats = cRepo.findByUser(user);
		List<User> users = new ArrayList<User>();
		for (Chat chat : chats) {
			Optional<Board> oBoard = bRepo.findById(chat.getBoard().getId());
			Board board = oBoard.get();
			
			//걍 덮어써버려. 메세지 검색땐 어차피 chat id로 찾을거기 때문에 그거만 안건들면 됨.
			chat.setUser(board.getUser());
		}
		
		model.addAttribute("chats",chats);
		//똑바로 나옴(ResponseBody로 체크)
		
		//채팅 페이지로 넘어오는 방법
		//1. nav에 있는 채팅버튼 눌러서 (넘어오는 보드아이디가 없는 경우, 주소로 구분하면 될듯)
		//  해당 유저 아이디로 채팅 검색 -> 채팅 목록 누르면 (페이지 로딩하고 아무것도 안눌렀을때 뭐 디폴트 화면 채팅목록을 선택해주세요 같이)
		// -> 채팅이 가지고 있는 보드아이디로 상품정보 왼쪽에 // 메세지에서 채팅아이디로 검색해서 대화내용 오른쪽 (Ajax)
		
		//2. detail에서 채팅요청 눌러서 (넘어오는 보드아이디가 있는 경우, 주소로 구분하면 될듯)
		// detail에서 눌러서 오면 해당 디테일에서 넘어온 보드아이디 넣고 채팅방 생성 맨 위에 표시 . 선택된 상태로
		// 왼쪽에 해당 상품정보 표시 오른쪽에 채팅방 표시(아직 DB에 Insert 안함)
		// 메세지 입력 후 전송하면 채팅방 DB에 Insert함.
		return "chat/chat";
	}
	
	//chat page test용
	@GetMapping("/chat/{id}")
	public List<Chat> testChat2(@PathVariable int id) {
		int boardId = id;
		int userId = 1;
		
		//detail에서 넘어온 board id로 보드 가져오기
		Optional<Board> oBoard =  bRepo.findById(boardId);
		Board board = oBoard.get();
		
		//AuthenticationPrincipal(?) 에서 가져온 user id로 유저 가져오기
		Optional<User> oUser = uRepo.findById(userId);
		User user = oUser.get();
		
		//위에서 얻은 유저로 해당 유저와 관련된 채팅 찾기
		List<Chat> chats = cRepo.findByUser(user);
		
		//똑바로 나옴(ResponseBody로 체크)
		
		//채팅 페이지로 넘어오는 방법
		//1. nav에 있는 채팅버튼 눌러서 (넘어오는 보드아이디가 없는 경우, 주소로 구분하면 될듯)
		//  해당 유저 아이디로 채팅 검색 -> 채팅 목록 누르면 (페이지 로딩하고 아무것도 안눌렀을때 뭐 디폴트 화면 채팅목록을 선택해주세요 같이)
		// -> 채팅이 가지고 있는 보드아이디로 상품정보 왼쪽에 // 메세지에서 채팅아이디로 검색해서 대화내용 오른쪽 (Ajax)
		
		//2. detail에서 채팅요청 눌러서 (넘어오는 보드아이디가 있는 경우, 주소로 구분하면 될듯)
		// detail에서 눌러서 오면 해당 디테일에서 넘어온 보드아이디 넣고 채팅방 생성 맨 위에 표시 . 선택된 상태로
		// 왼쪽에 해당 상품정보 표시 오른쪽에 채팅방 표시(아직 DB에 Insert 안함)
		// 메세지 입력 후 전송하면 채팅방 DB에 Insert함.
		return chats;
	}

}
