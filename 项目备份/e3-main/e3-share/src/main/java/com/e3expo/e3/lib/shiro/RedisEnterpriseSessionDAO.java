package com.e3expo.e3.lib.shiro;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

public class RedisEnterpriseSessionDAO extends EnterpriseCacheSessionDAO {
	
	public RedisEnterpriseSessionDAO(JedisConnectionFactory factory) {

		this.factory = factory;
	}
	
	
	
	
	
	@Override
    protected Serializable doCreate(Session session) {
        
        Serializable sessionId = super.doCreate(session);
        
        RedisConnection connection = null;
        
        try {
        		connection = factory.getConnection();
        		byte[] keys = sessionId.toString().getBytes();
        			
        		if ( !connection.exists(keys ) ) {
        			connection.set(keys, sessionToByte(session));
        		}
        		
        		connection.expire(keys, expireTime);
        } catch (Exception e) {
        		e.printStackTrace();
        } finally {
			connection.close();
		}
        return sessionId;
        
    }

	@Override
    protected Session doReadSession(Serializable sessionId) {
		
		   Session session = super.doReadSession(sessionId); 
		   
	        if(session == null){
	        	
	            RedisConnection connection = null;
	            
	            try {
	            		connection = factory.getConnection();
	            		
	    	            byte[] bytes = connection.get(sessionId.toString().getBytes());
	    	            
	    	            if(bytes != null && bytes.length > 0){
	    	                session = byteToSession(bytes);    
	    	            }
	            } catch (Exception e) {
	            		e.printStackTrace();
	            } finally {
	    				connection.close();
	    			}
	        }
	        
	        return session;
    }

	
	@Override
    protected void doUpdate(Session session) {
        //does nothing - parent class persists to cache.
		super.doUpdate(session);
		
		Serializable sessionId = session.getId();
		
		RedisConnection connection  = null;
		
		try {
			connection = factory.getConnection();
			
			byte[] keys = sessionId.toString().getBytes();
			
			if ( !connection.exists(keys) ) {
				connection.set(keys, sessionToByte(session));
			}

			connection.expire(keys, expireTime);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		
    }

	
	@Override
    protected void doDelete(Session session) {
		super.doDelete(session);
		
		RedisConnection connection = null;
		
		try {
			connection = factory.getConnection();
			Serializable sessionId = session.getId();
			byte[] keys = sessionId.toString().getBytes();
			
			if ( connection.exists(keys) ) {
				connection.del(keys);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}
    
	
	
	private byte[] sessionToByte(Session session){
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        byte[] bytes = null;
        try {
            ObjectOutputStream oo = new ObjectOutputStream(bo);
            oo.writeObject(session);
            bytes = bo.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }
	
	
	 private Session byteToSession(byte[] bytes){
	        ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
	        ObjectInputStream in;
	        SimpleSession session = null;
	        try {
	            in = new ObjectInputStream(bi);
	            session = (SimpleSession) in.readObject();
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    
	        return session;
	    }
	
	
	private int expireTime = 3600;
    
    private  JedisConnectionFactory factory;
    

}
