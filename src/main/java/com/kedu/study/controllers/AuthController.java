package com.kedu.study.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kedu.study.dto.LoginDTO;
import com.kedu.study.dto.LoginResponseDTO;
import com.kedu.study.service.AuthService;
import com.kedu.study.utils.JWTUtil;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private JWTUtil jwt;
	@Autowired
	private AuthService aServ;

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginDTO dto,HttpServletRequest request){
		System.out.println(dto.getId()+"  :  입력된 아이디"+dto.getPw()+"   :   입력된 PW");
		String id =dto.getId();
		request.setAttribute("userId", id);
		LoginResponseDTO userinfo = aServ.findByLoginIdAndPw(dto);
		
		if(true) {
			String token = jwt.createToken(dto.getId(), userinfo.getPer_secure(), userinfo.getPer_function());
			System.out.println(token);
			return ResponseEntity.ok(token);
		}
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
	
	@GetMapping("/user/info")
	public ResponseEntity<String> getInfo(HttpServletRequest request){
		String userId = (String)request.getAttribute("userId");
		String role = (String)request.getAttribute("userRole");
		
		if(userId == null) {
			return ResponseEntity.status(401).body("인증되지 않은 사용자입니다.");
		}
		
		return ResponseEntity.ok("유저 : " + userId + " 권한 : "+ role);
	}
	
	@DeleteMapping("/logout")
	public ResponseEntity<String> logout(){
		return ResponseEntity.ok().build();
	}
	
}
