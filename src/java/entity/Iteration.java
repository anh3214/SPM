package entity;

public class Iteration {

    int iteration_id;
    int subject_id;
    String iteration_name;
    int duration;
    int iteration_weight;
    int is_ongoin;
    String subject_code;
    String subject_name;
    String author_name;
    int author_id;
    
    public int getIteration_weight() {
        return iteration_weight;
    }

    public void setIteration_weight(int iteration_weight) {
        this.iteration_weight = iteration_weight;
    }

    public int getIs_ongoin() {
        return is_ongoin;
    }

    public void setIs_ongoin(int is_ongoin) {
        this.is_ongoin = is_ongoin;
    }
    int status;

    public Iteration() {
    }

    public Iteration(int iteration_id, int subject_id, String iteration_name, int duration, int status) {
        this.iteration_id = iteration_id;
        this.subject_id = subject_id;
        this.iteration_name = iteration_name;
        this.duration = duration;
        this.status = status;
    }

    public Iteration(int subject_id, String iteration_name, int duration, int status) {
        this.subject_id = subject_id;
        this.iteration_name = iteration_name;
        this.duration = duration;
        this.status = status;
    }

    public String getSubject_code() {
        return subject_code;
    }

    public void setSubject_code(String subject_code) {
        this.subject_code = subject_code;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }
    
    public int getIteration_id() {
        return iteration_id;
    }

    public void setIteration_id(int iteration_id) {
        this.iteration_id = iteration_id;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public String getIteration_name() {
        return iteration_name;
    }

    public void setIteration_name(String iteration_name) {
        this.iteration_name = iteration_name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
