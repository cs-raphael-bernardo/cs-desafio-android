package com.concretesolutions.desafio.raphael.desafioconcreteselutions.rest.models;

import java.util.List;

/**
 * Created by Raphael on 11/10/2016.
 */

public class GitHubResponse
{
    private String message;

    public String getMessage() {
        return message != null ? message : "Sem mensagem de retorno disponível.";
    }

    private String incomplete_results;

    private List<Items> items;

    private int total_count;

    public String getIncomplete_results ()
    {
        return incomplete_results;
    }

    public void setIncomplete_results (String incomplete_results)
    {
        this.incomplete_results = incomplete_results;
    }

    public List<Items> getItems ()
    {
        return items;
    }

    public void setItems (List<Items> items)
    {
        this.items = items;
    }

    public int getTotal_count ()
    {
        return total_count;
    }

    public void setTotal_count (int total_count)
    {
        this.total_count = total_count;
    }

    @Override
    public String toString()
    {
        return "Repositories [incomplete_results = "+incomplete_results+", items = "+items+", total_count = "+total_count+"]";
    }
}