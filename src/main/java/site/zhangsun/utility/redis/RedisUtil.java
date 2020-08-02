package site.zhangsun.utility.redis;

import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.stereotype.Component;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

/**
 * @author ：Murphy ZhangSun
 * @version ：v.1.0.0
 * @description ：Redis工具类
 * @program ：vevor_demo
 * @date ：Created in 2020-06-30 15:00
 */
@Component
public class RedisUtil {

	private final StringRedisTemplate redisTemplate;

	public RedisUtil(StringRedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	/* -------------------key相关操作--------------------- */

	/**
	 * 删除key
	 * 
	 * @param key 键
	 */
	public void delete(String key) {
		redisTemplate.delete(key);
	}

	/**
	 * 批量删除key
	 * 
	 * @param keys 键列表
	 */
	public void delete(Collection<String> keys) {
		redisTemplate.delete(keys);
	}

	/**
	 * 序列化key
	 * 
	 * @param key 键 将键对应的值序列化为字节数组
	 * @return 字节数组
	 */
	public byte[] dump(String key) {
		return redisTemplate.dump(key);
	}

	/**
	 * 判断是否存在key
	 * 
	 * @param key 键
	 * @return 是否存在，存在：true，不存在：false
	 */
	public Boolean hasKey(String key) {
		return redisTemplate.hasKey(key);
	}

	/**
	 * 给key设置过期时间
	 * 
	 * @param key 键
	 * @param timeout 超时时间，倒计时方式
	 * @param unit 时间单位
	 * @return 设置结果，如果设置成功返回true,失败返回false
	 */
	public Boolean expire(String key, long timeout, TimeUnit unit) {
		return redisTemplate.expire(key, timeout, unit);
	}

	/**
	 * 设置过期时间
	 * 
	 * @param key 键
	 * @param date 超时时间，截至日期方式
	 * @return 设置结果，如果设置成功返回true,失败返回false
	 */
	public Boolean expireAt(String key, Date date) {
		return redisTemplate.expireAt(key, date);
	}

	/**
	 * 查找匹配的key
	 * redis中允许模糊查询的有3个通配符，分别是：*，?，[]
	 *
	 * *：通配任意多个字符
	 * ?：通配单个字符
	 * []：通配括号内的某一个字符
	 *
	 * eg:
	 * "*"+ pattern + "*"
	 * "?" + pattern
	 * "?" + pattern
	 * 
	 * @param pattern 匹配规则
	 * @return 匹配到的键列表
	 */
	public Set<String> keys(String pattern) {
		return redisTemplate.keys(pattern);
	}

	/**
	 * 将当前数据库的 key 移动到给定的数据库 db 当中
	 * 
	 * @param key 键
	 * @param dbIndex 数据库索引
	 * @return 移动结果，成功：true，失败：false
	 */
	public Boolean move(String key, int dbIndex) {
		return redisTemplate.move(key, dbIndex);
	}

	/**
	 * 移除 key 的过期时间，key 将持久保持
	 * 
	 * @param key 键
	 * @return 移除结果，成功：true，失败：false
	 */
	public Boolean persist(String key) {
		return redisTemplate.persist(key);
	}

	/**
	 * 返回 key 的剩余的过期时间
	 * 
	 * @param key 键
	 * @param unit 时间单位
	 * @return 移除结果，成功：true，失败：false
	 */
	public Long getExpire(String key, TimeUnit unit) {
		return redisTemplate.getExpire(key, unit);
	}

	/**
	 * 返回 key 的剩余的过期时间
	 * 
	 * @param key 键
	 * @return 剩余的过期时间戳毫秒值
	 */
	public Long getExpire(String key) {
		return redisTemplate.getExpire(key);
	}

	/**
	 * 从当前数据库中随机返回一个 key
	 * 
	 * @return 随机值
	 */
	public String randomKey() {
		return redisTemplate.randomKey();
	}

	/**
	 * 修改 key 的名称
	 * 
	 * @param oldKey 旧键
	 * @param newKey 新键
	 */
	public void rename(String oldKey, String newKey) {
		redisTemplate.rename(oldKey, newKey);
	}

	/**
	 * 仅当 newkey 不存在时，将 oldKey 改名为 newkey
	 *
	 * @param oldKey 旧键
	 * @param newKey 新键
	 * @return 修改结果，成功：true，失败：false
	 */
	public Boolean renameIfAbsent(String oldKey, String newKey) {
		return redisTemplate.renameIfAbsent(oldKey, newKey);
	}

	/**
	 * 查询key所储存的值的类型
	 * 
	 * @param key 键 被查询的key
	 * @return Redis数据类型@see org.springframework.data.redis.connection.DataType
	 */
	public DataType type(String key) {
		return redisTemplate.type(key);
	}


	/* -------------------string相关操作--------------------- */

	/**
	 * 设置键值对
	 *
	 * @param key 键
	 * @param value 值
	 */
	public void set(String key, String value) {
		redisTemplate.opsForValue().set(key, value);
	}

	/**
	 * 根据键获取值
	 *
	 * @param key 键
	 * @return 值
	 */
	public String get(String key) {
		return redisTemplate.opsForValue().get(key);
	}

	/**
	 * 返回key中字符串值的子字符
	 *
	 * @param key 键
	 * @param start 开始位置
	 * @param end 结束位置
	 * @return 字串
	 */
	public String getRange(String key, long start, long end) {
		return redisTemplate.opsForValue().get(key, start, end);
	}

	/**
	 * 将给定 key 的值设为 value ，并返回 key 的旧值(old value)
	 * 
	 * @param key 键
	 * @param value 值 新值
	 * @return 旧值
	 */
	public String getAndSet(String key, String value) {
		return redisTemplate.opsForValue().getAndSet(key, value);
	}

	/**
	 * 对 key 所储存的字符串值，获取指定偏移量上的位(bit)
	 * 
	 * @param key 键
	 * @param offset 偏移量
	 * @return 指定偏移量上的位(bit)
	 */
	public Boolean getBit(String key, long offset) {
		return redisTemplate.opsForValue().getBit(key, offset);
	}

	/**
	 * 设置ASCII码, 字符串'a'的ASCII码是97, 转为二进制是'01100001', 此方法是将二进制第offset位值变为value
	 *
	 * 应用场景
	 * 1.可作为简单的布尔过滤器来判断用户是否执行过某些操作
	 * 2.用户日活，月活,留存率的统计
	 * 3.实现用户上线次数统计
	 * 4.用户在线状态及人数统计记录
	 *
	 * @param key 键 被修改的key
	 * @param offset 位置
	 * @param value 值,true为1, false为0
	 * @return 返回的是原位置上的值
	 */
	public Boolean setBit(String key, long offset, boolean value) {
		return redisTemplate.opsForValue().setBit(key, offset, value);
	}

	/**
	 * 设置键值对并设置超时时间
	 * 
	 * @param key 键
	 * @param value 值
	 * @param timeout 过期时间
	 * @param unit
	 *            时间单位, 天:TimeUnit.DAYS 小时:TimeUnit.HOURS 分钟:TimeUnit.MINUTES
	 *            秒:TimeUnit.SECONDS 毫秒:TimeUnit.MILLISECONDS
	 */
	public void setEx(String key, String value, long timeout, TimeUnit unit) {
		redisTemplate.opsForValue().set(key, value, timeout, unit);
	}

	/**
	 * 只有在 key 不存在时设置 key 的值
	 * 
	 * @param key 键
	 * @param value 值
	 * @return 之前已经存在返回false,不存在返回true
	 */
	public Boolean setIfAbsent(String key, String value) {
		return redisTemplate.opsForValue().setIfAbsent(key, value);
	}
	/**
	 * 只有在 key 不存在时设置 key 的值, 同时设置超时时间并保证原子性操作
	 *
	 * @param key 键
	 * @param value 值
	 * @return 之前已经存在返回false,不存在返回true
	 */
	public Boolean setIfAbsent(String key, String value, long expire, TimeUnit timeUnit) {
		return redisTemplate.opsForValue().setIfAbsent(key, value, expire, timeUnit);
	}

	/**
	 * 用 value 参数覆写给定 key 所储存的字符串值，从偏移量 offset 开始
	 * 
	 * @param key 键
	 * @param value 值
	 * @param offset 从指定位置开始覆写
	 */
	public void setRange(String key, String value, long offset) {
		redisTemplate.opsForValue().set(key, value, offset);
	}

	/**
	 * 获取字符串的长度
	 * 
	 * @param key 键
	 * @return 字符串的长度
	 */
	public Long size(String key) {
		return redisTemplate.opsForValue().size(key);
	}

	/**
	 * 批量添加
	 * 
	 * @param maps 键值对列表
	 */
	public void multiSet(Map<String, String> maps) {
		redisTemplate.opsForValue().multiSet(maps);
	}

	/**
	 * 批量获取
	 *
	 * @param keys 键列表
	 * @return 值列表
	 */
	public List<String> multiGet(Collection<String> keys) {
		return redisTemplate.opsForValue().multiGet(keys);
	}

	/**
	 * 同时设置一个或多个 key-value 对，当且仅当所有给定 key 都不存在
	 * 
	 * @param maps 键值对列表
	 * @return 之前已经存在返回false,只要有一个存在，其他也不能设置成功,都不存在时返回true
	 */
	public Boolean multiSetIfAbsent(Map<String, String> maps) {
		return redisTemplate.opsForValue().multiSetIfAbsent(maps);
	}

	/**
	 * 增加(自增长), 负数则为自减
	 * 
	 * @param key 键，如果键不存在则新增并增长
	 * @param increment 步长
	 * @return 增长后的值
	 */
	public Long incrBy(String key, long increment) {
		return redisTemplate.opsForValue().increment(key, increment);
	}

	/**
	 * 增加(自增长), 负数则为自减
	 *
	 * @param key 键，如果键不存在则新增并增长
	 * @param increment 步长
	 * @return 增长后的值
	 */
	public Double incrByFloat(String key, double increment) {
		return redisTemplate.opsForValue().increment(key, increment);
	}

	/**
	 * 在原来的字符串值后面追加到末尾
	 * 
	 * @param key 键
	 * @param value 值
	 * @return 新值的长度
	 */
	public Integer append(String key, String value) {
		return redisTemplate.opsForValue().append(key, value);
	}

	/*-------------------hash相关操作------------------------- */

	/**
	 * 获取存储在哈希表中指定字段的值
	 * 
	 * @param key 键
	 * @param hashKey 哈希键
	 * @return 值
	 */
	public Object hGet(String key, String hashKey) {
		return redisTemplate.opsForHash().get(key, hashKey);
	}

	/**
	 * 设置哈希值
	 *
	 * @param key 键
	 * @param hashKey 哈希键
	 * @param value 值
	 */
	public void hPut(String key, String hashKey, String value) {
		redisTemplate.opsForHash().put(key, hashKey, value);
	}

	/**
	 * 获取所有给定字段的值
	 * 
	 * @param key 键
	 * @return 所有的字段值
	 */
	public Map<Object, Object> hGetAll(String key) {
		return redisTemplate.opsForHash().entries(key);
	}

	/**
	 * 获取所有给定字段的值
	 * 
	 * @param key 键
	 * @param fields 字段列表
	 * @return 字段值列表
	 */
	public List<Object> hMultiGet(String key, Collection<Object> fields) {
		return redisTemplate.opsForHash().multiGet(key, fields);
	}

	/**
	 * 批量添加字段值
	 *
	 * @param key 键
	 * @param maps 键值对列表
	 */
	public void hPutAll(String key, Map<String, String> maps) {
		redisTemplate.opsForHash().putAll(key, maps);
	}

	/**
	 * 仅当hashKey不存在时才设置
	 * 
	 * @param key 键
	 * @param hashKey 哈希键
	 * @param value 哈希值
	 * @return 如果存在设置成功，返回true，否则为false
	 */
	public Boolean hPutIfAbsent(String key, String hashKey, String value) {
		return redisTemplate.opsForHash().putIfAbsent(key, hashKey, value);
	}

	/**
	 * 删除一个或多个哈希表字段
	 * 
	 * @param key 键
	 * @param fields 哈希键列表
	 * @return 删除的条数
	 */
	public Long hDelete(String key, Object... fields) {
		return redisTemplate.opsForHash().delete(key, fields);
	}

	/**
	 * 查看哈希表 key 中，指定的字段是否存在
	 * 
	 * @param key 键
	 * @param field 哈希键
	 * @return 存在：true，不存在：false
	 */
	public boolean hExists(String key, String field) {
		return redisTemplate.opsForHash().hasKey(key, field);
	}

	/**
	 * 为哈希表 key 中的指定字段的整数值加上增量 increment
	 * 
	 * @param key 键
	 * @param field 哈希键
	 * @param increment 增量，可为负数
	 * @return 增长之后的值
	 */
	public Long hIncrBy(String key, Object field, long increment) {
		return redisTemplate.opsForHash().increment(key, field, increment);
	}

	/**
	 * 为哈希表 key 中的指定字段的浮点值加上增量 increment
	 * 
	 * @param key 键
	 * @param field 哈希键
	 * @param delta 增量，可为负数
	 * @return 增长之后的值
	 */
	public Double hIncrByFloat(String key, Object field, double delta) {
		return redisTemplate.opsForHash().increment(key, field, delta);
	}

	/**
	 * 获取所有哈希表中的字段
	 * 
	 * @param key 哈希键
	 * @return 所有的哈希键
	 */
	public Set<Object> hKeys(String key) {
		return redisTemplate.opsForHash().keys(key);
	}

	/**
	 * 获取哈希表中所有值
	 *
	 * @param key 哈希键
	 * @return 所有字段的值
	 */
	public List<Object> hValues(String key) {
		return redisTemplate.opsForHash().values(key);
	}

	/**
	 * 获取哈希表中字段的数量
	 * 
	 * @param key 哈希键
	 * @return 字段的数量
	 */
	public Long hSize(String key) {
		return redisTemplate.opsForHash().size(key);
	}

	/**
	 * 迭代哈希表中的键值对
	 * 
	 * @param key 键
	 * @param options 查询条件封装对象
	 * @return 游标对象，注意使用完关闭游标
	 */
	public Cursor<Entry<Object, Object>> hScan(String key, ScanOptions options) {
		return redisTemplate.opsForHash().scan(key, options);
	}

	/* ------------------------list相关操作---------------------------- */

	/**
	 * 通过索引获取列表中的元素
	 * 
	 * @param key 键
	 * @param index 索引
	 * @return 元素
	 */
	public String lIndex(String key, long index) {
		return redisTemplate.opsForList().index(key, index);
	}

	/**
	 * 获取列表指定范围内的元素
	 * 
	 * @param key 键
	 * @param start 开始位置, 0是开始位置
	 * @param end 结束位置, -1返回所有
	 * @return 指定范围内的元素
	 */
	public List<String> lRange(String key, long start, long end) {
		return redisTemplate.opsForList().range(key, start, end);
	}

	/**
	 * 存储在list头部
	 * 
	 * @param key 键
	 * @param value 值
	 * @return list长度
	 */
	public Long lLeftPush(String key, String value) {
		return redisTemplate.opsForList().leftPush(key, value);
	}

	/**
	 * 在list头部添加多个值
	 *
	 * @param key 键
	 * @param value 值列表
	 * @return list长度
	 */
	public Long lLeftPushAll(String key, String... value) {
		return redisTemplate.opsForList().leftPushAll(key, value);
	}

	/**
	 * 在list左边添加多个值
	 *
	 * @param key 键
	 * @param value 值列表
	 * @return list长度
	 */
	public Long lLeftPushAll(String key, Collection<String> value) {
		return redisTemplate.opsForList().leftPushAll(key, value);
	}

	/**
	 * 当list存在的时候才在左边加入
	 * 
	 * @param key 键
	 * @param value 值
	 * @return list长度
	 */
	public Long lLeftPushIfPresent(String key, String value) {
		return redisTemplate.opsForList().leftPushIfPresent(key, value);
	}

	/**
	 * 如果pivot存在,再pivot左边添加
	 * 
	 * @param key 键
	 * @param pivot 参考元素
	 * @param value 值
	 * @return list长度
	 */
	public Long lLeftPush(String key, String pivot, String value) {
		return redisTemplate.opsForList().leftPush(key, pivot, value);
	}

	/**
	 * 在list的右边添加
	 *
	 * @param key 键
	 * @param value 值
	 * @return list长度
	 */
	public Long lRightPush(String key, String value) {
		return redisTemplate.opsForList().rightPush(key, value);
	}

	/**
	 * 在list的右边添加多个值
	 *
	 * @param key 键
	 * @param value 值
	 * @return list长度
	 */
	public Long lRightPushAll(String key, String... value) {
		return redisTemplate.opsForList().rightPushAll(key, value);
	}

	/**
	 * 在list的右边添加多个值
	 *
	 * @param key 键
	 * @param value 值
	 * @return list长度
	 */
	public Long lRightPushAll(String key, Collection<String> value) {
		return redisTemplate.opsForList().rightPushAll(key, value);
	}

	/**
	 * 如果list存在则在右边添加值
	 * 
	 * @param key 键
	 * @param value 值
	 * @return list长度
	 */
	public Long lRightPushIfPresent(String key, String value) {
		return redisTemplate.opsForList().rightPushIfPresent(key, value);
	}

	/**
	 * 在pivot元素的右边添加值
	 * 
	 * @param key 键
	 * @param pivot 参考元素
	 * @param value 值
	 * @return list长度
	 */
	public Long lRightPush(String key, String pivot, String value) {
		return redisTemplate.opsForList().rightPush(key, pivot, value);
	}

	/**
	 * 通过索引设置列表元素的值
	 * 
	 * @param key 键
	 * @param index 索引
	 * @param value 值
	 */
	public void lSet(String key, long index, String value) {
		redisTemplate.opsForList().set(key, index, value);
	}

	/**
	 * 移出并获取列表的第一个元素
	 * 
	 * @param key 键
	 * @return 删除的元素
	 */
	public String lLeftPop(String key) {
		return redisTemplate.opsForList().leftPop(key);
	}

	/**
	 * 移出并获取列表左边的第一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止
	 * 
	 * @param key 键
	 * @param timeout 等待时间
	 * @param unit 时间单位
	 * @return 左边可获取到的第一个元素
	 */
	public String lBLeftPop(String key, long timeout, TimeUnit unit) {
		return redisTemplate.opsForList().leftPop(key, timeout, unit);
	}

	/**
	 * 从列表的右边弹出第一个元素
	 * 
	 * @param key 键
	 * @return 删除的元素
	 */
	public String lRightPop(String key) {
		return redisTemplate.opsForList().rightPop(key);
	}

	/**
	 * 移出并获取列表的右边一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止
	 * 
	 * @param key 键
	 * @param timeout 等待时间
	 * @param unit 时间单位
	 * @return 列表的右边一个元素
	 */
	public String lBRightPop(String key, long timeout, TimeUnit unit) {
		return redisTemplate.opsForList().rightPop(key, timeout, unit);
	}

	/**
	 * 移除A列表的右边一个元素，并将该元素添加到B列表的左边并返回该元素
	 * 
	 * @param sourceKey 要移出的列表
	 * @param destinationKey 要移入的列表
	 * @return 被移除的元素
	 */
	public String lRightPopAndLeftPush(String sourceKey, String destinationKey) {
		return redisTemplate.opsForList().rightPopAndLeftPush(sourceKey, destinationKey);
	}

	/**
	 * 移除A列表的右边一个元素，并将该元素添加到B列表的左边并返回该元素, 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止
	 *
	 * @param sourceKey 要移出的列表
	 * @param destinationKey 要移入的列表
	 * @param timeout 超时时间
	 * @param unit 超时时间单位
	 * @return 被移除的元素
	 */
	public String lBRightPopAndLeftPush(String sourceKey, String destinationKey, long timeout, TimeUnit unit) {
		return redisTemplate.opsForList().rightPopAndLeftPush(sourceKey, destinationKey, timeout, unit);
	}

	/**
	 * 删除集合中值等于value的元素
	 * 
	 * @param key 键
	 * @param count
	 * 			count > 0 : 从表头开始向表尾搜索，移除与 value 相等的元素，数量为 count 。
	 * 			count < 0 : 从表尾开始向表头搜索，移除与 value 相等的元素，数量为 count 的绝对值。
	 * 			count = 0 : 移除表中所有与 value 相等的值。
	 * @param value 值
	 * @return 移除成功的元素数量
	 */
	public Long lRemove(String key, long count, String value) {
		return redisTemplate.opsForList().remove(key, count, value);
	}

	/**
	 * 裁剪list，保留起止索引位置的数据
	 * 
	 * @param key 键
	 * @param start 开始索引
	 * @param end 截至索引
	 */
	public void lTrim(String key, long start, long end) {
		redisTemplate.opsForList().trim(key, start, end);
	}

	/**
	 * 获取列表长度
	 * 
	 * @param key 键
	 * @return 列表长度
	 */
	public Long lLen(String key) {
		return redisTemplate.opsForList().size(key);
	}

	/* --------------------set相关操作-------------------------- */

	/**
	 * set添加元素
	 * 
	 * @param key 键
	 * @param values 多个值
	 * @return 添加成功的数量
	 */
	public Long sAdd(String key, String... values) {
		return redisTemplate.opsForSet().add(key, values);
	}

	/**
	 * set移除元素
	 * 
	 * @param key 键
	 * @param values 多个值
	 * @return 删除成功的数量
	 */
	public Long sRemove(String key, Object... values) {
		return redisTemplate.opsForSet().remove(key, values);
	}

	/**
	 * 移除并返回集合的一个随机元素
	 * 
	 * @param key 键
	 * @return 被移除的元素
	 */
	public String sPop(String key) {
		return redisTemplate.opsForSet().pop(key);
	}

	/**
	 * 将元素value从一个集合移到另一个集合
	 * 
	 * @param key 键
	 * @param value 值
	 * @param destKey 目标集合
	 * @return 是否移动成功，成功：true，失败：false
	 */
	public Boolean sMove(String key, String value, String destKey) {
		return redisTemplate.opsForSet().move(key, value, destKey);
	}

	/**
	 * 获取集合的大小
	 * 
	 * @param key 键
	 * @return 集合的大小
	 */
	public Long sSize(String key) {
		return redisTemplate.opsForSet().size(key);
	}

	/**
	 * 判断集合是否包含value
	 * 
	 * @param key 键
	 * @param value 值
	 * @return 包含：true，不包含：false
	 */
	public Boolean sIsMember(String key, Object value) {
		return redisTemplate.opsForSet().isMember(key, value);
	}

	/**
	 * 获取两个集合的交集
	 * 
	 * @param key 集合A
	 * @param otherKey 集合B
	 * @return 交集
	 */
	public Set<String> sIntersect(String key, String otherKey) {
		return redisTemplate.opsForSet().intersect(key, otherKey);
	}

	/**
	 * 获取key集合与多个集合的交集
	 * 
	 * @param key 集合A
	 * @param otherKeys 集合列表
	 * @return 交集
	 */
	public Set<String> sIntersect(String key, Collection<String> otherKeys) {
		return redisTemplate.opsForSet().intersect(key, otherKeys);
	}

	/**
	 * key集合与otherKey集合的交集存储到destKey集合中
	 * 
	 * @param key 集合A
	 * @param otherKey 集合列表
	 * @param destKey 存储交集的集合
	 * @return 交集数量
	 */
	public Long sIntersectAndStore(String key, String otherKey, String destKey) {
		return redisTemplate.opsForSet().intersectAndStore(key, otherKey, destKey);
	}

	/**
	 * key集合与多个集合的交集存储到destKey集合中
	 *
	 * @param key 集合A
	 * @param otherKeys 集合列表
	 * @param destKey 存储交集的集合
	 * @return 交集数量
	 */
	public Long sIntersectAndStore(String key, Collection<String> otherKeys, String destKey) {
		return redisTemplate.opsForSet().intersectAndStore(key, otherKeys,
				destKey);
	}

	/**
	 * 获取两个集合的并集
	 *
	 * @param key 集合A
	 * @param otherKey 集合B
	 * @return 集合AB并集
	 */
	public Set<String> sUnion(String key, String otherKey) {
		return redisTemplate.opsForSet().union(key, otherKey);
	}

	/**
	 * 获取key集合与多个集合的并集
	 *
	 * @param key 集合A
	 * @param otherKeys 集合列表
	 * @return 并集
	 */
	public Set<String> sUnion(String key, Collection<String> otherKeys) {
		return redisTemplate.opsForSet().union(key, otherKeys);
	}

	/**
	 * key集合与otherKey集合的并集存储到destKey中
	 *
	 * @param key 集合A
	 * @param otherKeys 集合列表
	 * @param destKey 并集集合
	 * @return 并集集合长度
	 */
	public Long sUnionAndStore(String key, String otherKeys, String destKey) {
		return redisTemplate.opsForSet().unionAndStore(key, otherKeys, destKey);
	}

	/**
	 * key集合与多个集合的并集存储到destKey中
	 *
	 * @param key 集合A
	 * @param otherKeys 集合列表
	 * @param destKey 并集集合
	 * @return 并集集合长度
	 */
	public Long sUnionAndStore(String key, Collection<String> otherKeys,
			String destKey) {
		return redisTemplate.opsForSet().unionAndStore(key, otherKeys, destKey);
	}

	/**
	 * 获取两个集合的差集
	 *
	 * @param key 集合A
	 * @param otherKey 集合B
	 * @return 集合AB差集
	 */
	public Set<String> sDifference(String key, String otherKey) {
		return redisTemplate.opsForSet().difference(key, otherKey);
	}

	/**
	 * 获取key集合与多个集合的差集
	 *
	 * @param key 集合A
	 * @param otherKeys 集合列表
	 * @return 差集
	 */
	public Set<String> sDifference(String key, Collection<String> otherKeys) {
		return redisTemplate.opsForSet().difference(key, otherKeys);
	}

	/**
	 * key集合与otherKey集合的差集存储到destKey中
	 *
	 * @param key 集合A
	 * @param otherKey 集合B
	 * @param destKey 差集集合
	 * @return 差集集合长度
	 */
	public Long sDifference(String key, String otherKey, String destKey) {
		return redisTemplate.opsForSet().differenceAndStore(key, otherKey,
				destKey);
	}

	/**
	 * key集合与多个集合的差集存储到destKey中
	 *
	 *
	 * @param key 集合A
	 * @param otherKeys 集合列表
	 * @param destKey 差集集合
	 * @return 差集集合长度
	 */
	public Long sDifference(String key, Collection<String> otherKeys, String destKey) {
		return redisTemplate.opsForSet().differenceAndStore(key, otherKeys,
				destKey);
	}

	/**
	 * 获取集合所有元素
	 * 
	 * @param key 键
	 * @return 所有元素集合
	 */
	public Set<String> setMembers(String key) {
		return redisTemplate.opsForSet().members(key);
	}

	/**
	 * 随机获取集合中的一个元素
	 * 
	 * @param key 键
	 * @return 随机获取的元素
	 */
	public String sRandomMember(String key) {
		return redisTemplate.opsForSet().randomMember(key);
	}

	/**
	 * 随机获取集合中count个元素
	 * 
	 * @param key 键
	 * @param count 随机获取元素的数量
	 * @return 随机元素列表
	 */
	public List<String> sRandomMembers(String key, long count) {
		return redisTemplate.opsForSet().randomMembers(key, count);
	}

	/**
	 * 随机获取集合中count个元素并且去除重复的
	 * 
	 * @param key 键
	 * @param count 随机获取元素的数量
	 * @return 随机元素列表
	 */
	public Set<String> sDistinctRandomMembers(String key, long count) {
		return redisTemplate.opsForSet().distinctRandomMembers(key, count);
	}

	/**
	 * 按条件查询数据
	 *
	 * @param key 键
	 * @param options 查询条件
	 * @return 数据集合
	 */
	public Cursor<String> sScan(String key, ScanOptions options) {
		return redisTemplate.opsForSet().scan(key, options);
	}

	/* ------------------zSet相关操作--------------------------------*/
	
	/**
	 * 添加元素,有序集合是按照元素的score值由小到大排列
	 * 
	 * @param key 键
	 * @param value 值
	 * @param score 排序值
	 * @return 是否添加成功，成功：true，失败：false
	 */
	public Boolean zAdd(String key, String value, double score) {
		return redisTemplate.opsForZSet().add(key, value, score);
	}

	/**
	 * 添加多个元素
	 *
	 * @param key 键
	 * @param values 值和排序值列表
	 * @return 添加成功的数量
	 */
	public Long zAdd(String key, Set<TypedTuple<String>> values) {
		return redisTemplate.opsForZSet().add(key, values);
	}

	/**
	 * 删除多个值
	 *
	 * @param key 键
	 * @param values 要删除的值列表
	 * @return 删除成功的数量
	 */
	public Long zRemove(String key, Object... values) {
		return redisTemplate.opsForZSet().remove(key, values);
	}

	/**
	 * 增加元素的score值，并返回增加后的值
	 * 
	 * @param key 键
	 * @param value 值
	 * @param delta 增量
	 * @return 增加后的值
	 */
	public Double zIncrementScore(String key, String value, double delta) {
		return redisTemplate.opsForZSet().incrementScore(key, value, delta);
	}

	/**
	 * 返回元素在集合的排名,有序集合是按照元素的score值由小到大排列
	 * 
	 * @param key 键
	 * @param value 值
	 * @return 0表示第一位
	 */
	public Long zRank(String key, Object value) {
		return redisTemplate.opsForZSet().rank(key, value);
	}

	/**
	 * 返回元素在集合的排名,按元素的score值由大到小排列
	 * 
	 * @param key 键
	 * @param value 值
	 * @return 排名
	 */
	public Long zReverseRank(String key, Object value) {
		return redisTemplate.opsForZSet().reverseRank(key, value);
	}

	/**
	 * 获取集合的元素, 从小到大排序
	 * 
	 * @param key 键
	 * @param start 开始位置
	 * @param end 结束位置, -1查询所有
	 * @return 元素集合
	 */
	public Set<String> zRange(String key, long start, long end) {
		return redisTemplate.opsForZSet().range(key, start, end);
	}

	/**
	 * 获取集合元素, 并且把score值也获取
	 * 
	 * @param key 键
	 * @param start 开始排名
	 * @param end 截至排名
	 * @return 元素集合包括排名信息
	 */
	public Set<TypedTuple<String>> zRangeWithScores(String key, long start, long end) {
		return redisTemplate.opsForZSet().rangeWithScores(key, start, end);
	}

	/**
	 * 根据Score值查询集合元素
	 * 
	 * @param key 键
	 * @param min 最小值
	 * @param max 最大值
	 * @return 排名区间的元素集合
	 */
	public Set<String> zRangeByScore(String key, double min, double max) {
		return redisTemplate.opsForZSet().rangeByScore(key, min, max);
	}

	/**
	 * 根据Score值查询集合元素及排名, 从小到大排序
	 * 
	 * @param key 键
	 * @param min 最小值
	 * @param max 最大值
	 * @return 排名区间的元素和排名集合
	 */
	public Set<TypedTuple<String>> zRangeByScoreWithScores(String key, double min, double max) {
		return redisTemplate.opsForZSet().rangeByScoreWithScores(key, min, max);
	}

	/**
	 * 
	 * @param key 键
	 * @param min
	 * @param max
	 * @param start
	 * @param end
	 * @return
	 */
	public Set<TypedTuple<String>> zRangeByScoreWithScores(String key, double min, double max, long start, long end) {
		return redisTemplate.opsForZSet().rangeByScoreWithScores(key, min, max, start, end);
	}

	/**
	 * 获取集合的元素, 从大到小排序
	 * 
	 * @param key 键
	 * @param start
	 * @param end
	 * @return
	 */
	public Set<String> zReverseRange(String key, long start, long end) {
		return redisTemplate.opsForZSet().reverseRange(key, start, end);
	}

	/**
	 * 获取集合的元素, 从大到小排序, 并返回score值
	 * 
	 * @param key 键
	 * @param start
	 * @param end
	 * @return
	 */
	public Set<TypedTuple<String>> zReverseRangeWithScores(String key, long start, long end) {
		return redisTemplate.opsForZSet().reverseRangeWithScores(key, start, end);
	}

	/**
	 * 根据Score值查询集合元素, 从大到小排序
	 * 
	 * @param key 键
	 * @param min
	 * @param max
	 * @return
	 */
	public Set<String> zReverseRangeByScore(String key, double min, double max) {
		return redisTemplate.opsForZSet().reverseRangeByScore(key, min, max);
	}

	/**
	 * 根据Score值查询集合元素, 从大到小排序
	 * 
	 * @param key 键
	 * @param min
	 * @param max
	 * @return
	 */
	public Set<TypedTuple<String>> zReverseRangeByScoreWithScores(
			String key, double min, double max) {
		return redisTemplate.opsForZSet().reverseRangeByScoreWithScores(key, min, max);
	}

	/**
	 * 
	 * @param key 键
	 * @param min
	 * @param max
	 * @param start
	 * @param end
	 * @return
	 */
	public Set<String> zReverseRangeByScore(String key, double min, double max, long start, long end) {
		return redisTemplate.opsForZSet().reverseRangeByScore(key, min, max, start, end);
	}

	/**
	 * 根据score值获取集合元素数量
	 * 
	 * @param key 键
	 * @param min
	 * @param max
	 * @return
	 */
	public Long zCount(String key, double min, double max) {
		return redisTemplate.opsForZSet().count(key, min, max);
	}

	/**
	 * 获取集合大小
	 * 
	 * @param key 键
	 * @return
	 */
	public Long zSize(String key) {
		return redisTemplate.opsForZSet().size(key);
	}

	/**
	 * 获取集合大小
	 * 
	 * @param key 键
	 * @return
	 */
	public Long zZCard(String key) {
		return redisTemplate.opsForZSet().zCard(key);
	}

	/**
	 * 获取集合中value元素的score值
	 * 
	 * @param key 键
	 * @param value 值
	 * @return
	 */
	public Double zScore(String key, Object value) {
		return redisTemplate.opsForZSet().score(key, value);
	}

	/**
	 * 移除指定索引位置的成员
	 * 
	 * @param key 键
	 * @param start
	 * @param end
	 * @return
	 */
	public Long zRemoveRange(String key, long start, long end) {
		return redisTemplate.opsForZSet().removeRange(key, start, end);
	}

	/**
	 * 根据指定的score值的范围来移除成员
	 * 
	 * @param key 键
	 * @param min
	 * @param max
	 * @return
	 */
	public Long zRemoveRangeByScore(String key, double min, double max) {
		return redisTemplate.opsForZSet().removeRangeByScore(key, min, max);
	}

	/**
	 * 获取key和otherKey的并集并存储在destKey中
	 * 
	 * @param key 键
	 * @param otherKey
	 * @param destKey
	 * @return
	 */
	public Long zUnionAndStore(String key, String otherKey, String destKey) {
		return redisTemplate.opsForZSet().unionAndStore(key, otherKey, destKey);
	}

	/**
	 * 
	 * @param key 键
	 * @param otherKeys
	 * @param destKey
	 * @return
	 */
	public Long zUnionAndStore(String key, Collection<String> otherKeys, String destKey) {
		return redisTemplate.opsForZSet().unionAndStore(key, otherKeys, destKey);
	}

	/**
	 * 交集
	 * 
	 * @param key 键
	 * @param otherKey 集合A
	 * @param destKey 存储集合
	 * @return 交集
	 */
	public Long zIntersectAndStore(String key, String otherKey, String destKey) {
		return redisTemplate.opsForZSet().intersectAndStore(key, otherKey, destKey);
	}

	/**
	 * 交集
	 * 
	 * @param key 键
	 * @param otherKeys 集合列表
	 * @param destKey 存储集合
	 * @return 交集
	 */
	public Long zIntersectAndStore(String key, Collection<String> otherKeys, String destKey) {
		return redisTemplate.opsForZSet().intersectAndStore(key, otherKeys, destKey);
	}

	/**
	 * 使用迭代器获取
	 *
	 * @param key 键
	 * @param options 查询条件
	 * @return 列表
	 */
	public Cursor<TypedTuple<String>> zScan(String key, ScanOptions options) {
		return redisTemplate.opsForZSet().scan(key, options);
	}
}