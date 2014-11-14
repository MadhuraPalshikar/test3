/**
 * 
 */
package com.ftofstudios.wentros.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

/**
 * @author home-pc
 *
 */
public class RoleBasedTargetUrlResolver extends
		SimpleUrlAuthenticationSuccessHandler {
	
	private static final GrantedAuthority AUTHORITY_PLAYER = new SimpleGrantedAuthority("ROLE_PLAYER");
    private static final GrantedAuthority AUTHORITY_COACH = new SimpleGrantedAuthority("ROLE_COACH");
    private static final GrantedAuthority AUTHORITY_ORGANISATION = new SimpleGrantedAuthority("ROLE_ORGANISATION");
    private static final GrantedAuthority AUTHORITY_ADMIN = new SimpleGrantedAuthority("ROLE_ADMIN");

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    private Logger logger = Logger.getLogger(this.getClass());

	
	
	@Override
	protected void handle(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		String targetUrl = null;

        logger.info("Connexion : \"" + authentication.getName() + "\" " + authentication.getAuthorities().toString());
        
        if (authentication.getAuthorities().contains(AUTHORITY_PLAYER)) {
                targetUrl = "/diaryPlayerHome";

        } else if (authentication.getAuthorities().contains(AUTHORITY_COACH)) {
                targetUrl = "/diaryCoachHome";

        } else if (authentication.getAuthorities().contains(AUTHORITY_ORGANISATION)) {
            targetUrl = "/diaryPlayerHome";

        } else if (authentication.getAuthorities().contains(AUTHORITY_ADMIN)) {
            targetUrl = "/diaryAdminHome";

        }	else {
                throw new IllegalArgumentException("Unknown authority" + authentication.getAuthorities());
        }

        if (response.isCommitted()) {
                logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
                return;
        }

        redirectStrategy.sendRedirect(request, response, targetUrl);

	}

}
