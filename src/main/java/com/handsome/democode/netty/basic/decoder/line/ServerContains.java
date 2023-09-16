package com.handsome.democode.netty.basic.decoder.line;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class ServerContains {

    static Map<String, Channel> channelMap = new HashMap<>();

    public static void registerChannel(Channel channel){
        if(channelMap.containsValue(channel)) return;
        channelMap.put(channel.id().asLongText(), channel);
    }

    public static Channel getChannel(String id){
        return channelMap.get(id);
    }

    static Timer timer;

    static {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                System.out.println("Time Server");
                for(Channel ctx : channelMap.values()) {
                    byte[] bytes = ("定时任务返回:" + System.currentTimeMillis() + System.getProperty("line.separator")).getBytes();
                    ByteBuf message = Unpooled.buffer(bytes.length);
                    message.writeBytes(bytes);
                    ctx.writeAndFlush(message);
                }
            }
        }, 0, 2000);

    }
}
