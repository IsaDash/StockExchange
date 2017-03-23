import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.regex.*;

import org.junit.*;

import static org.junit.Assert.*;
import junit.framework.JUnit4TestAdapter;

/**
 * SafeTrade tests:
 *   TradeOrder
 *   PriceComparator
 *   Trader
 *   Brokerage
 *   StockExchange
 *   Stock
 *
 * @author Selena Huang
 * @author Isa Dash
 * @author Eric Fu
 * @version March 21, 2017
 * @author Assignment: JM Chapter 19 - SafeTrade
 * 
 * @author Sources: http://junit.org/junit4/javadoc/latest/
 *
 */
public class JUSafeTradeTest
{
    // --Test TradeOrder
    /**
     * TradeOrder tests:
     *   TradeOrderConstructor - constructs TradeOrder and then compare toString
     *   TradeOrderGetTrader - compares value returned to constructed value
     *   TradeOrderGetSymbol - compares value returned to constructed value
     *   TradeOrderIsBuy - compares value returned to constructed value
     *   TradeOrderIsSell - compares value returned to constructed value
     *   TradeOrderIsMarket - compares value returned to constructed value
     *   TradeOrderIsLimit - compares value returned to constructed value
     *   TradeOrderGetShares - compares value returned to constructed value
     *   TradeOrderGetPrice - compares value returned to constructed value
     *   TradeOrderSubtractShares - subtracts known value & compares result
     *     returned by getShares to expected value
     */
    private String symbol = "GGGL";
    private boolean buyOrder = true;
    private boolean marketOrder = true;
    private int numShares = 123;
    private int numToSubtract = 24;
    private double price = 123.45;

    @Test
    public void tradeOrderConstructor()
    {
        TradeOrder to = new TradeOrder( null, symbol, buyOrder, marketOrder,
            numShares, price );
        String toStr = to.toString();
        System.out.println(toStr);
        assertTrue( "<< Invalid TradeOrder Constructor >>",
                    toStr.contains( "TradeOrder[Trader trader:null" )
                        && toStr.contains( "java.lang.String symbol:" + symbol )
                        && toStr.contains( "boolean buyOrder:" + buyOrder )
                        && toStr.contains( "boolean marketOrder:" + marketOrder )
                        && toStr.contains( "int numShares:" + numShares )
                        && toStr.contains( "double price:" + price ) );
    }
    
    @Test
    public void TradeOrderToString()
    {
        TradeOrder to = new TradeOrder( null, symbol, buyOrder, marketOrder,
            numShares, price );
        assertNotNull( to.toString() );
    }

    @Test
    public void tradeOrderGetTrader()
    {
        TradeOrder to = new TradeOrder( null, symbol, buyOrder, marketOrder,
            numShares, price );
        assertNull( "<< TradeOrder: " + to.getTrader() + " should be null >>",
                    to.getTrader() );
    }

    @Test
    public void tradeOrderGetSymbol()
    {
        TradeOrder to = new TradeOrder( null, symbol, buyOrder, marketOrder,
            numShares, price );
        assertEquals( "<< TradeOrder: " + to.getTrader() + " should be "
            + symbol + " >>", symbol, to.getSymbol() );
    }

    @Test
    public void tradeOrderIsBuy()
    {
        TradeOrder to = new TradeOrder( null, symbol, buyOrder, marketOrder,
            numShares, price );

        assertTrue( "<< TradeOrder: " + to.isBuy() + " should be " + buyOrder
            + " >>", to.isBuy() );
    }

    @Test
    public void tradeOrderIsSell()
    {
        TradeOrder to = new TradeOrder( null, symbol, buyOrder, marketOrder,
            numShares, price );
        assertFalse( "<< TradeOrder: " + to.isSell() + " should be "
            + !buyOrder + " >>", to.isSell() );
    }

    @Test
    public void tradeOrderIsMarket()
    {
        TradeOrder to = new TradeOrder( null, symbol, buyOrder, marketOrder,
            numShares, price );
        assertTrue( "<< TradeOrder: " + to.isMarket() + " should be "
            + marketOrder + " >>", to.isMarket() );
    }

    @Test
    public void tradeOrderIsLimit()
    {
        TradeOrder to = new TradeOrder( null, symbol, buyOrder, marketOrder,
            numShares, price );

        assertFalse( "<< TradeOrder: " + to.isLimit() + " should be "
            + !marketOrder + ">>", to.isLimit() );
    }

    @Test
    public void tradeOrderGetShares()
    {
        TradeOrder to = new TradeOrder( null, symbol, buyOrder, marketOrder,
            numShares, price );
        assertTrue( "<< TradeOrder: " + to.getShares() + " should be "
            + numShares + ">>", numShares == to.getShares()
            || ( numShares - numToSubtract ) == to.getShares() );
    }

    @Test
    public void tradeOrderGetPrice()
    {
        TradeOrder to = new TradeOrder( null, symbol, buyOrder, marketOrder,
            numShares, price );
        assertEquals( "<< TradeOrder: " + to.getPrice() + " should be " + price
            + ">>", price, to.getPrice(), 0.0 );
    }

