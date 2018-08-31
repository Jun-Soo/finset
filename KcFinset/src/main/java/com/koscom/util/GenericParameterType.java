package com.koscom.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


/**
 * 제네닉 파라메터의 Type을 알기위해 사용
 */
public class GenericParameterType<X, Y> implements ParameterizedType {
	 
	private final Class<X> container;
     private final Class<Y> wrapped;

     public GenericParameterType(Class<X> container, Class<Y> wrapped) {
         this.container = container;
         this.wrapped = wrapped;
     }
 
    @Override
	public Type[] getActualTypeArguments() {
		  return new Type[]{wrapped};
	}

	@Override
	public Type getRawType() {
		 return container;
	}

	@Override
	public Type getOwnerType() {
		return null;
	}

}
