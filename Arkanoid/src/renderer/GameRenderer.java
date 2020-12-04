package renderer;

public interface GameRenderer {
    default void renderGame() {
        renderScore();
        renderPlayfield();
    }

    void renderScore();

    void renderPlayfield();

    void renderResult();
}
