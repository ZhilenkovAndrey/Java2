package ru.geekbrains;

public class Human implements IRunJump{

    private String className;
    private final double runLength = 1000;
    private final double jumpHeight = 1;

    public Human() {
        className = getClass().getSimpleName();
    }

    @Override
    public void run(double distance) {
        if (distance > runLength) {
            System.out.printf("%s can't run distance %1fm\n",className, distance);
        }
        else {
            System.out.printf("%s successfully run %1fm\n",className, distance);
        }
    }

    @Override
    public void jump(double distance) {
        if (distance > jumpHeight) {
            System.out.printf("%s can't jump %.1fm\n",className, distance);
        }
        else {
            System.out.printf("%s successfully jump %.1fm\n",className, distance);
        }
    }
}