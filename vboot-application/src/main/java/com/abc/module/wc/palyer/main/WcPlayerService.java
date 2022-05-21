package com.abc.module.wc.palyer.main;

import cn.hutool.http.HttpUtil;
import com.abc.module.wc.match.main.WcMatch;
import com.abc.module.wc.match.main.WcMatchRepo;
import com.abc.module.wc.stat.WcStat;
import com.abc.module.wc.stat.WcStatRepo;
import com.abc.module.wc.team.main.WcTeam;
import com.abc.module.wc.team.main.WcTeamRepo;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import vboot.common.util.lang.XdateUtil;
import vboot.common.util.lang.XnumberUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional(rollbackFor = Exception.class)
public class WcPlayerService {

    public void crawlData(String id){
        String my_url="http://info.sports.sina.com.cn/api/live/opta_lineup.php?format=json&_t=0.7390982927490362&id=o_"+id+"&callback=fun_786506334825271269";
        String str = HttpUtil.createGet(my_url).execute().body();
        String xx = str.split("fun_786506334825271269")[1];
        xx = xx.substring(1, xx.length() - 13);
        JSONObject json = JSONObject.parseObject(xx);

//        JsonObject returnData = new JsonParser().parse(xx).getAsJsonObject();
        JSONObject json2 = (JSONObject) json.get("result");
        JSONObject json3 = (JSONObject) json2.get("data");
        JSONObject matchJson = (JSONObject) json3.get("match");
        //第一步 保存球队
        WcTeam team1 = new WcTeam();
        team1.setId(""+matchJson.get("home_id"));
        team1.setName(""+matchJson.get("home_name_cn"));
        team1.setCoach(""+matchJson.get("home_trainer_cn"));
        teamRepo.save(team1);

        WcTeam team2 = new WcTeam();
        team2.setId(""+matchJson.get("away_id"));
        team2.setName(""+matchJson.get("away_name_cn"));
        team2.setCoach(""+matchJson.get("away_trainer_cn"));
        teamRepo.save(team2);

        //第二步 保存比赛
        WcMatch match = new WcMatch();
        match.setId(""+matchJson.get("id"));
        match.setHomeId(team1.getId());
        match.setHomeScore(Integer.parseInt(matchJson.get("home_score")+""));
        match.setAwayId(team2.getId());
        match.setAwayScore(Integer.parseInt(matchJson.get("away_score")+""));
        match.setName(""+matchJson.get("round_cn")+":"+team1.getName()+"VS"+team2.getName());
        matchRepo.save(match);


        //第三步 保存球员
        JSONArray array = (JSONArray) json3.get("event");
        List<WcPlayer> playerList = new ArrayList<>();
        for (Object obj : array) {
            WcPlayer player = new WcPlayer();
            JSONObject playerJson = (JSONObject) obj;
            System.out.println(playerJson);
            System.out.println(playerJson.get("id"));
            Optional<WcStat> id1 = playerStatRepo.findById("" + playerJson.get("id"));
            if(id1.isPresent()){
                continue;
            }
            System.out.println(playerJson.get("l_name_cn"));
            player.setId("" + playerJson.get("id"));
            player.setName("" + playerJson.get("l_name_cn"));
            player.setShirtNumber("" + playerJson.get("shirt_number"));
            player.setPosition("" + playerJson.get("position_long_cn"));
            String url = "http://match.sports.sina.com.cn/football/player.php?id=" + player.getId();
            Document doc = null;
            try {
                doc = Jsoup.connect(url).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Elements elements = doc.select(".txt .t dd");
            player.setEnName(elements.get(1).html());
            player.setEnFullName(elements.get(2).html());
            player.setBirthday(XdateUtil.strToDate(elements.get(3).html()));
            player.setHeight(Integer.parseInt(elements.get(4).html().replaceAll("[^0-9.]", "")));
            player.setWeight(Integer.parseInt(elements.get(5).html().replaceAll("[^0-9.]", "")));
            player.setWeekly(XnumberUtil.getRandomBigDecimal(new BigDecimal(10),new BigDecimal(50)));
            if("替补".equals(player.getPosition())){
                player.setPosition(elements.get(7).html());
            }
            if("0".equals(""+playerJson.get("lr_position"))){
                player.setTeamId(team1.getId());
            }else{
                player.setTeamId(team2.getId());
            }
//            player.setCountry(elements.get(8).html());
            player.setClub(elements.get(9).html());
            playerList.add(player);
        }
        repo.saveAll(playerList);

        //保存该比赛的球员技术统计
        playerStat(id);
    }

    private void playerStat(String id){
        String url = "http://info.sports.sina.com.cn/api/live/opta_player_stats.php?format=json&_t=0.46432963703738617&id=o_"+id+"&callback=fun_718625181803029488";

        String str = HttpUtil.createGet(url).execute().body();
        String xx = str.split("fun_718625181803029488")[1];
        xx = xx.substring(1, xx.length() - 13);
        JSONObject json = JSONObject.parseObject(xx);
        JSONObject json2 = (JSONObject) json.get("result");
        JSONObject json3 = (JSONObject) json2.get("data");
        JSONObject json4 = (JSONObject) json3.get("stats");
        JSONArray homeArray = (JSONArray) json4.get("home");
        JSONArray awayArray = (JSONArray) json4.get("away");
        List<WcStat> stats = new ArrayList<>();

        for (Object obj : homeArray) {
            WcStat stat = new WcStat();
            JSONObject statJson = (JSONObject) obj;
            System.out.println(statJson);
            stat.setId("" + statJson.get("id"));
            stat.setMatchId(id);
            stat.setPalyerId("" + statJson.get("player_id"));
            stat.setTotalScoringAtt(Integer.parseInt("" + statJson.get("total_scoring_att")));
            stat.setOntargetScoringAtt(Integer.parseInt("" + statJson.get("ontarget_scoring_att")));
            stat.setGoals(Integer.parseInt("" + statJson.get("goals")));
            stat.setGoalAssist(Integer.parseInt("" + statJson.get("goal_assist")));
            stat.setMinsPlayed(Integer.parseInt("" + statJson.get("mins_played")));
            stat.setFouls(Integer.parseInt("" + statJson.get("fouls")));
            stat.setWasFouled(Integer.parseInt("" + statJson.get("was_fouled")));
            stat.setTotalPass(Integer.parseInt("" + statJson.get("total_pass")));
            stat.setAccuratePass(Integer.parseInt("" + statJson.get("accurate_pass")));
            stat.setSaves(Integer.parseInt("" + statJson.get("saves")));
            if("z".equals("" + statJson.get("status")) ){
                stat.setStatus(true);
            }else{
                stat.setStatus(false);
            }
            stat.setIsHome(true);
            stats.add(stat);
        }

        for (Object obj : awayArray) {
            WcStat stat = new WcStat();
            JSONObject statJson = (JSONObject) obj;
            System.out.println(statJson);
            stat.setId("" + statJson.get("id"));
            stat.setMatchId(id);
            stat.setPalyerId("" + statJson.get("player_id"));
            stat.setTotalScoringAtt(Integer.parseInt("" + statJson.get("total_scoring_att")));
            stat.setOntargetScoringAtt(Integer.parseInt("" + statJson.get("ontarget_scoring_att")));
            stat.setGoals(Integer.parseInt("" + statJson.get("goals")));
            stat.setGoalAssist(Integer.parseInt("" + statJson.get("goal_assist")));
            stat.setMinsPlayed(Integer.parseInt("" + statJson.get("mins_played")));
            stat.setFouls(Integer.parseInt("" + statJson.get("fouls")));
            stat.setWasFouled(Integer.parseInt("" + statJson.get("was_fouled")));
            stat.setTotalPass(Integer.parseInt("" + statJson.get("total_pass")));
            stat.setAccuratePass(Integer.parseInt("" + statJson.get("accurate_pass")));
            stat.setSaves(Integer.parseInt("" + statJson.get("saves")));
            if("z".equals("" + statJson.get("status")) ){
                stat.setStatus(true);
            }else{
                stat.setStatus(false);
            }
            stat.setIsHome(false);
            stats.add(stat);
        }
        playerStatRepo.saveAll(stats);
    }

    public void test(){
        Optional<WcPlayer> id1 = repo.findById("109888");
        System.out.println(id1);
        System.out.println(id1.isPresent());
    }

    @Autowired
    private WcStatRepo playerStatRepo;

    @Autowired
    private WcTeamRepo teamRepo;

    @Autowired
    private WcMatchRepo matchRepo;

    @Autowired
    private WcPlayerRepo repo;


}
