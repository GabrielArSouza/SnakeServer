package domain;

// TODO: Auto-generated Javadoc
/**
 * This class represents a component of the snake. It extends the BoardPiece
 * class so that the board components can be snake pieces. It doesn't have any
 * new attributes.
 */
public class SnakePiece extends BoardPiece
{
	/**
	 * Instantiates a new snake piece from a BoardPiece object. It receives the same
	 * coordinates of the board piece.
	 *
	 * @param old
	 *            the old
	 */
	public SnakePiece(BoardPiece old)
	{
		super(old.getRow(), old.getColumn());
	}

	/**
	 * Instantiates a new snake piece from given coordinates.
	 *
	 * @param row
	 *            the row of the snake piece in the board
	 * @param column
	 *            the column of the snake piece in the board
	 */
	public SnakePiece(int row, int column)
	{
		super(row, column);
	}

	/**
	 * Makes the current piece clone the attributes of other piece.
	 *
	 * @param otherSnakePiece
	 *            the other snake piece whose attributes will be cloned
	 */
	public void copy(SnakePiece otherSnakePiece)
	{
		super.setRow(otherSnakePiece.getRow());
		super.setColumn(otherSnakePiece.getColumn());
	}
}
