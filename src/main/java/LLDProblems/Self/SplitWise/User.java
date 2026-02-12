package LLDProblems.Self.SplitWise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class User {
    private final String id;
    private final String name;
    private final String email;
    private final String phone;

    public User(String id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
}

abstract class Split {
    private final User user;
    private double amount;

    public Split(User user) {
        this.user = user;
    }

    public User getUser() { return user; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
}

class EqualSplit extends Split {
    public EqualSplit(User user) {
        super(user);
    }
}

class ExactSplit extends Split {
    public ExactSplit(User user, double amount) {
        super(user);
        setAmount(amount);
    }
}

class PercentageSplit extends Split {
    private final double percent;

    public PercentageSplit(User user, double percent) {
        super(user);
        this.percent = percent;
    }

    public double getPercent() { return percent; }
}

class Expense {
    private final String id;
    private final String description;
    private final double totalAmount;
    private final User paidBy;
    private final List<Split> splits;

    public Expense(String id, String description, double totalAmount,
                   User paidBy, List<Split> splits) {
        this.id = id;
        this.description = description;
        this.totalAmount = totalAmount;
        this.paidBy = paidBy;
        this.splits = splits;
    }

    public double getTotalAmount() { return totalAmount; }
    public User getPaidBy() { return paidBy; }
    public List<Split> getSplits() { return splits; }
}

class Group {
    private final String id;
    private final String name;
    private final List<User> members = new ArrayList<>();
    private final List<Expense> expenses = new ArrayList<>();

    public Group(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addMember(User user) { members.add(user); }
    public void addExpense(Expense exp) { expenses.add(exp); }

    public List<User> getMembers() { return members; }
    public List<Expense> getExpenses() { return expenses; }

    public String getId() {
        return id;
    }
}

interface SplitStrategy {
    void validateAndApply(Expense expense);
}

class EqualSplitStrategy implements SplitStrategy {
    @Override
    public void validateAndApply(Expense expense) {
        int count = expense.getSplits().size();
        double share = expense.getTotalAmount() / count;

        for (Split split : expense.getSplits()) {
            split.setAmount(share);
        }
    }
}

class ExactSplitStrategy implements SplitStrategy {
    @Override
    public void validateAndApply(Expense expense) {
        double sum = 0;

        for (Split split : expense.getSplits()) {
            sum += split.getAmount();
        }

        if (Math.abs(sum - expense.getTotalAmount()) > 0.01) {
            throw new RuntimeException("Exact split does not match total");
        }
    }
}

class PercentageSplitStrategy implements SplitStrategy {

    @Override
    public void validateAndApply(Expense expense) {
        double totalPercent = 0;

        for (Split split : expense.getSplits()) {
            totalPercent += ((PercentageSplit) split).getPercent();
        }

        if (Math.abs(totalPercent - 100) > 0.01) {
            throw new RuntimeException("Percentage must sum to 100");
        }

        for (Split split : expense.getSplits()) {
            PercentageSplit ps = (PercentageSplit) split;
            split.setAmount(expense.getTotalAmount() * ps.getPercent() / 100);
        }
    }
}

class BalanceSheet {

    private final Map<String, Map<String, Double>> sheet = new HashMap<>();

    public void updateBalance(User paidBy, List<Split> splits) {

        for (Split split : splits) {
            String owes = split.getUser().getId();
            String paidTo = paidBy.getId();
            double amount = split.getAmount();

            sheet.putIfAbsent(owes, new HashMap<>());
            sheet.putIfAbsent(paidTo, new HashMap<>());

            sheet.get(owes).put(paidTo,
                    sheet.get(owes).getOrDefault(paidTo, 0.0) + amount);

            sheet.get(paidTo).put(owes,
                    sheet.get(paidTo).getOrDefault(owes, 0.0) - amount);
        }
    }

    public void showBalances(Map<String, User> userMap) {
        System.out.println("----- BALANCES -----");
        for (String u1 : sheet.keySet()) {
            for (String u2 : sheet.get(u1).keySet()) {
                double amt = sheet.get(u1).get(u2);
                if (amt > 0) {
                    System.out.println(
                            userMap.get(u1).getName() + " owes " +
                                    amt + " to " +
                                    userMap.get(u2).getName()
                    );
                }
            }
        }
    }
}

class ExpenseManager {

    private final Map<String, User> userMap = new HashMap<>();
    private final Map<String, Group> groupMap = new HashMap<>();
    private final BalanceSheet balanceSheet = new BalanceSheet();

    public void addUser(User u) {
        userMap.put(u.getId(), u);
    }

    public void addGroup(Group g) {
        groupMap.put(g.getId(), g);
    }

    public void addExpense(String groupId, Expense expense, SplitStrategy strategy) {
        strategy.validateAndApply(expense);

        Group g = groupMap.get(groupId);
        g.addExpense(expense);

        balanceSheet.updateBalance(expense.getPaidBy(), expense.getSplits());
    }

    public void showBalances() {
        balanceSheet.showBalances(userMap);
    }
}