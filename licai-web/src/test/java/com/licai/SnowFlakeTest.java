package com.licai;

import cn.hutool.core.lang.Snowflake;
import org.junit.Test;

/**
 * 单元测试 - 分布式id生成器
 */
public class SnowFlakeTest {

    @Test
    public void m01() {
        Snowflake snowflake = new Snowflake(2,3);
        for (int i = 0; i < 5; i++) {
            String id = snowflake.nextIdStr();
            System.out.println(id);
        }
    }
}
