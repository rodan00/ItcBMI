package app.services;

import app.model.DataRecord;

import java.util.ArrayList;

public interface IService {


    DataRecord makeDataRecord(String strHight, String strWeight, String strSex);
    ArrayList<DataRecord> getAllDataRecords();
    DataRecord findDataRecord(String strId);
    int getDataRecordListSize();

}
