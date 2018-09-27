package com.jingyou.jybase.framework.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

/**
 * Created by Administrator on 2016/6/14 0014.
 */
@Target(value = {METHOD,TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AuditLog {
    String log();
    LogType type();
}
