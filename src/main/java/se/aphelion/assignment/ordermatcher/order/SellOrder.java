package se.aphelion.assignment.ordermatcher.order;

/**
 * This class separates the sell order (with SELL command) from its super class
 * Order and sorts the SellOrder object with best sell order (Lowest on the top).
 * 
 */
public class SellOrder extends Order implements Comparable<SellOrder>
{

	/**
	 * Class constructor specifying volume and price for a SellOrder.
	 * 
	 * @param volume
	 * @param price
	 */
	public SellOrder(
			int volume,
			int price)
	{
		super(volume,
				price);
	}

	/**
	 * Sorts the SellOrder object according to price
	 */
	@Override
	public int compareTo(SellOrder o)
	{
		return super.getPrice() - o.getPrice();   // Ascending order according to price
	}

}