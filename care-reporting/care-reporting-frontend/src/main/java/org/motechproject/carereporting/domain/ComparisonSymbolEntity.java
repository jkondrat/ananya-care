package org.motechproject.carereporting.domain;

import com.fasterxml.jackson.annotation.JsonView;
import org.motechproject.carereporting.domain.views.BaseView;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "comparison_symbol")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "comparison_symbol_id"))
})
public class ComparisonSymbolEntity extends AbstractEntity {

    @NotNull
    @Column (name = "name")
    @JsonView(BaseView.class)
    private String name;

    public ComparisonSymbolEntity() {

    }

    public ComparisonSymbolEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
