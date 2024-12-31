package Maze;


import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class Maze {

    private final Map<Integer, Set<Integer>> graph;

    private final int start;
    private final int end;

    public Maze(List<String> input) {
        graph = new HashMap<>();
        int index = 0;
        int start = -1;
        int end = -1;
        int rowCount = input.size();
        int columnCount = input.get(0).length();
        parseMaze maze = parseMaze(input, rowCount, columnCount, start, end, index);
        this.start = maze.start();
        this.end = maze.end();
    }

    public int bfs() {
        Queue<Integer> queue = new LinkedList<>(List.of(start));
        Set<Integer> seen = new HashSet<>(List.of(start));
        int steps = 0;
        while(!queue.isEmpty()) {
            steps++;
            Set<Integer> neighbours = graph.get(queue.poll());
            for(int neighbour : neighbours) {
                if (neighbour == end)
                    return steps;
                if(!seen.contains(neighbour)) {
                    queue.add(neighbour);
                    seen.add(neighbour);
                }
            }
        }
        return -1;
    }

    public int dfs() {
        Stack<Integer> stack = new Stack<>();
        Set<Integer> seen = new HashSet<>(List.of(start));
        stack.add(start);
        int steps = 0;
        while(!stack.isEmpty()) {
            steps++;
            Set<Integer> neighbours = graph.get(stack.pop());
            for(int neighbour : neighbours) {
                if (neighbour == end)
                    return steps;
                if(!seen.contains(neighbour)) {
                    stack.add(neighbour);
                    seen.add(neighbour);
                }
            }
        }
        return -1;
    }

    private parseMaze parseMaze(List<String> input, int rowCount, int columnCount, int start, int end, int index) {
        for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
            String row = input.get(rowIndex);
            for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
                char current = row.charAt(columnIndex);
                if(current == ' ' || current == 'S' || current == 'E') {
                    if(current == 'S')
                        start = rowIndex * columnCount + columnIndex;
                    if(current == 'E')
                        end = rowIndex * columnCount + columnIndex;
                    graph.put(index, getEmptyCells(input, rowCount, columnCount, rowIndex, columnIndex, row));
                }
                index++;
            }
        }
        return new parseMaze(start, end);
    }

    private static Set<Integer> getEmptyCells(List<String> input, int rowCount, int columnCount, int rowIndex, int columnIndex, String row) {
        Set<Integer> adjacentVertices = new HashSet<>();
        if (rowIndex > 0 && input.get(rowIndex - 1).charAt(columnIndex) != '#')
            adjacentVertices.add((rowIndex - 1) * columnCount + columnIndex);
        if (rowIndex < rowCount - 1 && input.get(rowIndex + 1).charAt(columnIndex) != '#')
            adjacentVertices.add((rowIndex + 1) * columnCount + columnIndex);
        if (columnIndex > 0 && row.charAt(columnIndex - 1) != '#')
            adjacentVertices.add((rowIndex * columnCount) + (columnIndex - 1));
        if (columnIndex < columnCount - 1 && row.charAt(columnIndex + 1) != '#')
            adjacentVertices.add(rowIndex * columnCount + (columnIndex + 1));
        return adjacentVertices;
    }

    private record parseMaze(int start, int end) {
    }
}
