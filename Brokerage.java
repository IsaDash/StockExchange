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


    public Brokerage( StockExchange exchange )
    {
        exchange = new StockExchange();
        traders = new TreeMap<String, Trader>();
        loggedTraders = new TreeSet<Trader>();
    }


    public int addUser( java.lang.String name, java.lang.String password )
    {
        if ( name.length() < 4 || name.length() > 10 )
        {
            return -1;
        }
        else if ( password.length() < 2 || password.length() > 10 )
        {
            return -2;
        }
        else if ( loggedTraders.contains( name ) )
        {
            return -3;
        }
        else
        {
            Trader trader = new Trader( exchange, name, password );
            traders.put( name, trader );
            return 0;
        }

    }


    public int login( java.lang.String name, java.lang.String password )
    {
        if ( !traders.containsKey( name ) )
        {
            return -1;
        }
        else if ( !traders.containsValue( password ) )
        {
            return -2;
        }
        else if ( loggedTraders.contains( name ) )
        {
            return -3;
        }
        else
        {
            openWindow();
            myWindow.show("Welcome to SafeTrade!");
            Trader trader = new Trader(exchange, name, password);
            loggedTraders.add( trader );
            return 0;
        }

    }
    
    public void logout(Trader trader)
    {
        loggedTraders.remove( trader );
    }
    
    public void getQuote(java.lang.String symbol,
        Trader trader)
    {
        java.lang.String msg = exchange.getQuote( symbol );
        trader.receiveMessage(msg);
    }
    
    public void placeOrder(TradeOrder order)
    {
        exchange.placeOrder( order );
    }

    // TODO complete class


    //
    // The following are for test purposes only
    //
    protected Map<String, Trader> getTraders()
    {
        return traders;
    }


    protected Set<Trader> getLoggedTraders()
    {
        return loggedTraders;
    }


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
