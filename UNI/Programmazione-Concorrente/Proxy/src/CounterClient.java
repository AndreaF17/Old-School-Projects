import interfaces.ServerInterface;

public class CounterClient {
    public static void main(String[] args) throws Exception {
        ServerInterface server = null;
        try {
            server = new ProxyServer();
            int init = server.reset();
            System.out.println("reset: "+ init);
            long startTime = System.currentTimeMillis();
        for(int i = 0; i < 1000; i++){
            int r = server.increment();
            System.out.println("increment: "+ r);
        }
        long endTime = System.currentTimeMillis();

        System.out.println("Elapsed time: "+(endTime-startTime)+"ms");
        } finally { 
            server.close(); 
        }
    }
}
