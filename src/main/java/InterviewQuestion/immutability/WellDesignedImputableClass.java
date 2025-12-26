package InterviewQuestion.immutability;


import java.util.List;
import java.util.ArrayList;
import java.util.Date;

final class WellDesignedImputableClass {
    private final String name;
    private final int age;
    private final List<String> hobbies;
    private final Date birthDate;

    public WellDesignedImputableClass(String name, int age, List<String> hobbies, Date birthDate) {
        this.name = name;
        this.age = age;
        this.hobbies = new ArrayList<>(hobbies); // Defensive copy
        this.birthDate = new Date(birthDate.getTime()); // Defensive copy
    }

    public String getName() { return name; }
    public int getAge() { return age; }

    public List<String> getHobbies() {
        return new ArrayList<>(hobbies); // Defensive copy
    }

    public Date getBirthDate() {
        return new Date(birthDate.getTime()); // Defensive copy
    }
}

