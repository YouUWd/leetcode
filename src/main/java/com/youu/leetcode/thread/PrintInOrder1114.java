package com.youu.leetcode.thread;

public class PrintInOrder1114 {
    Object m = new Object();
    int flag = 0;
    int count = 0;

    void print(int id, String msg) {

        while (count < 10) synchronized (m) {
            try {
                if (flag == 0) {
                    if (id > 1)
                        m.wait();
                } else {
                    m.wait();
                }


                System.out.println(msg);
                count++;
                flag = id+1;
                m.notifyAll();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public static void main(String[] args) {
        PrintInOrder1114 p = new PrintInOrder1114();
        new Thread(() -> p.print(1, "A"), "A").start();
        new Thread(() -> p.print(2, "B"), "B").start();
    }
}
