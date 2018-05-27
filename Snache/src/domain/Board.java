package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board
{
	private int width;
	private int height;
	private BoardPiece boardPieces[][];
	
	public Board(int width, int height)
	{
		this.width = width;
		this.height = height;
		boardPieces = new BoardPiece[width][height];
		
		for(int i = 0; i < width; ++i)
		{
			for(int j = 0; j < height; ++j)
			{
				boardPieces[i][j] = new BoardPiece(i, j);
			}
		}
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public BoardPiece getAvailableColumn(int size)
	{
		if(size >= height) return null;
		
		// random permutation of the column indexes
		List<Integer> columnIndexes = new ArrayList<Integer>();
		for(int j = 0; j < width; ++j) columnIndexes.add(j);
		Collections.shuffle(columnIndexes);
		
		int currAvailableSize = 0;
		int currentColumn;
		
		for(int i = 0; i < width; ++i)
		{
			currentColumn = columnIndexes.get(i);
			
			for(int row = 0; row < height; ++row)
			{
				if(!boardPieces[row][currentColumn].isEmpty())
				{
					currAvailableSize = 0;
				}
				
				else
				{
					// a space with enough length was found:  return the upmost position of it
					if(++currAvailableSize > size)
					{
						return new BoardPiece(row - size , currentColumn);
					}
				}
			}
			
		}
		
		currAvailableSize = 0;
	
		return null;
	}
	
	public BoardPiece getAvailableRow(int size)
	{
		if(size >= width) return null;
		
		// random permutation of the column indexes
		List<Integer> rowIndexes = new ArrayList<Integer>();
		for(int j = 0; j < height; ++j) rowIndexes.add(j);
		Collections.shuffle(rowIndexes);
		
		int currAvailableSize = 0;
		int currentRow;
		
		for(int i = 0; i < height; ++i)
		{
			currentRow = rowIndexes.get(i);
			
			for(int column = 0; column < width; ++column)
			{
				if(!boardPieces[currentRow][column].isEmpty())
				{
					currAvailableSize = 0;
				}
				
				else
				{
					// a space with enough length was found: return the leftmost position of it
					if(++currAvailableSize > size)
					{
						return new BoardPiece(currentRow, column-size);
					}
				}
			}
			
		}
		
		currAvailableSize = 0;
	
		return null;
	}
	
	public BoardPiece getBoardPiece(int row, int column)
	{
		return boardPieces[row][column];
	}
	
	public void occupyBoardPiece(BoardPiece p)
	{
		boardPieces[p.getRow()][p.getColumn()].setEmpty(false);
	}
	
	public void freeBoardPiece(BoardPiece p)
	{
		boardPieces[p.getRow()][p.getColumn()].setEmpty(true);
	}
}