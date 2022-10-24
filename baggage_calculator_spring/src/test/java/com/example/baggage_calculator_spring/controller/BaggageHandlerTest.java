package com.example.baggage_calculator_spring.controller;

import com.example.baggage_calculator_spring.entity.Luggage;

import com.jayway.jsonpath.internal.function.numeric.Sum;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
class BaggageHandlerTest {
    private BaggageHandler baggageHandler = new BaggageHandler();

    @Test
    void testSort() {
        List<Luggage> list = new ArrayList<>();
        Luggage luggage1 = new Luggage();
        luggage1.setSum_weight(50);
        luggage1.setSum_length(60);

        Luggage luggage2 = new Luggage();
        luggage2.setSum_weight(50);
        luggage2.setSum_length(80);

        Luggage luggage3 = new Luggage();
        luggage3.setSum_weight(30);

        list.add(luggage1);
        list.add(luggage2);
        list.add(luggage3);
        System.out.println(list);

        Collections.sort(list, new Comparator<Luggage>() {
            @Override
            public int compare(Luggage lug1, Luggage lug2) {
                // TODO Auto-generated method stub
                if (lug1.getSum_weight() != lug2.getSum_weight()) {
                    return lug1.getSum_weight() - lug2.getSum_weight();
                } else {
                    return lug1.getSum_length() - lug2.getSum_length();
                }

            }
        });
        System.out.println(list);
    }

    @Test
    void testRemoveByWeight() {
        List<Luggage> list = new ArrayList<>();
        List<Luggage> expectList = new ArrayList<>();

        Luggage luggage = new Luggage();
        luggage.setSum_weight(3);
        luggage.setLuggageType("普通行李");
        list.add(luggage);
        expectList.add(luggage);

        luggage = new Luggage();
        luggage.setSum_weight(4);
        luggage.setLuggageType("普通行李");
        list.add(luggage);
        expectList.add(luggage);

        luggage = new Luggage();
        luggage.setSum_weight(5);
        luggage.setLuggageType("普通行李");
        list.add(luggage);
        expectList.add(luggage);

        int freeWeight = 2;
        // 验证结果是否正确
        System.out.println(list);
        baggageHandler.removeByWeight(list, freeWeight);
        System.out.println(list);
        Assert.assertEquals(list, expectList);
        // 验证方法是否被执行了一次
        BaggageHandler tmp = mock(BaggageHandler.class);
        tmp.removeByWeight(list, freeWeight);
        verify(tmp).removeByWeight(list, freeWeight);
    }

    @Test
    void testSumWeight() {
        List<Luggage> list = new ArrayList<>();
        int expectInt = 0;

        Luggage luggage = new Luggage();
        luggage.setSum_weight(1);
        luggage.setLuggageType("普通行李");
        list.add(luggage);

        luggage = new Luggage();
        luggage.setSum_weight(2);
        luggage.setLuggageType("普通行李");
        list.add(luggage);

        luggage = new Luggage();
        luggage.setSum_weight(3);
        luggage.setLuggageType("普通行李");
        list.add(luggage);
        expectInt = 6;
        int result = baggageHandler.sumWeight(list);
        assertEquals(result, expectInt);
        System.out.println(result);
        // 验证方法是否被执行了一次
        BaggageHandler tmp = mock(BaggageHandler.class);
        tmp.sumWeight(list);
        verify(tmp).sumWeight(list);
    }

    @Test
    void testOutWeight() {
        List<Luggage> list = new ArrayList<>();
        int expectInt = 0;

        Luggage luggage = new Luggage();
        luggage.setSum_weight(32);
        luggage.setSum_length(159);
        luggage.setLuggageType("普通行李");
        list.add(luggage);

        luggage = new Luggage();
        luggage.setSum_weight(23);
        luggage.setSum_length(203);
        luggage.setLuggageType("普通行李");
        list.add(luggage);

        int resultExpect = baggageHandler.outWeight(list, 23, 28, 158, 2, 380, 980, 980, 1400);
        expectInt = 1400 + 980;
        assertEquals(resultExpect, expectInt);
        // 验证方法是否被执行了一次
        BaggageHandler tmp = mock(BaggageHandler.class);
        tmp.outWeight(list, 23, 28, 158, 2, 380, 980, 980, 1400);
        verify(tmp).outWeight(list, 23, 28, 158, 2, 380, 980, 980, 1400);
    }

    @Test
    void testOutNumber(){
        List<Luggage> list = new ArrayList<>();
        int expectInt = 0;

        Luggage luggage = new Luggage();
        luggage.setSum_weight(32);
        luggage.setLuggageType("普通行李");
        list.add(luggage);

        int resultExpect = baggageHandler.outNumber(list, 2,3,4);
        expectInt = 2;
        assertEquals(resultExpect, expectInt);
        // 验证方法是否被执行了一次
        BaggageHandler tmp = mock(BaggageHandler.class);
        tmp.outNumber(list,2,3,4);
        verify(tmp).outNumber(list, 2,3,4);
    }

//    @Test
//    void sumWeightTest() {
//        // mock creation
//        BaggageHandler mockedCalculator = mock(BaggageHandler.class);
//
//        // using mock object - it does not throw any "unexpected interaction" exception
//        //mockedList.add("one");
////        mockedList.clear();
//        List<Luggage> list = new ArrayList<>();
//        Luggage luggage = new Luggage();
//        luggage.setSum_weight(100);
//        list.add(luggage);
////        int result = mockedCalculator.testMethod1(0,list);
////        Assert.assertEquals(result,100);
//        int result = mockedCalculator.sumWeight(list);
//        System.out.println(result);
//        // selective, explicit, highly readable verification
////        verify(mockedList).add("one");
////        verify(mockedList).clear();
//    }
}