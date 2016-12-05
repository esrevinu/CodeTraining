package com.iqiyi.treewithlevel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lis2 on 2016/12/5.
 */
public class JTreeNode<M> {
    private Serializable id;
    private Serializable parentId;
    private int sortId;
    private int num;
    private String sign;
    private M data;
    private List<JTreeNode<M>> children = new ArrayList<JTreeNode<M>>();

    public JTreeNode() {
        super();
    }



    public JTreeNode(Serializable id, Serializable parentId, int sortId, M obj) {
        this.id = id;
        this.sortId = sortId;
        this.parentId = parentId;
        this.data = obj;
    }



    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public M getData() {
        return data;
    }

    public void setData(M data) {
        this.data = data;
    }

    public Serializable getId() {
        return id;
    }

    public void setId(Serializable id) {
        this.id = id;
    }

    public Serializable getParentId() {
        return parentId;
    }

    public void setParentId(Serializable parentId) {
        this.parentId = parentId;
    }

    public int getSortId() {
        return sortId;
    }

    public void setSortId(int sortId) {
        this.sortId = sortId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public List<JTreeNode<M>> getChildren() {
        return children;
    }

    public void setChildren(List<JTreeNode<M>> children) {
        this.children = children;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

}
