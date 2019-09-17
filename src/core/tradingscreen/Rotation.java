package core.tradingscreen;


import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;



public class Rotation {
	
	static String[] weekdayDevelopers = new String [] {
            "Chris Kingsbury",
            "Fabien Cortina" ,
            "Martin Berglund",
            "Deng Huaping",
            "Alistair Low",
            "Luca Nobili",
            "Denis Bregeon",
            "Brice Pesci"
    };
    static String[] weekendDevelopers = new String [] {
            "Michael Tepp",
            "Nirav Prajapati"
    };
    public static void main(String[] args) {
    	
        DateTimeFormatter fmt = DateTimeFormat.forPattern("dd MMM yyyy");
        int weekendExtraCount = weekdayDevelopers.length;
        
        LocalDate d = new LocalDate(2016, 1, 9);
       
        for (int i = 0; i < 60; ++i) {
            String weekdayCover = weekdayDevelopers[i%weekdayDevelopers.length];
            String weekendCover = weekdayCover;
            if (i % weekendExtraCount == 0) {
                weekendCover = weekendDevelopers[0];
                weekendExtraCount = weekdayDevelopers.length + 1;
            } else if (i % weekendExtraCount == 1 && i > 5) {  // hack for nirav
                weekendCover = weekendDevelopers[1];
                weekendExtraCount = weekdayDevelopers.length + 1;
            }
            System.out.println (String.format("Sat %s | %s | %s",  d.toString(fmt), weekendCover, weekdayCover));
//            System.out.println (String.format("%s",  weekendCover));
            d = d.plusWeeks(1);
        }
    }
}
	


