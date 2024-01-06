package com.rodrigo.flexmobilidade.dto.user;

import lombok.Data;

@Data
public class JwtAuthRequest {
	
		private String email ;
	    private String password;
	
}
