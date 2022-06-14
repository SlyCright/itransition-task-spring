//package com.boots.config;
//
//import com.boots.entity.GUser;
//import com.boots.repository.GUserRepo;
//import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Configuration
//@EnableWebSecurity
//@EnableOAuth2Sso
//public class GWebSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .mvcMatchers("/").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .csrf().disable();
//    }
//
//    @Bean
//    public PrincipalExtractor principalExtractor(GUserRepo gUserRepo) {
//        return map -> {
//            System.out.println(map.get("sub"));
//            String id = (String) map.get("sub");
//            GUser user = findByGID(id, gUserRepo);
//            if (user == null) {
//                user = gUserRepo.findById(id).orElseGet(() -> {
//                    GUser newUser = new GUser();
//                    newUser.setGoogleID(id);
//                    System.out.println(map.get("name"));
//                    newUser.setName((String) map.get("name"));
//                    System.out.println(map.get("email"));
//                    newUser.setEmail((String) map.get("email"));
//                    System.out.println(map.get("locale"));
//                    newUser.setLocale((String) map.get("locale"));
//                    System.out.println(map.get("gender"));
//                    newUser.setGender((String) map.get("gender"));
//                    System.out.println(map.get("picture"));
//                    newUser.setUserpicture((String) map.get("picture"));
//                    return newUser;
//                });
//            }
//            user.setLastVisit(LocalDateTime.now());
//            return gUserRepo.save(user);
//        };
//    }
//
//    private GUser findByGID(String gID, GUserRepo gUserRepo) {
//        List<GUser> users = gUserRepo.findAll();
//        for(GUser user : users) {
//            if(user.getGoogleID().equals(gID)) {
//                return user;
//            }
//        }
//        return null;
//    }
//}
