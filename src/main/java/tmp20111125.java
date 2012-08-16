/**
 * @author liubida
 */

@SuppressWarnings("unused")
public class tmp20111125 extends father1125 implements field1125 {

    public static void main(String[] args) {
        try {
            String s = "12322224252222";
            String[] a = s.split("2");
            String[] b = "|DF|A|||".split("\\|");
            String[] c = "||DF|A|||".split("\\|");
            
            System.out.println();
            // System.out.println(b);
            // System.out.println(a);
        } catch (Exception e) {
            System.out.println("xiaochao");
        }
    }
}

class father1125 {

    static {
        System.out.println("i'm initialized");
    }
}

interface field1125 {

    int        a = 2;
    static int b = 2;
    public int c = 0;

}
