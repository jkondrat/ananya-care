package org.motechproject.carereporting.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "care_user")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "user_id"))
})
public class UserEntity extends AbstractEntity implements UserDetails {

    @NotNull
    @Column(name = "username", unique = true)
    private String username;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "area_id")
    private AreaEntity area;

    @ManyToOne
    @JoinColumn(name = "default_language_id")
    private LanguageEntity defaultLanguage;

    @OneToMany(mappedBy = "owner")
    private Set<IndicatorEntity> indicators;

    @ManyToOne
    @JoinColumn(name = "default_dashboard_id")
    private DashboardEntity defaultDashboard;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "care_user_role", joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") })
    private Set<RoleEntity> roles;

    public UserEntity() {
        this.roles = new HashSet<>();
    }

    public UserEntity(String username) {
        this();
        this.username = username;
    }

    public UserEntity(String username, Set<RoleEntity> roles) {
        this.username = username;
        this.roles = roles;
    }

    public DashboardEntity getDefaultDashboard() {
        return defaultDashboard;
    }

    public void setDefaultDashboard(DashboardEntity defaultDashboard) {
        this.defaultDashboard = defaultDashboard;
    }

    public AreaEntity getArea() {
        return area;
    }

    public void setArea(AreaEntity area) {
        this.area = area;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (RoleEntity role: roles) {
            for (PermissionEntity permission: role.getPermissions()) {
                grantedAuthorities.add(new SimpleGrantedAuthority(permission.getName()));
            }
        }
        return grantedAuthorities;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getUsername() {
        return username;
    }

    public LanguageEntity getDefaultLanguage() {
        return defaultLanguage;
    }

    public void setDefaultLanguage(LanguageEntity defaultLanguage) {
        this.defaultLanguage = defaultLanguage;
    }

    @JsonIgnore
    public Set<IndicatorEntity> getIndicators() {
        return indicators;
    }

    public void setIndicators(Set<IndicatorEntity> indicators) {
        this.indicators = indicators;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserEntity that = (UserEntity) o;

        if (!id.equals(that.getId())) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
