package dp.pb.learn;

import java.util.HashMap;
import java.util.Map;

public class CchmTests {

	public static void main(String[] args) {

		       Map<Integer,Integer> hashmap = new HashMap<>();
		       int tt=13;
		       long begin1 = System.currentTimeMillis();
		       for(int i=0; i<1000000; i++){
		           tt = Math.abs(tt*(tt-i)-119);
		           hashmap.put(tt, tt);
		       }
		       System.out.println("time="+(System.currentTimeMillis() - begin1)+"ms.");

//		       
		       long begin2= System.currentTimeMillis();
		       for(int i=0; i<1000000; i++){
		    	  Object o =new Object();
		    	  o.getClass();
		    	  o.toString();
		       }  
		       System.out.println("time="+(System.currentTimeMillis() - begin2)+"ms.");
		    }

}
