package com.rodrigo.flexmobilidade.infra.security;

import lombok.Data;

/**
 * @author swapnil.mane
 *
 */
@Data
public class JwtAuthRequest {
	
		private String email ;
	    private String password;
	
}
