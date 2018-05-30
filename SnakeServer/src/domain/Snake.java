package domain;

import java.util.ArrayList;
import java.util.List;

import controller.EnumSnakeDirection;

public class Snake
{
	// number of SnakePiece objects in the List<SnakePiece> object
	private int bodySize;
	
	private SnakePiece head;
	private List<SnakePiece> body;
	private EnumSnakeDirection direction;
	private long id;
	
	public Snake(int headRow, int headColumn, int bodySize, EnumSnakeDirection initialDirection, long id)
	{
		this.id = id;
		this.head = new SnakePiece(headRow, headColumn);
		this.body = new ArrayList<SnakePiece>();
		this.bodySize = bodySize;
		
		for(int i = 0; i < bodySize; ++i)
		{
			if(initialDirection == EnumSnakeDirection.LEFT)
			{
				body.add(new SnakePiece(headRow, headColumn+i+1));
			}
			
			else if(initialDirection == EnumSnakeDirection.RIGHT)
			{
				body.add(new SnakePiece(headRow, headColumn-i-1));
			}
			
			else if(initialDirection == EnumSnakeDirection.DOWN)
			{
				body.add(new SnakePiece(headRow-i-1, headColumn));
			}
			
			else if(initialDirection == EnumSnakeDirection.UP)
			{
				body.add(new SnakePiece(headRow+i+1, headColumn));
			}
			
			this.direction = initialDirection;
		}
	}
	
	public void move()
	{
		// Iniciar nova cabeça
		SnakePiece newHead = null;
		
		// Mover Cabeça
		if ( direction == EnumSnakeDirection.UP )
			newHead = new SnakePiece((head.getRow()-1), head.getColumn());
		else if ( direction == EnumSnakeDirection.DOWN )
			newHead = new SnakePiece((head.getRow()+1), head.getColumn());
		else if ( direction == EnumSnakeDirection.LEFT)
			newHead = new SnakePiece(head.getRow(), (head.getColumn()-1));
		else if ( direction == EnumSnakeDirection.RIGHT)
			newHead = new SnakePiece(head.getRow(), (head.getColumn()+1));
		
		// Mover Corpo
		for ( int i = body.size()-1; i >=1; i--)
			body.get(i).copy(body.get(i-1)); 
		
		// Mover primeira posição do corpo
		body.get(0).copy(head);
		
		head.copy(newHead);
	}
	
	public SnakePiece getHead()
	{
		return head;
	}
	
	public List<SnakePiece> getBody()
	{
		return body;
	}
	
	public EnumSnakeDirection getDirection() {
		return direction;
	}

	public void setDirection(EnumSnakeDirection direction) {
		this.direction = direction;
	}

	public SnakePiece getTail()
	{
		return body.get(bodySize-1);
	}
		
	@Override
	public int hashCode()
	{
		return Long.hashCode(id);
	}
	
	public String toString()
	{
		String res = "";
		res += "[" + head.getRow() +", " + head.getColumn() + "], ";
		
		for(SnakePiece p : body)
		{
			res += "[" + p.getRow() +", " + p.getColumn() + "], ";
		}
		
		return res;
	}

	@Override
	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		Snake other = (Snake) obj;
		
		return id == other.id;
	}
}
