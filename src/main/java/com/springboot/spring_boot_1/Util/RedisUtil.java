package com.springboot.spring_boot_1.Util;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
@Component
public class RedisUtil {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    //===============================common===============================
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace(); return false;
        }
    }
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }
    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace(); return false;
        }
    }
    @SuppressWarnings("unchecked")
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            }
            else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }
    //===============================String===============================
    @Component
    public class redisString {
        public Object get(String key) {
            try {
                return key == null ? null : redisTemplate.opsForValue().get(key);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        public boolean set(String key, Object value) {
            try {
                redisTemplate.opsForValue().set(key, value); return true;
            } catch (Exception e) {
                e.printStackTrace(); return false;
            }
        }
        public boolean set(String key, Object value, long time) {
            try {
                if (time > 0) {
                    redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
                }
                else {
                    set(key, value);
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace(); return false;
            }
        }
        public long incr(String key, long delta) {
            if (delta < 0) {
                throw new RuntimeException("閫掑 鍥犲瓙蹇呴』澶т簬 0");
            }
            return redisTemplate.opsForValue().increment(key, delta);
        }
        public long decr(String key, long delta) {
            if (delta < 0) {
                throw new RuntimeException("閫掑噺鍥犲瓙蹇呴』灏忎簬 0");
            }
            return redisTemplate.opsForValue().increment(key, -delta);
        }
    }
    //===============================Map===============================
    @Component
    public class redisMap {
        public Object get(String key, String item) {
            return redisTemplate.opsForHash().get(key, item);
        }
        public Map<Object, Object> getAll(String key) {
            return redisTemplate.opsForHash().entries(key);
        }
        public boolean set(String key, Map<String, Object> map) {
            try {
                redisTemplate.opsForHash().putAll(key, map); return true;
            } catch (Exception e) {
                e.printStackTrace(); return false;
            }
        }
        public boolean set(String key, Map<String, Object> map, long time) {
            try {
                redisTemplate.opsForHash().putAll(key, map);
                if (time > 0) {
                    expire(key, time);
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace(); return false;
            }
        }
        public boolean set(String key, String item, Object value) {
            try {
                redisTemplate.opsForHash().put(key, item, value); return true;
            } catch (Exception e) {
                e.printStackTrace(); return false;
            }
        }
        public boolean set(String key, String item, Object value, long time) {
            try {
                redisTemplate.opsForHash().put(key, item, value);
                if (time > 0) {
                    expire(key, time);
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace(); return false;
            }
        }
        public void del(String key, Object... item) {
            redisTemplate.opsForHash().delete(key, item);
        }
        public boolean hasKey(String key, String item) {
            return redisTemplate.opsForHash().hasKey(key, item);
        }
        public double decr(String key, String item, double by) {
            return redisTemplate.opsForHash().increment(key, item, -by);
        }
        public double incr(String key, String item, double by) {
            return redisTemplate.opsForHash().increment(key, item, by);
        }
    }
    //===============================Set===============================
    @Component
    public class redisSet {
        public Set<Object> get(String key) {
            try {
                return redisTemplate.opsForSet().members(key);
            } catch (Exception e) {
                e.printStackTrace(); return null;
            }
        }
        public boolean hasKey(String key, Object value) {
            try {
                return redisTemplate.opsForSet().isMember(key, value);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        public long set(String key, Object... values) {
            try {
                return redisTemplate.opsForSet().add(key, values);
            } catch (Exception e) {
                e.printStackTrace(); return 0;
            }
        }
        public long set(String key, long time, Object... values) {
            try {
                Long count = redisTemplate.opsForSet().add(key, values);
                if (time > 0) {
                    expire(key, time);
                }
                return count;
            } catch (Exception e) {
                e.printStackTrace(); return 0;
            }
        }
        public long getSize(String key) {
            try {
                return redisTemplate.opsForSet().size(key);
            } catch (Exception e) {
                e.printStackTrace(); return 0;
            }
        }
        public long remove(String key, Object... values) {
            try {
                Long count = redisTemplate.opsForSet().remove(key, values); return count;
            } catch (Exception e) {
                e.printStackTrace(); return 0;
            }
        }
    }
    //===============================List===============================
    @Component
    public class redisList {
        public List<Object> get(String key, long start, long end) {
            try {
                return redisTemplate.opsForList().range(key, start, end);
            } catch (Exception e) {
                e.printStackTrace(); return null;
            }
        }
        public long getSize(String key) {
            try {
                return redisTemplate.opsForList().size(key);
            } catch (Exception e) {
                e.printStackTrace(); return 0;
            }
        }
        public Object getByIndex(String key, long index) {
            try {
                return redisTemplate.opsForList().index(key, index);
            } catch (Exception e) {
                e.printStackTrace(); return null;
            }
        }
        public boolean set(String key, Object value) {
            try {
                redisTemplate.opsForList().rightPush(key, value); return true;
            } catch (Exception e) {
                e.printStackTrace(); return false;
            }
        }
        public boolean set(String key, Object value, long time) {
            try {
                redisTemplate.opsForList().rightPush(key, value);
                if (time > 0) {
                    expire(key, time);
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace(); return false;
            }
        }
        public boolean set(String key, List<Object> value) {
            try {
                redisTemplate.opsForList().rightPushAll(key, value); return true;
            } catch (Exception e) {
                e.printStackTrace(); return false;
            }
        }
        public boolean set(String key, List<Object> value, long time) {
            try {
                redisTemplate.opsForList().rightPushAll(key, value);
                if (time > 0) {
                    expire(key, time);
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace(); return false;
            }
        }
        public boolean updateIndex(String key, long index, Object value) {
            try {
                redisTemplate.opsForList().set(key, index, value); return true;
            } catch (Exception e) {
                e.printStackTrace(); return false;
            }
        }
        public long remove(String key, long count, Object value) {
            try {
                Long remove = redisTemplate.opsForList().remove(key, count, value); return remove;
            } catch (Exception e) {
                e.printStackTrace(); return 0;
            }
        }
    }
}