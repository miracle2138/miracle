package com.liyao;

/**
 * Hello world!
 */
public class App {
//    public static void main(String[] args) {
//        ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
//                .setJdbcUrl("jdbc:h2:mem:flowable;DB_CLOSE_DELAY=-1")
//                .setJdbcUsername("")
//                .setJdbcPassword("")
//                .setJdbcDriver("org.h2.Driver")
//                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
//        ProcessEngine processEngine = cfg.buildProcessEngine();
//
//        RepositoryService repositoryService = processEngine.getRepositoryService();
//        Deployment deployment = repositoryService.createDeployment()
//                .addClasspathResource("holiday-request.bpmn20.xml")
//                .deploy();
//
//        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
//                .deploymentId(deployment.getId())
//                .singleResult();
//        System.out.println("Found process definition : " + processDefinition.getName());
//
//        Map<String, Object> variables = new HashMap<String, Object>();
//        variables.put("employee", "employee123");
//        variables.put("nrOfHolidays", 12);
//        variables.put("description", "description");
//        RuntimeService runtimeService = processEngine.getRuntimeService();
//        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("holidayRequest", variables);
//
//        TaskService taskService = processEngine.getTaskService();
//        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("managers").list();
//        System.out.println("You have " + tasks.size() + " tasks:");
//        for (int i = 0; i < tasks.size(); i++) {
//            System.out.println((i + 1) + ") " + tasks.get(i).getName());
//        }
//        Task task = tasks.get(0);
//        Map<String, Object> processVariables = taskService.getVariables(task.getId());
//        System.out.println(processVariables.get("employee") + " wants " +
//                processVariables.get("nrOfHolidays") + " of holidays. Do you approve this?");
//
//        variables = new HashMap<String, Object>();
//        variables.put("approved", true);
//        taskService.complete(task.getId(), variables);
//    }
}
