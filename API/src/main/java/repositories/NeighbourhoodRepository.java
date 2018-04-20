package repositories;

import BusinessObjects.Coordinate;
import dao.Dao;
import querydsl.*;

import java.awt.geom.Path2D;
import java.util.List;

/**
 * Created by jfink on 18/04/18.
 */
public class NeighbourhoodRepository extends Dao {

    // Which neighbourhood is linked to these coordinates
    public NeighbourhoodData getNeighbourhood(Coordinate coordinate){
        QNeighbourhood nei = new QNeighbourhood("Neighbourhood");
        QLien lien = new QLien("Lien");
        QCoordinates coord = new QCoordinates("Coordinates");

        //Retrieving all the points linked to a  neighbourhood

        List<NeighbourhoodData> all = this.queryFactory.select(QNeighbourhood.Neighbourhood).from(nei).fetch();

        //Looping through all of them and testing

        for(NeighbourhoodData cur : all){

            //Retrieving the border points

            List<CoordinatesData> c = this.queryFactory.select(QCoordinates.Coordinates).from(coord)
                    .innerJoin(lien).on(lien.coordinateID.eq(coord.id))
                    .where(lien.neighbourhoodId.eq(cur.getId()))
                    .orderBy(lien.ord.asc()).fetch();

            Path2D poly = buildPolygon(c);

            if(coordinatesAreIn(poly, coordinate))
                return cur;

        }

        return null;
    }



    private boolean coordinatesAreIn(Path2D poly, Coordinate c){
        return poly.contains((double) c.lat(), (double) c.longi());
    }

    private Path2D buildPolygon(List<CoordinatesData> points){
        Path2D polygon = new Path2D.Float();

        polygon.moveTo((double) points.get(0).getLatitude(), (double) points.get(0).getLongitude());

        for(CoordinatesData c : points){
            polygon.lineTo((double) c.getLatitude(), (double) c.getLongitude());
        }

        polygon.closePath();

        return polygon;
    }


    public List<NeighbourhoodData> getAll(){
        QNeighbourhood n = new QNeighbourhood("Neighbourhoods");
        return this.queryFactory.select(n).from(n).fetch();
    }

}
