package repositories;

import dao.Dao;
import querydsl.QOrchestrators;
import querydsl.QSensors;

/**
 * Created by jfink on 17/04/18.
 */
public class SensorsRepository extends Dao {

    public void setValue(String orchestrator, int id , float value){
        QSensors sensor = new QSensors("Sensors");

        this.queryFactory.update(sensor)
                .where(sensor.id.eq(id).and(sensor.orchestrator.eq(orchestrator)))
                .set(sensor.lastValue, value).execute();
    }
}
