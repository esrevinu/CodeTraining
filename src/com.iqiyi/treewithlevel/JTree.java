package com.iqiyi.treewithlevel;

/**
 * Created by lis2 on 2016/12/5.
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class JTree<M> {
    private List<JTreeNode<M>> initBeans = new ArrayList<JTreeNode<M>>();
    private String sign = "-";



    public String getSign() {
        return sign;
    }


    public void setSign(String sign) {
        this.sign = sign;
    }


    /**
     * @param isSort  是否排序,如果你列表是有序的，建立false，提供性能
     * @param rootId  根节点
     * @return
     */
    public List<JTreeNode<M>> start(Boolean isSort, Serializable rootId) {
        if(rootId == null){
            rootId = "";
        }
        List<JTreeNode<M>> arrayBeans = new ArrayList<JTreeNode<M>>();
        JTreeNode<M> treeRoot = getTree(initBeans, isSort, rootId);
        List<JTreeNode<M>> treeList = treeRoot.getChildren();
        return treeConvertList(treeList, 1, arrayBeans);
    }


    public JTreeNode<M> getTree(List<JTreeNode<M>> beans, boolean isSort,
                                Serializable rootId) {
        List<JTreeNode<M>> sortBeans = beans;
        if (isSort) {
            sortBeans = sort(beans);
        }
        JTreeNode<M> treeRoot = tree(sortBeans, rootId, null);
        return treeRoot;
    }

    /**
     * 采用冒泡对数组集合进行排序
     *
     * @param beans
     * @return
     */
    private List<JTreeNode<M>> sort(List<JTreeNode<M>> beans) {
        JTreeNode<M> temp;
        int size = beans.size();
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                if (beans.get(i).getSortId() > beans.get(j).getSortId()) {
                    temp = beans.get(i);
                    beans.set(i, beans.get(j));
                    beans.set(j, temp);
                }
            }
        }
        return beans;
    }

    /**
     * 采用递归对数组集合合并树形结构集合
     *
     * @param beans
     * @param parentId
     * @param root
     * @return
     */
    private JTreeNode<M> tree(List<JTreeNode<M>> beans, Serializable parentId,JTreeNode<M> root) {
        if (root == null) {
            root = new JTreeNode<M>();
            root.setId(parentId);
        }
        for (int i = 0; i < beans.size(); i++) {
            JTreeNode<M> bean = beans.get(i);
            Serializable pid = bean.getParentId();
            if(pid == null){
                pid = "";
            }
            if (pid.equals(root.getId())) {
                JTreeNode<M> nodeBean = tree(beans, bean.getId(), bean);
                root.getChildren().add(nodeBean);
            }
        }
        return root;
    }

    /**
     * 采用递归将树形集合转化成数组集合结构
     *
     * @param tree
     * @param num
     * @return
     */
    public List<JTreeNode<M>> treeConvertList(List<JTreeNode<M>> tree, int num,
                                              List<JTreeNode<M>> arrayBeans) {
        for (JTreeNode<M> bean : tree) {
            String sign ="";
            for (int i = 1; i < num; i++) {
                sign+=this.getSign();
            }
            bean.setNum(num);
            bean.setSign(sign);
            arrayBeans.add(bean);
            List<JTreeNode<M>> ChildrenList = bean.getChildren();
            if (!ChildrenList.isEmpty()) {
                int a = num + 1;
                treeConvertList(ChildrenList, a, arrayBeans);
            }
        }
        return arrayBeans;
    }



    public void add(Serializable id, Serializable pid, int sortIndex,M data) {
        initBeans.add(new JTreeNode(id, pid, sortIndex, data));
    }

    public static void main(String[] args) {

        JTree<String> jtree = new JTree<String>();
        jtree.setSign("++");
        jtree.add("1", null, 7, "系统管理");
        jtree.add("2", null, 6, "帮助中心");
        jtree.add("3","2", 5, "接口规范");
        jtree.add("4", "1", 4, "首页1");
        jtree.add("5", "4", 4, "首页2");
        jtree.add("6", "3", 4, "首页3");

        List<JTreeNode<String>> list = jtree.start(true, null);
        list.forEach(System.out::println);
    }

}
