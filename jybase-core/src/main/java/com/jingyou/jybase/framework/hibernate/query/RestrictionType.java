package com.jingyou.jybase.framework.hibernate.query;

/**
 * Created by Administrator on 2016/6/15 0015.
 */
public enum  RestrictionType {
    Like,// like '%%'
    Eq,//=
    Between,
    Ne,//!=
    Gt, // >
    Ge,// >=
    Lt, // <
    Le, //<=
    LLike, // like '%**'
    RLike, //like '**%'
    In,
    NotIn,
    IsNull,
    IsNotNull
}
