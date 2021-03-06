package controller;

/**
 * Direction of a snake.
 * This class is used to decide to which direction a snake should move.
 *  
 * Based on: http://mrmcgeek.blogspot.com/2009/06/custom-string-values-for-enum-in-java.html
 */
public enum EnumSnakeDirection
{
	/** up direction. */
	UP ("U"),
	
	/** left direction. */
	LEFT ("L"),
	
	/** down direction. */
	DOWN ("D"),
	
	/** right direction. */
	RIGHT ("R"),
	
	/** in case of direction change, this value means that the direction didn't change. */
	SAME ("S");
	
	/** String representation of the EnumSnakeDirection value. */
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
