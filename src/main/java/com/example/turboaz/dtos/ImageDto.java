package com.example.turboaz.dtos;

import com.example.turboaz.models.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageDto {
    private Long id;
    private String url;

    public ImageDto(Image image){
        this.id = image.getId();
        this.url = image.getUrl();
    }
}
