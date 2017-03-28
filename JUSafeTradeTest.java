import org.junit.*;
import static org.junit.Assert.*;

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
        assertTrue( "<< Invalid TradeOrder Constructor >>",
                    toStr.contains( "TradeOrder[Trader trader:null" )
                        && toStr.contains( "java.lang.String symbol:" + symbol )
                        && toStr.contains( "boolean buyOrder:" + buyOrder )
                        && toStr.contains( "boolean marketOrder:" + marketOrder )
                        && toStr.contains( "int numShares:" + numShares )
                        && toStr.contains( "double price:" + price ) );
    }
    
    @Test
    public void tradeOrderToString()
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
    
    // --Test TraderWindow Stub
//    @Test
//    public void traderWindowConstructor()
//    {
//        Trader trade = new Trader( brokerage, screenname, password);
//        TraderWindow tw = new TraderWindow( trade );
//        assertNotNull( tw );
//    }
//
//    @Test
//    public void traderWindowShowMessage()
//    {
//        Trader trade = new Trader( brokerage, screenname, password);
//        TraderWindow tw = new TraderWindow( trade );
//        assertNotNull( tw );
//        tw.showMessage( null );
//    }

    //  --Test PriceComparator
    
    @Test
    public void priceComparatorConstructor()
    {
        PriceComparator pc = new PriceComparator();
        assertNotNull( "Default PriceComparator constructor failed", pc );
        
        pc = new PriceComparator (false);
        assertNotNull( "PriceComparator constructor failed", pc );
    }
    
    @Test
    public void priceComparatorCompareMarketMarket()
    {
        String symbol = "SYMB";
        boolean buy = true;
        boolean market = true;
        int shares = 10;
        TradeOrder order1 = new TradeOrder( null, symbol, buy, market, shares, 
                                             12.50 );
        TradeOrder order2 = new TradeOrder( null, symbol, !buy, market, shares+10, 
                                             32.50 );
        
        PriceComparator pc = new PriceComparator();
        
        assertEquals("PriceComparator: expected 0 when comparing 2 market order", 
                     0, pc.compare(order1, order2));
    }
    
    @Test
    public void priceComparatorCompareMarketLimit()
    {
        String symbol = "SYMB";
        boolean buy = true;
        boolean market = true;
        int shares = 10;
        TradeOrder order1 = new TradeOrder( null, symbol, buy, market, shares, 
                                             12.50 );
        TradeOrder order2 = new TradeOrder( null, symbol, !buy, !market, shares+10, 
                                             32.50 );
        
        PriceComparator pc = new PriceComparator();
        
        assertEquals("PriceComparator: expected " + 
                     "-1 when comparing market order with limit order", 
                     -1, pc.compare(order1, order2));
    }
    
    @Test
    public void priceComparatorCompareLimitMarket()
    {
        String symbol = "SYMB";
        boolean buy = true;
        boolean market = true;
        int shares = 10;
        TradeOrder order1 = new TradeOrder( null, symbol, buy, !market, shares, 
                                             12.50 );
        TradeOrder order2 = new TradeOrder( null, symbol, !buy, market, shares+10, 
                                             32.50 );
        
        PriceComparator pc = new PriceComparator();
        
        assertEquals("PriceComparator: expected " + 
                     "1 when comparing limit order with market order", 
                     1, pc.compare(order1, order2));
    }
    
    @Test
    public void priceComparatorCompareLimitLimit()
    {
        String symbol = "SYMB";
        boolean buy = true;
        boolean market = true;
        int shares = 10;
        int diffCents = 55;
        TradeOrder order1 = new TradeOrder( null, symbol, buy, !market, shares, 
                                             12.50 );
        TradeOrder order2 = new TradeOrder( null, symbol, !buy, !market, shares+10, 
                                             order1.getPrice() + diffCents/100.0);
        
        PriceComparator pcAsc = new PriceComparator();
        
        assertEquals("PriceComparator: expected " + (-diffCents) +
                     " cents when comparing these 2 limit orders", 
                     -diffCents, pcAsc.compare(order1, order2));
        
        PriceComparator pcDesc = new PriceComparator(false);
        
        assertEquals("PriceComparator: expected " + diffCents +
                     " cents when comparing these 2 limit orders", 
                     diffCents, pcDesc.compare(order1, order2));

    }
     
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
        assertFalse("<<Trader: compareto trader2 should not equal trader>>", 
            trade.compareTo( trader2 ) == 0);
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
        StockExchange exchange = new StockExchange();
        exchange.listStock( "GGGL", "Giggle.com", 10.00 );
        Brokerage brokerage = new Brokerage( exchange );
        Trader trader = new Trader( brokerage, screenname, password );
        trader.getQuote( symbol );
        assertTrue( "<< Invalid Trader getQuote >>", trader.hasMessages() );
    }
    
    @Test
    public void traderPlaceOrder()
    {
        StockExchange to = new StockExchange();
        Brokerage safeTrade = new Brokerage( to );
        safeTrade.addUser( "stockman", "sesame" );
        Trader trade = new Trader( safeTrade, "stockman", "sesame" );
        TradeOrder order = new TradeOrder( trade, symbol,buyOrder, marketOrder, numShares, price );
        to.listStock( "GGGL", "Giggle.com", 10.00 );
        trade.placeOrder( order );
        assertTrue( "<< Invalid Trader placeOrder >>",
            trade.hasMessages() );
        trade.openWindow();
        to.placeOrder( order );
        assertFalse( "<< Invalid Trader placeOrder >>",
            trade.hasMessages() );
    }
    
    @Test
    public void traderQuit()
    {
        StockExchange exchange = new StockExchange();
        exchange.listStock( "GGGL", "Giggle.com", 10.00 );
        Brokerage brokerage = new Brokerage( exchange );
        Trader trader = new Trader( brokerage, screenname, password );
        brokerage.login( screenname, password );
        trader.quit();
        assertFalse( "<< Invalid trader logout >>", brokerage.getLoggedTraders()
            .contains(trader) );
        
    }
    
    // --Test Brokerage
    
    @Test
    public void brokerageConstructor()

    {
        StockExchange exchange = new StockExchange();
        exchange.listStock( "GGGL", "Giggle.com", 10.00 );
        Brokerage b = new Brokerage( exchange );
        String str = b.toString();

        assertTrue( "<< Invalid Brokerage Constructor >>",
            str.contains( "Brokerage[" ) );
    }

    @Test
    public void brokerageToString()
    {
        StockExchange exchange = new StockExchange();
        exchange.listStock( "GGGL", "Giggle.com", 10.00 );
        Brokerage b = new Brokerage( exchange );
        assertNotNull( b.toString() );
    }

    @Test
    public void brokerageAddUser()
    {
        StockExchange exchange = new StockExchange();
        exchange.listStock( "GGGL", "Giggle.com", 10.00 );
        Brokerage brokerage = new Brokerage( exchange );
        assertTrue( "<< Invalid name >>",
            brokerage.addUser( "name", "password" ) == -1 );
        assertTrue( "<< Invalid name >>",
            brokerage.addUser( "name", "password" ) == -1 );
        assertTrue( "<< Invalid name >>",
            brokerage.addUser( "username", "name" ) == -2 );
        assertTrue( "<< Invalid name >>",
            brokerage.addUser( "username", "traderUsers" ) == -2 );
        assertTrue( "<< Invalid name >>",
            brokerage.addUser( "username", "pswd" ) == 0
                && brokerage.getTraders().containsKey( "username" ) );
        assertTrue( "<< invalid name >>",
            brokerage.addUser( "username", "pswd1" ) == -3 );
    }


    @Test
    public void brokerageGetQuote()
    {
        StockExchange exchange = new StockExchange();
        exchange.listStock( "GGGL", "Giggle.com", 10.00 );
        Brokerage brokerage = new Brokerage( exchange );
        Trader trader = new Trader( brokerage, screenname, password );
        brokerage.getQuote( symbol, trader );
        assertTrue( "<< Invalid Brokerage getQuote >>", trader.hasMessages() );

    }


    @Test
    public void brokerageLogin()

    {
        StockExchange exchange = new StockExchange();
        exchange.listStock( "GGGL", "Giggle.com", 10.00 );
        Brokerage brokerage = new Brokerage( exchange );

        String n = "java";
        String p = "trader";
        brokerage.addUser( n, p );
        assertTrue( "<< Invalid brokerage login >>",
            brokerage.login( "user", p ) == -1 );
        assertTrue( "<< Invalid brokerage login >>",
            brokerage.login( n, "Invalid" ) == -2 );
        assertTrue( "<< Invalid brokerage login >>",
            brokerage.login( n, p ) == 0
                && brokerage.getLoggedTraders()
                    .contains( brokerage.getTraders().get( n ) )
                && !brokerage.getTraders().get( n ).hasMessages() );

        brokerage.login( n, p );

        assertTrue( "<< invalid brokerage login >>",
            brokerage.login( n, p ) == -3 );

    }


    @Test
    public void brokerageLogout()
    {
        StockExchange exchange = new StockExchange();
        exchange.listStock( "GGGL", "Giggle.com", 10.00 );
        Brokerage brokerage = new Brokerage( exchange );

        brokerage.addUser( "java", "user" );
        brokerage.login( "java", "user" );
        brokerage.logout( brokerage.getTraders().get( "java" ) );
        assertFalse( "<< Invalid brokerage logout >>", brokerage.getLoggedTraders()
            .contains( brokerage.getTraders().get( "java" ) ) );
    }


    @Test
    public void brokeragePlaceOrder()

    {
        StockExchange exchange = new StockExchange();
        exchange.listStock( "GGGL", "Giggle.com", 10.00 );
        Brokerage brokerage = new Brokerage( exchange );
        Trader trader = new Trader( brokerage, screenname, password );

        TradeOrder order = new TradeOrder( trader,
            symbol,
            true,
            false,
            10,
            10.0 );
        brokerage.placeOrder( order );
        assertTrue( "<< Invalid Brokerage getQuote >>", trader.hasMessages() );
        trader.openWindow();
        brokerage.placeOrder( order );
        assertFalse( "<< Invalid Brokerage getQuote >>", trader.hasMessages() );

    }
    
    // --Test StockExchange
    
    @Test
    public void stockExchangeConstructor()

    {
        StockExchange se = new StockExchange();
        String toStr = se.toString();
        assertTrue( "<< Invalid StockExchange Constructor >>",
            toStr.contains( "StockExchange[" ) );

    }


    @Test
    public void stockExchangeToString()

    {
        StockExchange se = new StockExchange();
        assertNotNull( se.toString() );

    }


    @Test
    public void stockExchangeListStock()

    {
        StockExchange se = new StockExchange();
        se.listStock( "GGGL", "Giggle.com", 10.0 );
        Map<String, Stock> listedStocks = se.getListedStocks();
        assertTrue( "<< Invalid StockExchange listStock >>",
            listedStocks.containsKey( "GGGL" ) );
    }


    @Test
    public void stockExchangeGetQuote()

    {
        StockExchange se = new StockExchange();
        se.listStock( symbol, "Giggle.com", 10.0 );
        String quote = se.getQuote( symbol );
        
        assertTrue( "<< Invalid StockExchange getQuote >>",
        quote != null );

    }


    @Test
    public void stockExchangePlaceOrder()

    {
        StockExchange to = new StockExchange();
        Brokerage safeTrade = new Brokerage( to );
        safeTrade.addUser( "stockman", "sesame" );
        Trader trade = new Trader( safeTrade, "stockman", "sesame" );

        TradeOrder order = new TradeOrder( trade,
        symbol,
        buyOrder,
        marketOrder,
        numShares,
        price );

        to.listStock( "GGGL", "Giggle.com", 10.00 );
        to.placeOrder( order );
        assertTrue( "<< Invalid StockExchange placeOrder >>",
            trade.hasMessages() );
        trade.openWindow();
        to.placeOrder( order );
        assertFalse( "<< Invalid StockExchange placeOrder >>",
            trade.hasMessages() );
    }
    
    
    // --Test Stock
    
    @Test
    public void stockConstructor()
    {
        String symbol = "SYMB";
        String companyName ="Lynbrook Inc.";
        double price = 135.75;
        Stock s = new Stock(symbol, companyName, price);
        
        assertNotNull( "Stock constructor failed", s);
    }
    
    @Test
    public void stockGetQuote()
    {
        String symbol = "SYMB";
        String companyName ="Lynbrook Inc.";
        double price = 135.75;
        Stock stock = new Stock(symbol, companyName, price);
        
        String quote = stock.getQuote();
        
        assertTrue("Stock quote contains invalid information",
                   quote.contains(symbol) && 
                   quote.contains(companyName) &&
                   quote.contains("" + price) );
    }
    
    @Test
    public void stockPlaceOrder()
    {
        String symbol = "SYMB";
        String companyName ="Lynbrook Inc.";
        double price = 135.75;
        double bidPrice = price + 1;
        Stock stock = new Stock(symbol, companyName, price);
        
        Trader trader1 = new Trader(null, "Lynb", "Viking");
        
        boolean buy = true;
        boolean market = true;
        int shares = 10;
        TradeOrder order1 = new TradeOrder(trader1, symbol, buy, market, shares, 
                                           bidPrice );
        try
        {
            stock.placeOrder(order1);
            
            String quote = stock.getQuote();      
            assertTrue("Stock quote contains invalid transaction information",
                       quote.contains("Bid: " + bidPrice +  " size: " + shares));
            //The above string concatenation needs match output of stock.getQuote()
        }
        catch (Exception e)
        {
            assertTrue("Failed to place an order", false);
        }
        
        assertTrue("Trader should have received message after placing an order",
                   trader1.hasMessages());
    }
    
    @Test
    public void stockExecuteOrders()
    {
        String symbol = "SYMB";
        String companyName ="Lynbrook Inc.";
        double price = 135.75;
        double bidPrice = price + 1;
        Stock stock = new Stock(symbol, companyName, price);
        
        Trader trader1 = new Trader(null, "Lynb", "Viking");
        Trader trader2 = new Trader(null, "Hyde", "Hi");
        
        boolean buy = true;
        boolean market = true;
        int shares = 10;
        TradeOrder order1 = new TradeOrder(trader1, symbol, buy, market, shares, 
                                           bidPrice );
        TradeOrder order2 = new TradeOrder(trader2, symbol, !buy, market, shares, 
                                           price);
        String quote;
        
        try
        {
            stock.placeOrder(order1); 
            
            quote = stock.getQuote();      
            assertTrue("Stock quote contains invalid transaction information",
                       quote.contains("Bid: " + bidPrice +  " size: " + shares));
            //The above string concatenation needs match output of stock.getQuote()
            
            stock.placeOrder(order2); 
            
            stock.executeOrders();
        }
        catch (Exception e)
        {
            assertTrue("Failed to place or execute orders", false);
        }
        
        assertTrue("Trader should have received message after placing an order",
                   trader1.hasMessages());
        
        assertTrue("Trader should have received message after placing an order",
                   trader2.hasMessages());
        
        quote = stock.getQuote();      
        
        assertTrue("Stock quote contains invalid transaction information",
                   quote.contains(symbol) && 
                   quote.contains(companyName) &&
                   quote.contains("Price: " +  price) &&
                   quote.contains(" vol: " + shares));
    }
    
    // Remove block comment below to run JUnit test in console
//
//    public static junit.framework.Test suite()
//    {
//        return new JUnit4TestAdapter( JUSafeTradeTest.class );
//    }
//    
//    public static void main( String args[] )
//    {
//        org.junit.runner.JUnitCore.main( "JUSafeTradeTest" );
//    }

}
