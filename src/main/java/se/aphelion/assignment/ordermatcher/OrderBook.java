package se.aphelion.assignment.ordermatcher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import se.aphelion.assignment.ordermatcher.order.BuyOrder;
import se.aphelion.assignment.ordermatcher.order.Order;
import se.aphelion.assignment.ordermatcher.order.SellOrder;

/**
 * This class is the data structure of the project. It contains two ArrayList.
 * One for sell and one for buy. Different operations in the ArrayList are
 * perform according to business requirement.
 * 
 */

public class OrderBook
{

	private List<SellOrder> sellList;
	private List<BuyOrder> buyList;
	private static final OrderBook ORDERBOOK = new OrderBook();

	/**
	 * 
	 */
	private OrderBook()
	{
		clear();
	}

	public List<SellOrder> getSellList()
	{
		return sellList;
	}

	public List<BuyOrder> getBuyList()
	{
		return buyList;
	}

	public void clear()
	{
		sellList = new ArrayList<SellOrder>();
		buyList = new ArrayList<BuyOrder>();
	}

	/**
	 * 
	 * @return ORDERBOOK
	 */
	public static OrderBook getInstance()
	{
		return ORDERBOOK;
	}

	/**
	 * Add new order to the corresponding list.
	 * 
	 * @param order
	 * @return true if order was added successfully, false otherwise
	 */
	public boolean addNewOrder(Order order)
	{

		if (order instanceof BuyOrder)
		{
			buyList.add((BuyOrder) order);
			Collections.sort(buyList);
			return true;
		}
		else if (order instanceof SellOrder)
		{
			sellList.add((SellOrder) order);
			Collections.sort(sellList);
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Removes the top order for the opposite list.
	 * 
	 * @param order
	 * @return true if order was deleted successfully, false otherwise
	 */
	public boolean removeTopOrder(Order order)
	{

		if (order instanceof BuyOrder && !sellList.isEmpty())
		{
			sellList.remove(0);
			return true;
		}
		else if (order instanceof SellOrder && !buyList.isEmpty())
		{
			buyList.remove(0);
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Update the top order for the corresponding list.
	 * 
	 * @param order
	 * @return true if order was updated successfully, false otherwise
	 */
	public boolean updateTopOrder(Order order)
	{

		if (order instanceof BuyOrder && !buyList.isEmpty())
		{
			buyList.get(0).setVolume(order.getVolume());
			return true;
		}
		else if (order instanceof SellOrder && !sellList.isEmpty())
		{
			sellList.get(0).setVolume(order.getVolume());
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Get the best order for the received order, it is always the top order in
	 * the corresponding list.
	 * 
	 * @param order
	 * @return order: which is the best order, or null: in the case of having no
	 *         offer
	 * 
	 */
	public Order getBestOrder(Order order)
	{

		if (order instanceof BuyOrder && !sellList.isEmpty())
		{
			return sellList.get(0);

		}
		else if (order instanceof SellOrder && !buyList.isEmpty())
		{
			return buyList.get(0);
		}
		else
		{
			return null;
		}
	}

	@Override
	public String toString()
	{
		StringBuilder orders = new StringBuilder("--- SELL ---");
		for (SellOrder order : sellList)
		{
			orders.append("\nSELL " + order.toString());
		}
		orders.append("\n--- BUY ---");
		for (BuyOrder order : buyList)
		{
			orders.append("\nBUY " + order.toString());
		}
		return orders.toString();
	}

}
