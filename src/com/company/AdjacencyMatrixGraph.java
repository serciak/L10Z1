package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.Hashtable;

public class AdjacencyMatrixGraph {

    private int vertexAmount;
    private int adjMatrix[][];

    public AdjacencyMatrixGraph(int vertexAmount) {
        this.vertexAmount = vertexAmount;
        adjMatrix = new int[vertexAmount][vertexAmount];
    }

    public void addEdge(int x, int y, int weight) {
        adjMatrix[x][y] = weight;
        adjMatrix[y][x] = weight;
    }

    public void addVertex() {
        int[][] newMatrix = new int[vertexAmount+1][vertexAmount+1];
        for(int i = 0; i < vertexAmount; i++)
            for(int j = 0; j < vertexAmount; j++)
                newMatrix[i][j] = adjMatrix[i][j];
        vertexAmount++;
        adjMatrix = newMatrix;
    }

    class set {
        int parent;
        int weight;
    }

    private int minVertex(boolean[] v, int[] w) {
        int minKey = Integer.MAX_VALUE;
        int minV = -1;

        for(int i = 0; i < vertexAmount; i++) {
            if(!v[i] && minKey > w[i]) {
                minKey = w[i];
                minV = i;
            }
        }
        return minV;
    }

    public void mst() {
        boolean[] v = new boolean[vertexAmount];
        set[] set = new set[vertexAmount];
        int[] w = new int[vertexAmount];

        for(int i = 0; i < vertexAmount; i++) {
            w[i] = Integer.MAX_VALUE;
            set[i] = new set();
        }

        w[0] = 0;
        set[0].parent = -1;

        for(int i = 0; i < vertexAmount; i++) {
            int current = minVertex(v, w);
            v[current] = true;

            for(int j = 0; j < vertexAmount; j++) {
                if(adjMatrix[current][j] > 0) {
                    if(!v[j] && adjMatrix[current][j] < w[j]) {
                        w[j] = adjMatrix[current][j];

                        set[j].parent = current;
                        set[j].weight = w[j];
                    }
                }
            }
        }
        printMST(set);
    }

    private void printMST(set[] set) {
        int minWeight = 0;

        System.out.println("MST:");
        for(int i = 1; i < vertexAmount; i++) {
            System.out.println(i + " - " + set[i].parent + " weight: " + set[i].weight);
            minWeight += set[i].weight;
        }

        System.out.println("\nMinimum weight: " + minWeight);
    }

    public void bfs(int start, int end) {
        boolean[] visited = new boolean[vertexAmount];
        Arrays.fill(visited, false);
        ArrayList<Integer> q = new ArrayList<>();

        q.add(start);
        visited[start] = true;

        int current = -1;
        System.out.print("BFS: ");
        while(!q.isEmpty() && current != end) {
            current = q.get(0);

            System.out.print(current + " ");
            q.remove(0);

            for(int i = 0; i < vertexAmount; i++) {

                if(adjMatrix[current][i] > 0 && (!visited[i])) {
                    q.add(i);
                    visited[i] = true;
                }
            }
        }
        System.out.println();
    }

    public void dfs(int start, int end) {
        boolean[] visited = new boolean[vertexAmount];
        ArrayList<Integer> path = new ArrayList<>();
        System.out.print("DFS: ");
        dfs(start, visited, path);

        for(int i : path) {
            System.out.print(i + " ");
            if(i == end)
                break;
        }
    }

    private void dfs(int start, boolean[] visited, ArrayList<Integer> path) {
        path.add(start);

        visited[start] = true;
        for(int i = 0; i < vertexAmount; i++) {
            if(adjMatrix[start][i] > 0 && (!visited[i]))
                dfs(i, visited, path);
        }
    }

    public int[] dijkstra(int start) {
        boolean[] spt = new boolean[vertexAmount];
        int[] distance = new int[vertexAmount];
        int INF = Integer.MAX_VALUE;

        for(int i = 0; i < vertexAmount; i++)
            distance[i] = INF;
        distance[start] = 0;

        for(int i = 0; i < vertexAmount; i++) {
            int v = minVertex(spt, distance);
            spt[v] = true;

            for(int u = 0; u < vertexAmount; u++) {
                if(adjMatrix[v][u] > 0) {
                    if(!spt[u]) {
                        int w = adjMatrix[v][u] + distance[v];
                        if(w < distance[u]) {
                            distance[u] = w;
                        }
                    }
                }
            }
        }
        return distance;
        //printDijkstra(start, distance);
    }

    private void printDijkstra(int sourceVertex, int [] key){
        System.out.println("\nDijkstra:");
        for (int i = 0; i < vertexAmount ; i++) {
            System.out.println("start vertex: " + sourceVertex + " to vertex " + i + " distance: " + key[i]);
        }
    }

    public ArrayList<Path> getAllPaths(int s, int d) {
        boolean[] isVisited = new boolean[vertexAmount];
        ArrayList<Integer> pathList = new ArrayList<>();
        ArrayList<Path> paths = new ArrayList<>();
        int w = 0;

        pathList.add(s);

        getAllPaths(s, d, w, isVisited, pathList, paths);
        return paths;
    }

    private void getAllPaths(Integer u, Integer d, int w, boolean[] isVisited, ArrayList<Integer> pathList, ArrayList<Path> paths) {

        if (u.equals(d)) {
            paths.add(new Path((ArrayList<Integer>) pathList.clone(), w));
            return;
        }

        isVisited[u] = true;

        for (int i = 0; i < vertexAmount; i++) {
            if(!isVisited[i] && adjMatrix[u][i] != 0) {
                pathList.add(i);
                w += adjMatrix[u][i];
                getAllPaths(i, d, w, isVisited, pathList, paths);

                pathList.remove(pathList.size()-1);
                w -= adjMatrix[u][i];
            }
        }

        isVisited[u] = false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");

        for(int i = 0; i < vertexAmount; i++) {
            for(int j = 0; j < vertexAmount; j++) {
                sb.append(adjMatrix[i][j]).append("\t");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public int getVertexAmount() {
        return vertexAmount;
    }
}
