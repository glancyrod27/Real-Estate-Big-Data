import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class RealReducer extends Reducer<Text, Text, Text, Text> {

	@Override
	public void reduce(Text key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException 
		{
			
			double count=0;
			double sum=0;
			double temp;
			double avg;
			String str2;
			String str1;
			for (Text value : values)
			{
			str2=value.toString();
			temp=Double.parseDouble(str2);

			//ignoring if no value present any precinct key 
			if(temp!=0.0)
			{
				sum=sum+temp;
				count=count+1;
			}
			}
			//to avoid divide by zero exception
			if(count==0)
			context.write(key, new Text("0"));				
			else
			{
				avg=sum/count;
				str1=avg+"";
				//sending precint as key and average market value as value
				context.write(key, new Text(str1));
			}
		
	}
}

