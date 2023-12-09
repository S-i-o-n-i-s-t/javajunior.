package dz.reflectionClass;

import dz.Animals;
import dz.Dog;
import dz.ListAnimals;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class ReflectionConstructor {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        ListAnimals animals = new ListAnimals();
        Class<?> listAnimals = Class.forName("dz.ListAnimals");
        System.out.println(listAnimals.getSimpleName());
        System.out.println("Получаем конструкторы класса");
        Constructor<?>[] constructors = listAnimals.getConstructors();
        System.out.println(constructors.length);

        List<Animals> list = new ArrayList<>();
        list.add(new Dog("dOG",12,"ГАВ"));
       // Constructor<?> constructors2 = constructors[1];
       // System.out.println(constructors2.getName());
        System.out.println("Получаем параметры конструктора");
        Class[] parametr = constructors[1].getParameterTypes(); // тип параметра
        for (Class<?> item: parametr) {
            System.out.println(item.getName());
        }
        //Создание объектов с помощью конструкторов
      //  System.out.println(constructors[1].newInstance(""))));;


    }
}
