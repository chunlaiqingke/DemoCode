package com.handsome.democode.ratelimit;

import java.util.HashMap;
import java.util.Map;

public class FunnelRateLimiter {
    private final Map<String, Funnel> funnelMap = new HashMap<>();

    public boolean isActionAllowed(String username, String action, int capacity, int allowQuota, int perSecond){
        String key = "funnel:" + action + ":" + username;
        if(!funnelMap.containsKey(key)){
            funnelMap.put(key, new Funnel(capacity, allowQuota, perSecond));
        }
        Funnel funnel = funnelMap.get(key);

        return funnel.watering(1);
    }

    public static void main(String[] args) throws InterruptedException {
        FunnelRateLimiter rateLimiter = new FunnelRateLimiter();
        int testAccessCount = 30;
        int capacity = 5;
        int allowQuota = 5;
        int perSecond = 30;
        int allowCount = 0;
        int denyCount = 0;
        for(int i = 0; i < testAccessCount ; i++) {
            boolean actionAllowed = rateLimiter.isActionAllowed("user", "dosomething", capacity, allowQuota, perSecond);
            if(actionAllowed) {
                allowCount++;
            } else {
                denyCount ++;
            }
            System.out.println("访问权限：" + actionAllowed);
            Thread.sleep(1000);
        }
    }
}
