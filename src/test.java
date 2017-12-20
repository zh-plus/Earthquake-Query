public class test {
    public static void main(String[] args) {
        test t = new test();
        t.method();
    }

    public void method()
    {
        String path = getClass().getResource("Viewer/MyStyle.css").getPath();
        System.out.println("test");
        System.out.println(path);
    }
}
