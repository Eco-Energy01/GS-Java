package br.com.fiap.ecoenergy.exception;

public class IdNaoEncontradoException extends RuntimeException {
    public IdNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
