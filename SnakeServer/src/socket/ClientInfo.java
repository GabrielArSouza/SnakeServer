package socket;

import controller.SharedSnakeDirection;

public class ClientInfo
{
	private final int MAX_ITERATIONS = 3;
	private int deadCont;
	private SharedSnakeDirection sharedSnakeDirection;
	private boolean directionUpdated;
	
	public ClientInfo(SharedSnakeDirection sharedSnakeDirection)
	{
		this.sharedSnakeDirection = sharedSnakeDirection;
		deadCont = MAX_ITERATIONS;
		directionUpdated = false;
	}
	
	public int getDeadCont()
	{
		return deadCont;
	}
	
	public void decreaseDeadCont()
	{
		--deadCont;
	}
	
	public void updateDirection(String direction)
	{
		if(! directionUpdated )
		{
			directionUpdated = true;
			sharedSnakeDirection.produce(direction);
			deadCont = MAX_ITERATIONS;
		}
		
	}
}
