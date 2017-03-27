import java.util.*;
import java.lang.reflect.*;
import java.text.DecimalFormat;

/**
 * Represents a stock in the SafeTrade project
 */
public class Stock
{
    public static DecimalFormat money = new DecimalFormat( "0.00" );

    private String stockSymbol;
    private String companyName;
    private double loPrice, hiPrice, lastPrice;
    private int volume;
    private PriorityQueue<TradeOrder> buyOrders, sellOrders;

    /**
     * Constructs a new stock with a given symbol, company name, and starting price.
     * 
     * @param symbol    the stock symbol.
     * @param name  full company name.
     * @param price opening price for this stock.
     */
    public Stock(String symbol, String name, double price)
    {
        stockSymbol = symbol;
        companyName = name;
        loPrice = price;
        hiPrice = price;
        lastPrice = price;
        volume = 0;
        PriceComparator asc = new PriceComparator();
        PriceComparator desc = new PriceComparator(false);
        sellOrders = new PriorityQueue<TradeOrder>(asc);
        buyOrders = new PriorityQueue<TradeOrder>(desc);
    }

    /**
     * Executes as many pending orders as possible. 
     */
    public void executeOrders()
    {
        while ( true )
        {
            TradeOrder topBuy = buyOrders.peek();
            TradeOrder topSell = sellOrders.peek();
            double price = 0;
            int shares = 0;
            
            if ( topBuy == null || topSell == null )
            {
                break;
            }
            
            if ( topBuy.isLimit() && topSell.isLimit()
                            && topBuy.getPrice() > topSell.getPrice() )
            {
                break;
            }
            
            if ( topBuy.isLimit() && topSell.isLimit()
                && topBuy.getPrice() <= topSell.getPrice() )
            {
                price = topSell.getPrice();
            }
            
            else if ( topBuy.isLimit() && topSell.isMarket()
                            || topBuy.isMarket() && topSell.isLimit() )
            {
                if ( topBuy.isLimit() )
                {
                    price = topBuy.getPrice();
                }
                else
                {
                    price = topSell.getPrice();
                }
            }
            
            else if ( topBuy.isMarket() && topSell.isMarket() )
            {
                price = lastPrice;
            }
 
            if ( topBuy.getShares() <= topSell.getShares() )
            {
                shares = topBuy.getShares();
            }
            else
            {
                shares = topSell.getShares();
            }
 
            topBuy.subtractShares( shares );
            topSell.subtractShares( shares );
 
            if ( topBuy.getShares() == 0 )
            {
                buyOrders.remove();
            }
            
            if ( topSell.getShares() == 0 )
            {
                sellOrders.remove();
            }
 
            if ( price < loPrice )
            {
                loPrice = price;
            }
            
            if ( price > hiPrice )
            {
                hiPrice = price;
            }
 
            volume += shares;
 
            topBuy.getTrader().receiveMessage( "You bought: " + shares + " "
                + topBuy.getSymbol() + " at " + price + " amt " + shares
                * price );
 
            topSell.getTrader().receiveMessage( "You sold: " + shares + " "
                + topSell.getSymbol() + " at " + price + " amt " + shares
                * price );
 
        }
    }
 
    /**
     * Executes as many pending orders as possible.
     * 
     * @return  the quote for this stock.
     */
    public String getQuote()
    {
        String str = companyName + " (" + stockSymbol + ")" + "\n"
            + "Price: " + lastPrice + " hi: " + hiPrice + " lo: " + loPrice
            + " vol: " + volume + "\n";
        String sell = "Ask: ";
        String buy = "Bid: ";
        
        if ( sellOrders.peek() != null )
        {
            sell += sellOrders.peek().getPrice() + " size: "
                            + sellOrders.peek().getShares();
        }
        else
        {
            sell += "none   ";
        }
        
        if ( buyOrders.peek() != null )
        {
            buy += buyOrders.peek().getPrice() + " size: "
                            + buyOrders.peek().getShares();
        }
        else
        {
            buy += "none";
        }
        
        return str + sell + buy;
 
    }
 
    /**
     * Places a trading order for this stock
     * 
     * @param order a trading order to be placed.
     */
    public void placeOrder( TradeOrder order )
    {
        String str = "New Order: ";

        if (order.isBuy())
        {
            buyOrders.add( order );
            str += "Buy " + order.getSymbol() + " (" + companyName + ")" + "\n"
                       + order.getShares() + " shares at " + order.getPrice();
        }
        else if (order.isSell())
        {
            sellOrders.add( order );
            str += "Sell " + order.getSymbol() + " (" + companyName + ") "
                       + "\n" + order.getShares() + " shares at market";
        }

        order.getTrader().receiveMessage( str );
        executeOrders();
    }
    
    //
    // The following are for test purposes only
    //
    
    protected String getStockSymbol()
    {
        return stockSymbol;
    }
    
    protected String getCompanyName()
    {
        return companyName;
    }
    
    protected double getLoPrice()
    {
        return loPrice;
    }
    
    protected double getHiPrice()
    {
        return hiPrice;
    }

    protected double getLastPrice()
    {
        return lastPrice;
    }
    
    protected int getVolume()
    {
        return volume;
    }

    protected PriorityQueue<TradeOrder> getBuyOrders()
    {
        return buyOrders;
    }
    
    protected PriorityQueue<TradeOrder> getSellOrders()
    {
        return sellOrders;
    }
    
    /**
     * <p>
     * A generic toString implementation that uses reflection to print names and
     * values of all fields <em>declared in this class</em>. Note that
     * superclass fields are left out of this implementation.
     * </p>
     * 
     * @return a string representation of this Stock.
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
