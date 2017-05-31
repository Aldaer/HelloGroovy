package generic

static int numberOfPointsWithinACircle(double r) {
    double rSquared = r * r
    int maxX = r as int
    int axisPoints = maxX
    int sectorSum = 0
    for (int y = 0; y <= maxX; y++) {
        int radius = maxX * maxX + y * y
        if (radius > rSquared) maxX--
        sectorSum += maxX - y + 1
    }
    int diagPoints = maxX
    return sectorSum * 8 - (diagPoints + axisPoints) * 4 - 7
}

Scanner sc = new Scanner(System.in)
print "Enter radius, r: "
double r = sc.nextDouble()
println "# of points: ${numberOfPointsWithinACircle(r)}"


