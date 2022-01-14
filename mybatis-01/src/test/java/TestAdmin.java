import com.bassomillo.entity.Admin;
import com.bassomillo.entity.User;
import com.bassomillo.mapper.AdminMapper;
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
public class TestAdmin {
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
            AdminMapper mapper = session.getMapper(AdminMapper.class);
            List<Admin> allAdmins = mapper.findAllAdmins();
            log.debug("result is [{}]", allAdmins);
        }
    }

    @Test
    public void testSqlSession1() throws IOException {

//        System.out.println(sqlSessionFactory);
//        log.debug("sqlSessionFactory is [{}]", sqlSessionFactory);
        try (SqlSession session = sqlSessionFactory.openSession()) {
            AdminMapper mapper = session.getMapper(AdminMapper.class);
            int jerry = mapper.saveAdmin(new Admin(4, "jerry", "234"));
            log.debug("result is [{}]", jerry);
            session.commit();
        }
    }
}
