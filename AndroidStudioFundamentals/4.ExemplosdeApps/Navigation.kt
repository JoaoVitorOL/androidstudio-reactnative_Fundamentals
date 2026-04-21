package com.example.aulacontroller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.aulacontroller.ui.theme.AulaControllerTheme
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp
import android.R.attr.onClick
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource



var tempoInicio: Long = 0L
var tempoFinal: Long = 0L

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navigationController = rememberNavController()

            NavHost(
                navController = navigationController,
                startDestination = "/tela01"
            ) {

                // HOME
                composable("/tela01") {
                    Tela(
                        nomeDaTela = "Caça ao Tesouro",
                        pergunta = "",
                        respostaCorreta = "",
                        mostrarCampo = false,
                        mostrarImagem = false,
                        clickAnterior = {},
                        clickProximo = {
                            tempoInicio = System.currentTimeMillis()
                            navigationController.navigate("/tela02")

                        }
                    )
                }

                // PISTA 1
                composable("/tela02") {
                    Tela(
                        nomeDaTela = "Pista 1",
                        pergunta = "Qual o maior elo do Valorant?",
                        respostaCorreta = "Radiante",
                        mostrarCampo = true,
                        mostrarImagem = false,
                        clickAnterior = {
                            navigationController.popBackStack()
                        },
                        clickProximo = {
                            navigationController.navigate("/tela03")
                        }
                    )
                }

                // PISTA 2
                composable("/tela03") {
                    Tela(
                        nomeDaTela = "Pista 2",
                        pergunta = "Jogo de blocos sandbox mais vendido no mundo?",
                        respostaCorreta = "Minecraft",
                        mostrarCampo = true,
                        mostrarImagem = false,
                        clickAnterior = {
                            navigationController.popBackStack()
                        },
                        clickProximo = {
                            navigationController.navigate("/tela04")
                        }
                    )
                }

                // PISTA 3
                composable("/tela04") {
                    Tela(
                        nomeDaTela = "Pista 3",
                        pergunta = "Quantos jogos Resident evil já fez?",
                        respostaCorreta = "11",
                        mostrarCampo = true,
                        mostrarImagem = false,
                        clickAnterior = {
                            navigationController.popBackStack()
                        },
                        clickProximo = {
                            navigationController.navigate("/tela05")
                        }
                    )
                }

                composable("/tela05") {
                    Tela(
                        nomeDaTela = "Pista 4",
                        pergunta = "Qual foi o placar da Copa do mundo de 2014?",
                        respostaCorreta = "7x1",
                        mostrarCampo = true,
                        mostrarImagem = false,
                        clickAnterior = {
                            navigationController.popBackStack()
                        },
                        clickProximo = {
                            tempoFinal = System.currentTimeMillis()
                            navigationController.navigate("/tela06")
                        }
                    )
                }

                // TESOURO
                composable("/tela06") {
                    Tela(
                        nomeDaTela = "!! TESOURO ENCONTRADO !!",
                        pergunta = "",
                        respostaCorreta = "",
                        mostrarCampo = false,
                        mostrarImagem = true,
                        clickAnterior = {},
                        clickProximo = {
                            navigationController.navigate("/tela01") {
                                popUpTo("/tela01") { inclusive = true }
                            }
                        }
                    )
                }
            }
        }
    }
}
@Composable
fun Tela(
    nomeDaTela: String,
    pergunta: String,
    respostaCorreta: String,
    mostrarCampo: Boolean,
    mostrarImagem: Boolean,
    clickAnterior: () -> Unit,
    clickProximo: () -> Unit
) {

    var resposta by remember { mutableStateOf("") }
    var erro by remember { mutableStateOf(false) }
    val tempoTotal = (tempoFinal - tempoInicio) / 1000

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(text = nomeDaTela, fontSize = 28.sp)

        Spacer(modifier = Modifier.height(16.dp))

        if (mostrarImagem) {
            Image(
                painter = painterResource(id = R.drawable.tesouro),
                contentDescription = "Tesouro",
                modifier = Modifier.height(200.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text("Tempo total: ${tempoTotal} segundos")
        }

        if (mostrarCampo) {

            Text(pergunta)

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = resposta,
                onValueChange = { resposta = it }
            )

            if (erro) {
                Text("Resposta incorreta")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                if (resposta.trim().equals(respostaCorreta.trim(), ignoreCase = true)) {
                    erro = false
                    clickProximo()
                } else {
                    erro = true
                }
            }) {
                Text("Próxima")
            }

        } else {
            Button(onClick = clickProximo) {
                Text("Continuar")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (mostrarCampo) {
            Button(onClick = clickAnterior) {
                Text("Voltar")
            }
        }
    }
}
