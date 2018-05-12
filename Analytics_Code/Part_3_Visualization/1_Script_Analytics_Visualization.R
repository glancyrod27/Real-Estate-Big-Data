require(stats)
dir = "/Users/Pratik/Desktop/BigData/"
setwd(dir)
getwd()
library(ggplot2)


#Reading Datasets
res_311_2008 = read.csv("311_2008.csv", header = FALSE)
res_311_2009 = read.csv("311_2009.csv", header = FALSE)
res_311_2010 = read.csv("311_2010.csv", header = FALSE)
res_311_2011 = read.csv("311_2011.csv", header = FALSE)
res_311_2012 = read.csv("311_2012.csv", header = FALSE)

res_crime_2008 = read.csv("major_crime_2008.csv", header = FALSE)
res_crime_2009 = read.csv("major_crime_2009.csv", header = FALSE)
res_crime_2010 = read.csv("major_crime_2010.csv", header = FALSE)
res_crime_2011 = read.csv("major_crime_2011.csv", header = FALSE)
res_crime_2012 = read.csv("major_crime_2012.csv", header = FALSE)

res_property_2008_09 = read.csv("real_estate_2008_09.csv", header = FALSE)
res_property_2009_10 = read.csv("real_estate_2009_10.csv", header = FALSE)
res_property_2010_11 = read.csv("real_estate_2010_11.csv", header = FALSE)
res_property_2011_12 = read.csv("real_estate_2011_12.csv", header = FALSE)

x = 33
ext_311_values = c()
ext_crime_values = c()
ext_property_values = c()

for (i in 1:nrow(res_311_2008)) {
  if(x==res_311_2008[i,1]){
    ext_311_values <- c(ext_311_values, res_311_2008[i,2])
    break
  }
}
for (i in 1:nrow(res_311_2009)) {
  if(x==res_311_2009[i,1]){
    ext_311_values <- c(ext_311_values, res_311_2009[i,2])
    break
  }
}
for (i in 1:nrow(res_311_2010)) {
  if(x==res_311_2010[i,1]){
    ext_311_values <- c(ext_311_values, res_311_2010[i,2])
    break
  }
}
for (i in 1:nrow(res_311_2011)) {
  if(x==res_311_2011[i,1]){
    ext_311_values <- c(ext_311_values, res_311_2011[i,2])
    break
  }
}
for (i in 1:nrow(res_311_2012)) {
  if(x==res_311_2012[i,1]){
    ext_311_values <- c(ext_311_values, res_311_2012[i,2])
    break
  }
}
  
##
for (i in 1:nrow(res_crime_2008)) {
  if(x==res_crime_2008[i,1]){
    ext_crime_values <- c(ext_crime_values, res_crime_2008[i,2]+res_crime_2008[i,3]+
                          res_crime_2008[i,4]+res_crime_2008[i,5]+res_crime_2008[i,6]+
                        res_crime_2008[i,7]+res_crime_2008[i,8])
    break
  }
}
for (i in 1:nrow(res_crime_2009)) {
  if(x==res_crime_2009[i,1]){
    ext_crime_values <- c(ext_crime_values, res_crime_2009[i,2]+res_crime_2009[i,3]+
                          res_crime_2009[i,4]+res_crime_2009[i,5]+res_crime_2009[i,6]+
                        res_crime_2009[i,7]+res_crime_2009[i,8])
    break
  }
}
for (i in 1:nrow(res_crime_2010)) {
  if(x==res_crime_2010[i,1]){
    ext_crime_values <- c(ext_crime_values, res_crime_2010[i,2]+res_crime_2010[i,3]+
                          res_crime_2010[i,4]+res_crime_2010[i,5]+res_crime_2010[i,6]+
                        res_crime_2010[i,7]+res_crime_2010[i,8])
    break
  }
}
for (i in 1:nrow(res_crime_2011)) {
  if(x==res_crime_2011[i,1]){
    ext_crime_values <- c(ext_crime_values, res_crime_2011[i,2]+res_crime_2011[i,3]+
                          res_crime_2011[i,4]+res_crime_2011[i,5]+res_crime_2011[i,6]+
                        res_crime_2011[i,7]+res_crime_2011[i,8])
    break
  }
}
for (i in 1:nrow(res_crime_2012)) {
  if(x==res_crime_2012[i,1]){
    ext_crime_values <- c(ext_crime_values, res_crime_2012[i,2]+res_crime_2012[i,3]+
                          res_crime_2012[i,4]+res_crime_2012[i,5]+res_crime_2012[i,6]+
                        res_crime_2012[i,7]+res_crime_2012[i,8])
    break
  }
}

##
for (i in 1:nrow(res_property_2008_09)) {
  if(x==res_property_2008_09[i,1]){
    ext_property_values <- c(ext_property_values, res_property_2008_09[i,4])
    break
  }
}
for (i in 1:nrow(res_property_2009_10)) {
  if(x==res_property_2009_10[i,1]){
    ext_property_values <- c(ext_property_values, res_property_2009_10[i,4])
    break
  }
}
for (i in 1:nrow(res_property_2010_11)) {
  if(x==res_property_2010_11[i,1]){
    ext_property_values <- c(ext_property_values, res_property_2010_11[i,4])
    break
  }
}
for (i in 1:nrow(res_property_2011_12)) {
  if(x==res_property_2011_12[i,1]){
    ext_property_values <- c(ext_property_values, res_property_2011_12[i,4])
    break
  }
}

#ext_311_values.timeseries <- ts(ext_311_values[2:5],start = c(2009,1),frequency = 1)
#plot(ext_311_values.timeseries,xlab="", ylab="",xaxt='n', yaxt='n')
#axis(1, at = c(2009:2012))

#combined_property_311 <- matrix(c(ext_311_values[2:5],ext_property_values),nrow = 4)
#combined_property_311.timeseries <- ts(combined_property_311 ,start = c(2009,1),frequency = 1)
#plot(combined_property_311.timeseries,xlab="", ylab="",xaxt='n', yaxt='n',
#     main = "Series 1: 311 & Series 2: Property")
#axis(1, at = c(2009:2012))

combined_property_crime <- matrix(c(ext_crime_values[2:5],ext_property_values),nrow = 4)
combined_property_crime.timeseries <- ts(combined_property_crime ,start = c(2009,1),frequency = 1)
plot(combined_property_crime.timeseries,xlab="", ylab="",xaxt='n', yaxt='n',
     main = "Series 1: Crime & Series 2: Property")
axis(1, at = c(2009:2012))

combined_all <- matrix(c(ext_crime_values[2:5],ext_311_values[2:5], ext_property_values),nrow = 4)
combined_all.timeseries <- ts(combined_all ,start = c(2009,1),frequency = 1)
plot(combined_all.timeseries,xlab="", ylab="",xaxt='n', yaxt='n',
     main = "Crime(Series 1), 311(Series 2) & Property(Series 3)")
axis(1, at = c(2009:2012))