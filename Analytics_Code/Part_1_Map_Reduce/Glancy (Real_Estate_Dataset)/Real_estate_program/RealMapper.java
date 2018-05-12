import java.io.IOException;
import java.lang.*;
import java.util.StringTokenizer;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapred.*;
import java.util.*;

public class RealMapper extends Mapper<LongWritable, Text, Text, Text>
{
	@Override
      public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException
      {
			
          String line = value.toString();
	  String[] rowData = line.split(",");

//Hashmap for mapping neighbourhood to precinct
		HashMap<String,String> neighPrecinctMap = createLookUpTable();
		HashSet<String> precinctList = new HashSet<String>();
                for(String nei_key : neighPrecinctMap.keySet()){
						precinctList.add(neighPrecinctMap.get(nei_key));
                }

//sending zero with all precinct to avoid error if precinct not available
				for(String prec : precinctList){
					context.write(new Text(prec), new Text("0"));
				}
  
	try
	{
		//neighbourhood is present at 4th position
         	 String neighbour = rowData[3];
		
		//to find precinct of all neighbourhood
	 	 String precinct = neighPrecinctMap.get(neighbour);
			if (rowData.length>11)
			{
			//market value present at 12th position	
			  String cost= rowData[11];
				float cost1=Float.parseFloat(cost);
				if(precinct != null && cost!=null)
				{
			   		context.write(new Text(precinct), new Text(cost));
			
				}
			}
	}
	catch(NumberFormatException ne)
	{
		System.out.println("NumberFormatException");
	}
       }
	
