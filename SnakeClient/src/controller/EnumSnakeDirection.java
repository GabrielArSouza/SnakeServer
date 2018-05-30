package controller;

/**
 * All possible movements for the snakes. 
 * Based on: http://mrmcgeek.blogspot.com/2009/06/custom-string-values-for-enum-in-java.html
 */
public enum EnumSnakeDirection
{
	/** move up. */
	UP ("U"),
	
	/** move left. */
	LEFT ("L"),
	
	/** move down. */
	DOWN ("D"),
	
	/** move right. */
	RIGHT ("R"),
	
	/** don't move. */
	DONT_MOVE ("N");
	
	/** String representation of the enum value. */
	private String direction;
	
	/**
	 * Instantiates a new EnumSnakeDirection object.
	 *
	 * @param direction string representation of the direction
	 */
	private EnumSnakeDirection(String direction)
	{
		this.direction = direction;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	public String toString()
	{
		return direction;
	}
	
	/**
	 * Gets the EnumSnakeDirection object that is represented by a given string.
	 *
	 * @param snakeDirection the string representation
	 * @return the corresponding EnumSnakeDirection
	 */
	public static EnumSnakeDirection getValue(String snakeDirection)
	{
		for(EnumSnakeDirection direction : EnumSnakeDirection.values())
		{
			if(direction.toString().equalsIgnoreCase(snakeDirection))
			{
				return direction;
			}
		}
		
		return null;
	}
}
