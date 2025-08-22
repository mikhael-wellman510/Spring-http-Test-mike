package geteway.util;

import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class RedisUtils {
	private final RedisTemplate<String,Object> redisTemplate;

	public void set(String key, Object data, long ttlSecond){
		redisTemplate.opsForValue().set(key,data,ttlSecond , TimeUnit.SECONDS);
	}

	public Object get(String key){
		return redisTemplate.opsForValue().get(key);
	}

	public Boolean hashKey(String key){
		return redisTemplate.hasKey(key);
	}

	public void deleted(String key){
		redisTemplate.delete(key);
	}

}
