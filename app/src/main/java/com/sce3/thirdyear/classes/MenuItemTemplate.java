package com.sce3.thirdyear.classes;

/**
 * Created by win7 on 12/05/2015.
 */
public class MenuItemTemplate {
    private String title;
    private int icon;

    public MenuItemTemplate(){}

    public MenuItemTemplate(String title, int icon){
        this.title = title;
        this.icon = icon;
    }



    public String getTitle(){
        return this.title;
    }

    public int getIcon(){
        return this.icon;
    }


    public void setTitle(String title){
        this.title = title;
    }

    public void setIcon(int icon){
        this.icon = icon;
    }
}