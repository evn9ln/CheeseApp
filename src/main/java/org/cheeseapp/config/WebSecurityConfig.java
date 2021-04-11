package org.cheeseapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import javax.sql.DataSource;


//класс гарантирует, что только аутентифицированные пользователи могут взаимодействовать с сайтом (кроме главное страницы)
//конфигурация WebSecurity
@Configuration
@EnableWebSecurity //включение поддержки веб-безопасности (Spring Security) и обеспечивание интеграции Spring MVC
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired //внедрение бина
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/", "/registration").permitAll() //для этих путей разрешен полный доступ
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login") //mapping логина
                    .permitAll()
                .and()
                    .logout()
                    .permitAll();
    }

    //авторизация с использованием бд
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(NoOpPasswordEncoder.getInstance()) //(будущая) шифровка паролей
                .usersByUsernameQuery("select login, password, active from usr where login=?") //поиск пользователя
                .authoritiesByUsernameQuery("select u.login, ur.roles from usr u inner join user_role ur on u.id = ur.user_id where u.login=?"); //распределение по ролям (beta)
    }
}