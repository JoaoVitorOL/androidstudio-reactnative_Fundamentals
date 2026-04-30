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
    color: '#007AFF',
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
  container: { flex: 1, padding: 16 },
  linha: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    gap: 8,
  },
  coluna: {
    flexDirection: 'column',
    justifyContent: 'space-between',
    height: 120,
  },
  caixa: { backgroundColor: '#e8f4fd', padding: 8, borderRadius: 4 },
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
  botao: { padding: 16, borderRadius: 8, alignItems: 'center', borderWidth: 2 },
  botaoAtivo: { backgroundColor: '#007AFF', borderColor: '#005DC1' },
  botaoInativo: { backgroundColor: 'white', borderColor: '#ccc' },
  textoBotao: { fontWeight: 'bold', fontSize: 16 },
  textoAtivo: { color: 'white' },
});
```

---

## Parte 5 — Estado e Hooks

---

### 5.1 O que é estado no React Native?

Estado é qualquer dado que muda ao longo do tempo e deve causar uma atualização visual quando muda — exatamente o mesmo conceito do `mutableStateOf` no Jetpack Compose.

No React Native, o estado é gerenciado com **Hooks** — funções especiais do React que permitem adicionar estado e outros recursos a componentes funcionais.

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

  useEffect(() => {
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

useEffect(() => { /* executa APENAS na montagem */ }, []);

useEffect(() => { /* executa quando 'userId' ou 'filtro' mudam */ }, [userId, filtro]);
```

---

### 5.5 useMemo e useCallback — otimização de performance

```tsx
import { useMemo, useCallback } from 'react';

function ListaFiltrada({ itens, termoBusca }) {
  // useMemo: recalcula apenas quando 'itens' ou 'termoBusca' mudam
  const itensFiltrados = useMemo(() => {
    return itens.filter(item =>
      item.nome.toLowerCase().includes(termoBusca.toLowerCase())
    );
  }, [itens, termoBusca]);

  // useCallback: memoriza a função — evita recriá-la a cada render
  const handlePress = useCallback((id) => {
    console.log('Item selecionado:', id);
  }, []);

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
import { FlatList, View, Text, StyleSheet } from 'react-native';

interface Tarefa {
  id: string;
  titulo: string;
  concluida: boolean;
}

const TAREFAS: Tarefa[] = [
  { id: '1', titulo: 'Comprar leite', concluida: false },
  { id: '2', titulo: 'Fazer exercício', concluida: true },
];

function ListaTarefas() {
  return (
    <FlatList
      data={TAREFAS}
      keyExtractor={(item) => item.id}
      renderItem={({ item, index }) => (
        <View style={styles.item}>
          <Text style={[styles.titulo, item.concluida && styles.concluido]}>
            {index + 1}. {item.titulo}
          </Text>
        </View>
      )}
      windowSize={10}
      removeClippedSubviews={true}
      ListHeaderComponent={<Text style={styles.cabecalho}>Minhas Tarefas</Text>}
      ListFooterComponent={<Text style={styles.rodape}>{TAREFAS.length} tarefas no total</Text>}
      ListEmptyComponent={<Text style={styles.vazio}>Nenhuma tarefa ainda!</Text>}
      ItemSeparatorComponent={() => <View style={styles.separador} />}
      contentContainerStyle={{ padding: 16 }}
      onEndReached={() => carregarMaisItens()}
      onEndReachedThreshold={0.3}
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
  { titulo: 'Frutas', data: ['Maçã', 'Banana', 'Laranja'] },
  { titulo: 'Verduras', data: ['Alface', 'Brócolis', 'Cenoura'] },
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
npm install @react-navigation/native
npm install react-native-screens react-native-safe-area-context
npm install @react-navigation/native-stack
npm install @react-navigation/bottom-tabs
```

---

### 7.2 Stack Navigator — navegação em pilha

```tsx
// navigation/AppNavigator.tsx
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';

type RootStackParams = {
  TelaInicial: undefined;
  TelaDetalhe: { itemId: number; titulo: string };
  TelaPerfil: undefined;
};

const Stack = createNativeStackNavigator<RootStackParams>();

export function AppNavigator() {
  return (
    <NavigationContainer>
      <Stack.Navigator
        initialRouteName="TelaInicial"
        screenOptions={{
          headerStyle: { backgroundColor: '#007AFF' },
          headerTintColor: 'white',
          headerTitleStyle: { fontWeight: 'bold' },
        }}
      >
        <Stack.Screen name="TelaInicial" component={TelaInicial} options={{ title: 'Início' }} />
        <Stack.Screen
          name="TelaDetalhe"
          component={TelaDetalhe}
          options={({ route }) => ({ title: route.params.titulo })}
        />
        <Stack.Screen name="TelaPerfil" component={TelaPerfil} options={{ title: 'Meu Perfil' }} />
      </Stack.Navigator>
    </NavigationContainer>
  );
}
```

---

### 7.3 Navegar entre telas e passar parâmetros

```tsx
import { NativeStackScreenProps } from '@react-navigation/native-stack';

type Props = NativeStackScreenProps<RootStackParams, 'TelaInicial'>;

function TelaInicial({ navigation }: Props) {
  return (
    <View style={{ padding: 16 }}>
      <TouchableOpacity
        onPress={() =>
          navigation.navigate('TelaDetalhe', { itemId: 42, titulo: 'Produto Incrível' })
        }
      >
        <Text>Ver Detalhe</Text>
      </TouchableOpacity>
    </View>
  );
}

type DetalheProps = NativeStackScreenProps<RootStackParams, 'TelaDetalhe'>;

function TelaDetalhe({ navigation, route }: DetalheProps) {
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
import { Ionicons } from '@expo/vector-icons';

const Tab = createBottomTabNavigator<TabParams>();

function TabNavigator() {
  return (
    <Tab.Navigator
      screenOptions={({ route }) => ({
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
function AppNavigator() {
  return (
    <NavigationContainer>
      <Tab.Navigator>
        <Tab.Screen name="InicioTab" component={InicioStackNavigator} />
        <Tab.Screen name="PerfilTab" component={PerfilStack} />
      </Tab.Navigator>
    </NavigationContainer>
  );
}

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

interface Usuario { id: number; nome: string; email: string; }

interface AuthContextType {
  usuario: Usuario | null;
  fazerLogin: (email: string, senha: string) => Promise<void>;
  fazerLogout: () => void;
  carregando: boolean;
}

const AuthContext = createContext<AuthContextType | null>(null);

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

  function fazerLogout() { setUsuario(null); }

  return (
    <AuthContext.Provider value={{ usuario, fazerLogin, fazerLogout, carregando }}>
      {children}
    </AuthContext.Provider>
  );
}

export function useAuth() {
  const contexto = useContext(AuthContext);
  if (!contexto) throw new Error('useAuth deve ser usado dentro de AuthProvider');
  return contexto;
}
```

