package com.basicsoops;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OverlappingMerge {
	// List<Intervals> list = new ArrayList<Intervals>();
	private static List<Intervals> getIntervalsObject(List<Intervals> intervals) {
		List<Intervals> result = new ArrayList<Intervals>();
		for (int i = 0; i < intervals.size(); i++) {
			for (int j = 0; j < intervals.size(); j++) {
				if (intervals.get(i).getStartPoint() < intervals.get(j).getStartPoint()
						|| intervals.get(i).getEndPoint() < intervals.get(j).getEndPoint()) {
					result.add(new Intervals(intervals.get(i).getStartPoint(), intervals.get(j).getEndPoint()));
				}
			}
		}
		for (Intervals merged : result) {
			System.out.println(merged.getStartPoint() + " " + merged.getEndPoint());
		}

		return null;
	}

	public static void main(String[] args) {
		List<Intervals> intervals = new ArrayList<Intervals>();

		// Intervals intervals2 = new Intervals();

		System.out.println("Enter number of intervals : ");
		Scanner sc = new Scanner(System.in);
		int intervalsLen = sc.nextInt();
		System.out.println("enter intervals start point and end point");
		for (int i = 0; i < intervalsLen; i++) {
			intervals.add(new Intervals(sc.nextInt(), sc.nextInt()));
		}
		getIntervalsObject(intervals);
		sc.close();

	}
}
