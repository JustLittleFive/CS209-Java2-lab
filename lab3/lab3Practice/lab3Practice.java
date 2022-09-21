package lab3Practice;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class lab3Practice {
    /**
     * @param args
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("Please input the function No: \n");
            System.out.println("1 - Get even nums \n");
            System.out.println("2 - Get odd nums \n");
            System.out.println("3 - Get prime nums \n");
            System.out.println("4 - Get prime nums bigger than 5 \n");
            System.out.println("0 - Quit \n");

            int operator = input.nextInt();

            if (operator == 0) {
                input.close();
                System.exit(0);
            }

            System.out.println("Input size of the list: \n");

            int size = input.nextInt();

            System.out.println("Input elements of the list: \n");

            List<Integer> list = new ArrayList<Integer>();

            for (int i = 0; i < size; i++) {
                list.add(input.nextInt());
            }

            List<Integer> result = new ArrayList<Integer>();

            switch (operator) {
                case 1:
                    result = list.stream().filter(temp -> (int) temp % 2 == 0).sorted().collect(Collectors.toList());
                    break;
                case 2:
                    result = list.stream().filter(temp -> (int) temp % 2 != 0).sorted().collect(Collectors.toList());
                    break;
                case 3:
                    result = list.stream().filter(temp -> isPrime((int) temp)).sorted().collect(Collectors.toList());
                    break;
                case 4:
                    result = list.stream().filter(temp -> isPrime((int) temp)).filter(temp -> (int) temp > 5).sorted()
                            .collect(Collectors.toList());
                    break;
                default:
                    input.close();
                    System.exit(0);
            }
            System.out.println(result);
        }

    }

    /**
     * @param num
     * @return bool isNumPrime
     */
    public static boolean isPrime(int num) {
        if (num < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

}
