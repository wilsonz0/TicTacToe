package bot;

import java.util.ArrayList;

import game.Board;
import javafx.util.Pair;

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
	
	public Pair<int[], Integer> getNextMove(char player) {
		return minimax(board, new int[] {0,0}, player, 2);
	}
	
	private Pair<int[], Integer> minimax(Board state, int[] recentCoord, char player, int depth) {
		if (depth < 1 || isTerminal(state)) return calculateValue(state, recentCoord);
		
		if (player == 'O') {
			// maximize
			int value = Integer.MIN_VALUE;
			int[] bestCoord = recentCoord;
			ArrayList<int[]> coords = state.getAllPossibleActions("O");
			
			for (int[] coord : coords) {
				Board newState = calculateResult(state, coord, "O");
				System.out.println("DEPTH: " + depth + " - new state run in player == \"O\"");
				newState.printBoard();
				
				Pair<int[], Integer> bestPair = minimax(newState, coord, 'X', depth - 1);
				int comparedValue = Math.max(value, bestPair.getValue());
				if (value < comparedValue) {
					value = comparedValue;
					bestCoord = coord;
					
//					System.out.println("best maximizing");
//					newState.printBoard();
//					System.out.println("value: " + value);
//					System.out.println("bestCoord: " + Arrays.toString(bestCoord));
				}
			}
			
			return new Pair<>(bestCoord, value);
		}
		else {
			// minimize
			int value = Integer.MAX_VALUE;
			int[] bestCoord = recentCoord;
			ArrayList<int[]> coords = state.getAllPossibleActions("X");
			
			for (int[] coord : coords) {
				Board newState = calculateResult(state, coord, "X");
				System.out.println("DEPTH: " + depth + " - new state run in player == \"X\"");
				newState.printBoard();
				
				Pair<int[], Integer> bestPair = minimax(newState, coord, 'O', depth - 1);
				int comparedValue = Math.min(value, bestPair.getValue());
				if (value > comparedValue) {
					value = comparedValue;
					bestCoord = coord;
					
//					System.out.println("best minimizing");
//					newState.printBoard();
//					System.out.println("value: " + value);
//					System.out.println("bestCoord: " + Arrays.toString(bestCoord));
				}
			}
			
			return new Pair<>(bestCoord, value);
		}
	}
	
	private Board calculateResult(Board oldState, int[] coord, String s) {		
		Board newBoard = new Board(oldState);
		newBoard.setMoveOnCoord(coord[0], coord[1], s);
		
		return newBoard;
	}
	
	private Pair<int[], Integer> calculateValue(Board state, int[] recentCoord) {
		int value;
		
		if (state.checkForWin()) {
			if (state.getTurn() == 'O') value = 1;
			else value = -1;
		}
		else {
			value = 0;
		}
		
		return new Pair<>(recentCoord, value);
	}
	
	private boolean isTerminal(Board state) {
		if (state.checkForWin() || state.checkForTie()) return true;
		
		return false;
	}
}
