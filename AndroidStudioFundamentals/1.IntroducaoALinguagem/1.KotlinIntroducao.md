

# 📌 Parte 1 — Introdução ao Kotlin

---

## 1.1 O que é Kotlin?

Kotlin é uma linguagem de programação:

- Estática (tipagem forte em tempo de compilação)
- Moderna
- Interoperável com Java
- Desenvolvida pela JetBrains
- Oficial para Android

---

## 1.2 Problema que o Kotlin resolve

O Kotlin foi criado para corrigir limitações do Java:

| Problema no Java | Solução no Kotlin |
|---|---|
| Muito código boilerplate | Sintaxe concisa |
| NullPointerException comum | Null Safety nativo |
| Verbosidade extrema | Inferência de tipo |
| Falta de funções modernas | Funções de alta ordem, lambdas |
| Classes extensas | Data classes automáticas |

---

## 1.3 Kotlin vs Java (visão geral)

```java
// Java
String nome = "João";
System.out.println(nome);
````

```kotlin
// Kotlin
val nome = "João"
println(nome)
````


##  Diferença estrutural

| Característica     | Java        | Kotlin   |
| ------------------ | ----------- | -------- |
| Ponto e vírgula    | Obrigatório | Opcional |
| Inferência de tipo | Limitada    | Forte    |
| Verbosidade        | Alta        | Baixa    |
| Null safety        | Não         | Sim      |
| Funcional          | Limitado    | Nativo   |

---

# 📌 Parte 2 — Variáveis e Tipos
### 2.1 Declaração de variáveis

```kotlin
val nome = "João"   // imutável
var idade = 25      // mutável
````

| Palavra-chave | Mutabilidade | Uso                      |
| ------------- | ------------ | ------------------------ |
| `val`         | Imutável     | Preferencial             |
| `var`         | Mutável      | Apenas quando necessário |



### 2.2 Tipagem
```kotlin
val nome: String = "João"
val idade: Int = 25
````
Inferência:
```kotlin
val nome = "João" // String inferido automaticamente
````


### 2.3 Tipos primitivos

| Kotlin  | Java equivalente |
| ------- | ---------------- |
| Int     | int              |
| Double  | double           |
| Boolean | boolean          |
| String  | String           |

⚠️ Diferença importante
No Kotlin:
> Tudo é objeto (na prática)



### 2.4 Null Safety (conceito crítico)

```kotlin
var nome: String = oi
var nome: String? = null
````
* String → não aceita null <br>
* String? → aceita null  <br>

---

## Operadores
```kotlin
nome?.length      // safe call
nome ?: "default" // elvis operator
````
Caso não encotre um valor: <br>
* Safe Call: Retorne null ao invés de crashar <br>
* Elvis operator: Retorne a expressão definida à direita ("default") ao invés de crashar <br>

| Problema             | Java   | Kotlin                      |
| -------------------- | ------ | --------------------------- |
| NullPointerException | Comum  | Evitado por tipo            |
| Verificação de null  | Manual | Obrigatória pelo compilador |


---

## 📌 Parte 3 — Controle de Fluxo

### 3.1 If como expressão
```kotlin
val maior = if (a > b) a else b
````


### 3.2 When (substitui switch)
```kotlin
when (x) {
    1 -> println("Um")
    2 -> println("Dois")
    else -> println("Outro")
}
````
Vantagens: <br>
* Não precisa break <br>
* Suporta ranges <br>
* Suporta tipos <br>

### 3.3 Loops
```kotlin
for (i in 1..5) {
    println(i)
}
````

| Estrutura       | Java     | Kotlin       |
| --------------- | -------- | ------------ |
| for tradicional | Sim      | Sim          |
| foreach         | Limitado | Forte        |
| ranges          | Não      | Sim (`1..5`) |

---

## 📌 Parte 4 — Funções

### 4.1 Declaração
```kotlin
fun somar(a: Int, b: Int): Int {
    return a + b
}
````

### 4.2 Função simplificada
```kotlin
fun somar(a: Int, b: Int) = a + b
````

