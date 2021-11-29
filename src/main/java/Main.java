public class Main {
    public static void main(String[] args) throws InterruptedException {

        Box box = new Box();
        Toy toy = new Toy("Игрушка", box);
        User user = new User("Пользователь", box);

        toy.start();
        user.start();
        user.join();

    }
}
