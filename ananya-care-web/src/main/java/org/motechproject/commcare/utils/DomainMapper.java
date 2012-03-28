package org.motechproject.commcare.utils;

import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.BeanUtils;
import org.motechproject.care.domain.CareCase;
import org.motechproject.commcare.domain.Case;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by IntelliJ IDEA.
 * User: pchandra
 * Date: 3/24/12
 * Time: 10:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class DomainMapper<T> {

    private Class<T> clazz;

    public DomainMapper(Class<T> clazz) {
        this.clazz = clazz;
    }

    
    public T mapToDomainObject(Case ccCase){
        T instance = null;
        try {
            instance = clazz.newInstance();
            BeanUtils.copyProperties(instance, ccCase);
            BeanUtils.populate(instance,ccCase.getFieldValues());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return instance;

    }

    public Case mapFromDomainObject(T careCase) {
        Case ccCase = new Case();
        try {
             BeanUtils.copyProperties(ccCase, careCase);

             BeanMap beanMap = new BeanMap(careCase);
             beanMap.remove("caseId");
             beanMap.remove("dateModified");
             beanMap.remove("caseTypeId");
             beanMap.remove("caseName");

             //ccCase.setFieldValues(beanMap.);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
