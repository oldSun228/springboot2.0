package com.xch.study.queue;

public class Consumer implements Runnable {
    @Override
    public void run() {
        try {
            while (true) {
                // 消费苹果
                String result = System.currentTimeMillis() + "";
                System.out.println("消费者准备消费苹果：" + result);
                Basket.consume(result);
                System.out.println("消费者消费苹果完毕：" + System.currentTimeMillis());
//                System.out.println("消费完后有苹果：" + Basket.getAppleNumber() + "个");
                // 休眠1000ms
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
//            e.printStackTrace();
        }
    }
}
