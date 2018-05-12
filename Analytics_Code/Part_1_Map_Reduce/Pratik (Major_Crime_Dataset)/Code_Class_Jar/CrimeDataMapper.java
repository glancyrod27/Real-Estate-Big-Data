import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CrimeDataMapper extends Mapper<LongWritable, Text, Text, Text> {

	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

		String record = value.toString();
		String[] rowData = record.split(",");

		//Precinct is present at 15th column
		String precinct = rowData[14];
		String year = rowData[6];
		try{
		if(Integer.parseInt(precinct) > 0 && Integer.parseInt(year)==2012){
			String crimeType = rowData[11];
			context.write(new Text(precinct), new Text(crimeType));
		}
		}catch(NumberFormatException ne){

			System.out.println("NumberFormatException");

		}

	}
}
