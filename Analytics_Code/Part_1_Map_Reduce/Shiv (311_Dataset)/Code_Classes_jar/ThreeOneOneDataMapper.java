import java.io.IOException;

import org.apache.hadoop.io.LongWritable;

import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Mapper;
import java.util.HashMap;



public class ThreeOneOneDataMapper extends Mapper<LongWritable, Text, Text, Text> {

	

	@Override

	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {



		String record = value.toString();
		String[] rowData = record.split(",");
		//Zip Code is present at 9th column
		String zipCode = rowData[8];
		int zipCodeInt = 0;
		HashMap<Integer,String> zipPrnMap = createLookUpTable();
		//To run the below for loop only once: at the first offset of the data set
		//This will decrease the amount of time and space taken by the mapper instances as 
		//the below code is run only once
		long minKey = 10000;
		if(key.get() < minKey){
			minKey = key.get();
			for(int zipKey : zipPrnMap.keySet()){
				context.write(new Text(zipPrnMap.get(zipKey)), new Text("NULL"));		
			}
		}
		try{

			zipCodeInt = Integer.parseInt(zipCode);

			//Range of Zip codes in NYC range from 10001 and 11697

			if((zipCodeInt <= 11697) && (zipCodeInt >= 10001)){
				//Converts NYC zip code to corresponding precinct
				String precinct = zipPrnMap.get(zipCodeInt);

				//Agency/Dept is present at 4th column

				String dept = rowData[3];

				if(precinct != null){

					context.write(new Text(precinct), new Text(dept));

				}
			}
		}catch(NumberFormatException ne){

			System.out.println("NumberFormatException");

		}

	}


