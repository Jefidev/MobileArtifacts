package repositories;

import BusinessObjects.Achievement;
import BusinessObjects.AchievementDetail;
import BusinessObjects.EventsDetail;
import com.querydsl.core.Tuple;
import com.querydsl.sql.SQLExpressions;
import dao.Dao;
import querydsl.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jfink on 13/04/18.
 */
public class AchievementsRepository extends Dao {

    public List<Achievement> getAllAchievements(UsersData u){
        QAchievements ach = new QAchievements("Achievements");
        QDone done = new QDone("Done");

        List<Tuple> tmp = this.queryFactory.select(ach.id, ach.name, ach.description, ach.points ,done)
                .from(ach)
                .leftJoin(SQLExpressions.selectFrom(done).where(done.userId.eq(u.getIdUsers())), done)
                .on(ach.id.eq(done.achievementId))
                .fetch();

        List<Achievement> retVal = new ArrayList<>();
        for(Tuple t: tmp){
            Achievement a = new Achievement(t.get(ach.id), t.get(ach.name), t.get(ach.description), t.get(done) != null, t.get(ach.points));
            retVal.add(a);
        }

        return retVal;
    }

    public Achievement getAchievement(UsersData u, int id){
        QAchievements ach = new QAchievements("Achievements");
        QDone done = new QDone("Done");

        Tuple t = this.queryFactory.select(ach.id, ach.name, ach.description, ach.points ,done)
                .from(ach)
                .leftJoin(SQLExpressions.selectFrom(done).where(done.userId.eq(u.getIdUsers())), done)
                .on(ach.id.eq(done.achievementId))
                .where(ach.id.eq(id))
                .fetchFirst();

        return new Achievement(t.get(ach.id), t.get(ach.name), t.get(ach.description), t.get(done) != null, t.get(ach.points));
    }

    public List<EventsDetail> getDetails(UsersData u, int id){

        QEvents event = new QEvents("Events");
        QAccomplished accomp = new QAccomplished("Accomplished");

        List<Tuple> tmp = this.queryFactory.select(event.id, event.name, event.description, accomp)
                .from(event)
                .leftJoin(SQLExpressions.selectFrom(accomp).where(accomp.idUser.eq(u.getIdUsers())), accomp)
                .on(accomp.idEvent.eq(event.id))
                .where(event.achievement.eq(id))
                .fetch();

        List<EventsDetail> result = new ArrayList<>();
        for(Tuple t : tmp){
            EventsDetail e = new EventsDetail(t.get(event.id), t.get(event.name), t.get(event.description), t.get(accomp) != null);
            result.add(e);
        }

        return result;
    }

}
