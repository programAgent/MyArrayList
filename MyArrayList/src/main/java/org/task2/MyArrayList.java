import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.stream.Collectors;


public class MyArrayList <T> implements MyArrayListAbiliy <T> {

    private int size;
    private T[] data;
    private static final int DEFAULT_SIZE = 10;

    // Конструктор с перегрузкой метода
    public MyArrayList() {
        this.data = (T[]) new Object[DEFAULT_SIZE];
        this.size = 0;
    }

    public MyArrayList(int size) {
        this.data = (T[]) new Object[size];
        this.size = 0;
    }

    public MyArrayList(Object[] data) {
        this.data = (T[]) data;
        size = data.length;
    }

    //Внутренний метод создания увеличенного массива (х2)
    private void grow() {
        T[] newData = (T[]) new Object[data.length * 2];
        System.arraycopy(data, 0, newData, 0, data.length);
        data = newData;
    }

    // Метод проверки длины массива и его увеличение в 2 раза
    private void checkingSize() {
        if (size == data.length) {
            grow();
        }
    }

    // Метод проверки наличия свободного места по индексу (освободение индекса)
    private void checkingFreeIndex(int index) {
        checkingSize();
        if (data[index] != null) {
            System.arraycopy(data, index, data, index + 1, size - index);
        }
    }

    // метод - возвращающий колличество элементов
    public int size() {
        return size;
    }

    // Добавляет элемент в конец массива
    @Override
    public void add(T element) {
        checkingSize();
        data[size++] = (T) element;

    }
    // Добавляет элемент по индексу
    @Override
    public void add(int index, T element) {

        checkingFreeIndex(index);
        data[index] = element;
        size++;

    }
// получение элемента по индексу
    @Override
    public T get(int index) {
        return data[index];
    }

    // удаление элемента по индексу
    @Override
  public void remove(int index) {

         //   System.arraycopy(data, index+1, data, index, size - 1 - index);
            swap(data, size -1 , index);
            data[size-1] = null;
            size --;
            if (!(index==size)) {
            swap(data,index,size -1);}

    }

    // удаление всех элементов в списке
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }

        size = 0;
    }

    //Сортировка элементов с Comparator
    @Override
    public void sort(Comparator<T> comparator) {
        quickSort(data, 0, size - 1, comparator);

    }

    // Сортировка Comparable
    @Override
    public void sort () {
        quickSort(data, 0, size - 1);
    }

    //реализация метода быстрой сортировки

    private void quickSort(T[] array, int lowArr, int highArr, Comparator<T> comparator) {

        Deque<int[]> stack = new ArrayDeque<>();
        stack.push(new int[]{lowArr, highArr});

        while (!stack.isEmpty()) {
            int[] range = stack.pop();
            int low = range[0];
            int high = range[1];

            if (low < high) {
                int pivotIndex = partition(array, low, high, comparator);
                if (pivotIndex-1>low) {
                    stack.push(new int[]{low, pivotIndex - 1});
                }
                if (pivotIndex+1<high) {
                    stack.push(new int[]{pivotIndex + 1, high});
                }
            }
        }
    }

        private int partition (T[]array,int low, int high, Comparator<T > comparator){
            int pivotIndex = low + (int) (Math.random() * (high - low + 1));
            swap(array, pivotIndex, high);
            T pivot = array[high];
            int i = low - 1;

            for (int j = low; j < high; j++) {
                if (comparator.compare(array[j], pivot) <= 0) {
                    i++;
                    swap(array, i, j);
                }

            }
            swap(array, i + 1, high);

            return i + 1;
        }

        private void swap (T[]array,int x, int y){
            T temp = array[x];
            array[x] = array[y];
            array[y] = temp;
        }

        private void quickSort (T[]array,int lowArr, int highArr){
            Deque<int[]> stack = new ArrayDeque<>();
            stack.push(new int[]{lowArr, highArr});

            while (!stack.isEmpty()) {
                int[] range = stack.pop();
                int low = range[0];
                int high = range[1];

                if (low < high) {
                    int pivotIndex = partition(array, low, high);

                    if (pivotIndex - 1 > low) {
                        stack.push(new int[]{low, pivotIndex - 1});
                    }
                    if (pivotIndex + 1 < high) {
                        stack.push(new int[]{pivotIndex + 1, high});
                    }
                }
            }
        }

        private int partition (T[]array,int low, int high){
            int pivotIndex = low + (int) (Math.random() * (high - low + 1));
            swap(array, pivotIndex, high);
            T pivot = array[high];
            int i = low - 1;

            for (int j = low; j < high; j++) {
                if (((Comparable<T>) array[j]).compareTo(pivot) <= 0) {
                    i++;
                    swap(array, i, j);
                }

            }
            swap(array, i + 1, high);

            return i + 1;
        }

    @Override
    public String toString() {
        return Arrays.stream(data, 0, size)
                .map(String::valueOf)
                .collect(Collectors.joining(", ", "[", "]"));
    }
}









