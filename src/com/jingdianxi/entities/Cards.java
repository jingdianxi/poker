package com.jingdianxi.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Cards {
	// ����ֽ��
	private List<Poker> cards = new ArrayList<Poker>();
	// ���ƽ��
	private List<Poker> hand = new ArrayList<Poker>();
	// ���췽������������ֽ��
	public Cards() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 13; j++) {
				Poker poker = new Poker(i, j);
				cards.add(poker);
			}
		}
	}
	// ��ʾ����ֽ��
	public void showAll() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 13; j++) {
				System.out.print(cards.get(i * 13 + j));
			}
			System.out.println();
		}
	}
	// ϴ��
	public void shuffle() {
		Collections.shuffle(cards);
	}
	// ����
	public void deal() {
		for (int i = 0; i < 5; i++) {
			hand.add(cards.get(i));
		}
	}
	// �����������
	public void show() {
		for (int i = 0; i < 4; i++) {
			for (int j = i + 1; j < 5; j++) {
				// ��������������
				if (hand.get(i).getPoint() > hand.get(j).getPoint()) {
					Poker temp = hand.get(i);
					hand.set(i, hand.get(j));
					hand.set(j, temp);
				} else if (hand.get(i).getPoint() == hand.get(j).getPoint()) {
					// ������ͬʱ����ɫ����
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
	// �����ж�
	public String judge() {
		// ͳ�ƻ�ɫ
		Set<Integer> suitSet = new HashSet<Integer>();
		for (Poker poker : hand) {
			suitSet.add(poker.getSuit());
		}
		// ͳ�Ƶ���
		Set<Integer> pointSet = new HashSet<Integer>();
		for (Poker poker : hand) {
			pointSet.add(poker.getPoint());
		}
		// ͳ�Ƶ����ֲ����
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
		// ���ݵ���ͳ���ж�
		switch (pointSet.size()) {
		case 2:
			// ���������ú�
			if (map.get(hand.get(0).getPoint()) == 1 || map.get(hand.get(0).getPoint()) == 4) {
				return "���� Four of a Kind";
			} else {
				return "���ú� Fullhouse";
			}
		case 3:
			// ����������
			switch (map.get(hand.get(0).getPoint())) {
			case 2:
				return "���� Two Pairs";
			case 3:
				return "���� Three of a Kind";
			default:
				if (map.get(hand.get(1).getPoint()) == 2) {
					return "���� Two Pairs";
				} else {
					return "���� Three of a Kind";
				}
			}
		case 4:
			// һ��
			return "һ�� One Pair";
		default:
			// ˳�ӻ�ͬ��˳
			if ((hand.get(4).getPoint() - hand.get(3).getPoint() == 1
					&& hand.get(3).getPoint() - hand.get(2).getPoint() == 1
					&& hand.get(2).getPoint() - hand.get(1).getPoint() == 1
					&& hand.get(1).getPoint() - hand.get(0).getPoint() == 1)
					|| (hand.get(0).getPoint() == 0 && hand.get(1).getPoint() == 9 && hand.get(2).getPoint() == 10
							&& hand.get(3).getPoint() == 11 && hand.get(4).getPoint() == 12)) {
				if (suitSet.size() == 1) {
					return "ͬ��˳ Straight Flush";
				} else {
					return "˳�� Straight";
				}
			} else {
				// ͬ�����޶�
				if (suitSet.size() == 1) {
					return "ͬ�� Flush";
				} else {
					return "�޶� Zilch";
				}
			}
		}
	}
}
