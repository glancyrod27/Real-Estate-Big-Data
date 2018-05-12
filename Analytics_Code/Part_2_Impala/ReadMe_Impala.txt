1. The results of Map_Reduce are saved in .csv files and used as input to impala.
2. These .csv files are used to create tables in impala. (See file 'Impala_Create_Tables.txt' for queries)
3. Various combinations of queries are run using these tables.
4. For example: 
	a. Get top 20 precincts with lowest 311 complaints in each borough
	b. From part (a) results, get top 10 precincts with lowest property prices in each borough
	c. From part (b) results, get top 5 precincts with lowest major crimes.
5. Similar to 4, different combination of queries is run.
6. Query Results is stored for each year from 2009 to 2012 (borough-wise) in following files:
	'Impala_2009_Results.txt'
	'Impala_2010_Results.txt'
	'Impala_2011_Results.txt'
	'Impala_2012_Results.txt'
