package com.example.backendproject.payload.request;

public class UserAnswerRequest {
        private Long user;
        private Long id;
        private  Long score;

        private String serie ;

        public String getSerie() {
                return serie;
        }

        public void setSerie(String serie) {
                this.serie = serie;
        }

        public Long getUser() {
                return user;
        }

        public void setUser(Long user) {
                this.user = user;
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public Long getScore() {
                return score;
        }

        public void setScore(Long score) {
                this.score = score;
        }

        public Long getUserID() {
                return user;
        }

        public void setUserID(Long user) {
                this.user = user;
        }

        public Long getExerciceID() {
                return id;
        }

        public void setExerciceID(Long id) {
                this.id = id;
        }



}
