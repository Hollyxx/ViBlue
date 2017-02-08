package cn.estronger.bike.bean;

/**
 * Created by MrLv on 2017/1/6.
 */

public class MarkLength implements Comparable<MarkLength> {
    private int length;
    private int id;

    public MarkLength(int length, int id) {
        this.length = length;
        this.id = id;
    }

    public int getLength() {
        return length;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setLength(int length) {
        this.length = length;
    }
    public int compareTo(MarkLength o) {
        return this.getLength() - o.getLength();
    }
}
