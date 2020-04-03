package wind.yang.jojolduBook.config.auth;


import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import wind.yang.jojolduBook.domain.user.Role;

@RequiredArgsConstructor
@EnableWebSecurity // SpringSecurity 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http    // h2-console을 사용하기 위해 disable
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                    // URL별 관리를 시작하는 시작점
                    .authorizeRequests()
                    // 모두에게 허용
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                    // 특정 Role 사용자만 허용
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                    // 인증된 사용자만 허용
                    .anyRequest().authenticated()
                .and()
                    .logout()
                        .logoutSuccessUrl("/")
                .and()
                    // oauth2 로그인 기능에 대한 설정 시작점
                    .oauth2Login()
                        // oauth2 로그인 성공이후 사용자 정보를 가져올 때의 설정
                        .userInfoEndpoint()
                            // 로그인 성공후 실행될 UserService 인터페이스의 구현체를 등록
                            .userService(customOAuth2UserService);
    }
}
