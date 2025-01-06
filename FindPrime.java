import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class FindPrime {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("find all prime numbers <= n, enter n: ");
        int n = scanner.nextInt();
        int number = 2;
        int count = 0;
        int squareRoot = 1;
        Boolean isPrime;
        ArrayList<Integer> list = new ArrayList<>();
        while (number <= n) {
            isPrime = true;
            if (squareRoot * squareRoot <= number) {
                squareRoot++;
            } else {
                for (int i : list) {
                    if (number % i == 0) {
                        isPrime = false;
                        break;
                    }
                }
                if (isPrime) {
                    list.add(number);
                    count++;
                }
                number++;

            }


        }
        System.out.println("count num " + count);
        System.out.println(list);
    }
}
