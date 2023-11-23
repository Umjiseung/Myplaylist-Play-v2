package com.project.playlist.domain.playlist.data.dto.request;


import com.project.playlist.domain.playlist.data.entity.Category;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;



@Getter
@NoArgsConstructor
public class PlayListWriteRequest {

    @NotBlank
    private String studentId;
    @NotBlank
    private String studentName;
    @NotBlank
    private String musicName;
    @NotBlank
    private String musicURL;
    @NotBlank
    private String musicContent;
    @NotBlank
    private Category category;
    @NotBlank
    private String playListPW;

}
