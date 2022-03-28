package com.example.mvvmpractice.model;

import java.util.List;

public class Response {
    private String total;
    private String totalHits;
    private List<Image> hits;

    public Response(String total, String totalHits, List<Image> hits) {
        this.total = total;
        this.totalHits = totalHits;
        this.hits = hits;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotalHits() {
        return totalHits;
    }

    public void setTotalHits(String totalHits) {
        this.totalHits = totalHits;
    }

    public List<Image> getHits() {
        return hits;
    }

    public void setHits(List<Image> hits) {
        this.hits = hits;
    }
}
