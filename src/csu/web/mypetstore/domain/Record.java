package csu.web.mypetstore.domain;

import java.io.Serializable;
import java.util.Date;

public class Record implements Serializable {

    private String username;
    private String record;
    private Date recordDate;
    public Record(){

    }
    public Record(String username,String record,Date recordDate){
        this.username = username;
        this.record = record;
        this.recordDate = recordDate;
    }
    public String getUsername(){
        return username;
    }
    public String getRecord(){
        return record;
    }
    public void setUsername(String username){
        this.username =username;
    }
    public void setRecord(String record){
        this.record = record;
    }
    public Date getRecordDate(){
        return recordDate;
    }
    public void setRecordDate(Date recordDate){
        this.recordDate = recordDate;
    }
}
