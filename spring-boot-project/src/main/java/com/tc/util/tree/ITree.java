package com.tc.util.tree;

import java.util.List;

/**
 * @Auther: tianchao
 * @Date: 2020/4/9 20:15
 * @Description:
 */
public interface ITree<E> {

    Long getId();

    Long getParentId();

    void setChildrenList(List<E> childrenList);

    List<E> getChildrenList();
}
