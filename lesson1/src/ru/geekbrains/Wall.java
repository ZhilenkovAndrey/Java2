package ru.geekbrains;

public class Wall implements Course{

    @Override
    public double distance(IRunJump x) {
        x.jump(1.5);

        return 0;
    }
}
