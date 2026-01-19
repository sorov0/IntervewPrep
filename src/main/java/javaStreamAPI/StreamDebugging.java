package javaStreamAPI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamDebugging {

    public static void main(String[] args) {




        /*
        Real-World Spring Boot Use Cases of Streams :

        //1. Filtering & Transforming API Responses
        @GetMapping("/orders")
        public List<OrderDTO> getOrders() {

            return orderRepository.findAll().stream()
                    .filter(order -> order.isActive())
                    .map(order -> new OrderDTO(order.getId(), order.getAmount()))
                    .collect(Collectors.toList());

        //2. Validation in Service Layer
            public void validateUsers(List<User> users) {

                boolean invalidUserExists = users.stream()
                        .anyMatch(u -> u.getAge() <= 0);

                if (invalidUserExists) {
                    throw new IllegalArgumentException("Invalid age found");
                }
            }

        //3. Aggregation for Dashboards (Very Common)
            public Map<String, Double> avgSalaryByDept() {

                return employeeRepository.findAll().stream()
                        .collect(Collectors.groupingBy(
                                Employee::getDept,
                                Collectors.averagingInt(Employee::getSalary)
                        ));
            }

            //4. Mapping Entity → DTO (Most Common Use)
            public List<EmployeeDTO> getEmployeeDTOs() {

                return employeeRepository.findAll().stream()
                        .map(emp -> new EmployeeDTO(
                                emp.getId(),
                                emp.getName(),
                                emp.getDept()
                        ))
                        .collect(Collectors.toList());
            }


            //5. Removing Duplicates Before Persistence
            public List<String> getUniqueEmails(List<User> users) {

                return users.stream()
                        .map(User::getEmail)
                        .distinct()
                        .collect(Collectors.toList());
            }

            //6. Authorization & Role Checks
            public boolean isAdmin(User user) {

                return user.getRoles().stream()
                        .anyMatch(role -> role.equals("ADMIN"));
            }

            //7. Logging Pipelines with peek() (Production-Safe)
            return users.stream()
                    .peek(u -> log.debug("Fetched user: {}", u.getId()))
                    .filter(User::isActive)
                    .peek(u -> log.debug("Active user: {}", u.getId()))
                    .map(UserDTO::from)
                    .collect(Collectors.toList());

            //8. Bulk Updates (Controlled)
            users.stream()
                    .filter(User::isInactive)
                    .forEach(user -> user.setStatus("ARCHIVED"));

            //9. Pagination + Streams
            List<User> page = users.stream()
                    .skip(pageNo * pageSize)
                    .limit(pageSize)
                    .collect(Collectors.toList());

            //10. Combining Multiple Repositories
            List<String> names =
                    Stream.concat(
                                    repo1.findAll().stream(),
                                    repo2.findAll().stream()
                            )
                            .map(User::getName)
                            .distinct()
                            .collect(Collectors.toList());


        //===============================================================================================


        PART 1️⃣ — Stream Debugging with peek()
        🔹 What is peek()?
        peek(Consumer<T>)

        Intermediate operation
        Used to observe elements flowing through the stream
        Does NOT modify data
        Executes only when a terminal operation is called

        📌 Think of peek() as a debugging tap in a pipeline.
        🔹 Why peek() Exists
        Streams are:
        Lazy
        Hard to debug step-by-step
        peek() lets you inspect values at each stage without breaking the pipeline.

        ✅ When peek() is ACCEPTABLE

✔ Logging
✔ Debugging
✔ Metrics
✔ Tracing pipeline behavior
         */

        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);

        //Shows how data transforms stage by stage
        nums.stream()
                .filter(n -> n % 2 == 0)
                .peek(n -> System.out.println("After filter: " + n))
                .map(n -> n * n)
                .peek(n -> System.out.println("After map: " + n))
                .collect(Collectors.toList());


        // Debugging Complex Streams
        // This is extremely useful when debugging wrong results.
        List<Employee> employees = new ArrayList<>();
        employees.stream()
                .peek(e -> System.out.println("Original: " + e))
                .filter(e -> e.getDept().equals("IT"))
                .peek(e -> System.out.println("After filter: " + e))
                .sorted(Comparator.comparingInt(Employee::getSalary).reversed())
                .peek(e -> System.out.println("After sort: " + e))
                .limit(3)
                .peek(e -> System.out.println("After limit: " + e))
                .map(Employee::getName)
                .collect(Collectors.toList());

        // Note: Each element flows one-by-one through entire pipeline
        // Each element flows through pipeline fully before next element



    }
}
