package com.hmmes.utils;

import java.util.Random;

public class RandomUtils {
	
	
	
	/**
	 * ����0��max��������� ����0 ������max
	 * 
	 * @param max
	 *            �����������
	 * @return
	 */
	public static int getRandom(int max) {
		return new Random().nextInt(max);
	}

	/**
	 * ���� min��max��������� ���� min �������� max
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static int getRandom(int min, int max) {
		int r = getRandom(max - min);
		return r + min;
	}
	
	
	
	/**
	 * ����0��max��������� ����0 ������max
	 * 
	 * @param max
	 *            �����������
	 * @return
	 */
	public static long getRandomLong(long max) {
		long randNum  = (long)(Math.random()* max);// + minId;
		return randNum;
	}

	/**
	 * ���� min��max��������� ���� min �������� max
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static long getRandomLong(long min, long max) {
		long r = getRandomLong(max - min);
		return r + min;
	}
	
	
	/**
	 * �����ȡͼƬ
	 * @param num
	 * @return
	 */
	public static long getSQLRandom(Long num){
		Long newNum  = getRandomLong(num);
		String numStr = newNum+"";
		if(numStr.length() < 8){
			return newNum;
		}
		int randLen = getRandom(8,numStr.length());
		return Long.valueOf(numStr.substring(0, randLen));
	}
}
