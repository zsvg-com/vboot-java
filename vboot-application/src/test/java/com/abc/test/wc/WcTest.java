package com.abc.test.wc;

import com.abc.module.wc.palyer.main.WcPlayer;
import com.abc.module.wc.palyer.main.WcPlayerRepo;
import com.abc.module.wc.palyer.main.WcPlayerService;
import vboot.common.util.lang.XnumberUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;


@SpringBootTest
public class WcTest {


    @Test
    public void parse(){
        service.crawlData("958022");//A组 俄罗斯 VS 沙特阿拉伯
        service.crawlData("958023");//A组 埃及 VS 乌拉圭
        service.crawlData("958024");//A组 俄罗斯 VS 埃及
        service.crawlData("958025");//A组 乌拉圭 VS 沙特阿拉伯
        service.crawlData("958027");//A组 沙特阿拉伯 VS 埃及
        service.crawlData("958026");//A组 乌拉圭 VS 俄罗斯

        service.crawlData("958028");//B组 摩洛哥 VS 伊朗
        service.crawlData("958029");//B组 葡萄牙 VS 西班牙
        service.crawlData("958030");//B组 伊朗 VS 西班牙
        service.crawlData("958031");//B组 葡萄牙 VS 摩洛哥
        service.crawlData("958033");//B组 伊朗 VS 葡萄牙
        service.crawlData("958032");//B组 西班牙 VS 摩洛哥

        service.crawlData("958034");//C组 法国 VS 澳大利亚
        service.crawlData("958035");//C组 秘鲁 VS 丹麦
        service.crawlData("958037");//C组 丹麦 VS 澳大利亚
        service.crawlData("958036");//C组 法国 VS 秘鲁
        service.crawlData("958039");//C组 澳大利亚 VS 秘鲁
        service.crawlData("958038");//C组 丹麦 VS 法国

        service.crawlData("958041");//D组 阿根廷 VS 冰岛
        service.crawlData("958040");//D组 克罗地亚 VS 尼日利亚
        service.crawlData("958042");//D组 阿根廷 VS 克罗地亚
        service.crawlData("958043");//D组 尼日利亚 VS 冰岛
        service.crawlData("958044");//D组 冰岛 VS 克罗地亚
        service.crawlData("958045");//D组 尼日利亚 VS 阿根廷

        service.crawlData("958046");//E组 巴西 VS 瑞士
        service.crawlData("958047");//E组 哥斯达黎加 VS 塞尔维亚
        service.crawlData("958049");//E组 巴西 VS 哥斯达黎加
        service.crawlData("958048");//E组 塞尔维亚 VS 瑞士
        service.crawlData("958050");//E组 塞尔维亚 VS 巴西
        service.crawlData("958051");//E组 瑞士 VS 哥斯达黎加

        service.crawlData("958057");//F组 德国 VS 墨西哥
        service.crawlData("958056");//F组 瑞典 VS 韩国
        service.crawlData("958055");//F组 德国 VS 瑞典
        service.crawlData("958054");//F组 韩国 VS 墨西哥
        service.crawlData("958053");//F组 韩国 VS 德国
        service.crawlData("958052");//F组 墨西哥 VS 瑞典

        service.crawlData("958058");//G组 比利时 VS 巴拿马
        service.crawlData("958059");//G组 突尼斯 VS 英格兰
        service.crawlData("958060");//G组 比利时 VS 突尼斯
        service.crawlData("958061");//G组 英格兰 VS 巴拿马
        service.crawlData("958062");//G组 英格兰 VS 比利时
        service.crawlData("958063");//G组 巴拿马 VS 突尼斯

        service.crawlData("958069");//G组 哥伦比亚 VS 日本
        service.crawlData("958068");//G组 波兰 VS 塞内加尔
        service.crawlData("958066");//G组 日本 VS 塞内加尔
        service.crawlData("958067");//G组 波兰 VS 哥伦比亚
        service.crawlData("958065");//G组 日本 VS 波兰
        service.crawlData("958064");//G组 塞内加尔 VS 哥伦比亚

        service.crawlData("958070");//16强 法国 VS 阿根廷
        service.crawlData("958071");//16强 乌拉圭 VS 葡萄牙
        service.crawlData("958072");//16强 西班牙 VS 俄罗斯
        service.crawlData("958073");//16强 克罗地亚 VS 丹麦
        service.crawlData("958075");//16强 巴西 VS 墨西哥
        service.crawlData("958074");//16强 比利时 VS 日本
        service.crawlData("958077");//16强 瑞典 VS 瑞士
        service.crawlData("958076");//16强 哥伦比亚 VS 英格兰


        service.crawlData("958078");//1/4决赛 乌拉圭 VS 法国
        service.crawlData("958079");//1/4决赛 巴西 VS 比利时
        service.crawlData("958081");//1/4决赛 瑞典 VS 英格兰
        service.crawlData("958080");//1/4决赛 俄罗斯 VS 克罗地亚

        service.crawlData("958082");//半决赛 法国 VS 比利时
        service.crawlData("958083");//半决赛 克罗地亚 VS 英格兰

        service.crawlData("958084");//半决赛 比利时 VS 英格兰
        service.crawlData("958085");//决赛 法国 VS 克罗地亚

    }

    @Test
    public void addWeekly(){
        List<WcPlayer> players = repo.findAll();
        for (WcPlayer player : players) {
            player.setWeekly(XnumberUtil.getRandomBigDecimal(new BigDecimal(30),new BigDecimal(100)));
        }
        repo.saveAll(players);

    }

    @Autowired
    private WcPlayerService service;

    @Autowired
    private WcPlayerRepo repo;



}