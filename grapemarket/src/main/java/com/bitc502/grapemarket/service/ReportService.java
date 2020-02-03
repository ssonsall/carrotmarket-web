package com.bitc502.grapemarket.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitc502.grapemarket.model.Board;
import com.bitc502.grapemarket.model.Comment;
import com.bitc502.grapemarket.model.Message;
import com.bitc502.grapemarket.model.Report;
import com.bitc502.grapemarket.model.User;
import com.bitc502.grapemarket.payload.ReportFormData;
import com.bitc502.grapemarket.repository.BoardRepository;
import com.bitc502.grapemarket.repository.CommentRepository;
import com.bitc502.grapemarket.repository.MessageRepository;
import com.bitc502.grapemarket.repository.ReportRepository;

@Service
public class ReportService {

	@Autowired
	private BoardRepository bRepo;

	@Autowired
	private CommentRepository cRepo;

	@Autowired
	private ReportRepository rRepo;

	@Autowired
	private MessageRepository mRepo;

	public ReportFormData reportFormData(int id, String type) {
		ReportFormData rf = new ReportFormData();
		try {
			if (type.equals("board")) {
				Optional<Board> oBoard = bRepo.findById(id);
				Board board = oBoard.get();
				rf.setObject(board);
			} else if (type.equals("comment")) {
				Optional<Comment> oComment = cRepo.findById(id);
				Comment comment = oComment.get();
				rf.setObject(comment);
			} else if (type.equals("message")) {
				Optional<Message> oMessage = mRepo.findById(id);
				Message message = oMessage.get();
				rf.setObject(message);
			}
			rf.setType(type);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rf;
	}

	public int reportProc(Report report, User user) {
		try {
			report.setUser(user);
			rRepo.save(report);
			return 1;
		} catch (Exception e) {
		}
		return -1;
	}

}
