package main;

import game.GameRound;
import game.GameRules;
import objects.*;
import renderer.GameRenderer;
import renderer.WindowsRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class WindowsRunnable extends JFrame implements ActionListener {
    private final GameRules rules;
    private final GameRenderer renderer;
    private final KeyAdapter keyAdapter;
    private final Timer timer;

    public WindowsRunnable() throws IOException {
        Playfield playfield = Playfield.getInstance();
        java.util.List<Brick> bricks = new ArrayList<>();
        Vaus vaus = new Vaus(new Position(12, 29), 4);
        Ball ball = new Ball(new Position(14, 28));
        GameRound round = new GameRound(playfield, bricks, vaus, ball);
        rules = new GameRules(round);

        round.createBricks(5);

        setTitle("Arkanoid");
        setPreferredSize(new Dimension(464, 536));
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        renderer = new WindowsRenderer(round, getGraphics());

        keyAdapter =
                new KeyAdapter() {
                    public void keyPressed(KeyEvent e) {
                        char key = e.getKeyChar();
                        rules.processInput(key);
                        repaint();
                    }
                };
        addKeyListener(keyAdapter);

        timer = new Timer(200, this);
        timer.start();
    }

    public static void startGame() throws InvocationTargetException, InterruptedException {
        final Runnable runGame;
        runGame =
                () -> {
                    try {
                        new WindowsRunnable();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                };
        SwingUtilities.invokeAndWait(runGame);
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        renderer.renderGame();
        if (rules.isGameOver())
            renderer.renderResult();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        rules.moveBall();
        rules.removeBricks();
        repaint();
        if (rules.isGameOver())
            endGame();
    }

    public void endGame() {
        repaint();
        removeKeyListener(keyAdapter);
        timer.stop();
    }
}
