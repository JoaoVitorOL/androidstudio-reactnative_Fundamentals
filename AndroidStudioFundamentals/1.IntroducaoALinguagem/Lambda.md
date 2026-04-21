# 👅 Funções Lambda na Programação

[Doc Kotlin](https://kotlinlang.org/docs/lambdas.html)

[Video lambdas](https://youtu.be/7FFusJuFlgo?si=3SWpabhzo1NAzqp3)


## 1. O que são funções Lambda

Funções **lambda** são funções **anônimas**, ou seja, não possuem um nome explícito e normalmente são definidas **no local onde são usadas**.

Características principais:
- Não possuem nome.
- Geralmente são curtas (Uma linha).
- Expressam uma única operação ou expressão.
- Podem ser atribuídas a variáveis, passadas como parâmetro ou usadas imediatamente.

Forma conceitual: <br>
entrada(s) → expressão → saída <br>

---

## 2. Por que funções Lambda existem
Funções lambda surgiram para **resolver problemas específicos** de clareza, concisão e expressividade do código. <br>
Motivações principais: <br>

* Evitar a criação de funções auxiliares triviais.
* Tornar operações simples mais legíveis.
* Facilitar programação funcional (map, filter, reduce).
* Permitir passar comportamento como dado.
* 
Em outras palavras:
> quando o comportamento é pequeno e local, a lambda evita burocracia.

---

## 3. ✔️ Quando funções Lambda são usadas
Funções lambda são usadas quando:

* A função é curta
* tem lógica simples
* não será reutilizada em vários pontos
* O foco é o que fazer, não como a função se chama
* A função é passada como argumento

Casos típicos:
* Iterações sobre coleções
*  Filtros
* Transformações de dados
* Callbacks
* Comparadores
* Eventos

---

## ❎  4. Quando NÃO usar funções Lambda

Funções lambda  **não são** ideais quando:
* A lógica é complexa.
* Há múltiplas etapas internas.
* É necessário reaproveitamento.
* É importante nomear a intenção da regra.
* Há necessidade de testes unitários isolados.

 > Se você precisa comentar uma lambda para explicá-la, ela já deveria ser uma função normal.

---

## 5. Diferença entre Lambda e função tradicional


### 5.1 Função tradicional

Características:

* Possui nome.
* Pode ter múltiplas linhas.
* Pode conter controle de fluxo complexo.
* Facilmente reutilizável.
* Mais adequada para regras de negócio.

Exemplo conceitual:
````text
função calcularDesconto(valor):
    se valor > 100:
        retornar valor * 0.9
    senão:
        retornar valor
``````

### 5.2 Função Lambda
Características:

* Anônima.
* Normalmente uma única expressão.
* Criada no ponto de uso.
* Menos verbosa.
* Ideal para operações locais.

Exemplo conceitual:
````text
(valor) -> valor > 100 ? valor * 0.9 : valor
``````

----


## Interpretação de uma função Lambda


## Cenário
Você está desenvolvendo um **backend** que processa pedidos de compra.
Cada pedido possui:
- valor total
- status
- cliente

Você precisa:
- filtrar pedidos válidos
- calcular valor com desconto
- gerar uma lista final pronta para persistência ou resposta de API

---

## 1. Modelo de dados (contexto)

```kotlin
data class Pedido(
    val id: Long,
    val valor: Double,
    val status: Status
)

enum class Status {
    CRIADO,
    PAGO,
    CANCELADO
}
````
Regra de negócio:

* considerar apenas pedidos com status PAGO
* aplicar 10% de desconto se o valor > 500
* gerar uma lista de valores finais
  
````kotlin
val pedidosPagosComDesconto =
    pedidos
        .filter { it.status == Status.PAGO }
        .map { pedido ->
            if (pedido.valor > 500)
                pedido.valor * 0.9
            else
                pedido.valor
        }
````

### filter { it.status == Status.PAGO }
* filter espera uma função: (Pedido) -> Boolean
* it representa cada Pedido da lista
* Retorna true apenas para pedidos pagos

  Forma explícita equivalente:
````kotlin
.filter { pedido: Pedido -> pedido.status == Status.PAGO }
````

### map { pedido -> ... }
map espera uma função: (Pedido) -> Double
pedido (it) foi nomeado:
* porque a lógica é maior
* melhora legibilidade
* A última expressão é o retorno

 ## 5. O que o it representa neste contexto
> “o elemento atual da coleção” <br>
> Nest caso it = Pedido atual da iteração <br>
