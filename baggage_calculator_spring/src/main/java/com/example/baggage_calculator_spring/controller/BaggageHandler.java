package com.example.baggage_calculator_spring.controller;

import com.example.baggage_calculator_spring.entity.Info;
import com.example.baggage_calculator_spring.entity.Luggage;
import jdk.swing.interop.LightweightContentWrapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/baggage")
public class BaggageHandler {

    /**
     * @param list   行李列表
     * @param weight 可免费托运一件的行李限制重量
     */
    public void removeByWeight(List<Luggage> list, int weight) {
        if (list == null) return;
        for (int i = 0; i < list.size(); i++) {
            Luggage luggage = list.get(i);
            if (Objects.equals(luggage.getLuggageType(), "普通行李") && luggage.getSum_weight() <= weight) {
                list.remove(i);
                break;
            }
        }
    }

    public int sumWeight(List<Luggage> list) {
        if (list == null) return 0;
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i).getSum_weight();
        }
        return sum;
    }

    public int outWeight(List<Luggage> list, int freeWeight, int freeWeight1, int okLength, int freeNum, int p1, int p2, int p3, int p4) {
        if (list.isEmpty()) return 0;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getSum_weight() < freeWeight && list.get(i).getSum_length() < okLength && freeNum > 0) {
                freeNum--;
                list.remove(i);
            }
            if (freeNum <= 0) {
                break;
            }
        }
        int sum = 0;
        if (freeNum > 0 && !list.isEmpty()) {
//            Collections.sort(list, new Comparator<Luggage>() {
//                @Override
//                public int compare(Luggage lug1, Luggage lug2) {
//                    // TODO Auto-generated method stub
//                    if (lug1.getSum_weight() != lug2.getSum_weight()) {
//                        return lug1.getSum_weight() - lug2.getSum_weight();
//                    } else {
//                        return lug1.getSum_length() - lug2.getSum_length();
//                    }
//
//                }
//            });
            for (int i = 0; i < (Math.min(freeNum, list.size())); i++) {
                int weight = list.get(i).getSum_weight();
                int length = list.get(i).getSum_length();
                if (weight > freeWeight1 && length > okLength) {
                    sum += p4;
                    list.remove(i);
                    freeNum--;
                    i--;
                } else if (weight <= freeWeight && length > okLength) {
                    sum += p3;
                    list.remove(i);
                    freeNum--;
                    i--;
                } else if (weight > freeWeight1 && length <= okLength) {
                    sum += p2;
                    list.remove(i);
                    freeNum--;
                    i--;
                } else if (weight > freeWeight && length <= okLength) {
                    sum += p1;
                    list.remove(i);
                    freeNum--;
                    i--;
                }
            }
        }
        return sum;
    }

    public int outNumber(List<Luggage> list, int p1, int p2, int p3) {
        if (list.isEmpty()) return 0;
        int sum = 0;
        if (list.size() == 1) {
            sum += p1;
        } else if (list.size() == 2) {
            sum += p1 + p2;
        } else {
            sum += p1 + p2 + (list.size() - 2) * p3;
        }
        list.removeAll(list);
        return sum;
    }

    private int outPrice(List<Luggage> list, String airLevel, int p1, int p2, int p3, int p4, int a, int b, int c) {
        if (list == null) return 0;
        int sum = 0;
        if (Objects.equals(airLevel, "头等、公务舱旅客")) {
            sum += outWeight(list, 32, 32, 158, 2, p1, p2, p3, p4);

        } else if (Objects.equals(airLevel, "悦享经济舱、超级经济舱")) {
            sum += outWeight(list, 23, 28, 158, 2, p1, p2, p3, p4);
        } else if (Objects.equals(airLevel, "经济舱")) {
            sum += outWeight(list, 23, 28, 158, 2, p1, p2, p3, p4);
        }
        sum += outNumber(list, a, b, c);
        return sum;
    }

    private int specialLuggage(List<Luggage> list) {
        if (list == null) return 0;
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            String luggageType = list.get(i).getLuggageType();
            int weight = list.get(i).getSum_weight();
            if (!Objects.equals(luggageType, "普通行李")) {
                if (Objects.equals(luggageType, "皮划艇/独木舟等")) {
                    if (weight <= 23) {
                        sum += 2600;
                    } else if (weight > 23 && weight <= 32) {
                        sum += 3900;
                    } else {
                        sum += 5200;
                    }
                } else if (Objects.equals(luggageType, "撑杆/标枪等")) {
                    if (weight <= 23) {
                        sum += 1300;
                    } else if (weight > 23 && weight <= 32) {
                        sum += 2600;
                    } else {
                        sum += 3900;
                    }
                } else if (Objects.equals(luggageType, "小型电器或仪器、媒体设备")) {
                    if (weight <= 23) {
                        sum += 490;
                    } else if (weight > 23 && weight <= 32) {
                        sum += 3900;
                    }
                } else if (Objects.equals(luggageType, "枪支")) {
                    if (weight <= 23) {
                        sum += 1300;
                    } else if (weight > 23 && weight <= 32) {
                        sum += 2600;
                    }
                } else if (Objects.equals(luggageType, "子弹")) {
                    sum += 1300;
                } else if (Objects.equals(luggageType, "小动物(仅限家庭驯养的猫、狗)")) {
                    if (weight <= 8) {
                        sum += 3900;
                    } else if (weight > 8 && weight <= 23) {
                        sum += 5200;
                    } else {
                        sum += 7800;
                    }
                }
                list.remove(i);
                i--;
            }
        }
        return sum;
    }

    @PostMapping("/calculate")
    public String calculatePrice(@RequestBody Info info) {
//        System.out.println(infoBaggage.getFlightKind());
//        System.out.println(infoBaggage.getBabyTicket());

        System.out.println(info.getFlightKind());
        System.out.println(info.getBabyTicket());
        System.out.println(info.getStatusKind());
        System.out.println(info.getInAirfareLevel());
        System.out.println(info.getEconomyAifare());
        System.out.println(info.getLuggageList());
        System.out.println(info.getOutAirfareLevel());
        System.out.println(info.getFlightArea());
        double resultPrice = 0;
        if (Objects.equals(info.getFlightKind(), "国内航班")) {
            List<Luggage> list = info.getLuggageList();
            if (Objects.equals(info.getBabyTicket(), "有婴儿票")) {
                removeByWeight(list, 10);
            }
            if (Objects.equals(info.getStatusKind(), "凤凰指引终身白金卡") || Objects.equals(info.getStatusKind(), "白金卡")) {
                removeByWeight(list, 30);
            } else if (Objects.equals(info.getStatusKind(), "凤凰知音金卡") || Objects.equals(info.getStatusKind(), "银卡") || Objects.equals(info.getStatusKind(), "星空联盟金卡")) {
                removeByWeight(list, 20);
            }
            try {
                resultPrice += specialLuggage(list);
                int sum = sumWeight(list);
                double scal_widget = info.getEconomyAifare() * 0.015;
                if (Objects.equals(info.getInAirfareLevel(), "头等舱")) {
                    if (sum > 40)
                        resultPrice += ((sum - 40) * scal_widget);
                } else if (Objects.equals(info.getInAirfareLevel(), "商务舱")) {
                    if (sum > 30)
                        resultPrice += ((sum - 30) * scal_widget);
                } else if (Objects.equals(info.getInAirfareLevel(), "经济舱")) {
                    if (sum > 20)
                        resultPrice += ((sum - 20) * scal_widget);
                } else {
                    throw new RuntimeException("AirfareLevel data error");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (Objects.equals(info.getFlightKind(), "国际航班")) {
            String level = info.getOutAirfareLevel();
            String area = info.getFlightArea();
            List<Luggage> list = info.getLuggageList();
            resultPrice += specialLuggage(list);
            if (Objects.equals(area, "美洲(除美国/加拿大外)")) {
                resultPrice += outPrice(list, level, 380, 980, 980, 1400, 1400, 2000, 3000);
            } else if (Objects.equals(area, "欧洲/中东与非洲/亚洲/西南太平洋之间航线")) {
                resultPrice += outPrice(list, level, 280, 690, 690, 1100, 1100, 1100, 1590);
            } else if (Objects.equals(area, "加拿大与美洲(除美国/加拿大外)")) {
                resultPrice += outPrice(list, level, 520, 520, 520, 520, 1170, 1170, 1590);
            } else if (Objects.equals(area, "美国(含夏威夷)与美洲(除美国外)")) {
                resultPrice += outPrice(list, level, 690, 1040, 1040, 2050, 1380, 1380, 1590);
            } else if (Objects.equals(area, "非洲与亚洲(除日本外)之间航线")) {
                resultPrice += outPrice(list, level, 210, 520, 520, 830, 830, 1100, 1590);
            }
        }
        return resultPrice + "";
    }
}
