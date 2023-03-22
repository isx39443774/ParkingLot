package parking

fun main() {
    val parking = ParkingLot()
    while (true) {
        val input = readln().split(" ")
        val command = input[0]
        when (command) {
            "exit" -> break
            "create" -> parking.create(input[1].toInt())
        }
        if (parking.created()) {
            when (command) {
                "park" -> parking.park(Car(input[1], input[2]))
                "leave" -> parking.leave(input[1].toInt())
                "status" -> parking.status()
                "reg_by_color" -> parking.regByColor(input[1])
                "spot_by_color" -> parking.spotByColor(input[1])
                "spot_by_reg" -> parking.spotByReg(input[1])
            }
        } else {
            println("Sorry, a parking lot has not been created.")
        }

    }
}

data class Car(val plate: String, val color: String)

class ParkingLot() {

    private var parkingSpots = mutableListOf<String>()

    fun regByColor(color:String) {
        val allRegs = mutableListOf<String>()
        for (i in parkingSpots.indices) {
            if (parkingSpots[i] != "") {
                if (color.equals(parkingSpots[i].split(" ")[1], ignoreCase = true))
                    allRegs.add(parkingSpots[i].split(" ")[0])
            }
        }
        if (allRegs.size == 0) {
            println("No cars with color $color were found.")
        } else {
            println(allRegs.joinToString(separator = ", "))
        }
    }

    fun spotByColor(color: String) {
        val allRegs = mutableListOf<Int>()
        for (i in parkingSpots.indices) {
            if (parkingSpots[i] != "") {
                if (color.equals(parkingSpots[i].split(" ")[1], ignoreCase = true))
                    allRegs.add(i+1)
            }
        }
        if (allRegs.size == 0) {
            println("No cars with color $color were found.")
        } else {
            println(allRegs.joinToString(separator = ", "))
        }
    }


    fun spotByReg(reg: String) {
        val spot = parkingSpots.indexOfFirst { it.startsWith(reg) }
        if (spot == -1) {
            println("No cars with registration number $reg were found.")
        } else {
            println(spot+1)
        }
    }

    fun create(size: Int) {
        parkingSpots = mutableListOf()
        repeat(size) {
            parkingSpots.add("")
        }
        println("Created a parking lot with $size spots.")
    }

    fun created(): Boolean {
        if (parkingSpots.size != 0) {
            return true
        }
        return false
    }

    fun park(car: Car) {
        val spot = parkingSpots.indexOf("")
        if (spot < 0) {
            println("Sorry, the parking lot is full.")
        } else {
            parkingSpots[spot] = "${car.plate} ${car.color}"
            println("${car.color} car parked in spot ${spot + 1}.")
        }
    }

    fun leave(spot: Int) {
        if (parkingSpots[spot - 1] != "") {
            parkingSpots[spot - 1] = ""
            println("Spot $spot is free.")
        } else {
            println("There is no car in spot $spot.")
        }
    }

    fun status() {
        var empty = true
        for (car in parkingSpots) {
            if (car != "") {
                val spot = parkingSpots.indexOf(car)
                empty = false
                println("${spot + 1} $car")
            }
        }
        if (empty) {
            println("Parking lot is empty.")
        }
    }
}

/* Part 3
fun main() {
    val parking = ParkingLot(20)
    while (true){
        val input = readln().split(" ")
        val command = input[0]
        if (command == "exit") {
            break
        } else if (command == "park"){
            val car = Car(input[1], input[2])
            parking.park(car)
        } else if (command == "leave"){
            parking.leave(input[1].toInt())
        }
    }
}

data class Car (var plate: String, var color: String)

class ParkingLot (size: Int) {

    private var parkingSpots = mutableListOf<Boolean>()
    init {
        repeat(size){
           parkingSpots.add(false)
        }
    }

    fun park(car: Car) {
        val spot = parkingSpots.indexOf(false)
        if (spot < 0) {
            println("Sorry, the parking lot is full.")
        } else {
            parkingSpots[spot] = true
            println("${car.color} car parked in spot ${spot+1}.")
        }
    }

    fun leave(spot:Int){
        if (parkingSpots[spot-1]){
            parkingSpots[spot-1] = false
            println("Spot $spot is free.")
        } else {
            println("There is no car in spot $spot.")
        }
    }
}
*/
/* PART 2
fun main () {
    var parking = mutableListOf<Int>(1,0)
    val input = readln().split(" ")
    if (input[0] == "park") {
        for (index in parking.indices) {
            if (parking[index] == 0) {
                println("${input[2]} car parked in spot ${index+1}.")
                parking[index] = 1
            }
        }
    } else if (input[0] == "leave") {
        var spot = input[1].toInt()
        if (parking[spot-1] == 1) {
            println("Spot $spot is free.")
            parking[spot-1] = 0
        } else {
            println("There is no car in spot $spot.")
        }
    }
}
*/

