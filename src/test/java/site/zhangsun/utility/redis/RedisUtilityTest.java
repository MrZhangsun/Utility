package site.zhangsun.utility.redis;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import site.zhangsun.utility.UtilityApplicationTests;

public class RedisUtilityTest extends UtilityApplicationTests {

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void set() {
        redisUtil.set("name", "zhangsunjiankun");
    }

    @Test
    public void get() {
        String savedThreadId = redisUtil.get("LOCK:DISTRIBUTED_LOCKER");
        System.out.println(savedThreadId);
    }
}