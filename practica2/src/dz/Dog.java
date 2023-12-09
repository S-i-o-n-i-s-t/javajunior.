package dz;

public class Dog extends Animals{
    public String command;
    public Dog(String name, int age, String command) {
        super.name = name;
        super.age = age;
        this.command = command;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "command='" + command + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    // region Геттер/сеттер
    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
    // endregion
}
