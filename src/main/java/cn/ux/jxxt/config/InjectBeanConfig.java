package cn.ux.jxxt.config;

import cn.ux.jxxt.vo.GeneralMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InjectBeanConfig {
    @Bean("GeneralMessage")
    public GeneralMessage getGeneralMessage() {
        return new GeneralMessage();
    }
}
