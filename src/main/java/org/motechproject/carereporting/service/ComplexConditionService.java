package org.motechproject.carereporting.service;

import org.motechproject.carereporting.domain.ComparisonSymbolEntity;
import org.motechproject.carereporting.domain.ComplexConditionEntity;
import org.motechproject.carereporting.domain.OperatorTypeEntity;
import org.motechproject.carereporting.domain.forms.ComplexConditionFormObject;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Set;

public interface ComplexConditionService {

    String HAS_ROLE_CAN_CREATE_COMPLEX_CONDITIONS = "hasRole('CAN_CREATE_COMPLEX_CONDITIONS')";

    Set<ComplexConditionEntity> findAllComplexConditions();

    ComplexConditionEntity findComplexConditionById(Integer complexConditionId);

    Set<ComplexConditionEntity> findComplexConditionsByIndicatorId(Integer indicatorId);

    @PreAuthorize(HAS_ROLE_CAN_CREATE_COMPLEX_CONDITIONS)
    void createNewComplexCondition(ComplexConditionEntity complexCondition);

    @PreAuthorize(HAS_ROLE_CAN_CREATE_COMPLEX_CONDITIONS)
    void createNewComplexCondition(ComplexConditionFormObject complexConditionFormObject);

    void updateComplexCondition(ComplexConditionEntity complexConditionEntity);

    void updateComplexCondition(ComplexConditionFormObject complexConditionFormObject);

    void deleteComplexCondition(ComplexConditionEntity complexConditionEntity);

    Set<OperatorTypeEntity> findAllOperatorTypes();

    OperatorTypeEntity findOperatorTypeById(Integer operatorTypeId);

    void createNewOperatorType(OperatorTypeEntity operatorType);

    void updateOperatorType(OperatorTypeEntity operatorTypeEntity);

    void deleteOperatorType(OperatorTypeEntity operatorTypeEntity);

    Set<ComparisonSymbolEntity> findAllComparisonSymbols();

    ComparisonSymbolEntity findComparisonSymbolById(Integer comparisonSymbolId);

    void createNewComparisonSymbol(ComparisonSymbolEntity comparisonSymbol);

    void updateComparisonSymbol(ComparisonSymbolEntity comparisonSymbolEntity);

    void deleteComparisonSymbol(ComparisonSymbolEntity comparisonSymbolEntity);

}
