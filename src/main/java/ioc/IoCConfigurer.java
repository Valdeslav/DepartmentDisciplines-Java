package ioc;

import java.util.HashMap;
import java.util.Map;

public class IoCConfigurer {
    public static void configure() throws IoCException {
        IoCContainer.registerFactory("java.sql.Connection", "pool.ConnectionFactory");

        Map<String, String> daoDependencies = new HashMap<>();
        daoDependencies.put("java.sql.Connection", "setConnection");
        IoCContainer.registerClass("dao.DisciplineDao", "dao.sqlImpl.DisciplineDaoSQLImpl", daoDependencies);
        IoCContainer.registerClass("dao.TopicDao", "dao.sqlImpl.TopicDaoSQLImpl", daoDependencies);

        Map<String, String> disciplineServiceDependencies = new HashMap<>();
        disciplineServiceDependencies.put("dao.DisciplineDao", "setDisciplineDao");
        IoCContainer.registerClass("service.DisciplineService", "service.DisciplineServiceImpl", disciplineServiceDependencies);

        Map<String, String> topicServiceDependencies = new HashMap<>();
        topicServiceDependencies.put("dao.TopicDao", "setTopicDao");
        IoCContainer.registerClass("service.TopicService", "service.TopicServiceImpl", topicServiceDependencies);
    }
}
