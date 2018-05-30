package socket;

import controller.SharedSnakeDirection;

public class ClientInfo
{
	private final int MAX_ITERATIONS = 3;
	private int deadCont;
	private SharedSnakeDirection sharedSnakeDirection;
	
	public ClientInfo(SharedSnakeDirection sharedSnakeDirection)
	{
		this.sharedSnakeDirection = sharedSnakeDirection;
		deadCont = MAX_ITERATIONS;
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
		sharedSnakeDirection.produce(direction);
		deadCont = MAX_ITERATIONS;
	}
}