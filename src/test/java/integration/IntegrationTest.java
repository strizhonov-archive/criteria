package integration;

import daolayer.model.Column;
import daolayer.model.Resume;
import daolayer.query.Predicate;
import daolayer.query.impl.SelectQueryBuilder;
import daolayer.query.impl.predicate.WhereBuilder;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;

import java.lang.reflect.Field;
import java.sql.*;
import java.sql.Date;
import java.util.*;

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
        Assert.assertEquals(1, foundResumes.size());

        Resume found = foundResumes.get(0);
        Assert.assertEquals("Мария", found.getName());
        Assert.assertEquals("Морская", found.getSurname());
        Assert.assertEquals("Васильевна", found.getSecondName());
        Assert.assertEquals("female", found.getGender());

        java.util.Date expectedBirthday = new GregorianCalendar(1999, Calendar.JULY, 11).getTime();
        Assert.assertEquals(expectedBirthday, found.getBirthday());

    }


    @Test
    public void bShouldSelectResumesBySurnamePatternOrGender() throws SQLException {
        String sql = createBTestQuery();
        List<Resume> foundResumes = getResumesFromQuery(sql);
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
        long id = rs.getLong(RESUME_FIELDS_JAVA_SQL.get("id"));
        String name = rs.getString(RESUME_FIELDS_JAVA_SQL.get("name"));
        String surname = rs.getString(RESUME_FIELDS_JAVA_SQL.get("surname"));
        String secondName = rs.getString(RESUME_FIELDS_JAVA_SQL.get("secondName"));
        Date birthday = rs.getDate(RESUME_FIELDS_JAVA_SQL.get("birthday"));
        String gender = rs.getString(RESUME_FIELDS_JAVA_SQL.get("gender"));

        return new Resume(id, name, surname, secondName, birthday, gender, new HashSet<>(), new HashSet<>());
    }


    private static void storeFieldData(final Field fieldToStore) {
        Column currentAnnotation = fieldToStore.getAnnotation(Column.class);
        if (currentAnnotation != null) {
            RESUME_FIELDS_JAVA_SQL.put(fieldToStore.getName(), currentAnnotation.name());
        }
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
