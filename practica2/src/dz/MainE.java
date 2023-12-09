package dz;
//Задача 1:
//        Создайте абстрактный класс "Animal" с полями "name" и "age".
//        Реализуйте два класса-наследника от "Animal" (например, "Dog" и "Cat") с уникальными полями и методами.
//        Создайте массив объектов типа "Animal" и с использованием Reflection API выполните следующие действия:
//        Выведите на экран информацию о каждом объекте.
//        Вызовите метод "makeSound()" у каждого объекта, если такой метод присутствует.

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainE {
    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        System.out.println("Hello world!");
        // Создаем список наследников Animals
        ListAnimals animals = new ListAnimals();
        animals.addAnimal();
        Class<?> clazz = animals.getClass();
        Field field = clazz.getField("animals"); // Получаем доступ к полю

        Class<?> clazz3 = field.get(animals).getClass(); // Создаем класс поля
        Method[] methods3 = clazz3.getMethods(); // список методов для проверки, есть ли нужный нам метод
        for (Method item: methods3) {
            if (item.getName().equals("size")){System.out.println(item.getName());}
            if (item.getName().equals("get")){System.out.println(item.getName());}
        }
        Method methodField = clazz3.getMethod("size"); // Метод для получения длинны поля List
        Method methodField2 = clazz3.getMethod("get", int.class); // Метод для чтения List поля
        for (int i = 0; i < (Integer) methodField.invoke(field.get(animals)); i++){
            System.out.println(methodField2.invoke(field.get(animals),i)); // выводим значение в консоль
            Class<?> clazz2 = methodField2.invoke(field.get(animals),i).getClass(); // Делаем Reflection Сlass из каждого элемента поля List
            Method[] methods1 = clazz2.getDeclaredMethods(); // Смотрим методы элементов поля List
            for (Method item: methods1) {
                if (item.getName().equals("makeSound")){ // Проверяем на наличие нужного метода
                    Method method1 = clazz2.getDeclaredMethod("makeSound");
                    method1.invoke(methodField2.invoke(field.get(animals),i)); // Применяем метод
                }
            }
        }
    }
}