# 🚀 React Native — Guia Técnico Completo

> **Nível:** Zero (sem experiência prévia)
> **Linguagem:** JavaScript / TypeScript
> **Fonte de referência:** [reactnative.dev](https://reactnative.dev/docs/getting-started)
> **Versão de referência:** React Native 0.74+

---

## Parte 1 — Introdução e Contextualização

---

### 1.1 O que é React Native?

React Native é um **framework de código aberto criado pelo Facebook (Meta) em 2015** para construir aplicativos móveis nativos usando JavaScript e React. Diferentemente de soluções que apenas embrulham um WebView, o React Native **renderiza componentes nativos reais** da plataforma — os mesmos que aparecem em apps escritos em Swift (iOS) ou Kotlin (Android).

O slogan histórico do framework é: **"Learn once, write anywhere"** — aprenda uma vez, escreva para qualquer plataforma. Isso significa que você escreve um único código JavaScript e ele gera interfaces nativas para iOS e Android simultaneamente.

Em 2022, o React Native passou por uma reestruturação interna significativa chamada **Nova Arquitetura** (New Architecture), que trouxe o motor JavaScript **Hermes**, o sistema de renderização **Fabric** e o sistema de módulos nativos **TurboModules**. Essa arquitetura está disponível por padrão a partir da versão 0.74.

---

### 1.2 Como o React Native funciona internamente

Para entender o React Native, é necessário entender o que diferencia ele de uma solução WebView.

#### Abordagem WebView (Ionic, Capacitor)

```
JavaScript → WebView → renderiza HTML/CSS → visual "nativo" simulado
```

Apps WebView são basicamente sites dentro de um container. O botão que o usuário vê é um `<button>` HTML estilizado com CSS — não é o botão nativo do iOS ou Android. O resultado visual pode ser similar, mas o comportamento, a performance e a acessibilidade diferem do nativo.

#### Abordagem React Native

```
JavaScript → Bridge/JSI → componentes nativos reais (UIButton, android.widget.Button)
```

Quando você escreve `<Button />` no React Native, o framework instrui o sistema operacional a criar um botão nativo real — o mesmo componente que um desenvolvedor Swift ou Kotlin criaria. O JavaScript descreve **o que** mostrar; o sistema operacional decide **como** renderizar.

**Comparativo de abordagens:**

| Abordagem | Exemplos | Como renderiza | Performance | Acesso a APIs nativas |
|---|---|---|---|---|
| WebView | Ionic, Capacitor | HTML/CSS em WebView | Limitada | Via bridge JS |
| React Native | React Native | Componentes nativos reais | Alta | Via bridge/JSI |
| Compilado nativo | Flutter | Renderização própria (Skia/Impeller) | Muito alta | Direta |
| Nativo puro | Swift/Kotlin | Componentes do SO | Máxima | Direta |

---

### 1.3 O problema que o React Native resolve

Antes do React Native, um app para iOS e Android exigia **duas equipes separadas** trabalhando em duas bases de código completamente distintas — Swift/Objective-C para iOS e Java/Kotlin para Android. Toda funcionalidade precisava ser implementada duas vezes, com bugs diferentes, ciclos de lançamento diferentes e custos dobrados.

**Comparativo do problema:**

| Problema (desenvolvimento nativo duplo) | Como o React Native resolve |
|---|---|
| Dois times, duas linguagens | Uma equipe, uma linguagem (JavaScript/TypeScript) |
| Código duplicado para iOS e Android | Até 90% de código compartilhado |
| Conhecimento dividido em silos | Desenvolvedores web podem criar apps móveis |
| Ciclos de desenvolvimento lentos | Hot Reload: vê mudanças em tempo real sem recompilar |
| Custo elevado de manutenção | Uma base de código para manter |

---

### 1.4 React Native vs Flutter — comparativo honesto

Estas são as duas opções dominantes de desenvolvimento cross-platform em 2024. Não existe resposta universalmente correta — a escolha depende do contexto.

| Critério | React Native | Flutter |
|---|---|---|
| **Linguagem** | JavaScript / TypeScript | Dart |
| **Criado por** | Meta (Facebook) | Google |
| **Renderização** | Componentes nativos do SO | Engine própria (Impeller) |
| **Visual** | Segue as convenções de cada plataforma | Consistente em todas as plataformas |
| **Curva de aprendizado** | Baixa para devs web | Média — Dart é nova linguagem |
| **Ecossistema** | Enorme — npm com milhões de pacotes | Crescente — pub.dev |
| **Mercado de trabalho** | Muito alto | Alto e crescendo |
| **Performance** | Muito boa | Excelente |
| **Hot Reload** | Sim | Sim |
| **Tamanho do app** | Menor | Maior (inclui engine Flutter) |
| **Quando escolher** | Time com background web, prazo curto | Consistência visual total, performance máxima |

---

### 1.5 O paradigma declarativo no React Native

React Native herda o paradigma declarativo do React. Assim como no Jetpack Compose, a interface é sempre uma função do estado atual dos dados.

```
UI = f(estado)
```

**Paradigma imperativo (o que não existe no React Native moderno):**

```javascript
// Você NÃO faz isso no React Native moderno
// Não existe "encontre esse elemento e mude sua cor"
const botao = document.getElementById('meuBotao');
botao.style.backgroundColor = 'red';
botao.textContent = 'Erro!';
```

**Paradigma declarativo (a abordagem correta):**

```javascript
// Você descreve COMO a UI deve parecer para cada estado
function MeuBotao({ temErro }) {
  return (
    <TouchableOpacity style={{ backgroundColor: temErro ? 'red' : 'blue' }}>
      <Text>{temErro ? 'Erro!' : 'Clique aqui'}</Text>
    </TouchableOpacity>
  );
}
// Quando temErro muda, o React recalcula e atualiza a UI automaticamente
```

**A diferença fundamental:**

| | Imperativo | Declarativo |
|---|---|---|
| Quando a tela muda | Você manipula elementos manualmente | O React reexecuta o componente automaticamente |
| Quem controla a UI | O desenvolvedor, passo a passo | O estado dos dados |
| Risco de bug | UI e dados ficam dessincronizados | Impossível: UI é sempre derivada do estado |

---

### 1.6 Resumo da Parte 1

| Conceito | Definição resumida |
|---|---|
| React Native | Framework da Meta para criar apps móveis nativos com JavaScript/React |
| Componentes nativos | React Native renderiza UIButton/android.widget.Button — não HTML |
| "Learn once, write anywhere" | Um código JavaScript para iOS e Android |
| Nova Arquitetura | Hermes + Fabric + TurboModules — padrão desde RN 0.74 |
| Paradigma declarativo | UI = f(estado) — você descreve o resultado, não os passos |

---

## Parte 2 — Configuração do Ambiente

---

### 2.1 As duas formas de começar

Existem dois pontos de entrada no ecossistema React Native:

| Abordagem | Ferramenta | Quando usar |
|---|---|---|
| **Expo** | `create-expo-app` | Início rápido, prototipagem, apps sem código nativo customizado |
| **React Native CLI** | `@react-native-community/cli` | Controle total, código nativo customizado, produção |

#### Expo — o caminho recomendado para iniciantes

O **Expo** é uma plataforma construída sobre o React Native que abstrai toda a complexidade de configuração do ambiente nativo. Com o Expo, você não precisa instalar Xcode ou Android Studio para começar — basta um celular com o app **Expo Go** instalado.

```bash
# Criar um novo projeto com Expo
npx create-expo-app@latest MeuApp
cd MeuApp

# Iniciar o servidor de desenvolvimento
npx expo start
```

Após rodar `npx expo start`, um QR code aparece no terminal. Escaneie com o app **Expo Go** no seu celular e o app aparece instantaneamente.

**Limitações do Expo Go:** o Expo Go só suporta as APIs incluídas no SDK do Expo. Se você precisar de um módulo nativo que não está no SDK (como um SDK de pagamento específico, Bluetooth personalizado, etc.), precisará usar um **Expo Development Build** ou migrar para React Native CLI.

#### React Native CLI — controle total

```bash
# Pré-requisitos: Node.js, JDK, Android Studio, Xcode (macOS)

# Criar projeto com React Native CLI
npx @react-native-community/cli@latest init MeuApp
cd MeuApp

# Rodar no Android (emulador ou dispositivo físico)
npx react-native run-android

# Rodar no iOS (apenas macOS, requer Xcode)
npx react-native run-ios
```

---

### 2.2 Estrutura de um projeto React Native

```
MeuApp/
├── android/          → código nativo Android (Kotlin/Java)
├── ios/              → código nativo iOS (Swift/Objective-C)
├── src/              → seu código JavaScript/TypeScript (convenção)
│   ├── components/   → componentes reutilizáveis
│   ├── screens/      → telas do app
│   ├── navigation/   → configuração de navegação
│   └── services/     → chamadas de API, utilitários
├── App.tsx           → componente raiz do app
├── index.js          → ponto de entrada (registra o app)
├── package.json      → dependências e scripts
└── tsconfig.json     → configuração do TypeScript
```

---

### 2.3 O arquivo App.tsx — ponto de entrada

```tsx
// App.tsx — o componente raiz que o sistema carrega primeiro
import React from 'react';
import { View, Text, StyleSheet } from 'react-native';

export default function App() {
  return (
    <View style={styles.container}>
      <Text style={styles.titulo}>Olá, React Native!</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#fff',
  },
  titulo: {
    fontSize: 24,
    fontWeight: 'bold',
  },
});
```

---

## Parte 3 — Componentes Fundamentais

---

### 3.1 O que são componentes no React Native?

No React Native, **componentes são os blocos de construção da interface**. Cada componente é uma função JavaScript que descreve uma parte da UI. Diferentemente do React para web, aqui não existem elementos HTML — os componentes mapeiam diretamente para elementos nativos.

| React (Web) | React Native | Elemento nativo iOS | Elemento nativo Android |
|---|---|---|---|
| `<div>` | `<View>` | `UIView` | `android.view.View` |
| `<p>`, `<span>`, `<h1>` | `<Text>` | `UILabel` | `android.widget.TextView` |
| `<img>` | `<Image>` | `UIImageView` | `android.widget.ImageView` |
| `<input>` | `<TextInput>` | `UITextField` | `android.widget.EditText` |
| `<button>` | `<TouchableOpacity>` / `<Pressable>` | `UIButton` | `android.widget.Button` |
| `<ul>` + `<li>` | `<FlatList>` | `UITableView` | `RecyclerView` |
| `<select>` | — | `UIPickerView` | `Spinner` |

---

### 3.2 View — o contêiner fundamental

`View` é o componente mais básico do React Native. Equivale à `<div>` do HTML e ao `Column`/`Box` do Compose. Ele é um contêiner invisível que organiza outros componentes.

```tsx
import { View, Text, StyleSheet } from 'react-native';

function ExemploView() {
  return (
    // View externa — contêiner principal
    <View style={styles.container}>

      {/* View interna — agrupa os textos */}
      <View style={styles.caixa}>
        <Text>Primeiro</Text>
        <Text>Segundo</Text>
      </View>

    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,          // ocupa todo o espaço disponível
    padding: 16,
    backgroundColor: '#f5f5f5',
  },
  caixa: {
    backgroundColor: 'white',
    borderRadius: 8,
    padding: 12,
  },
});
```

---

### 3.3 Text — exibindo texto

`Text` é o único componente que pode conter texto no React Native. Diferentemente do HTML, onde texto pode aparecer diretamente em qualquer elemento, no React Native **todo texto deve estar dentro de um componente `<Text>`**.

```tsx
import { Text, View } from 'react-native';

function ExemploText() {
  return (
    <View>
      {/* Texto simples */}
      <Text>Olá, mundo!</Text>

      {/* Texto com estilo */}
      <Text style={{ fontSize: 24, fontWeight: 'bold', color: '#333' }}>
        Título grande
      </Text>

      {/* Aninhamento de Text — herda estilos do pai */}
      <Text style={{ color: 'gray' }}>
        Texto cinza com{' '}
        <Text style={{ fontWeight: 'bold', color: 'black' }}>
          parte em negrito
        </Text>
        {' '}e volta ao cinza.
      </Text>

      {/* numberOfLines trunca o texto com reticências */}
      <Text numberOfLines={2} ellipsizeMode="tail">
        Este é um texto muito longo que será truncado após duas linhas...
        Lorem ipsum dolor sit amet consectetur adipiscing elit.
      </Text>
    </View>
  );
}
```

> ⚠️ **Erro comum:** colocar texto fora de `<Text>` causa um erro de tempo de execução:
> ```tsx
> <View>Texto aqui</View>  // ❌ ERRO: Text strings must be rendered within a <Text> component
> <View><Text>Texto aqui</Text></View>  // ✅ correto
> ```

---

### 3.4 Image — exibindo imagens

```tsx
import { Image, View } from 'react-native';

function ExemploImage() {
  return (
    <View>
      {/* Imagem local — da pasta assets do projeto */}
      <Image
        source={require('./assets/logo.png')}
        style={{ width: 200, height: 100 }}
      />

      {/* Imagem remota — de uma URL */}
      {/* width e height são OBRIGATÓRIOS para imagens remotas */}
      <Image
        source={{ uri: 'https://picsum.photos/200/100' }}
        style={{ width: 200, height: 100, borderRadius: 8 }}
      />

      {/* resizeMode controla como a imagem preenche o espaço */}
      <Image
        source={{ uri: 'https://picsum.photos/400/200' }}
        style={{ width: 200, height: 100 }}
        resizeMode="cover"   // cover | contain | stretch | center
      />
    </View>
  );
}
```

> **Atenção:** Para imagens remotas em produção, use a biblioteca **Expo Image** ou **react-native-fast-image** em vez do `Image` padrão. Elas oferecem cache avançado, melhor performance e placeholder durante o carregamento — equivalente ao Coil no Android.

---

### 3.5 TextInput — entrada de texto

```tsx
import { TextInput, View, Text } from 'react-native';
import { useState } from 'react';

function ExemploTextInput() {
  const [email, setEmail] = useState('');
  const [senha, setSenha] = useState('');

  return (
    <View style={{ padding: 16, gap: 12 }}>

      {/* Campo básico */}
      <TextInput
        value={email}
        onChangeText={setEmail}          // chamado a cada tecla pressionada
        placeholder="Digite seu e-mail"
        placeholderTextColor="#999"
        keyboardType="email-address"     // abre teclado com @
        autoCapitalize="none"
        style={{
          borderWidth: 1,
          borderColor: '#ccc',
          borderRadius: 8,
          padding: 12,
          fontSize: 16,
        }}
      />

      {/* Campo de senha */}
      <TextInput
        value={senha}
        onChangeText={setSenha}
        placeholder="Senha"
        secureTextEntry={true}           // oculta o texto (bullets)
        placeholderTextColor="#999"
        style={{
          borderWidth: 1,
          borderColor: '#ccc',
          borderRadius: 8,
          padding: 12,
          fontSize: 16,
        }}
      />

      <Text>E-mail digitado: {email}</Text>
    </View>
  );
}
```

**Propriedades mais utilizadas do TextInput:**

| Propriedade | O que faz | Exemplo de valor |
|---|---|---|
| `value` | Valor controlado pelo estado | `email` |
| `onChangeText` | Callback a cada caractere digitado | `(texto) => setEmail(texto)` |
| `placeholder` | Texto de dica quando vazio | `"Digite seu nome"` |
| `keyboardType` | Tipo de teclado | `"email-address"`, `"numeric"`, `"phone-pad"` |
| `secureTextEntry` | Oculta o texto (senha) | `true` |
| `autoCapitalize` | Controla capitalização automática | `"none"`, `"sentences"`, `"words"` |
| `multiline` | Permite múltiplas linhas | `true` |
| `maxLength` | Limite de caracteres | `100` |
| `onSubmitEditing` | Callback ao pressionar "Enter" | `() => fazerLogin()` |

---

### 3.6 TouchableOpacity e Pressable — interações de toque

```tsx
import { TouchableOpacity, Pressable, Text, View } from 'react-native';

function ExemploBotoes() {
  return (
    <View style={{ gap: 12, padding: 16 }}>

      {/* TouchableOpacity — o mais comum para botões */}
      {/* Reduz a opacidade visualmente ao pressionar */}
      <TouchableOpacity
        onPress={() => console.log('Pressionado!')}
        activeOpacity={0.7}    // opacidade ao pressionar (0 a 1)
        style={{
          backgroundColor: '#007AFF',
          padding: 16,
          borderRadius: 8,
          alignItems: 'center',
        }}
      >
        <Text style={{ color: 'white', fontWeight: 'bold' }}>
          Clique aqui
        </Text>
      </TouchableOpacity>

      {/* Pressable — mais moderno e flexível */}
      {/* Recebe o estado de pressionamento como parâmetro */}
      <Pressable
        onPress={() => console.log('Pressable!')}
        style={({ pressed }) => ({
          backgroundColor: pressed ? '#0055CC' : '#007AFF',
          padding: 16,
          borderRadius: 8,
          alignItems: 'center',
          opacity: pressed ? 0.8 : 1,
        })}
      >
        {({ pressed }) => (
          <Text style={{ color: 'white' }}>
            {pressed ? 'Pressionando...' : 'Pressable'}
          </Text>
        )}
      </Pressable>

    </View>
  );
}
```

**Comparativo dos componentes de toque:**

| Componente | Feedback visual | Quando usar |
|---|---|---|
| `TouchableOpacity` | Reduz opacidade | Botões gerais — o mais usado |
| `TouchableHighlight` | Escurece o fundo | Listas com itens clicáveis |
| `TouchableWithoutFeedback` | Nenhum | Fechar teclado ao tocar na tela |
| `Pressable` | Totalmente customizável | Quando precisa de controle total do estado de pressão |

---

### 3.7 ScrollView — conteúdo rolável

```tsx
import { ScrollView, View, Text } from 'react-native';

function ExemploScrollView() {
  return (
    // ScrollView renderiza TODOS os filhos de uma vez
    // Use apenas para conteúdo de tamanho previsível e pequeno
    <ScrollView
      style={{ flex: 1 }}
      contentContainerStyle={{ padding: 16 }}
      showsVerticalScrollIndicator={false}   // esconde a barra de rolagem
      horizontal={false}                     // true para rolagem horizontal
    >
      {/* Conteúdo que excede a tela */}
      {Array.from({ length: 20 }, (_, i) => (
        <View key={i} style={{ height: 80, backgroundColor: '#eee', marginBottom: 8, borderRadius: 8 }}>
          <Text style={{ padding: 16 }}>Item {i + 1}</Text>
        </View>
      ))}
    </ScrollView>
  );
}
```

> ⚠️ `ScrollView` renderiza **todos** os filhos imediatamente, igual ao `Column` no Jetpack Compose. Para listas longas, use `FlatList` — a versão virtualizada que só renderiza os itens visíveis.

---

## Parte 4 — Estilização no React Native

---

### 4.1 StyleSheet — o sistema de estilos

No React Native, **não existe CSS**. A estilização é feita via objetos JavaScript que seguem uma API inspirada no CSS, mas com diferenças importantes. O `StyleSheet.create()` é o método recomendado para definir estilos.

```tsx
import { StyleSheet, View, Text } from 'react-native';

function ExemploEstilos() {
  return (
    <View style={styles.container}>
      <Text style={styles.titulo}>Título</Text>
      <Text style={[styles.texto, styles.destaque]}>
        Múltiplos estilos — o array mescla os objetos
      </Text>
    </View>
  );
}

// StyleSheet.create() valida os estilos em tempo de desenvolvimento
// e otimiza a performance ao enviar os estilos para o lado nativo
const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 16,
    backgroundColor: '#fff',
  },
  titulo: {
    fontSize: 24,
    fontWeight: 'bold',
    color: '#333',
    marginBottom: 8,
  },
  texto: {
    fontSize: 16,
    color: '#666',
    lineHeight: 24,
  },
  destaque: {
    color: '#007AFF',      // sobrescreve a cor definida em 'texto'
    fontWeight: '600',
  },
});
```

**Diferenças críticas entre CSS e React Native StyleSheet:**

| CSS (Web) | React Native | Observação |
|---|---|---|
| `font-size: 16px` | `fontSize: 16` | Sem unidades — dp/pt por padrão |
| `background-color: red` | `backgroundColor: 'red'` | camelCase, sem hífen |
| `border-radius: 8px` | `borderRadius: 8` | Sem unidades |
| `display: flex` | Flex por padrão | Todo View já é flex |
| `flex-direction: column` | `flexDirection: 'column'` | Padrão é `column` (diferente do web que é `row`) |
| `margin: 8px 16px` | `marginVertical: 8, marginHorizontal: 16` | Sem shorthand de múltiplos valores |
| `padding: 8px 16px 4px 12px` | Não existe — use `paddingTop`, `paddingRight`, etc. | |
| `box-shadow` | `shadowColor`, `shadowOffset`, `elevation` | Diferente para iOS e Android |

---

### 4.2 Flexbox no React Native

O React Native usa **Flexbox** como sistema de layout, mas com algumas diferenças do Flexbox web:

- **`flexDirection` padrão é `'column'`** (web é `'row'`)
- **`alignContent` padrão é `'flex-start'`** (web é `'stretch'`)
- Todo `View` já é `display: flex` por padrão

```tsx
import { View, Text, StyleSheet } from 'react-native';

function ExemploFlex() {
  return (
    <View style={styles.container}>

      {/* Linha horizontal — equivale a Row no Compose */}
      <View style={styles.linha}>
        <View style={[styles.caixa, { flex: 2 }]}>
          <Text>2/3 do espaço</Text>
        </View>
        <View style={[styles.caixa, { flex: 1, backgroundColor: '#e0e0ff' }]}>
          <Text>1/3 do espaço</Text>
        </View>
      </View>

      {/* Coluna vertical — equivale a Column no Compose */}
      <View style={styles.coluna}>
        <Text>Topo</Text>
        <Text>Meio</Text>
        <Text>Base</Text>
      </View>

    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 16,
  },
  linha: {
    flexDirection: 'row',              // empilha filhos horizontalmente
    justifyContent: 'space-between',   // espaço entre os filhos
    alignItems: 'center',              // centraliza verticalmente
    gap: 8,                            // espaço fixo entre filhos (RN 0.71+)
  },
  coluna: {
    flexDirection: 'column',           // padrão — empilha verticalmente
    justifyContent: 'space-between',
    height: 120,
  },
  caixa: {
    backgroundColor: '#e8f4fd',
    padding: 8,
    borderRadius: 4,
  },
});
```

**Equivalência com Jetpack Compose:**

| Jetpack Compose | React Native |
|---|---|
| `Column` | `View` com `flexDirection: 'column'` (padrão) |
| `Row` | `View` com `flexDirection: 'row'` |
| `Box` | `View` com `position: 'relative'` e filhos `position: 'absolute'` |
| `Modifier.weight(1f)` | `flex: 1` |
| `Arrangement.SpaceBetween` | `justifyContent: 'space-between'` |
| `Alignment.CenterVertically` | `alignItems: 'center'` |
| `Spacer(modifier = Modifier.height(8.dp))` | `<View style={{ height: 8 }} />` ou `gap: 8` |

---

### 4.3 Estilos dinâmicos e condicionais

```tsx
import { View, Text, TouchableOpacity, StyleSheet } from 'react-native';
import { useState } from 'react';

function BotaoToggle() {
  const [ativo, setAtivo] = useState(false);

  return (
    <TouchableOpacity
      onPress={() => setAtivo(!ativo)}
      // Array de estilos — o último sobrescreve propriedades repetidas
      style={[
        styles.botao,
        ativo ? styles.botaoAtivo : styles.botaoInativo,
      ]}
    >
      <Text style={[styles.textoBotao, ativo && styles.textoAtivo]}>
        {ativo ? 'Ativado' : 'Desativado'}
      </Text>
    </TouchableOpacity>
  );
}

const styles = StyleSheet.create({
  botao: {
    padding: 16,
    borderRadius: 8,
    alignItems: 'center',
    borderWidth: 2,
  },
  botaoAtivo: {
    backgroundColor: '#007AFF',
    borderColor: '#005DC1',
  },
  botaoInativo: {
    backgroundColor: 'white',
    borderColor: '#ccc',
  },
  textoBotao: {
    fontWeight: 'bold',
    fontSize: 16,
  },
  textoAtivo: {
    color: 'white',
  },
});
```

---

## Parte 5 — Estado e Hooks

---

### 5.1 O que é estado no React Native?

Estado é qualquer dado que muda ao longo do tempo e deve causar uma atualização visual quando muda — exatamente o mesmo conceito do `mutableStateOf` no Jetpack Compose.

No React Native, o estado é gerenciado com **Hooks** — funções especiais do React que permitem adicionar estado e outros recursos a componentes funcionais.

```tsx
// Sem estado — componente estático
function Contador() {
  let contador = 0;  // variável comum — o React não monitora isso

  return (
    <TouchableOpacity onPress={() => { contador++; console.log(contador); }}>
      <Text>Valor: {contador}</Text>
      {/* A tela NUNCA atualiza — React não sabe que contador mudou */}
    </TouchableOpacity>
  );
}
```

---

### 5.2 useState — o hook fundamental

`useState` é equivalente ao `mutableStateOf` + `remember` do Compose. Ele declara um estado que o React monitora e, quando muda, reexecuta o componente.

```tsx
import { useState } from 'react';
import { View, Text, TouchableOpacity, StyleSheet } from 'react-native';

function ContadorCorreto() {
  // useState(0) → valor inicial é 0
  // Retorna [valorAtual, funcaoParaAtualizar]
  const [contador, setContador] = useState(0);
  const [nome, setNome] = useState('Usuário');

  return (
    <View style={styles.container}>
      <Text style={styles.texto}>Olá, {nome}!</Text>
      <Text style={styles.texto}>Contagem: {contador}</Text>

      <TouchableOpacity
        style={styles.botao}
        // setContador(novoValor) → atualiza o estado e reexecuta o componente
        onPress={() => setContador(contador + 1)}
      >
        <Text style={styles.textoBotao}>Incrementar</Text>
      </TouchableOpacity>

      <TouchableOpacity
        style={[styles.botao, { backgroundColor: 'red' }]}
        onPress={() => setContador(0)}
      >
        <Text style={styles.textoBotao}>Resetar</Text>
      </TouchableOpacity>
    </View>
  );
}
```

**Comparativo com Kotlin/Compose:**

| Jetpack Compose | React Native |
|---|---|
| `var x by remember { mutableStateOf(0) }` | `const [x, setX] = useState(0)` |
| `x = novoValor` | `setX(novoValor)` |
| Recomposição automática | Re-renderização automática |
| `remember` previne reset na recomposição | `useState` já garante isso |

---

### 5.3 Atualização funcional do estado

Quando o novo valor depende do valor anterior, use a **forma funcional** do setter para evitar problemas com closures:

```tsx
const [contador, setContador] = useState(0);

// ❌ Pode ter bugs em atualizações rápidas — usa o valor do closure
onPress={() => setContador(contador + 1)}

// ✅ Sempre usa o valor mais recente — forma funcional
onPress={() => setContador(valorAnterior => valorAnterior + 1)}
```

---

### 5.4 useEffect — efeitos colaterais

`useEffect` é usado para executar código que produz efeitos colaterais: buscar dados de uma API, configurar timers, fazer log, etc. Ele é equivalente ao `init { }` do ViewModel do Android.

```tsx
import { useState, useEffect } from 'react';
import { View, Text, ActivityIndicator } from 'react-native';

function ExemploUseEffect() {
  const [usuarios, setUsuarios] = useState([]);
  const [carregando, setCarregando] = useState(true);
  const [erro, setErro] = useState(null);

  // useEffect recebe dois parâmetros:
  // 1. A função a executar (efeito)
  // 2. O array de dependências — quando executar

  useEffect(() => {
    // Este código roda APÓS o componente aparecer na tela
    async function buscarUsuarios() {
      try {
        const resposta = await fetch('https://jsonplaceholder.typicode.com/users');
        const dados = await resposta.json();
        setUsuarios(dados);
      } catch (e) {
        setErro('Erro ao carregar dados');
      } finally {
        setCarregando(false);
      }
    }

    buscarUsuarios();

    // Função de limpeza — executada quando o componente sai da tela
    return () => {
      console.log('Componente desmontado — cancele requisições aqui');
    };
  }, []);
  // [] = array de dependências vazio = executa apenas uma vez (na montagem)

  if (carregando) return <ActivityIndicator size="large" color="#007AFF" />;
  if (erro) return <Text style={{ color: 'red' }}>{erro}</Text>;

  return (
    <View>
      {usuarios.map(usuario => (
        <Text key={usuario.id}>{usuario.name}</Text>
      ))}
    </View>
  );
}
```

**Como o array de dependências controla o `useEffect`:**

```tsx
useEffect(() => { /* executa toda vez que o componente re-renderiza */ });

useEffect(() => { /* executa APENAS na montagem (equivalente ao init do ViewModel) */ }, []);

useEffect(() => {
  /* executa quando 'userId' ou 'filtro' mudam */
}, [userId, filtro]);
```

---

### 5.5 useMemo e useCallback — otimização de performance

```tsx
import { useMemo, useCallback } from 'react';

function ListaFiltrada({ itens, termoBusca }) {
  // useMemo: recalcula apenas quando 'itens' ou 'termoBusca' mudam
  // Evita filtrar uma lista grande a cada re-renderização
  const itensFiltrados = useMemo(() => {
    return itens.filter(item =>
      item.nome.toLowerCase().includes(termoBusca.toLowerCase())
    );
  }, [itens, termoBusca]);

  // useCallback: memoriza a função — evita recriá-la a cada render
  // Útil quando a função é passada como prop para componentes filhos
  const handlePress = useCallback((id) => {
    console.log('Item selecionado:', id);
  }, []); // sem dependências — nunca recria

  return (
    <FlatList
      data={itensFiltrados}
      keyExtractor={item => item.id.toString()}
      renderItem={({ item }) => (
        <TouchableOpacity onPress={() => handlePress(item.id)}>
          <Text>{item.nome}</Text>
        </TouchableOpacity>
      )}
    />
  );
}
```

---

### 5.6 Custom Hooks — reutilizando lógica de estado

Custom hooks são funções que encapsulam lógica de estado reutilizável. O nome deve começar com `use`.

```tsx
// hooks/useBuscaCep.ts
import { useState } from 'react';

interface DadosCep {
  logradouro: string;
  bairro: string;
  cidade: string;
  estado: string;
}

function useBuscaCep() {
  const [dados, setDados] = useState<DadosCep | null>(null);
  const [carregando, setCarregando] = useState(false);
  const [erro, setErro] = useState<string | null>(null);

  async function buscar(cep: string) {
    setCarregando(true);
    setErro(null);
    try {
      const resposta = await fetch(`https://viacep.com.br/ws/${cep}/json/`);
      const json = await resposta.json();
      if (json.erro) throw new Error('CEP não encontrado');
      setDados({
        logradouro: json.logradouro,
        bairro: json.bairro,
        cidade: json.localidade,
        estado: json.uf,
      });
    } catch (e: any) {
      setErro(e.message);
    } finally {
      setCarregando(false);
    }
  }

  return { dados, carregando, erro, buscar };
}

