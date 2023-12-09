package dz.reflectionClass;

import dz.Animals;
import dz.Cat;
import dz.Dog;
import dz.ListAnimals;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ReflectionMethod {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        Class<?> animal = Class.forName("dz.ListAnimals");
        Method[] methods = animal.getMethods();
        for (Method item: methods) {
            System.out.println(item.getName());
        }
        List<Animals> listAnimal = new ArrayList<>();
        listAnimal.add(new Cat("Cat1",2,"Oriental1"));

        Method method = animal.getMethod("addAnimal2", Animals.class);
        System.out.println(method.getName());
        System.out.println("Параметры метода и типы возвращаемых значений");
        Class[] classes = method.getParameterTypes();
        for (Class item: classes) {
            System.out.println(item.getName());
        }

        System.out.println("Вызов метода с помощью Java рефлексии");
        Method method2 = animal.getDeclaredMethod("addAnimal2", Animals.class);
        ListAnimals listAnimals = new ListAnimals<>();
        Dog dog = new Dog("Dog1",3,"Voice1");
        method2.invoke(listAnimals ,dog);
        System.out.println(listAnimals.getAnimals().get(0));

        System.out.println("Вызов метода с помощью Java рефлексии в поле List");
        ListAnimals listAnimals1 = new ListAnimals<>();
        listAnimals1.addAnimal();
        Class<?> test = listAnimals1.getClass();
        Field field = test.getField("animals");
        test = field.getClass();
        Method[] method1 = test.getMethods();
        for (Method item: method1) {
            System.out.println(item.getName());
        }
        List<Animals> animals = new ArrayList<>();
        animals.add(dog);
        System.out.println("///////////////////////////////////////////////////////");
        Class<?> fieldClass = animals.getClass();
        System.out.println("///////////////////////////////////////////////////////");
        Method fieldMetod = fieldClass.getDeclaredMethod("get", int.class);
        fieldMetod.invoke(animals, 0);
        System.out.println("///////////////////////////////////////////////////////");
        System.out.println(animals.get(0));



        Class<?> meinClass = listAnimals1.getClass();
        Method method3 = meinClass.getDeclaredMethod("getAnimal", Integer.class);
        System.out.println(method3.invoke(listAnimals1, 0));

        ArrayList<String> listString = new ArrayList<>();
        Class<?> listClass = listString.getClass();
        Method listMetod = listClass.getDeclaredMethod("add", Object.class);
        listMetod.invoke(listString, "text");
        System.out.println(listString.get(0));






    }
}










