## 4.3 Funções como primeira classe
```kotlin
val soma = { a: Int, b: Int -> a + b }
````

| Feature              | Java     | Kotlin  |
| -------------------- | -------- | ------- |
| Lambdas              | Verboso  | Simples |
| Função como variável | Limitado | Nativo  |
| Alta ordem           | Complexo | Natural |

---

## 📌 Parte 5 — Classes e Objetos
### 5.1 Classe básica
```kotlin
class Pessoa(val nome: String, var idade: Int)
````

### 5.2 Comparação com Java
```kotlin
// Java
class Pessoa {
    private String nome;
    private int idade;

    // getters/setters
}
````

### 5.3 Data Class
```kotlin
data class Usuario(val nome: String, val idade: Int)
````
O que o Kotlin gera automaticamente: <br>
toString() <br>
equals() <br>
hashCode() <br>
copy() <br>

| Feature      | Java   | Kotlin     |
| ------------ | ------ | ---------- |
| Boilerplate  | Alto   | Baixo      |
| Data classes | Manual | Automático |


--- 

## 📌 Parte 6 — Herança e Interfaces
### 6.1 Classe aberta
```kotlin
open class Animal
````

### 6.2 Herança
```kotlin
class Cachorro : Animal()
````
Regra importante
> Tudo é final por padrão no Kotlin


### 6.3 Interface
```kotlin
interface Veiculo {
    fun acelerar()
}
````

| Feature          | Java       | Kotlin     |
| ---------------- | ---------- | ---------- |
| Métodos default  | Limitado   | Sim        |
| Múltipla herança | Interfaces | Interfaces |

---

## 📌 Parte 7 — Coleções

### 7.1 List
```kotlin
val lista = listOf(1, 2, 3)
````

### 7.2 MutableList
```kotlin
val lista = mutableListOf(1, 2, 3)
lista.add(4)
````

| Tipo        | Mutável |
| ----------- | ------- |
| List        | ❌       |
| MutableList | ✅       |
| Set         | ❌       |
| Map         | ❌       |


### 7.3 Funções funcionais
```kotlin
lista.filter { it > 2 }
lista.map { it * 2 }
````

| Feature    | Java     | Kotlin  |
| ---------- | -------- | ------- |
| Streams    | Verboso  | Simples |
| Filter/Map | Complexo | Nativo  |

---

## 📌 Parte 8 — Programação Funcional

### 8.1 Lambda
```kotlin
val dobro = { x: Int -> x * 2 }
````

### 8.2 Função de alta ordem
```kotlin
fun operar(a: Int, b: Int, op: (Int, Int) -> Int): Int {
    return op(a, b)
}
````

### 8.3 Imutabilidade
> Preferência por val:
> Imutabilidade = menos bugs

| Conceito      | Java     | Kotlin      |
| ------------- | -------- | ----------- |
| FP nativo     | Parcial  | Forte       |
| Lambdas       | Verboso  | Simples     |
| Imutabilidade | Opcional | Incentivada |


---

## 📌 Parte 9 — Extensions (Recurso exclusivo forte)
### 9.1 Função de extensão
```kotlin
fun String.reverter(): String {
    return this.reversed()
}
````

| Feature             | Java | Kotlin |
| ------------------- | ---- | ------ |
| Extensão de classes | ❌    | ✅      |

---

## 📌 Parte 10 — Coroutines (visão introdutória)
### 10.1 Problema
Java: <br>
* Threads pesadas <br>
* Complexidade alta <br>

### 10.2 Kotlin
```kotlin
launch {
    delay(1000)
    println("Executou")
}
````

| Aspecto        | Java Threads | Kotlin Coroutines |
| -------------- | ------------ | ----------------- |
| Custo          | Alto         | Baixo             |
| Sintaxe        | Complexa     | Simples           |
| Escalabilidade | Limitada     | Alta              |

---

## 📌 Resumo Geral Kotlin vs Java
| Aspecto       | Java     | Kotlin      |
| ------------- | -------- | ----------- |
| Verbosidade   | Alta     | Baixa       |
| Null Safety   | Não      | Sim         |
| FP            | Limitado | Forte       |
| Classes       | Verbosas | Concisas    |
| Extensions    | Não      | Sim         |
| Coroutines    | Não      | Sim         |
| Imutabilidade | Opcional | Incentivada |
