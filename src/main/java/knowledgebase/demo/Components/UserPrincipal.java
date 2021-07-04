package knowledgebase.demo.Components;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import knowledgebase.demo.Domain.Profile;
import java.util.Collection;
import java.util.Collections;

@Component
public class UserPrincipal implements UserDetails {

    @Autowired
    private Profile account;

    public UserPrincipal(Profile account){
        this.account=account;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(account.getAccountType()));
    }

    @Override
    public String getPassword() {
        return account.getPassword();
    }

    @Override
    public String getUsername() {
        return account.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        if(account==null) return true;

        return account.isAccountNonBlocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }

    @Override
    public boolean isEnabled() {
        if(account==null) return false;
        return account.isAccountEnabled();
    }
}
