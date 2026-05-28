# React Native — Guia Técnico Completo

> **Nível:** Zero (sem experiência prévia)
> **Linguagem:** JavaScript / TypeScript
> **Referência oficial:** [reactnative.dev](https://reactnative.dev/docs/getting-started)
> **Versão de referência:** React Native 0.74+ / Expo SDK 51+
> **Fontes:** Material didático UNISATC — Prof. Thyerri Mezzari + documentação oficial

---

## Prefácio

Este guia reúne, em documento único, todo o conhecimento necessário para desenvolver aplicativos móveis com React Native partindo do absoluto zero. O conteúdo foi organizado de forma progressiva: cada parte pressupõe o domínio da anterior. Não é um tutorial de copiar e colar — cada bloco de código é acompanhado de explicação linha a linha, comparativos com tecnologias equivalentes e indicação explícita de armadilhas comuns. O leitor encontrará aqui desde a arquitetura interna do framework até o build de produção nas lojas, passando por componentes, estado, navegação, requisições HTTP, persistência, testes e performance. Exemplos práticos foram extraídos de aulas reais e expandidos com padrões da indústria. Palavras-chave técnicas estão linkadas ao Glossário ao final do documento. Ao terminar a leitura, o leitor estará apto a criar, organizar, otimizar e publicar um app React Native completo.

### Como estudar este guia

Este material foi escrito para ser lido de forma progressiva, mas não precisa ser consumido de uma única vez. Uma boa estratégia é:

1. Ler as Partes 1 a 5 em sequência para formar a base mental do React Native.
2. Praticar os exemplos em um projeto pequeno, mesmo que simples.
3. Voltar depois para as Partes 6 em diante conforme surgirem necessidades reais do app.
4. Usar o Glossário como apoio sempre que um termo técnico reaparecer.

Se algum trecho parecer denso, a melhor abordagem não é decorar o código, mas responder três perguntas:

- qual problema essa ferramenta resolve?
- quando eu escolheria isso em vez de outra opção?
- o que mudaria na experiência do usuário ou do desenvolvedor se eu não usasse isso?

---

## Sumário

- [Parte 1 — Introdução e Contextualização](#parte-1--introdução-e-contextualização)
  - [1.1 O que é React Native?](#11-o-que-é-react-native)
  - [1.2 Como o React Native funciona internamente](#12-como-o-react-native-funciona-internamente)
  - [1.3 O problema que o React Native resolve](#13-o-problema-que-o-react-native-resolve)
  - [1.4 React Native vs Flutter](#14-react-native-vs-flutter)
  - [1.5 O paradigma declarativo](#15-o-paradigma-declarativo)
- [Parte 2 — Configuração do Ambiente](#parte-2--configuração-do-ambiente)
  - [2.1 Expo vs React Native CLI](#21-expo-vs-react-native-cli)
  - [2.2 Criando o primeiro projeto com Expo](#22-criando-o-primeiro-projeto-com-expo)
  - [2.3 Estrutura de pastas do projeto](#23-estrutura-de-pastas-do-projeto)
  - [2.4 O arquivo App.tsx](#24-o-arquivo-apptsx)
- [Parte 3 — Componentes Fundamentais](#parte-3--componentes-fundamentais)
  - [3.1 O que são componentes](#31-o-que-são-componentes)
  - [3.2 View](#32-view)
  - [3.3 Text](#33-text)
  - [3.4 Image](#34-image)
  - [3.5 TextInput](#35-textinput)
  - [3.6 TouchableOpacity e Pressable](#36-touchableopacity-e-pressable)
  - [3.7 ScrollView](#37-scrollview)
  - [3.8 Props e Children](#38-props-e-children)
- [Parte 4 — Estilização](#parte-4--estilização)
  - [4.1 StyleSheet](#41-stylesheet)
  - [4.2 Flexbox no React Native](#42-flexbox-no-react-native)
  - [4.3 Estilos dinâmicos e condicionais](#43-estilos-dinâmicos-e-condicionais)
- [Parte 5 — Estado e Hooks](#parte-5--estado-e-hooks)
  - [5.1 O que é estado](#51-o-que-é-estado)
  - [5.2 O que são Hooks](#52-o-que-são-hooks)
  - [5.3 Tipos de Hooks](#53-tipos-de-hooks)
  - [5.4 useState](#54-usestate)
  - [5.5 Atualização funcional do estado](#55-atualização-funcional-do-estado)
  - [5.6 useEffect](#56-useeffect)
  - [5.7 useMemo e useCallback](#57-usememo-e-usecallback)
  - [5.8 useRef](#58-useref)
  - [5.9 Custom Hooks](#59-custom-hooks)
- [Parte 6 — Listas e FlatList](#parte-6--listas-e-flatlist)
  - [6.1 Por que ScrollView não serve para listas longas](#61-por-que-scrollview-não-serve-para-listas-longas)
  - [6.2 FlatList](#62-flatlist)
  - [6.3 SectionList](#63-sectionlist)
- [Parte 7 — Navegação com React Navigation](#parte-7--navegação-com-react-navigation)
  - [7.1 Instalação](#71-instalação)
  - [7.2 Stack Navigator](#72-stack-navigator)
  - [7.3 Navegar e passar parâmetros](#73-navegar-e-passar-parâmetros)
  - [7.4 Bottom Tab Navigator](#74-bottom-tab-navigator)
  - [7.5 Navegação aninhada](#75-navegação-aninhada)
- [Parte 8 — Gerenciamento de Estado Global](#parte-8--gerenciamento-de-estado-global)
  - [8.1 Context API](#81-context-api)
  - [8.2 Zustand](#82-zustand)
- [Parte 9 — Requisições HTTP](#parte-9--requisições-http)
  - [9.1 Fetch nativo](#91-fetch-nativo)
  - [9.2 Axios](#92-axios)
  - [9.3 TanStack Query](#93-tanstack-query)
- [Parte 10 — Persistência de Dados](#parte-10--persistência-de-dados)
  - [10.1 AsyncStorage](#101-asyncstorage)
  - [10.2 MMKV](#102-mmkv)
  - [10.3 SQLite com expo-sqlite](#103-sqlite-com-expo-sqlite)
- [Parte 11 — Permissões](#parte-11--permissões)
  - [11.1 Por que permissões existem](#111-por-que-permissões-existem)
  - [11.2 Câmera e Galeria](#112-câmera-e-galeria)
  - [11.3 Localização](#113-localização)
- [Parte 12 — Frameworks de UI e Ícones](#parte-12--frameworks-de-ui-e-ícones)
  - [12.1 React Native Paper](#121-react-native-paper)
  - [12.2 NativeWind](#122-nativewind)
  - [12.3 Ícones](#123-ícones)
- [Parte 13 — Animações](#parte-13--animações)
  - [13.1 Animated API](#131-animated-api)
  - [13.2 Reanimated 3](#132-reanimated-3)
  - [13.3 Lottie](#133-lottie)
- [Parte 14 — TypeScript no React Native](#parte-14--typescript-no-react-native)
  - [14.1 Tipagem de componentes e props](#141-tipagem-de-componentes-e-props)
  - [14.2 Utility Types](#142-utility-types)
  - [14.3 Tipagem de navegação](#143-tipagem-de-navegação)
  - [14.4 Tipagem de estado e hooks](#144-tipagem-de-estado-e-hooks)
- [Parte 15 — Performance e Otimização](#parte-15--performance-e-otimização)
  - [15.1 React.memo](#151-reactmemo)
  - [15.2 O problema com funções inline em FlatList](#152-o-problema-com-funções-inline-em-flatlist)
  - [15.3 Hermes](#153-hermes)
  - [15.4 Tabela de otimizações](#154-tabela-de-otimizações)
- [Parte 16 — Testes](#parte-16--testes)
  - [16.1 Estrutura de testes](#161-estrutura-de-testes)
  - [16.2 Jest — testes unitários](#162-jest--testes-unitários)
  - [16.3 React Native Testing Library](#163-react-native-testing-library)
- [Parte 17 — Tratamento de Erros em Produção](#parte-17--tratamento-de-erros-em-produção)
  - [17.1 Error Boundaries](#171-error-boundaries)
  - [17.2 Sentry](#172-sentry)
- [Parte 18 — Formulários](#parte-18--formulários)
  - [18.1 React Hook Form + Zod](#181-react-hook-form--zod)
- [Parte 19 — Build e Publicação](#parte-19--build-e-publicação)
  - [19.1 Os três ambientes Expo](#191-os-três-ambientes-expo)
  - [19.2 EAS Build](#192-eas-build)
  - [19.3 Build com React Native CLI](#193-build-com-react-native-cli)
- [Parte 20 — Arquitetura e Boas Práticas](#parte-20--arquitetura-e-boas-práticas)
  - [20.1 Estrutura de projeto escalável](#201-estrutura-de-projeto-escalável)
  - [20.2 Separação entre UI e lógica](#202-separação-entre-ui-e-lógica)
- [Parte 21 — Mapa do Ecossistema](#parte-21--mapa-do-ecossistema)
- [Glossário](#glossário)

---

## Parte 1 — Introdução e Contextualização

---

### 1.1 O que é React Native?

React Native é um [framework](#framework) de código aberto criado pelo Meta (Facebook) em 2015 para construir aplicativos móveis nativos usando [JavaScript](#javascript) e [React](#react). Diferentemente de soluções que apenas embrulham um [WebView](#webview), o React Native **renderiza componentes nativos reais** da plataforma — os mesmos que aparecem em apps escritos em Swift (iOS) ou Kotlin (Android).

O slogan histórico do framework é: **"Learn once, write anywhere"** — aprenda uma vez, escreva para qualquer plataforma. Isso significa que você escreve um único código JavaScript e ele gera interfaces nativas para iOS e Android simultaneamente.

Em 2022, o React Native passou por uma reestruturação interna chamada **Nova Arquitetura**, que trouxe o motor JavaScript [Hermes](#hermes), o sistema de renderização [Fabric](#fabric) e o sistema de módulos nativos [TurboModules](#turbomodules). Essa arquitetura está disponível por padrão a partir da versão 0.74.

---

### 1.2 Como o React Native funciona internamente

Para entender o React Native, é necessário entender o que diferencia ele de uma solução WebView.

**Abordagem WebView (Ionic, Capacitor):**

```
JavaScript → WebView → renderiza HTML/CSS → visual "nativo" simulado
```

Apps WebView são basicamente sites dentro de um container. O botão que o usuário vê é um `<button>` HTML estilizado com CSS — não é o botão nativo do iOS ou Android.

**Abordagem React Native:**

```
JavaScript → Bridge/JSI → componentes nativos reais (UIButton, android.widget.Button)
```

Quando você escreve `<Button />` no React Native, o framework instrui o sistema operacional a criar um botão nativo real. O JavaScript descreve **o que** mostrar; o SO decide **como** renderizar.

O framework opera com duas [threads](#thread) principais:

- **Main thread (nativa):** responsável pela renderização e pelo comportamento nativo do app.
- **JS thread:** executa o código JavaScript da aplicação (lógica de negócio, estado, etc.).

Essas threads nunca se comunicam diretamente. A comunicação passa pela **[Bridge](#bridge)**, que tem três características: assíncrona, em lote (*batched*) e serializada (formato JSON).

| Abordagem | Exemplos | Como renderiza | Performance |
|---|---|---|---|
| WebView | Ionic, Capacitor | HTML/CSS em WebView | Limitada |
| React Native | React Native | Componentes nativos reais | Alta |
| Compilado nativo | Flutter | Engine própria (Skia/Impeller) | Muito alta |
| Nativo puro | Swift/Kotlin | Componentes do SO | Máxima |

---

### 1.3 O problema que o React Native resolve

Antes do React Native, um app para iOS e Android exigia **duas equipes separadas** com duas bases de código completamente distintas. Toda funcionalidade precisava ser implementada duas vezes.

| Problema (desenvolvimento nativo duplo) | Como o React Native resolve |
|---|---|
| Dois times, duas linguagens | Uma equipe, uma linguagem (JavaScript/TypeScript) |
| Código duplicado para iOS e Android | Até 90% de código compartilhado |
| Conhecimento dividido em silos | Desenvolvedores web podem criar apps móveis |
| Ciclos de desenvolvimento lentos | Hot Reload: vê mudanças em tempo real sem recompilar |
| Custo elevado de manutenção | Uma base de código para manter |

---

### 1.4 React Native vs Flutter

| Critério | React Native | Flutter |
|---|---|---|
| **Linguagem** | JavaScript / TypeScript | Dart |
| **Criado por** | Meta (Facebook) | Google |
| **Renderização** | Componentes nativos do SO | Engine própria (Impeller) |
| **Visual** | Segue as convenções de cada plataforma | Consistente em todas as plataformas |
| **Curva de aprendizado** | Baixa para devs web | Média — Dart é nova linguagem |
| **Ecossistema** | Enorme — npm com milhões de pacotes | Crescente — pub.dev |
| **Tamanho do app** | Menor | Maior (inclui engine Flutter) |
| **Quando escolher** | Time com background web, prazo curto | Consistência visual total, performance máxima |

---

### 1.5 O paradigma declarativo

React Native herda o paradigma [declarativo](#paradigma-declarativo) do React. A interface é sempre uma função do estado atual dos dados:

```
UI = f(estado)
```

**Paradigma imperativo (o que NÃO existe no React Native moderno):**

```javascript
// Você NÃO faz isso no React Native moderno
const botao = document.getElementById('meuBotao');
botao.style.backgroundColor = 'red'; // manipulação manual do elemento
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

| | Imperativo | Declarativo |
|---|---|---|
| Quando a tela muda | Você manipula elementos manualmente | O React reexecuta o componente automaticamente |
| Quem controla a UI | O desenvolvedor, passo a passo | O estado dos dados |
| Risco de bug | UI e dados ficam dessincronizados | Impossível: UI é sempre derivada do estado |

---

## Parte 2 — Configuração do Ambiente

---

### 2.1 Expo vs React Native CLI

Existem dois pontos de entrada no ecossistema React Native:

| Abordagem | Ferramenta | Quando usar |
|---|---|---|
| **[Expo](#expo)** | `create-expo-app` | Início rápido, prototipagem, apps sem código nativo customizado |
| **React Native CLI** | `@react-native-community/cli` | Controle total, módulos nativos customizados, produção avançada |

**Limitações do Expo Go:** o Expo Go só suporta as APIs incluídas no SDK do Expo. Se você precisar de um módulo nativo não incluído (SDK de pagamento específico, Bluetooth customizado, etc.), precisará usar um **Expo Development Build** ou migrar para React Native CLI.

---

### 2.2 Criando o primeiro projeto com Expo

```bash
# Criar um novo projeto com Expo
npx create-expo-app@latest --template blank MeuApp
cd MeuApp

# Iniciar o servidor de desenvolvimento
npx expo start
```

Após rodar `npx expo start`, um QR code aparece no terminal. Escaneie com o app **Expo Go** no celular e o app aparece instantaneamente.

Atalhos úteis no terminal enquanto o projeto está rodando:

| Tecla | Ação |
|---|---|
| `a` | Abre o emulador Android |
| `i` | Abre o simulador iOS (apenas macOS) |
| `r` | Reinicia/recarrega o app |
| `Ctrl+C` | Encerra o servidor |

---

### 2.3 Estrutura de pastas do projeto

```
MeuApp/
├── android/          → código nativo Android (Kotlin/Java) — apenas no CLI
├── ios/              → código nativo iOS (Swift/Objective-C) — apenas no CLI
├── src/              → seu código JavaScript/TypeScript (convenção)
│   ├── components/   → componentes reutilizáveis (botões, cards, etc.)
│   ├── screens/      → telas do app (cada arquivo = uma tela)
│   ├── navigation/   → configuração de rotas e tipos de navegação
│   ├── hooks/        → custom hooks reutilizáveis
│   ├── services/     → chamadas de API, utilitários
│   ├── stores/       → estado global (Zustand, Context)
│   └── types/        → tipos TypeScript compartilhados
├── App.tsx           → componente raiz do app
├── index.js          → ponto de entrada (registra o app)
├── package.json      → dependências e scripts
└── tsconfig.json     → configuração do TypeScript
```

> **Dica:** Dan Abramov (colaborador principal do React/Meta) afirma que não existe estrutura "oficial" obrigatória. A regra prática é: mova arquivos para subpastas quando a quantidade incomodar. Consulte https://react-file-structure.surge.sh para mais.

Para código específico por plataforma, há dois caminhos:

```
// 1. Arquivo específico por plataforma
MeuComponente.android.js  → carregado apenas no Android
MeuComponente.ios.js      → carregado apenas no iOS

// 2. API Platform dentro do código
import { Platform } from 'react-native';
const valor = Platform.OS === 'ios' ? 'valor_ios' : 'valor_android';
```

---

### 2.4 O arquivo App.tsx

```tsx
// App.tsx — o componente raiz que o sistema carrega primeiro
// Tudo que aparecer aqui é o ponto de partida visual do app
import React from 'react';
import { View, Text, StyleSheet } from 'react-native';

export default function App() {
  return (
    // View é o contêiner raiz — equivale a uma <div> no HTML
    <View style={styles.container}>
      <Text style={styles.titulo}>Olá, React Native!</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,              // ocupa todo o espaço disponível
    justifyContent: 'center', // centraliza verticalmente
    alignItems: 'center', // centraliza horizontalmente
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

### 3.1 O que são componentes

No React Native, **componentes são os blocos de construção da interface**. Cada componente é uma função JavaScript que descreve uma parte da UI. Diferentemente do React para web, não existem elementos HTML — os componentes mapeiam diretamente para elementos nativos.

| React (Web) | React Native | iOS nativo | Android nativo |
|---|---|---|---|
| `<div>` | `<View>` | `UIView` | `android.view.View` |
| `<p>`, `<span>`, `<h1>` | `<Text>` | `UILabel` | `android.widget.TextView` |
| `<img>` | `<Image>` | `UIImageView` | `android.widget.ImageView` |
| `<input>` | `<TextInput>` | `UITextField` | `android.widget.EditText` |
| `<button>` | `<TouchableOpacity>` / `<Pressable>` | `UIButton` | `android.widget.Button` |
| `<ul>` + `<li>` | `<FlatList>` | `UITableView` | `RecyclerView` |

---

### 3.2 View

`View` é o componente mais básico do React Native. Equivale à `<div>` do HTML. É um contêiner invisível que organiza outros componentes e serve como bloco de layout.

```tsx
import { View, Text, StyleSheet } from 'react-native';

function ExemploView() {
  return (
    // View externa — contêiner principal
    <View style={styles.container}>

      {/* View interna — agrupa textos em uma caixa */}
      <View style={styles.caixa}>
        <Text>Primeiro</Text>
        <Text>Segundo</Text>
      </View>

    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,          // ocupa todo o espaço disponível da tela
    padding: 16,
    backgroundColor: '#f5f5f5',
  },
  caixa: {
    backgroundColor: 'white',
    borderRadius: 8,  // cantos arredondados
    padding: 12,
  },
});
```

---

### 3.3 Text

`Text` é o único componente que pode conter texto. No React Native, **todo texto deve estar dentro de um `<Text>`** — diferentemente do HTML, onde texto pode aparecer dentro de qualquer elemento.

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

      {/* Aninhamento — Text filho herda estilos do Text pai */}
      <Text style={{ color: 'gray' }}>
        Texto cinza com{' '}
        <Text style={{ fontWeight: 'bold', color: 'black' }}>
          parte em negrito
        </Text>
        {' '}e volta ao cinza.
      </Text>

      {/* numberOfLines trunca o texto com reticências */}
      <Text numberOfLines={2} ellipsizeMode="tail">
        Este texto longo será truncado após duas linhas...
        Lorem ipsum dolor sit amet consectetur adipiscing elit.
      </Text>
    </View>
  );
}
```

> **Erro comum:** colocar texto diretamente fora de `<Text>` causa erro de tempo de execução:
> ```tsx
> <View>Texto aqui</View>       // ❌ ERRO em tempo de execução
> <View><Text>Texto</Text></View> // ✅ correto
> ```

---

### 3.4 Image

```tsx
import { Image, View } from 'react-native';

function ExemploImage() {
  return (
    <View>
      {/* Imagem local — referenciada com require() */}
      <Image
        source={require('./assets/logo.png')}
        style={{ width: 200, height: 100 }}
      />

      {/* Imagem remota — width e height são OBRIGATÓRIOS para imagens remotas */}
      {/* Sem width/height a imagem não aparece */}
      <Image
        source={{ uri: 'https://picsum.photos/200/100' }}
        style={{ width: 200, height: 100, borderRadius: 8 }}
      />

      {/* resizeMode controla como a imagem preenche o espaço disponível */}
      <Image
        source={{ uri: 'https://picsum.photos/400/200' }}
        style={{ width: 200, height: 100 }}
        resizeMode="cover"   // cover | contain | stretch | center
      />
    </View>
  );
}
```

> Para imagens remotas em produção, prefira **Expo Image** (`npx expo install expo-image`) em vez do `Image` padrão — ela oferece cache avançado, placeholder durante carregamento e melhor performance.

---

### 3.5 TextInput

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
        onChangeText={setEmail}           // chamado a cada tecla pressionada
        placeholder="Digite seu e-mail"
        placeholderTextColor="#999"
        keyboardType="email-address"      // abre teclado com @ visível
        autoCapitalize="none"             // não capitaliza automaticamente
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
        secureTextEntry={true}            // oculta os caracteres digitados
        placeholderTextColor="#999"
        style={{
          borderWidth: 1,
          borderColor: '#ccc',
          borderRadius: 8,
          padding: 12,
          fontSize: 16,
        }}
      />

      <Text>E-mail: {email}</Text>
    </View>
  );
}
```

**Propriedades mais utilizadas:**

| Propriedade | O que faz | Exemplo |
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

### 3.6 TouchableOpacity e Pressable

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
      {/* Recebe o estado de pressionamento como parâmetro do style */}
      <Pressable
        onPress={() => console.log('Pressable!')}
        style={({ pressed }) => ({
          backgroundColor: pressed ? '#0055CC' : '#007AFF',
          padding: 16,
          borderRadius: 8,
          alignItems: 'center',
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

| Componente | Feedback visual | Quando usar |
|---|---|---|
| `TouchableOpacity` | Reduz opacidade | Botões gerais — o mais usado |
| `TouchableHighlight` | Escurece o fundo | Listas com itens clicáveis |
| `TouchableWithoutFeedback` | Nenhum | Fechar teclado ao tocar fora |
| `Pressable` | Totalmente customizável | Quando precisa de controle total do estado de pressão |

---

#### O que é um "botão nativo real"?

Nenhum dos componentes acima (`TouchableOpacity`, `Pressable`, etc.) é, tecnicamente, um **botão nativo real**. Para entender o que isso significa, é preciso compreender o que acontece por baixo do React Native.

**O que "nativo" significa aqui**

Cada sistema operacional (Android e iOS) possui seus próprios componentes de interface gráfica implementados em sua respectiva linguagem nativa:

| Plataforma | Linguagem | Botão nativo |
|---|---|---|
| Android | Kotlin / Java | `android.widget.Button` |
| iOS | Swift / Objective-C | `UIButton` |

Esses componentes são fornecidos pelo próprio sistema operacional. Eles carregam consigo comportamentos padrão que o usuário já conhece: feedback tátil (vibração leve ao toque), animação de ripple no Android, highlight no iOS, acessibilidade integrada (leitores de tela identificam o elemento como "botão"), e resposta ao foco via teclado físico ou controle externo.

**O que `TouchableOpacity` e `Pressable` fazem de fato**

Esses componentes são wrappers JavaScript sobre uma `View` comum. O que eles fazem é:

1. Capturar eventos de toque via o sistema de gestos do React Native.
2. Modificar propriedades visuais da `View` (opacidade, cor de fundo) em resposta ao toque.
3. Chamar callbacks (`onPress`, `onLongPress`) quando o gesto é reconhecido.

Ou seja, são **simulações visuais** de um botão — não o componente `Button` que o sistema operacional fornece nativamente. O feedback é implementado em JavaScript, não pelo OS.

**Existe um componente de botão nativo no React Native?**

Sim. O React Native expõe o componente `<Button>`:

```tsx
import { Button } from 'react-native';

// Botão nativo real — renderiza UIButton no iOS e Button no Android
<Button
  title="Confirmar"
  onPress={() => console.log('confirmado')}
  color="#007AFF"   // no iOS: cor do texto; no Android: cor de fundo
/>
```

Este componente **delega a renderização ao sistema operacional**. O resultado visual é o botão padrão da plataforma — sem personalização de forma, tamanho, fonte ou layout. Por isso, na prática, `<Button>` raramente é usado em produção.

**Por que então usar `TouchableOpacity` ou `Pressable`?**

Porque a maioria dos designs de produto exige botões personalizados (cores, bordas, ícones, tamanhos, animações). Isso não é possível com o `<Button>` nativo. A troca é deliberada:

| Critério | `<Button>` nativo | `TouchableOpacity` / `Pressable` |
|---|---|---|
| Visual | Padrão do sistema operacional | Totalmente customizável |
| Acessibilidade | Automática e completa | Requer configuração manual (`accessibilityRole="button"`) |
| Feedback tátil | Fornecido pelo OS | Implementado em JavaScript |
| Personalização | Nenhuma | Total |
| Uso em produção | Raro | Predominante |

**Conclusão**

Quando alguém diz "botão nativo real", está se referindo ao componente que o sistema operacional renderiza diretamente — com todos os comportamentos padrão da plataforma incluídos. No React Native, isso é o `<Button>`. Os demais (`TouchableOpacity`, `Pressable`) são composições JavaScript que simulam o comportamento de botão sobre uma `View` — tecnicamente, são `View`s com listeners de toque, não botões nativos.

---

### 3.7 ScrollView

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
      horizontal={false}                     // true = rolagem horizontal
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

> **Atenção:** `ScrollView` renderiza **todos** os filhos imediatamente na memória. Para listas longas, use `FlatList` — ela só renderiza os itens visíveis na tela (virtualização).

---

### 3.8 Props e Children

**Props** são os parâmetros que um componente recebe. São a forma de injetar dados externos em um componente.

```tsx
// Declarando quais props o componente aceita
function KittenCard({ titulo, texto, labelBotao = 'Clique Aqui' }) {
  //                                              ↑ valor padrão para prop opcional
  return (
    <View>
      <Text>{titulo}</Text>
      <Text>{texto}</Text>
      {/* Renderiza o botão APENAS se labelBotao estiver preenchido */}
      {!!labelBotao && (
        <TouchableOpacity onPress={() => console.log('Pressionou')}>
          <Text>{labelBotao}</Text>
        </TouchableOpacity>
      )}
    </View>
  );
}

// Usando o componente com props
function App() {
  return (
    <View>
      <KittenCard
        titulo="Adote um Gatinho!"
        texto="Eles destroem seus móveis mas são fofos."
        labelBotao="Quero Adotar"
      />
      <KittenCard
        titulo="Outro Gato"
        texto="Também muito fofo."
        // labelBotao omitido → usa o valor padrão 'Clique Aqui'
      />
    </View>
  );
}
```

**Children** é uma prop reservada do React que representa o conteúdo JSX inserido **entre as tags de abertura e fechamento** de um componente.

#### O mecanismo por trás de `children`

Quando você escreve JSX como este:

```tsx
<MeuComponente>
  <Text>Olá</Text>
</MeuComponente>
```

O compilador (Babel/SWC) transforma isso em:

```js
React.createElement(MeuComponente, null, React.createElement(Text, null, "Olá"));
```

O terceiro argumento de `createElement` — o conteúdo entre as tags — é automaticamente acessível dentro do componente como `props.children`. Ou seja, `children` **não é mágica**: é apenas o nome reservado para esse terceiro argumento.

#### Por que `children` existe?

Sem `children`, todo conteúdo variável de um componente precisaria ser passado via prop explícita — o que se torna impraticável quando o conteúdo é complexo ou arbitrário.

Compare as duas abordagens:

```tsx
// ❌ Sem children — cada variação de conteúdo exige uma nova prop
<Card conteudo={<Text>Texto longo e complexo</Text>} />

// ✅ Com children — o conteúdo é inserido naturalmente entre as tags
<Card>
  <Text>Texto longo e complexo</Text>
</Card>
```

A segunda forma é mais legível e permite que o componente `Card` seja genérico — ele não precisa saber o que vai dentro dele, apenas onde renderizar.

#### Exemplo progressivo

**Passo 1 — Componente sem `children`: conteúdo fixo**

```tsx
// Este componente sempre renderiza o mesmo texto interno
// Não é reutilizável para conteúdos diferentes
function Card() {
  return (
    <View style={{ padding: 16, borderRadius: 8, backgroundColor: '#f5f5f5' }}>
      <Text>Conteúdo fixo — não posso trocar isso de fora</Text>
    </View>
  );
}
```

**Passo 2 — Introduzindo `children`: conteúdo injetado pelo pai**

```tsx
// children é desestruturado das props como qualquer outra prop
function Card({ children }) {
  return (
    // A View define o visual do "container"
    <View style={{ padding: 16, borderRadius: 8, backgroundColor: '#f5f5f5' }}>
      {/* children é renderizado aqui — pode ser qualquer JSX */}
      {children}
    </View>
  );
}

// Uso: qualquer coisa entre <Card> e </Card> vira children
function App() {
  return (
    <View>
      {/* Caso 1: children é um texto simples */}
      <Card>
        <Text>Conteúdo simples</Text>
      </Card>

      {/* Caso 2: children são múltiplos elementos */}
      <Card>
        <Text style={{ fontWeight: 'bold' }}>Título do card</Text>
        <Text>Descrição do card</Text>
      </Card>

      {/* Caso 3: children é outro componente inteiro */}
      <Card>
        <KittenCard titulo="Gato" texto="Fofo" />
      </Card>
    </View>
  );
}
```

O componente `Card` não sabe — e não precisa saber — o que `children` contém. Ele apenas define onde aquilo será posicionado e qual visual o envolve.

**Passo 3 — Combinando props nomeadas com `children`**

```tsx
// Card com título fixo (prop nomeada) e conteúdo variável (children)
function Card({ titulo, children, corFundo = '#f5f5f5' }) {
  //            ↑ prop nomeada   ↑ conteúdo injetado  ↑ prop com valor padrão
  return (
    <View style={{ padding: 16, borderRadius: 8, backgroundColor: corFundo }}>
      {/* Cabeçalho fixo, definido via prop */}
      <Text style={{ fontWeight: 'bold', marginBottom: 8 }}>{titulo}</Text>

      {/* Separador visual */}
      <View style={{ height: 1, backgroundColor: '#ddd', marginBottom: 8 }} />

      {/* Corpo variável, definido via children */}
      {children}
    </View>
  );
}

function App() {
  return (
    <Card titulo="Informações do usuário" corFundo="#e8f4fd">
      <Text>Nome: João Silva</Text>
      <Text>Email: joao@email.com</Text>
    </Card>
  );
}
```

#### `children` pode ser renderizado condicionalmente

Assim como qualquer outra prop, `children` pode ser verificado antes de renderizar:

```tsx
function Card({ titulo, children }) {
  return (
    <View style={{ padding: 16, borderRadius: 8, backgroundColor: '#f5f5f5' }}>
      <Text style={{ fontWeight: 'bold' }}>{titulo}</Text>

      {/* Só renderiza a seção de conteúdo se children existir */}
      {children && (
        <View style={{ marginTop: 8 }}>
          {children}
        </View>
      )}
    </View>
  );
}

// Uso sem children — o bloco interno não aparece
<Card titulo="Card vazio" />

// Uso com children — o bloco interno aparece
<Card titulo="Card com conteúdo">
  <Text>Algo aqui</Text>
</Card>
```

#### Tipagem correta de `children` em TypeScript

```tsx
import { ReactNode } from 'react';

// ReactNode é o tipo correto para children —
// aceita: JSX, string, number, null, undefined, arrays de qualquer combinação
type CardProps = {
  titulo: string;
  children: ReactNode;   // ✅ correto para qualquer conteúdo JSX
  corFundo?: string;     // opcional, indicado pelo '?'
};

function Card({ titulo, children, corFundo = '#f5f5f5' }: CardProps) {
  return (
    <View style={{ padding: 16, borderRadius: 8, backgroundColor: corFundo }}>
      <Text style={{ fontWeight: 'bold' }}>{titulo}</Text>
      {children}
    </View>
  );
}
```

#### Resumo: props nomeadas vs `children`

| Critério | Props nomeadas | `children` |
|---|---|---|
| Sintaxe | `<Card titulo="X" />` | `<Card>conteúdo</Card>` |
| Tipo de dado | Qualquer valor primitivo ou objeto | Qualquer JSX (elementos, componentes, texto) |
| Quando usar | Dados simples e configuração do componente | Conteúdo visual arbitrário que o componente envolve |
| Legibilidade | Alta para dados simples | Alta para conteúdo complexo ou composto |

---

## Parte 4 — Estilização

---

### 4.1 StyleSheet

No React Native, **não existe CSS**. A estilização é feita via objetos JavaScript que seguem uma API inspirada no CSS, com diferenças importantes.

```tsx
import { StyleSheet, View, Text } from 'react-native';

function ExemploEstilos() {
  return (
    <View style={styles.container}>
      <Text style={styles.titulo}>Título</Text>
      {/* Array de estilos — os objetos são mesclados da esquerda para a direita */}
      <Text style={[styles.texto, styles.destaque]}>
        Texto com múltiplos estilos
      </Text>
    </View>
  );
}

// StyleSheet.create() valida os estilos em tempo de desenvolvimento
// e otimiza a performance ao enviar estilos para a camada nativa
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

**Diferenças críticas entre CSS e StyleSheet:**

| CSS (Web) | React Native | Observação |
|---|---|---|
| `font-size: 16px` | `fontSize: 16` | Sem unidades — dp/pt por padrão |
| `background-color: red` | `backgroundColor: 'red'` | camelCase, sem hífen |
| `border-radius: 8px` | `borderRadius: 8` | Sem unidades |
| `display: flex` | Flex por padrão | Todo View já é flex |
| `flex-direction: column` | `flexDirection: 'column'` | Padrão é `column` (diferente do web que é `row`) |
| `margin: 8px 16px` | `marginVertical: 8, marginHorizontal: 16` | Sem shorthand de múltiplos valores |
| `box-shadow` | `shadowColor`, `shadowOffset`, `elevation` | API diferente para iOS e Android |

---

### 4.2 Flexbox no React Native

O React Native usa **Flexbox** como sistema de layout, com diferenças em relação ao Flexbox web:

- `flexDirection` padrão é `'column'` (web é `'row'`)
- Todo `View` já é `display: flex` por padrão

```tsx
import { View, Text, StyleSheet } from 'react-native';

function ExemploFlex() {
  return (
    <View style={styles.container}>

      {/* Linha horizontal — equivale a Row no Jetpack Compose */}
      <View style={styles.linha}>
        {/* flex: 2 → ocupa 2/3 do espaço disponível */}
        <View style={[styles.caixa, { flex: 2 }]}>
          <Text>2/3 do espaço</Text>
        </View>
        {/* flex: 1 → ocupa 1/3 do espaço disponível */}
        <View style={[styles.caixa, { flex: 1, backgroundColor: '#e0e0ff' }]}>
          <Text>1/3 do espaço</Text>
        </View>
      </View>

    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, padding: 16 },
  linha: {
    flexDirection: 'row',         // disposição horizontal
    justifyContent: 'space-between', // espaço entre os filhos
    alignItems: 'center',         // alinha verticalmente ao centro
    gap: 8,                       // espaço entre itens (equivalente a Spacer)
  },
  caixa: { backgroundColor: '#e8f4fd', padding: 8, borderRadius: 4 },
});
```

**Equivalência com Jetpack Compose:**

| Jetpack Compose | React Native |
|---|---|
| `Column` | `View` com `flexDirection: 'column'` (padrão) |
| `Row` | `View` com `flexDirection: 'row'` |
| `Box` | `View` com filhos `position: 'absolute'` |
| `Modifier.weight(1f)` | `flex: 1` |
| `Arrangement.SpaceBetween` | `justifyContent: 'space-between'` |
| `Alignment.CenterVertically` | `alignItems: 'center'` |
| `Spacer(Modifier.height(8.dp))` | `<View style={{ height: 8 }} />` ou `gap: 8` |

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
        // Aplica um dos dois estilos com base no estado
        ativo ? styles.botaoAtivo : styles.botaoInativo,
      ]}
    >
      {/* && só adiciona o estilo ao array quando ativo for true */}
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

### 5.1 O que é estado

Estado é a **memória do componente**. É onde o React guarda valores que podem mudar com o tempo e que precisam refletir na interface: texto digitado, contador, lista carregada da API, item selecionado, status de loading e assim por diante.

A ideia central do React Native continua sendo:

```text
UI = f(estado)
```

Ou seja: a tela que o usuário vê é uma consequência direta dos dados atuais. Quando o estado muda, o React executa o componente novamente e atualiza a UI para combinar com esse novo estado.

Compare os três conceitos abaixo:

| Conceito | Quem define | Pode mudar? | Quando usar |
|---|---|---|---|
| **Props** | Componente pai | Sim, mas quem muda é o pai | Dados recebidos de fora |
| **Estado** | O próprio componente | Sim | Dados internos que afetam a tela |
| **Variável comum** | O código local | Sim | Cálculos temporários que não precisam re-renderizar |

```tsx
import { useState } from 'react';

function Exemplo() {
  let contadorComum = 0; // variável comum
  const [contador, setContador] = useState(0); // estado real

  function incrementarErrado() {
    contadorComum += 1;
    // A UI não acompanha essa mudança porque o React não monitora essa variável
  }

  function incrementarCerto() {
    setContador(valorAtual => valorAtual + 1);
    // Aqui o React sabe que precisa renderizar de novo
  }
}
```

Resumo prático: se um valor mudou e a tela precisa refletir essa mudança, isso quase sempre é **estado**.

---

### 5.2 O que são Hooks

**[Hooks](#hooks)** são funções especiais do React que conectam componentes funcionais a recursos do framework: estado, efeitos colaterais, contexto, referências e otimizações.

Antes dos Hooks, esse tipo de recurso ficava concentrado principalmente em componentes de classe. Hoje, em React Native moderno, Hooks são a forma padrão de organizar lógica de componente.

Uma boa forma de pensar é:

- O componente descreve **o que renderizar**
- Os Hooks dizem **como esse componente guarda estado, reage a mudanças e conversa com o mundo externo**

Exemplo:

```tsx
import { useState, useEffect } from 'react';
import { Text } from 'react-native';

function Perfil() {
  const [nome, setNome] = useState('Maria');

  useEffect(() => {
    console.log('O nome mudou para:', nome);
  }, [nome]);

  return <Text>{nome}</Text>;
}
```

No exemplo acima:

- `useState` guarda o valor `nome`
- `useEffect` reage quando `nome` muda
- o componente continua sendo uma função normal de JavaScript

#### Regras dos Hooks

Hooks funcionam porque o React depende da **ordem em que eles são chamados**. Por isso, existem regras rígidas:

1. **Chame Hooks apenas no nível mais alto do componente.**
2. **Nunca chame Hooks dentro de `if`, `for`, `while`, callbacks ou funções aninhadas.**
3. **Chame Hooks apenas em componentes React ou em outros custom hooks.**

Exemplo incorreto:

```tsx
function Tela({ logado }) {
  if (logado) {
    // ❌ Errado: o Hook fica dentro de uma condição
    const [nome, setNome] = useState('');
  }

  return <Text>Tela</Text>;
}
```

Exemplo correto:

```tsx
function Tela({ logado }) {
  // ✅ O Hook sempre é chamado na mesma ordem
  const [nome, setNome] = useState('');

  if (!logado) {
    return <Text>Faça login</Text>;
  }

  return <Text>Olá, {nome}</Text>;
}
```

---

### 5.3 Tipos de Hooks

Não existe uma divisão "oficial" da documentação com essa exata nomenclatura, mas, para estudar, é útil separar Hooks por **papel**:

| Tipo | Hooks comuns | Para que servem | Exemplo de uso |
|---|---|---|---|
| **Hooks de estado** | `useState`, `useReducer` | Guardar dados que mudam e alteram a UI | contador, formulário, toggle |
| **Hooks de efeito** | `useEffect` | Sincronizar o componente com algo externo | API, timer, listener |
| **Hooks de contexto** | `useContext` | Consumir dados globais compartilhados | tema, usuário autenticado |
| **Hooks de referência** | `useRef` | Guardar valores mutáveis sem re-renderizar | foco em input, ID de timer |
| **Hooks de performance** | `useMemo`, `useCallback` | Evitar trabalho desnecessário quando isso realmente importa | lista filtrada, função passada como prop |
| **Custom Hooks** | `useBuscaCep`, `useAuth`, `useCarrinho` | Reutilizar lógica composta por outros Hooks | lógica compartilhada entre telas |

Em aplicações React Native do dia a dia, os mais comuns para iniciantes são:

1. `useState`
2. `useEffect`
3. `useRef`
4. `useMemo` e `useCallback`
5. Custom Hooks

`useContext` e `useReducer` também são muito importantes, mas normalmente aparecem com mais força quando a aplicação cresce e a organização de estado fica mais sofisticada.

---

### 5.4 useState

`useState` é o Hook mais básico e mais importante para começar. Ele cria um pedaço de estado local dentro do componente.

Sintaxe:

```tsx
const [valorAtual, setValorAtual] = useState(valorInicial);
```

- `valorAtual` é o dado atual
- `setValorAtual` é a função que agenda a atualização
- `valorInicial` é o valor usado na primeira renderização

Exemplo completo:

```tsx
import { useState } from 'react';
import { View, Text, TouchableOpacity, StyleSheet } from 'react-native';

function Contador() {
  const [contador, setContador] = useState(0);
  const [nome, setNome] = useState('Usuário');

  return (
    <View style={styles.container}>
      <Text>Olá, {nome}!</Text>
      <Text>Contagem: {contador}</Text>

      <TouchableOpacity
        style={styles.botao}
        onPress={() => setContador(contador + 1)}
      >
        <Text style={styles.textoBotao}>Incrementar</Text>
      </TouchableOpacity>

      <TouchableOpacity
        style={[styles.botao, { backgroundColor: '#d9534f' }]}
        onPress={() => setContador(0)}
      >
        <Text style={styles.textoBotao}>Resetar</Text>
      </TouchableOpacity>

      <TouchableOpacity
        style={[styles.botao, { backgroundColor: '#5cb85c' }]}
        onPress={() => setNome('Ana')}
      >
        <Text style={styles.textoBotao}>Trocar nome</Text>
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

#### Estado com objetos e arrays

Quando o estado é um objeto ou array, você não deve mutá-lo diretamente. O React espera uma **nova referência**.

```tsx
const [formData, setFormData] = useState({
  nome: '',
  email: '',
  idade: '',
});

// ✅ Atualização correta: cria um novo objeto
setFormData({ ...formData, email: 'novo@email.com' });

const [tarefas, setTarefas] = useState(['Estudar Hooks']);

// ❌ Errado: muta o array existente
tarefas.push('Revisar useEffect');

// ✅ Certo: cria um novo array
setTarefas([...tarefas, 'Revisar useEffect']);
```

---

### 5.5 Atualização funcional do estado

Quando o próximo estado depende do valor anterior, use a **forma funcional** do setter:

```tsx
const [contador, setContador] = useState(0);

// ❌ Pode gerar resultado inesperado em atualizações encadeadas
setContador(contador + 1);
setContador(contador + 1);
setContador(contador + 1);
// Resultado esperado por muita gente: +3
// Resultado real comum: +1

// ✅ Sempre usa o valor mais recente disponível
setContador(valorAnterior => valorAnterior + 1);
setContador(valorAnterior => valorAnterior + 1);
setContador(valorAnterior => valorAnterior + 1);
// Resultado: +3
```

Isso acontece porque atualizações de estado podem ser agrupadas (*batched*). A função recebe o valor mais recente já processado pelo React, então ela é a forma segura quando existe dependência do estado anterior.

Regra prática:

- Se você já tem o valor pronto, use `setAlgo(novoValor)`
- Se o novo valor depende do anterior, use `setAlgo(valorAnterior => ...)`

---

### 5.6 useEffect

`useEffect` serve para lidar com **efeitos colaterais**. Em outras palavras: código que sai da renderização pura e sincroniza o componente com algo externo.

Casos típicos:

- buscar dados em uma API
- configurar e limpar timers
- adicionar listeners de eventos
- sincronizar dados com `AsyncStorage`
- fazer log, analytics ou integração com bibliotecas nativas

Uma distinção importante:

- **Renderizar** é descrever a UI
- **Effect** é reagir depois que a UI já foi descrita

```tsx
import { useState, useEffect } from 'react';
import { View, Text, ActivityIndicator } from 'react-native';

function ExemploUseEffect() {
  const [usuarios, setUsuarios] = useState([]);
  const [carregando, setCarregando] = useState(true);
  const [erro, setErro] = useState(null);

  useEffect(() => {
    let cancelado = false;

    async function buscarUsuarios() {
      try {
        const resposta = await fetch('https://jsonplaceholder.typicode.com/users');
        const dados = await resposta.json();

        if (!cancelado) {
          setUsuarios(dados);
        }
      } catch (e) {
        if (!cancelado) {
          setErro('Erro ao carregar dados');
        }
      } finally {
        if (!cancelado) {
          setCarregando(false);
        }
      }
    }

    buscarUsuarios();

    return () => {
      // Evita atualizar estado depois que a tela já saiu
      cancelado = true;
    };
  }, []);

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

#### Como o array de dependências funciona

```tsx
useEffect(() => { /* executa toda vez que o componente re-renderiza */ });

useEffect(() => { /* executa APENAS na montagem */ }, []);

useEffect(() => { /* executa quando 'userId' ou 'filtro' mudam */ }, [userId, filtro]);
```

#### Quando NÃO usar `useEffect`

Um erro muito comum é usar `useEffect` para calcular valores que poderiam ser derivados diretamente na renderização.

```tsx
// ❌ Desnecessário
const [nomeCompleto, setNomeCompleto] = useState('');

useEffect(() => {
  setNomeCompleto(`${nome} ${sobrenome}`);
}, [nome, sobrenome]);

// ✅ Melhor: valor derivado diretamente
const nomeCompleto = `${nome} ${sobrenome}`;
```

Se um valor pode ser calculado a partir de props, estado e variáveis locais sem acessar nada externo, normalmente ele **não precisa de `useEffect`**.

---

### 5.7 useMemo e useCallback

`useMemo` e `useCallback` são Hooks de **otimização**, não de funcionalidade básica. O componente funciona sem eles na maioria dos casos.

A pergunta certa não é "posso usar?", mas sim:

```text
Existe um problema real de performance ou de identidade de referência aqui?
```

Diferença entre os dois:

| Hook | Memoriza o quê? | Quando usar |
|---|---|---|
| `useMemo` | O resultado de um cálculo | Quando o cálculo é caro ou quando você precisa estabilizar um valor |
| `useCallback` | A própria função | Quando a função é passada para filhos memorizados ou usada em dependências |

```tsx
import { useMemo, useCallback } from 'react';

function ListaFiltrada({ itens, termoBusca }) {
  const itensFiltrados = useMemo(() => {
    return itens.filter(item =>
      item.nome.toLowerCase().includes(termoBusca.toLowerCase())
    );
  }, [itens, termoBusca]);

  const handlePress = useCallback((id) => {
    console.log('Item selecionado:', id);
  }, []);

  return (/* JSX */);
}
```

Regra prática:

- `useMemo` evita recalcular um **valor**
- `useCallback` evita recriar uma **função**
- se não existe cálculo caro nem componente sensível a referência, provavelmente você não precisa deles

---

### 5.8 useRef

`useRef` guarda um valor mutável que **sobrevive entre renderizações** sem causar nova renderização quando muda.

Ele é muito útil em dois cenários:

1. acessar um componente nativo de forma imperativa
2. guardar dados auxiliares que não precisam aparecer na tela

Exemplo com foco em `TextInput`:

```tsx
import { useRef } from 'react';
import { TextInput, TouchableOpacity, Text, View } from 'react-native';

function Busca() {
  const inputRef = useRef<TextInput>(null);

  return (
    <View>
      <TextInput
        ref={inputRef}
        placeholder="Digite sua busca"
        style={{ borderWidth: 1, padding: 12, marginBottom: 12 }}
      />

      <TouchableOpacity onPress={() => inputRef.current?.focus()}>
        <Text>Focar no campo</Text>
      </TouchableOpacity>
    </View>
  );
}
```

Exemplo guardando um ID de timer:

```tsx
const timerRef = useRef<ReturnType<typeof setInterval> | null>(null);

useEffect(() => {
  timerRef.current = setInterval(() => {
    console.log('Rodando...');
  }, 1000);

  return () => {
    if (timerRef.current) {
      clearInterval(timerRef.current);
    }
  };
}, []);
```

Ponto importante: mudar `ref.current` **não re-renderiza** o componente. Se a UI precisa reagir, o dado deve estar em `useState`, não em `useRef`.

---

### 5.9 Custom Hooks

Custom Hooks são funções cujo nome começa com `use` e que encapsulam lógica reutilizável baseada em outros Hooks.

Eles não criam "poderes especiais" novos. Apenas permitem compor:

- `useState`
- `useEffect`
- `useRef`
- `useMemo`
- outros Hooks customizados

Quando vale a pena extrair um custom hook:

- quando duas telas repetem a mesma lógica
- quando um componente ficou grande e misturou UI com regras de negócio
- quando você quer padronizar acesso a uma API, permissão ou fluxo de formulário

Importante: custom hook **não significa estado global**. Cada chamada cria uma instância independente daquela lógica.

```tsx
// Exemplo didático: definição do hook + uso em uma tela
import { useState } from 'react';
import { View, Text, TextInput, TouchableOpacity, ActivityIndicator } from 'react-native';

export function useBuscaCep() {
  const [dados, setDados] = useState(null);
  const [carregando, setCarregando] = useState(false);
  const [erro, setErro] = useState(null);

  async function buscar(cep) {
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
    } catch (e) {
      setErro(e instanceof Error ? e.message : 'Erro ao buscar CEP');
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

## Parte 6 — Listas e FlatList

---

### 6.1 Por que ScrollView não serve para listas longas

`ScrollView` renderiza todos os filhos de uma vez na memória. Com 500 itens, ele cria 500 elementos nativos simultâneos — causando lentidão e consumo excessivo de memória.

**A solução é `FlatList`** — a lista virtualizada do React Native. Ela só renderiza os itens que estão visíveis na tela em um dado momento (equivalente ao `LazyColumn` do Jetpack Compose).

Pense assim:

- `ScrollView` é bom quando você já sabe que o conteúdo será pequeno
- `FlatList` é bom quando a quantidade de itens pode crescer
- `SectionList` é bom quando, além de crescer, os dados precisam ser agrupados

A palavra-chave aqui é **virtualização**. Em vez de manter tudo montado ao mesmo tempo, o React Native recicla e desmonta partes da lista conforme o usuário rola a tela. Isso reduz uso de memória e melhora a fluidez do scroll.

Regra prática:

- formulário, tela de perfil, página de detalhe: `ScrollView`
- feed, catálogo, chat, histórico, ranking: `FlatList`
- agenda, lista por categoria, contatos por letra: `SectionList`

---

### 6.2 FlatList

`FlatList` é um dos componentes mais importantes do React Native porque listas longas aparecem em quase todo app real. Para usar bem, você precisa entender três props fundamentais:

- `data`: o array de itens
- `renderItem`: a função que desenha cada linha
- `keyExtractor`: a chave única de cada item

Se essas três partes estiverem corretas, a lista já nasce saudável. O resto são refinamentos de experiência e performance.

```tsx
import { FlatList, View, Text, StyleSheet } from 'react-native';

const TAREFAS = [
  { id: '1', titulo: 'Comprar leite', concluida: false },
  { id: '2', titulo: 'Fazer exercício', concluida: true },
  // ... muitos outros itens
];

function ListaTarefas() {
  return (
    <FlatList
      data={TAREFAS}
      // keyExtractor: fornece uma chave única para cada item
      // NUNCA use o índice do array como key — use IDs únicos
      keyExtractor={(item) => item.id}
      // renderItem: função que retorna o JSX de cada linha
      renderItem={({ item, index }) => (
        <View style={styles.item}>
          <Text style={[styles.titulo, item.concluida && styles.concluido]}>
            {index + 1}. {item.titulo}
          </Text>
        </View>
      )}
      // windowSize: quantos itens fora da tela são mantidos renderizados
      windowSize={10}
      // removeClippedSubviews: desmonta itens muito distantes da tela (Android)
      removeClippedSubviews={true}
      // Componentes opcionais para cabeçalho, rodapé e lista vazia
      ListHeaderComponent={<Text style={styles.cabecalho}>Minhas Tarefas</Text>}
      ListFooterComponent={<Text style={styles.rodape}>{TAREFAS.length} tarefas no total</Text>}
      ListEmptyComponent={<Text style={styles.vazio}>Nenhuma tarefa ainda!</Text>}
      // ItemSeparatorComponent: renderizado entre cada par de itens
      ItemSeparatorComponent={() => <View style={styles.separador} />}
      contentContainerStyle={{ padding: 16 }}
      // Paginação: onEndReached é chamado quando o usuário chega ao final
      onEndReached={() => carregarMaisItens()}
      onEndReachedThreshold={0.3} // dispara quando chega a 30% do fim
      // Pull to refresh
      refreshing={false}
      onRefresh={() => recarregarLista()}
    />
  );
}
```

Pontos que merecem atenção:

- `keyExtractor` deve usar um identificador estável. Índice do array só é aceitável quando a lista nunca muda de ordem nem recebe inserções.
- `renderItem` é chamado muitas vezes. Quanto mais leve for o item renderizado, melhor será a rolagem.
- `ListEmptyComponent`, `ListHeaderComponent` e `ItemSeparatorComponent` deixam a lista mais profissional sem precisar “gambiarrar” elementos fora dela.
- `refreshing` e `onRefresh` são o jeito nativo de implementar pull to refresh.

Erro comum: colocar uma `ScrollView` em volta da `FlatList`. Na maioria dos casos isso quebra a virtualização e piora a performance. Se a própria lista já rola, ela normalmente deve ser o contêiner principal da tela.

---

### 6.3 SectionList

`SectionList` é a versão agrupada da `FlatList`. Em vez de um único array simples, ela recebe um array de seções. Cada seção tem um título e um conjunto de itens.

Ela é ideal quando o agrupamento faz parte da experiência de leitura. O usuário não quer apenas ver itens soltos; ele quer entender a estrutura dos dados.

```tsx
import { SectionList, Text, View } from 'react-native';

// Dados estruturados em seções
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
      // renderSectionHeader: cabeçalho exibido antes de cada grupo
      renderSectionHeader={({ section: { titulo } }) => (
        <View style={{ backgroundColor: '#f0f0f0', padding: 8 }}>
          <Text style={{ fontWeight: 'bold', fontSize: 16 }}>{titulo}</Text>
        </View>
      )}
    />
  );
}
```

| Componente | Virtualizado | Quando usar |
|---|---|---|
| `ScrollView` | ❌ | Conteúdo pequeno e fixo (formulários, páginas de detalhe) |
| `FlatList` | ✅ | Listas com quantidade variável ou grande de itens |
| `SectionList` | ✅ | Listas agrupadas por categoria/seção |

Se a sua lista está ficando lenta, a primeira pergunta não é "qual otimização avançada eu ativo?", mas sim: **estou usando o componente certo para o tipo de conteúdo?**

---

## Parte 7 — Navegação com React Navigation

---

### 7.1 Instalação

React Navigation é a biblioteca de navegação padrão da comunidade React Native. Não faz parte do núcleo do framework — é instalada separadamente.

Navegação em app mobile não é só "trocar de tela". Ela define:

- histórico de navegação
- botão voltar
- cabeçalhos
- parâmetros entre telas
- estrutura de abas, pilhas e drawers

O React Navigation resolve tudo isso de forma declarativa, integrada ao ciclo de vida do React.

```bash
npm install @react-navigation/native
npm install react-native-screens react-native-safe-area-context
npm install @react-navigation/native-stack       # navegação em pilha
npm install @react-navigation/bottom-tabs        # abas inferiores
```

---

### 7.2 Stack Navigator

O `Stack Navigator` funciona como uma pilha de cartas:

- quando você abre uma nova tela, ela é empilhada sobre a anterior
- quando volta, a tela do topo é removida

Esse modelo combina com fluxos de detalhe, cadastro, checkout e qualquer jornada sequencial.

Também vale fixar o papel de cada peça:

- `NavigationContainer`: gerencia o estado global da navegação
- `Stack.Navigator`: define um grupo de telas que pertencem à mesma pilha
- `Stack.Screen`: registra cada tela disponível dentro dessa pilha

```tsx
// navigation/AppNavigator.tsx
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';

// Definição dos tipos de cada rota e seus parâmetros
type RootStackParams = {
  TelaInicial: undefined;                           // sem parâmetros
  TelaDetalhe: { itemId: number; titulo: string };  // com parâmetros
  TelaPerfil: undefined;
};

const Stack = createNativeStackNavigator<RootStackParams>();

export function AppNavigator() {
  return (
    // NavigationContainer envolve toda a aplicação — vai em App.tsx
    <NavigationContainer>
      <Stack.Navigator
        initialRouteName="TelaInicial"
        screenOptions={{
          // Estilo global da barra de cabeçalho
          headerStyle: { backgroundColor: '#007AFF' },
          headerTintColor: 'white',
          headerTitleStyle: { fontWeight: 'bold' },
        }}
      >
        {/* Cada Screen registra uma tela na pilha de navegação */}
        <Stack.Screen name="TelaInicial" component={TelaInicial} options={{ title: 'Início' }} />
        {/* O título pode ser dinâmico, baseado nos parâmetros recebidos */}
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

### 7.3 Navegar e passar parâmetros

Parâmetros de navegação servem para contexto de tela, não para substituir estado global.

Bom uso de params:

- ID do produto que será exibido
- título da tela
- modo atual, como `"editar"` ou `"criar"`

Mau uso de params:

- carrinho inteiro
- usuário autenticado inteiro
- objetos pesados que poderiam ser buscados por ID

Pense nos params como a "URL mental" da tela: eles dizem **qual tela** abrir e **com qual contexto mínimo**.

```tsx
import { NativeStackScreenProps } from '@react-navigation/native-stack';

// O tipo Props injeta 'navigation' e 'route' com os tipos corretos
type Props = NativeStackScreenProps<RootStackParams, 'TelaInicial'>;

function TelaInicial({ navigation }: Props) {
  return (
    <View style={{ padding: 16 }}>
      <TouchableOpacity
        onPress={() =>
          // navigate: abre a tela e passa os parâmetros
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
  // route.params contém os dados passados pela tela anterior
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

**Métodos de navegação disponíveis:**

| Método | O que faz |
|---|---|
| `navigation.navigate('NomeDaTela', params)` | Abre a tela ou vai para ela se já existir na pilha |
| `navigation.push('NomeDaTela', params)` | Sempre empilha uma nova instância da tela |
| `navigation.goBack()` | Volta para a tela anterior |
| `navigation.popToTop()` | Volta para a tela raiz da pilha |
| `navigation.replace('NomeDaTela')` | Substitui a tela atual sem adicionar ao histórico |

Regra prática:

- `navigate` para ir até um destino conhecido
- `push` quando você quer abrir outra instância da mesma tela
- `replace` em fluxos como login, splash e onboarding, onde voltar não faz sentido

---

### 7.4 Bottom Tab Navigator

Abas inferiores funcionam melhor quando representam **grandes áreas do app**, não ações pontuais.

Boas abas:

- Início
- Busca
- Favoritos
- Perfil

Más abas:

- "Salvar"
- "Enviar"
- "Confirmar"

Abas organizam a navegação lateral entre áreas. Ações continuam sendo responsabilidade de botões dentro das telas.

```tsx
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import { Ionicons } from '@expo/vector-icons';

const Tab = createBottomTabNavigator();

function TabNavigator() {
  return (
    <Tab.Navigator
      screenOptions={({ route }) => ({
        // tabBarIcon: define o ícone de cada aba
        tabBarIcon: ({ focused, color, size }) => {
          const icones = {
            Inicio: focused ? 'home' : 'home-outline',
            Busca: focused ? 'search' : 'search-outline',
            Perfil: focused ? 'person' : 'person-outline',
          };
          return <Ionicons name={icones[route.name]} size={size} color={color} />;
        },
        tabBarActiveTintColor: '#007AFF',   // cor do item ativo
        tabBarInactiveTintColor: 'gray',    // cor dos itens inativos
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

### 7.5 Navegação aninhada

Navegação aninhada existe porque apps reais têm mais de um tipo de fluxo ao mesmo tempo. Um caso clássico é:

- uma estrutura principal por abas
- dentro de cada aba, uma pilha própria de telas

Isso evita misturar tudo em uma única pilha gigante e deixa o comportamento do botão voltar mais previsível.

```tsx
// Estrutura comum: abas que têm suas próprias pilhas de navegação
function AppNavigator() {
  return (
    <NavigationContainer>
      <Tab.Navigator>
        {/* Cada aba é um Stack Navigator independente */}
        <Tab.Screen name="InicioTab" component={InicioStackNavigator} />
        <Tab.Screen name="PerfilTab" component={PerfilStack} />
      </Tab.Navigator>
    </NavigationContainer>
  );
}

// Stack da aba "Início"
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

### 8.1 Context API

Quando múltiplas telas precisam do mesmo dado (usuário logado, carrinho de compras, tema), o estado precisa ser **global**.

O problema que o Context resolve é o **prop drilling**: passar a mesma informação por muitos níveis de componentes só para um filho distante conseguir usá-la.

Sem Context:

```text
App -> Layout -> Tela -> Header -> AvatarUsuario
```

Com Context, qualquer componente abaixo do Provider pode consumir o dado diretamente, sem esse repasse manual.

```tsx
// contexts/AuthContext.tsx
import { createContext, useContext, useState } from 'react';

// 1. Criar o contexto com o tipo dos dados
const AuthContext = createContext(null);

// 2. Provider: envolve o app e fornece os dados para todos os filhos
export function AuthProvider({ children }) {
  const [usuario, setUsuario] = useState(null);
  const [carregando, setCarregando] = useState(false);

  async function fazerLogin(email, senha) {
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

// 3. Hook customizado para consumir o contexto com verificação de segurança
export function useAuth() {
  const contexto = useContext(AuthContext);
  if (!contexto) throw new Error('useAuth deve ser usado dentro de AuthProvider');
  return contexto;
}
```

```tsx
// App.tsx — envolver o app com o Provider
export default function App() {
  return (
    <AuthProvider>
      <AppNavigator />
    </AuthProvider>
  );
}

// Em qualquer tela dentro do Provider:
function TelaHome() {
  const { usuario, fazerLogout } = useAuth();
  return <Text>Bem-vindo, {usuario?.nome}</Text>;
}
```

O Context funciona muito bem quando:

- o dado é realmente compartilhado
- a frequência de atualização não é absurda
- você quer algo nativo do React, sem biblioteca extra

Exemplos clássicos: autenticação, tema, idioma e preferências globais.

---

### 8.2 Zustand

Zustand é uma biblioteca de estado global moderna, com menos código que Redux e sem a verbosidade do Context API para estados complexos.

A principal diferença mental é esta:

- no Context, você costuma pensar em `Provider` + `value`
- no Zustand, você pensa em **store global** + **fatias de estado**

Outro benefício prático é que o componente pode selecionar só o que precisa da store, reduzindo re-renderizações desnecessárias.

```bash
npm install zustand
```

```tsx
// stores/useCarrinhoStore.ts
import { create } from 'zustand';

// create() define o estado e as ações em um único objeto
export const useCarrinhoStore = create((set, get) => ({
  itens: [],
  totalItens: 0,
  totalPreco: 0,

  // Ação: adiciona um produto ou incrementa a quantidade se já existir
  adicionarItem: (produto) => {
    const itensAtuais = get().itens; // get() acessa o estado atual
    const itemExistente = itensAtuais.find(i => i.id === produto.id);
    const novosItens = itemExistente
      ? itensAtuais.map(i => i.id === produto.id ? { ...i, quantidade: i.quantidade + 1 } : i)
      : [...itensAtuais, { ...produto, quantidade: 1 }];

    // set() atualiza o estado — mescla com o estado atual automaticamente
    set({
      itens: novosItens,
      totalItens: novosItens.reduce((acc, i) => acc + i.quantidade, 0),
      totalPreco: novosItens.reduce((acc, i) => acc + i.preco * i.quantidade, 0),
    });
  },

  limparCarrinho: () => set({ itens: [], totalItens: 0, totalPreco: 0 }),
}));

// Uso em qualquer componente — sem Provider, sem Context:
function BotaoCarrinho() {
  const totalItens = useCarrinhoStore(state => state.totalItens);
  const adicionarItem = useCarrinhoStore(state => state.adicionarItem);
  return <Text>Carrinho: {totalItens}</Text>;
}
```

| Biblioteca | Verbosidade | Curva de aprendizado | Quando usar |
|---|---|---|---|
| `Context API` | Média | Baixa | Estado simples (autenticação, tema) |
| `Zustand` | Baixa | Baixa | Estado global de média complexidade |
| `Redux Toolkit` | Alta | Alta | Apps enterprise, equipes grandes |
| `Jotai` | Muito baixa | Baixa | Estado atômico e granular |

Se você está começando:

- use `useState` para estado local
- use `Context API` para compartilhamentos simples
- use `Zustand` quando o app crescer e o estado global começar a exigir mais organização

---

## Parte 9 — Requisições HTTP

---

### 9.1 Fetch nativo

A API `fetch` está disponível nativamente no React Native — sem instalação adicional.

Ela é o ponto de partida ideal para entender o básico de HTTP:

- método (`GET`, `POST`, `PUT`, `DELETE`)
- headers
- corpo da requisição
- tratamento de status de resposta

O lado ruim é que quase tudo fica manual: tratamento de erro, timeout, interceptação, autenticação repetida e cache.

```tsx
// GET — buscar dados
async function buscarUsuarios() {
  const resposta = await fetch('https://api.exemplo.com/usuarios', {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`,
    },
  });

  // Verificar se a resposta foi bem-sucedida antes de parsear
  if (!resposta.ok) throw new Error(`Erro HTTP: ${resposta.status}`);
  return resposta.json();
}

// POST — enviar dados
async function criarTarefa(titulo) {
  const resposta = await fetch('https://api.exemplo.com/tarefas', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ titulo, concluida: false }), // objeto → string JSON
  });
  return resposta.json();
}
```

`fetch` é ótimo para aprender e para casos simples. Quando o projeto começa a repetir muita configuração de rede, vale migrar para uma camada mais estruturada.

---

### 9.2 Axios

Axios é um cliente HTTP com recursos adicionais como interceptors, timeout e cancelamento.

Na prática, Axios costuma entrar quando você quer uma camada de API mais profissional:

- instância centralizada
- configuração padrão compartilhada
- interceptors para token e tratamento global de erro
- menos código repetido em cada chamada

```bash
npm install axios
```

```tsx
// services/api.ts — instância configurada do Axios
import axios from 'axios';

export const api = axios.create({
  baseURL: 'https://api.exemplo.com',
  timeout: 10000,                            // timeout de 10 segundos
  headers: { 'Content-Type': 'application/json' },
});

// Interceptor de requisição — adiciona token em TODAS as requisições automaticamente
api.interceptors.request.use(async (config) => {
  const token = await AsyncStorage.getItem('token');
  if (token) config.headers.Authorization = `Bearer ${token}`;
  return config;
});

// Interceptor de resposta — trata erros 401 globalmente (sessão expirada)
api.interceptors.response.use(
  (resposta) => resposta, // sucesso: passa adiante sem modificar
  async (erro) => {
    if (erro.response?.status === 401) {
      await AsyncStorage.removeItem('token');
      navigation.navigate('Login');
    }
    return Promise.reject(erro); // repassa o erro para o catch() do chamador
  }
);
```

Pense no Axios como o "cliente de transporte" das requisições. Ele melhora a ergonomia da chamada HTTP, mas ainda não resolve sozinho o ciclo de vida completo dos dados na interface.

---

### 9.3 TanStack Query

TanStack Query (anteriormente React Query) é o padrão moderno para requisições HTTP. Gerencia automaticamente cache, estados de loading/error e revalidação.

O conceito mais importante aqui é distinguir dois tipos de estado:

- **estado de UI**: modal aberto, texto digitado, aba ativa
- **estado de servidor**: lista de produtos, perfil do usuário, pedidos vindos da API

TanStack Query foi criado para o segundo caso. Ele entende que dados de servidor podem ficar desatualizados, precisam de cache, refetch e invalidação.

```bash
npm install @tanstack/react-query
```

```tsx
import { QueryClient, QueryClientProvider, useQuery, useMutation, useQueryClient } from '@tanstack/react-query';

// QueryClient configura o comportamento padrão de todas as queries
const queryClient = new QueryClient({
  defaultOptions: {
    queries: {
      staleTime: 5 * 60 * 1000, // dados ficam "frescos" por 5 minutos
      retry: 2,                   // tenta novamente 2x em caso de erro
    },
  },
});

// QueryClientProvider envolve o app — vai em App.tsx junto com NavigationContainer
export default function App() {
  return (
    <QueryClientProvider client={queryClient}>
      <AppNavigator />
    </QueryClientProvider>
  );
}

// useQuery: busca e mantém dados em cache
function TelaProdutos() {
  const { data: produtos, isLoading, isError, error, refetch } = useQuery({
    queryKey: ['produtos'],           // chave única para identificar o cache
    queryFn: () => api.get('/produtos').then(r => r.data), // função que busca os dados
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

// useMutation: para POST/PUT/DELETE — invalida o cache após sucesso
function FormularioTarefa() {
  const queryClient = useQueryClient();

  const criarTarefaMutation = useMutation({
    mutationFn: (novaTarefa) => api.post('/tarefas', novaTarefa),
    onSuccess: () => {
      // Invalida o cache das tarefas → força recarregamento automático
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

| Abordagem | Cache automático | Loading state | Refetch automático | Complexidade |
|---|---|---|---|---|
| `fetch` nativo | ❌ | Manual | Manual | Baixa |
| `Axios` | ❌ | Manual | Manual | Baixa |
| `TanStack Query` | ✅ | Automático | Automático | Média |

Resumo honesto:

- `fetch` ensina os fundamentos
- `Axios` melhora a camada de transporte
- `TanStack Query` melhora a experiência de dados no app inteiro

É comum usar **Axios + TanStack Query** juntos: Axios faz a chamada HTTP; TanStack Query gerencia cache e sincronização.

---

## Parte 10 — Persistência de Dados

---

### 10.1 AsyncStorage

AsyncStorage é o equivalente ao `SharedPreferences` do Android — armazena pares chave/valor do tipo string que persistem mesmo após o app ser fechado.

Ele funciona bem para dados pequenos e simples, como:

- tema escuro/claro
- token de sessão não sensível
- flags de onboarding
- filtros e preferências do usuário

O ponto-chave é lembrar que tudo vira string. Quando o dado é objeto ou array, você precisa serializar com `JSON.stringify` e desserializar com `JSON.parse`.

```bash
npx expo install @react-native-async-storage/async-storage
```

```tsx
import AsyncStorage from '@react-native-async-storage/async-storage';

// Salvar string simples
await AsyncStorage.setItem('tema', 'escuro');

// Salvar objeto — deve ser convertido para JSON string antes
const usuario = { id: 1, nome: 'Ana' };
await AsyncStorage.setItem('usuario', JSON.stringify(usuario));

// Recuperar string simples
const tema = await AsyncStorage.getItem('tema');

// Recuperar objeto — deve ser convertido de volta com JSON.parse
const usuarioStr = await AsyncStorage.getItem('usuario');
const usuarioRecuperado = usuarioStr ? JSON.parse(usuarioStr) : null;

// Remover item específico
await AsyncStorage.removeItem('tema');

// Limpar tudo (use com cautela)
await AsyncStorage.clear();
```

> **Segurança:** AsyncStorage **não é criptografado**. Para dados sensíveis como tokens, use `react-native-keychain` ou `expo-secure-store`.

Se você precisa guardar poucas preferências e quer a solução mais simples possível, AsyncStorage costuma ser suficiente.

---

### 10.2 MMKV

MMKV é uma alternativa ao AsyncStorage com performance muito superior — é **síncrona** (sem await).

Isso significa que leitura e escrita acontecem de forma imediata, o que pode ser útil em fluxos que dependem de acesso muito frequente a dados locais.

Em geral, MMKV faz mais sentido quando:

- você lê e grava valores com muita frequência
- quer reduzir overhead de Promises
- o armazenamento local já virou parte importante da experiência do app

```bash
npm install react-native-mmkv
```

```tsx
import { MMKV } from 'react-native-mmkv';

const storage = new MMKV();

// MMKV é SÍNCRONO — sem await, sem try/catch obrigatório
storage.set('token', 'meu-jwt-token');
storage.set('contador', 42);

const token = storage.getString('token');    // string | undefined
const contador = storage.getNumber('contador'); // number | undefined

storage.delete('token');
```

| | AsyncStorage | MMKV |
|---|---|---|
| Tipo | Assíncrono (Promise) | Síncrono |
| Performance | Boa | Muito superior |
| Criptografia | Não | Sim (com configuração) |
| Tamanho máximo | Sem limite definido | Sem limite definido |

Em termos práticos:

- AsyncStorage prioriza simplicidade
- MMKV prioriza velocidade

Se o seu app ainda é pequeno, não existe problema nenhum em começar com AsyncStorage e migrar depois.

---

### 10.3 SQLite com expo-sqlite

SQLite é um banco de dados relacional local, embutido no dispositivo. Recomendado quando os dados têm estrutura tabular e relacionamentos.

Ele entra em cena quando chave/valor já não basta mais. Exemplos:

- app de estoque com produtos, categorias e movimentações
- app offline com várias tabelas relacionadas
- histórico grande que precisa de busca, filtro e paginação local

Se você precisa fazer consultas como "traga os 20 pedidos mais recentes do usuário X" ou "liste produtos com estoque abaixo de 10", um banco relacional faz muito mais sentido do que salvar tudo em JSON.

```bash
npx expo install expo-sqlite
```

```tsx
import * as SQLite from 'expo-sqlite';

// SQL de criação da tabela — IF NOT EXISTS evita erro se já existir
const SQL_CREATE_USUARIOS = `
  CREATE TABLE IF NOT EXISTS usuarios (
    id INTEGER PRIMARY KEY autoincrement,
    name varchar(255) NOT NULL,
    email varchar(255) NOT NULL
  )
`;

// db.js — arquivo centralizado de conexão (recomendado)
// Importar este arquivo em qualquer tela que precise do banco
let _db = null;

export default function openDB() {
  if (!_db) {
    _db = SQLite.openDatabaseSync('meuApp.sqlite');
    // Na primeira conexão, criar as tabelas se não existirem
    _db.withTransactionSync(() => {
      _db.execSync(SQL_CREATE_USUARIOS);
    });
  }
  return _db;
}
```

```tsx
// Uso em qualquer tela
import openDB from './db';

const db = openDB();

function MinhaTelaComBancoDados() {
  useEffect(() => {
    // getAllSync: retorna todas as linhas do SELECT
    const rows = db.getAllSync('SELECT * FROM usuarios', []);
    console.log(rows);
  }, []);

  async function inserirUsuario(nome, email) {
    // runAsync: INSERT, UPDATE, DELETE — retorna o ID inserido
    const resultado = await db.runAsync(
      'INSERT INTO usuarios (name, email) VALUES (?, ?)',
      [nome, email]  // parâmetros preparados — previnem SQL injection
    );
    console.log('ID inserido:', resultado.lastInsertRowId);
  }
}
```

**Métodos disponíveis no expo-sqlite:**

| Método | O que faz | Síncrono? |
|---|---|---|
| `db.execSync(sql)` | Executa SQL sem retorno | ✅ |
| `db.runSync(sql, params)` | INSERT/UPDATE/DELETE — retorna lastInsertRowId | ✅ |
| `db.getAllSync(sql, params)` | SELECT — retorna array de rows | ✅ |
| `db.execAsync(sql)` | Versão assíncrona de execSync | ❌ |
| `db.runAsync(sql, params)` | Versão assíncrona de runSync | ❌ |
| `db.getAllAsync(sql, params)` | Versão assíncrona de getAllSync | ❌ |

Guia de escolha rápida:

- poucos dados simples: `AsyncStorage`
- chave/valor com mais performance: `MMKV`
- dados estruturados, relacionais ou offline-first: `SQLite`

---

## Parte 11 — Permissões

---

### 11.1 Por que permissões existem

Por padrão, nenhum app tem permissão de acessar dados privados do usuário (câmera, galeria, localização, contatos, SMS) ou recursos que consomem bateria. Tanto Android quanto iOS exigem que o app solicite permissão explicitamente ao usuário antes de usar cada recurso.

No Expo, cada módulo possui seu próprio método de solicitação de permissão — não é necessário gerenciar manualmente o arquivo `AndroidManifest.xml` na maioria dos casos.

Do ponto de vista de produto, pedir permissão cedo demais é um erro comum. O ideal é solicitar a permissão **no momento em que ela faz sentido**.

Exemplo:

- ao abrir a tela pela primeira vez, explique por que a câmera será usada
- só então peça a permissão
- se o usuário negar, ofereça uma alternativa ou explique como continuar sem aquele recurso

Isso melhora a taxa de aceitação e evita a sensação de que o app está pedindo acesso "do nada".

---

### 11.2 Câmera e Galeria

O fluxo correto quase sempre é:

1. pedir a permissão
2. validar se foi concedida
3. abrir câmera ou galeria
4. tratar o caso em que o usuário cancela a ação

Perceba que permissão concedida não significa imagem selecionada. São etapas diferentes.

```bash
npx expo install expo-image-picker
```

```tsx
import * as ImagePicker from 'expo-image-picker';

async function selecionarDaGaleria() {
  // Passo 1: solicitar permissão — abre o diálogo nativo do SO
  const { status } = await ImagePicker.requestMediaLibraryPermissionsAsync();
  if (status !== 'granted') {
    Alert.alert('Permissão necessária para acessar a galeria');
    return;
  }

  // Passo 2: abrir o seletor de imagens
  const resultado = await ImagePicker.launchImageLibraryAsync({
    mediaTypes: ImagePicker.MediaTypeOptions.Images,
    allowsEditing: true,  // permite recorte antes de confirmar
    aspect: [1, 1],       // proporção do recorte
    quality: 0.8,         // compressão (0 a 1)
  });

  // Passo 3: usar a imagem selecionada (canceled = true se o usuário cancelou)
  if (!resultado.canceled) {
    const uri = resultado.assets[0].uri;
    // Usar o URI local da imagem
  }
}

async function tirarFoto() {
  const { status } = await ImagePicker.requestCameraPermissionsAsync();
  if (status !== 'granted') return;

  const resultado = await ImagePicker.launchCameraAsync({ quality: 0.8 });
  if (!resultado.canceled) {
    const uri = resultado.assets[0].uri;
  }
}
```

Boa prática: trate também o caso de negação permanente com uma mensagem clara, orientando o usuário a revisar a permissão nas configurações do sistema se quiser usar o recurso depois.

---

### 11.3 Localização

Localização merece cuidado especial porque é uma das permissões que mais impactam privacidade e bateria. Antes de pedir acesso, tenha clareza sobre qual necessidade existe:

- localização única para preencher endereço
- localização ao abrir o mapa
- acompanhamento contínuo em background

Cada caso tem implicações diferentes de UX e de consumo de recursos.

```bash
npx expo install expo-location
```

```tsx
import * as Location from 'expo-location';

async function obterLocalizacao() {
  // requestForegroundPermissionsAsync: permissão para uso enquanto o app está aberto
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

Se o seu app só precisa da localização uma vez, evite lógica contínua. Quanto mais simples o uso, mais fácil justificar a permissão para o usuário.

---

## Parte 12 — Frameworks de UI e Ícones

---

### 12.1 React Native Paper

React Native Paper implementa o Material Design 3 para React Native.

Ele é uma boa escolha quando você quer:

- componentes prontos e consistentes
- acessibilidade razoável já embutida
- velocidade para montar telas sem reinventar botão, input, card e app bar

O ganho principal não é só visual. É também de consistência entre telas.

```bash
npm install react-native-paper react-native-vector-icons
```

```tsx
import { Provider as PaperProvider, Button, TextInput, Card, Appbar } from 'react-native-paper';

// Envolver o app com PaperProvider em App.tsx
export default function App() {
  return (
    <PaperProvider>
      <AppNavigator />
    </PaperProvider>
  );
}

function TelaCadastro({ navigation }) {
  const [nome, setNome] = useState('');
  return (
    <View>
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
          <Button mode="contained" onPress={() => salvar()} style={{ marginTop: 16 }}>
            Salvar
          </Button>
        </Card.Content>
      </Card>
    </View>
  );
}
```

Frameworks de UI ajudam bastante no começo, mas não substituem entendimento de layout, estado e navegação. Pense neles como aceleradores, não como atalhos mágicos.

---

### 12.2 NativeWind

NativeWind permite usar classes Tailwind CSS no React Native.

Ele costuma agradar especialmente quem vem do front-end web e já pensa em utilitários como `p-4`, `rounded-xl` e `flex-row`.

O benefício principal é a velocidade de composição visual. O risco principal é exagerar na quantidade de classes e deixar componentes difíceis de ler. Se a linha de `className` começou a virar um parágrafo, talvez seja hora de extrair um componente ou organizar melhor os estilos.

```bash
npm install nativewind
npm install --save-dev tailwindcss
npx tailwindcss init
```

```tsx
// Com NativeWind, estilização via className (igual ao Tailwind na web)
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

### 12.3 Ícones

Ícones parecem detalhe, mas ajudam o usuário a reconhecer áreas do app com mais velocidade. Ainda assim, ícone bom quase sempre vem acompanhado de contexto, principalmente em navegação e ações críticas.

Boas práticas:

- mantenha uma família de ícones consistente
- use nomes semanticamente claros
- evite depender só do ícone quando o texto melhora compreensão
- preserve tamanho e área de toque adequados

```bash
# Expo (já incluso):
# @expo/vector-icons

# React Native CLI:
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

| Família | Quantidade de ícones | Estilo |
|---|---|---|
| `Ionicons` | ~1300 | iOS / Android |
| `MaterialIcons` | ~1000 | Material Design |
| `FontAwesome5` | ~1500 | Web clássico |
| `Feather` | ~280 | Minimalista |

---

## Parte 13 — Animações

---

### 13.1 Animated API

A Animated API é nativa do React Native e não requer instalação adicional.

Ela resolve muito bem animações simples de interface:

- feedback de toque
- fade in / fade out
- escala
- translação

Quando o objetivo é apenas deixar a interação mais agradável, ela costuma ser suficiente.

```tsx
import { Animated, TouchableOpacity, View, Text } from 'react-native';
import { useRef } from 'react';

function BotaoAnimado() {
  // useRef mantém a referência do valor animado entre re-renders
  const escala = useRef(new Animated.Value(1)).current;
  const opacidade = useRef(new Animated.Value(1)).current;

  function aoPresionar() {
    // Animated.parallel executa múltiplas animações simultaneamente
    Animated.parallel([
      // spring: animação com física (elasticidade)
      Animated.spring(escala, { toValue: 0.95, useNativeDriver: true }),
      // timing: animação linear com duração definida
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
    // Animated.View é o componente que aceita valores animados no style
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

O ponto mais importante sobre animação é intenção: animar para comunicar estado, foco, continuidade ou resposta ao toque. Animação sem função tende a cansar a experiência.

---

### 13.2 Reanimated 3

Reanimated 3 executa animações diretamente na thread nativa de UI, garantindo 60fps mesmo com carga na thread JavaScript.

Ele é indicado quando a animação deixa de ser decorativa e passa a ser uma parte importante da experiência:

- gestos complexos
- cards arrastáveis
- transições sofisticadas
- efeitos que precisam continuar fluidos mesmo sob carga

Em resumo: `Animated` cobre o básico com simplicidade; `Reanimated` cobre cenários mais exigentes com mais poder.

```bash
npm install react-native-reanimated
```

```tsx
import Animated, {
  useSharedValue,
  useAnimatedStyle,
  withSpring,
  withTiming
} from 'react-native-reanimated';

function CartaoAnimado() {
  // useSharedValue: valor que vive na thread nativa — sem Bridge
  const translateX = useSharedValue(0);
  const rotacao = useSharedValue(0);

  // useAnimatedStyle: estilo reativo que roda na thread de UI, não na JS thread
  const estilo = useAnimatedStyle(() => ({
    transform: [
      { translateX: translateX.value },
      { rotate: `${rotacao.value}deg` },
    ],
  }));

  return (
    <Animated.View style={[styles.cartao, estilo]}>
      <TouchableOpacity onPress={() => {
        // withSpring / withTiming: animações declarativas
        translateX.value = withSpring(100);
        rotacao.value = withTiming(15);
      }}>
        <Text>Animar</Text>
      </TouchableOpacity>
      <TouchableOpacity onPress={() => {
        translateX.value = withSpring(0);
        rotacao.value = withTiming(0);
      }}>
        <Text>Resetar</Text>
      </TouchableOpacity>
    </Animated.View>
  );
}
```

---

### 13.3 Lottie

Lottie renderiza animações vetoriais criadas no Adobe After Effects.

Ele é ótimo quando a animação vem do design, não do código. Em vez de programar cada frame, o app apenas reproduz um arquivo JSON exportado.

Casos comuns:

- tela de loading
- sucesso/erro de ação
- onboarding
- empty states mais expressivos

```bash
npm install lottie-react-native
```

```tsx
import LottieView from 'lottie-react-native';

function AnimacaoCarregamento() {
  return (
    <LottieView
      source={require('./assets/loading.json')} // arquivo JSON exportado do After Effects
      autoPlay    // inicia automaticamente
      loop        // repete indefinidamente
      style={{ width: 200, height: 200 }}
    />
  );
}
```

Sites para baixar animações Lottie gratuitas: [lottiefiles.com](https://lottiefiles.com) e [lordicon.com](https://lordicon.com).

---

## Parte 14 — TypeScript no React Native

TypeScript é o padrão do ecossistema React Native. O `create-expo-app` já gera projetos TypeScript por padrão.

O valor do TypeScript não está em “escrever mais código”, e sim em reduzir ambiguidades. Ele ajuda a responder cedo perguntas como:

- este campo pode ser `null`?
- essa função recebe string ou número?
- essa rota exige parâmetros?
- esse objeto realmente tem essa propriedade?

Em app mobile, onde muita coisa conversa com API, navegação, armazenamento e componentes, essa previsibilidade reduz bastante erros de runtime.

---

### 14.1 Tipagem de componentes e props

Tipar props significa formalizar o contrato do componente. Isso deixa claro:

- o que é obrigatório
- o que é opcional
- quais tipos são aceitos
- como o componente deve ser usado

Sem esse contrato, o componente vira uma “caixa preta” fácil de usar errado.

```tsx
// Interface declara o contrato do componente — o que ele aceita e o que é obrigatório
interface CardProdutoProps {
  produto: {
    id: number;
    nome: string;
    preco: number;
    imagem: string;
    disponivel: boolean;
  };
  onPress: (id: number) => void;  // callback tipado — obrigatório
  destaque?: boolean;              // ? = prop opcional (tem valor padrão)
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

### 14.2 Utility Types

Utility Types são atalhos prontos do TypeScript para transformar tipos já existentes. Eles ficam especialmente úteis quando o formato dos dados muda conforme o caso de uso.

Exemplo mental:

- o modelo completo do produto pode vir da API
- a tela de edição precisa só de parte dele
- o POST talvez não aceite `id`

Em vez de duplicar interfaces parecidas, você reaproveita o tipo original com regras de transformação.

```tsx
interface Produto {
  id: number;
  nome: string;
  preco: number;
  categoria: string;
  estoque: number;
}

// Partial<T>: todos os campos ficam opcionais — útil para payload de PATCH
type AtualizacaoProduto = Partial<Produto>;
// { id?: number; nome?: string; preco?: number; ... }

// Pick<T, K>: seleciona apenas os campos listados — útil para listagens
type ResumoProduto = Pick<Produto, 'id' | 'nome' | 'preco'>;
// { id: number; nome: string; preco: number }

// Omit<T, K>: remove os campos listados — útil para payload de POST (id gerado pelo servidor)
type NovoProduto = Omit<Produto, 'id'>;
// { nome: string; preco: number; categoria: string; estoque: number }

// Readonly<T>: impede modificação dos campos após criação
type ProdutoImutavel = Readonly<Produto>;
```

---

### 14.3 Tipagem de navegação

Tipar navegação é uma das maiores fontes de valor do TypeScript em React Native. Sem isso, é muito fácil:

- navegar para uma rota com nome errado
- esquecer um parâmetro obrigatório
- ler `route.params` assumindo campos que podem não existir

Com os tipos corretos, o editor passa a avisar esses erros antes mesmo de o app rodar.

```tsx
// navigation/types.ts — centraliza os tipos de todas as rotas
import { NativeStackScreenProps } from '@react-navigation/native-stack';

export type RootStackParams = {
  Login: undefined;                               // sem parâmetros
  Home: undefined;
  Detalhe: { produtoId: number; titulo: string }; // parâmetros obrigatórios
  Editar: { produtoId: number };
};

// Tipos prontos para usar em cada tela
export type LoginScreenProps   = NativeStackScreenProps<RootStackParams, 'Login'>;
export type DetalheScreenProps = NativeStackScreenProps<RootStackParams, 'Detalhe'>;
```

```tsx
// screens/Detalhe.tsx
import { DetalheScreenProps } from '../navigation/types';

function TelaDetalhe({ navigation, route }: DetalheScreenProps) {
  // TypeScript sabe exatamente quais campos existem em route.params
  const { produtoId, titulo } = route.params;
  // produtoId: number — TypeScript valida
  // titulo: string    — TypeScript valida

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

### 14.4 Tipagem de estado e hooks

Aqui o objetivo é fazer o tipo acompanhar o ciclo real do dado. Se um valor começa como `null` e só depois recebe dados da API, o tipo precisa representar essa possibilidade.

O mesmo vale para hooks:

- `useState` precisa refletir o formato do valor armazenado
- `useRef` precisa refletir o tipo da referência
- `useReducer` precisa refletir todos os estados e ações válidos

Quando essa modelagem está bem feita, o código fica mais seguro e também mais legível.

```tsx
// Estado com tipo explícito — necessário quando o valor inicial é null
const [usuario, setUsuario] = useState<Usuario | null>(null);
const [produtos, setProdutos] = useState<Produto[]>([]);

// useRef tipado — útil para referenciar componentes nativos
const inputRef = useRef<TextInput>(null);

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

## Parte 15 — Performance e Otimização

---

### 15.1 React.memo

Por padrão, quando um componente pai re-renderiza, **todos os filhos re-renderizam também**, mesmo que suas props não tenham mudado. `React.memo` memoriza o componente e só o re-renderiza se suas props mudarem.

Isso é útil quando o componente filho:

- é renderizado muitas vezes
- custa caro para montar
- recebe props estáveis com frequência

Mas existe um detalhe importante: `React.memo` não é um passe mágico. Se as props mudam de referência o tempo todo, ele não conseguirá ajudar.

```tsx
// Sem React.memo — re-renderiza sempre que o pai re-renderiza
function ItemLista({ titulo, onPress }) {
  console.log('Renderizando:', titulo);
  return <TouchableOpacity onPress={onPress}><Text>{titulo}</Text></TouchableOpacity>;
}

// Com React.memo — só re-renderiza se 'titulo' ou 'onPress' mudarem
const ItemListaMemo = React.memo(function ItemLista({ titulo, onPress }) {
  console.log('Renderizando:', titulo);
  return <TouchableOpacity onPress={onPress}><Text>{titulo}</Text></TouchableOpacity>;
});
```

---

### 15.2 O problema com funções inline em FlatList

Este é o erro de performance mais comum em React Native. Funções inline são recriadas a cada re-render, invalidando o `React.memo` dos filhos.

O problema real não é “usar arrow function é proibido”. O problema é criar uma nova referência em pontos críticos, especialmente em listas grandes. Em uma `FlatList`, isso se multiplica rápido.

Por isso, performance em React Native quase sempre passa por dois conceitos:

- virtualização da lista
- estabilidade de referências (`useCallback`, `useMemo`, props estáveis)

```tsx
// ❌ Problema — função inline cria nova referência a cada render do pai
function ListaTarefas({ tarefas, onRemover }) {
  return (
    <FlatList
      data={tarefas}
      renderItem={({ item }) => (
        // Nova função () => onRemover(item.id) criada a cada render
        // React.memo no ItemTarefa não tem efeito
        <ItemTarefa tarefa={item} onRemover={() => onRemover(item.id)} />
      )}
    />
  );
}

// ✅ Solução — useCallback estabiliza a referência da função
function ListaTarefas({ tarefas, onRemover }) {
  // handleRemover é a MESMA referência enquanto 'onRemover' não mudar
  const handleRemover = useCallback((id) => onRemover(id), [onRemover]);

  return (
    <FlatList
      data={tarefas}
      renderItem={({ item }) => (
        // handleRemover é estável → React.memo funciona corretamente
        <ItemTarefa tarefa={item} onRemover={handleRemover} />
      )}
    />
  );
}
```

---

### 15.3 Hermes

[Hermes](#hermes) é o motor JavaScript desenvolvido pela Meta especificamente para React Native. Ativo por padrão desde o RN 0.70.

Você raramente “programa para o Hermes” diretamente, mas ele influencia muito a experiência final porque melhora o tempo de inicialização e o uso de memória.

Na prática, isso significa que otimização em React Native não é só escrever componente melhor. Também envolve entender a plataforma de execução.

| Aspecto | JavaScriptCore (motor antigo) | Hermes |
|---|---|---|
| Compilação | JIT (Just-In-Time) | AOT (Ahead-Of-Time) — compila no build |
| Tempo de inicialização | Mais lento | Até 2× mais rápido |
| Consumo de memória | Maior | Menor |
| Tamanho do bundle | Maior | Menor |

Você não precisa configurar o Hermes — ele já está ativo. O que importa entender: o **bytecode gerado no build** é o que o dispositivo executa, não o JavaScript fonte.

---

### 15.4 Tabela de otimizações

Antes de aplicar qualquer técnica da tabela abaixo, vale uma regra simples: **otimize onde há gargalo observável**. Código mais “otimizado” nem sempre é mais legível, então a melhor otimização é a que resolve um problema real.

| Técnica | O que resolve | Quando aplicar |
|---|---|---|
| `React.memo` | Re-renders desnecessários | Componentes com props estáveis |
| `useCallback` | Instabilidade de referências de funções | Funções passadas como props, especialmente em `renderItem` |
| `useMemo` | Recálculo de dados derivados a cada render | Filtragem, ordenação, transformação de listas |
| `keyExtractor` estável | Reconciliação errada na FlatList | Sempre — use IDs únicos, nunca índices |
| `removeClippedSubviews` | Memória em listas muito longas | FlatLists com centenas de itens |
| `getItemLayout` | Scroll para posição sem medir | Listas com altura de item conhecida e fixa |

---

## Parte 16 — Testes

---

### 16.1 Estrutura de testes

Pensar em testes por camadas evita dois extremos ruins:

- testar tudo só por E2E, o que fica lento e caro
- testar só funções isoladas e nunca validar a experiência real da interface

Cada camada responde a perguntas diferentes:

- teste unitário: a regra funciona?
- teste de componente: a interface reage corretamente?
- teste E2E: o fluxo inteiro funciona no app rodando de verdade?

| Camada | Ferramenta | O que testa | Velocidade |
|---|---|---|---|
| Unitário | Jest | Funções puras, hooks, stores | Muito rápida |
| Componente | React Native Testing Library | Renderização e interação | Rápida |
| E2E | Detox | Fluxos completos no dispositivo | Lenta |

---

### 16.2 Jest — testes unitários

Jest é pré-configurado em todos os projetos Expo e React Native CLI.

Ele é a melhor ferramenta para testar lógica pura, porque esses testes são:

- rápidos
- baratos de manter
- fáceis de executar com frequência

Quanto menos dependência de UI, rede e ambiente externo um teste tiver, mais confiável e previsível ele tende a ser.

```tsx
// utils/formatarPreco.ts
export function formatarPreco(valor) {
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
});
```

**Testando um custom hook:**

```tsx
import { renderHook, act } from '@testing-library/react-native';
import { useContador } from '../useContador';

describe('useContador', () => {
  it('inicia com o valor padrão', () => {
    const { result } = renderHook(() => useContador());
    expect(result.current.valor).toBe(0);
  });

  it('incrementa corretamente', () => {
    const { result } = renderHook(() => useContador());

    // act() envolve qualquer ação que causa mudança de estado
    act(() => {
      result.current.incrementar();
    });

    expect(result.current.valor).toBe(1);
  });
});
```

---

### 16.3 React Native Testing Library

A React Native Testing Library testa o componente mais perto da perspectiva do usuário. Em vez de verificar detalhes internos, a ideia é validar:

- o que aparece na tela
- o que muda após interação
- se o usuário consegue acionar o comportamento esperado

Isso leva a testes mais resilientes do que verificar implementação interna ou estado privado do componente.

```bash
npm install --save-dev @testing-library/react-native
```

```tsx
import { render, fireEvent, screen } from '@testing-library/react-native';
import { BotaoLogin } from '../BotaoLogin';

describe('BotaoLogin', () => {
  it('renderiza o texto corretamente', () => {
    render(<BotaoLogin onPress={() => {}} />);
    expect(screen.getByText('Entrar')).toBeTruthy();
  });

  it('chama onPress quando pressionado', () => {
    const mockOnPress = jest.fn(); // função mock que registra chamadas
    render(<BotaoLogin onPress={mockOnPress} />);

    fireEvent.press(screen.getByText('Entrar')); // simula um toque

    expect(mockOnPress).toHaveBeenCalledTimes(1);
  });

  it('fica desabilitado quando carregando', () => {
    render(<BotaoLogin onPress={() => {}} carregando={true} />);
    const botao = screen.getByTestId('botao-login');
    expect(botao.props.accessibilityState.disabled).toBe(true);
  });
});
```

---

## Parte 17 — Tratamento de Erros em Produção

---

### 17.1 Error Boundaries

Um Error Boundary captura erros JavaScript durante a renderização de qualquer filho. Sem ele, um erro em qualquer componente derruba o app inteiro com tela branca.

Limitação importante: ele **não captura tudo**. Error Boundary não pega automaticamente:

- erros em event handlers
- erros em código assíncrono fora da renderização
- falhas nativas fora do escopo do React

Ou seja: ele é uma rede de proteção útil, mas não substitui tratamento de erro em API, formulários e side effects.

```tsx
// components/ErrorBoundary.tsx
// Deve ser um componente de CLASSE (class component) — não existe versão hook ainda
import React, { Component } from 'react';
import { View, Text, TouchableOpacity } from 'react-native';

export class ErrorBoundary extends Component {
  constructor(props) {
    super(props);
    this.state = { temErro: false, erro: null };
  }

  // Chamado quando qualquer filho lança um erro durante renderização
  static getDerivedStateFromError(erro) {
    return { temErro: true, erro };
  }

  // Chamado após o erro ser capturado — ideal para logar no Sentry
  componentDidCatch(erro, info) {
    console.error('ErrorBoundary capturou:', erro, info.componentStack);
  }

  render() {
    if (this.state.temErro) {
      if (this.props.fallback) {
        return this.props.fallback;
      }

      return (
        <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center', padding: 24 }}>
          <Text style={{ fontSize: 20, fontWeight: 'bold', marginBottom: 8 }}>Algo deu errado</Text>
          <Text style={{ color: '#666', marginBottom: 24 }}>{this.state.erro?.message}</Text>
          <TouchableOpacity
            style={{ backgroundColor: '#007AFF', padding: 16, borderRadius: 8 }}
            onPress={() => this.setState({ temErro: false, erro: null })}
          >
            <Text style={{ color: 'white', fontWeight: 'bold' }}>Tentar novamente</Text>
          </TouchableOpacity>
        </View>
      );
    }
    return this.props.children;
  }
}
```

```tsx
// App.tsx — aplicar em diferentes níveis de granularidade
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

Uma estratégia madura é combinar:

- Error Boundary para falhas de renderização
- `try/catch` para ações assíncronas
- Sentry para observabilidade em produção

---

### 17.2 Sentry

Sentry é a ferramenta padrão do mercado para capturar e monitorar erros em produção.

Ele não serve só para “guardar erro”. O valor real está em responder perguntas como:

- qual tela está quebrando mais?
- quantos usuários foram afetados?
- qual versão do app gerou o problema?
- qual ação aconteceu antes do erro?

```bash
npx expo install @sentry/react-native
```

```tsx
// App.tsx
import * as Sentry from '@sentry/react-native';

Sentry.init({
  dsn: 'https://sua-chave@sentry.io/projeto',
  tracesSampleRate: 0.1,                           // 10% das sessões como dados de performance
  enabled: process.env.NODE_ENV === 'production',  // desativo em desenvolvimento
});

export default Sentry.wrap(App); // envolve o app para capturar erros nativos também
```

```tsx
// Captura manual com contexto
try {
  await api.post('/pedido', dadosPedido);
} catch (erro) {
  Sentry.captureException(erro, {
    tags: { tela: 'Checkout', acao: 'criar_pedido' },
    extra: { usuarioId: usuario.id, totalItens: carrinho.totalItens },
  });
  Alert.alert('Erro', 'Não foi possível finalizar o pedido. Tente novamente.');
}
```

---

## Parte 18 — Formulários

---

### 18.1 React Hook Form + Zod

React Hook Form gerencia formulários com alta performance (evita re-renders a cada tecla). Zod valida os dados com segurança de tipo.

Esse par funciona muito bem porque cada biblioteca resolve uma parte diferente do problema:

- React Hook Form gerencia entrada, submissão e estado do formulário
- Zod descreve e valida o formato dos dados

Na prática, isso cria uma fonte de verdade mais consistente. O mesmo esquema usado para validar pode orientar os tipos do formulário.

No React Native, `Controller` aparece com frequência porque muitos inputs funcionam como componentes controlados e precisam de uma ponte entre a UI e o React Hook Form.

```bash
npm install react-hook-form zod @hookform/resolvers
```

```tsx
import { useForm, Controller } from 'react-hook-form';
import { z } from 'zod';
import { zodResolver } from '@hookform/resolvers/zod';

// Esquema de validação com Zod — funciona como especificação dos dados
const esquemaCadastro = z.object({
  nome: z.string().min(3, 'Nome deve ter pelo menos 3 caracteres'),
  email: z.string().email('E-mail inválido'),
  senha: z.string().min(8, 'Senha deve ter pelo menos 8 caracteres'),
  confirmarSenha: z.string(),
}).refine(data => data.senha === data.confirmarSenha, {
  message: 'As senhas não coincidem',
  path: ['confirmarSenha'],
});

// TypeScript infere o tipo automaticamente do esquema Zod
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
    // handleSubmit só chama onSubmit se todos os campos forem válidos
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
            {/* Exibe a mensagem de erro do Zod se houver */}
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

## Parte 19 — Build e Publicação

---

### 19.1 Os três ambientes Expo

| Ambiente | Como rodar | O que suporta | Quando usar |
|---|---|---|---|
| **Expo Go** | App da loja + QR code | Apenas SDK do Expo | Aprendizado e prototipagem |
| **Development Build** | Build customizado no dispositivo | SDK Expo + módulos nativos custom | Desenvolvimento de produção |
| **Standalone (produção)** | APK/AAB/IPA publicado | Tudo | Publicação nas lojas |

O erro mais comum de quem está começando é achar que Expo Go representa 100% do ambiente final. Ele é excelente para aprender e iterar rápido, mas não cobre todos os cenários nativos.

Pense assim:

- `Expo Go` acelera o início
- `Development Build` aproxima o desenvolvimento do app real
- `Standalone` é o artefato final que vai para a loja

---

### 19.2 EAS Build

EAS (Expo Application Services) é a plataforma de build em nuvem do Expo. O build acontece nos servidores da Expo — sem necessidade de Android Studio ou Xcode instalados localmente.

Isso muda bastante a experiência do time, porque a máquina do desenvolvedor deixa de ser o único lugar capaz de gerar build. O processo fica mais reproduzível e mais próximo de CI/CD.

```bash
npm install -g eas-cli
eas login
eas build:configure       # cria o arquivo eas.json
```

```json
// eas.json — perfis de build para cada ambiente
{
  "build": {
    "development": {
      "developmentClient": true,   // gera Development Build
      "distribution": "internal"   // não vai para a loja
    },
    "preview": {
      "distribution": "internal",
      "android": { "buildType": "apk" }  // APK para testes internos
    },
    "production": {
      "android": { "buildType": "app-bundle" }  // AAB — exigido pela Google Play
    }
  }
}
```

```bash
# Build de produção para Android
eas build --platform android --profile production

# Build de produção para iOS
eas build --platform ios --profile production

# Enviar diretamente para as lojas após o build
eas submit --platform android
eas submit --platform ios
```

Em equipes pequenas, EAS reduz muito o atrito operacional. Em equipes maiores, ele ajuda a padronizar ambientes e distribuição interna.

---

### 19.3 Build com React Native CLI

Para projetos sem Expo, o build é feito localmente com as ferramentas nativas.

Aqui o ganho é controle total. O custo é a complexidade maior de ambiente, assinatura, certificados e configuração específica por plataforma.

Em outras palavras:

- Expo simplifica bastante o caminho
- React Native CLI expõe mais diretamente o mundo nativo

```bash
# ── ANDROID ────────────────────────────────────────────

# 1. Gerar a keystore de assinatura (apenas uma vez)
keytool -genkey -v \
  -keystore meu-app.keystore \
  -alias meu-app \
  -keyalg RSA -keysize 2048 -validity 10000

# 2. Gerar o AAB de produção
cd android
./gradlew bundleRelease
# Saída: android/app/build/outputs/bundle/release/app-release.aab

# ── iOS (apenas macOS) ─────────────────────────────────
# Abrir no Xcode: Product → Archive → Distribute App → App Store Connect
```

---

## Parte 20 — Arquitetura e Boas Práticas

---

### 20.1 Estrutura de projeto escalável

Estrutura de pastas não é objetivo final; é ferramenta de organização. A melhor estrutura é a que facilita:

- encontrar arquivos
- isolar responsabilidades
- evoluir features sem espalhar lógica em muitos lugares

No começo, estruturas simples costumam vencer. Conforme o projeto cresce, separar por domínio, feature ou responsabilidade passa a fazer mais diferença.

```
src/
├── assets/           → imagens, fontes, animações Lottie
├── components/
│   ├── ui/           → componentes genéricos (Button, Card, Input)
│   └── domain/       → componentes do domínio (CardProduto, ItemTarefa)
├── screens/
│   ├── Home/
│   │   ├── index.tsx           → componente da tela (UI)
│   │   ├── useHomeViewModel.ts → lógica da tela (hooks, estado)
│   │   └── Home.styles.ts      → estilos separados
│   └── Produtos/
├── navigation/       → configuração de rotas e tipos
├── services/
│   ├── api.ts        → instância do Axios
│   └── produtos.ts   → funções de cada endpoint
├── stores/           → estado global (Zustand)
├── hooks/            → custom hooks reutilizáveis
├── utils/            → funções utilitárias puras (formatarPreco, validarCpf)
├── types/            → tipos TypeScript compartilhados
└── constants/        → constantes do app (cores, tamanhos, rotas)
```

---

### 20.2 Separação entre UI e lógica

Equivalente ao padrão MVVM do Android: a tela (View) não contém lógica; a lógica fica em um custom hook (ViewModel).

Isso não significa que a tela nunca terá nenhuma lógica. Significa apenas que regras mais complexas, chamadas de API, transformação de dados e ações reutilizáveis não precisam ficar misturadas ao JSX.

O benefício é duplo:

- a UI fica mais legível
- a lógica fica mais fácil de testar e reaproveitar

```tsx
// ── HOOK DE LÓGICA — equivalente ao ViewModel ─────────────────────────
// hooks/useTelaProdutos.ts
export function useTelaProdutos() {
  const { data: produtos, isLoading, isError, refetch } = useQuery({
    queryKey: ['produtos'],
    queryFn: () => produtosService.listar(),
  });

  const { mutate: adicionarAoCarrinho } = useMutation({
    mutationFn: carrinhoService.adicionar,
  });

  // Retorna apenas o que a tela precisa — encapsula a complexidade
  return { produtos: produtos ?? [], isLoading, isError, refetch, adicionarAoCarrinho };
}

// ── COMPONENTE DE UI — apenas renderização ─────────────────────────────
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

## Parte 21 — Mapa do Ecossistema

```
REACT NATIVE APP
│
├── SCAFFOLDING
│   ├── create-expo-app       → projeto Expo (recomendado para iniciantes)
│   └── react-native-cli      → projeto nativo completo
│
├── LINGUAGEM
│   └── TypeScript ✓          → padrão da indústria
│
├── UI E COMPONENTES
│   ├── react-native-paper    → Material Design 3
│   ├── gluestack-ui          → design system moderno
│   ├── nativewind            → Tailwind CSS
│   └── @expo/vector-icons    → ícones
│
├── NAVEGAÇÃO
│   └── react-navigation ✓
│       ├── native-stack
│       ├── bottom-tabs
│       └── drawer
│
├── ESTADO
│   ├── useState/useReducer   → estado local
│   ├── Context API           → estado global simples
│   └── Zustand ✓             → estado global moderno
│
├── REQUISIÇÕES HTTP
│   ├── Axios                 → cliente HTTP
│   └── TanStack Query ✓      → cache + loading + error automáticos
│
├── FORMULÁRIOS
│   ├── react-hook-form ✓
│   └── zod                   → validação de schemas
│
├── ARMAZENAMENTO
│   ├── AsyncStorage          → chave-valor assíncrono
│   ├── MMKV ✓                → chave-valor síncrono e rápido
│   └── expo-sqlite           → banco relacional local
│
├── ANIMAÇÕES
│   ├── Reanimated 3 ✓        → animações nativas 60fps
│   ├── Lottie                → animações vetoriais After Effects
│   └── Animated API          → animações simples nativas
│
├── MONITORAMENTO
│   └── Sentry ✓              → captura erros em produção
│
├── TESTES
│   ├── Jest ✓                → testes unitários
│   ├── React Native Testing Library ✓ → testes de componentes
│   └── Detox                 → testes E2E em dispositivos reais
│
└── BUILD E PUBLICAÇÃO
    ├── EAS Build ✓           → build em nuvem (Expo)
    └── Gradle / Xcode        → build local (React Native CLI)
```

---

## Glossário

| Termo | Definição |
|---|---|
| <a name="asyncstorage">**AsyncStorage**</a> | Biblioteca para armazenar pares chave-valor de forma assíncrona e persistente no dispositivo. Equivalente ao SharedPreferences do Android. Não criptografado. |
| <a name="bridge">**Bridge**</a> | Camada de comunicação entre a thread JavaScript e a thread nativa do React Native. Opera de forma assíncrona, em lote e serializada (JSON). Substituída gradualmente pelo JSI na Nova Arquitetura. |
| <a name="bundle">**Bundle**</a> | Arquivo único gerado pelo Metro Bundler que contém todo o código JavaScript da aplicação, compactado e otimizado para distribuição. |
| <a name="context-api">**Context API**</a> | API nativa do React para compartilhar dados entre componentes sem passar props manualmente em cada nível. Indicada para estado global simples. |
| <a name="custom-hook">**Custom Hook**</a> | Função JavaScript cujo nome começa com `use` e encapsula lógica de estado reutilizável. Permite compartilhar lógica entre componentes sem duplicação. |
| <a name="eas">**EAS (Expo Application Services)**</a> | Plataforma de build e distribuição em nuvem do Expo. Permite gerar APKs, AABs e IPAs sem necessidade de Android Studio ou Xcode instalados localmente. |
| <a name="expo">**Expo**</a> | Plataforma construída sobre o React Native que abstrai a configuração do ambiente nativo. Oferece SDK com APIs prontas para câmera, localização, notificações, etc. |
| <a name="expo-go">**Expo Go**</a> | App disponível na Google Play e App Store que permite rodar projetos Expo diretamente no celular via QR code, sem compilação nativa. |
| <a name="fabric">**Fabric**</a> | Sistema de renderização da Nova Arquitetura do React Native. Substitui o renderer antigo e elimina parte da Bridge, comunicando-se diretamente com a thread nativa via JSI. |
| <a name="flatlist">**FlatList**</a> | Componente de lista virtualizada do React Native. Renderiza apenas os itens visíveis na tela, economizando memória e melhorando performance. Equivalente ao LazyColumn do Jetpack Compose. |
| <a name="flexbox">**Flexbox**</a> | Sistema de layout do React Native. `flexDirection: 'column'` é o padrão (diferente do web onde é `row`). Todo componente `View` já é flex por padrão. |
| <a name="framework">**Framework**</a> | Conjunto de ferramentas, convenções e bibliotecas que provê uma estrutura para desenvolvimento de aplicações, impondo um modelo de organização e fluxo. |
| <a name="hermes">**Hermes**</a> | Motor JavaScript desenvolvido pela Meta especificamente para React Native. Utiliza compilação AOT (Ahead-Of-Time) no momento do build, resultando em inicialização mais rápida e menor consumo de memória. Ativo por padrão desde RN 0.70. |
| <a name="hooks">**Hooks**</a> | Funções especiais do React que permitem adicionar estado (`useState`), efeitos colaterais (`useEffect`) e outros recursos a componentes funcionais. Disponíveis desde o React 16.8. |
| <a name="javascript">**JavaScript**</a> | Linguagem de programação interpretada, multiparadigma, base do ecossistema React Native. Não tem relação com Java. O padrão atual é o ECMAScript (ES6+). |
| <a name="jsx">**JSX**</a> | Extensão de sintaxe do JavaScript que permite escrever estruturas semelhantes a HTML dentro do código JS. Compilado para chamadas `React.createElement()` pelo Babel. |
| <a name="jsi">**JSI (JavaScript Interface)**</a> | Substituto moderno da Bridge na Nova Arquitetura. Permite chamadas síncronas entre JavaScript e código nativo, eliminando a serialização JSON. |
| <a name="metro">**Metro Bundler**</a> | Empacotador (bundler) oficial do React Native. Responsável por transformar o código JavaScript e seus módulos em um único arquivo bundle para execução no dispositivo. |
| <a name="mmkv">**MMKV**</a> | Biblioteca de armazenamento chave-valor síncrono de alta performance para React Native. Baseada no MMKV do WeChat. Alternativa ao AsyncStorage com leitura/escrita síncrona. |
| <a name="native-modules">**Native Modules**</a> | Código nativo (Kotlin/Swift) exposto ao JavaScript via Bridge ou JSI. Permite acessar APIs do sistema operacional não disponíveis no core do React Native. |
| <a name="npm">**npm**</a> | Node Package Manager. Gerenciador de pacotes do ecossistema JavaScript. Usado para instalar dependências em projetos React Native via `npm install`. |
| <a name="paradigma-declarativo">**Paradigma Declarativo**</a> | Abordagem de programação onde o desenvolvedor descreve **o que** a UI deve mostrar, não **como** atualizar cada elemento. A UI é sempre uma função do estado: `UI = f(estado)`. |
| <a name="props">**Props**</a> | Abreviação de "properties". Parâmetros passados de um componente pai para um componente filho. São imutáveis dentro do componente filho. |
| <a name="react">**React**</a> | Biblioteca JavaScript da Meta para construção de interfaces de usuário declarativas com componentes reutilizáveis. Base sobre a qual o React Native é construído. |
| <a name="react-navigation">**React Navigation**</a> | Biblioteca de navegação padrão da comunidade React Native. Oferece Stack Navigator, Bottom Tab Navigator, Drawer Navigator e outros. |
| <a name="reanimated">**Reanimated 3**</a> | Biblioteca de animações para React Native que executa animações diretamente na thread nativa de UI via JSI, garantindo 60fps independente da carga na thread JavaScript. |
| <a name="reducer">**Reducer**</a> | Função pura que recebe o estado atual e uma ação, e retorna o novo estado. Padrão usado com `useReducer` para gerenciar estado complexo de forma previsível. |
| <a name="sentry">**Sentry**</a> | Plataforma de monitoramento de erros em produção. Captura, agrupa e notifica sobre exceções não tratadas em apps React Native em tempo real. |
| <a name="stylesheet">**StyleSheet**</a> | API do React Native para definição de estilos via objetos JavaScript. Substitui o CSS da web. Propriedades usam camelCase e valores sem unidades (px, rem, dp). |
| <a name="tanstack-query">**TanStack Query**</a> | Biblioteca para gerenciamento de estado de servidor em React Native. Gerencia automaticamente cache, estados de loading/error, refetch e paginação de requisições HTTP. |
| <a name="thread">**Thread**</a> | Linha de execução independente em um processo. O React Native opera com duas threads principais: a JS thread (lógica JavaScript) e a main thread (renderização nativa). |
| <a name="turbomodules">**TurboModules**</a> | Sistema de módulos nativos da Nova Arquitetura do React Native. Permite carregamento lazy e comunicação síncrona com código nativo via JSI, substituindo o sistema antigo de Native Modules. |
| <a name="typescript">**TypeScript**</a> | Superset tipado do JavaScript mantido pela Microsoft. Adiciona verificação de tipos em tempo de compilação, autocompletar e documentação inline. Padrão no ecossistema React Native moderno. |
| <a name="useeffect">**useEffect**</a> | Hook do React para execução de efeitos colaterais em componentes funcionais: chamadas de API, timers, subscrições. Aceita um array de dependências que controla quando o efeito é re-executado. |
| <a name="usememo">**useMemo**</a> | Hook do React que memoriza o resultado de uma função computacionalmente custosa. Só recalcula quando as dependências mudam. |
| <a name="usestate">**useState**</a> | Hook fundamental do React para declarar estado local em componentes funcionais. Retorna um par `[valorAtual, funcaoDeAtualização]`. Equivalente ao `mutableStateOf` + `remember` do Jetpack Compose. |
| <a name="view">**View**</a> | Componente de layout mais básico do React Native. Equivale à `<div>` do HTML. Contêiner invisível que organiza outros componentes e suporta Flexbox para posicionamento. |
| <a name="virtualizacao">**Virtualização**</a> | Técnica onde apenas os itens visíveis na tela são renderizados na memória, enquanto os demais são criados/destruídos conforme o scroll. Implementado pelo `FlatList` e `SectionList`. |
| <a name="webview">**WebView**</a> | Componente que renderiza conteúdo HTML dentro de um container nativo. Ionic e Capacitor usam WebView — os elementos visuais são HTML/CSS, não componentes nativos reais. |
| <a name="zustand">**Zustand**</a> | Biblioteca de gerenciamento de estado global para React/React Native com API minimalista. Não requer Provider ou Context. Os componentes se inscrevem diretamente nas fatias de estado que precisam. |
