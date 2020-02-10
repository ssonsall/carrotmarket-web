package com.bitc502.grapemarket.util;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.bitc502.grapemarket.model.Visitor;
import com.bitc502.grapemarket.repository.VisitorRepository;

@WebListener
public class VisitorCounter implements HttpSessionListener {

	public static int currentVisitorCount;

	private VisitorRepository vRepo;

	public VisitorCounter(VisitorRepository vRepo) {
		this.vRepo = vRepo;
	}

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		currentVisitorCount++;
		HttpSession session = event.getSession();
		// 등록되어있는 빈을 사용할수 있도록 설정해준다
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest req = attr.getRequest();
		// request를 파라미터에 넣지 않고도 사용할수 있도록 설정
		Visitor visitor = new Visitor();
		try {
			visitor.setIp(req.getRemoteAddr().toString());
			visitor.setAgent(req.getHeader("User-Agent"));// 브라우저 정보
			visitor.setRefer(req.getHeader("referer"));// 접속 전 사이트 정보
			vRepo.save(visitor);
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.getServletContext().log(" 세션생성 , 접속자수 : " + currentVisitorCount);

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		currentVisitorCount--;
		if (currentVisitorCount < 0)
			currentVisitorCount = 0;
		HttpSession session = event.getSession();
		session.getServletContext().log(" 세션소멸 , 접속자수 : " + currentVisitorCount);
	}

}