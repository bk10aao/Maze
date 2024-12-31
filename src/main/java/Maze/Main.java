package Maze;

import Utils.ReadFile;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        List<String> input = ReadFile.getInput("maze_1.txt");
        Maze maze = new Maze(input);
        System.out.println("BFS: " + maze.bfs());
        System.out.println("DFS: " + maze.dfs());
        input = ReadFile.getInput("maze_2.txt");
        maze = new Maze(input);
        System.out.println("BFS: " + maze.bfs());
        System.out.println("DFS: " + maze.dfs());
        input = ReadFile.getInput("maze_3.txt");
        maze = new Maze(input);
        System.out.println("BFS: " + maze.bfs());
        System.out.println("DFS: " + maze.dfs());
    }
}
