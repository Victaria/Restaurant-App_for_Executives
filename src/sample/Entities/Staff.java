package sample.Entities;

import java.util.Comparator;

public class Staff implements Comparator {
    private int id;
    private String name;

    private static int lastId = 0;

    public Staff(int id, String name) {
        this.id = id;
        this.name = name;

        setLastId(id);
    }

    public Staff(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compare(Object o1, Object o2) {
            if (o1.equals(o2)) return 0;
            else return -1;
    }

    public static int getLastId() {
        return lastId;
    }

    public static void setLastId(int id) {
        if (lastId < id)
            lastId = id;
    }
}
