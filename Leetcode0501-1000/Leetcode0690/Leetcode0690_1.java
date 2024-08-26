package Leetcode0690;

import java.util.*;

class Employee {
    public int id;
    public int importance;
    public List<Integer> subordinates;
};
public class Leetcode0690_1 {
    public int getImportance(List<Employee> employees, int id) {
        Map<Integer, Employee> map = new HashMap<>();
        for (Employee employee : employees) {
            map.put(employee.id, employee);
        }
        Employee idee = map.get(id);
        if(idee == null) return 0;
        int ans = idee.importance;
        Queue<Employee> q = new ArrayDeque<>();
        q.offer(idee);
        while(!q.isEmpty()){
            Employee cur = q.poll();
            if(cur.subordinates == null) continue;
            for (int sub : cur.subordinates) {
                Employee next = map.get(sub);
                if(next != null) {
                    ans += next.importance;
                    q.offer(next);
                }
            }
        }
        return ans;
    }
}
