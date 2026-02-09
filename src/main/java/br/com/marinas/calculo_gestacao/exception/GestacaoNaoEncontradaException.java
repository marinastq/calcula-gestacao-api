package br.com.marinas.calculo_gestacao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,
        reason = "Gestacao nao encontrada")
public class GestacaoNaoEncontradaException extends Exception{

    public GestacaoNaoEncontradaException(Long id) {
        super("Gestacao nao encontrada: "+ id);
    }
}