// Uso em qualquer componente
function TelaCadastro() {
  const [cep, setCep] = useState('');
  const { dados, carregando, erro, buscar } = useBuscaCep();

  return (
    <View>
      <TextInput value={cep} onChangeText={setCep} placeholder="CEP" />
      <TouchableOpacity onPress={() => buscar(cep)}>
        <Text>Buscar</Text>
      </TouchableOpacity>
      {carregando && <ActivityIndicator />}
      {dados && <Text>{dados.logradouro}, {dados.bairro}</Text>}
      {erro && <Text style={{ color: 'red' }}>{erro}</Text>}
    </View>
  );
}
```

---

## Parte 6 — FlatList e Listas Virtualizadas

---

### 6.1 Por que ScrollView não serve para listas longas

Exatamente como o `Column` no Jetpack Compose, o `ScrollView` renderiza todos os filhos de uma vez. Com 500 itens, ele cria 500 elementos nativos simultâneos — causando lentidão e consumo excessivo de memória.

**A solução: `FlatList`** — a lista virtualizada do React Native.

---

### 6.2 FlatList — listas de alta performance

```tsx
import { FlatList, View, Text, TouchableOpacity, StyleSheet } from 'react-native';

interface Tarefa {
  id: string;
  titulo: string;
  concluida: boolean;
}

