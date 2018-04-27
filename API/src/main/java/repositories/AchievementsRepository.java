package repositories;

import BusinessObjects.Achievement;
import BusinessObjects.AchievementDetail;
import BusinessObjects.EventContext;
import BusinessObjects.EventsDetail;
import com.querydsl.core.Tuple;
import com.querydsl.sql.SQLExpressions;
import dao.Dao;
import querydsl.*;

import java.util.ArrayList;
import java.util.Date;
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
            Achievement a = new Achievement(t.get(ach.id), t.get(ach.name), t.get(ach.description), t.get(done).getUserId() != null, t.get(ach.points));
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

        return new Achievement(t.get(ach.id), t.get(ach.name), t.get(ach.description), t.get(done).getUserId() != null, t.get(ach.points));
    }

    public List<EventsDetail> getDetails(String u, int id){

        QEvents event = new QEvents("Events");
        QAccomplished accomp = new QAccomplished("Accomplished");

        List<Tuple> tmp = this.queryFactory.select(event.id, event.name, event.description, accomp)
                .from(event)
                .leftJoin(SQLExpressions.selectFrom(accomp).where(accomp.idUser.eq(u)), accomp)
                .on(accomp.idEvent.eq(event.id))
                .where(event.achievement.eq(id))
                .fetch();

        List<EventsDetail> result = new ArrayList<>();
        for(Tuple t : tmp){
            EventsDetail e = new EventsDetail(t.get(event.id), t.get(event.name), t.get(event.description), t.get(accomp).getIdUser() != null);
            result.add(e);
        }

        return result;
    }


    public Boolean alreadyDone(UsersData u, int idEvent){
        QAccomplished accomp = new QAccomplished("Accomplished");

        AccomplishedData a = this.queryFactory.selectFrom(accomp)
                .where(accomp.idUser.eq(u.getIdUsers()).and(accomp.idEvent.eq(idEvent)))
                .fetchFirst();

        return a != null;
    }


    public EventsData getEvent(int id){
        QEvents event = new QEvents("Events");
        return this.queryFactory.selectFrom(event).where(event.id.eq(id)).fetchFirst();
    }


    public List<EventContext> getContext(int id){
        QContexts cont = new QContexts("Contexts");
        QSensors sens = new QSensors("Sensorts");

        List<Tuple> tmp = this.queryFactory.select(cont.maxVal, cont.minVal, sens.lastValue, sens.name)
                .from(cont)
                .innerJoin(sens).on(sens.id.eq(cont.idSensor))
                .where(cont.idEvents.eq(id)).fetch();


        List<EventContext> result = new ArrayList<>();

        for(Tuple t:tmp){
            EventContext e = new EventContext(t.get(cont.minVal), t.get(cont.maxVal), t.get(sens.lastValue), t.get(sens.name));
            result.add(e);
        }

        return result;
    }

    private void checkAchievementDone(int idAchievement, String idUser){

        List<EventsDetail> eventAchiev = getDetails(idUser, idAchievement);

        for(EventsDetail e : eventAchiev){
            if(!e.done())
                return;
        }

        QDone done = new QDone("Done");

        this.queryFactory.insert(done)
                .columns(done.achievementId, done.earningDate, done.userId)
                .values(idAchievement, new Date(), idUser).execute();

    }

    public void eventDone(int idEvent, String idUser){
        QAccomplished acc = new QAccomplished("Accomplished");
        QEvents ev = new QEvents("Events");

        this.queryFactory.insert(acc)
                .columns(acc.idEvent, acc.idUser, acc.earningDate)
                .values(idEvent, idUser, new Date())
                .execute();

        EventsData e = getEvent(idEvent);
        checkAchievementDone(e.getAchievement(), idUser);
    }


}
