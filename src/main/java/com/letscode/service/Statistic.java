package com.letscode.service;

import com.letscode.model.Sale;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Statistic {

    private static Predicate<Sale> filterValue = s -> s.getValue() != null;
    private static Predicate<Sale> filterFull = s -> s.getDate() != null && !s.getName().isBlank() && s.getValue() != null;

    /**
     * Calcula o total de vendas
     *
     * @param sales Lista de vendas
     * @return Total de vendas
     */
    public static double salesAmount(List<Sale> sales) {
        return sales.stream().filter(filterValue).mapToDouble(Sale::getValue).sum();
    }

    /**
     * Calcula o total de vendas por mês
     *
     * @param sales Lista de vendas
     * @return Total de vendas por mês
     */
    public static Map<Integer, Map<Integer, Double>> totalSalesMonth(List<Sale> sales) {
        return sales.stream().filter(filterFull).collect(Collectors.groupingBy(
                a -> a.getDate().getYear(),
                Collectors.groupingBy(
                        m -> m.getDate().getMonthValue(),
                        Collectors.summingDouble(Sale::getValue)
                )
        ));
    }

    /**
     * Calcula o total de vendas por produto
     *
     * @param sales Lista de vendas
     * @return Total de vendas por produto
     */
    public static Map<String, Double> totalSalesProduct(List<Sale> sales) {
        return sales.stream().filter(filterFull).collect(Collectors.groupingBy(
                p -> p.getName(),
                Collectors.summingDouble(Sale::getValue)
        ));
    }

    /**
     * Encontra o produto mais vendido
     *
     * @param sales Lista de vendas
     * @return O nome do produto mais vendido
     */
    public static String bestSellingProduct(List<Sale> sales) {
        return Collections.max(totalSalesProduct(sales).entrySet(), (p1, p2) -> (int) (p1.getValue() - p2.getValue())).getKey();
    }

    /**
     * Encontra o produto menos vendido
     *
     * @param sales Lista de vendas
     * @return O nome do produto menos vendido
     */
    public static String leastSoldProduct(List<Sale> sales) {
        return Collections.max(totalSalesProduct(sales).entrySet(), (p1, p2) -> (int) (p2.getValue() - p1.getValue())).getKey();
    }

    /**
     * Calcula a venda mais cara
     *
     * @param sales Lista de vendas
     * @return A venda mais cara
     */
    public static Sale mostExpensiveSale(List<Sale> sales) {
        return sales.stream().filter(filterValue).max((s1, s2) -> (int) (s1.getValue() - s2.getValue())).get();
    }

    /**
     * Calcula a venda mais barata
     *
     * @param sales Lista de vendas
     * @return A venda mais barata
     */
    public static Sale cheapestSale(List<Sale> sales) {
        return sales.stream().filter(filterValue).max((s1, s2) -> (int) (s2.getValue() - s1.getValue())).get();
    }

    /**
     * Calcula o preço médio dos produtos vendidos
     *
     * @param sales Lista de vendas
     * @return O Preço médio dos produtos vendidos
     */
    public static Map<String, Double> averagePriceProductsSold(List<Sale> sales) {
        return sales.stream().filter(filterFull).collect(Collectors.groupingBy(
                p -> p.getName(),
                Collectors.averagingDouble(Sale::getValue)
        ));
    }
}
