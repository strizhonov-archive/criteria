package integration;

import daolayer.model.Column;
import daolayer.model.Resume;
import daolayer.query.Predicate;
import daolayer.query.impl.SelectQueryBuilder;
import daolayer.query.impl.predicate.WhereBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

@RunWith(JUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class IntegrationTest {

    private static final String DB_PROPERTIES_NAME = "db";
    private static final String USER_KEY = "user";
    private static final String PASSWORD_KEY = "password";
    private static final String URL_KEY = "url";
    private static final String DRIVER_KEY = "driver";
    private static final Map<String, String> RESUME_FIELDS_JAVA_SQL = new HashMap<>();
    private static String url;
    private static String user;
    private static String password;
    private Connection connection;


    @Before
    public void retrieveConnection() throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
    }

    @After
    public void closeConnection() throws SQLException {
        connection.close();
    }


    @Test
    public void aShouldSelectResumesByNameAnSurnameAndSecondName() throws SQLException {
        String sql = createATestQuery();
        List<Resume> foundResumes = getResumesFromQuery(sql);

        Resume expected = new Resume.ResumeBuilder()
                .id(3)
                .name("Мария")
                .surname("Морская")
                .secondName("Васильевна")
                .birthday(new GregorianCalendar(1999, Calendar.JULY, 11).getTime())
                .gender("female")
                .contacts(new HashSet<>())
                .technologies(new HashSet<>())
                .build();

        Assert.assertTrue(foundResumes.contains(expected));

    }


    @Test
    public void bShouldSelectResumesBySurnamePatternOrGender() throws SQLException {
        String sql = createBTestQuery();
        List<Resume> foundResumes = getResumesFromQuery(sql);

        Resume expected = new Resume.ResumeBuilder()
                .id(1)
                .name("Петр")
                .surname("Петров")
                .secondName("Петрович")
                .birthday(new GregorianCalendar(1986, Calendar.DECEMBER, 12).getTime())
                .gender("male")
                .technologies(new HashSet<>())
                .contacts(new HashSet<>())
                .build();

        Assert.assertTrue(foundResumes.contains(expected));

        expected = new Resume.ResumeBuilder()
                .id(2)
                .name("Иван")
                .surname("Иванов")
                .secondName("Иванович")
                .birthday(new GregorianCalendar(1997, Calendar.APRIL, 4).getTime())
                .gender("male")
                .technologies(new HashSet<>())
                .contacts(new HashSet<>())
                .build();

        Assert.assertTrue(foundResumes.contains(expected));

        expected = new Resume.ResumeBuilder()
                .id(3)
                .name("Мария")
                .surname("Морская")
                .secondName("Васильевна")
                .birthday(new GregorianCalendar(1999, Calendar.JULY, 11).getTime())
                .gender("female")
                .contacts(new HashSet<>())
                .technologies(new HashSet<>())
                .build();

        Assert.assertTrue(foundResumes.contains(expected));
    }


    @BeforeClass
    public static void inflateFieldsMeta() {
        Field[] declaredFields = Resume.class.getDeclaredFields();
        for (Field current : declaredFields) {
            storeFieldData(current);
        }
    }


    @BeforeClass
    public static void initDb() throws ClassNotFoundException {
        ResourceBundle dbSettings = ResourceBundle.getBundle(DB_PROPERTIES_NAME);

        registerDriver(dbSettings);
        setInfoForDbConnection(dbSettings);
    }


    private String createATestQuery() {
        SelectQueryBuilder<Resume> queryBuilder = new SelectQueryBuilder<>(Resume.class);
        Predicate<Resume> firstTestPredicate = createFirstTestPredicate(queryBuilder.createWhereBuilder());

        return new SelectQueryBuilder<>(Resume.class)
                .where(firstTestPredicate)
                .build()
                .toString();
    }


    private String createBTestQuery() {
        SelectQueryBuilder<Resume> queryBuilder = new SelectQueryBuilder<>(Resume.class);
        Predicate<Resume> secondTestPredicate = createSecondTestPredicate(queryBuilder.createWhereBuilder());

        return queryBuilder
                .where(secondTestPredicate)
                .build()
                .toString();
    }


    private Predicate<Resume> createFirstTestPredicate(final WhereBuilder<Resume> whereBuilder) {

        return whereBuilder
                .equals("name", "Мария")
                .and()
                .equals("surname", "Морская")
                .and()
                .equals("secondName", "Васильевна")
                .build();
    }


    private Predicate<Resume> createSecondTestPredicate(final WhereBuilder<Resume> whereBuilder) {

        return whereBuilder
                .like("surname", "%ов")
                .or()
                .equals("gender", "female")
                .build();

    }


    private List<Resume> getResumesFromQuery(final String sql) throws SQLException {
        ResultSet resultSet = getResultSetFrom(sql);

        List<Resume> result = new ArrayList<>();

        while (resultSet.next()) {
            Resume compiled = compile(resultSet);
            result.add(compiled);
        }

        return result;
    }


    private ResultSet getResultSetFrom(final String sqlQuery) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(sqlQuery);
    }


    private Resume compile(final ResultSet rs) throws SQLException {
        return new Resume.ResumeBuilder()
                .id(rs.getLong(RESUME_FIELDS_JAVA_SQL.get("id")))
                .name(rs.getString(RESUME_FIELDS_JAVA_SQL.get("name")))
                .surname(rs.getString(RESUME_FIELDS_JAVA_SQL.get("surname")))
                .secondName(rs.getString(RESUME_FIELDS_JAVA_SQL.get("secondName")))
                .birthday(rs.getDate(RESUME_FIELDS_JAVA_SQL.get("birthday")))
                .gender(rs.getString(RESUME_FIELDS_JAVA_SQL.get("gender")))
                .technologies(new HashSet<>())
                .contacts(new HashSet<>())
                .build();
    }


    private static void storeFieldData(final Field fieldToStore) {
        Column currentAnnotation = fieldToStore.getAnnotation(Column.class);
        RESUME_FIELDS_JAVA_SQL.put(
                fieldToStore.getName(),
                currentAnnotation == null
                        ? fieldToStore.getName()
                        : currentAnnotation.name()
        );

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

}
