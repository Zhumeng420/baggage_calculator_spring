package com.example.baggage_calculator_spring.entity;

import lombok.Data;

@Data
public class Luggage {
    private String luggageType;
    private int sum_length;
    private int chang;
    private int kuan;
    private int gao;
    private int sum_weight;

    public String getLuggageType() {
        return luggageType;
    }

    public void setLuggageType(String luggageType) {
        this.luggageType = luggageType;
    }

    public int getSum_length() {
        return sum_length;
    }

    public void setSum_length(int sum_length) {
        this.sum_length = sum_length;
    }

    public int getSum_weight() {
        return sum_weight;
    }

    public void setSum_weight(int sum_weight) {
        this.sum_weight = sum_weight;
    }

    public int getChang() {
        return chang;
    }

    public void setChang(int chang) {
        this.chang = chang;
    }

    public int getKuan() {
        return kuan;
    }

    public void setKuan(int kuan) {
        this.kuan = kuan;
    }

    public int getGao() {
        return gao;
    }

    public void setGao(int gao) {
        this.gao = gao;
    }
}
