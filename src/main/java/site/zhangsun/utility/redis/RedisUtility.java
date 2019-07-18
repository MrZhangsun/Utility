package site.zhangsun.utility.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Base64;

/**
 * Functions: Redis Utility
 *
 * @author Murphy Zhang Sun
 * @date 2019-06-15 14:19
 * @since v4.0.1
 */
@Component
public class RedisUtility {

    private final RedisTemplate<String, Object> redisTemplate;


    public RedisUtility(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public String getValue(String key) {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        Object o = valueOperations.get(key);
        byte[] decode = Base64.getDecoder().decode(o.toString());
        return new String(decode);
    }
}