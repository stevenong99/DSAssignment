/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.util.LinkedList;

/**
 *
 * @author Crimson
 */
public class Queue<Orders> {

    LinkedList<Orders> list;

    void clear() {
        list.clear();
    }

    public class Node {

        String type;
        int priority;
    }

    public Queue() {
        list = new LinkedList<>();
    }

    public void enqueue(Orders e) {
        list.addLast(e);
    }

    public Orders dequeue() {
        return list.removeFirst();
    }

    public Orders getElement(int i) {
        return list.get(i);
    }

    public Orders peek() {
        return list.get(list.size() - 1);
    }

    public int getSize() {
        return list.size();
    }

    public boolean contains(Orders e) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(e)) {
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty() {
        if (list.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public int getIndex(Object o) {
        return list.indexOf(o);
    }

    public String toString() {
        String s = "";
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                s = s + list.get(i) + ".";
            } else {
                s = s + list.get(i) + ",";
            }
        }
        return s;
    }
}
