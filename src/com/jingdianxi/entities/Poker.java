package com.jingdianxi.entities;
/**
 * ֽ����
 */
public class Poker {
	// ��ɫ
	private int suit;
	// ����
	private int point;
	// �вι��췽��
	public Poker(int suit, int point) {
		this.suit = suit;
		this.point = point;
	}
	public int getSuit() {
		return suit;
	}
	public int getPoint() {
		return point;
	}
	// ��ɫ����
	private String[] suits = { "����", "����", "����", "÷��" };
	// ��������
	private String[] points = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
	// ��дtoString()
	public String toString() {
		return suits[this.suit] + points[this.point] + " ";
	}
}
