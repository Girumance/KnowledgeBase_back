package knowledgebase.demo.Security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import knowledgebase.demo.Components.LogInModel;
import knowledgebase.demo.Services.ProfileService;
import knowledgebase.demo.Domain.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;


//jwtAUthenicationFiler extends UsernamePasswordAthenticaioinFilter and overrides two methods
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    AuthenticationManager authenticationManager;


    ProfileService accountService;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, ProfileService accountService) {
        this.authenticationManager = authenticationManager;
        this.accountService = accountService;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {


        String token = Jwts.builder()
                .setSubject(authResult.getName())
                .setExpiration(new Date(System.currentTimeMillis() + JwtProperties.EXPIRE))
                .signWith(SignatureAlgorithm.HS512, JwtProperties.SECRET.getBytes()).compact();


        PrintWriter writer = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Gson gson = new Gson();


        Profile account = accountService.getAccoutnByEmail(authResult.getName());

        account.setPassword(JwtProperties.TOKE_PREFIX + token);


        String data = gson.toJson(account);

        writer.println(data);
        writer.flush();

    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        //Accepts user name and password
        LogInModel credentials = null;
        try {
            credentials = new ObjectMapper().readValue(request.getInputStream(), LogInModel.class);
        } catch (IOException e) {
            e.printStackTrace();
        }


        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword(), new ArrayList<>());
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        //returned to successfulAUthenticatioin method
        return authentication;
    }

}