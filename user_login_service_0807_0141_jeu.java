// 代码生成时间: 2025-08-07 01:41:25
// user_login_service.java

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserLoginService {

    // Autowired components
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**<ol>
     * Logs in a user and returns the authentication object if successful.
     * @param username The username of the user trying to login.
     * @param password The password of the user trying to login.
     * @return Authentication object if login is successful, otherwise null.
     * @throws Exception If login fails due to an exception.
     */
    public Authentication login(String username, String password) throws Exception {
        // Fetch user details for the given username
        org.springframework.security.core.userdetails.UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        // Encode the password using the password encoder
        String encodedPassword = passwordEncoder.encode(password);

        // Create an authentication token with the username and encoded password
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
            userDetails.getUsername(), encodedPassword
        );

        // Attempt authentication
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        // Check if authentication is successful and return the authentication object
        if (authentication.isAuthenticated()) {
            return authentication;
        } else {
            // If authentication fails, throw an exception
            throw new Exception("Authentication failed for user: " + username);
        }
    }
}
