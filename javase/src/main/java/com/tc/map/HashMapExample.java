package com.tc.map;

/**
 * 源码学院-monkey
 * 只为培养BAT程序员而生
 * http://bat.ke.qq.com
 * 往期视频加群:516212256 暗号:6
 * * 线程不安全
 */

import java.util.HashMap;
import java.util.Map;

public class HashMapExample {
  public static final Map<String, String> map = new HashMap<String, String>();

  public static void main(String[] args) throws InterruptedException {
    //线程一
     new Thread() {
      public void run() {
        for (int i = 0; i < 1000; i++) {
          map.put(String.valueOf(i), String.valueOf(i));
        }
      }
    }.start();
    //线程二
     new Thread(){
          public void run() {
                  for(int z=2000;z<3000;z++){
                         map.put(String.valueOf(z), String.valueOf(z));
                      }
              }
     }.start();
      new Thread(){
          public void run() {
              for(int j=1000;j<2000;j++){
                  map.put(String.valueOf(j), String.valueOf(j));
              }
          }
      }.start();

    try {
      Thread.currentThread().sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    //输出
    for(int i=0;i<3000;i++){
      System.out.println("第："+i+"元素，值："+map.get(String.valueOf(i)));

            }
  }


}
