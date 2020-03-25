package com.mttl.vlms.common;

import java.beans.PropertyDescriptor;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.stereotype.Component;

/**
 * BeanConverter
 *
 *  
 *
 */
@Component
public class BeanConverter {

	@Autowired
	ConversionService conversionService;

	public <T> T convert(Object source, Class<T> clazz) {

		T target = BeanUtils.instantiate(clazz);

		BeanWrapper sourceWrapper = PropertyAccessorFactory.forBeanPropertyAccess(source);
		PropertyDescriptor[] sourceDescs = sourceWrapper.getPropertyDescriptors();

		BeanWrapper targetWrapper = PropertyAccessorFactory.forBeanPropertyAccess(target);

		String propertyName = null;
		for (PropertyDescriptor sourcePropDesc : sourceDescs) {
			propertyName = sourcePropDesc.getName();

			TypeDescriptor typeDesc = targetWrapper.getPropertyTypeDescriptor(propertyName);
			TypeDescriptor sourceDesc = sourceWrapper.getPropertyTypeDescriptor(propertyName);

			if (null == typeDesc) {
				continue;
			}
			if (!targetWrapper.isWritableProperty(propertyName)) {
				continue;
			}

			Object value = sourceWrapper.getPropertyValue(propertyName);
			if (null == value || "".equals(value)) {
				targetWrapper.setPropertyValue(propertyName, null);
			} else {
				// targetWrapper.setPropertyValue(propertyName,
				// conversionService.convert(value, typeDesc.getType()));
				targetWrapper.setPropertyValue(propertyName, conversionService.convert(value, sourceDesc, typeDesc));
			}
		}
		return target;
	}
}
