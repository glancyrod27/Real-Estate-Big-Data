import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class CrimeDataReducer extends Reducer<Text, Text, Text, Text> {

	@Override
	public void reduce(Text key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {

		//Only few departments listed below are counted as the complaints that are files in others is either
		//specific to the building/Apt or irrelevant to the area
		String[] crimeType = {"BURGLARY", "ROBBERY","GRAND LARCENY OF MOTOR VEHICLE", "GRAND LARCENY",
												  "FELONY ASSAULT", "RAPE","MURDER & NON-NEGL. MANSLAUGHTE"};
		//count corresponding to each dept
		int[] count = {0,0,0,0,0,0,0};
		boolean foundValid = false;

		for (Text value : values) {
			for(int i = 0; i < 7; i++){
				if(value.equals(new Text(crimeType[i]))){
					count[i]++;
					foundValid = true;
					break;
				}
			}
		}
		if(foundValid){
			//String crimeCount = "";
			//for(int i = 0; i < 6; i++){
			//		crimeCount += count[i]+",";
			//}
			//crimeCount += count[6];

			//Crimes are ranked from 1 to 3; 3 being extreme
			float WMA_Value =  (float) count[0]*1.0f + count[1]*1.0f + count[2]*2.0f + count[3]*2.0f +
						count[4]*3.0f + count[5]*4.0f + count[6]*4.0f;
			WMA_Value = WMA_Value/17;
			context.write(key, new Text(String.valueOf(WMA_Value)));
		}
	}
}
