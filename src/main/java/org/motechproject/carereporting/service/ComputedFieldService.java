package org.motechproject.carereporting.service;

import org.motechproject.carereporting.domain.ComputedFieldEntity;
import org.motechproject.carereporting.domain.forms.ComputedFieldFormObject;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Set;

public interface ComputedFieldService {

    String HAS_ROLE_CAN_CREATE_COMPUTED_FIELDS = "hasRole('CAN_CREATE_COMPUTED_FIELDS')";

    Set<ComputedFieldEntity> findAllComputedFields();

    Set<ComputedFieldEntity> findComputedFieldsByFormId(Integer formId);

    ComputedFieldEntity findComputedFieldById(Integer computedFieldId);

    @PreAuthorize(HAS_ROLE_CAN_CREATE_COMPUTED_FIELDS)
    void createNewComputedField(ComputedFieldEntity computedFieldEntity);

    @PreAuthorize(HAS_ROLE_CAN_CREATE_COMPUTED_FIELDS)
    void createNewComputedFieldFromFormObject(ComputedFieldFormObject computedFieldFormObject);
}
