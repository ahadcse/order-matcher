package se.aphelion.assignment.ordermatcher.order;

/**
 * This class separates the buy order (with BUY command) from its super class
 * Order and sorts BuyOrder object with best buy order (Higest in the top).
 * 
 */
public class BuyOrder extends Order implements Comparable<BuyOrder>
{

	/**
	 * Class constructor specifying volume and price for a BuyOrder.
	 * 
	 * @param volume
	 * @param price
	 */
	public BuyOrder(
			int volume,
			int price)
	{
		super(volume,
				price);
	}

	/**
	 * Sorts the BuyOrder object according to price
	 */
	@Override
	public int compareTo(BuyOrder o)
	{
		return o.getPrice() - super.getPrice();   // Descending order accroding to price
	}

}
