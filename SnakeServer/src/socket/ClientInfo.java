package socket;

import controller.EnumSnakeDirection;
import controller.SharedSnakeDirection;
import domain.Snake;

public class ClientInfo
{
	private final int MAX_ITERATIONS = 3;
	private int deadCont;
	private SharedSnakeDirection sharedSnakeDirection;
	private boolean directionUpdated;
	private Snake snake;
	
	public ClientInfo(SharedSnakeDirection sharedSnakeDirection, Snake snake)
	{
		this.sharedSnakeDirection = sharedSnakeDirection;
		deadCont = MAX_ITERATIONS;
		directionUpdated = false;
		this.snake = snake;
	}
	
	public Snake getSnake()
	{
		return snake;
	}
	
	public boolean isActive()
	{
		return deadCont != 0;
	}
	
	public void decreaseDeadCont()
	{
		--deadCont;
	}
	
	public void updateDirection(String direction)
	{
		if(direction.equals(EnumSnakeDirection.SAME.toString()) || directionUpdated)
		{
			return;
		}
		
		else
		{
			System.out.println("updating snake direction to " + direction + "...");
			directionUpdated = true;
			sharedSnakeDirection.produce(direction);
			deadCont = MAX_ITERATIONS;
		}
		
	}
	
	public void setDirectionUpdated(boolean directionUpdated)
	{
		this.directionUpdated = directionUpdated;
	}
}
