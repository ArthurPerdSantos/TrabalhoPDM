package com.example.trabalhopdm.utils;

public class TextosParaAjuda {
    public String Batimentos(String estado) {
        String texto_taquicardia =
                "1- Ficar em pé e dobrar o tronco em direção às pernas;\n" +
                "2- Colocar compressa gelada no rosto;\n" +
                "3- Tossir com força 5 vezes;\n" +
                "4- Assoprar soltando o ar lentamente com a boca semi-fechada 5 vezes;\n" +
                "5- Respirar fundo, inspirando pelo nariz e deitando o ar pela boca lentamente 5 vezes;\n" +
                "6- Fazer contagem dos números do 60 para o 0, de forma lenta e olhando para cima.\n\n" +
                "Caso a sua pressão não normalize em até 30 minutos, ou o paciente sinta dormência de um lado do corpo ou desmaie, uma ambulância deve ser chamada imediatamente";

        String texto_bradicardia =
                "Caso seja detectado uma bradicardia, que é o coração bater de forma lenta, o paciente" +
                        "deve ir imediatamente a um cardiologista para ser instruido da forma correta";
        String texto_normal =
                "O seu batimento está em um nível normal";

        if (estado.equals("Taquicardia"))
            return texto_taquicardia;
        if (estado.equals("Bradicardia"))
            return texto_bradicardia;
        else
            return texto_normal;

    }
}
