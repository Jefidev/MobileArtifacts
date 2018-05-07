package repositories;

import dao.Dao;
import querydsl.LogsData;
import querydsl.QLogs;

import java.util.List;

/**
 * Created by jfink on 30/04/18.
 */
public class LogsRepository extends Dao {

    public List<LogsData> getLogsByAchievements(String idUser, int idAchievements){
        QLogs logs = new QLogs("Logs");
        return this.queryFactory.selectFrom(logs)
                .where(logs.idAchievement.eq(idAchievements).and(logs.idUser.eq(idUser)))
                .fetch();
    }


    public List<String> getUniqueMessages(String idUser, int idAchievements){
        QLogs logs = new QLogs("Logs");
        return this.queryFactory.selectDistinct(logs.message)
                .from(logs)
                .where(logs.idAchievement.eq(idAchievements).and(logs.idUser.eq(idUser)))
                .fetch();
    }


    public void deleteLogsByAchievements(String idUser, int idAchievements){
        QLogs logs = new QLogs("Logs");
        this.queryFactory.delete(logs)
                .where(logs.idAchievement.eq(idAchievements).and(logs.idUser.eq(idUser)))
                .execute();
    }

    public void addLogs(String idUser, int idAchievements, String message){
        QLogs logs = new QLogs("Logs");
        this.queryFactory.insert(logs)
                .columns(logs.idAchievement, logs.idUser, logs.message)
                .values(idAchievements,idUser, message)
                .execute();
    }

}
