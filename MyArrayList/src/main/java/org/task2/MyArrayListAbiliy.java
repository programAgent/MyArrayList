
import java.util.Comparator;

public interface MyArrayListAbiliy <T> {

    // Добавление элемента без индекса
    void add(T element);

    // Добавить элемент по индексу
    void add(int index, T element);

    // Получить элемент по индексу
   T get (int index);

    //удалить элемент по индексу
    void remove (int index);

    // очистить всю коллекцию
    void clear ();

    //сортировка коллекции Comparator
    void sort (Comparator<T> comparator);

    //сортировка коллекции Comparable
    <U extends Comparable<? super U>> void sort ();



}
