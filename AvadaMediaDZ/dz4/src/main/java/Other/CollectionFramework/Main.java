package Other.CollectionFramework;

import java.util.*;

public class Main<E> {

    public List<E> getArrList(E... a) {
        ArrayList<E> list = new ArrayList<>();
        for (E x: a)
            list.add(x);
        list.trimToSize();
        return list;
    }

    public List<E> getLinkedList(E... a) {
        LinkedList<E> list = new LinkedList<>();
        for (E x: a)
            list.add(x);
        return list;
    }

    public static void main(String[] args) {
        Main<Person> main = new Main<>();
        Person oleg = new Person("Oleg", 20);
        Person oleg1 = new Person("Oleg", 20);
        Person vova = new Person("Vova", 21);
        Person liza = new Person("Liza", 20);
        //List
        System.out.println("\nList:");
        List<Person> list1 = main.getArrList(oleg, vova, liza);
        List<Person> list2 = main.getLinkedList(oleg, vova, liza);
        //hashCode
        System.out.println("HashCode:");
        System.out.println("list1 hashCode -> " + list1.hashCode());
        System.out.println("list2 hashCode -> " + list2.hashCode());
        //equals
        System.out.println("Equals:");
        System.out.println("list1.equals(list2) -> " + list1.equals(list2));
        System.out.println("oleg.equals(liza) -> " + oleg.equals(liza));
        //Map
        System.out.println("\nMap:");
        HashMap<String, Person> integerPersonHashMap = new HashMap<>();
        integerPersonHashMap.put("1", oleg);
        integerPersonHashMap.put("2", vova);
        //compareTo
        System.out.println("compareTo:");
        System.out.println("oleg compareTo(oleg1) -> " + integerPersonHashMap.get("1").compareTo(oleg1));
        System.out.println("oleg compareTo(vova) -> " + integerPersonHashMap.get("1").compareTo(integerPersonHashMap.get("2")));
        //TreeMap
        TreeMap<Person, String> treeMap = new TreeMap<>();
        treeMap.put(vova, "He will be third");
        treeMap.put(oleg, "Second");
        treeMap.put(liza, "First");
        System.out.println(treeMap);
        //iterator
        System.out.println("iterator:");
        Iterator<Map.Entry<Person, String>> iterator = treeMap.entrySet().iterator();
        while (iterator.hasNext())
            System.out.println(iterator.next());
        //reduce
        System.out.println("reduce:");
        System.out.println(list1.stream().map(Person::getAge).reduce(Integer::max).get());

        List<Integer> integers = Arrays.asList(2312, 4324, 3234, 1111);
        integers.add(4323);
    }

}

final class Person implements Comparable<Person>, Comparator<Person> {

    final String name;
    final int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    @Override
    public int compareTo(Person o) {
        return compare(this, o);
    }

    @Override
    public int compare(Person o1, Person o2) {
        return (o1.equals(o2)) ? ((o1.age == o2.age) ? 0 : 1) : -1;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return person.hashCode() == this.hashCode();
    }

    @Override
    public int hashCode() {
        return name.hashCode() + age;
    }

}