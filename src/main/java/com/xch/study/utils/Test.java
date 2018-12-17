package com.xch.study.utils;

import java.math.BigDecimal;

/**
 * @author fgs
 * @data 2018/12/12 16:45
 */
public class Test {
    public static void main(String[] args) {
        BigDecimal bignum1 = new BigDecimal("10.456");
        BigDecimal bignum2 = new BigDecimal("3.3333");
//        BigDecimal bignum3 = null;
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+bignum1.compareTo(bignum2));


//        //加法
//        bignum3 = bignum1.add(bignum2).setScale(2,BigDecimal.ROUND_HALF_UP);
//        System.out.println("和 是：" + bignum3);
//
//        //减法
//        bignum3 = bignum1.subtract(bignum2);
//        System.out.println("差  是：" + bignum3);
//
//        //乘法
//        bignum3 = bignum1.multiply(bignum2);
//        System.out.println("积  是：" + bignum3);

//        //除法
//        bignum3 = bignum1.divide(bignum2,2,BigDecimal.ROUND_HALF_UP);
//        System.out.println("商  是：" + bignum3);


//        List<Map<String, String>> listMap = new ArrayList<>();
//        Map<String, String> map = new HashMap<>();
//        map.put("1", "a");
//        listMap.add(map);
//        map = new HashMap<>();
//        map.put("2", "b");
//        listMap.add(map);
//        map = new HashMap<>();
//        map.put("3", "c");
//        listMap.add(map);
//        for (int i = 0; i < listMap.size(); i++) {
//            if ("a".equals(listMap.get(i).get("1"))) {
//                listMap.remove(listMap.get(i));
//                i--;
//            }
//        }
//        for (Map<String, String> o : listMap) {
//            for (Map.Entry<String, String> ma : o.entrySet()) {
//                System.out.println(">>>>>>>>>>>>>>>>>>>>>" + ma.getValue());
//            }
//        }

        // 第一种：
//        for (Integer in : map.keySet()) {
//            //map.keySet()返回的是所有key的值
//            String str = map.get(in);//得到每个key多对用value的值
//            System.out.println(in + "     " + str);
//        }
        // 第二种：
//        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
//        while (it.hasNext()) {
//            Map.Entry<Integer, String> entry = it.next();
//            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
//        }
        // 第三种：推荐，尤其是容量大时
//        for (Map.Entry<Integer, String> entry : map.entrySet()) {
//            //Map.entry<Integer,String> 映射项（键-值对）  有几个方法：用上面的名字entry
//            //entry.getKey() ;entry.getValue(); entry.setValue();
//            //map.entrySet()  返回此映射中包含的映射关系的 Set视图。
//            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
//            entry.setValue(entry.getValue()+"i");
//
//        }
//
//        for (Map.Entry<Integer, String> entry : map.entrySet()) {
//            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
//        }


        // 第四种：
//        System.out.println("第四种：通过Map.values()遍历所有的value，但不能遍历key");
//        for (String v : map.values()) {
//            System.out.println("value= " + v);
//        }

    }
}
