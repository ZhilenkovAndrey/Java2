package ru.geekbrains;

/**
 * Java2. HomeWork1
 *
 * @autor Andrey Zhilenkov
 * @ version 10.10.2021
 */
public class Main {

    public static void main(String[] args) {

        Team name = new Team();

        IRunJump[] challengers = {new Human(), new Cat(), new Robot()};
        Course[] courses = {new Wall(), new TreadMill()};

        for (IRunJump challenger : challengers) {
            System.out.println();
            for (Course course : courses) {
                course.distance(challenger);
            }
        }

        System.out.println();
        System.out.println(name.name());

        Team[] team = new Team[4];
        team[0] = new Team("Boris", 3000, 1.8);
        team[1] = new Team("Sergey", 2000, 1.3);
        team[2] = new Team("Vadim", 2500, 1.7);
        team[3] = new Team("Oleg", 2700, 1.4);


        for (int i = 0; i <= 3; i++) {
            System.out.println();
            for (Course course : courses) {
                course.distance(team[i]);
            }
        }
    }
}