package com.jingdianxi.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Cards {
	// 整副纸牌
	private List<Poker> cards = new ArrayList<Poker>();
	// 分牌结果
	private List<Poker> hand = new ArrayList<Poker>();
	// 构造方法，生成整副纸牌
	public Cards() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 13; j++) {
				Poker poker = new Poker(i, j);
				cards.add(poker);
			}
		}
	}
	// 显示整副纸牌
	public void showAll() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 13; j++) {
				System.out.print(cards.get(i * 13 + j));
			}
			System.out.println();
		}
	}
	// 洗牌
	public void shuffle() {
		Collections.shuffle(cards);
	}
	// 发牌
	public void deal() {
		for (int i = 0; i < 5; i++) {
			hand.add(cards.get(i));
		}
	}
	// 手牌排序后看牌
	public void show() {
		for (int i = 0; i < 4; i++) {
			for (int j = i + 1; j < 5; j++) {
				// 按点数升序排序
				if (hand.get(i).getPoint() > hand.get(j).getPoint()) {
					Poker temp = hand.get(i);
					hand.set(i, hand.get(j));
					hand.set(j, temp);
				} else if (hand.get(i).getPoint() == hand.get(j).getPoint()) {
					// 点数相同时按花色排序
					if (hand.get(i).getSuit() > hand.get(j).getSuit()) {
						Poker temp = hand.get(i);
						hand.set(i, hand.get(j));
						hand.set(j, temp);
					}
				}
			}
		}
		for (Poker poker : hand) {
			System.out.print(poker);
		}
		System.out.println();
	}
	// 牌型判断
	public String judge() {
		// 统计花色
		Set<Integer> suitSet = new HashSet<Integer>();
		for (Poker poker : hand) {
			suitSet.add(poker.getSuit());
		}
		// 统计点数
		Set<Integer> pointSet = new HashSet<Integer>();
		for (Poker poker : hand) {
			pointSet.add(poker.getPoint());
		}
		// 统计点数分布情况
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < 13; i++) {
			int count = 0;
			for (Poker poker : hand) {
				if (i == poker.getPoint()) {
					count++;
				}
			}
			map.put(i, count);
		}
		// 根据点数统计判断
		switch (pointSet.size()) {
		case 2:
			// 四条或满堂红
			if (map.get(hand.get(0).getPoint()) == 1 || map.get(hand.get(0).getPoint()) == 4) {
				return "四条 Four of a Kind";
			} else {
				return "满堂红 Fullhouse";
			}
		case 3:
			// 三条或两对
			switch (map.get(hand.get(0).getPoint())) {
			case 2:
				return "两对 Two Pairs";
			case 3:
				return "三条 Three of a Kind";
			default:
				if (map.get(hand.get(1).getPoint()) == 2) {
					return "两对 Two Pairs";
				} else {
					return "三条 Three of a Kind";
				}
			}
		case 4:
			// 一对
			return "一对 One Pair";
		default:
			// 顺子或同花顺
			if ((hand.get(4).getPoint() - hand.get(3).getPoint() == 1
					&& hand.get(3).getPoint() - hand.get(2).getPoint() == 1
					&& hand.get(2).getPoint() - hand.get(1).getPoint() == 1
					&& hand.get(1).getPoint() - hand.get(0).getPoint() == 1)
					|| (hand.get(0).getPoint() == 0 && hand.get(1).getPoint() == 9 && hand.get(2).getPoint() == 10
							&& hand.get(3).getPoint() == 11 && hand.get(4).getPoint() == 12)) {
				if (suitSet.size() == 1) {
					return "同花顺 Straight Flush";
				} else {
					return "顺子 Straight";
				}
			} else {
				// 同花或无对
				if (suitSet.size() == 1) {
					return "同花 Flush";
				} else {
					return "无对 Zilch";
				}
			}
		}
	}
}
