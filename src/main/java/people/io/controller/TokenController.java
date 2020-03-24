package people.io.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import people.io.config.JwtTokenUtil;
import people.io.model.JwtResponse;

@CrossOrigin
@RestController
@RequestMapping(value="/token/", produces=MediaType.APPLICATION_JSON_VALUE)
public class TokenController {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@GetMapping("/")
	public ResponseEntity<?>  getToken() {
		
		String token = jwtTokenUtil.getJWTToken("anonymous");
		return ResponseEntity.ok(new JwtResponse(token));
		
	}

}
