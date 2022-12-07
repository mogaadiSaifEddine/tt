package com.example.backendproject.payload.request;
public class UserAnswerMinRequest {
    private Long exercice_block_id;
    private String value;


    public UserAnswerMinRequest() {
    }

    public UserAnswerMinRequest(Long exercice_block_id, String value) {
        this.exercice_block_id = exercice_block_id;
        this.value = value;
    }

    public Long getExercice_block_id() {
        return exercice_block_id;
    }

    public void setExercice_block_id(Long exercice_block_id) {
        this.exercice_block_id = exercice_block_id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
