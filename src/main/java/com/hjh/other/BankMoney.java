package com.hjh.other;

import org.junit.Test;

import java.text.DecimalFormat;

/**
 * @author: hjh
 * @description: 公积金贷款 + 通货膨胀 + 利息计算
 */
public class BankMoney {

  public static DecimalFormat format = new DecimalFormat("######0.00");

  /**
   * 等额本金 金额相同，利息递减
   *
   * @param capital 本金
   * @param interestRate 利率
   * @param years 贷款年限
   * @param inflation 通货膨胀
   */
  public void averageCapital(double capital, double interestRate, int years, double inflation) {
    System.out.println("本金:" + capital + ",利率:" + interestRate + ",年限:" + years);

    // 月利率
    double monthlyInterestRate = interestRate / 12;

    // 总月份
    double monthSum = years * 12;

    // 购买力 通膨率
    double price = 1 + inflation;

    // 每月等额本金还款
    double monthCapital = capital / monthSum;

    // 遍历每个月
    int monthCnt = 1;
    // 余额
    double account = capital;
    printTitle();

    double sum_rates = 0;
    double sum_monthly = 0;
    double sum_price = 0;

    double years_monthly = 0;
    double years_price = 0;
    double sum_prices = 1;

    for (int i = 1; i <= years; i++) {

      //
      // System.out.println("第"+(i-1)+"年,上年月供:"+years_monthly+"上年购买力:"+years_price+",上年通膨:"+sum_prices);
      years_monthly = 0;
      for (int j = 1; j <= 12; j++) {
        double curr_rates = account * monthlyInterestRate;

        account = account - monthCapital;
        double monthly = monthCapital + curr_rates;

        sum_rates += curr_rates;
        sum_monthly += monthly;
        years_monthly += monthly;

        //                print(month_cnt++,month_money,curr_rates,monthly,yue);
      }

      years_price = years_monthly / sum_prices;
      sum_price += years_price;
      sum_prices = sum_prices * price;
    }
    //
    // System.out.println("第"+years+"年,上年月供:"+years_monthly+"上年购买力:"+years_price+",上年通膨:"+sum_prices);

    System.out.println("总计:");
    print(0, capital, sum_rates, sum_monthly, sum_price);

    monthCnt = 1;
    sum_prices = price;
    sum_monthly = 0;
    sum_price = 0;
    System.out.println("等额本息");
    for (int i = 1; i <= years; i++) {

      years_monthly = 0;

      for (int j = 1; j <= 12; j++) {
        double monthly = 3481.65;
        years_monthly += monthly;
        sum_monthly += monthly;
        //                print(month_cnt++,0,0,monthly,0);
      }

      years_price = years_monthly / sum_prices;
      sum_price += years_price;
      //
      // System.out.println("第"+i+"年,当年月供:"+years_monthly+"当年购买力:"+years_price+",当年通膨:"+sum_prices);
      sum_prices = sum_prices * price;
    }
    System.out.println("总计:");
    print(0, capital, sum_monthly - capital, sum_monthly, sum_price);
  }

  public void printTitle() {
    StringBuffer sb = new StringBuffer();
    sb.append("期数")
        .append("\t\t")
        .append("月供本金")
        .append("\t\t")
        .append("月供利息")
        .append("\t\t")
        .append("月供")
        .append("\t\t")
        .append("剩余本金");
    System.out.println(sb);
  }

  public void print(int month, double money, double rates, double monthly, double yue) {
    StringBuffer sb = new StringBuffer();
    sb.append(month)
        .append("\t\t")
        .append(format.format(money))
        .append("\t\t")
        .append(format.format(rates))
        .append("\t\t")
        .append(format.format(monthly))
        .append("\t\t")
        .append(format.format(yue));
    System.out.println(sb);
  }

  @Test
  public void test() {
    averageCapital(800000d, 0.0325d, 30, 0.02);
  }

  /** 等额本息 利息+本金相同 */
  public void averageCapitalPlusInterest(
      double capital, double rates, int years, double inflation) {

    // 总月份
    int monthSum = 0;

    for (int i = 1; i <= years; i++) {
      for (int j = 1; j <= 12; j++) {}
    }
  }
}
