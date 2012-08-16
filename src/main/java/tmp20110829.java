/**
 * @author liubida
 */
public class tmp20110829 {
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        tmp20110829 t = new tmp20110829();
        int a = 0xffffffff;
        System.out.println(a);
        System.out.println(((byte) a) == 0xff);
        System.out.println(((char) a) == 0xffff);
        System.out.println('\0');
        System.out.println((char) 58);
        new tmp20110831();
    }
}

interface B {
    void r();
}

abstract class A {
    public abstract void p();
}

