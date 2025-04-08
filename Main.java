import java.io.*;
import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        boolean end = false;

        System.out.print("Введіть шлях до файлу (наприклад, aboba.txt): ");
        String path = sc.nextLine();

        while (!end) {
            System.out.println("\n======== ГОЛОВНЕ МЕНЮ ========");
            System.out.println("1. Відредагувати файл\n2. Прочитати файл\n3. Вийти");
            System.out.print("Ваш вибір: ");

            int choice;
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                sc.nextLine();
            } else {
                System.out.println("Некоректний вибір! Спробуйте ще раз.");
                sc.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    editFile(path);
                    break;
                case 2:
                    readFile(path);
                    break;
                case 3:
                    System.out.println("До зустрічі!");
                    end = true;
                    break;
                default:
                    System.out.println("Некоректний вибір! Спробуйте ще раз.");
            }
        }
        sc.close();
    }

    public static void editFile(String path) {
        System.out.println("\n1. Перезаписати файл\n2. Додати у файл\n3. Повернутися");
        System.out.print("Ваш вибір: ");

        int editChoice;
        if (sc.hasNextInt()) {
            editChoice = sc.nextInt();
            sc.nextLine();
        } else {
            System.out.println("Некоректний вибір! Спробуйте ще раз.");
            sc.nextLine();
            return;
        }

        if (editChoice == 3) {
            return;
        }

        boolean append;
        if (editChoice == 2) append = true;

        else append = false;

        try (BufferedWriter wr = new BufferedWriter(new FileWriter(path, append))){
            System.out.println("Введіть текст (щоб завершити введення, натисніть Enter на порожньому рядку):");

            String s;
            int counter = 1;
            while(true) {
                System.out.print(counter + " ");
                s = sc.nextLine();
                counter++;

                if (!s.isEmpty()) {
                    wr.write(s);
                    wr.newLine();
                } else {
                    break;
                }
            }

            System.out.println("Файл успішно оновлено!");
        } catch (IOException e) {
            System.out.println("Помилка запису у файл: " + e.getMessage());
        }
    }

    public static void readFile(String path) {
        System.out.println("1. Зчитати весь файл\n2. Зчитати діапазон");
        int readChoice;
        if (sc.hasNextInt()) {
            readChoice = sc.nextInt();
            sc.nextLine();
        } else {
            System.out.println("Некоректний вибір! Спробуйте ще раз.");
            sc.nextLine();
            return;
        }

        switch (readChoice) {
            case 1:
                try (BufferedReader br = new BufferedReader(new FileReader(path))) {
                    System.out.println("\n===== Вміст файлу =====");
                    String s;
                    int counter = 1;
                    while ((s = br.readLine()) != null) {
                        System.out.println(counter + " " + s);
                        counter++;
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("Файл не знайдено! " + e.getMessage());
                } catch (IOException e) {
                    System.out.println("Помилка читання файлу! " + e.getMessage());
                }
                break;
            case 2:
                System.out.print("Введіть початок діапазону: ");
                int diapazonStart = sc.nextInt();
                sc.nextLine();

                System.out.print("Введіть кінець діапазону: ");
                int diapazonEnd = sc.nextInt();
                sc.nextLine();

                if (diapazonStart <= 0 || diapazonEnd < diapazonStart) {
                    System.out.println("Некоректний діапазон!");
                    return;
                }

                try (BufferedReader br = new BufferedReader(new FileReader(path))) {
                    System.out.println("\n===== Вміст діапазону =====");
                    String s;
                    int counter = 1;
                    while ((s = br.readLine()) != null) {
                        if (counter >= diapazonStart && counter <= diapazonEnd) {
                            System.out.println(counter + " " + s);
                        }
                        counter++;
                        if (counter > diapazonEnd) {
                            break;
                        }
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("Файл не знайдено! " + e.getMessage());
                } catch (IOException e) {
                    System.out.println("Помилка читання файлу! " + e.getMessage());
                }
                break;
            default:
                System.out.println("Некоректний вибір! Спробуйте ще раз.");
        }
    }
}
