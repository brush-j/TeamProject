package namkyu.and.mz.homeshopping.config;

import lombok.Data;
import namkyu.and.mz.homeshopping.entity.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Data
public class PrincipalDetails implements UserDetails, OAuth2User {

    private Member member;

    private Map<String, Object> attributes;

    public PrincipalDetails(Member user) {
        this.member = user;
    }

    public PrincipalDetails(Member user, Map<String, Object> attributes){
        this.member = user;
        this.attributes = attributes;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(()->{return String.valueOf(member.getRole());});
        return collect;
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Member getUser() {
        return member;
    }

    @Override
    public String getName() {
//        String sub = attributes.get("sub").toString();
        return member.getEmail();
    }
}
