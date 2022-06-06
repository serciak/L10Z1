package com.company;

public class Main {

    public static void main(String[] args) {
        CitiesMap cp = new CitiesMap();
        cp.addCity("Wroclaw");
        cp.addCity("Leszno");
        cp.addCity("Poznan");
        cp.addCity("Gdansk");
        cp.addCity("Rawicz");
        cp.addCity("Koscian");
        cp.addPath("Wroclaw", "Leszno", 100);
        cp.addPath("Leszno", "Poznan", 95);
        cp.addPath("Wroclaw", "Poznan", 200);
        cp.addPath("Gdansk", "Poznan", 400);
        cp.addPath("Koscian", "Poznan", 50);
        cp.addPath("Leszno", "Koscian", 30);
        cp.addPath("Rawicz", "Leszno", 45);
        cp.addPath("Wroclaw", "Rawicz", 60);
        cp.shortestPathBetween("Wroclaw", "Poznan");
        cp.printAllPaths("Wroclaw", "Poznan");
    }

}
