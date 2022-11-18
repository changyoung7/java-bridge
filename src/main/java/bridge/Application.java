package bridge;

public class Application {

    public static void main(String[] args) {
        BridgeGame game = new BridgeGame();

        try {
            game.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
