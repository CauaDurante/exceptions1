package model.exception;

public class DomainException extends Exception{

    //Construtor passando a mensagem para o construtor da superclasse
    public DomainException(String msg){
        super(msg);
    }

}