	private HashMap<String,String> createLookUpTable()
	{
		HashMap<String,String> neighPrecinctMap = new HashMap<String,String>();
		neighPrecinctMap.put("AIRPORT LA GUARDIA","115");
		neighPrecinctMap.put("ALPHABET CITY","9");
		neighPrecinctMap.put("ARROCHAR-SHORE ACRES","120");
		neighPrecinctMap.put("ARVERNE","100");
		neighPrecinctMap.put("ASTORIA","114");
		neighPrecinctMap.put("BATH BEACH","62");
		neighPrecinctMap.put("BAY RIDGE","68");
		neighPrecinctMap.put("BAYCHESTER","47");
		neighPrecinctMap.put("BAYSIDE","111");
		neighPrecinctMap.put("BEDFORD PARK/NORWOOD","52");
		neighPrecinctMap.put("BEDFORD STUYVESANT","79");
		neighPrecinctMap.put("BEECHHURST","109");
		neighPrecinctMap.put("BELLE HARBOR","100");
		neighPrecinctMap.put("BELMONT","46");
		neighPrecinctMap.put("BENSONHURST","62");
		neighPrecinctMap.put("BERGEN BEACH","63");
		neighPrecinctMap.put("BOERUM HILL","84");
		neighPrecinctMap.put("BOROUGH PARK","66");
		neighPrecinctMap.put("BRIARWOOD","102");
		neighPrecinctMap.put("BRIGHTON BEACH","60");
		neighPrecinctMap.put("BRONXDALE","49");
		neighPrecinctMap.put("BROOKLYN HEIGHTS","84");
		neighPrecinctMap.put("BROWNSVILLE","73");
		neighPrecinctMap.put("BUSHWICK","83");
		neighPrecinctMap.put("BUSH TERMINAL","68");
		neighPrecinctMap.put("CASTLE HILL/UNIONPORT","43");
		neighPrecinctMap.put("CANARSIE","69");
		neighPrecinctMap.put("CARROLL GARDENS","76");
		neighPrecinctMap.put("CHELSEA","10");
		neighPrecinctMap.put("CHINATOWN","5");
		neighPrecinctMap.put("CITY ISLAND","45");
		neighPrecinctMap.put("CIVIC CENTER","5");
		neighPrecinctMap.put("CLINTON","18");
		neighPrecinctMap.put("CLINTON HILL","88");
		neighPrecinctMap.put("CLOVE LAKES","120");
		neighPrecinctMap.put("CO-OP CITY","45");
		neighPrecinctMap.put("COBBLE HILL","76");
		neighPrecinctMap.put("COBBLE HILL-WEST","76");
		neighPrecinctMap.put("COLLEGE POINT","109");
		neighPrecinctMap.put("CONCORD-FOX HILLS","122");
		neighPrecinctMap.put("CONEY ISLAND","60");
		neighPrecinctMap.put("CORONA","110");
		neighPrecinctMap.put("COUNTRY CLUB","49");
		neighPrecinctMap.put("CROTONA PARK","42");
		neighPrecinctMap.put("CROWN HEIGHTS","77");
		neighPrecinctMap.put("CYPRESS HILLS","75");
		neighPrecinctMap.put("DONGAN HILLS","122");
		neighPrecinctMap.put("DOUGLASTON","111");
		neighPrecinctMap.put("DOWNTOWN-FULTON FERRY","84");
		neighPrecinctMap.put("DOWNTOWN-METROTECH","84");
		neighPrecinctMap.put("EAST ELMHURST","115");
		neighPrecinctMap.put("EAST NEW YORK","75");
		neighPrecinctMap.put("EAST TREMONT","48");
		neighPrecinctMap.put("EAST VILLAGE","9");
		neighPrecinctMap.put("ELMHURST","115");
		neighPrecinctMap.put("FAR ROCKAWAY","101");
		neighPrecinctMap.put("FASHION","14");
		neighPrecinctMap.put("FIELDSTON","50");
		neighPrecinctMap.put("FINANCIAL","5");
		neighPrecinctMap.put("FLATBUSH-CENTRAL","67");
		neighPrecinctMap.put("FLATBUSH-EAST","67");
		neighPrecinctMap.put("FLATBUSH-LEFFERTS GARDEN","71");
		neighPrecinctMap.put("FLATBUSH-NORTH","67");
		neighPrecinctMap.put("FLATIRON","13");
		neighPrecinctMap.put("FLATLANDS","63");
		neighPrecinctMap.put("FLORAL PARK","105");
		neighPrecinctMap.put("FLUSHING MEADOW PARK","112");
		neighPrecinctMap.put("FLUSHING-NORTH","109");
		neighPrecinctMap.put("FLUSHING-SOUTH","107");
		neighPrecinctMap.put("FORDHAM","46");
		neighPrecinctMap.put("FOREST HILLS","112");
		neighPrecinctMap.put("FORT GREENE","88");
		neighPrecinctMap.put("FRESH MEADOWS","111");
		neighPrecinctMap.put("GLEN OAKS","105");
		neighPrecinctMap.put("GLENDALE","104");
		neighPrecinctMap.put("GRAMERCY","13");
		neighPrecinctMap.put("GRANT CITY","122");
		neighPrecinctMap.put("GRAVESEND","62");
		neighPrecinctMap.put("GREAT KILLS","123");
		neighPrecinctMap.put("GREAT KILLS-BAY TERRACE","123");
		neighPrecinctMap.put("GREENPOINT","94");
		neighPrecinctMap.put("GREENWICH VILLAGE-CENTRAL","6");
		neighPrecinctMap.put("GREENWICH VILLAGE-WEST","6");
		neighPrecinctMap.put("GRYMES HILL","120");
		neighPrecinctMap.put("HAMMELS","100");
		neighPrecinctMap.put("HARLEM-CENTRAL","28");
		neighPrecinctMap.put("HARLEM-EAST","23");
		neighPrecinctMap.put("HARLEM-UPPER","32");
		neighPrecinctMap.put("HARLEM-WEST","26");
		neighPrecinctMap.put("HIGHBRIDGE/MORRIS HEIGHTS","44");
		neighPrecinctMap.put("HILLCREST","107");
		neighPrecinctMap.put("HOLLIS","103");
		neighPrecinctMap.put("HOLLIS HILLS","111");
		neighPrecinctMap.put("HOLLISWOOD","107");
		neighPrecinctMap.put("HOWARD BEACH","106");
		neighPrecinctMap.put("HUNTS POINT","41");
		neighPrecinctMap.put("INWOOD","33");
		neighPrecinctMap.put("JACKSON HEIGHTS","115");
		neighPrecinctMap.put("JAMAICA","103");
		neighPrecinctMap.put("JAMAICA ESTATES","107");
		neighPrecinctMap.put("JAMAICA HILLS","103");
		neighPrecinctMap.put("JAVITS CENTER","18");
		neighPrecinctMap.put("KENSINGTON","66");
		neighPrecinctMap.put("KEW GARDENS","102");
		neighPrecinctMap.put("KINGSBRIDGE HTS/UNIV HTS","50");
		neighPrecinctMap.put("KINGSBRIDGE/JEROME PARK","52");
		neighPrecinctMap.put("KIPS BAY","13");
		neighPrecinctMap.put("LAURELTON","105");
		neighPrecinctMap.put("LITTLE ITALY","5");
		neighPrecinctMap.put("LITTLE NECK","111");
		neighPrecinctMap.put("LIVINGSTON","84");
		neighPrecinctMap.put("LONG ISLAND CITY","108");
		neighPrecinctMap.put("LOWER EAST SIDE","7");
		neighPrecinctMap.put("MADISON","14");
		neighPrecinctMap.put("MANHATTAN VALLEY","24");
		neighPrecinctMap.put("MARINE PARK","69");
		neighPrecinctMap.put("MASPETH","104");
		neighPrecinctMap.put("MELROSE/CONCOURSE","42");
		neighPrecinctMap.put("MIDDLE VILLAGE","104");
		neighPrecinctMap.put("MIDTOWN CBD","18");
		neighPrecinctMap.put("MIDTOWN EAST","17");
		neighPrecinctMap.put("MIDTOWN WEST","18");
		neighPrecinctMap.put("MIDWOOD","70");
		neighPrecinctMap.put("MORNINGSIDE HEIGHTS","26");
		neighPrecinctMap.put("MORRIS PARK/VAN NEST","49");
		neighPrecinctMap.put("MORRISANIA/LONGWOOD","41");
		neighPrecinctMap.put("MOTT HAVEN/PORT MORRIS","40");
		neighPrecinctMap.put("MOUNT HOPE/MOUNT EDEN","44");
		neighPrecinctMap.put("MURRAY HILL","13");
		neighPrecinctMap.put("NEW BRIGHTON","120");
		neighPrecinctMap.put("NEW BRIGHTON-ST. GEORGE","120");
		neighPrecinctMap.put("OAKLAND GARDENS","111");
		neighPrecinctMap.put("OCEAN HILL","73");
		neighPrecinctMap.put("OCEAN PARKWAY-NORTH","70");
		neighPrecinctMap.put("OCEAN PARKWAY-SOUTH","66");
		neighPrecinctMap.put("OLD MILL BASIN","63");
		neighPrecinctMap.put("PARK SLOPE","78");
		neighPrecinctMap.put("PARK SLOPE SOUTH","78");
		neighPrecinctMap.put("PARKCHESTER","43");
		neighPrecinctMap.put("PELHAM PARKWAY NORTH","49");
		neighPrecinctMap.put("PELHAM PARKWAY SOUTH","49");
		neighPrecinctMap.put("PROSPECT HEIGHTS","77");
		neighPrecinctMap.put("QUEENS VILLAGE","105");
		neighPrecinctMap.put("REGO PARK","112");
		neighPrecinctMap.put("RICHMOND HILL","102");
		neighPrecinctMap.put("RIVERDALE","50");
		neighPrecinctMap.put("ROCKAWAY PARK","100");
		neighPrecinctMap.put("ROSEBANK","120");
		neighPrecinctMap.put("SCHUYLERVILLE/PELHAM BAY","45");
		neighPrecinctMap.put("SHEEPSHEAD BAY","61");
		neighPrecinctMap.put("SO. JAMAICA-BAISLEY PARK","103");
		neighPrecinctMap.put("SOHO","1");
		neighPrecinctMap.put("SOUNDVIEW","43");
		neighPrecinctMap.put("SOUTH JAMAICA","103");
		neighPrecinctMap.put("SOUTHBRIDGE","1");
		neighPrecinctMap.put("SPRINGFIELD GARDENS","113");
		neighPrecinctMap.put("SUNNYSIDE","108");
		neighPrecinctMap.put("SUNSET PARK","72");
		neighPrecinctMap.put("TRIBECA","1");
		neighPrecinctMap.put("UPPER EAST SIDE (59-79)","19");
		neighPrecinctMap.put("UPPER EAST SIDE (79-96)","19");
		neighPrecinctMap.put("UPPER EAST SIDE (96-110)","19");
		neighPrecinctMap.put("UPPER WEST SIDE (59-79)","24");
		neighPrecinctMap.put("UPPER WEST SIDE (79-96)","24");
		neighPrecinctMap.put("UPPER WEST SIDE (96-116)","24");
		neighPrecinctMap.put("VAN CORTLANDT PARK","50");
		neighPrecinctMap.put("WASHINGTON HEIGHTS LOWER","33");
		neighPrecinctMap.put("WASHINGTON HEIGHTS UPPER","34");
		neighPrecinctMap.put("WEST NEW BRIGHTON","120");
		neighPrecinctMap.put("WESTCHESTER","43");
		neighPrecinctMap.put("WHITESTONE","109");
		neighPrecinctMap.put("WILLIAMSBRIDGE","47");
		neighPrecinctMap.put("WILLIAMSBURG-EAST","90");
		neighPrecinctMap.put("WILLIAMSBURG-SOUTH","90");
		neighPrecinctMap.put("WINDSOR TERRACE","72");
		neighPrecinctMap.put("WOODHAVEN","102");
		neighPrecinctMap.put("WOODLAWN","47");
		neighPrecinctMap.put("WOODSIDE","108");
		neighPrecinctMap.put("NULL1","20");
		neighPrecinctMap.put("NULL2","22");
		neighPrecinctMap.put("NULL3","25");
		neighPrecinctMap.put("NULL4","30");
		neighPrecinctMap.put("NULL5","81");
		neighPrecinctMap.put("DYKER HEIGHTS","68");
		neighPrecinctMap.put("GOWANUS","78");
 		neighPrecinctMap.put("MILL BASIN","63");
 		neighPrecinctMap.put("NEW SPRINGVILLE","121");
 		neighPrecinctMap.put("OZONE PARK","106");
 		neighPrecinctMap.put("RIDGEWOOD","104");
 		neighPrecinctMap.put("SILVER LAKE","120");
 		neighPrecinctMap.put("SOUTH OZONE PARK","106");
 		neighPrecinctMap.put("THROGS NECK","45");
 		neighPrecinctMap.put("TOMPKINSVILLE","120");
 		neighPrecinctMap.put("WILLIAMSBURG-CENTRAL","90");
 		neighPrecinctMap.put("WILLIAMSBURG-NORTH","90");
 		neighPrecinctMap.put("WYCKOFF HEIGHTS","83");

		return neighPrecinctMap;
	}
}
                                                                                
	
