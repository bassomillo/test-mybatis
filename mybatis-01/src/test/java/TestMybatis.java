import com.bassomillo.entity.User;
import com.bassomillo.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Slf4j
public class TestMybatis {
//    public static final Logger logger = LoggerFactory.getLogger(TestMybatis.class);
    SqlSessionFactory sqlSessionFactory = null;
    @Before
    public void before() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
    }


    @Test
    public void testSqlSession() throws IOException {

//        System.out.println(sqlSessionFactory);
//        log.debug("sqlSessionFactory is [{}]", sqlSessionFactory);
        try (SqlSession session = sqlSessionFactory.openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            List<User> userList = mapper.UserSelect();
            log.debug("result is [{}]", userList);
        }
    }

    @Test
    public void testSqlSession1() throws IOException {

//        System.out.println(sqlSessionFactory);
//        log.debug("sqlSessionFactory is [{}]", sqlSessionFactory);
        try (SqlSession session = sqlSessionFactory.openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            User user = mapper.UserSelectById(1);
            log.debug("result is [{}]", user);
        }
    }
    @Test
    public void testSqlSession2() throws IOException {

//        System.out.println(sqlSessionFactory);
//        log.debug("sqlSessionFactory is [{}]", sqlSessionFactory);
        try (SqlSession session = sqlSessionFactory.openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            User user = mapper.UserSelect1("john","123");
            log.debug("result is [{}]", user);
        }
    }

    @Test
    public void testSqlSession3() throws IOException {

//        System.out.println(sqlSessionFactory);
//        log.debug("sqlSessionFactory is [{}]", sqlSessionFactory);
        try (SqlSession session = sqlSessionFactory.openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            User user1 = new User(1, "bob", "234");
            User user = mapper.UserSelect2(user1);
            log.debug("result is [{}]", user);
        }
    }

    @Test
    public void testSqlSession4() throws IOException {

//        System.out.println(sqlSessionFactory);
//        log.debug("sqlSessionFactory is [{}]", sqlSessionFactory);
        try (SqlSession session = sqlSessionFactory.openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            User user1 = new User(1, "bob", "234");
            List<User> userList = mapper.UserSelectByUsername("bob");
            log.debug("result is [{}]", userList);
        }
    }

    @Test
    public void testSqlSession5() throws IOException {

//        System.out.println(sqlSessionFactory);
//        log.debug("sqlSessionFactory is [{}]", sqlSessionFactory);
        try (SqlSession session = sqlSessionFactory.openSession()) {
            try {
                UserMapper mapper = session.getMapper(UserMapper.class);
                User user1 = new User(5, "bob1", "2345");
                Integer integer = mapper.InsertUser(user1);
                log.debug("result is [{}]", integer);
                int i = 1/0;
                session.commit();
            }catch (Exception e){
                log.error("error is", e);
                session.rollback();
            }

        }
    }

    @Test
    public void testSqlSession6() throws IOException {

//        System.out.println(sqlSessionFactory);
//        log.debug("sqlSessionFactory is [{}]", sqlSessionFactory);
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
                UserMapper mapper = session.getMapper(UserMapper.class);
                User user1 = new User(2, "bob2", "2345");
                Integer integer = mapper.UpdateUser(user1);
                log.debug("result is [{}]", integer);
                session.commit();


        }
    }

    @Test
    public void testSqlSession7() throws IOException {

//        System.out.println(sqlSessionFactory);
//        log.debug("sqlSessionFactory is [{}]", sqlSessionFactory);
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            User user1 = new User(2, "bob2", "2345");
            Integer integer = mapper.DeleteUser(3);
            log.debug("result is [{}]", integer);
            session.commit();


        }
    }
}
