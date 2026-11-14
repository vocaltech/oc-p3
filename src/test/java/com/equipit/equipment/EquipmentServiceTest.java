package com.equipit.equipment;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EquipmentServiceTest {

    @Mock
    private EquipmentRepository repository;

    @InjectMocks
    private EquipmentService service;

    @Test
    void givenTwoEquipments_whenFindAll_thenReturnBoth() {
        Equipment laptop = new Equipment("Ordinateur portable", "Informatique");
        Equipment projector = new Equipment("Vidéo-projecteur", "Audiovisuel");
        when(repository.findAll()).thenReturn(List.of(laptop, projector));

        List<Equipment> result = service.findAll();

        assertThat(result).containsExactly(laptop, projector);
        verify(repository).findAll();
    }

    @Test
    void givenExistingEquipment_whenFindById_thenReturnEquipment() {
        Equipment laptop = new Equipment("Ordinateur portable", "Informatique");
        when(repository.findById(1L)).thenReturn(Optional.of(laptop));

        Equipment result = service.findById(1L);

        assertThat(result).isEqualTo(laptop);
        assertThat(result.getId()).isNull();
        verify(repository).findById(1L);
    }

    @Test
    void givenUnknownEquipment_whenFindById_thenThrowIllegalArgument() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.findById(99L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Unknown equipment: 99");
    }

    @Test
    void givenAvailableEquipment_whenBorrowEquipment_thenMarkAsBorrowed() {
        Equipment laptop = new Equipment("Ordinateur portable", "Informatique");
        when(repository.findById(1L)).thenReturn(Optional.of(laptop));

        Equipment result = service.borrowEquipment(1L, "Jean", "Dupont");

        assertThat(result.isAvailable()).isFalse();
        assertThat(result.getBorrowerFirstName()).isEqualTo("Jean");
        assertThat(result.getBorrowerLastName()).isEqualTo("Dupont");
        verify(repository).findById(1L);
    }

    @Test
    void givenBorrowedEquipment_whenReturnEquipment_thenMarkAsAvailable() {
        Equipment laptop = new Equipment("Ordinateur portable", "Informatique");
        laptop.setAvailable(false);
        laptop.setBorrowerFirstName("Jean");
        laptop.setBorrowerLastName("Dupont");
        when(repository.findById(1L)).thenReturn(Optional.of(laptop));

        Equipment result = service.returnEquipment(1L);

        assertThat(result.isAvailable()).isTrue();
        assertThat(result.getBorrowerFirstName()).isNull();
        assertThat(result.getBorrowerLastName()).isNull();
        verify(repository).findById(1L);
    }
}
