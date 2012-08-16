/**
 * @author liubida 2012-2-16 下午7:58:08
 */
public class Singleton {

    private Singleton(){
    }

    private static class instanceHolder {

        private static Singleton instance = new Singleton();
    }

    public static Singleton getInstance() {
        return instanceHolder.instance;
    }
    
    public static void main(String[] args) {
        Singleton s = getInstance();
        System.out.println(s);
        Singleton a = getInstance();
        System.out.println(a);
        
    }
}

class Singleton2 {

    private Singleton2(){
    }

    private static Singleton2 instance;

    public static synchronized Singleton2 getInstance() {
        if (null == instance) {
            instance = new Singleton2();
        }
        return instance;
    }
}
