# KotlinWeatherDataCodeKata
 
## Problem

The dataset below contains all available weather data for a particular month in a place with some very odd weather.
The first column is the day of the month, the second the maximum temperature on that day, and the third the minimum temperature.

 1. Create a data structure to hold the data below (including its category) and parse it into the structure.
 1. Print the weather data based on the max temperature, for each day of the month:
     * MaxTemp >= 85: print `Whew! Day XX was a scorcher! Max Temp: YY.`
     * MaxTemp < 85 && MaxTemp >= 60: print `Wow! Day XX was pleasant! Max Temp: YY.`
     * MaxTemp < 60: `CHILLY`: print `Brr! Day XX was cold! Max Temp: YY.`
     * If there is no data for a particular day, print: `Oops! No data for day XX.`
 1. Find the day with the max temp differential, and print its info.
 
 ## Dataset
``` 
1,88,59
2,79,63
3,77,55
4,77,59
5,90,66
7,55,53
8,75,54
9,86,32
10,84,64
11,91,59
12,88,73
13,70,59
15,64,55
16,79,59
17,47,30
18,82,52
19,81,61
20,84,57
21,86,59
22,90,64
23,50,32
25,90,72
26,97,64
29,88,66
30,90,45
```

## Solution

See [WeatherDataCodeKata.kt](WeatherDataCodeKata.kt).

## Sample Output

See [output.txt](output.txt).
