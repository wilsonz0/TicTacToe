package bot;

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
			
			for ( Board newState : board.getAllPossibleStates("O")) {
				value = Math.max(value, minimax(newState, 'X'));
			}
			
			return value;
		}
		else {
			// minimize		
			int value = Integer.MAX_VALUE;
			
			for ( Board newState : board.getAllPossibleStates("X")) {
				value = Math.max(value, minimax(newState, 'O'));
			}
			
			return value;
		}
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
