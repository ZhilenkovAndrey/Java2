package ru.geekbrains;

public class TreadMill  implements Course{

    @Override
    public double distance(IRunJump x) {
        x.run(2000);
        return 0;
    }
}