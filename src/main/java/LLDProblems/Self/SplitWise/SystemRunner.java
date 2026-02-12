package LLDProblems.Self.SplitWise;

import java.util.List;

public class SystemRunner {

    public static void main(String[] args) {

        ExpenseManager manager = new ExpenseManager();

        User u1 = new User("U1", "Saurav", "s@test.com", "111");
        User u2 = new User("U2", "Ravi", "r@test.com", "222");
        User u3 = new User("U3", "Neha", "n@test.com", "333");

        manager.addUser(u1);
        manager.addUser(u2);
        manager.addUser(u3);

        Group g = new Group("G1", "Goa Trip");
        g.addMember(u1);
        g.addMember(u2);
        g.addMember(u3);
        manager.addGroup(g);

        // Equal Expense
        List<Split> s1 = List.of(new EqualSplit(u1), new EqualSplit(u2), new EqualSplit(u3));
        Expense e1 = new Expense("E1", "Hotel", 300, u1, s1);
        manager.addExpense("G1", e1, new EqualSplitStrategy());

        // Percentage Expense
        List<Split> s2 = List.of(
                new PercentageSplit(u1, 40),
                new PercentageSplit(u2, 20),
                new PercentageSplit(u3, 40)
        );
        Expense e2 = new Expense("E2", "Cab", 500, u2, s2);
        manager.addExpense("G1", e2, new PercentageSplitStrategy());

        manager.showBalances();
    }
}
