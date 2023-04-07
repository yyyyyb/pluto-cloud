package com.pluto;

import com.pluto.config.QQProperties;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.connection.MessageListener;

import javax.annotation.Resource;
import java.io.File;

public class QQBotRunner implements ApplicationRunner {
    @Resource
    private QQProperties qq;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        botInit();
    }


    /**
     * 机器人初始化
     */
    public void botInit(){
        BotConfiguration botConfiguration = new BotConfiguration();
        // 登录协议
        botConfiguration.setProtocol(BotConfiguration.MiraiProtocol.valueOf(config.getProtocol()));
        // 心跳协议
        botConfiguration.setHeartbeatStrategy(BotConfiguration.HeartbeatStrategy.STAT_HB);
        // 设置 cache 目录
        botConfiguration.setCacheDir(new File("./cache"));
        // 设置 device
        botConfiguration.fileBasedDeviceInfo("./device.json");
        //设置是否掉线重登录
        botConfiguration.setAutoReconnectOnForceOffline(config.isReLogin());

        bot = BotFactory.INSTANCE.newBot(config.getAccount(), config.getPassWord(), botConfiguration);
        // 登录
        bot.login();
        // 注册消息处理 通道
        bot.getEventChannel().registerListenerHost(new MessageListener());
    }
}
