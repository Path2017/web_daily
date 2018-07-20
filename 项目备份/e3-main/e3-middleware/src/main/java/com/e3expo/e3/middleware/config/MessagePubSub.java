package com.e3expo.e3.middleware.config;

import redis.clients.jedis.JedisPubSub;

public class MessagePubSub extends JedisPubSub {
	
	/**
	 * 监听到订阅频道接受到消息时的回调
	 */
	@Override
    public void onMessage(String channel, String message) {
        // TODO Auto-generated method stub
        System.out.println(channel + "," + message);
    }

	/**
	 * 监听到订阅模式接受到消息时的回调 
	 */
    @Override
    public void onPMessage(String pattern, String channel, String message) {
        // TODO Auto-generated method stub
        System.out.println(pattern + "," + channel + "," + message);

    }
    /**
     * 订阅频道时的回调
     */
    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        // TODO Auto-generated method stub
        System.out.println("onSubscribe: channel[" + channel + "]," + "subscribedChannels[" + subscribedChannels + "]");
    }
    
    
    /**
     * 取消订阅频道时的回调
     */
    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
        // TODO Auto-generated method stub
        System.out.println(
                "onUnsubscribe: channel[" + channel + "], " + "subscribedChannels[" + subscribedChannels + "]");
    }
    
    /**
     * 取消订阅模式时的回调
     */
    @Override
    public void onPUnsubscribe(String pattern, int subscribedChannels) {
        // TODO Auto-generated method stub
        System.out.println("onPUnsubscribe: pattern[" + pattern + "]," +

                "subscribedChannels[" + subscribedChannels + "]");

    }
    
    /**
     * 订阅频道模式时的回调
     */
    @Override
    public void onPSubscribe(String pattern, int subscribedChannels) {
        System.out.println("onPSubscribe: pattern[" + pattern + "], " +

                "subscribedChannels[" + subscribedChannels + "]");

    }
}
