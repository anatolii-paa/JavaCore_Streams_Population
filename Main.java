import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 100; i++) {  //"создаем поппуляцию"
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())), //задаем случайное имя из массива
                    families.get(new Random().nextInt(families.size())), // аналогично фамилию
                    new Random().nextInt(100), //возраст
                    Sex.values()[new Random().nextInt(Sex.values().length)], //пол
                    Education.values()[new Random().nextInt(Education.values().length)]) //образование
            );
        }
        long underages = persons.stream().filter(person -> person.getAge() < 18).count(); //проверяем на совершеннолетие

        List<String> recruit = persons.stream().filter(person -> (person.getAge() >= 18 && person.getAge() <= 27))//проверяем на возраст призывников
                .filter(person -> person.getSex() == Sex.MAN).map(Person::getFamily) //проверяем на пол
                .collect(Collectors.toList());

        Collection<Person> ableBodied = persons.stream().
                filter(person -> person.getEducation() == Education.HIGHER) //проверяем, что образование высшее
                .filter(person -> person.getSex() == Sex.MAN ? person.getAge() >= 18 && person.getAge() <= 65 // если мужчина, диапазон возраста д.б. в пределах 18..65
                        : person.getAge() >= 18 && person.getAge() <= 60) //если женщина, диапазон возраста д.б. в пределах 18..60
                .sorted(Comparator.comparing(Person::getFamily)).collect(Collectors.toList());


    }
}