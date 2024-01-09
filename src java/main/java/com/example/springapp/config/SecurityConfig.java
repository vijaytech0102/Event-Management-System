
// package com.example.springapp.config;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.http.HttpMethod;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
// import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// import com.example.springapp.filter.CustomFilter;
// @Configuration
// @EnableWebSecurity
// public class SecurityConfig
// {   
//     public static final String MY_URL="*";
// 	@Autowired
// 	private CustomFilter customFilter;
	
//   @Bean
//   public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
// 	System.out.println("In SecurityConfig filterChain");
//       http
//         .cors()
//         .and()
//     	.authorizeHttpRequests()
//     	.antMatchers("/auth/**").permitAll()
//         .antMatchers("/auth/register").permitAll()
//     	.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//         .anyRequest()
//         .authenticated()
//         .and().formLogin().disable()
//          .logout()
//         .logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout"))  
//         .logoutSuccessUrl("/auth/login")
//         .invalidateHttpSession(true)
//         .deleteCookies("JSESSIONID")
//         .and().csrf().disable()
//         .addFilterBefore(
//             customFilter, BasicAuthenticationFilter.class)
//         .sessionManagement()
//         .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

//       	return http.build(); 
//   	}

// 	@Bean
// 	public PasswordEncoder encoder() {
// 		System.out.println("In password encoder");	    	
// 		return new BCryptPasswordEncoder();
// 	}
	  
// 	@Bean
// 	public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
// 		return http.getSharedObject(AuthenticationManagerBuilder.class)
// 				.build();
// 	}
// }

package com.example.springapp.config;
import com.example.springapp.filter.CustomFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.http.HttpMethod;
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String MY_URL = "*";

    @Autowired
    private CustomFilter customFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("In SecurityConfig configure");
        http
        .csrf().disable()
            .cors()
            .and()
            .authorizeRequests()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/auth/register").permitAll()
                .antMatchers("/***").permitAll()
                .antMatchers("/api/event").permitAll()
                .antMatchers("/api/facility").permitAll()
                .antMatchers("/api/registration/organizer").permitAll()
                // .antMatchers("/api/event").hasAnyRole("ADMIN")
                // .antMatchers(HttpMethod.GET,"/api/event").hasAnyRole("ADMIN","USER")
                // .antMatchers(HttpMethod.POST,"/api/event").hasAnyRole("ADMIN")
                // .antMatchers(HttpMethod.GET,"/api/facility").hasAnyRole("ADMIN")
                // .antMatchers(HttpMethod.POST,"/api/facility").hasAnyRole("ADMIN")
                // .antMatchers(HttpMethod.GET,"/api/registration/organizer").hasAnyRole("ADMIN")
                // .antMatchers(HttpMethod.POST,"/api/registration/organizer").hasAnyRole("USER")
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().disable()
            .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout"))
                .logoutSuccessUrl("/auth/login")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .and().csrf().disable()
            .addFilterBefore(customFilter, BasicAuthenticationFilter.class)
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    public PasswordEncoder encoder() {
        System.out.println("In password encoder");
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}