const TAREFAS: Tarefa[] = [
  { id: '1', titulo: 'Comprar leite', concluida: false },
  { id: '2', titulo: 'Fazer exercício', concluida: true },
  // ... pode ter milhares de itens
];

function ListaTarefas() {
  return (
    <FlatList
      // ── DADOS ────────────────────────────────────────
      data={TAREFAS}

      // keyExtractor: identifica cada item de forma única
      // Equivalente ao 'key' no LazyColumn do Compose
      keyExtractor={(item) => item.id}

      // renderItem: função que retorna o componente para cada item
      renderItem={({ item, index }) => (
        <View style={styles.item}>
          <Text style={[styles.titulo, item.concluida && styles.concluido]}>
            {index + 1}. {item.titulo}
          </Text>
        </View>
      )}

      // ── OTIMIZAÇÕES ──────────────────────────────────
      // Quantos itens renderizar além da área visível (buffer)
      windowSize={10}

      // Remove itens que ficaram longe da área visível (econômico de memória)
      removeClippedSubviews={true}

      // ── LAYOUT ───────────────────────────────────────
      // Cabeçalho fixo acima da lista
      ListHeaderComponent={
        <Text style={styles.cabecalho}>Minhas Tarefas</Text>
      }

      // Rodapé fixo abaixo da lista
      ListFooterComponent={
        <Text style={styles.rodape}>{TAREFAS.length} tarefas no total</Text>
      }

      // Exibido quando data está vazio
      ListEmptyComponent={
        <Text style={styles.vazio}>Nenhuma tarefa ainda!</Text>
      }

      // Espaço entre os itens
      ItemSeparatorComponent={() => <View style={styles.separador} />}

      // ── PADDING ──────────────────────────────────────
      contentContainerStyle={{ padding: 16 }}

      // ── EVENTOS ──────────────────────────────────────
      // Chamado quando o usuário chega próximo ao final da lista
      onEndReached={() => carregarMaisItens()}
      onEndReachedThreshold={0.3}  // 30% antes do fim

      // Puxar para atualizar (pull-to-refresh)
      refreshing={carregando}
      onRefresh={recarregarLista}
    />
  );
}

