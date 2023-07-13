package bot;

import java.util.ArrayList;

import game.Board;

/*
 * This class defines a AI bot that will play against the player.
 * This bot will be using the minimax algorithm to find the optimal move. 
 * 
 * For now, the bot will be always second (the O player)
 */
public class GameBot {
	Board board;
	
	public GameBot(Board board) {
		this.board = board;
	}
	
	public int minimax(Board state, char player) {
		if (isTerminal(state)) return calculateValue(state);
		
		if (player == 'O') {
			// maximize
			int value = Integer.MIN_VALUE;
			ArrayList<int[]> coords = board.getAllPossibleActions("O");
			
			for ( Board newState : calculateResult(coords, "O") ) {
				value = Math.max(value, minimax(newState, 'X'));
			}
			
			return value;
		}
		else {
			// minimize		
			int value = Integer.MAX_VALUE;
			ArrayList<int[]> coords = board.getAllPossibleActions("X");
			
			for ( Board newState : calculateResult(coords, "X") ) {
				value = Math.min(value, minimax(newState, 'O'));
			}
			
			return value;
		}
	}
	
	private ArrayList<Board> calculateResult(ArrayList<int[]> coord, String s) {
		ArrayList<Board> result = new ArrayList<Board>();
		
		for (int[] arr : coord) {
			Board newBoard = new Board(board);
			newBoard.setMove(arr[0], arr[1], s);
		}
		
		return result;
	}
	
	private int calculateValue(Board state) {
		if (state.checkForWin()) {
			if (state.getTurn() == 'O') return 1;
			else return -1;
		}
		else {
			return 0;
		}
	}
	
	private boolean isTerminal(Board state) {
		if (board.checkForWin() || board.checkForTie()) return true;
		
		return false;
	}
}
