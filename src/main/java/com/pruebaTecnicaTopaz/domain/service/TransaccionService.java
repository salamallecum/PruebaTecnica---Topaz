package com.pruebaTecnicaTopaz.domain.service;

import com.pruebaTecnicaTopaz.exception.TransaccionEnEstadoIncorrectoException;
import com.pruebaTecnicaTopaz.exception.TransaccionNoEncontradaException;
import com.pruebaTecnicaTopaz.exception.TransaccionVencidaException;
import com.pruebaTecnicaTopaz.persistence.entity.Transaccion;
import com.pruebaTecnicaTopaz.persistence.repository.TransaccionRepository;
import com.pruebaTecnicaTopaz.util.EstadoTransaccion;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransaccionService {

    private final TransaccionRepository transaccionRepository;

    public TransaccionService(TransaccionRepository transaccionRepository) {
        this.transaccionRepository = transaccionRepository;
    }

    public void reversar(Long id) {
        //Consultamos que la transacción existe
        Optional<Transaccion> transaccion = transaccionRepository.findById(id);
        if(!transaccion.isEmpty()){
            Transaccion transac = transaccion.get();

            //Validamos que la fecha de la transaccion sea inferior a 24 horas
            if(transac.getFechaCreacion().isBefore(java.time.LocalDateTime.now().minusHours(24))){
                throw new TransaccionVencidaException("Transacción no puede ser reversada, ya que fue creada hace más de 24 horas.");
            }else{
                //Validamos que la transaccion esté en estado APROBADA
                if(transac.getEstado().equals(EstadoTransaccion.APROBADA)){
                    //Cambiamos el estado a REVERSADA
                    transac.setEstado(EstadoTransaccion.REVERSADA);
                    //Guardamos la transacción
                    transaccionRepository.save(transac);
                } else {
                    throw new TransaccionEnEstadoIncorrectoException("Transacción en estado incorrecto para reversar. Estado actual: " + transac.getEstado());
                }
            }
        }else{
            throw new TransaccionNoEncontradaException("Transacción con id " + id + " no encontrada.");
        }
    }
}

