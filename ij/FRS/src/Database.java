import java.sql.*;

public class Database {
    //connecting to the database
    public Connection connection;

    //the Query class instance
    public Query query;

    /**
     * The Database class constructor
     * @param db
     * @param userName
     * @param password
     * @throws SQLException
     */
    public Database(String db, String userName, String password) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/" + db, userName, password);
    }

    //class attribute
    //class methods

    /**
     *
     * @param query
     * @param params
     * @return
     * @throws SQLException
     */
    private int query(String query, Object[] params) throws SQLException{
        PreparedStatement ps = connection.prepareStatement(query);
        if (params != null){
            int index = 1;
            for(Object param : params){
                ps.setObject(index, param);
                index++;
            }
        }
        return ps.executeUpdate();
    }

    //class attribute
    //class methods

    /**
     * Removes data from a database table
     * @param table
     * @param requirement
     * @param param
     * @return
     * @throws SQLException
     */
    public int delete(String table, String requirement, Object[] param) throws SQLException{
        query = new Query();
        query.delete(table)
                .where(requirement);
        return query(query.getQuery(), param);
    }

    //class attribute
    //class methods

    /**
     * Inserts one row to a database table
     * @param table
     * @param params
     * @return
     * @throws java.sql.SQLException
     */
    public int insert(String table, Object[] params) throws SQLException{
        query = new Query();
        query.insert(table)
                .values(params);
        return query(query.getQuery(), params);
    }
    public int insert(String table, Object[] columns, Object[] params) throws SQLException{
        query = new Query();
        query.insert(table, columns).values(params);
        return query(query.getQuery(), params);
    }

    //class attribute
    //class methods

    /**
     * Updates data stored in a database table
     * @param table
     * @param columns
     * @param requirement
     * @param params
     * @return
     * @throws SQLException
     */
    public int update(String table, String[] columns, String requirement, Object[] params) throws SQLException{
        query = new Query();

        query.update(table)
                .set(columns)
                .where(requirement);

        return query(query.getQuery(), params);
    }

    //class attribute
    //class methods
    /**
     * Returns data from a table
     * @param table
     * @param columns
     * @param params
     * @return
     * @throws SQLException
     */
    public ResultSet select(String table, Object[] columns, Object[] params) throws SQLException{
        return this.select(table, columns, "", params);
    }

    public ResultSet count(String table) throws SQLException{
        query = new Query();
        query.count(table);

        PreparedStatement ps = connection.prepareStatement(query.getQuery());

        ResultSet rs = ps.executeQuery();
        return rs;
    }

    public ResultSet count(String table, String column, String value) throws SQLException{
        query = new Query();
        query.count(table, column, value);
        PreparedStatement ps = connection.prepareStatement(query.getQuery());
        ResultSet rs = ps.executeQuery();
        return rs;
    }

    /**
     * Returns data from a table
     * @param table
     * @param columns
     * @param requirement
     * @param params
     * @return
     * @throws SQLException
     */
    public ResultSet select(String table, Object[] columns, String requirement, Object[] params) throws SQLException{
        query = new Query();
        query.select(columns)
                .from(table)
                .where(requirement);

        PreparedStatement ps = connection.prepareStatement(query.getQuery());
        if(params != null){
            int index = 1;
            for(Object param : params){
                ps.setObject(index, param);
                index++;
            }
        }

        ResultSet rs = ps.executeQuery();
        return rs;
    }
}

