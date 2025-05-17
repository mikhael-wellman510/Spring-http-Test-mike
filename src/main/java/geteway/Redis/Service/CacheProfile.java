package geteway.Redis.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import geteway.Redis.Profile;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class CacheProfile {
    private static final String USER_KEY_PREFIX = "user";
    private final RedisTemplate<String,Object> redisTemplate;
    private final ObjectMapper objectMapper;

    public void cacheUser(Profile profile){
        String key = USER_KEY_PREFIX + profile.getId();

        redisTemplate.opsForValue().set(key,profile, 10, TimeUnit.SECONDS);

    }

    public Profile getCachedProfile(Long id){
        String key = USER_KEY_PREFIX + id;
        Object data = redisTemplate.opsForValue().get(key);
        return objectMapper.convertValue(data,Profile.class);
    }


}
