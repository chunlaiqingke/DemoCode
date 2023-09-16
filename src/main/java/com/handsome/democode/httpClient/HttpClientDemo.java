package com.handsome.democode.httpClient;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class HttpClientDemo {

    static CloseableHttpClient client = HttpClientBuilder.create().build();

    public static void main(String[] args) throws IOException {

        String cookie = "_ntes_nnid=2e4de755f005d63df833808129fa090b,1629540283787; _ntes_nuid=2e4de755f005d63df833808129fa090b; P_INFO=15656930604|1675510621|0|rms|00&99|null&null&null#shh&null#10#0#0|&0|null|15656930604; NTES_CMT_USER_INFO=481232727%7C%E6%9C%89%E6%80%81%E5%BA%A6%E7%BD%91%E5%8F%8B0sHMtn%7Chttp%3A%2F%2Fcms-bucket.nosdn.127.net%2F2018%2F08%2F13%2F078ea9f65d954410b62a52ac773875a1.jpeg%7Cfalse%7CeWQuNzY5OGQ4NjgwYzMwNDQxZWFAMTYzLmNvbQ%3D%3D; wyy_uid=79f6cd3c-c473-4835-87b9-9e307404f445; _ga=GA1.2.444878597.1679148428; _ntes_origin_from=google; trace_session_id=018A87A4-8258-1A71-D830-D45403AB7004; fingerprint=jS2s2Wn1lnt5Cjw7QBBaLfUm; _flow_group_v2=g44; _external_mark=direct; reco_sid=3Qxx_-XIy3HK_fIj1rpN8igNbffhVcuXP7FT4yzR; gdxidpyhxdE=kO6Efzc%2F7f%2BPKeY8okhMq36svAITVvxiUZrmZy9Hhmi0nR75SY6crNjZuNMOde38Uep7dTPSsxrxrOlVq7Yu01r3EPTPLND6xuYIPzmYmTiXNNw7rpJx3UHh3QE%5C2x8hGIZA6JQ%5C6AMnq8X3r7e5GNxWv48NdE28cE0R1cGQIhEjr%2BZ4%3A1694530109996; YD00000722197596%3AWM_NI=fKw%2BigOpL%2Bx18WPcvsDjKvBEVKjRiic3LUsNLYkqmiECgc6TAvXaIYNfgCZ36v3HsaWb9SJKLc%2FLAxdHGQ0dKre2MOW6%2BjbZVHNrCmkj%2FEcSmDwnT0CXIT5RQnJunb2fYUQ%3D; YD00000722197596%3AWM_NIKE=9ca17ae2e6ffcda170e2e6eed9eb489caaaa93f34b92928bb3d85a979b9badc462a19c9dd9e57bf3b89d84b62af0fea7c3b92aa695b9ccec6d8db084ccfb3bfc89a084f57988b68597c14f9695a6b3ec63a1ed84b6f2469087a592f063879da984b56083aa9882cc538ebaa9a4d939f6aafda9f26b95bef7b4c94190bde1adc474a89889b2b37089aeabd3d04595eae58be15296988293c267a9b6e1a6e259af9cb98fd73c818887b5b85a93b28696ed6af2ba97b6d437e2a3; YD00000722197596%3AWM_TID=f%2BHgBAO9IWBAEBRUAVbRjZSC4Ou0xDM7; is_log_active_stat=1; transmit_info=%7B%22time%22%3A1694855499064%2C%22bs%22%3A%7B%22ticket_id%22%3A%2200_25117_1694855499064%22%2C%22bs_type%22%3A%22%22%7D%7D";
        String pageSessionId = "018A9D0B-35EC-D388-637D-CDC554D4141F";
        //请求2页demo
        for(int i = 1; i< 3; i++) {
            JSONObject recommendListResult = getRecommendList(i, cookie);
            JSONArray result = recommendListResult.getJSONArray("result");
            int size = result.size();
            for(int j = 0; j<size; j++) {
                JSONObject object = result.getJSONObject(j);
                int serverid = object.getIntValue("serverid");
                String ordersn = object.getString("game_ordersn");
                JSONObject equipDetail = getEquipDetail(serverid, ordersn, pageSessionId, cookie);
                JSONObject equip = equipDetail.getJSONObject("equip");
                String equip_desc = equip.getString("equip_desc");
                System.out.println(equip_desc);
            }
        }

    }

    public static JSONObject getEquipDetail(int serverId, String ordersn, String pageSessionId, String cookie){
        String url = "https://my.cbg.163.com/cgi/api/get_equip_detail";
        //param
        NameValuePair clientTypeParam = new BasicNameValuePair("client_type", "h5");
        List<NameValuePair> params = Arrays.asList(clientTypeParam);
        //head
        NameValuePair acceptHead = new BasicNameValuePair("Accept", "application/json, text/javascript, */*; q=0.01");
        NameValuePair acceptEncodingHead = new BasicNameValuePair("Accept-Encoding", "gzip, deflate, br");
        NameValuePair acceptLanguageHead = new BasicNameValuePair("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
        NameValuePair cookieHead = new BasicNameValuePair("Cookie", cookie);
        NameValuePair contentTypeHead = new BasicNameValuePair("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        NameValuePair userAgentHead = new BasicNameValuePair("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36");
        List<NameValuePair> heads = Arrays.asList(acceptHead, acceptEncodingHead, acceptLanguageHead, cookieHead, userAgentHead, contentTypeHead);
        //body
        NameValuePair serverIdBody = new BasicNameValuePair("serverid", "" + serverId);
        NameValuePair ordersnBody = new BasicNameValuePair("ordersn", ordersn);
        NameValuePair viewLocBody = new BasicNameValuePair("view_loc", "reco_home|tag_key:{\"is_from_ad_reco\": 0, \"tag\": \"latest\"}");
        NameValuePair exterBody = new BasicNameValuePair("exter", "direct");
        NameValuePair pageSessionIdBody = new BasicNameValuePair("page_session_id", pageSessionId);
        List<NameValuePair> body = Arrays.asList(serverIdBody, ordersnBody, viewLocBody, exterBody, pageSessionIdBody);

        //请求
        try {
            return postUrlEncodedForm(url, body, heads, params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static JSONObject getRecommendList(int page, String cookie){
        String url = "https://my.cbg.163.com/cgi-bin/recommend.py?client_type=h5&act=recommd_home&count=15&view_loc=reco_home&page=1&exter=direct&page_session_id=018A9D0B-35EC-D388-637D-CDC554D4141F&_=1694868186217";
        //创建请求参数
//        NameValuePair clientTypeParam = new BasicNameValuePair("client_type", "h5");
//        NameValuePair recommendHomeParam = new BasicNameValuePair("act", "recommend_home");
//        NameValuePair countParam = new BasicNameValuePair("count", "15");
//        NameValuePair viewLocParam = new BasicNameValuePair("view_loc", "reco_home");
//        NameValuePair pageParam = new BasicNameValuePair("page", "" + page);
//        NameValuePair exterParam = new BasicNameValuePair("exter", "direct");
//        NameValuePair pageSessionIdParam = new BasicNameValuePair("page_session_id", "018A9D0B-35EC-D388-637D-CDC554D4141F");
//        NameValuePair underlineParam = new BasicNameValuePair("_", "1694855498701");
//        List<NameValuePair> params = Arrays.asList(clientTypeParam, recommendHomeParam, countParam, viewLocParam, pageParam, exterParam, pageSessionIdParam, underlineParam);
        //创建请求头
//        NameValuePair acceptHead = new BasicNameValuePair("Accept", "application/json, text/javascript, */*; q=0.01");
//        NameValuePair acceptEncodingHead = new BasicNameValuePair("Accept-Encoding", "gzip, deflate, br");
//        NameValuePair acceptLanguageHead = new BasicNameValuePair("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
        NameValuePair cookieHead = new BasicNameValuePair("Cookie", cookie);
        NameValuePair userAgentHead = new BasicNameValuePair("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36");
//        NameValuePair refererHead = new BasicNameValuePair("Referer", "https://my.cbg.163.com/cgi/mweb/");
//        NameValuePair secChUa = new BasicNameValuePair("Sec-Ch-Ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"");
//        NameValuePair secChUaMobile = new BasicNameValuePair("Sec-Ch-Ua-Mobile", "?0");
//        NameValuePair secChUaPlatform = new BasicNameValuePair("Sec-Ch-Ua-Platform", "\"macOS\"");
//        NameValuePair secFetchSite = new BasicNameValuePair("Sec-Fetch-Site", "same-origin");
//        NameValuePair xmlHttpRequest = new BasicNameValuePair("X-Requested-With", "XMLHttpRequest");
        List<NameValuePair> heads = Arrays.asList(cookieHead, userAgentHead);

        //请求
        try {
            return get(url, heads);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static JSONObject get(String url, List<NameValuePair> heads) throws Exception {
        URI uri = new URIBuilder(url).build();
        HttpGet httpGet = new HttpGet(uri);
        heads.forEach(h -> httpGet.addHeader(h.getName(), h.getValue()));
        CloseableHttpResponse execute = client.execute(httpGet);
        if(execute.getStatusLine().getStatusCode() == 200) {
            String s = EntityUtils.toString(execute.getEntity(), StandardCharsets.UTF_8);
            System.out.println(JSONObject.parseObject(s).toJSONString());
            return JSONObject.parseObject(s);

        }
        return null;
    }

    public static JSONObject postUrlEncodedForm(String url, List<NameValuePair> bodyParam, List<NameValuePair> heads, List<NameValuePair> params) throws Exception {
        URI uri = new URIBuilder(url).setParameters(params).build();
        HttpPost httpPost = new HttpPost(uri);
        heads.forEach(h -> httpPost.addHeader(h.getName(), h.getValue()));
        UrlEncodedFormEntity body = new UrlEncodedFormEntity(bodyParam);
        httpPost.setEntity(body);
        CloseableHttpResponse execute = client.execute(httpPost);
        if(execute.getStatusLine().getStatusCode() == 200) {
            String s = EntityUtils.toString(execute.getEntity(), StandardCharsets.UTF_8);
            return JSONObject.parseObject(s);
        }
        return null;
    }


}
