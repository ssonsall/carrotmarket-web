package com.bitc502.grapemarket.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitc502.grapemarket.oauthutil.OAuthUtil;
import com.bitc502.grapemarket.payload.OAuthToken;
import com.bitc502.grapemarket.payload.OAuthUser;
import com.google.gson.Gson;

@RequestMapping("/oauth")
@Controller
public class OAuthController {

	//Naver 로그인 버튼 누르면 state code 생성해서 요청 session에 저장해야 한다.
	@GetMapping("/naver")
	public String naverLogin(HttpServletRequest request) {
		String state = OAuthUtil.generateState();
		request.getSession().setAttribute("state", state);
		return "redirect:https://nid.naver.com/oauth2.0/authorize?client_id=or2XAlCgalSB_WRmdqkR&response_type=code&redirect_uri=http://localhost:8080/oauth/naver/callback&state="+state;
	}
	
	//redirect:https://nid.naver.com/oauth2.0/authorize?client_id=or2XAlCgalSB_WRmdqkR&response_type=code&redirect_uri=http://localhost:8080/oauth/naver/callback&state="+state
	//위 주소 갔다가 콜백되어오는 곳
	@GetMapping("/naver/callback")
	public @ResponseBody OAuthUser callback(HttpServletRequest request, @RequestParam String code,
			@RequestParam String state) {

		// 콜백 된 state랑 아까 저장한 state랑 비교. 만약 다르면 잘못된 콜백임.
		boolean stateCheck = OAuthUtil.checkStateCode(state, (String) request.getSession().getAttribute("state"));
		String clientId = "or2XAlCgalSB_WRmdqkR";
		
		//유출되면 안됨
		String clientSecret = "hd9q29HosG";

		try {
			if (stateCheck) {
				// 접근 토큰 요청
				URL url;
				url = new URL("https://nid.naver.com/oauth2.0/token?client_id=" + clientId + "&client_secret="
						+ clientSecret + "&grant_type=authorization_code&state=" + state + "&code=" + code);
				HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
				
				conn.setRequestMethod("POST");
				
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				
				StringBuilder sb = new StringBuilder();
				String line;
				
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				
				conn.disconnect();
				br.close();
				
				Gson gson = new Gson();
				OAuthToken oAuthToken = gson.fromJson(sb.toString(), OAuthToken.class); 
				
				
				//리턴받은 접근 토큰으로 해당 유저 프로필 조회해서 정보 가져오기
				url = new URL("https://openapi.naver.com/v1/nid/me");
				
				conn = (HttpsURLConnection) url.openConnection();				
				
				String accessToken = oAuthToken.getAccess_token();
				String tokenType = oAuthToken.getToken_type();
				
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Authorization", tokenType + " " + accessToken);
				
				br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

				sb.setLength(0);
				
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				
				br.close();
				conn.disconnect();
				
				OAuthUser oAuthUser = gson.fromJson(sb.toString(), OAuthUser.class);
				
				//여기까지 오면 oAuthUser에 해당 유저 정보가 온다.
				//최초 로그인(DB조회해서 oAuthUser.getResponse().getId()에 해당하는 사용자가 없으면)이면 우리 회원테이블에 세이브해주고 로그인처리
				//있으면 로그인 처리하면 될 것 같다.(DB 어떻게 하느냐에 따라 처리 달리 하면 됨)
				//로그인 처리는 강제로 세션에 넣는 방식으로 해야한다. 주호쌤 블로그 참조 ( https://blog.naver.com/getinthere/221741763496 )
				//현재 문제점 : 한번 OAuth로그인하고 나니깐 그 다음부터는 아이디 비번 묻지도 않고 바로 처리됨
				//Delete는 아예 연동 끊을때 사용하는거 같은데. 뭘 건드려야 하는지 모르겠음.
				
				return oAuthUser;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
