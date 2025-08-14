import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        compare(1);
        compare(2);
        compare(5);
        compare(15);
    }

    public static void compare(int day) {
        System.out.println("=== Day " + day + " ===");
        int[] startNumbers = {21, 1, 20, 23};
        int iterative = chooseHobbyIterative(startNumbers, day);
        int recursive = chooseHobbyRecursive(startNumbers, day, new int[day]);
        System.out.println("Iterative = " + iterative + " | Recursive = " + recursive);
    }

    public static int chooseHobbyRecursive(int[] startNumbers, int day, int[] memory) {
        int[] sharedArray = new int[startNumbers.length + memory.length];
        System.arraycopy(startNumbers, 0, sharedArray, 0, startNumbers.length);
        System.arraycopy(memory, 0, sharedArray, 4, memory.length);

        int prv = sharedArray[sharedArray.length - 1 - day];
        int prePrePrev = sharedArray[sharedArray.length - 1 - day - 2];
        int result = (prv * prePrePrev) % 10 + 1;

        if (day == 1) {
            return result;
        }

        memory[memory.length - day] = result;

        return chooseHobbyRecursive(startNumbers, day - 1, memory);
    }

    public static int chooseHobbyIterative(int[] startNumbers, int day) {
        List<Integer> numbers = new ArrayList<>();

        numbers.add(startNumbers[0]);
        numbers.add(startNumbers[1]);
        numbers.add(startNumbers[2]);
        numbers.add(startNumbers[3]);

        for (int d = 0; d < day; d++) {
            int index = d + 4; // индексы дней в массиве сдвинуты на 4
            int prev = numbers.get(index - 1); // предыдущее значение
            int prePrePrev = numbers.get(index - 3); // пре-пре-предыдущее значение
            numbers.add((prev * prePrePrev) % 10 + 1);
        }
        return numbers.get(numbers.size() - 1);
    }
}