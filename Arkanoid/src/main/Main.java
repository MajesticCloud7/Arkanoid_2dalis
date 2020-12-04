package main;

import game.GameRound;
import game.GameRules;
import objects.*;
import renderer.GameRenderer;
import renderer.ConsoleRenderer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Playfield playfield = Playfield.getInstance();
        List<Brick> bricks = new ArrayList<>();
        Vaus vaus = new Vaus(new Position(12, 29), 4);
        Ball ball = new Ball(new Position(14, 28));
        GameRound round = new GameRound(playfield, bricks, vaus, ball);
        GameRules rules = new GameRules(round);
        GameRenderer renderer = new ConsoleRenderer(round);
        Scanner scanner = new Scanner(System.in);

        round.createBricks(3);
        renderer.renderGame();

        while (!rules.isGameOver()) {
            int key = scanner.next().charAt(0);
            rules.processInput(key);
            rules.moveBall();
            rules.removeBricks();
            renderer.renderGame();
        }
        renderer.renderResult();
    }
}
