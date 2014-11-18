package se.aphelion.assignment.ordermatcher;

import se.aphelion.assignment.ordermatcher.order.BuyOrder;
import se.aphelion.assignment.ordermatcher.order.Order;
import se.aphelion.assignment.ordermatcher.order.SellOrder;

/**
 * This class implements the main business logic or trading rules.
 * 
 */
public class Broker
{

	private OrderBook orderBook;

	/**
	 * Class constructor of OrderBook used for trading.
	 * 
	 * @param orderBook
	 */
	public Broker(
			OrderBook orderBook)
	{
		super();
		this.orderBook = orderBook;
	}

	/**
	 * Process an order by adding that to the orderBook or sending that for
	 * trading rules checking.
	 * 
	 * @param order
	 */
	public void processOrder(Order order)
	{

		Order bestOrder = orderBook.getBestOrder(order);
		if (bestOrder == null)
		{
			orderBook.addNewOrder(order);
		}
		else
		{
			this.checkRoles(order, bestOrder);
		}
	}

	/**
	 * Check the order with the best offer from orderBook if rules are matched
	 * trades that else add order to the orderBook.
	 * 
	 * @param order
	 * @param bestOrder
	 */
	public void checkRoles(Order order, Order bestOrder)
	{

		if (((order instanceof BuyOrder) && (bestOrder.getPrice() <= order.getPrice()))
				|| ((order instanceof SellOrder) && (bestOrder.getPrice() >= order.getPrice())))
		{
			this.trade(order, bestOrder);

		}
		else
		{
			orderBook.addNewOrder(order);
		}
	}

	/**
	 * Does the actual trade depending on the volume of order and the best
	 * order. It depends on the difference of volume that is performed in
	 * different actions.
	 * 
	 * @param order
	 * @param bestOrder
	 */
	public void trade(Order order, Order bestOrder)
	{

		int remainVolume = order.getVolume() - bestOrder.getVolume();

		if (remainVolume == 0)   // If volume of order = volume of best order
		{

			this.printTrade(order.getVolume(), bestOrder.getPrice());
			orderBook.removeTopOrder(order);

		}
		else if (remainVolume > 0)    // If volume of order > best order
		{

			this.printTrade(bestOrder.getVolume(), bestOrder.getPrice());
			order.setVolume(remainVolume);
			orderBook.removeTopOrder(order);
			this.processOrder(order);

		}
		else						// If volume of order < best order
		{

			this.printTrade(order.getVolume(), bestOrder.getPrice());
			bestOrder.setVolume(-remainVolume);
			orderBook.updateTopOrder(bestOrder);
		}
	}

	/**
	 * Prints the trade to standard output.
	 * 
	 * @param volume
	 *        Quantity of the share
	 * @param price
	 *        Price per share
	 */
	public void printTrade(int volume, int price)
	{
		System.out.println("TRADE " + volume + "@" + price);
	}

}
