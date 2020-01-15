package com.bitc502.grapemarket.util;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.bitc502.grapemarket.model.Visitor;
import com.bitc502.grapemarket.repository.VisitorRepository;

@WebListener
public class VisitorCounter implements HttpSessionListener{
	
	private VisitorRepository vRepo;
    public VisitorCounter(VisitorRepository vRepo) {
		this.vRepo = vRepo;
	}
    
	@Override
    public void sessionCreated(HttpSessionEvent arg0){
        HttpSession session = arg0.getSession();
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(session.getServletContext());
        //등록되어있는 빈을 사용할수 있도록 설정해준다
        ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
        HttpServletRequest req = attr.getRequest();
        //request를 파라미터에 넣지 않고도 사용할수 있도록 설정
        System.out.println("세션 생성");
        Visitor visitor = new Visitor();
        try {
        	System.out.println("request>>   "+req.getRemoteAddr());
        	System.out.println("request>>   "+req.getHeader("User-Agent"));
        	System.out.println("request>>   "+req.getHeader("referer"));
            visitor.setIp(req.getRemoteAddr().toString());
            visitor.setAgent(req.getHeader("User-Agent"));//브라우저 정보
            visitor.setRefer(req.getHeader("referer"));//접속 전 사이트 정보
            vRepo.save(visitor);
            System.out.println("session add success");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("session add fail");
		}
//      vRepo.visitCountDAO.insertVisitor(vo);
    }
    @Override
    public void sessionDestroyed(HttpSessionEvent arg0){
    	
     }
}