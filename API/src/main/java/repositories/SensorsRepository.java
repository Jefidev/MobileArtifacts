package repositories;

import dao.Dao;
import querydsl.QOrchestrators;

/**
 * Created by jfink on 17/04/18.
 */
public class SensorsRepository extends Dao {

    public void setValue(String orchestrator, int id , float value){

        QOrchestrators orches = new QOrchestrators("Orchestrator");
    }
}
