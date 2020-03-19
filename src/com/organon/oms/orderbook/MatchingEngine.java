package com.organon.oms.orderbook;

import java.util.List;

interface MatchingEngine {
    List<Trade> enterOrder(Order orderNew);
}
