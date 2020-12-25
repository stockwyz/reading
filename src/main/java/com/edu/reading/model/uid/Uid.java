package com.edu.reading.model.uid;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标示Model的id属性
 * @author zht
 *
 */
@Target(ElementType.FIELD) 
@Retention(RetentionPolicy.RUNTIME)
public @interface Uid {

}
