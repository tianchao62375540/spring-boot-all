package com.tc.util.tree;

import java.util.Arrays;
import java.util.List;

/**
 * @Auther: tianchao
 * @Date: 2020/4/9 21:26
 * @Description:
 */
public class TestTree {
    public static void main(String[] args) {
        Menu root1 = new Menu(1L, 0L, "跟1");
        Menu root2 = new Menu(2L, 0L, "跟2");
        Menu root11 = new Menu(3L, 1L, "11");
        Menu root12 = new Menu(4L, 1L, "12");
        Menu root13 = new Menu(5L, 1L, "13");
        Menu root121 = new Menu(6L, 4L, "121");
        Menu root122 = new Menu(7L, 4L, "122");
        Menu root123 = new Menu(8L, 4L, "123");
        Menu root1234 = new Menu(9L, 8L, "1234");
        List<Menu> menus = Arrays.asList(root1, root2, root11, root12, root13, root121, root122, root123, root1234);
        ITreeParser parser = new SimpleTreeParser();
        List<Menu> treeList = parser.getTreeList(menus);
        System.out.println(treeList);
        List<Menu> treeList1 = parser.getTreeList(root12, menus);
        System.out.println("==================");
    }
}
