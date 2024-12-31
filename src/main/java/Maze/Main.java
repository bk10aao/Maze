package Maze;

import Utils.ReadFile;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        Maze maze = new Maze(ReadFile.getInput("maze_1.txt"));
        System.out.println("BFS: " + maze.bfs());
        System.out.println("DFS: " + maze.dfs());
        maze = new Maze(ReadFile.getInput("maze_2.txt"));
        System.out.println("BFS: " + maze.bfs());
        System.out.println("DFS: " + maze.dfs());
        maze = new Maze(ReadFile.getInput("maze_3.txt"));
        System.out.println("BFS: " + maze.bfs());
        System.out.println("DFS: " + maze.dfs());
        maze = new Maze(ReadFile.getInput("maze_4.txt"));
        System.out.println("BFS: " + maze.bfs());
        System.out.println("DFS: " + maze.dfs());
    }
}