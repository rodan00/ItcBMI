package app.model;

import java.time.LocalDate;

public class DataRecord {

    private int dataId;
    private double hight;
    private double weight;
    private Sex sex;
    private double bmi;
    private LocalDate date;
    private int counter;

    private DataRecord(){};

    public DataRecord(int id, double hight, double weight, Sex sex){
        this.dataId=id;
        this.hight=hight;
        this.weight=weight;
        this.bmi=this.weight*10_000./(this.hight*this.hight);
        this.sex=sex;
        this.date=LocalDate.now();
    }


    public DataRecord(int id, double hight, double weight, double bmi, Sex sex, LocalDate date){
        this.dataId=id;
        this.hight=hight;
        this.weight=weight;
        this.bmi=this.weight*10_000./(this.hight*this.hight);
        this.sex=sex;
        this.date=LocalDate.now();
    }

    public int getDataId() {
        return dataId;
    }

    public double getHight() {
        return hight;
    }

    public double getWeight() {
        return weight;
    }

    public Sex getSex() { return sex;}

    public double getBmi() {
        return bmi;
    }

    public LocalDate getDate() {
        return date;
    }


    @Override
    public String toString() {
        return "Data{" +
                "id='" + dataId + '\'' +
                ", hight=" + hight +
                ", weight=" + weight +
                ", bmi=" + bmi +
                ", sex='" + sex + '\'' +
                ", date=" + date +
                '}';
    }




}
