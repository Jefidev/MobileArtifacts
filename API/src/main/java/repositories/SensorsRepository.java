package repositories;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.Expressions;
import dao.Dao;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import querydsl.QOrchestrators;
import querydsl.QSensors;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by jfink on 17/04/18.
 */
public class SensorsRepository extends Dao {

    public void setValue(String orchestrator, int id , float value){
        DateTime dt = new DateTime(DateTimeZone.UTC);
        Timestamp ts = new Timestamp(dt.getMillis());

        QSensors sensor = new QSensors("Sensors");

        this.queryFactory.update(sensor)
                .where(sensor.id.eq(id).and(sensor.orchestrator.eq(orchestrator)))
                .set(sensor.lastValue, value)
                .set(sensor.lastUpdate, ts)
                .execute();
    }
}
