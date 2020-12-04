package renderer;

import game.GameRound;
import objects.Brick;
import objects.SilverBrick;

public class ConsoleRenderer implements GameRenderer {
    private final GameRound round;

    public ConsoleRenderer(GameRound round) {
        this.round = round;
    }

    @Override
    public void renderScore() {
        System.out.println("SCORE: " + round.getScore());
    }

    @Override
    public void renderPlayfield() {
        for (int y = 0; y < round.getPlayfield().getHeight(); y++) {
            for (int x = 0; x < round.getPlayfield().getWidth(); x++) {
                if (round.getVaus().isAt(x, y)) {
                    renderVaus(x);
                    x = increasedX(x, round.getVaus().getSize());
                    continue;
                }
                if (!round.getBricks().isEmpty())
                    for (Brick brick : round.getBricks())
                        if (brick.isAt(x, y)) {
                            renderBrick(brick, x);
                            x = increasedX(x, Brick.getSize());
                            break;
                        }
                if (round.getBall().isAt(x, y)) {
                    System.out.print("o  ");
                    continue;
                }
                if (round.getBricks().isEmpty()) {
                    if (round.getPlayfield().isEmpty(x, y))
                        System.out.print("   ");
                    if (round.getPlayfield().isWall(x, y))
                        System.out.print("*  ");
                } else if (!round.isBrickAt(round, x, y)) {
                    if (round.getPlayfield().isEmpty(x, y))
                        System.out.print("   ");
                    if (round.getPlayfield().isWall(x, y))
                        System.out.print("*  ");
                }
            }
            System.out.println();
        }
    }

    @Override
    public void renderResult() {
        System.out.println("\n" + round.getResult());
    }

    public void renderVaus(int x) {
        for (int i = x; i < x + round.getVaus().getSize(); i++)
            System.out.print("T  ");
    }

    public void renderBrick(Brick brick, int x) {
        for (int i = x; i < x + Brick.getSize(); i++)
            if (brick instanceof SilverBrick)
                System.out.print("@  ");
            else System.out.print("#  ");
    }

    public int increasedX(int x, int objectSize) {
        return x + objectSize - 1;
    }
}
