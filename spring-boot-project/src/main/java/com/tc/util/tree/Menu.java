package com.tc.util.tree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Auther: tianchao
 * @Date: 2020/4/9 21:22
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu implements ITree<Menu>{
    private Long id;

    private Long parentId;

    private String name;

    private List<Menu> childrenList;

    public Menu(Long id, Long parentId, String name) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public Long getParentId() {
        return parentId;
    }

    @Override
    public void setChildrenList(List<Menu> childrenList) {
        this.childrenList = childrenList;
    }

    @Override
    public List<Menu> getChildrenList() {
        return this.childrenList;
    }

}
