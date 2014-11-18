package se.aphelion.assignment.ordermatcher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import se.aphelion.assignment.ordermatcher.order.BuyOrder;
import se.aphelion.assignment.ordermatcher.order.SellOrder;

/**
 * This class does command handling, for example: input taking and parsing.
 *
 */
public class CommandHandler
{
	private Pattern pattern;
	private Matcher matcher;
	private OrderBook orderBook;
	private Broker broker;
	private static final String COMMAND_FORMAT = "(BUY|SELL) ([0]*[1-9]+\\d*|[1-9]+\\d*)@([0]*[1-9]+\\d*\\s*|[1-9]+\\d*\\s*)|(PRINT\\s*)";

	/**
	 * Class constructor
	 */
	public CommandHandler()
	{
		pattern = Pattern.compile(COMMAND_FORMAT);
		orderBook = OrderBook.getInstance();
		broker = new Broker(OrderBook.getInstance());
		this.commandListener();
	}

	/**
	 * Listens for commands from console, and then sends to another method for
	 * validation.
	 * 
	 */
	public void commandListener()
	{
		try
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String input;

			System.out.print(">");
			while ((input = br.readLine()) != null)
			{
				this.commandValidation(input);
				System.out.print(">");
			}
		}
		catch (IOException io)
		{
			System.out.println(io.getMessage());
		}
	}

	/**
	 * This method validates input string against COMMAND_FORMAT, It also checks
	 * the validity of inserted data (integer number) for the command. After
	 * validation and fragmentation of input it calls runCommand with parameter.
	 * 
	 * @param String
	 *        input
	 */
	private void commandValidation(final String input)
	{

		matcher = pattern.matcher(input);
		if (matcher.matches())
		{
			String command = (matcher.group(4) == null) ? matcher.group(1) : matcher.group(4);
			try
			{
				this.runCommand(command.trim(), matcher.group(2), matcher.group(3));
			}
			catch (NumberFormatException e)
			{
				System.out.println("Enter valid data (integer number).");
			}
		}
		else
		{
			System.out.println("Use commands format shown in example. Only positive numbers are supported");
		}
	}

	/**
	 * Runs the input command, depending on the command performers different
	 * actions.
	 * 
	 * @param command
	 * @param quantity
	 * @param price
	 * @throws NumberFormatException
	 */
	private void runCommand(String command, String quantity, String price) throws NumberFormatException
	{

		switch (command)
		{
			case "PRINT":
				System.out.println(orderBook.toString());
				break;
			// System.exit(0);
			case "BUY":
				broker.processOrder(new BuyOrder(Integer.parseInt(quantity.trim()), Integer.parseInt(price.trim())));
				break;
			case "SELL":
				broker.processOrder(new SellOrder(Integer.parseInt(quantity.trim()), Integer.parseInt(price.trim())));
				break;
			default:
				System.out.println("UNKNOWN COMMAND");
				break;
		}
	}
}
