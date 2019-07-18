package site.zhangsun.utility.redis;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import site.zhangsun.utility.UtilityApplicationTests;

import static org.junit.Assert.*;

public class RedisUtilityTest extends UtilityApplicationTests {

    @Autowired
    private RedisUtility redisUtility;

    @Test
    public void getValue() {
        String name = redisUtility.getValue("YXT:AUTH:JWT:PUB");
        System.out.println(name);
    }
}