package com.jingyou.jybase.framework.hibernate;

import com.jingyou.jybase.common.util.UUID;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/5/17 0017.
 */
public class UuidGenerator implements IdentifierGenerator {
    @Override
    public Serializable generate(SessionImplementor sessionImplementor, Object o) throws HibernateException {
        String uuid = UUID.generate();
        return uuid;
    }
}
