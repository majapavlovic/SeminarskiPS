package domain;

import java.io.Serializable;
import java.sql.*;

public interface GeneralDObject extends Serializable{

    String getAtrValue();
    String setAtrValue();
    String getClassName();
    String getWhereCondition();
    String getNameByColumn(int column);

    GeneralDObject getNewRecord(ResultSet rs) throws SQLException;

    public default String getFields() { return "*"; }

    public default String getOrderBy() { return ""; }

    public default String getWhereCondition1() {return ""; }
}
