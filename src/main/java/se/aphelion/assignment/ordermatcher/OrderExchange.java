package se.aphelion.assignment.ordermatcher;

/**
 * This is the main class where command related works are done, for example: input taking and parsing.
 *
 */
public class OrderExchange
{
	/**
	 * Class constructor
	 */
	private OrderExchange() {}

	/**
	 * Entry point. main() method
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		System.out.println("Valid commands: SELL|BUY|PRINT \nFor example:\nSELL 100@10\nBUY 120@17\nPRINT");
		CommandHandler ch = new CommandHandler();
		ch.commandListener();
	}

}
