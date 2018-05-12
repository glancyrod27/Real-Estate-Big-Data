import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ThreeOneOneDataReducer extends Reducer<Text, Text, Text, Text> {

	@Override
	public void reduce(Text key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {
			
		//Only few departments listed below are counted as the complaints that are files in others is either 
		//specific to the building/Apt or irrelevant to the area 
		String[] deptNames = {"NULL","DEP","DOE","DOHMH","DOITT","DOT","DPR","DSNY","EDC","NYPD","TLC"};
		//count corresponding to each dept
		int count = 0;
		boolean foundValidDept = false;
		for (Text value : values) {
			//NULL CHECK
			if(value.equals(new Text(deptNames[0]))){
				foundValidDept = true;
			}else{
				for(int i = 1; i < 11; i++){
					if(value.equals(new Text(deptNames[i]))){
						count++;
						foundValidDept = true;
						break;
					}
				}
			}
		}
		if(foundValidDept){
			String deptsCount = "";
			deptsCount += count+"";
			context.write(key, new Text(deptsCount));
		}
	}
}
