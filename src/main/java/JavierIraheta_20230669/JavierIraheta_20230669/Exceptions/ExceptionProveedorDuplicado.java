package JavierIraheta_20230669.JavierIraheta_20230669.Exceptions;

import lombok.Getter;

public class ExceptionProveedorDuplicado extends RuntimeException {
    @Getter
    private String campoDuplicado;


    public ExceptionProveedorDuplicado(String message, String campo) {
        super(message);
        this.campoDuplicado = campo;
    }

    public ExceptionProveedorDuplicado(String message){super(message);};
}
