# 1. O que são Built-in Functions
Built-in functions são **funções nativas da linguagem**, fornecidas diretamente pelo núcleo da linguagem ou sua biblioteca padrão essencial.
> Built-in functions são funções que existem por padrão, sem que o programador precise criá-las, <br>
> e fazem parte do contrato básico da linguagem.

* Já vêm prontas
* Não dependem do domínio da aplicação
* São estáveis e padronizadas
* Geralmente disponíveis sem import explícito (ou com import implícito)

## 2. Para que servem Built-in Functions
Servem para resolver **necessidades universais, comuns** a qualquer programa.
Exemplos de categorias:

* Entrada e saída
* Validação básica
* Erros e falhas
* Conversões primitivas
* Operações fundamentais de runtime

## 3. Built-in Functions são exclusivas de uma linguagem?

Conceito: NÃO <br>
Implementação: SIM <br>

Explicação técnica:

* Toda linguagem possui built-ins

* Mas o conjunto e os nomes são exclusivos de cada linguagem

Exemplos:

* Python → print, len, type
* Java → System.out.println
* Kotlin → println, require, check, error

> Built-in functions são universais como conceito, mas específicas por linguagem.

---

## 4. Built-in Functions NÃO são Extension Functions
Diferença essencial:

Built-in:

* não pertencem a um tipo

* não usam receptor (this)

* não parecem métodos de objeto

Extension:

* sempre aparecem como objeto.funcao()

 ````  kotlin 
println("abc")      // built-in
"abc".uppercase()   // NÃO built-in (é extension)

````

---

# 1. O que são Extension Functions

[Kotlin Doc](https://kotlinlang.org/docs/extensions.html#extension-functions)

[Extension video](https://youtu.be/8kMT-H6ck2U?si=RKsuaAr9eNF7q9vm)

**Extension functions** são funções declaradas fora de uma classe, mas que podem ser chamadas **como se fossem métodos dessa classe**.

Definição técnica precisa:

> Extension functions permitem adicionar comportamento a um tipo existente **sem alterar seu código-fonte**, **sem herança** e **sem polimorfismo**.

Pontos fundamentais:
- Não modificam a classe original
- Não têm acesso a membros `private`
- São resolvidas em **tempo de compilação**
- São apenas uma conveniência sintática

Exemplo:

```kotlin
fun String.removerEspacos(): String {
    return this.replace(" ", "")
}

val texto = "Olá Mundo"
texto.removerEspacos()
```

Apesar da aparência:
* String não foi alterada
* removerEspacos não é método real
* é apenas uma função externa com sintaxe orientada a objeto

---

 ## 3. Extension Functions são exclusivas de uma linguagem?
 Conceito: NÃO <br>
Implementação idiomática: SIM

Explicação técnica:

* A ideia de “função aplicada a um tipo” existe em várias linguagens

* Mas o modelo seguro, estático e idiomático é raro

  ---

##  5. Extension Functions mais importantes e usadas com frequência
5.1 Coleções (uso diário em backend e APIs)
````text
map
filter
flatMap
any
all
none
first
firstOrNull
lastOrNull
count
groupBy
associate
rverse
````
5.2 Strings
````text
trim
uppercase
lowercase
split
replace
substring
startsWith
endsWith
isBlank
isEmpty
````
5.3 Funções de escopo
````text
let
run
also
apply
with
````
5.4 Nulos e controle de fluxo
````text
takeIf
takeUnless
orEmpty
````
