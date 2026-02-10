package examples.example_4_partials_curried.functionalVsImperativ;


import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


public class EmployeeFns {

    record Task(String taskId, String description, int estimatedHours) {}
    record Project(String projectId, List<Task> tasks) {}
    record Employee(String employeeId, List<Project> projects) {}
    record Pair(Employee a, List<Task> b) {}

    private List<Employee> employees = List.of(
            new Employee("E1", List.of(
                    new Project("P1",
                            List.of(
                                    new Task("T1541", "Setup environment", 2),
                                    new Task("T2235", "Develop features", 5))
                    ),
                    new Project("P2", List.of(new Task("T3", "Prepare release", 1)))
            )),
            new Employee("E2", List.of(
                    new Project("P1",
                            List.of(
                                    new Task("T1021", "Migrate database", 12),
                                    new Task("T4162", "Run tests", 4))
                    ),
                    new Project("P3", List.of(new Task("T5", "Documentation", 3)))
            ))
    );

    /**
     * Shows different imparative methods vs functional style with streams.
     * Notice: Even the streams variants can be hard to read as they grow.
     */
    void employeeFilter() {

        Map<Employee, List<Task>> employeeTasksMap = new HashMap<>();

        // Populate the map
        for (Employee employee : employees) {
            List<Task> tasks = new ArrayList<>();
            for (Project project : employee.projects()) {
                for (Task task : project.tasks()) {
                    if (task.estimatedHours() >= 2) {
                        tasks.add(task);
                    }
                }
            }
            tasks.sort((t1, t2) -> Integer.compare(t2.estimatedHours(), t1.estimatedHours()));
            employeeTasksMap.put(employee, tasks);
        }



        Map<Employee, List<Task>> employeeTasksMap2 = employees.stream()
                .flatMap(employee -> employee.projects().stream()
                        .flatMap(project -> project.tasks().stream())
                        .filter(task -> task.estimatedHours() >= 2)  // Filter tasks with at least 2 hours estimated
                        .map(task -> new AbstractMap.SimpleEntry<>(employee, task)))  // Map each task to an entry of its employee
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,  // Grouping by employee
                        Collectors.mapping(Map.Entry::getValue,  // Mapping to get only the task
                                Collectors.collectingAndThen(
                                        Collectors.toList(),
                                        list -> list.stream()  // Additional stream to sort the tasks
                                                .sorted(Comparator.comparingInt(Task::estimatedHours).reversed())
                                                .collect(Collectors.toList())
                                )
                        )
                ));

        Map<Employee, List<Task>> employeeTasksMap3 = employees.stream()
                // Map each employee to a stream of their tasks
                .collect(Collectors.toMap(
                        Function.identity(),
                        employee -> employee.projects().stream()
                                .flatMap(project -> project.tasks().stream())  // Flatten all tasks across projects
                                .filter(task -> task.estimatedHours() >= 2)  // Filter tasks with at least 2 hours estimated
                                .sorted(Comparator.comparingInt(Task::estimatedHours).reversed())  // Sort tasks by estimated hours descending
                                .collect(Collectors.toList())  // Collect tasks into a list
                ));


        List<Pair> employeeTaskPairs = employees
                .stream()
                .map(employee -> new Pair(
                        employee,
                        employee.projects().stream()
                                .flatMap(project -> project.tasks().stream())
                                .filter(task -> task.estimatedHours() >= 2)
                                .sorted(Comparator.comparingInt(Task::estimatedHours).reversed())
                                .toList()
                ))
                .toList();



        employeeTasksMap.forEach((employee, tasks) -> {
            System.out.println("Employee ID: " + employee.employeeId());
            tasks.forEach(task -> System.out.println("  Task ID: " + task.taskId() +
                    ", Description: " + task.description() +
                    ", Estimated Hours: " + task.estimatedHours()));
        });


        employeeTaskPairs.forEach(pair -> {
            System.out.println("Employee ID: " + pair.a().employeeId());
            pair.b().forEach(task -> System.out.println("  Task ID: " + task.taskId() +
                    ", Description: " + task.description() +
                    ", Estimated Hours: " + task.estimatedHours()));
        });
    }

    public static void main(String[] args) {
        new EmployeeFns().employeeFilter();
    }
}