    @Test
    public void tradeOrderSubtractShares()
    {
        TradeOrder to = new TradeOrder( null, symbol, buyOrder, marketOrder,
            numShares, price );
        to.subtractShares( numToSubtract );
        assertEquals( "<< TradeOrder: subtractShares(" + numToSubtract
            + ") should be " + ( numShares - numToSubtract ) + ">>", numShares
            - numToSubtract, to.getShares() );
    }
    
/*    // --Test TraderWindow Stub
    @Test
    public void traderWindowConstructor()
    {
        TraderWindow tw = new TraderWindow( null );
        assertNotNull( tw );
    }

    @Test
    public void traderWindowShowMessage()
    {
        TraderWindow tw = new TraderWindow( null );
        assertNotNull( tw );
        tw.showMessage( null );
    }*/

    //  --Test PriceComparator
    
    // TODO your tests here
    
    
    // --Test Trader
    /**
     * Trader tests:
     *  Constructor
     */
    private String screenname = "asdfghjkl";
    private String password = "1234asdf";
    private Brokerage brokerage = null;
    private Trader trader1 = new Trader (null, screenname, "password");
    private Trader trader2 = new Trader (null, "AHJKL", "pasword");

    @Test
    public void traderConstructor()
    {
        Trader trade = new Trader( brokerage, screenname, password);
        String toStr = trade.toString();
        System.out.print( toStr );
        System.out.println();
        assertTrue( "<< Invalid Trader Constructor >>",
                    toStr.contains( "Trader[Brokerage brokerage" )
                        && toStr.contains( "java.lang.String screenName:" + screenname )
                        && toStr.contains( "java.lang.String password:" + password )
                        && toStr.contains( "TraderWindow myWindow:null" )
                        && toStr.contains( "java.util.Queue mailbox:[]" ));
    }
    
    @Test
    public void traderToString()
    {
        Trader trade = new Trader( brokerage, screenname, password);
        assertNotNull(trade.toString());
    }
    
    @Test
    public void traderCompareTo()
    {
        Trader trade = new Trader( brokerage, screenname, password);
        assertEquals("<<Trader: compareto trader1 should equal trader>>", 0, trade.compareTo( trader1 ));
        assertNotEquals("<<Trader: compareto trader2 should not equal trader>>", 
            0, trade.compareTo( trader2 ));
    }
    
    @Test
    public void traderEquals()
    {
        Trader trade = new Trader( brokerage, screenname, password);
        assertTrue("<<Trader: " + trade.getName().toLowerCase() + " should equal " 
            + trader1.getName().toLowerCase() + ">>", trade.equals( trader1 ));
        assertFalse("<<Trader: " + trade.getName().toLowerCase() + " should not equal " + 
            trader2.getName().toLowerCase() + ">>", trade.equals( trader2 ));
    }
    
    @Test
    public void traderGetName()
    {
        Trader trade = new Trader( brokerage, screenname, password);
        assertEquals("Trader: " + trade.getName() + " should equal " + screenname + ">>",
            trade.getName(), screenname);
    }
    
    @Test
    public void traderGetPassword()
    {
        Trader trade = new Trader( brokerage, screenname, password);
        assertEquals("Trader: " + trade.getPassword() + " should equal " + password + ">>",
            trade.getPassword(), password);
    }
    
    @Test
    public void traderHasMessages()
    {
        Trader trade = new Trader( brokerage, screenname, password);
        assertFalse("Trader: " + trade.hasMessages() + " should be false>>",
            trade.hasMessages());
        trade.receiveMessage( "message" );
        assertTrue("Trader: " + trade.hasMessages() + " should be true>>",
            trade.hasMessages());
    }
    
    @Test
    public void traderOpenWindow()
    {
        Trader trade = new Trader( brokerage, screenname, password);
        trade.receiveMessage( "message" );
        trade.openWindow();
        assertFalse("Trader: " + trade.hasMessages() + " should be false>>",
            trade.hasMessages());
    }
    
    @Test
    public void traderGetQuote()
    {
        Trader trade = new Trader( brokerage, screenname, password);
        //TODO after brokerage
    }
    
    @Test
    public void traderPlaceOrder()
    {
        Trader trade = new Trader( brokerage, screenname, password);
        //TODO after brokerage
    }
    
    @Test
    public void traderQuit()
    {
        Trader trade = new Trader( brokerage, screenname, password);
        //TODO after brokerage
    }
    
    
    
    // --Test Brokerage
    
    // TODO your tests here
    
    
    // --Test StockExchange
    
    // TODO your tests here
    
    
    // --Test Stock
    
    // TODO your tests here

    
    // Remove block comment below to run JUnit test in console
/*
    public static junit.framework.Test suite()
    {
        return new JUnit4TestAdapter( JUSafeTradeTest.class );
    }
    
    public static void main( String args[] )
    {
        org.junit.runner.JUnitCore.main( "JUSafeTradeTest" );
    }
*/
}

