package com.ieti.taskplanner.taskback.restcontroller;

/**
 *
 * @author Sergio
 */
import com.ieti.taskplanner.taskback.model.User;
import com.ieti.taskplanner.taskback.services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import javax.servlet.ServletException;
import java.util.Date;

@Service
@RestController
@CrossOrigin(origins = "*")
@RequestMapping( "/user" )
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public boolean signup(@RequestBody User signup) {
        return userService.createUser(signup);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Token login(@RequestBody User login)
            throws ServletException {

        String jwtToken = "";

        if (login.getId() == null || login.getPassword() == null) {
            throw new ServletException("Please fill in username and password");
        }

        User user = userService.findUserByUsernameAndPassword(login.getId(), login.getPassword());

        if (user == null) {
            throw new ServletException("Invalid login. Please check your name and password.");
        }
        jwtToken = Jwts.builder().setSubject(login.getId()).claim("roles", "user").setIssuedAt(new Date()).signWith(
                SignatureAlgorithm.HS256, "secretkey420").compact();

        return new Token(jwtToken);
    }

    public class Token {

        String accessToken;


        public Token(String accessToken) {
            this.accessToken = accessToken;
        }


        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String access_token) {
            this.accessToken = access_token;
        }
    }
} 
