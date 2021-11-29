import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class Box {

    // if true - box is open, false - box is closed
    public volatile boolean status;

    private final int WAITING_USER = 1500;
    private final int WAITING_TOY = 1000;
    private final int MAX_NUMBER_OF_SHUTDOWN = 10;
    private int currentNumberOfShutdowns = 0;
    private final String[] MESSAGES =
            {": Ай-яй!", ": Остановитесь!", ": Не делай так!", ": Не приятно, но ладно!", ": Закрываю!", "......"};
    private final Random RANDOM = new Random();

    public void on() {
        try {
            while (currentNumberOfShutdowns < MAX_NUMBER_OF_SHUTDOWN - 1) {
                Thread.sleep(WAITING_USER);
                if (!status) {
                    status = true;
                    System.out.println(Thread.currentThread().getName() + ": открывает коробку.");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void off() {
        try {
            while (currentNumberOfShutdowns != MAX_NUMBER_OF_SHUTDOWN) {
                Thread.sleep(WAITING_TOY);
                if (status) {
                    System.out.println(Thread.currentThread().getName() +
                            MESSAGES[RANDOM.nextInt(MESSAGES.length - 1)]);
                    Thread.sleep(WAITING_TOY);
                    System.out.println(Thread.currentThread().getName() + ": закрыла коробку!");
                    status = false;
                    currentNumberOfShutdowns++;
                    Thread.sleep(WAITING_TOY);
                }
            }

            System.out.println(Thread.currentThread().getName() + ": Открывай сколько хочешь! Я сдаюсь!");
            Thread.sleep(WAITING_TOY);
            Thread.sleep(WAITING_TOY);
            Desktop.getDesktop().browse(new URI("https://www.youtube.com/watch?v=A6oyj2R3xtM"));
            Thread.currentThread().interrupt();

        } catch (InterruptedException | URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }
}