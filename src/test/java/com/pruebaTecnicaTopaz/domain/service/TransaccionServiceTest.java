package com.pruebaTecnicaTopaz.domain.service;

import com.pruebaTecnicaTopaz.exception.TransaccionEnEstadoIncorrectoException;
import com.pruebaTecnicaTopaz.exception.TransaccionVencidaException;
import com.pruebaTecnicaTopaz.persistence.entity.Transaccion;
import com.pruebaTecnicaTopaz.persistence.repository.TransaccionRepository;
import com.pruebaTecnicaTopaz.util.EstadoTransaccion;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransaccionServiceTest {

    @Mock
    private TransaccionRepository transaccionRepository;

    @InjectMocks
    private TransaccionService transaccionService;

    @Test
    void noDebeGuardarCuandoLaTransaccionNoExiste() {
        Long id = 1L;
        when(transaccionRepository.findById(id)).thenReturn(Optional.empty());

        transaccionService.reversar(id);

        verify(transaccionRepository).findById(id);
        verify(transaccionRepository, never()).save(org.mockito.ArgumentMatchers.any(Transaccion.class));
    }

    @Test
    void debeReversarYGuardarUnaTransaccionAprobadaVigente() {
        Transaccion transaccion = transaccion(EstadoTransaccion.APROBADA, LocalDateTime.now().minusHours(23));
        when(transaccionRepository.findById(1L)).thenReturn(Optional.of(transaccion));

        transaccionService.reversar(1L);

        assertThat(transaccion.getEstado()).isEqualTo(EstadoTransaccion.REVERSADA);
        verify(transaccionRepository).save(transaccion);
    }

    @Test
    void debeLanzarExcepcionCuandoLaTransaccionEstaVencida() {
        Transaccion transaccion = transaccion(EstadoTransaccion.APROBADA, LocalDateTime.now().minusHours(25));
        when(transaccionRepository.findById(1L)).thenReturn(Optional.of(transaccion));

        assertThatThrownBy(() -> transaccionService.reversar(1L))
                .isInstanceOf(TransaccionVencidaException.class)
                .hasMessage("Transacción no puede ser reversada, ya que fue creada hace más de 24 horas.");

        verify(transaccionRepository, never()).save(org.mockito.ArgumentMatchers.any(Transaccion.class));
    }

    @Test
    void debeLanzarExcepcionCuandoLaTransaccionNoEstaAprobada() {
        Transaccion transaccion = transaccion(EstadoTransaccion.PENDIENTE, LocalDateTime.now().minusHours(1));
        when(transaccionRepository.findById(1L)).thenReturn(Optional.of(transaccion));

        assertThatThrownBy(() -> transaccionService.reversar(1L))
                .isInstanceOf(TransaccionEnEstadoIncorrectoException.class)
                .hasMessage("Transacción en estado incorrecto para reversar. Estado actual: PENDIENTE");

        verify(transaccionRepository, never()).save(org.mockito.ArgumentMatchers.any(Transaccion.class));
    }

    private Transaccion transaccion(EstadoTransaccion estado, LocalDateTime fechaCreacion) {
        Transaccion transaccion = new Transaccion();
        transaccion.setEstado(estado);
        transaccion.setFechaCreacion(fechaCreacion);
        return transaccion;
    }
}
