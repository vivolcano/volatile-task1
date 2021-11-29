public class Toy extends Thread {

    private Box box;

    public Toy(String name, Box box) {
        super(name);
        this.box= box;
    }

    @Override
    public void run() {
        box.off();
    }
}
