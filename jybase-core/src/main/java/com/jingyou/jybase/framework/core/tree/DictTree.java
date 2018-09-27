package com.jingyou.jybase.framework.core.tree;

import java.util.List;

/**
 * Created by Administrator on 2016/7/8 0008.
 */
public class DictTree {
    private String id;
    private String name;
    private String pid;
    private List<DictTree> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public List<DictTree> getChildren() {
        return children;
    }

    public void setChildren(List<DictTree> children) {
        this.children = children;
    }
}
