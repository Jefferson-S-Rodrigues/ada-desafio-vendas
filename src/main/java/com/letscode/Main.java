package com.letscode;

import com.letscode.model.Sale;
import com.letscode.service.CSVService;
import com.letscode.service.Statistic;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String localSales = "sales.csv";

        try {
            List<Sale> sales = CSVService.read_csv(localSales);
            printStatistics(sales);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void printStatistics(List<Sale> sales) {
        System.out.println(String.format("Total de Vendas: R$ %.2f", Statistic.salesAmount(sales)));
        System.out.println("Total de Vendas por mês:");
        Statistic.totalSalesMonth(sales).forEach((year, v) -> v.forEach((month, total) -> System.out.println(String.format("* %d/%d: R$ %.2f", month, year, total))));
        System.out.println("Total de vendas por produto:");
        Statistic.totalSalesProduct(sales).forEach((product, total) -> System.out.println(String.format("* %s: R$ %.2f", product, total)));
        System.out.println(String.format("Produto mais vendido: %s", Statistic.bestSellingProduct(sales)));
        System.out.println(String.format("Produto menos vendido: %s", Statistic.leastSoldProduct(sales)));
        System.out.println(String.format("Venda mais cara: %s", Statistic.mostExpensiveSale(sales)));
        System.out.println(String.format("Venda mais barata: %s", Statistic.cheapestSale(sales)));
        System.out.println("Preço médio dos produtos vendidos:");
        Statistic.averagePriceProductsSold(sales).forEach((product, value) -> System.out.println(String.format("* %s: R$ %.2f", product, value)));
    }
}
