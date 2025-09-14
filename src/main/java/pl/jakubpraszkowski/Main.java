package pl.jakubpraszkowski;

public class Main {
    public static void main(String[] args) {
        FootballWCScoreboard board = new FootballWCScoreboard();

        board.startGame("Mexico", "Canada");
        board.updateScore("Mexico", "Canada", 0, 5);

        board.startGame("Spain", "Brazil");
        board.updateScore("Spain", "Brazil", 10, 2);

        System.out.println(board.displayScoreboard());
    }
}