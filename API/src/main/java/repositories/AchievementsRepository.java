package repositories;

import BusinessObjects.Achievement;
import com.querydsl.core.Tuple;
import com.querydsl.sql.SQLExpressions;
import dao.Dao;
import querydsl.QAchievements;
import querydsl.QDone;
import querydsl.UsersData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jfink on 13/04/18.
 */
public class AchievementsRepository extends Dao {

    public List<Achievement> getAllAchievements(UsersData u){
        QAchievements ach = new QAchievements("Achievements");
        QDone done = new QDone("Done");

        List<Tuple> tmp = this.queryFactory.select(ach.id, ach.name, ach.description, done)
                .from(ach)
                .leftJoin(SQLExpressions.selectFrom(done).where(done.userId.eq(u.getIdUsers())), done)
                .on(ach.id.eq(done.achievementId))
                .fetch();

        List<Achievement> retVal = new ArrayList<>();
        for(Tuple t: tmp){
            Achievement a = new Achievement(t.get(ach.id), t.get(ach.name), t.get(ach.description), t.get(done) != null);
            retVal.add(a);
        }

        return retVal;
    }
}
