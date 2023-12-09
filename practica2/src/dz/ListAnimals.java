package dz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListAnimals <E extends Animals>{
    public List<E> animals = new ArrayList<>();
    public void addAnimal(){
        animals.add((E) new Cat("Cat1",2,"Oriental1"));
        animals.add((E) new Dog("Dog1",3,"Voice1"));
        animals.add((E) new Cat("Cat2",4,"Oriental2"));
        animals.add((E) new Dog("Dog2",5,"Voice2"));
    }

    public ListAnimals(List<E> animals) {
        this.animals = animals;
    }
    public ListAnimals() {
        this.animals = animals;
    }
    public void addAnimal2(E animals){
        this.animals.add(animals);
    }
    public E getAnimal(int index){
        return animals.get(index);
    }

    public List<E> getAnimals() {
        return animals;
    }
}
