package com.organon.oms.orderbook;

public class State {
    //                                                   name                    atStart alive  pending done   pendingAck
    public static State registered           = new State("Registered",           true,   false, false,  false, false);
    public static State working              = new State("Working",              false,  true,  false,  false, false);
    public static State deferred             = new State("Deferred",             false,  true,  false,  false, false);
    public static State submitting           = new State("Submitting",           false,  true,  true,   false, true);
    public static State amending             = new State("Amending",             false,  true,  true,   false, true);
    public static State canceling            = new State("Canceling",            false,  true,  true,   false, true);
    public static State alleged              = new State("Alleged",              false,  true,  false,  false, false);
    public static State pendingAcceptance    = new State("PendingAcceptance",    false,  true,  false,  false, true);
    public static State pendingCancel        = new State("PendingCancel",        false,  true,  false,  false, true);
    public static State pendingAuthorization = new State("PendingAuthorization", true,   true,  false,  false, true);
    public static State rejected             = new State("Rejected",             true,   false, false,  false, false);
    public static State expired              = new State("Expired",              true,   false, false,  false, false);
    public static State staged               = new State("Staged",               true,   false, false,  false, false);
    public static State pendingPricing       = new State("PendingPricing",       true,   false, false,  false, true);
    public static State accepted             = new State("Accepted",             false,  false, false,  true,  false);
    public static State broken               = new State("Broken",               false,  false, false,  true,  false);
    public static State cancelled            = new State("Cancelled",            false,  false, false,  true,  false);
    public static State executed             = new State("Executed",             false,  false, false,  true,  false);
    public static State deleted              = new State("Deleted",              false,  false, false,  true,  false);
    public static State tradingComplete      = new State("TradingComplete",      false,  false, false,  true,  false);
    public static State done                 = new State("Done",                 false,  false, false,  true,  false);
    public static State deactivated          = new State("Deactivated",          false,  false, false,  true,  false);
    public static State managed              = new State("Managed",              true,   false, false,  false, false);
    public static State pendingProduct       = new State("PendingProduct",       true,   false, false,  false, false);
    public static State matched              = new State("Matched",              false,  true,  false,  false, true);
    public static State retracted            = new State("Retracted",            false,  false, false,  true,  false);

    private State(String name, boolean atStart, boolean isAlive, boolean isPending, boolean isDone, boolean isPendingAck) {
     
        atStart_ = atStart;
        isAlive_ = isAlive;
        isPending_ = isPending;
        isDone_ = isDone;
        isPendingAck_ = isPendingAck;
    }

    private boolean atStart_;
    private boolean isAlive_;
    private boolean isPending_;
    private boolean isDone_;
    private boolean isPendingAck_;

    public boolean atStart () { return atStart_; }
    public boolean isAlive () { return isAlive_; }
    public boolean isPending () { return isPending_; }
    public boolean isDone () { return isDone_; }
    public boolean isPendingAck () { return isPendingAck_; }
}

