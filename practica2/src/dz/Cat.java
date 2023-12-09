package dz;

public class Cat extends Animals{
    public String breed;
    public Cat(String name, int age, String breed) {
        super.name = name;
        super.age = age;
        this.breed = breed;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "breed='" + breed + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
    public void makeSound(){
        System.out.println("makeSound метод - есть в этом классе");
    }

    // region Геттер/сеттер
    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }
    // endregion
}
