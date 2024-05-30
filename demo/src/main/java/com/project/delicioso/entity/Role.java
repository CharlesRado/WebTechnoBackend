package com.project.delicioso.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.project.delicioso.entity.Permission.*;

/*@Data
@NoArgsConstructor
@Entity
@Table(name="roles", schema="public")
public class Role {
    @Id
    @GeneratedValue
    private Long id;
    private String role_name;
}
*/

@RequiredArgsConstructor
public enum Role {
    USER(
            Set.of(
                    USER_READ,
                    USER_UPDATE,
                    USER_CREATE,
                    USER_DELETE
            )
    ),
    DELEVERYMAN(
            Set.of(
                    DELEVERYMAN_READ,
                    DELEVERYMAN_UPDATE,
                    DELEVERYMAN_CREATE,
                    DELEVERYMAN_DELETE
            )
    )
    ;

    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE" + this.name()));
        return authorities;
    }
}