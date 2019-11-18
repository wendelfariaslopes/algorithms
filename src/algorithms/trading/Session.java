package algorithms.trading;

import org.joda.time.DateMidnight;
import org.joda.time.DateTime;

public class Session implements Comparable<Session> {
	
	public final DateTime openDate;
    public final DateTime closeDate;
    public final DateTime expiryDate;
    @SuppressWarnings("deprecation")
	public final DateMidnight tradeDate;

    public final boolean  noHolidayForTradeDate;
    public final DateTime preOpenStartDate;
    public final DateTime preOpenEndDate;
    public final DateTime liquidStartDate;
    public final DateTime liquidEndDate;
    
    public Session(DateTime openDate, DateTime closeDate, DateTime expiryDate, DateMidnight tradeDate) {
        this(openDate, closeDate, expiryDate, tradeDate,false, null,null,null,null);
    }

    public Session(DateTime openDate, DateTime closeDate, DateTime expiryDate, DateMidnight tradeDate,  boolean noHolidayForTradeDate,
            DateTime preOpenStartDate, DateTime preOpenEndDate, DateTime liquidStartDate, DateTime liquidEndDate) {
        if (openDate == null || closeDate == null || expiryDate == null || tradeDate == null)
            throw new IllegalArgumentException("Date is null.");

        this.openDate = openDate;
        this.closeDate = closeDate;
        this.expiryDate = expiryDate;
        this.tradeDate = tradeDate;
        this.noHolidayForTradeDate = noHolidayForTradeDate;
        this.preOpenStartDate=preOpenStartDate;
        this.preOpenEndDate=preOpenEndDate;
        this.liquidStartDate=liquidStartDate;
        this.liquidEndDate=liquidEndDate;
    }

    public StringBuffer printTo(StringBuffer sb) {
        sb.append("{O=");
//        printTo(sb, openDate);
//        sb.append(", C=");
//        dateHourMinuteSecond.printTo(sb, closeDate);
//        sb.append(", E=");
//        dateHourMinuteSecond.printTo(sb, expiryDate);
        
        
        if (preOpenStartDate != null) {
            sb.append(", POS=").append(preOpenStartDate);
        }
        if (preOpenEndDate != null) {
            sb.append(", POE=").append(preOpenEndDate);
        }
        if (liquidStartDate != null) {
            sb.append(", OCS").append(liquidStartDate);
        }
        if (liquidEndDate != null) {
            sb.append(", OCE:").append(liquidEndDate);
        }
        sb.append("}");
        return sb;
    }

    public String toString() {
        return this.printTo(new StringBuffer(128)).toString();
    }

    /**
     * Compare this session to the instant in time. An instant is considered
     * inside the session if openDate &lt;= instant &lt;= closeDate.
     * @param instant the datetime to compare
     * @return  0 if the instant is in the session.
     *          1 if the instant occurs before the session.
     *         -1 if the instant occurs after the session.
     */
    public int compareTo(DateTime instant) {
        if (openDate.compareTo(instant) > 0)
            return 1;
        if (closeDate.compareTo(instant) < 0)
            return -1;
        return 0;
    }

    public int compareOpen(Session that) {
        return this.openDate.compareTo(that.openDate);
    }

    public int compareClose(Session that) {
        return this.closeDate.compareTo(that.closeDate);
    }

    /**
     * @deprecated Use {@link #compareTo(Session)} instead
     */
    @Deprecated
    public static int specificity(Session x, Session y) {
        return x.compareTo(y);
    }

    /**
     * This session is considered less than the specified session if (in order of consideration):
     * <pre>
     *     1. it closes earlier
     *     2. it opens later
     *     3. it has an earlier expiry date
     *     4. it has an earlier trade date
     * </pre>
     * If all values are equal, then this session is equal to the specified session.
     *
     * @param that the session to compare against.
     * @return a negative integer, zero, or a positive integer if this session is less than,
     *         equal to, or greater than the specified session.
     */
    @Override 
    public int compareTo(Session that) {
        int cmp = this.compareClose(that);
        if (cmp == 0) {
            cmp = that.compareOpen(this);
            if (cmp == 0) {
                cmp = this.expiryDate.compareTo(that.expiryDate);
                if (cmp == 0) {
                    cmp = this.tradeDate.compareTo(that.tradeDate);
                }
            }
        }
        return cmp;
    }

}
