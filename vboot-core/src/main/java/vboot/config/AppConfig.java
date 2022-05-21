package vboot.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
@Configuration
@ConfigurationProperties(prefix = "zsvg")
public class AppConfig {

    @Getter
    @Setter
    @Valid
    private Jwt jwt = new Jwt();

    @Getter
    @Setter
    public static class Jwt {
        private String header = "Authorization"; //HTTP 报头的认证字段的 key
        private String prefix = "Bearer "; //HTTP 报头的认证字段的值的前缀

        private long atime = 28800L; //accessToken过期时间 此处单位秒 ，默认8小时 28800

        private long rtime = 3456000L; //accessToken过期时间 此处单位秒 ，默认30天 3456000

        private String akey;//生成accessToken用的key

        private String rkey;//生成refreshToken用的key


        //以下赞未用到
//        //在线用户 key，根据 key 查询 redis 中在线用户的数据
//        private String onlineKey;
//
//        //验证码 key
//        private String codeKey;
//
//        //token 续期检查
//        private Long detect;
//
//        //续期时间
//        private Long renew;

    }

}
