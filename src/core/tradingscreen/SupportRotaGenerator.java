package core.tradingscreen;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class SupportRotaGenerator {

    private final static Calendar START_DATE = new GregorianCalendar(2019, Calendar.JULY, 01);
    private final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MMM-dd-yyyy");
    private final static String[] ROTA = new String[] {
            "lfinker",
            "dstrite",
            "apatki",
            "fsidokhi",
            "mtepp",
            "chuang",
            "kou",
            "jishah",
            "biyer",
            "mcullina",
            "sbhaleka",
            "scarbaja",
            "jamine",
            "wlopes"
    };
    private final static int NUM_WEEKS = ROTA.length;

    public static void main(String[] args) {
        StringBuffer sb = new StringBuffer();
        Calendar date = START_DATE;
        for (int i = 0; i < NUM_WEEKS; i++) {
            sb.append(createLine(formatDate(date), formatUser(ROTA[i])));
            sb.append("\n");
            date.add(Calendar.WEEK_OF_YEAR, 1);
        }
        System.out.println(sb);
    }

    private static String createLine(String date, String user) {
        return String.format("|%s|%s|", date, user);
    }

    private static String formatDate(Calendar date) {
        return DATE_FORMAT.format(date.getTime());
    }

    private static String formatUser(String user) {
        return String.format("[~%s]", user);
    }
}
