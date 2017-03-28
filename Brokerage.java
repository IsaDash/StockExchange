import java.lang.reflect.*;
import java.util.*;
 
 
/**
 * Represents a brokerage.
 */
public class Brokerage implements Login
{
    private Map<String, Trader> traders;
 
    private Set<Trader> loggedTraders;
 
    private StockExchange exchange;
 
 
    /**
     * constructs new stock exchange
     * @param exchange is a stock exchange
     */
    public Brokerage( StockExchange exchange )
    {
        this.exchange = exchange;
        traders = new TreeMap<String, Trader>();
        loggedTraders = new TreeSet<Trader>();
    }
 
 
    /**
     * Tries to register a new trader with a given screen name and password.
     * 
     * @param name is the trader name
     * @param password is password trader
     * @return int is the result when entered
     */
    public int addUser( String name, String password )
    {
        if ( name.length() > 10 || name.length() < 4 )
        {
            return -1;
        }
        else if ( password.length() > 10 || password.length() < 2 )
        {
            return -2;
        }
        else if ( traders.get( name ) != null )
        {
            return -3;
        }
        traders.put( name, new Trader( this, name, password ) );
        return 0;
    }
 
 
    /**
     * Requests a quote for a given stock from the stock exachange and passes it
     * along to the trader by calling trader's receiveMessage method.
     * 
     * @param symbol is for getting a message
     * @param trader is the receiver
     */
    public void getQuote( String symbol, Trader trader )
    {
        trader.receiveMessage( exchange.getQuote( symbol ) );
        
    }
 
 
    /** (non-Javadoc)
     * @see Login#login(java.lang.String, java.lang.String)
     * @param name is the trader name
     * @param password is the trader password
     * @return int is the result
     */
    public int login( String name, String password )
    {
        if ( traders.get( name ) == null )
        {
            return -1;
        }
        Trader check = traders.get( name );
        if ( loggedTraders.contains( check ) )
        {
            return -3;
        }
        if ( !check.getPassword().equals( password ) )
        {
            return -2;
        }
        else
        {
            check.openWindow();
            check.receiveMessage( "Welcome to SafeTrade!" );
            
            loggedTraders.add( check );
            return 0;
        }
    }
 
 
    /**
     * Removes a specified trader from the set of logged-in traders.
     * @param trader - the trader that logs out.
     */
    public void logout( Trader trader )
    {
        loggedTraders.remove( trader );
    }
 
 
    /**
     * Places an order at the stock exchange. 
     * @param order - an order to be placed at the stock exchange.
     */
    public void placeOrder( TradeOrder order )
    {
        exchange.placeOrder( order );
    }
 
 
    //
    // The following are for test purposes only
    //
    /**
     * map of traders
     * @return the traders
     */
    protected Map<String, Trader> getTraders()
    {
        return traders;
    }
 
 
    /**
     * set of traders
     * @return trader set
     */
    protected Set<Trader> getLoggedTraders()
    {
        return loggedTraders;
    }
 
 
    /**
     * stock exchange
     * @return stock exchange
     */
    protected StockExchange getExchange()
    {
        return exchange;
    }
 
 
    /**
     * <p>
     * A generic toString implementation that uses reflection to print names and
     * values of all fields <em>declared in this class</em>. Note that
     * superclass fields are left out of this implementation.
     * </p>
     * 
     * @return a string representation of this Brokerage.
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
