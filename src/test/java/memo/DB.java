package memo;

import org.junit.jupiter.api.*;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DB {
    ResultSet rs;
    ResultSetMetaData md;

    @BeforeEach
    void setUp() throws SQLException {
        rs = DriverManager.getConnection(Datas.urlDB, Datas.usernameDB, Datas.passDB).
                createStatement().executeQuery(Datas.query_getlastid);
        md = rs.getMetaData();
    }

    @DisplayName("checking last added user")
    @Test
    void getUserWithlastId() throws SQLException {
        rs.next();
        Map<String, Object> map = new HashMap<>();
        for (int i = 1; i <= 3; i++) {
            map.put(md.getColumnName(i), rs.getObject(i));
        }
        System.out.println(map);
        Assertions.assertEquals(Datas.createdName, map.get("full_name"));
        Assertions.assertEquals(Datas.createdmail, map.get("email"));
    }

    @AfterEach
    void tearDown() throws SQLException {
        rs.close();
    }

}
