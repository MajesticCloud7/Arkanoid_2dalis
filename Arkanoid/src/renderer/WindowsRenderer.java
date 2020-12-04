package renderer;

import game.GameRound;
import objects.Brick;
import objects.SilverBrick;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class WindowsRenderer implements GameRenderer {
    private final GameRound round;
    private final Graphics graphics;
    private final BufferedImage backgroundImage;
    private final BufferedImage wallImage;
    private final BufferedImage vausImage;
    private final BufferedImage ballImage;
    private final BufferedImage silverBrickImage;
    private final BufferedImage silverBrickCrackedImage;
    private final BufferedImage whiteBrickImage;

    public WindowsRenderer(GameRound round, Graphics graphics) throws IOException {
        this.round = round;
        this.graphics = graphics;
        backgroundImage = ImageIO.read(new FileInputStream("img/background.png"));
        wallImage = ImageIO.read(new FileInputStream("img/wall.png"));
        vausImage = ImageIO.read(new FileInputStream("img/vaus.png"));
        ballImage = ImageIO.read(new FileInputStream("img/ball.png"));
        silverBrickImage = ImageIO.read(new FileInputStream("img/silver_brick.png"));
        silverBrickCrackedImage = ImageIO.read(new FileInputStream("img/silver_brick_cracked.png"));
        whiteBrickImage = ImageIO.read(new FileInputStream("img/white_brick.png"));
    }

    @Override
    public void renderScore() {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(8, 32, 464, 16);
        graphics.setFont(new Font("Monospaced", Font.BOLD, 16));
        graphics.setColor(Color.YELLOW);
        graphics.drawString("SCORE: " + round.getScore(), 24, 45);
    }

    @Override
    public void renderPlayfield() {
        drawBackground();
        drawVaus();
        drawBall();
        drawBricks();
        drawWalls();
    }

    @Override
    public void renderResult() {
        Font font = new Font("Monospaced", Font.BOLD, 32);
        graphics.setFont(font);
        FontMetrics fontMetrics = graphics.getFontMetrics(font);

        drawResultBackground(fontMetrics);
        drawResultString(fontMetrics);
    }

    public void drawResultBackground(FontMetrics fontMetrics) {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(
                (round.getPlayfield().getWidth() * 16 - fontMetrics.stringWidth(round.getResult())) / 2 + 8,
                (round.getPlayfield().getWidth() * 16 - fontMetrics.getHeight()) / 2 + 44,
                fontMetrics.stringWidth(round.getResult()),
                32);
    }

    public void drawResultString(FontMetrics fontMetrics) {
        graphics.setColor(Color.YELLOW);
        graphics.drawString(
                round.getResult(),
                (round.getPlayfield().getWidth() * 16 - fontMetrics.stringWidth(round.getResult())) / 2 + 8,
                round.getPlayfield().getWidth() * 8 + 48);
    }


    public void drawBackground() {
        graphics.drawImage(backgroundImage, 24, 64, 416, 464, null);
    }

    public void drawVaus() {
        graphics.drawImage(
                vausImage,
                round.getVaus().getPosition().getX() * 16 + 8,
                round.getVaus().getPosition().getY() * 16 + 48,
                64,
                16,
                null);
    }

    public void drawBall() {
        graphics.drawImage(
                ballImage,
                round.getBall().getPosition().getX() * 16 + 8,
                round.getBall().getPosition().getY() * 16 + 48,
                16,
                16,
                null);
    }

    public void drawBricks() {
        for (Brick brick : round.getBricks())
            if (brick instanceof SilverBrick)
                drawSilverBrick(brick);
            else drawWhiteBrick(brick);
    }

    public void drawSilverBrick(Brick brick) {
        BufferedImage image;
        if (brick.isCracked())
            image = silverBrickCrackedImage;
        else image = silverBrickImage;
        graphics.drawImage(
                image,
                brick.getStartingPosition().getX() * 16 + 8,
                brick.getStartingPosition().getY() * 16 + 48,
                32,
                16,
                null);
    }

    public void drawWhiteBrick(Brick brick) {
        graphics.drawImage(
                whiteBrickImage,
                brick.getStartingPosition().getX() * 16 + 8,
                brick.getStartingPosition().getY() * 16 + 48,
                32,
                16,
                null);
    }

    public void drawWalls() {
        for (int y = 0; y < round.getPlayfield().getHeight(); y++)
            for (int x = 0; x < round.getPlayfield().getWidth(); x++)
                if (round.getPlayfield().isWall(x, y))
                    graphics.drawImage(wallImage, x * 16 + 8, y * 16 + 48, 16, 16, null);
    }
}
