package com.wky.springboot.myexcel.utils;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

/**
 * @Description:
 * @Author: 吴开云
 * @Date: 2019/12/4 0004
 * @Version： 1.0
 */
public class BloomUtile {
    //大小
    static final Integer DEFAULT_SIZE = 2 << 24;
    //构建BIT位数
    private static BitSet bitSet = new BitSet(DEFAULT_SIZE);
    //质因子列表
    private static Integer []seeds = new Integer[]{5,7,11,17,23,31};
    //构建Hash算法
    private static SimpleHash []simpleHashArray = new SimpleHash[seeds.length];
    static {
        for (int i = 0; i < seeds.length; i++) {
            simpleHashArray[i] = new SimpleHash(seeds[i]);
        }
    }

    //检测是否存在
    public static Boolean contain(Object o){
        Boolean flag = false;
        for (int i = 0; i < simpleHashArray.length; i++) {
            flag = flag & bitSet.get(simpleHashArray[i].getHashCode(o));
        }
        return flag;
    }

    //新增加值
    public static Boolean add(Object o){
        if(contain(o)){
            return false;
        }
        for (int i = 0; i < simpleHashArray.length; i++) {
            bitSet.set(simpleHashArray[i].getHashCode(o));
        }
        return true;
    }

}

class SimpleHash{
    private int seed = 31;

    public SimpleHash() {
    }

    public SimpleHash(int seed) {
        this.seed = seed;
    }

    public Integer getHashCode(Object o){
        int h;
        h = (o == null) ? 0 : (h = o.hashCode()) ^ (h >>> 16);
        return seed * h;
    }
}