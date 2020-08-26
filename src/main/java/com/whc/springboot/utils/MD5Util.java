package com.whc.springboot.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

/**
 * MD5 Util，密码加密
 *
 * @author: HymanHu
 * @date: 2019年10月29日
 */
//密码加盐类
public class MD5Util {
    private static final String SALT = "&%5123***&&%%$$#@";

    public static String getMD5(String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        String base = str + "/" + SALT;
        return DigestUtils.md5DigestAsHex(base.getBytes());
    }

    public static void main(String[] args) {
        System.out.println(MD5Util.getMD5("111111"));
//        long start = System.currentTimeMillis();
////        for (int i = 0; i < 10; i++) {
////            for (int j = 0; j < 100; j++) {
////                System.out.println("aaaaaaa");
////            }
////        }
////        long end = System.currentTimeMillis();
////        System.out.println("总耗时：" + (end - start));
//测试（大量数据/小量数据）：外：10000，100；内：100，10
//外大内小545 16（i初始化1次，j初始化100次）
//外小内大561 12（i初始化1次，j初始化10000次）
//当数据量很大的时候，外层循环次数多，内层循环次数少，运行效率更高（变量初始化影响运行速率更大）
//当数据量很小的时候，外层循环次数少，内层循环次数多，运行效率更高
    }
}
