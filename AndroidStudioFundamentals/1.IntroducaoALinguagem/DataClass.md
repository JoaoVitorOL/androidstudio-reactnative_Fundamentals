# `data class` em Kotlin — Explicação Técnica e Prática

[Kotlin doc](https://kotlinlang.org/docs/data-classes.html)

[DataClass video](https://youtu.be/5mMpM8zK4pY?si=uhpdqAgdTCWXrUsV)

## 1. O que é um `data class`

Um `data class` é uma **classe cujo objetivo principal é representar dados**, não comportamento.

Ela existe para modelar **estruturas de dados imutáveis ou quase imutáveis**, reduzindo código repetitivo que, em outras linguagens, é considerado boilerplate.

Em termos formais:
- É uma classe orientada a **estado**
- Não é focada em regras de negócio
- O compilador gera automaticamente código padrão

---

## 2. Exemplo mínimo

```kotlin
data class Usuario(
    val id: Long,
    val nome: String,
    val email: String
)
````

Com isso, ( no caso do Kotlin ) gera automaticamente:

* equals()
* hashCode()
* toString()
* copy()
* componentN() (para destructuring)

Sem você escrever nada disso.

### Esse data class é equivalente conceitualmente a algo como:
```kotlin
class Usuario(
    val id: Long,
    val nome: String,
    val email: String
) {

    override fun equals(other: Any?): Boolean { ... }

    override fun hashCode(): Int { ... }

    override fun toString(): String { ... }

    fun copy(
        id: Long = this.id,
        nome: String = this.nome,
        email: String = this.email
    ): Usuario { ... }
}
````


---

## 4. Por que data class existe

Problema clássico:

* Classes que só carregam dados
* Muito código repetitivo
* Alta chance de erro manual

Solução do Kotlin:

* Declarar intenção
* Automatizar código mecânico
* Tornar o modelo explícito

> Se a classe existe para “carregar dados”, ela provavelmente é um data class.

---

## 5. Diferença entre class e data class

### Classe comum
```kotlin
class Produto(
    val id: Long,
    val preco: Double
)
````
Problemas:

* equals compara referência, não conteúdo
* toString é inútil para log
* Não existe copy
* Comparações falham silenciosamente

### Data class
```kotlin
data class Produto(
    val id: Long,
    val preco: Double
)
````
Benefícios imediatos:

* Comparação por valor
* Logs legíveis
* Cópias seguras
* Código previsível

### 6. Comparação prática
```kotlin
val p1 = Produto(1, 10.0)
val p2 = Produto(1, 10.0)
````
### Classe comum
p1 == p2 → false

### Data Class
p1 == p2 → true

## Motivo
> data class redefine equals para comparar propriedades do construtor primário.

---

## 9. Regras obrigatórias de um data class

Um data class:
* Deve ter construtor primário
* Deve ter ao menos uma propriedade val ou var
* Não pode ser abstract, open ou sealed

Somente propriedades do construtor primário:

* entram em equals
* entram em hashCode
* entram em toString

## Erro comum no mundo real
> Colocar regra de negócio em data class:
