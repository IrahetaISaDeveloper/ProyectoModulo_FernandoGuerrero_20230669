package JavierIraheta_20230669.JavierIraheta_20230669.Controller;

import JavierIraheta_20230669.JavierIraheta_20230669.Exceptions.ExceptionProveedorDuplicado;
import JavierIraheta_20230669.JavierIraheta_20230669.Exceptions.ExceptionProveedorNoEncontrado;
import JavierIraheta_20230669.JavierIraheta_20230669.Models.DTO.ProveedorDTO;
import JavierIraheta_20230669.JavierIraheta_20230669.Service.ProveedorService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/apiProveedores")
public class ProveedorController {
    @Autowired
    ProveedorService service;

    @GetMapping("/consultarDatos")
    public ResponseEntity<?> nuevoProveedor (@Valid @RequestBody ProveedorDTO json, HttpServletRequest request){
        try{
            ProveedorDTO respuesta = service.InsertarDatos(json);
            if (respuesta == null){
                return ResponseEntity.badRequest().body(Map.of(
                   "Status", "Insercion Fallida",
                   "errorType", "VALIDATION_ERROR",
                   "message", "Los datos no pudieron ser registrados"
                ));
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "Status", "Success",
                    "data", respuesta
            ));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "Status", "Server Error",
                    "message", "Error no controlado al registrar el usuario",
                    "detail", e.getMessage()

            ));
        }
    }

    @PutMapping("/editarProveedor/{id}")
    public ResponseEntity<?> modificarProveedor(
            @PathVariable Long id,
            @Valid @RequestBody ProveedorDTO json,
            BindingResult bindingResult
    ){
        if (bindingResult.hasErrors()){
            Map<String, String> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    errores.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errores);
        }
        try {
            ProveedorDTO dto = new service.actualizarProveedor(id, json);
            return ResponseEntity.ok(dto);
        }catch (ExceptionProveedorNoEncontrado e){
            return ResponseEntity.notFound().build();
        }catch (ExceptionProveedorDuplicado e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of(
                    "Error", "Datos Duplicados",
                    "Campo", e.getCampoDuplicado()
            ));
        }
    }


}