---

### 8.3 Zustand — gerenciamento de estado leve e moderno

```bash
npm install zustand
```

```tsx
// stores/useCarrinhoStore.ts
import { create } from 'zustand';

interface Produto { id: number; nome: string; preco: number; quantidade: number; }

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
async function buscarUsuarios(): Promise<Usuario[]> {
  const resposta = await fetch('https://api.exemplo.com/usuarios', {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`,
    },
  });

  if (!resposta.ok) throw new Error(`Erro HTTP: ${resposta.status}`);
  return resposta.json();
}

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
      await AsyncStorage.removeItem('token');
      navigation.navigate('Login');
    }
    return Promise.reject(erro);
  }
);
```

---

### 9.3 TanStack Query (React Query) — o padrão moderno

```bash
npm install @tanstack/react-query
```

```tsx
import { QueryClient, QueryClientProvider, useQuery, useMutation, useQueryClient } from '@tanstack/react-query';

const queryClient = new QueryClient({
  defaultOptions: {
    queries: { staleTime: 5 * 60 * 1000, retry: 2 },
  },
});

export default function App() {
  return (
    <QueryClientProvider client={queryClient}>
      <AppNavigator />
    </QueryClientProvider>
  );
}

function TelaProdutos() {
  const { data: produtos, isLoading, isError, error, refetch } = useQuery({
    queryKey: ['produtos'],
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

function FormularioTarefa() {
  const queryClient = useQueryClient();

  const criarTarefaMutation = useMutation({
    mutationFn: (novaTarefa: { titulo: string }) => api.post('/tarefas', novaTarefa),
    onSuccess: () => {
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
npm install react-native-paper react-native-vector-icons
```

```tsx
import { Provider as PaperProvider, Button, TextInput, Card, Appbar } from 'react-native-paper';

export default function App() {
  return (
    <PaperProvider>
      <AppNavigator />
    </PaperProvider>
  );
}

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
          <TextInput label="Nome" value={nome} onChangeText={setNome} mode="outlined" />
          <Button mode="contained" onPress={() => salvar()} style={{ marginTop: 16 }}>
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

```bash
npm install nativewind
npm install --save-dev tailwindcss
npx tailwindcss init
```

```tsx
function CartaoPerfil({ nome, cargo, avatar }) {
  return (
    <View className="flex-row items-center p-4 bg-white rounded-xl shadow-md m-4">
      <Image source={{ uri: avatar }} className="w-14 h-14 rounded-full" />
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

### 10.4 Ícones

```bash
# Expo (pré-instalado)  →  @expo/vector-icons
# React Native CLI      →  npm install react-native-vector-icons
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

| Família | Quantidade | Estilo |
|---|---|---|
| `Ionicons` | ~1300 | iOS / Android |
| `MaterialIcons` | ~1000 | Material Design |
| `FontAwesome5` | ~1500 | Web clássico |
| `Feather` | ~280 | Minimalista |
| `AntDesign` | ~298 | Ant Design |

---

## Parte 11 — Animações

---

### 11.1 Animated API — animações nativas

```tsx
import { Animated, TouchableOpacity, View, Text } from 'react-native';
import { useRef } from 'react';

function BotaoAnimado() {
  const escala = useRef(new Animated.Value(1)).current;
  const opacidade = useRef(new Animated.Value(1)).current;

  function aoPresionar() {
    Animated.parallel([
      Animated.spring(escala, { toValue: 0.95, useNativeDriver: true }),
      Animated.timing(opacidade, { toValue: 0.7, duration: 100, useNativeDriver: true }),
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

```bash
npm install react-native-reanimated
```

```tsx
import Animated, { useSharedValue, useAnimatedStyle, withSpring, withTiming } from 'react-native-reanimated';

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

  return (
    <Animated.View style={[styles.cartao, estilo]}>
      <TouchableOpacity onPress={() => { translateX.value = withSpring(100); rotacao.value = withTiming(15); }}>
        <Text>Animar</Text>
      </TouchableOpacity>
      <TouchableOpacity onPress={() => { translateX.value = withSpring(0); rotacao.value = withTiming(0); }}>
        <Text>Resetar</Text>
      </TouchableOpacity>
    </Animated.View>
  );
}
```

---

### 11.3 Lottie — animações vetoriais

```bash
npm install lottie-react-native
```

```tsx
import LottieView from 'lottie-react-native';

function AnimacaoCarregamento() {
  return (
    <LottieView
      source={require('./assets/loading.json')}
      autoPlay
      loop
      style={{ width: 200, height: 200 }}
    />
  );
}
```

Sites para baixar animações: [lottiefiles.com](https://lottiefiles.com) e [lordicon.com](https://lordicon.com).

---

## Parte 12 — Armazenamento Local

---

### 12.1 AsyncStorage

```bash
npm install @react-native-async-storage/async-storage
```

```tsx
import AsyncStorage from '@react-native-async-storage/async-storage';

await AsyncStorage.setItem('tema', 'escuro');
await AsyncStorage.setItem('usuario', JSON.stringify({ id: 1, nome: 'Ana' }));

const tema = await AsyncStorage.getItem('tema');
const usuarioStr = await AsyncStorage.getItem('usuario');
const usuario = usuarioStr ? JSON.parse(usuarioStr) : null;

await AsyncStorage.removeItem('tema');
await AsyncStorage.clear();
```

> ⚠️ AsyncStorage **não é criptografado**. Para dados sensíveis use `react-native-keychain` ou `expo-secure-store`.

---

### 12.2 MMKV — armazenamento de alta performance

```bash
npm install react-native-mmkv
```

```tsx
import { MMKV } from 'react-native-mmkv';

const storage = new MMKV();

// MMKV é SÍNCRONO — não precisa de await
storage.set('token', 'meu-jwt-token');
storage.set('contador', 42);

const token = storage.getString('token');
const contador = storage.getNumber('contador');

storage.delete('token');
```

---

### 12.3 WatermelonDB — banco de dados relacional local

```bash
npm install @nozbe/watermelondb
```

WatermelonDB é o equivalente mobile do Room: modelos, relacionamentos, migrações e queries reativas. É a escolha para apps offline-first com sincronização.

---

## Parte 13 — TypeScript no React Native

TypeScript é o padrão do ecossistema React Native. O `create-expo-app` já gera projetos TypeScript por padrão. Esta parte cobre os padrões de tipagem específicos para React Native.

---

### 13.1 Tipagem de componentes e props

A forma mais clara de documentar o contrato de um componente é declarar uma `interface` para suas props.

```tsx
// Interface de props — documenta o que o componente aceita
interface CardProdutoProps {
  produto: {
    id: number;
    nome: string;
    preco: number;
    imagem: string;
    disponivel: boolean;
  };
  onPress: (id: number) => void;  // callback tipado
  destaque?: boolean;              // prop opcional — o '?' indica que tem valor padrão
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

### 13.2 Utility Types — transformando tipos existentes

O TypeScript fornece utility types que evitam duplicação de código ao derivar novos tipos de tipos já existentes.

```tsx
interface Produto {
  id: number;
  nome: string;
  preco: number;
  categoria: string;
  estoque: number;
}

// Partial<T> — torna todos os campos opcionais
// Uso: payload de atualização parcial (PATCH)
type AtualizacaoProduto = Partial<Produto>;
// { id?: number; nome?: string; preco?: number; ... }

// Pick<T, K> — seleciona apenas os campos listados
// Uso: exibir apenas o resumo em um card de lista
type ResumoProduto = Pick<Produto, 'id' | 'nome' | 'preco'>;
// { id: number; nome: string; preco: number }

// Omit<T, K> — remove os campos listados
// Uso: payload de criação (o id é gerado pelo servidor)
type NovoProduto = Omit<Produto, 'id'>;
// { nome: string; preco: number; categoria: string; estoque: number }

// Required<T> — torna todos os campos obrigatórios (inverso de Partial)
type ProdutoCompleto = Required<Produto>;

// Readonly<T> — impede modificação dos campos após criação
type ProdutoImutavel = Readonly<Produto>;
```

---

### 13.3 Tipagem de navegação com React Navigation

Tipar as rotas elimina erros de digitação nos nomes de telas e nos parâmetros passados.

```tsx
// navigation/types.ts — definição central de todas as rotas

import { NativeStackScreenProps } from '@react-navigation/native-stack';
import { BottomTabScreenProps } from '@react-navigation/bottom-tabs';

// Stack principal
export type RootStackParams = {
  Login: undefined;                            // sem parâmetros
  Home: undefined;
  Detalhe: { produtoId: number; titulo: string }; // com parâmetros obrigatórios
  Editar: { produtoId: number };
};

// Abas inferiores
export type TabParams = {
  Inicio: undefined;
  Busca: { termoPadrao?: string };             // parâmetro opcional
  Perfil: undefined;
};

// Tipos prontos para usar em cada tela
export type LoginScreenProps   = NativeStackScreenProps<RootStackParams, 'Login'>;
export type DetalheScreenProps = NativeStackScreenProps<RootStackParams, 'Detalhe'>;
export type BuscaTabProps      = BottomTabScreenProps<TabParams, 'Busca'>;
```

```tsx
// screens/Detalhe.tsx — usando os tipos definidos
import { DetalheScreenProps } from '../navigation/types';

function TelaDetalhe({ navigation, route }: DetalheScreenProps) {
  // TypeScript sabe exatamente quais campos existem em route.params
  const { produtoId, titulo } = route.params;
  // produtoId: number — TypeScript valida o tipo
  // titulo: string    — TypeScript valida o tipo

  return (
    <View>
      <Text>{titulo}</Text>
      <TouchableOpacity onPress={() => navigation.navigate('Editar', { produtoId })}>
        <Text>Editar</Text>
      </TouchableOpacity>
    </View>
  );
}
```

---

### 13.4 Tipagem de estado e hooks

```tsx
// Estado com tipo explícito — útil quando o tipo inicial é null
const [usuario, setUsuario] = useState<Usuario | null>(null);
const [produtos, setProdutos] = useState<Produto[]>([]);
const [carregando, setCarregando] = useState<boolean>(false);

// useRef tipado
const inputRef = useRef<TextInput>(null);
const timerRef = useRef<ReturnType<typeof setTimeout>>();

// useReducer para estado complexo
type Estado = {
  itens: Produto[];
  carregando: boolean;
  erro: string | null;
};

type Acao =
  | { type: 'CARREGANDO' }
  | { type: 'SUCESSO'; payload: Produto[] }
  | { type: 'ERRO'; payload: string };

function reducer(estado: Estado, acao: Acao): Estado {
  switch (acao.type) {
    case 'CARREGANDO': return { ...estado, carregando: true, erro: null };
    case 'SUCESSO':    return { itens: acao.payload, carregando: false, erro: null };
    case 'ERRO':       return { ...estado, carregando: false, erro: acao.payload };
  }
}

const [estado, dispatch] = useReducer(reducer, { itens: [], carregando: false, erro: null });
```

---

### 13.5 Configuração do tsconfig.json para React Native

```json
{
  "extends": "@tsconfig/react-native/tsconfig.json",
  "compilerOptions": {
    "strict": true,
    "baseUrl": ".",
    "paths": {
      "@components/*": ["src/components/*"],
      "@screens/*":    ["src/screens/*"],
      "@hooks/*":      ["src/hooks/*"],
      "@services/*":   ["src/services/*"],
      "@stores/*":     ["src/stores/*"],
      "@types/*":      ["src/types/*"]
    }
  }
}
```

O campo `paths` habilita **path aliases** — substitui `'../../../components/Button'` por `'@components/Button'`, eliminando imports com múltiplos `../`.

---

## Parte 14 — Performance e Otimização

---

### 14.1 React.memo — evitando re-renders desnecessários

Por padrão, quando um componente pai re-renderiza, **todos os filhos re-renderizam também**, mesmo que suas props não tenham mudado. `React.memo` memoriza o componente e só o re-renderiza se suas props mudarem.

```tsx
// Sem React.memo — re-renderiza sempre que o pai re-renderiza
function ItemLista({ titulo, onPress }: { titulo: string; onPress: () => void }) {
  console.log('Renderizando:', titulo);
  return (
    <TouchableOpacity onPress={onPress}>
      <Text>{titulo}</Text>
    </TouchableOpacity>
  );
}

// Com React.memo — só re-renderiza se 'titulo' ou 'onPress' mudarem
const ItemListaMemo = React.memo(function ItemLista({
  titulo,
  onPress,
}: {
  titulo: string;
  onPress: () => void;
}) {
  console.log('Renderizando:', titulo);
  return (
    <TouchableOpacity onPress={onPress}>
      <Text>{titulo}</Text>
    </TouchableOpacity>
  );
});
```

---

### 14.2 O problema clássico com FlatList e funções inline

Este é o erro de performance mais comum em React Native. Quando `renderItem` recebe uma função inline (criada dentro do render), ela é uma **nova referência a cada re-render** — mesmo que o código seja idêntico. O `React.memo` no item não tem efeito porque a prop `onPress` sempre parece diferente.

```tsx
// ❌ Problema — função inline cria nova referência a cada render
function ListaTarefas({ tarefas, onRemover }) {
  return (
    <FlatList
      data={tarefas}
      keyExtractor={t => t.id}
      renderItem={({ item }) => (
        // Nova função criada a cada render de ListaTarefas
        // React.memo no ItemTarefa não consegue otimizar
        <ItemTarefa tarefa={item} onRemover={() => onRemover(item.id)} />
      )}
    />
  );
}

// ✅ Solução — useCallback estabiliza a referência da função
function ListaTarefas({ tarefas, onRemover }) {
  // handleRemover é a MESMA referência entre renders
  // enquanto 'onRemover' não mudar
  const handleRemover = useCallback(
    (id: string) => onRemover(id),
    [onRemover]
  );

  return (
    <FlatList
      data={tarefas}
      keyExtractor={t => t.id}
      // Agora React.memo no ItemTarefa funciona corretamente
      renderItem={({ item }) => (
        <ItemTarefa tarefa={item} onRemover={handleRemover} />
      )}
    />
  );
}
```

---

### 14.3 Hermes — o motor JavaScript do React Native

**Hermes** é o motor JavaScript desenvolvido pela Meta especificamente para React Native. É ativado por padrão desde o React Native 0.70.

O que ele muda na prática:

| Aspecto | JavaScriptCore (motor antigo) | Hermes |
|---|---|---|
| Compilação | JIT (Just-In-Time) — compila ao rodar | AOT (Ahead-Of-Time) — compila no build |
| Tempo de inicialização | Mais lento | Até 2× mais rápido |
| Consumo de memória | Maior | Menor |
| Tamanho do bundle | Maior | Menor |
| Debugging | Via Chrome DevTools | Via Hermes Inspector |

Você não precisa configurar o Hermes — ele já está ativo. O que importa entender é que o **bytecode gerado no build** é o que o dispositivo executa, não o JavaScript fonte. Isso é parte do motivo pelo qual projetos Expo e React Native CLI têm etapas de build separadas do desenvolvimento.

---

### 14.4 Flipper — profiling e debugging

Flipper é o cliente desktop de debugging oficial do React Native.

```bash
# Instalar o Flipper Desktop: https://fbflipper.com
# No projeto, já vem configurado no React Native CLI
# No Expo: requer desenvolvimento build (não funciona no Expo Go)
```

O que inspecionar no Flipper para identificar problemas de performance:

| Plugin do Flipper | O que mostra |
|---|---|
| **React DevTools** | Árvore de componentes, props, estado, re-renders |
| **Network** | Todas as requisições HTTP com headers, body e tempo |
| **Layout Inspector** | Hierarquia visual de Views, margens, tamanhos |
| **Hermes Debugger** | Profiling de CPU — identifica quais funções são lentas |
| **Crash Reporter** | Stack traces de crashes nativos |

---

### 14.5 Resumo das otimizações

| Técnica | O que resolve | Quando aplicar |
|---|---|---|
| `React.memo` | Re-renders desnecessários de componentes filhos | Componentes que recebem props estáveis |
| `useCallback` | Instabilidade de referências de funções | Funções passadas como props, especialmente em `renderItem` |
| `useMemo` | Recálculo de dados derivados a cada render | Filtragem, ordenação, transformação de listas grandes |
| `keyExtractor` estável | Reconciliação errada na FlatList | Sempre — use IDs únicos, nunca índices |
| `removeClippedSubviews` | Memória em listas muito longas | FlatLists com centenas de itens |
| `getItemLayout` | Scroll para posição específica sem medir | Listas com altura de item conhecida e fixa |

---

## Parte 15 — Testes

---

### 15.1 Estrutura de testes no React Native

O ecossistema de testes do React Native tem três camadas com responsabilidades distintas:

| Camada | Ferramenta | O que testa | Velocidade |
|---|---|---|---|
| Unitário | Jest | Funções puras, hooks, stores Zustand | Muito rápida |
| Componente | React Native Testing Library | Renderização e interação de componentes | Rápida |
| E2E | Detox | Fluxos completos no dispositivo real | Lenta |

---

### 15.2 Jest — testes unitários

Jest é pré-configurado em todos os projetos Expo e React Native CLI. Não precisa de instalação adicional.

```tsx
// utils/formatarPreco.ts
export function formatarPreco(valor: number): string {
  return `R$ ${valor.toFixed(2).replace('.', ',')}`;
}

// utils/__tests__/formatarPreco.test.ts
import { formatarPreco } from '../formatarPreco';

describe('formatarPreco', () => {
  it('formata número inteiro corretamente', () => {
    expect(formatarPreco(150)).toBe('R$ 150,00');
  });

  it('formata decimal corretamente', () => {
    expect(formatarPreco(49.9)).toBe('R$ 49,90');
  });

  it('formata zero corretamente', () => {
    expect(formatarPreco(0)).toBe('R$ 0,00');
  });
});
```

**Testando um custom hook com `renderHook`:**

```tsx
// hooks/__tests__/useContador.test.ts
import { renderHook, act } from '@testing-library/react-native';
import { useContador } from '../useContador';

describe('useContador', () => {
  it('inicia com o valor padrão', () => {
    const { result } = renderHook(() => useContador());
    expect(result.current.valor).toBe(0);
  });

  it('incrementa corretamente', () => {
    const { result } = renderHook(() => useContador());

    // act() envolve qualquer ação que muda estado
    act(() => {
      result.current.incrementar();
    });

    expect(result.current.valor).toBe(1);
  });

  it('reseta para o valor inicial', () => {
    const { result } = renderHook(() => useContador(10));

    act(() => {
      result.current.incrementar();
      result.current.resetar();
    });

    expect(result.current.valor).toBe(10);
  });
});
```

---

### 15.3 React Native Testing Library — testes de componentes

```bash
npm install --save-dev @testing-library/react-native
```

A biblioteca renderiza o componente em um ambiente simulado e permite interagir com ele como um usuário faria.

```tsx
// components/__tests__/BotaoLogin.test.tsx
import { render, fireEvent, screen } from '@testing-library/react-native';
import { BotaoLogin } from '../BotaoLogin';

describe('BotaoLogin', () => {
  it('renderiza o texto corretamente', () => {
    render(<BotaoLogin onPress={() => {}} />);

    // getByText busca um elemento pelo texto visível
    expect(screen.getByText('Entrar')).toBeTruthy();
  });

  it('chama onPress quando pressionado', () => {
    // jest.fn() cria uma função mock que registra chamadas
    const mockOnPress = jest.fn();
    render(<BotaoLogin onPress={mockOnPress} />);

    // fireEvent.press simula um toque no elemento
    fireEvent.press(screen.getByText('Entrar'));

    expect(mockOnPress).toHaveBeenCalledTimes(1);
  });

  it('fica desabilitado quando carregando', () => {
    render(<BotaoLogin onPress={() => {}} carregando={true} />);

    // getByTestId busca pelo atributo testID no componente
    const botao = screen.getByTestId('botao-login');
    expect(botao.props.accessibilityState.disabled).toBe(true);
  });
});
```

```tsx
// components/BotaoLogin.tsx — com testID para facilitar os testes
interface BotaoLoginProps {
  onPress: () => void;
  carregando?: boolean;
}

function BotaoLogin({ onPress, carregando = false }: BotaoLoginProps) {
  return (
    <TouchableOpacity
      testID="botao-login"          // identificador para os testes
      onPress={onPress}
      disabled={carregando}
      accessibilityState={{ disabled: carregando }}
    >
      {carregando ? <ActivityIndicator /> : <Text>Entrar</Text>}
    </TouchableOpacity>
  );
}
```

---

### 15.4 Testando componentes com estado e mocks de API

```tsx
// screens/__tests__/TelaLista.test.tsx
import { render, screen, waitFor } from '@testing-library/react-native';
import { TelaLista } from '../TelaLista';

// Mock do módulo de API — substitui a chamada real por dados controlados
jest.mock('../../services/api', () => ({
  api: {
    get: jest.fn(),
  },
}));

import { api } from '../../services/api';

describe('TelaLista', () => {
  it('exibe indicador de carregamento inicialmente', () => {
    // A promise nunca resolve — mantém o estado de carregando
    (api.get as jest.Mock).mockReturnValue(new Promise(() => {}));

    render(<TelaLista />);

    expect(screen.getByTestId('loading-indicator')).toBeTruthy();
  });

  it('exibe a lista após carregar os dados', async () => {
    (api.get as jest.Mock).mockResolvedValue({
      data: [
        { id: 1, titulo: 'Tarefa A' },
        { id: 2, titulo: 'Tarefa B' },
      ],
    });

    render(<TelaLista />);

    // waitFor aguarda o estado assíncrono se resolver
    await waitFor(() => {
      expect(screen.getByText('Tarefa A')).toBeTruthy();
      expect(screen.getByText('Tarefa B')).toBeTruthy();
    });
  });

  it('exibe mensagem de erro quando a API falha', async () => {
    (api.get as jest.Mock).mockRejectedValue(new Error('Sem conexão'));

    render(<TelaLista />);

    await waitFor(() => {
      expect(screen.getByText(/erro/i)).toBeTruthy();
    });
  });
});
```

---

## Parte 16 — Tratamento de Erros em Produção

---

### 16.1 Error Boundaries — capturando erros de renderização

Um **Error Boundary** é um componente de classe que captura erros JavaScript que ocorrem durante a renderização de qualquer filho. Sem ele, um erro em qualquer componente da árvore derruba o app inteiro com uma tela branca.

```tsx
// components/ErrorBoundary.tsx
import React, { Component, ReactNode } from 'react';
import { View, Text, TouchableOpacity, StyleSheet } from 'react-native';

interface Props {
  children: ReactNode;
  fallback?: ReactNode; // UI alternativa — opcional, tem padrão
}

interface State {
  temErro: boolean;
  erro: Error | null;
}

export class ErrorBoundary extends Component<Props, State> {
  state: State = { temErro: false, erro: null };

  // Chamado quando qualquer filho lança um erro durante renderização
  static getDerivedStateFromError(erro: Error): State {
    return { temErro: true, erro };
  }

  // Chamado após o erro ser capturado — ideal para logar no Sentry
  componentDidCatch(erro: Error, info: React.ErrorInfo) {
    console.error('ErrorBoundary capturou:', erro, info.componentStack);
    // Sentry.captureException(erro); // ver seção 16.2
  }

  render() {
    if (this.state.temErro) {
      // Exibe fallback customizado ou UI padrão de erro
      return this.props.fallback ?? (
        <View style={styles.container}>
          <Text style={styles.titulo}>Algo deu errado</Text>
          <Text style={styles.descricao}>{this.state.erro?.message}</Text>
          <TouchableOpacity
            style={styles.botao}
            onPress={() => this.setState({ temErro: false, erro: null })}
          >
            <Text style={styles.textoBotao}>Tentar novamente</Text>
          </TouchableOpacity>
        </View>
      );
    }

    return this.props.children;
  }
}

const styles = StyleSheet.create({
  container: { flex: 1, justifyContent: 'center', alignItems: 'center', padding: 24 },
  titulo: { fontSize: 20, fontWeight: 'bold', marginBottom: 8 },
  descricao: { color: '#666', textAlign: 'center', marginBottom: 24 },
  botao: { backgroundColor: '#007AFF', padding: 16, borderRadius: 8 },
  textoBotao: { color: 'white', fontWeight: 'bold' },
});
```

```tsx
// App.tsx — aplicando o ErrorBoundary em diferentes níveis
export default function App() {
  return (
    // Nível global — captura qualquer erro não tratado
    <ErrorBoundary>
      <AppNavigator />
    </ErrorBoundary>
  );
}

// Por tela — captura erros isolados sem derrubar toda a navegação
function TelaCarrinho() {
  return (
    <ErrorBoundary fallback={<Text>Não foi possível carregar o carrinho.</Text>}>
      <ConteudoCarrinho />
    </ErrorBoundary>
  );
}
```

---

### 16.2 Sentry — monitoramento de erros em produção

**Sentry** é a ferramenta padrão do mercado para capturar e monitorar erros em apps em produção. Sem ela, você não sabe que seus usuários estão encontrando erros.

```bash
npx expo install @sentry/react-native
# ou para React Native CLI:
npm install @sentry/react-native
npx @sentry/react-native init
```

```tsx
// App.tsx — configuração do Sentry
import * as Sentry from '@sentry/react-native';

Sentry.init({
  dsn: 'https://sua-chave@sentry.io/projeto',
  // Envia 10% das sessões como dados de performance
  tracesSampleRate: 0.1,
  // Desativa em desenvolvimento para não poluir o dashboard
  enabled: process.env.NODE_ENV === 'production',
});

export default Sentry.wrap(App); // envolve o app para capturar erros nativos também
```

```tsx
// Uso manual — capturar erros específicos com contexto
try {
  await api.post('/pedido', dadosPedido);
} catch (erro) {
  // Envia o erro com contexto adicional
  Sentry.captureException(erro, {
    tags: { tela: 'Checkout', acao: 'criar_pedido' },
    extra: { usuarioId: usuario.id, totalItens: carrinho.totalItens },
  });

  // Mostra mensagem amigável ao usuário
  Alert.alert('Erro', 'Não foi possível finalizar o pedido. Tente novamente.');
}
```

---

### 16.3 Erros não capturados — último recurso global

```tsx
// index.js — interceptar erros JavaScript não capturados por nenhum try/catch
import { ErrorUtils } from 'react-native';

const handlerOriginal = ErrorUtils.getGlobalHandler();

ErrorUtils.setGlobalHandler((erro, isFatal) => {
  // Loga o erro e repassa para o handler padrão do RN
  console.error('Erro global não capturado:', erro);
  // Sentry.captureException(erro);

  handlerOriginal(erro, isFatal);
});
```

---

## Parte 17 — Acessibilidade

---

### 17.1 Por que acessibilidade importa no mobile

Acessibilidade em apps mobile garante que usuários com deficiências visuais, motoras ou cognitivas possam usar o app com tecnologias assistivas como **TalkBack** (Android) e **VoiceOver** (iOS). Além do impacto para os usuários, apps nas lojas são avaliados por critérios de acessibilidade.

O React Native expõe as APIs de acessibilidade nativas de iOS e Android através de props padronizadas.

---

### 17.2 Props fundamentais de acessibilidade

```tsx
// accessibilityLabel — o que o leitor de tela anuncia ao usuário
// Use quando o texto visível não descreve suficientemente a ação
<TouchableOpacity
  accessibilityLabel="Adicionar produto ao carrinho"
  accessibilityHint="Toca duas vezes para adicionar"
  onPress={adicionarAoCarrinho}
>
  <Text>+</Text>
</TouchableOpacity>

// accessibilityRole — informa o tipo do elemento ao leitor de tela
// Valores: 'button', 'link', 'header', 'image', 'checkbox', 'radio', etc.
<TouchableOpacity
  accessibilityRole="button"
  accessibilityLabel="Fazer login"
  onPress={fazerLogin}
>
  <Text>Entrar</Text>
</TouchableOpacity>

// accessibilityState — estado atual do elemento
<TouchableOpacity
  accessibilityRole="checkbox"
  accessibilityState={{ checked: marcado, disabled: desabilitado }}
  onPress={alternarMarcacao}
>
  <Text>{marcado ? '✓' : '○'} Aceito os termos</Text>
</TouchableOpacity>

// accessible={true} — agrupa elementos para o leitor de tela anunciar juntos
// Sem isso, o leitor anuncia cada Text filho separadamente
<View
  accessible={true}
  accessibilityLabel="Produto: Teclado Mecânico, R$ 350,00, disponível"
>
  <Text>Teclado Mecânico</Text>
  <Text>R$ 350,00</Text>
  <Text>Em estoque</Text>
</View>
```

---

### 17.3 Imagens e acessibilidade

```tsx
// Imagem decorativa — accessibilityElementsHidden esconde do leitor de tela
<Image
  source={require('./assets/decoracao.png')}
  accessibilityElementsHidden={true}  // iOS
  importantForAccessibility="no"      // Android
/>

// Imagem informativa — descreva o conteúdo
<Image
  source={{ uri: produto.imagem }}
  accessibilityLabel={`Foto do produto: ${produto.nome}`}
  accessible={true}
/>
```

---

### 17.4 Testando acessibilidade

```tsx
// React Native Testing Library inclui queries de acessibilidade
import { render, screen } from '@testing-library/react-native';

it('botão tem label acessível correto', () => {
  render(<BotaoAdicionar produto={produtoMock} />);

  // getByRole busca por accessibilityRole
  const botao = screen.getByRole('button', { name: /adicionar/i });
  expect(botao).toBeTruthy();
});
```

---

## Parte 18 — Build e Publicação nas Lojas

---

### 18.1 Os três estados de um app Expo

Entender as diferenças entre os ambientes de execução do Expo é fundamental antes de publicar.

| Ambiente | Como rodar | O que suporta | Quando usar |
|---|---|---|---|
| **Expo Go** | App da loja + QR code | Apenas SDK do Expo | Aprendizado e prototipagem |
| **Development Build** | Build customizado no dispositivo | SDK Expo + módulos nativos custom | Desenvolvimento de produção |
| **Standalone (produção)** | APK/AAB/IPA publicado | Tudo | Publicação nas lojas |

---

### 18.2 Build com EAS (Expo Application Services)

EAS é a plataforma de build em nuvem do Expo. Você não precisa do Android Studio nem do Xcode na sua máquina — o build acontece nos servidores da Expo.

```bash
# Instalar o EAS CLI
npm install -g eas-cli

# Fazer login na conta Expo
eas login

# Configurar o projeto (cria eas.json)
eas build:configure
```

```json
// eas.json — perfis de build
{
  "build": {
    "development": {
      "developmentClient": true,    // gera um Development Build
      "distribution": "internal"    // distribuição interna (não vai para loja)
    },
    "preview": {
      "distribution": "internal",   // APK para testes internos
      "android": {
        "buildType": "apk"
      }
    },
    "production": {
      "android": {
        "buildType": "app-bundle"   // AAB — formato exigido pela Google Play
      }
    }
  }
}
```

```bash
# Build para Android (AAB para Google Play)
eas build --platform android --profile production

# Build para iOS (IPA para App Store)
eas build --platform ios --profile production

# Build para ambas as plataformas simultaneamente
eas build --platform all --profile production

# Enviar direto para as lojas após o build
eas submit --platform android
eas submit --platform ios
```

---

### 18.3 Build com React Native CLI (sem Expo)

Para projetos sem Expo, o build é feito localmente com as ferramentas nativas.

```bash
# ── ANDROID ──────────────────────────────────────────────

# 1. Gerar a keystore (certificado de assinatura) — apenas uma vez
keytool -genkey -v \
  -keystore meu-app.keystore \
  -alias meu-app \
  -keyalg RSA \
  -keysize 2048 \
  -validity 10000

# 2. Configurar a keystore em android/gradle.properties
# MYAPP_UPLOAD_STORE_FILE=meu-app.keystore
# MYAPP_UPLOAD_KEY_ALIAS=meu-app
# MYAPP_UPLOAD_STORE_PASSWORD=senha
# MYAPP_UPLOAD_KEY_PASSWORD=senha

# 3. Gerar o AAB de produção
cd android
./gradlew bundleRelease
# Saída: android/app/build/outputs/bundle/release/app-release.aab

# ── iOS (apenas macOS) ────────────────────────────────────

# 1. Abrir no Xcode
open ios/MeuApp.xcworkspace

# 2. Product → Archive
# 3. Distribute App → App Store Connect
```

---

### 18.4 Variáveis de ambiente por ambiente

```bash
# .env.development
API_URL=https://dev-api.exemplo.com
SENTRY_DSN=

# .env.production
API_URL=https://api.exemplo.com
SENTRY_DSN=https://chave@sentry.io/projeto
```

```bash
npm install react-native-dotenv
# ou para Expo:
npm install expo-constants
```

```tsx
// app.config.ts — com Expo, variáveis ficam no extra do app.config
import 'dotenv/config';

export default {
  expo: {
    extra: {
      apiUrl: process.env.API_URL,
      sentryDsn: process.env.SENTRY_DSN,
    },
  },
};

// Uso no app
import Constants from 'expo-constants';
const apiUrl = Constants.expoConfig?.extra?.apiUrl;
```

---

## Parte 19 — Ferramentas e Ecossistema

---

### 19.1 Ferramentas essenciais do dia a dia

| Ferramenta | O que é | Por que usar |
|---|---|---|
| **Flipper** | Debugger desktop para React Native | Inspecionar rede, estado, logs, layout |
| **React Native Debugger** | IDE de debugging standalone | Redux DevTools + React DevTools integrados |
| **Reactotron** | Ferramenta de debug customizável | Inspecionar estado, requisições, logs |
| **Expo Go** | App para testar sem compilar | Desenvolvimento rápido sem emulador |
| **Android Studio** | IDE oficial Android | Emulador Android, inspetor de layout |
| **Xcode** | IDE oficial Apple | Simulador iOS, Instruments |

---

### 19.2 Bibliotecas essenciais que todo projeto usa

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
npm install expo-image                  # Expo
npm install react-native-fast-image    # React Native CLI

# Animações
npm install react-native-reanimated
npm install react-native-gesture-handler
npm install lottie-react-native

# Formulários
npm install react-hook-form
npm install zod @hookform/resolvers

# Ícones
npm install @expo/vector-icons          # Expo
npm install react-native-vector-icons  # React Native CLI

# UI
npm install react-native-paper
npm install nativewind

# Monitoramento
npm install @sentry/react-native

# Testes
npm install --save-dev @testing-library/react-native
npm install --save-dev @testing-library/jest-native

# Utilidades
npm install date-fns
npm install react-native-uuid
```

---

### 19.3 React Hook Form + Zod — formulários com validação

```tsx
import { useForm, Controller } from 'react-hook-form';
import { z } from 'zod';
import { zodResolver } from '@hookform/resolvers/zod';

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
            {errors.nome && <Text style={styles.textoErro}>{errors.nome.message}</Text>}
          </View>
        )}
      />
      <TouchableOpacity onPress={handleSubmit(onSubmit)} disabled={isSubmitting} style={styles.botao}>
        <Text>{isSubmitting ? 'Cadastrando...' : 'Cadastrar'}</Text>
      </TouchableOpacity>
    </View>
  );
}
```

---

## Parte 20 — Arquitetura e Boas Práticas

---

### 20.1 Estrutura de projeto escalável

```
src/
├── assets/           → imagens, fontes, animações Lottie
├── components/
│   ├── ui/           → componentes genéricos (Button, Card, Input)
│   └── domain/       → componentes específicos do domínio (CardProduto)
├── screens/
│   ├── Home/
│   │   ├── index.tsx           → componente da tela
│   │   ├── useHomeViewModel.ts → lógica da tela (hooks, estado)
│   │   └── Home.styles.ts      → estilos
│   └── Produtos/
├── navigation/       → toda a configuração de rotas e tipos
├── services/
│   ├── api.ts        → instância do Axios
│   └── produtos.ts   → funções de cada endpoint
├── stores/           → estado global (Zustand)
├── hooks/            → custom hooks reutilizáveis
├── utils/            → funções utilitárias puras
├── types/            → tipos TypeScript compartilhados
└── constants/        → constantes do app (cores, tamanhos, rotas)
```

---

### 20.2 O padrão de separação entre UI e lógica

Assim como no MVVM do Android, no React Native é uma boa prática separar a lógica da UI em um custom hook equivalente ao ViewModel:

```tsx
// ── HOOK DE LÓGICA (equivalente ao ViewModel) ──────────────────────────
// hooks/useTelaProdutos.ts
export function useTelaProdutos() {
  const { data: produtos, isLoading, isError, refetch } = useQuery({
    queryKey: ['produtos'],
    queryFn: () => produtosService.listar(),
  });

  const { mutate: adicionarAoCarrinho } = useMutation({
    mutationFn: carrinhoService.adicionar,
  });

  return { produtos: produtos ?? [], isLoading, isError, refetch, adicionarAoCarrinho };
}

// ── COMPONENTE DE UI (equivalente ao Composable stateless) ──────────────
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

## Parte 21 — Funcionalidades Nativas

---

### 21.1 Câmera e galeria — expo-image-picker

```bash
npx expo install expo-image-picker
```

```tsx
import * as ImagePicker from 'expo-image-picker';

function SeletorImagem() {
  const [imagem, setImagem] = useState<string | null>(null);

  async function selecionarDaGaleria() {
    const { status } = await ImagePicker.requestMediaLibraryPermissionsAsync();
    if (status !== 'granted') {
      Alert.alert('Permissão necessária para acessar a galeria');
      return;
    }

    const resultado = await ImagePicker.launchImageLibraryAsync({
      mediaTypes: ImagePicker.MediaTypeOptions.Images,
      allowsEditing: true,
      aspect: [1, 1],
      quality: 0.8,
    });

    if (!resultado.canceled) setImagem(resultado.assets[0].uri);
  }

  async function tirarFoto() {
    const { status } = await ImagePicker.requestCameraPermissionsAsync();
    if (status !== 'granted') return;

    const resultado = await ImagePicker.launchCameraAsync({ allowsEditing: true, quality: 0.8 });
    if (!resultado.canceled) setImagem(resultado.assets[0].uri);
  }

  return (
    <View>
      {imagem && <Image source={{ uri: imagem }} style={{ width: 200, height: 200 }} />}
      <TouchableOpacity onPress={selecionarDaGaleria}><Text>Galeria</Text></TouchableOpacity>
      <TouchableOpacity onPress={tirarFoto}><Text>Câmera</Text></TouchableOpacity>
    </View>
  );
}
```

---

### 21.2 Localização — expo-location

```bash
npx expo install expo-location
```

```tsx
import * as Location from 'expo-location';

async function obterLocalizacao() {
  const { status } = await Location.requestForegroundPermissionsAsync();
  if (status !== 'granted') {
    Alert.alert('Permissão de localização negada');
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

### 21.3 Notificações Push — expo-notifications

```bash
npx expo install expo-notifications expo-device
```

```tsx
import * as Notifications from 'expo-notifications';
import * as Device from 'expo-device';

async function registrarNotificacoes() {
  if (!Device.isDevice) {
    Alert.alert('Notificações push precisam de um dispositivo físico');
    return;
  }

  const { status } = await Notifications.requestPermissionsAsync();
  if (status !== 'granted') return;

  const token = await Notifications.getExpoPushTokenAsync();
  console.log('Push Token:', token.data);
  // Envie este token para seu servidor para enviar notificações depois
}
```

---

## Parte 22 — Resumo do Ecossistema

---

### 22.1 O mapa completo das ferramentas

```
REACT NATIVE APP
│
├── SCAFFOLDING
│   ├── create-expo-app     → projeto Expo (recomendado para iniciantes)
│   └── react-native-cli    → projeto nativo completo
│
├── LINGUAGEM
│   └── TypeScript ✓        → padrão da indústria
│
├── UI E COMPONENTES
│   ├── react-native-paper  → Material Design 3
│   ├── gluestack-ui        → design system moderno
│   ├── nativewind          → Tailwind CSS
│   └── @expo/vector-icons  → ícones
│
├── NAVEGAÇÃO
│   └── react-navigation ✓
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
│   ├── react-hook-form ✓
│   └── zod                 → validação de schemas
│
├── ARMAZENAMENTO
│   ├── AsyncStorage        → chave-valor assíncrono
│   ├── MMKV ✓              → chave-valor síncrono e rápido
│   └── WatermelonDB        → banco relacional offline
│
├── ANIMAÇÕES
│   ├── Reanimated 3 ✓      → animações nativas 60fps
│   ├── Lottie              → animações vetoriais After Effects
│   └── Animated API        → animações simples nativas
│
├── MONITORAMENTO
│   └── Sentry ✓            → captura erros em produção
│
├── TESTES
│   ├── Jest ✓              → testes unitários
│   ├── React Native Testing Library ✓ → testes de componentes
│   └── Detox               → testes E2E em dispositivos reais
│
└── BUILD E PUBLICAÇÃO
    ├── EAS Build ✓         → build em nuvem (Expo)
    └── Gradle / Xcode      → build local (React Native CLI)
```

---

### 22.2 Tabela de resumo geral

| Conceito | Resumo |
|---|---|
| **React Native** | Framework da Meta para apps móveis nativos com JavaScript/React |
| **Expo** | Plataforma sobre React Native que abstrai configuração nativa |
| **JSX** | Sintaxe que mistura JavaScript e XML para descrever UI |
| **Componente** | Função JavaScript que retorna elementos de UI |
| **useState** | Hook para estado local — equivalente ao `mutableStateOf` |
| **useEffect** | Hook para efeitos colaterais — equivalente ao `init` do ViewModel |
| **React.memo** | Memoriza um componente — evita re-renders quando as props não mudaram |
| **useCallback** | Estabiliza referências de funções entre renders |
| **FlatList** | Lista virtualizada — equivalente ao `LazyColumn` do Compose |
| **StyleSheet** | Sistema de estilos baseado em objetos JS (não CSS) |
| **Flexbox** | Sistema de layout — `flexDirection: 'column'` é o padrão |
| **React Navigation** | Biblioteca de navegação padrão da comunidade |
| **Zustand** | Gerenciamento de estado global moderno e simples |
| **TanStack Query** | Cache + estado de requisições HTTP automático |
| **Reanimated 3** | Animações nativas de 60fps na thread da UI |
| **AsyncStorage/MMKV** | Persistência de dados chave-valor no dispositivo |
| **React Hook Form** | Gerenciamento de formulários com alta performance |
| **Error Boundary** | Componente que captura erros de renderização sem derrubar o app |
| **Sentry** | Captura e monitora erros em produção |
| **Hermes** | Motor JavaScript otimizado para React Native — AOT compilation |
| **EAS Build** | Serviço de build em nuvem do Expo — sem precisar de Mac/Android Studio |
| **TypeScript** | Tipagem estática — detecta erros antes de rodar o app |
