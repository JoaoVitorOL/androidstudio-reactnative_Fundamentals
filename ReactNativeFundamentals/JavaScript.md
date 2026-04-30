# 📌 Guia Técnico: Introdução ao JavaScript

> **Nível:** Zero (sem experiência prévia com JavaScript)
> **Linguagem:** JavaScript (ES2015+ / ESNext)
> **Fonte de referência:** [developer.mozilla.org (MDN)](https://developer.mozilla.org/pt-BR/docs/Web/JavaScript)
> **Versão de referência:** ECMAScript 2024 (ES15)

---

## Parte 1 — Introdução e Contextualização

---

### 1.1 O que é JavaScript?

JavaScript é uma **linguagem de programação interpretada, dinamicamente tipada e de propósito geral**, criada por Brendan Eich em 1995 para rodar dentro de navegadores web. Hoje, com o Node.js, ela também roda no servidor, em dispositivos móveis (React Native), em CLIs e em praticamente qualquer ambiente computacional.

É a **única linguagem de programação que roda nativamente em todos os navegadores** do mundo — sem instalação, sem compilação prévia pelo usuário.

O nome oficial e padronizado da linguagem é **ECMAScript** (ES). "JavaScript" é o nome comercial popularizado pela Netscape. A organização ECMA International publica atualizações anuais do padrão. Versões importantes:

| Versão | Ano | O que trouxe de relevante |
|---|---|---|
| ES5 | 2009 | `strict mode`, `Array.forEach`, `JSON` nativo |
| **ES6 / ES2015** | 2015 | `let`/`const`, arrow functions, classes, módulos, Promises, template literals, destructuring |
| ES2017 | 2017 | `async/await` |
| ES2020 | 2020 | Nullish coalescing (`??`), optional chaining (`?.`), `BigInt` |
| ES2022 | 2022 | Top-level `await`, `Array.at()`, campos privados de classe |
| ES2024 | 2024 | `Object.groupBy`, `Promise.withResolvers`, `ArrayBuffer.resize` |

> Quando este guia mencionar "JavaScript moderno", refere-se a ES2015 (ES6) ou posterior — o padrão atual do mercado.

---

### 1.2 O problema que o JavaScript resolve — e cria

JavaScript nasceu para resolver um problema específico: páginas web eram estáticas. Todo clique que precisasse de uma resposta do servidor exigia recarregar a página inteira. O JavaScript permitiu **manipular a página sem recarregá-la**, tornando a web interativa.

Com o tempo, cresceu muito além disso. Hoje é a linguagem mais usada do mundo (Stack Overflow Developer Survey, 2023) — e carrega consigo décadas de decisões de design questionáveis que nunca puderam ser removidas por questões de compatibilidade retroativa.

**Exemplos de comportamentos contraintuitivos históricos do JavaScript:**

```javascript
// Coerção de tipos — JavaScript tenta converter tipos automaticamente
// com resultados surpreendentes
console.log(1 + "2");      // "12"  — número virou string
console.log("5" - 3);      // 2     — string virou número
console.log(true + true);  // 2     — booleanos viraram números
console.log([] + []);      // ""    — arrays viraram strings vazias
console.log([] + {});      // "[object Object]"
console.log({} + []);      // 0     — contextos diferentes, resultado diferente

// null vs undefined — dois valores para "ausência"
console.log(null == undefined);   // true  — comparação solta
console.log(null === undefined);  // false — comparação estrita
console.log(typeof null);         // "object" — bug histórico nunca corrigido
```

O JavaScript moderno (ES6+) e o TypeScript existem parcialmente para mitigar esses problemas com ferramentas como `===` (igualdade estrita), `const`/`let`, e tipagem estática opcional.

---

### 1.3 JavaScript vs TypeScript

**TypeScript** é um superset do JavaScript criado pela Microsoft. Todo código JavaScript válido é TypeScript válido. O TypeScript adiciona **tipagem estática opcional** — os tipos são verificados em tempo de compilação e o compilador (`tsc`) produz JavaScript puro que o navegador/Node executa.

```typescript
// TypeScript — tipos verificados em tempo de compilação
function somar(a: number, b: number): number {
  return a + b;
}

somar(3, 7);      // OK
somar("3", 7);    // ERRO DE COMPILAÇÃO — string não é number
```

```javascript
// JavaScript puro — sem verificação de tipos
function somar(a, b) {
  return a + b;
}

somar(3, 7);      // 10 — OK
somar("3", 7);    // "37" — JavaScript concatenou sem avisar
```

| Critério | JavaScript | TypeScript |
|---|---|---|
| Verificação de tipos | Tempo de execução (erros em produção) | Tempo de compilação (erros antes de rodar) |
| Verbosidade | Baixa | Média |
| Curva de aprendizado | Baixa | Média |
| Uso em projetos novos | Projetos pequenos/scripts | Padrão da indústria |
| React Native | Suportado | **Recomendado** (padrão no `create-expo-app`) |

> Este guia cobre **JavaScript puro (ES2015+)**. Os conceitos se aplicam diretamente ao TypeScript — TypeScript é JavaScript com tipos opcionais por cima.

---

### 1.4 Onde o JavaScript roda

```
┌─────────────────────────────────────────────────────────┐
│               AMBIENTES DE EXECUÇÃO                     │
│                                                         │
│  Navegador (Chrome, Firefox, Safari)                    │
│    → manipula o DOM, responde a eventos, faz requisições│
│                                                         │
│  Node.js                                                │
│    → servidor HTTP, CLI, scripts de automação           │
│                                                         │
│  Deno / Bun                                             │
│    → alternativas modernas ao Node.js                   │
│                                                         │
│  React Native (via Hermes / JavaScriptCore)             │
│    → apps móveis iOS e Android                          │
│                                                         │
│  Electron                                               │
│    → apps desktop (VS Code, Slack, Discord usam isso)   │
└─────────────────────────────────────────────────────────┘
```

**Como rodar JavaScript no seu computador hoje:**

```bash
# Opção 1 — Node.js (instale em nodejs.org)
node meu_arquivo.js

# Opção 2 — REPL interativo (Read-Eval-Print Loop)
node
> 2 + 2
4

# Opção 3 — Console do navegador
# Abra qualquer página → F12 → aba "Console" → digite JS diretamente
```

---

## Parte 2 — Variáveis e Tipos

---

### 2.1 Declaração de variáveis: `var`, `let` e `const`

JavaScript tem três palavras-chave para declarar variáveis. A escolha importa.

```javascript
const nome = "João";   // const → imutável: não pode ser reatribuída
let   idade = 25;      // let   → mutável: pode ser reatribuída
var   legado = true;   // var   → evitar: comportamento problemático
```

**Por que `var` deve ser evitado:**

`var` tem **escopo de função** (não de bloco) e sofre de **hoisting** — o JavaScript move a declaração para o topo da função antes de executar, causando comportamentos confusos:

```javascript
// var — hoisting confuso
console.log(x);  // undefined — não lança erro, mas o valor não existe ainda
var x = 5;
console.log(x);  // 5

// O código acima é interpretado como:
var x;           // declaração é "içada" para o topo
console.log(x);  // undefined
x = 5;
console.log(x);  // 5

// var ignora blocos
if (true) {
  var vazamento = "visível fora do bloco"; // vaza para fora do if
}
console.log(vazamento); // "visível fora do bloco" — surpreendente

// let e const respeitam blocos
if (true) {
  let correto = "confinado ao bloco";
}
console.log(correto); // ReferenceError: correto is not defined
```

**Regra prática:**
- Use `const` por padrão.
- Use `let` quando a reatribuição for necessária.
- Nunca use `var` em código novo.

| Palavra-chave | Mutabilidade | Escopo | Hoisting | Quando usar |
|---|---|---|---|---|
| `const` | Imutável (referência) | Bloco | Não inicializado | **Padrão** |
| `let` | Mutável | Bloco | Não inicializado | Quando precisa reatribuir |
| `var` | Mutável | Função | Inicializado como `undefined` | **Nunca** em código novo |

> **Atenção:** `const` significa que a **referência** não pode ser reatribuída — não que o objeto seja imutável. Um array ou objeto declarado com `const` ainda pode ter seu conteúdo modificado.

```javascript
const lista = [1, 2, 3];
lista.push(4);         // OK — modifica o conteúdo, não a referência
lista = [1, 2, 3, 4]; // TypeError: reatribuição não permitida

const pessoa = { nome: "Ana" };
pessoa.nome = "Maria"; // OK — modifica propriedade
pessoa = {};           // TypeError: reatribuição não permitida
```

---

### 2.2 Tipos de dados primitivos

JavaScript tem **sete tipos primitivos**. Primitivos são imutáveis e passados por valor.

```javascript
// String — texto
const nome = "João";
const saudacao = 'Olá';        // aspas simples ou duplas — equivalentes
const template = `Olá, ${nome}!`;  // template literal — interpolação com ${}

// Number — inteiros e decimais no mesmo tipo (IEEE 754 de 64 bits)
const inteiro = 42;
const decimal = 3.14;
const negativo = -7;
const infinito = Infinity;
const naoENumero = NaN;        // resultado de operações inválidas: "abc" * 2

// BigInt — inteiros arbitrariamente grandes (ES2020)
const grande = 9007199254740993n; // sufixo 'n' indica BigInt

// Boolean
const ativo = true;
const desativado = false;

// undefined — variável declarada mas sem valor atribuído
let semValor;
console.log(semValor); // undefined

// null — ausência intencional de valor (atribuída pelo programador)
let semObjeto = null;

// Symbol — identificador único (avançado)
const id1 = Symbol("id");
const id2 = Symbol("id");
console.log(id1 === id2); // false — cada Symbol é único
```

**A distinção entre `null` e `undefined`:**

```javascript
// undefined → o JavaScript não encontrou um valor aqui
let a;
console.log(a);               // undefined
console.log(typeof a);        // "undefined"

// null → o programador definiu intencionalmente "sem valor"
let b = null;
console.log(b);               // null
console.log(typeof b);        // "object" — bug histórico do JS, nunca corrigido

// Verificação segura para ambos
console.log(a == null);  // true — == null captura tanto null quanto undefined
console.log(b == null);  // true
console.log(a === null); // false — === distingue os dois
```

---

### 2.3 Tipos não-primitivos (objetos)

Tudo que não é primitivo em JavaScript é um **objeto** — incluindo arrays, funções e instâncias de classes. Objetos são passados por referência.

```javascript
// Objeto literal
const pessoa = {
  nome: "Ana",
  idade: 30,
  ativa: true,
};

// Array
const numeros = [1, 2, 3, 4, 5];

// Função (também é um objeto em JavaScript)
const somar = function(a, b) { return a + b; };

// Passagem por referência — importância crítica
const obj1 = { valor: 1 };
const obj2 = obj1;         // obj2 aponta para O MESMO objeto
obj2.valor = 99;
console.log(obj1.valor);   // 99 — obj1 também foi modificado!

// Para copiar um objeto sem compartilhar referência:
const obj3 = { ...obj1 };  // spread operator — cópia rasa (shallow copy)
obj3.valor = 42;
console.log(obj1.valor);   // 99 — obj1 não foi afetado desta vez
```

---

### 2.4 Verificação de tipos com `typeof`

```javascript
typeof "texto"        // "string"
typeof 42             // "number"
typeof true           // "boolean"
typeof undefined      // "undefined"
typeof null           // "object"  ← bug histórico
typeof {}             // "object"
typeof []             // "object"  ← arrays são objetos
typeof function(){}   // "function"
typeof Symbol()       // "symbol"
typeof 42n            // "bigint"

// Para verificar se algo é um array:
Array.isArray([])     // true
Array.isArray({})     // false
```

---

### 2.5 Coerção de tipos e comparações

A coerção automática de tipos é uma das principais fontes de bugs em JavaScript. O operador `==` tenta converter os tipos antes de comparar. O `===` não converte — compara valor **e** tipo.

```javascript
// == (igualdade solta) — faz coerção de tipos
console.log(0 == false);    // true — false vira 0
console.log("" == false);   // true — ambos viram 0
console.log(1 == "1");      // true — string "1" vira número 1
console.log(null == undefined); // true

// === (igualdade estrita) — sem coerção
console.log(0 === false);   // false — tipos diferentes
console.log(1 === "1");     // false — tipos diferentes
console.log(null === undefined); // false

// Regra: sempre use === em comparações
```

**Valores falsy — o que é considerado falso em um contexto booleano:**

```javascript
// Estes valores são falsy (equivalem a false em condicionais):
false
0
-0
0n          // BigInt zero
""          // string vazia
null
undefined
NaN

// Tudo o mais é truthy — inclusive:
"0"         // string "0" é truthy (não é string vazia)
[]          // array vazio é truthy
{}          // objeto vazio é truthy
```

---

## Parte 3 — Funções

---

### 3.1 Declaração de funções

JavaScript tem quatro formas principais de declarar funções:

```javascript
// 1. Function declaration — tem hoisting (pode ser chamada antes da declaração)
function somar(a, b) {
  return a + b;
}

// 2. Function expression — atribuída a uma variável, sem hoisting
const subtrair = function(a, b) {
  return a - b;
};

// 3. Arrow function (ES6) — sintaxe concisa, sem próprio 'this'
const multiplicar = (a, b) => a * b;

// 4. Named function expression — útil para recursão e stack traces
const fatorial = function calcFatorial(n) {
  return n <= 1 ? 1 : n * calcFatorial(n - 1);
};
```

**A arrow function em detalhes:**

```javascript
// Com um parâmetro — parênteses opcionais
const dobrar = x => x * 2;

// Com dois ou mais parâmetros — parênteses obrigatórios
const somar = (a, b) => a + b;

// Com corpo de múltiplas linhas — chaves e return explícito obrigatórios
const processar = (a, b) => {
  const resultado = a * b;
  console.log("Processando...");
  return resultado;
};

// Retornando um objeto literal — envolva em parênteses para evitar ambiguidade
// sem parênteses, as chaves seriam interpretadas como bloco de código
const criarPessoa = (nome, idade) => ({ nome, idade });
```

---

### 3.2 Parâmetros padrão, rest e spread

```javascript
// Parâmetros padrão (ES6) — valor usado quando argumento não é fornecido
function criarUsuario(nome, ativo = true, nivel = 1) {
  return { nome, ativo, nivel };
}

criarUsuario("Ana");                  // { nome: "Ana", ativo: true, nivel: 1 }
criarUsuario("Bruno", false);         // { nome: "Bruno", ativo: false, nivel: 1 }
criarUsuario("Carlos", true, 5);      // { nome: "Carlos", ativo: true, nivel: 5 }

// Rest parameters (...args) — captura quantidade variável de argumentos em array
function somar(...numeros) {
  return numeros.reduce((acc, n) => acc + n, 0);
}

somar(1, 2, 3, 4, 5); // 15

// Spread operator (...) — expande um array ou objeto
const nums1 = [1, 2, 3];
const nums2 = [4, 5, 6];
const todos = [...nums1, ...nums2];   // [1, 2, 3, 4, 5, 6]

const base = { nome: "Ana", idade: 30 };
const completo = { ...base, cargo: "Dev" }; // { nome: "Ana", idade: 30, cargo: "Dev" }
```

---

### 3.3 Funções de primeira classe e alta ordem

Em JavaScript, funções são **valores de primeira classe** — podem ser atribuídas a variáveis, passadas como argumentos e retornadas por outras funções. Isso é o fundamento da programação funcional em JavaScript.

```javascript
// Função como argumento
function executar(operacao, a, b) {
  return operacao(a, b);
}

const resultado = executar((x, y) => x + y, 10, 5); // 15

// Função que retorna outra função (closure)
function criarMultiplicador(fator) {
  // A função interna "lembra" o valor de 'fator' — isso é uma closure
  return function(numero) {
    return numero * fator;
  };
}

const dobrar = criarMultiplicador(2);
const triplicar = criarMultiplicador(3);

dobrar(5);    // 10
triplicar(5); // 15

// Array de funções
const transformacoes = [
  x => x * 2,
  x => x + 10,
  x => x.toString(),
];

let valor = 5;
for (const fn of transformacoes) {
  valor = fn(valor);
}
console.log(valor); // "20"
```

---

### 3.4 Closures — escopo léxico e memória

Uma **closure** é uma função que "lembra" o escopo em que foi criada, mesmo após a função externa ter terminado. É um dos conceitos mais importantes e poderosos do JavaScript.

```javascript
function criarContador(inicio = 0) {
  let count = inicio; // variável privada — não acessível de fora

  return {
    incrementar: () => ++count,
    decrementar: () => --count,
    valor: () => count,
    resetar: () => { count = inicio; },
  };
}

const contador = criarContador(10);
contador.incrementar(); // 11
contador.incrementar(); // 12
contador.decrementar(); // 11
console.log(contador.valor()); // 11
// count não é acessível diretamente — está "fechado" na closure

const outraInstancia = criarContador(0);
outraInstancia.incrementar(); // 1
// cada chamada de criarContador() cria um escopo independente
```

---

### 3.5 O `this` no JavaScript

`this` é um dos conceitos mais confusos do JavaScript. Seu valor depende de **como** e **onde** a função é chamada — não de onde ela foi definida (exceto em arrow functions).

```javascript
// Em uma função comum, 'this' é o objeto que chamou a função
const pessoa = {
  nome: "Ana",
  saudar: function() {
    console.log(`Olá, sou ${this.nome}`);
  },
};

pessoa.saudar(); // "Olá, sou Ana" — this = pessoa

// O problema com callbacks e funções comuns
const pessoa2 = {
  nome: "Bruno",
  saudarDepois: function() {
    setTimeout(function() {
      // 'this' aqui NÃO é pessoa2 — é o contexto global (ou undefined em strict mode)
      console.log(`Olá, sou ${this.nome}`); // undefined
    }, 1000);
  },
};

// Arrow functions resolvem esse problema — herdam o 'this' do escopo externo
const pessoa3 = {
  nome: "Carlos",
  saudarDepois: function() {
    setTimeout(() => {
      // Arrow function herda o 'this' de saudarDepois, que é pessoa3
      console.log(`Olá, sou ${this.nome}`); // "Carlos"
    }, 1000);
  },
};
```

**Regra prática para evitar problemas com `this`:**
- Use **arrow functions** em callbacks, métodos que são passados como argumento, e qualquer lugar onde você precisa que `this` seja o objeto externo.
- Use **function declaration/expression** para métodos de objetos e classes.

---

## Parte 4 — Objetos e Arrays

---

### 4.1 Objetos

```javascript
// Criação
const produto = {
  id: 1,
  nome: "Teclado",
  preco: 150.00,
  disponivel: true,
  // Método — função dentro de objeto
  desconto: function(percentual) {
    return this.preco * (1 - percentual / 100);
  },
  // Sintaxe curta (ES6) — equivalente ao acima
  desconto(percentual) {
    return this.preco * (1 - percentual / 100);
  },
};

// Acesso a propriedades
console.log(produto.nome);        // notação de ponto
console.log(produto["nome"]);     // notação de colchetes — útil para chaves dinâmicas

const chave = "preco";
console.log(produto[chave]);      // 150 — acesso com variável

// Modificação
produto.preco = 130;              // OK mesmo sendo const — modifica propriedade, não referência
produto.emEstoque = 50;           // adiciona nova propriedade

// Remoção
delete produto.disponivel;

// Verificação de existência
"nome" in produto;                // true
produto.hasOwnProperty("id");    // true
```

**Shorthand properties (ES6) — quando variável e chave têm o mesmo nome:**

```javascript
const nome = "Ana";
const idade = 30;
const cargo = "Dev";

// Antes do ES6:
const pessoa = { nome: nome, idade: idade, cargo: cargo };

// Com shorthand (ES6) — equivalente exato:
const pessoa = { nome, idade, cargo };
```

---

### 4.2 Destructuring — desestruturação

Destructuring permite extrair valores de objetos e arrays de forma concisa.

```javascript
// Destructuring de objeto
const usuario = { id: 1, nome: "Ana", email: "ana@email.com", nivel: 3 };

// Sem destructuring
const nome1 = usuario.nome;
const email1 = usuario.email;

// Com destructuring — equivalente, mais conciso
const { nome, email } = usuario;

// Com renomeação
const { nome: nomeUsuario, nivel: nivelAcesso } = usuario;
// nomeUsuario = "Ana", nivelAcesso = 3

// Com valor padrão
const { ativo = true, cargo = "Membro" } = usuario;
// ativo = true (padrão, pois não existe no objeto), cargo = "Membro"

// Destructuring de array
const coordenadas = [40.7128, -74.0060, 10];

const [latitude, longitude] = coordenadas;
// latitude = 40.7128, longitude = -74.0060

// Pular elementos com vírgula
const [, , altitude] = coordenadas;
// altitude = 10

// Destructuring em parâmetros de função
function exibirUsuario({ nome, email, nivel = 1 }) {
  console.log(`${nome} (${email}) — Nível ${nivel}`);
}

exibirUsuario(usuario); // "Ana (ana@email.com) — Nível 3"
```

---

### 4.3 Arrays e métodos funcionais

Arrays em JavaScript têm um conjunto rico de métodos funcionais que operam sem modificar o array original.

```javascript
const numeros = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];

// map — transforma cada elemento, retorna novo array de mesmo tamanho
const dobrados = numeros.map(n => n * 2);
// [2, 4, 6, 8, 10, 12, 14, 16, 18, 20]

// filter — mantém apenas os elementos que passam no teste
const pares = numeros.filter(n => n % 2 === 0);
// [2, 4, 6, 8, 10]

// reduce — combina todos os elementos em um único valor
const soma = numeros.reduce((acumulador, atual) => acumulador + atual, 0);
// 55 (o segundo argumento de reduce é o valor inicial do acumulador)

// find — retorna o PRIMEIRO elemento que passa no teste (ou undefined)
const primeiroPar = numeros.find(n => n % 2 === 0);
// 2

// findIndex — retorna o ÍNDICE do primeiro elemento que passa no teste
const indicePrimeiroPar = numeros.findIndex(n => n % 2 === 0);
// 1

// some — retorna true se ALGUM elemento passa no teste
const temImpar = numeros.some(n => n % 2 !== 0);
// true

// every — retorna true se TODOS os elementos passam no teste
const todosMenorQue100 = numeros.every(n => n < 100);
// true

// includes — verifica se o array contém o valor
numeros.includes(5);  // true
numeros.includes(11); // false

// flat — achata arrays aninhados
const aninhado = [[1, 2], [3, 4], [5, 6]];
aninhado.flat();       // [1, 2, 3, 4, 5, 6]
aninhado.flat(Infinity); // achata qualquer profundidade

// flatMap — map + flat em uma operação
const frases = ["hello world", "foo bar"];
frases.flatMap(f => f.split(" ")); // ["hello", "world", "foo", "bar"]

// Encadeamento — o poder real está em combinar métodos
const resultado = numeros
  .filter(n => n % 2 === 0)      // [2, 4, 6, 8, 10]
  .map(n => n ** 2)               // [4, 16, 36, 64, 100]
  .reduce((acc, n) => acc + n, 0); // 220

// slice — cópia de uma parte do array (não modifica o original)
numeros.slice(2, 5);   // [3, 4, 5] — índices 2 a 4

// splice — modifica o array original (use com cautela)
const copia = [...numeros];
copia.splice(2, 2);    // remove 2 elementos a partir do índice 2
// copia agora é [1, 2, 5, 6, 7, 8, 9, 10]
```

| Método | Modifica original | Retorna | Uso |
|---|---|---|---|
| `map` | ❌ | Novo array | Transformar elementos |
| `filter` | ❌ | Novo array | Filtrar elementos |
| `reduce` | ❌ | Valor único | Agregar elementos |
| `find` | ❌ | Elemento ou `undefined` | Buscar um elemento |
| `some` | ❌ | Boolean | Verificar se algum satisfaz |
| `every` | ❌ | Boolean | Verificar se todos satisfazem |
| `flat` / `flatMap` | ❌ | Novo array | Aplainar |
| `slice` | ❌ | Novo array | Copiar parte do array |
| `push` / `pop` | ✅ | Elemento | Adicionar/remover do final |
| `shift` / `unshift` | ✅ | Elemento | Remover/adicionar do início |
| `splice` | ✅ | Elementos removidos | Inserir/remover em qualquer posição |
| `sort` | ✅ | Array original | Ordenar (cuidado: ordena como string por padrão) |
| `reverse` | ✅ | Array original | Inverter |

---

### 4.4 Optional chaining e Nullish coalescing (ES2020)

```javascript
const usuario = {
  nome: "Ana",
  endereco: {
    cidade: "Florianópolis",
    // rua não existe
  },
};

// Sem optional chaining — verboso e propenso a erro
const rua = usuario.endereco && usuario.endereco.rua
  ? usuario.endereco.rua
  : "Não informada";

// Com optional chaining (?.) — retorna undefined se qualquer parte for null/undefined
const rua = usuario?.endereco?.rua;           // undefined — sem erro
const cep = usuario?.endereco?.cep?.codigo;   // undefined — sem erro

// Nullish coalescing (??) — retorna o lado direito se o lado esquerdo for null ou undefined
// (diferente de || que retorna o lado direito para QUALQUER valor falsy)
const cidade = usuario?.endereco?.cidade ?? "Cidade não informada";
// "Florianópolis"

const telefone = usuario?.contato?.telefone ?? "Sem telefone";
// "Sem telefone"

// Diferença entre ?? e ||
const valor = 0;
console.log(valor ?? "padrão");  // 0     — ?? só atua em null/undefined
console.log(valor || "padrão");  // "padrão" — || atua em qualquer falsy (inclusive 0)
```

---

### 4.5 Iteração moderna

```javascript
const frutas = ["maçã", "banana", "laranja"];
const pessoa = { nome: "Ana", idade: 30, cargo: "Dev" };

// for...of — itera sobre VALUES de qualquer iterável (arrays, strings, Maps, Sets)
for (const fruta of frutas) {
  console.log(fruta);
}

// for...of com índice usando entries()
for (const [indice, fruta] of frutas.entries()) {
  console.log(`${indice}: ${fruta}`);
}

// for...in — itera sobre CHAVES de objetos (evite em arrays)
for (const chave in pessoa) {
  console.log(`${chave}: ${pessoa[chave]}`);
}

// Object.keys / Object.values / Object.entries
Object.keys(pessoa);     // ["nome", "idade", "cargo"]
Object.values(pessoa);   // ["Ana", 30, "Dev"]
Object.entries(pessoa);  // [["nome", "Ana"], ["idade", 30], ["cargo", "Dev"]]

// Transformar objeto em array, processar e reconstruir
const precos = { teclado: 150, mouse: 80, monitor: 900 };

const comDesconto = Object.fromEntries(
  Object.entries(precos).map(([produto, preco]) => [produto, preco * 0.9])
);
// { teclado: 135, mouse: 72, monitor: 810 }
```

---

## Parte 5 — Classes e Orientação a Objetos

---

### 5.1 Classes (ES6)

Classes em JavaScript são **açúcar sintático** sobre o sistema de protótipos existente. Elas não introduzem um novo modelo de herança — apenas tornam a sintaxe mais legível e próxima de linguagens como Java e Kotlin.

```javascript
class Animal {
  // Campo de classe (ES2022) — propriedade declarada na classe, não no construtor
  tipo = "Animal";

  // Campo privado (ES2022) — prefixo # torna inacessível de fora da classe
  #saude = 100;

  // Constructor — executado ao criar uma instância com 'new'
  constructor(nome, especie) {
    this.nome = nome;       // propriedade pública
    this.especie = especie;
  }

  // Método de instância
  emitirSom() {
    console.log("...");
  }

  // Getter — acessa como propriedade, mas executa lógica
  get statusSaude() {
    return this.#saude > 50 ? "Saudável" : "Precisando de cuidados";
  }

  // Setter
  set saude(valor) {
    if (valor < 0 || valor > 100) throw new Error("Saúde deve ser entre 0 e 100");
    this.#saude = valor;
  }

  // Método estático — pertence à classe, não às instâncias
  static criarSelvagem(especie) {
    return new Animal(`${especie} Selvagem`, especie);
  }

  // toString — chamado automaticamente em contextos string
  toString() {
    return `${this.nome} (${this.especie})`;
  }
}

// Herança
class Cachorro extends Animal {
  constructor(nome, raca) {
    super(nome, "Canis lupus familiaris"); // chama o constructor da classe pai
    this.raca = raca;
  }

  // Override — sobrescreve o método da classe pai
  emitirSom() {
    console.log(`${this.nome} late: Au au!`);
  }

  // Método adicional
  buscar(objeto) {
    console.log(`${this.nome} busca o ${objeto}`);
  }
}

// Uso
const rex = new Cachorro("Rex", "Labrador");
rex.emitirSom();                       // "Rex late: Au au!"
console.log(rex instanceof Cachorro);  // true
console.log(rex instanceof Animal);    // true — herança funciona com instanceof
console.log(rex.statusSaude);          // "Saudável"

const selvagem = Animal.criarSelvagem("Lobo");
console.log(selvagem.toString());      // "Lobo Selvagem (Lobo)"
```

---

### 5.2 Prototype — o sistema real por baixo das classes

Entender o prototype chain é fundamental para debugar comportamentos inesperados:

```javascript
// Toda função tem uma propriedade 'prototype'
// Toda instância tem um link interno __proto__ para o prototype do seu construtor

function Pessoa(nome) {
  this.nome = nome;
}

// Adicionando método ao prototype — compartilhado por todas as instâncias
Pessoa.prototype.saudar = function() {
  console.log(`Olá, sou ${this.nome}`);
};

const ana = new Pessoa("Ana");
ana.saudar(); // funciona — JavaScript busca 'saudar' no prototype chain

// Classes são açúcar sintático para isso:
// class Pessoa { saudar() {} }  →  o método vai para Pessoa.prototype
```

---

## Parte 6 — Programação Assíncrona

---

### 6.1 O problema: JavaScript é single-threaded

JavaScript executa em uma única thread. Se você bloquear essa thread com uma operação lenta (buscar um arquivo, fazer uma requisição HTTP), a aplicação trava completamente. A solução do JavaScript é o modelo assíncrono com **event loop**.

```
┌──────────────────────────────────────────────────────────┐
│                    CALL STACK                            │
│  (onde o código síncrono executa, um por vez)            │
└──────────────────────────────┬───────────────────────────┘
                               │
┌──────────────────────────────▼───────────────────────────┐
│                   EVENT LOOP                             │
│  (monitora: está a call stack vazia?)                    │
│  (se sim: move itens da callback queue para a stack)     │
└──────────────────────────────┬───────────────────────────┘
                               │
┌──────────────────────────────▼───────────────────────────┐
│                 CALLBACK QUEUE / TASK QUEUE              │
│  setTimeout, setInterval, eventos do DOM, I/O            │
└──────────────────────────────────────────────────────────┘

Web APIs (browser) / Node.js APIs:
  Recebem as operações assíncronas, executam em segundo plano,
  e quando terminam, colocam o callback na fila.
```

---

### 6.2 Callbacks — o modelo original

```javascript
// Callback — função passada como argumento, chamada quando a operação termina
setTimeout(function() {
  console.log("Executou depois de 1 segundo");
}, 1000);

// O problema: callback hell
buscarUsuario(id, function(usuario) {
  buscarPedidos(usuario.id, function(pedidos) {
    buscarDetalhes(pedidos[0].id, function(detalhe) {
      calcularTotal(detalhe, function(total) {
        // lógica enterrada em 4 níveis de aninhamento
        // tratamento de erros em cada nível
      });
    });
  });
});
```

---

### 6.3 Promises — o modelo intermediário

Uma **Promise** representa um valor que ainda não está disponível, mas estará em algum momento (ou nunca, se der erro). Ela tem três estados: `pending`, `fulfilled` (resolvida com sucesso) e `rejected` (rejeitada com erro).

```javascript
// Criando uma Promise manualmente
const promessa = new Promise((resolve, reject) => {
  // Simulando operação assíncrona
  const sucesso = Math.random() > 0.5;

  setTimeout(() => {
    if (sucesso) {
      resolve({ id: 1, nome: "Ana" }); // resolve com um valor
    } else {
      reject(new Error("Falha ao buscar usuário")); // rejeita com erro
    }
  }, 1000);
});

// Consumindo uma Promise com .then() e .catch()
promessa
  .then(usuario => {
    console.log("Usuário:", usuario.nome);
    return usuario.id; // o retorno de .then() é passado para o próximo .then()
  })
  .then(id => {
    console.log("ID do usuário:", id);
  })
  .catch(erro => {
    console.error("Erro:", erro.message);
  })
  .finally(() => {
    // Executado sempre — com sucesso ou erro
    console.log("Operação concluída");
  });
```

**Métodos estáticos de Promise:**

```javascript
// Promise.all — aguarda TODAS resolverem (falha se qualquer uma falhar)
const [usuarios, produtos, pedidos] = await Promise.all([
  buscarUsuarios(),
  buscarProdutos(),
  buscarPedidos(),
]);
// As três requisições acontecem em paralelo — não uma após a outra

// Promise.allSettled — aguarda TODAS, independente de sucesso ou erro
const resultados = await Promise.allSettled([
  buscarUsuarios(),
  buscarProdutos(),
  promessaQueVaiFalhar(),
]);
resultados.forEach(resultado => {
  if (resultado.status === "fulfilled") {
    console.log("Sucesso:", resultado.value);
  } else {
    console.log("Erro:", resultado.reason);
  }
});

// Promise.race — resolve/rejeita com o PRIMEIRO a terminar
const primeira = await Promise.race([
  buscarDoCacheLocal(),
  buscarDaAPI(),
]);

// Promise.any — resolve com o PRIMEIRO a ter sucesso (rejeita se todos falharem)
const algumSucesso = await Promise.any([
  tentarServidor1(),
  tentarServidor2(),
  tentarServidor3(),
]);
```

---

### 6.4 Async/Await — o modelo moderno

`async/await` é açúcar sintático sobre Promises. Permite escrever código assíncrono como se fosse síncrono, mantendo todos os benefícios das Promises.

```javascript
// Sem async/await — Promises encadeadas
function buscarDadosDoUsuario(id) {
  return buscarUsuario(id)
    .then(usuario => buscarPedidos(usuario.id))
    .then(pedidos => buscarDetalhes(pedidos[0].id))
    .catch(erro => console.error(erro));
}

// Com async/await — equivalente, mais legível
async function buscarDadosDoUsuario(id) {
  try {
    const usuario = await buscarUsuario(id);           // pausa até resolver
    const pedidos = await buscarPedidos(usuario.id);   // pausa até resolver
    const detalhe = await buscarDetalhes(pedidos[0].id);
    return detalhe;
  } catch (erro) {
    console.error("Erro:", erro.message);
    throw erro; // re-lança o erro para o chamador tratar também
  } finally {
    console.log("Busca concluída");
  }
}

// async/await em paralelo — use Promise.all para não serializar operações independentes
async function buscarTudo() {
  // ❌ Serializado — busca um após o outro (lento)
  const usuarios = await buscarUsuarios();
  const produtos = await buscarProdutos();

  // ✅ Paralelo — busca simultaneamente (rápido)
  const [usuarios, produtos] = await Promise.all([
    buscarUsuarios(),
    buscarProdutos(),
  ]);
}
```

**Uma função `async` sempre retorna uma Promise**, mesmo que você retorne um valor primitivo:

```javascript
async function retornarCinco() {
  return 5;
}

retornarCinco(); // Promise<5> — não o número 5 diretamente
retornarCinco().then(console.log); // 5
const cinco = await retornarCinco(); // 5 — apenas dentro de função async
```

---

### 6.5 Fetch API — requisições HTTP nativas

```javascript
// GET básico
async function buscarUsuarios() {
  const resposta = await fetch("https://jsonplaceholder.typicode.com/users");

  if (!resposta.ok) {
    // resposta.ok é true para status 200-299
    throw new Error(`Erro HTTP: ${resposta.status}`);
  }

  const dados = await resposta.json(); // parser de JSON — também retorna Promise
  return dados;
}

// POST com corpo e headers
async function criarTarefa(titulo) {
  const resposta = await fetch("https://jsonplaceholder.typicode.com/todos", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      "Authorization": "Bearer meu-token",
    },
    body: JSON.stringify({ titulo, concluida: false }),
  });

  return resposta.json();
}

// Tratamento de erros centralizado
async function requisicao(url, opcoes = {}) {
  try {
    const resposta = await fetch(url, opcoes);
    if (!resposta.ok) throw new Error(`HTTP ${resposta.status}: ${resposta.statusText}`);
    return resposta.json();
  } catch (erro) {
    if (erro.name === "TypeError") {
      // Erro de rede — sem conexão
      throw new Error("Sem conexão com a internet");
    }
    throw erro;
  }
}
```

---

## Parte 7 — Módulos (ES Modules)

---

### 7.1 O sistema de módulos moderno

Módulos ES (ESModules) são o padrão nativo do JavaScript para organizar código em arquivos separados. Cada arquivo é um módulo com seu próprio escopo — variáveis não vazam para outros módulos.

```javascript
// matematica.js — exportando
export const PI = 3.14159;

export function somar(a, b) {
  return a + b;
}

export function subtrair(a, b) {
  return a - b;
}

// export default — exportação padrão, apenas uma por arquivo
export default class Calculadora {
  static multiplicar(a, b) { return a * b; }
}
```

```javascript
// app.js — importando
// Importações nomeadas — usam o nome exato exportado
import { PI, somar, subtrair } from "./matematica.js";

// Com renomeação
import { somar as adicionar } from "./matematica.js";

// Importação padrão — pode usar qualquer nome
import Calculadora from "./matematica.js";

// Importar tudo como namespace
import * as Matematica from "./matematica.js";
Matematica.somar(2, 3); // 5
Matematica.PI;           // 3.14159

// Importação dinâmica — carrega o módulo sob demanda (lazy loading)
const { somar } = await import("./matematica.js");
```

---

### 7.2 CommonJS — o sistema legado do Node.js

Antes dos ES Modules, o Node.js usava `require`/`module.exports`. Você ainda encontrará muito código nesse formato:

```javascript
// CommonJS — exports
const PI = 3.14159;
function somar(a, b) { return a + b; }

module.exports = { PI, somar };     // exportar objeto
module.exports = somar;             // exportar apenas a função

// CommonJS — imports
const { PI, somar } = require("./matematica");
const fs = require("fs");           // módulo nativo do Node.js
```

---

## Parte 8 — Estruturas de Dados Modernas

---

### 8.1 Map — chave-valor com qualquer tipo de chave

`Map` é uma coleção de pares chave-valor onde a chave pode ser **qualquer tipo** — incluindo objetos e funções. Diferente de objetos, a Map mantém a ordem de inserção.

```javascript
const mapa = new Map();

// set — adiciona um par
mapa.set("nome", "Ana");
mapa.set(1, "número como chave");
mapa.set({ id: 1 }, "objeto como chave");

// get — recupera pelo chave
mapa.get("nome");     // "Ana"

// has — verifica existência
mapa.has("nome");     // true

// delete — remove
mapa.delete("nome");

// size — tamanho
mapa.size;            // número de entradas

// Iteração
for (const [chave, valor] of mapa) {
  console.log(`${chave}: ${valor}`);
}

// Criação a partir de array de pares
const configuracoes = new Map([
  ["tema", "escuro"],
  ["idioma", "pt-BR"],
  ["fontSize", 16],
]);
```

---

### 8.2 Set — coleção de valores únicos

`Set` armazena apenas valores únicos. Qualquer tentativa de inserir um valor duplicado é silenciosamente ignorada.

```javascript
const conjunto = new Set([1, 2, 3, 2, 1, 4]);
console.log(conjunto); // Set(4) { 1, 2, 3, 4 } — duplicatas removidas

conjunto.add(5);
conjunto.has(3);     // true
conjunto.delete(3);
conjunto.size;       // 4

// Converter para array
const array = [...conjunto]; // ou Array.from(conjunto)

// Remover duplicatas de um array — uso mais comum de Set
const comDuplicatas = [1, 2, 2, 3, 3, 3, 4];
const semDuplicatas = [...new Set(comDuplicatas)]; // [1, 2, 3, 4]

// Operações de conjuntos
const A = new Set([1, 2, 3, 4]);
const B = new Set([3, 4, 5, 6]);

const uniao = new Set([...A, ...B]);          // {1,2,3,4,5,6}
const intersecao = new Set([...A].filter(x => B.has(x))); // {3,4}
const diferenca = new Set([...A].filter(x => !B.has(x))); // {1,2}
```

---

### 8.3 WeakMap e WeakRef — referências fracas

```javascript
// WeakMap — chaves devem ser objetos; referências fracas (o GC pode coletar)
// Útil para associar dados a objetos sem impedir que sejam coletados
const cache = new WeakMap();

function processar(objeto) {
  if (cache.has(objeto)) return cache.get(objeto);
  const resultado = /* processamento custoso */ { ...objeto, processado: true };
  cache.set(objeto, resultado);
  return resultado;
}
```

---

## Parte 9 — Manipulação de Strings

---

### 9.1 Template Literals e métodos de String

```javascript
const nome = "Ana";
const saudacao = `Olá, ${nome}!`;          // "Olá, Ana!"
const multilinhas = `
  Primeira linha
  Segunda linha
  ${1 + 1} + 1 = ${1 + 1 + 1}
`;

// Métodos úteis de String
const texto = "  Olá, Mundo!  ";

texto.trim();                    // "Olá, Mundo!" — remove espaços das bordas
texto.trimStart();               // "Olá, Mundo!  "
texto.trimEnd();                 // "  Olá, Mundo!"
texto.toLowerCase();             // "  olá, mundo!  "
texto.toUpperCase();             // "  OLÁ, MUNDO!  "
texto.includes("Mundo");         // true
texto.startsWith("  Olá");       // true
texto.endsWith("!  ");           // true
texto.indexOf("Mundo");          // 6 (posição da primeira ocorrência)
texto.replace("Mundo", "JS");    // "  Olá, JS!  "
texto.replaceAll("o", "0");      // substitui todas as ocorrências
texto.split(", ");               // ["  Olá", "Mundo!  "]
texto.slice(2, 7);               // "Olá, " (início inclusivo, fim exclusivo)
texto.padStart(20, "*");         // "******  Olá, Mundo!  " (completa com * à esquerda)
texto.padEnd(20, "-");           // "  Olá, Mundo!  -----"
texto.repeat(3);                 // repete a string 3 vezes

// String.raw — ignora sequências de escape
String.raw`Linha1\nLinha2`;  // "Linha1\nLinha2" — \n não é interpretado
```

---

### 9.2 Expressões Regulares

```javascript
// Literal de regex
const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

// Construtor (útil quando o padrão é dinâmico)
const termo = "JavaScript";
const dinamica = new RegExp(termo, "i"); // flag 'i' = case-insensitive

// Flags
// i = case-insensitive
// g = global (encontra todas as ocorrências)
// m = multiline (^ e $ consideram quebras de linha)

const texto = "Eu amo JavaScript e JavaScript é incrível";

// test — retorna boolean
emailRegex.test("ana@email.com");  // true

// match — retorna array com correspondências
texto.match(/JavaScript/g);       // ["JavaScript", "JavaScript"]

// replace com regex
texto.replace(/JavaScript/g, "JS"); // "Eu amo JS e JS é incrível"

// Grupos de captura
const data = "2024-03-15";
const match = data.match(/(\d{4})-(\d{2})-(\d{2})/);
// match[0] = "2024-03-15" (correspondência completa)
// match[1] = "2024" (grupo 1)
// match[2] = "03" (grupo 2)
// match[3] = "15" (grupo 3)

// Grupos nomeados
const match2 = data.match(/(?<ano>\d{4})-(?<mes>\d{2})-(?<dia>\d{2})/);
match2.groups.ano; // "2024"
match2.groups.mes; // "03"
```

---

## Parte 10 — Tratamento de Erros

---

### 10.1 try/catch/finally

```javascript
try {
  // Código que pode lançar erro
  const resultado = JSON.parse("texto inválido"); // lança SyntaxError
} catch (erro) {
  // 'erro' é um objeto Error com:
  // erro.name    — tipo do erro ("SyntaxError", "TypeError", etc.)
  // erro.message — descrição
  // erro.stack   — stack trace completa
  console.error(`${erro.name}: ${erro.message}`);
} finally {
  // Executado sempre — com ou sem erro
  // Útil para liberar recursos (fechar conexões, etc.)
  console.log("Bloco finally sempre executa");
}

// Criando erros customizados
class ErroDeValidacao extends Error {
  constructor(campo, mensagem) {
    super(mensagem);              // passa a mensagem para Error
    this.name = "ErroDeValidacao";
    this.campo = campo;
  }
}

function validarEmail(email) {
  if (!email.includes("@")) {
    throw new ErroDeValidacao("email", `E-mail inválido: ${email}`);
  }
  return true;
}

try {
  validarEmail("emailinvalido");
} catch (erro) {
  if (erro instanceof ErroDeValidacao) {
    console.log(`Campo com erro: ${erro.campo}`);
    console.log(`Mensagem: ${erro.message}`);
  } else {
    throw erro; // re-lança erros inesperados
  }
}
```

---

### 10.2 Tipos de erro nativos

| Tipo | Quando ocorre |
|---|---|
| `Error` | Erro genérico — base para todos os outros |
| `TypeError` | Operação em tipo errado: `null.propriedade` |
| `ReferenceError` | Variável não declarada: `console.log(x)` sem declarar `x` |
| `SyntaxError` | JSON inválido, código malformado |
| `RangeError` | Valor fora do intervalo permitido: `new Array(-1)` |
| `URIError` | `decodeURIComponent('%')` — URI malformado |

---

## Parte 11 — Padrões Modernos e Técnicas Avançadas

---

### 11.1 Imutabilidade — trabalhar sem mutar dados

```javascript
const estado = {
  usuario: { nome: "Ana", nivel: 3 },
  tarefas: [
    { id: 1, titulo: "Tarefa A", concluida: false },
    { id: 2, titulo: "Tarefa B", concluida: true },
  ],
};

// ❌ Mutação direta — causa problemas em React e Redux
estado.usuario.nivel = 4;

// ✅ Cópia imutável com spread
const novoEstado = {
  ...estado,
  usuario: {
    ...estado.usuario,
    nivel: 4,             // apenas este campo muda
  },
};

// Atualizar um item em um array imutavelmente
const tarefasAtualizadas = estado.tarefas.map(tarefa =>
  tarefa.id === 1
    ? { ...tarefa, concluida: true }  // cópia com mudança
    : tarefa                           // sem mudança
);

// Adicionar item imutavelmente
const novasTarefas = [
  ...estado.tarefas,
  { id: 3, titulo: "Nova Tarefa", concluida: false },
];

// Remover item imutavelmente
const semTarefa1 = estado.tarefas.filter(t => t.id !== 1);
```

---

### 11.2 Composition over inheritance — funções sobre classes

```javascript
// Com classes e herança — acoplamento forte
class Animal {
  respirar() { console.log("Respirando..."); }
}

class Peixe extends Animal {
  nadar() { console.log("Nadando..."); }
}

class Tartaruga extends Animal {
  nadar() { console.log("Nadando devagar..."); }
  andar() { console.log("Andando..."); }
}

// Com composição de funções — mais flexível
const podePular = (estado) => ({
  pular: () => console.log(`${estado.nome} pula!`),
});

const podeNadar = (estado) => ({
  nadar: () => console.log(`${estado.nome} nada!`),
});

const podeVoar = (estado) => ({
  voar: () => console.log(`${estado.nome} voa!`),
});

// Compor capacidades livremente
const criarPato = (nome) => {
  const estado = { nome };
  return Object.assign({}, podeNadar(estado), podeVoar(estado), podePular(estado));
};

const criarPeixe = (nome) => {
  const estado = { nome };
  return Object.assign({}, podeNadar(estado));
};

const donald = criarPato("Donald");
donald.nadar(); // "Donald nada!"
donald.voar();  // "Donald voa!"
```

---

### 11.3 Currying e Partial Application

```javascript
// Currying — transforma f(a, b, c) em f(a)(b)(c)
const curry = fn => {
  return function curried(...args) {
    if (args.length >= fn.length) {
      return fn(...args);
    }
    return (...maisArgs) => curried(...args, ...maisArgs);
  };
};

const somar = curry((a, b, c) => a + b + c);
somar(1)(2)(3);   // 6
somar(1, 2)(3);   // 6
somar(1)(2, 3);   // 6

// Partial application — pré-preencher alguns argumentos
const multiplicar = (fator, numero) => fator * numero;
const dobrar = multiplicar.bind(null, 2);
const triplicar = multiplicar.bind(null, 3);

dobrar(5);     // 10
triplicar(5);  // 15
```

---

### 11.4 Generators e Iterators

```javascript
// Generator — função que pode pausar sua execução
function* contador(inicio = 0, fim = Infinity) {
  let i = inicio;
  while (i <= fim) {
    yield i++;  // pausa aqui e retorna o valor
  }
}

const gen = contador(1, 5);
gen.next(); // { value: 1, done: false }
gen.next(); // { value: 2, done: false }
gen.next(); // { value: 3, done: false }

// Iterável com for...of
for (const n of contador(1, 5)) {
  console.log(n); // 1, 2, 3, 4, 5
}

// Sequência infinita com lazy evaluation
function* fibonacci() {
  let [a, b] = [0, 1];
  while (true) {
    yield a;
    [a, b] = [b, a + b];
  }
}

const fib = fibonacci();
Array.from({ length: 10 }, () => fib.next().value);
// [0, 1, 1, 2, 3, 5, 8, 13, 21, 34]
```

---

### 11.5 Proxy e Reflect — meta-programação

```javascript
// Proxy — intercepta operações em objetos
const handler = {
  get(alvo, propriedade) {
    console.log(`Acessando: ${propriedade}`);
    return propriedade in alvo ? alvo[propriedade] : `Propriedade '${propriedade}' não existe`;
  },
  set(alvo, propriedade, valor) {
    if (typeof valor !== "number") throw new TypeError("Apenas números");
    alvo[propriedade] = valor;
    return true; // indica sucesso
  },
};

const objeto = new Proxy({}, handler);
objeto.x = 5;            // OK
objeto.y = "texto";      // TypeError: Apenas números
console.log(objeto.x);   // "Acessando: x" → 5
console.log(objeto.z);   // "Acessando: z" → "Propriedade 'z' não existe"
```

---

## Parte 12 — JavaScript no Ambiente Web (DOM)

---

### 12.1 O DOM — Document Object Model

O DOM é a representação em objeto do HTML da página. O JavaScript manipula o DOM para alterar a página sem recarregá-la.

```javascript
// Seleção de elementos
const elemento = document.getElementById("meu-id");
const primeiro = document.querySelector(".minha-classe");      // primeiro match
const todos = document.querySelectorAll("p.destaque");         // todos os matches (NodeList)

// Modificação de conteúdo
elemento.textContent = "Novo texto";          // apenas texto — sem interpretar HTML
elemento.innerHTML = "<strong>Negrito</strong>"; // interpreta HTML (cuidado: XSS)

// Modificação de estilos
elemento.style.backgroundColor = "red";
elemento.style.fontSize = "16px";

// Classes CSS
elemento.classList.add("ativo");
elemento.classList.remove("inativo");
elemento.classList.toggle("destaque");        // adiciona se não tem, remove se tem
elemento.classList.contains("ativo");         // true/false

// Atributos
elemento.setAttribute("data-id", "42");
elemento.getAttribute("data-id");             // "42"
elemento.removeAttribute("data-id");

// Criar e inserir elementos
const novoDiv = document.createElement("div");
novoDiv.textContent = "Elemento criado dinamicamente";
novoDiv.classList.add("card");

document.body.appendChild(novoDiv);           // inserir no final do body
elemento.insertBefore(novoDiv, elemento.firstChild); // antes do primeiro filho
elemento.after(novoDiv);                      // imediatamente após o elemento
```

---

### 12.2 Eventos

```javascript
const botao = document.querySelector("#meu-botao");

// Adicionar event listener
botao.addEventListener("click", function(evento) {
  console.log("Clicado!");
  console.log(evento.target);        // o elemento que disparou o evento
  console.log(evento.currentTarget); // o elemento ao qual o listener está anexado
});

// Arrow function como listener
botao.addEventListener("click", (e) => {
  e.preventDefault(); // impede comportamento padrão (ex: submit de form, seguir link)
  e.stopPropagation(); // impede o evento de "subir" para elementos pai
});

// Remover listener (a função precisa ser a mesma referência)
function aoClicar(e) { console.log("Clicado"); }
botao.addEventListener("click", aoClicar);
botao.removeEventListener("click", aoClicar);

// Event delegation — um listener no pai captura eventos dos filhos
// Muito mais eficiente do que listeners individuais
document.querySelector("#lista").addEventListener("click", (e) => {
  if (e.target.matches("li")) {
    console.log("Item clicado:", e.target.textContent);
  }
});

// Eventos comuns
"click"        // clique do mouse
"dblclick"     // clique duplo
"mouseover"    // mouse entrou no elemento
"mouseout"     // mouse saiu do elemento
"keydown"      // tecla pressionada
"keyup"        // tecla solta
"input"        // valor de input mudou
"change"       // campo perdeu foco após mudança
"submit"       // formulário submetido
"load"         // página/recurso carregou
"DOMContentLoaded" // HTML parseado (antes de imagens)
"resize"       // janela redimensionada
"scroll"       // página rolada
```

---

## Parte 13 — Node.js — JavaScript no Servidor

---

### 13.1 O que o Node.js adiciona ao JavaScript

Node.js é um runtime JavaScript construído sobre o motor V8 do Chrome. Ele adiciona APIs que não existem no navegador:

```javascript
// APIs exclusivas do Node.js
const fs = require("fs/promises");     // sistema de arquivos
const path = require("path");          // manipulação de caminhos
const http = require("http");          // servidor HTTP
const os = require("os");              // informações do sistema operacional
const crypto = require("crypto");      // criptografia
const process = require("process");   // informações do processo atual

// Exemplos de uso
const conteudo = await fs.readFile("arquivo.txt", "utf8");
await fs.writeFile("saida.txt", "conteúdo aqui");
const arquivos = await fs.readdir("./pasta");

const caminho = path.join(__dirname, "assets", "imagem.png");
path.extname("arquivo.js");  // ".js"
path.basename("/foo/bar.js"); // "bar.js"
```

---

### 13.2 Servidor HTTP com Node.js puro

```javascript
const http = require("http");

const servidor = http.createServer((requisicao, resposta) => {
  const url = requisicao.url;
  const metodo = requisicao.method;

  if (url === "/" && metodo === "GET") {
    resposta.writeHead(200, { "Content-Type": "application/json" });
    resposta.end(JSON.stringify({ mensagem: "Olá, mundo!" }));
  } else if (url === "/usuarios" && metodo === "GET") {
    resposta.writeHead(200, { "Content-Type": "application/json" });
    resposta.end(JSON.stringify([{ id: 1, nome: "Ana" }]));
  } else {
    resposta.writeHead(404);
    resposta.end(JSON.stringify({ erro: "Rota não encontrada" }));
  }
});

servidor.listen(3000, () => {
  console.log("Servidor rodando em http://localhost:3000");
});
```

---

### 13.3 npm e gerenciamento de pacotes

```bash
# Inicializar um projeto
npm init -y

# Instalar dependência (vai para "dependencies" no package.json)
npm install express

# Instalar dependência de desenvolvimento (só para dev/build)
npm install --save-dev nodemon

# Instalar globalmente
npm install -g typescript

# Remover dependência
npm uninstall express

# Listar pacotes instalados
npm list

# Rodar scripts definidos no package.json
npm run build
npm run test
npm start
```

**Estrutura do `package.json`:**

```json
{
  "name": "meu-projeto",
  "version": "1.0.0",
  "type": "module",
  "scripts": {
    "start": "node src/index.js",
    "dev": "nodemon src/index.js",
    "build": "tsc",
    "test": "jest"
  },
  "dependencies": {
    "express": "^4.18.2"
  },
  "devDependencies": {
    "nodemon": "^3.0.1",
    "typescript": "^5.3.0"
  }
}
```

---

## Parte 14 — Ferramentas do Ecossistema JavaScript

---

### 14.1 Onde encontrar pacotes e componentes prontos

| Recurso | URL | O que oferece |
|---|---|---|
| **npm Registry** | `npmjs.com` | Repositório central com 2M+ pacotes |
| **JSDelivr** | `jsdelivr.com` | CDN para pacotes npm sem instalação |
| **Bundlephobia** | `bundlephobia.com` | Verifica o tamanho de qualquer pacote npm |
| **Snyk Advisor** | `snyk.io/advisor` | Score de qualidade/segurança de pacotes |
| **OpenBase** | `openbase.com` | Comparativo e avaliações de libs |
| **State of JS** | `stateofjs.com` | Survey anual de popularidade das ferramentas |
| **GitHub Trending** | `github.com/trending/javascript` | Projetos JS em alta |

---

### 14.2 Bundlers e Ferramentas de Build

| Ferramenta | Uso | Característica |
|---|---|---|
| **Vite** | Bundler moderno | Extremamente rápido; padrão para React/Vue/Svelte |
| **Webpack** | Bundler tradicional | Configurável; ainda muito usado em projetos legados |
| **esbuild** | Bundler/transpiler | Mais rápido que webpack; base do Vite |
| **Rollup** | Bundler para bibliotecas | Tree-shaking excelente; ideal para publicar libs |
| **Parcel** | Bundler zero-config | Sem configuração; bom para projetos pequenos |
| **Turbopack** | Bundler novo | Rust-based; integrado ao Next.js 13+ |
| **Bun** | Runtime + bundler + pkg manager | Substituto all-in-one do Node.js + npm |

---

### 14.3 Transpilers e Type Checkers

| Ferramenta | O que faz |
|---|---|
| **TypeScript (tsc)** | Compila TypeScript para JavaScript, verifica tipos |
| **Babel** | Transpila JS moderno para versões mais antigas (legacy) |
| **SWC** | Substituto do Babel escrito em Rust — mais rápido |
| **Biome** | Formatter + linter em um só (substituto do ESLint + Prettier) |

---

### 14.4 Linters e Formatters

```bash
# ESLint — análise estática de código (encontra bugs e bad practices)
npm install --save-dev eslint
npx eslint --init

# Prettier — formata o código automaticamente
npm install --save-dev prettier

# Biome — faz os dois de uma vez, muito mais rápido
npm install --save-dev @biomejs/biome
npx biome init
```

---

### 14.5 Frameworks e Bibliotecas mais usados

**Frontend Web:**

| Biblioteca/Framework | Uso | Característica |
|---|---|---|
| **React** | UI declarativa | Mais popular; componentes; enorme ecossistema |
| **Vue.js** | UI declarativa | Curva suave; popular na Europa/Ásia |
| **Svelte** | UI compilada | Sem virtual DOM; compila para JS puro |
| **Solid.js** | UI reativa | Sem virtual DOM; reatividade granular |
| **Angular** | Framework completo | TypeScript-first; MVC; usado em empresas grandes |
| **Astro** | Sites estáticos + islands | Performance máxima; qualquer framework de UI |

**Backend (Node.js):**

| Biblioteca/Framework | Uso |
|---|---|
| **Express.js** | Framework HTTP minimalista — mais usado historicamente |
| **Fastify** | Express moderno — mais rápido e TypeScript-first |
| **NestJS** | Framework enterprise com injeção de dependências |
| **Hono** | Ultra-leve, funciona em Node/Bun/Deno/Edge |
| **Elysia** | Para Bun — API ergonômica e muito rápida |

**Utilitários:**

| Biblioteca | Uso |
|---|---|
| **Zod** | Validação e parsing de schemas com inferência TypeScript |
| **date-fns** | Manipulação de datas (substituto moderno do Moment.js) |
| **Lodash** | Utilitários funcionais para arrays/objetos |
| **Axios** | Cliente HTTP com interceptors e cancelamento |
| **uuid** | Geração de UUIDs |
| **dotenv** | Variáveis de ambiente de arquivo `.env` |

**Testes:**

| Ferramenta | Uso |
|---|---|
| **Vitest** | Testes unitários — moderno, compatível com Jest, muito rápido |
| **Jest** | Testes unitários — mais antigo, amplo suporte |
| **Playwright** | Testes E2E — controla Chrome/Firefox/Safari |
| **Cypress** | Testes E2E — mais visual, bom DX |

---

### 14.6 Gerenciadores de pacotes

| Ferramenta | Comando de install | Diferencial |
|---|---|---|
| **npm** | `npm install` | Nativo do Node.js |
| **pnpm** | `pnpm install` | Economiza disco com hard links; mais rápido |
| **yarn** | `yarn` | Workspaces maduros; determinístico |
| **bun** | `bun install` | Runtime + pkg manager; mais rápido de todos |

---

## Parte 15 — Padrões de Projeto em JavaScript

---

### 15.1 Module Pattern

```javascript
// Encapsula estado e expõe API pública
const ContaBancaria = (() => {
  let saldo = 0; // privado — não acessível de fora

  return {
    depositar(valor) {
      if (valor <= 0) throw new Error("Valor inválido");
      saldo += valor;
    },
    sacar(valor) {
      if (valor > saldo) throw new Error("Saldo insuficiente");
      saldo -= valor;
    },
    get saldoAtual() { return saldo; },
  };
})(); // IIFE — executa imediatamente

ContaBancaria.depositar(100);
ContaBancaria.sacar(30);
console.log(ContaBancaria.saldoAtual); // 70
```

---

### 15.2 Observer Pattern

```javascript
class EventEmitter {
  #listeners = {};

  on(evento, callback) {
    this.#listeners[evento] ??= [];
    this.#listeners[evento].push(callback);
    return () => this.off(evento, callback); // retorna função para remover
  }

  off(evento, callback) {
    this.#listeners[evento] = this.#listeners[evento]?.filter(l => l !== callback);
  }

  emit(evento, ...args) {
    this.#listeners[evento]?.forEach(cb => cb(...args));
  }
}

const emitter = new EventEmitter();
const remover = emitter.on("mensagem", (texto) => console.log(`Recebido: ${texto}`));
emitter.emit("mensagem", "Olá!"); // "Recebido: Olá!"
remover(); // cancela o listener
```

---

### 15.3 Pipeline / Função Pipe

```javascript
// Compõe funções da esquerda para a direita
const pipe = (...fns) => (valor) => fns.reduce((acc, fn) => fn(acc), valor);

const processarTexto = pipe(
  texto => texto.trim(),
  texto => texto.toLowerCase(),
  texto => texto.replace(/\s+/g, "-"),
  texto => `slug-${texto}`,
);

processarTexto("  Olá Mundo  "); // "slug-olá-mundo"
```

---

## Parte 16 — JavaScript Moderno — Funcionalidades Recentes

---

### 16.1 Top-level await (ES2022)

```javascript
// Em módulos ES, await pode ser usado fora de funções async
// (não funciona em CommonJS)
const dados = await fetch("https://api.exemplo.com/dados").then(r => r.json());
console.log(dados);
```

---

### 16.2 Object.groupBy (ES2024)

```javascript
const produtos = [
  { nome: "Teclado", categoria: "Periférico" },
  { nome: "Mouse", categoria: "Periférico" },
  { nome: "Monitor", categoria: "Display" },
];

const porCategoria = Object.groupBy(produtos, p => p.categoria);
// {
//   Periférico: [{ nome: "Teclado", ... }, { nome: "Mouse", ... }],
//   Display: [{ nome: "Monitor", ... }],
// }
```

---

### 16.3 Estruturação moderna de projeto JavaScript/TypeScript

```
meu-projeto/
├── src/
│   ├── index.ts          → ponto de entrada
│   ├── types/            → interfaces e tipos TypeScript
│   ├── services/         → lógica de negócio
│   ├── utils/            → funções utilitárias puras
│   └── __tests__/        → testes
├── dist/                 → código compilado (gerado pelo tsc)
├── .env                  → variáveis de ambiente (nunca versionar)
├── .env.example          → exemplo sem valores reais (versionar)
├── .eslintrc.json        → configuração do ESLint
├── .prettierrc           → configuração do Prettier
├── tsconfig.json         → configuração do TypeScript
├── package.json
└── README.md
```

---

## Resumo Geral — JavaScript Moderno

| Conceito | Resumo |
|---|---|
| `const` / `let` | Prefira `const`; use `let` para reatribuição; nunca `var` |
| Tipos primitivos | `string`, `number`, `boolean`, `undefined`, `null`, `symbol`, `bigint` |
| `===` | Sempre use igualdade estrita — evite `==` |
| Arrow function | Herda o `this` do escopo externo; sintaxe concisa |
| Destructuring | Extrai valores de objetos e arrays de forma concisa |
| Spread / Rest | `...` expande ou captura múltiplos valores |
| Template literals | `` `Olá ${nome}` `` — interpolação e multilinhas |
| Optional chaining | `a?.b?.c` — acesso seguro sem NPE |
| Nullish coalescing | `a ?? "padrão"` — substitui apenas `null`/`undefined` |
| Promise | Representação de valor futuro assíncrono |
| `async/await` | Código assíncrono com aparência síncrona |
| `fetch` | API nativa para requisições HTTP |
| ES Modules | `import` / `export` — sistema de módulos padrão |
| `Map` / `Set` | Estruturas de dados com chaves tipadas e valores únicos |
| Closure | Função que lembra o escopo onde foi criada |
| Imutabilidade | Usar spread e métodos sem mutação em vez de mutar dados |
| Classes | Açúcar sintático sobre protótipos — `class`, `extends`, `super` |
| Generator | Função que pode pausar execução com `yield` |
