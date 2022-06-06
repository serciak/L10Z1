package com.company;

import java.util.*;

public class CitiesMap {

    private AdjacencyMatrixGraph amg;
    private Map<String, Integer> cities;

    public CitiesMap() {
        amg = new AdjacencyMatrixGraph(0);
        cities = new Hashtable<>();
    }

    public void addCity(String city) {
        int c = amg.getVertexAmount();
        cities.put(city, c);
        amg.addVertex();
    }

    public void addPath(String src, String des, int dis) {
        amg.addEdge(cities.get(src), cities.get(des), dis);
    }

    public void shortestPathBetween(String src, String des) {
        int[] distances = amg.dijkstra(cities.get(src));
        System.out.println("Shortest path from " + src + " to " + des + " is " + distances[cities.get(des)] + " km");
    }

    public void printAllPaths(String src, String des) {
        ArrayList<Path> paths = amg.getAllPaths(cities.get(src), cities.get(des));
        paths.sort(Comparator.comparingInt(Path::getWeight));

        for(Path p : paths) {
            ArrayList<Integer> path = p.getPath();
            for(int i : path) {
                System.out.print(getCity(i) + " ");

                if(path.indexOf(i) != path.size() - 1)
                    System.out.print("-> ");
            }
            System.out.println("\nDistance: " + p.getWeight() + " km");
        }
    }



    private String getCity(int i) {
        for(Map.Entry<String, Integer> entry : cities.entrySet()) {
            if (i == entry.getValue()) {
                return entry.getKey();
            }
        }
        return null;
    }
}
