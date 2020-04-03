package integration;

import daolayer.model.Resume;
import daolayer.query.impls.SelectQueryBuilder;
import daolayer.query.Predicate;
import daolayer.query.impls.WherePredicateBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.sql.*;
import java.util.ResourceBundle;

@RunWith(JUnit4.class)
public class IntegrationTest {

    private static final String DB_PROPERTIES_NAME = "db";
    private static final String USER_KEY = "user";
    private static final String PASSWORD_KEY = "password";
    private static final String URL_KEY = "url";
    private static final String DRIVER_KEY = "driver";
    private static String url;
    private static String user;
    private static String password;
    private Connection connection;

    @BeforeClass
    public static void initDb() throws ClassNotFoundException {
        ResourceBundle dbSettings = ResourceBundle.getBundle(DB_PROPERTIES_NAME);

        registerDriver(dbSettings);
        setInfoForDbConnection(dbSettings);
    }

    @Before
    public void retrieveConnection() throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
    }

    @After
    public void closeConnection() throws SQLException {
        connection.close();
    }

    @Test
    public void shouldSelectResumesByNameAnSurnameAndSecondName() throws SQLException {
        Predicate firstTestPredicate = createFirstTestPredicate();

        String sqlQuery = new SelectQueryBuilder()
                .select(Resume.class)
                .where(firstTestPredicate)
                .build()
                .toString();

        ResultSet resultSet = getResultSetFrom(sqlQuery);

    }

    @Test
    public void shouldSelectResumesBySurnameOrGender() throws SQLException {
        Predicate secondTestPredicate = createSecondTestPredicate();

        String sqlQuery = new SelectQueryBuilder()
                .select(Resume.class)
                .where()
                .equals("name", "surname")
                .build();

        ResultSet resultSet = getResultSetFrom(sqlQuery);

    }

    private static void registerDriver(final ResourceBundle dbSettings) throws ClassNotFoundException {
        String driverClassName = dbSettings.getString(DRIVER_KEY);
        Class.forName(driverClassName);
    }

    private static void setInfoForDbConnection(final ResourceBundle dbSettings) {
        url = dbSettings.getString(URL_KEY);
        user = dbSettings.getString(USER_KEY);
        password = dbSettings.getString(PASSWORD_KEY);
    }

    private ResultSet getResultSetFrom(final String sqlQuery) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(sqlQuery);
    }


    private Predicate createFirstTestPredicate() {
        return new WherePredicateBuilder()
                .equals(Column.name(), "Мария")
                .and()
                .equals("surname", "Морская")
                .and()
                .equals("second_name", "Васильевна")
                .build();
    }

    private Predicate createSecondTestPredicate() {
        return new WherePredicateBuilder()
                .like("surname", "%ов")
                .or()
                .equals("gender", "female")
                .build();
    }

}
