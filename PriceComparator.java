/**
 *  A price comparator for trade orders.
 */
public class PriceComparator implements java.util.Comparator<TradeOrder>
{
    private boolean ascending;
    
    /**
     *  Constructs a price comparator that compares two orders in ascending 
     *  order.
     */
    public PriceComparator()
    {
        ascending = true;
    }

    /**
     *  Constructs a price comparator that compares two orders in ascending or 
     *  descending order. The order of comparison depends on the value of a 
     *  given parameter.
     * 
     *  @param asc   if true, make an ascending comparator; otherwise make a 
     *              descending comparator.
     */
    public PriceComparator(boolean asc)
    {
        ascending = asc;
    }
    
    /**
     *  Compares two trade orders. 
     */
    public int compare( TradeOrder order1, TradeOrder order2 )
    {
        if (order1.isMarket() && order2.isLimit())
        {
            return -1;
        }
        
        else if (order1.isLimit() && order2.isMarket())
        {
            return 1;
        }
        
        else if (order1.isLimit() && order2.isLimit())
        {
            double cents1 = order1.getPrice();
            double cents2 = order2.getPrice();
            
            if (ascending == true)
            {
                int c = (int)( Math.round((cents1 - cents2) * 100.0) );
                return c;
            }
            
            else if (ascending == false)
            {
                int c = (int)( Math.round((cents2 - cents1) * 100.0) );
                return c;
            }
        }
        
        return 0;
    }
}
