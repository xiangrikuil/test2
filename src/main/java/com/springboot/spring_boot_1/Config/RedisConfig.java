package com.springboot.spring_boot_1.Config;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
/**
 * redis 配置类
 * @program: redisdemo
 * @Author: david
 * @Description: */
@Configuration @EnableCaching //开启注解
public class RedisConfig extends CachingConfigurerSupport {
    /**
     * retemplate 相关配置
     * @param factory
     * @return*/
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
// 配置连接工厂
        template.setConnectionFactory(factory);
//使用 Jackson2JsonRedisSerializer 来序列化和反序列化 redis 的 value 值（默认用 JDK 序列化）
        Jackson2JsonRedisSerializer jacksonSeial = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
// 指定要序列化的域，field,get 和 set,以及修饰符范围，ANY 是都有包括 private 和 public om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//指定序列化输入类型，类必须非 final 修饰的（比如 String,Integer 等会跑出异常）。
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jacksonSeial.setObjectMapper(om);
// 值采用 json 序列化
        template.setValueSerializer(jacksonSeial);
//使用 StringRedisSerializer 来序列化和反序列化 redis 的 key 值
        template.setKeySerializer(new StringRedisSerializer());
// 设置 hash key 和 value 序列化模式
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(jacksonSeial);
        template.afterPropertiesSet(); return template;
    }
    /**
     * 对 hash 类型的数据操作
     * @param redisTemplate * @return*/
    @Bean
    public HashOperations<String, String, Object> hashOperations(RedisTemplate<String, Object>
                                                                         redisTemplate) {
        return redisTemplate.opsForHash();
    }
    /**
     * 对 redis 字符串类型数据操作
     * @param redisTemplate * @return*/
    @Bean
    public ValueOperations<String, Object> valueOperations(RedisTemplate<String, Object>
                                                                   redisTemplate) {
        return redisTemplate.opsForValue();
    }
    /**
     * 对链表类型的数据操作
     * @param redisTemplate * @return*/
    @Bean
    public ListOperations<String, Object> listOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForList();
    }
    /**
     * 对无序集合类型的数据操作
     * @param redisTemplate * @return*/
    @Bean
    public SetOperations<String, Object> setOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForSet();
    }
    /**
     * 对有序集合类型的数据操作
     * @param redisTemplate * @return*/
    @Bean
    public ZSetOperations<String, Object> zSetOperations(RedisTemplate<String, Object> redisTemplate)
    {
        return redisTemplate.opsForZSet();
    }
}