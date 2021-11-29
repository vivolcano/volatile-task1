public class User extends Thread {

    private Box box;

    public User(String name, Box box) {
        super(name);
        this.box= box;
    }

    @Override
    public void run() {
        box.on();
    }
}
