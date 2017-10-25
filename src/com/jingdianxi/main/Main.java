package com.jingdianxi.main;

import com.jingdianxi.entities.Cards;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Cards cards = new Cards();
		cards.showAll();
		cards.shuffle();
		System.out.println("œ¥≈∆“‘∫Û");
		cards.showAll();
		cards.deal();
		System.out.println("∑¢≈∆");
		cards.show();
		System.out.println(cards.judge());
	}

}
