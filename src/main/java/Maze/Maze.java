package Maze;


import Utils.ReadFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Maze {


    public static void main(String[] args) throws IOException {
        List<String> input = ReadFile.getInput("maze_1.txt");
        for(String s : input) {
            System.out.println(s);
        }
        Maze maze = new Maze();
        ParsedMaze parsedMaze = maze.toGraph(input);
        System.out.println(maze.bfs(parsedMaze.graph, parsedMaze.start, parsedMaze.end));
    }

    private class ParsedMaze {
        Map<Integer, Set<Integer>> graph = new HashMap<>();

        int start;
        int end;
    }

    private ParsedMaze toGraph(List<String> input) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        int index = 0;
        int start = -1;
        int end = -1;
        for(int rowIndex = 0; rowIndex < input.size() - 1; rowIndex++) {
            String row = input.get(rowIndex);
            int columns = input.get(rowIndex).length();
            for(int columnIndex = 0; columnIndex < columns; columnIndex++) {
                char current = row.charAt(columnIndex);
                if(current == ' ' || current == 'S' || current == 'E') {
                    if(current == 'S') {
                        start = index;
                    }
                    if(current == 'E') {
                        end = index;
                    }
                    Set<Integer> adjacentVertices = new HashSet<>();
                    graph.put(index, adjacentVertices);

                    try {
                        char upChar = input.get(rowIndex - 1).charAt(columnIndex);
                        if (upChar == ' ') {
                            int above = (rowIndex - 1) * columns + columnIndex;
                            adjacentVertices.add(above);
                        }
                    } catch(Exception ignored) {}

                    try {
                        char downChar = input.get(rowIndex + 1).charAt(columnIndex);

                        if (downChar == ' ') {
                            int below = (rowIndex + 1) * columns + columnIndex;
                            adjacentVertices.add(below);
                        }
                    } catch(Exception ignored) {}

                    try {
                        char leftChar = input.get(rowIndex).charAt(columnIndex - 1);
                        if(leftChar == ' ') {
                            int left = (rowIndex * columns) + (columnIndex -1);
                            adjacentVertices.add(left);
                        }
                    } catch (Exception e) {}

                    try {
                        char rightChar = input.get(rowIndex).charAt(columnIndex + 1);
                        if (rightChar == ' ') {
                            int right = rowIndex * columns + (columnIndex + 1);
                            adjacentVertices.add(right);
                        }
                    } catch (Exception e) {}
                }
                index++;
            }
        }
        ParsedMaze parsedMaze = new ParsedMaze();
        parsedMaze.start = start;
        parsedMaze.end = end;
        parsedMaze.graph = graph;
        return parsedMaze;
    }

    public boolean bfs(Map<Integer, Set<Integer>> graph, int start, int end) {
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> seen = new HashSet<>();

        queue.add(start);
        seen.add(start);

        while(!queue.isEmpty()) {
            int currentVertex = queue.poll();
            Set<Integer> neighbours = graph.get(currentVertex);
            for(int neighbour : neighbours) {
                if (neighbour == end) {
                    return true;
                }
                if(!seen.contains(neighbour)) {
                    queue.add(neighbour);
                    seen.add(neighbour);
                }
            }
        }
        return false;
    }

    /**
     *  1  2  3  4  5
     *  6  7  8  9 10
     * 11 12 13 14 15
     * 16 17 18 19 20
     * 21 22 23 24 25
     *
         #####
         S   #
         # # #
         # # E
         #####
     *
     * 16 17 18 13 8 9 10
     * 16 -> {17}
     * 17 -> {16, 18}
     * 18 -> {17, 13}
     * 13 -> {18, 8}
     *  8 -> {13, 9}
     *  9 -> {8, 10}
     * 10 -> {9}
     *
     * BFS
     * [16]
     * [17]
     * [16, 18]
     * [13]
     * [8]
     * [9]
     * [10]
     *
     *
     * if(!visited) {
     *     add to queue
     *     add to visited set
     * }
     * O(n * 4)
     * O(n)
     */
}
