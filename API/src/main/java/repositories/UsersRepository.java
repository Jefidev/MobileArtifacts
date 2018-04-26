package repositories;

import dao.Dao;
import querydsl.QUsers;
import querydsl.UsersData;


public class UsersRepository extends Dao {

    public UsersData createOrRetrieve(String mail){
        QUsers users = new QUsers("Users");

        UsersData d = this.queryFactory.select(QUsers.Users).from(users).where(users.idUsers.eq(mail)).fetchFirst();

        if(d == null){
            System.out.println("Addding user " + mail);
            this.queryFactory.insert(users).columns(users.idUsers).values(mail).execute();
            d = this.queryFactory.select(QUsers.Users).from(users).where(users.idUsers.eq(mail)).fetchFirst();
        }

        return d;
    }

    public void updateUser(String id, UsersData u){
        QUsers users = new QUsers("Users");
        this.queryFactory.update(users).where(users.idUsers.eq(id))
                .set(users.rfid, u.getRfid())
                .set(users.name, u.getName())
                .execute();
    }


    public UsersData getByID(String id){
        QUsers users = new QUsers("Users");
        return this.queryFactory.selectFrom(users).where(users.idUsers.eq(id)).fetchFirst();
    }

}
