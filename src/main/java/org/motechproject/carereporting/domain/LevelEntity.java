package org.motechproject.carereporting.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "level")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "level_id"))
})
public class LevelEntity extends AbstractEntity {
}
