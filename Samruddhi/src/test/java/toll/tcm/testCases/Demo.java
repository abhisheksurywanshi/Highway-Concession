package toll.tcm.testCases;

public class Demo extends BaseClass {
	public static void main(String[] args) {
		
		String[] keysArray=new String[3];
		keysArray[0]="121";
		keysArray[1]="122";
		keysArray[2]="123";
		int j=0;
		System.out.println(System.getProperty("sun.arch.data.model"));
		OtherCashKey=2;
		 for (int i = 1; i <=4; i++)
		 {
			 int count=1;
	    		System.out.println("condition "+i);
	    		for(int k=1;k<=8;k++)
				{
	    			int FromWeight =7501;
		    		int toWeight=11991;
		    		
		    		System.out.println("weight is noted.... and  vehicle is selected "+k);
		    		do {
						 
						
								
								
								String OtherCashkey = keysArray[j];
								System.out.println(OtherCashkey+" mop is selected and transaction completed ");
								count++;
							
						   
						 if(count!=OtherCashKey)
						 {
							 System.out.println("break");
							 j++;
							 break;
						 }
							 
					 }while(OtherCashKey>=count);
		    		if(count!=OtherCashKey)
		    		{
		    			j=count;
		    			break;
		    		}
		    		
				 
				 }
	    		
	    		
		 }
	}

}
