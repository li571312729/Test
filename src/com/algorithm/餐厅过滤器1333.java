package com.algorithm;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 给你一个餐馆信息数组 restaurants，其中  restaurants[i] = [idi, ratingi, veganFriendlyi, pricei, distancei]。
 * 你必须使用以下三个过滤器来过滤这些餐馆信息。
 *
 * 其中素食者友好过滤器 veganFriendly 的值可以为 true 或者 false，
 * 如果为 true 就意味着你应该只包括 veganFriendlyi 为 true 的餐馆，为 false 则意味着可以包括任何餐馆。
 * 此外，我们还有最大价格 maxPrice 和最大距离 maxDistance 两个过滤器，它们分别考虑餐厅的价格因素和距离因素的最大值。
 *
 * 过滤后返回餐馆的 id，按照 rating 从高到低排序。
 * 如果 rating 相同，那么按 id 从高到低排序。
 * 简单起见， veganFriendlyi 和 veganFriendly 为 true 时取值为 1，为 false 时，取值为 0 。
 */
public class 餐厅过滤器1333 {

    public static void main(String[] args) {
        System.out.println(filterRestaurants(new int[][]{{1,4,1,40,10}, {2,8,0,50,5}, {3,8,1,30,4}, {4,10,0,10,3}, {5,1,1,15,1}}
        , 1, 50, 10));
    }

    public static List<Integer> filterRestaurants(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {

        Stream<int[]> stream = Arrays.stream(restaurants);
        //第一步过滤是否素食
        if(veganFriendly == 1){
            stream = stream.filter(o -> o[2] == veganFriendly);
        }

        //第二步过滤价格和距离
        stream = stream.filter(o -> o[3] <= maxPrice && o[4] <= maxDistance);

        //最后一步进行排序
        List<Integer> result = stream.sorted((o1, o2) -> {
            if (o1[1] == o2[1]) {
                return o2[0] - o1[0];
            } else {
                return o2[1] - o1[1];
            }
        }).map(o -> o[0]).collect(Collectors.toList());
        return result;
    }
}
