package app.services;

import app.dao.IDao;
import app.dao.impl.DaoData;
import app.model.DataRecord;
import app.model.Sex;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ServiceBMI implements IService{

/** Poniżej podłączmy do zmiennej interfejsowej klasę implementującą
 * "DaoData" w sposób klasyczny czyli przez podstawienie i inicjalizację
 * nowego obiektu.
 * Jeżeli nie zrobimy z klasy DaoData singletona a tak nie zrobiliśmy,
 * to za każdym wywołanie dowolnej metody spowoduje powstanie nowego obiektu
 * klasy DaoData
 Zeby przez zmienną interfejsową używać Singletona DaoData należałoby do idao
 podstawiać obiekt DaoData przez metodę, czyli getterem, i zagwarantować w
 klasie DaoData występowanie prywatnego obiektu DaoData */

    public IDao idao=new DaoData();

    @Override
    public ArrayList<DataRecord> getAllDataRecords(){;
        return idao.loadDataRecords();
    }

    @Override
    public DataRecord makeDataRecord
            (String strHight, String strWeight, String strSex){
        double hight=Double.parseDouble(strHight);
        double weight=Double.parseDouble(strWeight);
        Sex sex= Sex.valueOf(strSex);
        int id= idao.loadDataRecords().size()+1;
        DataRecord dataRecord = new DataRecord(id,hight,weight,sex);
        idao.addDataRecordToDB(dataRecord);
        return dataRecord;
    }

    @Override
    public int getDataRecordListSize(){
        return idao.getListSize();
    }


    @Override
    public DataRecord findDataRecord(String strId) {
        int Id =Integer.parseInt(strId);
        ArrayList<DataRecord> dataRecordList= idao.loadDataRecords();
        DataRecord searchedDataRecord=null;
        for (DataRecord dataRecord: dataRecordList){
            if(dataRecord.getDataId()==Id) {
                searchedDataRecord=dataRecord;
            }
        }
        System.out.println(searchedDataRecord);
        return searchedDataRecord;
    }
}
