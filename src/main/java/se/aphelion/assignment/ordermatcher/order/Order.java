package se.aphelion.assignment.ordermatcher.order;

/**
 * A simple POJO class which contains volume and price of an order.
 * 
 * @param volume - Volume of share
 * @param price  - Price per share
 */
public class Order
{
	private int volume;
	private int price;

	public Order(
			int volume,
			int price)
	{
		super();
		this.volume = volume;
		this.price = price;
	}

	/**
	 * Getter class to get the price of share
	 * 
	 * @return volume
	 */
	public int getPrice()
	{
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(int price)
	{
		this.price = price;
	}

	/**
	 * Getter class to set the volume of share
	 *
	 * @return int volume
	 */
	public int getVolume()
	{
		return volume;
	}

	/**
	 * Setter class to set the volume of share
	 * 
	 * @param volume
	 */
	public void setVolume(int volume)
	{
		this.volume = volume;
	}

	@Override
	public String toString()
	{
		return Integer.toString(this.volume) + "@" + Integer.toString(this.price);
	}
}
