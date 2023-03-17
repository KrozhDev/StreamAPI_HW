import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {


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

        long howManyChildren = persons.stream().
                filter(p -> p.getAge() < 18).count();

        List<String> listOfRecruitsSurnames = persons.stream().
                filter(p -> p.getAge() >= 18).
                filter(p -> p.getAge() <= 27 ).
                map(Person::getFamily).collect(Collectors.toList());

        List<Person> listOfWorkableHighEducatedPersons = persons.stream().
                filter(p -> p.getEducation() == Education.HIGHER).
                filter(p -> p.getAge() >= 18 ).
                filter(p -> p.getSex() == Sex.WOMAN && p.getAge() <= 60 | p.getSex() == Sex.MAN && p.getAge() <= 65).
                sorted(Comparator.comparing(Person::getFamily)).
                collect(Collectors.toList());

        System.out.println();
        System.out.printf("Количество несовершеннолетних: %d \n", howManyChildren);
        System.out.println();
        System.out.printf("Список призывников: %s ... \n", listOfRecruitsSurnames.subList(0,10));
        System.out.println();
        System.out.printf("Список работоспособных с высшим образованием: %s ... \n", listOfWorkableHighEducatedPersons.subList(0,10));



    }
}
