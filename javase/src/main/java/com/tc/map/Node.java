package com.tc.map;

/**
 * @Auther: tianchao
 * @Date: 2020/1/31 21:08
 * @Description:
 */
public class Node {
    private Node next;
    private Object data;

    public Node(Object data) {
        this.data = data;
    }

    public static void main(String[] args) {
        Node node = new Node(0);
        node.next = new Node(1);
    }
}