const styles = StyleSheet.create({
  item: { padding: 16, backgroundColor: 'white', borderRadius: 8 },
  titulo: { fontSize: 16 },
  concluido: { textDecorationLine: 'line-through', color: '#999' },
  cabecalho: { fontSize: 20, fontWeight: 'bold', marginBottom: 12 },
  rodape: { textAlign: 'center', color: '#999', marginTop: 12 },
  vazio: { textAlign: 'center', color: '#999', marginTop: 40 },
  separador: { height: 8 },
});
```

---

### 6.3 SectionList — listas com seções

```tsx
import { SectionList, Text, View } from 'react-native';

const DADOS_COM_SECOES = [
  {
    titulo: 'Frutas',
    data: ['Maçã', 'Banana', 'Laranja'],
  },
  {
    titulo: 'Verduras',
    data: ['Alface', 'Brócolis', 'Cenoura'],
  },
];

function ListaComSecoes() {
  return (
    <SectionList
      sections={DADOS_COM_SECOES}
      keyExtractor={(item, index) => item + index}
      renderItem={({ item }) => (
        <View style={{ padding: 12 }}>
          <Text>{item}</Text>
        </View>
      )}
      renderSectionHeader={({ section: { titulo } }) => (
        <View style={{ backgroundColor: '#f0f0f0', padding: 8 }}>
          <Text style={{ fontWeight: 'bold', fontSize: 16 }}>{titulo}</Text>
        </View>
      )}
    />
  );
}
```

**Comparativo ScrollView vs FlatList vs SectionList:**

| Componente | Virtualizado | Quando usar |
|---|---|---|
| `ScrollView` | ❌ | Conteúdo pequeno e fixo (formulários, detalhes) |
| `FlatList` | ✅ | Listas com quantidade variável ou grande de itens |
| `SectionList` | ✅ | Listas agrupadas por categoria/seção |

---

## Parte 7 — Navegação com React Navigation

---

### 7.1 O que é React Navigation?

React Navigation é a **biblioteca de navegação padrão da comunidade React Native**. Ela não faz parte do núcleo do React Native — é instalada separadamente, mas é tão universalmente adotada que é considerada o padrão de facto.

```bash
# Instalação básica
npm install @react-navigation/native
npm install react-native-screens react-native-safe-area-context

# Stack Navigator (navegação em pilha — como o NavController do Android)
npm install @react-navigation/native-stack

# Bottom Tab Navigator (abas inferiores)
npm install @react-navigation/bottom-tabs
```

---

### 7.2 Stack Navigator — navegação em pilha

```tsx
// navigation/AppNavigator.tsx
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';

// Define os parâmetros de cada rota — equivalente ao object Rotas do Android
type RootStackParams = {
  TelaInicial: undefined;         // sem parâmetros
  TelaDetalhe: { itemId: number; titulo: string }; // com parâmetros
  TelaPerfil: undefined;
};

