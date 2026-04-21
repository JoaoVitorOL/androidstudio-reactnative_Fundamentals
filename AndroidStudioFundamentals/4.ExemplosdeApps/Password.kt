data class Requisito(val mensagemErro: String, val validacao: (String) -> Boolean)

fun main() {

    val listaDeRegras = listOf(
        Requisito("Mínimo 4 caracteres") { it.length > 4 },
        Requisito("Deve conter o ano do Hexa (2026)") { it.contains("2026") },
        Requisito("Deve conter pelo menos uma letra maiúscula") {it.any{char -> char.isUpperCase() }},
        Requisito("Deve conter pelo menos um número") {it.any{char -> char.isDigit() }},
        Requisito("Não pode ter uma soma de caracteres divisível por 2") { it.length % 2 != 0 },
        Requisito("Deve conter número ímpar de vogais") { val vogais = "aeiouAEIOU"
            val quantidadeVogais = it.count { char -> char in vogais }
            quantidadeVogais % 2 != 0 },
        Requisito("Deve conter a palavra 'satc' (case insensitive)") {
            it.contains("satc", ignoreCase = true)
        },
        Requisito("Deve conter pelo menos um emoji ❄") {
            it.any { char ->
                char in listOf('❄')
            }
        },
        Requisito("Deve conter ter uma soma de caracteres divisível por 5") { it.length % 5 == 0 },
        Requisito("Deve conter conter a palavra VALORANT ou COUNTERSTRIKE escrita ao contrário") { var palavra = "VALORANT"
            var palavra2 = "COUNTERSTRIKE"

            it.contains(palavra.reversed()) or it.contains(palavra2.reversed())  })

    var senhaAprovada = false

    do {
        println("\nDigite sua senha:")
        val entrada = readLine() ?: ""

        var erroEncontrado: String? = null

        for (regra in listaDeRegras) {
            if (!regra.validacao(entrada)) {
                erroEncontrado = regra.mensagemErro
                break
            }
        }

        if (erroEncontrado != null) {
            println("Mensagem de erro: $erroEncontrado")
        } else {
            println("Sucesso! Senha aprovada")
            senhaAprovada = true
        }

    } while (!senhaAprovada)
}
