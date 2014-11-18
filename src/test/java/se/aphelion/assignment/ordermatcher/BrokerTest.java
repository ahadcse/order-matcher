package se.aphelion.assignment.ordermatcher;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import se.aphelion.assignment.ordermatcher.order.BuyOrder;
import se.aphelion.assignment.ordermatcher.order.Order;
import se.aphelion.assignment.ordermatcher.order.SellOrder;

/**
 * Unit test class.
 */
public class BrokerTest {
	private OrderBook orderBook;
	private Broker broker;

	@Before
	public void setup() {
		orderBook = OrderBook.getInstance();
		broker = new Broker(orderBook);
		
		orderBook.clear();
	}

	@Test
	public void testProcessOrder_WithoutTrading() {
		// Adding 3 SELL orders.
		broker.processOrder(new SellOrder(100, 55));
		broker.processOrder(new SellOrder(500, 67));
		broker.processOrder(new SellOrder(200, 88));

		// Adding 2 BUY orders.
		broker.processOrder(new BuyOrder(100, 44));
		broker.processOrder(new BuyOrder(300, 33));
		
		// No trading.
		
		// All the SELL orders should exists.
		Assert.assertEquals(3, orderBook.getSellList().size());
		assertOrder(100, 55, orderBook.getSellList().get(0));
		assertOrder(500, 67, orderBook.getSellList().get(1));
		assertOrder(200, 88, orderBook.getSellList().get(2));
		
		// All the BUY orders should exists.
		Assert.assertEquals(2, orderBook.getBuyList().size());
		assertOrder(100, 44, orderBook.getBuyList().get(0));
		assertOrder(300, 33, orderBook.getBuyList().get(1));
	}

	@Test
	public void testProcessOrder_WithTrading() {
		// Adding 1 SELL order.
		broker.processOrder(new SellOrder(100, 10));

		// SELL-list should contain 1 sell order.
		Assert.assertEquals(1, orderBook.getSellList().size());
		assertOrder(100, 10, orderBook.getSellList().get(0));

		// 1 more SELL
		broker.processOrder(new SellOrder(100, 15));
		Assert.assertEquals(2, orderBook.getSellList().size());
		assertOrder(100, 15, orderBook.getSellList().get(1));

		// 1 BUY.
		broker.processOrder(new BuyOrder(120, 17));

		// The above BUY will trade. So, only 1 SELL should exists 80@15
		Assert.assertEquals(1, orderBook.getSellList().size());
		assertOrder(80, 15, orderBook.getSellList().get(0));

		// No BUY
		Assert.assertEquals(0, orderBook.getBuyList().size());
	}

	private void assertOrder(int expectedVolume, int expectedPrice, Order actualOrder) {
		Assert.assertEquals(expectedVolume, actualOrder.getVolume());
		Assert.assertEquals(expectedPrice, actualOrder.getPrice());
	}
}
