/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cinema.model;

/**
 *
 * @author Mingwei
 */
public class LayoutTemplate {
    private String id;
    private String name;
    private String width;
    private String height;
    public LayoutTemplate(){}
    public LayoutTemplate(String id, String name){
        this.id = id;
        this.name = name;
    }
    
    @Override
    public String toString(){
        return name;
    }
    
    public String getKey(){
        return id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
    
}
