package com.concretesolutions.desafio.raphael.desafioconcreteselutions.rest.models.pulls;

/**
 * Created by Raphael on 11/11/2016.
 */

public class Pull {
    private String title;
    private String body;
    private Head head;
    private int id;
    private String state;
    private String created_at;
    private String updated_at;
    private User user;
    private String html_url;


    public String getHtml_url() {
        return html_url;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        if (body != null && !body.isEmpty() && !body.equalsIgnoreCase(""))
            return body;
        else
            return "Pull request sem descrição.";
    }

    public Head getHead() {
        return head;
    }

    public int getId() {
        return id;
    }

    public String getState() {
        return state;
    }

    public String getCreatedAt() {
        if (created_at == null) {
            if (updated_at == null)
                return "--/--/-- (Data indisponível)";
            else
                return updated_at;
        } else
            return created_at;
    }

    public String getUpdatedAt() {
        return updated_at;
    }

    public User getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pull)) return false;

        Pull pull = (Pull) o;

        return id == pull.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}
