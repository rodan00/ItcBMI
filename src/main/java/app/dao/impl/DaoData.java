package app.dao.impl;

import app.dao.IDao;
import app.model.DataRecord;
import app.model.Sex;
import jdk.nashorn.api.scripting.ScriptObjectMirror;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DaoData implements IDao {

    private static final String fileName="src/main/resources/dataRecords.txt";
    private static int listSize;

    @Override
    public ArrayList<DataRecord> loadDataRecords() {
        ArrayList<DataRecord> loadedDataList=new ArrayList<DataRecord>();
        File plik = new File(fileName);
        BufferedReader br;
        String line;
        try {
            br = new BufferedReader(new FileReader(plik));
            while ((line=br.readLine())!=null) {
                DataRecord dataRecord= lineToDataRecord(line);
                System.out.println(dataRecord);
                loadedDataList.add(dataRecord);

            }
            br.close();
        }
        catch (IOException e) {e.printStackTrace(); }
        return loadedDataList;
    }

    @Override
    public void addDataRecordToDB(DataRecord dataRecordToAdd){
        ArrayList<DataRecord> dataRecodsList=loadDataRecords();
        dataRecodsList.add(dataRecordToAdd);
        saveDataRecords(dataRecodsList);
    }

    @Override
    public int getListSize() {
        listSize=loadDataRecords().size();
        return listSize;
    }

    private void saveDataRecords(List<DataRecord> dataRecordsToSave){
        File file=new File(fileName);
        FileWriter fw= null;
        try {
            fw = new FileWriter(file);
            BufferedWriter bw=new BufferedWriter(fw);
            for (DataRecord dataRecord : dataRecordsToSave) {
                String line=dataRecordToLine(dataRecord);
                System.out.println(line);
                bw.write(line+"\n");
            }
            bw.close();
        }
        catch (IOException e) {e.printStackTrace();}
    }

    private DataRecord lineToDataRecord(String line){
        DataRecord dataRecord=null;
        String[] value=line.split(",");
        int id=Integer.parseInt(value[0]);
        double hight=Double.parseDouble(value[1]);
        double weight=Double.parseDouble(value[2]);
        double bmi=Double.parseDouble(value[3]);
        Sex sex= Sex.valueOf(value[4]);
        DateTimeFormatter dateFormat=DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date=LocalDate.parse(value[5],dateFormat);
        dataRecord=new DataRecord(id,hight,weight,bmi,sex,date);
        return dataRecord;
    }

    private String dataRecordToLine(DataRecord dataRecord){
        StringBuilder sb=new StringBuilder();
        sb.append(dataRecord.getDataId()+",");
        sb.append(dataRecord.getHight()+",");
        sb.append(dataRecord.getWeight()+",");
        sb.append(dataRecord.getBmi()+",");
        sb.append(dataRecord.getSex()+",");
        sb.append(dataRecord.getDate());
        return sb.toString();
    }

}
