package com.company.Command;

public class Client {

    Command select;
    Command insert;
    Command update;
    Command delete;

    public Client(
            Command select,
            Command insert,
            Command update,
            Command delete
    ) {
        this.select = select;
        this.insert = insert;
        this.update = update;
        this.delete = delete;
    }

    public void selectFromDB() {
        select.execute();
    }

    public void insertToDB() {
        insert.execute();
    }

    public void updateToDB() {
        update.execute();
    }

    public void deleteToDB() {
        delete.execute();
    }

}