const Stack = createNativeStackNavigator<RootStackParams>();

export function AppNavigator() {
  return (
    // NavigationContainer: equivalente ao NavHost do Android
    // Deve envolver toda a árvore de navegação
    <NavigationContainer>
      <Stack.Navigator
        initialRouteName="TelaInicial"
        screenOptions={{
          headerStyle: { backgroundColor: '#007AFF' },
          headerTintColor: 'white',
          headerTitleStyle: { fontWeight: 'bold' },
        }}
      >
        {/* Cada Stack.Screen registra uma tela — equivalente a composable() no NavHost */}
        <Stack.Screen
          name="TelaInicial"
          component={TelaInicial}
          options={{ title: 'Início' }}
        />
        <Stack.Screen
          name="TelaDetalhe"
          component={TelaDetalhe}
          options={({ route }) => ({ title: route.params.titulo })}
        />
        <Stack.Screen
          name="TelaPerfil"
          component={TelaPerfil}
          options={{ title: 'Meu Perfil' }}
        />
      </Stack.Navigator>
    </NavigationContainer>
  );
}
```

---

### 7.3 Navegar entre telas e passar parâmetros

```tsx
// screens/TelaInicial.tsx
import { NativeStackScreenProps } from '@react-navigation/native-stack';

// O TypeScript garante que os parâmetros passados são os corretos
type Props = NativeStackScreenProps<RootStackParams, 'TelaInicial'>;

function TelaInicial({ navigation }: Props) {
  return (
    <View style={{ padding: 16 }}>
      <TouchableOpacity
        onPress={() =>
          navigation.navigate('TelaDetalhe', {
            itemId: 42,
            titulo: 'Produto Incrível',
          })
        }
      >
        <Text>Ver Detalhe</Text>
      </TouchableOpacity>

      <TouchableOpacity onPress={() => navigation.navigate('TelaPerfil')}>
        <Text>Ver Perfil</Text>
      </TouchableOpacity>
    </View>
  );
}

// screens/TelaDetalhe.tsx
type DetalheProps = NativeStackScreenProps<RootStackParams, 'TelaDetalhe'>;

function TelaDetalhe({ navigation, route }: DetalheProps) {
  // route.params contém os parâmetros passados na navegação
  const { itemId, titulo } = route.params;

  return (
    <View style={{ padding: 16 }}>
      <Text>ID: {itemId}</Text>
      <Text>Título: {titulo}</Text>
      <TouchableOpacity onPress={() => navigation.goBack()}>
        <Text>← Voltar</Text>
      </TouchableOpacity>
    </View>
  );
}
```

---

### 7.4 Bottom Tab Navigator — navegação por abas

```tsx
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import { Ionicons } from '@expo/vector-icons'; // lib de ícones

type TabParams = {
  Inicio: undefined;
  Busca: undefined;
  Perfil: undefined;
};

const Tab = createBottomTabNavigator<TabParams>();

function TabNavigator() {
  return (
    <Tab.Navigator
      screenOptions={({ route }) => ({
        // Ícone dinâmico por aba
        tabBarIcon: ({ focused, color, size }) => {
          const icones = {
            Inicio: focused ? 'home' : 'home-outline',
            Busca: focused ? 'search' : 'search-outline',
            Perfil: focused ? 'person' : 'person-outline',
          };
          return <Ionicons name={icones[route.name]} size={size} color={color} />;
        },
        tabBarActiveTintColor: '#007AFF',
        tabBarInactiveTintColor: 'gray',
      })}
    >
      <Tab.Screen name="Inicio" component={TelaInicio} options={{ title: 'Início' }} />
      <Tab.Screen name="Busca" component={TelaBusca} />
      <Tab.Screen name="Perfil" component={TelaPerfil} />
    </Tab.Navigator>
  );
}
```

---

### 7.5 Navegação aninhada — Stack dentro de Tab

```tsx
// Estrutura comum em apps reais:
// TabNavigator contém múltiplos StackNavigators
function AppNavigator() {
  return (
    <NavigationContainer>
      <Tab.Navigator>
        <Tab.Screen name="InicioTab" component={InicioStack} />
        <Tab.Screen name="PerfilTab" component={PerfilStack} />
      </Tab.Navigator>
    </NavigationContainer>
  );
}

// Stack de Início: Lista → Detalhe
const InicioStack = createNativeStackNavigator();
function InicioStackNavigator() {
  return (
    <InicioStack.Navigator>
      <InicioStack.Screen name="Lista" component={TelaLista} />
      <InicioStack.Screen name="Detalhe" component={TelaDetalhe} />
    </InicioStack.Navigator>
  );
}
```

---

## Parte 8 — Gerenciamento de Estado Global

---

### 8.1 Quando o estado local não basta

O `useState` dentro de um componente é suficiente para estados locais. Mas quando múltiplas telas precisam do mesmo dado — como informações do usuário logado, carrinho de compras, configurações — o estado precisa ser **global**.

---

### 8.2 Context API — estado global nativo do React

```tsx
// contexts/AuthContext.tsx
import { createContext, useContext, useState, ReactNode } from 'react';

interface Usuario {
  id: number;
  nome: string;
  email: string;
}

interface AuthContextType {
  usuario: Usuario | null;
  fazerLogin: (email: string, senha: string) => Promise<void>;
  fazerLogout: () => void;
  carregando: boolean;
}

// 1. Criar o contexto
const AuthContext = createContext<AuthContextType | null>(null);

// 2. Criar o Provider — envolve toda a árvore que precisa do contexto
export function AuthProvider({ children }: { children: ReactNode }) {
  const [usuario, setUsuario] = useState<Usuario | null>(null);
  const [carregando, setCarregando] = useState(false);

  async function fazerLogin(email: string, senha: string) {
    setCarregando(true);
    try {
      const resposta = await api.login(email, senha);
      setUsuario(resposta.usuario);
    } finally {
      setCarregando(false);
    }
  }

  function fazerLogout() {
    setUsuario(null);
  }

  return (
    <AuthContext.Provider value={{ usuario, fazerLogin, fazerLogout, carregando }}>
      {children}
    </AuthContext.Provider>
  );
}

// 3. Hook customizado para consumir o contexto
export function useAuth() {
  const contexto = useContext(AuthContext);
  if (!contexto) throw new Error('useAuth deve ser usado dentro de AuthProvider');
  return contexto;
}

// App.tsx — Provider envolve toda a navegação
export default function App() {
  return (
    <AuthProvider>
      <AppNavigator />
    </AuthProvider>
  );
}

// Em qualquer tela — acessa o estado global
function TelaHome() {
  const { usuario, fazerLogout } = useAuth();

  return (
    <View>
      <Text>Bem-vindo, {usuario?.nome}!</Text>
      <TouchableOpacity onPress={fazerLogout}>
        <Text>Sair</Text>
      </TouchableOpacity>
    </View>
  );
}
```

---

### 8.3 Zustand — gerenciamento de estado leve e moderno

Para estados globais mais complexos, a comunidade migrou do Redux (verboso e boilerplate-heavy) para soluções mais modernas. **Zustand** é a mais popular em 2024.

```bash
npm install zustand
```

```tsx
// stores/useCarrinhoStore.ts
import { create } from 'zustand';

interface Produto {
  id: number;
  nome: string;
  preco: number;
  quantidade: number;
}

interface CarrinhoStore {
  itens: Produto[];
  totalItens: number;
  totalPreco: number;
  adicionarItem: (produto: Omit<Produto, 'quantidade'>) => void;
  removerItem: (id: number) => void;
  limparCarrinho: () => void;
}

export const useCarrinhoStore = create<CarrinhoStore>((set, get) => ({
  itens: [],
  totalItens: 0,
  totalPreco: 0,

  adicionarItem: (produto) => {
    const itensAtuais = get().itens;
    const itemExistente = itensAtuais.find(i => i.id === produto.id);

    const novosItens = itemExistente
      ? itensAtuais.map(i => i.id === produto.id ? { ...i, quantidade: i.quantidade + 1 } : i)
      : [...itensAtuais, { ...produto, quantidade: 1 }];

    set({
      itens: novosItens,
      totalItens: novosItens.reduce((acc, i) => acc + i.quantidade, 0),
      totalPreco: novosItens.reduce((acc, i) => acc + i.preco * i.quantidade, 0),
    });
  },

  removerItem: (id) => {
    const novosItens = get().itens.filter(i => i.id !== id);
    set({
      itens: novosItens,
      totalItens: novosItens.reduce((acc, i) => acc + i.quantidade, 0),
      totalPreco: novosItens.reduce((acc, i) => acc + i.preco * i.quantidade, 0),
    });
  },

  limparCarrinho: () => set({ itens: [], totalItens: 0, totalPreco: 0 }),
}));

