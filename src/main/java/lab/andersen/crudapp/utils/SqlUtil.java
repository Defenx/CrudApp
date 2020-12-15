package lab.andersen.crudapp.utils;

import java.sql.*;

public class SqlUtil {
    public static long findIdForCreateOperation(Connection connection, String query) throws SQLException {
        long createdId = 0;
        Statement statementForKnowId = connection.createStatement();
        ResultSet resultSet = statementForKnowId.executeQuery(query);
        if (resultSet.next()){
            createdId = resultSet.getLong(1);
        }
        statementForKnowId.close();
        resultSet.close();
        return createdId;
    }

    public static String findNameByIdCountry(Connection connection, String query, long id) throws SQLException {
        PreparedStatement statementForFindCountryName;
        statementForFindCountryName=  connection.prepareStatement(query);
        statementForFindCountryName.setLong(1,id);
        ResultSet resultSet;
        String countryName = null;
        resultSet = statementForFindCountryName.executeQuery();
        if(resultSet.next()){
            countryName = resultSet.getString(1);
        }
        statementForFindCountryName.close();
        resultSet.close();
        return countryName;
    }

}
