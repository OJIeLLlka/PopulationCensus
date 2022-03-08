package netology.population;

import org.w3c.dom.ls.LSOutput;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static netology.population.Education.HIGHER;
import static netology.population.Sex.MAN;
import static netology.population.Sex.WOMAN;

public class Main {

    public static void main(String[] args) {
        // write your code here
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();

        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        //Несовершеннолетние
        long minors = persons.stream()
                .filter(x -> x.getAge() < 18)
                .count();
        System.out.println("Количество  несовершеннолетних: " + minors + " человек.");

        //Призывники
        List<String> conscripts = persons.stream()
                .filter(x -> x.getAge() < 27)
                .filter(x -> x.getAge() > 18)
                .map(Person::getFamily)
                .collect(Collectors.toList());

//        System.out.println("Фамилии призывников: ");
//        for (String c : conscripts) {
//            System.out.print(c + ", ");
//        }

        //Работоспособные с вышкой
        List<Person> ableToWork = persons.stream()
                .filter(x -> x.getEducation().equals(HIGHER))
                .filter(x -> x.getAge() > 18)
                .filter(x -> ((x.getSex().equals(MAN) && x.getAge() < 65) || (x.getSex().equals(WOMAN) && x.getAge() < 60)))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());

//        System.out.println("Те кто может работать: ");
//        for (Person p : ableToWork) {
//            System.out.println(p);
//        }
    }
}


