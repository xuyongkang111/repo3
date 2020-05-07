package service.impl;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import service.UserService;

import java.util.ArrayList;
import java.util.List;

@Component(value = "userService")
public class UserServiceImpl implements UserService {

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String password = null;
        List<SimpleGrantedAuthority> list = new ArrayList<SimpleGrantedAuthority>();
        //模拟用户从数据库查找用户开始
        if ("tom".equals(username)){
            password="123";
            list.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        if ("timor".equals(username)){
            password="111";
            list.add(new SimpleGrantedAuthority("ROLE_USER"));
            list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        //模拟用户从数据库查找用户结束
        //当密码为加密是 "{noop}"+userInfo.getPassword() 需加"{noop}"
        //User user = new User(username,"{noop}"+password,list);
        //String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities
        User user = new User(username,"{noop}"+password,true,true,true,true,list);
        //第一个true表 用户是否可用,如果为false,账号,密码正确也登录不上,可根据需求选择构造函数
        return user;
    }
}
