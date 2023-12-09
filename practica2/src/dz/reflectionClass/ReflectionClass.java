package dz.reflectionClass;

import dz.Dog;
import dz.ListAnimals;

import java.lang.reflect.Modifier;
import java.util.concurrent.Callable;

public class ReflectionClass {
    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException, ClassNotFoundException {
        System.out.println("Hello world!");
        ListAnimals animals = new ListAnimals();
        animals.addAnimal();
//        for (int i = 0; i < animals.getAnimals().size(); i++) {
//            System.out.println(animals.getAnimals().get(i));
//        }

        Class<?> listAnimalTest = ListAnimals.class;
        Class<?> listAnimalTest2 = Class.forName("dz.ListAnimals");
        System.out.println(listAnimalTest.getName());
        System.out.println(listAnimalTest2.getSimpleName()); // название класса без имени пакета
        System.out.println("Модификаторы доступа");
        System.out.println(listAnimalTest.getModifiers());// модификатор доступа
        System.out.println("Является ли абстрактным");
        System.out.println(Modifier.isAbstract(listAnimalTest.getModifiers()));
        System.out.println("Является ли FINAL");
        System.out.println(Modifier.isFinal(listAnimalTest.getModifiers()));
        // итд
//        System.out.println(Modifier.isInterface(listAnimalTest.getModifiers()));
//        System.out.println(Modifier.isNative(listAnimalTest.getModifiers()));
//        System.out.println(Modifier.isPrivate(listAnimalTest.getModifiers()));
//        System.out.println(Modifier.isProtected(listAnimalTest.getModifiers()));
//        System.out.println(Modifier.isPublic(listAnimalTest.getModifiers()));
//        System.out.println(Modifier.isStatic(listAnimalTest.getModifiers()));
//        System.out.println(Modifier.isStrict(listAnimalTest.getModifiers()));
//        System.out.println(Modifier.isSynchronized(listAnimalTest.getModifiers()));
//        System.out.println(Modifier.isTransient(listAnimalTest.getModifiers()));
//        System.out.println(Modifier.isVolatile(listAnimalTest.getModifiers()));
        System.out.println("Получение информации о пакете");
        Package pack = listAnimalTest.getPackage();
        System.out.println(pack.getName());
        System.out.println("Зная объект Class, мы можем получить доступ к его суперклассу");
        Class<?> dog = Class.forName("dz.Dog");
        Class<?> animal = dog.getSuperclass();
        System.out.println(animal.getName());
        System.out.println("Реализованные интерфейсы (интерфейсов тут нет)");
        Class<?>[] interfaces = listAnimalTest.getInterfaces();


    }
}
