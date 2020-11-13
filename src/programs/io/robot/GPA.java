package programs.io.robot;



/**
 * GPA represents a student's Grade Point Average. GPA is calculated by dividing
 * the total amount of grade points earned by the total amount of units
 * attempted. GPA ranges from 0.0 to a 4.0.
 *
 * @author John Dalbey
 * @version 0.1
 */
public class GPA
{
    // number of courses taken

    private int courseCount;
    // grade points earned
    private double totalPoints;
    // units attempted
    private double totalUnits;

    /**
     * Constructor an initial GPA with no courses.
     */
    public GPA()
    {
        // initialise instance variables
        courseCount = 0;
        totalPoints = 0.0;
        totalUnits = 0.0;
    }

    /**
     * Add a course to the total.
     *
     * @param letterGrade the grade earned in the course (A,B,C,D,F)
     * @param units the number of units the course was worth Assumes units > 0
     */
    public void addCourse(String letterGrade, double units)
    {
        // increment courses taken
        courseCount += 1;
        // update totals
        totalPoints += letterToPts(letterGrade) * units;
        totalUnits += units;

    }

    /**
     * Convert a letter grade to grade points.
     *
     * @param letterGrade the symbol for a grade Assumes letterGrade is one of
     * (A,B,C,D,F)
     * @return grade points for the given letter grade.
     */
    public double letterToPts(String letterGrade)
    {
        String letters = "ABCDF";
        int index = letters.indexOf(letterGrade.toUpperCase());
        return (double) (4 - index);
    }

    /**
     * Accessor to the number of courses taken.
     *
     * @return count of courses (integer)
     */
    public int getCourseCount()
    {
        return courseCount;
    }

    /**
     * Compute the GPA.
     *
     * @return GPA as a double.
     */
    public double getGPA()
    {
        double result = 0;
        // Don't allow divide by zero
        if (totalUnits > 0)
        {
            result = totalPoints / totalUnits;
        }
        return result;
    }

    /**
     * Return a string representation of the GPA.
     *
     * @return GPA as a string with two digits after the decimal point.
     */
    public String toString()
    {
        return String.format("%2.2f", getGPA());
    }
}
