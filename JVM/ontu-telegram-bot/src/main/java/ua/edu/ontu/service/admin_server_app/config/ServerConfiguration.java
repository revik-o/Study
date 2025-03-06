package ua.edu.ontu.service.admin_server_app.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ua.edu.ontu.service.admin_server_app.database.repo.IAdministratorRepository;
import ua.edu.ontu.service.admin_server_app.database.service.SessionService;
import ua.edu.ontu.service.admin_server_app.database.util.InitializationUtilVOne;
import ua.edu.ontu.service.admin_server_app.dto.AppProperties;
import ua.edu.ontu.service.admin_server_app.util.EncryptionUtil;

@Configuration
@EnableWebSecurity
public class ServerConfiguration implements WebMvcConfigurer {

	private final String corsUrls;
	private final IAdministratorRepository administratorRepository;

	@Autowired
	public ServerConfiguration(@Value("${server.cors}") String corsUrls,
			IAdministratorRepository administratorRepository, EncryptionUtil encryptionUtil) throws IOException {
		this.corsUrls = corsUrls;
		this.administratorRepository = administratorRepository;
		new InitializationUtilVOne(this.administratorRepository).setUpTheFirstLaunchOfTheApplication();
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		var urls = this.corsUrls.trim().split(",");
		String[] methods = { HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.PUT.name(),
				HttpMethod.DELETE.name(), HttpMethod.OPTIONS.name() };
		registry.addMapping("/api/**").allowedOrigins(urls).allowedMethods(methods).allowedHeaders("*");
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/admin/**").setViewName("forward:/static/admin/index.html");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:static/");
	}

	@Bean
	public SecurityFilterChain httpConfiguration(HttpSecurity http, SessionService sessionService) throws Exception {
		String[] paths = { "/admin", "/api/v1.0/admin/sign-in", };
		return http
				.authorizeHttpRequests(
						customizer -> customizer.requestMatchers(paths).permitAll().anyRequest().hasRole("ADMIN"))
				.csrf(AbstractHttpConfigurer::disable).formLogin(AbstractHttpConfigurer::disable)
				.logout(AbstractHttpConfigurer::disable)
				.addFilterBefore(new RequestFilter(sessionService), UsernamePasswordAuthenticationFilter.class).build();
	}

	@Bean
	public ObjectMapper createGlobalJacksonObjectMapper() {
		return new ObjectMapper();
	}

	public RestTemplate createRestTemplate() {
		return new RestTemplate();
	}

	@Bean
	public AuthenticationManager authenticationManagerBean() {
		return authentication -> authentication;
	}

	@Bean
	public AppProperties createAppProperties(@Value("${telegram-bot-api.url}") String tgApiUrl,
			@Value("${telegram-bot.directory}") String tgDirectory) {
		return new AppProperties(tgApiUrl, tgDirectory);
	}
}

@Slf4j
@RequiredArgsConstructor
class RequestFilter extends OncePerRequestFilter {

	private final SessionService sessionService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			boolean isValid = this.sessionService
					.authorizationHeaderIsValid(request.getHeader(HttpHeaders.AUTHORIZATION));
			Authentication authentication = (isValid)
					? new UsernamePasswordAuthenticationToken("ADMIN", null,
							new ArrayList<>(List.of((GrantedAuthority) () -> "ROLE_ADMIN")))
					: new UsernamePasswordAuthenticationToken(null, null);

			if (authentication.isAuthenticated()) {
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}

			filterChain.doFilter(request, response);
		} catch (Exception exception) {
			RequestFilter.log.error(exception.getMessage(), exception);
			throw exception;
		}
	}

}