package q4;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        List<Integer> list = getRandomArrayList(100, 100);
        List<Integer> copyList = new ArrayList<>(list);

        // IndexOutOfBound istisnasını almamak için 0 ile 99 arasında rastgele sayı oluşturuyoruz
        int randomIndex = getRandomNumberBetweenZeroTo(99);

        System.out.format("Silinecek olan sayı: [%d]: %d\n", randomIndex, copyList.get(randomIndex));
        copyList.remove(randomIndex);

        int returnedIndex = findMissingElement(list, copyList);
        if (returnedIndex != -1) {
            System.out.println("Kopya listedeki eksik eleman: " + list.get(returnedIndex));
        }

    }

    public static int findMissingElement(List<Integer> original, List<Integer> copy) {
        int missingElementIndex = -1;
        int minSize = Math.min(original.size(), copy.size());
        for (int i = 0; i < minSize; i++) {
            if (!Objects.equals(original.get(i), copy.get(i))) {
                missingElementIndex = i;
                break;
            }
        }
        // Silinen eleman döngüde bulunamadıysa orijinal listenin son indisindedir
        if (missingElementIndex == -1 && original.size() > copy.size()) {
            missingElementIndex = original.size() - 1;
        }
        return missingElementIndex;
    }

    public static int getRandomNumberBetweenZeroTo(int upperBound) {
        // Sıfır ile üst sınır arasında sayı üretmek için, üst sınırı bir artırıp random() metodu ile çarparız
        return (int) (Math.random() * (upperBound + 1));
    }

    public static List<Integer> getRandomArrayList(int size, int bound) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(getRandomNumberBetweenZeroTo(bound));
        }
        return list;
    }

}
