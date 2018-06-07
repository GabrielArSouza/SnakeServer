package socket;

import controller.EnumSnakeDirection;
import controller.SharedSnakeDirection;
import domain.Snake;

/**
 * This class contains information about a single player connected to the game.
 */
public class ClientInfo
{
	/**
	 * The max number of time steps that the player may not respond to the server.
	 */
	private final int MAX_ITERATIONS = 3;

	/**
	 * Number of chances that the player has to connect to the server. It receives
	 * initially the value of MAX_ITERATIONS. If it becomes 0, the player is
	 * disconnected by the server and its snake is killed.
	 */
	private int deadCont;

	/**
	 * The EnumSnakeDirection wrapper that will be used to move the player's snake.
	 */
	private SharedSnakeDirection sharedSnakeDirection;

	/**
	 * True if the game updated the direction of the player's snake based on the
	 * command sent by the player, false otherwise. This flag is used to update the
	 * player's snake only one time per time step, even if multiple commands were
	 * sent to the server.
	 */
	private boolean directionUpdated;

	/** The player's snake. */
	private Snake snake;

	/**
	 * Instantiates a new object.
	 *
	 * @param sharedSnakeDirection
	 *            the snake direction wrapper
	 * @param snake
	 *            the snake
	 */
	public ClientInfo(SharedSnakeDirection sharedSnakeDirection, Snake snake)
	{
		this.sharedSnakeDirection = sharedSnakeDirection;
		deadCont = MAX_ITERATIONS;
		directionUpdated = false;
		this.snake = snake;
	}

	/**
	 * Gets the player's snake.
	 *
	 * @return the player's snake
	 */
	public Snake getSnake()
	{
		return snake;
	}

	/**
	 * Checks if the player is active. A player is considered active if his/her
	 * deadCont isn't 0, i.e., the server shouldn't disconnect him/her.
	 *
	 * @return true if the player is active, false otherwise
	 */
	public boolean isActive()
	{
		return deadCont != 0;
	}

	/**
	 * Decreases the counter of how many chances the player has to connect to the
	 * server.
	 */
	public void decreaseDeadCont()
	{
		--deadCont;
	}

	/**
	 * It sets the content of the EnumSnakeDirection shared wrapper to a new
	 * direction. The direction is updated only if the argument will make the
	 * current snake's direction change (i.e., if it's nor null neither SAME). It
	 * resets the counter of remaining chances since if this method is called, it
	 * means that the player is active.
	 *
	 * @param direction
	 *            the new direction to be consumed by the player's snake
	 */
	public void updateDirection(String direction)
	{
		deadCont = MAX_ITERATIONS;

		if(direction.equals(EnumSnakeDirection.SAME.toString()) || directionUpdated)
		{
			return;
		}

		else
		{
			System.out.println("updating palyer's snake direction to " + direction + "...");
			directionUpdated = true;
			
			synchronized(sharedSnakeDirection)
			{
				sharedSnakeDirection.produce(direction);
			}
		}

	}

	/**
	 * Sets if the player's snake direction was updated already.
	 *
	 * @param directionUpdated
	 *            true if the direction of the player's snake was updated, false
	 *            otherwise
	 */
	public void setDirectionUpdated(boolean directionUpdated)
	{
		this.directionUpdated = directionUpdated;
	}
}
