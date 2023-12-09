package dz.reflectionClass;

import dz.ListAnimals;

import java.lang.reflect.Field;
import java.util.List;

public class ReflectionField {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        ListAnimals listAnimals = new ListAnimals<>();
        Class<?> clazz = Class.forName("dz.ListAnimals");
        System.out.println("Доступ к полям класса");
        Field[] fields = clazz.getFields();
        for (Field item: fields) {
            System.out.println(item.getName());
        }
        System.out.println("Доступ к конкретному полю класса - смотрим тип класса");
        Field field = clazz.getField("animals");
        System.out.println(field.getAnnotatedType());

        System.out.println("Получаем и устанавливаем значения полей");
        ListAnimals listAnimals2 = new ListAnimals<>();// создаем экземпляр для подстановки
        listAnimals2.addAnimal();

        Object value = field.get(listAnimals2);
        field.set(listAnimals2, value);

        System.out.println(field.get(listAnimals));


    }
}
