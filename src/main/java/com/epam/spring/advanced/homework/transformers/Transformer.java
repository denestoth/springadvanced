package com.epam.spring.advanced.homework.transformers;

import java.util.List;

/**
 * @author Denes_Toth
 */
public interface Transformer<DTO, VIEW> {
    DTO viewToDto(VIEW view);

    List<DTO> viewsToDtos(List<VIEW> views);

    VIEW dtoToView(DTO dto);

    List<VIEW> dtosToViews(List<DTO> dtos);

}