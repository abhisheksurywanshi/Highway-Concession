package toll.tcm.testData;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import toll.tcm.Hardware.WeightInsert;
import toll.tcm.MOPs.*;
import toll.tcm.testCases.*;
public class SequentialExcelTransaction extends BaseClass 
{
	static Logger logger=LogManager.getLogger(SequentialExcelTransaction.class);

	public SequentialExcelTransaction() throws Exception {
		int total=Seq.numberoftransaction().length;
		int sum=0;
		GetTag t=new GetTag();
		logger.info("Tag transaction is :"+Seq.numberoftransaction()[0]);
		logger.info("Cash transaction is :"+Seq.numberoftransaction()[1]);
		logger.info("Exempt transaction is :"+Seq.numberoftransaction()[2]);
		logger.info("freeConvoy transaction is :"+Seq.numberoftransaction()[3]);
		logger.info("Violation transaction is :"+Seq.numberoftransaction()[4]);
		logger.info("NSV transaction is :"+Seq.numberoftransaction()[5]);
		for(int j=Seq.numberoftransaction()[0]-(Seq.numberoftransaction()[0]-1);j<=Seq.numberoftransaction()[0];j++)
		{
			
			if(Seq.numberoftransaction()[0]==0)
			{
				
			}
			else
			{
				t.getTagData();
				
			}
			
		}
		for(int j=Seq.numberoftransaction()[1]-(Seq.numberoftransaction()[1]-1);j<=Seq.numberoftransaction()[1];j++)
		{
			if(Seq.numberoftransaction()[1]==0)
			{
				
			}
			else
			{
				CashTransaction.randomCashTransaction();
			}
			
		}
		for(int j=Seq.numberoftransaction()[2]-(Seq.numberoftransaction()[2]-1);j<=Seq.numberoftransaction()[2];j++)
		{
			if(Seq.numberoftransaction()[2]==0)
			{
				
			}
			else
			{
				ExemptTransaction.randomExempt();
			}
		}
		for(int j=Seq.numberoftransaction()[3]-(Seq.numberoftransaction()[3]-1);j<=Seq.numberoftransaction()[3];j++)
		{
			if(Seq.numberoftransaction()[3]==0)
			{
				
			}
			else
			{
				System.out.println("convoy count"+j);
				FreeConvoy.freeConvoy(1);
			}
			
		}
		for(int j=Seq.numberoftransaction()[4]-(Seq.numberoftransaction()[4]-1);j<=Seq.numberoftransaction()[4];j++)
		{
			if(Seq.numberoftransaction()[4]==0)
			{
				
			}
			else
			{
				Violation.randomViolation();
			}
			
		}
		for(int j=Seq.numberoftransaction()[5]-(Seq.numberoftransaction()[5]-1);j<=Seq.numberoftransaction()[5];j++)
		{
			if(Seq.numberoftransaction()[5]==0)
			{
				
			}
			else
			{
				NSV.toNSV();
			}
			
		}
		
		for(int i=0;i<total;i++)
		{
			sum=sum+Seq.numberoftransaction()[i];
			
		
		}
		logger.info("Total sum of Transaction is"+sum);
	}
	public static void  inconsequentExcelTransaction() throws Exception
	{
		int ETC=Seq.numberoftransaction()[0];
		int Cash=Seq.numberoftransaction()[1];
		int Exempt=Seq.numberoftransaction()[2];
		int freeConvoy=Seq.numberoftransaction()[3];
		int Violation=Seq.numberoftransaction()[4];
		int NSV=Seq.numberoftransaction()[5];
		int total=0;
		for(int i=0;i<Seq.numberoftransaction().length;i++)
		{
		 total=total+Seq.numberoftransaction()[i];
		}
		
		System.out.printf("%d, %d, %d, %d, %d, %d, %d,",ETC, Cash, Exempt,freeConvoy,Violation,NSV,total);
		System.out.println("total"+total);
		RandomSpecificTransaction(ETC, Cash, Exempt,freeConvoy,Violation,NSV,total);
	}
	public static void  RandomSpecificTransaction(int LastETC,int LastSJ,int LastEx,int LastfreeConvoy,int Lastviolation,int Lastnsv,int LastTotal) throws Exception

	{


		int ETC=0,SJ=0,Ex=0,Total=0,freeConvoy=0,violation=0,nsv=0;

		GetTag t=new GetTag();

		if(LastETC+LastSJ+LastEx+LastfreeConvoy+Lastviolation+Lastnsv!=LastTotal)

		{

			System.out.println("Somthing went Wrong please check total transaction Calculation");

		}

		else

		{

			while(Total!=LastTotal)

			{

				int randomNumber = randomGenerator(1,6);

				

				switch(randomNumber)

				{

				case 1:

					if(Total!=LastTotal&&ETC!=LastETC)

					{

						    WeightInsert.insertWeight();
							t.getTagData();
							ETC++;

							System.out.println("RandomNumber: "+randomNumber);

							System.out.println("ETC:"+ETC);

							Total++;

						

					}

					

					continue;

				case 2:

					 if ( Total!=LastTotal &&SJ!=LastSJ)

					{

						 
						 WeightInsert.insertWeight();
								SJ++;
								CashTransaction.randomCashTransaction();
								System.out.println("RandomNumber: "+randomNumber);

								System.out.println("SJ:"+SJ);

								Total++;

					}


					
					continue;

				case 3:

					if ( Total!=LastTotal & Ex!=LastEx)

					{

						

							Ex++;
							 WeightInsert.insertWeight();
							ExemptTransaction.randomExempt();
							System.out.println("RandomNumber: "+randomNumber);

							System.out.println("Ex:"+Ex);

							Total++;

					}
					continue;
				case 4:

					if ( Total!=LastTotal & freeConvoy!=LastfreeConvoy)

					{

						 
						  WeightInsert.insertWeight();
						    freeConvoy++;
						    
							System.out.println("RandomNumber: "+randomNumber);

							System.out.println("freeConvoy:"+LastfreeConvoy);

							Total++;
							FreeConvoy.freeConvoy(1);
					}
					continue;
				case 5:

					if ( Total!=LastTotal & violation!=Lastviolation)

					{

						
						 WeightInsert.insertWeight();
						    violation++;
						    Violation.randomViolation();
							System.out.println("RandomNumber: "+randomNumber);

							System.out.println("violation:"+Lastviolation);

							Total++;

					}
					continue;
				case 6:

					if ( Total!=LastTotal & nsv!=Lastnsv)

					{

						
						 WeightInsert.insertWeight();
						    nsv++;
						    NSV.getNonSchedualVehicleKey();
							System.out.println("RandomNumber: "+randomNumber);

							System.out.println("nsv:"+Lastviolation);

							Total++;

					}	
					continue;

				}

				

				

			}

			

			

				System.out.println("LastTotal:"+LastTotal+" Total:"+Total+" ETC:"+ETC+" SJ:"+SJ+" Ex:"+Ex+" freeConvoy:"+freeConvoy+" violation:"+violation+" nsv:"+nsv);

				System.out.println(LastTotal==Total);

			

			

		

		}

		

	}

}
