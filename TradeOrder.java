import java.lang.reflect.*;

/**
 * Represents a buy or sell order for trading a given number of shares of a
 * specified stock.
 */
public class TradeOrder
{
    private Trader trader;
    private String symbol;
    private boolean buyOrder;
    private boolean marketOrder;
    private int numShares;
    private double price;

    /**
     * constructor
     * @param myTrader is the trader
     * @param sym is the symbol
     * @param buy is to buy
     * @param market is the market order
     * @param shares is # of shares
     * @param p is price of object
     */
    public TradeOrder(Trader myTrader, String sym, boolean buy, boolean market, int shares, double p )
    {
        trader = myTrader;
        symbol = sym;
        buyOrder = buy;
        marketOrder = market;
        numShares = shares;
        price = p;              
    }
    
    /**
     *  Returns the price per share for this trade order (used by a limit order).
     * @return price per share
     */
    public double getPrice()
    {
        return price;
    }
    
    /**
     *  Returns the number of shares to be traded in this trade order.
     * @return shares number
     */
    public int getShares()
    {
        return numShares;
    }
    
    /**
     * Returns the stock symbol for this trade order.
     * @return symbol
     */
    public String getSymbol()
    {
        return symbol;
    }
    /**
     *  Returns the trader for this trade order.
     * @return trader 
     */
    public Trader getTrader()
    {
        return trader;
    }
    
    /**
     * Returns true if this is a buy order; otherwise returns false.
     * @return if this is a buy order
     */
    public boolean isBuy()
    {
        return buyOrder;
    }
    
    /**
     * Returns true if this is a limit order; otherwise returns false.
     * @return if this is a limit order
     */ 
    public boolean isLimit()
    {
        return !marketOrder;
    }
    
    /**
     * Returns true if this is a market order; otherwise returns false.
     * @return  if this is a market order
     */
    public boolean isMarket()
    {
        return marketOrder;
    }
    
    /**
     * Returns true if this is a sell order; otherwise returns false.
     * @return if this is a sell order
     */
    public boolean isSell()
    {
        return !buyOrder;
    }
    
    /**
     * Subtracts a given number of shares from the total number of shares in this trade order. 
     * @param shares = a number of shares to be subtracted. 
     */
    public void subtractShares(int shares)
    {
        if (shares > numShares)
        {
            throw new IllegalArgumentException();
        }
        numShares = numShares - shares;
    }
    
    
    //
    // The following are for test purposes only
    //
    /**
     * <p>
     * A generic toString implementation that uses reflection to print names and
     * values of all fields <em>declared in this class</em>. Note that
     * superclass fields are left out of this implementation.
     * </p>
     * 
     * @return a string representation of this TradeOrder.
     */
    public String toString()
    {
        String str = this.getClass().getName() + "[";
        String separator = "";

        Field[] fields = this.getClass().getDeclaredFields();

        for ( Field field : fields )
        {
            try
            {
                str += separator + field.getType().getName() + " "
                    + field.getName() + ":" + field.get( this );
            }
            catch ( IllegalAccessException ex )
            {
                System.out.println( ex );
            }

            separator = ", ";
        }

        return str + "]";
    }
}
