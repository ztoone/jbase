package com.jingyou.jybase.framework.core.base;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Created by Administrator on 2016/6/17 0017.
 */
@Transactional
@Repository
public class BaseDao extends BaseHibernateDaoImpl<Object,String>{
}
