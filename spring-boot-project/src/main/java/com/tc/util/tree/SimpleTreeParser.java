package com.tc.util.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Auther: tianchao
 * @Date: 2020/4/9 20:44
 * @Description:
 */
public class SimpleTreeParser implements ITreeParser {
    /**
     * 根据根id获取一棵树
     *
     * @param root
     * @param entityList
     * @return
     */
    @Override
    public <E extends ITree<E>> List<E> getTreeList(E root, List<E> entityList) {
        if (entityList == null || entityList.size() == 0){
            return Collections.emptyList();
        }
        Map<Long, List<E>> mapHelper = entityList.stream().collect(Collectors.groupingBy(E::getParentId));
        getSubListHelper(root,mapHelper);
        return root.getChildrenList();
    }

    /**
     * 直接获取一棵树
     *
     * @param entityList
     * @return
     */
    @Override
    public <E extends ITree<E>> List<E> getTreeList(List<E> entityList) {
        if (entityList == null || entityList.size() == 0){
            return Collections.emptyList();
        }
        Map<Long, List<E>> mapHelper = entityList.stream().collect(Collectors.groupingBy(E::getParentId));
        List<E> result = new ArrayList<>();
        for (E root :entityList){
            if (root.getParentId()==null||root.getParentId()==0){
                result.add(root);
                getSubListHelper(root, mapHelper);
            }
        }
        return result;
    }

    private <E extends ITree<E> > void getSubListHelper(E entity,Map<Long, List<E>> mapHelper){
        final List<E> children = mapHelper.get(entity.getId());
        entity.setChildrenList(children);
        if (children==null||children.size()==0){
            return;
        }
        for (E child : children) {
            getSubListHelper(child, mapHelper);
        }
    }
}
