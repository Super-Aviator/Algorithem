import java.util.Arrays;

public class StopWatch {
    private static final double T=1.0e9;
    private long start;
    public StopWatch(){
        start=System.nanoTime();
    }

    public double elapsedTime(){
        return (System.nanoTime()-start)/T;
    }

    public static void main(String[] args) {
        StopWatch tc=new StopWatch();
        for(int i=0;i<10;i++)
            Arrays.sort(new int[]{1,2,3,4,5,6,6});
        System.out.println(tc.elapsedTime());
    }
}
