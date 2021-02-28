package mru.tsc.controller;

import java.util.ArrayList;

import mru.game.model.*;
import mru.tsc.model.Animal;
import mru.tsc.model.BoardGame;
import mru.tsc.model.Toy;

public class GameManager {
	
	private static final String FILE_PATH = "res/toys.txt";
	ArrayList<Toy> toys = new ArrayList<>();
	Toy t1 = new Animal("type1");
	toys.add(t1);
	Toy t2 = new BoardGame(10);
	toys.add(t2);
	
	if(toys.get(1) instanceof Animal ) {
		Animal tt = (Animal)toys.get(1);
		System.out.println("Type Toy Animal");
	} else {
		System.out.println("Type Toy BoardGame");
		BoardGame tt = (BoardGame)toys.get(1);
	}

}
