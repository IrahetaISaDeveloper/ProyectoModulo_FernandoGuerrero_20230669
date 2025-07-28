package JavierIraheta_20230669.JavierIraheta_20230669.Service;

import JavierIraheta_20230669.JavierIraheta_20230669.Entities.ProveedorEntity;
import JavierIraheta_20230669.JavierIraheta_20230669.Exceptions.ExceptionProveedorNoEncontrado;
import JavierIraheta_20230669.JavierIraheta_20230669.Exceptions.ExceptionProveedorNoRegistrado;
import JavierIraheta_20230669.JavierIraheta_20230669.Models.DTO.ProveedorDTO;
import JavierIraheta_20230669.JavierIraheta_20230669.Repositories.ProveedorRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProveedorService {

    @Autowired
    ProveedorRepository repo;

    //Metodo para obtener los proveedores de la base de datos en formato DTO
    public List<ProveedorDTO> obtenerProveedores(){
        List<ProveedorEntity> lista = repo.findAll();
        return lista.stream()
                .map(this :: convertirADTO)
                .collect(Collectors.toList());
    }

    //Metodo para convertir los datos de entity provenientes de la base a formato DTO
    private ProveedorDTO convertirADTO(ProveedorEntity entity){
        ProveedorDTO dto = new ProveedorDTO();
        dto.setId(entity.getId());
        dto.setProviderName(entity.getProviderName());
        dto.setProviderPhone(entity.getProviderPhone());
        dto.setProviderAddress(entity.getProviderAddress());
        dto.setProviderEmail(entity.getProviderEmail());
        dto.setProviderCode(entity.getProviderCode());
        dto.setProviderStatus(entity.getProviderStatus());
        dto.setProviderComments(entity.getProviderComments());
        return dto;
    }

    //Metodo para insertar los datos en la base de datos por medio del DTO verificando si no hay datos nulos y convirtiendolo a formato entity para poder ingresarlos en la base
    public ProveedorDTO insertarDatos (@Valid ProveedorDTO json){
        if (json == null || json.getProviderName() == null || json.getProviderName().isEmpty()){
            throw new IllegalArgumentException("Proveedor contiene nulos");
        }
        try{
            ProveedorEntity entity = convertirAEntity(json);
            ProveedorEntity respuesta = repo.save(entity);
            return convertirADTO(respuesta);
        }catch (Exception e){
            log.error("Error con Proveedor: " + e.getMessage());
            throw new ExceptionProveedorNoRegistrado("Error al registrar");
        }
    }
    //Metodo para convertir los datos DTO a formato Entity
    private ProveedorEntity convertirAEntity (@Valid ProveedorDTO json){
        ProveedorEntity entity = new ProveedorEntity();
        entity.setProviderName(json.getProviderName());
        entity.setProviderPhone(json.getProviderPhone());
        entity.setProviderAddress(json.getProviderAddress());
        entity.setProviderEmail(json.getProviderEmail());
        entity.setProviderCode(json.getProviderCode());
        entity.setProviderStatus(json.getProviderStatus());
        entity.setProviderComments(json.getProviderComments());
        return entity;
    }

    //Metodo para actualizar el proveedor por medio del id
    public ProveedorDTO actualizarProveedor (Long id, @Valid ProveedorDTO json){
        ProveedorEntity proveedorExiste = repo.findById(id).orElseThrow( ()
                -> new ExceptionProveedorNoEncontrado("Proveedor no encontrado"));

                proveedorExiste.setProviderName(json.getProviderName());
                proveedorExiste.setProviderPhone(json.getProviderPhone());
                proveedorExiste.setProviderAddress(json.getProviderAddress());
                proveedorExiste.setProviderEmail(json.getProviderEmail());
                proveedorExiste.setProviderCode(json.getProviderCode());
                proveedorExiste.setProviderStatus(json.getProviderStatus());
                proveedorExiste.setProviderComments(json.getProviderComments());

        ProveedorEntity proveedorActualizado = repo.save(proveedorExiste);
        return convertirADTO(proveedorActualizado);
    }
}


