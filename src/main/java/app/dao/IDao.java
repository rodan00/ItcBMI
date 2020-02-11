package app.dao;

import app.model.DataRecord;

import java.util.ArrayList;
import java.util.List;

public interface IDao {

    ArrayList<DataRecord> loadDataRecords();
    int getListSize();
    void addDataRecordToDB(DataRecord dataRecordToAdd);

}
