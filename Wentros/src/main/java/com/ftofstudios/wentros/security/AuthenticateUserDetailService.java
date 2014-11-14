/**
 * 
 */
package com.ftofstudios.wentros.security;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ftofstudios.wentros.vo.UserVO;



/**
 * @author home-pc
 *
 */
@Service
public class AuthenticateUserDetailService implements UserDetailsService {
	
	private Logger logger = Logger.getLogger(AuthenticateUserDetailService.class);
	
	@Autowired
	com.ftofstudios.wentros.manager.UserManager manager;

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		//System.out.println("Called");
		logger.info("Authenticating user for user name : " + userName);
		try {
			UserVO user = manager.getUserForAuthentication(userName);
			if(user == null) {
				logger.error("No user found with user name : " +  userName);
				throw new UsernameNotFoundException("User not found with user name : " + userName);
			}
			
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();			
			/*if(user.getRole().getRoleID() == 1) {
				GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_PLAYER");		
				authorities.add(authority);
			} else if(user.getRole().getRoleID() == 2) {
				GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_COACH");
				authorities.add(authority);
			} else if(user.getRole().getRoleID() == 3) {
				GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ORGANISATION");
				authorities.add(authority);
			} else if(user.getRole().getRoleID() == 4) {
				GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ADMIN");
				authorities.add(authority);
			}*/
			
			
			return new User(userName, "", true, true, true, true, authorities);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Error occurred while getting user details" + e.getMessage());
			logger.error("Error occurred while getting user details" , e);

			throw new UsernameNotFoundException("User not found with user name : " + userName);
		}
		/*}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			throw new RuntimeException(e);
		}*/
		//return null;
	}

}
