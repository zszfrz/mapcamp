package com.mapcamp.search;

import org.springframework.stereotype.Component;
import com.mapcamp.domain.entity.Post;

/*Postの評価平均*/
//コンポーネントか？
@Component
public class Search_rate {

	Post post;
	
	public int AverageRate() {
		double rate = 0;
		rate = post.getYummy() + post.getClean() + post.getStaff() + post.getSpeed();

		double average;
		// review.size=0が trueの時，average=0
		// falseの時 sum / reviews.size()
		// average = reviews.size() == 0 ? 0 : sum / reviews.size();
		average = rate / 4;
		
		return (int) Math.round(average);

	}
}
