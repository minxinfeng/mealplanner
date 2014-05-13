package com.threeone.mealplanner.push;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.baidu.yun.channel.auth.ChannelKeyPair;
import com.baidu.yun.channel.client.BaiduChannelClient;
import com.baidu.yun.channel.exception.ChannelClientException;
import com.baidu.yun.channel.exception.ChannelServerException;
import com.baidu.yun.channel.model.PushUnicastMessageRequest;
import com.baidu.yun.channel.model.PushUnicastMessageResponse;
import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
import com.threeone.mealplanner.mapper.UserBindMapper;
import com.threeone.mealplanner.model.entity.UserBind;

/*
 * @brief 推送单播通知(Android Push SDK拦截并解析) message_type = 1 (默认为0)
 */
public class PushService implements Runnable {
	private static final Log LOG = LogFactory.getLog(PushService.class);
	
	private final static String APIKEY = "FshB9Rvr4LcUaWWowF9gnYqC";
	private final static String SECRET_KEY= "Ix6fIVyBBNihOsPruWZEtseRX9So85Zz";
	
	private UserBindMapper userBindMapper;
	
	private int userId;
	private String title;
	private String description;
	
	public void run() {
		this.pushNotify(userId, title, description);
	}
	
	private void pushNotify(int userId, String title, String description){
		// 1. 设置developer平台的ApiKey/SecretKey
        ChannelKeyPair pair = new ChannelKeyPair(APIKEY, SECRET_KEY);
       
        // 2. 创建BaiduChannelClient对象实例
        BaiduChannelClient channelClient = new BaiduChannelClient(pair);

        // 3. 若要了解交互细节，请注册YunLogHandler类
        channelClient.setChannelLogHandler(new YunLogHandler() {
            public void onHandle(YunLogEvent event) {
                System.out.println(event.getMessage());
            }
        });
        
        //根据userId获取手机端的channelId 和baiduUserId
    	UserBind userBind =  userBindMapper.getUserBind(userId);
    	String baiduUserId = "";
    	long channelId = 0;
    	if(userBind != null){
	    	baiduUserId = userBind.getBaiduuserid();
	    	channelId = userBind.getChannelid();
    	}
        try {
            // 4. 创建请求类对象
            // 手机端的ChannelId， 手机端的UserId
            PushUnicastMessageRequest request = new PushUnicastMessageRequest();
            request.setDeviceType(3); // device_type => 1: web 2: pc 3:android
                                      // 4:ios 5:wp
            request.setChannelId(channelId);
            request.setUserId(baiduUserId);

            request.setMessageType(1);
            request.setMessage("{\"title\":\"" + title + "\",\"description\":\"" + description + "\"}");

            // 5. 调用pushMessage接口
            PushUnicastMessageResponse response = channelClient
                    .pushUnicastMessage(request);

            // 6. 认证推送成功
            LOG.info("Push notify to userId=" + userId + " success! Push amount : " + response.getSuccessAmount());

        } catch (ChannelClientException e) {
            // 处理客户端错误异常
           LOG.error("Push notify to userId=" + userId + " failed!" + e.getMessage());
        } catch (ChannelServerException e) {
            // 处理服务端错误异常
           LOG.error("Push notify to userId=" + userId + " failed!" + String.format(
                    "request_id: %d, error_code: %d, error_message: %s",
                    e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
        }
	}

	public void setUserBindMapper(UserBindMapper userBindMapper) {
		this.userBindMapper = userBindMapper;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
