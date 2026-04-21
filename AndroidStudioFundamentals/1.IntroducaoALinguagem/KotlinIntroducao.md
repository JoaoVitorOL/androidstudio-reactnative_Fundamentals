# 🚀 Guia Técnico: Fundamentos da Linguagem Kotlin
> **Baseado no estilo didático do material fornecido (Jetpack Compose)**  
> **Nível:** Zero → Intermediário  
> **Foco:** Fundamentos + Comparação com Java e outras linguagens

---

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
