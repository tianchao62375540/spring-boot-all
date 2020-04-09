package com.tc.util.tree;

import java.util.List;

/**
 * @Auther: tianchao
 * @Date: 2020/4/9 20:23
 * @Description: 解析树
 */
public interface ITreeParser {
    /**
     * 根据根id获取一棵树
     * @param root
     * @param entityList
     * @param <E>
     * @return
     */
    <E extends ITree<E>> List<E> getTreeList(E root,List<E> entityList);

    /**
     * 直接获取一棵树
     * @param entityList
     * @param <E>
     * @return
     */
    <E extends ITree<E>> List<E> getTreeList(List<E> entityList);
}