// Uso em qualquer componente — sem Provider!
function TelaProduto({ produto }) {
  const { adicionarItem, totalItens } = useCarrinhoStore();

  return (
    <View>
      <Text>Carrinho: {totalItens} itens</Text>
      <TouchableOpacity onPress={() => adicionarItem(produto)}>
        <Text>Adicionar ao Carrinho</Text>
      </TouchableOpacity>
    </View>
  );
}
```

**Comparativo de bibliotecas de estado global:**

| Biblioteca | Verbosidade | Curva de aprendizado | Quando usar |
|---|---|---|---|
| `Context API` | Média | Baixa | Estado simples (autenticação, tema) |
| `Zustand` | Baixa | Baixa | Estado global de média complexidade |
| `Redux Toolkit` | Alta | Alta | Apps enterprise, equipes grandes |
| `Jotai` | Muito baixa | Baixa | Estado atômico e granular |
| `Recoil` | Baixa | Média | Apps com estado derivado complexo |

---

## Parte 9 — Requisições HTTP

---

### 9.1 Fetch API nativa

```tsx
// O React Native inclui a Fetch API — não precisa de instalação
async function buscarUsuarios(): Promise<Usuario[]> {
  const resposta = await fetch('https://api.exemplo.com/usuarios', {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`,
    },
  });

  if (!resposta.ok) {
    throw new Error(`Erro HTTP: ${resposta.status}`);
  }

  return resposta.json();
}

// POST com corpo
async function criarTarefa(titulo: string): Promise<Tarefa> {
  const resposta = await fetch('https://api.exemplo.com/tarefas', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ titulo, concluida: false }),
  });

  return resposta.json();
}
```

---

### 9.2 Axios — cliente HTTP com mais recursos

```bash
npm install axios
```

```tsx
// services/api.ts
import axios from 'axios';

// Criando uma instância com configurações padrão
export const api = axios.create({
  baseURL: 'https://api.exemplo.com',
  timeout: 10000,
  headers: { 'Content-Type': 'application/json' },
});

// Interceptor de requisição — adiciona token automaticamente
api.interceptors.request.use(async (config) => {
  const token = await AsyncStorage.getItem('token');
  if (token) config.headers.Authorization = `Bearer ${token}`;
  return config;
});

// Interceptor de resposta — trata erros globalmente
api.interceptors.response.use(
  (resposta) => resposta,
  async (erro) => {
    if (erro.response?.status === 401) {
      // Token expirado — redirecionar para login
      await AsyncStorage.removeItem('token');
      navigation.navigate('Login');
    }
    return Promise.reject(erro);
  }
);

// Uso
const usuarios = await api.get<Usuario[]>('/usuarios');
const novaTarefa = await api.post<Tarefa>('/tarefas', { titulo: 'Nova tarefa' });
```

---

### 9.3 TanStack Query (React Query) — o padrão moderno

**TanStack Query** é a biblioteca mais recomendada pela comunidade para gerenciar requisições HTTP. Ela resolve automaticamente: cache, loading states, error states, refetching, paginação e muito mais.

```bash
npm install @tanstack/react-query
```

```tsx
// App.tsx — configuração do Provider
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';

const queryClient = new QueryClient({
  defaultOptions: {
    queries: {
      staleTime: 5 * 60 * 1000,  // dados válidos por 5 minutos
      retry: 2,                    // tenta 2 vezes em caso de erro
    },
  },
});

export default function App() {
  return (
    <QueryClientProvider client={queryClient}>
      <AppNavigator />
    </QueryClientProvider>
  );
}

// Uso em qualquer componente
function TelaProdutos() {
  // useQuery cuida de: loading, erro, cache, refetch automático
  const {
    data: produtos,
    isLoading,
    isError,
    error,
    refetch,
  } = useQuery({
    queryKey: ['produtos'],           // chave única para o cache
    queryFn: () => api.get('/produtos').then(r => r.data),
  });

  if (isLoading) return <ActivityIndicator />;
  if (isError) return <Text>Erro: {error.message}</Text>;

  return (
    <FlatList
      data={produtos}
      keyExtractor={p => p.id.toString()}
      renderItem={({ item }) => <CardProduto produto={item} />}
      refreshing={isLoading}
      onRefresh={refetch}
    />
  );
}

// useMutation para operações de escrita (POST, PUT, DELETE)
function FormularioTarefa() {
  const queryClient = useQueryClient();

  const criarTarefaMutation = useMutation({
    mutationFn: (novaTarefa: { titulo: string }) =>
      api.post('/tarefas', novaTarefa),

    onSuccess: () => {
      // Invalida o cache da lista — força um refetch
      queryClient.invalidateQueries({ queryKey: ['tarefas'] });
    },
  });

  return (
    <TouchableOpacity
      onPress={() => criarTarefaMutation.mutate({ titulo: 'Nova tarefa' })}
      disabled={criarTarefaMutation.isPending}
    >
      <Text>{criarTarefaMutation.isPending ? 'Salvando...' : 'Criar Tarefa'}</Text>
    </TouchableOpacity>
  );
}
```

**Comparativo de abordagens para requisições HTTP:**

| Abordagem | Cache automático | Loading state | Refetch | Paginação | Complexidade |
|---|---|---|---|---|---|
| `fetch` nativo | ❌ | Manual | Manual | Manual | Baixa |
| `Axios` | ❌ | Manual | Manual | Manual | Baixa |
| `TanStack Query` | ✅ | Automático | Automático | Automático | Média |
| `SWR` | ✅ | Automático | Automático | Parcial | Baixa |

---

## Parte 10 — Componentes de UI — Bibliotecas e Templates

---

### 10.1 Onde encontrar templates e componentes prontos?

Um dos maiores diferenciais do ecossistema React Native é a abundância de recursos prontos.

**Repositórios de templates e componentes:**

| Recurso | URL | O que oferece |
|---|---|---|
| **React Native Elements** | `rneui.org` | Biblioteca de UI completa, Material-inspired |
| **NativeBase / GlueStack** | `gluestack.io` | Componentes modernos, design system completo |
| **React Native Paper** | `callstack.github.io/react-native-paper` | Material Design 3 para React Native |
| **UI Kitten** | `akveo.github.io/react-native-ui-kitten` | Design system customizável |
| **Expo Snack** | `snack.expo.dev` | IDE online para testar componentes |
| **React Native Directory** | `reactnative.directory` | Catálogo de todas as libs da comunidade |
| **Nativewind Components** | `nativewind.dev` | Tailwind CSS para React Native |
| **Tamagui** | `tamagui.dev` | UI kit cross-platform de alta performance |
| **Shopify Restyle** | GitHub Shopify/restyle | Design system da Shopify |

---

### 10.2 React Native Paper — Material Design 3

```bash
npm install react-native-paper
npm install react-native-vector-icons
```

```tsx
import { Provider as PaperProvider, Button, TextInput, Card, Appbar } from 'react-native-paper';

// Envolve o app com o Provider do Paper
export default function App() {
  return (
    <PaperProvider>
      <AppNavigator />
    </PaperProvider>
  );
}

// Usando os componentes
function TelaCadastro() {
  const [nome, setNome] = useState('');

  return (
    <View style={{ padding: 16 }}>
      <Appbar.Header>
        <Appbar.BackAction onPress={() => navigation.goBack()} />
        <Appbar.Content title="Cadastro" />
      </Appbar.Header>

      <Card style={{ margin: 16 }}>
        <Card.Content>
          <TextInput
            label="Nome"
            value={nome}
            onChangeText={setNome}
            mode="outlined"
          />
          <Button
            mode="contained"
            onPress={() => salvar()}
            style={{ marginTop: 16 }}
          >
            Salvar
          </Button>
        </Card.Content>
      </Card>
    </View>
  );
}
```

---

### 10.3 NativeWind — Tailwind CSS para React Native

**NativeWind** traz a experiência do Tailwind CSS para o React Native — sem `StyleSheet`, apenas classes utilitárias.

```bash
npm install nativewind
npm install --save-dev tailwindcss
npx tailwindcss init
```

```tsx
// Com NativeWind — classes Tailwind no React Native
function CartaoPerfil({ nome, cargo, avatar }) {
  return (
    <View className="flex-row items-center p-4 bg-white rounded-xl shadow-md m-4">
      <Image
        source={{ uri: avatar }}
        className="w-14 h-14 rounded-full"
      />
      <View className="ml-3 flex-1">
        <Text className="text-lg font-bold text-gray-900">{nome}</Text>
        <Text className="text-sm text-gray-500">{cargo}</Text>
      </View>
      <TouchableOpacity className="bg-blue-500 px-4 py-2 rounded-lg">
        <Text className="text-white font-semibold">Seguir</Text>
      </TouchableOpacity>
    </View>
  );
}
```

---

### 10.4 Ícones — @expo/vector-icons e react-native-vector-icons

```bash
# No Expo (já incluído no SDK)
# @expo/vector-icons é pré-instalado

# No React Native CLI
npm install react-native-vector-icons
```

```tsx
import { Ionicons, MaterialIcons, FontAwesome5 } from '@expo/vector-icons';

function BarraNavegacao() {
  return (
    <View style={{ flexDirection: 'row', justifyContent: 'space-around', padding: 16 }}>
      <Ionicons name="home" size={28} color="#007AFF" />
      <MaterialIcons name="search" size={28} color="#666" />
      <FontAwesome5 name="user-circle" size={24} color="#666" />
    </View>
  );
}
```

**Famílias de ícones disponíveis:**

| Família | Prefixo | Quantidade | Estilo |
|---|---|---|---|
| `Ionicons` | `ion-` | ~1300 | iOS / Android |
| `MaterialIcons` | `md-` | ~1000 | Material Design |
| `FontAwesome5` | `fa5-` | ~1500 | Web clássico |
| `Feather` | `feather-` | ~280 | Minimalista |
| `AntDesign` | `antd-` | ~298 | Ant Design |

---

## Parte 11 — Animações

---

### 11.1 Animated API — animações nativas

```tsx
import { Animated, TouchableOpacity, View, Text } from 'react-native';
import { useRef } from 'react';

function BotaoAnimado() {
  // useRef mantém o valor entre re-renders sem causar re-render
  const escala = useRef(new Animated.Value(1)).current;
  const opacidade = useRef(new Animated.Value(1)).current;

  function aoPresionar() {
    // Animação paralela — escala e opacidade juntas
    Animated.parallel([
      Animated.spring(escala, {
        toValue: 0.95,
        useNativeDriver: true,  // SEMPRE true quando possível — roda na thread nativa
      }),
      Animated.timing(opacidade, {
        toValue: 0.7,
        duration: 100,
        useNativeDriver: true,
      }),
    ]).start();
  }

  function aoSoltar() {
    Animated.parallel([
      Animated.spring(escala, { toValue: 1, useNativeDriver: true }),
      Animated.timing(opacidade, { toValue: 1, duration: 100, useNativeDriver: true }),
    ]).start();
  }

  return (
    <Animated.View style={{ transform: [{ scale: escala }], opacity: opacidade }}>
      <TouchableOpacity
        onPressIn={aoPresionar}
        onPressOut={aoSoltar}
        style={{ backgroundColor: '#007AFF', padding: 16, borderRadius: 8 }}
      >
        <Text style={{ color: 'white', fontWeight: 'bold' }}>Pressione</Text>
      </TouchableOpacity>
    </Animated.View>
  );
}
```

---

### 11.2 Reanimated 3 — animações de alta performance

**React Native Reanimated** é a biblioteca de animações mais poderosa do ecossistema. Ela executa animações diretamente na thread da UI — sem passar pelo JavaScript — resultando em 60/120fps suaves mesmo em dispositivos lentos.

```bash
npm install react-native-reanimated
```

```tsx
import Animated, {
  useSharedValue,
  useAnimatedStyle,
  withSpring,
  withTiming,
  runOnJS,
} from 'react-native-reanimated';

function CartaoAnimado() {
  const translateX = useSharedValue(0);
  const rotacao = useSharedValue(0);

  // useAnimatedStyle cria um estilo reativo que roda na thread da UI
  const estilo = useAnimatedStyle(() => ({
    transform: [
      { translateX: translateX.value },
      { rotate: `${rotacao.value}deg` },
    ],
  }));

  function animar() {
    translateX.value = withSpring(100);    // spring physics
    rotacao.value = withTiming(15, { duration: 300 });
  }

  function resetar() {
    translateX.value = withSpring(0);
    rotacao.value = withTiming(0);
  }

  return (
    <Animated.View style={[styles.cartao, estilo]}>
      <TouchableOpacity onPress={animar}>
        <Text>Animar</Text>
      </TouchableOpacity>
      <TouchableOpacity onPress={resetar}>
        <Text>Resetar</Text>
      </TouchableOpacity>
    </Animated.View>
  );
}
```

---

### 11.3 Lottie — animações vetoriais

**Lottie** permite usar animações criadas no Adobe After Effects diretamente no React Native — arquivos JSON leves e de alta qualidade.

```bash
npm install lottie-react-native
```

```tsx
import LottieView from 'lottie-react-native';

function AnimacaoCarregamento() {
  return (
    <LottieView
      source={require('./assets/loading.json')}  // arquivo da animação
      autoPlay
      loop
      style={{ width: 200, height: 200 }}
    />
  );
}
```

**Sites para baixar animações Lottie:**
- [lottiefiles.com](https://lottiefiles.com) — maior repositório gratuito
- [lordicon.com](https://lordicon.com) — ícones animados

---

## Parte 12 — Armazenamento Local

---

### 12.1 AsyncStorage — equivalente ao DataStore

```bash
npm install @react-native-async-storage/async-storage
```

```tsx
import AsyncStorage from '@react-native-async-storage/async-storage';

// Salvar
await AsyncStorage.setItem('tema', 'escuro');
await AsyncStorage.setItem('usuario', JSON.stringify({ id: 1, nome: 'Ana' }));

// Ler
const tema = await AsyncStorage.getItem('tema');              // 'escuro' ou null
const usuarioStr = await AsyncStorage.getItem('usuario');
const usuario = usuarioStr ? JSON.parse(usuarioStr) : null;

// Remover
await AsyncStorage.removeItem('tema');

// Limpar tudo
await AsyncStorage.clear();

// Múltiplas operações de uma vez (mais eficiente)
await AsyncStorage.multiSet([
  ['chave1', 'valor1'],
  ['chave2', 'valor2'],
]);
```

> ⚠️ AsyncStorage é assíncrono mas **não é criptografado**. Para dados sensíveis (tokens, senhas), use `react-native-keychain` ou `expo-secure-store`.

---

### 12.2 MMKV — armazenamento de alta performance

**MMKV** é até 30x mais rápido que AsyncStorage. É a escolha para apps que precisam ler/escrever estado frequentemente.

```bash
npm install react-native-mmkv
```

```tsx
import { MMKV } from 'react-native-mmkv';

const storage = new MMKV();

// MMKV é SÍNCRONO — não precisa de await!
storage.set('token', 'meu-jwt-token');
storage.set('contador', 42);
storage.set('ativo', true);

const token = storage.getString('token');   // string | undefined
const contador = storage.getNumber('contador');
const ativo = storage.getBoolean('ativo');

storage.delete('token');
```

---

### 12.3 WatermelonDB — banco de dados relacional local

Para apps com muitos dados relacionais (similar ao Room do Android):

```bash
npm install @nozbe/watermelondb
```

WatermelonDB é o equivalente mobile do Room: modelos, relacionamentos, migrações e queries reativas. É a escolha para apps offline-first com sincronização.

---

## Parte 13 — Ferramentas e Ecossistema

---

### 13.1 Ferramentas essenciais do dia a dia

| Ferramenta | O que é | Por que usar |
|---|---|---|
| **Flipper** | Debugger desktop para React Native | Inspecionar rede, estado, logs, layout |
| **React Native Debugger** | IDE de debugging standalone | Redux DevTools + React DevTools integrados |
| **Reactotron** | Ferramenta de debug customizável | Inspecionar estado, requisições, logs |
| **Expo Go** | App para testar sem compilar | Desenvolvimento rápido sem emulador |
| **Android Studio** | IDE oficial Android | Emulador Android, inspetor de layout |
| **Xcode** | IDE oficial Apple | Simulador iOS, Instruments |

---

### 13.2 Bibliotecas essenciais que todo projeto usa

```bash
# Navegação
npm install @react-navigation/native
npm install @react-navigation/native-stack
npm install @react-navigation/bottom-tabs
npm install react-native-screens react-native-safe-area-context

# Requisições HTTP
npm install axios
npm install @tanstack/react-query

# Estado global
npm install zustand

# Armazenamento
npm install @react-native-async-storage/async-storage
npm install react-native-mmkv

# Imagens
npm install expo-image  # (Expo) ou
npm install react-native-fast-image  # (React Native CLI)

# Animações
npm install react-native-reanimated
npm install react-native-gesture-handler
npm install lottie-react-native

# Formulários
npm install react-hook-form
npm install zod  # validação de schemas

# Ícones
npm install @expo/vector-icons  # (Expo) ou
npm install react-native-vector-icons

# UI
npm install react-native-paper  # Material Design
npm install nativewind           # Tailwind CSS

# Utilidades
npm install date-fns              # manipulação de datas
npm install react-native-uuid     # geração de UUIDs
```

---

### 13.3 React Hook Form + Zod — formulários com validação

```tsx
import { useForm, Controller } from 'react-hook-form';
import { z } from 'zod';
import { zodResolver } from '@hookform/resolvers/zod';

// Schema de validação com Zod
const esquemaCadastro = z.object({
  nome: z.string().min(3, 'Nome deve ter pelo menos 3 caracteres'),
  email: z.string().email('E-mail inválido'),
  senha: z.string().min(8, 'Senha deve ter pelo menos 8 caracteres'),
  confirmarSenha: z.string(),
}).refine(data => data.senha === data.confirmarSenha, {
  message: 'As senhas não coincidem',
  path: ['confirmarSenha'],
});

type FormCadastro = z.infer<typeof esquemaCadastro>;

function FormularioCadastro() {
  const {
    control,
    handleSubmit,
    formState: { errors, isSubmitting },
  } = useForm<FormCadastro>({
    resolver: zodResolver(esquemaCadastro),
    defaultValues: { nome: '', email: '', senha: '', confirmarSenha: '' },
  });

  async function onSubmit(dados: FormCadastro) {
    await api.post('/cadastro', dados);
    navigation.navigate('Home');
  }

  return (
    <View style={{ padding: 16, gap: 12 }}>
      <Controller
        control={control}
        name="nome"
        render={({ field: { onChange, value } }) => (
          <View>
            <TextInput
              value={value}
              onChangeText={onChange}
              placeholder="Nome completo"
              style={[styles.input, errors.nome && styles.inputErro]}
            />
            {errors.nome && (
              <Text style={styles.textoErro}>{errors.nome.message}</Text>
            )}
          </View>
        )}
      />

      {/* ... outros campos */}

      <TouchableOpacity
        onPress={handleSubmit(onSubmit)}
        disabled={isSubmitting}
        style={styles.botao}
      >
        <Text>{isSubmitting ? 'Cadastrando...' : 'Cadastrar'}</Text>
      </TouchableOpacity>
    </View>
  );
}
```

---

### 13.4 TypeScript no React Native — configuração e boas práticas

TypeScript é fortemente recomendado em projetos React Native. O `create-expo-app` já cria projetos TypeScript por padrão.

```tsx
// Tipagem de componentes
interface CardProdutoProps {
  produto: {
    id: number;
    nome: string;
    preco: number;
    imagem: string;
    disponivel: boolean;
  };
  onPress: (id: number) => void;
  destaque?: boolean;  // opcional
}

function CardProduto({ produto, onPress, destaque = false }: CardProdutoProps) {
  return (
    <TouchableOpacity
      onPress={() => onPress(produto.id)}
      style={[styles.card, destaque && styles.cardDestaque]}
    >
      <Image source={{ uri: produto.imagem }} style={styles.imagem} />
      <Text style={styles.nome}>{produto.nome}</Text>
      <Text style={styles.preco}>R$ {produto.preco.toFixed(2)}</Text>
      {!produto.disponivel && (
        <Text style={styles.indisponivel}>Indisponível</Text>
      )}
    </TouchableOpacity>
  );
}
```

---

## Parte 14 — Arquitetura e Boas Práticas

---

### 14.1 Estrutura de projeto escalável

```
src/
├── assets/           → imagens, fontes, animações Lottie
├── components/       → componentes reutilizáveis entre telas
│   ├── ui/           → componentes genéricos (Button, Card, Input)
│   └── domain/       → componentes específicos do domínio (CardProduto)
├── screens/          → telas do app (uma pasta por tela)
│   ├── Home/
│   │   ├── index.tsx           → componente da tela
│   │   ├── HomeViewModel.ts    → lógica da tela (hooks, estado)
│   │   └── Home.styles.ts      → estilos
│   └── Produtos/
├── navigation/       → toda a configuração de rotas
├── services/         → comunicação com APIs
│   ├── api.ts        → instância do Axios
│   └── produtos.ts   → funções de cada endpoint
├── stores/           → estado global (Zustand)
├── hooks/            → custom hooks reutilizáveis
├── utils/            → funções utilitárias puras
├── types/            → tipos TypeScript compartilhados
└── constants/        → constantes do app (cores, tamanhos, rotas)
```

---

### 14.2 O padrão de separação entre UI e lógica

Assim como no MVVM do Android, no React Native é uma boa prática separar a lógica da UI:

```tsx
// ── HOOK DE LÓGICA (equivalente ao ViewModel) ────────────────────────
// hooks/useTelaProdutos.ts
export function useTelaProdutos() {
  const { data: produtos, isLoading, isError, refetch } = useQuery({
    queryKey: ['produtos'],
    queryFn: () => produtosService.listar(),
  });

  const { mutate: adicionarAoCarrinho } = useMutation({
    mutationFn: carrinhoService.adicionar,
  });

  return {
    produtos: produtos ?? [],
    isLoading,
    isError,
    refetch,
    adicionarAoCarrinho,
  };
}

// ── COMPONENTE DE UI (equivalente ao Composable stateless) ─────────────
// screens/Produtos/index.tsx
function TelaProdutos() {
  const { produtos, isLoading, isError, refetch, adicionarAoCarrinho } =
    useTelaProdutos();

  if (isLoading) return <LoadingScreen />;
  if (isError) return <ErrorScreen onRetry={refetch} />;

  return (
    <FlatList
      data={produtos}
      keyExtractor={p => p.id.toString()}
      renderItem={({ item }) => (
        <CardProduto
          produto={item}
          onAdicionarCarrinho={() => adicionarAoCarrinho(item.id)}
        />
      )}
      refreshing={isLoading}
      onRefresh={refetch}
    />
  );
}
```

---

## Parte 15 — Funcionalidades Nativas

---

### 15.1 Câmera e galeria — expo-image-picker

```bash
npx expo install expo-image-picker
```

```tsx
import * as ImagePicker from 'expo-image-picker';

function SeletorImagem() {
  const [imagem, setImagem] = useState<string | null>(null);

  async function selecionarDaGaleria() {
    // Solicita permissão
    const { status } = await ImagePicker.requestMediaLibraryPermissionsAsync();
    if (status !== 'granted') {
      alert('Permissão necessária para acessar a galeria');
      return;
    }

    const resultado = await ImagePicker.launchImageLibraryAsync({
      mediaTypes: ImagePicker.MediaTypeOptions.Images,
      allowsEditing: true,
      aspect: [1, 1],       // recorte quadrado
      quality: 0.8,          // 80% de qualidade
    });

    if (!resultado.canceled) {
      setImagem(resultado.assets[0].uri);
    }
  }

  async function tirarFoto() {
    const { status } = await ImagePicker.requestCameraPermissionsAsync();
    if (status !== 'granted') return;

    const resultado = await ImagePicker.launchCameraAsync({
      allowsEditing: true,
      quality: 0.8,
    });

    if (!resultado.canceled) {
      setImagem(resultado.assets[0].uri);
    }
  }

  return (
    <View>
      {imagem && <Image source={{ uri: imagem }} style={{ width: 200, height: 200 }} />}
      <TouchableOpacity onPress={selecionarDaGaleria}>
        <Text>Galeria</Text>
      </TouchableOpacity>
      <TouchableOpacity onPress={tirarFoto}>
        <Text>Câmera</Text>
      </TouchableOpacity>
    </View>
  );
}
```

---

### 15.2 Localização — expo-location

```bash
npx expo install expo-location
```

```tsx
import * as Location from 'expo-location';

async function obterLocalizacao() {
  const { status } = await Location.requestForegroundPermissionsAsync();
  if (status !== 'granted') {
    alert('Permissão de localização negada');
    return;
  }

  const localizacao = await Location.getCurrentPositionAsync({
    accuracy: Location.Accuracy.High,
  });

  console.log('Latitude:', localizacao.coords.latitude);
  console.log('Longitude:', localizacao.coords.longitude);
}
```

---

### 15.3 Notificações Push — expo-notifications

```bash
npx expo install expo-notifications expo-device
```

```tsx
import * as Notifications from 'expo-notifications';
import * as Device from 'expo-device';

async function registrarNotificacoes() {
  if (!Device.isDevice) {
    alert('Notificações push precisam de um dispositivo físico');
    return;
  }

  const { status } = await Notifications.requestPermissionsAsync();
  if (status !== 'granted') return;

  // Token do dispositivo para enviar notificações pelo servidor
  const token = await Notifications.getExpoPushTokenAsync();
  console.log('Push Token:', token.data);
  // Envie este token para seu servidor
}
```

---

## Parte 16 — Resumo do Ecossistema

---

### 16.1 O mapa completo das ferramentas

```
REACT NATIVE APP
│
├── SCAFFOLDING
│   ├── create-expo-app     → projeto Expo (recomendado para iniciantes)
│   └── react-native-cli    → projeto nativo completo
│
├── LINGUAGEM
│   ├── JavaScript          → funciona, mas não recomendado
│   └── TypeScript ✓        → padrão da indústria
│
├── UI E COMPONENTES
│   ├── react-native-paper  → Material Design 3
│   ├── gluestack-ui        → design system moderno
│   ├── nativewind          → Tailwind CSS
│   └── @expo/vector-icons  → ícones
│
├── NAVEGAÇÃO
│   └── react-navigation    → padrão universal
│       ├── native-stack
│       ├── bottom-tabs
│       └── drawer
│
├── ESTADO
│   ├── useState/useReducer → estado local
│   ├── Context API         → estado global simples
│   └── Zustand ✓           → estado global moderno
│
├── REQUISIÇÕES HTTP
│   ├── Axios               → cliente HTTP
│   └── TanStack Query ✓    → cache + loading + error automáticos
│
├── FORMULÁRIOS
│   ├── react-hook-form ✓   → performance e DX
│   └── zod                 → validação de schemas
│
├── ARMAZENAMENTO
│   ├── AsyncStorage        → chave-valor assíncrono
│   ├── MMKV                → chave-valor síncrono e rápido
│   └── WatermelonDB        → banco relacional offline
│
├── ANIMAÇÕES
│   ├── Reanimated 3 ✓      → animações nativas 60fps
│   ├── Lottie              → animações vetoriais After Effects
│   └── Animated API        → animações simples nativas
│
└── TESTES
    ├── Jest                → testes unitários
    ├── React Native Testing Library → testes de componentes
    └── Detox               → testes E2E em dispositivos reais
```

---

### 16.2 Tabela de resumo geral

| Conceito | Resumo |
|---|---|
| **React Native** | Framework da Meta para apps móveis nativos com JavaScript/React |
| **Expo** | Plataforma sobre React Native que abstrai configuração nativa |
| **JSX** | Sintaxe que mistura JavaScript e XML para descrever UI |
| **Componente** | Função JavaScript que retorna elementos de UI |
| **useState** | Hook para estado local — equivalente ao `mutableStateOf` |
| **useEffect** | Hook para efeitos colaterais — equivalente ao `init` do ViewModel |
| **FlatList** | Lista virtualizada — equivalente ao `LazyColumn` do Compose |
| **StyleSheet** | Sistema de estilos baseado em objetos JS (não CSS) |
| **Flexbox** | Sistema de layout — `flexDirection: 'column'` é o padrão |
| **React Navigation** | Biblioteca de navegação padrão da comunidade |
| **Zustand** | Gerenciamento de estado global moderno e simples |
| **TanStack Query** | Cache + estado de requisições HTTP automático |
| **Reanimated 3** | Animações nativas de 60fps na thread da UI |
| **AsyncStorage/MMKV** | Persistência de dados chave-valor no dispositivo |
| **React Hook Form** | Gerenciamento de formulários com alta performance |
