package com.jingdianxi.entities;
/**
 * 纸牌类
 */
public class Poker {
	// 花色
	private int suit;
	// 点数
	private int point;
	// 有参构造方法
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
	// 花色集合
	private String[] suits = { "黑桃", "红心", "方块", "梅花" };
	// 点数集合
	private String[] points = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
	// 重写toString()
	public String toString() {
		return suits[this.suit] + points[this.point] + " ";
	}
}
