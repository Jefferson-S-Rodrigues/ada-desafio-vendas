Enunciado

Desafio: Crie uma aplicação de gerenciamento de vendas de produtos.

Essa aplicacao deverá ler um Arquivo CSV conforme exemplo abaixo e deverá retornar algumas informações sobre as vendas, como:

* Total de vendas
* Total de vendas por mês
* Total de vendas por produto
* Produto mais vendido
* Produto menos vendido
* Venda mais cara
* Venda mais barata
* Preço médio dos produtos vendidos

Requisitos:

* Leitura de um arquivo CSV com os dados de vendas (data da venda, nome do produto, valor da venda)
* Uso de Streams para filtrar vendas por período definido pelo usuário (data inicial e final)
* Uso de Map para agrupar as vendas por produto
* Uso de Collectors para calcular o total de vendas e o valor total para cada produto
* Uso de Interfaces funcionais para definir comportamentos personalizados de agrupamento e cálculo de valores.
* Uso de GroupingBy

Obs: Adicione outros recursos e melhorias à sua escolha.

Bonus: Aplicar principios SOLID

Exemplo do arquivo CSV

Data,Nome do Produto,Valor
01/01/2022,Produto A,100
02/01/2022,Produto B,200
03/01/2022,Produto A,150
04/01/2022,Produto C,null
05/01/2022,Produto B,300
06/01/2022,Produto A,50
null
07/01/2022,Produto C,350