	private HashMap<Integer,String> createLookUpTable(){

		HashMap<Integer,String> zipPrnMap = new HashMap<Integer,String>();

		zipPrnMap.put(10001,"10");

		zipPrnMap.put(10002,"7");

		zipPrnMap.put(10003,"9");

		zipPrnMap.put(10004,"1");

		zipPrnMap.put(10005,"1");

		zipPrnMap.put(10006,"1");

		zipPrnMap.put(10007,"1");

		zipPrnMap.put(10009,"9");

		zipPrnMap.put(10010,"13");

		zipPrnMap.put(10011,"10");

		zipPrnMap.put(10012,"6");

		zipPrnMap.put(10013,"1");

		zipPrnMap.put(10014,"6");

		zipPrnMap.put(10016,"17");

		zipPrnMap.put(10017,"17");

		zipPrnMap.put(10018,"14");

		zipPrnMap.put(10019,"18");

		zipPrnMap.put(10020,"20");

		zipPrnMap.put(10021,"19");

		zipPrnMap.put(10022,"17");

		zipPrnMap.put(10023,"20");

		zipPrnMap.put(10024,"20");

		zipPrnMap.put(10025,"24");

		zipPrnMap.put(10026,"26");

		zipPrnMap.put(10027,"26");

		zipPrnMap.put(10028,"30");

		zipPrnMap.put(10029,"23");

		zipPrnMap.put(10030,"32");

		zipPrnMap.put(10031,"33");

		zipPrnMap.put(10032,"33");

		zipPrnMap.put(10033,"34");

		zipPrnMap.put(10034,"34");

		zipPrnMap.put(10035,"25");

		zipPrnMap.put(10036,"18");

		zipPrnMap.put(10037,"32");

		zipPrnMap.put(10038,"1");

		zipPrnMap.put(10039,"30");

		zipPrnMap.put(10040,"34");

		zipPrnMap.put(10044,"114");

		zipPrnMap.put(10065,"19");

		zipPrnMap.put(10075,"19");

		zipPrnMap.put(10128,"23");

		zipPrnMap.put(10280,"1");

		zipPrnMap.put(10301,"120");

		zipPrnMap.put(10302,"121");

		zipPrnMap.put(10303,"121");

		zipPrnMap.put(10304,"122");

		zipPrnMap.put(10305,"122");

		zipPrnMap.put(10306,"122");

		zipPrnMap.put(10307,"123");

		zipPrnMap.put(10308,"122");

		zipPrnMap.put(10309,"123");

		zipPrnMap.put(10310,"120");

		zipPrnMap.put(10312,"123");

		zipPrnMap.put(10314,"121");

		zipPrnMap.put(10451,"40");

		zipPrnMap.put(10452,"44");

		zipPrnMap.put(10453,"46");

		zipPrnMap.put(10454,"40");

		zipPrnMap.put(10455,"42");

		zipPrnMap.put(10456,"42");

		zipPrnMap.put(10457,"46");

		zipPrnMap.put(10458,"52");

		zipPrnMap.put(10459,"41");

		zipPrnMap.put(10460,"48");

		zipPrnMap.put(10461,"49");

		zipPrnMap.put(10462,"43");

		zipPrnMap.put(10463,"50");

		zipPrnMap.put(10464,"45");

		zipPrnMap.put(10465,"45");

		zipPrnMap.put(10466,"47");

		zipPrnMap.put(10467,"52");

		zipPrnMap.put(10468,"52");

		zipPrnMap.put(10469,"49");

		zipPrnMap.put(10470,"47");

		zipPrnMap.put(10471,"50");

		zipPrnMap.put(10472,"43");

		zipPrnMap.put(10473,"43");

		zipPrnMap.put(10474,"41");

		zipPrnMap.put(10475,"45");

		zipPrnMap.put(11004,"105");

		zipPrnMap.put(11005,"105");

		zipPrnMap.put(11101,"114");

		zipPrnMap.put(11102,"114");

		zipPrnMap.put(11103,"114");

		zipPrnMap.put(11104,"108");

		zipPrnMap.put(11105,"108");

		zipPrnMap.put(11106,"114");

		zipPrnMap.put(11201,"84");

		zipPrnMap.put(11203,"67");

		zipPrnMap.put(11204,"66");

		zipPrnMap.put(11205,"88");

		zipPrnMap.put(11206,"90");

		zipPrnMap.put(11207,"75");

		zipPrnMap.put(11208,"75");

		zipPrnMap.put(11209,"68");

		zipPrnMap.put(11210,"63");

		zipPrnMap.put(11211,"90");

		zipPrnMap.put(11212,"73");

		zipPrnMap.put(11213,"77");

		zipPrnMap.put(11214,"62");

		zipPrnMap.put(11215,"78");

		zipPrnMap.put(11216,"79");

		zipPrnMap.put(11217,"78");

		zipPrnMap.put(11218,"72");

		zipPrnMap.put(11219,"66");

		zipPrnMap.put(11220,"68");

		zipPrnMap.put(11221,"81");

		zipPrnMap.put(11222,"94");

		zipPrnMap.put(11223,"62");

		zipPrnMap.put(11224,"60");

		zipPrnMap.put(11225,"71");

		zipPrnMap.put(11226,"67");

		zipPrnMap.put(11228,"68");

		zipPrnMap.put(11229,"61");

		zipPrnMap.put(11230,"70");

		zipPrnMap.put(11231,"76");

		zipPrnMap.put(11232,"72");

		zipPrnMap.put(11233,"81");

		zipPrnMap.put(11234,"63");

		zipPrnMap.put(11235,"61");

		zipPrnMap.put(11236,"69");

		zipPrnMap.put(11237,"83");

		zipPrnMap.put(11238,"79");

		zipPrnMap.put(11239,"75");

		zipPrnMap.put(11354,"109");

		zipPrnMap.put(11355,"109");

		zipPrnMap.put(11356,"109");

		zipPrnMap.put(11357,"109");

		zipPrnMap.put(11358,"109");

		zipPrnMap.put(11359,"109");

		zipPrnMap.put(11360,"111");

		zipPrnMap.put(11361,"111");

		zipPrnMap.put(11362,"111");

		zipPrnMap.put(11363,"111");

		zipPrnMap.put(11364,"111");

		zipPrnMap.put(11365,"107");

		zipPrnMap.put(11366,"107");

		zipPrnMap.put(11367,"109");

		zipPrnMap.put(11368,"110");

		zipPrnMap.put(11369,"115");

		zipPrnMap.put(11370,"115");

		zipPrnMap.put(11372,"115");

		zipPrnMap.put(11373,"110");

		zipPrnMap.put(11374,"112");

		zipPrnMap.put(11375,"112");

		zipPrnMap.put(11377,"108");

		zipPrnMap.put(11378,"104");

		zipPrnMap.put(11379,"104");

		zipPrnMap.put(11385,"104");

		zipPrnMap.put(11411,"105");

		zipPrnMap.put(11412,"101");

		zipPrnMap.put(11413,"105");

		zipPrnMap.put(11414,"106");

		zipPrnMap.put(11415,"102");

		zipPrnMap.put(11416,"102");

		zipPrnMap.put(11417,"106");

		zipPrnMap.put(11418,"102");

		zipPrnMap.put(11419,"106");

		zipPrnMap.put(11420,"106");

		zipPrnMap.put(11421,"102");

		zipPrnMap.put(11422,"105");

		zipPrnMap.put(11423,"105");

		zipPrnMap.put(11426,"105");

		zipPrnMap.put(11427,"105");

		zipPrnMap.put(11428,"105");

		zipPrnMap.put(11429,"105");

		zipPrnMap.put(11432,"103");

		zipPrnMap.put(11433,"103");

		zipPrnMap.put(11434,"113");

		zipPrnMap.put(11435,"103");

		zipPrnMap.put(11436,"113");

		zipPrnMap.put(11691,"101");

		zipPrnMap.put(11692,"100");

		zipPrnMap.put(11693,"100");

		zipPrnMap.put(11694,"100");

		zipPrnMap.put(11695,"100");

		zipPrnMap.put(11697,"100");
		
		//Dummy put for the precinct 5,22,28 which are not present in the mapping of zip code to precinct
		zipPrnMap.put(-1,"5");
		
		zipPrnMap.put(-2,"22");
		
		zipPrnMap.put(-3,"28");
		

		return zipPrnMap;

	}

	



}
