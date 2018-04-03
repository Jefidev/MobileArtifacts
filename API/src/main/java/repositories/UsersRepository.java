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

}
