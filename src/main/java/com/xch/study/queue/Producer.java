package com.xch.study.queue;

public class Producer implements Runnable {
    @Override
    public void run() {
        try {
            while (true) {
                /**生产苹果*/
                String result = System.currentTimeMillis() + "";
                System.out.println("生产者准备生产苹果：" + result);
                Basket.produce(result);
//                System.out.println("生产完后有苹果：" + Basket.getAppleNumber() + "个");
                /**休眠300ms*/
                Thread.sleep(300);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
