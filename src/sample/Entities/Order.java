package sample.Entities;

import java.util.Comparator;
import java.util.Date;

public class Order implements Comparator {

    private int id;
    private int table;
    private double sum;
    private String date;
    private String staffName;

    private static int lastId = 0;

    public Order(int id, int tbl, double sum, String date, String staffId) {
        this.id = id;
        this.table = tbl;
        this.sum = sum;
        this.date = date;
        this.staffName = staffId;

        setLastId(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTable() {
        return table;
    }

    public void setTable(int table) {
        this.table = table;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffId) {
        this.staffName = staffId;
    }

    public static int getLastId() {
        return lastId;
    }

    public static void setLastId(int id){
        if (lastId < id)
            lastId = id;
    }

    @Override
    public int compare(Object o1, Object o2) {
        if (o1.equals(o2)) return 0;
        else return -1;
    }
}
