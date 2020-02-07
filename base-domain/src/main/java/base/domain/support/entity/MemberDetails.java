package base.domain.support.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Convert;
import javax.persistence.MappedSuperclass;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@MappedSuperclass
public class MemberDetails extends CreatedAndModifiedEntity {

    @Convert(converter = RoleAttributeConverter.class)
    protected List<String> roles;
    protected Boolean accountNonExpired;
    protected Boolean accountNonLocked;
    protected Boolean credentialsNonExpired;
    protected Boolean enabled;
}
