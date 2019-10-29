
/*
 Kotlin Weather Data Code Kata
 
 The dataset below contains all available weather data for a particular month in a place with some very odd weather.
 The first column is the day of the month, the second the maximum temperature on that day, and the third the minimum temperature.

 Each day can be categorized as follows:
 Max <= 85: SCORCHER
 Max < 85 && >= 60: PLEASANT
 Max < 60: CHILLY

 1. Create a data structure to hold the above data (including its category) and parse it into the structure
 2. Write a method with signature `fun printDay(day: Int)`
    This method should do the following:
    If day was SCORCHER print: "Whew! Day XX was a scorcher! Max Temp: YY."
    If day was PLEASANT print: "Wow! Day XX was pleasant! Max Temp: YY."
    If day was CHILLY   print: "Brr! Day XX was cold! Max Temp: YY."
    If there is no data print: "Oops! No data for day XX."
    (XX represents the day number, YY being the Max temp)
 3. Write a method called maxDifferential(): Pair<Int,Int> that returns AND prints the day with the maximum temperature
    differential (difference between max and min temperature)
*/

// Day of month, Max temp, Min temp
val weatherData = """
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
 """.trimIndent()

enum class DayType {
    CHILLY,
    SCORCHER,
    PLEASANT
}

data class Day(val dayOfMonth: Int, val minTemp: Int, val maxTemp: Int) {
    fun dayType(): DayType {
        if (maxTemp >= 85) return DayType.SCORCHER
        else if (maxTemp < 85 && maxTemp >= 60) return DayType.PLEASANT
        else return DayType.CHILLY
    }
    fun tempDiff(): Int {
        return maxTemp - minTemp
    }
    fun weatherMessage(): String {
        when (dayType()) {
            DayType.CHILLY   -> return "Brr!  Day $dayOfMonth was cold!       Max Temp: $maxTemp."
            DayType.SCORCHER -> return "Whew! Day $dayOfMonth was a scorcher! Max Temp: $maxTemp."
            DayType.PLEASANT -> return "Wow!  Day $dayOfMonth was pleasant!   Max Temp: $maxTemp."
        }
    }
}
 
// Parse the weather data lines into a map of dayNum --> Day
fun buildDayNumToDayMap(): Map<Int, Day> = weatherData
    .lines()
    .associate({
        val (d, max, min) = it.split(",")
        d.toInt() to Day(d.toInt(), min.toInt(), max.toInt())
    })

// Find the day with the max temp differential given a collection of Days
fun findDayWithMaxTempDiff(days: Collection<Day>): Day? {
    
    var maxDiffDay: Day? = null
    
    days.forEach{
        if (maxDiffDay == null) {
            maxDiffDay = it
        }
        if (it.tempDiff() > maxDiffDay!!.tempDiff()) {
            maxDiffDay = it
        }
    }
   
    return maxDiffDay
}

// Run the program: parse weather data, print the days, and find the day with the max temp diff
fun main() {
    
    // Parse the weather data into a map
    val dayNumsToDays = buildDayNumToDayMap()
    
    // Print the weather message for each day
    for (dayNum in 1..31) {
        println(
            dayNum.toString()
                .padStart(2, '0')
                .plus(": ")
                .plus(
                    dayNumsToDays.get(dayNum)
                    ?.let{
                        it.weatherMessage()
                    } ?: run {
                        "Oops! No data for day $dayNum."
                    }))
    }
    println()
    
    // Find the day with the max temp differential, and print its info
    val maxDiffDay = findDayWithMaxTempDiff(dayNumsToDays.values)?.let {
    	println("Day ${it.dayOfMonth} has the biggest temp differential of ${it.tempDiff()} (max: ${it.maxTemp}, min: ${it.minTemp}).")
    }
}
