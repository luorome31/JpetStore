package csu.web.mypetstore.persistence;
import java.util.List;
import csu.web.mypetstore.domain.Record;
public interface LogDao {
    void addRecord(Record record);
    List<Record> searchRecord(String username);
}
