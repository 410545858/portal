/**
 * 
 */
package com.frank.startup.portal.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.frank.startup.portal.common.Constant;
import com.frank.startup.portal.service.RedisSessionService;
import com.google.common.collect.Maps;

/**
 * @author frankwong
 *
 */
@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class RedisSessionServiceImpl implements RedisSessionService,Constant {

	@Autowired
	private RedisTemplate redisTemplate;
	
	
	@Override
	public long del(String... keys) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void set(final byte[] key,final byte[] value,final long liveTime) {
		redisTemplate.execute(new RedisCallback() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                connection.set(key, value);
                if (liveTime > 0) {
                    connection.expire(key, liveTime);
                }
                return 1L;
            }
        });
	}
	
	@Override
	public void set(String sessionId,String key,Object value) {
		byte[] serializedKey = redisTemplate.getKeySerializer().serialize(key);
		byte[] serializedValue = redisTemplate.getValueSerializer().serialize(value);
		final Map<byte[], byte[]> map = Maps.newHashMap();
		map.put(serializedKey, serializedValue);
		final byte[] serializedId = redisTemplate.getKeySerializer().serialize(sessionId);
		redisTemplate.execute(new RedisCallback(){
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				connection.multi();
				connection.hMSet(serializedId, map);
				connection.expire(serializedId, SESSION_LIVE_SECONDS);
				connection.exec();
				return 1L;
			}
		});
	}

	@Override
	public void set(String key, Object value) {
		// TODO Auto-generated method stub
	}

	@Override
	public void set(byte[] key,byte[] value) {
		// TODO Auto-generated method stub
	}

	@Override
	public Object get(String sessionId,String key) {
		
		final byte[] serializedId = redisTemplate.getKeySerializer().serialize(sessionId);
		final byte[] serializedKey = redisTemplate.getKeySerializer().serialize(key);
		
		List<byte[]> result = (List<byte[]>)redisTemplate.execute(new RedisCallback(){
			@Override
			public List<byte[]> doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.hMGet(serializedId, serializedKey);
			}
		});
		if (result == null || result.size() != 1) {
			return null;
		}
		return  redisTemplate.getValueSerializer().deserialize(result.get(0));
	}

	@Override
	public void Setkeys(String pattern) {
	}

	@Override
	public boolean exists(String key) {
		return false;
	}

	@Override
	public String flushDB() {
		return null;
	}

	@Override
	public long dbSize() {
		return 0;
	}

	@Override
	public String ping() {
		return redisTemplate.getConnectionFactory().getConnection().ping();
	}

	@Override
	public void freshSession(String sessionId) {
		final byte[] serializedId = redisTemplate.getKeySerializer().serialize(sessionId);
		redisTemplate.execute(new RedisCallback(){
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				 connection.expire(serializedId, SESSION_LIVE_SECONDS);
				 return null;
			}
		});
		
	}

}
