package ru.geekbrains;

public class Team implements IRunJump {

    private String name;
    private double runLength;
    private double jumpHeight;
    private String teamName = "'Victory'";
    private String winnersName = "Winners";

    public Team(String name, double runLength, double jumpHeight) {

        this.runLength = runLength;
        this.jumpHeight = jumpHeight;
        this.name = name;
    }

    public Team() {
    }

    @Override
    public void run(double distance) {
        if (distance > runLength) {
            System.out.printf("%s can't run distance %1fm\n", name, distance);
        }
        else {
            System.out.printf("%s successfully run %1fm\n", name, distance);
        }
    }

    @Override
    public void jump(double distance) {
        if (distance > jumpHeight) {
            System.out.printf("%s can't jump %.1fm\n", name, distance);
        }
        else {
            System.out.printf("%s successfully jump %.1fm\n", name, distance);
        }
    }

    public String name() {
        return " Team " + teamName;
    }
}
