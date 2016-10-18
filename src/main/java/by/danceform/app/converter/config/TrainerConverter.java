package by.danceform.app.converter.config;

import by.danceform.app.converter.AbstractConverter;
import by.danceform.app.domain.config.Organization;
import by.danceform.app.domain.config.Trainer;
import by.danceform.app.dto.config.OrganizationDTO;
import by.danceform.app.dto.config.TrainerDTO;
import org.springframework.stereotype.Component;

/**
 * Created by Dmitry_Shanko on 10/17/2016.
 */
@Component("trainerConverter")
public class TrainerConverter extends AbstractConverter<Trainer, TrainerDTO, Long> {

    @Override
    protected TrainerDTO convertEntityToDto(Trainer entity, TrainerDTO dto) {
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setVisible(entity.isVisible());
        return dto;
    }

    @Override
    protected Trainer convertDtoToEntity(TrainerDTO dto, Trainer entity) {
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setVisible(dto.isVisible());
        return entity;
    }

    @Override
    protected TrainerDTO getNewDTO() {
        return new TrainerDTO();
    }

    @Override
    protected Trainer getNewEntity() {
        return new Trainer();
    }
}