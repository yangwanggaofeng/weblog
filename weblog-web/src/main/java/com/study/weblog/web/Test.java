package com.study.weblog.web;

/**
 * @ClassName Test
 * @Description TODO
 * @Author zhang
 * @Date 2024/4/30
 * @Version 1.0
 **/
public class Test {
    public static void main(String[] args) {
    Something something = new Something();
    something.doSomething();
    }
}

class Something{
     int i;
     public void doSomething(){
         System.out.println("i===" +i);
     }
}
